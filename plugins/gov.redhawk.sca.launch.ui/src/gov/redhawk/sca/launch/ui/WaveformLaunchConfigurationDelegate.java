/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.sca.launch.ui;

import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.util.LaunchWaveformJob;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.launch.ScaLaunchConfigurationConstants;
import gov.redhawk.sca.launch.ScaLaunchConfigurationUtil;
import gov.redhawk.sca.ui.ScaUI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.UIJob;

import CF.DataType;
import CF.DeviceAssignmentType;

/**
 * @since 8.0
 * 
 */
public class WaveformLaunchConfigurationDelegate extends LaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch, final IProgressMonitor mainMonitor)
	        throws CoreException {
		if (ILaunchManager.RUN_MODE.equals(mode)) {
			final ScaDomainManagerRegistry domainManagerRegistry = ScaPlugin.getDefault().getDomainManagerRegistry(null);
			final SubMonitor monitor = SubMonitor.convert(mainMonitor, "Launch Waveform...", 3);
			final String domainName = configuration.getAttribute(ScaLaunchConfigurationConstants.ATT_DOMAIN_NAME, (String) null);
			for (final ScaDomainManager domain : domainManagerRegistry.getDomains()) {
				if (domain.getLabel().equals(domainName)) {
					if (!domain.isConnected()) {
						try {
							domain.connect(monitor.newChild(1), RefreshDepth.SELF);
						} catch (final DomainConnectionException e) {
							throw new CoreException(new Status(IStatus.ERROR, ScaLauncherActivator.PLUGIN_ID, "Failed to connect to domain.", e));
						}
					} else {
						monitor.setWorkRemaining(2);
					}

					final Object waitLock = new Object();
					final SimpleDateFormat dateFormat = new SimpleDateFormat("DDD_HHmmssSSS");
					final String waveformName = configuration.getName() + "_" + dateFormat.format(new Date());
					final IPath waveformPath = new Path(configuration.getAttribute(ScaLaunchConfigurationConstants.ATT_PROFILE, ""));

					final DeviceAssignmentType[] deviceAssn = ScaLaunchConfigurationUtil.loadDeviceAssignment(configuration);

					final ScaComponent assemblyController = ScaFactory.eINSTANCE.createScaComponent();
					ScaLaunchConfigurationUtil.loadProperties(configuration, assemblyController);
					final List<DataType> configPropsList = new ArrayList<DataType>();
					for (final ScaAbstractProperty< ? > property : assemblyController.getProperties()) {
						if (!property.isDefaultValue()) {
							configPropsList.add(property.getProperty());
						}
					}
					final DataType[] configProps = configPropsList.toArray(new DataType[configPropsList.size()]);

					final boolean autoStart = configuration.getAttribute(ScaLaunchConfigurationConstants.ATT_START,
					        ScaLaunchConfigurationConstants.DEFAULT_VALUE_ATT_START);
					final boolean openEditor = configuration.getAttribute(ScaLaunchConfigurationConstants.ATT_OPEN,
					        ScaLaunchConfigurationConstants.DEFAULT_VALUE_ATT_OPEN);

					final LaunchWaveformJob job = new LaunchWaveformJob(domain, waveformName, waveformPath, deviceAssn, configProps, autoStart, waitLock);
					try {
						job.schedule();

						synchronized (waitLock) {
							while (job.getResult() == null) {
								if (monitor.isCanceled()) {
									job.cancel();
								} else {
									try {
										waitLock.wait(1000);
									} catch (final InterruptedException e) {
										break;
									}
								}
							}
						}

						if (job.getWaveform() != null && openEditor) {
							final UIJob openEditorJob = new UIJob("Open SCA Waveform Editor") {

								@Override
								public IStatus runInUIThread(final IProgressMonitor monitor) {
									try {
										final boolean useUri = !SWT.getPlatform().startsWith("rap");
										final IWorkbench workbench = PlatformUI.getWorkbench();
										final IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
										ScaUI.openEditorOnEObject(activePage, job.getWaveform(), useUri);
									} catch (final CoreException e) {
										return new Status(e.getStatus().getSeverity(), ScaLauncherActivator.PLUGIN_ID, e.getLocalizedMessage(), e);
									}
									return Status.OK_STATUS;
								}
							};
							openEditorJob.schedule();
						}
					} finally {
						monitor.done();
					}
				}
			}
		}
	}
}

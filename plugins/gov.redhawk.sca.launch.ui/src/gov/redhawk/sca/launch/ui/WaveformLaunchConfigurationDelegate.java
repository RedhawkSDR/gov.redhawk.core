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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import CF.DataType;
import CF.DeviceAssignmentType;
import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.launch.ScaLaunchConfigurationConstants;
import gov.redhawk.sca.launch.ScaLaunchConfigurationUtil;
import gov.redhawk.sca.model.jobs.LaunchWaveformJob;
import gov.redhawk.sca.ui.ScaUI;

/**
 * @since 8.0
 * 
 */
public class WaveformLaunchConfigurationDelegate extends LaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

	@Override
	public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch, final IProgressMonitor mainMonitor)
	        throws CoreException {
		if (ILaunchManager.RUN_MODE.equals(mode)) {
			run(configuration, launch, mainMonitor);
		}
	}

	private void run(final ILaunchConfiguration configuration, final ILaunch launch, final IProgressMonitor mainMonitor) throws CoreException {
		final ScaDomainManagerRegistry domainManagerRegistry = ScaPlugin.getDefault().getDomainManagerRegistry(null);
		final SubMonitor monitor = SubMonitor.convert(mainMonitor, "Launch Waveform...", 3);
		final String domainName = configuration.getAttribute(ScaLaunchConfigurationConstants.ATT_DOMAIN_NAME, (String) null);

		ScaDomainManager domain = domainManagerRegistry.findDomain(domainName);
		if (domain == null) {
			throw new CoreException(new Status(IStatus.ERROR, ScaLauncherActivator.PLUGIN_ID, "No connection found for domain: " + domainName));
		}

		if (!domain.isConnected()) {
			try {
				domain.connect(monitor.newChild(1), RefreshDepth.SELF);
			} catch (final DomainConnectionException e) {
				throw new CoreException(new Status(IStatus.ERROR, ScaLauncherActivator.PLUGIN_ID, "Failed to connect to domain.", e));
			}
		} else {
			monitor.setWorkRemaining(2);
		}

		final SimpleDateFormat dateFormat = new SimpleDateFormat("DDD_HHmmssSSS");
		final String waveformName = configuration.getName() + "_" + dateFormat.format(new Date());
		// TODO: Should this be ScaLaunchConfigurationUtil.getProfileURI(configuration)?
		final IPath waveformPath = new Path(configuration.getAttribute(ScaLaunchConfigurationConstants.ATT_PROFILE, ""));

		final DeviceAssignmentType[] deviceAssn = ScaLaunchConfigurationUtil.loadDeviceAssignment(configuration);

		final ScaComponent assemblyController = ScaFactory.eINSTANCE.createScaComponent();
		ScaLaunchConfigurationUtil.loadProperties(configuration, assemblyController);
		final List<DataType> configPropsList = new ArrayList<DataType>();
		for (final ScaAbstractProperty< ? > property : assemblyController.getProperties()) {
			if (!property.isDefaultValue()) {
				DataType dt = property.getProperty();
				if (dt != null) {
					configPropsList.add(dt);
				}
			}
		}
		final DataType[] configProps = configPropsList.toArray(new DataType[configPropsList.size()]);

		final boolean autoStart = configuration.getAttribute(ScaLaunchConfigurationConstants.ATT_START,
		        ScaLaunchConfigurationConstants.DEFAULT_VALUE_ATT_START);
		final boolean openEditor = configuration.getAttribute(ScaLaunchConfigurationConstants.ATT_OPEN,
		        ScaLaunchConfigurationConstants.DEFAULT_VALUE_ATT_OPEN);

		final LaunchWaveformJob job = new LaunchWaveformJob(domain, waveformName, waveformPath, deviceAssn, configProps, autoStart);
		job.schedule();
		try {
			job.join(0, monitor);
		} catch (OperationCanceledException | InterruptedException e) {
			throw new CoreException(new Status(IStatus.CANCEL, ScaLauncherActivator.PLUGIN_ID, "Cancelled while launching", e));
		}

		final ScaWaveform waveform = job.getWaveform();
		if (waveform == null) {
			return;
		}

		if (openEditor) {
			final IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			ScaUI.openEditor(activePage, waveform);
		}
	}
}

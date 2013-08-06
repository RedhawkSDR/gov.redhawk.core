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
package gov.redhawk.sca.internal.ui.wizards;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.util.LaunchWaveformJob;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.launch.ScaLaunchConfigurationConstants;
import gov.redhawk.sca.launch.ScaLaunchConfigurationUtil;
import gov.redhawk.sca.ui.ScaUI;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.ui.preferences.RedhawkUIPreferenceConstants;

import java.lang.reflect.InvocationTargetException;

import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

import CF.DataType;
import CF.DeviceAssignmentType;

public class LaunchWaveformWizard extends BasicLaunchWaveformWizard {

	public LaunchWaveformWizard(final ScaDomainManager domMgr) {
		super(domMgr);
	}

	@Override
	public boolean performFinish() {
		final WaveformSelectionWizardPage waveformPage = getWaveformPage();
		final ApplicationCreationPropertyEditWizardPage propertyValuePage = getPropertyValuePage();
		final DeviceAssignmentWizardPage deviceAssignmentPage = getDeviceAssignmentPage();
		waveformPage.saveWidgetValues();
		final SoftwareAssembly sad = waveformPage.getSoftwareAssembly();
		final boolean autoStart = waveformPage.isAutoStart();
		final String name = waveformPage.getWaveformName().trim();
		final IWizardPage endingPage = getContainer().getCurrentPage();
		final DataType[] configProps;
		final DeviceAssignmentType[] deviceAssn;
		boolean createLaunchConfiguration = false;
		if (endingPage instanceof DeviceAssignmentWizardPage) {
			configProps = propertyValuePage.getCreationProperties();
			deviceAssn = deviceAssignmentPage.getDeviceAssignment();
			createLaunchConfiguration = true;
		} else if (endingPage instanceof ApplicationCreationPropertyEditWizardPage) {
			configProps = propertyValuePage.getCreationProperties();
			deviceAssn = new DeviceAssignmentType[0];
			createLaunchConfiguration = true;
		} else {
			configProps = new DataType[0];
			deviceAssn = new DeviceAssignmentType[0];
		}

		if (createLaunchConfiguration) {
			try {
				createLaunchConfiguration(sad, autoStart, propertyValuePage.getPropertyContainer(), deviceAssn);
			} catch (final CoreException e) {
				StatusManager.getManager().handle(e, ScaUiPlugin.PLUGIN_ID);
			}
		}

		final IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		final Object waitLock = new Object();
		final LaunchWaveformJob job = new LaunchWaveformJob(getDomMgr(),
		        name,
		        new Path(sad.eResource().getURI().path()),
		        deviceAssn,
		        configProps,
		        autoStart,
		        waitLock);

		try {
			getContainer().run(true, true, new IRunnableWithProgress() {

				public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

					monitor.beginTask("Launching waveform " + name, IProgressMonitor.UNKNOWN);
					try {
						job.schedule();

						synchronized (waitLock) {
							while (job.getResult() == null) {
								if (monitor.isCanceled()) {
									job.cancel();
									throw new InterruptedException();
								} else {
									waitLock.wait(1000);
								}
							}
						}

						if (job.getWaveform() != null) {
							activePage.getWorkbenchWindow().getShell().getDisplay().asyncExec(new Runnable() {

								public void run() {
									try {
										final boolean useUri = !SWT.getPlatform().startsWith("rap");
										ScaUI.openEditorOnEObject(activePage, job.getWaveform(), useUri);
									} catch (final CoreException e) {
										StatusManager.getManager().handle(e, ScaUiPlugin.PLUGIN_ID);
									}
								}

							});
						}
					} finally {
						monitor.done();
					}
				}
			});
		} catch (final InvocationTargetException e) {
			String msg = e.getMessage();
			if (e.getCause() != null) {
				msg = e.getCause().getMessage();
			}

			if (msg == null || msg.length() == 0) {
				msg = "Unknown Error.";
			}
			final IStatus status = new Status(IStatus.ERROR, ScaPlugin.PLUGIN_ID, msg, e.getCause());
			StatusManager.getManager().handle(status, StatusManager.SHOW);
			return false;
		} catch (final InterruptedException e) {
			return false;
		}

		if (!job.getSilentStatus().isOK()) {
			if (job.getSilentStatus().getSeverity() != IStatus.CANCEL) {
				StatusManager.getManager().handle(job.getSilentStatus(), StatusManager.SHOW);
				return false;
			}
		}

		return true;
	}

	private void createLaunchConfiguration(final SoftwareAssembly sad, final boolean autoStart, final ScaPropertyContainer< ?, ? > assemblyController,
	        final DeviceAssignmentType[] deviceAssn) throws CoreException {
		boolean createConfiguration = false;
		final String promptOption = ScaUiPlugin.getDefault().getPreferenceStore().getString(RedhawkUIPreferenceConstants.CREATE_WAVEFORM_LAUNCH_CONFIGURATION);
		if (MessageDialogWithToggle.PROMPT.equals(promptOption)) {
			final MessageDialogWithToggle dialog = MessageDialogWithToggle.openYesNoQuestion(getShell(),
			        "New Launch Configuration",
			        "You have created a custom waveform configuration.\n\nWould you like to save this configuration?",
			        "Remember my decision",
			        false,
			        ScaUiPlugin.getDefault().getPreferenceStore(),
			        RedhawkUIPreferenceConstants.CREATE_WAVEFORM_LAUNCH_CONFIGURATION);
			if (dialog.getReturnCode() == 2) {
				createConfiguration = true;
			}
			if (dialog.getToggleState()) {
				ScaUiPlugin.getDefault().getPreferenceStore().setValue(RedhawkUIPreferenceConstants.CREATE_WAVEFORM_LAUNCH_CONFIGURATION, createConfiguration);
			}
		} else if (MessageDialogWithToggle.ALWAYS.equals(promptOption)) {
			createConfiguration = true;
		} else {
			createConfiguration = false;
		}

		if (createConfiguration) {
			final ILaunchConfigurationType configType = getConfigurationType();
			final ILaunchConfigurationWorkingCopy wc = configType.newInstance(null, getLaunchManager().generateLaunchConfigurationName(sad.getName()));
			wc.setAttribute(ScaLaunchConfigurationConstants.ATT_DOMAIN_NAME, getDomMgr().getName());
			wc.setAttribute(ScaLaunchConfigurationConstants.ATT_START, autoStart);
			wc.setAttribute(ScaLaunchConfigurationConstants.ATT_OPEN, ScaLaunchConfigurationConstants.DEFAULT_VALUE_ATT_OPEN);
			ScaLaunchConfigurationUtil.saveProperties(wc, assemblyController);
			ScaLaunchConfigurationUtil.saveDeviceAssignment(wc, deviceAssn);
			wc.setAttribute(ScaLaunchConfigurationConstants.ATT_PROFILE, sad.eResource().getURI().path());

			final ILaunchConfiguration[] currentConfigurations = getLaunchManager().getLaunchConfigurations(getConfigurationType());
			for (final ILaunchConfiguration currentConfig : currentConfigurations) {
				if (currentConfig.contentsEqual(wc)) {
					return;
				}
			}
			wc.doSave();
		}
	}

	protected ILaunchConfigurationType getConfigurationType() {
		return getLaunchManager().getLaunchConfigurationType(ScaLaunchConfigurationConstants.ID_WAVEFORM);
	}

	private ILaunchManager getLaunchManager() {
		return DebugPlugin.getDefault().getLaunchManager();
	}

}

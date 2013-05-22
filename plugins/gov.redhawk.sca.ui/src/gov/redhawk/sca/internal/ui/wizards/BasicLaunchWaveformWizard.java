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
import gov.redhawk.model.sca.util.LaunchWaveformJob;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.ui.ScaUI;
import gov.redhawk.sca.ui.ScaUiPlugin;

import java.lang.reflect.InvocationTargetException;

import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

import CF.DataType;
import CF.DeviceAssignmentType;

public class BasicLaunchWaveformWizard extends Wizard {
	private static final String PROPERTY_PAGE = "propertyPage";
	private static final String WAVEFORM_SELECTION_PAGE = "waveformSelection";
	private static final String LAUNCH_WAVEFORM_DIALOG_SETTINGS_SECTION = "gov.redhawk.sca.internal.ui.wizards.LaunchWaveformWizard";
	private static final String ASSIGNMENT_PAGE = "ASSIGNMENT_PAGE";
	private final WaveformSelectionWizardPage waveformPage = new WaveformSelectionWizardPage(BasicLaunchWaveformWizard.WAVEFORM_SELECTION_PAGE);
	private final DeviceAssignmentWizardPage deviceAssignmentPage;
	private final ApplicationCreationPropertyEditWizardPage propertyValuePage = new ApplicationCreationPropertyEditWizardPage(BasicLaunchWaveformWizard.PROPERTY_PAGE);

	private final ScaDomainManager domMgr;

	public BasicLaunchWaveformWizard(final ScaDomainManager domMgr) {
		this.domMgr = domMgr;
		this.deviceAssignmentPage = new DeviceAssignmentWizardPage(BasicLaunchWaveformWizard.ASSIGNMENT_PAGE, getDomMgr());
		this.waveformPage.init(this.domMgr);

		this.setWindowTitle("Launch Waveform");
		this.setNeedsProgressMonitor(true);

		IDialogSettings section = ScaUiPlugin.getDefault().getDialogSettings().getSection(BasicLaunchWaveformWizard.LAUNCH_WAVEFORM_DIALOG_SETTINGS_SECTION);
		if (section == null) {
			section = ScaUiPlugin.getDefault().getDialogSettings().addNewSection(BasicLaunchWaveformWizard.LAUNCH_WAVEFORM_DIALOG_SETTINGS_SECTION);
		}
		setDialogSettings(section);
	}

	public WaveformSelectionWizardPage getWaveformPage() {
		return this.waveformPage;
	}

	public ApplicationCreationPropertyEditWizardPage getPropertyValuePage() {
		return this.propertyValuePage;
	}

	public DeviceAssignmentWizardPage getDeviceAssignmentPage() {
		return this.deviceAssignmentPage;
	}

	@Override
	public void addPages() {
		super.addPage(this.waveformPage);
		super.addPage(this.propertyValuePage);
		super.addPage(this.deviceAssignmentPage);
	}

	@Override
	public IWizardPage getNextPage(final IWizardPage page) {
		if (page.equals(this.waveformPage)) {
			this.propertyValuePage.init(this.waveformPage.getSoftwareAssembly());
			this.deviceAssignmentPage.init(this.waveformPage.getSoftwareAssembly());
		}
		return super.getNextPage(page);
	}

	public ScaDomainManager getDomMgr() {
		return this.domMgr;
	}

	@Override
	public boolean performFinish() {
		this.waveformPage.saveWidgetValues();
		final SoftwareAssembly sad = this.waveformPage.getSoftwareAssembly();
		final boolean autoStart = this.waveformPage.isAutoStart();
		final String name = this.waveformPage.getWaveformName().trim();
		final IWizardPage endingPage = getContainer().getCurrentPage();
		final DataType[] configProps;
		final DeviceAssignmentType[] deviceAssn;
		if (endingPage instanceof DeviceAssignmentWizardPage) {
			configProps = this.propertyValuePage.getCreationProperties();
			deviceAssn = this.deviceAssignmentPage.getDeviceAssignment();
		} else if (endingPage instanceof ApplicationCreationPropertyEditWizardPage) {
			configProps = this.propertyValuePage.getCreationProperties();
			deviceAssn = new DeviceAssignmentType[0];
		} else {
			configProps = new DataType[0];
			deviceAssn = new DeviceAssignmentType[0];
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

}

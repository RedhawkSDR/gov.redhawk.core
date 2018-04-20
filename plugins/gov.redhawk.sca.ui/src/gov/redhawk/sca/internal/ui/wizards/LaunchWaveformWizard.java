/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.sca.internal.ui.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

import CF.DataType;
import CF.DeviceAssignmentType;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.model.jobs.LaunchWaveformJob;
import gov.redhawk.sca.ui.ScaUI;
import gov.redhawk.sca.ui.ScaUiPlugin;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

public class LaunchWaveformWizard extends Wizard {
	private static final String PROPERTY_PAGE = "propertyPage";
	private static final String WAVEFORM_SELECTION_PAGE = "waveformSelection";
	private static final String LAUNCH_WAVEFORM_DIALOG_SETTINGS_SECTION = "gov.redhawk.sca.internal.ui.wizards.LaunchWaveformWizard";
	private static final String ASSIGNMENT_PAGE = "ASSIGNMENT_PAGE";
	private final WaveformSelectionWizardPage waveformPage = new WaveformSelectionWizardPage(LaunchWaveformWizard.WAVEFORM_SELECTION_PAGE);
	private final DeviceAssignmentWizardPage deviceAssignmentPage;
	private final ApplicationCreationPropertyEditWizardPage propertyValuePage = new ApplicationCreationPropertyEditWizardPage(LaunchWaveformWizard.PROPERTY_PAGE);

	private final ScaDomainManager domMgr;

	public LaunchWaveformWizard(final ScaDomainManager domMgr) {
		this.domMgr = domMgr;
		this.deviceAssignmentPage = new DeviceAssignmentWizardPage(LaunchWaveformWizard.ASSIGNMENT_PAGE, getDomMgr());
		this.waveformPage.init(this.domMgr);

		this.setWindowTitle("Launch Waveform");
		this.setNeedsProgressMonitor(true);

		IDialogSettings section = ScaUiPlugin.getDefault().getDialogSettings().getSection(LaunchWaveformWizard.LAUNCH_WAVEFORM_DIALOG_SETTINGS_SECTION);
		if (section == null) {
			section = ScaUiPlugin.getDefault().getDialogSettings().addNewSection(LaunchWaveformWizard.LAUNCH_WAVEFORM_DIALOG_SETTINGS_SECTION);
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

	protected void reinitalizePages() {
		this.propertyValuePage.init(this.waveformPage.getSoftwareAssembly());
		this.deviceAssignmentPage.init(this.waveformPage.getSoftwareAssembly());
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
		final DataType[] configProps;
		final DeviceAssignmentType[] deviceAssn;
		
		if (this.propertyValuePage.getCreationProperties() == null) {
			configProps = new DataType[0];
		} else {
			configProps = this.propertyValuePage.getCreationProperties();
		}
		
		if (this.deviceAssignmentPage.getDeviceAssignment() == null) {
			deviceAssn = new DeviceAssignmentType[0];
		} else {
			deviceAssn = this.deviceAssignmentPage.getDeviceAssignment();
		}

		final IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		final LaunchWaveformJob job = new LaunchWaveformJob(getDomMgr(),
		        name,
		        new Path(sad.eResource().getURI().path()),
		        deviceAssn,
		        configProps,
		        autoStart);

		try {
			getContainer().run(true, true, monitor -> {
				monitor.beginTask("Launching waveform " + name, IProgressMonitor.UNKNOWN);
				job.schedule();
				job.join(0, monitor);

				ScaWaveform waveform = job.getWaveform();
				if (waveform == null) {
					return;
				}
				ScaUI.openEditor(activePage, waveform);
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

		if (!job.getSilentStatus().isOK() && job.getSilentStatus().getSeverity() != IStatus.CANCEL) {
			StatusManager.getManager().handle(job.getSilentStatus(), StatusManager.SHOW);
			return false;
		}

		return true;
	}

}

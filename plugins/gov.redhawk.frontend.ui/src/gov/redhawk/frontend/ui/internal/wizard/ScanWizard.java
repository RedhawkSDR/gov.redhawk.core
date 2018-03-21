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
package gov.redhawk.frontend.ui.internal.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import FRONTEND.ScanningTunerPackage.ScanMode;
import FRONTEND.ScanningTunerPackage.ScanModeDefinition;
import FRONTEND.ScanningTunerPackage.ScanStrategy;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.jobs.ScanJob;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

/* package */ class ScanWizard extends Wizard {

	private TunerStatus tuner;

	private ScanWizardPage scanWizardPage;
	private ManualScanPage manualScanPage;
	private SpanScanPage spanScanPage;
	private DiscreteScanPage discreteScanPage;

	public ScanWizard(TunerStatus tuner) {
		setWindowTitle("Tuner Scan");
		setNeedsProgressMonitor(true);
		this.tuner = tuner;
	}

	@Override
	public void addPages() {
		scanWizardPage = new ScanWizardPage();
		addPage(scanWizardPage);
		manualScanPage = new ManualScanPage();
		addPage(manualScanPage);
		spanScanPage = new SpanScanPage();
		addPage(spanScanPage);
		discreteScanPage = new DiscreteScanPage();
		addPage(discreteScanPage);
	}

	@Override
	public boolean canFinish() {
		if (!scanWizardPage.isPageComplete()) {
			return false;
		}
		IWizardPage secondPage = getNextPage(scanWizardPage);
		return secondPage != null && secondPage.isPageComplete();
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		ScanMode scanMode = scanWizardPage.getScanMode();
		if (page != scanWizardPage || scanMode == null) {
			return null;
		}
		switch (scanMode.value()) {
		case ScanMode._MANUAL_SCAN:
			return manualScanPage;
		case ScanMode._SPAN_SCAN:
			return spanScanPage;
		case ScanMode._DISCRETE_SCAN:
			return discreteScanPage;
		default:
			return null;
		}
	}

	@Override
	public boolean performFinish() {
		// Keep the current settings for the next time the wizard is opened
		saveSettings();

		ScaDevice< ? > device = ScaModelCommand.runExclusive(tuner, () -> {
			return ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
		});
		final IStatus[] status = { Status.CANCEL_STATUS };

		// Create the strategy
		ScanStrategy strategy = new ScanStrategy();
		strategy.scan_mode = scanWizardPage.getScanMode();
		strategy.scan_definition = new ScanModeDefinition();
		switch (strategy.scan_mode.value()) {
		case ScanMode._MANUAL_SCAN:
			strategy.scan_definition.center_frequency(manualScanPage.getCenterFrequency());
			break;
		case ScanMode._SPAN_SCAN:
			strategy.scan_definition.freq_scan_list(spanScanPage.getScanList());
			break;
		case ScanMode._DISCRETE_SCAN:
			strategy.scan_definition.discrete_freq_list(discreteScanPage.getFrequencies());
			break;
		default:
		}
		strategy.control_mode = scanWizardPage.getControlMode();
		strategy.control_value = scanWizardPage.getControlValue();

		ScanJob scanJob = new ScanJob(device, tuner.getAllocationID(), strategy, scanWizardPage.getStartTime());
		if (scanWizardPage.isBackground()) {
			scanJob.schedule();
			return true;
		}

		try {
			getContainer().run(true, true, monitor -> {
				status[0] = scanJob.run(monitor);
			});
		} catch (InvocationTargetException e) {
			status[0] = new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, Messages.ScanWizard_InvocationTargetExceptionMsg, e);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return false;
		}

		if (!status[0].isOK()) {
			ErrorDialog.openError(getShell(), Messages.ScanWizard_ErrorTitle, Messages.ScanWizard_ErrorMessage, status[0]);
			return false;
		}
		return true;
	}

	public void loadSettings() {
		SettingsWizardPage page = (SettingsWizardPage) getStartingPage();
		while (page != null) {
			page.loadSettings();
			page = (SettingsWizardPage) getNextPage(page);
		}
	}

	public void saveSettings() {
		SettingsWizardPage page = (SettingsWizardPage) getStartingPage();
		while (page != null) {
			page.saveSettings();
			page = (SettingsWizardPage) getNextPage(page);
		}
	}

}

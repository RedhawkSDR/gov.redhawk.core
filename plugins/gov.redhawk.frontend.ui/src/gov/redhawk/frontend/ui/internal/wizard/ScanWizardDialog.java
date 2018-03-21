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

import java.io.IOException;

import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator;

public class ScanWizardDialog extends WizardDialog {

	private static final IDialogSettings SETTINGS = new DialogSettings("ScanWizard"); //$NON-NLS-1$

	private static final String LOAD_IMAGE = "$nl$/icons/load.png"; //$NON-NLS-1$
	private static final String SAVE_IMAGE = "$nl$/icons/save.png"; //$NON-NLS-1$
	private static final int LOAD_ID = IDialogConstants.CLIENT_ID;
	private static final int SAVE_ID = IDialogConstants.CLIENT_ID + 1;

	private Button loadButton;
	private Button saveButton;

	public ScanWizardDialog(Shell parentShell, TunerStatus tunerStatus) {
		super(parentShell, new ScanWizard(tunerStatus));
		((Wizard) getWizard()).setDialogSettings(SETTINGS);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		ResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources(), parent);

		loadButton = createButton(parent, LOAD_ID, "", false); //$NON-NLS-1$
		loadButton.setImage(resourceManager.createImage(FrontEndUIActivator.getDefault().getImageDescriptor(LOAD_IMAGE)));
		loadButton.setToolTipText(Messages.ScanWizardDialog_LoadButton_ToolTip);
		loadButton.setLayoutData(null);

		saveButton = createButton(parent, SAVE_ID, "", false); //$NON-NLS-1$
		saveButton.setImage(resourceManager.createImage(FrontEndUIActivator.getDefault().getImageDescriptor(SAVE_IMAGE)));
		saveButton.setToolTipText(Messages.ScanWizardDialog_SaveButton_ToolTip);
		saveButton.setLayoutData(null);

		super.createButtonsForButtonBar(parent);
	}

	@Override
	protected void buttonPressed(int buttonId) {
		switch (buttonId) {
		case LOAD_ID:
			doLoad();
			break;
		case SAVE_ID:
			doSave();
			break;
		default:
			super.buttonPressed(buttonId);
			break;
		}
	}

	private void doLoad() {
		FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
		setFileDialogDefaults(dialog);
		String fileName = dialog.open();
		if (fileName == null) {
			return;
		}
		try {
			getWizard().getDialogSettings().load(fileName);
		} catch (IOException e) {
			MessageDialog.openError(getShell(), Messages.ScanWizardDialog_LoadErrorTitle, e.getMessage());
			return;
		}
		((ScanWizard) getWizard()).loadSettings();
	}

	private void doSave() {
		FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
		setFileDialogDefaults(dialog);
		String fileName = dialog.open();
		if (fileName == null) {
			return;
		}
		((ScanWizard) getWizard()).saveSettings();
		try {
			getWizard().getDialogSettings().save(fileName);
		} catch (IOException e) {
			MessageDialog.openError(getShell(), Messages.ScanWizardDialog_SaveErrorTitle, e.getMessage());
			return;
		}
	}

	private void setFileDialogDefaults(FileDialog dialog) {
		dialog.setFilterNames(new String[] { Messages.ScanWizardDialog_ScanSettingsFilterName });
		dialog.setFilterExtensions(new String[] { "*.xml" }); //$NON-NLS-1$
		dialog.setOverwrite(true);
	}

	@Override
	public void updateButtons() {
		// Only show the "open" button on the first page
		if (getCurrentPage() != null && getCurrentPage().getPreviousPage() == null) {
			loadButton.setVisible(true);
		} else {
			loadButton.setVisible(false);
		}
		saveButton.setEnabled(getWizard().canFinish());
		super.updateButtons();
	}
}

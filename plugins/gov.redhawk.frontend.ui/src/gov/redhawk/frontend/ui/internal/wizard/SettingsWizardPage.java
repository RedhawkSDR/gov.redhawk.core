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

import java.text.NumberFormat;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import gov.redhawk.frontend.ui.FrontEndUIActivator;

/* package */ abstract class SettingsWizardPage extends WizardPage {

	private static final String ADD_IMAGE = "$nl$/icons/add.png"; //$NON-NLS-1$
	private static final String REMOVE_IMAGE = "$nl$/icons/remove.png"; //$NON-NLS-1$

	private NumberFormat nf;

	private Button addButton;
	private Button removeButton;

	protected SettingsWizardPage(String pageName) {
		super(pageName);
		nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);
		nf.setMaximumFractionDigits(6);
		nf.setGroupingUsed(false);
	}

	public abstract void loadSettings();

	public abstract void saveSettings();

	protected IDialogSettings getPageSettings() {
		IDialogSettings settings = getDialogSettings();
		if (settings == null) {
			return new DialogSettings("temp"); //$NON-NLS-1$
		}
		IDialogSettings sectionSettings = settings.getSection(getClass().getSimpleName());
		if (sectionSettings == null) {
			sectionSettings = settings.addNewSection(getClass().getSimpleName());
		}
		return sectionSettings;
	}

	protected UpdateValueStrategy getStringToDoubleFreqStrategy(int updatePolicy) {
		UpdateValueStrategy stringToDoubleFreqStrategy = new UpdateValueStrategy(updatePolicy);
		stringToDoubleFreqStrategy.setConverter(IConverter.create(String.class, Double.class, value -> {
			return Double.parseDouble((String) value) * 1e6;
		}));
		stringToDoubleFreqStrategy.setAfterGetValidator(value -> {
			String stringValue = (String) value;
			if (stringValue.isEmpty()) {
				return ValidationStatus.error(Messages.SettingsWizardPage_Error_NoValue);
			}
			try {
				Double.parseDouble(stringValue);
			} catch (NumberFormatException e) {
				return ValidationStatus.error(Messages.SettingsWizardPage_Error_InvalidNumber);
			}
			return ValidationStatus.ok();
		});
		stringToDoubleFreqStrategy.setAfterConvertValidator(value -> {
			double val = (Double) value;
			if (val <= 0) {
				return ValidationStatus.error(Messages.SettingsWizardPage_Error_ValueMustBePositiveNonZero);
			}
			return ValidationStatus.ok();
		});
		return stringToDoubleFreqStrategy;
	}

	protected UpdateValueStrategy getDoubleToStringFreqStrategy() {
		UpdateValueStrategy doubleToStringFreqStrategy = new UpdateValueStrategy();
		doubleToStringFreqStrategy.setConverter(IConverter.create(Double.class, String.class, value -> {
			if (value == null) {
				return ""; //$NON-NLS-1$
			}
			return nf.format((Double) value / 1e6);
		}));
		return doubleToStringFreqStrategy;
	}

	protected Button getAddButton() {
		return addButton;
	}

	protected Button getRemoveButton() {
		return removeButton;
	}

	/**
	 * Creates add and remove buttons.
	 * @param parent
	 */
	protected void createButtons(Composite parent) {
		Composite buttonComposite = new Composite(parent, SWT.NONE);
		buttonComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		buttonComposite.setLayout(new GridLayout(1, false));

		ResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources(), parent);

		addButton = new Button(buttonComposite, SWT.PUSH);
		addButton.setImage(resourceManager.createImage(FrontEndUIActivator.getDefault().getImageDescriptor(ADD_IMAGE)));
		addButton.setToolTipText(Messages.SettingsWizardPage_AddButton_ToolTip);
		addButton.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));

		removeButton = new Button(buttonComposite, SWT.PUSH);
		removeButton.setImage(resourceManager.createImage(FrontEndUIActivator.getDefault().getImageDescriptor(REMOVE_IMAGE)));
		removeButton.setToolTipText(Messages.SettingsWizardPage_RemoveButton_ToolTip);
		removeButton.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));
		removeButton.setEnabled(false);
	}
}

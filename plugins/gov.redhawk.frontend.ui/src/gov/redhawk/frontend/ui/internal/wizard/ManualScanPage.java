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

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/* package */ class ManualScanPage extends SettingsWizardPage {

	private WritableValue<Double> centerFrequency = new WritableValue<>();

	private Text centerFreqText;

	ManualScanPage() {
		super(Messages.ManualScanPage_PageName);
	}

	@Override
	public void createControl(Composite parent) {
		setTitle(Messages.ManualScanPage_PageTitle);
		setDescription(Messages.ManualScanPage_PageDescription);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		setControl(composite);

		createFields(composite);
		createBindings();
	}

	private void createFields(Composite parent) {
		new Label(parent, SWT.NONE).setText(Messages.ManualScanPage_CenterFreq_Text);
		centerFreqText = new Text(parent, SWT.BORDER);
		centerFreqText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	}

	private void createBindings() {
		DataBindingContext context = new DataBindingContext();

		Binding binding = context.bindValue(WidgetProperties.text(SWT.Modify).observe(centerFreqText), centerFrequency, getStringToDoubleFreqStrategy(UpdateValueStrategy.POLICY_UPDATE),
			getDoubleToStringFreqStrategy());
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);

		WizardPageSupport.create(this, context);
	}

	@Override
	public void loadSettings() {
		IDialogSettings settings = getPageSettings();
		try {
			centerFrequency.setValue(settings.getDouble("centerFrequency")); //$NON-NLS-1$
		} catch (NumberFormatException e) {
			// PASS
		}

	}

	@Override
	public void saveSettings() {
		IDialogSettings settings = getPageSettings();
		settings.put("centerFrequency", centerFrequency.getValue()); //$NON-NLS-1$
	}

	public double getCenterFrequency() {
		return centerFrequency.getValue();
	}
}

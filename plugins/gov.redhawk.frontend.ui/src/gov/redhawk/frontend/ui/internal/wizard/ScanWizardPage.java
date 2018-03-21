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

import java.util.Calendar;
import java.util.TimeZone;
import java.util.function.Function;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import BULKIO.PrecisionUTCTime;
import BULKIO.TCM_CPU;
import BULKIO.TCS_VALID;
import FRONTEND.ScanningTunerPackage.OutputControlMode;
import FRONTEND.ScanningTunerPackage.ScanMode;

public class ScanWizardPage extends SettingsWizardPage {

	private static final String MANUAL_SCAN = "MANUAL_SCAN"; //$NON-NLS-1$
	private static final String SPAN_SCAN = "SPAN_SCAN"; //$NON-NLS-1$
	private static final String DISCRETE_SCAN = "DISCRETE_SCAN"; //$NON-NLS-1$
	private static final String TIME_BASED = "TIME_BASED"; //$NON-NLS-1$
	private static final String SAMPLE_BASED = "SAMPLE_BASED"; //$NON-NLS-1$

	private ScanModel scanModel;

	private ComboViewer modeCombo;
	private ComboViewer controlModeCombo;
	private Text controlValueText;
	private Spinner delaySpinner;
	private Button backgroundJobButton;

	protected ScanWizardPage() {
		super(Messages.ScanWizardPage_PageName);
		scanModel = new ScanModel();
	}

	@Override
	public void createControl(Composite parent) {
		setTitle(Messages.ScanWizardPage_PageTitle);
		setDescription(Messages.ScanWizardPage_PageDescription);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		setControl(composite);

		createControls(composite);
		bindControls();
	}

	private void createControls(Composite parent) {
		GridDataFactory grab = GridDataFactory.fillDefaults().grab(true, false);

		Label modeLabel = new Label(parent, SWT.NONE);
		modeLabel.setText(Messages.ScanWizardPage_Mode_Text);
		modeCombo = new ComboViewer(parent, SWT.NONE | SWT.READ_ONLY);
		modeCombo.getControl().setToolTipText(Messages.ScanWizardPage_Mode_ToolTip);
		modeCombo.setContentProvider(new ArrayContentProvider());
		modeCombo.setInput(new String[] { MANUAL_SCAN, SPAN_SCAN, DISCRETE_SCAN });
		modeCombo.getControl().setLayoutData(grab.create());

		Label controlModeLabel = new Label(parent, SWT.NONE);
		controlModeLabel.setText(Messages.ScanWizardPage_ControlMode_Text);
		controlModeCombo = new ComboViewer(parent, SWT.NONE | SWT.READ_ONLY);
		controlModeCombo.getControl().setToolTipText(Messages.ScanWizardPage_ControlMode_ToolTip);
		controlModeCombo.setContentProvider(new ArrayContentProvider());
		controlModeCombo.setInput(new String[] { TIME_BASED, SAMPLE_BASED });
		controlModeCombo.getControl().setLayoutData(grab.create());

		Label controlValueLabel = new Label(parent, SWT.NONE);
		controlValueLabel.setText(Messages.ScanWizardPage_ControlValue_Text);
		controlValueText = new Text(parent, SWT.BORDER);
		controlValueText.setLayoutData(grab.create());

		controlModeCombo.addSelectionChangedListener(event -> {
			// Adjust the tooltip on the control limit field based on the control mode
			Object selection = event.getStructuredSelection().getFirstElement();
			if (selection == null) {
				controlValueText.setToolTipText(Messages.ScanWizardPage_Error_NoControlMode);
				return;
			}
			switch ((String) selection) {
			case TIME_BASED:
				controlValueText.setToolTipText(Messages.ScanWizardPage_ControlValue_ToolTip_Seconds);
				break;
			case SAMPLE_BASED:
				controlValueText.setToolTipText(Messages.ScanWizardPage_ControlValue_ToolTip_Samples);
				break;
			default:
				controlValueText.setToolTipText(Messages.ScanWizardPage_Error_NoControlMode);
			}
		});

		Label label = new Label(parent, SWT.NONE);
		label.setText(Messages.ScanWizardPage_Delay_Text);
		delaySpinner = new Spinner(parent, SWT.NONE);
		delaySpinner.setToolTipText(Messages.ScanWizardPage_Delay_Description);
		delaySpinner.setValues(10, 0, 60 * 60 * 24, 0, 1, 10);
		delaySpinner.setLayoutData(grab.create());

		Composite bgJobComp = new Composite(parent, SWT.NONE);
		bgJobComp.setLayout(new GridLayout(2, false));
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).align(SWT.END, SWT.CENTER).applyTo(bgJobComp);
		backgroundJobButton = new Button(bgJobComp, SWT.CHECK);
		backgroundJobButton.setText(Messages.ScanWizardPage_RunInBackground_Text);
	}

	@SuppressWarnings("unchecked")
	private void bindControls() {
		DataBindingContext context = new DataBindingContext();

		IValidator noNulls = value -> {
			if (value == null) {
				return ValidationStatus.error(Messages.ScanWizardPage_Error_NoValue);
			}
			return ValidationStatus.ok();
		};

		UpdateValueStrategy modeTargetToModel = new UpdateValueStrategy();
		modeTargetToModel.setAfterGetValidator(noNulls);
		Function<String, ScanMode> modeFuncToModel = value -> {
			if (value == null) {
				return null;
			}
			switch (value) {
			case MANUAL_SCAN:
				return ScanMode.MANUAL_SCAN;
			case SPAN_SCAN:
				return ScanMode.SPAN_SCAN;
			case DISCRETE_SCAN:
				return ScanMode.DISCRETE_SCAN;
			default:
				return null;
			}
		};
		modeTargetToModel.setConverter(IConverter.create(String.class, ScanMode.class, modeFuncToModel));
		UpdateValueStrategy modeModelToTarget = new UpdateValueStrategy();
		Function<ScanMode, String> modeFuncToTarget = value -> {
			if (value == null) {
				return null;
			}
			switch (value.value()) {
			case ScanMode._MANUAL_SCAN:
				return MANUAL_SCAN;
			case ScanMode._SPAN_SCAN:
				return SPAN_SCAN;
			case ScanMode._DISCRETE_SCAN:
				return DISCRETE_SCAN;
			default:
				return null;
			}
		};
		modeModelToTarget.setConverter(IConverter.create(ScanMode.class, String.class, modeFuncToTarget));
		Binding binding = context.bindValue(ViewersObservables.observeSingleSelection(modeCombo), BeanProperties.value("mode").observe(scanModel), //$NON-NLS-1$
			modeTargetToModel, modeModelToTarget);
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);

		UpdateValueStrategy controlModeTargetToModel = new UpdateValueStrategy();
		controlModeTargetToModel.setAfterGetValidator(noNulls);
		Function<String, OutputControlMode> controlModeFuncToModel = value -> {
			if (value == null) {
				return null;
			}
			switch (value) {
			case TIME_BASED:
				return OutputControlMode.TIME_BASED;
			case SAMPLE_BASED:
				return OutputControlMode.SAMPLE_BASED;
			default:
				return null;
			}
		};
		controlModeTargetToModel.setConverter(IConverter.create(String.class, OutputControlMode.class, controlModeFuncToModel));
		UpdateValueStrategy controlModeModelToTarget = new UpdateValueStrategy();
		Function<OutputControlMode, String> controlModeFuncToTarget = value -> {
			if (value == null) {
				return null;
			}
			switch (value.value()) {
			case OutputControlMode._TIME_BASED:
				return TIME_BASED;
			case OutputControlMode._SAMPLE_BASED:
				return SAMPLE_BASED;
			default:
				return null;
			}
		};
		controlModeModelToTarget.setConverter(IConverter.create(OutputControlMode.class, String.class, controlModeFuncToTarget));
		binding = context.bindValue(ViewersObservables.observeSingleSelection(controlModeCombo), BeanProperties.value("controlMode").observe(scanModel), //$NON-NLS-1$
			controlModeTargetToModel, controlModeModelToTarget);
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);

		UpdateValueStrategy stringToDoubleStrategy = new UpdateValueStrategy();
		stringToDoubleStrategy.setAfterGetValidator(value -> {
			String stringValue = (String) value;
			if (stringValue.isEmpty()) {
				return ValidationStatus.error(Messages.ScanWizardPage_Error_NoValue);
			}
			try {
				Double.parseDouble(stringValue);
			} catch (NumberFormatException e) {
				return ValidationStatus.error(Messages.ScanWizardPage_Error_InvalidNumber);
			}
			return ValidationStatus.ok();
		});
		stringToDoubleStrategy.setAfterConvertValidator(value -> {
			double val = (Double) value;
			if (val <= 0) {
				return ValidationStatus.error(Messages.ScanWizardPage_Error_ValueMustBePositiveNonZero);
			}
			return ValidationStatus.ok();
		});
		UpdateValueStrategy doubleToStringStrategy = new UpdateValueStrategy();
		doubleToStringStrategy.setConverter(IConverter.create(Double.TYPE, String.class, value -> {
			return String.valueOf((double) value);
		}));
		binding = context.bindValue(WidgetProperties.text(SWT.Modify).observe(controlValueText), BeanProperties.value("controlValue").observe(scanModel), //$NON-NLS-1$
			stringToDoubleStrategy, doubleToStringStrategy);
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);

		binding = context.bindValue(WidgetProperties.selection().observe(delaySpinner), BeanProperties.value("delay").observe(scanModel)); //$NON-NLS-1$
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);

		WizardPageSupport.create(this, context);
	}

	public ScanMode getScanMode() {
		return scanModel.getMode();
	}

	public OutputControlMode getControlMode() {
		return scanModel.getControlMode();
	}

	public double getControlValue() {
		return scanModel.getControlValue();
	}

	public PrecisionUTCTime getStartTime() {
		long timeMillis = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis(); //$NON-NLS-1$
		return new PrecisionUTCTime(TCM_CPU.value, TCS_VALID.value, 0, timeMillis / 1000 + scanModel.getDelay(), timeMillis % 1000);
	}

	public boolean isBackground() {
		return backgroundJobButton.getSelection();
	}

	@Override
	public void loadSettings() {
		IDialogSettings settings = getPageSettings();
		modeCombo.setSelection(new StructuredSelection(settings.get("scanMode"))); //$NON-NLS-1$
		controlModeCombo.setSelection(new StructuredSelection(settings.get("controlMode"))); //$NON-NLS-1$
		try {
			scanModel.setControlValue(settings.getDouble("controlValue")); //$NON-NLS-1$
		} catch (NumberFormatException e) {
			// PASS
		}
		try {
			scanModel.setDelay(settings.getDouble("delay")); //$NON-NLS-1$
		} catch (NumberFormatException e) {
			// PASS
		}
		backgroundJobButton.setSelection(settings.getBoolean("background")); //$NON-NLS-1$
	}

	@Override
	public void saveSettings() {
		IDialogSettings settings = getPageSettings();
		settings.put("scanMode", (String) modeCombo.getStructuredSelection().getFirstElement()); //$NON-NLS-1$
		settings.put("controlMode", (String) controlModeCombo.getStructuredSelection().getFirstElement()); //$NON-NLS-1$
		settings.put("controlValue", scanModel.getControlValue()); //$NON-NLS-1$
		settings.put("delay", scanModel.getDelay()); //$NON-NLS-1$
		settings.put("background", backgroundJobButton.getSelection()); //$NON-NLS-1$
	}

}

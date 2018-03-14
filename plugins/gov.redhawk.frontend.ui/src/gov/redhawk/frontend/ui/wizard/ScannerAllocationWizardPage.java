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
package gov.redhawk.frontend.ui.wizard;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.util.MultiRange;
import gov.redhawk.frontend.util.TunerProperties.ScannerAllocationProperties;
import gov.redhawk.frontend.util.TunerProperties.TunerStatusAllocationProperties;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.observables.SCAObservables;

/**
 * @since 1.1
 */
public class ScannerAllocationWizardPage extends WizardPage {

	private static final String SPAN_SCAN = "SPAN_SCAN"; //$NON-NLS-1$
	private static final String DISCRETE_SCAN = "DISCRETE_SCAN"; //$NON-NLS-1$
	private static final String TIME_BASED = "TIME_BASED"; //$NON-NLS-1$
	private static final String SAMPLE_BASED = "SAMPLE_BASED"; //$NON-NLS-1$

	private ScaStructProperty scannerAllocationStruct;
	private MultiRange freqRange = new MultiRange();

	private Text minFreqText;
	private Text maxFreqText;
	private ComboViewer modeCombo;
	private ComboViewer controlModeCombo;
	private Text controlLimitText;
	private EMFDataBindingContext context;

	private NumberFormat nf;

	public ScannerAllocationWizardPage() {
		super(Messages.ScannerAllocationWizardPage_PageName);
		nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);
		nf.setMaximumFractionDigits(6);
		nf.setGroupingUsed(false);
	}

	public ScannerAllocationWizardPage(ScaStructProperty scannerAllocationStruct) {
		this();
		this.scannerAllocationStruct = scannerAllocationStruct;
	}

	/* package */ ScannerAllocationWizardPage(TunerStatus tuner) {
		this();
		ScaModelCommand.runExclusive(tuner, () -> {
			setMinMaxValues(Collections.singletonList(tuner.getTunerStatusStruct()));
			return null;
		});
	}

	private void setMinMaxValues(List<ScaStructProperty> statuses) {
		if (statuses.size() == 0) {
			return;
		}
		for (ScaStructProperty status : statuses) {
			ScaSimpleProperty availFreq = status.getSimple(TunerStatusAllocationProperties.AVAILABLE_FREQUENCY.getId());
			if (availFreq != null) {
				freqRange.addRange((String) availFreq.getValue());
			}
		}
	}

	public ScaStructProperty getScannerAllocationStruct() {
		return scannerAllocationStruct;
	}

	@Override
	public void createControl(Composite parent) {
		initializeScannerAllocationStruct();

		setTitle(Messages.ScannerAllocationWizardPage_PageTitle);
		setDescription(Messages.ScannerAllocationWizardPage_PageDescription);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		createFields(composite);
		setControl(composite);
		context = new EMFDataBindingContext();
		addFreqBindings();
		addOtherBindings();
		WizardPageSupport.create(this, context);
	}

	private void createFields(Composite parent) {
		GridDataFactory grab = GridDataFactory.fillDefaults().grab(true, false);

		Label minFreqLabel = new Label(parent, SWT.NONE);
		minFreqLabel.setText(Messages.ScannerAllocationWizardPage_MinFreq_Text);
		minFreqText = new Text(parent, SWT.BORDER);
		minFreqText.setToolTipText(Messages.ScannerAllocationWizardPage_MinFreq_ToolTip);
		minFreqText.setLayoutData(grab.create());

		Label maxFreqLabel = new Label(parent, SWT.NONE);
		maxFreqLabel.setText(Messages.ScannerAllocationWizardPage_MaxFreq_Text);
		maxFreqText = new Text(parent, SWT.BORDER);
		maxFreqText.setToolTipText(Messages.ScannerAllocationWizardPage_MaxFreq_ToolTip);
		maxFreqText.setLayoutData(grab.create());

		Label modeLabel = new Label(parent, SWT.NONE);
		modeLabel.setText(Messages.ScannerAllocationWizardPage_Model_Text);
		modeCombo = new ComboViewer(parent, SWT.NONE | SWT.READ_ONLY);
		modeCombo.getControl().setToolTipText(Messages.ScannerAllocationWizardPage_Model_ToolTip);
		modeCombo.setContentProvider(new ArrayContentProvider());
		modeCombo.setInput(new String[] { SPAN_SCAN, DISCRETE_SCAN });
		modeCombo.getControl().setLayoutData(grab.create());

		Label controlModeLabel = new Label(parent, SWT.NONE);
		controlModeLabel.setText(Messages.ScannerAllocationWizardPage_ControlMode_Text);
		controlModeCombo = new ComboViewer(parent, SWT.NONE | SWT.READ_ONLY);
		controlModeCombo.getControl().setToolTipText(Messages.ScannerAllocationWizardPage_ControlMode_ToolTip);
		controlModeCombo.setContentProvider(new ArrayContentProvider());
		controlModeCombo.setInput(new String[] { TIME_BASED, SAMPLE_BASED });
		controlModeCombo.getControl().setLayoutData(grab.create());

		Label controlLimitLabel = new Label(parent, SWT.NONE);
		controlLimitLabel.setText(Messages.ScannerAllocationWizardPage_ControlLimit_Text);
		controlLimitText = new Text(parent, SWT.BORDER);
		controlLimitText.setLayoutData(grab.create());

		controlModeCombo.addSelectionChangedListener(event -> {
			// Adjust the tooltip on the control limit field based on the control mode
			Object selection = event.getStructuredSelection().getFirstElement();
			if (selection == null) {
				controlLimitText.setToolTipText(Messages.ScannerAllocationWizardPage_Error_NoControlMode);
				return;
			}
			switch ((String) selection) {
			case TIME_BASED:
				controlLimitText.setToolTipText(Messages.ScannerAllocationWizardPage_ControlLimit_ToolTip_Seconds);
				break;
			case SAMPLE_BASED:
				controlLimitText.setToolTipText(Messages.ScannerAllocationWizardPage_ControlLimit_ToolTip_Samples);
				break;
			default:
				controlLimitText.setToolTipText(Messages.ScannerAllocationWizardPage_Error_NoControlMode);
			}
		});
	}

	private void initializeScannerAllocationStruct() {
		// if not null it was provided in constructor
		if (scannerAllocationStruct != null) {
			return;
		}

		scannerAllocationStruct = FEIStructDefaults.defaultScannerAllocationStruct();
	}

	private void addFreqBindings() {
		UpdateValueStrategy stringToDoubleFreqStrategy = new EMFUpdateValueStrategy();
		stringToDoubleFreqStrategy.setConverter(IConverter.create(String.class, Double.class, value -> {
			return Double.parseDouble((String) value) * 1e6;
		}));
		stringToDoubleFreqStrategy.setAfterGetValidator(value -> {
			String stringValue = (String) value;
			if (stringValue.isEmpty()) {
				return ValidationStatus.error(Messages.ScannerAllocationWizardPage_Error_NoValue);
			}
			try {
				Double.parseDouble(stringValue);
			} catch (NumberFormatException e) {
				return ValidationStatus.error(Messages.ScannerAllocationWizardPage_Error_InvalidNumber);
			}
			return ValidationStatus.ok();
		});
		stringToDoubleFreqStrategy.setAfterConvertValidator(value -> {
			double val = (Double) value;
			if (val <= 0) {
				return ValidationStatus.error(Messages.ScannerAllocationWizardPage_Error_MustBePositiveNonZero);
			}
			if (!freqRange.inRange(val)) {
				return ValidationStatus.error(Messages.ScannerAllocationWizardPage_Error_OutOfFreqRange);
			}
			return ValidationStatus.ok();
		});

		UpdateValueStrategy doubleToStringFreqStrategy = new EMFUpdateValueStrategy();
		doubleToStringFreqStrategy.setConverter(IConverter.create(Double.class, String.class, value -> {
			if (value == null) {
				return ""; //$NON-NLS-1$
			}
			return nf.format((Double) value / 1e6);
		}));

		// Bind the text boxes to middle observables
		IObservableValue<Double> middle0 = new WritableValue<Double>(null, Double.TYPE);
		IObservableValue<Double> middle1 = new WritableValue<Double>(null, Double.TYPE);
		Binding binding = context.bindValue(WidgetProperties.text(SWT.Modify).observe(minFreqText), middle0, stringToDoubleFreqStrategy,
			doubleToStringFreqStrategy);
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);
		binding = context.bindValue(WidgetProperties.text(SWT.Modify).observe(maxFreqText), middle1, stringToDoubleFreqStrategy, doubleToStringFreqStrategy);
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);

		// Validate the two frequencies against each other using the middle observables
		MultiValidator validator = new MultiValidator() {
			protected IStatus validate() {
				// Calculate the validation status
				Double minFreq = (Double) middle0.getValue();
				Double maxFreq = (Double) middle1.getValue();
				if (minFreq != null && maxFreq != null && minFreq > maxFreq) {
					return ValidationStatus.error(Messages.ScannerAllocationWizardPage_Error_MinFreqBiggerThanMaxFreq);
				}
				return ValidationStatus.ok();
			}
		};
		context.addValidationStatusProvider(validator);

		// Bind the middle observables to the model
		context.bindValue(middle0, SCAObservables.observeSimpleProperty(scannerAllocationStruct.getSimple(ScannerAllocationProperties.MIN_FREQ.getId())));
		context.bindValue(middle1, SCAObservables.observeSimpleProperty(scannerAllocationStruct.getSimple(ScannerAllocationProperties.MAX_FREQ.getId())));
	}

	private void addOtherBindings() {
		UpdateValueStrategy stringToEnumStrategy = new EMFUpdateValueStrategy();
		stringToEnumStrategy.setAfterGetValidator(value -> {
			if (value == null) {
				return ValidationStatus.error(Messages.ScannerAllocationWizardPage_Error_NoValue);
			}
			return ValidationStatus.ok();
		});

		Binding binding = context.bindValue(ViewersObservables.observeSingleSelection(modeCombo),
			SCAObservables.observeSimpleProperty(scannerAllocationStruct.getSimple(ScannerAllocationProperties.MODE.getId())), stringToEnumStrategy, null);
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);

		binding = context.bindValue(ViewersObservables.observeSingleSelection(controlModeCombo),
			SCAObservables.observeSimpleProperty(scannerAllocationStruct.getSimple(ScannerAllocationProperties.CONTROL_MODE.getId())), stringToEnumStrategy,
			null);
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);

		UpdateValueStrategy stringToDoublePositiveNumber = new EMFUpdateValueStrategy();
		stringToDoublePositiveNumber.setAfterGetValidator(value -> {
			String stringValue = (String) value;
			if (stringValue.isEmpty()) {
				return ValidationStatus.error(Messages.ScannerAllocationWizardPage_Error_NoValue);
			}
			try {
				Double.parseDouble(stringValue);
			} catch (NumberFormatException e) {
				return ValidationStatus.error(Messages.ScannerAllocationWizardPage_Error_InvalidNumber);
			}
			return ValidationStatus.ok();
		});
		stringToDoublePositiveNumber.setAfterConvertValidator(value -> {
			if ((Double) value <= 0) {
				return ValidationStatus.error(Messages.ScannerAllocationWizardPage_Error_MustBePositiveNonZero);
			}
			return ValidationStatus.ok();
		});

		UpdateValueStrategy doubleToStringPositiveStrategy = new EMFUpdateValueStrategy();
		doubleToStringPositiveStrategy.setConverter(IConverter.create(Double.class, String.class, value -> {
			if (value == null) {
				return ""; //$NON-NLS-1$
			}
			return nf.format((Double) value);
		}));

		binding = context.bindValue(WidgetProperties.text(SWT.Modify).observe(controlLimitText),
			SCAObservables.observeSimpleProperty(scannerAllocationStruct.getSimple(ScannerAllocationProperties.CONTROL_LIMIT.getId())),
			stringToDoublePositiveNumber, doubleToStringPositiveStrategy);
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);
	}
}

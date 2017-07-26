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
package gov.redhawk.frontend.ui.wizard;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.ui.FrontEndUIActivator.AllocationMode;
import gov.redhawk.frontend.util.TunerProperties.ListenerAllocationProperties;
import gov.redhawk.frontend.util.TunerProperties.ListenerAllocationProperty;
import gov.redhawk.frontend.util.TunerProperties.TunerAllocationProperties;
import gov.redhawk.frontend.util.TunerProperties.TunerAllocationProperty;
import gov.redhawk.frontend.util.TunerProperties.TunerStatusAllocationProperties;
import gov.redhawk.frontend.util.TunerProperties.TunerStatusProperty;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.sca.observables.SCAObservables;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class TunerAllocationWizardPage extends WizardPage {

	private TunerStatus tuner;
	private Text allocIdText;
	private Text cfText;
	private Text bwText;
	private Text bwTolText;
	private Text srText;
	private Text srTolText;
	private Text targetAllocText;
	private Text rfFlowIdText;
	private Text groupIdText;
	private ScaStructProperty tunerAllocationStruct;
	private ScaStructProperty listenerAllocationStruct;
	private String defaultAllocationType = ALLOCATE_TUNER;
	private static final double FREQUENCY_VALUE_CONVERSION_FACTOR = 1e6;
	private static final double TOLERANCE_CONVERSION = 0.01;
	private UUID uuid;
	private ComboViewer typeCombo;
	private ComboViewer allocationComboViewer;
	private AllocationMode allocationMode = AllocationMode.TUNER;
	private EMFDataBindingContext context;
	private Button srAnyValue;
	private Button bwAnyValue;
	private Button bgJobButton;  // Background Job option
	private List<Control> tunerControls = new ArrayList<Control>();
	private NumberFormat nf = NumberFormat.getInstance();
	private Double minBw;
	private Double maxBw;
	private Double minFreq;
	private Double maxFreq;
	private Double minSr;
	private Double maxSr;
	private ScaDevice< ? > feiDevice;

	private static final String ALLOCATE_TUNER = "Control New Tuner";
	private static final String LISTEN_TUNER_BY_ID = "Listen to Existing Tuner by ID";
	private static final String LISTEN_TUNER_BY_PROPERTIES = "Listen to Existing Tuner by Properties";
	private static final String[] TUNER_ALLOCATION_TYPES = new String[] { ALLOCATE_TUNER, LISTEN_TUNER_BY_ID, LISTEN_TUNER_BY_PROPERTIES };

	private static final String TUNER_TYPE_MISSING_ERR_MSG = "Please select a tuner type";
	private static final String TUNER_TYPE_NOT_SUPPORTED_ERR_MSG = "The selected tuner type is not supported";
	private static final String ALLOC_ID_CONTAINS_COMMA_ERR_MSG = "Allocation ID must not contain a comma";
	private static final String ALLOC_ID_MISSING = "Please provide an allocation ID. Any text, excluding commas and colons is acceptable.";
	private static final String EXISTING_LISTENER_ID_MISSING = "Please enter the Allocation ID of an existing Tuner";
	private static final String CENTER_FREQUENCY_ERR_MSG = "Please specify a center frequency";
	private static final String BNDWIDTH_ERR_MSG = "Please specify a bandwidth";
	private static final String SAMPLE_RATE_ERR_MSG = "Please specify a sample rate";
	private static final String BANDWIDTH_TOLERANCE_ERR_MSG = "Please specify a bandwidth tolerance greater than 0";
	private static final String SAMPLE_RATE_TOLERANCE_ERR_MSG = "Please specify a sample rate tolerance greater than 0";
	private static final String NOT_VALID_NUMBER_ERR_MSG = "You must enter a valid decimal number";
	private static final String NEGATIVE_ERR_MSG = "The value must not be negative";
	private static final String NEGATIVE_OR_ZERO_ERR_MSG = "The value must be a positive non-zero number";
	private static final String FREQ_BELOW_MIN = "The selected frequency is below the minimum known supported frequency of ";
	private static final String FREQ_ABOVE_MAX = "The selected frequency is above the maximum known supported frequency of ";
	private static final String BW_BELOW_MIN = "The selected bandwidth is below the minimum known supported bandwidth of ";
	private static final String BW_ABOVE_MAX = "The selected bandwidth is above the maximum known supported bandwidth of ";
	private static final String SR_BELOW_MIN = "The selected sample rate is below the minimum known supported sample rate of ";
	private static final String SR_ABOVE_MAX = "The selected sample rate is above the maximum known supported sample rate of ";

	public class TargetableValidator implements IValidator {

		private Control control;

		public TargetableValidator(Control control) {
			this.control = control;
		}

		@Override
		public IStatus validate(Object value) {
			return getValidationStatus(control, value);
		}
	}

	private class UseAnyValueListener extends SelectionAdapter {
		private String previousBwValue = "";
		private String previousSrValue = "";

		private UseAnyValueListener() {
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			Button b = (Button) e.getSource();
			if (b == bwAnyValue) {
				bwText.setEnabled(!b.getSelection());
				if (b.getSelection()) {
					previousBwValue = bwText.getText();
					final ScaSimpleProperty bwSimple = tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH.getId());
					bwSimple.setValue(0.0);
				} else {
					bwText.setText(previousBwValue);
				}
			} else if (b == srAnyValue) {
				srText.setEnabled(!b.getSelection());
				final ScaSimpleProperty srSimple = tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE.getId());
				if (b.getSelection()) {
					previousSrValue = srText.getText();
					srSimple.setValue(0.0);
				} else {
					srText.setText(previousSrValue);
				}
			}

			// perform validation
			for (Object binding : context.getBindings()) {
				((Binding) binding).validateModelToTarget();
				((Binding) binding).validateTargetToModel();
			}
			// setPageComplete(validatePageComplete());
		}
	}

	private ISelectionChangedListener allocationModeListener = new ISelectionChangedListener() {
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			IStructuredSelection structuredSelection = (IStructuredSelection) allocationComboViewer.getSelection();
			String selection = (String) structuredSelection.getFirstElement();
			if (ALLOCATE_TUNER.equals(selection) || LISTEN_TUNER_BY_PROPERTIES.equals(selection)) {
				allocationMode = AllocationMode.TUNER;
			} else {
				allocationMode = AllocationMode.LISTENER;
			}

			tunerAllocationStruct.getSimple(TunerAllocationProperties.DEVICE_CONTROL.getId()).setValue(ALLOCATE_TUNER.equals(selection));

			for (Control c : tunerControls) {
				c.setEnabled(allocationMode == AllocationMode.TUNER);
			}
			if (bwAnyValue.getSelection()) {
				bwText.setEnabled(false);
			}
			if (srAnyValue.getSelection()) {
				srText.setEnabled(false);
			}
			targetAllocText.setEnabled(LISTEN_TUNER_BY_ID.equals(selection));
			for (Object binding : context.getBindings()) {
				((Binding) binding).validateModelToTarget();
				((Binding) binding).validateTargetToModel();
			}
			// setPageComplete(validatePageComplete());
		}
	};

	public IStatus getValidationStatus(Control control, Object value) {
		if (control == typeCombo.getControl()) {
			IStructuredSelection structuredSelection = (IStructuredSelection) allocationComboViewer.getSelection();
			String selection = (String) structuredSelection.getFirstElement();
			if (LISTEN_TUNER_BY_ID.equals(selection)) {
				return Status.OK_STATUS;
			}
			String s = (String) value;
			if (s == null || s.trim().equals("")) {
				return ValidationStatus.error(TunerAllocationWizardPage.TUNER_TYPE_MISSING_ERR_MSG);
			}
			if (FRONTEND.TUNER_TYPE_RX_SCANNER_DIGITIZER.value.equals(s)) {
				// We don't support allocating these (yet)
				return ValidationStatus.error(TunerAllocationWizardPage.TUNER_TYPE_NOT_SUPPORTED_ERR_MSG);
			}
			if (!FrontEndUIActivator.SUPPORTED_TUNER_TYPES.contains(s)) {
				return ValidationStatus.error(TunerAllocationWizardPage.TUNER_TYPE_NOT_SUPPORTED_ERR_MSG);
			}
			return Status.OK_STATUS;
		} else if (control == allocIdText) {
			String s = (String) value;
			if (s.contains(",")) {
				return ValidationStatus.error(TunerAllocationWizardPage.ALLOC_ID_CONTAINS_COMMA_ERR_MSG);
			}
			if ("".equals(s)) {
				return ValidationStatus.error(TunerAllocationWizardPage.ALLOC_ID_MISSING);
			}
			return Status.OK_STATUS;
		} else if (control == targetAllocText) {
			if (allocationMode == AllocationMode.TUNER) {
				return Status.OK_STATUS;
			}
			String s = (String) value;
			if ("".equals(s)) {
				return ValidationStatus.error(TunerAllocationWizardPage.EXISTING_LISTENER_ID_MISSING);
			}
			return Status.OK_STATUS;
		} else if (allocationMode == AllocationMode.LISTENER) {
			return Status.OK_STATUS;
		} else if (control == cfText) {
			if (allocationMode == AllocationMode.LISTENER) {
				return Status.OK_STATUS;
			}
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(TunerAllocationWizardPage.CENTER_FREQUENCY_ERR_MSG);
			}
			Double val = null;
			try {
				val = Double.parseDouble(String.valueOf(value)) * getUnitsConversionFactor(TunerAllocationProperties.CENTER_FREQUENCY);
			} catch (NumberFormatException e) {
				return ValidationStatus.error(TunerAllocationWizardPage.NOT_VALID_NUMBER_ERR_MSG);
			}
			if (val <= 0) {
				return ValidationStatus.error(TunerAllocationWizardPage.NEGATIVE_OR_ZERO_ERR_MSG);
			}
			if (minFreq != null && val < minFreq) {
				return ValidationStatus.warning(TunerAllocationWizardPage.FREQ_BELOW_MIN
					+ String.valueOf(minFreq / getUnitsConversionFactor(TunerAllocationProperties.CENTER_FREQUENCY)) + " MHz");
			}
			if (maxFreq != null && val > maxFreq) {
				return ValidationStatus.warning(TunerAllocationWizardPage.FREQ_ABOVE_MAX
					+ String.valueOf(maxFreq / getUnitsConversionFactor(TunerAllocationProperties.CENTER_FREQUENCY)) + " MHz");
			}
			return Status.OK_STATUS;
		} else if (control == bwText) {
			if (allocationMode == AllocationMode.LISTENER || bwAnyValue.getSelection()) {
				return Status.OK_STATUS;
			}
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(TunerAllocationWizardPage.BNDWIDTH_ERR_MSG);
			}
			Double val = null;
			try {
				val = Double.parseDouble(String.valueOf(value)) * getUnitsConversionFactor(TunerAllocationProperties.BANDWIDTH);
			} catch (NumberFormatException e) {
				return ValidationStatus.error(TunerAllocationWizardPage.NOT_VALID_NUMBER_ERR_MSG);
			}
			if (val < 0) {
				return ValidationStatus.error(TunerAllocationWizardPage.NEGATIVE_ERR_MSG);
			}
			if (minBw != null && val < minBw) {
				return ValidationStatus.warning(TunerAllocationWizardPage.BW_BELOW_MIN
					+ String.valueOf(minBw / getUnitsConversionFactor(TunerAllocationProperties.BANDWIDTH)) + " MHz");
			}
			if (maxBw != null && val > maxBw) {
				return ValidationStatus.warning(TunerAllocationWizardPage.BW_ABOVE_MAX
					+ String.valueOf(maxBw / getUnitsConversionFactor(TunerAllocationProperties.BANDWIDTH)) + " MHz");
			}
			return Status.OK_STATUS;
		} else if (control == srText) {
			if (allocationMode == AllocationMode.LISTENER || srAnyValue.getSelection()) {
				return Status.OK_STATUS;
			}
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(TunerAllocationWizardPage.SAMPLE_RATE_ERR_MSG);
			}
			Double val = null;
			try {
				val = Double.parseDouble(String.valueOf(value)) * getUnitsConversionFactor(TunerAllocationProperties.SAMPLE_RATE);
			} catch (NumberFormatException e) {
				return ValidationStatus.error(TunerAllocationWizardPage.NOT_VALID_NUMBER_ERR_MSG);
			}
			if (val < 0) {
				return ValidationStatus.error(TunerAllocationWizardPage.NEGATIVE_ERR_MSG);
			}
			if (minSr != null && val < minSr) {
				return ValidationStatus.warning(TunerAllocationWizardPage.SR_BELOW_MIN
					+ String.valueOf(minSr / getUnitsConversionFactor(TunerAllocationProperties.SAMPLE_RATE)) + " Msps");
			}
			if (maxSr != null && val > maxSr) {
				return ValidationStatus.warning(TunerAllocationWizardPage.SR_ABOVE_MAX
					+ String.valueOf(maxSr / getUnitsConversionFactor(TunerAllocationProperties.SAMPLE_RATE)) + " Msps");
			}
			return Status.OK_STATUS;
		} else if (control == bwTolText) {
			if (allocationMode == AllocationMode.LISTENER) {
				return Status.OK_STATUS;
			}
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(TunerAllocationWizardPage.BANDWIDTH_TOLERANCE_ERR_MSG);
			}
			Double val = null;
			try {
				val = Double.parseDouble(String.valueOf(value));
			} catch (NumberFormatException e) {
				return ValidationStatus.error(TunerAllocationWizardPage.NOT_VALID_NUMBER_ERR_MSG);
			}
			if (val < 0) {
				return ValidationStatus.error(TunerAllocationWizardPage.BANDWIDTH_TOLERANCE_ERR_MSG);
			}
			return Status.OK_STATUS;
		} else if (control == srTolText) {
			if (allocationMode == AllocationMode.LISTENER) {
				return Status.OK_STATUS;
			}
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(TunerAllocationWizardPage.SAMPLE_RATE_TOLERANCE_ERR_MSG);
			}
			Double val = null;
			try {
				val = Double.parseDouble(String.valueOf(value));
			} catch (NumberFormatException e) {
				return ValidationStatus.error(TunerAllocationWizardPage.NOT_VALID_NUMBER_ERR_MSG);
			}
			if (val < 0) {
				return ValidationStatus.error(TunerAllocationWizardPage.SAMPLE_RATE_TOLERANCE_ERR_MSG);
			}
			return Status.OK_STATUS;
		}
		return Status.OK_STATUS;
	}

	public TunerAllocationWizardPage() {
		super("Allocate A Tuner");
		nf.setMinimumFractionDigits(0);
	}

	protected TunerAllocationWizardPage(TunerStatus tuner) {
		super("Allocate A Tuner");
		Assert.isNotNull(tuner, "Tuner must not be null");
		this.tuner = tuner;
		setMinMaxValues();
		nf.setMinimumFractionDigits(0);
		nf.setGroupingUsed(false);
	}

	protected TunerAllocationWizardPage(TunerStatus tuner, ScaDevice< ? > device) {
		super("Allocate A Tuner");
		Assert.isNotNull(tuner, "Tuner must not be null");
		this.tuner = tuner;
		this.feiDevice = device;
		setMinMaxValues();
		nf.setMinimumFractionDigits(0);
		nf.setGroupingUsed(false);
	}

	public TunerAllocationWizardPage(ScaStructProperty allocationStruct) {
		this();
		if (TunerAllocationProperty.INSTANCE.getId().equals(allocationStruct.getId())) {
			setTunerAllocationStruct(allocationStruct);
			ScaSimpleProperty deviceControl = (ScaSimpleProperty) allocationStruct.getField(TunerAllocationProperties.DEVICE_CONTROL.getId());
			if (!(Boolean) deviceControl.getValue()) {
				// withOUT device control
				defaultAllocationType = LISTEN_TUNER_BY_PROPERTIES;
			} else {
				defaultAllocationType = ALLOCATE_TUNER;
			}
		} else if (ListenerAllocationProperty.INSTANCE.getId().equals(allocationStruct.getId())) {
			setListenerAllocationStruct(allocationStruct);
			defaultAllocationType = LISTEN_TUNER_BY_ID;
		}
	}

	@Override
	public void createControl(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		createGroupControls(comp);
		setControl(comp);

		setTitle("Tuner Allocation");
		setDescription("Specify the parameters for the tuner you would like to allocate.");
		this.uuid = UUID.randomUUID();

		initializeTunerAllocationStruct();
		initializeListenerAllocationStruct();

		context = new EMFDataBindingContext();
		addBindings();
		WizardPageSupport.create(this, context);
	}

	private void addTunerTypeBindings() {
		UpdateValueStrategy tunerTypeStrategy = new UpdateValueStrategy();
		tunerTypeStrategy.setAfterGetValidator(new TargetableValidator(typeCombo.getControl()));
		ControlDecorationSupport.create(
			context.bindValue(ViewerProperties.singleSelection().observe(typeCombo),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.TUNER_TYPE.getId())), tunerTypeStrategy, null),
			SWT.TOP | SWT.LEFT);
		typeCombo.setInput(FrontEndUIActivator.SUPPORTED_TUNER_TYPES.toArray(new String[0]));
		if (tuner != null) {
			if (tuner.getTunerType() != null) {
				typeCombo.setSelection(new StructuredSelection(tuner.getTunerType()));
			} else {
				typeCombo.setSelection(new StructuredSelection(FRONTEND.TUNER_TYPE_RX_DIGITIZER.value));
			}
		} else {
			String tunerType = (String) tunerAllocationStruct.getSimple(TunerAllocationProperties.TUNER_TYPE.getId()).getValue();
			if (tunerType != null) {
				typeCombo.setSelection(new StructuredSelection(tunerAllocationStruct.getSimple(TunerAllocationProperties.TUNER_TYPE.getId()).getValue()));
			}
		}

	}

	private void addAllocIdBindings() {
		UpdateValueStrategy allocIdStrategy = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				return value;
			}
		};
		allocIdStrategy.setAfterGetValidator(new TargetableValidator(allocIdText));

		ControlDecorationSupport.create(
			context.bindValue(WidgetProperties.text(SWT.Modify).observe(allocIdText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.ALLOCATION_ID.getId())), allocIdStrategy, null),
			SWT.TOP | SWT.LEFT);
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(allocIdText),
			SCAObservables.observeSimpleProperty(listenerAllocationStruct.getSimple(ListenerAllocationProperties.LISTENER_ALLOCATION_ID.getId())),
			allocIdStrategy, null), SWT.TOP | SWT.LEFT);
		if (allocIdText.getText().isEmpty()) {
			// initialize and color cyan
			allocIdText.setText(getUsername() + ":" + uuid.toString());
			allocIdText.setBackground(allocIdText.getDisplay().getSystemColor(SWT.COLOR_CYAN));
		}
		allocIdText.addListener(SWT.FocusIn, event -> {
			allocIdText.setBackground(null);
			// Select-all asynchronously; it won't work directly here since we're moving into the allocIdText control
			getShell().getDisplay().asyncExec(() -> {
				if (allocIdText.isDisposed()) {
					return;
				}
				allocIdText.selectAll();
			});
		});
	}

	private void addExistingAllocIdBindings() {
		UpdateValueStrategy existingAllocIdStrategy1 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				return value;
			}
		};
		UpdateValueStrategy existingAllocIdStrategy2 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				return value;
			}
		};
		existingAllocIdStrategy1.setAfterGetValidator(new TargetableValidator(targetAllocText));
		existingAllocIdStrategy2.setAfterGetValidator(new TargetableValidator(targetAllocText));

		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(targetAllocText),
			SCAObservables.observeSimpleProperty(listenerAllocationStruct.getSimple(ListenerAllocationProperties.EXISTING_ALLOCATION_ID.getId())),
			existingAllocIdStrategy1, existingAllocIdStrategy2), SWT.TOP | SWT.LEFT);
	}

	private void addCfBindings() {
		UpdateValueStrategy cfStrategy1 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				try {
					Double.parseDouble((String) value);
				} catch (NumberFormatException e) {
					return null;
				}
				return Double.valueOf((String) value) * getUnitsConversionFactor(TunerAllocationProperties.CENTER_FREQUENCY);
			}
		};
		UpdateValueStrategy cfStrategy2 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				if (value instanceof Double) {
					if (((Double) value).doubleValue() == 0.) {
						return "";
					}
					double retVal = (Double) value / getUnitsConversionFactor(TunerAllocationProperties.CENTER_FREQUENCY);
					return String.valueOf(nf.format(retVal));
				}
				return null;
			}
		};
		cfStrategy1.setAfterGetValidator(new TargetableValidator(cfText));
		cfStrategy2.setAfterConvertValidator(new TargetableValidator(cfText));
		ControlDecorationSupport.create(
			context.bindValue(WidgetProperties.text(SWT.Modify).observe(cfText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.CENTER_FREQUENCY.getId())), cfStrategy1,
				cfStrategy2), SWT.TOP | SWT.LEFT);
	}

	private void addBwBindings() {
		UpdateValueStrategy bwStrategy1 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				try {
					Double.parseDouble((String) value);
				} catch (NumberFormatException e) {
					return null;
				}
				return Double.valueOf((String) value) * getUnitsConversionFactor(TunerAllocationProperties.BANDWIDTH);
			}
		};
		UpdateValueStrategy bwStrategy2 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				if (value instanceof Double) {
					if (((Double) value).intValue() == 0) {
						return "";
					}
					double retVal = (Double) value / getUnitsConversionFactor(TunerAllocationProperties.BANDWIDTH);
					return String.valueOf(nf.format(retVal));
				}
				return null;
			}
		};
		bwStrategy1.setAfterGetValidator(new TargetableValidator(bwText));
		bwStrategy2.setAfterConvertValidator(new TargetableValidator(bwText));
		ControlDecorationSupport.create(
			context.bindValue(WidgetProperties.text(SWT.Modify).observe(bwText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH.getId())), bwStrategy1, bwStrategy2),
			SWT.TOP | SWT.LEFT);
		bwText.addListener(SWT.FocusOut, event -> {
			// If the sample rate is already set, don't do anything
			if (srAnyValue.getSelection() || !"".equals(srText.getText())) {
				return;
			}

			// Must have a valid, non-zero number for bandwidth
			double bwVal;
			try {
				bwVal = Double.parseDouble(bwText.getText());
			} catch (NumberFormatException ex) {
				return;
			}
			if (bwVal == 0.0) {
				return;
			}

			// Set the sample rate to twice the bandwidth
			double srVal = bwVal * 2;
			NumberFormat localNF = NumberFormat.getInstance();
			localNF.setMinimumFractionDigits(0);
			localNF.setGroupingUsed(false);
			srText.setText(localNF.format(srVal));
			// Set background asynchronously; if the user is moving into the srText control this prevents setting the
			// background
			getShell().getDisplay().asyncExec(() -> {
				if (srText.isDisposed()) {
					return;
				}
				srText.setBackground(srText.getDisplay().getSystemColor(SWT.COLOR_CYAN));
			});
		});

		// bwAnyValue
		if (tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH.getId()).getValue() != null) {
			bwAnyValue.setSelection((Double) tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH.getId()).getValue() == 0);
		}

		// BW Tolerance Text
		UpdateValueStrategy bwTolStrategy1 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				try {
					Double.parseDouble((String) value);
				} catch (NumberFormatException e) {
					return null;
				}
				return Double.valueOf((String) value);
			}
		};
		UpdateValueStrategy bwTolStrategy2 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				if (value instanceof Double) {
					if (((Double) value).doubleValue() == 0.) {
						return "";
					}
					double retVal = (Double) value;
					return String.valueOf(nf.format(retVal));
				}
				return null;
			}
		};
		bwTolStrategy1.setAfterGetValidator(new TargetableValidator(bwTolText));
		bwTolStrategy2.setAfterGetValidator(new TargetableValidator(bwTolText));
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(bwTolText),
			SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH_TOLERANCE.getId())), bwTolStrategy1,
			bwTolStrategy2), SWT.TOP | SWT.LEFT);
		bwTolText.setText("20");
	}

	private void addSrBindings() {
		UpdateValueStrategy srStrategy1 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				try {
					Double.parseDouble((String) value);
				} catch (NumberFormatException e) {
					return null;
				}
				return Double.valueOf((String) value) * getUnitsConversionFactor(TunerAllocationProperties.SAMPLE_RATE);
			}
		};
		UpdateValueStrategy srStrategy2 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				if (value instanceof Double) {
					if (((Double) value).intValue() == 0) {
						return "";
					}
					double retVal = (Double) value / getUnitsConversionFactor(TunerAllocationProperties.SAMPLE_RATE);
					return String.valueOf(nf.format(retVal));
				}
				return null;
			}
		};
		srStrategy1.setAfterGetValidator(new TargetableValidator(srText));
		srStrategy2.setAfterConvertValidator(new TargetableValidator(srText));
		ControlDecorationSupport.create(
			context.bindValue(WidgetProperties.text(SWT.Modify).observe(srText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE.getId())), srStrategy1, srStrategy2),
			SWT.TOP | SWT.LEFT);
		srText.addListener(SWT.FocusIn, event -> srText.setBackground(null));

		// srAnyValue
		if (tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE.getId()).getValue() != null) {
			srAnyValue.setSelection((Double) tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE.getId()).getValue() == 0);
		}

		// SR Tolerance Text
		UpdateValueStrategy srTolStrategy1 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				try {
					Double.parseDouble((String) value);
				} catch (NumberFormatException e) {
					return null;
				}
				return Double.valueOf((String) value);
			}
		};
		UpdateValueStrategy srTolStrategy2 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				if (value instanceof Double) {
					if (((Double) value).doubleValue() == 0.) {
						return "";
					}
					double retVal = (Double) value;
					return String.valueOf(nf.format(retVal));
				}
				return null;
			}
		};
		srTolStrategy1.setAfterGetValidator(new TargetableValidator(srTolText));
		srTolStrategy2.setAfterGetValidator(new TargetableValidator(srTolText));
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(srTolText),
			SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE_TOLERANCE.getId())), srTolStrategy1,
			srTolStrategy2), SWT.TOP | SWT.LEFT);
		srTolText.setText("20");
	}

	private void addAllocationComboBindings() {
		allocationComboViewer.addSelectionChangedListener(allocationModeListener);
		allocationComboViewer.setInput(TUNER_ALLOCATION_TYPES);
		// set selection
		allocationComboViewer.setSelection(new StructuredSelection(defaultAllocationType));
	}

	private void addRfFlowIdBindings() {
		ControlDecorationSupport.create(
			context.bindValue(WidgetProperties.text(SWT.Modify).observe(rfFlowIdText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.RF_FLOW_ID.getId())), null, null), SWT.TOP
				| SWT.LEFT);

	}

	private void addGroupIdBindings() {
		ControlDecorationSupport.create(
			context.bindValue(WidgetProperties.text(SWT.Modify).observe(groupIdText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.GROUP_ID.getId())), null, null), SWT.TOP
				| SWT.LEFT);

	}

	public void addBindings() {

		// Tuner Type combo
		addTunerTypeBindings();

		// allocation ID Text
		addAllocIdBindings();

		// Existing allocation ID Text
		addExistingAllocIdBindings();

		// CF Text
		addCfBindings();

		// BW Text
		addBwBindings();

		// SR Text
		addSrBindings();

		// RF FLow ID Text
		addRfFlowIdBindings();

		// Allocation Combo
		addAllocationComboBindings();

		// Group ID Text
		addGroupIdBindings();
	}

	private String getUsername() {
		return System.getProperty("user.name");
	}

	private void initializeTunerAllocationStruct() {
		// if not null it was provided in constructor
		if (tunerAllocationStruct != null) {
			return;
		}

		tunerAllocationStruct = ScaFactory.eINSTANCE.createScaStructProperty();
		tunerAllocationStruct.setId(TunerAllocationProperty.INSTANCE.getId());
		tunerAllocationStruct.setDefinition(TunerAllocationProperty.INSTANCE.createProperty());
		for (TunerAllocationProperties propDetails : TunerAllocationProperties.values()) {
			ScaSimpleProperty simple = tunerAllocationStruct.getSimple(propDetails.getId());
			setValueForProp(propDetails, simple);
		}
	}

	private void initializeListenerAllocationStruct() {
		// if not null it was provided in constructor
		if (listenerAllocationStruct != null) {
			return;
		}

		listenerAllocationStruct = ScaFactory.eINSTANCE.createScaStructProperty();
		listenerAllocationStruct.setId(ListenerAllocationProperty.INSTANCE.getId());
		listenerAllocationStruct.setDefinition(ListenerAllocationProperty.INSTANCE.createProperty());
		for (ListenerAllocationProperties propDetails : ListenerAllocationProperties.values()) {
			ScaSimpleProperty simple = listenerAllocationStruct.getSimple(propDetails.getId());
			setValueForProp(propDetails, simple);
		}
	}

	private void createGroupControls(Composite parent) {
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(parent);

		Composite paddingComp = new Composite(parent, SWT.NONE);
		paddingComp.setLayout(new FillLayout());
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(paddingComp);

		Composite allocTypeComp = new Composite(parent, SWT.NONE);
		allocTypeComp.setLayout(new GridLayout(3, false));
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(allocTypeComp);

		Label allocationComboViewerLabel = new Label(allocTypeComp, SWT.NONE);
		allocationComboViewerLabel.setText("Allocation:");
		Combo allocationCombo = new Combo(allocTypeComp, SWT.READ_ONLY | SWT.SINGLE | SWT.LEAD | SWT.BORDER);
		allocationComboViewer = new ComboViewer(allocationCombo);
		allocationComboViewer.setContentProvider(new ArrayContentProvider());
		GridData allocationComboViewerGridData = GridDataFactory.fillDefaults().grab(false, false).create();

		allocationComboViewer.getControl().setLayoutData(allocationComboViewerGridData);

		Group allocPropGroup = new Group(parent, SWT.SHADOW_ETCHED_IN);
		allocPropGroup.setText("Allocation Properties");
		allocPropGroup.setLayout(new GridLayout(2, false));
		GridData groupGridData = GridDataFactory.fillDefaults().grab(true, false).create();
		groupGridData.horizontalSpan = 2;
		allocPropGroup.setLayoutData(groupGridData);

		Label targetAllocLabel = new Label(allocPropGroup, SWT.NONE);
		targetAllocLabel.setText("Existing Tuner Allocation ID");
		targetAllocText = new Text(allocPropGroup, SWT.BORDER);
		targetAllocText.setToolTipText("If you would like to create a Listener allocation for a specific tuner, enter its Allocation ID here");
		targetAllocText.setEnabled(false);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(targetAllocText);

		Label allocIdLabel = new Label(allocPropGroup, SWT.NONE);
		allocIdLabel.setText("New Allocation ID");
		allocIdText = new Text(allocPropGroup, SWT.BORDER);
		allocIdText.setToolTipText("Enter any ID for ease of reference. Additional characters will be appended after this name, to ensure uniqueness");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(allocIdText);

		Label typeLabel = new Label(allocPropGroup, SWT.NONE);
		typeLabel.setText("Tuner Type");
		typeCombo = new ComboViewer(allocPropGroup, SWT.NONE | SWT.READ_ONLY);
		typeCombo.setContentProvider(new ArrayContentProvider());
		GridDataFactory.fillDefaults().grab(true, false).applyTo(typeCombo.getControl());
		tunerControls.add(typeCombo.getControl());

		Label cfLabel = new Label(allocPropGroup, SWT.NONE);
		cfLabel.setText("Center Frequency (MHz)");
		cfText = new Text(allocPropGroup, SWT.BORDER);
		cfText.setToolTipText(TunerAllocationProperties.CENTER_FREQUENCY.getDescription());
		tunerControls.add(cfText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cfText);

		Label bwLabel = new Label(allocPropGroup, SWT.NONE);
		bwLabel.setText("Bandwidth (MHz)");
		Composite bwComp = new Composite(allocPropGroup, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwComp);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(bwComp);
		bwText = new Text(bwComp, SWT.BORDER);
		bwText.setToolTipText(TunerAllocationProperties.BANDWIDTH.getDescription());
		tunerControls.add(bwText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwText);
		bwAnyValue = new Button(bwComp, SWT.CHECK);
		tunerControls.add(bwAnyValue);
		bwAnyValue.setText("Any Value");
		bwAnyValue.addSelectionListener(new UseAnyValueListener());
		GridDataFactory.fillDefaults().grab(false, false).applyTo(bwAnyValue);

		Label srLabel = new Label(allocPropGroup, SWT.NONE);
		srLabel.setText("Sample Rate (Msps)");
		Composite srComp = new Composite(allocPropGroup, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srComp);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(srComp);
		srText = new Text(srComp, SWT.BORDER);
		srText.setToolTipText(TunerAllocationProperties.SAMPLE_RATE.getDescription());
		tunerControls.add(srText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srText);
		srAnyValue = new Button(srComp, SWT.CHECK);
		tunerControls.add(srAnyValue);
		srAnyValue.setText("Any Value");
		srAnyValue.addSelectionListener(new UseAnyValueListener());
		GridDataFactory.fillDefaults().grab(false, false).applyTo(srAnyValue);

		Label bwTolLabel = new Label(allocPropGroup, SWT.NONE);
		bwTolLabel.setText("Bandwidth Tolerance (%)");
		bwTolText = new Text(allocPropGroup, SWT.BORDER);
		bwTolText.setToolTipText(TunerAllocationProperties.BANDWIDTH_TOLERANCE.getDescription());
		tunerControls.add(bwTolText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwTolText);

		Label srTolLabel = new Label(allocPropGroup, SWT.NONE);
		srTolLabel.setText("Sample Rate Tolerance (%)");
		srTolText = new Text(allocPropGroup, SWT.BORDER);
		srTolText.setToolTipText(TunerAllocationProperties.SAMPLE_RATE_TOLERANCE.getDescription());
		tunerControls.add(srTolText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srTolText);

		Label rfFlowIdLabel = new Label(allocPropGroup, SWT.NONE);
		rfFlowIdLabel.setText("RF Flow ID");
		rfFlowIdText = new Text(allocPropGroup, SWT.BORDER);
		rfFlowIdText.setToolTipText(TunerAllocationProperties.RF_FLOW_ID.getDescription());
		tunerControls.add(rfFlowIdText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(rfFlowIdText);

		Label groupIdLabel = new Label(allocPropGroup, SWT.NONE);
		groupIdLabel.setText("Group ID");
		groupIdText = new Text(allocPropGroup, SWT.BORDER);
		groupIdText.setToolTipText(TunerAllocationProperties.GROUP_ID.getDescription());
		tunerControls.add(groupIdText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(groupIdText);

		Composite bgJobComp = new Composite(parent, SWT.NONE);
		bgJobComp.setLayout(new GridLayout(3, false));
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).align(SWT.END, SWT.CENTER).applyTo(bgJobComp);

		if (getWizard() instanceof TunerAllocationWizard) {
			bgJobButton = new Button(bgJobComp, SWT.CHECK);
			bgJobButton.setText("Run in background");
		}

	}

	private void setValueForProp(TunerAllocationProperties allocProp, ScaSimpleProperty simple) {
		switch (allocProp) {
		case ALLOCATION_ID:
			simple.setValue(allocIdText.getText());
			break;
		case BANDWIDTH:
			String bwString = bwText.getText();
			Double bw = null;
			if (!"".equals(bwString.trim())) {
				try {
					bw = Double.parseDouble(bwString);
				} catch (NumberFormatException e) {
					// PASS
				}
			}
			if (bw != null) {
				simple.setValue(bw);
			}
			break;
		case BANDWIDTH_TOLERANCE:
			String bwTolString = bwTolText.getText();
			Double bwTol = null;
			if (!"".equals(bwTolString.trim())) {
				try {
					bwTol = Double.parseDouble(bwTolString);
				} catch (NumberFormatException e) {
					// PASS
				}
			}
			if (bwTol != null) {
				simple.setValue(bwTol);
			}
			break;
		case CENTER_FREQUENCY:
			String cfString = cfText.getText();
			Double cf = null;
			if (!"".equals(cfString.trim())) {
				try {
					cf = Double.parseDouble(cfString);
				} catch (NumberFormatException e) {
					// PASS
				}
			}
			if (cf != null) {
				simple.setValue(cf);
			}
			break;
		case DEVICE_CONTROL:
			IStructuredSelection structuredSelection = (IStructuredSelection) allocationComboViewer.getSelection();
			String selection = (String) structuredSelection.getFirstElement();
			if (ALLOCATE_TUNER.equals(selection)) {
				allocationMode = AllocationMode.TUNER;
				tunerAllocationStruct.getSimple(TunerAllocationProperties.DEVICE_CONTROL.getId()).setValue(true);
				simple.setValue(ALLOCATE_TUNER.equals(selection));
				break;
			} else if (selection == null) {
				simple.setValue(true);
				break;
			}
			simple.setValue(ALLOCATE_TUNER.equals(selection));
			break;
		case RF_FLOW_ID:
			simple.setValue(rfFlowIdText.getText());
			break;
		case SAMPLE_RATE:
			String srString = srText.getText();
			Double sr = null;
			if (!"".equals(srString.trim())) {
				try {
					sr = Double.parseDouble(srString);
				} catch (NumberFormatException e) {
					// PASS
				}
			}
			if (sr != null) {
				simple.setValue(sr);
			}
			break;
		case SAMPLE_RATE_TOLERANCE:
			String srTolString = srTolText.getText();
			Double srTol = null;
			if (!"".equals(srTolString.trim())) {
				try {
					srTol = Double.parseDouble(srTolString);
				} catch (NumberFormatException e) {
					// PASS
				}
			}
			if (srTol != null) {
				simple.setValue(srTol);
			}
			break;
		case TUNER_TYPE:
			IStructuredSelection sel = (IStructuredSelection) typeCombo.getSelection();
			if (sel.getFirstElement() instanceof String) {
				simple.setValue(sel.getFirstElement());
			}
			break;
		case GROUP_ID:
			simple.setValue(groupIdText.getText());
			break;
		default:
		}
	}

	private void setValueForProp(ListenerAllocationProperties allocProp, ScaSimpleProperty simple) {
		switch (allocProp) {
		case EXISTING_ALLOCATION_ID:
			simple.setValue(targetAllocText.getText());
			break;
		case LISTENER_ALLOCATION_ID:
			simple.setValue(allocIdText.getText());
			break;
		default:
		}
	}

	private double getUnitsConversionFactor(TunerAllocationProperties prop) {
		switch (prop) {
		case BANDWIDTH:
		case CENTER_FREQUENCY:
		case SAMPLE_RATE:
			return TunerAllocationWizardPage.FREQUENCY_VALUE_CONVERSION_FACTOR;
		case BANDWIDTH_TOLERANCE:
		case SAMPLE_RATE_TOLERANCE:
			return TunerAllocationWizardPage.TOLERANCE_CONVERSION;
		default:
			return 1;
		}
	}

	private void setMinMaxValues() {
		ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
		if (device == null) {
			device = this.feiDevice;
		}
		if (device == null) {
			FrontEndUIActivator.getDefault().getLog().log(
				new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Unable to add Allocation wizard page because no Device was found."));
			return;
		}
		ScaStructSequenceProperty statusContainer = (ScaStructSequenceProperty) device.getProperty(TunerStatusProperty.INSTANCE.getId());
		for (ScaStructProperty struct : statusContainer.getStructs()) {
			ScaSimpleProperty availFreqSimple = struct.getSimple(TunerStatusAllocationProperties.AVAILABLE_FREQUENCY.getId());
			if (availFreqSimple != null) {
				updateMinMaxValues(availFreqSimple);
			}
			ScaSimpleProperty availBwSimple = struct.getSimple(TunerStatusAllocationProperties.AVAILABLE_BANDWIDTH.getId());
			if (availBwSimple != null) {
				updateMinMaxValues(availBwSimple);
			}
			ScaSimpleProperty availSrSimple = struct.getSimple(TunerStatusAllocationProperties.AVAILABLE_SAMPLE_RATE.getId());
			if (availSrSimple != null) {
				updateMinMaxValues(availSrSimple);
			}
		}
	}

	private void updateMinMaxValues(ScaSimpleProperty simple) {
		TunerStatusAllocationProperties property = TunerStatusAllocationProperties.fromPropID(simple.getId());
		switch (property) {
		case AVAILABLE_BANDWIDTH:
			setMinMaxValue((String) simple.getValue(), property);
			break;
		case AVAILABLE_FREQUENCY:
			setMinMaxValue((String) simple.getValue(), property);
			break;
		case AVAILABLE_SAMPLE_RATE:
			setMinMaxValue((String) simple.getValue(), property);
			break;
		default:
		}
	}

	private void setMinMaxValue(String value, TunerStatusAllocationProperties prop) {
		value = value.replaceAll("[^\\d\\.\\-,]", "");
		String[] parts = value.split("\\-");
		Double candMin = null;
		Double candMax = null;
		if (parts.length == 2) {
			try {
				candMin = Double.parseDouble(parts[0]);
				candMax = Double.parseDouble(parts[1]);
			} catch (NumberFormatException e) {
				// PASS
			}
		} else {
			parts = value.split(",");
			if (parts.length == 1) {
				try {
					candMin = Double.parseDouble(parts[0]);
					candMax = candMin;
				} catch (NumberFormatException e) {
					// PASS
				}
			} else if (parts.length > 1) {
				Map<String, Double> minMax = getMinMax(parts);
				candMin = minMax.get("min");
				candMax = minMax.get("max");
			}
		}
		if (candMin != null && candMax != null) {
			setMinMaxValue(prop, candMin, candMax);
		} else {
			FrontEndUIActivator.getDefault().getLog().log(
				new Status(IStatus.WARNING, FrontEndUIActivator.PLUGIN_ID, "Invalid range value for available capacity: " + value));
		}
	}

	private Map<String, Double> getMinMax(String[] items) {
		Double min = null;
		Double max = null;
		for (String item : items) {
			try {
				double val = Double.parseDouble(item);
				if (min == null || val < min.doubleValue()) {
					min = val;
				}
				if (max == null || val > max.doubleValue()) {
					max = val;
				}
			} catch (NumberFormatException e) {
				continue;
			}
		}
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("min", min);
		map.put("max", max);
		return map;
	}

	private void setMinMaxValue(TunerStatusAllocationProperties prop, Double candidateMin, Double candidateMax) {
		switch (prop) {
		case AVAILABLE_BANDWIDTH:
			if (minBw == null || candidateMin < minBw) {
				minBw = candidateMin;
			}
			if (maxBw == null || candidateMax > maxBw) {
				maxBw = candidateMax;
			}
			break;
		case AVAILABLE_FREQUENCY:
			if (minFreq == null || candidateMin < minFreq) {
				minFreq = candidateMin;
			}
			if (maxFreq == null || candidateMax > maxFreq) {
				maxFreq = candidateMax;
			}
			break;
		case AVAILABLE_SAMPLE_RATE:
			if (minSr == null || candidateMin < minSr) {
				minSr = candidateMin;
			}
			if (maxSr == null || candidateMax > maxSr) {
				maxSr = candidateMax;
			}
			break;
		default:
		}

	}

	public ScaStructProperty getTunerAllocationStruct() {
		return this.tunerAllocationStruct;
	}

	public ScaStructProperty getListenerAllocationStruct() {
		return this.listenerAllocationStruct;
	}

	public AllocationMode getAllocationMode() {
		return this.allocationMode;
	}

	public ScaStructProperty getAllocationStruct() {
		switch (allocationMode) {
		case LISTENER:
			return listenerAllocationStruct;
		case TUNER:
			return tunerAllocationStruct;
		default:
			return null;
		}
	}

	/**
	 * Returns true if allocation is to be run in a background job
	 * @return
	 */
	public boolean isBackgroundJob() {
		return bgJobButton.getSelection();
	}

	public void setTunerAllocationStruct(ScaStructProperty tunerAllocationStruct) {
		this.tunerAllocationStruct = tunerAllocationStruct;
	}

	public void setListenerAllocationStruct(ScaStructProperty listenerAllocationStruct) {
		this.listenerAllocationStruct = listenerAllocationStruct;
	}

	public EMFDataBindingContext getContext() {
		return context;
	}

}

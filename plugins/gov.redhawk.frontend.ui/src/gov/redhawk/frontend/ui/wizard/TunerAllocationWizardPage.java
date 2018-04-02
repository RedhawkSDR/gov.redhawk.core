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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
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
import gov.redhawk.frontend.util.MultiRange;
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
import org.eclipse.core.databinding.conversion.Converter;

public class TunerAllocationWizardPage extends WizardPage {

	private static final double FREQUENCY_VALUE_CONVERSION_FACTOR = 1e6;

	private static final String ALLOCATE_TUNER = Messages.TunerAllocationWizardPage_ControlNewTuner;
	private static final String LISTEN_TUNER_BY_ID = Messages.TunerAllocationWizardPage_ListenTunerByID;
	private static final String LISTEN_TUNER_BY_PROPERTIES = Messages.TunerAllocationWizardPage_ListenTunerByProps;
	private static final String[] TUNER_ALLOCATION_TYPES = new String[] { ALLOCATE_TUNER, LISTEN_TUNER_BY_ID, LISTEN_TUNER_BY_PROPERTIES };

	private TunerStatus tuner;
	private ScaDevice< ? > feiDevice;
	private ScaStructProperty tunerAllocationStruct;
	private ScaStructProperty listenerAllocationStruct;

	private ComboViewer allocationComboViewer;
	private Text targetAllocText;
	private Text allocIdText;
	private ComboViewer typeCombo;
	private Text cfText;
	private Text bwText;
	private Button bwAnyValue;
	private Text srText;
	private Button srAnyValue;
	private Text bwTolText;
	private Text srTolText;
	private Text rfFlowIdText;
	private Text groupIdText;
	private Button bgJobButton; // Background Job option
	private List<Control> tunerControls = new ArrayList<Control>();

	private String defaultAllocationType = ALLOCATE_TUNER;
	private AllocationMode allocationMode = AllocationMode.TUNER;
	private EMFDataBindingContext context;
	private NumberFormat nf;

	private MultiRange cfRange = new MultiRange();
	private MultiRange bwRange = new MultiRange();
	private MultiRange srRange = new MultiRange();

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
		}
	};

	/**
	 * String in MHz -> Double in Hz
	 */
	private final IConverter stringToDoubleFreqConverter = new Converter(String.class, Double.class) {
		@Override
		public Object convert(Object value) {
			try {
				return Double.parseDouble((String) value) * TunerAllocationWizardPage.FREQUENCY_VALUE_CONVERSION_FACTOR;
			} catch (NumberFormatException e) {
				return null;
			}
		}
	};

	/**
	 * Double in Hz -> String in MHz
	 */
	private final IConverter doubleToStringFreqConverter = new Converter(Double.class, String.class) {
		@Override
		public Object convert(Object value) {
			if (value == null || ((Double) value) == 0.0) {
				return "";
			}
			return nf.format((Double) value / TunerAllocationWizardPage.FREQUENCY_VALUE_CONVERSION_FACTOR);
		}
	};

	/**
	 * Double -> String, using {@link #nf}
	 */
	private final IConverter doubleToStringConverter = new Converter(Double.class, String.class) {
		@Override
		public Object convert(Object value) {
			if (value == null || ((Double) value) == 0.0) {
				return "";
			}
			return nf.format((Double) value);
		}
	};

	public TunerAllocationWizardPage() {
		super(Messages.TunerAllocationWizardPage_PageName);
		nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);
		nf.setMaximumFractionDigits(6);
		nf.setGroupingUsed(false);
	}

	protected TunerAllocationWizardPage(TunerStatus tuner) {
		this(tuner, null);
	}

	protected TunerAllocationWizardPage(TunerStatus tuner, ScaDevice< ? > device) {
		this();
		Assert.isNotNull(tuner, "Tuner must not be null"); //$NON-NLS-1$
		this.tuner = tuner;
		this.feiDevice = device;
		setMinMaxValues();
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
			allocationMode = AllocationMode.TUNER;
		} else if (ListenerAllocationProperty.INSTANCE.getId().equals(allocationStruct.getId())) {
			setListenerAllocationStruct(allocationStruct);
			defaultAllocationType = LISTEN_TUNER_BY_ID;
			allocationMode = AllocationMode.LISTENER;
		}
	}

	@Override
	public void createControl(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		createGroupControls(comp);
		setControl(comp);

		setTitle(Messages.TunerAllocationWizardPage_PageTitle);
		setDescription(Messages.TunerAllocationWizardPage_PageDescription);

		initializeTunerAllocationStruct();
		initializeListenerAllocationStruct();

		context = new EMFDataBindingContext();
		addBindings();
		WizardPageSupport.create(this, context);
	}

	private void addTunerTypeBindings() {
		UpdateValueStrategy tunerTypeStrategy = new UpdateValueStrategy();
		tunerTypeStrategy.setAfterGetValidator(value -> {
			IStructuredSelection structuredSelection = (IStructuredSelection) allocationComboViewer.getSelection();
			String selection = (String) structuredSelection.getFirstElement();
			if (LISTEN_TUNER_BY_ID.equals(selection)) {
				return ValidationStatus.ok();
			}
			String s = (String) value;
			if (s == null || s.trim().isEmpty()) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_NoTunerType);
			}
			if (!FrontEndUIActivator.SUPPORTED_TUNER_TYPES.contains(s)) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_TunerTypeNotSupported);
			}
			return ValidationStatus.ok();
		});

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
		UpdateValueStrategy targetToModel = new UpdateValueStrategy();
		targetToModel.setAfterGetValidator(value -> {
			String s = (String) value;
			if (s == null | s.trim().isEmpty()) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_NoAllocationID);
			}
			if (s.contains(",")) { //$NON-NLS-1$
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_AllocationIDHasComma);
			}
			return ValidationStatus.ok();
		});

		// For model -> target, because we're binding against two models we need to bind in the correct order. We bind
		// the model whose value we care about last so that its value ends up in the text box. The value will also
		// overwrite the value in the other model we don't care about.
		if (allocationMode == AllocationMode.TUNER) {
			bindListenerAllocID(targetToModel);
			bindTunerAllocID(targetToModel);
		} else {
			bindTunerAllocID(targetToModel);
			bindListenerAllocID(targetToModel);
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

	private void bindTunerAllocID(UpdateValueStrategy targetToModel) {
		ControlDecorationSupport.create(
			context.bindValue(WidgetProperties.text(SWT.Modify).observe(allocIdText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.ALLOCATION_ID.getId())), targetToModel, null),
			SWT.TOP | SWT.LEFT);
	}

	private void bindListenerAllocID(UpdateValueStrategy targetToModel) {
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(allocIdText),
			SCAObservables.observeSimpleProperty(listenerAllocationStruct.getSimple(ListenerAllocationProperties.LISTENER_ALLOCATION_ID.getId())),
			targetToModel, null), SWT.TOP | SWT.LEFT);
	}

	private void addExistingAllocIdBindings() {
		UpdateValueStrategy targetToModel = new UpdateValueStrategy();
		UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
		IValidator validator = value -> {
			if (allocationMode == AllocationMode.TUNER) {
				return ValidationStatus.ok();
			}
			String s = (String) value;
			if (s == null | s.trim().isEmpty()) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_NoExistingAllocationID);
			}
			return ValidationStatus.ok();
		};
		targetToModel.setAfterGetValidator(validator);
		modelToTarget.setAfterGetValidator(validator);

		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(targetAllocText),
			SCAObservables.observeSimpleProperty(listenerAllocationStruct.getSimple(ListenerAllocationProperties.EXISTING_ALLOCATION_ID.getId())),
			targetToModel, modelToTarget), SWT.TOP | SWT.LEFT);
	}

	private void addCfBindings() {
		UpdateValueStrategy targetToModel = new UpdateValueStrategy();
		targetToModel.setConverter(stringToDoubleFreqConverter);
		targetToModel.setAfterGetValidator(value -> {
			if (allocationMode == AllocationMode.LISTENER) {
				return ValidationStatus.ok();
			}
			String s = (String) value;
			if (s == null || s.trim().isEmpty()) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_NoCenterFreq);
			}
			try {
				Double.parseDouble(s);
			} catch (NumberFormatException e) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_InvalidDecimalNumber);
			}
			return ValidationStatus.ok();
		});
		targetToModel.setAfterConvertValidator(value -> {
			if (allocationMode == AllocationMode.LISTENER) {
				return ValidationStatus.ok();
			}
			if (value == null) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_NoCenterFreq);
			}
			Double val = (Double) value;
			if (val <= 0) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_ValueMustBePositiveNonZero);
			}
			if (!cfRange.inRange(val)) {
				return ValidationStatus.warning(Messages.TunerAllocationWizardPage_Error_FreqOutOfRange);
			}
			return ValidationStatus.ok();
		});

		UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
		modelToTarget.setConverter(doubleToStringFreqConverter);

		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(cfText),
			SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.CENTER_FREQUENCY.getId())), targetToModel,
			modelToTarget), SWT.TOP | SWT.LEFT);
	}

	private void addBwBindings() {
		UpdateValueStrategy targetToModel = new UpdateValueStrategy();
		targetToModel.setConverter(stringToDoubleFreqConverter);
		targetToModel.setAfterGetValidator(value -> {
			if (allocationMode == AllocationMode.LISTENER || bwAnyValue.getSelection()) {
				return ValidationStatus.ok();
			}
			String s = (String) value;
			if (s == null || s.trim().isEmpty()) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_NoBandwidth);
			}
			try {
				Double.parseDouble(s);
			} catch (NumberFormatException e) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_InvalidDecimalNumber);
			}
			return ValidationStatus.ok();
		});
		targetToModel.setAfterConvertValidator(value -> {
			if (allocationMode == AllocationMode.LISTENER || bwAnyValue.getSelection()) {
				return ValidationStatus.ok();
			}
			if (value == null) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_NoBandwidth);
			}
			Double val = (Double) value;
			if (val < 0) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_ValueCanNotBeNegative);
			}
			if (!bwRange.inRange(val)) {
				return ValidationStatus.warning(Messages.TunerAllocationWizardPage_Error_FreqOutOfRange);
			}
			return ValidationStatus.ok();
		});

		UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
		modelToTarget.setConverter(doubleToStringFreqConverter);

		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(bwText),
			SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH.getId())), targetToModel, modelToTarget),
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
	}

	private void addBwToleranceBindings() {
		UpdateValueStrategy targetToModel = new EMFUpdateValueStrategy();
		targetToModel.setAfterGetValidator(value -> {
			if (allocationMode == AllocationMode.LISTENER) {
				return ValidationStatus.ok();
			}
			String s = (String) value;
			if (s == null || s.trim().isEmpty()) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_NoBandwidthTolerance);
			}
			try {
				Double.parseDouble(s);
			} catch (NumberFormatException e) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_InvalidDecimalNumber);
			}
			return ValidationStatus.ok();
		});
		targetToModel.setAfterConvertValidator(value -> {
			if (allocationMode == AllocationMode.LISTENER) {
				return ValidationStatus.ok();
			}
			if (value == null) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_NoBandwidthTolerance);
			}
			Double val = (Double) value;
			if (val < 0) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_ValueCanNotBeNegative);
			}
			return ValidationStatus.ok();
		});

		UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
		modelToTarget.setConverter(doubleToStringConverter);

		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(bwTolText),
			SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH_TOLERANCE.getId())), targetToModel,
			modelToTarget), SWT.TOP | SWT.LEFT);
	}

	private void addSrBindings() {
		UpdateValueStrategy targetToModel = new UpdateValueStrategy();
		targetToModel.setConverter(stringToDoubleFreqConverter);
		targetToModel.setAfterGetValidator(value -> {
			if (allocationMode == AllocationMode.LISTENER || srAnyValue.getSelection()) {
				return ValidationStatus.ok();
			}
			String s = (String) value;
			if (s == null || s.trim().isEmpty()) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_NoSampleRate);
			}
			try {
				Double.parseDouble(s);
			} catch (NumberFormatException e) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_InvalidDecimalNumber);
			}
			return ValidationStatus.ok();
		});
		targetToModel.setAfterConvertValidator(value -> {
			if (allocationMode == AllocationMode.LISTENER || srAnyValue.getSelection()) {
				return ValidationStatus.ok();
			}
			if (value == null) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_NoSampleRate);
			}
			Double val = (Double) value;
			if (val < 0) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_ValueCanNotBeNegative);
			}
			if (!srRange.inRange(val)) {
				return ValidationStatus.warning(Messages.TunerAllocationWizardPage_Error_FreqOutOfRange);
			}
			return ValidationStatus.ok();
		});

		UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
		modelToTarget.setConverter(doubleToStringFreqConverter);

		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(srText),
			SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE.getId())), targetToModel, modelToTarget),
			SWT.TOP | SWT.LEFT);
		srText.addListener(SWT.FocusIn, event -> srText.setBackground(null));

		// srAnyValue
		if (tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE.getId()).getValue() != null) {
			srAnyValue.setSelection((Double) tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE.getId()).getValue() == 0);
		}
	}

	private void addSrToleranceBindings() {
		UpdateValueStrategy targetToModel = new EMFUpdateValueStrategy();
		targetToModel.setAfterGetValidator(value -> {
			if (allocationMode == AllocationMode.LISTENER) {
				return ValidationStatus.ok();
			}
			String s = (String) value;
			if (s == null || s.trim().isEmpty()) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_NoSampleRateTolerance);
			}
			try {
				Double.parseDouble(s);
			} catch (NumberFormatException e) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_InvalidDecimalNumber);
			}
			return ValidationStatus.ok();
		});
		targetToModel.setAfterConvertValidator(value -> {
			if (allocationMode == AllocationMode.LISTENER) {
				return ValidationStatus.ok();
			}
			if (value == null) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_NoSampleRateTolerance);
			}
			Double val = (Double) value;
			if (val < 0) {
				return ValidationStatus.error(Messages.TunerAllocationWizardPage_Error_ValueCanNotBeNegative);
			}
			return ValidationStatus.ok();
		});

		UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
		modelToTarget.setConverter(doubleToStringConverter);

		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(srTolText),
			SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE_TOLERANCE.getId())), targetToModel,
			modelToTarget), SWT.TOP | SWT.LEFT);
	}

	private void addAllocationComboBindings() {
		allocationComboViewer.addSelectionChangedListener(allocationModeListener);
		allocationComboViewer.setInput(TUNER_ALLOCATION_TYPES);
		// set selection
		allocationComboViewer.setSelection(new StructuredSelection(defaultAllocationType));
	}

	private void addRfFlowIdBindings() {
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(rfFlowIdText),
			SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.RF_FLOW_ID.getId()))), SWT.TOP | SWT.LEFT);

	}

	private void addGroupIdBindings() {
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(groupIdText),
			SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.GROUP_ID.getId()))), SWT.TOP | SWT.LEFT);

	}

	public void addBindings() {
		addTunerTypeBindings();
		addAllocIdBindings();
		addExistingAllocIdBindings();
		addCfBindings();
		addBwBindings();
		addBwToleranceBindings();
		addSrBindings();
		addSrToleranceBindings();
		addRfFlowIdBindings();
		addAllocationComboBindings();
		addGroupIdBindings();
	}

	private void initializeTunerAllocationStruct() {
		// if not null it was provided in constructor
		if (tunerAllocationStruct != null) {
			return;
		}

		tunerAllocationStruct = ScaFactory.eINSTANCE.createScaStructProperty();
		tunerAllocationStruct.setId(TunerAllocationProperty.INSTANCE.getId());
		tunerAllocationStruct.setDefinition(TunerAllocationProperty.INSTANCE.createProperty());

		// Defaults
		String allocId;
		if (listenerAllocationStruct != null) {
			// Use allocation ID from the existing listener
			allocId = (String) listenerAllocationStruct.getSimple(ListenerAllocationProperties.EXISTING_ALLOCATION_ID.getId()).getValue();
		} else {
			// Generate a new allocation ID
			allocId = generateDefaultAllocID();
			allocIdText.setBackground(allocIdText.getDisplay().getSystemColor(SWT.COLOR_CYAN));
		}
		tunerAllocationStruct.getSimple(TunerAllocationProperties.ALLOCATION_ID.getId()).setValue(allocId);
		tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH_TOLERANCE.getId()).setValue(20.0);
		tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE_TOLERANCE.getId()).setValue(20.0);
		tunerAllocationStruct.getSimple(TunerAllocationProperties.RF_FLOW_ID.getId()).setValue("");
		tunerAllocationStruct.getSimple(TunerAllocationProperties.GROUP_ID.getId()).setValue("");
	}

	private void initializeListenerAllocationStruct() {
		// if not null it was provided in constructor
		if (listenerAllocationStruct != null) {
			return;
		}

		listenerAllocationStruct = ScaFactory.eINSTANCE.createScaStructProperty();
		listenerAllocationStruct.setId(ListenerAllocationProperty.INSTANCE.getId());
		listenerAllocationStruct.setDefinition(ListenerAllocationProperty.INSTANCE.createProperty());
	}

	private String generateDefaultAllocID() {
		if (isRuntime()) {
			return System.getProperty("user.name") + ":" + UUID.randomUUID().toString(); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			return UUID.randomUUID().toString();
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
		allocationComboViewerLabel.setText(Messages.TunerAllocationWizardPage_Allocation_Text);
		Combo allocationCombo = new Combo(allocTypeComp, SWT.READ_ONLY | SWT.SINGLE | SWT.LEAD | SWT.BORDER);
		allocationComboViewer = new ComboViewer(allocationCombo);
		allocationComboViewer.setContentProvider(new ArrayContentProvider());
		GridData allocationComboViewerGridData = GridDataFactory.fillDefaults().grab(false, false).create();

		allocationComboViewer.getControl().setLayoutData(allocationComboViewerGridData);

		Group allocPropGroup = new Group(parent, SWT.SHADOW_ETCHED_IN);
		allocPropGroup.setText(Messages.TunerAllocationWizardPage_AllocationPropsGroup_Text);
		allocPropGroup.setLayout(new GridLayout(2, false));
		GridData groupGridData = GridDataFactory.fillDefaults().grab(true, false).create();
		groupGridData.horizontalSpan = 2;
		allocPropGroup.setLayoutData(groupGridData);

		Label targetAllocLabel = new Label(allocPropGroup, SWT.NONE);
		targetAllocLabel.setText(Messages.TunerAllocationWizardPage_ExistingAllocID_Text);
		targetAllocText = new Text(allocPropGroup, SWT.BORDER);
		targetAllocText.setToolTipText(Messages.TunerAllocationWizardPage_ExistingAllocID_ToolTip);
		targetAllocText.setEnabled(false);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(targetAllocText);

		Label allocIdLabel = new Label(allocPropGroup, SWT.NONE);
		allocIdLabel.setText(Messages.TunerAllocationWizardPage_NewAllocID_Text);
		allocIdText = new Text(allocPropGroup, SWT.BORDER);
		allocIdText.setToolTipText(Messages.TunerAllocationWizardPage_NewAllocID_ToolTip);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(allocIdText);

		Label typeLabel = new Label(allocPropGroup, SWT.NONE);
		typeLabel.setText(Messages.TunerAllocationWizardPage_TunerType_Text);
		typeCombo = new ComboViewer(allocPropGroup, SWT.NONE | SWT.READ_ONLY);
		typeCombo.setContentProvider(new ArrayContentProvider());
		GridDataFactory.fillDefaults().grab(true, false).applyTo(typeCombo.getControl());
		tunerControls.add(typeCombo.getControl());

		Label cfLabel = new Label(allocPropGroup, SWT.NONE);
		cfLabel.setText(Messages.TunerAllocationWizardPage_CenterFreq_Text);
		cfText = new Text(allocPropGroup, SWT.BORDER);
		cfText.setToolTipText(TunerAllocationProperties.CENTER_FREQUENCY.getDescription());
		tunerControls.add(cfText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cfText);

		Label bwLabel = new Label(allocPropGroup, SWT.NONE);
		bwLabel.setText(Messages.TunerAllocationWizardPage_Bandwidth_Text);
		Composite bwComp = new Composite(allocPropGroup, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwComp);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(bwComp);
		bwText = new Text(bwComp, SWT.BORDER);
		bwText.setToolTipText(TunerAllocationProperties.BANDWIDTH.getDescription());
		tunerControls.add(bwText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwText);
		bwAnyValue = new Button(bwComp, SWT.CHECK);
		tunerControls.add(bwAnyValue);
		bwAnyValue.setText(Messages.TunerAllocationWizardPage_BandwidthAny_Text);
		bwAnyValue.addSelectionListener(new UseAnyValueListener());
		GridDataFactory.fillDefaults().grab(false, false).applyTo(bwAnyValue);

		Label srLabel = new Label(allocPropGroup, SWT.NONE);
		srLabel.setText(Messages.TunerAllocationWizardPage_SampleRate_Text);
		Composite srComp = new Composite(allocPropGroup, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srComp);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(srComp);
		srText = new Text(srComp, SWT.BORDER);
		srText.setToolTipText(TunerAllocationProperties.SAMPLE_RATE.getDescription());
		tunerControls.add(srText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srText);
		srAnyValue = new Button(srComp, SWT.CHECK);
		tunerControls.add(srAnyValue);
		srAnyValue.setText(Messages.TunerAllocationWizardPage_SampleRateAny_Text);
		srAnyValue.addSelectionListener(new UseAnyValueListener());
		GridDataFactory.fillDefaults().grab(false, false).applyTo(srAnyValue);

		Label bwTolLabel = new Label(allocPropGroup, SWT.NONE);
		bwTolLabel.setText(Messages.TunerAllocationWizardPage_BandwidthTolerance_Text);
		bwTolText = new Text(allocPropGroup, SWT.BORDER);
		bwTolText.setToolTipText(TunerAllocationProperties.BANDWIDTH_TOLERANCE.getDescription());
		tunerControls.add(bwTolText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwTolText);

		Label srTolLabel = new Label(allocPropGroup, SWT.NONE);
		srTolLabel.setText(Messages.TunerAllocationWizardPage_SampleRateTolerance_Text);
		srTolText = new Text(allocPropGroup, SWT.BORDER);
		srTolText.setToolTipText(TunerAllocationProperties.SAMPLE_RATE_TOLERANCE.getDescription());
		tunerControls.add(srTolText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srTolText);

		Label rfFlowIdLabel = new Label(allocPropGroup, SWT.NONE);
		rfFlowIdLabel.setText(Messages.TunerAllocationWizardPage_RFFlowID_Text);
		rfFlowIdText = new Text(allocPropGroup, SWT.BORDER);
		rfFlowIdText.setToolTipText(TunerAllocationProperties.RF_FLOW_ID.getDescription());
		tunerControls.add(rfFlowIdText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(rfFlowIdText);

		Label groupIdLabel = new Label(allocPropGroup, SWT.NONE);
		groupIdLabel.setText(Messages.TunerAllocationWizardPage_GroupID_Text);
		groupIdText = new Text(allocPropGroup, SWT.BORDER);
		groupIdText.setToolTipText(TunerAllocationProperties.GROUP_ID.getDescription());
		tunerControls.add(groupIdText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(groupIdText);

		Composite bgJobComp = new Composite(parent, SWT.NONE);
		bgJobComp.setLayout(new GridLayout(3, false));
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).align(SWT.END, SWT.CENTER).applyTo(bgJobComp);

		if (isRuntime()) {
			bgJobButton = new Button(bgJobComp, SWT.CHECK);
			bgJobButton.setText(Messages.TunerAllocationWizardPage_RunInBackground_Text);
		}
	}

	/**
	 * @return True if this page is being used in a runtime (not design-time) context.
	 */
	private boolean isRuntime() {
		return (getWizard() instanceof TunerAllocationWizard);
	}

	private void setMinMaxValues() {
		ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
		if (device == null) {
			device = this.feiDevice;
		}
		if (device == null) {
			FrontEndUIActivator.getDefault().getLog().log(
				new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, Messages.TunerAllocationWizardPage_Error_NoDeviceFoundForWizardPage));
			return;
		}

		ScaStructSequenceProperty statusContainer = (ScaStructSequenceProperty) device.getProperty(TunerStatusProperty.INSTANCE.getId());
		for (ScaStructProperty struct : statusContainer.getStructs()) {
			ScaSimpleProperty availFreqSimple = struct.getSimple(TunerStatusAllocationProperties.AVAILABLE_FREQUENCY.getId());
			if (availFreqSimple != null) {
				cfRange.addRange((String) availFreqSimple.getValue());
			}
			ScaSimpleProperty availBwSimple = struct.getSimple(TunerStatusAllocationProperties.AVAILABLE_BANDWIDTH.getId());
			if (availBwSimple != null) {
				bwRange.addRange((String) availBwSimple.getValue());
			}
			ScaSimpleProperty availSrSimple = struct.getSimple(TunerStatusAllocationProperties.AVAILABLE_SAMPLE_RATE.getId());
			if (availSrSimple != null) {
				srRange.addRange((String) availSrSimple.getValue());
			}
		}
	}

	public ScaStructProperty getTunerAllocationStruct() {
		return this.tunerAllocationStruct;
	}

	public void setTunerAllocationStruct(ScaStructProperty tunerAllocationStruct) {
		this.tunerAllocationStruct = tunerAllocationStruct;
	}

	public ScaStructProperty getListenerAllocationStruct() {
		return this.listenerAllocationStruct;
	}

	public void setListenerAllocationStruct(ScaStructProperty listenerAllocationStruct) {
		this.listenerAllocationStruct = listenerAllocationStruct;
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

	public AllocationMode getAllocationMode() {
		return this.allocationMode;
	}

	/**
	 * @return True if allocation is to be run in a background job
	 */
	public boolean isBackgroundJob() {
		return bgJobButton.getSelection();
	}
}

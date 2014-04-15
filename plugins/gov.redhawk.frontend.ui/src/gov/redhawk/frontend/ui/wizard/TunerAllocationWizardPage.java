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

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.ui.FrontEndUIActivator.ALLOCATION_MODE;
import gov.redhawk.frontend.util.TunerProperties.ListenerAllocationProperties;
import gov.redhawk.frontend.util.TunerProperties.StatusProperties;
import gov.redhawk.frontend.util.TunerProperties.TunerAllocationProperties;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.observables.SCAObservables;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.IMessageProvider;
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
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.progress.UIJob;

public class TunerAllocationWizardPage extends WizardPage {

	private TunerStatus tuner;
	private Text allocIdText;
	private Text cfText;
	private Text bwText;
	private Text bwTolText;
	private Text srText;
	private Text srTolText;
	private Button listenBySearch;
	private Text targetAllocText;
	private Text rfFlowIdText;
	private ScaStructProperty tunerAllocationStruct;
	private ScaStructProperty listenerAllocationStruct;
	private static final double FREQUENCY_VALUE_CONVERSION_FACTOR = 1e6;
	private static final double TOLERANCE_CONVERSION = 0.01;
	private UUID uuid;
	private ComboViewer typeCombo;
	private ALLOCATION_MODE allocationMode = ALLOCATION_MODE.TUNER;
	private EMFDataBindingContext context;
	private Button listenById;
	private Button tunerAlloc;
	private Button listenerAlloc;
	private Button srAnyValue;
	private Button bwAnyValue;
	private List<Control> tunerControls = new ArrayList<Control>();
	private NumberFormat nf = NumberFormat.getInstance();
	private Double minBw;
	private Double maxBw;
	private Double minFreq;
	private Double maxFreq;
	private Double minSr;
	private Double maxSr;
	private ScaDevice<?> feiDevice;

	private static final String TUNER_TYPE_MISSING_ERR_MSG = "Please select a Tuner Type";
	private static final String TUNER_TYPE_NOT_SUPPORTED_ERR_MSG = "The selected Tuner Type is not supported";
	private static final String ALLOC_ID_CONTAINS_COMMA_ERR_MSG = "Allocation ID must not contain a comma";
	private static final String ALLOC_ID_MISSING = "Please provide an allocation ID. Any text, excludig commas and colons is acceptable.";
	private static final String EXISTING_LISTENER_ID_MISSING = "Please enter the Allocation ID of an existing Tuner";
	private static final String CENTER_FREQUENCY_ERR_MSG = "Please specify a Center Frequency";
	private static final String BNDWIDTH_ERR_MSG = "Please specify a Bandwidth";
	private static final String SAMPLE_RATE_ERR_MSG = "Please specify a Sample Rate";
	private static final String BANDWIDTH_TOLERANCE_ERR_MSG = "Please specify a Bandwidth Tolerance between 0 and 100";
	private static final String SAMPLE_RATE_TOLERANCE_ERR_MSG = "Please specify a Sample Rate Tolerance between 0 and 100";
	private static final String NOT_VALID_NUMBER_ERR_MSG = "You must enter a valid decimal number";
	private static final String PERCENT_VALUE_ERR_MSG = "Percentage must be entered as a number between 0 and 100";
	private static final String NEGATIVE_ERR_MSG = "The value must not be negative";
	private static final String NEGATIVE_OR_ZERO_ERR_MSG = "The value must be a positive non-zero number";
	private static final String FREQ_BELOW_MIN = "The selected frequency is below the minimum known supported frequency of ";
	private static final String FREQ_ABOVE_MAX = "The selected frequency is above the maximum known supported frequency of ";
	private static final String BW_BELOW_MIN = "The selected bandwidth is below the minimum known supported bandwidth of ";
	private static final String BW_ABOVE_MAX = "The selected bandwidth is above the maximum known supported bandwidth of ";
	private static final String SR_BELOW_MIN = "The selected sample rate is below the minimum known supported sample rate of ";
	private static final String SR_ABOVE_MAX = "The selected sample rate is above the maximum known supported sample rate of ";
	private static final String UNSUPPORTED_TUNER_TYPE = "The selected Tuner type is not yet supported";

	private class TargetableValidator implements IValidator {

		private Control control;

		public TargetableValidator(Control control) {
			this.control = control;
		}

		@Override
		public IStatus validate(Object value) {
			return getValidationStatus(control, value);
		}
	}

	private class TargetableFocusListener implements FocusListener {

		private Control control;

		public TargetableFocusListener(Control control) {
			this.control = control;
		}

		@Override
		public void focusGained(FocusEvent e) {
			//If we don't do this asynchronously, the focus_in event will come afterwards and cancel the selection
			if (control == allocIdText) {
				allocIdText.setBackground(null);
				UIJob job = new UIJob("Select Allocation ID text") {

					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						allocIdText.selectAll();
						return Status.OK_STATUS;
					}

				};
				job.schedule();

			} else if (control == srText) {
				srText.setBackground(null);
			}
			int pageStatus = IMessageProvider.NONE;
			String msg = null;

			String value = null;
			if (control instanceof Text) {
				value = ((Text) control).getText();
			} else if (control instanceof Combo) {
				Combo combo = (Combo) control;
				value = combo.getItem(combo.getSelectionIndex());
			}
			IStatus status = getValidationStatus(control, value);

			switch (status.getSeverity()) {
			case IStatus.OK:
				//PASS
				break;
			case IStatus.WARNING:
				pageStatus = IMessageProvider.WARNING;
				msg = status.getMessage();
				break;
			case IStatus.ERROR:
				setErrorMessage(status.getMessage());
				return;
			default:
			}
			setMessage(msg, pageStatus);
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (control == bwText) {
				if ("".equals(srText.getText())) {
					Double bwVal = null;
					String bwString = bwText.getText();
					try {
						bwVal = Double.parseDouble(bwString);
					} catch (NumberFormatException ex) {
						//PASS
					}

					if (bwVal != null && bwVal.intValue() != 0) {
						double srVal = bwVal * 2;
						NumberFormat localNF = NumberFormat.getInstance();
						localNF.setMinimumFractionDigits(0);
						localNF.setGroupingUsed(false);
						srText.setText(localNF.format(srVal));
						UIJob job = new UIJob("Set SR Text Background") {

							@Override
							public IStatus runInUIThread(IProgressMonitor monitor) {
								srText.setBackground(srText.getDisplay().getSystemColor(SWT.COLOR_CYAN));
								return Status.OK_STATUS;
							}

						};
						job.schedule();
					}
				}
			} else if (control == srText) {
				UIJob job = new UIJob("Clear SR Text Background") {

					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						srText.setBackground(null);
						return Status.OK_STATUS;
					}

				};
				job.schedule();
			}
		}

	}

	private ISelectionChangedListener tunerTypeListener = new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			int pageStatus = IMessageProvider.NONE;
			String msg = null;
			Control control = typeCombo.getControl();
			IStructuredSelection sel = (IStructuredSelection) typeCombo.getSelection();
			String value = (String) sel.getFirstElement();

			IStatus status = getValidationStatus(control, value);

			switch (status.getSeverity()) {
			case IStatus.OK:
				//PASS
				break;
			case IStatus.WARNING:
				pageStatus = IMessageProvider.WARNING;
				msg = status.getMessage();
				break;
			case IStatus.ERROR:
				setErrorMessage(status.getMessage());
				return;
			default:
			}
			setMessage(msg, pageStatus);
		}

	};

	private class UseAnyValueListener extends SelectionAdapter {
		private String previousBwValue;
		private String previousSrValue;

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
					ScaModelCommand.execute(bwSimple, new ScaModelCommand() {

						@Override
						public void execute() {
							bwSimple.setValue(0.0);
							for (Object b : context.getBindings()) {
								((Binding) b).updateModelToTarget();
								((Binding) b).validateModelToTarget();
							}
						}

					});
				} else {
					final ScaSimpleProperty bwSimple = tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH.getId());
					ScaModelCommand.execute(bwSimple, new ScaModelCommand() {

						@Override
						public void execute() {
							try {
								bwText.setText(previousBwValue);
								for (Object b : context.getBindings()) {
									((Binding) b).updateTargetToModel();
									((Binding) b).validateTargetToModel();
								}
							} catch (NumberFormatException e) {
								//PASS
							}
						}

					});
				}
			} else if (b == srAnyValue) {
				srText.setEnabled(!b.getSelection());
				final ScaSimpleProperty srSimple = tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE.getId());
				if (b.getSelection()) {
					previousSrValue = srText.getText();
					ScaModelCommand.execute(srSimple, new ScaModelCommand() {

						@Override
						public void execute() {
							srSimple.setValue(0.0);
							for (Object b : context.getBindings()) {
								((Binding) b).updateModelToTarget();
								((Binding) b).validateModelToTarget();
							}
						}

					});
				} else {
					ScaModelCommand.execute(srSimple, new ScaModelCommand() {

						@Override
						public void execute() {
							try {
								srText.setText(previousSrValue);
								for (Object b : context.getBindings()) {
									((Binding) b).updateTargetToModel();
									((Binding) b).validateTargetToModel();
								}
							} catch (NumberFormatException e) {
								//PASS
							}
						}

					});
				}
			}
		}
	}

	private ModifyListener allocIdListener = new ModifyListener() {

		@Override
		public void modifyText(ModifyEvent e) {
			setPageComplete(validateAsListener());
		}

	};

	private SelectionAdapter allocationModeListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			boolean listener = listenerAlloc.getSelection() && listenById.getSelection();
			allocationMode = (listener) ? ALLOCATION_MODE.LISTENER : ALLOCATION_MODE.TUNER;
			handleAllocationModeChange();
		}
	};

	private void handleAllocationModeChange() {
		for (Control c : tunerControls) {
			c.setEnabled(allocationMode == ALLOCATION_MODE.TUNER);
		}
		listenBySearch.setEnabled(listenerAlloc.getSelection());
		listenById.setEnabled(listenerAlloc.getSelection());
		targetAllocText.setEnabled(listenerAlloc.getSelection() && listenById.getSelection());
		setPageComplete(validateAsListener());
		for (Object binding : context.getBindings()) {
			((Binding) binding).validateModelToTarget();
		}
	}

	private boolean validateAsListener() {
		if (allocationMode == ALLOCATION_MODE.LISTENER) {
			return !"".equals(allocIdText.getText()) && !"".equals(targetAllocText.getText());
		}
		return false;
	}

	public IStatus getValidationStatus(Control control, Object value) {
		if (control == typeCombo.getControl()) {
			String s = (String) value;
			if (!FrontEndUIActivator.SUPPORTED_TUNER_TYPES.contains(s)) {
				return ValidationStatus.error(TunerAllocationWizardPage.UNSUPPORTED_TUNER_TYPE);
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
			if (allocationMode == ALLOCATION_MODE.TUNER) {
				return Status.OK_STATUS;
			}
			String s = (String) value;
			if ("".equals(s)) {
				return ValidationStatus.error(TunerAllocationWizardPage.EXISTING_LISTENER_ID_MISSING);
			}
			return Status.OK_STATUS;
		} else if (allocationMode == ALLOCATION_MODE.LISTENER) {
			return Status.OK_STATUS;
		} else if (control == typeCombo.getControl()) {
			String s = (String) value;
			if (s == null || "".equals(s)) {
				return ValidationStatus.error(TunerAllocationWizardPage.TUNER_TYPE_MISSING_ERR_MSG);
			}
			if (!FrontEndUIActivator.SUPPORTED_TUNER_TYPES.contains(s)) {
				return ValidationStatus.error(TunerAllocationWizardPage.TUNER_TYPE_NOT_SUPPORTED_ERR_MSG);
			}
			return Status.OK_STATUS;
		} else if (control == cfText) {
			if (allocationMode == ALLOCATION_MODE.LISTENER) {
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
			if (allocationMode == ALLOCATION_MODE.LISTENER || bwAnyValue.getSelection()) {
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
			if (allocationMode == ALLOCATION_MODE.LISTENER || srAnyValue.getSelection()) {
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
			if (allocationMode == ALLOCATION_MODE.LISTENER) {
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
			if (val < 0 || val > 100) {
				return ValidationStatus.error(TunerAllocationWizardPage.PERCENT_VALUE_ERR_MSG);
			}
			return Status.OK_STATUS;
		} else if (control == srTolText) {
			if (allocationMode == ALLOCATION_MODE.LISTENER) {
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
			if (val < 0 || val > 100) {
				return ValidationStatus.error(TunerAllocationWizardPage.PERCENT_VALUE_ERR_MSG);
			}
			return Status.OK_STATUS;
		}
		return Status.OK_STATUS;
	}

	protected TunerAllocationWizardPage(TunerStatus tuner) {
		super("Allocate A Tuner");
		Assert.isNotNull(tuner, "Tuner must not be null");
		this.tuner = tuner;
		setMinMaxValues();
		nf.setMinimumFractionDigits(0);
		nf.setGroupingUsed(false);
	}

	protected TunerAllocationWizardPage(TunerStatus tuner, ScaDevice<?> device) {
		super("Allocate A Tuner");
		Assert.isNotNull(tuner, "Tuner must not be null");
		this.tuner = tuner;
		this.feiDevice = device;
		setMinMaxValues();
		nf.setMinimumFractionDigits(0);
		nf.setGroupingUsed(false);
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

	private void addBindings() {

		//Tuner Type combo
		UpdateValueStrategy tunerTypeStrategy = new UpdateValueStrategy();
		tunerTypeStrategy.setAfterGetValidator(new TargetableValidator(typeCombo.getControl()));
		ControlDecorationSupport.create(
			context.bindValue(ViewerProperties.singleSelection().observe(typeCombo),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.TUNER_TYPE.getId())), tunerTypeStrategy, null),
				SWT.TOP | SWT.LEFT);
		typeCombo.addSelectionChangedListener(tunerTypeListener);
		typeCombo.setInput(FrontEndUIActivator.SUPPORTED_TUNER_TYPES.toArray(new String[0]));
		if (tuner.getTunerType() != null) {
			typeCombo.setSelection(new StructuredSelection(tuner.getTunerType()));
		} else {
			typeCombo.setSelection(new StructuredSelection(FRONTEND.TUNER_TYPE_RX_DIGITIZER.value));
		}

		//allocation ID Text
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
		allocIdText.setText(getUsername() + ":" + uuid.toString());
		allocIdText.setBackground(allocIdText.getDisplay().getSystemColor(SWT.COLOR_CYAN));
		allocIdText.addFocusListener(new TargetableFocusListener(allocIdText));

		//Existing allocation ID Text
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
		targetAllocText.addFocusListener(new TargetableFocusListener(targetAllocText));
		targetAllocText.addModifyListener(allocIdListener);

		//CF Text
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
		cfText.addFocusListener(new TargetableFocusListener(cfText));

		//BW Text
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
		bwText.addFocusListener(new TargetableFocusListener(bwText));

		//SR Text
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
		srText.addFocusListener(new TargetableFocusListener(srText));

		//BW Tolerance Text
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
		bwTolText.addFocusListener(new TargetableFocusListener(bwTolText));

		//SR Tolerance Text
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
		srTolText.addFocusListener(new TargetableFocusListener(srTolText));

		//Listener Allocation Check Box
		UpdateValueStrategy listenerAllocStrategy1 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				//If selected, set device control to false
				if (value instanceof Boolean) {
					Boolean selected = (Boolean) value;
					return new Boolean(!selected);
				}
				return new Boolean(true);
			}
		};
		UpdateValueStrategy listenerAllocStrategy2 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				//If selected, set device control to false
				if (value instanceof Boolean) {
					Boolean selected = (Boolean) value;
					return new Boolean(!selected);
				}
				return null;
			}
		};
		context.bindValue(WidgetProperties.selection().observe(listenerAlloc),
			SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.DEVICE_CONTROL.getId())), listenerAllocStrategy1,
			listenerAllocStrategy2);

		//RF FLow ID Text
		ControlDecorationSupport.create(
			context.bindValue(WidgetProperties.text(SWT.Modify).observe(rfFlowIdText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.RF_FLOW_ID.getId())), null, null), SWT.TOP
				| SWT.LEFT);
	}

	private String getUsername() {
		return System.getProperty("user.name");
	}

	private void initializeTunerAllocationStruct() {
		tunerAllocationStruct = ScaFactory.eINSTANCE.createScaStructProperty();
		for (TunerAllocationProperties allocProp : TunerAllocationProperties.values()) {
			ScaSimpleProperty simple = ScaFactory.eINSTANCE.createScaSimpleProperty();
			Simple definition = (Simple) PrfFactory.eINSTANCE.create(PrfPackage.Literals.SIMPLE);
			definition.setType(allocProp.getType());
			definition.setId(allocProp.getType().getLiteral());
			definition.setName(allocProp.getType().getName());
			simple.setDefinition(definition);
			simple.setId(allocProp.getId());
			setValueForProp(allocProp, simple);
			tunerAllocationStruct.getSimples().add(simple);
		}
	}

	private void initializeListenerAllocationStruct() {
		listenerAllocationStruct = ScaFactory.eINSTANCE.createScaStructProperty();
		for (ListenerAllocationProperties allocProp : ListenerAllocationProperties.values()) {
			ScaSimpleProperty simple = ScaFactory.eINSTANCE.createScaSimpleProperty();
			Simple definition = (Simple) PrfFactory.eINSTANCE.create(PrfPackage.Literals.SIMPLE);
			definition.setType(allocProp.getType());
			definition.setId(allocProp.getType().getLiteral());
			definition.setName(allocProp.getType().getName());
			simple.setDefinition(definition);
			simple.setId(allocProp.getId());
			setValueForProp(allocProp, simple);
			listenerAllocationStruct.getSimples().add(simple);
		}
	}

	private void createGroupControls(Composite parent) {
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(parent);

		Composite comp1 = new Composite(parent, SWT.NONE);
		comp1.setLayout(new FillLayout());
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(comp1);

		tunerAlloc = new Button(comp1, SWT.RADIO);
		tunerAlloc.setText("Allocate a Tuner");
		tunerAlloc.addSelectionListener(allocationModeListener);
		tunerAlloc.setSelection(true);

		listenerAlloc = new Button(comp1, SWT.RADIO);
		listenerAlloc.setText("Allocate a listener on an existing Tuner");
		listenerAlloc.addSelectionListener(allocationModeListener);

		Composite comp2 = new Composite(parent, SWT.NONE);
		comp2.setLayout(new FillLayout());
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(comp2);
		listenBySearch = new Button(comp2, SWT.RADIO);
		listenBySearch.setText("Match by search parameters");
		listenBySearch.setToolTipText("Search for a Tuner that matches the specified frequency, bandwidth, and sample rate, and allocate as a listener on that Tuner");
		listenBySearch.addSelectionListener(allocationModeListener);
		listenBySearch.setSelection(true);
		listenBySearch.setEnabled(false);

		listenById = new Button(comp2, SWT.RADIO);
		listenById.setText("Match by existing Allocation ID");
		listenById.setToolTipText("Search for a Tuner with Allocation ID matching the ID specified in the \"Existing Tuner Allocation ID\" field, and allocate as a listener on that Tuner");
		listenById.addSelectionListener(allocationModeListener);
		listenById.setEnabled(false);

		Label targetAllocLabel = new Label(parent, SWT.NONE);
		targetAllocLabel.setText("Existing Tuner Allocation ID");
		targetAllocText = new Text(parent, SWT.BORDER);
		targetAllocText.setToolTipText("If you would like to create a Listener allocation for a specific tuner, enter its Allocation ID here");
		targetAllocText.setEnabled(false);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(targetAllocText);

		Label allocIdLabel = new Label(parent, SWT.NONE);
		allocIdLabel.setText("Your Allocation ID");
		allocIdText = new Text(parent, SWT.BORDER);
		allocIdText.setToolTipText("Enter any ID for ease of reference. Additional characters will be appended after this name, to ensure uniqueness");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(allocIdText);

		Label typeLabel = new Label(parent, SWT.NONE);
		typeLabel.setText("Tuner Type");
		typeCombo = new ComboViewer(parent, SWT.NONE | SWT.READ_ONLY);
		typeCombo.setContentProvider(new ArrayContentProvider());
		GridDataFactory.fillDefaults().grab(true, false).applyTo(typeCombo.getControl());
		tunerControls.add(typeCombo.getControl());

		Label cfLabel = new Label(parent, SWT.NONE);
		cfLabel.setText("Center Frequency (MHz)");
		cfText = new Text(parent, SWT.BORDER);
		tunerControls.add(cfText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cfText);

		Label bwLabel = new Label(parent, SWT.NONE);
		bwLabel.setText("Bandwidth (MHz)");

		Composite bwComp = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwComp);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(bwComp);
		bwText = new Text(bwComp, SWT.BORDER);
		tunerControls.add(bwText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwText);
		bwAnyValue = new Button(bwComp, SWT.CHECK);
		tunerControls.add(bwAnyValue);
		bwAnyValue.setText("Any Value");
		bwAnyValue.addSelectionListener(new UseAnyValueListener());
		GridDataFactory.fillDefaults().grab(false, false).applyTo(bwAnyValue);

		Label srLabel = new Label(parent, SWT.NONE);
		srLabel.setText("Sample Rate (Msps)");

		Composite srComp = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srComp);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(srComp);
		srText = new Text(srComp, SWT.BORDER);
		tunerControls.add(srText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srText);
		srAnyValue = new Button(srComp, SWT.CHECK);
		tunerControls.add(srAnyValue);
		srAnyValue.setText("Any Value");
		srAnyValue.addSelectionListener(new UseAnyValueListener());
		GridDataFactory.fillDefaults().grab(false, false).applyTo(srAnyValue);

		Label bwTolLabel = new Label(parent, SWT.NONE);
		bwTolLabel.setText("Bandwidth Tolerance (%)");
		bwTolText = new Text(parent, SWT.BORDER);
		tunerControls.add(bwTolText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwTolText);

		Label srTolLabel = new Label(parent, SWT.NONE);
		srTolLabel.setText("Sample Rate Tolerance (%)");
		srTolText = new Text(parent, SWT.BORDER);
		tunerControls.add(srTolText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srTolText);

		Label rfFlowIdLabel = new Label(parent, SWT.NONE);
		rfFlowIdLabel.setText("RF Flow ID");
		rfFlowIdText = new Text(parent, SWT.BORDER);
		rfFlowIdText.setToolTipText("If you would like to allocate tuners for a specific input source, enter the RF Flow ID of the source here");
		tunerControls.add(rfFlowIdText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(rfFlowIdText);
	}

	private void setValueForProp(TunerAllocationProperties allocProp, ScaSimpleProperty simple) {
		switch (allocProp) {
		case ALLOCATION_ID:
			simple.setValue(allocIdText.getText() + ":" + uuid.toString());
			break;
		case BANDWIDTH:
			String bwString = bwText.getText();
			Double bw = null;
			if (!"".equals(bwString.trim())) {
				try {
					bw = Double.parseDouble(bwString);
				} catch (NumberFormatException e) {
					//PASS
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
					//PASS
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
					//PASS
				}
			}
			if (cf != null) {
				simple.setValue(cf);
			}
			break;
		case DEVICE_CONTROL:
			simple.setValue(!listenerAlloc.getSelection());
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
					//PASS
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
					//PASS
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
			simple.setValue("");
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
			simple.setValue(allocIdText.getText() + ":" + uuid.toString());
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
				new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID,
						"Unable to add Allocation wizard page because no Device was found."));
			return;
		}
		ScaStructSequenceProperty statusContainer = (ScaStructSequenceProperty) device.getProperty(StatusProperties.FRONTEND_TUNER_STATUS.getId());
		for (ScaStructProperty struct : statusContainer.getStructs()) {
			ScaSimpleProperty availFreqSimple = struct.getSimple(StatusProperties.AVAILABLE_FREQUENCY.getId());
			if (availFreqSimple != null) {
				updateMinMaxValues(availFreqSimple);
			}
			ScaSimpleProperty availBwSimple = struct.getSimple(StatusProperties.AVAILABLE_BANDWIDTH.getId());
			if (availBwSimple != null) {
				updateMinMaxValues(availBwSimple);
			}
			ScaSimpleProperty availSrSimple = struct.getSimple(StatusProperties.AVAILABLE_SAMPLE_RATE.getId());
			if (availSrSimple != null) {
				updateMinMaxValues(availSrSimple);
			}
		}
	}

	private void updateMinMaxValues(ScaSimpleProperty simple) {
		StatusProperties property = StatusProperties.getValueFor(simple);
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

	private void setMinMaxValue(String value, StatusProperties prop) {
		value = value.replaceAll("[^\\d\\.\\-,]", "");
		String[] parts = value.split("\\-");
		Double candMin = null;
		Double candMax = null;
		if (parts.length == 2) {
			try {
				candMin = Double.parseDouble(parts[0]);
				candMax = Double.parseDouble(parts[1]);
			} catch (NumberFormatException e) {
				//PASS
			}
		} else {
			parts = value.split(",");
			if (parts.length == 1) {
				try {
					candMin = Double.parseDouble(parts[0]);
					candMax = candMin;
				} catch (NumberFormatException e) {
					//PASS
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

	private void setMinMaxValue(StatusProperties prop, Double candidateMin, Double candidateMax) {
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

	public ALLOCATION_MODE getAllocationMode() {
		return this.allocationMode;
	}
}

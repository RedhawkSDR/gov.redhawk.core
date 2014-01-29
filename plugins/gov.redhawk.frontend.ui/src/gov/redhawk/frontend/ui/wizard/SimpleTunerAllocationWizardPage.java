package gov.redhawk.frontend.ui.wizard;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.edit.utils.TunerProperties.ListenerAllocationProperties;
import gov.redhawk.frontend.edit.utils.TunerProperties.TunerAllocationProperties;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.ui.FrontEndUIActivator.ALLOCATION_MODE;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.observables.SCAObservables;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
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


public class SimpleTunerAllocationWizardPage extends WizardPage {

	private TunerStatus[] tuners;
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
	List<Control> tunerControls = new ArrayList<Control>();
	private NumberFormat nf = NumberFormat.getInstance();

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
				new UIJob("Select Allocation ID text") {

					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						allocIdText.selectAll();return Status.OK_STATUS;
					}

				}.schedule();

			} else if (control == srText) {
				srText.setBackground(null);
			}
			int pageStatus = DialogPage.NONE;
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
			case Status.OK:
				//PASS
				break;
			case Status.WARNING:
				pageStatus = DialogPage.WARNING;
				msg = status.getMessage();
				break;
			case Status.ERROR:
				pageStatus = DialogPage.ERROR;
				msg = status.getMessage();
				break;
			default:
			}
			setMessage(msg, pageStatus);
		}


		@Override
		public void focusLost(FocusEvent e) {
			if (control == bwText) {
				if ("".equals(srText.getText())) {
					Double bwVal = null;
					String bw_s = bwText.getText();
					try {
						bwVal = Double.parseDouble(bw_s);
					} catch (NumberFormatException ex) {
						//PASS
					}

					if (bwVal != null && bwVal.intValue() != 0) {
						double srVal = bwVal *2;
						NumberFormat nf = NumberFormat.getInstance();
						nf.setMinimumFractionDigits(0);
						srText.setText(nf.format(srVal));
						new UIJob("Set SR Text Background") {

							@Override
							public IStatus runInUIThread(
									IProgressMonitor monitor) {
								srText.setBackground(srText.getDisplay().getSystemColor(SWT.COLOR_CYAN));
								return Status.OK_STATUS;
							}

						}.schedule();
					}
				}
			} else if (control == srText) {
				new UIJob("Clear SR Text Background") {

					@Override
					public IStatus runInUIThread(
							IProgressMonitor monitor) {
						srText.setBackground(null);
						return Status.OK_STATUS;
					}

				}.schedule();
			}
		}

	}

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
			allocationMode = listener ? ALLOCATION_MODE.LISTENER : ALLOCATION_MODE.TUNER;
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
		if (control == allocIdText) {
			String s = (String) value;
			if (s.contains(",")) {
				return ValidationStatus.error(ALLOC_ID_CONTAINS_COMMA_ERR_MSG);
			} if ("".equals(s)) {
				return ValidationStatus.error(ALLOC_ID_MISSING);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == targetAllocText) {
			if (allocationMode == ALLOCATION_MODE.TUNER) {
				return ValidationStatus.OK_STATUS;
			}
			String s = (String) value;
			if ("".equals(s)) {
				return ValidationStatus.error(EXISTING_LISTENER_ID_MISSING);
			}
			return ValidationStatus.OK_STATUS;
		} else if (allocationMode == ALLOCATION_MODE.LISTENER) {
			return ValidationStatus.OK_STATUS;
		} else if (control == typeCombo.getControl()) {
			String s = (String) value;
			if (s == null || "".equals(s)) {
				return ValidationStatus.error(TUNER_TYPE_MISSING_ERR_MSG);
			}
			if (!FrontEndUIActivator.supportedTunerTypes.contains(s)) {
				return ValidationStatus.error(TUNER_TYPE_NOT_SUPPORTED_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == cfText) {
			if (allocationMode == ALLOCATION_MODE.LISTENER) {
				return ValidationStatus.OK_STATUS;
			}
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(CENTER_FREQUENCY_ERR_MSG);
			}
			Double val = null;
			try{
				val = Double.parseDouble(String.valueOf(value));
			} catch (NumberFormatException e) {
				return ValidationStatus.error(NOT_VALID_NUMBER_ERR_MSG);
			}
			if (val <= 0) {
				return ValidationStatus.error(NEGATIVE_OR_ZERO_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == bwText) {
			if (allocationMode == ALLOCATION_MODE.LISTENER) {
				return ValidationStatus.OK_STATUS;
			}
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(BNDWIDTH_ERR_MSG);
			}
			Double val = null;
			try{
				val = Double.parseDouble(String.valueOf(value));
			} catch (NumberFormatException e) {
				return ValidationStatus.error(NOT_VALID_NUMBER_ERR_MSG);
			}
			if (val < 0) {
				return ValidationStatus.error(NEGATIVE_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == srText) {
			if (allocationMode == ALLOCATION_MODE.LISTENER) {
				return ValidationStatus.OK_STATUS;
			}
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(SAMPLE_RATE_ERR_MSG);
			}
			Double val = null;
			try{
				val = Double.parseDouble(String.valueOf(value));
			} catch (NumberFormatException e) {
				return ValidationStatus.error(NOT_VALID_NUMBER_ERR_MSG);
			}
			if (val < 0) {
				return ValidationStatus.error(NEGATIVE_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == bwTolText) {
			if (allocationMode == ALLOCATION_MODE.LISTENER) {
				return ValidationStatus.OK_STATUS;
			}
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(BANDWIDTH_TOLERANCE_ERR_MSG);
			}
			Double val = null;
			try{
				val = Double.parseDouble(String.valueOf(value));
			} catch (NumberFormatException e) {
				return ValidationStatus.error(NOT_VALID_NUMBER_ERR_MSG);
			}
			if (val < 0 || val > 100) {
				return ValidationStatus.error(PERCENT_VALUE_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == srTolText) {
			if (allocationMode == ALLOCATION_MODE.LISTENER) {
				return ValidationStatus.OK_STATUS;
			}
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(SAMPLE_RATE_TOLERANCE_ERR_MSG);
			}
			Double val = null;
			try{
				val = Double.parseDouble(String.valueOf(value));
			} catch (NumberFormatException e) {
				return ValidationStatus.error(NOT_VALID_NUMBER_ERR_MSG);
			}
			if (val < 0 || val > 100) {
				return ValidationStatus.error(PERCENT_VALUE_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		}
		return ValidationStatus.OK_STATUS;
	}

	protected SimpleTunerAllocationWizardPage(TunerStatus[] tuners) {
		super("Allocate A Tuner");
		this.tuners = tuners;
		nf.setMinimumFractionDigits(0);
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
		ControlDecorationSupport.create(context.bindValue(ViewerProperties.singleSelection().observe(typeCombo),
				SCAObservables.observeSimpleProperty(
						tunerAllocationStruct.getSimple(TunerAllocationProperties.TUNER_TYPE.getId())),
						tunerTypeStrategy,
						null),
						SWT.TOP | SWT.LEFT);
		typeCombo.setInput(FrontEndUIActivator.supportedTunerTypes.toArray(new String[0]));
		typeCombo.setSelection(new StructuredSelection(FRONTEND.TUNER_TYPE_RX_DIGITIZER.value));
		typeCombo.getControl().addFocusListener(new TargetableFocusListener(typeCombo.getControl()));

		//allocation ID Text
		UpdateValueStrategy allocIdStrategy = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				return (String) value;
			}
		};
		allocIdStrategy.setAfterGetValidator(new TargetableValidator(allocIdText));

		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(allocIdText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.ALLOCATION_ID.getId())),
				allocIdStrategy,
				null), SWT.TOP | SWT.LEFT);
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(allocIdText),
				SCAObservables.observeSimpleProperty(listenerAllocationStruct.getSimple(ListenerAllocationProperties.LISTENER_ALLOCATION_ID.getId())),
				allocIdStrategy,
				null),
				SWT.TOP | SWT.LEFT);
		allocIdText.setText(getUsername() + ":" + uuid.toString());
		allocIdText.setBackground(allocIdText.getDisplay().getSystemColor(SWT.COLOR_CYAN));
		allocIdText.addFocusListener(new TargetableFocusListener(allocIdText));
		allocIdText.addModifyListener(allocIdListener);

		//Existing allocation ID Text
		UpdateValueStrategy existingAllocIdStrategy1 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				return (String) value;
			}
		};
		UpdateValueStrategy existingAllocIdStrategy2 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				return (String) value;
			}
		};
		existingAllocIdStrategy1.setAfterGetValidator(new TargetableValidator(targetAllocText));
		existingAllocIdStrategy2.setAfterGetValidator(new TargetableValidator(targetAllocText));

		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(targetAllocText),
				SCAObservables.observeSimpleProperty(listenerAllocationStruct.getSimple(ListenerAllocationProperties.EXISTING_ALLOCATION_ID.getId())),
				existingAllocIdStrategy1,
				existingAllocIdStrategy2),
				SWT.TOP | SWT.LEFT);
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
					if (((Double) value).intValue() == 0) {
						return "";
					}
					double retVal = (Double) value / getUnitsConversionFactor(TunerAllocationProperties.CENTER_FREQUENCY);
					return String.valueOf(nf.format(retVal));
				}
				return null;
			}
		};
		cfStrategy1.setAfterGetValidator(new TargetableValidator(cfText));
		cfStrategy2.setAfterGetValidator(new TargetableValidator(cfText));
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(cfText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.CENTER_FREQUENCY.getId())),
				cfStrategy1,
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
		bwStrategy2.setAfterGetValidator(new TargetableValidator(bwText));
		ControlDecorationSupport.create( context.bindValue(WidgetProperties.text(SWT.Modify).observe(bwText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH.getId())),
				bwStrategy1,
				bwStrategy2), SWT.TOP | SWT.LEFT);
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
		srStrategy2.setAfterGetValidator(new TargetableValidator(srText));
		ControlDecorationSupport.create( context.bindValue(WidgetProperties.text(SWT.Modify).observe(srText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE.getId())),
				srStrategy1,
				srStrategy2), SWT.TOP | SWT.LEFT);
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
				return Double.valueOf((String) value) * getUnitsConversionFactor(TunerAllocationProperties.BANDWIDTH_TOLERANCE);
			}
		};
		UpdateValueStrategy bwTolStrategy2 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				if (value instanceof Double) {
					if (((Double) value).doubleValue() == 0.) {
						return "";
					}
					double retVal = (Double) value / getUnitsConversionFactor(TunerAllocationProperties.BANDWIDTH_TOLERANCE);
					return String.valueOf(nf.format(retVal));
				}
				return null;
			}
		};
		bwTolStrategy1.setAfterGetValidator(new TargetableValidator(bwTolText));
		bwTolStrategy2.setAfterGetValidator(new TargetableValidator(bwTolText));
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(bwTolText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH_TOLERANCE.getId())),
				bwTolStrategy1,
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
				return Double.valueOf((String) value) * getUnitsConversionFactor(TunerAllocationProperties.SAMPLE_RATE_TOLERANCE);
			}
		};
		UpdateValueStrategy srTolStrategy2 = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				if (value instanceof Double) {
					if (((Double) value).doubleValue() == 0.) {
						return "";
					}
					double retVal = (Double) value / getUnitsConversionFactor(TunerAllocationProperties.SAMPLE_RATE_TOLERANCE);
					return String.valueOf(nf.format(retVal));
				}
				return null;
			}
		};
		srTolStrategy1.setAfterGetValidator(new TargetableValidator(srTolText));
		srTolStrategy2.setAfterGetValidator(new TargetableValidator(srTolText));
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(srTolText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE_TOLERANCE.getId())),
				srTolStrategy1,
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
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.DEVICE_CONTROL.getId())),
				listenerAllocStrategy1,
				listenerAllocStrategy2);

		//RF FLow ID Text
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(rfFlowIdText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.RF_FLOW_ID.getId())),
				null,
				null), SWT.TOP | SWT.LEFT);
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
		typeCombo = new ComboViewer(parent, SWT.NONE);
		typeCombo.setContentProvider(new ArrayContentProvider());
		GridDataFactory.fillDefaults().grab(true, false).applyTo(typeCombo.getControl());
		tunerControls.add(typeCombo.getControl());

		Label cfLabel = new Label(parent, SWT.NONE);
		cfLabel.setText("Center Frequency (Mhz)");
		cfText = new Text(parent, SWT.BORDER);
		tunerControls.add(cfText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cfText);

		Label bwLabel = new Label(parent, SWT.NONE);
		bwLabel.setText("Bandwidth (Mhz)");

		Composite bwComp = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwComp);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(bwComp);
		bwText = new Text(bwComp, SWT.BORDER);
		tunerControls.add(bwText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwText);
		bwAnyValue = new Button(bwComp, SWT.CHECK);
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

	private void setValueForProp(TunerAllocationProperties allocProp,
			ScaSimpleProperty simple) {
		switch (allocProp) {
		case ALLOCATION_ID:
			simple.setValue(allocIdText.getText() + ":" + uuid.toString());
			break;
		case BANDWIDTH:
			String bw_s = bwText.getText();
			Double bw = null;
			if (!"".equals(bw_s.trim())) {
				try {
					bw = Double.parseDouble(bw_s);
				} catch (NumberFormatException e) {
					//PASS
				}
			}
			if (bw != null) {
				simple.setValue(bw * getUnitsConversionFactor(allocProp));
			}
			break;
		case BANDWIDTH_TOLERANCE:
			String bwTol_s = bwTolText.getText();
			Double bwTol = null;
			if (!"".equals(bwTol_s.trim())) {
				try {
					bwTol = Double.parseDouble(bwTol_s);
				} catch (NumberFormatException e) {
					//PASS
				}
			}
			if (bwTol != null) {
				simple.setValue(bwTol);
			}
			break;
		case CENTER_FREQUENCY:
			String cf_s = cfText.getText();
			Double cf = null;
			if (!"".equals(cf_s.trim())) {
				try {
					cf = Double.parseDouble(cf_s);
				} catch (NumberFormatException e) {
					//PASS
				}
			}
			if (cf != null) {
				simple.setValue(cf * getUnitsConversionFactor(allocProp));
			}
			break;
		case DEVICE_CONTROL:
			simple.setValue(!listenerAlloc.getSelection());
			break;
		case RF_FLOW_ID:
			simple.setValue(rfFlowIdText.getText());
			break;
		case SAMPLE_RATE:
			String sr_s = srText.getText();
			Double sr = null;
			if (!"".equals(sr_s.trim())) {
				try {
					sr = Double.parseDouble(sr_s);
				} catch (NumberFormatException e) {
					//PASS
				}
			}
			if (sr != null) {
				simple.setValue(sr * getUnitsConversionFactor(allocProp));
			}
			break;
		case SAMPLE_RATE_TOLERANCE:
			String srTol_s = srTolText.getText();
			Double srTol = null;
			if (!"".equals(srTol_s.trim())) {
				try {
					srTol = Double.parseDouble(srTol_s);
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

	private void setValueForProp(ListenerAllocationProperties allocProp,
			ScaSimpleProperty simple) {
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
			return FREQUENCY_VALUE_CONVERSION_FACTOR;
		case BANDWIDTH_TOLERANCE:
		case SAMPLE_RATE_TOLERANCE:
			return TOLERANCE_CONVERSION;
		default:
			return 1;
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

package gov.redhawk.frontend.ui.wizard;

import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.edit.utils.TunerProperties.ListenerAllocationProperties;
import gov.redhawk.frontend.edit.utils.TunerProperties.TunerAllocationProperties;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.ui.FrontEndUIActivator.ALLOCATION_MODE;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.sca.observables.SCAObservables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;

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
import org.eclipse.jface.viewers.ISelection;
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
import org.eclipse.swt.events.SelectionListener;
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
	private Button listenerAlloc;
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
	private Button listenerAlloc2;
	List<Control> tunerControls = new ArrayList<Control>();

	private static final String TUNER_TYPE_MISSING_ERR_MSG = "Please select a Tuner Type";
	private static final String TUNER_TYPE_NOT_SUPPORTED_ERR_MSG = "The selected Tuner Type is not supported";
	private static final String ALLOC_ID_CONTINS_COLON_ERR_MSG = "Allocation ID must not contain a colon";
	private static final String ALLOC_ID_CONTINS_COMMA_ERR_MSG = "Allocation ID must not contain a comma";
	private static final String CENTER_FREQUENCY_ERR_MSG = "Please specify a Center Frequency";
	private static final String BNDWIDTH_ERR_MSG = "Please specify a Bandwidth";
	private static final String SAMPLE_RATE_ERR_MSG = "Please specify a Sample Rate";
	private static final String BANDWIDTH_TOLERANCE_ERR_MSG = "Please specify a Bandwidth Tolerance between 0 and 100";
	private static final String SAMPLE_RATE_TOLERANCE_ERR_MSG = "Please specify a Sample Rate Tolerance between 0 and 100";

	private SelectionAdapter allocationModeListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			boolean listener = listenerAlloc2.getSelection();
			handleAllocationModeChange(listener);
		}
	};
	private boolean listener;
	private String targetId;
	private ModifyListener allocIdListener = new ModifyListener() {

		@Override
		public void modifyText(ModifyEvent e) {
			setPageComplete(validateAsListener());
		}
		
	};

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
			//PASS
		}

	}
	
	private void handleAllocationModeChange(boolean listener) {
		allocationMode = listener ? ALLOCATION_MODE.LISTENER : ALLOCATION_MODE.TUNER;
		for (Control c : tunerControls) {
			c.setEnabled(!listener);
		}
		targetAllocText.setEnabled(listener);
		setPageComplete(validateAsListener());
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
			if (s.contains(":")) {
				return ValidationStatus.error(ALLOC_ID_CONTINS_COLON_ERR_MSG);
			}
			if (s.contains(",")) {
				return ValidationStatus.error(ALLOC_ID_CONTINS_COMMA_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == typeCombo.getControl()) {
			if (allocationMode == ALLOCATION_MODE.LISTENER) {
				return ValidationStatus.OK_STATUS;
			}
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
			return ValidationStatus.OK_STATUS;
		} else if (control == bwText) {
			if (allocationMode == ALLOCATION_MODE.LISTENER) {
				return ValidationStatus.OK_STATUS;
			}
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(BNDWIDTH_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == srText) {
			if (allocationMode == ALLOCATION_MODE.LISTENER) {
				return ValidationStatus.OK_STATUS;
			}
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(SAMPLE_RATE_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == bwTolText) {
			if (allocationMode == ALLOCATION_MODE.LISTENER) {
				return ValidationStatus.OK_STATUS;
			}
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(BANDWIDTH_TOLERANCE_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == srTolText) {
			if (allocationMode == ALLOCATION_MODE.LISTENER) {
				return ValidationStatus.OK_STATUS;
			}
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(SAMPLE_RATE_TOLERANCE_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		}
		return ValidationStatus.OK_STATUS;
	}

	protected SimpleTunerAllocationWizardPage(TunerStatus[] tuners, boolean listener, String targetId) {
		super("Allocate A Tuner");
		this.tuners = tuners;
		this.listener = listener;
		this.targetId = targetId;
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
		
		if (listener) {
			setupListener(targetId);
			handleAllocationModeChange(true);
		}
	}

	private void addBindings() {

		//Tuner Type combo
		UpdateValueStrategy tunerTypeStrategy = new UpdateValueStrategy();
		tunerTypeStrategy.setBeforeSetValidator(new TargetableValidator(typeCombo.getControl()));
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
				return (String) value + ":" + uuid.toString();
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
				null), SWT.TOP | SWT.LEFT);
		allocIdText.setText(getUsername());
		allocIdText.addFocusListener(new TargetableFocusListener(allocIdText));
		allocIdText.addModifyListener(allocIdListener);

		//CF Text
		UpdateValueStrategy cfStrategy = new UpdateValueStrategy() {
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
		cfStrategy.setBeforeSetValidator(new TargetableValidator(cfText));
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(cfText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.CENTER_FREQUENCY.getId())),
				cfStrategy,
				null), SWT.TOP | SWT.LEFT);
		cfText.addFocusListener(new TargetableFocusListener(cfText));

		//BW Text
		UpdateValueStrategy bwStrategy = new UpdateValueStrategy() {
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
		bwStrategy.setBeforeSetValidator(new TargetableValidator(bwText));
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(bwText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH.getId())),
				bwStrategy,
				null), SWT.TOP | SWT.LEFT);
		bwText.addFocusListener(new TargetableFocusListener(bwText));

		//SR Text
		UpdateValueStrategy srStrategy = new UpdateValueStrategy() {
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
		srStrategy.setBeforeSetValidator(new TargetableValidator(srText));
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(srText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE.getId())),
				srStrategy,
				null), SWT.TOP | SWT.LEFT);
		srText.addFocusListener(new TargetableFocusListener(srText));

		//BW Tolerance Text
		UpdateValueStrategy bwTolStrategy = new UpdateValueStrategy() {
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
		bwTolStrategy.setBeforeSetValidator(new TargetableValidator(bwTolText));
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(bwTolText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH_TOLERANCE.getId())),
				bwTolStrategy,
				null), SWT.TOP | SWT.LEFT);
		bwTolText.setText("20");
		bwTolText.addFocusListener(new TargetableFocusListener(bwTolText));

		//SR Tolerance Text
		UpdateValueStrategy srTolStrategy = new UpdateValueStrategy() {
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
		srTolStrategy.setBeforeSetValidator(new TargetableValidator(srTolText));
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(srTolText),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE_TOLERANCE.getId())),
				srTolStrategy,
				null), SWT.TOP | SWT.LEFT);
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
				return new Boolean(true);
			}
		};
		context.bindValue(WidgetProperties.selection().observe(listenerAlloc),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.DEVICE_CONTROL.getId())),
				listenerAllocStrategy1,
				listenerAllocStrategy2);
		//Ensure model value is initialized
		listenerAlloc.setSelection(true);
		listenerAlloc.setSelection(false);

		//Target Allocation ID text
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(targetAllocText),
				SCAObservables.observeSimpleProperty(listenerAllocationStruct.getSimple(ListenerAllocationProperties.EXISTING_ALLOCATION_ID.getId())),
				null,
				null), SWT.TOP | SWT.LEFT);
		targetAllocText.addModifyListener(allocIdListener);

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

		Label typeLabel = new Label(parent, SWT.NONE);
		typeLabel.setText("Tuner Type");
		typeCombo = new ComboViewer(parent, SWT.NONE);
		typeCombo.setContentProvider(new ArrayContentProvider());
		GridDataFactory.fillDefaults().grab(true, false).applyTo(typeCombo.getControl());
		tunerControls.add(typeCombo.getControl());

		Label allocIdLabel = new Label(parent, SWT.NONE);
		allocIdLabel.setText("Your Allocation ID");
		allocIdText = new Text(parent, SWT.BORDER);
		allocIdText.setToolTipText("Enter any ID for ease of reference. Additional characters will be appended after this name, to ensure uniqueness");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(allocIdText);

		Label cfLabel = new Label(parent, SWT.NONE);
		cfLabel.setText("Requested Center Frequency (Mhz)");
		cfText = new Text(parent, SWT.BORDER);
		tunerControls.add(cfText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cfText);

		Label bwLabel = new Label(parent, SWT.NONE);
		bwLabel.setText("Requested Bandwidth (Mhz)");
		bwText = new Text(parent, SWT.BORDER);
		tunerControls.add(bwText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwText);

		Label srLabel = new Label(parent, SWT.NONE);
		srLabel.setText("Requested Sample Rate (Msps)");
		srText = new Text(parent, SWT.BORDER);
		tunerControls.add(srText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srText);

		Label bwTolLabel = new Label(parent, SWT.NONE);
		bwTolLabel.setText("Allowable Bandwidth Tolerance (%)");
		bwTolText = new Text(parent, SWT.BORDER);
		tunerControls.add(bwTolText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwTolText);

		Label srTolLabel = new Label(parent, SWT.NONE);
		srTolLabel.setText("Allowable Sample Rate Tolerance (%)");
		srTolText = new Text(parent, SWT.BORDER);
		tunerControls.add(srTolText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srTolText);

		listenerAlloc = new Button(parent, SWT.CHECK);
		listenerAlloc.setText("Allocate as a Listener if necessary");
		listenerAlloc.setToolTipText("Allocate a tuner based on the specified frequency, bandwidth, and sample rate, and accept a Listener allocation if all matching tuners are already allocated");
		listenerAlloc.addSelectionListener(allocationModeListener);
		tunerControls.add(listenerAlloc);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, false).applyTo(listenerAlloc);

		listenerAlloc2 = new Button(parent, SWT.CHECK);
		listenerAlloc2.setText("Allocate as a Listener to an existing tuner");
		listenerAlloc2.setToolTipText("Add a listener allocation to the tuner specified in the \"Existing Tuner Allocation ID\" field, ignoring all other allocation request parameters");
		listenerAlloc2.addSelectionListener(allocationModeListener);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, false).applyTo(listenerAlloc2);

		Label targetAllocLabel = new Label(parent, SWT.NONE);
		targetAllocLabel.setText("Existing Tuner Allocation ID");
		targetAllocText = new Text(parent, SWT.BORDER);
		targetAllocText.setToolTipText("If you would like to create a Listener allocation for a specific tuner, enter its Allocation ID here");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(targetAllocText);

		Label rfFlowIdLabel = new Label(parent, SWT.NONE);
		rfFlowIdLabel.setText("RF Flow ID");
		rfFlowIdText = new Text(parent, SWT.BORDER);
		rfFlowIdText.setToolTipText("If you would like to allocate tuners for a specific input source, enter the RF Flow ID of the source here");
		tunerControls.add(rfFlowIdText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(rfFlowIdText);

		//setPageComplete(validate());
	}

	public void setupListener(String targetId) {
		listenerAlloc2.setSelection(true);
		targetAllocText.setText(targetId);
		allocationMode = ALLOCATION_MODE.LISTENER;
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

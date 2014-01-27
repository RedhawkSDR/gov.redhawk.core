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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


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

	private static final String TUNER_TYPE_MISSING_ERR_MSG = "Please select a Tuner Type";
	private static final String TUNER_TYPE_NOT_SUPPORTED_ERR_MSG = "The selected Tuner Type is not supported";
	private static final String ALLOC_ID_CONTINS_COLON_ERR_MSG = "Allocation ID must not contain a colon";
	private static final String ALLOC_ID_CONTINS_COMMA_ERR_MSG = "Allocation ID must not contain a comma";
	private static final String CENTER_FREQUENCY_ERR_MSG = "Please specify a Center Frequency";
	private static final String BNDWIDTH_ERR_MSG = "Please specify a Bandwidth";
	private static final String SAMPLE_RATE_ERR_MSG = "Please specify a Sample Rate";
	private static final String BANDWIDTH_TOLERANCE_ERR_MSG = "Please specify a Bandwidth Tolerance between 0 and 100";
	private static final String SAMPLE_RATE_TOLERANCE_ERR_MSG = "Please specify a Sample Rate Tolerance between 0 and 100";

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
			String value = null;
			if (control instanceof Text) {
				value = ((Text) control).getText();
			} else if (control instanceof Combo) {
				Combo combo = (Combo) control;
				value = combo.getItem(combo.getSelectionIndex());
			}
			IStatus status = getValidationStatus(control, value);
			if (status.getSeverity() == Status.OK) {
				setErrorMessage(null);
				setMessage(getDescription());
			} else if (status.getSeverity() == Status.ERROR){
				setErrorMessage(status.getMessage());
			} else if (status.getSeverity() == Status.WARNING){
				setMessage(status.getMessage(), Status.WARNING);
			} 
		}

		@Override
		public void focusLost(FocusEvent e) {
			//PASS
		}

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
			String s = (String) value;
			if (s == null || "".equals(s)) {
				return ValidationStatus.error(TUNER_TYPE_MISSING_ERR_MSG);
			}
			if (!FrontEndUIActivator.supportedTunerTypes.contains(s)) {
				return ValidationStatus.error(TUNER_TYPE_NOT_SUPPORTED_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == cfText) {
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(CENTER_FREQUENCY_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == bwText) {
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(BNDWIDTH_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == srText) {
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(SAMPLE_RATE_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == bwTolText) {
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(BANDWIDTH_TOLERANCE_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == srTolText) {
			if (value == null || "".equals(value)) {
				return ValidationStatus.error(SAMPLE_RATE_TOLERANCE_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		}
		return ValidationStatus.OK_STATUS;
	}

	protected SimpleTunerAllocationWizardPage(TunerContainer container) {
		super("Allocate a Tuner");
		this.tuners = container.getTunerStatus().toArray(new TunerStatus[0]);
	}

	protected SimpleTunerAllocationWizardPage(TunerStatus[] tuners) {
		super("Allocate A Tuner");
		this.tuners = tuners;
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
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.selection().observe(listenerAlloc),
				SCAObservables.observeSimpleProperty(tunerAllocationStruct.getSimple(TunerAllocationProperties.DEVICE_CONTROL.getId())),
				null,
				null), SWT.TOP | SWT.LEFT);

		//Target Allocation ID text
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(targetAllocText),
				SCAObservables.observeSimpleProperty(listenerAllocationStruct.getSimple(ListenerAllocationProperties.EXISTING_ALLOCATION_ID.getId())),
				null,
				null), SWT.TOP | SWT.LEFT);

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

		Label allocIdLabel = new Label(parent, SWT.NONE);
		allocIdLabel.setText("Your Allocation ID");
		allocIdText = new Text(parent, SWT.BORDER);
		allocIdText.setToolTipText("Enter any ID for ease of reference. Additional characters will be appended after this name, to ensure uniqueness");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(allocIdText);

		Label cfLabel = new Label(parent, SWT.NONE);
		cfLabel.setText("Requested Center Frequency (Mhz)");
		cfText = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cfText);

		Label bwLabel = new Label(parent, SWT.NONE);
		bwLabel.setText("Requested Bandwidth (Mhz)");
		bwText = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwText);

		Label srLabel = new Label(parent, SWT.NONE);
		srLabel.setText("Requested Sample Rate (Msps)");
		srText = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srText);

		Label bwTolLabel = new Label(parent, SWT.NONE);
		bwTolLabel.setText("Allowable Bandwidth Tolerance (%)");
		bwTolText = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwTolText);

		Label srTolLabel = new Label(parent, SWT.NONE);
		srTolLabel.setText("Allowable Sample Rate Tolerance (%)");
		srTolText = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srTolText);

		listenerAlloc = new Button(parent, SWT.CHECK);
		listenerAlloc.setText("Allocate as a Listener if necessary");
		listenerAlloc.setToolTipText("Accept a Listener allocation if all matching tuners are already allocated");
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(listenerAlloc);

		Label targetAllocLabel = new Label(parent, SWT.NONE);
		targetAllocLabel.setText("Attach Listener Allocation to existing tuner");
		targetAllocText = new Text(parent, SWT.BORDER);
		targetAllocText.setToolTipText("If you would like to create a Listener allocation for a specific tuner, enter its Allocation ID here");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(targetAllocText);

		Label rfFlowIdLabel = new Label(parent, SWT.NONE);
		rfFlowIdLabel.setText("RF Flow ID");
		rfFlowIdText = new Text(parent, SWT.BORDER);
		rfFlowIdText.setToolTipText("If you would like to allocate tuners for a specific input source, enter the RF Flow ID of the source here");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(rfFlowIdText);

		if (tuners[0] != null) {
			String allocID = tuners[0].getAllocationID();
			if (!(allocID == null || allocID.length() == 0)) {
				SetupListener(tuners[0]);
			}
		}

		setPageComplete(validate());
	}

	private void SetupListener(TunerStatus tuner) {
		String allocID = tuner.getAllocationID();
		int index = allocID.indexOf(",");
		if (index > -1) {
			allocID = allocID.substring(0, index);
		}
		listenerAlloc.setSelection(true);
		targetAllocText.setText(allocID);
		allocationMode = ALLOCATION_MODE.LISTENER;
		ISelection selection = new StructuredSelection(tuner.getTunerType()) {};
		typeCombo.setSelection(selection, true);;
		cfText.setText(Double.toString(tuner.getCenterFrequency() / 1000000.0));
		bwText.setText(Double.toString(tuner.getBandwidth() / 1000000.0));
		srText.setText(Double.toString(tuner.getSampleRate() / 1000000.0));
		allocIdText.setFocus();
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

	private boolean validate() {

		if (!"".trim().equals(targetAllocText.getText()) && !"".trim().equals(allocIdText.getText())) {
			setErrorMessage(null);
			return true;
		}
		IStructuredSelection sel = (IStructuredSelection) typeCombo.getSelection();
		if (sel == null || !(sel.getFirstElement() instanceof String)) {
			setErrorMessage("Please Select a tuner type");
			return false;
		}

		String allocId = allocIdText.getText();
		if (allocId.contains(",")) {
			setErrorMessage("The requested Allocation ID must not contain a comma");
			return false;
		}
		if (allocId.contains(":")) {
			setErrorMessage("The requested Allocation ID must not contain a colon");
			return false;
		}

		String cf = cfText.getText();
		try {
			Double.parseDouble(cf);
		} catch (NumberFormatException e) {
			setErrorMessage("Please enter a number value for the requested Center Frequency");
			return false;
		}

		String bw = bwText.getText();
		try {
			Double.parseDouble(bw);
		} catch (NumberFormatException e) {
			setErrorMessage("Please enter a number value for the requested Bandwidth");
			return false;
		}

		String sr = srText.getText();
		try {
			Double.parseDouble(sr);
		} catch (NumberFormatException e) {
			setErrorMessage("Please enter a number value for the requested Sample Rate");
			return false;
		}

		String bwTol = bwTolText.getText();
		try {
			Double.parseDouble(bwTol);
		} catch (NumberFormatException e) {
			setErrorMessage("Please enter a number value for the Allowable Bandwidth Tolerance");
			return false;
		}

		String srTol = srTolText.getText();
		try {
			Double.parseDouble(srTol);
		} catch (NumberFormatException e) {
			setErrorMessage("Please enter a number value for the Allowable Sample Rate Tolerance");
			return false;
		}

		setErrorMessage(null);
		return true;
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

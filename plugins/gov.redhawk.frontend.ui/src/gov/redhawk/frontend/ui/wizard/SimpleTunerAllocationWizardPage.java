package gov.redhawk.frontend.ui.wizard;

import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.edit.utils.TunerProperties.ListenerAllocationProperties;
import gov.redhawk.frontend.edit.utils.TunerProperties.TunerAllocationProperties;
import gov.redhawk.frontend.ui.FrontEndUIActivator.ALLOCATION_MODE;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;

import java.util.UUID;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
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
	private Text groupIdText;
	private Text rfFlowIdText;
	private ScaStructProperty tunerAllocationStruct;
	private ScaStructProperty listenerAllocationStruct;
	private static final double FREQUENCY_VALUE_CONVERSION_FACTOR = 1e6;
	private static final double TOLERANCE_CONVERSION = 0.01;
	private UUID uuid;
	private ComboViewer typeCombo;
	private ALLOCATION_MODE allocationMode = ALLOCATION_MODE.TUNER;

	protected SimpleTunerAllocationWizardPage(TunerContainer container) {
		super("Allocate a Tune");
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
		addBindings();
	}

	private void addBindings() {
		
		typeCombo.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				setValueForProp(TunerAllocationProperties.TUNER_TYPE,
						tunerAllocationStruct.getSimple(TunerAllocationProperties.TUNER_TYPE.getId()));
				setPageComplete(validate());
			}
			
		});
		
		allocIdText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setValueForProp(
						TunerAllocationProperties.ALLOCATION_ID,
						tunerAllocationStruct.getSimple(TunerAllocationProperties.ALLOCATION_ID.getId()));
				setValueForProp(
						ListenerAllocationProperties.LISTENER_ALLOCATION_ID,
						listenerAllocationStruct.getSimple(ListenerAllocationProperties.LISTENER_ALLOCATION_ID.getId()));
				setPageComplete(validate());
			}

		});
		allocIdText.setText(getUsername());

		cfText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setValueForProp(
						TunerAllocationProperties.CENTER_FREQUENCY,
						tunerAllocationStruct.getSimple(TunerAllocationProperties.CENTER_FREQUENCY.getId()));
				setPageComplete(validate());
			}

		});

		bwText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setValueForProp(
						TunerAllocationProperties.BANDWIDTH,
						tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH.getId()));
				setPageComplete(validate());
			}

		});

		srText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setValueForProp(
						TunerAllocationProperties.SAMPLE_RATE,
						tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE.getId()));
				setPageComplete(validate());
			}

		});

		bwTolText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setValueForProp(
						TunerAllocationProperties.BANDWIDTH_TOLERANCE,
						tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH_TOLERANCE.getId()));
				setPageComplete(validate());
			}

		});
		bwTolText.setText("20");

		srTolText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setValueForProp(
						TunerAllocationProperties.SAMPLE_RATE_TOLERANCE,
						tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE_TOLERANCE.getId()));
				setPageComplete(validate());
			}

		});
		srTolText.setText("20");
		
		listenerAlloc.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				setValueForProp(TunerAllocationProperties.DEVICE_CONTROL,
						tunerAllocationStruct.getSimple(TunerAllocationProperties.DEVICE_CONTROL.getId()));
			}
		});

		targetAllocText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setValueForProp(
						ListenerAllocationProperties.EXISTING_ALLOCATION_ID,
						listenerAllocationStruct.getSimple(ListenerAllocationProperties.EXISTING_ALLOCATION_ID.getId()));
				allocationMode = ("".trim().equals(targetAllocText.getText()) ? ALLOCATION_MODE.TUNER : ALLOCATION_MODE.LISTENER);
				setPageComplete(validate());
			}

		});

		rfFlowIdText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setValueForProp(
						TunerAllocationProperties.RF_FLOW_ID,
						tunerAllocationStruct.getSimple(TunerAllocationProperties.RF_FLOW_ID.getId()));
				setPageComplete(validate());
			}

		});
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
		typeCombo.setInput(new String[] {FRONTEND.TUNER_TYPE_RX_DIGITIZER.value});//TODO Add other tuner types when supported
		typeCombo.getCombo().select(0);
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

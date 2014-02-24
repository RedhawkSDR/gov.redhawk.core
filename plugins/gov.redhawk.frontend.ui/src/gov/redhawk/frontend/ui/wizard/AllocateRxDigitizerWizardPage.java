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

import gov.redhawk.common.ui.widgets.Dval;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator.ALLOCATION_MODE;
import gov.redhawk.frontend.util.TunerProperties.ListenerAllocationProperties;
import gov.redhawk.frontend.util.TunerProperties.StatusProperties;
import gov.redhawk.frontend.util.TunerProperties.TunerAllocationProperties;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class AllocateRxDigitizerWizardPage extends WizardPage {

	//TODO add listener to radio buttons to diable/enable all other fields as needed. Maybe
	//consolidate the checks for enabled state of the allocation options components
	private static final double MIN_BANDWIDTH = 0;
	private static final double MAX_BANDWIDTH = 40000000;
	private static final double MIN_FREQUENCY = 0;
	private static final double MAX_FREQUENCY = 1000000000;
	private static final double MIN_SAMPLERATE = 0;
	private static final double MAX_SAMPLERATE = 1000000;
	private static final double FREQUENCY_VALUE_CONVERSION_FACTOR = 1e6;
	private static final double TOLERANCE_CONVERSION = 0.01;
	private static final double ONE = 1;
	private TunerStatus tuner;
	private Dval cfVal;
	private Dval bwVal;
	private Dval bwTolVal;
	private Dval srVal;
	private Dval srTolVal;
	private Button listenerAlloc;
	private Text groupIdText;
	private Text rfFlowIdText;
	private Text allocIdText;
	private Text idText;
	private UUID uuid;
	private Text typeText;
	private ScaStructProperty tunerAllocationStruct;
	private ScaStructProperty listenerAllocationStruct;
	private Text allocIdCsvText;
	private Button listenById;
	private List<Control> tunerControls = new ArrayList<Control>();
	private List<Control> listenerControls = new ArrayList<Control>();

	protected AllocateRxDigitizerWizardPage(TunerStatus tuner) {
		super("Allocate RX Digitizer Tuner");
		this.tuner = tuner;
	}

	private ALLOCATION_MODE allocationMode = ALLOCATION_MODE.TUNER;
	private Button listenByParams;
	private Text targetAllocText;
	private Label targetAllocLabel;

	@Override
	public void createControl(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		createGroupControls(comp);
		setControl(comp);
		setTitle("RX Digitizer Allocation");
		setDescription("Select the desired parameters for the Tuner to be allocated");
		this.uuid = UUID.randomUUID();
		initializeTunerAllocationStruct();
		initializeListenerAllocationStruct();
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

	private void setValueForProp(TunerAllocationProperties allocProp, ScaSimpleProperty simple) {
		switch (allocProp) {
		case GROUP_ID:
			simple.setValue(groupIdText.getText());
			break;
		case ALLOCATION_ID:
			simple.setValue(allocIdText.getText() + ":" + uuid.toString());
			break;
		case BANDWIDTH:
			simple.setValue(bwVal.getValue() * getUnitsConversionFactor(allocProp));
			break;
		case BANDWIDTH_TOLERANCE:
			simple.setValue(bwTolVal.getValue());
			break;
		case CENTER_FREQUENCY:
			simple.setValue(cfVal.getValue() * getUnitsConversionFactor(allocProp));
			break;
		case DEVICE_CONTROL:
			simple.setValue(!listenerAlloc.getSelection());
			break;
		case RF_FLOW_ID:
			simple.setValue(rfFlowIdText.getText());
			break;
		case SAMPLE_RATE:
			simple.setValue(srVal.getValue() * getUnitsConversionFactor(allocProp));
			break;
		case SAMPLE_RATE_TOLERANCE:
			simple.setValue(srTolVal.getValue());
			break;
		case TUNER_TYPE:
			simple.setValue(typeText.getText());
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

	private void createGroupControls(Composite parent) {
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(parent);

		Label idLabel = new Label(parent, SWT.NONE);
		idLabel.setText("Tuner Instance");
		idText = new Text(parent, SWT.NONE);
		idText.setText(tuner.getTunerID());
		idText.setEditable(false);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(idText);

		Label typeLabel = new Label(parent, SWT.NONE);
		typeLabel.setText("Tuner Type");
		typeText = new Text(parent, SWT.NONE);
		typeText.setText(tuner.getTunerType());
		typeText.setEditable(false);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(typeText);

		Label allocIdCsvLabel = new Label(parent, SWT.NONE);
		allocIdCsvLabel.setText("Existing Allocation ID(s)");
		allocIdCsvText = new Text(parent, SWT.NONE);
		allocIdCsvText.setEditable(false);
		String allocIdCsv = getAllocationIdCsv(tuner);
		allocIdCsvText.setText(allocIdCsv);
		allocIdCsvText.setToolTipText("Allocation and/or listener IDs for Tuners that are already allocated");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(allocIdCsvText);

		Label allocIdLabel = new Label(parent, SWT.NONE);
		allocIdLabel.setText("Requested Allocation ID");
		allocIdText = new Text(parent, SWT.BORDER);
		allocIdText.setToolTipText("Enter any ID for ease of reference. Additional characters will be appended after this name, to ensure uniqueness");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(allocIdText);
		allocIdText.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event event) {
				setValueForProp(TunerAllocationProperties.ALLOCATION_ID, tunerAllocationStruct.getSimple(TunerAllocationProperties.ALLOCATION_ID.getId()));
				setValueForProp(ListenerAllocationProperties.LISTENER_ALLOCATION_ID,
					listenerAllocationStruct.getSimple(ListenerAllocationProperties.LISTENER_ALLOCATION_ID.getId()));
				setPageComplete(validate());
			}

		});

		Label cfLabel = new Label(parent, SWT.NONE);
		cfLabel.setText("Center Frequency (Mhz)");
		tunerControls.add(cfLabel);
		cfVal = new Dval(parent, SWT.NONE);
		setControlValues(cfVal, StatusProperties.AVAILABLE_FREQUENCY);
		tunerControls.add(cfVal);
		cfVal.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event event) {
				setValueForProp(TunerAllocationProperties.CENTER_FREQUENCY, tunerAllocationStruct.getSimple(TunerAllocationProperties.CENTER_FREQUENCY.getId()));
				setPageComplete(validate());
			}

		});
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cfVal);

		Label bwLabel = new Label(parent, SWT.NONE);
		bwLabel.setText("Bandwidth (Mhz)");
		tunerControls.add(bwLabel);
		bwVal = new Dval(parent, SWT.NONE);
		setControlValues(bwVal, StatusProperties.AVAILABLE_BANDWIDTH);
		tunerControls.add(bwVal);
		bwVal.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event event) {
				setValueForProp(TunerAllocationProperties.BANDWIDTH, tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH.getId()));
				setPageComplete(validate());
			}

		});
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwVal);

		Label bwTolLabel = new Label(parent, SWT.NONE);
		bwTolLabel.setText("Bandwidth Tolerance (%)");
		tunerControls.add(bwTolLabel);
		bwTolVal = new Dval(parent, SWT.NONE);
		bwTolVal.setMinimum(0);
		bwTolVal.setMaximum(100);
		bwTolVal.setIncrement(1);
		bwTolVal.setPageIncrement(10);
		bwTolVal.setValue(20d);
		tunerControls.add(bwTolVal);
		bwTolVal.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event event) {
				setValueForProp(TunerAllocationProperties.BANDWIDTH_TOLERANCE,
					tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH_TOLERANCE.getId()));
				setPageComplete(validate());
			}

		});
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwTolVal);

		Label srLabel = new Label(parent, SWT.NONE);
		srLabel.setText("Sample Rate (Msps)");
		tunerControls.add(srLabel);
		srVal = new Dval(parent, SWT.NONE);
		setControlValues(srVal, StatusProperties.AVAILABLE_SAMPLE_RATE);
		tunerControls.add(srVal);
		srVal.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event event) {
				setValueForProp(TunerAllocationProperties.SAMPLE_RATE, tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE.getId()));
				setPageComplete(validate());
			}

		});
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srVal);

		Label srTolLabel = new Label(parent, SWT.NONE);
		srTolLabel.setText("Sample Rate Tolerance (%)");
		tunerControls.add(srTolLabel);
		srTolVal = new Dval(parent, SWT.NONE);
		srTolVal.setMinimum(0);
		srTolVal.setMaximum(100);
		srTolVal.setIncrement(1);
		srTolVal.setPageIncrement(10);
		srTolVal.setValue(20d);
		tunerControls.add(srTolVal);
		srTolVal.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event event) {
				setValueForProp(TunerAllocationProperties.SAMPLE_RATE_TOLERANCE,
					tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE_TOLERANCE.getId()));
				setPageComplete(validate());
			}

		});
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srTolVal);

		listenerAlloc = new Button(parent, SWT.CHECK);
		listenerAlloc.setText("Allocate as listener only");
		listenerAlloc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setValueForProp(TunerAllocationProperties.DEVICE_CONTROL, tunerAllocationStruct.getSimple(TunerAllocationProperties.DEVICE_CONTROL.getId()));
				boolean selected = listenerAlloc.getSelection();
				if (selected) {
					allocationMode = ((listenById.getSelection()) ? ALLOCATION_MODE.LISTENER : ALLOCATION_MODE.TUNER);
				} else {
					allocationMode = ALLOCATION_MODE.TUNER;
				}
				updateControlsEnable();
				setPageComplete(validate());
			}
		});
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(listenerAlloc);

		listenById = new Button(parent, SWT.RADIO);
		listenById.setText("By specified allocation ID");
		listenById.setSelection(true);
		listenById.setEnabled(false);
		listenById.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setValueForProp(TunerAllocationProperties.DEVICE_CONTROL, tunerAllocationStruct.getSimple(TunerAllocationProperties.DEVICE_CONTROL.getId()));
				boolean selected = listenById.getSelection();
				allocationMode = ((selected) ? ALLOCATION_MODE.LISTENER : ALLOCATION_MODE.TUNER);
				updateControlsEnable();
				setPageComplete(validate());
			}
		});
		GridDataFactory.fillDefaults().applyTo(listenById);

		listenByParams = new Button(parent, SWT.RADIO);
		listenByParams.setText("By specified parameters");
		listenByParams.setEnabled(false);
		listenByParams.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setValueForProp(TunerAllocationProperties.DEVICE_CONTROL, tunerAllocationStruct.getSimple(TunerAllocationProperties.DEVICE_CONTROL.getId()));
				setPageComplete(validate());
			}

		});
		GridDataFactory.fillDefaults().grab(true, false).applyTo(listenByParams);

		targetAllocLabel = new Label(parent, SWT.NONE);
		targetAllocLabel.setText("Target Allocation ID");
		targetAllocLabel.setEnabled(false);
		listenerControls.add(targetAllocLabel);
		targetAllocText = new Text(parent, SWT.BORDER);
		targetAllocText.setToolTipText("Specify the Allocation ID of the Tuner against which the listener allocation is to be made");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(targetAllocText);
		targetAllocText.setEnabled(false);
		listenerControls.add(targetAllocText);
		targetAllocText.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event event) {
				setValueForProp(ListenerAllocationProperties.EXISTING_ALLOCATION_ID,
					listenerAllocationStruct.getSimple(ListenerAllocationProperties.EXISTING_ALLOCATION_ID.getId()));
				setPageComplete(validate());
			}

		});

		Label groupIdLabel = new Label(parent, SWT.NONE);
		groupIdLabel.setText("Group ID");
		tunerControls.add(groupIdLabel);
		groupIdText = new Text(parent, SWT.NONE);
		groupIdText.setEditable(false);
		groupIdText.setText((tuner.getGroupID() == null) ? "" : tuner.getGroupID());
		tunerControls.add(groupIdText);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(groupIdText);

		Label rfFlowIdLabel = new Label(parent, SWT.NONE);
		rfFlowIdLabel.setText("RF Flow ID");
		tunerControls.add(rfFlowIdLabel);
		rfFlowIdText = new Text(parent, SWT.BORDER);
		rfFlowIdText.setText((tuner.getRfFlowID() == null) ? "" : tuner.getRfFlowID());
		tunerControls.add(rfFlowIdText);
		rfFlowIdText.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event event) {
				setValueForProp(TunerAllocationProperties.RF_FLOW_ID, tunerAllocationStruct.getSimple(TunerAllocationProperties.RF_FLOW_ID.getId()));
				setPageComplete(validate());
			}

		});
		GridDataFactory.fillDefaults().grab(true, false).applyTo(rfFlowIdText);

		setPageComplete(validate(false));

	}

	private String getAllocationIdCsv(TunerStatus tuner) {
		ScaSimpleProperty simple = tuner.getTunerStatusStruct().getSimple(StatusProperties.ALLOCATION_ID_CSV.getId());
		if (simple != null) {
			String val = (String) simple.getValue();
			if (val != null) {
				return val;
			}
		}
		return "";
	}

	public TunerStatus getTuner() {
		return this.tuner;
	}

	public void setTuner(TunerStatus tuner) {
		this.tuner = tuner;
	}

	private void setControlValues(Dval dVal, StatusProperties prop) {
		ScaStructProperty struct = tuner.getTunerStatusStruct();
		ScaSimpleProperty simple = struct.getSimple(prop.getId());
		if (simple == null) {
			applyDefaultSettings(dVal, prop);
			return;
		}
		String val = (String) simple.getValue();
		String[] parts = val.split("-");
		Double min = Double.parseDouble(parts[0]);
		Double max = Double.parseDouble(parts[1]);
		double mult = getUnitsConversionFactor(prop);
		dVal.setMinimum(min / mult);
		dVal.setMaximum(max / mult);
		dVal.setValue(dVal.getMinimum() + (dVal.getMaximum() - dVal.getMinimum()) / 2);
		dVal.setIncrement((max / mult - min / mult) / 100);
		dVal.setPageIncrement((max / mult - min / mult) / 10);
	}

	private void applyDefaultSettings(Dval dVal, StatusProperties prop) {
		double mult = getUnitsConversionFactor(prop);
		double min = 0;
		double max = 0;
		switch (prop) {
		case AVAILABLE_BANDWIDTH:
			min = AllocateRxDigitizerWizardPage.MIN_BANDWIDTH;
			max = AllocateRxDigitizerWizardPage.MAX_BANDWIDTH;
			break;
		case AVAILABLE_FREQUENCY:
			min = AllocateRxDigitizerWizardPage.MIN_FREQUENCY;
			max = AllocateRxDigitizerWizardPage.MAX_FREQUENCY;
			break;
		case AVAILABLE_SAMPLE_RATE:
			min = AllocateRxDigitizerWizardPage.MIN_SAMPLERATE;
			max = AllocateRxDigitizerWizardPage.MAX_SAMPLERATE;
			break;
		default:
		}

		dVal.setMinimum(min / mult);
		dVal.setMaximum(max / mult);
		dVal.setValue(dVal.getMinimum() + (dVal.getMaximum() - dVal.getMinimum()) / 2);
		dVal.setIncrement((max / mult - min / mult) / 100);
		dVal.setPageIncrement((max / mult - min / mult) / 10);
	}

	private double getUnitsConversionFactor(StatusProperties prop) {
		switch (prop) {
		case AVAILABLE_BANDWIDTH:
		case AVAILABLE_FREQUENCY:
		case AVAILABLE_SAMPLE_RATE:
			return AllocateRxDigitizerWizardPage.FREQUENCY_VALUE_CONVERSION_FACTOR;
		default:
			return AllocateRxDigitizerWizardPage.ONE;
		}
	}

	private double getUnitsConversionFactor(TunerAllocationProperties prop) {
		switch (prop) {
		case BANDWIDTH:
		case CENTER_FREQUENCY:
		case SAMPLE_RATE:
			return AllocateRxDigitizerWizardPage.FREQUENCY_VALUE_CONVERSION_FACTOR;
		case BANDWIDTH_TOLERANCE:
		case SAMPLE_RATE_TOLERANCE:
			return AllocateRxDigitizerWizardPage.TOLERANCE_CONVERSION;
		default:
			return 1;
		}
	}

	private void updateControlsEnable() {
		if (listenerAlloc.getSelection()) {
			listenById.setEnabled(true);
			listenByParams.setEnabled(true);
			enableTunerControls(!listenById.getSelection());
			enableListenerControls(listenById.getSelection());
		} else {
			listenById.setEnabled(false);
			listenByParams.setEnabled(false);
			enableTunerControls(true);
			enableListenerControls(false);
		}
	}

	private void enableTunerControls(boolean enable) {
		for (Control c : tunerControls) {
			c.setEnabled(enable);
		}
	}

	private void enableListenerControls(boolean enable) {
		for (Control c : listenerControls) {
			c.setEnabled(enable);
		}
	}

	@Override
	public IWizardPage getNextPage() {
		return getWizard().getNextPage(this);
	}

	@Override
	public IWizardPage getPreviousPage() {
		return getWizard().getPreviousPage(this);
	}

	private boolean validate() {
		return validate(true);
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

	private boolean validate(boolean updateButtons) {
		boolean valid = true;
		String msg = null;
		if (cfVal.getValue() == 0) {
			msg = "Center Frequency must not be zero";
			valid = false;
		} else if (bwVal.getValue() == 0) {
			msg = "Bandwidth must not be zero";
			valid = false;
		} else if (srVal.getValue() == 0) {
			msg = "Sample Rate must not be zero";
			valid = false;
		} else if ("".equals(allocIdText.getText())) {
			msg = "Please enter a requested Allocation ID";
			valid = false;
		} else if (listenerAlloc.getSelection() && listenById.getSelection()) {
			if ("".equals(targetAllocText.getText())) {
				msg = "Please enter a target allocation ID";
				valid = false;
			}
		}
		setErrorMessage(msg);
		if (updateButtons) {
			getContainer().updateButtons();
		}
		return valid;
	}

}

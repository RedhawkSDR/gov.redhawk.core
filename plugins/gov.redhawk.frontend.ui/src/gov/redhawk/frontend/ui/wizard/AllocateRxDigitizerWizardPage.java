package gov.redhawk.frontend.ui.wizard;

import gov.redhawk.common.ui.widgets.Dval;
import gov.redhawk.frontend.Tuner;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class AllocateRxDigitizerWizardPage extends WizardPage {

	private static final String PROP_AVAILABLE_FREQUENCY = "FRONTEND::tuner_status::available_frequency";
	private static final String PROP_AVAILABLE_BANDWIDTH = "FRONTEND::tuner_status::available_bandwidth";
	private static final String PROP_AVAILABLE_SAMPLE_RATE = "FRONTEND::tuner_status::available_sample_rate";
	private Tuner tuner;

	protected AllocateRxDigitizerWizardPage(Tuner tuner) {
		super("Allocate RX Digitizer Tuner");
		this.tuner = tuner;
	}

	@Override
	public void createControl(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		createGroupControls(comp);
		setControl(comp);
		setTitle("RX Digitizer Allocation");
		setDescription("Select the desired parameters for the Tuner to be allocated");
	}

	private void createGroupControls(Composite parent) {
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(parent);
		
		Label idLabel = new Label(parent, SWT.NONE);
		idLabel.setText("Tuner Instance");
		Text idText = new Text(parent, SWT.NONE);
		idText.setText(tuner.getTunerID());
		idText.setEditable(false);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(idText);
		
		Label typeLabel = new Label(parent, SWT.NONE);
		typeLabel.setText("Tuner Type");
		Text typeText = new Text(parent, SWT.NONE);
		typeText.setText(tuner.getTunerType());
		typeText.setEditable(false);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(typeText);
		
		Label cfLabel = new Label(parent, SWT.NONE);
		cfLabel.setText("Center Frequency");
		Dval cfVal = new Dval(parent, SWT.NONE);
		cfVal.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event event) {
				setPageComplete(validate());
			}
			
		});
		setMinMax(cfVal, PROP_AVAILABLE_FREQUENCY);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(cfVal);
		
		Label bwLabel = new Label(parent, SWT.NONE);
		bwLabel.setText("Bandwidth");
		Dval bwVal = new Dval(parent, SWT.NONE);
		bwVal.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event event) {
				setPageComplete(validate());
			}
			
		});
		setMinMax(bwVal, PROP_AVAILABLE_BANDWIDTH);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwVal);
		
		Label bwTolLabel = new Label(parent, SWT.NONE);
		bwTolLabel.setText("Bandwidth Tolerance (%)");
		Dval bwTolVal = new Dval(parent, SWT.NONE);
		bwTolVal.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event event) {
				setPageComplete(validate());
			}
			
		});
		bwTolVal.setMinimum(0);
		bwTolVal.setMaximum(100);
		bwTolVal.setIncrement(1);
		bwTolVal.setPageIncrement(10);
		bwTolVal.setValue(20d);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(bwTolVal);
		
		Label srLabel = new Label(parent, SWT.NONE);
		srLabel.setText("Sample Rate");
		Dval srVal = new Dval(parent, SWT.NONE);
		srVal.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event event) {
				setPageComplete(validate());
			}
			
		});
		setMinMax(srVal, PROP_AVAILABLE_SAMPLE_RATE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srVal);
		
		Label srTolLabel = new Label(parent, SWT.NONE);
		srTolLabel.setText("Sample Rate Tolerance (%)");
		Dval srTolVal = new Dval(parent, SWT.NONE);
		srTolVal.addListener(SWT.Modify, new Listener() {

			@Override
			public void handleEvent(Event event) {
				setPageComplete(validate());
			}
			
		});
		srTolVal.setMinimum(0);
		srTolVal.setMaximum(100);
		srTolVal.setIncrement(1);
		srTolVal.setPageIncrement(10);
		srTolVal.setValue(20d);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(srTolVal);
		
		Button listenerAlloc = new Button(parent, SWT.CHECK);
		listenerAlloc.setText("Allocate as listener only");
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(listenerAlloc);
		
		Label groupIdLabel = new Label(parent, SWT.NONE);
		groupIdLabel.setText("Group ID");
		Text groupIdtext = new Text(parent, SWT.NONE);
		groupIdtext.setEditable(false);
		groupIdtext.setText((tuner.getGroupID() == null || "".equals(tuner.getGroupID().trim())) ? "[None]" : tuner.getGroupID());
		GridDataFactory.fillDefaults().grab(true, false).applyTo(groupIdtext);
		
		Label rfFlowIdLabel = new Label(parent, SWT.NONE);
		rfFlowIdLabel.setText("RF Flow ID");
		Text rfFlowIdText = new Text(parent, SWT.BORDER);
		rfFlowIdText.setText((tuner.getRfFlowID() == null) ? "" : tuner.getRfFlowID());
		GridDataFactory.fillDefaults().grab(true, false).applyTo(rfFlowIdText);
	
		setPageComplete(validate());
	}
	
	public Tuner getTuner() {
		return this.tuner;
	}
	
	public void setTuner(Tuner tuner) {
		this.tuner = tuner;
	}

	private void setMinMax(Dval dVal, String prop) {
		ScaStructProperty struct = tuner.getTunerStruct();
		ScaSimpleProperty simple = struct.getSimple(prop);
		String val = (String) simple.getValue();
		String[] parts = val.split("-");
		Double min = Double.parseDouble(parts[0]);
		Double max = Double.parseDouble(parts[1]);
		dVal.setMinimum(min);
		dVal.setMaximum(max);
		dVal.setIncrement((max - min) / 100);
		dVal.setPageIncrement((max - min) / 10);
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
		return true;
	}

}

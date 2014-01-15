package gov.redhawk.frontend.ui.wizard;

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.Tuner;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.ui.wizard.AllocateRxDigitizerWizardPage.ALLOCATION_MODE;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.prf.PropertyValueType;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import CF.DataType;
import CF.DevicePackage.InsufficientCapacity;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;

public class TunerAllocationWizard extends Wizard {

	private Tuner tuner;
	private Tuner[] tuners = new Tuner[0];
	private AllocateRxDigitizerWizardPage allocateRxDigitizerPage;
	private AllocateMultipleRxDigitizerWizardPage allocatemultipleRxDigitizersPage;
	private List<Tuner> selectedTuners = new ArrayList<Tuner>();
	private Map<Tuner, IWizardPage> tunerMap = new HashMap<Tuner, IWizardPage>();
	private Map<IWizardPage, Tuner> pageMap = new HashMap<IWizardPage, Tuner>();

	public enum TunerAllocationProperties {
		//	instance name			ID														PRF type
		TUNER_TYPE(				"FRONTEND::tuner_allocation::tuner_type",				PropertyValueType.STRING),
		ALLOCATION_ID(			"FRONTEND::tuner_allocation::allocation_id",			PropertyValueType.STRING),
		CENTER_FREQUENCY(		"FRONTEND::tuner_allocation::center_frequency",			PropertyValueType.DOUBLE),
		BANDWIDTH(				"FRONTEND::tuner_allocation::bandwidth",				PropertyValueType.DOUBLE),
		BANDWIDTH_TOLERANCE(	"FRONTEND::tuner_allocation::bandwidth_tolerance",		PropertyValueType.DOUBLE),
		SAMPLE_RATE(			"FRONTEND::tuner_allocation::sample_rate",				PropertyValueType.DOUBLE),
		SAMPLE_RATE_TOLERANCE(	"FRONTEND::tuner_allocation::sample_rate_tolerance",	PropertyValueType.DOUBLE),
		DEVICE_CONTROL(			"FRONTEND::tuner_allocation::device_control",			PropertyValueType.BOOLEAN),
		GROUP_ID(				"FRONTEND::tuner_allocation::group_id",					PropertyValueType.STRING),
		RF_FLOW_ID(				"FRONTEND::tuner_allocation::rf_flow_id",				PropertyValueType.STRING);

		private String id;
		private PropertyValueType type;

		private TunerAllocationProperties(String id, PropertyValueType prfType) {
			this.id = id;
			this.type = prfType;
		}

		public String getId() {
			return this.id;
		}

		public PropertyValueType getType() {
			return this.type;
		}
	}

	public enum ListenerAllocationProperties {
		//	instance name			ID																	PRF type
		EXISTING_ALLOCATION_ID(		"FRONTEND::listener_allocation::existing_allocation_id",			PropertyValueType.STRING),
		LISTENER_ALLOCATION_ID(		"FRONTEND::listener_allocation::listener_allocation_id",			PropertyValueType.STRING);

		private String id;
		private PropertyValueType type;

		private ListenerAllocationProperties(String id, PropertyValueType prfType) {
			this.id = id;
			this.type = prfType;
		}

		public String getId() {
			return this.id;
		}

		public PropertyValueType getType() {
			return this.type;
		}
	}

	public enum StatusProperties {
		//	instance name			ID														PRF type
		ALLOCATION_ID_CSV(		"FRONTEND::tuner_status::allocation_id_csv",			PropertyValueType.STRING),
		AVAILABLE_FREQUENCY(	"FRONTEND::tuner_status::available_frequency",			PropertyValueType.DOUBLE),
		AVAILABLE_BANDWIDTH(	"FRONTEND::tuner_status::available_bandwidth",			PropertyValueType.DOUBLE),
		AVAILABLE_SAMPLE_RATE(	"FRONTEND::tuner_status::available_sample_rate",		PropertyValueType.DOUBLE);

		private String id;
		private PropertyValueType type;

		private StatusProperties(String id, PropertyValueType prfType) {
			this.id = id;
			this.type = prfType;
		}

		public String getId() {
			return this.id;
		}

		public PropertyValueType getType() {
			return this.type;
		}
	}


	public TunerAllocationWizard(Tuner tuner) {
		this.tuner = tuner;
	}

	public TunerAllocationWizard(TunerContainer container) {
		this.tuners = container.getTuners().toArray(new Tuner[0]);
	}

	public TunerAllocationWizard(Tuner[] tuners) {
		this.tuners  = tuners;
	}

	@Override
	public void addPages() {
		if (tuner != null) {
			allocateRxDigitizerPage = new AllocateRxDigitizerWizardPage(tuner);
			addPage(allocateRxDigitizerPage);
			this.selectedTuners.add(tuner);
			tunerMap.put(tuner, allocateRxDigitizerPage);
			pageMap.put(allocateRxDigitizerPage,  tuner);
		} else if (tuners != null && tuners.length > 0) {
			allocatemultipleRxDigitizersPage = new AllocateMultipleRxDigitizerWizardPage(tuners);
			addPage(allocatemultipleRxDigitizersPage);
			for (Tuner tuner : tuners) {
				AllocateRxDigitizerWizardPage page = new AllocateRxDigitizerWizardPage(tuner);
				addPage(page);
				tunerMap.put(tuner, page);
				pageMap.put(page,  tuner);
			}
		}
	}

	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		IWizardPage prevPage = getPreviousTunerPage(page);
		if (prevPage == null) {
			return allocatemultipleRxDigitizersPage;
		}

		return prevPage;
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == allocatemultipleRxDigitizersPage) {
			if (selectedTuners.size() == 0) {
				return null;
			}
		}
		return getNextTunerPage(page);
	}

	public void addTuner(Tuner tuner) {
		this.selectedTuners.add(tuner);
	}

	public void removeTuner(Tuner tuner) {
		this.selectedTuners.remove(tuner);
	}

	public int getSelectedTunerCount() {
		return this.selectedTuners.size();
	}

	private IWizardPage getNextTunerPage(IWizardPage page) {
		int index;
		if (page == allocatemultipleRxDigitizersPage) {
			index = 0;
		} else {
			Tuner t = pageMap.get(page);
			index = Integer.parseInt(t.getTunerID()) + 1;
		}
		if (index > getMaxIndex()) {
			return null;
		}
		while (index <= getMaxIndex()) {
			if (tunerSelected(getTunerById(index))) {
				break;
			}
			index++;
		}
		Tuner t = getTunerById(index);
		if (t == null || !tunerSelected(t)) {
			return null;
		} else {
			return tunerMap.get(t);
		}
	}

	private int getMaxIndex() {
		int max = 0;
		for (Tuner t : tuners) {
			int index = Integer.parseInt(t.getTunerID());
			if (index > max) {
				max = index;
			}
		}
		return max;
	}

	private boolean tunerSelected(Tuner tuner) {
		if (tuner == null) {
			return false;
		}
		for (Tuner t : selectedTuners) {
			if (t.getTunerID().equals(tuner.getTunerID())) {
				return true;
			}
		}
		return false;
	}

	private Tuner getTunerById(int index) {
		for (Tuner t : tuners) {
			if (Integer.parseInt(t.getTunerID()) == index ) {
				return t;
			}
		}
		return null;
	}

	private IWizardPage getPreviousTunerPage(IWizardPage page) {
		if (page == allocatemultipleRxDigitizersPage) {
			return null;
		}
		int index = Integer.parseInt(pageMap.get(page).getTunerID());
		if (index == 0) {
			return allocatemultipleRxDigitizersPage;
		}
		index--;
		while (!tunerSelected(getTunerById(index)) && index >= 0) {
			index--;
		}
		if (tunerSelected(getTunerById(index))) {
			return tunerMap.get(getTunerById(index));
		} else {
			return allocatemultipleRxDigitizersPage;
		}
	}

	@Override
	public boolean canFinish() {
		boolean canFinish = true;
		if (allocatemultipleRxDigitizersPage != null) {
			canFinish = allocatemultipleRxDigitizersPage.isPageComplete();
		}
		return  canFinish && selectedTunerPagesComplete();
	}


	private boolean selectedTunerPagesComplete() {
		for (Tuner tuner : selectedTuners) {
			IWizardPage page = tunerMap.get(tuner);
			if (!page.isPageComplete()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean performFinish() {
		ScaDevice<?> device = selectedTuners.get(0).getTunerContainer().getModelDevice().getScaDevice();
		boolean result = true;
		StringBuilder sb = new StringBuilder();
		for (Tuner tuner : selectedTuners) {
			DataType[] props = createAllocationProperties(tuner);
			String delim = "";
			try {
				if (!device.allocateCapacity(props)) {
					sb.append(delim + "The allocation requested for Tuner " + tuner.getTunerID() + 
							" was not accepted because resources matching all aspects of the request were not available.");
					delim = "\n\n";
					result = false;
				}
			} catch (InvalidCapacity e) {
				sb.append(delim + "The Allocation Request for Tuner " + tuner.getTunerID() + 
						" was invalid. Please contact your System Administrator. Message: " + e.getMessage());
				delim = "\n\n";
				result = false;
			} catch (InvalidState e) {
				sb.append(delim + "The Allocation Request for Tuner " + tuner.getTunerID() + " failed because the" +
						" device is in an invalid state. Message: " + e.getMessage());
				delim = "\n\n";
				result = false;
			} catch (InsufficientCapacity e) {
				sb.append(delim + "The Allocation Request for Tuner " + tuner.getTunerID() + " failed because the" +
						" device has insufficient capacity. Message: " + e.getMessage());
				delim = "\n\n";
				result = false;
			}
		}
		if (!result) {
			MessageDialog.openError(getShell(), "The Allocation was not successful", sb.toString());
		} else {
			MessageDialog.openInformation(getShell(), "Successful allocation", "The requested allocation has been acepted.");
		}
		return result;
	}

	private DataType[] createAllocationProperties(Tuner tuner) {
		List<DataType> props = new ArrayList<DataType>();
		AllocateRxDigitizerWizardPage page = ((AllocateRxDigitizerWizardPage) tunerMap.get(tuner));
		ScaStructProperty struct;
		DataType dt = new DataType();
		if (page.getAllocationMode() == ALLOCATION_MODE.TUNER) {
			struct = page.getTunerAllocationStruct();
			dt.id = "FRONTEND::tuner_allocation";
		} else {
			struct = page.getListenerAllocationStruct();
			dt.id = "FRONTEND::listener_allocation";
		}
		dt.value = struct.toAny();
		props.add(dt);
		return props.toArray(new DataType[0]);
	}

}

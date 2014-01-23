package gov.redhawk.frontend.ui.wizard;

import gov.redhawk.frontend.FrontendFactory;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.edit.utils.TunerProperties.ListenerAllocationProperties;
import gov.redhawk.frontend.ui.FrontEndUIActivator.ALLOCATION_MODE;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaStructProperty;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

import CF.DataType;
import CF.DevicePackage.InsufficientCapacity;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;

public class TunerAllocationSimpleWizard extends Wizard {


	private TunerStatus[] tuners;
	private SimpleTunerAllocationWizardPage allocatePage;

	public TunerAllocationSimpleWizard(TunerStatus[] tuners) {
		this.tuners = tuners;
	}

	@Override
	public void addPages() {
		allocatePage = new SimpleTunerAllocationWizardPage(tuners);
		addPage(allocatePage);
		if (tuners.length == 1 && tuners[0].getAllocationID() != null && tuners[0].getAllocationID().length() > 0) {

		}
	}

	@Override
	public boolean performFinish() {
		ScaDevice<?> device = tuners[0].getTunerContainer().getModelDevice().getScaDevice();
		boolean result = true;
		StringBuilder sb = new StringBuilder();
		DataType[] props = createAllocationProperties(allocatePage);
		String delim = "";
		try {
			if (!device.allocateCapacity(props)) {
				sb.append(delim + "The allocation request was not accepted because resources matching"
						+ " all aspects of the request were not available.");
				delim = "\n\n";
				result = false;
			}
		} catch (InvalidCapacity e) {
			sb.append(delim + "The allocation request was invalid. Message: " + e.getMessage());
			delim = "\n\n";
			result = false;
		} catch (InvalidState e) {
			sb.append(delim + "The Allocation Request failed because the device is in an invalid state. Message: " + e.getMessage());
			delim = "\n\n";
			result = false;
		} catch (InsufficientCapacity e) {
			sb.append(delim + "The Allocation Request failed because the device has insufficient capacity. Message: " + e.getMessage());
			delim = "\n\n";
			result = false;
		}
		if (result && allocatePage.getAllocationMode() == ALLOCATION_MODE.LISTENER) {
			String listenerID = allocatePage.getListenerAllocationStruct().getSimple(ListenerAllocationProperties.LISTENER_ALLOCATION_ID.getId()).getValue().toString();
			ListenerAllocation listener = FrontendFactory.eINSTANCE.createListenerAllocation();
			listener.setListenerID(listenerID);
			tuners[0].getListenerAllocations().add(listener);
		}

		if (!result) {
			MessageDialog.openError(getShell(), "The Allocation was not successful", sb.toString());
		} else {
			MessageDialog.openInformation(getShell(), "Successful allocation", "The requested allocation has been accepted.");
		}
		return result;
	}
	
	private DataType[] createAllocationProperties(SimpleTunerAllocationWizardPage page) {
		List<DataType> props = new ArrayList<DataType>();
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

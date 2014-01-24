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
		//what is intended here?
//		if (tuners.length == 1 && tuners[0].getAllocationID() != null && tuners[0].getAllocationID().length() > 0) {
//
//		}
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
		//I don't think we want to manually manipulate the tuners on the UI from the wizard. Even if the model is persisting those changes
		//back to the device in the domain, that's also what the allocateCapacity call in line 50 does. We don't want to do that twice,
		//and we should have the UI updated from the device when the device properties are changing. The wizard needs to call allocateCapacity()
		//on the device in order for the capacity to be allocated, and we should then let the UI model be updated from the device when it makes
		//that allocation, rather than update the UI directly in parallel with the call to allocateCapacity(). I think the only time we would want
		//to propagate changes to the device by manipulating the UI model directly is if we expose non-read-only properties to the user and the user
		//changes those properties in the properties view.
		
//		if (result && allocatePage.getAllocationMode() == ALLOCATION_MODE.LISTENER) {
//			String listenerID = allocatePage.getListenerAllocationStruct().getSimple(ListenerAllocationProperties.LISTENER_ALLOCATION_ID.getId()).getValue().toString();
//			ListenerAllocation listener = FrontendFactory.eINSTANCE.createListenerAllocation();
//			listener.setListenerID(listenerID);
//			tuners[0].getListenerAllocations().add(listener);
//		}

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

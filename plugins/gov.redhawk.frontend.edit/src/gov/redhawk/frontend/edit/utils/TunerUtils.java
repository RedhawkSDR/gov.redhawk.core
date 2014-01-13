package gov.redhawk.frontend.edit.utils;

import gov.redhawk.frontend.FrontendFactory;
import gov.redhawk.frontend.ModelDevice;
import gov.redhawk.frontend.Tuner;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.scd.Interface;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import FRONTEND.DigitalTunerHelper;

public enum TunerUtils {
	INSTANCE;

	private Map<EObject, Object[]> fMap = Collections.synchronizedMap(new HashMap<EObject, Object[]>());
	private final TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain("gov.redhawk.sca.editingDomain");
	private Resource tunerStatus;

	private TunerUtils() {
		tunerStatus = editingDomain.getResourceSet().createResource(URI.createURI("null:///tunerStatus.xml"));
	}

	public void processChange(Notification notification) {

	}

	/**
	 * Checks device for tuners, and if found returns a TunerContainer object with an Object array
	 * 
	 * @param device
	 * @return container object for the devices tuners
	 */
	public Object[] getTunerContainer(final ScaDevice< ? > device) {
		//Create model device and tuner container
		ModelDevice modelDevice = FrontendFactory.eINSTANCE.createModelDevice();
		modelDevice.setScaDevice(device);
		modelDevice.setTunerContainer(FrontendFactory.eINSTANCE.createTunerContainer());

		EList<Interface> interfaceList = device.getProfileObj().getDescriptor().getComponent().getInterfaces().getInterface();
		for (Interface i : interfaceList) {
			// Check to see if this is a frontEndInterface
			if (i.getRepid().equals(DigitalTunerHelper.id())) {
				// Fetch list of tuners
				ScaStructSequenceProperty prop = (ScaStructSequenceProperty) device.getProperty("FRONTEND::tuner_status");
				List<ScaStructProperty> structs = prop.getStructs();

				// create TunerContainer model object
				TunerContainer container = modelDevice.getTunerContainer();
				EList<Tuner> tunerList = container.getTuners();

				// populate container object with tuners from device
				for (ScaStructProperty struct : structs) {
					final Tuner tuner = FrontendFactory.eINSTANCE.createTuner();
					tuner.setTunerContainer(container);
					tuner.setTunerStruct(struct);
					tunerList.add(tuner);

					// Assign tuner type to model object - provides tree label
					setTunerType(tuner);

					// TODO - Temporary code, remove this with a better implementation for setting properties
					setTunerProperties(tuner);
				}

				return new Object[] { container };
			}
		}
		return new Object[0];
	}

	/**
	 * 
	 * 
	 * @param container
	 * @return an Object[] containing the tuners associated with the parent device
	 */
	public Object[] getChildren(final TunerContainer container) {
		final List<Tuner> tuners = new ArrayList<Tuner>();
		// Create tuner model object
		Tuner[] tunerList = (Tuner[]) container.getTuners().toArray();
		int numOfTuners = tunerList.length;
		for (int i = 0; i < numOfTuners; i++) {
			final Tuner tuner = tunerList[i];
			tuner.setTunerID(String.valueOf(i));
			tuners.add(tuner);

		}
		if (!tuners.isEmpty()) {
			return tuners.toArray();
		}

		// If there are no tuners, return an empty Object array
		return new Object[0];
	}

	/** 
	 * Assigns tuner type to model object
	 * 
	 * @param tuner represents tuner model object
	 * 
	 */
	private void setTunerType(Tuner tuner) {
		ScaStructProperty tunerDevice = tuner.getTunerStruct();
		String tunerType = tunerDevice.getSimple("FRONTEND::tuner_status::tuner_type").getValue().toString();
		tuner.setTunerType(tunerType);
	}

	/**
	 * Assigns tuner properties to model object
	 * 
	 * @param tunerDevice represents physical tuner
	 * @param tunerModel represents tuner model object
	 */
	private void setTunerProperties(Tuner tuner) {
		ScaStructProperty tunerDevice = tuner.getTunerStruct();
		String allocationID = tunerDevice.getSimple("FRONTEND::tuner_status::allocation_id_csv").getValue().toString();
		tuner.setAllocationID(allocationID);
		// Boolean deviceControl = (Boolean) tunerDevice.getSimple("FRONTEND::tuner_allocation::device_control").getValue();
		// tunerModel.setDeviceControl(deviceControl);
		String groupID = tunerDevice.getSimple("FRONTEND::tuner_status::group_id").getValue().toString();
		tuner.setGroupID(groupID);
		String rfFlowID = tunerDevice.getSimple("FRONTEND::tuner_status::rf_flow_id").getValue().toString();
		tuner.setRfFlowID(rfFlowID);
		Double gain = (Double) tunerDevice.getSimple("FRONTEND::tuner_status::gain").getValue();
		tuner.setGain(gain);

		if (tuner.getTunerStatus() == null) {
			tuner.setTunerStatus(FrontendFactory.eINSTANCE.createTunerStatus());
		}

		Double bandwidth = (Double) tunerDevice.getSimple("FRONTEND::tuner_status::bandwidth").getValue();
		tuner.getTunerStatus().setBandwidth(bandwidth);
		Double centerFrequency = (Double) tunerDevice.getSimple("FRONTEND::tuner_status::center_frequency").getValue();
		tuner.getTunerStatus().setCenterFrequency(centerFrequency);
		Double sampleRate = (Double) tunerDevice.getSimple("FRONTEND::tuner_status::sample_rate").getValue();
		tuner.getTunerStatus().setSampleRate(sampleRate);
		Boolean enabled = (Boolean) tunerDevice.getSimple("FRONTEND::tuner_status::enabled").getValue();
		tuner.getTunerStatus().setEnabled(enabled);
	}
}

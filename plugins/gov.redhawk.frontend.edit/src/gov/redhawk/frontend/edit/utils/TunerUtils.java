package gov.redhawk.frontend.edit.utils;

import gov.redhawk.frontend.FrontendFactory;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.ModelDevice;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;

import java.util.List;

import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.scd.Interface;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;

import FRONTEND.DigitalTunerHelper;

public enum TunerUtils {
	INSTANCE;

	private TunerUtils() {
	}

	public void processChange(Notification notification) {

	}

	/**
	 * Enum of available tuner status properties as defined by specification.
	 * Includes required and optional properties
	 */
	public enum TunerStatusAllocationProperties {
		//  required properties
		//	instance name			ID														PRF type				Human Readable Name
		TUNER_TYPE("FRONTEND::tuner_status::tuner_type", PropertyValueType.STRING, "Tuner Type"),
		ALLOCATION_ID("FRONTEND::tuner_status::allocation_id_csv", PropertyValueType.STRING, "Allocation ID"),
		CENTER_FREQUENCY("FRONTEND::tuner_status::center_frequency", PropertyValueType.DOUBLE, "Center Frequency"),
		BANDWIDTH("FRONTEND::tuner_status::bandwidth", PropertyValueType.DOUBLE, "Bandwidth"),
		SAMPLE_RATE("FRONTEND::tuner_status::sample_rate", PropertyValueType.DOUBLE, "Sample Rate"),
		GROUP_ID("FRONTEND::tuner_status::group_id", PropertyValueType.STRING, "Group ID"),
		RF_FLOW_ID("FRONTEND::tuner_status::rf_flow_id", PropertyValueType.STRING, "RF Flow ID"),
		ENABLED("FRONTEND::tuner_status::enabled", PropertyValueType.BOOLEAN, "Enabled"),

		//  optional properties
		//	instance name			ID														PRF type
		BANDWIDTH_TOLERANCE("FRONTEND::tuner_status::bandwidth_tolerance", PropertyValueType.DOUBLE, "Bandwidth Tolerance"),
		SAMPLE_RATE_TOLERANCE("FRONTEND::tuner_status::sample_rate_tolerance", PropertyValueType.DOUBLE, "Sample Rate Tolerance"),
		COMPLEX("FRONTEND::tuner_status::complex", PropertyValueType.BOOLEAN, "Complex"),
		GAIN("FRONTEND::tuner_status::gain", PropertyValueType.DOUBLE, "Gain"),
		AGC("FRONTEND::tuner_status::agc", PropertyValueType.BOOLEAN, "AGC"),
		VALID("FRONTEND::tuner_status::valid", PropertyValueType.BOOLEAN, "Valid"),
		AVAILABLE_FREQUENCY("FRONTEND::tuner_status::available_frequency", PropertyValueType.STRING, "Available Frequency"),
		AVAILABLE_BANDWIDTH("FRONTEND::tuner_status::available_bandwidth", PropertyValueType.STRING, "Available Bandwidth"),
		AVAILABLE_GAIN("FRONTEND::tuner_status::available_gain", PropertyValueType.STRING, "Available Gain"),
		AVAILABLE_SAMPLE_RATE("FRONTEND::tuner_status::available_sample_rate", PropertyValueType.STRING, "Available Sample Rate"),
		REFERENCE_SOURCE("FRONTEND::tuner_status::reference_source", PropertyValueType.LONG, "Reference Source"),
		OUTPUT_FORMAT("FRONTEND::tuner_status::output_format", PropertyValueType.STRING, "Output Format"),
		OUTPUT_MULTICAST("FRONTEND::tuner_status::output_multicast", PropertyValueType.STRING, "Output Multicast"),
		OUTPUT_VLAN("FRONTEND::tuner_status::output_vlan", PropertyValueType.LONG, "Output VLan"),
		OUTPUT_PORT("FRONTEND::tuner_status::output_port", PropertyValueType.LONG, "Output Port"),
		DECIMATION("FRONTEND::tuner_status::decimation", PropertyValueType.LONG, "Decimation"),
		TUNER_NUMBER("FRONTEND::tuner_status::tuner_number", PropertyValueType.SHORT, "Tuner Number");

		private String id;
		private PropertyValueType type;
		private String name;

		TunerStatusAllocationProperties(String id, PropertyValueType prfType, String name) {
			this.id = id;
			this.type = prfType;
			this.name = name;
		}

		public String getId() {
			return this.id;
		}

		public PropertyValueType getType() {
			return this.type;
		}

		public String getName() {
			return this.name;
		}
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
				EList<TunerStatus> tunerList = container.getTunerStatus();

				// populate container object with tuners from device
				int tunerIndex = 0;
				for (ScaStructProperty struct : structs) {
					final TunerStatus tuner = FrontendFactory.eINSTANCE.createTunerStatus();
					tuner.setTunerContainer(container);
					tuner.setTunerStatusStruct(struct);
					tuner.setTunerID(String.valueOf(tunerIndex));
					tunerList.add(tuner);
					tunerIndex++;
					// Assign tuner type to model object - provides tree label
					setTunerType(tuner);
					
					ScaSimpleProperty allocSimple = struct.getSimple("FRONTEND::tuner_status::allocation_id_csv");
					if (allocSimple == null) {
						continue;
					}
					Object allocationValue = allocSimple.getValue();
					if (allocationValue == null) {
						continue;
					}
					String allocationIDs = allocationValue.toString();
					for (String allocationID: allocationIDs.split(",")) {
						if ("".equals(allocationID)) {
							continue;
						}
						if (tuner.getAllocationID() == null) {
							tuner.setAllocationID(allocationID);
							continue;
						}
						ListenerAllocation allocation = FrontendFactory.eINSTANCE.createListenerAllocation();
						allocation.setListenerID(allocationID);
						tuner.getListenerAllocations().add(allocation);
					}
				}

				return new Object[] { container };
			}
		}
		return new Object[0];
	}

	/** 
	 * Assigns tuner type to model object
	 * 
	 * @param tuner represents tuner model object
	 * 
	 */
	private void setTunerType(TunerStatus tuner) {//		
		ScaStructProperty tunerDevice = tuner.getTunerStatusStruct();
		String tunerType = tunerDevice.getSimple("FRONTEND::tuner_status::tuner_type").getValue().toString();
		tuner.setTunerType(tunerType);
	}

	/**
	 * TODO
	 * Update tuner status struct with new property value
	 * @param entry
	 */
	public static void setTunerProperties(final TunerPropertyWrapper entry) {
//		String allocationID = entry.getTuner().getAllocationID();
//		ScaDevice< ? > device = entry.getTuner().getTunerContainer().getModelDevice().getScaDevice();
//		
//		org.omg.CORBA.Object port = null;
//		
//		try {
//			port = device.getPort("DigitalTuner_in");
//		} catch (UnknownPort e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		DigitalTuner digitalTunerPort = DigitalTunerHelper.narrow(port);
//		
//		try {
//			if (entry.getID().equals("Gain")) {
//				digitalTunerPort.setTunerGain(allocationID, Float.parseFloat(String.valueOf(entry.getTuner().getGain())));
//			}
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FrontendException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (BadParameterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NotSupportedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
}

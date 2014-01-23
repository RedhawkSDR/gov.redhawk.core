package gov.redhawk.frontend.edit.utils;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.model.sca.ScaSimpleProperty;
import mil.jpeojtrs.sca.prf.PropertyValueType;

import org.eclipse.emf.common.notify.Notification;

/**
 * Enum of available tuner status properties as defined by specification.
 * Includes required and optional properties
 */
public enum TunerProperties {
	INSTANCE;

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

		//	public static TunerStatusAllocationProperties getProperty(EAttribute attribute) {
		//		for (TunerStatusAllocationProperties prop : TunerStatusAllocationProperties.values()) {
		//			if (prop.feiPropertyAttribute == attribute) {
		//				return prop;
		//			}
		//		}
		//		return null;
		//	}
		//
		//	public static TunerStatusAllocationProperties getProperty(ScaSimpleProperty scaProp) {
		//		for (TunerStatusAllocationProperties prop : TunerStatusAllocationProperties.values()) {
		//			if (prop.getId().equals(scaProp.getId())) {
		//				return prop;
		//			}
		//		}
		//		return null;
		//	}

		public static void setValue(TunerStatus tuner, ScaSimpleProperty simple) {
			if (simple.getId().equals(ALLOCATION_ID.getId())) {
				tuner.setAllocationID(simple.getValue().toString());
			} else if (simple.getId().equals(AGC.getId())) {
				tuner.setAgc(Boolean.parseBoolean(simple.getValue().toString()));
			} else if (simple.getId().equals(BANDWIDTH.getId())) {
				tuner.setBandwidth(Double.parseDouble(simple.getValue().toString()));
			} else if (simple.getId().equals(CENTER_FREQUENCY.getId())) {
				tuner.setCenterFrequency(Double.parseDouble(simple.getValue().toString()));
			} else if (simple.getId().equals(TunerAllocationProperties.DEVICE_CONTROL.getId())) {
				tuner.setDeviceControl(Boolean.parseBoolean(simple.getValue().toString()));
			} else if (simple.getId().equals(ENABLED.getId())) {
				tuner.setEnabled(Boolean.parseBoolean(simple.getValue().toString()));
			} else if (simple.getId().equals(GAIN.getId())) {
				tuner.setGain(Double.parseDouble(simple.getValue().toString()));
			} else if (simple.getId().equals(GROUP_ID.getId())) {
				tuner.setGroupID(simple.getValue().toString());
			} else if (simple.getId().equals(REFERENCE_SOURCE.getId())) {
				tuner.setReferenceSource(Long.parseLong(simple.getValue().toString()));
			} else if (simple.getId().equals(SAMPLE_RATE.getId())) {
				tuner.setSampleRate(Double.parseDouble(simple.getValue().toString()));
			} else if (simple.getId().equals(RF_FLOW_ID.getId())) {
				tuner.setRfFlowID(simple.getValue().toString());
			} else if (simple.getId().equals(TUNER_TYPE.getId())) {
				tuner.setTunerType(simple.getValue().toString());
			}
			//		TunerStatusAllocationProperties property = getProperty(source);
			//		target.eSet(property.feiPropertyAttribute, source.getValue());
		}

		public static void updateValue(TunerStatus source, Notification msg) {
			//		TunerStatusAllocationProperties property = getProperty((EAttribute) msg.getFeature());
			//		DigitalTuner tuner;
			//		switch (property) {
			//		case TUNER_NUMBER:
			//		}
		}
	}

	public enum TunerAllocationProperties {
		//	instance name			ID														PRF type
		TUNER_TYPE("FRONTEND::tuner_allocation::tuner_type", PropertyValueType.STRING),
		ALLOCATION_ID("FRONTEND::tuner_allocation::allocation_id", PropertyValueType.STRING),
		CENTER_FREQUENCY("FRONTEND::tuner_allocation::center_frequency", PropertyValueType.DOUBLE),
		BANDWIDTH("FRONTEND::tuner_allocation::bandwidth", PropertyValueType.DOUBLE),
		BANDWIDTH_TOLERANCE("FRONTEND::tuner_allocation::bandwidth_tolerance", PropertyValueType.DOUBLE),
		SAMPLE_RATE("FRONTEND::tuner_allocation::sample_rate", PropertyValueType.DOUBLE),
		SAMPLE_RATE_TOLERANCE("FRONTEND::tuner_allocation::sample_rate_tolerance", PropertyValueType.DOUBLE),
		DEVICE_CONTROL("FRONTEND::tuner_allocation::device_control", PropertyValueType.BOOLEAN),
		GROUP_ID("FRONTEND::tuner_allocation::group_id", PropertyValueType.STRING),
		RF_FLOW_ID("FRONTEND::tuner_allocation::rf_flow_id", PropertyValueType.STRING);

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
		EXISTING_ALLOCATION_ID("FRONTEND::listener_allocation::existing_allocation_id", PropertyValueType.STRING),
		LISTENER_ALLOCATION_ID("FRONTEND::listener_allocation::listener_allocation_id", PropertyValueType.STRING);

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
		ALLOCATION_ID_CSV("FRONTEND::tuner_status::allocation_id_csv", PropertyValueType.STRING),
		AVAILABLE_FREQUENCY("FRONTEND::tuner_status::available_frequency", PropertyValueType.DOUBLE),
		AVAILABLE_BANDWIDTH("FRONTEND::tuner_status::available_bandwidth", PropertyValueType.DOUBLE),
		AVAILABLE_SAMPLE_RATE("FRONTEND::tuner_status::available_sample_rate", PropertyValueType.DOUBLE);

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
}

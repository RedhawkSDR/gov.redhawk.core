package gov.redhawk.frontend.edit.utils;

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.model.sca.ScaSimpleProperty;
import mil.jpeojtrs.sca.prf.PropertyValueType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;

import FRONTEND.DigitalTuner;

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
	TUNER_NUMBER("FRONTEND::tuner_status::tuner_number", PropertyValueType.SHORT, "Tuner Number", FrontendPackage.Literals.TUNER_STATUS__TUNER_NUMBER);

	private String id;
	private PropertyValueType type;
	private String name;
	private EAttribute feiPropertyAttribute;

	TunerStatusAllocationProperties(String id, PropertyValueType prfType, String name, EAttribute attribute) {
		this.id = id;
		this.type = prfType;
		this.name = name;
		this.feiPropertyAttribute = attribute;
	}

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
	
	public void setFeiPropertyAttribute(ScaSimpleProperty simple) {
		System.out.println("ID: " + simple.getId());
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
//
//	public static void updateValue(TunerStatus target, ScaSimpleProperty source) {
//		TunerStatusAllocationProperties property = getProperty(source);
//		target.eSet(property.feiPropertyAttribute, source.getValue());
//	}
//
//	public static void setValue(TunerStatus source, Notification msg) {
//		TunerStatusAllocationProperties property = getProperty((EAttribute) msg.getFeature());
//		DigitalTuner tuner;
//		switch (property) {
//		case TUNER_NUMBER:
//		}
//	}
}

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
package gov.redhawk.frontend.edit.utils;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.model.sca.ScaSimpleProperty;

public class TunerPropertyWrapper {

	private TunerStatus tuner;
	private String id;
	private Object value;

	public TunerPropertyWrapper(TunerStatus tuner, ScaSimpleProperty simple) {
		this.tuner = tuner;
		setID(simple.getId());
		setValue((tuner));
	}

	/**
	 * retrieves a human readable ID to use for label provider
	 * @return
	 */
	public String getID() {
		return id;
	}

	/**
	 * Creates a human readable ID
	 * @param id 
	 */
	private void setID(String id) {
		for (TunerStatusAllocationProperties allocProp : TunerStatusAllocationProperties.values()) {
			if (allocProp.getId().equals(id)) {
				this.id = allocProp.getName();
				return;
			}
		}
		// catch edge case
		this.id = id;
	}

	public void setValue(final TunerStatus tuner) {
		if (id.equals("Tuner Type"))
			value = tuner.getTunerType();
		else if (id.equals("Allocation ID"))
			value = tuner.getAllocationID();
		else if (id.equals("Center Frequency"))
			value = tuner.getCenterFrequency();
		else if (id.equals("Bandwidth"))
			value = tuner.getBandwidth();
		else if (id.equals("Sample Rate"))
			value = tuner.getSampleRate();
		else if (id.equals("Group ID"))
			value = tuner.getGroupID();
		else if (id.equals("RF Flow ID"))
			value = tuner.getRfFlowID();
		else if (id.equals("Enabled"))
			value = tuner.isEnabled();
		else if (id.equals("Bandwidth Tolerance"))
			value = tuner.getBandwidthTolerance();
		else if (id.equals("Sample Rate Tolerance"))
			value = tuner.getSampleRateTolerance();
		else if (id.equals("Complex"))
			value = tuner.isComplex();
		else if (id.equals("Gain"))
			value = tuner.getGain();
		else if (id.equals("AGC"))
			value = tuner.isAgc();
		else if (id.equals("Valid"))
			value = tuner.isValid();
		else if (id.equals("Available Frequency"))
			value = tuner.getAvailableFrequency();
		else if (id.equals("Available Bandwidth"))
			value = tuner.getAvailableBandwidth();
		else if (id.equals("Available Gain"))
			value = tuner.getAvailableGain();
		else if (id.equals("Available Sample Rate"))
			value = tuner.getAvailableSampleRate();
		else if (id.equals("Reference Source"))
			value = tuner.getReferenceSource();
		else if (id.equals("Output Format"))
			value = tuner.getOutputFormat();
		else if (id.equals("Output Multicast"))
			value = tuner.getOutputMulticast();
		else if (id.equals("Output VLan"))
			value = tuner.getOutputVlan();
		else if (id.equals("Output Port"))
			value = tuner.getOutputPort();
		else if (id.equals("Decimation"))
			value = tuner.getDecimation();
		else if (id.equals("Tuner Number"))
			value = tuner.getTuner_number();
	}

	public Object getValue() {
		return value;
	}

	public void updateValue(final Object value) {
		if (value == null) {
			return;
		}

		if (id.equals("Tuner Type"))
			tuner.setTunerType(value.toString());
		else if (id.equals("Allocation ID"))
			tuner.setAllocationID(String.valueOf(value));
		else if (id.equals("Center Frequency"))
			tuner.setCenterFrequency(Double.parseDouble(value.toString()));
		else if (id.equals("Bandwidth"))
			tuner.setBandwidth(Double.parseDouble(value.toString()));
		else if (id.equals("Sample Rate"))
			tuner.setSampleRate(Double.parseDouble(value.toString()));
		else if (id.equals("Group ID"))
			tuner.setGroupID(String.valueOf(value));
		else if (id.equals("RF Flow ID"))
			tuner.setRfFlowID(String.valueOf(value));
		else if (id.equals("Enabled"))
			tuner.setEnabled(Boolean.parseBoolean(value.toString()));
		else if (id.equals("Bandwidth Tolerance"))
			tuner.setBandwidthTolerance(Double.parseDouble(value.toString()));
		else if (id.equals("Sample Rate Tolerance"))
			tuner.setSampleRateTolerance(Double.parseDouble(value.toString()));
		else if (id.equals("Complex"))
			tuner.setComplex(Boolean.parseBoolean(value.toString()));
		else if (id.equals("Gain"))
			tuner.setGain(Double.parseDouble(value.toString()));
		else if (id.equals("AGC"))
			tuner.setAgc(Boolean.parseBoolean(value.toString()));
		else if (id.equals("Valid"))
			tuner.setValid(Boolean.parseBoolean(value.toString()));
		else if (id.equals("Available Frequency"))
			tuner.setAvailableFrequency(value.toString());
		else if (id.equals("Available Bandwidth"))
			tuner.setAvailableBandwidth(value.toString());
		else if (id.equals("Available Gain"))
			tuner.setAvailableGain(value.toString());
		else if (id.equals("Available Sample Rate"))
			tuner.setAvailableSampleRate(value.toString());
		else if (id.equals("Reference Source"))
			tuner.setReferenceSource(Long.parseLong(value.toString()));
		else if (id.equals("Output Format"))
			tuner.setOutputFormat(value.toString());
		else if (id.equals("Output Multicast"))
			tuner.setOutputMulticast(value.toString());
		else if (id.equals("Output VLan"))
			tuner.setOutputVlan(Long.parseLong(value.toString()));
		else if (id.equals("Output Port"))
			tuner.setOutputPort(Long.parseLong(value.toString()));
		else if (id.equals("Decimation"))
			tuner.setDecimation(Long.parseLong(value.toString()));
		else if (id.equals("Tuner Number"))
			tuner.setTuner_number(Short.parseShort(value.toString()));
	}

	public TunerStatus getTuner() {
		return tuner;
	}
}

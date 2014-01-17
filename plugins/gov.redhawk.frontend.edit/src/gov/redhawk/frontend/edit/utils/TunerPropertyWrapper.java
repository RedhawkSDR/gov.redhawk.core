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
import gov.redhawk.frontend.edit.utils.TunerUtils.TunerStatusAllocationProperties;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;

public class TunerPropertyWrapper {

	private TunerStatus tuner;
	private String id;
	private Object value;

	public TunerPropertyWrapper(TunerStatus tuner, ScaSimpleProperty simple) {
		this.tuner = tuner;
		setID(simple.getId());
		setValue(simple.getValue());
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
		for (TunerStatusAllocationProperties allocProp : TunerUtils.TunerStatusAllocationProperties.values()) {
			if (allocProp.getId().equals(id)) {
				this.id = allocProp.getName();
				return;
			}
		}

		// catch edge case
		this.id = id;
	}

	public Object getValue() {
		return value;
	}

	private void setValue(final Object value) {
		if (value == null) {
			this.value = "";
		} else {
			this.value = value;
		}
	}

	public void updateValue(final Object value) {
		this.value = value;
		ScaModelCommand.execute(tuner, new ScaModelCommand() {
			@Override
			public void execute() {
				if (id.equals("Allocation ID"))
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
					tuner.setGain(Double.parseDouble(value.toString()));
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
		});
	}

	//	ScaModelCommand.execute(tuner, new ScaModelCommand() {
	//
	//		@Override
	//		public void execute() {
	//			for (TunerStatusAllocationProperties allocProp : TunerUtils.TunerStatusAllocationProperties.values()) {
	//				switch(allocProp) {
	//				case TUNER_TYPE:
	//					if (allocProp.getId().equals(id)) {
	//						tuner.setTunerType(String.valueOf(value));
	//					}
	//					break;
	//				
	//				default: 
	//					break;
	//				}
	//			}
	//			if (TunerUtils.TunerStatusAllocationProperties.ALLOCATION_ID.getId().equals(id)) {
	//				
	//			}
	//		}
	//		
	//	});

	public TunerStatus getTuner() {
		return tuner;
	}
}

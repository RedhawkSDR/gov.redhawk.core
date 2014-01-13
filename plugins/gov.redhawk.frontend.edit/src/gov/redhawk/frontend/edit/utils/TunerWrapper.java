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

import gov.redhawk.frontend.FrontendFactory;
import gov.redhawk.frontend.Tuner;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.util.ArrayList;
import java.util.List;

public class TunerWrapper {

	private Tuner tuner;
	private List<TunerProperty> properties = new ArrayList<TunerProperty>();

	public TunerWrapper(Tuner tuner) {
		this.setTuner(tuner);
		setProperties();
	}

	public class TunerProperty {
		private String id;
		private Object value;

		TunerProperty(String id, Object value) {
			this.setId(id);
			this.setValue(value);
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(final Object value) {
			this.value = value;
			ScaModelCommand.execute(tuner, new ScaModelCommand() {

				@Override
				public void execute() {
					if(id.equals("Allocation ID")) 
						tuner.setAllocationID(value.toString());
					if(id.equals("Device Control"))
						tuner.setDeviceControl(Boolean.parseBoolean(value.toString()));
					if(id.equals("Group ID"))
						tuner.setGroupID(value.toString());
					if(id.equals("RF Flow ID"))
						tuner.setRfFlowID(value.toString());
					if(id.equals("Gain"))
						tuner.setGain(Double.parseDouble(value.toString()));
					if(id.equals("Bandwidth"))
						tuner.getTunerStatus().setBandwidth(Double.parseDouble(value.toString()));
					if(id.equals("Center Frequency"))
						tuner.getTunerStatus().setCenterFrequency(Double.parseDouble(value.toString()));
					if(id.equals("Sample Rate"))
						tuner.getTunerStatus().setSampleRate(Double.parseDouble(value.toString()));
					if(id.equals("Enabled"))
						tuner.getTunerStatus().setEnabled(Boolean.parseBoolean(value.toString()));
				}
			});
		}

		public Object[] getTunerStatusElements() {
			List<TunerProperty> tunerStatusProperties = new ArrayList<TunerProperty>();
			TunerStatus tunerStatus = (TunerStatus) value;
			if (tunerStatus == null) {
				tunerStatus = FrontendFactory.eINSTANCE.createTunerStatus();
			}
			tunerStatusProperties.add(new TunerProperty("Bandwidth", tunerStatus.getBandwidth()));
			tunerStatusProperties.add(new TunerProperty("Center Frequency", tunerStatus.getCenterFrequency()));
			tunerStatusProperties.add(new TunerProperty("Sample Rate", tunerStatus.getSampleRate()));
			tunerStatusProperties.add(new TunerProperty("Enabled", tunerStatus.isEnabled()));
			return tunerStatusProperties.toArray();
		}
	}

	private void setProperties() {
		properties.add(new TunerProperty("Allocation ID", tuner.getAllocationID()));
		properties.add(new TunerProperty("Tuner Type", tuner.getTunerType()));
		properties.add(new TunerProperty("Device Control", tuner.isDeviceControl()));
		properties.add(new TunerProperty("Group ID", tuner.getGroupID()));
		properties.add(new TunerProperty("RF Flow ID", tuner.getRfFlowID()));
		properties.add(new TunerProperty("Gain", tuner.getGain()));
		properties.add(new TunerProperty("Tuner Status", tuner.getTunerStatus()));
	}

	/**
	 * 
	 * @return array of TunerProperty objects
	 */
	public Object[] getProperties() {
		return properties.toArray();
	}

	public Tuner getTuner() {
		return tuner;
	}

	public void setTuner(Tuner tuner) {
		this.tuner = tuner;
	}
}

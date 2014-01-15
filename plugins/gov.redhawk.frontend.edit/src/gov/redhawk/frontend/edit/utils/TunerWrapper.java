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
		private TunerWrapper wrapper; //reference to container object for backwards navigation

		TunerProperty(String id, Object value, TunerWrapper wrapper) {
			this.setId(id);
			this.setValue(value);
			this.setWrapper(wrapper);
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

		private void setWrapper(TunerWrapper wrapper) {
			this.wrapper = wrapper;
		}

		public TunerWrapper getWrapper() {
			return wrapper;
		}

		public void setValue(final Object newValue) {
			if (newValue == null) {
				this.value = "";
			} else {
				this.value = newValue;
			}
			ScaModelCommand.execute(tuner, new ScaModelCommand() {

				@Override
				public void execute() {
					if (id.equals("Allocation ID"))
						tuner.setAllocationID(value.toString());
					if (id.equals("Device Control"))
						tuner.setDeviceControl(Boolean.parseBoolean(value.toString()));
					if (id.equals("Group ID"))
						tuner.setGroupID(value.toString());
					if (id.equals("RF Flow ID"))
						tuner.setRfFlowID(value.toString());
					if (id.equals("Gain"))
						tuner.setGain(Double.parseDouble(value.toString()));
					if (id.equals("Bandwidth"))
						tuner.getTunerStatus().setBandwidth(Double.parseDouble(value.toString()));
					if (id.equals("Center Frequency"))
						tuner.getTunerStatus().setCenterFrequency(Double.parseDouble(value.toString()));
					if (id.equals("Sample Rate"))
						tuner.getTunerStatus().setSampleRate(Double.parseDouble(value.toString()));
					if (id.equals("Enabled"))
						tuner.getTunerStatus().setEnabled(Boolean.parseBoolean(value.toString()));
				}
			});
		}

		public Object[] getTunerStatusElements() {
			List<TunerProperty> tunerStatusProperties = new ArrayList<TunerProperty>();
			TunerStatus tunerStatus;
			if (value instanceof TunerStatus) {
				tunerStatus = (TunerStatus) value;
			} else {
				tunerStatus = FrontendFactory.eINSTANCE.createTunerStatus();
			}

			tunerStatusProperties.add(new TunerProperty("Bandwidth", tunerStatus.getBandwidth(), getWrapper()));
			tunerStatusProperties.add(new TunerProperty("Center Frequency", tunerStatus.getCenterFrequency(), getWrapper()));
			tunerStatusProperties.add(new TunerProperty("Sample Rate", tunerStatus.getSampleRate(), getWrapper()));
			tunerStatusProperties.add(new TunerProperty("Enabled", tunerStatus.isEnabled(), getWrapper()));
			return tunerStatusProperties.toArray();
		}
	}

	private void setProperties() {
		properties.add(new TunerProperty("Allocation ID", tuner.getAllocationID(), this));
		properties.add(new TunerProperty("Tuner Type", tuner.getTunerType(), this));
		properties.add(new TunerProperty("Device Control", tuner.isDeviceControl(), this));
		properties.add(new TunerProperty("Group ID", tuner.getGroupID(), this));
		properties.add(new TunerProperty("RF Flow ID", tuner.getRfFlowID(), this));
		properties.add(new TunerProperty("Gain", tuner.getGain(), this));
		properties.add(new TunerProperty("Tuner Status", tuner.getTunerStatus(), this));
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

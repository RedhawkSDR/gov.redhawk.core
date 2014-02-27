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
package gov.redhawk.frontend.util;

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.util.PluginUtil;
import gov.redhawk.sca.util.SubMonitor;

import java.util.concurrent.Callable;

import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.util.CorbaUtils;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;

import CF.PortSupplierPackage.UnknownPort;
import FRONTEND.BadParameterException;
import FRONTEND.DigitalTuner;
import FRONTEND.DigitalTunerHelper;
import FRONTEND.FrontendException;
import FRONTEND.NotSupportedException;

/**
 * Enum of available tuner status properties as defined by specification.
 * Includes required and optional properties
 */
public enum TunerProperties {
	INSTANCE;

	public static enum TunerStatusAllocationProperties {

		// required properties
		// instance name ID PRF type Human Readable Name
		TUNER_TYPE("FRONTEND::tuner_status::tuner_type", PropertyValueType.STRING, "Tuner Type", FrontendPackage.Literals.TUNER_STATUS__TUNER_TYPE),
		ALLOCATION_ID(
				"FRONTEND::tuner_status::allocation_id_csv",
					PropertyValueType.STRING,
					"Allocation ID",
					FrontendPackage.Literals.TUNER_STATUS__ALLOCATION_ID),
		CENTER_FREQUENCY(
				"FRONTEND::tuner_status::center_frequency",
					PropertyValueType.DOUBLE,
					"Center Frequency",
					FrontendPackage.Literals.TUNER_STATUS__CENTER_FREQUENCY),
		BANDWIDTH("FRONTEND::tuner_status::bandwidth", PropertyValueType.DOUBLE, "Bandwidth", FrontendPackage.Literals.TUNER_STATUS__BANDWIDTH),
		SAMPLE_RATE("FRONTEND::tuner_status::sample_rate", PropertyValueType.DOUBLE, "Sample Rate", FrontendPackage.Literals.TUNER_STATUS__SAMPLE_RATE),
		GROUP_ID("FRONTEND::tuner_status::group_id", PropertyValueType.STRING, "Group ID", FrontendPackage.Literals.TUNER_STATUS__GROUP_ID),
		RF_FLOW_ID("FRONTEND::tuner_status::rf_flow_id", PropertyValueType.STRING, "RF Flow ID", FrontendPackage.Literals.TUNER_STATUS__RF_FLOW_ID),
		ENABLED("FRONTEND::tuner_status::enabled", PropertyValueType.BOOLEAN, "Enabled", FrontendPackage.Literals.TUNER_STATUS__ENABLED),

		// optional properties
		// instance name ID PRF type
		BANDWIDTH_TOLERANCE("FRONTEND::tuner_status::bandwidth_tolerance", PropertyValueType.DOUBLE, "Bandwidth Tolerance"),
		SAMPLE_RATE_TOLERANCE("FRONTEND::tuner_status::sample_rate_tolerance", PropertyValueType.DOUBLE, "Sample Rate Tolerance"),
		COMPLEX("FRONTEND::tuner_status::complex", PropertyValueType.BOOLEAN, "Complex"),
		GAIN("FRONTEND::tuner_status::gain", PropertyValueType.DOUBLE, "Gain", FrontendPackage.Literals.TUNER_STATUS__GAIN),
		AGC("FRONTEND::tuner_status::agc", PropertyValueType.BOOLEAN, "AGC", FrontendPackage.Literals.TUNER_STATUS__AGC),
		VALID("FRONTEND::tuner_status::valid", PropertyValueType.BOOLEAN, "Valid"),
		AVAILABLE_FREQUENCY("FRONTEND::tuner_status::available_frequency", PropertyValueType.STRING, "Available Frequency"),
		AVAILABLE_BANDWIDTH("FRONTEND::tuner_status::available_bandwidth", PropertyValueType.STRING, "Available Bandwidth"),
		AVAILABLE_GAIN("FRONTEND::tuner_status::available_gain", PropertyValueType.STRING, "Available Gain"),
		AVAILABLE_SAMPLE_RATE("FRONTEND::tuner_status::available_sample_rate", PropertyValueType.STRING, "Available Sample Rate"),
		REFERENCE_SOURCE(
				"FRONTEND::tuner_status::reference_source",
					PropertyValueType.LONG,
					"Reference Source",
					FrontendPackage.Literals.TUNER_STATUS__REFERENCE_SOURCE),
		OUTPUT_FORMAT("FRONTEND::tuner_status::output_format", PropertyValueType.STRING, "Output Format"),
		OUTPUT_MULTICAST("FRONTEND::tuner_status::output_multicast", PropertyValueType.STRING, "Output Multicast"),
		OUTPUT_VLAN("FRONTEND::tuner_status::output_vlan", PropertyValueType.LONG, "Output VLan"),
		OUTPUT_PORT("FRONTEND::tuner_status::output_port", PropertyValueType.LONG, "Output Port"),
		DECIMATION("FRONTEND::tuner_status::decimation", PropertyValueType.LONG, "Decimation"),
		TUNER_NUMBER("FRONTEND::tuner_status::tuner_number", PropertyValueType.SHORT, "Tuner Number");

		private String id;
		private PropertyValueType type;
		private String name;
		private EAttribute feiAttr;

		TunerStatusAllocationProperties(String id, PropertyValueType prfType, String name, EAttribute feiAttr) {
			this.id = id;
			this.type = prfType;
			this.name = name;
			this.feiAttr = feiAttr;
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

		public EAttribute getFeiAttribute() {
			return feiAttr;
		}

		public static TunerStatusAllocationProperties fromPropID(String propID) {
			for (TunerStatusAllocationProperties prop : TunerStatusAllocationProperties.values()) {
				if (prop.id.equals(propID)) {
					return prop;
				}
			}
			return null;
		}

		public static TunerStatusAllocationProperties fromAttribute(EAttribute attr) {
			for (TunerStatusAllocationProperties prop : TunerStatusAllocationProperties.values()) {
				if (prop.feiAttr == attr) {
					return prop;
				}
			}
			return null;
		}

		public static void setValue(final TunerStatus tuner, final ScaSimpleProperty simple) {
			TunerStatusAllocationProperties prop = TunerStatusAllocationProperties.fromPropID(simple.getId());
			setValue(tuner, prop, simple.getValue());
		}

		/**
		 * Updates model convenience properties
		 * @param tuner
		 * @param simple
		 */
		public static void setValue(final TunerStatus tuner, final TunerStatusAllocationProperties prop, final Object newValue) {
			if (prop == null || prop.getFeiAttribute() == null) {
				return;
			}
			ScaModelCommand.execute(tuner, new ScaModelCommand() {

				@Override
				public void execute() {
					boolean equals = PluginUtil.equals(tuner.eGet(prop.getFeiAttribute()), newValue);
					if (!equals && newValue != null) {
						tuner.eSet(prop.getFeiAttribute(), newValue);
					}
				}

			});
		}

		/**
		 * Updates the model object to a new value
		 * @param wrapper TunerPropertyWrapper
		 * @param value New value
		 */
		public static void updateValue(final TunerPropertyWrapper wrapper, final Object newValue) {
			final TunerStatusAllocationProperties value = TunerStatusAllocationProperties.fromPropID(wrapper.getId());
			if (value == null) {
				// TODO PANIC!!
				return;
			}
			TunerStatusAllocationProperties.setValue(wrapper.getTuner(), value, newValue);
			ScaModelCommand.execute(wrapper.getSimple(), new ScaModelCommand() {

				@Override
				public void execute() {
					try {
						wrapper.getSimple().setIgnoreRemoteSet(true);
						wrapper.getSimple().setValue(newValue);
					} finally {
						wrapper.getSimple().setIgnoreRemoteSet(false);
					}
				}
			});

		}

		public static void updateDeviceValue(final TunerStatus tuner, final Notification notification) {
			// Don't reflect to device on initialization or if no change is being made
			if (notification.isTouch() || notification.getOldValue() == null || notification.getOldValue().equals(notification.getNewValue())) {
				return;
			}

			// parse out the control id
			String allocationID = tuner.getAllocationID();
			if (allocationID == null || allocationID.isEmpty()) {
				return;
			}

			int endControlIndex = allocationID.indexOf(',');
			if (endControlIndex > 0) {
				allocationID = allocationID.substring(0, endControlIndex);
			}
			final String finalAllocationId = allocationID;

			final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
			if (device == null) {
				return;
			}

			if (notification.getFeature() instanceof EAttribute) {
				final EAttribute feiAttr = (EAttribute) notification.getFeature();

				// Translate into Tuner Status Prop, if translation fails it isn't writable
				final TunerStatusAllocationProperties prop = TunerStatusAllocationProperties.fromAttribute(feiAttr);
				if (prop == null) {
					return;
				}

				// Get the related Simple and check the current value of the simple to see if we should update
				final ScaSimpleProperty simple = tuner.getTunerStatusStruct().getSimple(prop.id);
				if (simple == null || PluginUtil.equals(simple.getValue(), notification.getNewValue())) {
					return;
				}
				Job job = new Job("Update device property: " + prop.getName()) {

					@Override
					protected IStatus run(IProgressMonitor parentMonitor) {
						final SubMonitor subMonitor = SubMonitor.convert(parentMonitor, "Setting value of " + prop.getName() + " to " + notification.getNewValue(),
							IProgressMonitor.UNKNOWN);
						IStatus retVal;
						try {
							retVal = CorbaUtils.invoke(new Callable<IStatus>() {

								@Override
								public IStatus call() throws Exception {
									return doRun(device, feiAttr, finalAllocationId, tuner);
								}

							}, subMonitor.newChild(1));
						} catch (CoreException e) {
							return e.getStatus();
						} catch (InterruptedException e) {
							return Status.CANCEL_STATUS;
						}
						device.fetchProperties(subMonitor.newChild(1));
						return retVal;
					}

				};
				job.setUser(true);
				job.setSystem(false);
				job.schedule();

			}
		}

		private static IStatus doRun(ScaDevice< ? > device, final EAttribute feiAttr, String finalAllocationId, final TunerStatus tuner) {
			try {
				org.omg.CORBA.Object port = null;

				try {
					port = device.getPort("DigitalTuner_in");
				} catch (UnknownPort e) {
					return new Status(IStatus.ERROR, "gov.redhawk.frontend.edit", "Unknown Port Exception", e);
				}
				DigitalTuner digitalTunerPort = DigitalTunerHelper.narrow(port);
				if (feiAttr.equals(AGC.getFeiAttribute())) {
					digitalTunerPort.setTunerAgcEnable(finalAllocationId, tuner.isAgc());
				} else if (feiAttr.equals(BANDWIDTH.getFeiAttribute())) {
					digitalTunerPort.setTunerBandwidth(finalAllocationId, tuner.getBandwidth());
				} else if (feiAttr.equals(CENTER_FREQUENCY.getFeiAttribute())) {
					digitalTunerPort.setTunerCenterFrequency(finalAllocationId, tuner.getCenterFrequency());
				} else if (feiAttr.equals(ENABLED.getFeiAttribute())) {
					digitalTunerPort.setTunerEnable(finalAllocationId, tuner.isEnabled());
				} else if (feiAttr.equals(GAIN.getFeiAttribute())) {
					// Gain is double in model and documentation, but float in API
					float gain = Double.valueOf(tuner.getGain()).floatValue();
					digitalTunerPort.setTunerGain(finalAllocationId, gain);
				} else if (feiAttr.equals(SAMPLE_RATE.getFeiAttribute())) {
					digitalTunerPort.setTunerOutputSampleRate(finalAllocationId, tuner.getSampleRate());
				} else if (feiAttr.equals(REFERENCE_SOURCE.getFeiAttribute())) {
					digitalTunerPort.setTunerReferenceSource(finalAllocationId, tuner.getReferenceSource());
				}
			} catch (NumberFormatException e) {
				return new Status(IStatus.ERROR, "gov.redhawk.frontend.edit", "Number Format Exception in property assignment", e);
			} catch (FrontendException e) {
				return new Status(IStatus.ERROR, "gov.redhawk.frontend.edit", "Frontend Exception in property assignment: " + e.msg, e);
			} catch (BadParameterException e) {
				return new Status(IStatus.ERROR, "gov.redhawk.frontend.edit", "Bad Parameter Exception in property assignment: " + e.msg, e);
			} catch (NotSupportedException e) {
				return new Status(IStatus.ERROR, "gov.redhawk.frontend.edit", "Not Supported Exception in property assignment: " + e.msg, e);
			}
			return Status.OK_STATUS;
		}
	}

	public static enum TunerAllocationProperties {
		// instance name ID PRF type
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

	public static enum ListenerAllocationProperties {
		// instance name ID PRF type
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

	public static enum StatusProperties {
		// instance name ID PRF type
		FRONTEND_TUNER_STATUS("FRONTEND::tuner_status", PropertyValueType.OBJREF),
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

		public static StatusProperties getValueFor(ScaSimpleProperty simple) {
			for (StatusProperties prop : StatusProperties.values()) {
				if (prop.getId().equals(simple.getId())) {
					return prop;
				}
			}
			return null;
		}
	}
}

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;

import FRONTEND.AnalogTuner;
import FRONTEND.AnalogTunerHelper;
import FRONTEND.BadParameterException;
import FRONTEND.DigitalTuner;
import FRONTEND.DigitalTunerHelper;
import FRONTEND.FrontendException;
import FRONTEND.NotSupportedException;
import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.sca.util.PluginUtil;
import gov.redhawk.sca.util.SubMonitor;
import mil.jpeojtrs.sca.prf.ConfigurationKind;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructPropertyConfigurationType;
import mil.jpeojtrs.sca.util.CorbaUtils;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

/**
 * Enum of available tuner status properties as defined by specification.
 * Includes required and optional properties
 */
public enum TunerProperties {
	INSTANCE;

	public static enum TunerStatusAllocationProperties {

		// required properties
		// instance name ID PRF type Human Readable Name
		TUNER_TYPE("FRONTEND::tuner_status::tuner_type", PropertyValueType.STRING, "Tuner Type", FrontendPackage.Literals.TUNER_STATUS__TUNER_TYPE, false),
		ALLOCATION_ID(
				"FRONTEND::tuner_status::allocation_id_csv",
					PropertyValueType.STRING,
					"Allocation ID",
					FrontendPackage.Literals.TUNER_STATUS__ALLOCATION_ID,
					false),
		CENTER_FREQUENCY(
				"FRONTEND::tuner_status::center_frequency",
					PropertyValueType.DOUBLE,
					"Center Frequency",
					FrontendPackage.Literals.TUNER_STATUS__CENTER_FREQUENCY,
					true),
		BANDWIDTH("FRONTEND::tuner_status::bandwidth", PropertyValueType.DOUBLE, "Bandwidth", FrontendPackage.Literals.TUNER_STATUS__BANDWIDTH, true),
		SAMPLE_RATE("FRONTEND::tuner_status::sample_rate", PropertyValueType.DOUBLE, "Sample Rate", FrontendPackage.Literals.TUNER_STATUS__SAMPLE_RATE, true),
		GROUP_ID("FRONTEND::tuner_status::group_id", PropertyValueType.STRING, "Group ID", FrontendPackage.Literals.TUNER_STATUS__GROUP_ID, false),
		RF_FLOW_ID("FRONTEND::tuner_status::rf_flow_id", PropertyValueType.STRING, "RF Flow ID", FrontendPackage.Literals.TUNER_STATUS__RF_FLOW_ID, false),
		ENABLED("FRONTEND::tuner_status::enabled", PropertyValueType.BOOLEAN, "Enabled", FrontendPackage.Literals.TUNER_STATUS__ENABLED, true),

		// optional properties
		// instance name ID PRF type
		BANDWIDTH_TOLERANCE("FRONTEND::tuner_status::bandwidth_tolerance", PropertyValueType.DOUBLE, "Bandwidth Tolerance"),
		SAMPLE_RATE_TOLERANCE("FRONTEND::tuner_status::sample_rate_tolerance", PropertyValueType.DOUBLE, "Sample Rate Tolerance"),
		COMPLEX("FRONTEND::tuner_status::complex", PropertyValueType.BOOLEAN, "Complex"),
		GAIN("FRONTEND::tuner_status::gain", PropertyValueType.DOUBLE, "Gain", FrontendPackage.Literals.TUNER_STATUS__GAIN, true),
		AGC("FRONTEND::tuner_status::agc", PropertyValueType.BOOLEAN, "AGC", FrontendPackage.Literals.TUNER_STATUS__AGC, true),
		VALID("FRONTEND::tuner_status::valid", PropertyValueType.BOOLEAN, "Valid"),
		AVAILABLE_FREQUENCY("FRONTEND::tuner_status::available_frequency", PropertyValueType.STRING, "Available Frequency"),
		AVAILABLE_BANDWIDTH("FRONTEND::tuner_status::available_bandwidth", PropertyValueType.STRING, "Available Bandwidth"),
		AVAILABLE_GAIN("FRONTEND::tuner_status::available_gain", PropertyValueType.STRING, "Available Gain"),
		AVAILABLE_SAMPLE_RATE("FRONTEND::tuner_status::available_sample_rate", PropertyValueType.STRING, "Available Sample Rate"),
		REFERENCE_SOURCE(
				"FRONTEND::tuner_status::reference_source",
					PropertyValueType.LONG,
					"Reference Source",
					FrontendPackage.Literals.TUNER_STATUS__REFERENCE_SOURCE,
					true),
		OUTPUT_FORMAT("FRONTEND::tuner_status::output_format", PropertyValueType.STRING, "Output Format"),
		OUTPUT_MULTICAST("FRONTEND::tuner_status::output_multicast", PropertyValueType.STRING, "Output Multicast"),
		OUTPUT_VLAN("FRONTEND::tuner_status::output_vlan", PropertyValueType.LONG, "Output VLan"),
		OUTPUT_PORT("FRONTEND::tuner_status::output_port", PropertyValueType.LONG, "Output Port"),
		DECIMATION("FRONTEND::tuner_status::decimation", PropertyValueType.LONG, "Decimation"),
		TUNER_NUMBER("FRONTEND::tuner_status::tuner_number", PropertyValueType.SHORT, "Tuner Number");

		private final String id;
		private final PropertyValueType type;
		private final String name;
		private final EAttribute feiAttr;
		private final boolean editable;

		TunerStatusAllocationProperties(String id, PropertyValueType prfType, String name, EAttribute feiAttr, boolean editable) {
			this.id = id;
			this.type = prfType;
			this.name = name;
			this.feiAttr = feiAttr;
			this.editable = editable;
		}

		TunerStatusAllocationProperties(String id, PropertyValueType prfType, String name) {
			this(id, prfType, name, null, false);
		}

		public boolean isEditable() {
			return editable;
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

		/**
		 * Updates model convenience properties
		 * @param tuner
		 * @param simple
		 */
		public static void updateTunerStatusValue(final TunerStatus tuner, final TunerStatusAllocationProperties prop, final Object newValue) {
			if (prop == null || prop.getFeiAttribute() == null) {
				return;
			}
			boolean equals = PluginUtil.equals(tuner.eGet(prop.getFeiAttribute()), newValue);
			if (!equals && newValue != null) {
				tuner.eSet(prop.getFeiAttribute(), newValue);
			}
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
						final SubMonitor subMonitor = SubMonitor.convert(parentMonitor,
							"Setting value of " + prop.getName() + " to " + notification.getNewValue(), IProgressMonitor.UNKNOWN);
						IStatus retVal;
						try {
							retVal = CorbaUtils.invoke(new Callable<IStatus>() {

								@Override
								public IStatus call() throws CoreException {
									TunerStatusAllocationProperties.doRun(device, feiAttr, finalAllocationId, notification.getNewValue());
									return Status.OK_STATUS;
								}

							}, subMonitor.newChild(1));
						} catch (CoreException e) {
							return new Status(e.getStatus().getSeverity(), "gov.redhawk.frontend", "Failed to update device property: " + prop.getName(), e);
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

		private static void doRun(ScaDevice< ? > device, final EAttribute feiAttr, String controlID, Object newValue) throws CoreException {

			EList<ScaPort< ? , ? >> ports = device.getPorts();
			for (ScaPort< ? , ? > p : ports) {
				if (p instanceof ScaProvidesPort) {
					ScaProvidesPort pp = (ScaProvidesPort) p;
					if (pp._is_a(DigitalTunerHelper.id())) {
						doRunDigital(DigitalTunerHelper.narrow(pp.getObj()), feiAttr, controlID, newValue);
						return;
					} else if (pp._is_a(AnalogTunerHelper.id())) {
						doRunAnalog(AnalogTunerHelper.narrow(pp.getObj()), feiAttr, controlID, newValue);
						return;
					}
				}
			}

			throw new CoreException(new Status(IStatus.ERROR, "gov.redhawk.frontend", "Failed to find tuner control port", null));
		}

		private static void doRunDigital(DigitalTuner digitalTunerPort, final EAttribute feiAttr, String controlID, Object newValue) throws CoreException {
			try {
				if (feiAttr.equals(SAMPLE_RATE.getFeiAttribute())) {
					digitalTunerPort.setTunerOutputSampleRate(controlID, (Double) newValue);
				} else {
					doRunAnalog(digitalTunerPort, feiAttr, controlID, newValue);
				}
			} catch (NumberFormatException e) {
				throw new CoreException(new Status(IStatus.ERROR, "gov.redhawk.frontend", "Number Format Exception in property assignment", e));
			} catch (FrontendException e) {
				throw new CoreException(new Status(IStatus.ERROR, "gov.redhawk.frontend", "Frontend Exception in property assignment: " + e.msg, e));
			} catch (BadParameterException e) {
				throw new CoreException(new Status(IStatus.ERROR, "gov.redhawk.frontend", "Bad Parameter Exception in property assignment: " + e.msg, e));
			} catch (NotSupportedException e) {
				throw new CoreException(new Status(IStatus.ERROR, "gov.redhawk.frontend", "Not Supported Exception in property assignment: " + e.msg, e));
			}
		}

		private static void doRunAnalog(AnalogTuner analogTunerPort, final EAttribute feiAttr, String controlID, Object newValue) throws CoreException {
			try {
				if (feiAttr.equals(AGC.getFeiAttribute())) {
					analogTunerPort.setTunerAgcEnable(controlID, (Boolean) newValue);
				} else if (feiAttr.equals(BANDWIDTH.getFeiAttribute())) {
					analogTunerPort.setTunerBandwidth(controlID, (Double) newValue);
				} else if (feiAttr.equals(CENTER_FREQUENCY.getFeiAttribute())) {
					analogTunerPort.setTunerCenterFrequency(controlID, (Double) newValue);
				} else if (feiAttr.equals(ENABLED.getFeiAttribute())) {
					analogTunerPort.setTunerEnable(controlID, (Boolean) newValue);
				} else if (feiAttr.equals(GAIN.getFeiAttribute())) {
					// Gain is double in model and documentation, but float in API
					float gain = ((Double) newValue).floatValue();
					analogTunerPort.setTunerGain(controlID, gain);
				} else if (feiAttr.equals(REFERENCE_SOURCE.getFeiAttribute())) {
					analogTunerPort.setTunerReferenceSource(controlID, (Integer) newValue);
				}
			} catch (NumberFormatException e) {
				throw new CoreException(new Status(IStatus.ERROR, "gov.redhawk.frontend", "Number Format Exception in property assignment", e));
			} catch (FrontendException e) {
				throw new CoreException(new Status(IStatus.ERROR, "gov.redhawk.frontend", "Frontend Exception in property assignment: " + e.msg, e));
			} catch (BadParameterException e) {
				throw new CoreException(new Status(IStatus.ERROR, "gov.redhawk.frontend", "Bad Parameter Exception in property assignment: " + e.msg, e));
			} catch (NotSupportedException e) {
				throw new CoreException(new Status(IStatus.ERROR, "gov.redhawk.frontend", "Not Supported Exception in property assignment: " + e.msg, e));
			}
		}
	}

	public static enum ListenerAllocationProperty {
		INSTANCE;

		/**
		 * @return The fully qualified ID of the property
		 */
		public String getId() {
			return "FRONTEND::listener_allocation";
		}

		/**
		 * @return A human-readable description of the property
		 */
		public String getDescription() {
			return "Frontend Interfaces v2 listener allocation structure";
		}

		/**
		 * @return A new {@link Struct} instance for the property
		 */
		public Struct createStruct() {
			Struct listenerAllocStruct = PrfFactory.eINSTANCE.createStruct();
			listenerAllocStruct.setDescription(getDescription());
			listenerAllocStruct.setId(getId());
			listenerAllocStruct.setName("frontend_listener_allocation");
			final ConfigurationKind kind = PrfFactory.eINSTANCE.createConfigurationKind();
			kind.setType(StructPropertyConfigurationType.ALLOCATION);
			listenerAllocStruct.getConfigurationKind().add(kind);
			listenerAllocStruct.getSimple().addAll(createListenerAllocationSimples());

			return listenerAllocStruct;
		}

		/**
		 * @param listenerAllocation The listener to be deallocated
		 * @return A new {@link Struct} instance for deallocation
		 * @since 1.1
		 */
		public Struct createDeallocationStruct(ListenerAllocation listenerAllocation) {
			Struct struct = createStruct();
			String allocationId = listenerAllocation.getTunerStatus().getAllocationID().split(",")[0];
			((Simple) struct.getProperty(ListenerAllocationProperties.EXISTING_ALLOCATION_ID.getId())).setValue(allocationId);
			((Simple) struct.getProperty(ListenerAllocationProperties.LISTENER_ALLOCATION_ID.getId())).setValue(listenerAllocation.getListenerID());
			return struct;
		}

		private Collection< ? extends Simple> createListenerAllocationSimples() {
			List<Simple> listenerAllocSimpleList = new ArrayList<Simple>();
			listenerAllocSimpleList.add(ListenerAllocationProperties.EXISTING_ALLOCATION_ID.createSimple());
			listenerAllocSimpleList.add(ListenerAllocationProperties.LISTENER_ALLOCATION_ID.createSimple());

			return listenerAllocSimpleList;
		}
	}

	public static enum TunerAllocationProperty {
		INSTANCE;

		/**
		 * @return The fully qualified ID of the property
		 */
		public String getId() {
			return "FRONTEND::tuner_allocation";
		}

		/**
		 * @return A human-readable description of the property
		 */
		public String getDescription() {
			return "Frontend Interfaces v2 main allocation structure";
		}

		/**
		 * @return A new {@link Struct} instance for the property
		 */
		public Struct createStruct() {
			Struct tunerAllocStruct = PrfFactory.eINSTANCE.createStruct();
			tunerAllocStruct.setDescription(getDescription());
			tunerAllocStruct.setId(getId());
			tunerAllocStruct.setName("frontend_tuner_allocation");
			final ConfigurationKind kind = PrfFactory.eINSTANCE.createConfigurationKind();
			kind.setType(StructPropertyConfigurationType.ALLOCATION);
			tunerAllocStruct.getConfigurationKind().add(kind);
			tunerAllocStruct.getSimple().addAll(createTunerAllocationSimples());

			return tunerAllocStruct;
		}

		/**
		 * @param tuner The tuner to be deallocated
		 * @return A new {@link Struct} instance for deallocation
		 * @since 1.1
		 */
		public Struct createDeallocationStruct(TunerStatus tuner) {
			// Only the allocation ID needs to have a valid value per FEI docs; other fields are ignored
			Struct struct = createStruct();
			String allocationId = tuner.getAllocationID().split(",")[0];
			((Simple) struct.getProperty(TunerAllocationProperties.TUNER_TYPE.getId())).setValue("");
			((Simple) struct.getProperty(TunerAllocationProperties.ALLOCATION_ID.getId())).setValue(allocationId);
			((Simple) struct.getProperty(TunerAllocationProperties.CENTER_FREQUENCY.getId())).setValue("0");
			((Simple) struct.getProperty(TunerAllocationProperties.BANDWIDTH.getId())).setValue("0");
			((Simple) struct.getProperty(TunerAllocationProperties.BANDWIDTH_TOLERANCE.getId())).setValue("0");
			((Simple) struct.getProperty(TunerAllocationProperties.SAMPLE_RATE.getId())).setValue("0");
			((Simple) struct.getProperty(TunerAllocationProperties.SAMPLE_RATE_TOLERANCE.getId())).setValue("0");
			((Simple) struct.getProperty(TunerAllocationProperties.DEVICE_CONTROL.getId())).setValue("false");
			((Simple) struct.getProperty(TunerAllocationProperties.GROUP_ID.getId())).setValue("");
			((Simple) struct.getProperty(TunerAllocationProperties.RF_FLOW_ID.getId())).setValue("");
			return struct;
		}

		private Collection< ? extends Simple> createTunerAllocationSimples() {
			List<Simple> tunerAllocSimpleList = new ArrayList<Simple>();

			tunerAllocSimpleList.add(TunerProperties.TunerAllocationProperties.TUNER_TYPE.createSimple());

			tunerAllocSimpleList.add(TunerProperties.TunerAllocationProperties.ALLOCATION_ID.createSimple());

			Simple cFreq = TunerProperties.TunerAllocationProperties.CENTER_FREQUENCY.createSimple();
			cFreq.setValue("0.0");
			tunerAllocSimpleList.add(cFreq);

			Simple bandwidth = TunerProperties.TunerAllocationProperties.BANDWIDTH.createSimple();
			bandwidth.setValue("0.0");
			tunerAllocSimpleList.add(bandwidth);

			Simple bandwidthTol = TunerProperties.TunerAllocationProperties.BANDWIDTH_TOLERANCE.createSimple();
			bandwidthTol.setValue("10.0");
			tunerAllocSimpleList.add(bandwidthTol);

			Simple sampleRate = TunerProperties.TunerAllocationProperties.SAMPLE_RATE.createSimple();
			sampleRate.setValue("0.0");
			tunerAllocSimpleList.add(sampleRate);

			Simple sampleRateTol = TunerProperties.TunerAllocationProperties.SAMPLE_RATE_TOLERANCE.createSimple();
			sampleRateTol.setValue("10.0");
			tunerAllocSimpleList.add(sampleRateTol);

			Simple deviceControl = TunerProperties.TunerAllocationProperties.DEVICE_CONTROL.createSimple();
			deviceControl.setValue("true");
			tunerAllocSimpleList.add(deviceControl);

			tunerAllocSimpleList.add(TunerProperties.TunerAllocationProperties.GROUP_ID.createSimple());

			tunerAllocSimpleList.add(TunerProperties.TunerAllocationProperties.RF_FLOW_ID.createSimple());

			return tunerAllocSimpleList;
		}
	}

	public static enum TunerAllocationProperties {
		// instance name ID PRF type
		TUNER_TYPE(
				"tuner_type",
					PropertyValueType.STRING,
					null,
					"Tuner type",
					"Example Tuner Types: TX, RX, CHANNELIZER, DDC, RX_DIGITIZER, RX_DIGTIZIER_CHANNELIZER"),
		ALLOCATION_ID(
				"allocation_id",
					PropertyValueType.STRING,
					null,
					"Allocation ID",
					"The allocation_id set by the caller. Used by the caller to reference the allocation uniquely"),
		CENTER_FREQUENCY("center_frequency", PropertyValueType.DOUBLE, "Hz", "Center frequency", "Requested center frequency"),
		BANDWIDTH("bandwidth", PropertyValueType.DOUBLE, "Hz", "Bandwidth", "Requested bandwidth (+/- the tolerance)"),
		BANDWIDTH_TOLERANCE(
				"bandwidth_tolerance",
					PropertyValueType.DOUBLE,
					"percent",
					"Bandwidth tolerance",
					"Allowable Percent above requested bandwidth  (ie - 100 would be up to twice)"),
		SAMPLE_RATE(
				"sample_rate",
					PropertyValueType.DOUBLE,
					"Hz",
					"Sample rate",
					"Requested sample rate (+/- the tolerance). This can be ignored for such devices as analog tuners"),
		SAMPLE_RATE_TOLERANCE(
				"sample_rate_tolerance",
					PropertyValueType.DOUBLE,
					"percent",
					"Sample rate tolerance",
					"Allowable Percent above requested sample rate (ie - 100 would be up to twice)"),
		DEVICE_CONTROL(
				"device_control",
					PropertyValueType.BOOLEAN,
					null,
					"Device control",
					"True: Has control over the device to make changes\n"
						+ "False: Does not need control and can just attach to any currently tasked device that satisfies the parameters (essentually a listener)"),
		GROUP_ID(
				"group_id",
					PropertyValueType.STRING,
					null,
					"Group ID",
					"Unique identifier that specifies the group a device must be in. Must match group_id on the device"),
		RF_FLOW_ID(
				"rf_flow_id",
					PropertyValueType.STRING,
					null,
					"RF flow ID",
					"Optional. Specifies the RF flow of a specific input source to allocate against. If left empty, it will match all FrontEnd devices.");

		private String id;
		private PropertyValueType type;
		private String shortId;
		private String name;
		private String description;
		private String units;

		private TunerAllocationProperties(String shortId, PropertyValueType prfType, String units, String name, String description) {
			this.id = "FRONTEND::tuner_allocation::" + shortId;
			this.type = prfType;
			this.shortId = shortId;
			this.name = name;
			this.description = description;
			this.units = units;
		}

		/**
		 * @return The fully qualified ID of the property
		 */
		public String getId() {
			return this.id;
		}

		/**
		 * @return The property's type
		 */
		public PropertyValueType getType() {
			return this.type;
		}

		/**
		 * @return A human-readable name
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * @return A human-readable description of the property
		 */
		public String getDescription() {
			return this.description;
		}

		/**
		 * @return A new {@link Simple} instance for the specified property
		 */
		public Simple createSimple() {
			Simple simple = PrfFactory.eINSTANCE.createSimple();
			simple.setId(this.id);
			simple.setName(this.shortId);
			simple.setDescription(this.description);
			simple.setType(this.type);
			if (this.units != null) {
				simple.setUnits(this.units);
			}
			return simple;
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

		/**
		 * @return The fully qualified ID of the property
		 */
		public String getId() {
			return this.id;
		}

		/**
		 * @return The property's type
		 */
		public PropertyValueType getType() {
			return this.type;
		}

		/**
		 * @return A new {@link Simple} instance for the specified property
		 */
		public Simple createSimple() {
			Simple simple = PrfFactory.eINSTANCE.createSimple();
			simple.setId(this.id);
			simple.setType(this.type);
			return simple;
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

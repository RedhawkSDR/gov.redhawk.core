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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

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
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.util.CorbaUtils2;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;
import mil.jpeojtrs.sca.util.collections.FeatureMapIterator;
import mil.jpeojtrs.sca.util.collections.FeatureMapList;

/**
 * Enum of available tuner status properties as defined by specification.
 * Includes required and optional properties
 */
public enum TunerProperties {
	INSTANCE;

	/**
	 * Contains FEI property definitions mapped by ID.
	 */
	private Map<String, AbstractProperty> feiProps;

	TunerProperties() {
		ResourceSet resourceSet = new ResourceSetImpl();

		URI uri = URI.createPlatformPluginURI("/gov.redhawk.frontend/xml/fei.prf.xml", false);
		Resource resource = resourceSet.getResource(uri, true);
		Properties props = Properties.Util.getProperties(resource);
		feiProps = new HashMap<>();
		for (AbstractProperty prop : new FeatureMapList<AbstractProperty>(props.getProperties(), AbstractProperty.class)) {
			feiProps.put(prop.getId(), prop);
		}
	}

	/**
	 * Used to create the <code>FRONTEND::tuner_status</code> property.
	 * @since 2.0
	 */
	public static enum TunerStatusProperty {
		INSTANCE;

		/**
		 * @return The fully qualified ID of the property
		 */
		public String getId() {
			return "FRONTEND::tuner_status";
		}

		/**
		 * @deprecated Use {@link #createProperty(boolean)}
		 */
		@Deprecated
		public StructSequence createProperty() {
			return createProperty(false);
		}

		/**
		 * @param scanner If the device is a scanner device
		 * @return A new {@link StructSequence} instance of the property containing only the required fields.
		 */
		public StructSequence createProperty(boolean scanner) {
			StructSequence prop = (StructSequence) TunerProperties.INSTANCE.feiProps.get(getId());
			prop = EcoreUtil.copy(prop);

			// Remove members that aren't required
			Iterator<AbstractProperty> iter = new FeatureMapIterator<>(prop.getStruct().getFields(), AbstractProperty.class);
			while (iter.hasNext()) {
				AbstractProperty member = iter.next();
				if (!TunerStatusAllocationProperties.fromPropID(member.getId()).isRequired(scanner)) {
					iter.remove();
				}
			}

			return prop;
		}
	}

	/**
	 * Required and optional fields within the FRONTEND::tuner_status property. Note that it is not an
	 * allocation property (this <code>enum</code> is misnamed).
	 * <p/>
	 * The name ({@link #getName()}) and FEI package reference ({@link #getFeiAttribute()}) are used by the property
	 * view for displaying and editing a tuner status.
	 * @see TunerStatusProperty
	 */
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
		/**
		 * @since 2.0
		 */
		SCAN_MODE_ENABLED("FRONTEND::tuner_status::scan_mode_enabled", PropertyValueType.BOOLEAN, "Scan Mode Enabled"),
		/**
		 * @since 2.0
		 */
		SUPPORTS_SCAN("FRONTEND::tuner_status::supports_scan", PropertyValueType.BOOLEAN, "Supports Scanning"),

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
		private final AbstractProperty property;

		TunerStatusAllocationProperties(String id, PropertyValueType prfType, String name, EAttribute feiAttr, boolean editable) {
			this.id = id;
			this.type = prfType;
			this.name = name;
			this.feiAttr = feiAttr;
			this.editable = editable;

			StructSequence ss = (StructSequence) TunerProperties.INSTANCE.feiProps.get(TunerStatusProperty.INSTANCE.getId());
			AbstractProperty tmpProp = ss.getStruct().getProperty(id);
			if (tmpProp != null) {
				this.property = tmpProp;
				return;
			}

			// This should never occur
			throw new IllegalStateException("No PRF definition found for " + toString());
		}

		TunerStatusAllocationProperties(String id, PropertyValueType prfType, String name) {
			this(id, prfType, name, null, false);
		}

		/**
		 * @return True if the FEI IDL provides a method to change this value
		 */
		public boolean isEditable() {
			return editable;
		}

		public String getId() {
			return this.id;
		}

		/**
		 * @deprecated Do not use
		 */
		@Deprecated
		public PropertyValueType getType() {
			return this.type;
		}

		/**
		 * @return A human-readable name for the property
		 */
		public String getName() {
			return this.name;
		}

		public EAttribute getFeiAttribute() {
			return feiAttr;
		}

		/**
		 * @return Whether this property is required or optional per the FEI spec
		 * @since 2.0
		 * @deprecated Use {@link #isRequired(boolean)}
		 */
		@Deprecated
		public boolean isRequired() {
			return isRequired(false);
		}

		/**
		 * @return Whether this property is required or optional per the FEI spec
		 * @param scanner If the device is a scanner device
		 * @since 2.0
		 */
		public boolean isRequired(boolean scanner) {
			switch (this) {
			case ALLOCATION_ID:
			case BANDWIDTH:
			case CENTER_FREQUENCY:
			case ENABLED:
			case GROUP_ID:
			case RF_FLOW_ID:
			case SAMPLE_RATE:
			case TUNER_TYPE:
				// Always required
				return true;
			case SCAN_MODE_ENABLED:
			case SUPPORTS_SCAN:
				// Required if it's a scanner
				return scanner;
			default:
				// Everything else not required
				return false;
			}
		}

		/**
		 * @since 2.0
		 */
		public AbstractProperty createProperty() {
			return EcoreUtil.copy(this.property);
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
				Job job = Job.create("Update device property: " + prop.getName(), parentMonitor -> {
					final SubMonitor subMonitor = SubMonitor.convert(parentMonitor,
						"Setting value of " + prop.getName() + " to " + notification.getNewValue(), IProgressMonitor.UNKNOWN);
					try {
						return CorbaUtils2.invoke(() -> {
							TunerStatusAllocationProperties.doRun(device, feiAttr, finalAllocationId, notification.getNewValue());
							return Status.OK_STATUS;
						}, subMonitor.newChild(1));
					} catch (ExecutionException e) {
						return new Status(IStatus.ERROR, "gov.redhawk.frontend", "Failed to update device property: " + prop.getName(), e.getCause());
					} finally {
						device.fetchProperties(subMonitor.newChild(1));
					}
				});
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

	/**
	 * Used to create the <code>FRONTEND::listener_allocation</code> property.
	 */
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
		 * @deprecated Use {@link #createProperty()}
		 */
		@Deprecated
		public Struct createStruct() {
			return createProperty();
		}

		/**
		 * @return A new {@link Struct} instance of the property
		 * @since 2.0
		 */
		public Struct createProperty() {
			Struct prop = (Struct) TunerProperties.INSTANCE.feiProps.get(getId());
			return EcoreUtil.copy(prop);
		}

		/**
		 * @param listenerAllocation The listener to be deallocated
		 * @return A new {@link Struct} instance for deallocation
		 * @since 1.1
		 */
		public Struct createDeallocationStruct(ListenerAllocation listenerAllocation) {
			Struct struct = createProperty();
			String allocationId = listenerAllocation.getTunerStatus().getAllocationID().split(",")[0];
			((Simple) struct.getProperty(ListenerAllocationProperties.EXISTING_ALLOCATION_ID.getId())).setValue(allocationId);
			((Simple) struct.getProperty(ListenerAllocationProperties.LISTENER_ALLOCATION_ID.getId())).setValue(listenerAllocation.getListenerID());
			return struct;
		}
	}

	/**
	 * Used to create the <code>FRONTEND::tuner_allocation</code> property.
	 */
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
		 * @deprecated Use {@link #createProperty()}.
		 */
		@Deprecated
		public Struct createStruct() {
			return createProperty();
		}

		/**
		 * @return A new {@link Struct} instance for the property
		 * @since 2.0
		 */
		public Struct createProperty() {
			Struct prop = (Struct) TunerProperties.INSTANCE.feiProps.get(getId());
			return EcoreUtil.copy(prop);
		}

		/**
		 * @param tuner The tuner to be deallocated
		 * @return A new {@link Struct} instance for deallocation
		 * @since 1.1
		 */
		public Struct createDeallocationStruct(TunerStatus tuner) {
			// Only the allocation ID needs to have a valid value per FEI docs; other fields are ignored
			Struct struct = createProperty();
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
	}

	/**
	 * Fields within the <code>FRONTEND::tuner_allocation</code> property.
	 * @see TunerAllocationProperty
	 */
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
		 * @deprecated Do not use
		 */
		@Deprecated
		public PropertyValueType getType() {
			return this.type;
		}

		/**
		 * @return A human-readable name for the property
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
		 * @deprecated Do not use. All fields are required, not just some.
		 */
		@Deprecated
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

	/**
	 * Fields within the <code>FRONTEND::listener_allocation</code> property.
	 * @see ListenerAllocationProperty
	 */
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
		 * @deprecated Do not use
		 */
		@Deprecated
		public PropertyValueType getType() {
			return this.type;
		}

		/**
		 * @deprecated Do not use. All fields are required, not just some.
		 */
		@Deprecated
		public Simple createSimple() {
			Simple simple = PrfFactory.eINSTANCE.createSimple();
			simple.setId(this.id);
			simple.setType(this.type);
			return simple;
		}
	}

	/**
	 * Used to create the <code>FRONTEND::scanner_allocation</code> property.
	 * @since 2.0
	 */
	public enum ScannerAllocationProperty {
		INSTANCE;

		/**
		 * @return The fully qualified ID of the property
		 */
		public String getId() {
			return "FRONTEND::scanner_allocation";
		}

		/**
		 * @return A new {@link Struct} instance of the property
		 * @since 2.0
		 */
		public Struct createProperty() {
			Struct prop = (Struct) TunerProperties.INSTANCE.feiProps.get(getId());
			return EcoreUtil.copy(prop);
		}
	}

	/**
	 * Used to create the <code>connectionTable</code> property.
	 * @since 2.0
	 */
	public enum ConnectionTableProperty {
		INSTANCE;

		/**
		 * @return The fully qualified ID of the property
		 */
		public String getId() {
			return "connectionTable";
		}

		/**
		 * @return A new {@link StructSequence} instance for the property
		 */
		public StructSequence createProperty() {
			StructSequence prop = (StructSequence) TunerProperties.INSTANCE.feiProps.get(getId());
			return EcoreUtil.copy(prop);
		}
	}

	/**
	 * @deprecated Use {@link TunerStatusProperty} or {@link TunerStatusAllocationProperties}.
	 */
	@Deprecated
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

package gov.redhawk.frontend.edit.utils;

import gov.redhawk.frontend.FrontendFactory;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.ModelDevice;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.edit.utils.TunerProperties.TunerStatusAllocationProperties;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.util.List;

import mil.jpeojtrs.sca.scd.Interface;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EContentAdapter;

import CF.PortSupplierPackage.UnknownPort;
import FRONTEND.BadParameterException;
import FRONTEND.DigitalTuner;
import FRONTEND.DigitalTunerHelper;
import FRONTEND.FrontendException;
import FRONTEND.NotSupportedException;

public enum TunerUtils {
	INSTANCE;

	private TunerUtils() {
	}

	public void processChange(Notification notification) {

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
					tuner.getSimples().addAll(struct.getSimples());
					tuner.setTunerID(String.valueOf(tunerIndex));
					tunerList.add(tuner);
					tunerIndex++;

					
					for (ScaSimpleProperty simple : tuner.getSimples()) {
						setTunerProperty(tuner, simple);
						for (TunerStatusAllocationProperties value : TunerStatusAllocationProperties.values()) {
							if (value.getId().equals(simple.getId())) {
								//value.setFeiPropertyAttribute(simple);//disabled this because there is no such method
							}
						}

					}
					
					addNotificationAdapter(tuner);

					// TODO add comment describing what is going on here
					ScaSimpleProperty allocSimple = struct.getSimple("FRONTEND::tuner_status::allocation_id_csv");
					if (allocSimple == null) {
						continue;
					}
					Object allocationValue = allocSimple.getValue();
					if (allocationValue == null) {
						continue;
					}
					String allocationIDs = allocationValue.toString();
					for (String allocationID : allocationIDs.split(",")) {
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
	 * Creates adapter to fire whenever model is updated
	 * @param tuner Model Object
	 */
	private void addNotificationAdapter(TunerStatus tuner) {
		EContentAdapter adapter = new EContentAdapter() {
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				// TODO
				// if oldValue != new Value... reflect back to device
				// System.out.println("Old Value: " + notification.getOldValue() + "  --- New Value: " + notification.getNewValue());
			}
		};
		tuner.eAdapters().add(adapter);

	}

	/** 
	 * Initializes properties of model object</br />
	 * Tuner type provides tree label in SCA Explorer, see TunerStatusItemProvider getText() 
	 * 
	 * @param tuner represents tuner model object
	 * 
	 */
	public static void setTunerProperty(final TunerStatus tuner, final ScaSimpleProperty simple) {
			String name = null;
			final Object value = simple.getValue();
			for (TunerProperties.TunerStatusAllocationProperties allocProp : TunerProperties.TunerStatusAllocationProperties.values()) {
				if (allocProp.getId().equals(simple.getId())) {
					name = allocProp.getName();
				}
			}
			
			final String id = name;
			ScaModelCommand.execute(tuner, new ScaModelCommand() {
				@Override
				public void execute() {
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
			});
	
	}

	/**
	 * TODO
	 * Update tuner status struct with new property value
	 * @param wrapper
	 */
	public static void updateTunerProperties(final TunerPropertyWrapper wrapper) {

		String allocationID = wrapper.getTuner().getAllocationID();

		// parse out the control id
		int endChar = allocationID.indexOf(",");
		if (endChar > 0) {
			allocationID = allocationID.substring(0, 10);
		}

		ScaDevice< ? > device = wrapper.getTuner().getTunerContainer().getModelDevice().getScaDevice();

		org.omg.CORBA.Object port = null;

		try {
			port = device.getPort("DigitalTuner_in");
		} catch (UnknownPort e1) {
			e1.printStackTrace();
		}

		DigitalTuner digitalTunerPort = DigitalTunerHelper.narrow(port);
		try {
			if (wrapper.getName().equals("AGC")) {
				digitalTunerPort.setTunerAgcEnable(allocationID, wrapper.getTuner().isEnabled());
			} else if (wrapper.getName().equals("Bandwidth")) {
				digitalTunerPort.setTunerBandwidth(allocationID, wrapper.getTuner().getBandwidth());
			} else if (wrapper.getName().equals("Center Frequency")) {
				digitalTunerPort.setTunerCenterFrequency(allocationID, wrapper.getTuner().getCenterFrequency());
			} else if (wrapper.getName().equals("Enabled")) {
				digitalTunerPort.setTunerEnable(allocationID, wrapper.getTuner().isEnabled());
			} else if (wrapper.getName().equals("Gain")) {
				//Gain is double in model and documentation, but float in API
				float gain = Float.parseFloat(String.valueOf(wrapper.getTuner().getGain()));
				digitalTunerPort.setTunerGain(allocationID, gain);
			} else if (wrapper.getName().equals("Reference Source")) {
				//Reference Source is long in model and documentation, but int in API
				int referenceSource = Integer.parseInt(String.valueOf(wrapper.getTuner().getReferenceSource()));
				digitalTunerPort.setTunerReferenceSource(allocationID, referenceSource);
			}
		} catch (NumberFormatException e) {
			// TODO - pass these exceptions to UI for transparency to user
			e.printStackTrace();
		} catch (FrontendException e) {
			e.printStackTrace();
		} catch (BadParameterException e) {
			e.printStackTrace();
		} catch (NotSupportedException e) {
			e.printStackTrace();
		}
	}

}

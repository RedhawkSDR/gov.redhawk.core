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
						TunerStatusAllocationProperties.setValue(tuner, simple);
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
					String[] allocations = allocationIDs.split(",");
					for (int index = 1; index < allocations.length; ++index) {
						if ("".equals(allocations[index])) {
							continue;
						}
						ListenerAllocation allocation = FrontendFactory.eINSTANCE.createListenerAllocation();
						allocation.setListenerID(allocations[index]);
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
	private void addNotificationAdapter(final TunerStatus tuner) {
		EContentAdapter adapter = new EContentAdapter() {
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				TunerStatusAllocationProperties.updateDeviceValue(tuner, notification);
			}
		};
		tuner.eAdapters().add(adapter);

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

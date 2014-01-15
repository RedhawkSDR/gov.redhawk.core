package gov.redhawk.frontend.edit.utils;

import gov.redhawk.frontend.FrontendFactory;
import gov.redhawk.frontend.ModelDevice;
import gov.redhawk.frontend.Tuner;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.edit.utils.TunerWrapper.TunerProperty;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.util.List;

import mil.jpeojtrs.sca.scd.Interface;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;

import FRONTEND.DigitalTunerHelper;

public enum TunerUtils {
	INSTANCE;

	//	private Map<EObject, Object[]> fMap = Collections.synchronizedMap(new HashMap<EObject, Object[]>());
	//	private final TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain("gov.redhawk.sca.editingDomain");
	//	private Resource tunerStatus;

	private TunerUtils() {
		//		tunerStatus = editingDomain.getResourceSet().createResource(URI.createURI("null:///tunerStatus.xml"));
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
				EList<Tuner> tunerList = container.getTuners();

				// populate container object with tuners from device
				int tunerIndex = 0;
				for (ScaStructProperty struct : structs) {
					final Tuner tuner = FrontendFactory.eINSTANCE.createTuner();
					tuner.setTunerStatus(FrontendFactory.eINSTANCE.createTunerStatus());
					tuner.setTunerContainer(container);
					tuner.setTunerStruct(struct);
					tuner.setTunerID(String.valueOf(tunerIndex));
					tunerList.add(tuner);
					tunerIndex++;

					// Assign tuner type to model object - provides tree label
					setTunerType(tuner);
				}

				return new Object[] { container };
			}
		}
		return new Object[0];
	}

	/** 
	 * Assigns tuner type to model object
	 * 
	 * @param tuner represents tuner model object
	 * 
	 */
	private void setTunerType(Tuner tuner) {//		
		ScaStructProperty tunerDevice = tuner.getTunerStruct();
		String tunerType = tunerDevice.getSimple("FRONTEND::tuner_status::tuner_type").getValue().toString();
		tuner.setTunerType(tunerType);
	}

	/**
	 * TODO
	 * @param entry
	 */
	public static void setTunerProperties(final TunerProperty entry) {
		final ScaStructProperty tunerStruct = entry.getWrapper().getTuner().getTunerStruct();
		final String newValue = String.valueOf(entry.getValue());
		
		//TODO get the port
		//org.omg.CORBA.Object port = device.getPort("DigitalTuner_in");
		//narrow to FrontEndPort (or something)
		//

		ScaModelCommand.execute(tunerStruct, new ScaModelCommand() {
			@Override
			public void execute() {
				if (entry.getId().equals("Allocation ID")) {
					tunerStruct.getSimple("FRONTEND::tuner_status::allocation_id_csv").setValue(newValue);
				}
				if (entry.getId().equals("Gain")) {
					tunerStruct.getSimple("FRONTEND::tuner_status::gain").setValue(Double.parseDouble(newValue));
				}
			}
		});
	}
}

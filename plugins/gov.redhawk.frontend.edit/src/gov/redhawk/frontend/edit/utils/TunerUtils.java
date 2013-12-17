package gov.redhawk.frontend.edit.utils;

import gov.redhawk.frontend.FrontendFactory;
import gov.redhawk.frontend.Tuner;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.scd.Interface;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import FRONTEND.DigitalTunerHelper;

public enum TunerUtils {
	INSTANCE;

	private Map<EObject, Object[]> fMap = Collections.synchronizedMap(new HashMap<EObject, Object[]>());
	private final TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain("gov.redhawk.sca.editingDomain");
	private Resource tunerStatus;

	private TunerUtils() {
		tunerStatus = editingDomain.getResourceSet().createResource(URI.createURI("null:///tunerStatus.xml"));
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
		EList<Interface> interfaceList = device.getProfileObj().getDescriptor().getComponent().getInterfaces().getInterface();
		for (Interface i : interfaceList) {
			// Check to see if this is a frontEndInterface
			if (i.getRepid().equals(DigitalTunerHelper.id())) {
				// Fetch list of tuners
				ScaStructSequenceProperty prop = (ScaStructSequenceProperty) device.getProperty("FRONTEND::tuner_status");
				EList<ScaStructProperty> structs = prop.getStructs();

				// create TunerContainer model object
				TunerContainer container = FrontendFactory.eINSTANCE.createTunerContainer();
				EList<ScaStructProperty> tunerList = container.getTuners();

				// populate container object with tuners from device
				for (ScaStructProperty struct : structs) {
					tunerList.add(struct);
				}

				return new Object[] { container };
			}
		}
		return new Object[0];
	}

	/**
	 * 
	 * 
	 * @param container
	 * @return an Object[] containing the tuners associated with the parent device
	 */
	public Object[] getChildren(final TunerContainer container) {
		final List<Tuner> tuners = new ArrayList<Tuner>();
		// Create tuner model object - TODO if there is not allocation ID, then do not create a tuner
		for (ScaStructProperty tunerDevice : container.getTuners()) {
			final Tuner tunerModel = FrontendFactory.eINSTANCE.createTuner();
			tuners.add(tunerModel);
			
			// Assign tuner type to model object - provides tree label
			setTunerType(tunerDevice, tunerModel);
			
			

			tunerDevice.getSimples(); //TODO this is all of the simples for each individual physical Tuner, map them to the model objects

			ScaModelCommand.execute(container, new ScaModelCommand() {
				@Override
				public void execute() {
					tunerStatus.getContents().add(tunerModel);

					// On dispose remove tuner information
					container.eAdapters().add(new AdapterImpl() {
						@Override
						public void notifyChanged(Notification msg) {
							switch (msg.getFeatureID(IDisposable.class)) {
							case ScaPackage.IDISPOSABLE__DISPOSED:
								if (msg.getNewBooleanValue()) {
									tunerStatus.getContents().remove(tunerModel);
									container.eAdapters().remove(this);
									fMap.remove(container);
								}
							}
						}

					});
				}
			});
		}
		if (!tuners.isEmpty()) {
			return tuners.toArray();
		}

		// If there are no tuners, return an empty Object array
		return new Object[0];
	}

	/** 
	 * Assigns tuner type to model object
	 * 
	 * @param tunerDevice represents physical tuner
	 * @param tunerModel represents tuner model object
	 * 
	 */
	private void setTunerType(ScaStructProperty tunerDevice, Tuner tunerModel) {
		String tunerType = tunerDevice.getSimple("FRONTEND::tuner_status::tuner_type").getValue().toString();
		tunerModel.setTunerType(tunerType);
	}
}

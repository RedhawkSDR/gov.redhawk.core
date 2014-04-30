/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.frontend.provider;

import gov.redhawk.frontend.FrontendFactory;
import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.UnallocatedTunerContainer;
import gov.redhawk.frontend.util.TunerProperties.StatusProperties;
import gov.redhawk.frontend.util.TunerProperties.TunerStatusAllocationProperties;
import gov.redhawk.frontend.util.TunerUtils;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.services.AbstractDataProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import mil.jpeojtrs.sca.scd.Interface;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.spd.SpdPackage;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;

import FRONTEND.DigitalTunerHelper;

/**
 * 
 */
public class FrontEndDataProvider extends AbstractDataProvider {

	private static final Pattern PROP_PATTERN = Pattern.compile("FRONTEND::tuner_status.*");

	private static final EStructuralFeature[] PATH = new EStructuralFeature[] { ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ,
		SpdPackage.Literals.SOFT_PKG__DESCRIPTOR, SpdPackage.Literals.DESCRIPTOR__COMPONENT, ScdPackage.Literals.SOFTWARE_COMPONENT__INTERFACES,
		ScdPackage.Literals.INTERFACES__INTERFACE };

	private final ScaDevice< ? > device;
	private boolean supportsFei = false;

	private Adapter tunerAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.isTouch()) {
				return;
			}
			switch (msg.getFeatureID(TunerStatus.class)) {
			case FrontendPackage.TUNER_STATUS__TUNER_STATUS_STRUCT:
				break;
			default:
				TunerStatusAllocationProperties.updateDeviceValue((TunerStatus) msg.getNotifier(), msg);
				break;
			}
		}
	};

	private Adapter deviceAdapter = new EContentAdapter() {

		@Override
		protected void addAdapter(final Notifier notifier) {
			if (notifier instanceof ScaAbstractProperty< ? >) {
				ScaAbstractProperty< ? > prop = (ScaAbstractProperty< ? >) notifier;
				if (FrontEndDataProvider.PROP_PATTERN.matcher(prop.getId()).matches()) {
					super.addAdapter(notifier);
				}
			}
		}

		@Override
		public void notifyChanged(Notification msg) {
			super.notifyChanged(msg);
			if (msg.isTouch()) {
				return;
			}
			if (msg.getNotifier() instanceof ScaDevice< ? >) {
				switch (msg.getFeatureID(ScaDevice.class)) {
				case ScaPackage.SCA_DEVICE__DISPOSED:
					if (msg.getNewBooleanValue()) {
						removeTunerContainer();
						((Notifier) msg.getNotifier()).eAdapters().remove(this);
					}
					break;
				case ScaPackage.SCA_DEVICE__PROPERTIES:
					updateTunerContainerJob.schedule(100);
					break;
				default:
					break;
				}
			} else if (msg.getNotifier() instanceof ScaAbstractProperty< ? >) {
				ScaAbstractProperty< ? > prop = (ScaAbstractProperty< ? >) msg.getNotifier();
				if (FrontEndDataProvider.PROP_PATTERN.matcher(prop.getId()).matches()) {
					updateTunerContainerJob.schedule(500);
				}
			}
		}

	};
	
	private Job updateTunerContainerJob = new Job("Update Tuner Container") {
		{
			setUser(false);
			setSystem(true);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			if (isDisposed()) {
				return Status.CANCEL_STATUS;
			}
			ScaModelCommand.execute(device, new ScaModelCommand() {

				@Override
				public void execute() {
					updateTunerContainer();
				}
			});
			return Status.OK_STATUS;
		}
		
	};

	private final TunerContainer container = FrontendFactory.eINSTANCE.createTunerContainer();

	private Job fetchAndPopulate = new Job("Fetch and populate Front End Device") {
		{
			setUser(false);
			setSystem(true);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			if (isDisposed()) {
				return Status.CANCEL_STATUS;
			}
			if (!device.isDisposed()) {
				try {
					device.refresh(monitor, RefreshDepth.FULL);
				} catch (InterruptedException e) {
					return Status.CANCEL_STATUS;
				}
//				device.fetchPorts(monitor);
//				device.fetchProperties(monitor);
			} else {
				return Status.CANCEL_STATUS;
			}
			if (device.getProfileObj() == null) {
				ScaModelCommand.execute(device, new ScaModelCommand() {
					
					@Override
					public void execute() {
						if (device.getProfileObj() == null) {
							device.eAdapters().add(new AdapterImpl() {
								public void notifyChanged(Notification msg) {
									switch(msg.getFeatureID(ScaDevice.class)) {
									case ScaPackage.SCA_DEVICE__PROFILE_URI:
										if (msg.getNewValue() != null) {
											device.eAdapters().remove(this);
											schedule();
										}
										break;
									default:
										break;
									}
								}
							});
						} else {
							schedule();
						}
					}
				});
				return Status.CANCEL_STATUS;
			}
			supportsFei = calculateSupport();
			if (supportsFei) {
				ScaModelCommand.execute(device, new ScaModelCommand() {
	
					@Override
					public void execute() {
						device.eAdapters().add(deviceAdapter);
						updateTunerContainer();
					}
				});
			}
			return Status.OK_STATUS;
		}

	};

	public FrontEndDataProvider(ScaDevice< ? > device) {
		this.device = device;
	}

	private void removeTunerContainer() {
		device.getFeatureData().remove(TunerUtils.TUNER_CONTAINER_ID);
	}

	@Override
	public void dispose() {
		if (!device.isDisposed()) {
			ScaModelCommand.execute(device, new ScaModelCommand() {

				@Override
				public void execute() {
					removeTunerContainer();
				}
			});
		}
		super.dispose();
	}

	@Override
	public IStatus refresh(IProgressMonitor monitor) {
		ScaModelCommand.execute(device, new ScaModelCommand() {

			@Override
			public void execute() {
				if (isEnabled()) {
					updateTunerContainer();
				}
			}
		});
		return super.refresh(monitor);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		if (enabled) {
			fetchAndPopulate.schedule();
		} else {
			ScaModelCommand.execute(device, new ScaModelCommand() {

				@Override
				public void execute() {
					device.eAdapters().remove(deviceAdapter);
					removeTunerContainer();
				}
			});
		}
	}

	private boolean calculateSupport() {
		EList<Interface> interfaceList = ScaEcoreUtils.getFeature(device, FrontEndDataProvider.PATH);
		if (interfaceList != null) {
			for (Interface i : interfaceList) {
				// Check to see if this is a frontEndInterface
				if (i.getRepid().equals(DigitalTunerHelper.id())) {
					return true;
				}
			}
		}
		return false;
	}

	private void updateTunerContainer() {
		if (isDisposed()) {
			removeTunerContainer();
			return;
		}
		if (!supportsFei || device.isDisposed()) {
			removeTunerContainer();
			return;
		}

		if (device.getFeatureData().get(TunerUtils.TUNER_CONTAINER_ID) != container) {
			device.getFeatureData().put(TunerUtils.TUNER_CONTAINER_ID, container);
		}

		// Fetch list of tuners
		ScaStructSequenceProperty prop = (ScaStructSequenceProperty) device.getProperty(StatusProperties.FRONTEND_TUNER_STATUS.getId());
		if (prop == null) {
			FrontEndDataActivator plugin = FrontEndDataActivator.getInstance();
			if (plugin != null) {
				plugin.getLog().log(
					new Status(IStatus.ERROR, "Device " + device.getIdentifier() + " is not a valid front end device, missing property: "
							+ StatusProperties.FRONTEND_TUNER_STATUS.getId(), null));
			}
			return;
		}

		int numCurrent = container.getTunerStatus().size();
		int newSize = prop.getStructs().size();

		int numRemove = numCurrent - newSize;
		int numAdd = newSize - numCurrent;

		// Remove old
		for (int i = 0; i < numRemove; i++) {
			TunerStatus removed = container.getTunerStatus().remove(container.getTunerStatus().size() - 1);
			if (removed != null) {
				removed.setTunerStatusStruct(null);
			}
		}

		// Add new
		for (int i = 0; i < numAdd; i++) {
			final TunerStatus tuner = FrontendFactory.eINSTANCE.createTunerStatus();
			tuner.eAdapters().add(tunerAdapter);
			container.getTunerStatus().add(tuner);
		}

		Map<String, Integer> tunerTypeMap = new HashMap<String, Integer>();
		// Update all
		for (int i = 0; i < container.getTunerStatus().size(); i++) {
			TunerStatus tuner = container.getTunerStatus().get(i);
			if (tuner.getTunerStatusStruct() != prop.getStructs().get(i)) {
				tuner.setTunerStatusStruct(prop.getStructs().get(i));
			}
			for (ScaSimpleProperty simple : tuner.getSimples()) {
				TunerStatusAllocationProperties statusProp = TunerStatusAllocationProperties.fromPropID(simple.getId());
				TunerStatusAllocationProperties.updateTunerStatusValue(tuner, statusProp, simple.getValue());
			}
			updateAllocationListeners(tuner);
			boolean allocated = tuner.isAllocated();
			if (!allocated) {
				int current;
				if (tunerTypeMap.containsKey(tuner.getTunerType())) {
					current = tunerTypeMap.get(tuner.getTunerType());
				} else {
					current = 0;
				}
				tunerTypeMap.put(tuner.getTunerType(), current + 1);
			}
		}

		Map<String, UnallocatedTunerContainer> current = new HashMap<String, UnallocatedTunerContainer>();
		for (UnallocatedTunerContainer c : container.getUnallocatedContainer()) {
			current.put(c.getTunerType(), c);
		}

		Map<String, UnallocatedTunerContainer> remove = new HashMap<String, UnallocatedTunerContainer>(current);

		Map<String, UnallocatedTunerContainer> newItems = new HashMap<String, UnallocatedTunerContainer>();
		for (String s : tunerTypeMap.keySet()) {
			UnallocatedTunerContainer newC = FrontendFactory.eINSTANCE.createUnallocatedTunerContainer();
			newC.setTunerType(s);
			newItems.put(s, newC);
		}

		remove.keySet().removeAll(newItems.keySet());
		newItems.keySet().removeAll(current.keySet());

		for (UnallocatedTunerContainer i : container.getUnallocatedContainer()) {
			Integer count = tunerTypeMap.get(i.getTunerType());
			if (count == null) {
				count = 0;
			}
			i.setCount(count);
		}

		if (!remove.isEmpty()) {
			container.getUnallocatedContainer().removeAll(remove.values());
		}
		if (!newItems.keySet().isEmpty()) {
			container.getUnallocatedContainer().addAll(newItems.values());
		}
	}

	private void updateAllocationListeners(final TunerStatus tuner) {
		ScaSimpleProperty allocSimple = tuner.getSimple(StatusProperties.ALLOCATION_ID_CSV.getId());
		if (allocSimple == null) {
			tuner.getListenerAllocations().clear();
			return;
		}

		Object allocationValue = allocSimple.getValue();
		Map<String, ListenerAllocation> currentListener = new HashMap<String, ListenerAllocation>();
		for (ListenerAllocation a : tuner.getListenerAllocations()) {
			currentListener.put(a.getListenerID(), a);
		}

		Map<String, ListenerAllocation> listenerToRemove = new HashMap<String, ListenerAllocation>(currentListener);

		Map<String, ListenerAllocation> listenersToAdd = new HashMap<String, ListenerAllocation>();
		if (allocationValue == null) {
			allocationValue = "";
		}
		String allocationIDs = allocationValue.toString();
		String[] allocations = allocationIDs.split(",");
		boolean isControl = true;
		for (String allocationId : allocations) {
			if ("".equals(allocationId)) {
				continue;
			}
			if (isControl) {
				isControl = false;
				continue;
			}
			ListenerAllocation allocation = FrontendFactory.eINSTANCE.createListenerAllocation();
			allocation.setListenerID(allocationId);
			listenersToAdd.put(allocationId, allocation);
		}

		listenerToRemove.keySet().removeAll(listenersToAdd.keySet());

		listenersToAdd.keySet().removeAll(currentListener.keySet());

		if (!listenerToRemove.isEmpty()) {
			tuner.getListenerAllocations().removeAll(listenerToRemove.values());
		}
		if (!listenersToAdd.isEmpty()) {
			tuner.getListenerAllocations().addAll(listenersToAdd.values());
		}
	}
	
	@Override
	public String getID() {
		return FrontEndDataProviderFactory.ID;
	}

}

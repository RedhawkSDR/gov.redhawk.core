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
package gov.redhawk.frontend.ui.internal;

import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.ui.internal.section.FrontendSection;
import gov.redhawk.frontend.util.TunerProperties.ListenerAllocationProperties;
import gov.redhawk.frontend.util.TunerProperties.TunerAllocationProperties;
import gov.redhawk.frontend.util.TunerUtils;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.util.CorbaUtils;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import CF.DataType;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;

/**
 * 
 */
public class DeallocateAction extends FrontendAction {

	public DeallocateAction(FrontendSection theSection) {
		super(theSection, "Deallocate", "gov.redhawk.frontend.actions.deallocate", "gov.redhawk.frontend.commands.deallocate",
			"icons/deallocate.gif");
	}
	
	@Override
	public void run() {
		EObject obj = getSection().getInput();
		boolean removeSelection = true;
		if (obj instanceof TunerStatus) {
			TunerStatus tuner = (TunerStatus) obj;
			if (tuner.getTunerContainer() == null) {
				// already deallocated, probably still in a pinned properties view
				return;
			}
			boolean proceed = true;
			if (tuner.getAllocationID() != null && !tuner.getAllocationID().isEmpty() 
					&& tuner.getAllocationID().contains(",")) {
				if (confirmDeallocate(tuner) == 0) {
					proceed = false;
					removeSelection = false;
				}
			}
			if (proceed) {
				deallocateTuner(tuner);
			}
		}
		if (obj instanceof TunerContainer) {
			TunerContainer container = (TunerContainer) obj;
			if (!confirmDeallocate(container)) {
				return;
			}
			for (TunerStatus tuner : container.getTunerStatus().toArray(new TunerStatus[0])) {
				deallocateTuner(tuner);
			}
			removeSelection = false;
		}
		if (obj instanceof ScaDevice) {
			ScaDevice< ? > device = (ScaDevice< ? >) obj;
			TunerContainer container = TunerUtils.INSTANCE.getTunerContainer(device);
			if (!confirmDeallocate(container)) {
				return;
			}
			for (TunerStatus tuner : container.getTunerStatus().toArray(new TunerStatus[0])) {
				deallocateTuner(tuner);
			}
		}
		if (obj instanceof ListenerAllocation) {
			final ListenerAllocation listener = (ListenerAllocation) obj;
			if (listener.getTunerStatus() == null) {
				// already deallocated, probably still in a pinned properties view
				return;
			}
			final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(listener, ScaDevice.class);
			if (device == null) {
				return;
			}
			final DataType[] props = new DataType[1];
			DataType dt = new DataType();
			dt.id = "FRONTEND::listener_allocation";
			dt.value = getListenerAllocationStruct(listener).toAny();
			props[0] = dt;

			Job job = new Job("Deallocate FEI control") {

				@Override
				protected IStatus run(IProgressMonitor monitor) {
					try {
						monitor.beginTask("Deallocating", IProgressMonitor.UNKNOWN);
						CorbaUtils.invoke(new Callable<Object>() {

							@Override
							public Object call() throws Exception {
								try {
									device.deallocateCapacity(props);
								} catch (InvalidCapacity e) {
									throw new CoreException(new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid Capacity in control deallocation: " + e.msg, e));
								} catch (InvalidState e) {
									throw new CoreException(new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid State in control deallocation: " + e.msg, e));
								} 
								return null;
							}
							
						}, monitor);
						
						device.refresh(null, RefreshDepth.SELF);
					} catch (InterruptedException e) {
						return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Interrupted Exception during control deallocation", e);
					} catch (CoreException e) {
						return new Status(e.getStatus().getSeverity(), FrontEndUIActivator.PLUGIN_ID, "Failed to deallocate.", e);
					}

					final TunerStatus tunerStatus = listener.getTunerStatus();
					if (tunerStatus != null) {
						ScaModelCommand.execute(tunerStatus, new ScaModelCommand() {
							@Override
							public void execute() {
								tunerStatus.getListenerAllocations().remove(listener);
							}
						});
					}
					return Status.OK_STATUS;
				}

			};
			job.setUser(true);
			job.schedule();
		}
		if (removeSelection) {
			getSection().unsetPageSelection();
		}
	}

	private int confirmDeallocate(TunerStatus tuner) {
		MessageDialog warning = new MessageDialog(Display.getCurrent().getActiveShell(), "Deallocation Warning", null,
			"Some selected tuners have listeners.  Deallocating them will also deallocate all of their listeners.  Deallocate them anyway?", 
			MessageDialog.WARNING, new String[] { "Cancel", "Yes" }, 0);
		return warning.open();
	}
	
	private boolean confirmDeallocate(TunerContainer container) {
		for (TunerStatus tuner : container.getTunerStatus()) {
			if (tuner.getAllocationID() != null && !tuner.getAllocationID().isEmpty() 
					&& tuner.getAllocationID().contains(",")) {
				int response = confirmDeallocate(tuner);
				if (response == 0) {
					return false;
				}
				return true;
			}
		}
		return true;
	}
	
	private boolean deallocateTuner(TunerStatus tuner) {
		final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
		final DataType[] props = createAllocationProperties(tuner);

		Job job = new Job("Deallocate FEI control") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					monitor.beginTask("Deallocating", IProgressMonitor.UNKNOWN);
					device.deallocateCapacity(props);
					device.refresh(null, RefreshDepth.SELF);
				} catch (InvalidCapacity e) {
					return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid Capacity in control deallocation: " + e.msg, e);
				} catch (InvalidState e) {
					return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid State in control deallocation: " + e.msg, e);
				} catch (InterruptedException e) {
					return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Interrupted Exception during control deallocation", e);
				}
				return Status.OK_STATUS;
			}

		};
		job.setUser(true);
		job.schedule();

		return true;
	}

	private DataType[] createAllocationProperties(TunerStatus tuner) {
		List<DataType> props = new ArrayList<DataType>();
		ScaStructProperty struct;
		DataType dt = new DataType();
		struct = getTunerAllocationStruct(tuner);
		dt.id = "FRONTEND::tuner_allocation";
		dt.value = struct.toAny();
		props.add(dt);
		return props.toArray(new DataType[0]);
	}

	private ScaStructProperty getTunerAllocationStruct(TunerStatus tuner) {
		ScaStructProperty tunerAllocationStruct = ScaFactory.eINSTANCE.createScaStructProperty();
		TunerAllocationProperties allocPropID = TunerAllocationProperties.valueOf("ALLOCATION_ID");
		ScaSimpleProperty simple = ScaFactory.eINSTANCE.createScaSimpleProperty();
		Simple definition = (Simple) PrfFactory.eINSTANCE.create(PrfPackage.Literals.SIMPLE);
		definition.setType(allocPropID.getType());
		definition.setId(allocPropID.getType().getLiteral());
		definition.setName(allocPropID.getType().getName());
		simple.setDefinition(definition);
		simple.setId(allocPropID.getId());
		setValueForProp(tuner, allocPropID, simple);
		tunerAllocationStruct.getSimples().add(simple);
		return tunerAllocationStruct;
	}

	private ScaStructProperty getListenerAllocationStruct(ListenerAllocation listener) {
		ScaStructProperty listenerAllocationStruct = ScaFactory.eINSTANCE.createScaStructProperty();
		ListenerAllocationProperties allocPropID = ListenerAllocationProperties.LISTENER_ALLOCATION_ID;
		ScaSimpleProperty simple = ScaFactory.eINSTANCE.createScaSimpleProperty();
		Simple definition = (Simple) PrfFactory.eINSTANCE.create(PrfPackage.Literals.SIMPLE);
		definition.setType(allocPropID.getType());
		definition.setId(allocPropID.getType().getLiteral());
		definition.setName(allocPropID.getType().getName());
		simple.setDefinition(definition);
		simple.setId(allocPropID.getId());
		simple.setValue(listener.getListenerID());
		listenerAllocationStruct.getSimples().add(simple);
		return listenerAllocationStruct;
	}

	private void setValueForProp(TunerStatus tuner, TunerAllocationProperties allocPropID, ScaSimpleProperty simple) {
		// Deallocates control id and all listeners
		String value = tuner.getTunerStatusStruct().getSimple("FRONTEND::tuner_status::allocation_id_csv").getValue().toString();
		int endControlIndex = value.indexOf(',');
		if (endControlIndex > 0) {
			value = value.substring(0, endControlIndex);
		}
		simple.setValue(value);
	}
}

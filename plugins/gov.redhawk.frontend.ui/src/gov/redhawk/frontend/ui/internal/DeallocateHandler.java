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
package gov.redhawk.frontend.ui.internal;

import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.internal.section.FrontendSection;
import gov.redhawk.frontend.util.TunerProperties.ListenerAllocationProperties;
import gov.redhawk.frontend.util.TunerProperties.TunerAllocationProperties;
import gov.redhawk.frontend.util.TunerUtils;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.model.jobs.DeallocateJob;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import CF.DataType;

public class DeallocateHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		}
		if (selection == null) {
			return null;
		}

		boolean removeSelection = true;
		Object obj = selection.getFirstElement();
		if (obj instanceof TunerStatus) {
			TunerStatus tuner = (TunerStatus) obj;
			if (tuner.getTunerContainer() == null) {
				// already deallocated, probably still in a pinned properties view
				return null;
			}

			// If there are listeners, the user must acknowledge deallocation
			if (hasListeners(tuner) && !confirmDeallocate(tuner, event)) {
				return null;
			}

			deallocateTuner(tuner);
		} else  if (obj instanceof TunerContainer) {
			TunerContainer container = (TunerContainer) obj;

			// User must confirm bulk deallocation
			if (!confirmDeallocate(container, event)) {
				return null;
			}

			for (TunerStatus tuner : container.getTunerStatus().toArray(new TunerStatus[0])) {
				deallocateTuner(tuner);
			}
			removeSelection = false;
		} else if (obj instanceof ScaDevice) {
			ScaDevice< ? > device = (ScaDevice< ? >) obj;
			TunerContainer container = TunerUtils.INSTANCE.getTunerContainer(device);

			// User must confirm bulk deallocation
			if (!confirmDeallocate(container, event)) {
				return null;
			}

			for (TunerStatus tuner : container.getTunerStatus().toArray(new TunerStatus[0])) {
				deallocateTuner(tuner);
			}
		} else if (obj instanceof ListenerAllocation) {
			final ListenerAllocation listener = (ListenerAllocation) obj;
			if (listener.getTunerStatus() == null) {
				// already deallocated, probably still in a pinned properties view
				return null;
			}
			final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(listener, ScaDevice.class);
			if (device == null) {
				return null;
			}

			DataType dt = new DataType();
			dt.id = "FRONTEND::listener_allocation";
			dt.value = getListenerAllocationStruct(listener).toAny();

			Job job = new DeallocateJob(device, dt);
			job.setName("Deallocate FEI listener");
			job.setUser(true);
			job.addJobChangeListener(new JobChangeAdapter() {
				@Override
				public void done(IJobChangeEvent event) {
					IStatus result = event.getResult();
					if (result == null || !result.isOK()) {
						return;
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
				}
			});
			job.schedule();
		}

		// If called from toolbar button, we must unset the property page's selection to clear it
		Object section = ((IEvaluationContext) event.getApplicationContext()).getVariable("gov.redhawk.frontend.propertySection");
		if (section instanceof FrontendSection && removeSelection) {
			FrontendSection feSection = (FrontendSection) section;
			feSection.unsetPageSelection();
		}
		return null;
	}

	/**
	 * Return true if there are any listeners
	 * @param tuner
	 * @return
	 */
	private boolean hasListeners(TunerStatus tuner) {
		return tuner.getAllocationID() != null && tuner.getAllocationID().indexOf(',') != -1;
	}

	private boolean confirmDeallocate(TunerStatus tuner, ExecutionEvent event) {
		MessageDialog warning = new MessageDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Deallocation Warning", null,
			"The selected tuner has listener(s).  Deallocating the tuner will also deallocate the listener(s). Are you sure?",
			MessageDialog.WARNING, new String[] { "Cancel", "Yes" }, 0);
		return warning.open() == 1;
	}

	private boolean confirmDeallocate(TunerContainer container, ExecutionEvent event) {
		int allocations = container.getTunerStatus().size();
		String msg = String.format("The selected tuner container has %d allocation(s). Are you sure you want to deallocate all?", allocations);
		MessageDialog warning = new MessageDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Deallocation Warning", null, msg,
			MessageDialog.WARNING, new String[] { "Cancel", "Yes" }, 0);
		return warning.open() == 1;
	}

	private void deallocateTuner(TunerStatus tuner) {
		final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
		final DataType prop = createAllocationProperties(tuner);

		Job job = new DeallocateJob(device, prop);
		job.setName("Deallocate FEI control");
		job.setUser(true);
		job.schedule();
	}

	private DataType createAllocationProperties(TunerStatus tuner) {
		DataType dt = new DataType();
		ScaStructProperty struct = getTunerAllocationStruct(tuner);
		dt.id = "FRONTEND::tuner_allocation";
		dt.value = struct.toAny();
		return dt;
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
		tunerAllocationStruct.getFields().add(simple);
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
		listenerAllocationStruct.getFields().add(simple);
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

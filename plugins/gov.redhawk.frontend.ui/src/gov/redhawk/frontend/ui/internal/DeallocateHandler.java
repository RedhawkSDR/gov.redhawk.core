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
import gov.redhawk.frontend.edit.utils.TunerProperties.ListenerAllocationProperties;
import gov.redhawk.frontend.edit.utils.TunerProperties.TunerAllocationProperties;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.ui.internal.section.FrontendSection;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.handlers.HandlerUtil;

import CF.DataType;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;

/**
 *
 */
public class DeallocateHandler extends AbstractHandler implements IHandler {

	private TunerStatus tuner;

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		}

		if (selection == null) {
			return null;
		}
		Object obj = selection.getFirstElement();
		if (obj instanceof TunerStatus) {
			TunerStatus tuner = (TunerStatus) obj;
			deallocateTuner(tuner, event);
		}
		if (obj instanceof TunerContainer) {
			TunerContainer container = (TunerContainer) obj;
			for (TunerStatus tuner : container.getTunerStatus()) {
				String allocationID = tuner.getAllocationID();
				if (!(allocationID == null || "".equals(allocationID))) {
					deallocateTuner(tuner, event);
				}
			}
		}
		if (obj instanceof ListenerAllocation) {
			final ListenerAllocation listener = (ListenerAllocation) obj;
			final ScaDevice< ? > device = listener.getTunerStatus().getTunerContainer().getModelDevice().getScaDevice();
			final DataType[] props = new DataType[1];
			DataType dt = new DataType();
			dt.id = "FRONTEND::listener_allocation";
			dt.value = getListenerAllocationStruct(listener).toAny();
			props[0] = dt;

			Job job = new Job("Deallocate FEI control") {

				@Override
				protected IStatus run(IProgressMonitor monitor) {
					try {
						monitor.beginTask("Deallocating",IProgressMonitor.UNKNOWN);
						device.deallocateCapacity(props);
					} catch (InvalidCapacity e) {
						return new Status(Status.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalide Capacity in control deallocation: " + e.msg, e);
					} catch (InvalidState e) {
						return new Status(Status.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalide State in control deallocation: " + e.msg, e);
					}
					
					ScaModelCommand.execute(listener.getTunerStatus(), new ScaModelCommand() {
						@Override
						public void execute() {
							listener.getTunerStatus().getListenerAllocations().remove(listener);
						}
					});
					return Status.OK_STATUS;
				}

			};
			job.setUser(true);
			job.schedule();
		}
		// If called from toolbar button, we must unset the property page's selection to clear it
		Object section = ((IEvaluationContext) event.getApplicationContext()).getVariable("gov.redhawk.frontend.propertySection");
		if (section != null) {
			FrontendSection feSection = (FrontendSection) section;
			feSection.unsetPageSelection();
		}
		return null;
	}

	private void deallocateTuner(TunerStatus tuner, ExecutionEvent event) {
		if (tuner.getAllocationID().contains(",")) {
			MessageBox warning = new MessageBox(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), SWT.ICON_WARNING | SWT.CANCEL | SWT.OK);
			warning.setText("Deallocation Warning");
			warning.setMessage("Deallocating a tuner will also deallocate all of its listeners.  Proceed?");
			if (warning.open() == SWT.CANCEL) {
				return;
			}
		}
		final ScaDevice< ? > device = tuner.getTunerContainer().getModelDevice().getScaDevice();
		final DataType[] props = createAllocationProperties(tuner);

		Job job = new Job("Deallocate FEI control") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					monitor.beginTask("Deallocating",IProgressMonitor.UNKNOWN);
					device.deallocateCapacity(props);
				} catch (InvalidCapacity e) {
					return new Status(Status.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalide Capacity in control deallocation: " + e.msg, e);
				} catch (InvalidState e) {
					return new Status(Status.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalide State in control deallocation: " + e.msg, e);
				}
				return Status.OK_STATUS;
			}

		};
		job.setUser(true);
		job.schedule();
		
	}
	
	private DataType[] createAllocationProperties(TunerStatus tuner) {
		this.tuner = tuner;
		List<DataType> props = new ArrayList<DataType>();
		ScaStructProperty struct;
		DataType dt = new DataType();
		struct = getTunerAllocationStruct();
		dt.id = "FRONTEND::tuner_allocation";
		dt.value = struct.toAny();
		props.add(dt);
		return props.toArray(new DataType[0]);
	}

	private ScaStructProperty getTunerAllocationStruct() {
		ScaStructProperty tunerAllocationStruct = ScaFactory.eINSTANCE.createScaStructProperty();
		TunerAllocationProperties allocPropID = TunerAllocationProperties.valueOf("ALLOCATION_ID");
		ScaSimpleProperty simple = ScaFactory.eINSTANCE.createScaSimpleProperty();
		Simple definition = (Simple) PrfFactory.eINSTANCE.create(PrfPackage.Literals.SIMPLE);
		definition.setType(allocPropID.getType());
		definition.setId(allocPropID.getType().getLiteral());
		definition.setName(allocPropID.getType().getName());
		simple.setDefinition(definition);
		simple.setId(allocPropID.getId());
		setValueForProp(allocPropID, simple);
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

	private void setValueForProp(TunerAllocationProperties allocPropID, ScaSimpleProperty simple) {
		// Deallocates control id and all listeners
		String value = tuner.getTunerStatusStruct().getSimple("FRONTEND::tuner_status::allocation_id_csv").getValue().toString();
		int endControlIndex = value.indexOf(',');
		if (endControlIndex > 0) {
			value = value.substring(0, endControlIndex);
		}
		simple.setValue(value);
	}

}

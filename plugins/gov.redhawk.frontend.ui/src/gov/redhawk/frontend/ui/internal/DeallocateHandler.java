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

import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.edit.utils.TunerProperties.TunerAllocationProperties;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
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

		// This check is used to tie the allocation wizard into the view toolbar buttons
		if (selection == null || selection.getFirstElement() == null) {
			TunerStatus currentSelection = FrontEndContentProvider.getCurrentSelection();
			if (currentSelection != null) {
				selection = new StructuredSelection(new Object[] { currentSelection });
			}
			if (currentSelection.getAllocationID() == null || currentSelection.getAllocationID().equals("")) {
				Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
				MessageBox alreadyAllocated = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
				alreadyAllocated.setMessage("Tuner has not been allocated");
				alreadyAllocated.setText("Deallocation Failed");
				alreadyAllocated.open();
				return null;
			}
		}

		if (selection == null) {
			return null;
		}
		Object obj = selection.getFirstElement();
		if (obj instanceof TunerStatus) {
			TunerStatus tuner = (TunerStatus) obj;
			ScaDevice< ? > device = tuner.getTunerContainer().getModelDevice().getScaDevice();
			DataType[] props = createAllocationProperties(tuner);
			try {
				device.deallocateCapacity(props);
			} catch (InvalidCapacity e) {
				e.printStackTrace();
			} catch (InvalidState e) {
				e.printStackTrace();
			}
			//TODO: Unset all the properties of the Tuner
		}
		if (obj instanceof TunerContainer) {
			TunerContainer container = (TunerContainer) obj;
			for (TunerStatus tuner : container.getTunerStatus()) {
				String allocationID = tuner.getAllocationID();
				if (!(allocationID == null || "".equals(allocationID))) {
					//TODO: deallocate tuner
				}
			}
		}
		return null;
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


	private void setValueForProp(TunerAllocationProperties allocPropID, ScaSimpleProperty simple) {
		// Deallocates control id and all listeners
		String value = tuner.getTunerStatusStruct().getSimple("FRONTEND::tuner_status::allocation_id_csv").getValue().toString();
		int endControlIndex = value.indexOf(',');
		if (endControlIndex > 0 ) {
			value = value.substring(0, endControlIndex);
		}
		simple.setValue(value);
	}
	

}

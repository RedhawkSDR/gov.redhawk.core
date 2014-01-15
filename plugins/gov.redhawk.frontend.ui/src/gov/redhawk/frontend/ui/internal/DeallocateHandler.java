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

import gov.redhawk.frontend.Tuner;
import gov.redhawk.frontend.TunerContainer;

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

/**
 *
 */
public class DeallocateHandler extends AbstractHandler implements IHandler {

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
			Tuner currentSelection = FrontEndContentProvider.getCurrentSelection();
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
		if (obj instanceof Tuner) {
			//			Tuner tuner = (Tuner) obj;
			//TODO: Unset all the properties of the Tuner
			//			tuner.setAllocationID(null);
		}
		if (obj instanceof TunerContainer) {
			TunerContainer container = (TunerContainer) obj;
			for (Tuner tuner : container.getTuners()) {
				String allocationID = tuner.getAllocationID();
				if (!(allocationID == null || "".equals(allocationID))) {
					//TODO: deallocate tuner
				}
			}
		}
		return null;
	}

}

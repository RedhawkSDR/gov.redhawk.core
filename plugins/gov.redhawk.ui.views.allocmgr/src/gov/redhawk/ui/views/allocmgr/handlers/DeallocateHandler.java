/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.ui.views.allocmgr.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import gov.redhawk.ui.views.allocmgr.AllocationStatus;
import gov.redhawk.ui.views.allocmgr.ScaAllocationManager;
import gov.redhawk.ui.views.allocmgr.jobs.DeallocateJob;

public class DeallocateHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection ss = HandlerUtil.getCurrentStructuredSelection(event);
		ScaAllocationManager allocMgr = (ScaAllocationManager) ((AllocationStatus) ss.getFirstElement()).eContainer();
		List<String> allocationIDs = new ArrayList<>();
		for (Iterator< ? > iter = ss.iterator(); iter.hasNext();) {
			AllocationStatus status = (AllocationStatus) iter.next();
			allocationIDs.add(status.getAllocationID());
		}

		// De-allocate
		Job job = new DeallocateJob(allocMgr, allocationIDs);
		job.setUser(true);
		job.schedule();

		return null;
	}
}

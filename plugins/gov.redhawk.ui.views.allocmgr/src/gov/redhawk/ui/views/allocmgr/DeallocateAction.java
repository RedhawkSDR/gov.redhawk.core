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
package gov.redhawk.ui.views.allocmgr;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.swt.widgets.TreeItem;

import gov.redhawk.ui.views.allocmgr.jobs.DeallocateJob;

public class DeallocateAction extends Action {

	private XViewer xviewer;

	public DeallocateAction(XViewer xviewer) {
		super("Deallocate");
		this.xviewer = xviewer;
	}

	@Override
	public void run() {
		TreeItem treeItem = xviewer.getRightClickSelectedItem();
		AllocationStatus status = (AllocationStatus) treeItem.getData();
		ScaAllocationManager allocMgr = (ScaAllocationManager) status.eContainer();
		String[] allocationIDs = new String[] { status.getAllocationID() };

		// De-allocate
		Job job = new DeallocateJob(allocMgr, allocationIDs);
		job.setUser(true);
		job.schedule();
	}

}

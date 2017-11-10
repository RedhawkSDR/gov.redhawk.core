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
package gov.redhawk.ui.views.allocmgr.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.omg.CORBA.SystemException;

import CF.AllocationManagerPackage.InvalidAllocationId;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.ui.views.allocmgr.AllocMgrPlugin;
import gov.redhawk.ui.views.allocmgr.ScaAllocationManager;
import mil.jpeojtrs.sca.util.CFErrorFormatter;

public class DeallocateJob extends Job {

	private ScaAllocationManager allocMgr;
	private List<String> allocationIDs;

	public DeallocateJob(ScaAllocationManager allocMgr, List<String> allocationIDs) {
		super("Deallocate");
		this.allocMgr = allocMgr;
		this.allocationIDs = allocationIDs;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, 2);

		try {
			String[] param = allocationIDs.toArray(new String[allocationIDs.size()]);
			allocMgr.deallocate(param);
			progress.worked(1);
		} catch (SystemException e) {
			return new Status(IStatus.ERROR, AllocMgrPlugin.ID, "Unable to deallocate", e);
		} catch (InvalidAllocationId e) {
			return new Status(IStatus.ERROR, AllocMgrPlugin.ID, CFErrorFormatter.format(e), e);
		}

		try {
			allocMgr.refresh(progress.split(1), RefreshDepth.CHILDREN);
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		}

		return Status.OK_STATUS;
	}

}

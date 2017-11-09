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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.omg.CORBA.SystemException;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.ui.views.allocmgr.AllocMgrPlugin;
import gov.redhawk.ui.views.allocmgr.ScaAllocationManager;

public class FetchAllocationManagerJob extends Job {

	private ScaDomainManager domMgr;
	private ScaAllocationManager allocMgr;

	public FetchAllocationManagerJob(ScaDomainManager domMgr, ScaAllocationManager allocMgr) {
		super("Get allocation manager");
		this.domMgr = domMgr;
		this.allocMgr = allocMgr;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, 1);

		CF.AllocationManager allocMgrObj;
		try {
			allocMgrObj = domMgr.allocationMgr();
			progress.worked(1);
		} catch (SystemException e) {
			return new Status(IStatus.ERROR, AllocMgrPlugin.ID, "Unable to get allocation manager", e);
		}

		allocMgr.setCorbaObj(allocMgrObj);
		return Status.OK_STATUS;
	}

}

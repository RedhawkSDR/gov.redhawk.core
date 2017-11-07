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

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.swt.widgets.Display;
import org.omg.CORBA.SystemException;

import CF.AllocationManagerPackage.AllocationStatusType;
import CF.AllocationManagerPackage.InvalidAllocationId;
import gov.redhawk.model.sca.DomainConnectionState;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.sca.util.IPreferenceAccessor;
import gov.redhawk.sca.util.ScopedPreferenceAccessor;

public class FetchAllocationsJob extends Job {

	private ScaDomainManager domMgr;
	private AllocationManager allocMgr;
	private boolean shouldRun = true;

	public FetchAllocationsJob(ScaDomainManager domMgr, AllocationManager allocMgr) {
		super("Fetch allocations");
		this.domMgr = domMgr;
		this.allocMgr = allocMgr;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, IProgressMonitor.UNKNOWN);

		if (domMgr.isDisposed()) {
			shouldRun = false;
			return Status.CANCEL_STATUS;
		}
		if (!DomainConnectionState.CONNECTED.equals(domMgr.getState())) {
			schedule(getDelay());
			return Status.OK_STATUS;
		}

		// Perform CORBA call to get allocations
		AllocationStatusType[] cfAllocStatuses;
		try {
			cfAllocStatuses = domMgr.allocationMgr().allocations(new String[0]);
		} catch (InvalidAllocationId | SystemException e) {
			schedule(getDelay());
			return Status.OK_STATUS;
		}

		if (progress.isCanceled()) {
			return Status.CANCEL_STATUS;
		}

		// Existing allocations
		Map<String, AllocationStatus> existingStatuses = new HashMap<>();
		for (AllocationStatus status : allocMgr.getStatus()) {
			existingStatuses.put(status.getAllocationID(), status);
		}

		// Determine what has been removed, what is new
		Set<AllocationStatus> statusesToRemove = new HashSet<>();
		statusesToRemove.addAll(allocMgr.getStatus());
		List<AllocationStatus> newStatuses = new LinkedList<>();
		for (AllocationStatusType cfAllocStatus : cfAllocStatuses) {
			String allocId = cfAllocStatus.allocationID;
			if (existingStatuses.containsKey(allocId)) {
				// Allocation doesn't need to be removed, it's still present
				statusesToRemove.remove(existingStatuses.get(allocId));
			} else {
				AllocationStatus status = AllocMgrFactory.eINSTANCE.createAllocationStatus(cfAllocStatus);
				newStatuses.add(status);
			}
		}

		if (progress.isCanceled()) {
			return Status.CANCEL_STATUS;
		}

		// Perform removals / additions in UI thread
		Display.getDefault().asyncExec(() -> {
			if (progress.isCanceled()) {
				return;
			}
			allocMgr.getStatus().removeAll(statusesToRemove);
			allocMgr.getStatus().addAll(newStatuses);
		});

		if (progress.isCanceled()) {
			return Status.CANCEL_STATUS;
		}

		schedule(getDelay());
		return Status.OK_STATUS;
	}

	@Override
	protected void canceling() {
		shouldRun = false;
	}

	@Override
	public boolean shouldRun() {
		return shouldRun;
	}

	private long getDelay() {
		IPreferenceAccessor preferences = new ScopedPreferenceAccessor(InstanceScope.INSTANCE, "gov.redhawk.sca.model.provider.refresh"); //$NON-NLS-1$
		return preferences.getLong("refreshInterval"); //$NON-NLS-1$
	}
}

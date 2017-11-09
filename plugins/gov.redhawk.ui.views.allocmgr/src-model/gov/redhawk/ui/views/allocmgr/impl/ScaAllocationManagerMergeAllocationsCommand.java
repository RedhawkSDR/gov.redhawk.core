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
package gov.redhawk.ui.views.allocmgr.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Status;

import CF.AllocationManagerPackage.AllocationStatusType;
import gov.redhawk.model.sca.commands.SetStatusCommand;
import gov.redhawk.ui.views.allocmgr.AllocMgrFactory;
import gov.redhawk.ui.views.allocmgr.AllocMgrPackage;
import gov.redhawk.ui.views.allocmgr.AllocationStatus;
import gov.redhawk.ui.views.allocmgr.ScaAllocationManager;

public class ScaAllocationManagerMergeAllocationsCommand extends SetStatusCommand<ScaAllocationManager> {

	private AllocationStatusType[] allocStatuses;

	public ScaAllocationManagerMergeAllocationsCommand(ScaAllocationManager provider, AllocationStatusType[] allocStatuses) {
		super(provider, AllocMgrPackage.Literals.SCA_ALLOCATION_MANAGER__ALLOCATIONS, Status.OK_STATUS);
		this.allocStatuses = allocStatuses;
	}

	@Override
	public void execute() {
		// Existing allocation statuses by allocation ID
		Map<String, AllocationStatus> existingAllocStatuses = new HashMap<>();
		for (AllocationStatus status : provider.getAllocations()) {
			existingAllocStatuses.put(status.getAllocationID(), status);
		}

		// Determine which have been removed, which need to be added
		Set<AllocationStatus> allocStatusesToRemove = new HashSet<>();
		allocStatusesToRemove.addAll(provider.getAllocations());
		List<AllocationStatus> newAllocStatuses = new LinkedList<>();
		for (AllocationStatusType allocStatus : allocStatuses) {
			String allocId = allocStatus.allocationID;
			if (!allocStatusesToRemove.remove(existingAllocStatuses.get(allocId))) {
				AllocationStatus status = AllocMgrFactory.eINSTANCE.createAllocationStatus(allocStatus);
				newAllocStatuses.add(status);
			}
		}

		provider.getAllocations().removeAll(allocStatusesToRemove);
		provider.getAllocations().addAll(newAllocStatuses);
		super.execute();
	}

}

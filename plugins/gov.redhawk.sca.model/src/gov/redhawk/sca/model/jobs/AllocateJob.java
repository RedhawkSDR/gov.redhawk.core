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
package gov.redhawk.sca.model.jobs;

import java.util.concurrent.ExecutionException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;

import CF.DataType;
import CF.DevicePackage.InsufficientCapacity;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaModelPlugin;
import mil.jpeojtrs.sca.util.CFErrorFormatter;
import mil.jpeojtrs.sca.util.CorbaUtils2;

/**
 * @since 21.1
 */
public class AllocateJob extends Job {

	private ScaDevice< ? > device;
	private DataType[] allocation;
	private String label;

	public AllocateJob(ScaDevice< ? > device, DataType... allocation) {
		super("Allocating");
		this.device = device;
		this.allocation = allocation;
		if (this.device.isSetLabel()) {
			this.label = "device " + this.device.getLabel();
		} else {
			this.label = "device";
		}
	}

	@Override
	public IStatus run(IProgressMonitor monitor) {
		final int WORK_ALLOCATE = 9;
		final int WORK_REFRESH = 1;
		SubMonitor progress = SubMonitor.convert(monitor, "Allocating", WORK_ALLOCATE + WORK_REFRESH);

		try {
			// Attempt the allocation
			IStatus result = CorbaUtils2.invoke(() -> {
				try {
					if (device.allocateCapacity(allocation)) {
						return Status.OK_STATUS;
					} else {
						return new Status(IStatus.ERROR, ScaModelPlugin.ID, "Allocation failed. Device returned 'false' to allocation requestion.");
					}
				} catch (InvalidCapacity e) {
					return new Status(IStatus.ERROR, ScaModelPlugin.ID, CFErrorFormatter.format(e, label), e);
				} catch (InvalidState e) {
					return new Status(IStatus.ERROR, ScaModelPlugin.ID, CFErrorFormatter.format(e, label), e);
				} catch (InsufficientCapacity e) {
					return new Status(IStatus.ERROR, ScaModelPlugin.ID, CFErrorFormatter.format(e, label), e);
				}
			}, progress.newChild(WORK_ALLOCATE));
			if (!result.isOK()) {
				return result;
			}
		} catch (ExecutionException e) {
			return new Status(IStatus.ERROR, ScaModelPlugin.ID, "Failed to allocate", e);
		}

		// Refresh the model since the allocation succeeded
		try {
			device.refresh(progress.newChild(WORK_REFRESH), RefreshDepth.SELF);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return Status.CANCEL_STATUS;
		}

		return Status.OK_STATUS;
	}

}

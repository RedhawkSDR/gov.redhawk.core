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

import org.eclipse.core.runtime.CoreException;
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
import mil.jpeojtrs.sca.util.CorbaUtils;

/**
 * @since 20.4
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
	protected IStatus run(IProgressMonitor monitor) {
		final int WORK_ALLOCATE = 9;
		final int WORK_REFRESH = 1;
		SubMonitor progress = SubMonitor.convert(monitor, "Allocating", WORK_ALLOCATE + WORK_REFRESH);

		try {
			CorbaUtils.invoke(() -> {
				try {
					device.allocateCapacity(allocation);
				} catch (InvalidCapacity e) {
					throw new CoreException(new Status(IStatus.ERROR, ScaModelPlugin.ID, CFErrorFormatter.format(e, label), e));
				} catch (InvalidState e) {
					throw new CoreException(new Status(IStatus.ERROR, ScaModelPlugin.ID, CFErrorFormatter.format(e, label), e));
				} catch (InsufficientCapacity e) {
					throw new CoreException(new Status(IStatus.ERROR, ScaModelPlugin.ID, CFErrorFormatter.format(e, label), e));
				}
				return null;
			}, progress.newChild(WORK_ALLOCATE));

			device.refresh(progress.newChild(WORK_REFRESH), RefreshDepth.SELF);
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		} catch (CoreException e) {
			return new Status(e.getStatus().getSeverity(), ScaModelPlugin.ID, "Failed to allocate", e);
		}

		return Status.OK_STATUS;
	}

}

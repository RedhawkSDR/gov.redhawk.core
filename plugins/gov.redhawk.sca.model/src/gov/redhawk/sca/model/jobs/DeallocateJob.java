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
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDevice;
import mil.jpeojtrs.sca.util.CFErrorFormatter;
import mil.jpeojtrs.sca.util.CorbaUtils;

/**
 * Performs a deallocation against a device.
 *
 * @since 20.2
 */
public class DeallocateJob extends Job {

	private static final String PLUGIN_ID = "gov.redhawk.frontend";

	private ScaDevice< ? > device;
	private DataType[] deallocation;
	private String label;

	/**
	 * @since 20.5
	 */
	public DeallocateJob(ScaDevice< ? > device, DataType... deallocation) {
		super("Deallocating");
		this.device = device;
		this.deallocation = deallocation;
		if (this.device.isSetLabel()) {
			label = "device " + this.device.getLabel();
		} else {
			label = "device";
		}
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		final int WORK_DEALLOCATE = 9;
		final int WORK_REFRESH = 1;
		SubMonitor progress = SubMonitor.convert(monitor, "Deallocating", WORK_DEALLOCATE + WORK_REFRESH);

		try {
			CorbaUtils.invoke(() -> {
				try {
					device.deallocateCapacity(deallocation);
				} catch (InvalidCapacity e) {
					throw new CoreException(new Status(IStatus.ERROR, PLUGIN_ID, CFErrorFormatter.format(e, label), e));
				} catch (InvalidState e) {
					throw new CoreException(new Status(IStatus.ERROR, PLUGIN_ID, CFErrorFormatter.format(e, label), e));
				}
				return null;
			}, progress.newChild(WORK_DEALLOCATE));

			device.refresh(progress.newChild(WORK_REFRESH), RefreshDepth.SELF);
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		} catch (CoreException e) {
			return new Status(e.getStatus().getSeverity(), PLUGIN_ID, "Failed to deallocate", e);
		}

		return Status.OK_STATUS;
	}

}

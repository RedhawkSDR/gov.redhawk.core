/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.frontend.ui.wizard;

import java.util.concurrent.Callable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import CF.DataType;
import CF.DevicePackage.InsufficientCapacity;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.model.sca.ScaDevice;
import mil.jpeojtrs.sca.util.CorbaUtils;

/**
 * @since 1.1
 * 
 */
public class TunerAllocationJob extends Job {

	private ScaDevice< ? > device;
	private DataType[] props;

	public TunerAllocationJob(String name, ScaDevice< ? > device, DataType[] props) {
		super(name);
		this.device = device;
		this.props = props;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		return allocate(monitor, new StringBuilder());
	}

	public IStatus allocate(IProgressMonitor monitor, StringBuilder sb) {
		boolean retVal = false;

		try {
			retVal = CorbaUtils.invoke(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					return allocateCapacity(device, props, sb);
				}
			}, monitor);
		} catch (InterruptedException e) {
			sb.append("The allocation request was cancelled");
			retVal = false;
		} catch (CoreException e) {
			sb.append("An error occurred during the invocation of the allocation request. Message: " + e.getMessage());
			retVal = false;
		}

		if (!retVal) {
			return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, sb.toString());
		}

		return Status.OK_STATUS;
	}

	/**
	 * Attempt to allocate capacity on the provided device
	 * @param device
	 * @param props
	 * @param sb - StringBuilder - used to pass details on any exceptions to the calling method
	 * @param monitor
	 * @return true if the allocation is successful, false otherwise
	 */
	private boolean allocateCapacity(ScaDevice< ? > device, DataType[] props, StringBuilder sb) {
		try {
			if (!device.allocateCapacity(props)) {
				sb.append("The allocation request was not accepted because resources matching all aspects of the request were not available.");
				return false;
			}
		} catch (InvalidCapacity e) {
			sb.append("The allocation request was invalid. Message: " + e.msg);
			return false;
		} catch (InvalidState e) {
			sb.append("The Allocation Request failed because the device is in an invalid state. Message: " + e.msg);
			return false;
		} catch (InsufficientCapacity e) {
			sb.append("The Allocation Request failed because the device has insufficient capacity. Message: " + e.msg);
			return false;
		}

		return true;
	}
}

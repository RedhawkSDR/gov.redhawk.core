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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import CF.DataType;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.sca.model.jobs.AllocateJob;

/**
 * @since 1.1
 * 
 */
public class TunerAllocationJob extends AllocateJob {

	public TunerAllocationJob(String name, ScaDevice< ? > device, DataType[] props) {
		super(device, props);
		setName(name);
	}

	public IStatus allocate(IProgressMonitor monitor, StringBuilder sb) {
		IStatus status = this.run(monitor);
		if (!status.isOK()) {
			sb.append(status.getMessage());
		}
		return status;
	}
}

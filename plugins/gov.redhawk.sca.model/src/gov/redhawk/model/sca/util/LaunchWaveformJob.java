/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.model.sca.util;

import org.eclipse.core.runtime.IPath;

import CF.DataType;
import CF.DeviceAssignmentType;
import gov.redhawk.model.sca.ScaDomainManager;

/**
 * @since 14.0
 * @deprecated Moved to {@link gov.redhawk.sca.model.jobs.LaunchWaveformJob}
 */
@Deprecated
public class LaunchWaveformJob extends gov.redhawk.sca.model.jobs.LaunchWaveformJob {

	public LaunchWaveformJob(ScaDomainManager domMgr, String waveformName, IPath waveformPath, DeviceAssignmentType[] deviceAssn, DataType[] configProps,
		boolean autoStart, Object waitLock) {
		this(domMgr, waveformName, waveformPath, deviceAssn, configProps, autoStart, waitLock, false);
	}

	/**
	 * @since 20.0
	 */
	public LaunchWaveformJob(ScaDomainManager domMgr, String waveformName, IPath waveformPath, DeviceAssignmentType[] deviceAssn, DataType[] configProps,
		boolean autoStart, Object waitLock, boolean uninstallExistingAppFactory) {
		super(domMgr, waveformName, waveformPath, deviceAssn, configProps, autoStart);
		this.setWaitLock(waitLock);
		this.setUninstallExistingAppFactory(uninstallExistingAppFactory);
	}

}

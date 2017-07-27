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
package gov.redhawk.model.sca.commands;

import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaPackage;
import mil.jpeojtrs.sca.util.collections.FeatureMapList;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;

/**
 * @since 14.0
 */
public class MergeAggregateDevices extends SetStatusCommand<ScaDevice< ? >> {

	private Set<String> deviceIds;

	public MergeAggregateDevices(ScaDevice< ? > provider, Set<String> deviceIds, IStatus status) {
		super(provider, ScaPackage.Literals.SCA_DEVICE__CHILD_DEVICES, status);
		this.deviceIds = deviceIds;
	}

	@Override
	public void execute() {
		if (status.isOK()) {
			Set<ScaDevice< ? >> devicesToRemove = new HashSet<ScaDevice< ? >>();
			devicesToRemove.addAll(provider.getChildDevices());
			if (provider.getDevMgr() != null && !deviceIds.isEmpty()) {
				for (ScaDevice< ? > device : new FeatureMapList<>(provider.getDevMgr().getDevices(), ScaDevice.class)) {
					String deviceId = device.getIdentifier();
					if (deviceIds.contains(deviceId)) {
						if (!devicesToRemove.contains(device)) {
							provider.getChildDevices().add(device);
						}
						devicesToRemove.remove(device);
					}
				}
			}
			if (!devicesToRemove.isEmpty()) {
				provider.getChildDevices().removeAll(devicesToRemove);
			}

			if (!provider.isSetChildDevices()) {
				provider.getChildDevices().clear();
			}
		} else {
			provider.unsetChildDevices();
		}
		super.execute();
	}

}

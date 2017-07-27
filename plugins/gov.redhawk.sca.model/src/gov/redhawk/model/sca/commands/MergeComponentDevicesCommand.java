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

import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaPackage;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;

/**
 * @since 14.0
 */
public class MergeComponentDevicesCommand extends SetStatusCommand<ScaComponent> {

	private Map<String, ScaDevice< ? >> newDevices;

	public MergeComponentDevicesCommand(ScaComponent provider, final Map<String, ScaDevice< ? >> newDevices, IStatus status) {
		super(provider, ScaPackage.Literals.SCA_COMPONENT__DEVICES, status);
		this.newDevices = newDevices;
	}

	@Override
	public void execute() {
		// Setup Current Device Map
		final Map<String, ScaDevice< ? >> currentDevices = new HashMap<String, ScaDevice< ? >>();
		for (ScaDevice< ? > device : provider.getDevices()) {
			currentDevices.put(device.getIdentifier(), device);
		}

		// Setup Remove device map
		final Map<String, ScaDevice< ? >> removeDevices = new HashMap<String, ScaDevice< ? >>();
		removeDevices.putAll(currentDevices);
		removeDevices.keySet().removeAll(newDevices.keySet());

		// Remove duplicates
		newDevices.keySet().removeAll(currentDevices.keySet());
		if (!removeDevices.isEmpty() && !provider.getDevices().isEmpty()) {
			provider.getDevices().removeAll(removeDevices.values());
		}

		if (provider.getWaveform() != null) {
			provider.getDevices().addAll(newDevices.values());
		}

		if (!provider.isSetDevices()) {
			provider.getDevices().clear();
		}

		super.execute();
	}
}

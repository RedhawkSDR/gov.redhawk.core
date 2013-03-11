/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.model.sca.commands;

import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;

import java.util.HashMap;
import java.util.Map;

import CF.DeviceManager;

/**
 * @since 14.0
 * 
 */
public class ScaDomainManagerMergeDeviceManagersCommand extends SetStatusCommand<ScaDomainManager> {

	private final DeviceManager[] deviceMgrs;

	public ScaDomainManagerMergeDeviceManagersCommand(ScaDomainManager provider, DeviceManager[] deviceMgrs) {
		super(provider, ScaPackage.Literals.SCA_DOMAIN_MANAGER__DEVICE_MANAGERS, null);
		this.deviceMgrs = deviceMgrs;
	}

	@Override
	public void execute() {
		// Setup Current Device Managers Map
		final Map<String, ScaDeviceManager> scaDeviceManagers = new HashMap<String, ScaDeviceManager>();
		for (final ScaDeviceManager manager : provider.getDeviceManagers()) {
			scaDeviceManagers.put(manager.getIor(), manager);
		}

		// Remove devices that have been deleted
		final Map<String, ScaDeviceManager> removeDeviceManagers = new HashMap<String, ScaDeviceManager>();
		removeDeviceManagers.putAll(scaDeviceManagers);

		final Map<String, DeviceManager> corbaDeviceManagers = new HashMap<String, DeviceManager>();
		if (deviceMgrs != null) {
			for (final DeviceManager devMgr : deviceMgrs) {
				if (devMgr != null) {
					corbaDeviceManagers.put(devMgr.toString(), devMgr);
				}
			}
		}

		removeDeviceManagers.keySet().removeAll(corbaDeviceManagers.keySet());

		// Remove Duplicates
		corbaDeviceManagers.keySet().removeAll(scaDeviceManagers.keySet());

		// Remove device managers
		if (!removeDeviceManagers.isEmpty() && !provider.getDeviceManagers().isEmpty()) {
			provider.getDeviceManagers().removeAll(removeDeviceManagers.values());
		}

		// Add device managers
		for (final DeviceManager manager : corbaDeviceManagers.values()) {
			ScaDeviceManager scaManager = ScaFactory.eINSTANCE.createScaDeviceManager();
			provider.getDeviceManagers().add(scaManager);
			scaManager.setCorbaObj(manager);
		}

		if (!provider.isSetDeviceManagers()) {
			provider.getDeviceManagers().clear();
		}
		super.execute();
	}

}

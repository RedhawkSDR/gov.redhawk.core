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

import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaService;

import java.util.HashMap;
import java.util.Map;

import CF.DeviceManagerPackage.ServiceType;

/**
 * @since 14.0
 * 
 */
public class MergeServicesCommand extends SetStatusCommand<ScaDeviceManager> {
	private final Map<String, ServiceType> newServices;

	public MergeServicesCommand(ScaDeviceManager provider, Map<String, ServiceType> newServices) {
		super(provider, ScaPackage.Literals.SCA_DEVICE_MANAGER__SERVICES, null);
		this.newServices = newServices;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		// Setup current Service Map
		final Map<String, ScaService> currentServices = new HashMap<String, ScaService>();
		for (ScaService service : provider.getServices()) {
			currentServices.put(service.getIor(), service);
		}

		// Setup Services to remove map
		final Map<String, ScaService> removeServices = new HashMap<String, ScaService>();
		removeServices.putAll(currentServices);
		removeServices.keySet().removeAll(newServices.keySet());

		// Remove duplicates
		newServices.keySet().removeAll(currentServices.keySet());

		// Remove Services
		provider.getServices().removeAll(removeServices.values());

		// Add Services
		for (ServiceType typeInfo : newServices.values()) {
			ScaService service = createScaService();
			provider.getServices().add(service);
			service.setName(typeInfo.serviceName);
			service.setCorbaObj(typeInfo.serviceObject);
		}

		if (!provider.isSetServices()) {
			provider.getServices().clear();
		}
		super.execute();

	}

	/**
	 * @since 18.0
	 */
	protected ScaService createScaService() {
	    return ScaFactory.eINSTANCE.createScaService();
    }

}

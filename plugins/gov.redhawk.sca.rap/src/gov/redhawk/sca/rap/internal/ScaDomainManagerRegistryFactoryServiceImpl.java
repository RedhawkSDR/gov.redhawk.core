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
package gov.redhawk.sca.rap.internal;

import gov.redhawk.sca.IScaDomainManagerRegistryContainer;
import gov.redhawk.sca.IScaDomainManagerRegistryFactoryService;
import gov.redhawk.sca.ScaPlugin;

import org.eclipse.rwt.SessionSingletonBase;

public class ScaDomainManagerRegistryFactoryServiceImpl implements IScaDomainManagerRegistryFactoryService {

	@Override
	public IScaDomainManagerRegistryContainer getRegistryContainer() {
		if (Boolean.valueOf(System.getProperty(ScaPlugin.PROP_SHARED_DOMAINS))) {
			return ScaDomainManagerRegistryContainerImpl.getInstance();
		}
		return SessionSingletonBase.getInstance(ScaDomainManagerRegistryContainerImpl.class);
	}
	
	public void deactivate() {
		SessionSingletonBase.getInstance(ScaDomainManagerRegistryContainerImpl.class).dispose();
	}
	
	public void activate() {
		//Nothing to do. Model will be loaded with specific user context when registry is obtained.
	}

}

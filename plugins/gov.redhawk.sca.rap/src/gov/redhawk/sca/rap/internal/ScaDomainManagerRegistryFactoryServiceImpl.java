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
import gov.redhawk.sca.rap.ScaRapPlugin;

import org.eclipse.rwt.SessionSingletonBase;
import org.eclipse.rwt.lifecycle.UICallBack;
import org.eclipse.swt.widgets.Display;

public class ScaDomainManagerRegistryFactoryServiceImpl implements IScaDomainManagerRegistryFactoryService {

	@Override
	public IScaDomainManagerRegistryContainer getRegistryContainer() {
		return getRegistryContainer(null);
	}
	
	@Override
	public IScaDomainManagerRegistryContainer getRegistryContainer(Object context) {
		if (Boolean.valueOf(System.getProperty(ScaRapPlugin.PROP_SHARED_DOMAINS))) {
			return ScaDomainManagerRegistryContainerImpl.getInstance();
		}
		final boolean[] done = {false};
		final IScaDomainManagerRegistryContainer[] result = new IScaDomainManagerRegistryContainer[1];
		
		UICallBack.runNonUIThreadWithFakeContext((Display) context, new Runnable() {

			@Override
			public void run() {
				result[0] = SessionSingletonBase.getInstance(ScaDomainManagerRegistryContainerImpl.class);
				done[0] = true;
			}
			
		});

		while (!done[0]) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				//PASS
			}
		}
		
		return result[0];
	}
	
	public void deactivate() {
		SessionSingletonBase.getInstance(ScaDomainManagerRegistryContainerImpl.class).dispose();
	}
	
	public void activate() {
		//Nothing to do. Model will be loaded with specific user context when registry is obtained.
	}
}

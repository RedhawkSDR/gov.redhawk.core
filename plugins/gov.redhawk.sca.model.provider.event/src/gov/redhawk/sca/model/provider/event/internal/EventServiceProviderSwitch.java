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
package gov.redhawk.sca.model.provider.event.internal;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.services.IScaDataProvider;
import gov.redhawk.model.sca.util.ScaSwitch;
import gov.redhawk.sca.model.provider.event.internal.listener.ScaDomainManagerEventServiceDataProvider;

/**
 * 
 */
public class EventServiceProviderSwitch extends ScaSwitch<IScaDataProvider> {

	@Override
	public IScaDataProvider caseScaDomainManager(final ScaDomainManager object) {
		return new ScaDomainManagerEventServiceDataProvider(object);
	}

}

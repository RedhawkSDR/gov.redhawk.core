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
package gov.redhawk.sca.model.provider.refresh.internal;

import gov.redhawk.model.sca.services.AbstractDataProviderService;
import gov.redhawk.model.sca.services.IScaDataProvider;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 */
public class ScaRefreshableDataProviderService extends AbstractDataProviderService {
	
	public static final String ID = "gov.redhawk.sca.model.provider.refresh";

	private static final RefresherSwitch SWITCH = new RefresherSwitch();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IScaDataProvider createDataProvider(final EObject object) {
		IRefresher refresher = ScaRefreshableDataProviderService.SWITCH.doSwitch(object);
		if (refresher != null) {
			return new RefreshTasker(object, refresher);
		}
		return null;
	}
	
	@Override
	public String getID() {
		return ID;
	}

}

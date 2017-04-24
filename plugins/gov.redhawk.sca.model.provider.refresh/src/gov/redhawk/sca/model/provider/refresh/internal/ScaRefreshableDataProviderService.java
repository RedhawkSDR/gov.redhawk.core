/** 
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.sca.model.provider.refresh.internal;

import org.eclipse.emf.ecore.EObject;

import gov.redhawk.model.sca.services.AbstractDataProviderService;
import gov.redhawk.model.sca.services.IScaDataProvider;

/**
 * A data provider service that performs regular, timed refreshes of the SCA model.
 */
public class ScaRefreshableDataProviderService extends AbstractDataProviderService {

	static final String ID = "gov.redhawk.sca.model.provider.refresh";

	private static final RefresherSwitch SWITCH = new RefresherSwitch();

	@Override
	protected IScaDataProvider createDataProvider(final EObject object) {
		IRefresher refresher = ScaRefreshableDataProviderService.SWITCH.doSwitch(object);
		return (refresher == null) ? null : new RefreshTasker(object, refresher);
	}

	@Override
	public String getID() {
		return ID;
	}

}

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

import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.services.AbstractDataProviderService;
import gov.redhawk.model.sca.services.IScaDataProvider;
import gov.redhawk.sca.model.provider.refresh.RefreshProviderPlugin;
import gov.redhawk.sca.model.provider.refresh.RefreshTask;
import gov.redhawk.sca.model.provider.refresh.preferences.RefreshPreferenceConstants;
import gov.redhawk.sca.model.provider.refresh.preferences.RefreshPreferenceInitializer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import mil.jpeojtrs.sca.util.NamedThreadFactory;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 */
public class ScaRefreshableDataProviderService extends AbstractDataProviderService {

	private static final RefresherSwitch SWITCH = new RefresherSwitch();

	public static final ScheduledExecutorService REFRESH_POOL = Executors.newScheduledThreadPool(ScaRefreshableDataProviderService.getPoolSize(),
			new NamedThreadFactory(ScaRefreshableDataProviderService.class.getName()));

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IScaDataProvider createDataProvider(final EObject object) {
		if (object instanceof IRefreshable) {
			return new RefreshTask(ScaRefreshableDataProviderService.REFRESH_POOL, object, ScaRefreshableDataProviderService.SWITCH.doSwitch(object));
		}
		return null;
	}

	private static int getPoolSize() {
		int permits = RefreshProviderPlugin.getInstance().getPreferenceAccessor().getInt(RefreshPreferenceConstants.REFRESH_PERMITS);
		if (permits <= 0) {
			permits = RefreshPreferenceInitializer.getDefaultRefreshPermits();
		}
		return permits;
	}

}

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
package gov.redhawk.sca.model.provider.refresh.preferences;

import gov.redhawk.sca.model.provider.refresh.RefreshProviderPlugin;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

/**
 * @since 4.0
 */
public class RefreshPreferenceInitializer extends AbstractPreferenceInitializer {

	private static final long DEFAULT_REFRESH_INTERVAL = 10000L;
	private static final int DEFAULT_REFRESH_PERMITS = 10;

	/**
	 * 
	 */
	public RefreshPreferenceInitializer() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initializeDefaultPreferences() {
		RefreshProviderPlugin.getInstance().getPreferenceAccessor().setDefault(RefreshPreferenceConstants.REFRESH_INTERVAL, DEFAULT_REFRESH_INTERVAL);
		RefreshProviderPlugin.getInstance().getPreferenceAccessor().setDefault(RefreshPreferenceConstants.REFRESH_TIMEOUT, 15000);
	}
	
	public static int getDefaultRefreshPermits() {
		return DEFAULT_REFRESH_PERMITS;
	}

}

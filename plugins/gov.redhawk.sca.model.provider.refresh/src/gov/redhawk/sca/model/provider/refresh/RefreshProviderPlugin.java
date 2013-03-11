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
package gov.redhawk.sca.model.provider.refresh;

import gov.redhawk.sca.model.provider.refresh.internal.ScaRefreshableDataProviderService;
import gov.redhawk.sca.util.IPreferenceAccessor;
import gov.redhawk.sca.util.ScopedPreferenceAccessor;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.framework.BundleContext;

public class RefreshProviderPlugin extends Plugin {

	public static final String PLUGIN_ID = "gov.redhawk.sca.model.provider.refresh";

	private static RefreshProviderPlugin instance;
	private final ScopedPreferenceAccessor refreshPreferenceStore = new ScopedPreferenceAccessor(new InstanceScope(), RefreshProviderPlugin.getPluginId());

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		RefreshProviderPlugin.instance = this;
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		ScaRefreshableDataProviderService.REFRESH_POOL.shutdownNow();
		super.stop(context);
		savePluginPreferences();
		RefreshProviderPlugin.instance = null;
	}

	public static RefreshProviderPlugin getInstance() {
		return RefreshProviderPlugin.instance;
	}

	public static String getPluginId() {
		return RefreshProviderPlugin.PLUGIN_ID;
	}

	/**
	 * @since 3.0
	 */
	public IPreferenceAccessor getPreferenceAccessor() {
		return this.refreshPreferenceStore;
	}

}

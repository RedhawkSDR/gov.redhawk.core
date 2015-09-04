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
package gov.redhawk.sca.model.provider.refresh;

import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.sca.model.provider.refresh.internal.RefreshTasker;
import gov.redhawk.sca.model.provider.refresh.preferences.RefreshPreferenceConstants;
import gov.redhawk.sca.util.IPreferenceAccessor;
import gov.redhawk.sca.util.ScopedPreferenceAccessor;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.framework.BundleContext;

public class RefreshProviderPlugin extends Plugin {

	public static final String PLUGIN_ID = "gov.redhawk.sca.model.provider.refresh";

	private static RefreshProviderPlugin instance;
	private final ScopedPreferenceAccessor refreshPreferenceStore = new ScopedPreferenceAccessor(InstanceScope.INSTANCE, RefreshProviderPlugin.getPluginId());

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		RefreshProviderPlugin.instance = this;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void stop(final BundleContext context) throws Exception {
		RefreshTasker.TASKER_POOL.shutdownNow();
		RefreshTasker.WORKER_POOL.shutdownNow();
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
	
	/**
	 * @since 5.0
	 */
	public static void setOverrideDepth(RefreshDepth depth) {
		if (instance != null) {
			final IPreferenceAccessor accessor = instance.getPreferenceAccessor();
			if (accessor != null) {
				accessor.setValue(RefreshPreferenceConstants.REFRESH_OVERRIDE_DEPTH, depth.toString());
			}
		}
	}
	
	/**
	 * @since 5.0
	 */
	public static void setRefreshInterval(long valueMs) {
		if (instance != null) {
			final IPreferenceAccessor accessor = instance.getPreferenceAccessor();
			if (accessor != null) {
				accessor.setValue(RefreshPreferenceConstants.REFRESH_INTERVAL, valueMs);
			}
		}
	}
	
	/**
	 * @since 5.0
	 */
	public static void setRefreshTimeout(long valueMs) {
		if (instance != null) {
			final IPreferenceAccessor accessor = instance.getPreferenceAccessor();
			if (accessor != null) {
				accessor.setValue(RefreshPreferenceConstants.REFRESH_TIMEOUT, valueMs);
			}
		}
	}
	
	/**
	 * @since 5.0
	 */
	public static long getRefreshInterval() {
		if (instance != null) {
			final IPreferenceAccessor accessor = instance.getPreferenceAccessor();
			if (accessor != null) {
				return accessor.getLong(RefreshPreferenceConstants.REFRESH_INTERVAL);
			}
		}
		return 500;
	}
	
	/**
	 * @since 5.0
	 */
	public static long getRefreshTimeout() {
		if (instance != null) {
			final IPreferenceAccessor accessor = instance.getPreferenceAccessor();
			if (accessor != null) {
				return accessor.getLong(RefreshPreferenceConstants.REFRESH_TIMEOUT);
			}
		}
		return 5000;
	}
	
	/**
	 * @since 5.0
	 */
	public static RefreshDepth getOverrideDepth() {
		String depth = null;
		if (instance != null) {
			final IPreferenceAccessor accessor = instance.getPreferenceAccessor();
			if (accessor != null) {
				depth = accessor.getString(RefreshPreferenceConstants.REFRESH_OVERRIDE_DEPTH);
			}
		}
		if (depth != null) {
			try {
				return RefreshDepth.valueOf(depth);
			} catch (IllegalArgumentException e) {
				// PASS
			}
		}
		return null;
	}
}

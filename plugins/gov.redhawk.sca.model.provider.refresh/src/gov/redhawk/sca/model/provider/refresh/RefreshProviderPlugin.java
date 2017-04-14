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
import gov.redhawk.sca.model.provider.refresh.internal.RefreshThreadPools;
import gov.redhawk.sca.model.provider.refresh.preferences.RefreshPreferenceConstants;
import gov.redhawk.sca.util.IPreferenceAccessor;
import gov.redhawk.sca.util.ScopedPreferenceAccessor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.framework.BundleContext;

public class RefreshProviderPlugin extends Plugin {

	public static final String PLUGIN_ID = "gov.redhawk.sca.model.provider.refresh";

	private static final long MAX_SHUTDOWN_TIME_MS = 2000;

	private static RefreshProviderPlugin instance;
	private final ScopedPreferenceAccessor refreshPreferenceStore = new ScopedPreferenceAccessor(InstanceScope.INSTANCE, RefreshProviderPlugin.getPluginId());

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		RefreshProviderPlugin.instance = this;

		// Debug option to periodically print refresh stats
		if ("true".equalsIgnoreCase(Platform.getDebugOption(PLUGIN_ID + "/debug/refreshStats"))) {
			RefreshThreadPools.getEventExecutor().scheduleWithFixedDelay(() -> {
				System.out.println(RefreshThreadPools.calculateStats().toString()); // SUPPRESS CHECKSTYLE Debug facility
			}, 10, 10, TimeUnit.SECONDS);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void stop(final BundleContext context) throws Exception {
		// Prevent any more refreshes from being started; give existing ones a little time to stop
		long endTime = System.currentTimeMillis() + MAX_SHUTDOWN_TIME_MS;
		ExecutorService[] executors = new ExecutorService[] { RefreshThreadPools.getEventExecutor(), RefreshThreadPools.getRefreshExecutor() };
		for (ExecutorService executor : executors) {
			executor.shutdownNow();
		}
		for (ExecutorService executor : executors) {
			long remainingTime = endTime - System.currentTimeMillis();
			if (remainingTime <= 0) {
				break;
			}
			executor.awaitTermination(remainingTime, TimeUnit.MILLISECONDS);
		}

		savePluginPreferences();
		RefreshProviderPlugin.instance = null;
		super.stop(context);
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
	 * @deprecated Do not use. No effect.
	 */
	@Deprecated
	public static void setOverrideDepth(RefreshDepth depth) {
		// PASS
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
	 * @deprecated Do not use. Returns null.
	 */
	@Deprecated
	public static RefreshDepth getOverrideDepth() {
		return null;
	}
}

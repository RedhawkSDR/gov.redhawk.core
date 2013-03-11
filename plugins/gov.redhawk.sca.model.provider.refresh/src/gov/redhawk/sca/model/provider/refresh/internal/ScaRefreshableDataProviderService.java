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
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 */
public class ScaRefreshableDataProviderService extends AbstractDataProviderService {
	/**
	* The default thread factory
	*/
	private static class RefreshThreadFactory implements ThreadFactory {
		private final ThreadGroup group;
		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private final String namePrefix;

		RefreshThreadFactory() {
			final SecurityManager s = System.getSecurityManager();
			if (s != null) {
				this.group = s.getThreadGroup();
			} else {
				this.group = Thread.currentThread().getThreadGroup();
			}
			this.namePrefix = "refreshDataProviderPool-thread-";
		}

		public Thread newThread(final Runnable r) {
			final Thread t = new Thread(this.group, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0);
			if (t.isDaemon()) {
				t.setDaemon(false);
			}
			if (t.getPriority() != Thread.NORM_PRIORITY) {
				t.setPriority(Thread.NORM_PRIORITY);
			}
			return t;
		}
	}

	private static final RefresherSwitch SWITCH = new RefresherSwitch();

	public static final ScheduledExecutorService REFRESH_POOL = Executors.newScheduledThreadPool(ScaRefreshableDataProviderService.getPoolSize(),
	        new RefreshThreadFactory());

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

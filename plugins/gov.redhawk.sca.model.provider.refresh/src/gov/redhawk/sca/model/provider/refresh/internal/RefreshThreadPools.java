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

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import gov.redhawk.sca.model.provider.refresh.RefreshProviderPlugin;
import mil.jpeojtrs.sca.util.NamedThreadFactory;

public class RefreshThreadPools {

	private static ScheduledThreadPoolExecutor refreshExecutor = new ScheduledThreadPoolExecutor(5,
		new NamedThreadFactory(RefreshThreadPools.class.getSimpleName() + "-refreshExecutor"));

	private static ScheduledThreadPoolExecutor eventExecutor = new ScheduledThreadPoolExecutor(1,
		new NamedThreadFactory(RefreshThreadPools.class.getSimpleName() + "-eventExecutor"));

	static {
		refreshExecutor.setRemoveOnCancelPolicy(true);
		eventExecutor.setRemoveOnCancelPolicy(true);
	}

	private RefreshThreadPools() {
	}

	/**
	 * Returns an executor used to run brief actions. Operations should complete quickly and not block.
	 * @return
	 */
	public static ScheduledExecutorService getEventExecutor() {
		return eventExecutor;
	}

	/**
	 * Gets the executor used to perform refreshes on SCA model objects. Operations are expected to be long-running, but
	 * not indefinite.
	 * @return
	 */
	public static ScheduledExecutorService getRefreshExecutor() {
		return refreshExecutor;
	}

	/**
	 * Calculates statistics about the thread pools.
	 * @return
	 */
	public static Stats calculateStats() {
		Stats stats = new Stats();
		final long REFRESH_DELAY = RefreshProviderPlugin.getRefreshInterval();
		stats.refreshUnder = refreshExecutor.getQueue().stream() //
				.filter(future -> ((ScheduledFuture< ? >) future).getDelay(TimeUnit.MILLISECONDS) <= REFRESH_DELAY) //
				.count();
		stats.refreshOver = refreshExecutor.getQueue().size() - stats.refreshUnder;
		stats.refreshActive = refreshExecutor.getActiveCount();
		stats.refreshTotal = refreshExecutor.getPoolSize();
		stats.events = eventExecutor.getQueue().size();
		stats.eventActive = eventExecutor.getActiveCount();
		stats.eventTotal = eventExecutor.getPoolSize();
		return stats;
	}

	/**
	 * A simple container for various statistics about the thread pools.
	 */
	public static class Stats {

		private static final String STR_FORMAT = "Refresh executor\n  Queued tasks long delay: %d\n  Queued tasks short delay: %d\n"
			+ "  Active threads: %d / %d\nEvent executor\n  Queued tasks: %d\n  Active threads: %d / %d\n";

		// CHECKSTYLE:OFF

		public long refreshOver, refreshUnder;
		public long refreshActive, refreshTotal;
		public long events;
		public long eventActive, eventTotal;

		// CHECKSTYLE:ON

		@Override
		public String toString() {
			return String.format(STR_FORMAT, refreshOver, refreshUnder, refreshActive, refreshTotal, events, eventActive, eventTotal);
		}
	}
}

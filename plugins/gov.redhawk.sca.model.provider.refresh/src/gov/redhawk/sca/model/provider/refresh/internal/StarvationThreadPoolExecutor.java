/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.sca.model.provider.refresh.internal;

import gov.redhawk.sca.model.provider.refresh.RefreshProviderPlugin;
import gov.redhawk.sca.util.Debug;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 */
public class StarvationThreadPoolExecutor extends ThreadPoolExecutor {
	private static final Debug DEBUG = new Debug(RefreshProviderPlugin.PLUGIN_ID, "worker");
	private static final int MIN_POOL = 5;

	private class Task implements Runnable {
		private static final long TIMEOUT = 5000;
		private long startTime = -1;
		private final Runnable command;

		public Task(Runnable command) {
			this.command = command;
		}

		public boolean isRunning() {
			return startTime > 0;
		}

		public boolean isHanging() {
			return isRunning() && (System.currentTimeMillis() - startTime > TIMEOUT);
		}

		@Override
		public void run() {
			startTime = System.currentTimeMillis();
			try {
				command.run();
			} finally {
				tasks.remove(this);
				fixPoolSize();
			}
		}
	}

	private final List<Task> tasks = Collections.synchronizedList(new LinkedList<StarvationThreadPoolExecutor.Task>());

	public StarvationThreadPoolExecutor(ThreadFactory factory) {
		super(0, MIN_POOL, 2, TimeUnit.MINUTES, new SynchronousQueue<Runnable>(), factory);
		allowCoreThreadTimeOut(true);
	}

	private void fixPoolSize() {
		synchronized (tasks) {
			int numHanging = 0;

			for (Task t : tasks) {
				if (t.isHanging()) {
					numHanging++;
				}
			}
			int poolSize = MIN_POOL + numHanging;
			if (getMaximumPoolSize() != poolSize) {
				setMaximumPoolSize(poolSize);
				if (DEBUG.enabled) {
					DEBUG.message("Adjusting pool size to: " + poolSize);
				}
			}
		}
	}

	@Override
	public void shutdown() {
		tasks.clear();
		super.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		tasks.clear();
		return super.shutdownNow();
	}

	@Override
	public void execute(Runnable command) {
		Task task = new Task(command);
		fixPoolSize();
		tasks.add(task);
		super.execute(task);
	}

}

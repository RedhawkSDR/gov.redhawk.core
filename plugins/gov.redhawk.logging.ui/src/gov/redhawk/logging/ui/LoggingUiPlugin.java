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
package gov.redhawk.logging.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import gov.redhawk.logging.ui.eventconsole.EventConsole;
import gov.redhawk.logging.ui.jobs.EventChannelCleanupFamily;
import gov.redhawk.sca.util.OrbSession;

/**
 * The activator class controls the plug-in life cycle
 */
public class LoggingUiPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "gov.redhawk.logging.ui"; //$NON-NLS-1$

	/**
	 * Time to wait for jobs to complete that are destroying event channel loggers. The wait occurs when the plugin is
	 * stopped.
	 */
	private static final long SHUTDOWN_JOB_WAIT_TIME_MS = 5000;

	// The shared instance
	private static LoggingUiPlugin plugin;

	/**
	 * We hold on to a reference only to make sure it isn't disposed until we're done
	 */
	private OrbSession session;

	public LoggingUiPlugin() {
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		session = OrbSession.createSession(PLUGIN_ID);
	}

	public void stop(BundleContext context) throws Exception {
		// Close all event logger consoles. This will trigger cleanup jobs.
		IConsoleManager consoleManager = ConsolePlugin.getDefault().getConsoleManager();
		List<IConsole> removeConsoles = new ArrayList<IConsole>();
		for (IConsole console : consoleManager.getConsoles()) {
			if (console instanceof EventConsole) {
				removeConsoles.add(console);
			}
		}
		if (removeConsoles.size() > 0) {
			consoleManager.removeConsoles(removeConsoles.toArray(new IConsole[removeConsoles.size()]));
		}

		// Wait for event channel cleanup jobs to complete
		IJobManager jobManager = Job.getJobManager();
		Job[] terminateJobs = jobManager.find(EventChannelCleanupFamily.class);
		long endTime = System.currentTimeMillis() + SHUTDOWN_JOB_WAIT_TIME_MS;
		for (Job terminateJob : terminateJobs) {
			long timeout = endTime - System.currentTimeMillis();
			if (timeout > 0) {
				try {
					terminateJob.join(timeout, null);
				} catch (InterruptedException | OperationCanceledException e) {
					// PASS
				}
			}
		}

		plugin = null;

		session.dispose();
		session = null;

		super.stop(context);
	}

	public static LoggingUiPlugin getDefault() {
		return plugin;
	}

}

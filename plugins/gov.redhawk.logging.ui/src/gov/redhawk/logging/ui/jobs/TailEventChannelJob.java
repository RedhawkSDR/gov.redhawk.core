/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package gov.redhawk.logging.ui.jobs;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;

import gov.redhawk.logging.ui.LoggingUiPlugin;
import gov.redhawk.logging.ui.eventconsole.EventChannelToConsoleStream;
import gov.redhawk.logging.ui.eventconsole.EventConsole;
import gov.redhawk.model.sca.ScaEventChannel;
import gov.redhawk.sca.util.SubMonitor;

/**
 * Opens a console view which will display log events received from an event channel.
 */
public class TailEventChannelJob extends Job {

	private ScaEventChannel eventChannel;
	private Job cleanupJob;

	/**
	 * @param eventChannel The event channel to view
	 * @param cleanupJob A cleanup job to remove the event channel logger from a logging configuration; null for no
	 * cleanup
	 */
	public TailEventChannelJob(ScaEventChannel eventChannel, Job cleanupJob) {
		super(Messages.TailEventChannelJob_0);
		this.eventChannel = eventChannel;
		this.cleanupJob = cleanupJob;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		final int WORK_NARROW_EVENT_CHANNEL = 1;
		final int WORK_CONNECT_CONSUMER = 2;
		SubMonitor progress = SubMonitor.convert(monitor, WORK_NARROW_EVENT_CHANNEL + WORK_CONNECT_CONSUMER);

		// Narrow if necessary
		if (!eventChannel.isSetObj()) {
			eventChannel.fetchNarrowedObject(progress.newChild(WORK_NARROW_EVENT_CHANNEL));
		} else {
			progress.notWorked(WORK_NARROW_EVENT_CHANNEL);
		}
		if (progress.isCanceled()) {
			if (cleanupJob != null) {
				cleanupJob.schedule();
			}
			return Status.CANCEL_STATUS;
		}

		// Add a new console and show it
		IConsoleManager consoleManager = ConsolePlugin.getDefault().getConsoleManager();
		EventConsole eventConsole = new EventConsole(Messages.bind(Messages.TailEventChannelJob_1, eventChannel.getName()), null, cleanupJob);
		consoleManager.addConsoles(new IConsole[] { eventConsole });
		eventConsole.activate();

		// Create the object to read the event channel and write to the console
		EventChannelToConsoleStream eventConsumer = new EventChannelToConsoleStream(eventChannel, eventConsole);
		eventConsole.setStream(eventConsumer);
		try {
			eventConsumer.activateAndConnect(progress.newChild(WORK_CONNECT_CONSUMER));
		} catch (CoreException e) {
			consoleManager.removeConsoles(new IConsole[] { eventConsole });
			return new MultiStatus(LoggingUiPlugin.PLUGIN_ID, 0, Messages.TailEventChannelJob_2, e);
		}

		return Status.OK_STATUS;
	}

}

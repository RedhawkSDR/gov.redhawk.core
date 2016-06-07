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
package gov.redhawk.logging.ui.eventconsole;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.console.IOConsole;

/**
 * A console to "tail" an event log
 */
public class EventConsole extends IOConsole {

	private Job cleanupJob;
	private EventChannelToConsoleStream stream;

	/**
	 * @param name The name of the console
	 * @param imageDescriptor The image for the console
	 * @param cleanupJob A cleanup job for the logging configuration, or null if none
	 */
	public EventConsole(String name, ImageDescriptor imageDescriptor, Job cleanupJob) {
		super(name, imageDescriptor);
		this.cleanupJob = cleanupJob;
	}

	/**
	 * Set the stream providing input from the event channel. This allows cleanup when the console is disposed.
	 * @param stream
	 */
	public void setStream(EventChannelToConsoleStream stream) {
		this.stream = stream;
	}

	@Override
	protected void dispose() {
		if (cleanupJob != null) {
			cleanupJob.schedule();
			cleanupJob = null;
		}
		if (stream != null) {
			stream.asyncDispose();
			stream = null;
		}
		super.dispose();
	}

}

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
package gov.redhawk.core.notification.ui.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import gov.redhawk.core.notification.ui.Notifications;

/**
 * Provides desktop notifications via the notify-send executable from libnotify.
 */
public class NativeNotifier implements INotifier {

	private static final String PLUGIN_ID = "gov.redhawk.core.notification.ui.linux";

	/**
	 * Throws a runtime exception if notify-send does not appear to be available on the system.
	 */
	public NativeNotifier() {
		Process process = null;
		try {
			process = new ProcessBuilder("notify-send", "--version").start();
			process.waitFor();
		} catch (InterruptedException | IOException e) {
			if (process != null) {
				process.destroyForcibly();
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public void notify(int severity, String title, String message) {
		List<String> command = new ArrayList<>();
		command.add("notify-send");

		switch (severity) {
		case Notifications.INFO:
			command.add("--icon");
			command.add("dialog-information");
			break;
		case Notifications.WARNING:
			command.add("--icon");
			command.add("dialog-warning");
			break;
		case Notifications.ERROR:
			command.add("--icon");
			command.add("dialog-error");
			break;
		default:
			break;
		}

		command.add(title);
		command.add(message);

		Process process = null;
		try {
			process = new ProcessBuilder(command).start();
			process.waitFor();
		} catch (InterruptedException | IOException e) {
			ILog log = Platform.getLog(Platform.getBundle(PLUGIN_ID));
			log.log(new Status(IStatus.ERROR, PLUGIN_ID, "Unable to display notification", e));
			if (process != null) {
				process.destroyForcibly();
			}
		}
	}
}

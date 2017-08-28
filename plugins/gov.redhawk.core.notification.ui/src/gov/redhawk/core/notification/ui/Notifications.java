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
package gov.redhawk.core.notification.ui;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import gov.redhawk.core.notification.ui.internal.INotifier;
import gov.redhawk.core.notification.ui.internal.SWTNotifier;

public enum Notifications {
	INSTANCE;

	public static final int INFO = 1;
	public static final int WARNING = 2;
	public static final int ERROR = 3;

	private static final String PLUGIN_ID = "gov.redhawk.core.notification.ui";

	private INotifier notifier;

	private Notifications() {
		try {
			// Attempt to instantiate the native notifier
			Class< ? > nativeClass = Class.forName("gov.redhawk.core.notification.ui.internal.NativeNotifier");
			notifier = nativeClass.asSubclass(INotifier.class).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// Fall back on SWT notifier
			notifier = new SWTNotifier();

			ILog log = Platform.getLog(Platform.getBundle(PLUGIN_ID));
			log.log(new Status(IStatus.ERROR, PLUGIN_ID, "Unable to display notification", e));
		}
	}

	/**
	 * Display a notification to the user.
	 * @param severity The severity. One of <code>INFO</code>, <code>WARNING</code>, or <code>ERROR</code>.
	 * @param title The title of the notification
	 * @param message The body of the message
	 */
	public void notify(int severity, String title, String message) {
		notifier.notify(severity, title, message);
	}

}

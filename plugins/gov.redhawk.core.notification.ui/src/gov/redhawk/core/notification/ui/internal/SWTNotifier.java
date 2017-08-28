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

import java.util.LinkedList;
import java.util.Queue;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * A backup notifier written for SWT which is used if the native notifier is unavailable.
 */
public class SWTNotifier implements INotifier {

	private class NotifyInfo {
		private int severity;
		private String title;
		private String message;

		public NotifyInfo(int severity, String title, String message) {
			this.severity = severity;
			this.title = title;
			this.message = message;
		}
	}

	private Queue<NotifyInfo> notifyInfos = new LinkedList<>();

	private volatile boolean notifyRunning = false;

	@Override
	public void notify(int severity, String title, String message) {
		synchronized (notifyInfos) {
			// Enqueue the notification
			notifyInfos.add(new NotifyInfo(severity, title, message));

			// If there is already a notification running, it will schedule the next one when it completes
			if (notifyRunning) {
				return;
			}

			// No running notification - we'll need to start one
			notifyRunning = true;
		}

		nextNotification();
	}

	private void nextNotification() {
		NotifyInfo info;
		synchronized (notifyInfos) {
			// Remove the next notification to be shown from the queue
			info = notifyInfos.poll();

			// If nothing left to notify
			if (info == null) {
				notifyRunning = false;
				return;
			}
		}

		// Start the notification. It is placed relative to the workbench window's monitor.
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window == null) {
			window = PlatformUI.getWorkbench().getWorkbenchWindows()[0];
		}
		Shell parentShell = window.getShell();
		parentShell.getDisplay().asyncExec(() -> {
			NotificationWindow currentNotification = new NotificationWindow(parentShell, info.severity, info.title, info.message);
			currentNotification.open();
			currentNotification.getShell().addDisposeListener(event -> {
				// Trigger next notification when disposed
				nextNotification();
			});
		});
	}

}

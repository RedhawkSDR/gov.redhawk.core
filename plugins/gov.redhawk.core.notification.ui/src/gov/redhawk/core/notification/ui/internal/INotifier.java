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

/**
 * Classes implementing this should be a fragment of gov.redhawk.core.notification.ui, and should be named
 * gov.redhawk.core.notification.ui.internal.NativeNotifier.
 */
public interface INotifier {

	/**
	 * Display a notification to the user.
	 * @param severity The severity. One of the {@link gov.redhawk.core.notification.ui.Notifications Notifications}
	 * constants: <code>INFO</code>, <code>WARNING</code>, or <code>ERROR</code>.
	 * @param title The title of the notification
	 * @param message The body of the message
	 */
	public void notify(int severity, String title, String message);

}

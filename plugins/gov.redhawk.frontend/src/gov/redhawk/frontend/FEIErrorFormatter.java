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
package gov.redhawk.frontend;

import FRONTEND.BadParameterException;
import FRONTEND.FrontendException;
import FRONTEND.NotSupportedException;

/**
 * Utility methods for formatting FRONTEND exceptions for display to the user. Since they're CORBA exceptions,
 * <code>toString()</code> doesn't produce a message with the error's details.
 * @since 2.0
 */
public class FEIErrorFormatter {

	private static final String FORMAT_ERRNAME_RESNAME_ERRMSG = "%s for %s: %s";

	private FEIErrorFormatter() {
	}

	public static String format(BadParameterException e, String resourceName) {
		return String.format(FORMAT_ERRNAME_RESNAME_ERRMSG, e.getClass().getName(), resourceName, e.msg);
	}

	public static String format(FrontendException e, String resourceName) {
		return String.format(FORMAT_ERRNAME_RESNAME_ERRMSG, e.getClass().getName(), resourceName, e.msg);
	}

	public static String format(NotSupportedException e, String resourceName) {
		return String.format(FORMAT_ERRNAME_RESNAME_ERRMSG, e.getClass().getName(), resourceName, e.msg);
	}

}

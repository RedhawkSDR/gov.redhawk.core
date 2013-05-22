/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

package gov.redhawk.sca.util;

/**
 * The Class CorbaURIUtil.
 */
public final class CorbaURIUtil {
	/**
	 * The IANA-registered default port for CORBA; note that the Java ORB does
	 * not use this port
	 */
	public static final int CORBALOC_PORT = 2809;

	private CorbaURIUtil() {

	}

	/**
	 * Adds the IANA-registered default CORBA port onto a corbaname or corbaloc
	 * URI if no port is explicitly given. This allows interoperability with
	 * OmniORB.
	 * 
	 * @param corbaURI the input CORBA URI
	 * 
	 * @return the input URI with the default port added, if necessary
	 */
	public static String addDefaultPort(final String corbaURI) {
		if (CorbaURIUtil.hasPort(corbaURI)) {
			return corbaURI;
		}

		// The expected inputs don't include components, so unless the need to handle
		// them arises, simply append the default port.
		return corbaURI + ":" + CorbaURIUtil.CORBALOC_PORT;
	}

	/**
	 * @since 1.3
	 */
	public static String addDefaultPrefix(String corbaURI) {
		if (!corbaURI.startsWith("corbaname::")) {
			corbaURI = "corbaname::" + corbaURI;
		}
		return corbaURI;
	}

	/**
	 * Checks if a port is specified in a CORBA URI.
	 * 
	 * @param corbaURI the CORBA URI to check
	 * 
	 * @return true if a port is specified, false otherwise
	 */
	public static boolean hasPort(final String corbaURI) {
		final int index = corbaURI.lastIndexOf(':');
		if (index < 0) {
			return false;
		}
		final String subString = corbaURI.substring(index + 1, corbaURI.length());
		try {
			final Integer portNumber = Integer.valueOf(subString);
			return (portNumber > 0);
		} catch (final NumberFormatException e) {
			return false;
		}
	}
}

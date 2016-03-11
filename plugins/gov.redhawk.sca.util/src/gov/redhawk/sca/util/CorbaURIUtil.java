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

package gov.redhawk.sca.util;

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
		if (!corbaURI.startsWith("corbaname:") && !corbaURI.startsWith("corbaloc:")) {
			// It's not a corbaname or corbaloc URI
			return corbaURI;
		}

		// Extract corbaname: or corbaloc:
		StringBuilder sb = new StringBuilder();
		String tmp = corbaURI;
		int index = tmp.indexOf(':');
		sb.append(tmp.substring(0, index + 1));
		tmp = tmp.substring(index + 1);

		// Extract protocol
		index = tmp.indexOf(':');
		if (index == -1) {
			// Doesn't have the second colon, not a valid URI
			return corbaURI;
		}
		sb.append(tmp.substring(0, index + 1));
		tmp = tmp.substring(index + 1);

		// We only account for a single address. Possible format is like:
		// [ major . minor @ ] host [ : port] [ / key ] [ # stringifiedname ]
		index = tmp.indexOf(':');
		if (index != -1) {
			// Already has a port
			return corbaURI;
		}

		// Append port before the key or stringified name.
		// Need to find the first '/' or '#'
		index = tmp.indexOf('/');
		if (index == -1) {
			index = tmp.indexOf('#');
		} else {
			int index2 = tmp.indexOf('#');
			if (index2 != -1) {
				index = Math.min(index, index2);
			}
		}
		if (index != -1) {
			sb.append(tmp.substring(0, index));
			sb.append(':');
			sb.append(CORBALOC_PORT);
			sb.append(tmp.substring(index));
			return sb.toString();
		}

		// The URI appears to end with the host. Just append the port.
		sb.append(tmp);
		sb.append(':');
		sb.append(CORBALOC_PORT);
		return sb.toString();
	}

	/**
	 * Checks a string to see if it looks like a CORBA URI. If not, it's assumed to be a hostname (possibly with port
	 * number) and the corbaname prefix is prepended.
	 * @since 1.3
	 */
	public static String addDefaultPrefix(String corbaURI) {
		if (!corbaURI.startsWith("corbaname:") && !corbaURI.startsWith("corbaloc:") && !corbaURI.startsWith("IOR:")) {
			corbaURI = "corbaname::" + corbaURI;
		}
		return corbaURI;
	}
}

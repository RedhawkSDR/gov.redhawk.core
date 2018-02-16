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
package gov.redhawk.model.sca.provider;

import org.eclipse.osgi.util.NLS;

/**
 * These UI messages are intended for global consumption.
 * @since 13.0
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "gov.redhawk.model.sca.provider.messages"; //$NON-NLS-1$

	/**
	 * We turn CheckStyle off here because we're using Eclipse's NLS method.
	 */
	// CHECKSTYLE:OFF

	public static String MultiOutPortManualConnectionIDWarning;

	// CHECKSTYLE:ON

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}

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
package gov.redhawk.core.graphiti.ui.internal.preferences;

import org.eclipse.osgi.util.NLS;

// CHECKSTYLE:OFF
public class GraphitiPreferencesNLS extends NLS {
	private static final String BUNDLE_NAME = "gov.redhawk.core.graphiti.ui.internal.preferences.messages"; //$NON-NLS-1$

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, GraphitiPreferencesNLS.class);
	}

	// ==============================================================================
	// Preference Pages
	// ==============================================================================

	// SAD Port Statistics Pref Page
	public static String PortStatPreference_errorGroupTitle;
	public static String PortStatPreference_errorGroupToolTip;
	public static String PortStatPreference_errorQueueFlush;
	public static String PortStatPreference_errorQueueFlushToolTip;
	public static String PortStatPreference_errorQueueFlushError;

	public static String PortStatPreference_warningGroupTitle;
	public static String PortStatPreference_warningGroupToolTip;
	public static String PortStatPreference_warningQueueLevel;
	public static String PortStatPreference_warningQueueLevelToolTip;
	public static String PortStatPreference_warningQueueLevelError;
	public static String PortStatPreference_warningNoData;
	public static String PortStatPreference_warningNoDataToolTip;
	public static String PortStatPreference_warningNoDataError;
}

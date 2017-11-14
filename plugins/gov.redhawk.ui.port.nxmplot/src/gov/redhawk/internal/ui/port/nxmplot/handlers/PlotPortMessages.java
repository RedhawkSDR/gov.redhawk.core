/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.internal.ui.port.nxmplot.handlers;

import org.eclipse.osgi.util.NLS;

public class PlotPortMessages extends NLS {

	private static final String BUNDLE_NAME = "gov.redhawk.internal.ui.port.nxmplot.handlers.messages";
	public static String PlotPortHandler_MULTIPLE_PORTS_WARNING_TITLE; // SUPPRESS CHECKSTYLE StaticVariableNames
	public static String PlotPortHandler_MULTIPLE_PORTS_WARNING_MSG; // SUPPRESS CHECKSTYLE StaticVariableNames
	static {
		// initialize resource bundle
		NLS.initializeMessages(PlotPortMessages.BUNDLE_NAME, PlotPortMessages.class);
	}

}

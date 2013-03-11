/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.internal.ui.port.nxmplot.handlers;

import gov.redhawk.ui.port.IPortHandler;
import gov.redhawk.ui.port.nxmplot.PlotActivator;

import java.util.List;

public class PlotPortHandler implements IPortHandler {

	public PlotPortHandler() {
	}

	public void connect(final List< ? > portList) {
		PlotActivator.getDefault().addPlot(portList, null);
	}
	
	public void connect(final List< ? > portList, final String filter) {
		if (filter != null && filter.startsWith(IPortHandler.FILTER_PLOT)) {
			connect(portList);
		}
	}
}

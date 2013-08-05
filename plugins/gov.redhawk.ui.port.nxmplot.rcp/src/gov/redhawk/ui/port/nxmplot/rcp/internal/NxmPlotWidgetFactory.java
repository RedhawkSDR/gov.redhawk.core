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
package gov.redhawk.ui.port.nxmplot.rcp.internal;

import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.INxmPlotWidgetFactory;
import gov.redhawk.ui.port.nxmplot.rcp.RcpNxmPlotWidget;

import org.eclipse.swt.widgets.Composite;

/**
 * @since 2.2
 */
public class NxmPlotWidgetFactory implements INxmPlotWidgetFactory {
	public NxmPlotWidgetFactory() {

	}
	
	public AbstractNxmPlotWidget createPlotWidget(Composite parent, int style) {
		return new RcpNxmPlotWidget(parent, style);
	}
}

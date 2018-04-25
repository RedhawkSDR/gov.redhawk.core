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
package gov.redhawk.ui.port.nxmplot;

import org.eclipse.ui.services.IDisposable;

/**
 * @since 4.4
 */
public interface IPlotView extends IDisposable {

	/**
	 * The type of plot to show. If unspecified, the advanced plot wizard is shown. The plot types are enumerated by
	 * {@link gov.redhawk.ui.port.nxmplot.PlotType}.
	 */
	String PARAM_PLOT_TYPE = "gov.redhawk.ui.port.nxmplot.type";
	String PARAM_ISFFT = "gov.redhawk.ui.port.nxmplot.isFft";
	String PARAM_CONNECTION_ID = "gov.redhawk.ui.port.nxmplot.connectionID";
	String PARAM_SECONDARY_ID = "gov.redhawk.ui.port.nxmplot.secondaryID";
	String COMMAND_ID = "gov.redhawk.ui.port.nxmplot.command.plot";

	/** the ID of the plot view extension */
	public static final String ID = PlotActivator.VIEW_PLOT_2;

	public PlotPageBook2 getPlotPageBook();

	public void setPartName(String partName);

	public void setTitleToolTip(String toolTip);

}

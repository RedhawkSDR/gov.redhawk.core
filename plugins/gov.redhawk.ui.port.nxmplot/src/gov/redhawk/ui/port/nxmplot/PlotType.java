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

import java.util.Arrays;
import java.util.List;

/**
 * @since 3.0
 */
public enum PlotType {
	LINE,
	RASTER,
	CONTOUR,
	MESH,
	POINT,
	DOT,
	/**
	 * Select an appropriate default based on the port type.
	 * @since 6.0
	 */
	DEFAULT;

	/**
	 * @since 4.4
	 */
	public static List<PlotType> getStandardPlotTypes() {
		return Arrays.asList(PlotType.DOT, PlotType.LINE, PlotType.POINT, PlotType.RASTER);
	}
}

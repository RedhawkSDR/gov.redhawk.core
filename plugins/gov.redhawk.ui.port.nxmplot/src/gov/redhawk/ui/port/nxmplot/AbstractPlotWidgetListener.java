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

/**
 * @since 3.0
 *
 */
public class AbstractPlotWidgetListener implements IPlotWidgetListener {

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.IPlotWidgetListener#motion(double, double, double)
	 */
	@Override
	public void motion(double x, double y, double t) {
		
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.IPlotWidgetListener#click(double, double, double)
	 */
	@Override
	public void click(double x, double y, double t) {
		
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.IPlotWidgetListener#zoomIn(double, double, double, double, java.lang.Object)
	 */
	@Override
	public void zoomIn(double xmin, double ymin, double xmax, double ymax, Object data) {
		
	}
	
	/**
     * @since 4.0
     */
	@Override
	public void zoomX(double xmin, double ymin, double xmax, double ymax, Object data) {
		
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.IPlotWidgetListener#dragBox(double, double, double, double)
	 */
	@Override
	public void dragBox(double xmin, double ymin, double xmax, double ymax) {
		
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.IPlotWidgetListener#zoomOut(double, double, double, double, java.lang.Object)
	 */
	@Override
	public void zoomOut(double x1, double y1, double x2, double y2, Object data) {

	}
	
	/**
     * @since 4.0
     */
	@Override
	public void unzoom(double x1, double y1, double x2, double y2, Object data) {

	}

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.IPlotWidgetListener#pan(double, double, double, double)
	 */
	@Override
	public void pan(double x1, double y1, double x2, double y2) {
		
	}

}

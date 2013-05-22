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
 */
public interface IPlotWidgetListener {
	
	/**
	 * Called when the mouse is moved on the plot.
	 * 
	 * @param x the x position of the mouse
	 * @param y the y position of the mouse
	 * @param t the t position of the mouse
	 */
	void motion(double x, double y, double t);

	/**
	 * Called when the mouse is clicked on the plot.
	 * 
	 * @param x the x position of the mouse click
	 * @param y the y position of the mouse click
	 * @param t the t position of the mouse click
	 */
	void click(double x, double y, double t);

	/**
	 * Called when a zoomIn takes place.
	 * 
	 * @param xmin min x position of the zoom box
	 * @param ymin min y position of the zoom box
	 * @param xmax max x position of the zoom box
	 * @param ymax max y position of the zoom box
	 * @param data the data object from the message, used for forwarding messages to other plots
	 */
	void zoomIn(double xmin, double ymin, double xmax, double ymax, Object data);
	
	/**
	 * Called when a zoomX takes place.
	 * 
	 * @param xmin min x position of the zoom box
	 * @param ymin min y position of the zoom box
	 * @param xmax max x position of the zoom box
	 * @param ymax max y position of the zoom box
	 * @param data the data object from the message, used for forwarding messages to other plots
	 * @since 4.0
	 */
	void zoomX(double xmin, double ymin, double xmax, double ymax, Object data);

	/**
	 * Called when a box is dragged on the plot.
	 * 
	 * @param xmin min x position 1 of the box
	 * @param ymin min y position 1 of the box
	 * @param xmax max x position 2 of the box
	 * @param ymax max y position 2 of the box
	 */
	void dragBox(double xmin, double ymin, double xmax, double ymax);

	/**
	 * Called when a zoom out is requested.
	 * 
	 * @param x1 x position 1 of the resulting zoom out
	 * @param y1 y position 1 of the resulting zoom out
	 * @param x2 x position 2 of the resulting zoom out
	 * @param y2 y position 2 of the resulting zoom out
	 * @param data the data object from the message, used for forwarding messages to other plots

	 */
	void zoomOut(double x1, double y1, double x2, double y2, Object data);
	
	/**
	 * Called when an unzoom is requested.
	 * 
	 * @param x1 x position 1 of the resulting unzoom
	 * @param y1 y position 1 of the resulting unzoom
	 * @param x2 x position 2 of the resulting unzoom
	 * @param y2 y position 2 of the resulting unzoom
	 * @param data the data object from the message, used for forwarding messages to other plots
	 * @since 4.0
	 */
	void unzoom(double x1, double y1, double x2, double y2, Object data);

	/**
	 * Called when the plot is panned.
	 * 
	 * @param x1 x position 1 of the pan
	 * @param y1 y position 1 of the pan
	 * @param x2 x position 2 of the pan
	 * @param y2 y position 2 of the pan
	 */
	void pan(double x1, double y1, double x2, double y2);

}

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
package gov.redhawk.ui.port;

/**
 * @since 5.0
 */
public interface IPlotListener {

	/**
	 * Called when the plot is disposed.
	 * 
	 * @param id the ID of the plot being disposed
	 */
	void dispose(String id);

	/**
	 * Called when the mouse is clicked on the plot.
	 * 
	 * @param id the id of the plot that was clicked
	 * @param x the x position of the mouse click
	 * @param y the y position of the mouse click
	 */
	void click(String id, double x, double y);

	/**
	 * Called when a zoom takes place.
	 * 
	 * @param id the id of the plot that was clicked
	 * @param xmin min x position of the zoom box
	 * @param ymin min y position of the zoom box
	 * @param xmax max x position of the zoom box
	 * @param ymax max y position of the zoom box
	 */
	void zoomIn(String id, double xmin, double ymin, double xmax, double ymax);

	/**
	 * Called when a box is dragged on the plot.
	 * 
	 * @param id the id of the plot that was clicked
	 * @param xmin min x position 1 of the box
	 * @param ymin min y position 1 of the box
	 * @param xmax max x position 2 of the box
	 * @param ymax max y position 2 of the box
	 */
	void dragBox(String id, double xmin, double ymin, double xmax, double ymax);

	/**
	 * Called when a zoom out is requested.
	 * 
	 * @param id the id of the plot that was clicked
	 * @param x1 x position 1 of the resulting zoom out
	 * @param y1 y position 1 of the resulting zoom out
	 * @param x2 x position 2 of the resulting zoom out
	 * @param y2 y position 2 of the resulting zoom out
	 */
	void zoomOut(String id, double x1, double y1, double x2, double y2);

	/**
	 * Called when the plot is panned.
	 * 
	 * @param id the id of the plot that was clicked
	 * @param x1 x position 1 of the pan
	 * @param y1 y position 1 of the pan
	 * @param x2 x position 2 of the pan
	 * @param y2 y position 2 of the pan
	 */
	void pan(String id, double x1, double y1, double x2, double y2);

}

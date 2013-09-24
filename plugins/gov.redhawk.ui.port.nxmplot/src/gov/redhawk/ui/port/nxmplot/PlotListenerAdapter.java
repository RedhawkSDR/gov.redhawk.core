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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @since 3.0
 */
public class PlotListenerAdapter implements IPlotWidgetListener {
		private final Set<IPlotWidgetListener> listenerList = Collections.synchronizedSet(new HashSet<IPlotWidgetListener>());
		private final PlotMessageAdapter adapter = new PlotMessageAdapter(this);

		public PlotListenerAdapter() {
		}
		
		public Set<IPlotWidgetListener> getListenerList() {
	        return listenerList;
        }
		
		public PlotMessageAdapter getAdapter() {
	        return adapter;
        }
		
		@Override
		public void motion(double x, double y, double t) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).motion(x, y, t);
			}
		}

		@Override
		public void click(double x, double y, double t) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).click(x, y, t);
			}
		}

		@Override
		public void zoomIn(double xmin, double ymin, double xmax, double ymax, Object data) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).zoomIn(xmin, ymin, xmax, ymax, data);
			}
		}
		
		/**
         * @since 4.0
         */
		@Override
		public void zoomX(double xmin, double ymin, double xmax, double ymax, Object data) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).zoomX(xmin, ymin, xmax, ymax, data);
			}
		}

		@Override
		public void dragBox(double xmin, double ymin, double xmax, double ymax) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).dragBox(xmin, ymin, xmax, ymax);
			}
		}

		/**
         * @since 3.0
         */
		@Override
		public void zoomOut(double x1, double y1, double x2, double y2, Object data) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).zoomOut(x1, y1, x2, y2, data);
			}
		}
		
		/**
         * @since 4.0
         */
		@Override
		public void unzoom(double x1, double y1, double x2, double y2, Object data) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).unzoom(x1, y1, x2, y2, data);
			}
		}

		@Override
		public void pan(double x1, double y1, double x2, double y2) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).pan(x1, y1, x2, y2);
			}
		}
	}
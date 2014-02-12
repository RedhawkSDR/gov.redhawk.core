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
package gov.redhawk.ui.port.nxmplot;

import gov.redhawk.model.sca.util.RedhawkEvents;

/**
 * @since 4.4
 * 
 */
public class PlotEvent {

	public interface EventTags {
		public static final String TOPIC = RedhawkEvents.UI_TOPIC_BASE + "/plot";
		public static final String SOURCE_VIEW = "sourceView";
		public static final String SOURCE_VIEW_ID = "sourceViewID";
		public static final String SOURCE_VIEW_SECONDARY_ID = "secondaryID";
		public static final String PLOT_EVENT = "plotEvent";
	}

	public static class ZoomX extends PlotEvent {
		public final double xmin, ymin, xmax, ymax;

		public ZoomX(AbstractNxmPlotWidget source, Object data, double xmin, double ymin, double xmax, double ymax) {
			super(source, data);
			this.xmin = xmin;
			this.ymin = ymin;
			this.xmax = xmax;
			this.ymax = ymax;
		}
	}

	public static class ZoomOut extends PlotEvent {
		public final double x1, y1, x2, y2;

		public ZoomOut(AbstractNxmPlotWidget source, Object data, double x1, double y1, double x2, double y2) {
			super(source, data);
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

	}

	public static class ZoomIn extends PlotEvent {
		public final double xmin, ymin, xmax, ymax;

		public ZoomIn(AbstractNxmPlotWidget source, Object data, double xmin, double ymin, double xmax, double ymax) {
			super(source, data);
			this.xmin = xmin;
			this.ymin = ymin;
			this.xmax = xmax;
			this.ymax = ymax;
		}

	}

	public static class Unzoom extends PlotEvent {
		public final double x1, y1, x2, y2;

		public Unzoom(AbstractNxmPlotWidget source, Object data, double x1, double y1, double x2, double y2) {
			super(source, data);
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

	}

	public static class Pan extends PlotEvent {
		public final double x1, y1, x2, y2;

		public Pan(AbstractNxmPlotWidget source, Object data, double x1, double y1, double x2, double y2) {
			super(source, data);
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	public static class Motion extends PlotEvent {
		public final double x, y, t;

		public Motion(AbstractNxmPlotWidget source, Object data, double x, double y, double t) {
			super(source, data);
			this.x = x;
			this.y = y;
			this.t = t;
		}

	}

	public static class DragBox extends PlotEvent {
		public final double xmin, ymin, xmax, ymax;

		public DragBox(AbstractNxmPlotWidget source, Object data, double xmin, double ymin, double xmax, double ymax) {
			super(source, data);
			this.xmin = xmin;
			this.ymin = ymin;
			this.xmax = xmax;
			this.ymax = ymax;
		}

	}

	public static class Click extends PlotEvent {
		public final double x, y, t;

		public Click(AbstractNxmPlotWidget source, Object data, double x, double y, double t) {
			super(source, data);
			this.x = x;
			this.y = y;
			this.t = t;
		}

	}

	public final AbstractNxmPlotWidget source;
	public final Object data;

	public PlotEvent(AbstractNxmPlotWidget source, Object data) {
		super();
		this.source = source;
		this.data = data;
	}

}

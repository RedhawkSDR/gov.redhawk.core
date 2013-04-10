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
		
		public void motion(double x, double y, double t) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).motion(x, y, t);
			}
		}

		public void click(double x, double y, double t) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).click(x, y, t);
			}
		}

		public void zoomIn(double xmin, double ymin, double xmax, double ymax, Object data) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).zoomIn(xmin, ymin, xmax, ymax, data);
			}
		}
		
		/**
         * @since 4.0
         */
		public void zoomX(double xmin, double ymin, double xmax, double ymax, Object data) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).zoomX(xmin, ymin, xmax, ymax, data);
			}
		}

		public void dragBox(double xmin, double ymin, double xmax, double ymax) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).dragBox(xmin, ymin, xmax, ymax);
			}
		}

		/**
         * @since 3.0
         */
		public void zoomOut(double x1, double y1, double x2, double y2, Object data) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).zoomOut(x1, y1, x2, y2, data);
			}
		}
		
		/**
         * @since 4.0
         */
		public void unzoom(double x1, double y1, double x2, double y2, Object data) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).unzoom(x1, y1, x2, y2, data);
			}
		}

		public void pan(double x1, double y1, double x2, double y2) {
			for (IPlotWidgetListener obj : listenerList) {
				((IPlotWidgetListener) obj).pan(x1, y1, x2, y2);
			}
		}
	}
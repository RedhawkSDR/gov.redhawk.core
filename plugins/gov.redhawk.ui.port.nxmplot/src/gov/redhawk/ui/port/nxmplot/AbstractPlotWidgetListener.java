/**
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
	public void motion(double x, double y, double t) {
		
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.IPlotWidgetListener#click(double, double, double)
	 */
	public void click(double x, double y, double t) {
		
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.IPlotWidgetListener#zoomIn(double, double, double, double, java.lang.Object)
	 */
	public void zoomIn(double xmin, double ymin, double xmax, double ymax, Object data) {
		
	}
	
	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.IPlotWidgetListener#zoomX(double, double, double, double, java.lang.Object)
	 */
	public void zoomX(double xmin, double ymin, double xmax, double ymax, Object data) {
		
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.IPlotWidgetListener#dragBox(double, double, double, double)
	 */
	public void dragBox(double xmin, double ymin, double xmax, double ymax) {
		
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.IPlotWidgetListener#zoomOut(double, double, double, double, java.lang.Object)
	 */
	public void zoomOut(double x1, double y1, double x2, double y2, Object data) {

	}
	
	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.IPlotWidgetListener#unzoom(double, double, double, double, java.lang.Object)
	 */
	public void unzoom(double x1, double y1, double x2, double y2, Object data) {

	}

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.IPlotWidgetListener#pan(double, double, double, double)
	 */
	public void pan(double x1, double y1, double x2, double y2) {
		
	}

}

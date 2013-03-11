package gov.redhawk.ui.port.nxmplot;

import org.eclipse.swt.widgets.Composite;

/**
 * @since 3.0
 */
public interface INxmPlotWidgetFactory {
	
	/**
	 * Creates a plot
	 * Pass configuration paramters to the plot in the init call.  Recommend to pass arguments to init via a job.
	 * @param parent
	 * @param style
	 * @return
	 */
	public AbstractNxmPlotWidget createPlotWidget(final Composite parent, int style);
}

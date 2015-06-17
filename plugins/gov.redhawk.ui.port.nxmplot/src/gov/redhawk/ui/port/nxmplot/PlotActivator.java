/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.ui.port.nxmplot;

import gov.redhawk.internal.ui.port.nxmplot.handlers.PlotPortHandler;
import gov.redhawk.internal.ui.port.nxmplot.view.PlotView2;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The activator class controls the plug-in life cycle.
 * @since 2.1
 */
public class PlotActivator extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "gov.redhawk.ui.port.nxmplot";

	/**
	 * @since 4.3
	 */
	public static final String VIEW_PLOT_2 = PlotView2.ID;

	private static PlotActivator plugin;

	private ServiceTracker<INxmPlotWidgetFactory, INxmPlotWidgetFactory> plotFactory;

	public PlotActivator() {
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		PlotActivator.plugin = this;
		plotFactory = new ServiceTracker<INxmPlotWidgetFactory, INxmPlotWidgetFactory>(getBundle().getBundleContext(), INxmPlotWidgetFactory.class, null);
		plotFactory.open(true);
	}

	/**
	 * @since 3.0
	 */
	public INxmPlotWidgetFactory getPlotFactory() {
		INxmPlotWidgetFactory retVal = plotFactory.getService();
		if (retVal == null) {
			throw new IllegalStateException("No Nxm Plot Widget Factory");
		}
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		if (plotFactory != null) {
			plotFactory.close();
			plotFactory = null;
		}
		PlotActivator.plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 *
	 * @return the shared instance
	 */
	public static PlotActivator getDefault() {
		return PlotActivator.plugin;
	}

	/**
	 * @since 4.4
	 */
	public IPlotView showPlotView(ExecutionEvent event) {
		return PlotPortHandler.showView(event);
	}
}

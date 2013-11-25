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

import gov.redhawk.internal.ui.port.nxmplot.view.PlotView;
import gov.redhawk.internal.ui.port.nxmplot.view.PlotView2;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.ui.port.PortHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.UIJob;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The activator class controls the plug-in life cycle.
 * @since 2.1
 */
public class PlotActivator extends AbstractUIPlugin {

	/** The Constant PLUGIN_ID. */
	public static final String PLUGIN_ID = "gov.redhawk.ui.port.nxmplot";

	/**
	 * Use {@link #VIEW_PLOT_2} instead
	 * @deprecated since 4.2
	 */
	@Deprecated
	public static final String VIEW_PLOT = PlotView.ID;

	/**
	 * @since 4.2
	 */
	public static final String VIEW_PLOT_2 = PlotView2.ID;

	/** The plugin. */
	private static PlotActivator plugin;

	private final ISchedulingRule plotRule;

	private ServiceTracker<INxmPlotWidgetFactory, INxmPlotWidgetFactory> plotFactory;

	/**
	 * The constructor.
	 */
	public PlotActivator() {
		this.plotRule = new PlotSchedulingRule();
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
	 * This will connect to a given port and process the data coming out of it.
	 * The list passed in must be of either ScaUsesPort or
	 * CorbaConnectionSettings, anything else will not be processed.
	 *
	 * @param portList A list of ScaUsesPorts or CorbaConnectionSettings to plot
	 * @param fft the settings to be used for FFTing the data before plotting.
	 *        This may be null if no FFT is desired.
	 * @deprecated
	 */
	@Deprecated
	public String addPlot(final List< ? > portList, final FftSettings fft) {
		final List<ScaUsesPort> ports = new ArrayList<ScaUsesPort>();
		final List<CorbaConnectionSettings> ports2 = new ArrayList<CorbaConnectionSettings>();
		final UUID sessionId = UUID.randomUUID();
		for (final Object port : portList) {
			if (port instanceof ScaUsesPort) {
				ports.add((ScaUsesPort) port);
			} else if (port instanceof CorbaConnectionSettings) {
				ports2.add((CorbaConnectionSettings) port);
			}
		}

		final UIJob job = new UIJob("Starting Port Data Plot") {
			@Override
			public IStatus runInUIThread(final IProgressMonitor monitor) {
				monitor.beginTask("Opening Port Data View", IProgressMonitor.UNKNOWN);
				final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				if (page != null) {
					PlotView view;
					try {
						view = (PlotView) page.showView(PlotView.ID);
						if (!ports.isEmpty()) {
							/** change these parameters to test individual plots vs a PlotPageBoook with Line and raster plots */
							view.createNewPlotTab(ports, fft, sessionId, true, null);
						} else if (!ports2.isEmpty()) {
							view.createNewPlotTabWithSettings(ports2, fft, sessionId, true, null);
						}
						PortHelper.refreshPorts(portList, monitor);
					} catch (final PartInitException e) {
						getLog().log(new Status(IStatus.ERROR, PlotActivator.PLUGIN_ID, "Error finding Plot View", e));
					}
				}
				return Status.OK_STATUS;
			}
		};
		job.setSystem(true);
		job.setRule(this.plotRule);
		job.schedule();

		return sessionId.toString();
	}

	/**
	 * Adds a plot click listener to this plot.
	 *
	 * @param listener the listener to add
	 * @param sessionId the id of the plot session to listen to
	 * @since 3.0
	 * @deprecated
	 */
	@Deprecated
	public void addPlotListener(final IPlotWidgetListener listener, final String sessionId) {
		final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if (page != null) {
			final PlotView view = (PlotView) page.findView(PlotView.ID);
			if (view != null) {
				final Job job = new Job("Add Plot Listener") {
					@Override
					protected IStatus run(final IProgressMonitor monitor) {
						view.addPlotListener(listener, sessionId);
						return Status.OK_STATUS;
					}
				};
				job.setRule(this.plotRule);
				job.setSystem(true);
				job.schedule();
			}
		}
	}

	/**
	 * Removes a plot click listener.
	 *
	 * @param listener the listener to remove
	 * @param sessionId the id of the plot session to stop listening to
	 * @since 3.0
	 * @deprecated
	 */
	@Deprecated
	public void removePlotListener(final IPlotWidgetListener listener, final String sessionId) {
		final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		if (page != null) {
			final PlotView view = (PlotView) page.findView(PlotView.ID);
			if (view != null) {
				final Job job = new Job("Remove Plot Listener") {
					@Override
					protected IStatus run(final IProgressMonitor monitor) {
						view.removePlotListener(listener, sessionId);
						return Status.OK_STATUS;
					}
				};
				job.setRule(this.plotRule);
				job.setSystem(true);
				job.schedule();
			}
		}
	}

	private static class PlotSchedulingRule implements ISchedulingRule {

		@Override
		public boolean contains(final ISchedulingRule rule) {
			return rule == this;
		}

		@Override
		public boolean isConflicting(final ISchedulingRule rule) {
			return contains(rule);
		}

	}

}

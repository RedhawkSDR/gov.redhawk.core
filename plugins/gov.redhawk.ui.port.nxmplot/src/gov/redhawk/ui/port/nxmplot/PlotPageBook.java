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

import gov.redhawk.model.sca.ScaUsesPort;

import java.util.List;
import java.util.UUID;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.PageBook;

public class PlotPageBook extends Composite {

	/**
     * @since 3.0
     */
	protected String plotQualifiers;

	/** The page book. */
	private PageBook pageBook;

	/** The next midas plot Line. */
	private AbstractNxmPlotWidget nxmPlotWidgetLine;

	/** The next midas plot raster. */
	private AbstractNxmPlotWidget nxmPlotWidgetRaster;

	/** The current state of the raster being visible. */
	private boolean rasterShowing;

	private List<CorbaConnectionSettings> connectionSettings;

	private List< ? extends ScaUsesPort> ports;

	private FftSettings fftSettings;

	private final PlotMessageAdapter adapter;

	private PlotListenerAdapter listenerAdapter;

	private List<IPlotSession> linePlotSessions;

	private List<IPlotSession> rasterPlotSessions;

	public PlotPageBook(Composite parent, int style, List<CorbaConnectionSettings> connList, FftSettings fft, List< ? extends ScaUsesPort> ports, UUID sessionId) {
		super(parent, style);
		setLayout(new FillLayout());
		listenerAdapter = new PlotListenerAdapter();
		adapter = new PlotMessageAdapter(listenerAdapter);
		this.pageBook = new PageBook(this, SWT.NONE);
		this.rasterShowing = false;
		parent.addDisposeListener(new DisposeListener() {

			public void widgetDisposed(DisposeEvent e) {
				dispose();
			}

		});

		plotPorts(connList, fft, ports, sessionId, check2d(connList));

	}

	private boolean check2d(final List<CorbaConnectionSettings> connList) {
		boolean is2d = false;
		for (final CorbaConnectionSettings settings : connList) {
			if (settings.is2D()) {
				is2d = true;
				break;
			}
		}
		return is2d;
	}

	/**
     * @since 3.0
     */
	public void setPlotQualifiers(String plotQualifiers) {
	    this.plotQualifiers = plotQualifiers;
    }

	/**
	 * This method clears any existing plots and plots the passed in ports or
	 * connections.
	 *
	 * @param connList a list of the settings for acquiring the data - may be null
	 * @param fft settings to use if an FFT is to be displayed
	 * @param ports list of ScaPort object to plot the output from.
	 * @param sessionId the unique ID of this plot instance - used for listeners
	 * @param createRaster true to force the creation of the raster plot
	 */
	public void plotPorts(final List<CorbaConnectionSettings> inputConnList, final FftSettings fft, final List< ? extends ScaUsesPort> ports, final UUID sessionId,
			final boolean createRaster) {
		disposeNxmResources();
		final String linePlotArgs = "TYPE=LINE AXIS=+GRID OPTIONS=BStore SCALE=AutoMin|AutoMax AUTOL=16";
		final String linePlotSwitches = "/RT/NICE";

		this.nxmPlotWidgetLine = PlotActivator.getDefault().getPlotFactory().createPlotWidget(this.pageBook, SWT.None);
		this.nxmPlotWidgetLine.addMessageHandler(this.adapter);

		final List<CorbaConnectionSettings> connList;
		if (inputConnList == null || inputConnList.isEmpty()) {
			connList = NxmPlotUtil.createConnList(ports);
		} else {
			connList = inputConnList;
		}
		final boolean is2d = createRaster || check2d(connList);
		final String rasterPlotArgs = "TYPE=RASTER View=iYX SCALE=AutoMin|AutoMax AUTOL=16";
		final String rasterPlotSwitches = "/LPS=200/RT/NICE";
		if (is2d) {
			this.nxmPlotWidgetRaster = PlotActivator.getDefault().getPlotFactory().createPlotWidget(this.pageBook, SWT.None);
			this.pageBook.setTabList(new Control[] { this.nxmPlotWidgetLine, this.nxmPlotWidgetRaster });
			this.nxmPlotWidgetRaster.addMessageHandler(this.adapter);
		} else {
			this.pageBook.setTabList(new Control[] { this.nxmPlotWidgetLine });
		}

		this.pageBook.showPage(this.pageBook.getTabList()[this.rasterShowing ? 1 : 0]); // SUPPRESS CHECKSTYLE AvoidInline
		nxmPlotWidgetLine.initPlot(linePlotSwitches, linePlotArgs);
		PlotPageBook.this.linePlotSessions = NxmPlotUtil.addSource(connList, fft, PlotPageBook.this.nxmPlotWidgetLine, plotQualifiers);
		if (nxmPlotWidgetRaster != null) {
			nxmPlotWidgetRaster.initPlot(rasterPlotSwitches, rasterPlotArgs);
			PlotPageBook.this.rasterPlotSessions = NxmPlotUtil.addSource(connList, fft, PlotPageBook.this.nxmPlotWidgetRaster, plotQualifiers);
		}

	}

	/**
	 * Toggle if the raster is visible or not.
	 *
	 * @param enable true if the raster should be shown
	 */
	public void showRaster(final boolean enable) {
		if (this.nxmPlotWidgetRaster != null) {
			this.rasterShowing = enable;
			if (this.rasterShowing) {
				this.pageBook.showPage(this.pageBook.getTabList()[1]);
			} else {
				this.pageBook.showPage(this.pageBook.getTabList()[0]);
			}
		}
	}

	/**
	 * Detect if the raster is currently displayed.
	 *
	 * @return true if the raster is showing
	 */
	public boolean isRasterShowing() {
		return (this.nxmPlotWidgetRaster != null) && this.rasterShowing;
	}

	public void disposeNxmResources() {
		if (nxmPlotWidgetLine != null) {
			nxmPlotWidgetLine.dispose();
			nxmPlotWidgetLine = null;
		}

		if (nxmPlotWidgetRaster != null) {
			nxmPlotWidgetRaster.dispose();
			nxmPlotWidgetRaster = null;
		}

		if (linePlotSessions != null) {
			for (IPlotSession session : linePlotSessions) {
				session.dispose();
			}
			linePlotSessions = null;
		}
		if (rasterPlotSessions != null) {
			for (IPlotSession session : rasterPlotSessions) {
				session.dispose();
			}
			rasterPlotSessions = null;
		}
	}

	@Override
	public void dispose() {
		if (isDisposed()) {
			return;
		}

		disposeNxmResources();
		if (this.pageBook != null) {
			this.pageBook = null;
		}

		super.dispose();
	}

	/**
	 * This method is used to check if the plot page contains a Raster plot.
	 *
	 * @return true if this page plots a raster
	 */
	public boolean isRasterable() {
		return this.nxmPlotWidgetRaster != null;
	}

	/**
	 * @since 3.0
	 */
	public AbstractNxmPlotWidget getNxmPlotWidgetRaster() {
		return this.nxmPlotWidgetRaster;
	}

	/**
	 * @since 3.0
	 */
	public AbstractNxmPlotWidget getNxmPlotWidgetLine() {
		return this.nxmPlotWidgetLine;
	}

	/**
	 * @since 3.0
	 */
	public AbstractNxmPlotWidget getActivePlotWidget() {
		if (isRasterShowing()) {
			return getNxmPlotWidgetRaster();
		} else {
			return getNxmPlotWidgetLine();
		}
	}

	/**
	 * @since 3.0
	 */
	public void addPlotListener(final IPlotWidgetListener listener) {
		this.listenerAdapter.getListenerList().add(listener);
	}

	/**
	 * @since 3.0
	 */
	public void removePlotListener(final IPlotWidgetListener listener) {
		this.listenerAdapter.getListenerList().remove(listener);
	}

	public List<CorbaConnectionSettings> getConnectionList() {
		return this.connectionSettings;
	}

	public List< ? extends ScaUsesPort> getPorts() {
		return this.ports;
	}

	public FftSettings getFft() {
		return this.fftSettings;
	}

}

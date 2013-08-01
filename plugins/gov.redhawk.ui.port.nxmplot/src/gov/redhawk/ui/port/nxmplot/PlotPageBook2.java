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

import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.util.DceUuidUtil;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.PageBook;

/**
 * @since 4.2
 */
public class PlotPageBook2 extends Composite {

	private static class PlotSession {
		private final AbstractNxmPlotWidget plot;
		private final Map<PlotSource, IPlotSession> sessionMap = new HashMap<PlotSource, IPlotSession>();

		public PlotSession(AbstractNxmPlotWidget plot, String plotArgs, String plotSwitches) {
			this.plot = plot;
			this.plot.initPlot(plotSwitches, plotArgs);
		}

		private synchronized void addSource(PlotSource newSource) {
			sessionMap.put(newSource, NxmPlotUtil.addSource(newSource, plot));
		}

		private synchronized void removeSource(PlotSource source) {
			IPlotSession session = sessionMap.get(source);
			if (session != null) {
				session.dispose();
			}
		}

		public synchronized void dispose() {
			for (IPlotSession session : sessionMap.values()) {
				session.dispose();
			}
			sessionMap.clear();
			plot.dispose();
		}
	}

	/** The page book. */
	private final PageBook pageBook;

	/** The plots. */
	private Map<PlotType, PlotSession> plots = new HashMap<PlotType, PlotSession>();

	private final PlotListenerAdapter listenerAdapter = new PlotListenerAdapter();

	private final PlotMessageAdapter adapter = new PlotMessageAdapter(listenerAdapter);

	private List<PlotSource> sources = Collections.synchronizedList(new ArrayList<PlotSource>());
	
	private Adapter portListener = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			switch(msg.getFeatureID(IDisposable.class)) {
			case ScaPackage.IDISPOSABLE__DISPOSED:
				if (!isDisposed()) {
					PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
						public void run() {
							dispose();
						}
						
					});
				}
				break;
			default:
				break;
			}
		}
	};

	private PlotType currentType;

	private Composite nullPage;

	public PlotPageBook2(Composite parent, int style, PlotType type) {
		super(parent, style);
		setLayout(new FillLayout());
		this.pageBook = new PageBook(this, SWT.NONE);
		parent.addDisposeListener(new DisposeListener() {

			public void widgetDisposed(DisposeEvent e) {
				dispose();
			}

		});

		nullPage = new Composite(pageBook, SWT.None);

		showPlot(type);
	}

	/**
	 * @since 4.2
	 */
	public PlotPageBook2(Composite parent, int style) {
		this(parent, style, null);
	}

	/**
	 * @since 4.2
	 */
	protected PlotSession createPlot(PlotType type) {
		AbstractNxmPlotWidget newPlot = PlotActivator.getDefault().getPlotFactory().createPlotWidget(this.pageBook, SWT.None);
		newPlot.addMessageHandler(this.adapter);

		final String plotArgs = NxmPlotUtil.getDefaultPlotArgs(type);
		final String plotSwitches = NxmPlotUtil.getDefaultPlotSwitches(type);
		PlotSession session = new PlotSession(newPlot, plotArgs, plotSwitches);
		this.plots.put(type, session);

		for (PlotSource source : this.sources) {
			session.addSource(source);
		}

		return session;
	}

	public IPlotSession addSource(final PlotSource plotSource) {
		for (PlotSession session : plots.values()) {
			session.addSource(plotSource);
		}
		this.sources.add(plotSource);
		ScaModelCommand.execute(plotSource.getInput(), new ScaModelCommand() {
			
			public void execute() {
				plotSource.getInput().eAdapters().add(portListener);
			}
		});
		return new IPlotSession() {

			private String id = DceUuidUtil.createDceUUID();

			public void dispose() {
				for (PlotSession session : plots.values()) {
					session.removeSource(plotSource);
				}
			}

			public String getSourceId() {
				return id;
			}
		};
	}

	public IPlotSession addSource(ScaUsesPort port, FftSettings settings, String qualifiers) {
		final PlotSource plotSource = new PlotSource(port, settings, qualifiers);
		return addSource(plotSource);
	}

	/**
	 * Toggle if the raster is visible or not.
	 *
	 * @param enable true if the raster should be shown
	 */
	public void showPlot(PlotType type) {
		if (type == null) {
			pageBook.showPage(nullPage);

		} else {
			PlotSession plotSession = this.plots.get(type);
			if (plotSession == null) {
				plotSession = createPlot(type);
			}
			pageBook.showPage(plotSession.plot);
		}
		this.currentType = type;
	}

	public PlotType getCurrentType() {
		return currentType;
	}

	/**
	 * Detect if the raster is currently displayed.
	 *
	 * @return true if the raster is showing
	 */
	public boolean isShowing(PlotType type) {
		return this.currentType == type;
	}

	@Override
	public void dispose() {
		if (isDisposed()) {
			return;
		}
		super.dispose();
		
		for (PlotSession session : this.plots.values()) {
			session.dispose();
		}
		this.plots.clear();
		
		for (final PlotSource source : sources) {
			ScaModelCommand.execute(source.getInput(), new ScaModelCommand() {
				
				public void execute() {
					source.getInput().eAdapters().remove(listenerAdapter);
				}
			});
		}
		this.sources.clear();
	}

	/**
	 * @since 3.0
	 */
	public AbstractNxmPlotWidget getPlot(PlotType type) {
		PlotSession session = this.plots.get(type);
		if (session != null) {
			return session.plot;
		}
		return null;
	}

	/**
	 * @since 3.0
	 */
	public AbstractNxmPlotWidget getActivePlotWidget() {
		return getPlot(currentType);
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

	public List<PlotSource> getSources() {
		return Collections.unmodifiableList(this.sources);
	}

}

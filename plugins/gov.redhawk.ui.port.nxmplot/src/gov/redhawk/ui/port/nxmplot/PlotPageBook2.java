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

import gov.redhawk.internal.ui.port.nxmplot.view.PlotSource;
import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.util.Debug;
import gov.redhawk.ui.port.PortHelper;
import gov.redhawk.ui.port.nxmblocks.BulkIONxmBlock;
import gov.redhawk.ui.port.nxmblocks.BulkIONxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.BulkIOSddsNxmBlock;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlock;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.PlotNxmBlock;
import gov.redhawk.ui.port.nxmblocks.PlotNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.SddsNxmBlockSettings;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.util.DceUuidUtil;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.PageBook;

import BULKIO.dataSDDSHelper;

/**
 * @since 4.2
 */
public class PlotPageBook2 extends Composite {
	
	private static final Debug TRACE_LOG = new Debug(PlotActivator.PLUGIN_ID, PlotPageBook2.class.getSimpleName());

	private static class PlotPage {
		private final List<INxmBlock< ? >> nxmBlocks = Collections.synchronizedList(new ArrayList<INxmBlock< ? >>());
		private final AbstractNxmPlotWidget plot;
		private final Map<PlotSource, IPlotSession> sessionMap = new HashMap<PlotSource, IPlotSession>();

		public PlotPage(AbstractNxmPlotWidget plot, String plotArgs, String plotSwitches) {
			this.plot = plot;
			this.plot.initPlot(plotSwitches, plotArgs);
		}

		private synchronized void addSource2(final ScaUsesPort scaPort, final FftSettings fftSettings, final String pipeQualifiers) {
			TRACE_LOG.enteringMethod(scaPort, fftSettings, pipeQualifiers);
			final AbstractNxmPlotWidget currentPlotWidget = this.plot;

			INxmBlock<?> startingBlock;
			if (dataSDDSHelper.id().equals(scaPort.getRepid())) {
				SddsNxmBlockSettings sddsSettings = new SddsNxmBlockSettings();
				sddsSettings.setDataByteOrder(ByteOrder.nativeOrder()); // workaround for REDHAWK SinkNic Component
				BulkIOSddsNxmBlock sddsBlock = new BulkIOSddsNxmBlock(currentPlotWidget, scaPort, sddsSettings);
				startingBlock = sddsBlock;
			} else {
				BulkIONxmBlockSettings bbSettings = new BulkIONxmBlockSettings();
				BulkIONxmBlock bulkioBlock = new BulkIONxmBlock(currentPlotWidget, scaPort, bbSettings);
				startingBlock = bulkioBlock;
			}
			TRACE_LOG.trace("PlotPageBook2.addSource(.) startingBlock = {0}", startingBlock);

			PlotNxmBlock plotBlock = new PlotNxmBlock(currentPlotWidget, new PlotNxmBlockSettings());
			if (fftSettings != null) {
				FftNxmBlockSettings fftBlockSettings = toFftNxmBlockSettings(fftSettings);
				FftNxmBlock fftBlock = new FftNxmBlock(currentPlotWidget, fftBlockSettings);
				fftBlock.addInput(startingBlock);
				try {
					fftBlock.start();
					nxmBlocks.add(fftBlock);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();  // SUPPRESS CHECKSTYLE DEBUG
				}

				plotBlock.addInput(fftBlock);
			} else {
				plotBlock.addInput(startingBlock);
			}

			try {
				plotBlock.start();
				startingBlock.start(); // starting block should be last to start
				
				nxmBlocks.add(plotBlock);
				nxmBlocks.add(startingBlock);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); // SUPPRESS CHECKSTYLE DEBUG
			}
			TRACE_LOG.exitingMethod();
		}
		
		private synchronized void addSource(PlotSource newSource) {
			String qualifiers = newSource.getQualifiers();
			if (qualifiers == null) {
				qualifiers = NxmPlotUtil.getDefaultPlotQualifiers(plot.getPlotSettings().getPlotType());
			}
			IPlotSession newSession = NxmPlotUtil.addSource(newSource.getInput(), newSource.getFftOptions(), plot, qualifiers);
			sessionMap.put(newSource, newSession);
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
	private Map<PlotType, PlotPage> plots = new HashMap<PlotType, PlotPage>();

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
						@Override
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

			@Override
			public void widgetDisposed(DisposeEvent e) {
				dispose();
			}

		});

		nullPage = new Composite(pageBook, SWT.None);

		showPlot(type);
	}

	public PlotPageBook2(Composite parent, int style) {
		this(parent, style, null);
	}

	protected PlotPage createPlot(PlotType type) {
		AbstractNxmPlotWidget newPlot = PlotActivator.getDefault().getPlotFactory().createPlotWidget(this.pageBook, SWT.None);
		newPlot.addMessageHandler(this.adapter);

		final String plotArgs = NxmPlotUtil.getDefaultPlotArgs(type);
		final String plotSwitches = NxmPlotUtil.getDefaultPlotSwitches(type);
		PlotPage session = new PlotPage(newPlot, plotArgs, plotSwitches);	
		this.plots.put(type, session);

		for (PlotSource source : this.sources) {
			session.addSource(source);
		}

		return session;
	}

	private IPlotSession addSource(final PlotSource plotSource) {
		for (PlotPage session : plots.values()) {
			session.addSource(plotSource);
		}
		this.sources.add(plotSource);
		ScaModelCommand.execute(plotSource.getInput(), new ScaModelCommand() {
			
			@Override
			public void execute() {
				plotSource.getInput().eAdapters().add(portListener);
			}
		});
		return new IPlotSession() {

			private String id = DceUuidUtil.createDceUUID();

			@Override
			public void dispose() {
				for (PlotPage session : plots.values()) {
					session.removeSource(plotSource);
				}
			}

			@Override
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
	 * @param scaPort
	 * @param fftSettings
	 * @param qualifiers
	 * @since 4.3
	 * @noreference This method is not intended to be referenced by clients
	 */
	public void addSource2(final ScaUsesPort scaPort, FftSettings fftSettings, String qualifiers) {
		
		for (PlotPage plotPage : plots.values()) {
			plotPage.addSource2(scaPort, fftSettings, qualifiers);
		}
		ScaModelCommand.execute(scaPort, new ScaModelCommand() {
			@Override
			public void execute() {
				scaPort.eAdapters().add(portListener);
			}
		});

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
			PlotPage newPlot = this.plots.get(type);
			if (newPlot == null) {
				AbstractNxmPlotWidget oldPlot = getActivePlotWidget();
				newPlot = createPlot(type);
				if (oldPlot != null) {
					PlotSettings existingSettings = new PlotSettings(oldPlot.getPlotSettings());
					existingSettings.setPlotType(type);
					newPlot.plot.applySettings(existingSettings);
				}
			}
			pageBook.showPage(newPlot.plot);
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
		
		for (PlotPage session : this.plots.values()) {
			session.dispose();
		}
		this.plots.clear();
		
		for (final PlotSource source : sources) {
			ScaModelCommand.execute(source.getInput(), new ScaModelCommand() {
				
				@Override
				public void execute() {
					source.getInput().eAdapters().remove(listenerAdapter);
				}
			});
			PortHelper.refreshPort(source.getInput(), null, 1000);
		}
		
		this.sources.clear();
	}

	/**
	 * @since 3.0
	 */
	public AbstractNxmPlotWidget getPlot(PlotType type) {
		PlotPage session = this.plots.get(type);
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

	public List<AbstractNxmPlotWidget> getAllPlotWidgets() {
		List<AbstractNxmPlotWidget> retVal = new ArrayList<AbstractNxmPlotWidget>(plots.size());
		for (PlotPage session :plots.values()) {
			retVal.add(session.plot);
		}
		return retVal;
	}

	@NonNull
	private static FftNxmBlockSettings toFftNxmBlockSettings(FftSettings fftOptions) {
		FftNxmBlockSettings settings = new FftNxmBlockSettings();
		settings.setTransformSize(Integer.parseInt(fftOptions.getTransformSize())); // 1+
		settings.setOverlap(Integer.parseInt(fftOptions.getOverlap()));             // 0-100
		settings.setNumAverages(Integer.parseInt(fftOptions.getNumAverages()));     // 1+
		settings.setWindow(FftNxmBlockSettings.WindowType.valueOf(fftOptions.getWindow().toString()));
		settings.setOutputType(FftNxmBlockSettings.OutputType.valueOf(fftOptions.getOutputType().toString()));
		return settings;
	}

}

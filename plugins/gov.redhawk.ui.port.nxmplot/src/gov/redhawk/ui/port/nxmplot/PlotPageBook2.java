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

import gov.redhawk.internal.ui.port.nxmplot.handlers.NxmBlockSettingsWizard;
import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
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
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.statushandlers.StatusManager;

import BULKIO.StreamSRI;
import BULKIO.dataSDDSHelper;

/**
 * @since 4.2
 */
public class PlotPageBook2 extends Composite {

	private static final Debug TRACE_LOG = new Debug(PlotActivator.PLUGIN_ID, PlotPageBook2.class.getSimpleName());
	private static final int PORT_REFRESH_DELAY_MS = 1000;

	private static class PlotPage {
		private final AbstractNxmPlotWidget plot;

		public PlotPage(AbstractNxmPlotWidget plot, String plotArgs, String plotSwitches) {
			this.plot = plot;
			this.plot.initPlot(plotSwitches, plotArgs);
		}

		public synchronized void dispose() {
			plot.dispose();
		}
	} // end inner-class PlotPage

	/** The page book. */
	private final PageBook pageBook;

	/** The plots. */
	private Map<PlotType, PlotPage> plots = new HashMap<PlotType, PlotPage>();

	private ListenerList plotListeners = new ListenerList();

	private IPlotWidgetListener plotListener = new IPlotWidgetListener() {

		@Override
		public void zoomX(double xmin, double ymin, double xmax, double ymax, Object data) {
			for (Object listener : plotListeners.getListeners()) {
				((IPlotWidgetListener) listener).zoomX(xmin, ymin, xmax, ymax, data);
			}
		}

		@Override
		public void zoomOut(double x1, double y1, double x2, double y2, Object data) {
			for (Object listener : plotListeners.getListeners()) {
				((IPlotWidgetListener) listener).zoomOut(x1, y1, x2, y2, data);
			}
		}

		@Override
		public void zoomIn(double xmin, double ymin, double xmax, double ymax, Object data) {
			for (Object listener : plotListeners.getListeners()) {
				((IPlotWidgetListener) listener).zoomIn(xmin, ymin, xmax, ymax, data);
			}
		}

		@Override
		public void unzoom(double x1, double y1, double x2, double y2, Object data) {
			for (Object listener : plotListeners.getListeners()) {
				((IPlotWidgetListener) listener).unzoom(x1, y1, x2, y2, data);
			}
		}

		@Override
		public void pan(double x1, double y1, double x2, double y2) {
			for (Object listener : plotListeners.getListeners()) {
				((IPlotWidgetListener) listener).pan(x1, y1, x2, y2);
			}
		}

		@Override
		public void motion(double x, double y, double t) {
			for (Object listener : plotListeners.getListeners()) {
				((IPlotWidgetListener) listener).motion(x, y, t);
			}
		}

		@Override
		public void dragBox(double xmin, double ymin, double xmax, double ymax) {
			for (Object listener : plotListeners.getListeners()) {
				((IPlotWidgetListener) listener).dragBox(xmin, ymin, xmax, ymax);
			}
		}

		@Override
		public void click(double x, double y, double t) {
			for (Object listener : plotListeners.getListeners()) {
				((IPlotWidgetListener) listener).click(x, y, t);
			}
		}
	};

	private List<PlotSource> sources = Collections.synchronizedList(new ArrayList<PlotSource>());

	private final Map<PlotSource, List<INxmBlock>> source2NxmBlocks = new ConcurrentHashMap<PlotSource, List<INxmBlock>>();

	private Adapter portListener = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (isDisposed()) {
				((Notifier) msg.getNotifier()).eAdapters().remove(this);
			}
			switch (msg.getFeatureID(IDisposable.class)) {
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
	private PlotPage currentPlotPage;

	private Composite nullPage;

	private IMenuManager contextMenu;

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

	/**
	 * @deprecated use {@link #createPlot(PlotSettings)} instead
	 */
	@Deprecated
	protected PlotPage createPlot(PlotType type) {
		PlotSettings plotSettings = new PlotSettings(type);
		return createPlot(plotSettings);
	}

	/**
	 * @since 4.4
	 */
	protected PlotPage createPlot(@NonNull PlotSettings plotSettings) {
		PlotType type = plotSettings.getPlotType();
		String plotArgs = NxmPlotUtil.getDefaultPlotArgs(plotSettings);
		String plotSwitches = NxmPlotUtil.getDefaultPlotSwitches(plotSettings);
		AbstractNxmPlotWidget newPlot = PlotActivator.getDefault().getPlotFactory().createPlotWidget(this.pageBook, SWT.None);
			
		PlotPage plotPage = new PlotPage(newPlot, plotArgs, plotSwitches);
		this.plots.put(type, plotPage);

		Set<Entry<PlotSource, List<INxmBlock>>> entrySet = source2NxmBlocks.entrySet();
		for (Entry<PlotSource, List<INxmBlock>> entry : entrySet) {
			PlotNxmBlockSettings plotBlockSettings = entry.getKey().getPlotBlockSettings();
			if (plotBlockSettings == null) {
				plotBlockSettings = new PlotNxmBlockSettings();
			}
			PlotNxmBlock plotBlock = new PlotNxmBlock(newPlot, plotBlockSettings);
			List<INxmBlock> nxmBlocksForSource = entry.getValue();
			int idx4LastNonPlotBlock = nxmBlocksForSource.size() - 2;
			if (idx4LastNonPlotBlock >= 0) { // must have at least initial NxmBlock + PlotNxmBlock
				final INxmBlock srcBlock = nxmBlocksForSource.get(idx4LastNonPlotBlock);
				StreamSRI[] streamSRIs = srcBlock.getLaunchedStreams();

				plotBlock.addInput(srcBlock);
				try {
					plotBlock.start();
					for (StreamSRI sri : streamSRIs) {
						plotBlock.launch(sri.streamID, sri);
					}
				} catch (CoreException e) {
					StatusManager.getManager().handle(
						new Status(IStatus.WARNING, PlotActivator.PLUGIN_ID, "Got Exception trying to plot Port: " + entry.getKey().getInput(), e),
						StatusManager.LOG);
				}
			}
		}

		return plotPage;
	}

	/**
	 * @deprecated since 4.4., use {@link #addSource(PlotSource)}
	 */
	@Deprecated
	public IPlotSession addSource(ScaUsesPort port, FftSettings oldFftsettings, String qualifiers) {
		final FftNxmBlockSettings fftSettings;
		if (oldFftsettings != null) {
			fftSettings = toFftNxmBlockSettings(oldFftsettings);
		} else {
			fftSettings = null;
		}
		final PlotSource plotSource = new PlotSource(port, fftSettings, qualifiers);
		
		final org.eclipse.ui.services.IDisposable disposable = addSource(plotSource);
		
		return new IPlotSession() {
			private String id = UUID.randomUUID().toString();

			@Override
			public void dispose() {
				disposable.dispose();
			}

			@Override
			public String getSourceId() {
				return id;
			}
		};
	}

	/**
	 * @since 4.4
	 */
	public org.eclipse.ui.services.IDisposable addSource(@NonNull final PlotSource plotSource) {
		final ScaUsesPort scaPort = plotSource.getInput();

		final String pipeQualifiers = plotSource.getQualifiers();
		FftNxmBlockSettings fftSettings = plotSource.getFftBlockSettings();
		PlotPageBook2.TRACE_LOG.enteringMethod(scaPort, plotSource.getBulkIOBlockSettings(), plotSource.getSddsBlockSettings(), fftSettings, pipeQualifiers);

		final PlotPage curPlotPage = this.currentPlotPage;
		if (curPlotPage == null) {
			StatusManager.getManager().handle(new Status(IStatus.WARNING, PlotActivator.PLUGIN_ID, "Unable to addSource Port when current PlotPage is null"),
				StatusManager.LOG);
			return null;
		}
		final AbstractNxmPlotWidget currentPlotWidget = curPlotPage.plot;

		final ArrayList<INxmBlock> nxmBlocksForSource = new ArrayList<INxmBlock>();
		INxmBlock startingBlock;
		if (scaPort._is_a(dataSDDSHelper.id())) {
			SddsNxmBlockSettings sddsSettings = plotSource.getSddsBlockSettings();
			if (sddsSettings == null) {
				sddsSettings = new SddsNxmBlockSettings();
				sddsSettings.setDataByteOrder(ByteOrder.nativeOrder()); // workaround for REDHAWK SinkNic Component
			}
			BulkIOSddsNxmBlock sddsBlock = new BulkIOSddsNxmBlock(currentPlotWidget, scaPort, sddsSettings);
			startingBlock = sddsBlock;
		} else {
			BulkIONxmBlockSettings bulkioSettings = plotSource.getBulkIOBlockSettings();
			if (bulkioSettings == null) {
				bulkioSettings = new BulkIONxmBlockSettings();
			}
			BulkIONxmBlock bulkioBlock = new BulkIONxmBlock(currentPlotWidget, scaPort, bulkioSettings);
			startingBlock = bulkioBlock;
		}
		PlotPageBook2.TRACE_LOG.trace("PlotPageBook2.addSource(.) startingBlock = {0}", startingBlock);
		nxmBlocksForSource.add(startingBlock);

		PlotNxmBlockSettings plotBlockSettings = plotSource.getPlotBlockSettings();
		if (plotBlockSettings == null) {
			plotBlockSettings = new PlotNxmBlockSettings();
		}
		PlotNxmBlock plotBlock = new PlotNxmBlock(currentPlotWidget, plotBlockSettings);

		final FftNxmBlock fftBlock;
		if (fftSettings != null) {
			fftBlock = new FftNxmBlock(currentPlotWidget, fftSettings);
			fftBlock.addInput(startingBlock);
			plotBlock.addInput(fftBlock);
			nxmBlocksForSource.add(fftBlock);
		} else {
			fftBlock = null;
			plotBlock.addInput(startingBlock);
		}
		nxmBlocksForSource.add(plotBlock); // TODO: remove this and associate with PlotPage?

		IMenuManager menu = this.contextMenu;
		if (menu != null) {
			final MenuManager subMenu;
			String subMenuText = scaPort.getName();
			//			final int numSources = source2NxmBlocks.size();
			//			if (numSources > 0) {
			EObject eObj = scaPort.eContainer();
			if (eObj != null) {
				final ScaItemProviderAdapterFactory factory = new ScaItemProviderAdapterFactory();
				Adapter adapter = factory.adapt(eObj, IItemLabelProvider.class);
				if (adapter instanceof IItemLabelProvider) {
					IItemLabelProvider lp = (IItemLabelProvider) adapter;
					subMenuText = lp.getText(eObj) + " -> " + subMenuText;
				}
			}
			//			}
			subMenu = new MenuManager(subMenuText, scaPort.getIor()); // subMenu for each Port source
			menu.add(subMenu);
			plotBlock.contributeMenuItems(subMenu); // allow NxmBlocks to contribute to subMenu
			subMenu.add(createSettingsMenuActionForSource(subMenuText, scaPort, nxmBlocksForSource.toArray(new INxmBlock[0]))); // add settings menu item
			subMenu.setVisible(true);
		}

		try {
			plotBlock.start();
			if (fftBlock != null) {
				fftBlock.start();
			}
			startingBlock.start(); // starting block should be last to start
		} catch (CoreException e) {
			StatusManager.getManager().handle(new Status(IStatus.WARNING, PlotActivator.PLUGIN_ID, "Got Exception trying to plot Port: " + scaPort, e),
				StatusManager.LOG);
		}

		this.source2NxmBlocks.put(plotSource, nxmBlocksForSource);

		// register adapter to handle when EMF model disposes of the SCA Port
		ScaModelCommand.execute(scaPort, new ScaModelCommand() {
			@Override
			public void execute() {
				scaPort.eAdapters().add(portListener);
			}
		});

		org.eclipse.ui.services.IDisposable retVal = new org.eclipse.ui.services.IDisposable() {
			@Override
			public void dispose() {
				Job job = new Job("Disposing Source " + plotSource) {

					@Override
					protected IStatus run(IProgressMonitor monitor) {
						removeSource2(plotSource);
						return Status.OK_STATUS;
					}

				};
				job.schedule();

			}
		};

		PortHelper.refreshPort(scaPort, null, PlotPageBook2.PORT_REFRESH_DELAY_MS);
		PlotPageBook2.TRACE_LOG.exitingMethod();
		return retVal;
	}

	private void removeSource2(PlotSource plotSource) {
		List<INxmBlock> nxmBlocks = source2NxmBlocks.get(plotSource);
		if (nxmBlocks != null) {
			for (INxmBlock nxmBlock : nxmBlocks) {
				if (nxmBlock != null) {
					nxmBlock.stop();
					nxmBlock.dispose();
				}
			}
		}
		source2NxmBlocks.remove(plotSource);
	}

	/**
	 * Toggle if the raster is visible or not.
	 * @param enable true if the raster should be shown
	 */
	public void showPlot(PlotType type) {
		PlotSettings plotSettings = new PlotSettings(type);
		showPlot(plotSettings);
	}

	/** NTN: NEW METHOD (from old showPlot(PlotType method)
	 * Toggle if the raster is visible or not.
	 * @param enable true if the raster should be shown
	 * @since 4.4
	 */
	public void showPlot(@NonNull PlotSettings plotSettings) {
		if (currentPlotPage != null) {
			currentPlotPage.plot.removePlotListener(plotListener);
		}
		PlotType type = plotSettings.getPlotType();
		if (type == null) {
			pageBook.showPage(nullPage);
			currentPlotPage = null;
		} else {
			PlotPage newPlot = this.plots.get(type);
			if (newPlot == null) {
				AbstractNxmPlotWidget oldPlot = getActivePlotWidget();
				newPlot = createPlot(plotSettings);
				if (oldPlot != null) {
					PlotSettings existingSettings = new PlotSettings(oldPlot.getPlotSettings());
					existingSettings.setPlotType(type);
					newPlot.plot.applySettings(existingSettings);
				}
			}
			pageBook.showPage(newPlot.plot);
			currentPlotPage = newPlot;
		}
		this.currentType = type;
		if (currentPlotPage != null) {
			currentPlotPage.plot.addPlotListener(plotListener);
		}
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

		for (final PlotSource source : this.sources) {
			PortHelper.refreshPort(source.getInput(), null, PlotPageBook2.PORT_REFRESH_DELAY_MS);
		}
		this.sources.clear();

		for (final PlotSource plotSource : this.source2NxmBlocks.keySet().toArray(new PlotSource[0])) {
			Job job = new Job("Disposing Source " + plotSource) {

				@Override
				protected IStatus run(IProgressMonitor monitor) {
					removeSource2(plotSource);
					PortHelper.refreshPort(plotSource.getInput(), null, PlotPageBook2.PORT_REFRESH_DELAY_MS);
					return Status.OK_STATUS;
				}

			};
			job.schedule();
		}
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
		this.plotListeners.add(listener);
	}

	/**
	 * @since 3.0
	 */
	public void removePlotListener(final IPlotWidgetListener listener) {
		this.plotListeners.remove(listener);
	}

	public List<PlotSource> getSources() {
		List<PlotSource> plotSources = new ArrayList<PlotSource>(this.source2NxmBlocks.keySet());
		plotSources.addAll(this.sources);
		return plotSources;
	}

	public List<AbstractNxmPlotWidget> getAllPlotWidgets() {
		List<AbstractNxmPlotWidget> retVal = new ArrayList<AbstractNxmPlotWidget>(plots.size());
		for (PlotPage session : plots.values()) {
			retVal.add(session.plot);
		}
		return retVal;
	}

	/**
	 * @noreference This method is not intended to be referenced by clients.
	 * @since 4.4
	 */
	@NonNull
	public static FftNxmBlockSettings toFftNxmBlockSettings(FftSettings fftOptions) {
		FftNxmBlockSettings settings = new FftNxmBlockSettings();
		settings.setTransformSize(Integer.parseInt(fftOptions.getTransformSize())); // 1+
		settings.setOverlap(Integer.parseInt(fftOptions.getOverlap())); // 0-100
		settings.setNumAverages(Integer.parseInt(fftOptions.getNumAverages())); // 1+
		settings.setWindow(FftNxmBlockSettings.WindowType.valueOf(fftOptions.getWindowType().name()));
		settings.setOutputType(FftNxmBlockSettings.OutputType.valueOf(fftOptions.getOutputType().name()));
		return settings;
	}

	/**
	 * @noreference This method is not intended to be referenced by clients.
	 * @since 4.3
	 */
	public void contributeMenuItems(IMenuManager menu) {
		this.contextMenu = menu; // save a reference so that we can make contributions later to the context menu
	}

	private IAction createSettingsMenuActionForSource(final String sourceInfo, final ScaUsesPort scaPort, final INxmBlock... nxmBlocks) {
		IAction action = new Action("Settings...") {
			@Override
			public void run() {
				final NxmBlockSettingsWizard wizard = new NxmBlockSettingsWizard(sourceInfo);
				//				PlotPageBook2.this.getAllPlotWidgets() // TODO
				wizard.setNxmBlocks(nxmBlocks);

				WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
				if (Window.OK == dialog.open()) {
					Job job = new Job("apply plot settings for source: " + sourceInfo) {
						@Override
						protected IStatus run(IProgressMonitor monitor) {
							for (INxmBlock nxmBlock : nxmBlocks) {
								Object newBlockSettings = wizard.getSettings(nxmBlock);
								nxmBlock.applySettings(newBlockSettings, null); // apply settings to all streams
							}
							return Status.OK_STATUS;
						}
					};
					job.schedule(0);
				}
			}
		};
		return action;
	}
}

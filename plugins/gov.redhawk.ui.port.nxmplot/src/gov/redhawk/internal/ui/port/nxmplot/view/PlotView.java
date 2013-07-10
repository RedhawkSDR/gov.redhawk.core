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
package gov.redhawk.internal.ui.port.nxmplot.view;

import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.CorbaConnectionSettings;
import gov.redhawk.ui.port.nxmplot.FftSettings;
import gov.redhawk.ui.port.nxmplot.INxmPlotWidgetFactory;
import gov.redhawk.ui.port.nxmplot.IPlotSession;
import gov.redhawk.ui.port.nxmplot.IPlotWidgetListener;
import gov.redhawk.ui.port.nxmplot.NxmPlotUtil;
import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.PlotListenerAdapter;
import gov.redhawk.ui.port.nxmplot.PlotPageBook;
import gov.redhawk.ui.port.nxmplot.PlotType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.ui.statushandlers.StatusManager;

import BULKIO.StreamSRI;
import CF.DataType;

/**
 * The spectral view provides multi-tab view that displays spectral data.
 *
 * @since 2.0
 */
public class PlotView extends ViewPart {

	private static int secondaryId = 0;

	/** The ID of the view. */
	public static final String ID = "gov.redhawk.ui.port.nxmplot.portdata";

	protected final String plotQualifiers;

	/** The tab folder that contains each plot. */
	private CTabFolder plotFolder;

	/** The private action for toggling raster enabled state. */
	private IAction rasterToggleAction;

	private IAction showSriAction;

	/** The private action for creating a new plot connection */
	private IAction newPlotViewAction;

	/** Map of plot ID to page books */
	private Map<String, Composite> plotMap;

	/** Map of ports to their corresponding CTabItems */
	private HashMap<ScaUsesPort, List<CTabItem>> portToTabMap;

	/** Map of spectral plots to plot sessions */
	private Map<Composite, List<IPlotSession>> plotSessionMap = new HashMap<Composite, List<IPlotSession>>();

	private final Adapter portListener = new AdapterImpl() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			switch (msg.getFeatureID(IDisposable.class)) {
			case ScaPackage.IDISPOSABLE__DISPOSED:
				if (msg.getNewBooleanValue()) {
					if (PlotView.this.disposed) {
						if (msg.getNotifier() instanceof Notifier) {
							((Notifier) msg.getNotifier()).eAdapters().remove(this);
						}
					}
					final UIJob job = new UIJob(getSite().getShell().getDisplay(), "Dispose Plot") {

						@Override
						public IStatus runInUIThread(final IProgressMonitor monitor) {
							if (msg.getNotifier() instanceof ScaUsesPort) {
								final ScaUsesPort usesPort = (ScaUsesPort) msg.getNotifier();

								final List<CTabItem> tabs = PlotView.this.portToTabMap.get(usesPort);
								for (CTabItem tab : tabs) {
									if (tab.isDisposed()) {
										return Status.CANCEL_STATUS;
									}
									tab.dispose();
								}

								if (PlotView.this.plotFolder.getTabList().length == 0) {
									PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(PlotView.this);
								}

							}
							return Status.OK_STATUS;
						}
					};
					job.setSystem(true);
					job.schedule();
				}
				break;
			default:
				break;
			}
		}
	};

	public PlotView() {
		plotQualifiers = "{CL=8}";
		/** provide plot display thinning */
	}

	private boolean disposed;

	private class PlotSelectionProvider implements ISelectionProvider {
		private final Set<ISelectionChangedListener> listeners = new HashSet<ISelectionChangedListener>();

		public void addSelectionChangedListener(final ISelectionChangedListener listener) {
			this.listeners.add(listener);
		}

		public ISelection getSelection() {
			return new StructuredSelection(getActiveSRI());
		}

		public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
			this.listeners.remove(listener);
		}

		public void setSelection(final ISelection selection) {
			// Do nothing
		}

		protected void fireSelectionChanged() {
			final SelectionChangedEvent event = new SelectionChangedEvent(this, getSelection());
			for (final ISelectionChangedListener listener : this.listeners) {
				listener.selectionChanged(event);
			}
		}

	}

	private final PlotSelectionProvider selectionProvider = new PlotSelectionProvider();
	private Map<String, PlotListenerAdapter> listenerMap = Collections.synchronizedMap(new HashMap<String, PlotListenerAdapter>());

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createPartControl(final Composite parent) {
		this.plotFolder = new CTabFolder(parent, SWT.BOTTOM | SWT.CLOSE);
		this.plotFolder.setUnselectedCloseVisible(false);
		this.plotMap = new HashMap<String, Composite>();
		this.portToTabMap = new HashMap<ScaUsesPort, List<CTabItem>>();

		createActions();
		createToolBars();
		createMenu();

		this.plotFolder.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(final SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(final SelectionEvent e) {
				updateRasterButton();
			}
		});

		getSite().setSelectionProvider(this.selectionProvider);
	}

	private void updateRasterButton() {
		if (plotFolder != null && plotFolder.isDisposed()) {
			return;
		}
		boolean checked = false;
		boolean enabled = false;
		CTabItem selectedTab = plotFolder.getSelection();
		if (selectedTab != null && !selectedTab.isDisposed()) {
			Control control = selectedTab.getControl();
			if (control instanceof PlotPageBook) {
				PlotPageBook currentPageBook = (PlotPageBook) selectedTab.getControl();
				if ((currentPageBook != null) && !currentPageBook.isDisposed() && currentPageBook instanceof PlotPageBook) {
					checked = ((PlotPageBook) currentPageBook).isRasterShowing();
					enabled = ((PlotPageBook) currentPageBook).isRasterable();
				}
			}
		}

		this.rasterToggleAction.setChecked(checked);
		this.rasterToggleAction.setEnabled(enabled);
		this.selectionProvider.fireSelectionChanged();

	}

	/**
	 * Creates a new spectral plot.
	 *
	 * @param connList list of connections to draw on the plot
	 * @param fft settings to use if an FFT is to be displayed
	 * @param sessionId the unique ID of this plot instance - used for listeners
	 */
	@SuppressWarnings("unchecked")
	public String createNewPlotTabWithSettings(final List<CorbaConnectionSettings> connList, final FftSettings fft, final UUID sessionId, boolean pageBook, PlotType plotType) {
		//When this method is used, the view will not be disposed when all ports are disposed, since a port list was not provided
		return createPlot(connList, fft, (List< ? extends ScaUsesPort>) Collections.emptyList(), sessionId, pageBook, plotType);
	}

	/**
	 * Creates a new spectral plot.
	 *
	 * @param portList list of ScaPort object to plot the output from.
	 * @param fft settings to use if an FFT is to be displayed
	 * @param sessionId the unique ID of this plot instance - used for listeners
	 * @param pageBook if true, create a PlotPageBook instance, otherwise create a PlotComposite instance
	 */
	public String createNewPlotTab(final List< ? extends ScaUsesPort> portList, final FftSettings fft, final UUID sessionId, boolean pageBook, PlotType plotType) {
		final List<CorbaConnectionSettings> connList = new ArrayList<CorbaConnectionSettings>();

		for (final ScaUsesPort port : portList) {
			final EObject parent = port.eContainer();
			String name = "";

			if (parent instanceof ScaComponent) {
				final ScaComponent component = (ScaComponent) parent;
				final SadComponentInstantiation instantiation = ((ScaComponent) parent).getComponentInstantiation();
				if (instantiation != null) {
					name = instantiation.getUsageName();
				} else {
					name = component.getName();
				}
			} else if (parent instanceof ScaDevice< ? >) {
				name = ((ScaDevice< ? >) parent).getLabel();
			} else if (parent instanceof ScaService) {
				name = ((ScaService) parent).getName();
			} else if (parent instanceof ScaWaveform) {
				name = ((ScaWaveform) parent).getName();
			}
			final CorbaConnectionSettings connectionSettings = new CorbaConnectionSettings(port.getIor(), port.getRepid());
			connectionSettings.setPortString(name + " [" + port.getName() + "]");
			connList.add(connectionSettings);
		}

		return createPlot(connList, fft, portList, sessionId, pageBook, plotType);
	}

	/**
	 * This creates a plot widget that wraps either a Line or Raster plot, or it wraps a PageBook with Line and Raster plots,
	 * and puts the plot widget in the specified tab
	 *
	 * @param connList a list of the settings for acquiring the data
	 * @param fft settings to use if an FFT is to be displayed
	 * @param ports list of ScaPort object to plot the output from.
	 * @param sessionId the unique ID of this plot instance - used for listeners
	 * @param pageBook if true, create a PlotPageBook instance, otherwise create a PlotComposite instance
	 * @return String unique identifier for this plot instance
	 */
	private String createPlot(final List<CorbaConnectionSettings> connList, final FftSettings fft, final List< ? extends ScaUsesPort> ports, final UUID sessionId,
	        boolean pageBook, PlotType plotType) {
		if (plotType == null) {
			plotType = PlotType.LINE;
		}
		Assert.isTrue(plotType == PlotType.LINE || plotType == PlotType.RASTER);
		final CTabItem newTab = new CTabItem(this.plotFolder, SWT.None);

		final ScaUsesPort port = (ports.isEmpty() ? null : ports.get(0));

		String tooltip = connList.get(0).getPortString();
		if (connList.size() > 0) {
			for (int i = 1; i < connList.size(); ++i) {
				final String portString = connList.get(i).getPortString();
				final int idx = portString.lastIndexOf('[');
				if (idx != -1) {
					tooltip += portString.substring(portString.lastIndexOf('['));
				} else {
					tooltip += portString;
				}
			}
		}
		newTab.setText(connList.get(0).getPortString());
		newTab.setToolTipText(tooltip);

		final Composite spectralPlots;
		// Create the holder for the plots.
		if (pageBook) {
			spectralPlots = new PlotPageBook(this.plotFolder, SWT.NONE, connList, fft, ports, sessionId);
			spectralPlots.setData("connList", connList);
			spectralPlots.setData("fft", fft);
			spectralPlots.setData("ports", ports);
			spectralPlots.setData("plotType", plotType);
		} else {
			INxmPlotWidgetFactory plotFactory = PlotActivator.getDefault().getPlotFactory();
			final String plotArgs = NxmPlotUtil.getDefaultPlotArgs(plotType);
			final String plotSwitches = NxmPlotUtil.getDefaultPlotSwitches(plotType);
			spectralPlots = plotFactory.createPlotWidget(this.plotFolder, SWT.NONE);

			if (spectralPlots instanceof AbstractNxmPlotWidget) {
				spectralPlots.setData("connList", connList);
				spectralPlots.setData("fft", fft);
				spectralPlots.setData("ports", ports);
				spectralPlots.setData("plotType", plotType);
				((AbstractNxmPlotWidget) spectralPlots).initPlot(plotSwitches, plotArgs);
				new Job("Adding Plot Source...") {

					@Override
					protected IStatus run(IProgressMonitor monitor) {
						List<IPlotSession> plotSessions = NxmPlotUtil.addSource(connList, fft, (AbstractNxmPlotWidget) spectralPlots, plotQualifiers);
						plotSessionMap.put(spectralPlots, plotSessions);
						return Status.OK_STATUS;
					}
					
				} .schedule();
			}
		}

		newTab.setControl(spectralPlots);
		this.plotFolder.setSelection(newTab);
		updateRasterButton();
		this.plotFolder.redraw();

		if (spectralPlots != null) {
			// Add listeners to make sure we clean up properly
			newTab.addDisposeListener(new DisposeListener() {
				public void widgetDisposed(final DisposeEvent e) {
					if (!spectralPlots.isDisposed()) {
						spectralPlots.dispose();
					}
					updateRasterButton();
				}
			});
			spectralPlots.addDisposeListener(new DisposeListener() {

				public void widgetDisposed(final DisposeEvent e) {
					if (port != null) {
						ScaModelCommand.execute(port, new ScaModelCommand() {

							public void execute() {
								port.eAdapters().remove(PlotView.this.portListener);
							}
						});
					}
					List<IPlotSession> plotSessions = plotSessionMap.get(spectralPlots);
					//NB: PlotPageBook handles disposing of its plot sessions when it is disposed
					if (plotSessions != null) {
						for (IPlotSession session : plotSessions) {
							session.dispose();
						}
						plotSessionMap.remove(spectralPlots);
					}
					if (!newTab.isDisposed()) {
						newTab.dispose();
					}

				}
			});
		}

		if (port != null && !port.eAdapters().contains(this.portListener)) {
			ScaModelCommand.execute(port, new ScaModelCommand() {

				public void execute() {
					port.eAdapters().add(PlotView.this.portListener);
				}
			});
		}

		// Store the ID of the plot with the new plot book for listener
		// addition and removal
		//final String id = spectralPlots.getIdentifier();
		this.plotMap.put(sessionId.toString(), spectralPlots);
		List<CTabItem> tabList = this.portToTabMap.get(port);
		if (tabList == null) {
			this.portToTabMap.put(port, tabList = new ArrayList<CTabItem>());
		}
		tabList.add(newTab);

		return sessionId.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFocus() {
		if (this.plotFolder != null) {
			this.plotFolder.setFocus();
		}
	}

	private void createMenu() {
		final IActionBars bars = getViewSite().getActionBars();
		final IMenuManager menu = bars.getMenuManager();
		if (this.newPlotViewAction != null) {
			menu.add(this.newPlotViewAction);
		}
		if (this.showSriAction != null) {
			menu.add(this.showSriAction);
		}
		menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	/**
	 * Create the view toolbars.
	 */
	private void createToolBars() {
		final IActionBars bars = getViewSite().getActionBars();
		new MenuManager("View Control", "gov.redhawk.ui.port.nxmplot.menu.ViewControl");

		final IToolBarManager toolBarManager = bars.getToolBarManager();
		if (this.rasterToggleAction != null) {
			toolBarManager.add(this.rasterToggleAction);
			toolBarManager.add(new Separator());
		}
		toolBarManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	/** Creates the actions. **/
	private void createActions() {
		this.rasterToggleAction = new Action() {
			@Override
			public void run() {
				final CTabItem activeTab = PlotView.this.plotFolder.getSelection();
				if (activeTab != null) {
					final PlotPageBook currentPageBook = (PlotPageBook) activeTab.getControl();
					if (currentPageBook instanceof PlotPageBook) {
						((PlotPageBook) currentPageBook).showRaster(this.isChecked());
					}
				} else {
					this.setChecked(false);
				}
			}

			@Override
			public void setChecked(final boolean checked) {
				super.setChecked(checked);
				if (!checked) {
					this.setToolTipText("Show Raster");
				} else {
					this.setToolTipText("Show Line");
				}
			}
		};

		this.rasterToggleAction.setEnabled(false);
		this.rasterToggleAction.setChecked(false);
		this.rasterToggleAction.setText("Show Raster");
		this.rasterToggleAction.setToolTipText("Show Raster");
		final ImageDescriptor rasterImageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(PlotActivator.PLUGIN_ID, "icons/raster.png");
		this.rasterToggleAction.setImageDescriptor(rasterImageDescriptor);

		this.newPlotViewAction = new Action() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				try {
					final IViewPart newView = getSite().getPage().showView(getSite().getId(), String.valueOf(PlotView.secondaryId++), IWorkbenchPage.VIEW_ACTIVATE);
					if (newView instanceof PlotView) {
						final PlotView newPlot = (PlotView) newView;
						for (final Control c : PlotView.this.plotFolder.getTabList()) {
							if (c instanceof PlotPageBook || c instanceof AbstractNxmPlotWidget) {
								Widget plotContainer = (Widget) c;

								newPlot.createPlot((List<CorbaConnectionSettings>) plotContainer.getData("connList"), (FftSettings) plotContainer.getData("fft"),
								        (List< ? extends ScaUsesPort>) plotContainer.getData("ports"), UUID.randomUUID(), true, (PlotType) plotContainer.getData("plotType"));
							}
						}
					}
				} catch (final PartInitException e) {
					StatusManager.getManager()
					        .handle(new Status(IStatus.ERROR, PlotActivator.PLUGIN_ID, "Failed to open new Plot View", e), StatusManager.SHOW | StatusManager.LOG);
				}
			}
		};
		this.newPlotViewAction.setEnabled(true);
		this.newPlotViewAction.setText("New Plot View");
		this.newPlotViewAction.setToolTipText("Open a new Plot View with all the same plots.");

		this.showSriAction = new Action() {

			private String getText(final Object obj) {
				if (obj instanceof DataType[]) {
					final DataType[] keywords = (DataType[]) obj;
					final List<String> result = new ArrayList<String>();
					for (final DataType t : keywords) {
						result.add(getText(t));
					}
					return result.toString();
				} else if (obj instanceof DataType) {
					final DataType t = (DataType) obj;
					return t.id + ", " + getText(AnyUtils.convertAny(t.value));
				} else if (obj != null) {
					return obj.toString();
				}
				return "null";
			}

			@Override
			public void run() {
				final StringBuilder builder = new StringBuilder();
				final StreamSRI[] sris = getActiveSRI();
				if (sris.length > 0) {
					for (final StreamSRI sri : sris) {
						builder.append("blocking: " + sri.blocking + "\n");
						builder.append("h version: " + sri.hversion + "\n");
						builder.append("mode: " + sri.mode + "\n");
						builder.append("streamID: " + sri.streamID + "\n");
						builder.append("subsize: " + sri.subsize + "\n");
						builder.append("xdelta: " + sri.xdelta + "\n");
						builder.append("xstart: " + sri.xstart + "\n");
						builder.append("xunits: " + sri.xunits + "\n");
						builder.append("ydelta: " + sri.ydelta + "\n");
						builder.append("ystart: " + sri.ystart + "\n");
						builder.append("yunits: " + sri.yunits + "\n");
						builder.append("keywords: " + getText(sri.keywords));
					}
				} else {
					builder.append("No SRI information available");
				}

				MessageDialog.openInformation(getSite().getShell(), "SRI", builder.toString());
			}
		};
		this.showSriAction.setEnabled(true);
		this.showSriAction.setText("SRI");
		this.showSriAction.setToolTipText("Display current plot SRI");
	}

	private StreamSRI[] getActiveSRI() {
		StreamSRI retVal = null;
		CTabItem selection = plotFolder.getSelection();
		if (selection != null && !selection.isDisposed()) {
			Control control = selection.getControl();
			if (control instanceof AbstractNxmPlotWidget) {
				retVal = ((AbstractNxmPlotWidget) control).getActiveSRI();
			} else if (control instanceof PlotPageBook) {
				retVal = ((PlotPageBook) control).getActivePlotWidget().getActiveSRI();
			}
		}
		if (retVal != null) {
			return new StreamSRI[] { retVal };
		} else {
			return new StreamSRI[0];
		}
	}

	/**
	 * Adds a plot click listener to this plot.
	 *
	 * @param listener the listener to add
	 * @param sessionId the unique ID of this plot instance - used for listeners
	 */
	public void addPlotListener(final IPlotWidgetListener listener, final String sessionId) {
		final Object plot = this.plotMap.get(sessionId);
		if (plot instanceof PlotPageBook) {
			((PlotPageBook) plot).addPlotListener(listener);
		} else if (plot instanceof AbstractNxmPlotWidget) {
			PlotListenerAdapter handler;
			synchronized (this.listenerMap) {
				handler = listenerMap.get(sessionId);
				if (handler == null) {
					handler = new PlotListenerAdapter();
					listenerMap.put(sessionId, handler);
					((AbstractNxmPlotWidget) plot).addMessageHandler(handler.getAdapter());
				}
			}
			handler.getListenerList().add(listener);
		}
	}

	/**
	 * Removes a plot click listener.
	 *
	 * @param listener the listener to remove
	 * @param sessionId the unique ID of this plot instance - used for listeners
	 */
	public void removePlotListener(final IPlotWidgetListener listener, final String sessionId) {
		final Object plot = this.plotMap.get(sessionId);
		if (plot instanceof PlotPageBook) {
			((PlotPageBook) plot).removePlotListener(listener);
		} else if (plot instanceof AbstractNxmPlotWidget) {
			synchronized (listenerMap) {
				PlotListenerAdapter handler = listenerMap.get(sessionId);
				if (handler != null) {
					handler.getListenerList().remove(listener);
					if (handler.getListenerList().isEmpty()) {
						((AbstractNxmPlotWidget) plot).removeMessageHandler(handler.getAdapter());
						listenerMap.remove(sessionId);
					}
				}
			}
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		this.disposed = true;
		this.newPlotViewAction = null;
		this.plotFolder = null;
		this.rasterToggleAction = null;
		this.plotMap.clear();
	}

}

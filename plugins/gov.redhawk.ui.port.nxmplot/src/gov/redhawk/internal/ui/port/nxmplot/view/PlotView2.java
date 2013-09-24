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

import gov.redhawk.internal.ui.port.nxmplot.PlotSettingsDialog;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.FftSettings;
import gov.redhawk.ui.port.nxmplot.IPlotSession;
import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.PlotPageBook2;
import gov.redhawk.ui.port.nxmplot.PlotSettings;
import gov.redhawk.ui.port.nxmplot.PlotType;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.statushandlers.StatusManager;

import BULKIO.StreamSRI;
import CF.DataType;

/**
 * The spectral view provides multi-tab view that displays spectral data.
 *
 * @since 2.0
 */
public class PlotView2 extends ViewPart {
	/** The ID of the view. */
	public static final String ID = "gov.redhawk.ui.port.nxmplot.PlotView2";

	private static int secondardId;

	/** The private action for toggling raster enabled state. */
	private IAction plotTypeAction;

	private IAction showSriAction;

	/** The private action for creating a new plot connection */
	private IAction newPlotViewAction;

	/** The private action for adjusting plot settings. */
	private IAction adjustPlotSettingsAction;


	private class PlotTypeMenuAction extends Action {

		public PlotTypeMenuAction() {
			super("Change Plot Type", IAction.AS_PUSH_BUTTON | IAction.AS_DROP_DOWN_MENU | SWT.None);
		}

		@Override
		public void run() {
			PlotType currentType = plotPageBook.getCurrentType();
			if (currentType == PlotType.RASTER) {
				plotPageBook.showPlot(PlotType.LINE);
			} else {
				plotPageBook.showPlot(PlotType.RASTER);
			}
		}

	}

	private PlotPageBook2 plotPageBook;

	private DisposeListener disposeListener = new DisposeListener() {

		@Override
		public void widgetDisposed(DisposeEvent e) {
			if (!diposed && !PlatformUI.getWorkbench().isClosing()) {
				getSite().getPage().hideView(PlotView2.this);
			}
		}
	};

	private boolean diposed;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createPartControl(final Composite parent) {
		this.plotPageBook = new PlotPageBook2(parent, SWT.None);
		this.plotPageBook.addDisposeListener(disposeListener);

		createActions();
		createToolBars();
		createMenu();
	}

	private IPlotSession addPlotSource(PlotSource source) {
		return this.plotPageBook.addSource(source.getInput(), source.getFftOptions(), source.getQualifiers());
	}

	@Override
	public void dispose() {
		this.diposed = true;
		if (this.plotPageBook != null && !plotPageBook.isDisposed()) {
			this.plotPageBook.removeDisposeListener(disposeListener);
			this.plotPageBook = null;
		}
		super.dispose();
	}

	/**
	 * @param fft settings to use if an FFT is to be displayed
	 * @param qualifiers 
	 * @param ports list of ScaPort object to plot the output from.
	 * @return New session
	 */
	public IPlotSession addPlotSource(ScaUsesPort port, final FftSettings fft, String qualifiers) {
		return this.plotPageBook.addSource(port, fft, qualifiers);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFocus() {
		if (this.plotPageBook != null && !this.plotPageBook.isDisposed()) {
			this.plotPageBook.setFocus();
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
		if (this.adjustPlotSettingsAction != null) {
			menu.add(this.adjustPlotSettingsAction);
		}
		menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	/**
	 * Create the view toolbars.
	 */
	private void createToolBars() {
		final IActionBars bars = getViewSite().getActionBars();

		final IToolBarManager toolBarManager = bars.getToolBarManager();
		toolBarManager.add(this.plotTypeAction);
		toolBarManager.add(new Separator());
		toolBarManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	public static String createSecondaryId() {
		return String.valueOf(secondardId++);
	}

	/** Creates the actions. **/
	private void createActions() {
		this.plotTypeAction = new PlotTypeMenuAction();

		final ImageDescriptor rasterImageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(PlotActivator.PLUGIN_ID, "icons/raster.png");
		this.plotTypeAction.setImageDescriptor(rasterImageDescriptor);

		this.newPlotViewAction = new Action() {
			@Override
			public void run() {
				try {
					final IViewPart newView = getSite().getPage().showView(getSite().getId(), createSecondaryId(), IWorkbenchPage.VIEW_ACTIVATE);
					if (newView instanceof PlotView2) {
						final PlotView2 newPlotView = (PlotView2) newView;
						newPlotView.setPartName(getPartName());
						newPlotView.setTitleToolTip(getTitleToolTip());
						newPlotView.getPlotPageBook().showPlot(plotPageBook.getCurrentType());
						for (PlotSource source : plotPageBook.getSources()) {
							newPlotView.addPlotSource(source);
						}
						PlotSettings settings = plotPageBook.getActivePlotWidget().getPlotSettings();
						settings.setPlotType(null);
						newPlotView.getPlotPageBook().getActivePlotWidget().applySettings(settings);
					}
				} catch (final PartInitException e) {
					StatusManager.getManager().handle(new Status(IStatus.ERROR, PlotActivator.PLUGIN_ID, "Failed to open new Plot View", e),
						StatusManager.SHOW | StatusManager.LOG);
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

		createActionAdjustPlotSettings();
	}

	private void createActionAdjustPlotSettings() {
		this.adjustPlotSettingsAction = new Action() {
			@Override
			public void run() {
				AbstractNxmPlotWidget activeWidget = plotPageBook.getActivePlotWidget();
				PlotSettings plotSettings = activeWidget.getPlotSettings();
				PlotSettingsDialog dialog = new PlotSettingsDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), plotSettings);
				final int result = dialog.open();
				if (result == Window.OK) {
					PlotSettings newSettings = dialog.getSettings();
					PlotType newType = newSettings.getPlotType();
					// Ignore Plot type in settings use page book instead
					newSettings.setPlotType(null);

					for (AbstractNxmPlotWidget widget : plotPageBook.getAllPlotWidgets()) {
						widget.applySettings(newSettings);
					}
					plotPageBook.showPlot(newType);
				}

			}
		};

		this.adjustPlotSettingsAction.setEnabled(true);
		this.adjustPlotSettingsAction.setText("Adjust Plot Settings");
		this.adjustPlotSettingsAction.setToolTipText("Adjust/Override Plot Settings");
	}

	private StreamSRI[] getActiveSRI() {
		StreamSRI retVal = null;
		if (this.plotPageBook != null && !plotPageBook.isDisposed()) {
			AbstractNxmPlotWidget plot = this.plotPageBook.getActivePlotWidget();
			if (plot != null && !plot.isDisposed()) {
				retVal = plot.getActiveSRI();
			}
		}

		if (retVal != null) {
			return new StreamSRI[] { retVal };
		} else {
			return new StreamSRI[0];
		}
	}

	public PlotPageBook2 getPlotPageBook() {
		return plotPageBook;
	}
	
	@Override
	public void setPartName(String partName) {
		super.setPartName(partName);
	}
	
	@Override
	public void setTitleToolTip(String toolTip) {
		super.setTitleToolTip(toolTip);
	}
}

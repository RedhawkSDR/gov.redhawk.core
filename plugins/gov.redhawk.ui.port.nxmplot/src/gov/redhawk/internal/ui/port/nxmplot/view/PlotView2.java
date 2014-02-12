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

import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.ui.port.nxmplot.FftNumAvgControls;
import gov.redhawk.ui.port.nxmplot.FftSettings;
import gov.redhawk.ui.port.nxmplot.IPlotView;
import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.PlotEventChannelForwarder;
import gov.redhawk.ui.port.nxmplot.PlotPageBook2;
import gov.redhawk.ui.port.nxmplot.PlotSettings;
import gov.redhawk.ui.port.nxmplot.PlotSource;
import gov.redhawk.ui.port.nxmplot.preferences.PlotPreferences;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.services.IDisposable;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * The spectral view provides view that displays spectral data in a plot.
 *
 * @since 4.2
 * @noreference This class is not intended to be referenced by clients
 */
public class PlotView2 extends ViewPart implements IPlotView {
	/** The ID of the view. */
	public static final String ID = "gov.redhawk.ui.port.nxmplot.PlotView2";

	public static final String ID_CHANGE_PLOT_TYPE_ACTION = "gov.redhawk.ChangePlotType";

	private static int secondardId;

	/** The private action for toggling raster enabled state. */
	private IAction plotTypeAction;

	/** The private action for creating a new plot connection */
	private IAction newPlotViewAction;

	/** The private action for adjusting plot settings. */
	private IAction adjustPlotSettingsAction;

	private IMenuManager menu;

	private PlotFftMenuAction fftSizeMenu;
	private PlotModeMenuAction plotModeMenu;

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

	private FftNumAvgControls fftControls;

	private PlotSettingsAction plotSettingsAction;

	private Composite parent;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createPartControl(final Composite parent) {
		this.parent = parent;
		GridLayout layout = new GridLayout(2, false);
		layout.marginBottom = 0;
		layout.marginHeight = 0;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		layout.marginTop = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		parent.setLayout(layout);
		this.plotPageBook = new PlotPageBook2(parent, SWT.None);
		this.plotPageBook.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (PlotPageBook2.PROP_SOURCES.equals(evt.getPropertyName())) {
					final boolean hasFft = hasFft();
					if (parent == null || parent.isDisposed()) {
						return;
					}
					parent.getDisplay().asyncExec(new Runnable() {

						@Override
						public void run() {
							updateFftSizeMenu(hasFft);
							updateFftControls(hasFft);
						}

					});
					if (evt.getNewValue() instanceof PlotSource) {
						PlotSource source = (PlotSource) evt.getNewValue();
						PlotEventChannelForwarder.forwardEvents(plotPageBook, source.getInput(), PlotView2.this);
					}
				}
			}
		});
		this.plotPageBook.setLayoutData(GridDataFactory.fillDefaults().indent(0, 0).grab(true, true).create());

		this.plotPageBook.addDisposeListener(disposeListener);
		this.plotPageBook.getSharedPlotBlockPreferences().addPropertyChangeListener(new IPropertyChangeListener() {

			@Override
			public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
				if (PlotPreferences.ENABLE_QUICK_CONTROLS.isEvent(event)) {
					final boolean hasFft = hasFft();
					if (parent == null || parent.isDisposed()) {
						return;
					}
					parent.getDisplay().asyncExec(new Runnable() {

						@Override
						public void run() {
							updateFftControls(hasFft);
						}

					});
					updateFftControls(hasFft);
				}
			}
		});
		createActions();
		createToolBars();
		createMenu();
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
	 * @param port ScaPort object to plot the output from.
	 * @param fftSettings settings to use if an FFT is to be displayed (null for none)
	 * @param qualifiers
	 * @return IDisposable (since 4.3, was IPlotSession in 4.2)
	 * @see PlotPageBook2#addSource2(PlotSource)
	 * @deprecated since 4.4, use PlotPageBook2#addSource2(PlotSource)
	 */
	@Deprecated
	public IDisposable addPlotSource(ScaUsesPort port, final FftSettings fftSettings, String qualifiers) {
		return this.plotPageBook.addSource(port, fftSettings, qualifiers);
	}

	public IDisposable addPlotSource(@NonNull PlotSource plotSource) {
		return this.plotPageBook.addSource(plotSource);
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
		menu = bars.getMenuManager();
		if (this.newPlotViewAction != null) {
			menu.add(this.newPlotViewAction);
		}
		if (this.adjustPlotSettingsAction != null) {
			menu.add(this.adjustPlotSettingsAction);
		}

		menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));

		if (this.plotSettingsAction != null) {
			menu.add(plotSettingsAction);
		}
	}

	/**
	 * Create the view toolbars.
	 */
	private void createToolBars() {
		final IActionBars bars = getViewSite().getActionBars();

		final IToolBarManager toolBarManager = bars.getToolBarManager();
		if (this.plotModeMenu != null) {
			toolBarManager.add(plotModeMenu);
		}
		if (this.fftSizeMenu != null) {
			toolBarManager.add(this.fftSizeMenu);
		}
		if (this.plotTypeAction != null) {
			toolBarManager.add(this.plotTypeAction);
		}
		toolBarManager.add(new Separator());
		toolBarManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	public static String createSecondaryId() {
		return String.valueOf(PlotView2.secondardId++);
	}

	/** Creates the actions. **/
	private void createActions() {
		this.plotTypeAction = new PlotTypeMenuAction(this.plotPageBook);

		final ImageDescriptor rasterImageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(PlotActivator.PLUGIN_ID, "icons/raster.png");
		this.plotTypeAction.setImageDescriptor(rasterImageDescriptor);

		this.newPlotViewAction = createNewPlotViewAction();

		this.plotModeMenu = new PlotModeMenuAction(plotPageBook);
		this.fftSizeMenu = new PlotFftMenuAction(plotPageBook);
		this.plotSettingsAction = new PlotSettingsAction(plotPageBook);
		boolean hasFft = hasFft();
		updateFftSizeMenu(hasFft);
		updateFftControls(hasFft);
		//		this.adjustPlotSettingsAction = createAdjustPlotSettingsAction();
	}

	private boolean hasFft() {
		for (PlotSource s : plotPageBook.getSources()) {
			if (s.getFftBlockSettings() != null) {
				return true;
			}
		}
		return false;
	}

	private void updateFftSizeMenu(boolean hasFft) {
		if (fftSizeMenu == null) {
			return;
		}
		fftSizeMenu.setEnabled(hasFft);
	}

	private void updateFftControls(boolean hasFft) {
		if (parent == null || parent.isDisposed()) {
			return;
		}
		if (!hasFft) {
			if (this.fftControls != null) {
				this.fftControls.dispose();
				this.fftControls = null;
				this.parent.layout(true, true);
			}
			return;
		}
		boolean controlsEnabled = false;
		controlsEnabled = PlotPreferences.ENABLE_QUICK_CONTROLS.getValue(plotPageBook.getSharedPlotBlockPreferences());

		if (!controlsEnabled) {
			if (this.fftControls != null) {
				this.fftControls.dispose();
				this.fftControls = null;
				this.parent.layout(true, true);
			}
			return;
		}

		if (this.fftControls != null) {
			return;
		}
		this.fftControls = new FftNumAvgControls(plotPageBook, parent);
		this.fftControls.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
		this.parent.layout(true, true);
		return;
	}

	private IAction createNewPlotViewAction() {
		IAction action = new Action() {
			@Override
			public void run() {
				try {
					final IViewPart newView = getSite().getPage().showView(getSite().getId(), PlotView2.createSecondaryId(), IWorkbenchPage.VIEW_ACTIVATE);
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
			} // end method
		};
		action.setEnabled(true);
		action.setText("New Plot View");
		action.setToolTipText("Open a new Plot View with all the same plots.");
		return action;
	}

	@Override
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

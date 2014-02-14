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

import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.PlotPageBook2;
import gov.redhawk.ui.port.nxmplot.PlotSettings;
import gov.redhawk.ui.port.nxmplot.PlotSettings.PlotMode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;

public class PlotModeMenuAction extends Action implements IMenuCreator {

	private class PlotModeAction extends Action {
		private final PlotMode mode;

		public PlotModeAction(PlotMode mode) {
			super(mode.getLabel(), IAction.AS_RADIO_BUTTON);
			this.mode = mode;
		}

		@Override
		public void run() {
			plotPageBook.getActivePlotWidget().setPlotMode(mode);
		}
	}

	private final PlotPageBook2 plotPageBook;
	private Menu fMenu;

	public PlotModeMenuAction(PlotPageBook2 plotPageBook) {
		super("&Plot Mode", IAction.AS_DROP_DOWN_MENU);
		setId(PlotView2.ID + ".mode");

		Bundle platformBundle = Platform.getBundle("org.eclipse.platform");
		if (platformBundle != null && platformBundle.getVersion().getMajor() < 4) {
			setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(PlotActivator.PLUGIN_ID, "icons/plotMode.png"));
		}

		setToolTipText("Change the plot's mode (" + PlotMode.IMAGINARY.getLabel() + ", " + PlotMode.TEN_LOG.getLabel() + ", etc.)");
		setMenuCreator(this);
		this.plotPageBook = plotPageBook;
	}

	@Override
	public void dispose() {
		if (fMenu != null) {
			fMenu.dispose();
			fMenu = null;
		}
	}

	@Override
	public Menu getMenu(Control parent) {
		if (fMenu != null) {
			fMenu.dispose();
		}
		List<PlotModeAction> modeActions = new ArrayList<PlotModeMenuAction.PlotModeAction>();

		fMenu = new Menu(parent);
		for (final PlotSettings.PlotMode mode : PlotSettings.PlotMode.values()) {
			PlotModeAction plotModeAction = new PlotModeAction(mode);
			modeActions.add(plotModeAction);
			addActionToMenu(fMenu, plotModeAction);
		}
		for (PlotModeAction a : modeActions) {
			a.setChecked(plotPageBook.getActivePlotWidget().getPlotMode() == a.mode);
		}
		return fMenu;
	}

	protected void addActionToMenu(Menu parent, Action action) {
		ActionContributionItem item = new ActionContributionItem(action);
		item.fill(parent, -1);
	}

	@Override
	public Menu getMenu(Menu parent) {
		return null;
	}
}

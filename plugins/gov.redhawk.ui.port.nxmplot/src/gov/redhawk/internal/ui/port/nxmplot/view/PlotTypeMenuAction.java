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
import gov.redhawk.ui.port.nxmplot.PlotType;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class PlotTypeMenuAction extends Action implements IMenuCreator {
	private final PlotPageBook2 plotPageBook;
	private Menu fMenu;
	private Menu fMenu2;

	private class PlotTypeAction extends Action {
		private final PlotType type;

		public PlotTypeAction(PlotType type) {
			super(type.toString(), IAction.AS_RADIO_BUTTON);
			this.type = type;
		}

		@Override
		public void run() {
			plotPageBook.showPlot(type);
		}
	}

	public PlotTypeMenuAction(PlotPageBook2 plotPageBook) {
		super("&Plot Type", IAction.AS_DROP_DOWN_MENU);
		final ImageDescriptor rasterImageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(PlotActivator.PLUGIN_ID, "icons/elcl16/raster.png");
		setImageDescriptor(rasterImageDescriptor);
		this.plotPageBook = plotPageBook;
		setToolTipText("Change the plot's type");
		setMenuCreator(this);
		setChecked(plotPageBook.getCurrentType() == PlotType.RASTER); // updates tool tip to display what action button will do
	}

	@Override
	public void run() {
		PlotType currentType = plotPageBook.getCurrentType();
		if (currentType == PlotType.RASTER) {
			plotPageBook.showPlot(PlotType.LINE);
			this.setChecked(false);
		} else {
			plotPageBook.showPlot(PlotType.RASTER);
			this.setChecked(true);
		}
	}

	@Override
	public void setChecked(final boolean checked) {
		super.setChecked(checked);
		//		if (!checked) {
		//			this.setToolTipText("Show Raster");
		//		} else {
		//			this.setToolTipText("Show Line");
		//		}
	}

	@Override
	public void dispose() {
		if (fMenu != null) {
			fMenu.dispose();
			fMenu = null;
		}
		if (fMenu2 != null) {
			fMenu2.dispose();
			fMenu2 = null;
		}
	}

	@Override
	public Menu getMenu(Control parent) {
		if (fMenu != null) {
			fMenu.dispose();
		}
		List<PlotTypeAction> typeActions = new ArrayList<PlotTypeAction>();

		fMenu = new Menu(parent);
		for (final PlotType type : PlotType.getStandardPlotTypes()) {
			PlotTypeAction plotTypeAction = new PlotTypeAction(type);
			typeActions.add(plotTypeAction);
			addActionToMenu(fMenu, plotTypeAction);
		}
		for (PlotTypeAction a : typeActions) {
			a.setChecked(plotPageBook.getActivePlotWidget().getPlotType() == a.type);
		}
		return fMenu;
	}

	protected void addActionToMenu(Menu parent, Action action) {
		ActionContributionItem item = new ActionContributionItem(action);
		item.fill(parent, -1);
	}

	@Override
	public Menu getMenu(Menu parent) {
		if (fMenu2 != null) {
			fMenu2.dispose();
		}
		List<PlotTypeAction> typeActions = new ArrayList<PlotTypeAction>();

		fMenu2 = new Menu(parent);
		for (final PlotType type : PlotType.getStandardPlotTypes()) {
			PlotTypeAction plotTypeAction = new PlotTypeAction(type);
			typeActions.add(plotTypeAction);
			addActionToMenu(fMenu2, plotTypeAction);
		}
		for (PlotTypeAction a : typeActions) {
			a.setChecked(plotPageBook.getActivePlotWidget().getPlotType() == a.type);
		}
		return fMenu2;
	}

} // end class PlotTypeMenuAction

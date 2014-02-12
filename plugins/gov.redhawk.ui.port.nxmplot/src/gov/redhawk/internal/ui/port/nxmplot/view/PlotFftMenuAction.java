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

import gov.redhawk.ui.port.nxmblocks.FftNxmBlock;
import gov.redhawk.ui.port.nxmplot.INxmBlock;
import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.PlotPageBook2;
import gov.redhawk.ui.port.nxmplot.PlotSource;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class PlotFftMenuAction extends Action implements IMenuCreator {

	private class FftAction extends Action {
		private final int size;

		public FftAction(int size) {
			super(size / 1024 + "k", IAction.AS_RADIO_BUTTON);
			this.size = size;
		}

		@Override
		public void run() {
			for (PlotSource s : plotPageBook.getSources()) {
				for (INxmBlock b : plotPageBook.getBlockChain(s)) {
					if (b instanceof FftNxmBlock) {
						((FftNxmBlock) b).setTransformSize(size);
					}
				}
			}
			for (IAction a : fftActions) {
				a.setChecked(false);
			}
			setChecked(true);
		}
	}

	private Menu fMenu;
	private List<FftAction> fftActions = new ArrayList<FftAction>();
	private final PlotPageBook2 plotPageBook;

	public PlotFftMenuAction(PlotPageBook2 plotPageBook) {
		super("fft:", IAction.AS_DROP_DOWN_MENU);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(PlotActivator.PLUGIN_ID, "icons/fftSize.png"));
		setId(PlotView2.ID + ".mode");
		setText("&FFT Size");
		setImageDescriptor(null);
		setMenuCreator(this);
		this.plotPageBook = plotPageBook;
	}

	private void updateMenu() {
		boolean found = false;
		for (PlotSource s : plotPageBook.getSources()) {
			if (s.getFftBlockSettings() != null) {
				found = true;
				List<INxmBlock> chain = plotPageBook.getBlockChain(s);
				for (INxmBlock b : chain) {
					if (b instanceof FftNxmBlock) {
						for (PlotFftMenuAction.FftAction a : fftActions) {
							a.setChecked(a.size == ((FftNxmBlock) b).getTransformSize());
						}
						break;
					}
				}
				break;
			}
		}
		setEnabled(found);
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
		fftActions.clear();

		fMenu = new Menu(parent);
		FftAction fftAction = new FftAction(1024);
		fftActions.add(fftAction);
		addActionToMenu(fMenu, fftAction);

		fftAction = new FftAction(2048);
		fftActions.add(fftAction);
		addActionToMenu(fMenu, fftAction);

		fftAction = new FftAction(4096);
		fftActions.add(fftAction);
		addActionToMenu(fMenu, fftAction);

		fftAction = new FftAction(8192);
		fftActions.add(fftAction);
		addActionToMenu(fMenu, fftAction);

		fftAction = new FftAction(16384);
		fftActions.add(fftAction);
		addActionToMenu(fMenu, fftAction);

		fftAction = new FftAction(32768);
		fftActions.add(fftAction);
		addActionToMenu(fMenu, fftAction);

		fftAction = new FftAction(65536);
		fftActions.add(fftAction);
		addActionToMenu(fMenu, fftAction);

		updateMenu();

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

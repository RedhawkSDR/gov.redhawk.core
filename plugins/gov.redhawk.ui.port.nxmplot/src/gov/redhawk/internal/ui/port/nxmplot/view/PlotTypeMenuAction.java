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

import gov.redhawk.ui.port.nxmplot.PlotPageBook2;
import gov.redhawk.ui.port.nxmplot.PlotType;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;

public class PlotTypeMenuAction extends Action {
	private final PlotPageBook2 plotPageBook;

	public PlotTypeMenuAction(PlotPageBook2 plotPageBook) {
		super("Change Plot Type", IAction.AS_PUSH_BUTTON | IAction.AS_CHECK_BOX | SWT.None);
		setChecked(plotPageBook.getCurrentType() == PlotType.RASTER); // updates tool tip to display what action button will do
		this.plotPageBook = plotPageBook;
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
		if (!checked) {
			this.setToolTipText("Show Raster");
		} else {
			this.setToolTipText("Show Line");
		}
	}
} // end class PlotTypeMenuAction

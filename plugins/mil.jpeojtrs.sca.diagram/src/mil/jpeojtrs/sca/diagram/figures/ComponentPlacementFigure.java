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
package mil.jpeojtrs.sca.diagram.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;

public class ComponentPlacementFigure extends NodeFigure {

	private WrappingLabel fFigureComponentPlacementLabelFigure;

	private IFigure fFigureComponentPlacementCompartmentFigure;

	public ComponentPlacementFigure() {

		final GridLayout layoutThis = new GridLayout();
		layoutThis.numColumns = 1;
		this.setLayoutManager(layoutThis);

		createContents();
	}

	private void createContents() {
		this.setBorder(null);

		this.fFigureComponentPlacementLabelFigure = new WrappingLabel();
		this.fFigureComponentPlacementLabelFigure.setText("<...>");
		this.fFigureComponentPlacementLabelFigure.setBorder(null);

		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalIndent = 20;
		this.add(this.fFigureComponentPlacementLabelFigure, gridData);

		this.fFigureComponentPlacementCompartmentFigure = new Figure();
		this.fFigureComponentPlacementCompartmentFigure.setBorder(null);

		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_FILL;
		gridData.verticalAlignment = GridData.VERTICAL_ALIGN_FILL;
		this.add(this.fFigureComponentPlacementCompartmentFigure, gridData);

	}

	public WrappingLabel getFigureComponentPlacementLabelFigure() {
		return this.fFigureComponentPlacementLabelFigure;
	}

	public IFigure getFigureComponentPlacementCompartmentFigure() {
		return this.fFigureComponentPlacementCompartmentFigure;
	}

}

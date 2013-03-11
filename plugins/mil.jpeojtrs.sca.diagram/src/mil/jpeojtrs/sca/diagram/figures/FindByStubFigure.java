/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package mil.jpeojtrs.sca.diagram.figures;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;

public class FindByStubFigure extends RectangleFigure {

	private Figure fFigureFindByStubCompartmentFigure;
	private WrappingLabel fFigureFindByStubLabel;

	public FindByStubFigure() {

		final BorderLayout layoutThis = new BorderLayout();
		this.setLayoutManager(layoutThis);
		this.setBorder(new MarginBorder(5, 5, 5, 5));
		createContents();
	}

	private void createContents() {

		this.fFigureFindByStubLabel = new WrappingLabel();
		this.fFigureFindByStubLabel.setText("FindBy");

		this.add(this.fFigureFindByStubLabel, BorderLayout.TOP);

		this.fFigureFindByStubCompartmentFigure = new Figure() {

		};

		this.add(this.fFigureFindByStubCompartmentFigure, BorderLayout.CENTER);

		final FlowLayout layoutFFigureFindByCompartmentFigure = new FlowLayout();
		layoutFFigureFindByCompartmentFigure.setStretchMinorAxis(false);
		layoutFFigureFindByCompartmentFigure.setMinorAlignment(FlowLayout.ALIGN_LEFTTOP);

		layoutFFigureFindByCompartmentFigure.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
		layoutFFigureFindByCompartmentFigure.setMajorSpacing(5);
		layoutFFigureFindByCompartmentFigure.setMinorSpacing(5);
		layoutFFigureFindByCompartmentFigure.setHorizontal(true);

		this.fFigureFindByStubCompartmentFigure.setLayoutManager(layoutFFigureFindByCompartmentFigure);
		this.fFigureFindByStubCompartmentFigure.setBorder(null);
	}

	public IFigure getFigureFindByStubCompartmentFigure() {
		return this.fFigureFindByStubCompartmentFigure;
	}

	public WrappingLabel getFigureFindByStubLabel() {
		return this.fFigureFindByStubLabel;
	}

}

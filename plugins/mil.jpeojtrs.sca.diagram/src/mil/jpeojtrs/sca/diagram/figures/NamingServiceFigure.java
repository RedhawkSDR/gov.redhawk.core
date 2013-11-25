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

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;

public class NamingServiceFigure extends RectangleFigure {

	private WrappingLabel fFigureNamingServiceNameLabel;

	public NamingServiceFigure() {

		final FlowLayout layoutThis = new FlowLayout();
		layoutThis.setStretchMinorAxis(false);
		layoutThis.setMinorAlignment(OrderedLayout.ALIGN_TOPLEFT);

		layoutThis.setMajorAlignment(OrderedLayout.ALIGN_TOPLEFT);
		layoutThis.setMajorSpacing(5);
		layoutThis.setMinorSpacing(5);
		layoutThis.setHorizontal(true);

		this.setLayoutManager(layoutThis);

		createContents();
	}

	private void createContents() {

		this.fFigureNamingServiceNameLabel = new WrappingLabel();
		this.fFigureNamingServiceNameLabel.setText("<...>");

		this.add(this.fFigureNamingServiceNameLabel);

	}

	public WrappingLabel getFigureNamingServiceNameLabel() {
		return this.fFigureNamingServiceNameLabel;
	}

}

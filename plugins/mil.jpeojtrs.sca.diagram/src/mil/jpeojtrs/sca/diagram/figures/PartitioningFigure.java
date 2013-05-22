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

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;

public class PartitioningFigure extends RectangleFigure {

	private RectangleFigure fFigurePartitioningCompartmentFigure;

	private WrappingLabel fFigurePartitioningLabel;

	public PartitioningFigure() {

		final BorderLayout layoutThis = new BorderLayout();
		this.setLayoutManager(layoutThis);

		createContents();
	}

	private void createContents() {

		this.fFigurePartitioningLabel = new WrappingLabel();
		this.fFigurePartitioningLabel.setText("Partitioning");

		this.add(this.fFigurePartitioningLabel, BorderLayout.TOP);

		this.fFigurePartitioningCompartmentFigure = new RectangleFigure();

		this.add(this.fFigurePartitioningCompartmentFigure, BorderLayout.CENTER);

	}

	public RectangleFigure getFigurePartitioningCompartmentFigure() {
		return this.fFigurePartitioningCompartmentFigure;
	}

	public WrappingLabel getFigurePartitioningLabel() {
		return this.fFigurePartitioningLabel;
	}

}

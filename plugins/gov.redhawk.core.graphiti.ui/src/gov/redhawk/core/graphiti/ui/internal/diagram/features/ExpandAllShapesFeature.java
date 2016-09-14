/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.core.graphiti.ui.internal.diagram.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;

public class ExpandAllShapesFeature extends AbstractCustomFeature {

	public ExpandAllShapesFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getDescription() {
		return "Expand All Shapes";
	}

	@Override
	public String getName() {
		return "&Expand All Shapes";
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length == 1 && pes[0] instanceof Diagram) {
			return true;
		}
		return false;
	}

	@Override
	public void execute(ICustomContext context) {
		final Diagram diagram = getDiagram();

		// expand existing shapes in diagram
		for (PictogramElement p : diagram.getChildren()) { // TODO: need to handle inside host collocation
			RHContainerShape rhContainerShape = (RHContainerShape) p;
			rhContainerShape.setCollapsed(false);
			updatePictogramElement(rhContainerShape);
			layoutPictogramElement(rhContainerShape);
		}

		updatePictogramElement(diagram);
	}
}

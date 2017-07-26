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
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import mil.jpeojtrs.sca.sad.HostCollocation;

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
		for (Shape shape : diagram.getChildren()) {
			if (DUtil.getBusinessObject(shape) instanceof HostCollocation) {
				for (Shape hcChildShape : ((ContainerShape) shape).getChildren()) {
					if (hcChildShape instanceof RHContainerShape) {
						expandShape((RHContainerShape) hcChildShape);
					}
				}

			} else if (shape instanceof RHContainerShape) {
				expandShape((RHContainerShape) shape);
			}

		}
		updatePictogramElement(diagram);
	}

	private void expandShape(RHContainerShape rhContainerShape) {
		rhContainerShape.setCollapsed(false);
		updatePictogramElement(rhContainerShape);
		layoutPictogramElement(rhContainerShape);
	}
}

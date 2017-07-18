/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.core.graphiti.ui.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class LayoutUtil {

	// Spacing/distance constants
	private static final int DIAGRAM_SHAPE_HORIZONTAL_PADDING = 100;
	private static final int DIAGRAM_SHAPE_SIBLING_VERTICAL_PADDING = 5;
	private static final int DIAGRAM_SHAPE_ROOT_VERTICAL_PADDING = 50;

	protected LayoutUtil() {

	}

	// do this because we need to pass it to layout diagram, assumes we already have shapes drawn of a certain
	// size and that we are just moving them
	public static Point calculateDiagramBounds(Diagram diagram) {
		// get all shapes in diagram, components, findby's etc
		List<RHContainerShape> rootShapes = new ArrayList<RHContainerShape>();
		for (Shape shape : diagram.getChildren()) {
			if (shape instanceof RHContainerShape) {
				RHContainerShape rhContainerShape = (RHContainerShape) shape;
				// if it has no provides ports or it has ports WITH NO CONNECTIONS than its a root in the tree
				if (rhContainerShape.getProvidesPortStubs() != null && (rhContainerShape.getProvidesPortStubs().size() < 1
					|| DUtil.getIncomingConnectionsContainedInContainerShape(rhContainerShape).size() < 1)) {
					rootShapes.add(rhContainerShape);
				}
			}
		}

		// combine dimensions of each root tree to determine total dimension required to house all shapes in diagram
		int height = 0;
		int width = 0;
		for (RHContainerShape shape : rootShapes) {
			Point childTreeDimension = calculateTreeDimensions(shape);
			height += childTreeDimension.getY();
			// use largest width
			width = Math.max(childTreeDimension.getX(), width);
		}
		// add padding between roots
		height += DIAGRAM_SHAPE_ROOT_VERTICAL_PADDING * rootShapes.size() - 1;

		Point point = StylesFactory.eINSTANCE.createPoint();
		point.setX(width);
		point.setY(height);
		return point;
	}

	/**
	 * Returns dimensions required to contain all shapes aligned in a horizontal tree diagram beginning with the
	 * provided root shape.
	 * @param rootShape
	 * @return
	 */
	private static Point calculateTreeDimensions(RHContainerShape rootShape) {
		return calculateTreeDimensions(rootShape, new HashSet<RHContainerShape>());
	}

	/**
	 * Internal method used by {@link #calculateTreeDimensions(RHContainerShape)}.
	 * @param rootShape
	 * @return
	 */
	private static Point calculateTreeDimensions(RHContainerShape rootShape, Set<RHContainerShape> visitedShapes) {
		// Keep track of the shape we're visiting; if we've been here, we're in a circular recursion
		if (!visitedShapes.add(rootShape)) {
			return null;
		}

		int height = rootShape.getGraphicsAlgorithm().getHeight();
		int width = rootShape.getGraphicsAlgorithm().getWidth();
		int childWidth = 0;
		int childHeight = 0;

		List<Connection> outs = DUtil.getOutgoingConnectionsContainedInContainerShape(rootShape);
		for (Connection conn : outs) {
			RHContainerShape targetRHContainerShape = ScaEcoreUtils.getEContainerOfType(conn.getEnd(), RHContainerShape.class);
			Point childDimension = calculateTreeDimensions(targetRHContainerShape, visitedShapes);
			if (childDimension == null) {
				continue;
			}
			childHeight += childDimension.getY() + DIAGRAM_SHAPE_SIBLING_VERTICAL_PADDING;
			// use largest width but don't add
			childWidth = Math.max(childDimension.getX(), childWidth);
		}
		if (outs.size() > 0) {
			width += childWidth + DIAGRAM_SHAPE_HORIZONTAL_PADDING;
		}
		// choose the largest of parent height or combined child height
		height = Math.max(childHeight, height);

		Point point = StylesFactory.eINSTANCE.createPoint();
		point.setX(width);
		point.setY(height);
		return point;
	}

}

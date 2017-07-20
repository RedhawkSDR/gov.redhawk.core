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

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class LayoutUtil {

	// Spacing/distance constants
	private static final int DIAGRAM_SHAPE_HORIZONTAL_PADDING = 100;
	private static final int HOST_COLLOCATION_SHAPE_HORIZONTAL_PADDING = 150;
	private static final int DIAGRAM_SHAPE_SIBLING_VERTICAL_PADDING = 5;
	private static final int DIAGRAM_SHAPE_ROOT_VERTICAL_PADDING = 50;

	protected LayoutUtil() {

	}

	/**
	 * Calculate the width/height boundaries for the container shape.<br/>
	 * Used as part of the layout process to determine contained shape locations.
	 * 
	 * @param containerShape - Should be either the Diagram or a HostCollocation
	 * @return {@link Point} where x is the right-most bound and y is the bottom-most bound
	 */
	public static Point calculateContainerBounds(ContainerShape containerShape) {
		boolean isHostCo = false;
		if (DUtil.getBusinessObject(containerShape) instanceof HostCollocation) {
			isHostCo = true;
		}

		// get root all shapes in diagram, components, findby's etc
		List<ContainerShape> rootShapes = new ArrayList<ContainerShape>();
		List<Shape> unexaminedShapes = new ArrayList<>();
		unexaminedShapes.addAll(containerShape.getChildren());
		for (Shape shape : containerShape.getChildren()) {
			if (shape instanceof RHContainerShape) {
				checkIfRoot((RHContainerShape) shape, rootShapes, containerShape);
			} else if (DUtil.getBusinessObject(shape) != null && DUtil.getBusinessObject(shape) instanceof HostCollocation) {
				checkIfHostCoRoot((ContainerShape) shape, rootShapes);
			}
		}

		// combine dimensions of each root tree to determine total dimension required to house all shapes in diagram
		int height = 0;
		int width = 0;
		for (ContainerShape rootShape : rootShapes) {
			Point childTreeDimension = calculateTreeDimensions(rootShape, unexaminedShapes, isHostCo, new HashSet<ContainerShape>());
			height += childTreeDimension.getY();
			// use largest width
			width = Math.max(childTreeDimension.getX(), width);
		}

		// Account for any shapes that were not yet touched (possibly because they were in feedback loops)
		for (int i = 0; i < unexaminedShapes.size(); i++) {
			if (unexaminedShapes.get(i) instanceof ContainerShape) {
				ContainerShape shape = (ContainerShape) unexaminedShapes.get(i);
				Point childTreeDimension = calculateTreeDimensions(shape, unexaminedShapes, isHostCo, new HashSet<ContainerShape>());
				height += childTreeDimension.getY();
				// use largest width
				width = Math.max(childTreeDimension.getX(), width);
			}
		}

		// add padding between roots
		height += DIAGRAM_SHAPE_ROOT_VERTICAL_PADDING * (rootShapes.size() - 1);

		Point point = StylesFactory.eINSTANCE.createPoint();
		point.setX(width);
		point.setY(height);

		return point;
	}

	/**
	 * If the provided {@link RHContainerShape} has no provides ports or it has ports with no connections than it is a
	 * root of a tree and is added to the list of rootShapes
	 * 
	 * @param rhContainerShape - Shape to be inspected
	 * @param rootShapes - List of rootShapes for the parent container
	 * @param containerShape - Top-level container shape. Should be either the Diagram or a HostCollocation
	 */
	private static void checkIfRoot(RHContainerShape rhContainerShape, List<ContainerShape> rootShapes, ContainerShape containerShape) {
		boolean inHostCollocation = (DUtil.getBusinessObject(containerShape) instanceof HostCollocation) ? true : false;

		if (!inHostCollocation) {
			List<Connection> incomingConnections = DUtil.getIncomingConnectionsContainedInContainerShape(rhContainerShape);
			if (incomingConnections.size() < 1) {
				rootShapes.add(rhContainerShape);
			}
		} else {
			// HostCo children are root relative to the HostCollocation only, other components won't be considered
			List<Connection> incomingConnections = DUtil.getIncomingConnectionsContainedInContainerShape(rhContainerShape);

			// If there are no incoming connections then this is automatically a root shape
			if (incomingConnections.size() < 1) {
				rootShapes.add(rhContainerShape);
				return;
			}

			// Otherwise check connections. If all originating components are OUTSIDE of the HostCollocation, then this
			// is still a root shape, at least relative to the HostCollocation's internal layout
			EList<Shape> compShapes = containerShape.getChildren();
			for (Connection connection : incomingConnections) {
				RHContainerShape originatingComp = ScaEcoreUtils.getEContainerOfType(connection.getStart(), RHContainerShape.class);
				if (compShapes.contains(originatingComp)) {
					// Not a root shape
					return;
				}
			}

			// Only get here for root shapes. Add to list and return.
			rootShapes.add(rhContainerShape);
		}
	}

	/**
	 * Used to check if a HostCollocation itself should be treated as a root shape, or as a tree leaf.<br/>
	 * Determined by checking to see if any contained component has an incoming connection originating outside the
	 * HostCollocation.
	 * 
	 * @param hostCoShape - HostCollocation shape to be inspected
	 * @param rootShapes - List of rootShapes for the parent container
	 */
	private static void checkIfHostCoRoot(ContainerShape hostCoShape, List<ContainerShape> rootShapes) {
		boolean isHostCoRoot = true;

		EList<Shape> compShapes = hostCoShape.getChildren();
		out: for (Shape childShape : compShapes) {
			RHContainerShape compShape = (RHContainerShape) childShape;

			// Check all incoming connections for components inside the HostCollocation.
			// If there is at least one externally originating connection. This HostCollocation is not a root.
			List<Connection> connections = DUtil.getIncomingConnectionsContainedInContainerShape(compShape);
			for (Connection connection : connections) {
				RHContainerShape originatingComp = ScaEcoreUtils.getEContainerOfType(connection.getStart(), RHContainerShape.class);
				if (!compShapes.contains(originatingComp)) {
					isHostCoRoot = false;
					break out;
				}
			}
		}

		if (isHostCoRoot) {
			rootShapes.add(hostCoShape);
		}
	}

	/**
	 * Internal method used by {@link #calculateTreeDimensions(RHContainerShape)}.
	 * @param currentShape
	 * @param unexaminedShapes
	 * @param isHostCo - true if the ultimate container is a HostCollocation. False if it is the Diagram.
	 * @param visitedShapes
	 * @return
	 */
	private static Point calculateTreeDimensions(ContainerShape currentShape, List<Shape> unexaminedShapes, boolean isHostCo,
		Set<ContainerShape> visitedShapes) {
		// Keep track of the shape we're visiting; if we've been here, we're in a circular recursion
		if (!visitedShapes.add(currentShape)) {
			return null;
		}
		unexaminedShapes.remove(currentShape);

		int height = currentShape.getGraphicsAlgorithm().getHeight();
		int width = currentShape.getGraphicsAlgorithm().getWidth();
		int childWidth = 0;
		int childHeight = 0;

		List<Connection> outs = DUtil.getOutgoingConnectionsContainedInContainerShape(currentShape);
		for (Connection conn : outs) {
			ContainerShape targetContainerShape = ScaEcoreUtils.getEContainerOfType(conn.getEnd(), RHContainerShape.class);
			Point childDimension = null;

			if (isHostCo) {
				// If the root container is a HostCollocation then both the source and the target must be inside the
				// same HostCollocation, other wise do not consider the relationship. This works, because of the else
				// block which builds a top-level tree where the HostCollocation is treated as a single shape.
				if (currentShape.getContainer() != targetContainerShape.getContainer()) {
					continue;
				}
			} else {
				// At this level we only care about the dimensions of the HostCollocation, not of any contained shapes
				if (DUtil.getBusinessObject(targetContainerShape.getContainer()) instanceof HostCollocation) {
					targetContainerShape = targetContainerShape.getContainer();
				}
			}

			childDimension = calculateTreeDimensions(targetContainerShape, unexaminedShapes, isHostCo, visitedShapes);
			if (childDimension == null) {
				continue;
			}
			childHeight += childDimension.getY() + DIAGRAM_SHAPE_SIBLING_VERTICAL_PADDING;
			// use largest width but don't add
			childWidth = Math.max(childDimension.getX(), childWidth);
		}

		// Add padding as necessary. This is somewhat of a hack, as it really should be handled by the layout algorithm.
		if (outs.size() > 0) {
			if (DUtil.getBusinessObject(currentShape) instanceof HostCollocation) {
				// Use a slightly larger padding when HC are involved, to try an minimize the overlapping cause by the
				// algorithms deficiencies
				width += childWidth + HOST_COLLOCATION_SHAPE_HORIZONTAL_PADDING;
			} else {
				width += childWidth + DIAGRAM_SHAPE_HORIZONTAL_PADDING;
			}
		}

		// choose the largest of parent height or combined child height
		height = Math.max(childHeight, height);

		Point point = StylesFactory.eINSTANCE.createPoint();
		point.setX(width);
		point.setY(height);
		return point;
	}
}

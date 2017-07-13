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
package gov.redhawk.core.graphiti.ui.diagram.features;

import org.eclipse.zest.layouts.algorithms.HorizontalTreeLayoutAlgorithm;
import org.eclipse.zest.layouts.dataStructures.BendPoint;
import org.eclipse.zest.layouts.dataStructures.DisplayIndependentDimension;
import org.eclipse.zest.layouts.dataStructures.DisplayIndependentPoint;
import org.eclipse.zest.layouts.dataStructures.DisplayIndependentRectangle;
import org.eclipse.zest.layouts.dataStructures.InternalNode;
import org.eclipse.zest.layouts.dataStructures.InternalRelationship;

public class TmpLayoutAlg extends HorizontalTreeLayoutAlgorithm {

	private double thisWidthToHeightRatio;
	
	public TmpLayoutAlg(int styles) {
		super(styles);
		this.thisWidthToHeightRatio = 1.0;
	}

	@Override
	protected void defaultFitWithinBounds(InternalNode[] entitiesToLayout, DisplayIndependentRectangle realBounds) {
		InternalRelationship[] relationships = new InternalRelationship[0];

		DisplayIndependentRectangle layoutBounds;

		if (resizeEntitiesAfterLayout) {
			layoutBounds = getLayoutBounds(entitiesToLayout, true);

			// Convert node x,y to be in percent rather than absolute coords
			convertPositionsToPercentage(entitiesToLayout, relationships, layoutBounds, false /*do not update size*/);

			// Resize and shift nodes
			resizeAndShiftNodes(entitiesToLayout);
		}

		// Recalculate layout, allowing for the node width, which we now know
		layoutBounds = getLayoutBounds(entitiesToLayout, true);

		// adjust node positions again, to the new coordinate system (still as a percentage)
		convertPositionsToPercentage(entitiesToLayout, relationships, layoutBounds, true /*update node size*/);

		DisplayIndependentRectangle screenBounds = calcScreenBounds(realBounds, layoutBounds);

		// Now convert to real screen coordinates
		convertPositionsToCoords(entitiesToLayout, relationships, screenBounds);
	}
	
	/**
	 * Convert all node positions into a percentage of the screen. If includeNodeSize
	 * is true then this also updates the node's internal size. 
	 * @param entitiesToLayout
	 */
	private void convertPositionsToPercentage(InternalNode[] entitiesToLayout, InternalRelationship[] relationships, DisplayIndependentRectangle layoutBounds, boolean includeNodeSize) {

		// Adjust node positions and sizes
		for (int i = 0; i < entitiesToLayout.length; i++) {
			InternalNode node = entitiesToLayout[i];
			DisplayIndependentPoint location = node.getInternalLocation().convertToPercent(layoutBounds);
			node.setInternalLocation(location.x, location.y);
			if (includeNodeSize) { // adjust node sizes
				double width = node.getInternalWidth() / layoutBounds.width;
				double height = node.getInternalHeight() / layoutBounds.height;
				node.setInternalSize(width, height);
			}
		}

		// Adjust bendpoint positions
		for (int i = 0; i < relationships.length; i++) {
			InternalRelationship rel = relationships[i];
			for (int j = 0; j < rel.getBendPoints().size(); j++) {
				BendPoint bp = (BendPoint) rel.getBendPoints().get(j);
				DisplayIndependentPoint toPercent = bp.convertToPercent(layoutBounds);
				bp.setX(toPercent.x);
				bp.setY(toPercent.y);
			}
		}
	}
	
	/**
	 * Find and set the node size - shift the nodes to the right and down to make 
	 * room for the width and height. 
	 * @param entitiesToLayout
	 * @param relationships
	 */
	private void resizeAndShiftNodes(InternalNode[] entitiesToLayout) {
		// get maximum node size as percent of screen dimmensions
		double nodeSize = getNodeSize(entitiesToLayout);
		double halfNodeSize = nodeSize / 2;

		// Resize and shift nodes
		for (int i = 0; i < entitiesToLayout.length; i++) {
			InternalNode node = entitiesToLayout[i];
			node.setInternalSize(nodeSize, nodeSize);
			node.setInternalLocation(node.getInternalX() + halfNodeSize, node.getInternalY() + halfNodeSize);
		}
	}
	
	/**
	 * Returns the maximum possible node size as a percentage of the width or height in current coord system.
	 */
	private double getNodeSize(InternalNode[] entitiesToLayout) {
		double width, height;
		if (entitiesToLayout.length == 1) {
			width = 0.8;
			height = 0.8;
		} else {
			DisplayIndependentDimension minimumDistance = getMinimumDistance(entitiesToLayout);
			width = 0.8 * minimumDistance.width;
			height = 0.8 * minimumDistance.height;
		}
		return Math.max(width, height);
	}
	/** 
	 * minDistance is the closest that any two points are together.
	 * These two points become the center points for the two closest nodes, 
	 * which we wish to make them as big as possible without overlapping.
	 * This will be the maximum of minDistanceX and minDistanceY minus a bit, lets say 20%
	 * 
	 * We make the recommended node size a square for convenience.
	 * 
	 * 
	 *    _______
	 *   |       | 
	 *   |       |
	 *   |   +   |
	 *   |   |\  |
	 *   |___|_\_|_____
	 *       | | \     |
	 *       | |  \    |
	 *       +-|---+   |
	 *         |       |
	 *         |_______|
	 * 
	 *  
	 * 
	 */
	private DisplayIndependentDimension getMinimumDistance(InternalNode[] entitiesToLayout) {
		DisplayIndependentDimension horAndVertdistance = new DisplayIndependentDimension(Double.MAX_VALUE, Double.MAX_VALUE);
		double minDistance = Double.MAX_VALUE; // the minimum distance between all the nodes
		//TODO: Very Slow!
		for (int i = 0; i < entitiesToLayout.length; i++) {
			InternalNode layoutEntity1 = entitiesToLayout[i];
			double x1 = layoutEntity1.getInternalX();
			double y1 = layoutEntity1.getInternalY();
			for (int j = i + 1; j < entitiesToLayout.length; j++) {
				InternalNode layoutEntity2 = entitiesToLayout[j];
				double x2 = layoutEntity2.getInternalX();
				double y2 = layoutEntity2.getInternalY();
				double distanceX = Math.abs(x1 - x2);
				double distanceY = Math.abs(y1 - y2);
				double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));

				if (distance < minDistance) {
					minDistance = distance;
					horAndVertdistance.width = distanceX;
					horAndVertdistance.height = distanceY;
				}
			}
		}
		return horAndVertdistance;
	}
	
	/**
	 * Calculate the screen bounds, maintaining the  
	 * @param realBounds
	 * @return
	 */
	private DisplayIndependentRectangle calcScreenBounds(DisplayIndependentRectangle realBounds, DisplayIndependentRectangle layoutBounds) {
		if (resizeEntitiesAfterLayout) { // OK to alter aspect ratio 
			double borderWidth = Math.min(realBounds.width, realBounds.height) / 10.0; // use 10% for the border - 5% on each side
			return new DisplayIndependentRectangle(realBounds.x + borderWidth / 2.0, realBounds.y + borderWidth / 2.0, realBounds.width - borderWidth, realBounds.height - borderWidth);
		} else { // retain layout aspect ratio 
			double heightAdjustment = realBounds.height / layoutBounds.height;
			double widthAdjustment = realBounds.width / layoutBounds.width;
			double ratio = Math.min(heightAdjustment, widthAdjustment);
			double adjustedHeight = layoutBounds.height * ratio;
			double adjustedWidth = layoutBounds.width * ratio;
			double adjustedX = realBounds.x + (realBounds.width - adjustedWidth) / 2.0;
			double adjustedY = realBounds.y + (realBounds.height - adjustedHeight) / 2.0;
			double borderWidth = Math.min(adjustedWidth, adjustedHeight) / 10.0; // use 10% for the border - 5% on each side
			return new DisplayIndependentRectangle(adjustedX + borderWidth / 2.0, adjustedY + borderWidth / 2.0, adjustedWidth - borderWidth, adjustedHeight - borderWidth);
		}
	}
	
	/**
	 * Convert the positions from a percentage of bounds area to fixed
	 * coordinates. NOTE: ALL OF THE POSITIONS OF NODES UNTIL NOW WERE FOR THE
	 * CENTER OF THE NODE - Convert it to the left top corner.
	 * 
	 * @param entitiesToLayout
	 * @param relationships
	 * @param realBounds
	 */
	private void convertPositionsToCoords(InternalNode[] entitiesToLayout, InternalRelationship[] relationships, DisplayIndependentRectangle screenBounds) {

		// Adjust node positions and sizes
		for (int i = 0; i < entitiesToLayout.length; i++) {
			InternalNode node = entitiesToLayout[i];
			double width = node.getInternalWidth() * screenBounds.width;
			double height = node.getInternalHeight() * screenBounds.height;
			DisplayIndependentPoint location = node.getInternalLocation().convertFromPercent(screenBounds);
			node.setInternalLocation(location.x - width / 2, location.y - height / 2);
			if (resizeEntitiesAfterLayout) {
				adjustNodeSizeAndPos(node, height, width);
			} else {
				node.setInternalSize(width, height);
			}
		}

		// Adjust bendpoint positions and shift based on source node size
		for (int i = 0; i < relationships.length; i++) {
			InternalRelationship rel = relationships[i];
			for (int j = 0; j < rel.getBendPoints().size(); j++) {
				BendPoint bp = (BendPoint) rel.getBendPoints().get(j);
				DisplayIndependentPoint fromPercent = bp.convertFromPercent(screenBounds);
				bp.setX(fromPercent.x);
				bp.setY(fromPercent.y);
			}
		}
	}
	
	/**
	 * Adjust node size to take advantage of space. Reset position to top left corner of node. 
	 * @param node
	 * @param height
	 * @param width
	 */
	private void adjustNodeSizeAndPos(InternalNode node, double height, double width) {
		double widthUsingHeight = height * thisWidthToHeightRatio;
		if (thisWidthToHeightRatio <= 1.0 && widthUsingHeight <= width) {
			double widthToUse = height * thisWidthToHeightRatio;
			double leftOut = width - widthToUse;
			node.setInternalSize(Math.max(height * thisWidthToHeightRatio, MIN_ENTITY_SIZE), Math.max(height, MIN_ENTITY_SIZE));
			node.setInternalLocation(node.getInternalX() + leftOut / 2, node.getInternalY());

		} else {
			double heightToUse = height / thisWidthToHeightRatio;
			double leftOut = height - heightToUse;

			node.setInternalSize(Math.max(width, MIN_ENTITY_SIZE), Math.max(width / thisWidthToHeightRatio, MIN_ENTITY_SIZE));
			node.setInternalLocation(node.getInternalX(), node.getInternalY() + leftOut / 2);
		}

	}
	
	@Override
	public void setEntityAspectRatio(double ratio) {
		thisWidthToHeightRatio = ratio;
		super.setEntityAspectRatio(ratio);
	}
}

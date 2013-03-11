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
package gov.redhawk.diagram.layout;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;

/**
 * 
 */
public class CustomBorderItemLocator extends BorderItemLocator {

	public CustomBorderItemLocator(final IFigure parentFigure, final int preferredSide) {
		super(parentFigure, preferredSide);
	}

	@Override
	public Rectangle getValidLocation(final Rectangle proposedLocation, final IFigure borderItem) {
		final Rectangle realLocation = new Rectangle(proposedLocation);
		final Point newTopLeft = locateOnBorder(realLocation.getTopLeft(), this.getPreferredSideOfParent(), 0, borderItem);
		realLocation.setLocation(newTopLeft);
		return realLocation;
	}

	@Override
	protected void calculateNextNonConflictingPosition(final Point currentLocation, final int interval, int currentSide, final IFigure borderItem,
	        final Rectangle obstacle) {
		currentSide = getPreferredSideOfParent();
		switch (currentSide) {
		case PositionConstants.EAST:
			currentLocation.y = Math.max(obstacle.getBottomLeft().y, obstacle.getBottomRight().y) + interval;
			return;
		default:
			break;
		}
		super.calculateNextNonConflictingPosition(currentLocation, interval, currentSide, borderItem, obstacle);
	}

	/**
	 * Override to always be on perferred side
	 * {@inheritDoc}
	 */
	@Override
	public void setCurrentSideOfParent(final int side) {
		super.setCurrentSideOfParent(getPreferredSideOfParent());
	}

	@Override
	protected Point getPreferredLocation(final int side, final IFigure borderItem) {
		return getPreferredLocation(borderItem);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Point getPreferredLocation(final IFigure borderItem) {
		final Rectangle bounds = getParentBorder();
		final int parentFigureWidth = bounds.width;
		final int parentFigureHeight = bounds.height;
		final int parentFigureX = bounds.x;
		final int parentFigureY = bounds.y;
		int x = parentFigureX;
		int y = parentFigureY;
		final int side = getPreferredSideOfParent();
		int adjustment = 30; // SUPPRESS CHECKSTYLE MagicNumber

		final Dimension borderItemSize = getSize(borderItem);

		if (side == PositionConstants.WEST) {
			x = parentFigureX - borderItemSize.width + getBorderItemOffset().width;
			y += adjustment;
		} else if (side == PositionConstants.EAST) {
			x = parentFigureX + parentFigureWidth - getBorderItemOffset().width;
			y += adjustment;
		} else if (side == PositionConstants.NORTH) {
			y = parentFigureY - borderItemSize.height + getBorderItemOffset().height;
			x += adjustment;
		} else if (side == PositionConstants.SOUTH) {
			x += adjustment;
			y = parentFigureY + parentFigureHeight - getBorderItemOffset().height;
		}
		return new Point(x, y);
	}

}

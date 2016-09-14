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
package gov.redhawk.core.graphiti.ui.util;

import java.util.List;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

/**
 * Utility methods for doing diagram updates and layouts.
 */
public class UpdateUtil {

	private UpdateUtil() {
	}

	/**
	 * Deletes a {@link PictogramElement} if it is non-null.
	 * @param pe PictogramElement to delete
	 * @return true if pe was non-null and deleted, false if it was null.
	 */
	public static boolean deleteIfNeeded(PictogramElement pe) {
		if (pe != null) {
			DUtil.fastDeletePictogramElement(pe);
			return true;
		}
		return false;
	}

	/**
	 * Deletes any non-null {@link PictogramElement}s.
	 * @param pictogramList PictogramElements to delete
	 * @return true if any PictogramElement was non-null and deleted, false if none were deleted
	 */
	public static boolean deleteIfNeeded(PictogramElement... pictogramList) {
		boolean deleted = false;
		for (PictogramElement pictogramElement : pictogramList) {
			if (pictogramElement != null) {
				DUtil.fastDeletePictogramElement(pictogramElement);
				deleted = true;
			}
		}
		return deleted;
	}

	/**
	 * Performs a layout on the overlaid {@link FixPointAnchor} for a shape so that it has the same dimensions.
	 * If necessary, the anchor point will be moved to maintain the horizontal alignment.
	 *
	 * @param parentShape shape containing the anchor
	 * @param hAlign horizontal alignment of anchor
	 * @return true if the anchor was updated, false if it already matched the shape
	 */
	public static boolean layoutOverlayAnchor(Shape parentShape, Orientation hAlign) {
		// Layout and resize anchor
		IDimension parentSize = Graphiti.getGaLayoutService().calculateSize(parentShape.getGraphicsAlgorithm());
		FixPointAnchor portAnchor = (FixPointAnchor) parentShape.getAnchors().get(0);
		Point anchorLocation = portAnchor.getLocation();

		// Adjust the anchor location
		int anchorY = parentSize.getHeight() / 2;
		int anchorX = 0;
		if (Orientation.ALIGNMENT_RIGHT.equals(hAlign)) {
			anchorX = parentSize.getWidth();
		}
		boolean modified = UpdateUtil.movePoint(anchorLocation, anchorX, anchorY);

		// Move the anchor to maintain position on top of parent
		if (UpdateUtil.moveAndResizeIfNeeded(portAnchor.getGraphicsAlgorithm(), -anchorX, -anchorY, parentSize.getWidth(), parentSize.getHeight())) {
			modified = true;
		}
		return modified;
	}

	/**
	 * Moves and resizes a {@link GraphicsAlgorithm} if it is not already at the requested location, or does not
	 * already match the requested size. Returns true if the GraphicsAlgorithm was moved or resized.
	 * @param ga target GraphicsAlgorithm
	 * @param x requested x position
	 * @param y requested y position
	 * @param y requested height
	 * @param width requested width
	 * @param height requested height
	 * @return true if ga was moved or resized, false otherwise
	 */
	public static boolean moveAndResizeIfNeeded(GraphicsAlgorithm ga, int x, int y, int width, int height) {
		if ((ga.getX() != x) || (ga.getY() != y) || (ga.getWidth() != width) || (ga.getHeight() != height)) {
			Graphiti.getGaLayoutService().setLocationAndSize(ga, x, y, width, height);
			return true;
		}
		return false;
	}

	/**
	 * Moves a {@link GraphicsAlgorithm} if it is not already at the requested location, returning whether the move
	 * occurred or not.
	 * @param ga target GraphicsAlgorithm
	 * @param x requested x position
	 * @param y requested y position
	 * @return true if ga was moved, false if it was already at the requested location
	 */
	public static boolean moveIfNeeded(GraphicsAlgorithm ga, int x, int y) {
		if ((ga.getX() != x) || (ga.getY() != y)) {
			Graphiti.getGaLayoutService().setLocation(ga, x, y);
			return true;
		}
		return false;
	}

	/**
	 * Repositions a {@link Point} to the given coordinates, if necessary.
	 * @param point the Point to move
	 * @param x requested x position
	 * @param y requested y position
	 * @return true if point was moved, false if it was already at the requested location
	 */
	public static boolean movePoint(Point point, int x, int y) {
		if ((point.getX() != x) || (point.getY() != y)) {
			point.setX(x);
			point.setY(y);
			return true;
		}
		return false;
	}

	/**
	 * Repositions a list of {@link Point}s to the given coordinates, if necessary.
	 * @param points list of points to move
	 * @param xy list of interleaved x/y coordinates, must be exactly twice the size of points
	 * @return true if any point was repositioned, false otherwise
	 * @throws IllegalArgumentException
	 */
	public static boolean movePoints(List<Point> points, int... xy) {
		if ((xy.length % 2) == 1) {
			throw new IllegalArgumentException("Coordinate list must have even size");
		} else if ((xy.length / 2) != points.size()) {
			throw new IllegalArgumentException("Coordinate list must match points size");
		}
		int offset = 0;
		boolean moved = false;
		for (Point point : points) {
			int pointX = xy[offset++];
			int pointY = xy[offset++];
			if (UpdateUtil.movePoint(point, pointX, pointY)) {
				moved = true;
			}
		}
		return moved;
	}

	/**
	 * Resizes a {@link GraphicsAlgorithm} if it does not already match the requested size, returning whether the
	 * resize occurred or not.
	 * @param ga target GraphicsAlgorithm
	 * @param width requested width
	 * @param height requested height
	 * @return true if ga was resized, false if it was already the requested size
	 */
	public static boolean resizeIfNeeded(GraphicsAlgorithm ga, int width, int height) {
		if ((ga.getWidth() != width) || (ga.getHeight() != height)) {
			Graphiti.getGaLayoutService().setSize(ga, width, height);
			return true;
		}
		return false;
	}

	/**
	 * Updates the value of a {@link Text}, if necessary.
	 * @param text the Text instance to update
	 * @param value the new String value to set
	 * @return true if text was updated, false otherwise
	 */
	public static boolean update(Text text, String value) {
		if (UpdateUtil.updateNeeded(text, value)) {
			text.setValue(value);
			return true;
		}
		return false;
	}

	/**
	 * Checks if a {@link Text} has the desired value.
	 * @param text the Text instance to check
	 * @param value the new String value to check
	 * @return true if text requires an update, false otherwise
	 */
	public static boolean updateNeeded(Text text, String value) {
		if (text != null) {
			return !text.getValue().equals(value);
		}
		return false;
	}
}

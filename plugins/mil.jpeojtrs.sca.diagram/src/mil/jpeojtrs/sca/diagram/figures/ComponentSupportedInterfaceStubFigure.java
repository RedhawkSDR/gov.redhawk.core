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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class ComponentSupportedInterfaceStubFigure extends Shape {

	private static final Color DEFAULT_FOREGROUND = ColorConstants.black;
	private static final Color DEFAULT_BACKGROUND = ColorConstants.black;

	public ComponentSupportedInterfaceStubFigure() {
		this.setLayoutManager(new XYLayout());
		this.addPoint(new Point(10, 1)); // SUPPRESS CHECKSTYLE MagicNumber
		this.addPoint(new Point(20, 1)); // SUPPRESS CHECKSTYLE MagicNumber
		this.setForegroundColor(ComponentSupportedInterfaceStubFigure.DEFAULT_FOREGROUND);
		this.setBackgroundColor(ComponentSupportedInterfaceStubFigure.DEFAULT_BACKGROUND);

		this.setPreferredSize(new Dimension(20, 10)); // SUPPRESS CHECKSTYLE MagicNumber
		createContents();
	}

	/**
	 */
	private void createContents() {

		final Ellipse elli0 = new Ellipse();
		elli0.setFill(false);
		elli0.setLocation(new Point(0, 0));
		elli0.setSize(10, 10); // SUPPRESS CHECKSTYLE MagicNumber

		this.add(elli0);

	}

	/**
	 */
	private final PointList myTemplate = new PointList();

	private Rectangle myTemplateBounds;

	/**
	 */
	public void addPoint(final Point point) {
		this.myTemplate.addPoint(point);
		this.myTemplateBounds = null;
	}

	/**
	 */
	@Override
	protected void fillShape(final Graphics graphics) {
		final Rectangle bounds = getBounds();
		graphics.pushState();
		graphics.translate(bounds.x, bounds.y);
		graphics.fillPolygon(scalePointList());
		graphics.popState();
	}

	@Override
	protected void outlineShape(final Graphics graphics) {
		final Rectangle bounds = getBounds();
		graphics.pushState();
		graphics.translate(bounds.x, bounds.y);
		graphics.drawPolygon(scalePointList());
		graphics.popState();
	}

	/**
	 */
	private Rectangle getTemplateBounds() {
		if (this.myTemplateBounds == null) {
			this.myTemplateBounds = this.myTemplate.getBounds().getCopy().union(0, 0);
			// just safety -- we are going to use this as divider
			if (this.myTemplateBounds.width < 1) {
				this.myTemplateBounds.width = 1;
			}
			if (this.myTemplateBounds.height < 1) {
				this.myTemplateBounds.height = 1;
			}
		}
		return this.myTemplateBounds;
	}

	/**
	 */
	private int[] scalePointList() {
		final Rectangle pointsBounds = getTemplateBounds();
		final Rectangle actualBounds = getBounds();

		final float xScale = ((float) actualBounds.width) / pointsBounds.width;
		final float yScale = ((float) actualBounds.height) / pointsBounds.height;

		if (xScale == 1 && yScale == 1) {
			return this.myTemplate.toIntArray();
		}
		final int[] scaled = this.myTemplate.toIntArray().clone();
		for (int i = 0; i < scaled.length; i += 2) {
			scaled[i] = (int) Math.floor(scaled[i] * xScale);
			scaled[i + 1] = (int) Math.floor(scaled[i + 1] * yScale);
		}
		return scaled;
	}

	public static Color getDefaultForegroundColor() {
		return ComponentSupportedInterfaceStubFigure.DEFAULT_FOREGROUND;
	}

	public static Color getDefaultBackgroundColor() {
		return ComponentSupportedInterfaceStubFigure.DEFAULT_BACKGROUND;
	}
}

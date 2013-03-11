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

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Display;

public class ComponentInstantiationFigure extends RoundedRectangle {

	/**
	 * @since 1.2
	 */
	public static final int DEFAULT_LINE_WIDTH = 2;
	/**
	 * @since 1.2
	 */
	public static final int ASSEMBLY_CONTROLLER_LINE_WIDTH = 3;
	/**
	 * @since 1.2
	 */
	public static final Color FOREGROUND_COLOR = new Color(null, 116, 130, 141); // TODO shouldn't we be disposing of these correctly?
	/**
	 * @since 1.2
	 */
	public static final Color COMPONENT_IDLE_COLOR = new Color(null, 219, 233, 246); // TODO shouldn't we be disposing of these correctly?
	/**
	 * @since 1.2
	 */
	public static final Color COMPONENT_STARTED_COLOR = new Color(null, 186, 234, 173); // TODO shouldn't we be disposing of these correctly?
	/**
	 * @since 1.2
	 */
	public static final Color DEFAULT_COMPONENT_COLOR = new Color(null, 176, 176, 176); // TODO shouldn't we be disposing of these correctly?
	/**
	 * @since 1.2
	 */
	public static final Color ASSEMBLY_CONTROLLER_COLOR = new Color(null, 255, 218, 105); // TODO shouldn't we be disposing of these correctly?
	/**
	 * @since 1.2
	 */
	public static final Font START_ORDER_FONT = new Font(null, "Arial", 12, SWT.BOLD); // TODO shouldn't we be disposing of these correctly?

	private Color gradientColor = ComponentInstantiationFigure.COMPONENT_IDLE_COLOR; // SUPPRESS CHECKSTYLE MagicNumber
	private WrappingLabel fComponentInstantiationLabelFigure;
	private boolean myUseLocalCoordinates = false;
	private int numPorts;
	private int adjustedWidth;

	public ComponentInstantiationFigure() {
		final BorderLayout layoutThis = new BorderLayout();
		this.setLayoutManager(layoutThis);

		this.setCornerDimensions(new Dimension(8, 8)); // SUPPRESS CHECKSTYLE MagicNumber
		this.setBorder(new MarginBorder(5, 5, 5, 5)); // SUPPRESS CHECKSTYLE MagicNumber
		this.lineWidth = 2;
		this.setForegroundColor(ComponentInstantiationFigure.FOREGROUND_COLOR);
		createContents();
	}

	private void createContents() {

		this.fComponentInstantiationLabelFigure = new WrappingLabel();
		this.fComponentInstantiationLabelFigure.setText("Component Instantiation");

		this.add(this.fComponentInstantiationLabelFigure, BorderLayout.TOP);
	}

	public WrappingLabel getFigureComponentInstantiationLabelFigure() {
		return this.fComponentInstantiationLabelFigure;
	}

	/**
	 * @since 1.2
	 */
	public int getAdjustedWidth() {
		return this.adjustedWidth;
	}

	/**
	 * @since 1.2
	 */
	public void setAdjustedWidth(final int adjustedWidth) {
		this.adjustedWidth = adjustedWidth;
	}

	@Override
	protected void fillShape(final Graphics graphics) {
		final Rectangle r = getBounds().getCopy();
		final Point topLeft = r.getTopLeft();
		final float x = (r.getTopRight().x - r.getTopLeft().x) / 2 + r.getTopLeft().x;

		super.fillShape(graphics);

		final int height = this.fComponentInstantiationLabelFigure.getBounds().height + 10; // SUPPRESS CHECKSTYLE MagicNumber
		final Pattern pattern = new Pattern(Display.getCurrent(), x, topLeft.y, x, topLeft.y + height, this.gradientColor, graphics.getBackgroundColor());
		final int oldFillRule = graphics.getFillRule();

		// We do this to support graphics that potentially don't support gradient fill,  for some reason the save as image doesn't support this
		try {
			graphics.setBackgroundPattern(pattern);
			r.setSize(r.width, height);
			graphics.setClip(r);
			graphics.fillRoundRectangle(getBounds(), this.corner.width, this.corner.height);
			graphics.setClip(getBounds());
			graphics.setBackgroundPattern(null);
			graphics.setFillRule(oldFillRule);
		} catch (final RuntimeException e) {
			// PASS
		} finally {
			pattern.dispose();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dimension getPreferredSize(final int hint, final int hint2) {
		return getAdjustedSize(super.getPreferredSize(hint, hint2));
	}

	private Dimension getAdjustedSize(final Dimension d) {
		final Dimension retVal = d;
		retVal.height = Math.max(retVal.height + 100, this.numPorts * 24 + 30); // SUPPRESS CHECKSTYLE MagicNumber
		retVal.width = Math.max(retVal.width + 100, this.adjustedWidth * 7 + 30); // SUPPRESS CHECKSTYLE MagicNumber
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dimension getMinimumSize(final int hint, final int hint2) {
		final Dimension retVal = super.getMinimumSize(hint, hint2);
		return getAdjustedSize(retVal);
	}

	public void setNumPorts(final int numPorts) {
		this.numPorts = numPorts;
	}

	public int getNumPorts() {
		return this.numPorts;
	}

	@Override
	protected boolean useLocalCoordinates() {
		return this.myUseLocalCoordinates;
	}

	protected void setUseLocalCoordinates(final boolean useLocalCoordinates) {
		this.myUseLocalCoordinates = useLocalCoordinates;
	}

	public WrappingLabel getFigureComponentPlacementLabelFigure() {
		return this.fComponentInstantiationLabelFigure;
	}

	/**
	 * @since 1.2
	 */
	public void setGradientColor(final Color color) {
		this.gradientColor = color;
	}

	/**
	 * @since 1.2
	 */
	public Color getGradientColor() {
		return this.gradientColor;
	}
}

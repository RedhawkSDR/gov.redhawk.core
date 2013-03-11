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
 * 
 * Based on the work of:
 * 
 * ******************************************************************************
 * Copyright (c) 2000, 2004  IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package gov.redhawk.diagram.layout;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * code copied from real logic example in gef
 * 
 * @since 2.0
 */
public class FixedConnectionAnchor extends AbstractConnectionAnchor {

	private final boolean leftToRight = true;
	private int offsetH;
	private int offsetV;
	private final boolean topDown = true;

	public FixedConnectionAnchor(final IFigure owner) {
		super(owner);
	}

	/**
	 * @see org.eclipse.draw2d.AbstractConnectionAnchor#ancestorMoved(IFigure)
	 */
	@Override
	public void ancestorMoved(final IFigure figure) {
		if (figure instanceof ScalableFigure) {
			return;
		}
		super.ancestorMoved(figure);
	}

	public Point getLocation(final Point reference) {
		final Rectangle r = getOwner().getBounds();
		int x, y;
		if (this.topDown) {
			y = r.y + this.offsetV;
		} else {
			y = r.bottom() - 1 - this.offsetV;
		}

		if (this.leftToRight) {
			x = r.x + this.offsetH;
		} else {
			x = r.right() - 1 - this.offsetH;
		}

		final Point p = new PrecisionPoint(x, y);
		getOwner().translateToAbsolute(p);
		return p;
	}

	@Override
	public Point getReferencePoint() {
		return getLocation(null);
	}

	/**
	 * @param offsetH The offsetH to set.
	 */
	public void setOffsetH(final int offsetH) {
		this.offsetH = offsetH;
		fireAnchorMoved();
	}

	/**
	 * @param offsetV The offsetV to set.
	 */
	public void setOffsetV(final int offsetV) {
		this.offsetV = offsetV;
		fireAnchorMoved();
	}

}

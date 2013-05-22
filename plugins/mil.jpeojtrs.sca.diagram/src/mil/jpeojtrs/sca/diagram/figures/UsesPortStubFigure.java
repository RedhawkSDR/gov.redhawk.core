/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package mil.jpeojtrs.sca.diagram.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Color;

public class UsesPortStubFigure extends RectangleFigure {
	private static final Color DEFAULT_FOREGROUND = ColorConstants.black;
	private static final Color DEFAULT_BACKGROUND = ColorConstants.black;
	private static final int DEFAULT_WIDTH = 15;
	private static final int DEFAULT_HEIGHT = 15;

	public UsesPortStubFigure() {
		this.setBackgroundColor(UsesPortStubFigure.DEFAULT_BACKGROUND);
		this.setPreferredSize(new Dimension(UsesPortStubFigure.DEFAULT_WIDTH, UsesPortStubFigure.DEFAULT_HEIGHT));
	}

	public static Color getDefaultBackgroundColor() {
		return UsesPortStubFigure.DEFAULT_BACKGROUND;
	}

	public static Color getDefaultForegroundColor() {
		return UsesPortStubFigure.DEFAULT_FOREGROUND;
	}
}

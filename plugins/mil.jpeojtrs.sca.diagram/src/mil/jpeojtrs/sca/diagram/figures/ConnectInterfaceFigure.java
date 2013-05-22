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

import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.swt.graphics.Color;

public class ConnectInterfaceFigure extends PolylineConnectionEx {

	public ConnectInterfaceFigure() {
		this.setForegroundColor(ConnectInterfaceFigure.SELECTED_FORE);
	
		setTargetDecoration(createTargetDecoration());
	}

	private RotatableDecoration createTargetDecoration() {
		final PolygonDecoration df = new PolygonDecoration();
		df.setFill(true);
		final PointList pl = new PointList();
		pl.addPoint(0, 0);
		pl.addPoint(-2, 2); // SUPPRESS CHECKSTYLE MagicNumber
		pl.addPoint(-2, -2); // SUPPRESS CHECKSTYLE MagicNumber
		pl.addPoint(0, 0);
		df.setTemplate(pl);
		df.setScale(7, 3); // SUPPRESS CHECKSTYLE MagicNumber
		return df;
	}

	public static final Color DESELECTED_FORE = new Color(null, 125, 125, 125);

	public static final Color SELECTED_FORE = new Color(null, 0, 0, 0);
}

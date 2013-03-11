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
package gov.redhawk.diagram.edit.parts;

import mil.jpeojtrs.sca.diagram.figures.ConnectInterfaceFigure;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;

/**
 * @since 3.0
 */
public class ConnectInterfaceEditPartHelper {

	private final IConnectInterfaceEditPartHelper editPart;

	private Color originalColor = null;

	private ConnectInterfaceFigure figure = null;

	public ConnectInterfaceEditPartHelper(final IConnectInterfaceEditPartHelper editPart) {
		this.editPart = editPart;
		this.figure = (ConnectInterfaceFigure) this.editPart.getFigure();
	}

	public void setSelected(final int selected) {
		this.editPart.basicSetSelected(selected);

		if (selected == EditPart.SELECTED_PRIMARY && this.originalColor == null) {
			this.originalColor = this.figure.getForegroundColor();
			this.figure.setLineWidth(2);
			this.figure.setForegroundColor(PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_LIST_SELECTION));
			final IFigure layer = this.editPart.getLayer(LayerConstants.CONNECTION_LAYER);
			final ConnectionLayer connectionLayer = (ConnectionLayer) layer;
			connectionLayer.getChildren().remove(this.editPart.getFigure());
			connectionLayer.getChildren().add(this.editPart.getFigure());
		} else if (selected == EditPart.SELECTED_NONE && this.originalColor != null) {
			this.figure.setLineWidth(1);
			this.figure.setForegroundColor(this.originalColor);
			this.originalColor = null;
		}

	}

}

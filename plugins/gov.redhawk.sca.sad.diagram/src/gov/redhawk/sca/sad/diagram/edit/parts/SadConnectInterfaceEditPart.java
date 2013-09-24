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
package gov.redhawk.sca.sad.diagram.edit.parts;

import gov.redhawk.diagram.edit.parts.ConnectInterfaceEditPartHelper;
import gov.redhawk.diagram.edit.parts.IConnectInterfaceEditPartHelper;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.notation.View;

public class SadConnectInterfaceEditPart extends mil.jpeojtrs.sca.sad.diagram.edit.parts.SadConnectInterfaceEditPart implements IConnectInterfaceEditPartHelper {

	private final ConnectInterfaceEditPartHelper editPartHelper = new ConnectInterfaceEditPartHelper(this);

	public SadConnectInterfaceEditPart(final View view) {
		super(view);
	}

	@Override
	public void setSelected(final int value) {
		this.editPartHelper.setSelected(value);
	}

	@Override
	public IFigure getLayer(final Object layer) {
		return super.getLayer(layer);
	}

	@Override
	public void basicSetSelected(final int selected) {
		super.setSelected(selected);
	}

}

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

import org.eclipse.draw2d.IFigure;

/**
 * @since 3.0
 * 
 */
public class ComponentPlacementCompartmentEditPartHelper {

	private final IComponentPlacementCompartmentEditPart editPart;

	public ComponentPlacementCompartmentEditPartHelper(final IComponentPlacementCompartmentEditPart editPart) {
		this.editPart = editPart;
	}

	public IFigure createFigure() {
		final IFigure retVal = this.editPart.basicCreateFigure();
		retVal.setBorder(null);
		retVal.setToolTip(null);
		return retVal;
	}

}

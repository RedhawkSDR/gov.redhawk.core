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
package gov.redhawk.sca.dcd.diagram.edit.parts;

import gov.redhawk.diagram.edit.parts.ComponentPlacementCompartmentEditPartHelper;
import gov.redhawk.diagram.edit.parts.IComponentPlacementCompartmentEditPart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 */
public class DcdComponentPlacementCompartmentEditPart extends mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementCompartmentEditPart implements
        IComponentPlacementCompartmentEditPart {

	private final ComponentPlacementCompartmentEditPartHelper editPartHelper = new ComponentPlacementCompartmentEditPartHelper(this);

	public DcdComponentPlacementCompartmentEditPart(final View view) {
		super(view);
	}

	@Override
	public IFigure createFigure() {
		return this.editPartHelper.createFigure();
	}

	/**
	 * {@inheritDoc}
	 */
	public IFigure basicCreateFigure() {
		return super.createFigure();
	}

}

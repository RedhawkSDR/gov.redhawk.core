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

import gov.redhawk.diagram.layout.FixedConnectionAnchor;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;

/**
 * @since 3.0
 */
public class ComponentSupportedInterfaceStubEditPartHelper {

	private FixedConnectionAnchor fixedAnchor;
	private final IComponentSupportedInterfaceStubEditPart editPart;

	public ComponentSupportedInterfaceStubEditPartHelper(final IComponentSupportedInterfaceStubEditPart editPart) {
		this.editPart = editPart;
	}

	public IFigure createNodeShape() {
		final IFigure primaryShape = this.editPart.basicCreateNodeShape();

		// Setup Anchor
		this.fixedAnchor = new FixedConnectionAnchor(primaryShape);
		this.fixedAnchor.setOffsetH(0);
		if (primaryShape.getPreferredSize() != null) {
			this.fixedAnchor.setOffsetV(primaryShape.getPreferredSize().height / 2);
		}

		this.editPart.setPrimaryShape(primaryShape);
		return primaryShape;
	}

	public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connEditPart) {
		return this.fixedAnchor;
	}

	public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
		return this.fixedAnchor;
	}

}

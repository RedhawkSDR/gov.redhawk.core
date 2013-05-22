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

import gov.redhawk.diagram.edit.parts.ComponentSupportedInterfaceStubEditPartHelper;
import gov.redhawk.diagram.edit.parts.IComponentSupportedInterfaceStubEditPart;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.notation.View;

public class ComponentSupportedInterfaceStubEditPart extends mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentSupportedInterfaceStubEditPart implements
        IComponentSupportedInterfaceStubEditPart {

	private final ComponentSupportedInterfaceStubEditPartHelper editPartHelper = new ComponentSupportedInterfaceStubEditPartHelper(this);

	public ComponentSupportedInterfaceStubEditPart(final View view) {
		super(view);
	}

	@Override
	protected IFigure createNodeShape() {
		return this.editPartHelper.createNodeShape();
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connEditPart) {
		return this.editPartHelper.getTargetConnectionAnchor(connEditPart);
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
		return this.editPartHelper.getTargetConnectionAnchor(request);
	}

	public IFigure basicCreateNodeShape() {
		return super.createNodeShape();
	}

	public void setPrimaryShape(final IFigure primaryShape) {
		this.primaryShape = primaryShape;
	}
}

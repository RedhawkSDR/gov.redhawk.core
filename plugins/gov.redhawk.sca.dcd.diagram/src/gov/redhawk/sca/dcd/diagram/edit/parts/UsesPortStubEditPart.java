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

import gov.redhawk.diagram.edit.parts.IUsesPortStubEditPart;
import gov.redhawk.diagram.edit.parts.UsesPortStubEditPartHelper;
import gov.redhawk.sca.dcd.diagram.part.RedhawkDcdVisualIDRegistry;
import gov.redhawk.sca.dcd.diagram.providers.RedhawkDcdElementTypes;

import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 */
public class UsesPortStubEditPart extends mil.jpeojtrs.sca.dcd.diagram.edit.parts.UsesPortStubEditPart implements IUsesPortStubEditPart {

	private final UsesPortStubEditPartHelper editPartHelper = new UsesPortStubEditPartHelper(this,
	        RedhawkDcdElementTypes.INSTANCE,
	        RedhawkDcdVisualIDRegistry.INSTANCE);

	public UsesPortStubEditPart(final View view) {
		super(view);
	}

	@Override
	public IFigure createNodeShape() {
		return this.editPartHelper.createNodeShape();
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(final ConnectionEditPart connEditPart) {
		return this.editPartHelper.getSourceConnectionAnchor(connEditPart);
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(final Request request) {
		return this.editPartHelper.getSourceConnectionAnchor(request);
	}

	@Override
	public void addBorderItem(final IFigure borderItemContainer, final IBorderItemEditPart borderItemEditPart) {
		this.editPartHelper.addBorderItem(borderItemContainer, borderItemEditPart);
	}

	@Override
	public EditPolicy getPrimaryDragEditPolicy() {
		return this.editPartHelper.getPrimaryDragEditPolicy();
	}

	@Override
	public EditPart getPrimaryChildEditPart() {
		return this.editPartHelper.getPrimaryChildEditPart();
	}

	@Override
	public DragTracker getDragTracker(final Request request) {
		return this.editPartHelper.getDragTracker(request);
	}

	@Override
	public List<IElementType> getMARelTypesOnSourceAndTarget(final IGraphicalEditPart targetEditPart) {
		return this.editPartHelper.getMARelTypesOnSourceAndTarget(targetEditPart);
	}

	@Override
	public List<IElementType> getMATypesForTarget(final IElementType relationshipType) {
		return this.editPartHelper.getMATypesForTarget(relationshipType);
	}

	/**
	 * {@inheritDoc}
	 */
	public IFigure basicCreateNodeShape() {
		return super.createNodeShape();
	}

	/**
	 * {@inheritDoc}
	 */
	public void basicAddBorderItem(final IFigure borderItemContainer, final IBorderItemEditPart borderItemEditPart) {
		super.addBorderItem(borderItemContainer, borderItemEditPart);
	}

	@Override
	public void setVisibility(final boolean vis) {
		super.setVisibility(vis);
	}

}

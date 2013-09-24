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

import gov.redhawk.diagram.edit.parts.IProvidesPortStubEditPart;
import gov.redhawk.diagram.edit.parts.ProvidesPortStubEditPartHelper;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 */
public class ProvidesPortStubEditPart extends mil.jpeojtrs.sca.dcd.diagram.edit.parts.ProvidesPortStubEditPart implements IProvidesPortStubEditPart {

	private final ProvidesPortStubEditPartHelper editPartHelper = new ProvidesPortStubEditPartHelper(this);

	public ProvidesPortStubEditPart(final View view) {
		super(view);
	}

	@Override
	public EditPolicy getPrimaryDragEditPolicy() {
		return this.editPartHelper.getPrimaryDragEditPolicy();
	}

	@Override
	public IFigure createNodeShape() {
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

	@Override
	public void addBorderItem(final IFigure borderItemContainer, final IBorderItemEditPart borderItemEditPart) {
		this.editPartHelper.addBorderItem(borderItemContainer, borderItemEditPart);
	}

	@Override
	public DragTracker getDragTracker(final Request request) {
		return this.editPartHelper.getDragTracker(request);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFigure basicCreateNodeShape() {
		return super.createNodeShape();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isInstanceofProvidesPortStubNameEditPart(final IBorderItemEditPart borderItemEditPart) {
		return borderItemEditPart instanceof ProvidesPortStubNameEditPart;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void basicAddBorderItem(final IFigure borderItemContainer, final IBorderItemEditPart borderItemEditPart) {
		super.addBorderItem(borderItemContainer, borderItemEditPart);
	}

	@Override
	public void setVisibility(final boolean vis) {
		super.setVisibility(vis);
	}
}

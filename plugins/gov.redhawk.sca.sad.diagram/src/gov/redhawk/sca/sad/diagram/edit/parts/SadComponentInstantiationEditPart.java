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

import gov.redhawk.diagram.edit.parts.ComponentInstantiationEditPartHelper;
import gov.redhawk.diagram.edit.parts.IComponentInstantiationEditPart;
import gov.redhawk.sca.sad.diagram.RedhawkSadDiagramPlugin;
import mil.jpeojtrs.sca.diagram.figures.ComponentInstantiationFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * 
 */
public class SadComponentInstantiationEditPart extends mil.jpeojtrs.sca.sad.diagram.edit.parts.SadComponentInstantiationEditPart implements
		IComponentInstantiationEditPart {
	private final ComponentInstantiationEditPartHelper editPartHelper = new ComponentInstantiationEditPartHelper(this);

	// Look at ListCompartmentEditPart for filtering details
	public SadComponentInstantiationEditPart(final View view) {
		super(view);
	}

	@Override
	protected NodeFigure createNodePlate() {
		return this.editPartHelper.createNodePlate();
	}

	@Override
	protected boolean addFixedChild(final EditPart childEditPart) {
		return this.editPartHelper.addFixedChild(childEditPart);
	}

	@Override
	protected IFigure createNodeShape() {
		return this.editPartHelper.createNodeShape();
	}

	/**
	 * @since 3.2
	 */
	public void addRuntimeListeners() {
		this.editPartHelper.addRuntimeListeners();
	}

	/**
	 * @since 3.2
	 */
	public void removeRuntimeListeners() {
		this.editPartHelper.removeRuntimeListeners();
	}

	/**
	 * @since 3.0
	 */
	@Override
	protected void addNotationalListeners() {
		this.editPartHelper.addNotationalListeners();
	}

	@Override
	protected void removeNotationalListeners() {
		this.editPartHelper.removeNotationalListeners();
	}

	@Override
	public DragTracker getDragTracker(final Request request) {
		return this.editPartHelper.getDragTracker(request);
	}

	@Override
	public NodeFigure basicCreateNodePlate() {
		return super.createNodePlate();
	}

	@Override
	public boolean basicAddFixedChild(final EditPart childEditPart) {
		return super.addFixedChild(childEditPart);
	}

	@Override
	public void setPrimaryShape(final ComponentInstantiationFigure retVal) {
		this.primaryShape = retVal;
	}

	@Override
	public IPreferenceStore getPreferenceStore() {
		return RedhawkSadDiagramPlugin.getDefault().getPreferenceStore();
	}

	@Override
	public void basicAddNotationalListeners() {
		super.addNotationalListeners();
	}

	@Override
	public void basicRemoveNotationalListeners() {
		super.removeNotationalListeners();
	}

	@Override
	public DragTracker basicGetDragTracker(final Request request) {
		return super.getDragTracker(request);
	}
}

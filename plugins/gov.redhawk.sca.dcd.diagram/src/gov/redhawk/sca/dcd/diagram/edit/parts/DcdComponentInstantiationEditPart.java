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

import gov.redhawk.diagram.edit.parts.ComponentInstantiationEditPartHelper;
import gov.redhawk.diagram.edit.parts.IComponentInstantiationEditPart;
import gov.redhawk.sca.dcd.diagram.DcdDiagramPluginActivator;
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
public class DcdComponentInstantiationEditPart extends mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentInstantiationEditPart implements
        IComponentInstantiationEditPart {

	private final ComponentInstantiationEditPartHelper editPartHelper = new ComponentInstantiationEditPartHelper(this);

	public DcdComponentInstantiationEditPart(final View view) {
		super(view);
	}

	@Override
	public NodeFigure createNodePlate() {
		return this.editPartHelper.createNodePlate();
	}

	@Override
	public boolean addFixedChild(final EditPart childEditPart) {
		return this.editPartHelper.addFixedChild(childEditPart);
	}

	@Override
	public IFigure createNodeShape() {
		return this.editPartHelper.createNodeShape();
	}

	@Override
	public void addNotationalListeners() {
		this.editPartHelper.addNotationalListeners();
	}

	@Override
	public void removeNotationalListeners() {
		this.editPartHelper.removeNotationalListeners();
	}

	@Override
	public DragTracker getDragTracker(final Request request) {
		return this.editPartHelper.getDragTracker(request);
	}

	/**
	 * {@inheritDoc}
	 */
	public NodeFigure basicCreateNodePlate() {
		return super.createNodePlate();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean basicAddFixedChild(final EditPart childEditPart) {
		return super.addFixedChild(childEditPart);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPrimaryShape(final ComponentInstantiationFigure retVal) {
		this.primaryShape = retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	public IPreferenceStore getPreferenceStore() {
		return DcdDiagramPluginActivator.getDefault().getPreferenceStore();
	}

	/**
	 * {@inheritDoc}
	 */
	public void basicAddNotationalListeners() {
		super.addNotationalListeners();
	}

	/**
	 * {@inheritDoc}
	 */
	public void basicRemoveNotationalListeners() {
		super.removeNotationalListeners();
	}

	/**
	 * {@inheritDoc}
	 */
	public DragTracker basicGetDragTracker(final Request request) {
		return super.getDragTracker(request);
	}

}

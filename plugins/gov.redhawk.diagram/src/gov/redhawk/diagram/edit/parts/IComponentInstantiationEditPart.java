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

import mil.jpeojtrs.sca.diagram.figures.ComponentInstantiationFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @since 3.0
 */
public interface IComponentInstantiationEditPart extends IGraphicalEditPart {

	NodeFigure basicCreateNodePlate();

	BorderedNodeFigure getBorderedFigure();

	boolean basicAddFixedChild(EditPart childEditPart);

	void setPrimaryShape(ComponentInstantiationFigure retVal);

	IPreferenceStore getPreferenceStore();

	void basicAddNotationalListeners();

	void basicRemoveNotationalListeners();

	DragTracker basicGetDragTracker(Request request);

	IFigure getPrimaryShape();

}

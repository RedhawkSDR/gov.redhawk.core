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
package gov.redhawk.sca.sad.diagram.palette;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.ui.palette.PaletteViewer;


/**
 * @since 3.2
 */
public class LabelEditPart extends org.eclipse.gef.editparts.AbstractGraphicalEditPart {

	private final PaletteFilter filter;

	public LabelEditPart(PaletteViewer viewer) {
		filter = new PaletteFilter(viewer, this);
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, null);
	}

	/**
	 * @return the filter
	 */
	public PaletteFilter getPaletteFilter() {
		return filter;
	}

	protected IFigure createFigure() {
		LabelFigure label = new LabelFigure();
		return label;
	}

	private void performDirectEdit() {
		new LabelEditManager(this, new LabelCellEditorLocator((LabelFigure) getFigure()), filter).show();
	}

	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT) {
			performDirectEdit();
		}
	}

	protected void refreshVisuals() {
		((LabelFigure) getFigure()).setText(filter.getFilter());
		super.refreshVisuals();
	}

}

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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeConnectionRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 */
public class HostCollocationCompartmentEditPart extends mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationCompartmentEditPart {

	public HostCollocationCompartmentEditPart(final View view) {
		super(view);
	}

	@Override
	public IFigure createFigure() {
		final IFigure figure = super.createFigure();
		figure.setToolTip(null);
		return figure;
	}

	@Override
	public Command getCommand(final Request request) {
		if (request instanceof ChangeBoundsRequest) {
			final ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
			for (final Object part : changeBoundsRequest.getEditParts()) {
				if (part instanceof IGraphicalEditPart) {
					final Rectangle bounds = changeBoundsRequest.getTransformedRectangle(((IGraphicalEditPart) part).getFigure().getBounds());
					if (this.children != null) {
						for (final Object obj : this.children) {
							if (part == obj) {
								continue;
							} else if (obj instanceof IGraphicalEditPart && ((IGraphicalEditPart) obj).getFigure().getBounds().intersects(bounds)) {
								return UnexecutableCommand.INSTANCE;
							}
						}	
					}
				}
			}
		}
		if (request instanceof CreateUnspecifiedTypeConnectionRequest) {
			// Do not allow creating connections to nowhere
			// By default this would allow creating an item.  However, we don't have any items types that could be created, therefore we disable this feature
			return UnexecutableCommand.INSTANCE;
		}
		return super.getCommand(request);
	}
}

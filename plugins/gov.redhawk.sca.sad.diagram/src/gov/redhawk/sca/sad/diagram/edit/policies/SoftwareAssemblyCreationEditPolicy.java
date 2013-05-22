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
package gov.redhawk.sca.sad.diagram.edit.policies;

import java.util.List;

import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementEditPart;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;

public class SoftwareAssemblyCreationEditPolicy extends CreationEditPolicy {

	/**
	 * Returns editing domain from the host edit part.
	 * 
	 * @generated
	 */
	protected TransactionalEditingDomain getEditingDomain() {
		return ((IGraphicalEditPart) getHost()).getEditingDomain();
	}

	@Override
	protected Command getCreateElementAndViewCommand(final CreateViewAndElementRequest request) {
		final List< ? extends ViewDescriptor> viewAndElementDesc = request.getViewDescriptors();
		if (request.getSize() != null) {
			for (final ViewDescriptor desc : viewAndElementDesc) {
				if (desc.getSemanticHint().equals(String.valueOf(mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationEditPart.VISUAL_ID))) {
					final Rectangle target = new Rectangle(request.getLocation(), request.getSize());

					for (final Object obj : getHost().getChildren()) {
						if (obj instanceof IGraphicalEditPart) {
							final IGraphicalEditPart childPart = (IGraphicalEditPart) obj;
							if (!(childPart instanceof ComponentPlacementEditPart)
							        && (target.intersects(childPart.getFigure().getBounds()) || target.contains(childPart.getFigure().getBounds()))) {
								return UnexecutableCommand.INSTANCE;
							}
						}
					}
				}
			}
		}

		return super.getCreateElementAndViewCommand(request);
	}
}

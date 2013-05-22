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
* Identification: $Revision: 6584 $
*/
package gov.redhawk.sca.sad.diagram.edit.policies;

import java.util.Collections;
import java.util.Iterator;

import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationEditPart;
import mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.AddCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.MoveElementsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

public class HostCollocationItemSemanticEditPolicy extends mil.jpeojtrs.sca.sad.diagram.edit.policies.HostCollocationItemSemanticEditPolicy {

	@Override
	protected Command getDestroyElementCommand(final DestroyElementRequest req) {
		final CompoundCommand cc = getDestroyEdgesCommand();
		addDestroyChildNodesCommand(cc);
		addDestroyShortcutsCommand(cc);
		final View view = (View) getHost().getModel();
		if (view.getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
			req.setElementToDestroy(view);
		}
		cc.add(getGEFWrapper(new DestroyElementCommand(req)));
		return cc.unwrap();
	}

	protected void addDestroyChildNodesCommand(final CompoundCommand cmd) {
		final View view = (View) getHost().getModel();
		final HostCollocationEditPart editPart = (HostCollocationEditPart) getHost();

		final EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation != null) {
			return;
		}
		for (final Iterator< ? > it = view.getChildren().iterator(); it.hasNext();) {
			final Node node = (Node) it.next();
			switch (SadVisualIDRegistry.getVisualID(node)) {
			case mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationCompartmentEditPart.VISUAL_ID:
				for (final Iterator< ? > cit = node.getChildren().iterator(); cit.hasNext();) {
					final Node cnode = (Node) cit.next();
					switch (SadVisualIDRegistry.getVisualID(cnode)) {
					case mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementEditPart.VISUAL_ID:
						final MoveRequest request = new MoveRequest(getEditingDomain(), cnode.getElement().eContainer().eContainer(), cnode.getElement());
						final MoveElementsCommand command = new MoveElementsCommand(request);
						cmd.add(getGEFWrapper(command));
						final AddCommand addCommand = new AddCommand(getEditingDomain(), new EObjectAdapter(view.eContainer()), new EObjectAdapter(cnode));
						cmd.add(getGEFWrapper(addCommand));
						final Point p = new Point();
						p.x = Math.abs(editPart.getFigure().getBounds().x + ((Location) cnode.getLayoutConstraint()).getX());
						p.y = Math.abs(editPart.getFigure().getBounds().y + ((Location) cnode.getLayoutConstraint()).getY());
						final SetBoundsCommand setBoundsCommand = new SetBoundsCommand(getEditingDomain(), cmd.getLabel(), new EObjectAdapter(cnode), p);
						cmd.add(getGEFWrapper(setBoundsCommand));
						break;
					default:
						break;
					}
				}
				break;
			default:
				break;
			}
		}
	}

	protected CompoundCommand getDestroyEdgesCommand() {
		final CompoundCommand cmd = new CompoundCommand();
		final View view = (View) getHost().getModel();
		for (final Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
			cmd.add(getDestroyElementCommand((Edge) it.next()));
		}
		for (final Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
			cmd.add(getDestroyElementCommand((Edge) it.next()));
		}
		return cmd;
	}

	protected void addDestroyShortcutsCommand(final CompoundCommand command) {
		final View view = (View) getHost().getModel();
		if (view.getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
			return;
		}
		if (view.getDiagram() != null) {
			for (final Iterator<?> it = view.getDiagram().getChildren().iterator(); it.hasNext();) {
				final View nextView = (View) it.next();
				if (nextView.getEAnnotation("Shortcut") == null || !nextView.isSetElement() || nextView.getElement() != view.getElement()) { //$NON-NLS-1$
					continue;
				}
				command.add(getDestroyElementCommand(nextView));
			}
		}
	}

	protected Command getDestroyElementCommand(final View view) {
		final EditPart editPart = (EditPart) getHost().getViewer().getEditPartRegistry().get(view);
		final DestroyElementRequest request = new DestroyElementRequest(getEditingDomain(), false);
		return editPart.getCommand(new EditCommandRequestWrapper(request, Collections.EMPTY_MAP));
	}
}

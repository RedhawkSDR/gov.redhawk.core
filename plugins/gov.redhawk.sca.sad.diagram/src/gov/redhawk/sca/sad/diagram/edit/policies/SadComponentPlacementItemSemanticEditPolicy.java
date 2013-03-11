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
* Identification: $Revision: 6584 $
*/
package gov.redhawk.sca.sad.diagram.edit.policies;

import gov.redhawk.sca.sad.diagram.edit.parts.SadComponentPlacementCompartmentEditPart;

import java.util.Iterator;

import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementCompartmentEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SadComponentInstantiationEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.policies.ComponentPlacementItemSemanticEditPolicy;
import mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @since 1.1
 */
public class SadComponentPlacementItemSemanticEditPolicy extends ComponentPlacementItemSemanticEditPolicy {

	@Override
	protected Command getDestroyElementCommand(final DestroyElementRequest req) {
		final View view = (View) getHost().getModel();
		final CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		Command tmpCommand = null;
		cmd.setTransactionNestingEnabled(false);
		final EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			tmpCommand = addDestroyChildNodesCommand(cmd);
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}

		if (tmpCommand != null) {
			return getGEFWrapper(cmd.reduce()).chain(tmpCommand);
		} else {
			return getGEFWrapper(cmd.reduce());
		}
	}

	private Command addDestroyChildNodesCommand(final ICompositeCommand cmd) {
		final View view = (View) getHost().getModel();
		for (final Iterator< ? > nit = view.getChildren().iterator(); nit.hasNext();) {
			final Node node = (Node) nit.next();
			switch (SadVisualIDRegistry.getVisualID(node)) {
			case ComponentPlacementCompartmentEditPart.VISUAL_ID:
				for (final Iterator< ? > cit = node.getChildren().iterator(); cit.hasNext();) {
					final Node cnode = (Node) cit.next();
					switch (SadVisualIDRegistry.getVisualID(cnode)) {
					case SadComponentInstantiationEditPart.VISUAL_ID:
						for (final Object obj1 : getHost().getChildren()) {
							if (obj1 instanceof SadComponentPlacementCompartmentEditPart) {
								final SadComponentPlacementCompartmentEditPart p1 = (SadComponentPlacementCompartmentEditPart) obj1;

								for (final Object obj2 : p1.getChildren()) {
									if (obj2 instanceof SadComponentInstantiationEditPart) {
										final SadComponentInstantiationEditPart p2 = (SadComponentInstantiationEditPart) obj2;

										final ComponentInstantiationItemSemanticEditPolicy policy = (ComponentInstantiationItemSemanticEditPolicy) p2
										        .getEditPolicy(EditPolicyRoles.SEMANTIC_ROLE);

										return policy.getDestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false));

									}
								}
							}
						}
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

		return null;
	}

}

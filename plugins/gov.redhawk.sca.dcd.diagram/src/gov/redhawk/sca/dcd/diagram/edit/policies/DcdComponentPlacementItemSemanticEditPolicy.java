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

package gov.redhawk.sca.dcd.diagram.edit.policies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mil.jpeojtrs.sca.dcd.DcdComponentPlacement;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentInstantiationEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementCompartmentEditPart;
import mil.jpeojtrs.sca.dcd.diagram.part.DcdVisualIDRegistry;
import mil.jpeojtrs.sca.partitioning.Partitioning;

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
public class DcdComponentPlacementItemSemanticEditPolicy extends mil.jpeojtrs.sca.dcd.diagram.edit.policies.DcdComponentPlacementItemSemanticEditPolicy {

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
			addDestroyAggChildrenCommand(cmd, view);
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
			switch (DcdVisualIDRegistry.getVisualID(node)) {
			case DcdComponentPlacementCompartmentEditPart.VISUAL_ID:
				for (final Iterator< ? > cit = node.getChildren().iterator(); cit.hasNext();) {
					final Node cnode = (Node) cit.next();
					switch (DcdVisualIDRegistry.getVisualID(cnode)) {
					case DcdComponentInstantiationEditPart.VISUAL_ID:
						for (final Object obj1 : getHost().getChildren()) {
							if (obj1 instanceof DcdComponentPlacementCompartmentEditPart) {
								final DcdComponentPlacementCompartmentEditPart p1 = (DcdComponentPlacementCompartmentEditPart) obj1;

								for (final Object obj2 : p1.getChildren()) {
									if (obj2 instanceof DcdComponentInstantiationEditPart) {
										final DcdComponentInstantiationEditPart p2 = (DcdComponentInstantiationEditPart) obj2;

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

	private void addDestroyAggChildrenCommand(final CompositeTransactionalCommand cmd, final View view) {
		final List<DcdComponentPlacement> children = getChildComponents((DcdComponentPlacement) view.getElement(), (Partitioning< ? >) view.getElement()
		        .eContainer());

		for (final DcdComponentPlacement comp : children) {
			cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), comp, false)));
		}
	}

	private List<DcdComponentPlacement> getChildComponents(final DcdComponentPlacement comp, final Partitioning< ? > part) {
		final List<DcdComponentPlacement> children = new ArrayList<DcdComponentPlacement>();

		for (final Object obj : part.getComponentPlacement()) {
			final DcdComponentPlacement cp = (DcdComponentPlacement) obj;

			if (cp.getCompositePartOfDevice() != null && cp.getCompositePartOfDevice().getRefID().equals(comp.getComponentInstantiation().get(0).getId())) {
				children.add(cp);
			}
		}

		return children;
	}
}

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
* Identification: $Revision: 6465 $
*/

package gov.redhawk.diagram.edit.policies;

import gov.redhawk.diagram.edit.commands.FindByStubComponentSupportedInterfaceStubCreateCommand;
import gov.redhawk.diagram.edit.commands.FindByStubProvidesPortStubCreateCommand;
import gov.redhawk.diagram.edit.commands.FindByStubUsesPortStubCreateCommand;
import gov.redhawk.diagram.edit.parts.DomainFinderEditPart;
import gov.redhawk.diagram.edit.parts.FindByStubCompartmentEditPart;
import gov.redhawk.diagram.edit.parts.NamingServiceEditPart;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry.MAPPING_ID;
import gov.redhawk.diagram.providers.PartitioningElementTypes;

import java.util.Iterator;

import mil.jpeojtrs.sca.partitioning.FindByStub;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @since 3.0
 * 
 */
public abstract class FindByStubItemSemanticEditPolicy extends PartitioningBaseItemSemanticEditPolicy {

	/**
	* 
	*/
	public FindByStubItemSemanticEditPolicy(final PartitioningElementTypes elementTypes, final PartitioningVisualIDRegistry visualIdRegistry) {
		super(PartitioningElementTypes.FindByStub, elementTypes, visualIdRegistry);
	}

	/**
	* 
	*/
	@Override
	protected Command getCreateCommand(final CreateElementRequest req) {
		if (this.elementTypes.getProvidesPortStubElementType() == req.getElementType()) {
			return getGEFWrapper(new FindByStubProvidesPortStubCreateCommand(req));
		}
		if (this.elementTypes.getUsesPortStubElementType() == req.getElementType()) {
			return getGEFWrapper(new FindByStubUsesPortStubCreateCommand(req));
		}
		if (this.elementTypes.getComponentSupportedInterfaceStubElementType() == req.getElementType()) {
			final FindByStub stub = (FindByStub) ((Shape) getHost().getModel()).getElement();

			if (stub.getInterface() == null) {
				return getGEFWrapper(new FindByStubComponentSupportedInterfaceStubCreateCommand(req));
			}
			return null;
		}
		return super.getCreateCommand(req);
	}

	/**
	* 
	*/
	@Override
	protected Command getDestroyElementCommand(final DestroyElementRequest req) {
		final View view = (View) getHost().getModel();
		final CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (final Iterator< ? > it = view.getTargetEdges().iterator(); it.hasNext();) {
			final Edge incomingLink = (Edge) it.next();
			if (this.visualIdRegistry.getMappingID(incomingLink) == MAPPING_ID.ConnectInterfaceEditPart) {
				final DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		final EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyChildNodesCommand(cmd);
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	* 
	*/
	private void addDestroyChildNodesCommand(final ICompositeCommand cmd) {
		final View view = (View) getHost().getModel();
		for (final Iterator< ? > nit = view.getChildren().iterator(); nit.hasNext();) {
			final Node node = (Node) nit.next();
			final int visualId = this.visualIdRegistry.getVisualID(node);
			switch (visualId) {
			case FindByStubCompartmentEditPart.VISUAL_ID:
				for (final Iterator< ? > cit = node.getChildren().iterator(); cit.hasNext();) {
					final Node cnode = (Node) cit.next();
					switch (this.visualIdRegistry.getVisualID(cnode)) {
					case NamingServiceEditPart.VISUAL_ID:
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case DomainFinderEditPart.VISUAL_ID:
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					default:
						break;
					}
				}
				break;
			default:
				final MAPPING_ID mappingId = this.visualIdRegistry.getMappingID(visualId);
				if (mappingId != null) {
					switch (mappingId) {
					case ProvidesPortStubEditPart:
						for (final Iterator< ? > it = node.getTargetEdges().iterator(); it.hasNext();) {
							final Edge incomingLink = (Edge) it.next();
							if (this.visualIdRegistry.getMappingID(incomingLink) == MAPPING_ID.ConnectInterfaceEditPart) {
								final DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(r));
								cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
								continue;
							}
						}
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of node as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
						break;
					case UsesPortStubEditPart:
						for (final Iterator< ? > it = node.getSourceEdges().iterator(); it.hasNext();) {
							final Edge outgoingLink = (Edge) it.next();
							if (this.visualIdRegistry.getMappingID(outgoingLink) == MAPPING_ID.ConnectInterfaceEditPart) {
								final DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(r));
								cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
								continue;
							}
						}
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of node as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
						break;
					case ComponentSupportedInterfaceStubEditPart:
						for (final Iterator< ? > it = node.getTargetEdges().iterator(); it.hasNext();) {
							final Edge incomingLink = (Edge) it.next();
							if (this.visualIdRegistry.getMappingID(incomingLink) == MAPPING_ID.ConnectInterfaceEditPart) {
								final DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(r));
								cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
								continue;
							}
						}
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of node as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
						break;
					default:
						break;
					}
				}
			}
		}
	}

	/**
	 * 
	 */
	@Override
	protected Command getCreateRelationshipCommand(final CreateRelationshipRequest req) {
		final Command command = (req.getTarget() == null) ? getStartCreateRelationshipCommand(req) : getCompleteCreateRelationshipCommand(req);
		return (command != null) ? command : super.getCreateRelationshipCommand(req);
	}

	/**
	 * 
	 */
	protected Command getStartCreateRelationshipCommand(final CreateRelationshipRequest req) {
		if (this.elementTypes.getConnectInterfaceElementType() == req.getElementType()) {
			return null;
		}
		return null;
	}

	/**
	 * 
	 */
	protected Command getCompleteCreateRelationshipCommand(final CreateRelationshipRequest req) {
		if (this.elementTypes.getConnectInterfaceElementType() == req.getElementType()) {
			return getGEFWrapper(createConnectInterfaceCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	protected abstract ICommand createConnectInterfaceCreateCommand(CreateRelationshipRequest req, EObject source, EObject target);

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * 
	 */
	@Override
	protected Command getReorientRelationshipCommand(final ReorientRelationshipRequest req) {
		final MAPPING_ID mappingId = this.visualIdRegistry.getMappingID(getVisualID(req));
		if (mappingId != null) {
			switch (mappingId) {
			case ConnectInterfaceEditPart:
				return getGEFWrapper(createConnectInterfaceReorientCommand(req));
			default:
				break;
			}
		}
		return super.getReorientRelationshipCommand(req);
	}

	protected abstract ICommand createConnectInterfaceReorientCommand(ReorientRelationshipRequest req);

}

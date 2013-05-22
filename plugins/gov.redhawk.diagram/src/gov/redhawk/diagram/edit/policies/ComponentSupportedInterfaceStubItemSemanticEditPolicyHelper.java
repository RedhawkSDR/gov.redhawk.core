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
package gov.redhawk.diagram.edit.policies;

import gov.redhawk.diagram.providers.PartitioningElementTypes;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.FindByStub;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;

/**
 * @since 3.0
 * 
 */
public class ComponentSupportedInterfaceStubItemSemanticEditPolicyHelper {

	private final PartitioningElementTypes elementTypes;
	private final IComponentSupportedInterfaceStubItemSemanticEditPolicy editPolicy;

	public ComponentSupportedInterfaceStubItemSemanticEditPolicyHelper(final PartitioningElementTypes elementTypes,
	        final IComponentSupportedInterfaceStubItemSemanticEditPolicy editPolicy) {
		this.elementTypes = elementTypes;
		this.editPolicy = editPolicy;
	}

	public Command getCompleteCreateRelationshipCommand(final CreateRelationshipRequest req) {
		if (this.elementTypes.getConnectInterfaceElementType() == req.getElementType()) {
			return this.editPolicy.getGEFWrapper(this.editPolicy.createConnectInterfaceCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
     * @since 4.0
     */
	public boolean shouldProceed(final DestroyRequest destroyRequest, final EObject object) {
		if (object instanceof ComponentSupportedInterfaceStub) {
			final ComponentSupportedInterfaceStub stub = (ComponentSupportedInterfaceStub) object;

			return (stub.eContainer() instanceof FindByStub);
		}
		return false;
	}

	public Command getDuplicateCommand(final DuplicateElementsRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

	public Command getMoveCommand(final MoveRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

}

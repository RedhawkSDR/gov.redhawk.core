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
package gov.redhawk.diagram.edit.policies;

import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry.MAPPING_ID;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.BorderItemSelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @since 3.0
 * 
 */
public class ComponentInstantiationItemLayoutEditPolicy extends LayoutEditPolicy {

	private final PartitioningVisualIDRegistry visualIdRegistry;

	public ComponentInstantiationItemLayoutEditPolicy(final PartitioningVisualIDRegistry visualIdRegistry) {
		this.visualIdRegistry = visualIdRegistry;
	}

	@Override
	public EditPolicy createChildEditPolicy(final EditPart child) {
		final View childView = (View) child.getModel();
		final MAPPING_ID mappingId = this.visualIdRegistry.getMappingID(childView);
		if (mappingId != null) {
			switch (mappingId) {
			case UsesPortStubEditPart:
			case ProvidesPortStubEditPart:
			case ComponentSupportedInterfaceStubEditPart:
				return new BorderItemSelectionEditPolicy() {
					@Override
					protected Command getMoveCommand(final ChangeBoundsRequest request) {
						return UnexecutableCommand.INSTANCE;
					}
				};
			default:
				break;
			}
		}
		EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
		if (result == null) {
			result = new NonResizableEditPolicy();
		}
		return result;
	}

	@Override
	public Command getCreateCommand(final CreateRequest request) {
		return null;
	}

	@Override
	public Command getMoveChildrenCommand(final Request request) {
		return null;
	}

	@Override
	public Command getCommand(final Request request) {
		if (request instanceof EditCommandRequestWrapper) {
			final EditCommandRequestWrapper wrapper = (EditCommandRequestWrapper) request;
			if (wrapper.getEditCommandRequest() instanceof DestroyElementRequest) {
				final DestroyElementRequest editRequest = (DestroyElementRequest) wrapper.getEditCommandRequest();

				if (editRequest.getElementToDestroy() instanceof ComponentInstantiation) {
					final ComponentInstantiation compInst = (ComponentInstantiation) editRequest.getElementToDestroy();
					final DestroyElementRequest destroyReq = new DestroyElementRequest(compInst.eContainer(), false);
					return getHost().getParent().getParent().getCommand(new EditCommandRequestWrapper(destroyReq));
				}
			}
		}
		return super.getCommand(request);
	}

}

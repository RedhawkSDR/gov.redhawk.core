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
package gov.redhawk.diagram.providers;

import gov.redhawk.diagram.edit.parts.DomainFinderEditPart;
import gov.redhawk.diagram.edit.parts.FindByStubCompartmentEditPart;
import gov.redhawk.diagram.edit.parts.FindByStubEditPart;
import gov.redhawk.diagram.edit.parts.NamingServiceEditPart;
import gov.redhawk.diagram.edit.policies.ComponentInstantiationItemLayoutEditPolicy;
import gov.redhawk.diagram.edit.policies.ComponentPlacementCompartmentItemLayoutEditPolicy;
import gov.redhawk.diagram.edit.policies.OpenDiagramEditPolicy;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry.MAPPING_ID;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @since 3.0
 * 
 */
public abstract class PartitioningEditPolicyProvider extends AbstractProvider implements IEditPolicyProvider {

	protected final PartitioningVisualIDRegistry visualIdRegistry; // SUPPRESS CHECKSTYLE Protected field
	protected final PartitioningElementTypes elementTypes; // SUPPRESS CHECKSTYLE Protected field

	public PartitioningEditPolicyProvider(final PartitioningElementTypes elementTypes, final PartitioningVisualIDRegistry visualIdRegistry) {
		this.visualIdRegistry = visualIdRegistry;
		this.elementTypes = elementTypes;
	}

	@Override
	public void createEditPolicies(final EditPart editPart) {
		final View view = (View) editPart.getModel();
		if (!this.visualIdRegistry.getModelID().equals(this.visualIdRegistry.getModelID(view))) {
			return;
		}
		final int visualId = this.visualIdRegistry.getVisualID(view);
		switch (visualId) {
		case FindByStubEditPart.VISUAL_ID:
			setupFindByStubEditPart(editPart);
			break;
		case FindByStubCompartmentEditPart.VISUAL_ID:
			setupFindByStubCompartmentEditPart(editPart);
			break;
		case DomainFinderEditPart.VISUAL_ID:
			setupDomainFinderEditPart(editPart);
			break;
		case NamingServiceEditPart.VISUAL_ID:
			setupNamingServiceEditPart(editPart);
			break;
		default:
			final MAPPING_ID mappingId = this.visualIdRegistry.getMappingID(visualId);
			if (mappingId != null) {
				switch (mappingId) {
				case UsesPortStubEditPart:
					setupUsesPortStubEditPart(editPart);
					break;
				case ProvidesPortStubEditPart:
					setupProvidesPortStubEditPart(editPart);
					break;
				case PartitioningEditPart:
					setupPartitioningEditPart(editPart);
					break;
				case PartitioningCompartmentEditPart:
					setupPartitioningCompartmentEditPart(editPart);
					break;
				case ComponentPlacementCompartmentEditPart:
					setupComponentPlacementCompartmentEditPart(editPart);
					break;
				case ComponentPlacementEditPart:
					setupComonentPlacementEditPart(editPart);
					break;
				case ComponentSupportedInterfaceStubEditPart:
					setupComponentSupportedInterfaceStubEditPart(editPart);
					break;
				case ComponentInstantiationEditPart:
					setupComponentInstantiationEditPart(editPart);
					break;
				case ProvidesPortStubNameEditPart:
					setupProvidesPortStubNameEditPart(editPart);
					break;
				case UsesPortStubNameEditPart:
					setupUsesPortStubNameEditPart(editPart);
					break;
				default:
					break;
				}
			}
		}
	}

	protected void setupNamingServiceEditPart(final EditPart editPart) {
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	protected void setupDomainFinderEditPart(final EditPart editPart) {
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	protected void setupFindByStubCompartmentEditPart(final EditPart editPart) {
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	protected void setupPartitioningCompartmentEditPart(final EditPart editPart) {
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	@Override
	public boolean provides(final IOperation operation) {
		return operation instanceof CreateEditPoliciesOperation;
	}

	protected void setupComonentPlacementEditPart(final EditPart editPart) {
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	protected void setupComponentInstantiationEditPart(final EditPart editPart) {
		editPart.installEditPolicy(EditPolicy.LAYOUT_ROLE, new ComponentInstantiationItemLayoutEditPolicy(this.visualIdRegistry));
		editPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, createComponentInstantiationItemSemanticEditPolicy());
		editPart.installEditPolicy(EditPolicyRoles.OPEN_ROLE, new OpenDiagramEditPolicy());
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	protected abstract EditPolicy createComponentInstantiationItemSemanticEditPolicy();

	protected void setupComponentPlacementCompartmentEditPart(final EditPart editPart) {
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
		editPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, createComponentPlacementCompartmentItemSemanticEditPolicy());
		editPart.installEditPolicy(EditPolicy.LAYOUT_ROLE, new ComponentPlacementCompartmentItemLayoutEditPolicy());
	}

	protected abstract EditPolicy createComponentPlacementCompartmentItemSemanticEditPolicy();

	protected void setupComponentSupportedInterfaceStubEditPart(final EditPart editPart) {
		editPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, createComponentSupportedInterfaceStubItemSemanticEditPolicy());
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	protected abstract EditPolicy createComponentSupportedInterfaceStubItemSemanticEditPolicy();

	protected void setupFindByStubEditPart(final EditPart editPart) {
		editPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, createFindByStubItemSemanticEditPolicy(this.elementTypes, this.visualIdRegistry));
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	protected abstract EditPolicy createFindByStubItemSemanticEditPolicy(PartitioningElementTypes elementTypes2, PartitioningVisualIDRegistry visualIdRegistry2);

	protected void setupPartitioningEditPart(final EditPart editPart) {
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	protected void setupProvidesPortStubEditPart(final EditPart editPart) {
		editPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, createProvidesPortStubItemSemanticEditPolicy());
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
	}

	protected abstract EditPolicy createProvidesPortStubItemSemanticEditPolicy();

	protected void setupProvidesPortStubNameEditPart(final EditPart editPart) {
		editPart.removeEditPolicy(EditPolicy.DIRECT_EDIT_ROLE);
		editPart.removeEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
	}

	protected void setupUsesPortStubEditPart(final EditPart editPart) {
		editPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, createUsesPortStubItemSemanticEditPolicy());
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
	}

	protected abstract EditPolicy createUsesPortStubItemSemanticEditPolicy();

	protected void setupUsesPortStubNameEditPart(final EditPart editPart) {
		editPart.removeEditPolicy(EditPolicy.DIRECT_EDIT_ROLE);
		editPart.removeEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
	}

}

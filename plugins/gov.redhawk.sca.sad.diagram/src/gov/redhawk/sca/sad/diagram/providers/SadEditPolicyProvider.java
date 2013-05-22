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
package gov.redhawk.sca.sad.diagram.providers;

import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.providers.PartitioningEditPolicyProvider;
import gov.redhawk.diagram.providers.PartitioningElementTypes;
import gov.redhawk.sca.sad.diagram.edit.policies.ComponentInstantiationItemSemanticEditPolicy;
import gov.redhawk.sca.sad.diagram.edit.policies.ComponentPlacementCompartmentItemSemanticEditPolicy;
import gov.redhawk.sca.sad.diagram.edit.policies.ComponentSupportedInterfaceStubItemSemanticEditPolicy;
import gov.redhawk.sca.sad.diagram.edit.policies.FindByStubItemSemanticEditPolicy;
import gov.redhawk.sca.sad.diagram.edit.policies.HostCollocationCompartmentCanonicalEditPolicy;
import gov.redhawk.sca.sad.diagram.edit.policies.HostCollocationCompartmentItemSemanticEditPolicy;
import gov.redhawk.sca.sad.diagram.edit.policies.HostCollocationItemSemanticEditPolicy;
import gov.redhawk.sca.sad.diagram.edit.policies.ProvidesPortStubItemSemanticEditPolicy;
import gov.redhawk.sca.sad.diagram.edit.policies.SadComponentInstantiationCanonicalEditPolicy;
import gov.redhawk.sca.sad.diagram.edit.policies.SadComponentPlacementItemSemanticEditPolicy;
import gov.redhawk.sca.sad.diagram.edit.policies.SoftwareAssemblyCanonicalEditPolicy;
import gov.redhawk.sca.sad.diagram.edit.policies.SoftwareAssemblyCreationEditPolicy;
import gov.redhawk.sca.sad.diagram.edit.policies.SoftwareAssemblyItemSemanticEditPolicy;
import gov.redhawk.sca.sad.diagram.edit.policies.UsesPortStubItemSemanticEditPolicy;
import gov.redhawk.sca.sad.diagram.part.RedhawkSadVisualIdRegistry;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationCompartmentEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SoftwareAssemblyEditPart;
import mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 */
public class SadEditPolicyProvider extends PartitioningEditPolicyProvider implements IEditPolicyProvider {

	public SadEditPolicyProvider() {
		super(RedhawkSadElementTypes.INSTANCE, RedhawkSadVisualIdRegistry.INSTANCE);
	}

	@Override
	public void createEditPolicies(final EditPart editPart) {
		final View view = (View) editPart.getModel();
		if (!SoftwareAssemblyEditPart.MODEL_ID.equals(SadVisualIDRegistry.getModelID(view))) {
			return;
		}
		final int visualId = SadVisualIDRegistry.getVisualID(view);
		switch (visualId) {
		case SoftwareAssemblyEditPart.VISUAL_ID:
			setupSoftwareAssemblyEditPart(editPart);
			break;
		case HostCollocationEditPart.VISUAL_ID:
			setupHostCollocationEditPart(editPart);
			break;
		case HostCollocationCompartmentEditPart.VISUAL_ID:
			setupHostCollocationCompartmentEditPart(editPart);
			break;
		case ComponentPlacementEditPart.VISUAL_ID:
			setupComponentPlacementEditPart(editPart);
			break;
		case mil.jpeojtrs.sca.sad.diagram.edit.parts.SadComponentInstantiationEditPart.VISUAL_ID:
			setupComponentInstantiationEditPart(editPart);
			break;
		default:
			super.createEditPolicies(editPart);
			break;
		}
	}

	/**
	 * @since 2.0
	 */
	protected void setupComponentPlacementEditPart(final EditPart editPart) {
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
		editPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new SadComponentPlacementItemSemanticEditPolicy());
	}

	/**
	 * @since 2.0
	 */
	protected void setupHostCollocationEditPart(final EditPart editPart) {
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
		editPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new HostCollocationItemSemanticEditPolicy());
	}

	/**
	 * @since 2.0
	 */
	protected void setupHostCollocationCompartmentEditPart(final EditPart editPart) {
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
		editPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new HostCollocationCompartmentItemSemanticEditPolicy());
		editPart.installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new HostCollocationCompartmentCanonicalEditPolicy());
	}

	/**
	 * @since 2.0
	 */
	protected void setupSoftwareAssemblyEditPart(final EditPart editPart) {
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
		editPart.installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new SoftwareAssemblyCanonicalEditPolicy());
		editPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new SoftwareAssemblyItemSemanticEditPolicy());
		editPart.installEditPolicy(EditPolicyRoles.CREATION_ROLE, new SoftwareAssemblyCreationEditPolicy());
	}

	@Override
	protected void setupComponentInstantiationEditPart(final EditPart editPart) {
		super.setupComponentInstantiationEditPart(editPart);
		editPart.installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new SadComponentInstantiationCanonicalEditPolicy());
	}

	@Override
	protected EditPolicy createComponentInstantiationItemSemanticEditPolicy() {
		return new ComponentInstantiationItemSemanticEditPolicy();
	}

	@Override
	protected EditPolicy createComponentPlacementCompartmentItemSemanticEditPolicy() {
		return new ComponentPlacementCompartmentItemSemanticEditPolicy();
	}

	@Override
	protected EditPolicy createComponentSupportedInterfaceStubItemSemanticEditPolicy() {
		return new ComponentSupportedInterfaceStubItemSemanticEditPolicy();
	}

	@Override
	protected EditPolicy createFindByStubItemSemanticEditPolicy(final PartitioningElementTypes elementTypes2,
	        final PartitioningVisualIDRegistry visualIdRegistry2) {
		return new FindByStubItemSemanticEditPolicy();
	}

	@Override
	protected EditPolicy createProvidesPortStubItemSemanticEditPolicy() {
		return new ProvidesPortStubItemSemanticEditPolicy();
	}

	@Override
	protected EditPolicy createUsesPortStubItemSemanticEditPolicy() {
		return new UsesPortStubItemSemanticEditPolicy();
	}

}

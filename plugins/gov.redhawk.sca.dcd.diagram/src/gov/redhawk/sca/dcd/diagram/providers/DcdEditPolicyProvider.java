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
package gov.redhawk.sca.dcd.diagram.providers;

import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.providers.PartitioningEditPolicyProvider;
import gov.redhawk.diagram.providers.PartitioningElementTypes;
import gov.redhawk.sca.dcd.diagram.edit.policies.ComponentInstantiationItemSemanticEditPolicy;
import gov.redhawk.sca.dcd.diagram.edit.policies.ComponentPlacementCompartmentItemSemanticEditPolicy;
import gov.redhawk.sca.dcd.diagram.edit.policies.ComponentSupportedInterfaceStubItemSemanticEditPolicy;
import gov.redhawk.sca.dcd.diagram.edit.policies.DcdComponentInstantiationCanonicalEditPolicy;
import gov.redhawk.sca.dcd.diagram.edit.policies.DcdComponentPlacementItemSemanticEditPolicy;
import gov.redhawk.sca.dcd.diagram.edit.policies.DeviceConfigurationEditPartCanonicalEditPolicy;
import gov.redhawk.sca.dcd.diagram.edit.policies.DeviceConfigurationEditPartItemSemanticEditPolicy;
import gov.redhawk.sca.dcd.diagram.edit.policies.FindByStubItemSemanticEditPolicy;
import gov.redhawk.sca.dcd.diagram.edit.policies.ProvidesPortStubItemSemanticEditPolicy;
import gov.redhawk.sca.dcd.diagram.edit.policies.UsesPortStubItemSemanticEditPolicy;
import gov.redhawk.sca.dcd.diagram.part.RedhawkDcdVisualIDRegistry;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementEditPart;
import mil.jpeojtrs.sca.dcd.diagram.part.DcdVisualIDRegistry;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 */
public class DcdEditPolicyProvider extends PartitioningEditPolicyProvider implements IEditPolicyProvider {

	public DcdEditPolicyProvider() {
		super(RedhawkDcdElementTypes.INSTANCE, RedhawkDcdVisualIDRegistry.INSTANCE);
	}

	@Override
	public void createEditPolicies(final EditPart editPart) {
		final View view = (View) editPart.getModel();
		if (!mil.jpeojtrs.sca.dcd.diagram.edit.parts.DeviceConfigurationEditPart.MODEL_ID.equals(DcdVisualIDRegistry.getModelID(view))) {
			return;
		}
		final int visualId = DcdVisualIDRegistry.getVisualID(view);
		switch (visualId) {
		case mil.jpeojtrs.sca.dcd.diagram.edit.parts.DeviceConfigurationEditPart.VISUAL_ID:
			setupDeviceConfigurationEditPartEditPart(editPart);
			break;
		case DcdComponentPlacementEditPart.VISUAL_ID:
			setupDcdComponentPlacementEditPart(editPart);
			break;
		case mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentInstantiationEditPart.VISUAL_ID:
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
	protected void setupDeviceConfigurationEditPartEditPart(final EditPart editPart) {
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
		editPart.installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new DeviceConfigurationEditPartCanonicalEditPolicy());
		editPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DeviceConfigurationEditPartItemSemanticEditPolicy());
	}

	/**
	 * @since 2.0
	 */
	protected void setupDcdComponentPlacementEditPart(final EditPart editPart) {
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
		editPart.installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DcdComponentPlacementItemSemanticEditPolicy());
	}

	@Override
	protected void setupComponentInstantiationEditPart(final EditPart editPart) {
		super.setupComponentInstantiationEditPart(editPart);
		editPart.installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new DcdComponentInstantiationCanonicalEditPolicy());
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

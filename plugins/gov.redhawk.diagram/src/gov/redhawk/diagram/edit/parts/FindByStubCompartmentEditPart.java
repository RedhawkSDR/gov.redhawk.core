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

package gov.redhawk.diagram.edit.parts;

import gov.redhawk.diagram.edit.policies.FindByStubCompartmentCanonicalEditPolicy;
import gov.redhawk.diagram.edit.policies.FindByStubCompartmentItemLayoutEditPolicy;
import gov.redhawk.diagram.edit.policies.FindByStubCompartmentItemSemanticEditPolicy;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.providers.PartitioningElementTypes;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @since 3.0
 * 
 */
public class FindByStubCompartmentEditPart extends ListCompartmentEditPart {

	/**
	* 
	*/
	public static final int VISUAL_ID = 17005;
	private final PartitioningVisualIDRegistry visualIdRegistry;
	private final PartitioningElementTypes elementTypes;

	/**
	* 
	*/
	public FindByStubCompartmentEditPart(final View view, final PartitioningVisualIDRegistry visualIdVisualIDRegistry,
	        final PartitioningElementTypes elementTypes) {
		super(view);
		this.visualIdRegistry = visualIdVisualIDRegistry;
		this.elementTypes = elementTypes;
	}

	/**
	* 
	*/
	@Override
	protected boolean hasModelChildrenChanged(final Notification evt) {
		return false;
	}

	/**
	* 
	*/
	@Override
	public String getCompartmentName() {
		return "FindBy";
	}

	/**
	* 
	*/
	@Override
	public IFigure createFigure() {
		final ResizableCompartmentFigure result = (ResizableCompartmentFigure) super.createFigure();
		result.setTitleVisibility(false);
		result.setBorder(null);
		return result;
	}

	/**
	* 
	*/
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new FindByStubCompartmentItemSemanticEditPolicy(this.elementTypes, this.visualIdRegistry));
		installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new DragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new FindByStubCompartmentCanonicalEditPolicy(this.visualIdRegistry));
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new FindByStubCompartmentItemLayoutEditPolicy());
	}

	/**
	* 
	*/
	@Override
	protected void setRatio(final Double ratio) {
		if (getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
			super.setRatio(ratio);
		}
	}

}

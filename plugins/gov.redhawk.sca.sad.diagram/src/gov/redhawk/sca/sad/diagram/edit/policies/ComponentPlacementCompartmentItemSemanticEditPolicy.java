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
package gov.redhawk.sca.sad.diagram.edit.policies;

import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;

/**
 * 
 */
public class ComponentPlacementCompartmentItemSemanticEditPolicy extends
        mil.jpeojtrs.sca.sad.diagram.edit.policies.ComponentPlacementCompartmentItemSemanticEditPolicy {

	private gov.redhawk.diagram.edit.policies.ComponentPlacementCompartmentItemSemanticEditPolicyHelper editPolicyHelper = new
		gov.redhawk.diagram.edit.policies.ComponentPlacementCompartmentItemSemanticEditPolicyHelper(); 
	
	@Override
	protected boolean shouldProceed(final DestroyRequest destroyRequest) {
		return this.editPolicyHelper.shouldProceed(destroyRequest);
	}
}

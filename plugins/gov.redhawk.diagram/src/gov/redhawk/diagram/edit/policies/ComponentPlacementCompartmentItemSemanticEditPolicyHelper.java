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

import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;

/**
 * @since 3.0
 * 
 */
public class ComponentPlacementCompartmentItemSemanticEditPolicyHelper {

	public ComponentPlacementCompartmentItemSemanticEditPolicyHelper() {
		
	}
	
	public boolean shouldProceed(final DestroyRequest destroyRequest) {
		return false;
	}
}

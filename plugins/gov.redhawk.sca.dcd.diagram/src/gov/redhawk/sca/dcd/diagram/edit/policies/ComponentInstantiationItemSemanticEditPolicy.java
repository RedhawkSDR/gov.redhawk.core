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

package gov.redhawk.sca.dcd.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

public class ComponentInstantiationItemSemanticEditPolicy extends mil.jpeojtrs.sca.dcd.diagram.edit.policies.DcdComponentInstantiationItemSemanticEditPolicy {

	private final gov.redhawk.diagram.edit.policies.ComponentInstantiationItemSemanticEditPolicyHelper editPolicyHelper = new gov.redhawk.diagram.edit.policies.ComponentInstantiationItemSemanticEditPolicyHelper();

	@Override
	protected Command getCreateCommand(final CreateElementRequest req) {
		return this.editPolicyHelper.getCreateCommand(req);
	}

	@Override
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
	    return super.getDestroyElementCommand(req);
	}
}

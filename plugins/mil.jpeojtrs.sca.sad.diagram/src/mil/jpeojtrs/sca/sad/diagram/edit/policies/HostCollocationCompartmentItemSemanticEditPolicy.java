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
// BEGIN GENERATED CODE

package mil.jpeojtrs.sca.sad.diagram.edit.policies;

import mil.jpeojtrs.sca.sad.diagram.edit.commands.ComponentPlacement2CreateCommand;
import mil.jpeojtrs.sca.sad.diagram.providers.SadElementTypes;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class HostCollocationCompartmentItemSemanticEditPolicy extends
		SadBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public HostCollocationCompartmentItemSemanticEditPolicy() {
		super(SadElementTypes.HostCollocation_3006);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (SadElementTypes.SadComponentPlacement_3007 == req.getElementType()) {
			return getGEFWrapper(new ComponentPlacement2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}

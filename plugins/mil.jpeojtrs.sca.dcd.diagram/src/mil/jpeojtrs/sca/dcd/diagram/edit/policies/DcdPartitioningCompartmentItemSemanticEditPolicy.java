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

// BEGIN GENERATED CODE
package mil.jpeojtrs.sca.dcd.diagram.edit.policies;

import mil.jpeojtrs.sca.dcd.diagram.edit.commands.DcdComponentPlacementCreateCommand;
import mil.jpeojtrs.sca.dcd.diagram.providers.DcdElementTypes;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class DcdPartitioningCompartmentItemSemanticEditPolicy extends
		DcdBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public DcdPartitioningCompartmentItemSemanticEditPolicy() {
		super(DcdElementTypes.DcdPartitioning_2001);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (DcdElementTypes.DcdComponentPlacement_3001 == req.getElementType()) {
			return getGEFWrapper(new DcdComponentPlacementCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}

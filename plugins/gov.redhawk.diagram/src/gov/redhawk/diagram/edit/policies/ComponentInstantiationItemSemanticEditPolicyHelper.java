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

package gov.redhawk.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @since 3.0
 */
public class ComponentInstantiationItemSemanticEditPolicyHelper {

	public ComponentInstantiationItemSemanticEditPolicyHelper() {
		
	}
	
	public Command getCreateCommand(final CreateElementRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

}

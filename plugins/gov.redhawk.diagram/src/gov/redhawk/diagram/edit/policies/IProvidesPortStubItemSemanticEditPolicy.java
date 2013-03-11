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

import gov.redhawk.diagram.edit.commands.ConnectInterfaceCreateCommand;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;

/**
 * @since 3.0
 * 
 */
public interface IProvidesPortStubItemSemanticEditPolicy {

	ConnectInterfaceCreateCommand createConnectInterfaceCreateCommand(CreateRelationshipRequest req, EObject source, EObject target);

	Command getGEFWrapper(ConnectInterfaceCreateCommand createConnectInterfaceCreateCommand);

}

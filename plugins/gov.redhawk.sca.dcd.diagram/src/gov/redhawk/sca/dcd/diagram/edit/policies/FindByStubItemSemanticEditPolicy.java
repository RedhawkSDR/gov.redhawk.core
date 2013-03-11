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
* Identification: $Revision: 6702 $
*/

package gov.redhawk.sca.dcd.diagram.edit.policies;

import gov.redhawk.sca.dcd.diagram.edit.commands.ConnectInterfaceCreateCommand;
import gov.redhawk.sca.dcd.diagram.edit.commands.DcdConnectInterfaceReorientCommand;
import gov.redhawk.sca.dcd.diagram.part.RedhawkDcdVisualIDRegistry;
import gov.redhawk.sca.dcd.diagram.providers.RedhawkDcdElementTypes;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 */
public class FindByStubItemSemanticEditPolicy extends gov.redhawk.diagram.edit.policies.FindByStubItemSemanticEditPolicy {

	/**
	* 
	*/
	public FindByStubItemSemanticEditPolicy() {
		super(RedhawkDcdElementTypes.INSTANCE, RedhawkDcdVisualIDRegistry.INSTANCE);
	}

	@Override
	protected ICommand createConnectInterfaceCreateCommand(final CreateRelationshipRequest req, final EObject source, final EObject target) {
		return new ConnectInterfaceCreateCommand((DeviceConfiguration) ((View) getHost().getRoot().getContents().getModel()).getElement(), req, source, target);
	}

	@Override
	protected ICommand createConnectInterfaceReorientCommand(final ReorientRelationshipRequest req) {
		return new DcdConnectInterfaceReorientCommand(req);
	}

}

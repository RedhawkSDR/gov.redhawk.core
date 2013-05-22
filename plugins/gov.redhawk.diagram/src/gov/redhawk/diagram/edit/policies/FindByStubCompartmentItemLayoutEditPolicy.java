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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy;

/**
 * @since 3.0
 * 
 */
public class FindByStubCompartmentItemLayoutEditPolicy extends LayoutEditPolicy {

	public FindByStubCompartmentItemLayoutEditPolicy() {
		
	};
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public EditPolicy createChildEditPolicy(final EditPart child) {
		return new NonResizableEditPolicy() {

			@Override
			public EditPart getHost() {
				// TODO Auto-generated method stub
				return super.getHost();
			}

			@Override
			protected Command getMoveCommand(final ChangeBoundsRequest request) {
				// TODO Auto-generated method stub
				return super.getMoveCommand(request);
			}
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
    public Command getCreateCommand(final CreateRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
    public Command getMoveChildrenCommand(final Request request) {
		// TODO Auto-generated method stub
		return null;
	}

}

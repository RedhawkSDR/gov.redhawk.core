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
package gov.redhawk.sca.dcd.diagram.edit.commands;

import gov.redhawk.diagram.util.InterfacesUtil;
import mil.jpeojtrs.sca.dcd.DcdConnectInterface;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRequest;

/**
 * @since 2.0
 */
public class DcdConnectInterfaceReorientCommand extends mil.jpeojtrs.sca.dcd.diagram.edit.commands.DcdConnectInterfaceReorientCommand {

	/**
	 * Constructor
	 * @param request
	 */
	public DcdConnectInterfaceReorientCommand(final ReorientRelationshipRequest request) {
		super(request);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canExecute() {
		boolean retVal = super.canExecute();

		if (retVal) {
			final DcdConnectInterface connect = (DcdConnectInterface) getElementToEdit();
			if (getRequest() instanceof ReorientRelationshipRequest) {
				final ReorientRelationshipRequest request = (ReorientRelationshipRequest) getRequest();

				if (request.getDirection() == ReorientRequest.REORIENT_SOURCE) {
					if (getNewSource() != getOldSource()) {
						retVal = InterfacesUtil.areCompatible(getNewSource(), (connect.getTarget()));
					}
				} else if (request.getDirection() == ReorientRequest.REORIENT_TARGET) {
					if (getNewTarget() != getOldTarget()) {
						if (getNewTarget() instanceof ProvidesPortStub || getNewTarget() instanceof ComponentSupportedInterfaceStub) {
							retVal = InterfacesUtil.areCompatible(connect.getSource(), getNewTarget());
						}
					}
				}
			}
		}

		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CommandResult reorientSource() throws ExecutionException {
		if (getOldSource() instanceof ComponentSupportedInterfaceStub) {
			getLink().setComponentSupportedInterface(null);
		} else {
			getLink().setUsesPort(null);
		}

		getLink().setSource(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CommandResult reorientTarget() throws ExecutionException {
		if (getOldTarget() instanceof ComponentSupportedInterfaceStub) {
			getLink().setComponentSupportedInterface(null);
		} else {
			getLink().setProvidesPort(null);
		}

		getLink().setTarget(getNewTarget());

		return CommandResult.newOKCommandResult(getLink());
	}
}

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

package mil.jpeojtrs.sca.sad.diagram.edit.commands;

import mil.jpeojtrs.sca.partitioning.ConnectionTarget;
import mil.jpeojtrs.sca.partitioning.Connections;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.sad.SadConnectInterface;
import mil.jpeojtrs.sca.sad.diagram.edit.policies.SadBaseItemSemanticEditPolicy;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

/**
 * @generated
 */
public class SadConnectInterfaceReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;
	/**
	 * @generated
	 */
	private final EObject oldEnd;
	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public SadConnectInterfaceReorientCommand(
			ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof SadConnectInterface) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof UsesPortStub && newEnd instanceof UsesPortStub)) {
			return false;
		}
		ConnectionTarget target = getLink().getTarget();
		if (!(getLink().eContainer() instanceof Connections)) {
			return false;
		}
		Connections container = (Connections) getLink().eContainer();
		return SadBaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistSadConnectInterface_4001(container, getLink(),
						getNewSource(), target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof ConnectionTarget && newEnd instanceof ConnectionTarget)) {
			return false;
		}
		UsesPortStub source = getLink().getSource();
		if (!(getLink().eContainer() instanceof Connections)) {
			return false;
		}
		Connections container = (Connections) getLink().eContainer();
		return SadBaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistSadConnectInterface_4001(container, getLink(), source,
						getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setSource(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setTarget(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected SadConnectInterface getLink() {
		return (SadConnectInterface) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected UsesPortStub getOldSource() {
		return (UsesPortStub) oldEnd;
	}

	/**
	 * @generated
	 */
	protected UsesPortStub getNewSource() {
		return (UsesPortStub) newEnd;
	}

	/**
	 * @generated
	 */
	protected ConnectionTarget getOldTarget() {
		return (ConnectionTarget) oldEnd;
	}

	/**
	 * @generated
	 */
	protected ConnectionTarget getNewTarget() {
		return (ConnectionTarget) newEnd;
	}
}

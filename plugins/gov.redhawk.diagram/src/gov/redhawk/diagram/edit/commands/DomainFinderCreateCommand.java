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

package gov.redhawk.diagram.edit.commands;

import mil.jpeojtrs.sca.partitioning.DomainFinder;
import mil.jpeojtrs.sca.partitioning.FindByStub;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @since 3.0
 * 
 */
public class DomainFinderCreateCommand extends BaseCreateCommand {

	/**
	* 
	*/
	public DomainFinderCreateCommand(final CreateElementRequest req) {
		super(req);
	}

	/**
	* 
	*/
	@Override
	public boolean canExecute() {
		final FindByStub container = (FindByStub) getElementToEdit();
		if (container.getDomainFinder() != null || container.getNamingService() != null) {
			return false;
		}
		return true;

	}

	/**
	* 
	*/
	@Override
	protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
		final DomainFinder newElement = PartitioningFactory.eINSTANCE.createDomainFinder();

		final FindByStub owner = (FindByStub) getElementToEdit();
		owner.setDomainFinder(newElement);

		doConfigure(newElement, monitor, info);

		((CreateElementRequest) getRequest()).setNewElement(newElement);
		return CommandResult.newOKCommandResult(newElement);
	}

}

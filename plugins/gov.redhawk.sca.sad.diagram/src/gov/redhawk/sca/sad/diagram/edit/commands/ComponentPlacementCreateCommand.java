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

package gov.redhawk.sca.sad.diagram.edit.commands;

import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadFactory;
import mil.jpeojtrs.sca.sad.SadPartitioning;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 */
public class ComponentPlacementCreateCommand extends EditElementCommand {

	/**
	* 
	*/
	public ComponentPlacementCreateCommand(final CreateElementRequest req) {
		super(req.getLabel(), null, req);
	}

	@Override
	protected EObject getElementToEdit() {
		EObject container = ((CreateElementRequest) getRequest()).getContainer();
		if (container instanceof View) {
			container = ((View) container).getElement();
		}
		return container;
	}

	/**
	* 
	*/
	@Override
	public boolean canExecute() {
		return true;

	}

	/**
	* 
	*/
	@Override
	protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
		final SadComponentPlacement newElement = SadFactory.eINSTANCE.createSadComponentPlacement();

		final EObject element = getElementToEdit();
		if (element instanceof SoftwareAssembly) {
			final SoftwareAssembly sad = (SoftwareAssembly) element;
			SadPartitioning owner = sad.getPartitioning();
			if (owner == null) {
				owner = SadFactory.eINSTANCE.createSadPartitioning();
				sad.setPartitioning(owner);
			}
			owner.getComponentPlacement().add(newElement);
		} else if (element instanceof HostCollocation) {
			final HostCollocation owner = (HostCollocation) element;
			owner.getComponentPlacement().add(newElement);
		}

		try {
			doConfigure(newElement, monitor, info);
		} catch (final OperationCanceledException e) {
			return CommandResult.newCancelledCommandResult();
		}

		((CreateElementRequest) getRequest()).setNewElement(newElement);
		return CommandResult.newOKCommandResult(newElement);
	}

	/**
	* 
	*/
	protected void doConfigure(final SadComponentPlacement newElement, final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
		final IElementType elementType = ((CreateElementRequest) getRequest()).getElementType();
		final ConfigureRequest configureRequest = new ConfigureRequest(getEditingDomain(), newElement, elementType);
		configureRequest.setClientContext(((CreateElementRequest) getRequest()).getClientContext());
		configureRequest.addParameters(getRequest().getParameters());
		final ICommand configureCommand = elementType.getEditCommand(configureRequest);
		if (configureCommand != null && configureCommand.canExecute()) {
			configureCommand.execute(monitor, info);
		}
	}

}

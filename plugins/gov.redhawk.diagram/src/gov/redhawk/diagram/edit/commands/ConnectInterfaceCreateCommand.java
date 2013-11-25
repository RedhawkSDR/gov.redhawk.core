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

import gov.redhawk.diagram.edit.helpers.ConnectInterfaceEditHelperAdvice;
import gov.redhawk.sca.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.partitioning.ConnectionTarget;
import mil.jpeojtrs.sca.partitioning.Connections;
import mil.jpeojtrs.sca.partitioning.FindByStub;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;

/**
 * @since 3.0
 * 
 */
public abstract class ConnectInterfaceCreateCommand extends EditElementCommand {

	/**
	 * {@inheritDoc}
	 */
	private final EObject source;

	/**
	 * {@inheritDoc}
	 */
	private final EObject target;

	/**
	 * {@inheritDoc}
	 */
	public ConnectInterfaceCreateCommand(final CreateRelationshipRequest request, final EObject source, final EObject target) {
		super(request.getLabel(), null, request);
		this.source = source;
		this.target = target;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canExecute() {
		if (this.source == null && this.target == null) {
			return false;
		}
		if (this.source != null && !(this.source instanceof UsesPortStub)) {
			return false;
		}
		if (this.target != null && !(this.target instanceof ConnectionTarget)) {
			return false;
		}
		if (getSource() == null) {
			return true; // link creation is in progress; source is not defined yet
		}
		if (this.target != null && this.source instanceof UsesPortStub) {
			//			Relax constraint on what constitutes a connection			
			//			TODO: Determine ultimately what defines a valid connection between two end points
			//			return InterfacesUtil.areCompatible(this.source, this.target);
			return (this.target instanceof ProvidesPortStub || this.target instanceof ComponentSupportedInterfaceStub || this.target instanceof FindByStub);
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException("Invalid arguments in create link command"); //$NON-NLS-1$
		}

		final ConnectInterface< ? , ? , ? > newElement = createSadConnectInterface();
		if (getConnections() == null) {
			setConnections(createConnections());
		}
		addNewConnectInterface(newElement);
		newElement.setSource(getSource());
		newElement.setTarget(getTarget());
		doConfigure(newElement, monitor, info);
		((CreateElementRequest) getRequest()).setNewElement(newElement);
		return CommandResult.newOKCommandResult(newElement);
	}

	/**
	 * 
	 * @param newElement
	 * @since 4.0
	 */
	protected abstract void addNewConnectInterface(ConnectInterface< ? , ? , ? > newElement);

	/**
	 * @param createConnections
	 * @since 4.0
	 */
	protected abstract void setConnections(Connections< ? > createConnections);

	/**
	 * 
	 * @return
	 * @since 4.0
	 */
	protected abstract Connections< ? > createConnections();

	/**
	 * 
	 * @return
	 * @since 4.0
	 */
	protected abstract Connections< ? extends ConnectInterface< ? , ? , ? >> getConnections();

	/**
	 * 
	 * @return
	 * @since 4.0
	 */
	protected abstract ConnectInterface< ? , ? , ? > createSadConnectInterface();

	protected void doConfigure(final ConnectInterface< ? , ? , ? > newElement, final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
		final IElementType elementType = ((CreateElementRequest) getRequest()).getElementType();
		final ConfigureRequest configureRequest = new ConfigureRequest(getEditingDomain(), newElement, elementType);
		configureRequest.setClientContext(((CreateElementRequest) getRequest()).getClientContext());
		configureRequest.addParameters(getRequest().getParameters());
		configureRequest.setParameter(CreateRelationshipRequest.SOURCE, getSource());
		configureRequest.setParameter(CreateRelationshipRequest.TARGET, getTarget());
		if (configureRequest.getParameter(ConnectInterfaceEditHelperAdvice.CONFIGURE_OPTIONS_ID) == null) {
			configureRequest.setParameter(ConnectInterfaceEditHelperAdvice.CONFIGURE_OPTIONS_ID, this.createConnectionId());
		}

		final ICommand configureCommand = elementType.getEditCommand(configureRequest);
		if (configureCommand != null && configureCommand.canExecute()) {
			configureCommand.execute(monitor, info);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setElementToEdit(final EObject element) {
		throw new UnsupportedOperationException();
	}

	protected UsesPortStub getSource() {
		return (UsesPortStub) this.source;
	}

	protected ConnectionTarget getTarget() {
		return (ConnectionTarget) this.target;
	}

	private String createConnectionId() {
		final List<String> ids = new ArrayList<String>();
		final List< ? extends ConnectInterface< ? , ? , ? >> connections = getConnections().getConnectInterface();
		for (final ConnectInterface< ? , ? , ? > connection : connections) {
			ids.add(connection.getId());
		}
		return StringUtil.defaultCreateUniqueString("connection_1", ids);
	}

}

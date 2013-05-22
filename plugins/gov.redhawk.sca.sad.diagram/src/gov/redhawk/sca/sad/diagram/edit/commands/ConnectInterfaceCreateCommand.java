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
package gov.redhawk.sca.sad.diagram.edit.commands;

import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.partitioning.Connections;
import mil.jpeojtrs.sca.sad.SadConnectInterface;
import mil.jpeojtrs.sca.sad.SadConnections;
import mil.jpeojtrs.sca.sad.SadFactory;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;

/**
 *
 */
public class ConnectInterfaceCreateCommand extends gov.redhawk.diagram.edit.commands.ConnectInterfaceCreateCommand {

	/**
	*
	*/
	private final SoftwareAssembly container;

	/**
	*
	*/
	public ConnectInterfaceCreateCommand(final SoftwareAssembly container, final CreateRelationshipRequest request, final EObject source, final EObject target) {
		super(request, source, target);
		this.container = container;
	}

	/**
	*
	*/
	@Override
	public boolean canExecute() {
		boolean retVal = super.canExecute();
		if (retVal) {
			// target may be null here but it's possible to check constraint
			if (getContainer() == null) {
				retVal = false;
			}
		}
		return retVal;
	}

	/**
	*
	*/
	public SoftwareAssembly getContainer() {
		return this.container;
	}

	@Override
	protected void setConnections(final Connections< ? > createConnections) {
		getContainer().setConnections((SadConnections) createConnections);

	}

	@Override
	protected SadConnections createConnections() {
		return SadFactory.eINSTANCE.createSadConnections();
	}

	@Override
	protected SadConnections getConnections() {
		return getContainer().getConnections();
	}

	@Override
	protected SadConnectInterface createSadConnectInterface() {
		return SadFactory.eINSTANCE.createSadConnectInterface();
	}

	@Override
	protected void addNewConnectInterface(final ConnectInterface< ? , ? , ? > newElement) {
		getContainer().getConnections().getConnectInterface().add((SadConnectInterface) newElement);
	}

}

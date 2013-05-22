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

import mil.jpeojtrs.sca.dcd.DcdConnectInterface;
import mil.jpeojtrs.sca.dcd.DcdConnections;
import mil.jpeojtrs.sca.dcd.DcdFactory;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.partitioning.Connections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;

/**
 *
 */
public class ConnectInterfaceCreateCommand extends gov.redhawk.diagram.edit.commands.ConnectInterfaceCreateCommand {

	/**
	*
	*/
	private final DeviceConfiguration container;

	/**
	*
	*/
	public ConnectInterfaceCreateCommand(final DeviceConfiguration dcd, final CreateRelationshipRequest request, final EObject source, final EObject target) {
		super(request, source, target);
		this.container = dcd;
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
	public DeviceConfiguration getContainer() {
		return this.container;
	}

	@Override
	protected void setConnections(final Connections< ? > createConnections) {
		getContainer().setConnections((DcdConnections) createConnections);

	}

	@Override
	protected DcdConnections createConnections() {
		return DcdFactory.eINSTANCE.createDcdConnections();
	}

	@Override
	protected DcdConnections getConnections() {
		return getContainer().getConnections();
	}

	@Override
	protected DcdConnectInterface createSadConnectInterface() {
		return DcdFactory.eINSTANCE.createDcdConnectInterface();
	}

	@Override
	protected void addNewConnectInterface(final ConnectInterface< ? , ? , ? > newElement) {
		getContainer().getConnections().getConnectInterface().add((DcdConnectInterface) newElement);
	}

}

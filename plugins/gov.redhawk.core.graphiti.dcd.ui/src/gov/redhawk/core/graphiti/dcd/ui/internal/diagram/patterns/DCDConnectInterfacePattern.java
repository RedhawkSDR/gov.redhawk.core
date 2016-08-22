/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.core.graphiti.dcd.ui.internal.diagram.patterns;

import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;

import gov.redhawk.core.graphiti.ui.diagram.patterns.AbstractConnectInterfacePattern;
import mil.jpeojtrs.sca.dcd.DcdConnectInterface;
import mil.jpeojtrs.sca.dcd.DcdFactory;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;

public class DCDConnectInterfacePattern extends AbstractConnectInterfacePattern {

	@Override
	public String getCreateImageId() {
		return NodeImageProvider.IMG_CONNECTION;
	}

	/**
	 * Determines if a connection can be made.
	 */
	@Override
	public boolean canAdd(IAddContext context) {
		if (context instanceof IAddConnectionContext && context.getNewObject() instanceof DcdConnectInterface) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// Verify DCD is available
		final DeviceConfiguration dcd = DUtil.getDiagramDCD(getDiagram());
		if (dcd == null) {
			return false;
		}

		return super.canStartConnection(context);
	}

	/**
	 * Determines whether creation of an interface connection is possible between source and destination anchors.
	 * User can begin drawing connection from either direction.
	 * Source anchor of connection must be UsesPort.
	 * Target Anchor must be ConnectionTarget which is the parent class for a variety of types.
	 */
	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// get dcd from diagram
		final DeviceConfiguration dcd = DUtil.getDiagramDCD(getDiagram());
		if (dcd == null) {
			return false;
		}

		return super.canCreate(context);
	}

	@Override
	protected ConnectInterface< ? , ? , ? > createConnectInterface() {
		return DcdFactory.eINSTANCE.createDcdConnectInterface();
	}

	@Override
	protected String createConnectionId() {
		DeviceConfiguration dcd = DUtil.getDiagramDCD(getDiagram());
		return createConnectionId(dcd.getConnections());
	}

	@Override
	protected void addConnectInterface(ConnectInterface< ? , ? , ? > connection) {
		// Get DCD from diagram
		DeviceConfiguration dcd = DUtil.getDiagramDCD(getDiagram());

		// Create connections if necessary
		if (dcd.getConnections() == null) {
			dcd.setConnections(DcdFactory.eINSTANCE.createDcdConnections());
		}

		// Add to connections
		dcd.getConnections().getConnectInterface().add((DcdConnectInterface) connection);
	}

}

/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.model.sca.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;

import ExtendedCF.ConnectionStatus;
import ExtendedCF.UsesConnection;
import gov.redhawk.model.sca.ScaConnection;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaNegotiatedConnection;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaUsesPort;

/**
 * @since 21.0
 */
public class ScaUsesPortMergeConnectionsCommand extends SetStatusCommand<ScaUsesPort> {

	private UsesConnection[] usesConnections;
	private ConnectionStatus[] connectionStatuses;

	/**
	 * Uses to merge port connection data from a {@link ExtendedCF.QueryablePort}.
	 *
	 * @param provider
	 * @param usesConnections
	 * @param status
	 */
	public ScaUsesPortMergeConnectionsCommand(ScaUsesPort provider, UsesConnection[] usesConnections, IStatus status) {
		super(provider, ScaPackage.Literals.SCA_USES_PORT__CONNECTIONS, status);
		this.usesConnections = usesConnections;
	}

	/**
	 * Uses to merge port connection data from a {@link ExtendedCF.NegotiableUsesPort}.
	 *
	 * @param provider
	 * @param connectionStatuses
	 * @param status
	 */
	public ScaUsesPortMergeConnectionsCommand(ScaUsesPort provider, ConnectionStatus[] connectionStatuses, IStatus status) {
		super(provider, ScaPackage.Literals.SCA_USES_PORT__CONNECTIONS, status);
		this.connectionStatuses = connectionStatuses;
	}

	@Override
	public void execute() {
		// Existing connection IDs -> SCA model object
		Map<String, ScaConnection> scaConnections = new HashMap<>();
		for (ScaConnection connection : provider.getConnections()) {
			scaConnections.put(connection.getId(), connection);
		}

		// New connection IDs
		Set<String> currentConnectionIDs = new HashSet<>();
		if (usesConnections != null) {
			for (UsesConnection connection : usesConnections) {
				currentConnectionIDs.add(connection.connectionId);
			}
		} else {
			for (ConnectionStatus connection : connectionStatuses) {
				currentConnectionIDs.add(connection.connectionId);
			}
		}

		// Remove old connections
		Map<String, ScaConnection> connectionsToRemove = new HashMap<>(scaConnections);
		connectionsToRemove.keySet().removeAll(currentConnectionIDs);
		if (!connectionsToRemove.isEmpty()) {
			provider.getConnections().removeAll(connectionsToRemove.values());
		}

		// Determine new connections / update existing
		List<ScaConnection> newConnections = new ArrayList<>();
		if (usesConnections != null) {
			for (UsesConnection connection : usesConnections) {
				if (scaConnections.containsKey(connection.connectionId)) {
					continue;
				}
				ScaConnection newConnection = ScaFactory.eINSTANCE.createScaConnection();
				newConnection.setId(connection.connectionId);
				newConnection.setTargetPort(connection.port);
				newConnections.add(newConnection);
			}
		} else {
			for (ConnectionStatus connection : connectionStatuses) {
				ScaNegotiatedConnection existingConnection = (ScaNegotiatedConnection) scaConnections.get(connection.connectionId);
				if (existingConnection != null) {
					if (existingConnection.isAlive() != connection.alive || !existingConnection.isSetAlive()) {
						existingConnection.setAlive(connection.alive);
					}
				} else {
					ScaNegotiatedConnection newConnection = ScaFactory.eINSTANCE.createScaNegotiatedConnection(connection);
					newConnections.add(newConnection);
				}
			}
		}

		// Add new connections
		if (!newConnections.isEmpty()) {
			provider.getConnections().addAll(newConnections);
		}

		// Do this to "Set" the connections
		if (!provider.isSetConnections()) {
			provider.getConnections().clear();
		}

		super.execute();
	}
}

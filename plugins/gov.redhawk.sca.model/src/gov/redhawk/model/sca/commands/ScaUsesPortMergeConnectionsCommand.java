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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;

import ExtendedCF.UsesConnection;
import gov.redhawk.model.sca.ScaConnection;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaUsesPort;

/**
 * @since 20.4
 */
public class ScaUsesPortMergeConnectionsCommand extends SetStatusCommand<ScaUsesPort> {

	private UsesConnection[] newConnections;

	public ScaUsesPortMergeConnectionsCommand(ScaUsesPort provider, UsesConnection[] newConnections, IStatus status) {
		super(provider, ScaPackage.Literals.SCA_USES_PORT__CONNECTIONS, status);
		this.newConnections = newConnections;
	}

	@Override
	public void execute() {
		Map<String, ScaConnection> currentConnections = new HashMap<String, ScaConnection>();
		for (ScaConnection connection : provider.getConnections()) {
			currentConnections.put(connection.getId(), connection);
		}

		Map<String, ScaConnection> connectionToRemove = new HashMap<String, ScaConnection>();
		connectionToRemove.putAll(currentConnections);

		Map<String, ScaConnection> newConnectionMaps = Collections.emptyMap();
		if (newConnections != null) {
			newConnectionMaps = new HashMap<String, ScaConnection>();
			for (UsesConnection connection : newConnections) {
				ScaConnection newConnection = ScaFactory.eINSTANCE.createScaConnection();
				newConnection.setData(connection);
				newConnectionMaps.put(connection.connectionId, newConnection);
			}
		}
		connectionToRemove.keySet().removeAll(newConnectionMaps.keySet());

		// Remove old connections
		if (!connectionToRemove.isEmpty()) {
			provider.getConnections().removeAll(connectionToRemove.values());
		}

		// Remove duplicates
		newConnectionMaps.keySet().removeAll(currentConnections.keySet());

		// Add new connections
		if (!newConnectionMaps.isEmpty()) {
			provider.getConnections().addAll(newConnectionMaps.values());
		}

		// Do this to "Set" the connections
		if (!provider.isSetConnections()) {
			provider.getConnections().clear();
		}

		super.execute();
	}
}

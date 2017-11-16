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
package gov.redhawk.ui.views.connmgr.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Status;

import CF.ConnectionManagerPackage.ConnectionStatusType;
import gov.redhawk.model.sca.commands.SetStatusCommand;
import gov.redhawk.ui.views.connmgr.ConnMgrFactory;
import gov.redhawk.ui.views.connmgr.ConnMgrPackage;
import gov.redhawk.ui.views.connmgr.ConnectionStatus;
import gov.redhawk.ui.views.connmgr.ScaConnectionManager;

public class ScaConnectionManagerMergeConnectionsCommand extends SetStatusCommand<ScaConnectionManager> {

	private ConnectionStatusType[] connectionStatuses;

	public ScaConnectionManagerMergeConnectionsCommand(ScaConnectionManager provider, ConnectionStatusType[] connectionStatuses) {
		super(provider, ConnMgrPackage.Literals.SCA_CONNECTION_MANAGER__CONNECTIONS, Status.OK_STATUS);
		this.connectionStatuses = connectionStatuses;
	}

	@Override
	public void execute() {
		// Existing connection statuses by connection record ID
		Map<String, ConnectionStatus> existingAllocStatuses = new HashMap<>();
		for (ConnectionStatus status : provider.getConnections()) {
			existingAllocStatuses.put(status.getConnectionRecordID(), status);
		}

		// Determine which have been removed, which need to be added
		Set<ConnectionStatus> connectionStatusesToRemove = new HashSet<>();
		connectionStatusesToRemove.addAll(provider.getConnections());
		List<ConnectionStatus> newConnectionStatuses = new LinkedList<>();
		for (ConnectionStatusType connectionStatus : connectionStatuses) {
			String connRecordId = connectionStatus.connectionRecordId;
			if (!connectionStatusesToRemove.remove(existingAllocStatuses.get(connRecordId))) {
				ConnectionStatus status = ConnMgrFactory.eINSTANCE.createConnectionStatus(connectionStatus);
				newConnectionStatuses.add(status);
			}
		}

		provider.getConnections().removeAll(connectionStatusesToRemove);
		provider.getConnections().addAll(newConnectionStatuses);
		super.execute();
	}
}

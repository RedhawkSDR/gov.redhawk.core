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
package gov.redhawk.bulkio.util.internal;

import gov.redhawk.bulkio.util.AbstractBulkIOPort;
import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.bulkio.util.IBulkIOPortConnectionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import BULKIO.updateSRIOperations;

/**
 * 
 */
@NonNullByDefault
public enum ConnectionManager implements IBulkIOPortConnectionManager {
	INSTANCE;

	private List<Connection> connections = Collections.synchronizedList(new ArrayList<Connection>());

	@Override
	public String connect(String ior, BulkIOType type, updateSRIOperations internalPort) throws CoreException {
		return connect(ior, type, internalPort, null);
	}

	@Nullable
	private Connection getConnection(final String ior, final BulkIOType type, @Nullable final String connectionID) {
		synchronized (connections) {
			for (Connection c : connections) {
				String connectIor = c.getIor();
				BulkIOType connectBulkIO = c.getBulkIOType();
				String connectConnectionId = c.getConnectionId();
				boolean isGenerated = c.isGeneratedID();
				if (connectIor.equals(ior) && connectBulkIO.equals(type)) {
					if (connectionID == null) {
						if (isGenerated) {
							return c;
						}
					} else {
						if (connectionID.equals(connectConnectionId)) {
							return c;
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public String connect(final String ior, final BulkIOType type, final updateSRIOperations internalPort, @Nullable final String connectionID)
			throws CoreException {
		if (ior == null || internalPort == null) {
			throw new IllegalArgumentException("Null ior or port implemention.");
		}
		boolean initConnection = false;
		Connection connection;
		synchronized (connections) {
			connection = getConnection(ior, type, connectionID);
			if (connection == null) {
				initConnection = true;
				connection = new Connection(ior, type, connectionID); // create stub
				connections.add(connection);
			}
			connection.registerDataReceiver(internalPort);
		}

		if (initConnection) {
			connection.connectPort();
		}
		return connection.getConnectionId();
	}

	@Override
	public void disconnect(String ior, BulkIOType type, updateSRIOperations internalPort) {
		disconnect(ior, type, internalPort, null);
	}

	@Override
	public void disconnect(String ior, BulkIOType type, updateSRIOperations internalPort, @Nullable String connectionID) {
		if (ior == null || internalPort == null) {
			return;
		}
		Connection connection = getConnection(ior, type, connectionID);
		boolean disposeConnection = false;
		if (connection != null) {
			synchronized (connections) {
				connection.deregisterDataReceiver(internalPort);
				if (connection.isEmpty()) {
					disposeConnection = connections.remove(connection);
				}
			}

			if (disposeConnection) {
				connection.dispose();
			}
		}
	}

	@Nullable
	@Override
	public AbstractBulkIOPort getExternalPort(String ior, BulkIOType type) {
		return getExternalPort(ior, type, null);
	}

	@Nullable
	@Override
	public AbstractBulkIOPort getExternalPort(String ior, BulkIOType type, @Nullable String connectionID) {
		Connection retVal = getConnection(ior, type, connectionID);
		return retVal;
	}

	public void dispose() {
		synchronized (connections) {
			for (Connection connection : connections) {
				connection.dispose();
			}
			connections.clear();
		}
	}
}

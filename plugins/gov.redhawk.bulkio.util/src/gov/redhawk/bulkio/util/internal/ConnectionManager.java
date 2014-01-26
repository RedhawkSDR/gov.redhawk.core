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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.annotation.NonNullByDefault;

import BULKIO.updateSRIOperations;

/**
 * 
 */
@NonNullByDefault
public enum ConnectionManager implements IBulkIOPortConnectionManager {
	INSTANCE;

	private Map<ConnectionKey, Connection> connections = new ConcurrentHashMap<ConnectionKey, Connection>();

	@Override
	public String connect(String ior, BulkIOType type, updateSRIOperations internalPort) throws CoreException {
		return connect(ior, type, internalPort, null);
	}

	@Override
	public String connect(String ior, BulkIOType type, updateSRIOperations internalPort, String connectionID) throws CoreException {
		if (ior == null || internalPort == null) {
			throw new IllegalArgumentException("Null ior or port implemention.");
		}
		ConnectionKey key = new ConnectionKey(ior, type, connectionID);
		boolean initConnection = false;
		Connection connection;
		synchronized (this) {
			connection = connections.get(key);
			if (connection == null) {
				initConnection = true;
				connection = new Connection(key); // create stub
				connections.put(key, connection);
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
	public void disconnect(String ior, BulkIOType type, updateSRIOperations internalPort, String connectionID) {
		if (ior == null || internalPort == null) {
			return;
		}
		ConnectionKey key = new ConnectionKey(ior, type, connectionID);
		Connection connection = connections.get(key);
		boolean disposeConnection = false;
		if (connection != null) {
			synchronized (this) {
				connection.deregisterDataReceiver(internalPort);
				if (connection.isEmpty()) {
					connections.remove(key);
					disposeConnection = true;
				}
			}

			if (disposeConnection) {
				connection.dispose();
			}
		}
	}
	
	@Override
	public AbstractBulkIOPort getExternalPort(String ior, BulkIOType type) {
		return getExternalPort(ior, type, null);
	}

	@Override
	public AbstractBulkIOPort getExternalPort(String ior, BulkIOType type, String connectionID) {
		ConnectionKey key = new ConnectionKey(ior, type, connectionID);
		return connections.get(key);
	}

	public synchronized void dispose() {
		for (Connection connection : connections.values()) {
			connection.dispose();
		}
		connections.clear();
	}
}

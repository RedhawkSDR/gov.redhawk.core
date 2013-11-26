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


	private static class ConnectionKey {
		private String ior;
		private BulkIOType type;

		public ConnectionKey(String ior, BulkIOType type) {
			super();
			this.ior = ior;
			this.type = type;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((ior == null) ? 0 : ior.hashCode());   // SUPPRESS CHECKSTYLE AvoidInline
			result = prime * result + ((type == null) ? 0 : type.hashCode()); // SUPPRESS CHECKSTYLE AvoidInline
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(java.lang.Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof ConnectionKey)) {
				return false;
			}
			ConnectionKey other = (ConnectionKey) obj;
			if (ior == null) {
				if (other.ior != null) {
					return false;
				}
			} else if (!ior.equals(other.ior)) {
				return false;
			}
			if (type != other.type) {
				return false;
			}
			return true;
		}

	}

	private Map<ConnectionKey, Connection> connections = new ConcurrentHashMap<ConnectionKey, Connection>();

	@Override
	public void connect(String ior, BulkIOType type, updateSRIOperations internalPort) throws CoreException {
		if (ior == null || internalPort == null) {
			throw new IllegalArgumentException("Null ior or port implemention.");
		}
		ConnectionKey key = new ConnectionKey(ior, type);
		boolean initConnection = false;
		Connection connection;
		synchronized (this) {
			connection = connections.get(key);
			if (connection == null) {
				initConnection = true;
				connection = new Connection(type); // create stub
				connections.put(key, connection);
			}
			connection.registerDataReceiver(internalPort);
		}

		if (initConnection) {
			connection.connectPort(ior);
		}
	}

	@Override
	public void disconnect(String ior, BulkIOType type, updateSRIOperations internalPort) {
		if (ior == null || internalPort == null) {
			return;
		}
		ConnectionKey key = new ConnectionKey(ior, type);
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
		ConnectionKey key = new ConnectionKey(ior, type);
		return connections.get(key);
	}

	public synchronized void dispose() {
		for (Connection connection : connections.values()) {
			connection.dispose();
		}
		connections.clear();
	}
}

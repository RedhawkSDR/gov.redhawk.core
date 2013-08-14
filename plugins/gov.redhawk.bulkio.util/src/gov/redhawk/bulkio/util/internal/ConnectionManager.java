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
import gov.redhawk.bulkio.util.BulkIOUtilActivator;
import gov.redhawk.bulkio.util.IBulkIOPortConnectionManager;
import gov.redhawk.sca.util.OrbSession;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.omg.CORBA.Object;
import org.omg.CORBA.SystemException;
import org.omg.PortableServer.Servant;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import BULKIO.updateSRIOperations;
import CF.Port;
import CF.PortHelper;
import CF.PortPackage.InvalidPort;
import CF.PortPackage.OccupiedPort;

/**
 * 
 */
public enum ConnectionManager implements IBulkIOPortConnectionManager {
	INSTANCE;

	private static OrbSession session = OrbSession.createSession();

	private static class Connection {
		private Port port;
		private AbstractSriReceiver< ? > receiver;
		private Object ref;
		private String connectionId;

		public Connection(Port port, BulkIOType type) throws CoreException {
			this.port = port;
			receiver = BulkIOReceiverFactory.createReceiver(type);

			Servant servant = receiver.toServant();
			try {
				ref = session.getPOA().servant_to_reference(servant);
				connectionId = createConnectionID();
				this.port.connectPort(ref, connectionId);
			} catch (ServantNotActive e) {
				throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to register new shared connection.", e));
			} catch (WrongPolicy e) {
				throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to register new shared connection.", e));
			} catch (InvalidPort e) {
				throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to register new shared connection.", e));
			} catch (OccupiedPort e) {
				throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to register new shared connection.", e));
			} catch (SystemException e) {
				throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to register new shared connection.", e));
			}
		}

		private static String createConnectionID() {
			return System.getProperty("user.name", "user") + "_" + System.currentTimeMillis();
		}

		public void dispose() {
			try {
				if (port != null) {
					port.disconnectPort(connectionId);
					port = null;
				}
			} catch (InvalidPort e) {
				// PASS
			} catch (SystemException e) {
				// PASS
			}
			if (ref != null) {
				ref._release();
				ref = null;
			}
			if (receiver != null) {
				receiver.clear();
				receiver = null;
			}
		}
	}

	private Map<String, Connection> connections = new HashMap<String, Connection>();

	public void connect(String ior, updateSRIOperations internalPort) throws CoreException {
		connect(ior, BulkIOType.getType(internalPort), internalPort);
	}

	public synchronized void connect(String ior, BulkIOType type, updateSRIOperations internalPort) throws CoreException {
		if (ior == null || internalPort == null) {
			return;
		}
		Connection connection = connections.get(ior);
		if (connection == null) {
			Port port = PortHelper.narrow(session.getOrb().string_to_object(ior));
			connection = new Connection(port, type);
			connections.put(ior, connection);
		}
		connection.receiver.registerDataReceiver(internalPort);
	}

	public synchronized void disconnect(String ior, updateSRIOperations internalPort) {
		if (ior == null || internalPort == null) {
			return;
		}
		Connection connection = connections.get(ior);
		if (connection != null) {
			connection.receiver.deregisterDataReceiver(internalPort);
			if (connection.receiver.isEmpty()) {
				connection.dispose();
				connections.remove(ior);
			}
		}
	}

	public synchronized AbstractBulkIOPort getExternalPort(String ior) {
		Connection connection = connections.get(ior);
		if (connection != null) {
			return connection.receiver;
		}
		return null;
	}

	public synchronized void dispose() {
		for (Connection connection : connections.values()) {
			connection.dispose();
		}
		connections.clear();
	}
}

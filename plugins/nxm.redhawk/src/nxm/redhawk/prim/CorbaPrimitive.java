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
package nxm.redhawk.prim;

import gov.redhawk.sca.util.OrbSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mil.jpeojtrs.sca.util.DceUuidUtil;
import nxm.sys.inc.Commandable;
import nxm.sys.lib.Primitive;

import org.eclipse.core.runtime.CoreException;
import org.omg.CORBA.SystemException;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;

import CF.Port;
import CF.PortHelper;
import CF.Resource;
import CF.ResourceHelper;
import CF.PortPackage.InvalidPort;
import CF.PortPackage.OccupiedPort;

/**
 * This class connects to the specified CORBA host and receives the float data
 * and plots the data.
 */
public class CorbaPrimitive extends Primitive {
	/**
	 * @since 8.0
	 */
	public static final String A_PORT_NAME = "PORT_NAME";
	/**
	 * @since 8.0
	 */
	public static final String A_RESOURCE = "RESOURCE";
	/**
	 * @since 8.0
	 */
	public static final String A_PORT = "PORT";
	/**
	 * @since 8.0
	 */
	public static final String A_HOST = "HOST";
	/** The maximum number of times to retry the initial ORB connection */
	protected static final int MAX_RETRIES = 5;
	
	private OrbSession session = OrbSession.createSession();
	/** The list of port connections */
	private final List<ConnectionData> connections = Collections.synchronizedList(new ArrayList<ConnectionData>());
	/** the NameService helper */
	private NamingContextExt ncRef = null;

	private static class ConnectionData {
		private org.omg.CORBA.Object tie;
		private Port port;
		private final String dceUUID;
		private boolean connected = false;

		public ConnectionData(final org.omg.CORBA.Object tie, final Port port) {
			super();
			this.tie = tie;
			this.port = port;
			this.dceUUID = DceUuidUtil.createDceUUID();
		}

		public void connect() throws InvalidPort, OccupiedPort {
			if (!this.connected) {
				this.port.connectPort(this.tie, this.dceUUID);
				this.connected = true;
			}
		}

		public void disconnect() throws InvalidPort {
			if (this.connected) {
				this.port.disconnectPort(this.dceUUID);
				this.connected = false;
			}
		}

		public void release() {
			try {
				disconnect();
				if (tie != null) {
					tie._release();
				}
				if (port != null) {
					port._release();
				}
			} catch (final InvalidPort e) {
				// PASS Ignore errors
			} catch (final SystemException e) {
				// PASS
			} finally {
				this.port = null;
				this.tie = null;
			}
		}

		@Override
		public boolean equals(final Object obj) {
			if (obj instanceof ConnectionData) {
				final ConnectionData cd = (ConnectionData) obj;
				return cd.port._is_equivalent(this.port);
			} else {
				return false;
			}
		}
	}
	
	protected POA getPOA() throws CoreException {
		return session.getPOA();
	}

	/**
	 * Open is used as the constructor for NeXtMidas primitives.
	 */
	@Override
	public int open() {
		int ret = super.open();

		try {
			// Make the ORB connection - MA is the argument list
			final String host = this.MA.getU(CorbaPrimitive.A_HOST);
			final int port = this.MA.getL(CorbaPrimitive.A_PORT);

			// There are two cases here. First, we are given all the
			// information to connect to a host and resolve the port.
			// Second, we're given an IOR to the port. The IOR doesn't
			// require the NameService so skip all that setup.
			if (host != null && !host.equals("")) {
				// get the root naming context
				// Use NamingContextExt which is part of the Interoperable Naming
				// Service (INS) specification.
				final org.omg.CORBA.Object obj = session.getOrb().string_to_object("corbaloc::" + host + ":" + port);
				this.ncRef = NamingContextExtHelper.narrow(obj);
			} 
		} catch (final SystemException e) {
			this.M.error(e);
			ret = Commandable.ABORT;
		}

		return ret;
	}

	/** {@inheritDoc} */
	@Override
	public int close() {
		shutdownNonBlocking();
		return super.close();
	}

	/**
	 * @since 9.0
	 */
	protected void shutdownNonBlocking() {
		new Thread("Corba Shutdown") {
			public void run() {
				shutdown();
			}
		} .start();
	}

	/**
	 * This closes all connected ports and cleans up the CORBA connection(s).
	 */
	protected void shutdown() {
		// loop through all the ports and disconnect/release
		synchronized (this.connections) {
			for (final ConnectionData data : this.connections) {
				data.release();
			}
			this.connections.clear();
		}

		this.ncRef = null;

		// Shutdown the ORB connection
		if (this.session != null) {
			this.session.dispose();
			this.session = null;
		}
	}

	/**
	 * This method connects the given Tie object to the port from the arguments
	 * list. It will retry MAX_RETRIES times before giving up trying to connect.
	 *
	 * @param tie the Tie CORBA object. This is generic to allow any type of
	 *            connection.
	 * @return true if the port was successfully connected
	 */
	protected boolean connectPort(final org.omg.CORBA.Object newTie) {
		boolean retVal = false;
		if (newTie == null) {
			throw new IllegalStateException("New Tie is null");
		}

		int retry = 0;

		// Get the resource to connect to - MA is the argument list
		// Resource is the component you're trying to connect to
		final String name = this.MA.getU(CorbaPrimitive.A_RESOURCE);
		final String portName = this.MA.getU(CorbaPrimitive.A_PORT_NAME);

		while (retry < CorbaPrimitive.MAX_RETRIES) {
			try {
				org.omg.CORBA.Object portRef = null;

				// Only do this if we were told which Host to connect to
				if (this.ncRef != null) {
					// Find the Resource from the NameService
					final org.omg.CORBA.Object ref = this.ncRef.resolve(this.ncRef.to_name(name));
					final Resource res = ResourceHelper.narrow(ref);

					// Get the port from the resource - PORT_NAME is the name of the
					// port you want to connect to
					portRef = res.getPort(portName);
				} else {
					// If ncRef was null, we were passed an IOR for the
					// port. Use this to directly resolve the port.
					portRef = session.getOrb().string_to_object(portName);
				}

				final Port port = PortHelper.narrow(portRef);

				// Make a new connection from the port to the given object
				final ConnectionData data = new ConnectionData(newTie, port);

				if (!this.connections.contains(data)) {
					data.connect();
					// Store the connected port for later cleanup
					this.connections.add(data);
				}
				retVal = true;
				break;
			} catch (final Exception e) {
				if (++retry == CorbaPrimitive.MAX_RETRIES) {
					this.M.error(e);
				}
				try {
					Thread.sleep(1000); // SUPPRESS CHECKSTYLE MagicNumber
				} catch (final InterruptedException i) {
					// PASS
				}
			}
		}

		return retVal;
	}
}

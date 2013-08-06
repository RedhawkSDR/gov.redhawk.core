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

import java.lang.Thread.UncaughtExceptionHandler;

import nxm.redhawk.lib.RedhawkOptActivator;
import nxm.sys.inc.Commandable;
import nxm.sys.lib.Primitive;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.omg.CORBA.ORB;
import org.omg.CORBA.SystemException;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;

import CF.Port;
import CF.PortHelper;
import CF.Resource;
import CF.ResourceHelper;
import CF.PortSupplierPackage.UnknownPort;

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
	
	/** the NameService helper */
	private NamingContextExt ncRef = null;

	public ORB getOrb() {
		return session.getOrb();
	}

	public POA getPOA() throws CoreException {
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
		Thread shutdownThread = new Thread("Corba Shutdown") {
			public void run() {
				shutdown();
			}
		};
		shutdownThread.setDaemon(true);
		shutdownThread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				RedhawkOptActivator.log(new Status(Status.ERROR, RedhawkOptActivator.ID, "Error occured while shuting down Corba Primitive", e));
			}
		});
		shutdownThread.start();
	}

	/**
	 * This closes all connected ports and cleans up the CORBA connection(s).
	 */
	protected void shutdown() {
		if (ncRef != null) {
			ncRef._release();
			ncRef = null;
		}

		// Shutdown the ORB connection
		if (this.session != null) {
			this.session.dispose();
			this.session = null;
		}
	}

	/**
	 * @since 9.1
	 */
	protected Port getPort() throws NotFound, CannotProceed, InvalidName, UnknownPort {
		// Get the resource to connect to - MA is the argument list
		// Resource is the component you're trying to connect to
		final String name = this.MA.getU(CorbaPrimitive.A_RESOURCE);
		final String portName = this.MA.getU(CorbaPrimitive.A_PORT_NAME);
		
		// Only do this if we were told which Host to connect to
		if (this.ncRef != null) {
			// Find the Resource from the NameService
			final org.omg.CORBA.Object ref = this.ncRef.resolve(this.ncRef.to_name(name));
			final Resource res = ResourceHelper.narrow(ref);

			// Get the port from the resource - PORT_NAME is the name of the
			// port you want to connect to
			return PortHelper.narrow(res.getPort(portName));
		} else {
			// If ncRef was null, we were passed an IOR for the
			// port. Use this to directly resolve the port.
			return PortHelper.narrow(session.getOrb().string_to_object(portName));
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
	@Deprecated
	protected boolean connectPort(final org.omg.CORBA.Object newTie) {
		return false;
	}
}

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

import gov.redhawk.sca.util.ORBUtil;
import gov.redhawk.sca.util.OrbSession;

import java.lang.Thread.UncaughtExceptionHandler;

import nxm.redhawk.lib.RedhawkOptActivator;
import nxm.sys.lib.MidasException;
import nxm.sys.lib.Primitive;

import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.annotation.NonNull;
import org.omg.CORBA.SystemException;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

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


	/** {@inheritDoc} */
	@Override
	public int close() {
		shutdownNonBlocking();
		return super.close();
	}

	/**
	 * This is done in another background thread since any CORBA call(s) in {@link #shutdown()} can
	 * block forever. So we do not want to block the Primitive from closing in those cases.
	 * @since 9.0
	 */
	protected final void shutdownNonBlocking() {
		Thread shutdownThread = new Thread("CorbaPrimitive Shutdown for " + getID()) {
			@Override
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

	}

	/**
	 * @since 10.0
	 */
	@NonNull
	protected String getPortIOR() {
		// Get the resource to connect to - MA is the argument list
		// Resource is the component you're trying to connect to
		final String resourceName = this.MA.getU(CorbaPrimitive.A_RESOURCE);
		final String portName = this.MA.getU(CorbaPrimitive.A_PORT_NAME);

		// Make the ORB connection - MA is the argument list
		final String host = this.MA.getU(CorbaPrimitive.A_HOST);
		final int port = this.MA.getL(CorbaPrimitive.A_PORT);
		String retVal;

		if (resourceName != null && !resourceName.isEmpty() && host != null && !host.isEmpty() && port > 0) {
			OrbSession session = OrbSession.createSession();
			NamingContextExt ncRef = null;
			Port portRef = null;
			try {
				// There are two cases here. First, we are given all the
				// information to connect to a host and resolve the port.
				// Second, we're given an IOR to the port. The IOR doesn't
				// require the NameService so skip all that setup.
				// get the root naming context
				// Use NamingContextExt which is part of the Interoperable Naming
				// Service (INS) specification.
				final org.omg.CORBA.Object obj = session.getOrb().string_to_object("corbaloc::" + host + ":" + port);
				ncRef = NamingContextExtHelper.narrow(obj);

				// Find the Resource from the NameService
				final org.omg.CORBA.Object ref = ncRef.resolve(ncRef.to_name(name));
				final Resource res = ResourceHelper.narrow(ref);

				// Get the port from the resource - PORT_NAME is the name of the
				// port you want to connect to
				portRef = PortHelper.narrow(res.getPort(portName));
				retVal = portRef.toString();
			} catch (final SystemException e) {
				throw new MidasException("Failed to find Port.", e);
			} catch (UnknownPort e) {
				throw new MidasException("Failed to find Port.", e);
			} catch (NotFound e) {
				throw new MidasException("Failed to find Port.", e);
			} catch (CannotProceed e) {
				throw new MidasException("Failed to find Port.", e);
			} catch (InvalidName e) {
				throw new MidasException("Failed to find Port.", e);
			} finally {
				if (ncRef != null) {
					ORBUtil.release(ncRef);
				}
				if (portRef != null) {
					ORBUtil.release(portRef);
				}
				session.dispose();
			}
		} else {
			// If ncRef was null, we were passed an IOR for the
			// port. Use this to directly resolve the port.
			retVal = portName;
		}

		if (retVal == null) {
			throw new IllegalStateException("Failed to resolve port ior");
		}
		return retVal;
	}

}

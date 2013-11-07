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
package gov.redhawk.bulkio.util;

import gov.redhawk.bulkio.util.internal.ConnectionManager;
import gov.redhawk.sca.util.Debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import mil.jpeojtrs.sca.util.NamedThreadFactory;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.annotation.NonNull;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import BULKIO.updateSRIOperations;

/**
 * The activator class controls the plug-in life cycle
 */
public class BulkIOUtilActivator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "gov.redhawk.bulkio.util"; //$NON-NLS-1$

	private static final ExecutorService EXECUTOR_POOL = Executors.newSingleThreadExecutor(new NamedThreadFactory(BulkIOUtilActivator.class.getName()));
	private static final Debug DEBUG = new Debug(PLUGIN_ID, "PortFactory");

	// The shared instance
	private static BulkIOUtilActivator plugin;

	private ServiceTracker<IPortFactory, IPortFactory> portFactoryTracker;

	private IPortFactory deligatingFactory = new IPortFactory() {

		@Override
		public PortReference connect(String connectionID, String portIor, BulkIOType type, updateSRIOperations handler) throws CoreException {
			List<ServiceReference<IPortFactory>> refs = new ArrayList<ServiceReference<IPortFactory>>(Arrays.asList(portFactoryTracker.getServiceReferences()));
			Collections.sort(refs);

			String orbType = System.getProperty("gov.redhawk.bulkio.orbType", "default");

			IPortFactory factory = null;
			for (ServiceReference<IPortFactory> ref : refs) {
				if (orbType.equals(ref.getProperty("orbType"))) {
					factory = portFactoryTracker.getService(ref);
					break;
				}
			}
			if (factory == null) {
				if (DEBUG.enabled) {
					DEBUG.trace("WARNING: No factory of type {0} using 'default'", orbType);
				}
				orbType = "default";
				for (ServiceReference<IPortFactory> ref : refs) {
					if (orbType.equals(ref.getProperty("gov.redhawk.bulkio.orbType"))) {
						factory = portFactoryTracker.getService(ref);
						break;
					}
				}
			}
			if (factory != null) {
				if (DEBUG.enabled) {
					DEBUG.message("DEBUG: Creating factory of type {0}", orbType);
				}
				PortReference retVal = factory.connect(connectionID, portIor, type, handler);
				if (DEBUG.enabled) {
					DEBUG.message("DEBUG: SUCCESS Created factory of type {0}", orbType);
				}
				return retVal;
			}
			throw new CoreException(new Status(Status.ERROR, PLUGIN_ID, "No port factories available."));
		}
	};

	/**
	 * The constructor
	 */
	public BulkIOUtilActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		portFactoryTracker = new ServiceTracker<IPortFactory, IPortFactory>(context, IPortFactory.class, null);
		portFactoryTracker.open();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
		if (portFactoryTracker != null) {
			portFactoryTracker.close();
			portFactoryTracker = null;
		}
		Future< ? > future = EXECUTOR_POOL.submit(new Runnable() {

			@Override
			public void run() {
				ConnectionManager.INSTANCE.dispose();
			}

		});
		try {
			future.get(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// PASS
		} catch (ExecutionException e) {
			// PASS
		} catch (TimeoutException e) {
			// PASS
		}
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static BulkIOUtilActivator getDefault() {
		return plugin;
	}

	public static IBulkIOPortConnectionManager getBulkIOPortConnectionManager() {
		return ConnectionManager.INSTANCE;
	}

	/**
	 * @return
	 * @since 2.0
	 */
	@NonNull
	public IPortFactory getPortFactory() {
		return deligatingFactory;
	}

}

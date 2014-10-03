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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import mil.jpeojtrs.sca.util.NamedThreadFactory;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
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

	/**
	 * @since 2.0
	 */
	public static final String BULKIO_ORB_TYPE = "orbType";
	/**
	 * @since 2.0
	 */
	public static final String SYSTEM_PROPERTY_BULKIO_ORB_TYPE = "gov.redhawk.bulkio.orbType";

	private static final ExecutorService EXECUTOR_POOL = Executors.newSingleThreadExecutor(new NamedThreadFactory(BulkIOUtilActivator.class.getName()));
	private static final Debug DEBUG = new Debug(BulkIOUtilActivator.PLUGIN_ID, "PortFactory");

	// The shared instance
	private static BulkIOUtilActivator plugin;

	private ServiceTracker<IPortFactory, IPortFactory> portFactoryTracker;

	private final IPortFactory deligatingFactory = new IPortFactory() {

		@Override
		public PortReference connect(String connectionID, String portIor, BulkIOType type, updateSRIOperations handler) throws CoreException {
			IEclipsePreferences defaultNode = DefaultScope.INSTANCE.getNode(BulkIOUtilActivator.PLUGIN_ID);
			IEclipsePreferences node = InstanceScope.INSTANCE.getNode(BulkIOUtilActivator.PLUGIN_ID);
			String orbType = node.get(BulkIOUtilActivator.BULKIO_ORB_TYPE, defaultNode.get(BulkIOUtilActivator.BULKIO_ORB_TYPE, null));

			if (System.getProperty(BulkIOUtilActivator.SYSTEM_PROPERTY_BULKIO_ORB_TYPE, null) != null) {
				orbType = System.getProperty(BulkIOUtilActivator.SYSTEM_PROPERTY_BULKIO_ORB_TYPE, "default");
			}

			Map<String, IPortFactory> factories = getPortFactories();

			IPortFactory factory = factories.get(orbType);

			if (factory == null) {
				if (BulkIOUtilActivator.DEBUG.enabled) {
					BulkIOUtilActivator.DEBUG.trace("WARNING: No factory of type {0} using 'default'", orbType);
				}
				orbType = "default";
				factory = factories.get(orbType);
			}
			if (factory != null) {
				if (BulkIOUtilActivator.DEBUG.enabled) {
					BulkIOUtilActivator.DEBUG.message("DEBUG: Creating factory of type {0}", orbType);
				}
				PortReference retVal = factory.connect(connectionID, portIor, type, handler);
				if (BulkIOUtilActivator.DEBUG.enabled) {
					BulkIOUtilActivator.DEBUG.message("DEBUG: SUCCESS Created factory of type {0}", orbType);
				}
				return retVal;
			}
			throw new CoreException(new Status(IStatus.ERROR, BulkIOUtilActivator.PLUGIN_ID, "No port factories available."));
		}
	};

	/**
	 * The constructor
	 */
	public BulkIOUtilActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		BulkIOUtilActivator.plugin = this;
		portFactoryTracker = new ServiceTracker<IPortFactory, IPortFactory>(context, IPortFactory.class, null);
		portFactoryTracker.open();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		BulkIOUtilActivator.plugin = null;
		if (portFactoryTracker != null) {
			portFactoryTracker.close();
			portFactoryTracker = null;
		}
		Future< ? > future = BulkIOUtilActivator.EXECUTOR_POOL.submit(new Runnable() {

			@Override
			public void run() {
				ConnectionManager.INSTANCE.dispose();
			}

		});
		try {
			future.get(30, TimeUnit.SECONDS); // SUPPRESS CHECKSTYLE MAGIC NUMBER
		} catch (InterruptedException e) {
			// PASS
		} catch (ExecutionException e) {
			// PASS
		} catch (TimeoutException e) {
			// PASS
		}
	}

	/**
	 * @since 2.0
	 */
	public Map<String, IPortFactory> getPortFactories() {
		ServiceReference<IPortFactory>[] refsArray = portFactoryTracker.getServiceReferences();
		List<ServiceReference<IPortFactory>> refsList;
		if (refsArray != null) {
			refsList = Arrays.asList(refsArray);
		} else {
			refsList = Collections.emptyList();
		}
		List<ServiceReference<IPortFactory>> refs = new ArrayList<ServiceReference<IPortFactory>>(refsList);
		Collections.sort(refs);
		Map<String, IPortFactory> retVal = new HashMap<String, IPortFactory>();
		for (ServiceReference<IPortFactory> ref : refs) {
			retVal.put(String.valueOf(ref.getProperty(BulkIOUtilActivator.BULKIO_ORB_TYPE)), portFactoryTracker.getService(ref));
		}
		return Collections.unmodifiableMap(retVal);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static BulkIOUtilActivator getDefault() {
		return BulkIOUtilActivator.plugin;
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

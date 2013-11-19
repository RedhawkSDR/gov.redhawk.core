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
package gov.redhawk.configurator;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;

public class RedhawkConfiguratorActivator implements BundleActivator {
	public static final String PLUGIN_ID = "gov.redhawk.configurator";

	private static final String BUNDLE_FILE_EXTENSION = ".jar";
	private static final String OSSIEHOME_PLUGIN_DIR = "lib";
	private static final String OSSIEHOME_ENV = "OSSIEHOME";
	private static final MessageFormat OUT_FORMAT = new MessageFormat("[{0,date,yyyy-MM-dd} {0,time,HH:mm:ss}] " + PLUGIN_ID + "  ERROR: {1}\n{2}");
	private static BundleContext context;
	private final List<Bundle> installedBundles = new ArrayList<Bundle>();
	private ServiceTracker<LogService, LogService> logServiceTracker;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(final BundleContext bundleContext) throws Exception {
		RedhawkConfiguratorActivator.context = bundleContext;
		this.logServiceTracker = new ServiceTracker<LogService, LogService>(bundleContext, LogService.class, null);
		this.logServiceTracker.open();
		this.installOSSIEHomeLibPlugins();
	}

	private void installOSSIEHomeLibPlugins() {
		final String ossieHome = System.getenv(OSSIEHOME_ENV);
		if (ossieHome != null) {
			final File ossieHomeDir = new File(ossieHome);
			if (ossieHomeDir.exists() && ossieHomeDir.isDirectory()) {
				final File libDir = new File(ossieHomeDir, OSSIEHOME_PLUGIN_DIR);
				this.installPluginDirectory(libDir);
			}
		}
	}

	public void installPluginDirectory(final File directory) {
		File[] files = directory.listFiles();
		if (files != null) {
			for (final File file : files) {
				if (file.getName().endsWith(BUNDLE_FILE_EXTENSION)) {
					this.installBundle(file);
				} else if (file.isDirectory() && new File(new File(file, "META-INF"), "MANIFEST.MF").exists()) {
					this.installBundle(file);
				}
			}
		}
	}

	private void installBundle(final File file) {
		try {
			this.installedBundles.add(context.installBundle(file.toURI().toURL().toString()));
		} catch (final BundleException e) {
			switch (e.getType()) {
			case BundleException.DUPLICATE_BUNDLE_ERROR:
				// Ignore duplicate bundle errors
				break;
			case BundleException.MANIFEST_ERROR:
			case BundleException.ACTIVATOR_ERROR:
			case BundleException.INVALID_OPERATION:
			case BundleException.NATIVECODE_ERROR:
			case BundleException.RESOLVE_ERROR:
			case BundleException.SECURITY_ERROR:
			case BundleException.START_TRANSIENT_ERROR:
			case BundleException.STATECHANGE_ERROR:
			case BundleException.UNSPECIFIED:
			case BundleException.UNSUPPORTED_OPERATION:
			default:
				this.logError("Invalid bundle: " + file.getAbsolutePath(), e);
			}
		} catch (final Exception e) { // SUPPRESS CHECKSTYLE Logged Catch all exception
			this.logError("Problems installing bundle: " + file.getAbsolutePath(), e);
		}
	}

	public void uninstallBundles() {
		for (final Bundle b : this.installedBundles) {
			if (b.getState() != Bundle.UNINSTALLED) {
				try {
					b.uninstall();
				} catch (final Exception e) { // SUPPRESS CHECKSTYLE Logged Catch all exception
					this.logError("Problems uninstalling bundle: " + b.getSymbolicName(), e);
				}
			}
		}
		this.installedBundles.clear();
	}

	private void logError(final String message, final Throwable e) {
		final LogService service = (LogService) this.logServiceTracker.getService();
		if (service != null) {
			service.log(LogService.LOG_ERROR, message, e);
		} else {
			final Object[] arguments = new Object[] { new Date(), message, e };
			final StringBuffer result = new StringBuffer();
			OUT_FORMAT.format(arguments, result, null);
			// We need to use sysErr at this point since we have no other way to log the error.
			// CHECKSTYLE:OFF
			System.err.println(result);
			// CHECKSTYLE:ON
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(final BundleContext bundleContext) throws Exception {
		// We probably shouldn't be uninstalling the bundles since the OSGI framework will do that for us anyway
		//		uninstallBundles();
		if (this.logServiceTracker != null) {
			this.logServiceTracker.close();
		}
		this.logServiceTracker = null;
		RedhawkConfiguratorActivator.context = null;
	}

}

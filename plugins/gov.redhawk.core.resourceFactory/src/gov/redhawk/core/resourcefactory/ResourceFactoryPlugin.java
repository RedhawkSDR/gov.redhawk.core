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
package gov.redhawk.core.resourcefactory;

import gov.redhawk.core.internal.resourcefactory.ResourceFactoryRegistry;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

public class ResourceFactoryPlugin extends Plugin {

	public static final String ID = "gov.redhawk.core.resourceFactory";
	private static ResourceFactoryPlugin plugin;

	@Override
	public void start(final BundleContext bundleContext) throws Exception {
		super.start(bundleContext);
		ResourceFactoryPlugin.plugin = this;
	}

	@Override
	public void stop(final BundleContext bundleContext) throws Exception {
		super.stop(bundleContext);
		ResourceFactoryRegistry.INSTANCE.dispose();
		ResourceFactoryPlugin.plugin = null;
	}

	public IResourceFactoryRegistry getResourceFactoryRegistry() {
		return ResourceFactoryRegistry.INSTANCE;
	}

	public static ResourceFactoryPlugin getDefault() {
		return ResourceFactoryPlugin.plugin;
	}

	/**
	 * Log an error
     * @since 2.0
     * @deprecated Use {@link #logError(String, Throwable)}
     */
	@Deprecated
	public static void log(String msg, Throwable e) {
		logError(msg, e);
	}

	/**
	 * @since 3.2
	 */
	public static void logError(String msg, Throwable e) {
		ResourceFactoryPlugin instance = plugin;
		if (instance != null) {
			instance.getLog().log(new Status(Status.ERROR, ID, msg, e));
		}
	}
}

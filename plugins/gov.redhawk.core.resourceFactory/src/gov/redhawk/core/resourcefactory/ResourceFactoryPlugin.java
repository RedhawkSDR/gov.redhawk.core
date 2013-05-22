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
import org.osgi.framework.BundleContext;

public class ResourceFactoryPlugin extends Plugin {

	public static final String ID = "gov.redhawk.core.resourceFactory";
	private static ResourceFactoryPlugin plugin;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(final BundleContext bundleContext) throws Exception {
		super.start(bundleContext);
		ResourceFactoryPlugin.plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(final BundleContext bundleContext) throws Exception {
		super.stop(bundleContext);
		ResourceFactoryPlugin.plugin = null;
	}

	public IResourceFactoryRegistry getResourceFactoryRegistry() {
		return ResourceFactoryRegistry.INSTANCE;
	}

	public static ResourceFactoryPlugin getDefault() {
		return ResourceFactoryPlugin.plugin;
	}
}

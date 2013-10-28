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
package gov.redhawk.bulkio.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class BulkIOUIActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "gov.redhawk.bulkio.ui"; //$NON-NLS-1$

	// The shared instance
	private static BulkIOUIActivator plugin;

	/**
	 * The constructor
	 */
	public BulkIOUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see 
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 * 
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		BulkIOUIActivator.plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see 
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 * 
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		BulkIOUIActivator.plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static BulkIOUIActivator getDefault() {
		return BulkIOUIActivator.plugin;
	}

}

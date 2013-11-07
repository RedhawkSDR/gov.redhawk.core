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
package gov.redhawk.bulkio.orb.omni.internal;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * 
 */
public class BulkIOOmniActivator extends Plugin {
	public static final String PLUGIN_ID = "gov.redhawk.bulkio.orb.omni";
	private static BulkIOOmniActivator instance;
	
	/*
	 * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		instance = this;
		super.start(context);
	}
	
	/**
	 * @return the instance
	 */
	public static BulkIOOmniActivator getInstance() {
		return instance;
	}
	
	/*
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		instance = null;
	}
	
}

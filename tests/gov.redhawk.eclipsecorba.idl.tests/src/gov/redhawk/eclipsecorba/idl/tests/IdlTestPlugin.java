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
package gov.redhawk.eclipsecorba.idl.tests;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * 
 */
public class IdlTestPlugin extends Plugin {

	private static IdlTestPlugin instance;

	/**
	 * 
	 */
	public IdlTestPlugin() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		IdlTestPlugin.instance = this;
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		super.stop(context);
		IdlTestPlugin.instance = null;
	}

	public static IdlTestPlugin getDefault() {
		return IdlTestPlugin.instance;
	}

}

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
package gov.redhawk.sca.util.internal;

import org.eclipse.core.runtime.Plugin;
import org.jacorb.JacorbActivator;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * 
 */
public class ScaUtilPluginActivator extends Plugin implements BundleActivator {

	public static final String ID = "gov.redhawk.sca.util";
	private static ScaUtilPluginActivator instance;

	@Override
	public void start(final BundleContext context) throws Exception {
		instance = this;
		super.start(context);
		JacorbActivator.getDefault().init();
	}

	public static ScaUtilPluginActivator getDefault() {
		return instance;
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		super.stop(context);
		instance = null;
	}

	/**
	 * Returns the bundle id of the bundle that contains the provided object, or
	 * <code>null</code> if the bundle could not be determined.
	 */
	public String getBundleId(final Object object) {
		if (object == null) {
			return null;
		}
		final Bundle source = FrameworkUtil.getBundle(object.getClass());
		if (source != null && source.getSymbolicName() != null) {
			return source.getSymbolicName();
		}
		return null;
	}
}

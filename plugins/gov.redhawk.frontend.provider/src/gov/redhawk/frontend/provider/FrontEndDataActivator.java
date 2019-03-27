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
package gov.redhawk.frontend.provider;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.framework.BundleContext;

import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.sca.util.ScopedPreferenceAccessor;

public class FrontEndDataActivator extends Plugin {

	/**
	 * @since 2.0
	 */
	public static final String ID = "gov.redhawk.frontend.provider";

	private static FrontEndDataActivator instance;

	private final ScopedPreferenceAccessor preferenceAccessor = new ScopedPreferenceAccessor(InstanceScope.INSTANCE, ScaModelPlugin.ID);

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		FrontEndDataActivator.instance = this;
	}

	public static FrontEndDataActivator getInstance() {
		return FrontEndDataActivator.instance;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		FrontEndDataActivator.instance = null;
	}

	/**
	 * @since 2.0
	 */
	public ScopedPreferenceAccessor getPreferenceAccessor() {
		return this.preferenceAccessor;
	}

}

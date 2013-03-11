/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.sca.ui.singledomain;

import gov.redhawk.sca.ui.ScaUiPlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class ScaSingleDomainPlugin extends AbstractUIPlugin {
	
	// The plug-in ID
	/** The Constant PLUGIN_ID. */
	public static final String PLUGIN_ID = "gov.redhawk.sca.ui.singledomain";

	// The shared instance
	/** The plugin. */
	private static ScaSingleDomainPlugin plugin;

	/**
	 * The constructor.
	 */
	public ScaSingleDomainPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		ScaSingleDomainPlugin.plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		ScaSingleDomainPlugin.plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 * 
	 * @return the shared instance
	 */
	public static ScaSingleDomainPlugin getDefault() {
		return ScaSingleDomainPlugin.plugin;
	}

	/**
	 * Log error.
	 * 
	 * @param msg the msg
	 * @param e the e
	 */
	public static void logError(final String msg, final Throwable e) {
		ScaUiPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, msg, e));
	}

	/**
	 * Log error.
	 * 
	 * @param msg the msg
	 * @param e the e
	 * @since 5.0
	 */
	public static void logInfo(final String msg) {
		ScaUiPlugin.getDefault().getLog().log(new Status(IStatus.INFO, ScaUiPlugin.PLUGIN_ID, msg));
	}

}

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
package gov.redhawk.waveformviewer.ui;


import gov.redhawk.sca.ScaPlugin;

import java.util.Properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class WaveformProjectPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "gov.redhawk.ui.waveformviewer";

	// The shared instance
	private static WaveformProjectPlugin plugin;

	/**
	 * The constructor
	 */
	public WaveformProjectPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		WaveformProjectPlugin.plugin = this;
		ScaPlugin.getDefault().start(context);
		final String initialHost = "org.omg.CORBA.ORBInitialHost";
		final String initialPort = "org.omg.CORBA.ORBInitialPort";
		final String host = "localhost";
		final String port = "2809";
		final Properties properties = new Properties();
		properties.put(initialHost, host);
		properties.put(initialPort, port);
		// TODO Start/Get the SCA service
		//		ScaPlugin.getDefault().addScaService(properties);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		WaveformProjectPlugin.plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static WaveformProjectPlugin getDefault() {
		return WaveformProjectPlugin.plugin;
	}

	/**
	 * Log error.
	 * 
	 * @param msg the msg
	 * @param e the e
	 */
	public static void logError(final String msg, final Throwable e) {
		WaveformProjectPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, WaveformProjectPlugin.PLUGIN_ID, msg, e));
	}
}

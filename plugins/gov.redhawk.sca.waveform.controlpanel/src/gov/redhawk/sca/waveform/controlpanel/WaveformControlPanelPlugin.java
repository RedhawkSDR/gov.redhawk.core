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

package gov.redhawk.sca.waveform.controlpanel;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class WaveformControlPanelPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "gov.redhawk.sca.waveform.controlpanel"; //$NON-NLS-1$

	// The shared instance
	private static WaveformControlPanelPlugin plugin;
	
	/**
	 * The constructor
	 */
	public WaveformControlPanelPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static WaveformControlPanelPlugin getDefault() {
		return plugin;
	}
	
	public static void logError(final String msg, final Throwable e) {
		WaveformControlPanelPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, WaveformControlPanelPlugin.PLUGIN_ID, msg, e));
	}

	public static void logWarning(String msg) {
		WaveformControlPanelPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, WaveformControlPanelPlugin.PLUGIN_ID, msg));
    }

}

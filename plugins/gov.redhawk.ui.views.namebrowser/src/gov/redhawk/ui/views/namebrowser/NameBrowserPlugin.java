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
package gov.redhawk.ui.views.namebrowser;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @since 1.1
 */
public class NameBrowserPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "gov.redhawk.ui.views.namebrowser";

	public static final String ADD = "add";
	public static final String CONNECT = "connect";
	public static final String CONTEXT = "context";
	public static final String DISCONNECT = "disconnect";
	public static final String NAMESERVER = "nameserver";
	public static final String OBJECT = "object";

	// The shared instance
	private static NameBrowserPlugin plugin;

	/**
	 * The constructor
	 */
	public NameBrowserPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext )
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		NameBrowserPlugin.plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext )
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		NameBrowserPlugin.plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static NameBrowserPlugin getDefault() {
		return NameBrowserPlugin.plugin;
	}

	@Override
	protected ImageRegistry createImageRegistry() {
		final ImageRegistry registry = super.createImageRegistry();
		registry.put(ADD, imageDescriptorFromPlugin(PLUGIN_ID, "icons/add.png"));
		registry.put(CONNECT, imageDescriptorFromPlugin(PLUGIN_ID, "icons/connect.png"));
		registry.put(DISCONNECT, imageDescriptorFromPlugin(PLUGIN_ID, "icons/disconnect.png"));
		registry.put(CONTEXT, imageDescriptorFromPlugin(PLUGIN_ID, "icons/NSContext.gif"));
		registry.put(NAMESERVER, imageDescriptorFromPlugin(PLUGIN_ID, "icons/nameServer.gif"));
		registry.put(OBJECT, imageDescriptorFromPlugin(PLUGIN_ID, "icons/NSObject.gif"));
		return registry;
	}

	/**
	 * Log error.
	 * 
	 * @param msg the msg
	 * @param e the e
	 */
	public static void logError(final String msg, final Throwable e) {
		NameBrowserPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, NameBrowserPlugin.PLUGIN_ID, msg, e));
	}
}

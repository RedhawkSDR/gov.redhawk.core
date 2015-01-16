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
package gov.redhawk.ui.views.monitor;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class MonitorViewPlugin extends Plugin {

	public static final String PLUGIN_ID = "gov.redhawk.ui.views.monitor";

	private static MonitorViewPlugin plugin;

	public MonitorViewPlugin() {
	}
	
	public static MonitorViewPlugin getDefault() {
		return MonitorViewPlugin.plugin;
	}

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		MonitorViewPlugin.plugin = this;
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		MonitorViewPlugin.plugin = null;
		super.stop(context);
	}

}

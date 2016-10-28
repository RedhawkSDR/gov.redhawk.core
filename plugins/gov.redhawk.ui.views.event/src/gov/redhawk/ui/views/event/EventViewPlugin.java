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
package gov.redhawk.ui.views.event;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class EventViewPlugin extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "gov.redhawk.ui.views.event";
	
	private static EventViewPlugin plugin;
	
	public EventViewPlugin() {
	}
	
	public static EventViewPlugin getDefault() {
		return EventViewPlugin.plugin;
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		EventViewPlugin.plugin = this;
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		EventViewPlugin.plugin = null;
		super.stop(context);
	}

}

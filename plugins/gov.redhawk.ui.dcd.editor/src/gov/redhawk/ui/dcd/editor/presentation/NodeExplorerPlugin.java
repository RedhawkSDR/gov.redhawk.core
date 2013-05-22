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
package gov.redhawk.ui.dcd.editor.presentation;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class NodeExplorerPlugin extends AbstractUIPlugin {

	public static final String ID = "gov.redhawk.ui.dcd.editor";

	private static NodeExplorerPlugin instance;

	public NodeExplorerPlugin() {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		NodeExplorerPlugin.instance = this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		super.stop(context);
		NodeExplorerPlugin.instance = null;
	}

	public static NodeExplorerPlugin getDefault() {
		return NodeExplorerPlugin.instance;
	}

	public static String getPluginID() {
		return NodeExplorerPlugin.getDefault().getBundle().getSymbolicName();
	}

	public static String getPluginId() {
		return NodeExplorerPlugin.getDefault().getBundle().getSymbolicName();
	}
}

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
package gov.redhawk.ui.sad.editor.presentation;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class SadExplorerPlugin extends AbstractUIPlugin {

	public static final String ID = "gov.redhawk.ui.sad.editor";

	private static SadExplorerPlugin instance;

	public SadExplorerPlugin() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		SadExplorerPlugin.instance = this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		super.stop(context);
		SadExplorerPlugin.instance = null;
	}

	public static SadExplorerPlugin getDefault() {
		return SadExplorerPlugin.instance;
	}

	public static String getPluginID() {
		return SadExplorerPlugin.getDefault().getBundle().getSymbolicName();
	}
}

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
package gov.redhawk.diagram.activator;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class PluginActivator extends AbstractUIPlugin {

	public static final String ID = "gov.redhawk.diagram"; //$NON-NLS-1$

	private static PluginActivator ourInstance;

	public PluginActivator() {
	}

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		PluginActivator.ourInstance = this;
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		PluginActivator.ourInstance = null;
		super.stop(context);
	}

	public static PluginActivator getDefault() {
		return PluginActivator.ourInstance;
	}
	
	/**
	 * @since 4.0
	 */
	public ImageDescriptor getImageDescriptor(final String string) {
		ImageDescriptor desc = getImageRegistry().getDescriptor(string);
		if (desc == null) {
			desc = AbstractUIPlugin.imageDescriptorFromPlugin(ID, string);
			if (desc != null) {
				getImageRegistry().put(string, desc);
			}
		}
		return desc;
	}

	/**
	 * @since 4.0
	 */
	public Image getImage(final String string) {
		// Ensure it is in the registry first
		getImageDescriptor(string);
		return getImageRegistry().get(string);
	}
}

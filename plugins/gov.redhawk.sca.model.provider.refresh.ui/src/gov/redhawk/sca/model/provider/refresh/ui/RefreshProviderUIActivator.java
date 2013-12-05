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
package gov.redhawk.sca.model.provider.refresh.ui;

import gov.redhawk.sca.model.provider.refresh.RefreshProviderPlugin;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class RefreshProviderUIActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "gov.redhawk.sca.model.provider.refresh.ui"; //$NON-NLS-1$

	// The shared instance
	private static RefreshProviderUIActivator plugin;

	private IPreferenceStore providerPreferenceStore;

	private Object lastId;

	/**
	 * The constructor
	 */
	public RefreshProviderUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		RefreshProviderUIActivator.plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		RefreshProviderUIActivator.plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RefreshProviderUIActivator getDefault() {
		return RefreshProviderUIActivator.plugin;
	}

	/**
	 * @since 3.0
	 */
	public IPreferenceStore getRefreshProviderPreferenceStore() {
		if (this.providerPreferenceStore == null) {
			this.providerPreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, RefreshProviderPlugin.getInstance().getBundle().getSymbolicName());
		}
		return this.providerPreferenceStore;
	}

	public ImageDescriptor getImageDescriptor(final String string) {
		ImageDescriptor desc = getImageRegistry().getDescriptor(string);
		if (desc == null) {
			desc = AbstractUIPlugin.imageDescriptorFromPlugin(RefreshProviderUIActivator.PLUGIN_ID, string);
			if (desc != null) {
				getImageRegistry().put(string, desc);
			}
		}
		return desc;
	}

}

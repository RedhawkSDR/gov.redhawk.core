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
package gov.redhawk.frontend.ui;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.ui.filters.AdvancedPropertiesExtensibleFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class FrontEndUIActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "gov.redhawk.frontend.ui"; //$NON-NLS-1$

	// The shared instance
	private static FrontEndUIActivator plugin;
	
	/**
	 * The constructor
	 */
	public FrontEndUIActivator() {
	}
	
	public static List<String> supportedTunerTypes = new ArrayList<String>(Arrays.asList(
			new String[] {
				FRONTEND.TUNER_TYPE_CHANNELIZER.value,
				FRONTEND.TUNER_TYPE_DDC.value,
				FRONTEND.TUNER_TYPE_RX.value,
				FRONTEND.TUNER_TYPE_RX_DIGITIZER.value,
				FRONTEND.TUNER_TYPE_RX_DIGITIZER_CHANNELIZER.value}
		));

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		if (ScaUiPlugin.getDefault() != null) {
			AdvancedPropertiesExtensibleFilter.addSubFilter(new org.eclipse.jface.viewers.IFilter() {

				@Override
				public boolean select(Object toTest) {
					if (toTest instanceof TunerStatus) {
						return false;
					}
					return true;
				}
				
			});
		}
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
	public static FrontEndUIActivator getDefault() {
		return plugin;
	}

}

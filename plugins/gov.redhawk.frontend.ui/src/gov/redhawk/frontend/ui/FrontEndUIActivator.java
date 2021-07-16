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

import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.UnallocatedTunerContainer;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.ui.filters.AdvancedPropertiesExtensibleFilter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class FrontEndUIActivator extends AbstractUIPlugin {

	/**
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "gov.redhawk.frontend.ui"; //$NON-NLS-1$

	/**
	 * The single activator instance for the plugin
	 */
	private static FrontEndUIActivator plugin;

	public FrontEndUIActivator() {
	}

	public static final List<String> SUPPORTED_TUNER_TYPES = getSupportedTuner();

	private static List<String> getSupportedTuner() {
		List<String> supportedTunerTypes = new ArrayList<String>();
		Collections.addAll(supportedTunerTypes, 
			FRONTEND.TUNER_TYPE_RX.value,
			FRONTEND.TUNER_TYPE_RX_ARRAY.value,
			FRONTEND.TUNER_TYPE_ABOT.value,
			FRONTEND.TUNER_TYPE_ARDC.value,
			FRONTEND.TUNER_TYPE_DBOT.value,
			FRONTEND.TUNER_TYPE_RDC.value,
			FRONTEND.TUNER_TYPE_SRDC.value,
			FRONTEND.TUNER_TYPE_DRDC.value,
			FRONTEND.TUNER_TYPE_TX_ARRAY.value,
			FRONTEND.TUNER_TYPE_TX.value,
			FRONTEND.TUNER_TYPE_TDC.value);
		return Collections.unmodifiableList(supportedTunerTypes);
	}

	public static enum AllocationMode {
		TUNER,
		LISTENER
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		FrontEndUIActivator.plugin = this;
		if (ScaUiPlugin.getDefault() != null) {
			AdvancedPropertiesExtensibleFilter.addSubFilter(toTest -> {
				return !((toTest instanceof TunerStatus) || (toTest instanceof ListenerAllocation) || (toTest instanceof TunerContainer)
					|| (toTest instanceof UnallocatedTunerContainer));
			});
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		FrontEndUIActivator.plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * @return the shared instance
	 */
	public static FrontEndUIActivator getDefault() {
		return FrontEndUIActivator.plugin;
	}

	/**
	 * @since 1.1
	 */
	public ImageDescriptor getImageDescriptor(String path) {
		URL url = FileLocator.find(getBundle(), new Path(path), null);
		return ImageDescriptor.createFromURL(url);
	}
}

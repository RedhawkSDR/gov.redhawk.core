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
package gov.redhawk.prf.ui.wizard;

import gov.redhawk.prf.ui.PrfUiPlugin;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.PendingUpdateAdapter;

/**
 * @since 3.1
 * 
 */
public class PropertiesPendingUpdateAdapter extends PendingUpdateAdapter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLabel(final Object o) {
		return "Loading...";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImageDescriptor getImageDescriptor(final Object object) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(PrfUiPlugin.PLUGIN_ID, "icons/SdrRoot.gif");
	}
}

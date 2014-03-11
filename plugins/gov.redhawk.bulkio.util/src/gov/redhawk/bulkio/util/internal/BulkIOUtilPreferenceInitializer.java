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
package gov.redhawk.bulkio.util.internal;

import gov.redhawk.bulkio.util.BulkIOUtilActivator;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

/**
 * 
 */
public class BulkIOUtilPreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * 
	 */
	public BulkIOUtilPreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	@Override
	public void initializeDefaultPreferences() {
		String defaultOrbType = System.getProperty(BulkIOUtilActivator.SYSTEM_PROPERTY_BULKIO_ORB_TYPE, "default");

		IEclipsePreferences defaultNode = DefaultScope.INSTANCE.getNode(BulkIOUtilActivator.PLUGIN_ID);
		defaultNode.put(BulkIOUtilActivator.BULKIO_ORB_TYPE, defaultOrbType);
	}

}

/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package gov.redhawk.logging.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import gov.redhawk.logging.ui.LoggingUiPlugin;

public class LoggingPreferenceInitializer extends AbstractPreferenceInitializer {
	public static final String SHOW_LOG_CONFIG_WARNING = "showLogConfigWarning";

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = LoggingUiPlugin.getDefault().getPreferenceStore();

		store.setDefault(LoggingPreferenceInitializer.SHOW_LOG_CONFIG_WARNING, true);
	}

}

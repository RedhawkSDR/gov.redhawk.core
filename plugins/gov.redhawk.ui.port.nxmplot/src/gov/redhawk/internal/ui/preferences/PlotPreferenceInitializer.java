/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.internal.ui.preferences;

import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.preferences.BulkIOPreferences;
import gov.redhawk.ui.port.nxmplot.preferences.FftPreferences;
import gov.redhawk.ui.port.nxmplot.preferences.PlotPreferences;
import gov.redhawk.ui.port.nxmplot.preferences.Preference;
import gov.redhawk.ui.port.nxmplot.preferences.SddsPreferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Class used to initialize default preference values.
 */
public class PlotPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = PlotActivator.getDefault().getPreferenceStore();

		for (Preference< ? > p : BulkIOPreferences.getAllPreferences()) {
			p.setDefaultValue(store);
		}

		for (Preference< ? > p : FftPreferences.getAllPreferences()) {
			p.setDefaultValue(store);
		}

		for (Preference< ? > p : PlotPreferences.getAllPreferences()) {
			p.setDefaultValue(store);
		}
		
		for (Preference< ? > p : SddsPreferences.getAllPreferences()) {
			p.setDefaultValue(store);
		}
	}

}

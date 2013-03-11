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
package gov.redhawk.sca.ui.preferences;

import gov.redhawk.sca.ui.ScaUiPlugin;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;

/**
 * 
 */
public class RedhawkPreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * 
	 */
	public RedhawkPreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initializeDefaultPreferences() {
		ScaUiPlugin.getDefault()
		        .getPreferenceStore()
		        .setDefault(RedhawkUIPreferenceConstants.CREATE_WAVEFORM_LAUNCH_CONFIGURATION, MessageDialogWithToggle.PROMPT);
	}

}

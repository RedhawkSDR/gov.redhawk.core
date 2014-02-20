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
package gov.redhawk.ui.port.nxmplot.preferences;

import java.util.List;

/**
 * @since 4.4
 */
public class FileNxmBlockPreferences {

	/** prevent instantiation as this class only contains constants and/or utility methods. */
	private FileNxmBlockPreferences() {
	}

	public static List<Preference< ? >> getAllPreferences() {
		return Preference.gettAllPreferencesFor(FileNxmBlockPreferences.class);
	}

}

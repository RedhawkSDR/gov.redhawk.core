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
public class SddsPreferences {
	public static final Preference<String> BYTE_ORDER = new Preference<String>("sddsBlock.byteOrder", "NATIVE");
	public static final Preference<Integer> PIPE_SIZE = new Preference<Integer>("sddsBlock.pipeSize", 0);
	public static final Preference<Integer> VLAN = new Preference<Integer>("sddsBlock.vlan", 0);
	public static final Preference<String> MCAST_ADDRESS = new Preference<String>("sddsBlock.mcastAddress", "");
	public static final Preference<Boolean> MCAST_ADDRESS_OVERRIDE = new Preference<Boolean>("sddsBlock.mcastAddress.override", false);
	public static final Preference<Integer> PORT = new Preference<Integer>("sddsBlock.port", 29495);
	public static final Preference<String> OUTPUT_FORMAT = new Preference<String>("sddsBlock.outputFormat", "SI");
	public static final Preference<String> INTERFACE_NAME = new Preference<String>("sddsBlock.interfaceName", "");
	public static final Preference<Boolean> INTERFACE_NAME_OVERRIDE = new Preference<Boolean>("sddsBlock.interfaceName.override", false);

	public static List<Preference< ? >> getAllPreferences() {
		return Preference.gettAllPreferencesFor(SddsPreferences.class);
	}

	private SddsPreferences() {

	}

}

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
package gov.redhawk.sca.preferences;

import gov.redhawk.sca.ScaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * The Class ScaPreferenceConstants.
 */
public final class ScaPreferenceConstants {

	/** The Constant SCA_PREFERENCE_PREFIX. */
	public static final String SCA_PREFERENCE_NODE = ScaPlugin.getPluginId();

	/**
	 * The Constant SCA_CORBA_CONNECT_ALL_PREFERENCE - flag to connect to all
	 * domains on startup
	 */
	public static final String SCA_CORBA_AUTOCONNECT_PREFERENCE = "CORBAConnectAll";

	/**
	 * @since 2.2
	 */
	public static final String SCA_DEFAULT_NAMING_SERVICE = "namingService";

	/**
	 * @since 6.0
	 */
	public static final String SCA_DOMAIN_WAVEFORMS_SEARCH_PATH = "domainWaveformsSearchPath";

	/**
	 * @since 6.0
	 */
	public static String createPath(final String[] items) {
		final StringBuffer path = new StringBuffer("");

		for (int i = 0; i < items.length; i++) {
			path.append(items[i]);
			path.append(File.pathSeparator);
		}
		return path.toString();
	}

	/**
	 * @since 6.0
	 */
	public static String[] parsePath(final String stringList) {
		final StringTokenizer st = new StringTokenizer(stringList, File.pathSeparator + "\n\r");
		final ArrayList<Object> v = new ArrayList<Object>();
		while (st.hasMoreElements()) {
			v.add(st.nextElement());
		}
		return v.toArray(new String[v.size()]);
	}

	/**
	 * The Constant SCA_CORBA_DOMAIN_PREFERENCE - the list of CORBA domains to
	 * connect to on startup
	 * @deprecated Use the ScaDomainManagerRegistry instead
	 */
	@Deprecated
	public static final String SCA_CORBA_DOMAIN_LIST_PREFERENCE = "CORBADomainList";

	/**
	 * Hidden constructor.
	 */
	private ScaPreferenceConstants() {
	}
}

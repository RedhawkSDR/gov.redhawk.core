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
package gov.redhawk.sca.preferences;

import gov.redhawk.sca.ScaPlugin;

import java.util.ArrayList;

public class ScaPreferenceUtil {
	private ScaPreferenceUtil() {

	}

	public static boolean shouldAutoConnect() {
		return ScaPlugin.getDefault().getScaPreferenceAccessor().getBoolean(ScaPreferenceConstants.SCA_CORBA_AUTOCONNECT_PREFERENCE);
	}

	/**
	 * @deprecated Use ScaDomainManagerRegistry instead
	 * @return
	 */
	@Deprecated
	public static ScaDomainConnectionDef[] loadDomainConnections() {
		return ScaPreferenceUtil.fromPreferenceStoreString(ScaPlugin.getDefault().getScaPreferenceAccessor()
		        .getString(ScaPreferenceConstants.SCA_CORBA_DOMAIN_LIST_PREFERENCE));
	}

	/**
	 * @deprecated
	 * @return
	 */
	@Deprecated
	private static ScaDomainConnectionDef[] fromPreferenceStoreString(final String connectionStringDef) {
		if (connectionStringDef.trim().length() == 0) {
			return new ScaDomainConnectionDef[0];
		}
		final String[] values = connectionStringDef.split(";");
		final ArrayList<ScaDomainConnectionDef> doms = new ArrayList<ScaDomainConnectionDef>();
		for (final String value : values) {
			final ScaDomainConnectionDef def = new ScaDomainConnectionDef();
			def.fromPreferenceValue(value);
			doms.add(def);
		}
		return doms.toArray(new ScaDomainConnectionDef[doms.size()]);
	}
}

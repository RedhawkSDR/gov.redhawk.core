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
package gov.redhawk.sca.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.omg.CORBA.ORB;

/**
 * @since 3.1
 * 
 */
public final class ORBUtil {
	private ORBUtil() {

	}

	private static final String CORBA_NAMESPACE = "org.omg.CORBA.";

	/**
	 * @since 3.1
	 */
	public static ORB init(final String[] args, Properties properties) {
		if (properties != null) {
			Map<Object, Object> newElements = new HashMap<Object, Object>();
			for (Entry<Object, Object> entry : properties.entrySet()) {
				Object key = entry.getKey();
				if (key instanceof String && ((String) key).startsWith(CORBA_NAMESPACE)) {
					newElements.put(((String) key).substring(CORBA_NAMESPACE.length()), entry.getValue());
				}
			}
			properties.putAll(newElements);
		}
		ORB retVal = ORB.init(args, properties);
		return retVal;
	}

	public static ORB init(final Properties properties) {
		return ORBUtil.init((String[]) null, properties);
	}
}

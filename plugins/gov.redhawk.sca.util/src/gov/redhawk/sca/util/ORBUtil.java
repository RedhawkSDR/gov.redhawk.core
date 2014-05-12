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
package gov.redhawk.sca.util;

import java.util.Properties;

import org.jacorb.JacorbUtil;
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
		return JacorbUtil.init(args, properties);
	}

	public static ORB init(final Properties properties) {
		return JacorbUtil.init(properties);
	}
}

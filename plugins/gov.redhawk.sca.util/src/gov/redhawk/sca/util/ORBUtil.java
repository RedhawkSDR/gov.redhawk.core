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

import mil.jpeojtrs.sca.util.ProtectedThreadExecutor;

import org.eclipse.jdt.annotation.Nullable;
import org.jacorb.JacorbUtil;
import org.omg.CORBA.ORB;

/**
 * @since 3.1
 * 
 */
public final class ORBUtil {
	private ORBUtil() {

	}

	/**
	 * @since 3.1
	 */
	public static ORB init(final String[] args, final Properties properties) {
		return org.jacorb.JacorbUtil.init(args, properties);
	}

	public static ORB init(final Properties properties) {
		return org.jacorb.JacorbUtil.init(properties);
	}
	
	/**
	 * Releases the CORBA object in a background thread asyncronously
	 * @since 3.4
	 */
	public static void release(@Nullable final org.omg.CORBA.Object obj) {
		if (obj == null) {
			return;
		}
		ProtectedThreadExecutor.async(new Runnable() {

			@Override
			public void run() {
				obj._release();
			}
			
		});
	}
}

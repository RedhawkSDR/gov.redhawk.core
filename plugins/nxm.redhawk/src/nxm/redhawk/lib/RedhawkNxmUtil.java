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
package nxm.redhawk.lib;

import java.util.concurrent.atomic.AtomicBoolean;

import nxm.sys.lib.NeXtMidas;

public final class RedhawkNxmUtil {
	private static final AtomicBoolean ADDED = new AtomicBoolean(false);

	private RedhawkNxmUtil() {

	}

	public static void initializeRedhawkOptionTrees() {
		if (!ADDED.compareAndSet(false, true)) {
			return;
		}
		NeXtMidas instance = NeXtMidas.getGlobalInstance();
		instance.runCommand("OPTION/VERBOSE=false REDHAWK CLASSPATH");
		instance.runCommand("OPTION/VERBOSE=false DSP CLASSPATH");
		instance.runCommand("PATH/VERBOSE=false   ADD DSP REDHAWK");
		instance.runCommand("DEBUG/VERBOSE=false  ON TRACE");
	}

}

package nxm.redhawk.lib;

import java.util.concurrent.atomic.AtomicBoolean;

import nxm.sys.lib.NeXtMidas;

public final class RedhawkNxmUtil {

	private static final AtomicBoolean added = new AtomicBoolean(false);

	private RedhawkNxmUtil() {

	}

	public static void initializeRedhawkOptionTrees() {
		if (!added.compareAndSet(false, true)) {
			return;
		}
		NeXtMidas instance = NeXtMidas.getGlobalInstance();
		instance.runCommand("OPTION/VERBOSE=false REDHAWK CLASSPATH");
		instance.runCommand("OPTION/VERBOSE=false DSP CLASSPATH");
		instance.runCommand("PATH/VERBOSE=false ADD DSP REDHAWK");
		instance.runCommand("DEBUG/VERBOSE=false ON TRACE");
	}

}

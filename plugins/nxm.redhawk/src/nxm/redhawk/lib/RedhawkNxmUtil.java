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
		instance.runCommand("OPTION REDHAWK CLASSPATH");
		instance.runCommand("OPTION DSP CLASSPATH");
		instance.runCommand("PATH ADD DSP REDHAWK");
		instance.runCommand("DEBUG ON TRACE");
	}

}

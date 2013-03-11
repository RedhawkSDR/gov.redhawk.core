package nxm.redhawk.lib;

import nxm.sys.lib.NeXtMidas;

public final class RedhawkNxmUtil {
	
	private static boolean added = false;

	private RedhawkNxmUtil() {

	}
	
	public static synchronized void initializeRedhawkOptionTrees() {
		if (added) {
			return;
		}
		added = true;
		NeXtMidas.getGlobalInstance().runCommand("OPTION REDHAWK CLASSPATH");
		NeXtMidas.getGlobalInstance().runCommand("OPTION DSP CLASSPATH");
		NeXtMidas.getGlobalInstance().runCommand("PATH ADD DSP REDHAWK");
	}

}

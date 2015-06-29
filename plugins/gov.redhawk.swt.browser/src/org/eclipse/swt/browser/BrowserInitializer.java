package org.eclipse.swt.browser;

import gov.redhawk.swt.browser.OsVersionParser;

import org.osgi.framework.Version;

/**
 * The static code in this class is invoked by SWT before instantiating a Browser object for the first time. This allows
 * us to modify certain properties that control the system library that will be used for the browser.
 */
public class BrowserInitializer {

	static {
		// If using RHEL/CentOS prior to 6.6, ensure we don't use webkit for the browser or the IDE crashes
		Version version = OsVersionParser.getRedhatVersion();
		if (version != null && version.compareTo(new Version(6, 6, 0)) < 0) {
			System.setProperty("org.eclipse.swt.browser.DefaultType", "mozilla");
		}
	}

}

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

package gov.redhawk.swt.browser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Version;

public class OsVersionParser {

	private static final String PLUGIN_ID = "gov.redhawk.swt.browser"; //$NON-NLS-1$

	private static final String REDHAT_RELEASE_FILE = "/etc/redhat-release"; //$NON-NLS-1$

	private static final String REDHAT_REGEX = "Red Hat.*release (\\d)\\.(\\d+).*"; //$NON-NLS-1$

	private static final String CENTOS_REGEX = "CentOS.*release (\\d)\\.(\\d+).*"; //$NON-NLS-1$

	private static final String WARNING_MESSAGE = "Unable to read OS version from /etc/redhat-release";

	private OsVersionParser() {
	}

	/**
	 * Gets the version of RedHat / CentOS based on the file <code>/etc/redhat-release</code>.
	 * @return The version of RedHat / CentOS, or null if it's a different OS. May also return a version of
	 * <code>0.0.0</code> if it's a RedHat-variant but the version is indeterminable.
	 */
	public static Version getRedhatVersion() {
		// Check for the version file. If it doesn't exist, we can reasonable assume it's not a RedHat / CentOS system
		File redhatRelease = new File(REDHAT_RELEASE_FILE);
		if (!redhatRelease.exists()) {
			return null;
		}

		// The file exists - attempt to parse it. If we have any failures at this point, return a version of 0.0.0.
		try (BufferedReader reader = Files.newBufferedReader(redhatRelease.toPath(), Charset.forName("UTF-8"))) {
			String versionFileString = reader.readLine();
			if (versionFileString == null) {
				StatusManager.getManager().handle(new Status(IStatus.WARNING, PLUGIN_ID, WARNING_MESSAGE));
				return new Version(0, 0, 0);
			}
			return getRedhatVersion(versionFileString);
		} catch (FileNotFoundException ex) {
			StatusManager.getManager().handle(new Status(IStatus.WARNING, PLUGIN_ID, WARNING_MESSAGE, ex));
			return new Version(0, 0, 0);
		} catch (IOException ex) {
			StatusManager.getManager().handle(new Status(IStatus.WARNING, PLUGIN_ID, WARNING_MESSAGE, ex));
			return new Version(0, 0, 0);
		}
	}

	/**
	 * Returns the version of RedHat / CentOS from the specified version string. Returns <code>0.0.0</code> if the
	 * string can't be recognized.
	 * @param versionFileString The contents of <code>/etc/redhat-release</code>
	 * @return The version of the OS
	 */
	public static Version getRedhatVersion(String versionFileString) {
		Matcher matcher = Pattern.compile(REDHAT_REGEX).matcher(versionFileString);
		if (matcher.matches()) {
			return new Version(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), 0);
		}
		matcher = Pattern.compile(CENTOS_REGEX).matcher(versionFileString);
		if (matcher.matches()) {
			return new Version(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), 0);
		}
		return new Version(0, 0, 0);
	}

}

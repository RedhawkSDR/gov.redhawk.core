package gov.redhawk.swt.browser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Version;

public class OsVersionParser {

	private static final String PLUGIN_ID = "gov.redhawk.swt.browser"; //$NON-NLS-1$
	
	private OsVersionParser() {
	}

	/**
	 * Gets the version of RedHat / CentOS based on the file <code>/etc/redhat-release</code>.
	 * @return The version of RedHat / CentOS, or null if it's a different OS. May also return a version of
	 * <code>0.0.0</code> if it's a RedHat-variant but the version is indeterminable.
	 */
	public static Version getRedhatVersion() {
		// Check for the version file. If it doesn't exist, we can reasonable assume it's not a RedHat / CentOS system
		File redhatRelease = new File("/etc/redhat-release");
		BufferedReader reader = null;
		if (!redhatRelease.exists()) {
			return null;
		}

		// The file exists - attempt to parse it. If we have any failures at this point, return a version of 0.0.0.
		try {
			reader = new BufferedReader(new FileReader(redhatRelease));
			String versionFileString = reader.readLine();
			if (versionFileString == null) {
				StatusManager.getManager().handle(new Status(IStatus.WARNING, PLUGIN_ID, "Unable to read OS version from /etc/redhat-release"));
				return new Version(0, 0, 0);
			}
			return getRedhatVersion(versionFileString);
		} catch (FileNotFoundException ex) {
			StatusManager.getManager().handle(new Status(IStatus.WARNING, PLUGIN_ID, "Unable to read OS version from /etc/redhat-release", ex));
			return new Version(0, 0, 0);
		} catch (IOException ex) {
			StatusManager.getManager().handle(new Status(IStatus.WARNING, PLUGIN_ID, "Unable to read OS version from /etc/redhat-release", ex));
			return new Version(0, 0, 0);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ex) {
					// PASS
				}
			}
		}
	}

	/**
	 * Returns the version of RedHat / CentOS from the specified version string. Returns <code>0.0.0</code> if the
	 * string can't be recognized.
	 * @param versionFileString The contents of <code>/etc/redhat-release</code>
	 * @return The version of the OS
	 */
	public static Version getRedhatVersion(String versionFileString) {
		Matcher matcher = Pattern.compile("Red Hat.*release (\\d)\\.(\\d+).*").matcher(versionFileString);
		if (matcher.matches()) {
			return new Version(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), 0);
		}
		matcher = Pattern.compile("CentOS.*release (\\d)\\.(\\d+).*").matcher(versionFileString);
		if (matcher.matches()) {
			return new Version(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), 0);
		}
		return new Version(0, 0, 0);
	}

}

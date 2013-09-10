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
package gov.redhawk.sca.efs;

import gov.redhawk.efs.sca.internal.cache.FileCache;
import gov.redhawk.efs.sca.internal.cache.ScaFileCache;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * @since 1.2
 */
public class ScaFileSystemPlugin extends Plugin {
	public static final String ID = "gov.redhawk.sca.efs"; //$NON-NLS-1$
	private static ScaFileSystemPlugin instance;

	public static ScaFileSystemPlugin getDefault() {
		return ScaFileSystemPlugin.instance;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		ScaFileSystemPlugin.instance = this;
		super.start(context);
		cleanOldFiles();
	}

	private void cleanOldFiles() {
		String systemPath = System.getProperty("java.io.tmpdir");
		File tmpDir = new File(systemPath);
		if (tmpDir.exists()) {
			Calendar currentTime = Calendar.getInstance();
			int year = currentTime.get(Calendar.YEAR);
			int month = currentTime.get(Calendar.MONTH);
			int day = currentTime.get(Calendar.DAY_OF_MONTH);
			currentTime.clear();
			currentTime.set(year, month, day);
			currentTime.add(Calendar.DAY_OF_MONTH, -2);
			Date current = currentTime.getTime();
			for (File f : tmpDir.listFiles()) {
				String pattern = "rhIDE-" + System.getProperty("user.name") + "-.*";
				if (f.getName().matches(pattern)) {
					String [] parts = f.getName().split("-");
					if (parts.length == 3) {
						try {
							Date date = FileCache.DATE_FORMAT.parse(parts[2]);
							if (date.before(current)) {
								try {
									FileUtils.deleteDirectory(f);
								} catch (IOException e) {
									// PASS
								}
							}
						} catch (ParseException e) {
							// PASS
						}
					}
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		super.stop(context);
		ScaFileCache.INSTANCE.clear();
		ScaFileSystemPlugin.instance = null;
	}

	public static void logError(final String msg, final Throwable e) {
		ScaFileSystemPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, msg, e));
	}

	public File getTempDirectory() {
		File dir = FileCache.getTempDir();
		if (!dir.exists()) {
			dir.mkdir();
		}
		return dir;
	}

}

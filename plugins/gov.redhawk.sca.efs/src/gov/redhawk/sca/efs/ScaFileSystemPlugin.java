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
		// TODO  In future be smart about clearing this
		try {
			FileUtils.deleteDirectory(getTempDirectory());
		} catch (IOException e) {
			// PASS
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		super.stop(context);
		ScaFileCache.INSTANCE.clear();
		cleanOldFiles();
		ScaFileSystemPlugin.instance = null;
	}

	public static void logError(final String msg, final Throwable e) {
		ScaFileSystemPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, msg, e));
	}

	/**
	 * @since 1.3
	 */
	public static void log(final IStatus msg) {
		ScaFileSystemPlugin.getDefault().getLog().log(msg);
	}

	public File getTempDirectory() {
		File dir = FileCache.getTempDir();
		if (!dir.exists()) {
			dir.mkdir();
		}
		return dir;
	}

}

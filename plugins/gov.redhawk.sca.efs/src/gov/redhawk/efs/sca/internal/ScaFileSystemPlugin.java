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
package gov.redhawk.efs.sca.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

public class ScaFileSystemPlugin extends Plugin {
	public static final String ID = "gov.redhawk.sca.efs"; //$NON-NLS-1$
	private static ScaFileSystemPlugin instance;
	private final ScaFileCache fileCache = new ScaFileCache();

	public ScaFileSystemPlugin() {
		ScaFileSystemPlugin.instance = this;
	}


	public static ScaFileSystemPlugin getDefault() {
		return ScaFileSystemPlugin.instance;
	}

	public ScaFileCache getFileCache() {
		return this.fileCache;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		super.stop(context);
		this.fileCache.clear();
		ScaFileSystemPlugin.instance = null;
	}

	public static void logError(final String msg, final Throwable e) {
		ScaFileSystemPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, msg, e));
	}

}

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
package gov.redhawk.core.filemanager;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

public class FileManagerPlugin extends Plugin {

	public static final String ID = "gov.redhawk.core.filemanager";
	private static FileManagerPlugin plugin;

	@Override
	public void start(final BundleContext context) throws Exception {
		FileManagerPlugin.plugin = this;
		super.start(context);
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		FileManagerPlugin.plugin = null;
		super.stop(context);
	}

	public static void logError(final String string, final Throwable exception) {
		FileManagerPlugin.plugin.getLog().log(new Status(IStatus.ERROR, FileManagerPlugin.ID, string, exception));
	}
}

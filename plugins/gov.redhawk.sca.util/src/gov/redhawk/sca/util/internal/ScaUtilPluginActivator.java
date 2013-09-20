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
package gov.redhawk.sca.util.internal;

import java.util.Date;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.packageadmin.PackageAdmin;
import org.osgi.util.tracker.ServiceTracker;

/**
 * 
 */
public class ScaUtilPluginActivator extends Plugin implements BundleActivator {

	public static final String ID = "gov.redhawk.sca.util";
	private static ScaUtilPluginActivator instance;
	private ServiceTracker bundleTracker = null;

	@Override
	public void start(final BundleContext context) throws Exception {
		instance = this;
		super.start(context);
	}

	public static ScaUtilPluginActivator getDefault() {
		return instance;
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		super.stop(context);
		if (this.bundleTracker != null) {
			this.bundleTracker.close();
			this.bundleTracker = null;
		}
		instance = null;
	}

	/*
	 * Return the package admin service, if available.
	 */
	private PackageAdmin getBundleAdmin() {
		if (this.bundleTracker == null) {
			this.bundleTracker = new ServiceTracker(getBundle().getBundleContext(), PackageAdmin.class.getName(), null);
			this.bundleTracker.open();
		}
		return (PackageAdmin) this.bundleTracker.getService();
	}

	/**
	 * Returns the bundle id of the bundle that contains the provided object, or
	 * <code>null</code> if the bundle could not be determined.
	 */
	public String getBundleId(final Object object) {
		if (object == null) {
			return null;
		}
		if (this.bundleTracker == null) {
			getBundleAdmin();
			if (this.bundleTracker == null) {
				message(Status.ERROR, "Bundle tracker is not set"); //$NON-NLS-1$
				return null;
			}
		}
		final PackageAdmin packageAdmin = (PackageAdmin) this.bundleTracker.getService();
		if (packageAdmin == null) {
			return null;
		}
		final Bundle source = packageAdmin.getBundle(object.getClass());
		if (source != null && source.getSymbolicName() != null) {
			return source.getSymbolicName();
		}
		return null;
	}

	/**
	 * Print a message to the Eclipse log. 
	 * Pre-pend the message with the current date and the name of the current thread.
	 */
	private void message(int severity, final String message) {
		final StringBuilder buffer = new StringBuilder();
		buffer.append(new Date(System.currentTimeMillis()));
		buffer.append(" - ["); //$NON-NLS-1$
		buffer.append(Thread.currentThread().getName());
		buffer.append("] "); //$NON-NLS-1$
		buffer.append(message);
		getLog().log(new Status(severity, ID, buffer.toString(), null));
	}
}

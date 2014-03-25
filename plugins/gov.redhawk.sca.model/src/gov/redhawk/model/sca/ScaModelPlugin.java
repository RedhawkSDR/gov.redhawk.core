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
package gov.redhawk.model.sca;

import gov.redhawk.model.sca.impl.SilentModelJob;
import gov.redhawk.model.sca.services.IScaObjectLocator;
import gov.redhawk.sca.model.internal.DataProviderServicesRegistry;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.jacorb.eclipse.JacorbEclipseActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventAdmin;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @since 2.0
 */
public class ScaModelPlugin extends Plugin implements IScaObjectLocator {
	public static final String ID = "gov.redhawk.sca.model"; //$NON-NLS-1$

	private static ScaModelPlugin instance;

	private ServiceTracker<IScaObjectLocator, IScaObjectLocator> locatorTracker;

	private ServiceTracker<EventAdmin, EventAdmin> eventAdminTracker;

	private BundleContext bundleContext;

	public ScaModelPlugin() {
	}

	public static ScaModelPlugin getDefault() {
		return ScaModelPlugin.instance;
	}

	@Override
	public void start(final BundleContext context) throws Exception {
		bundleContext = context;
		ScaModelPlugin.instance = this;
		locatorTracker = new ServiceTracker<IScaObjectLocator, IScaObjectLocator>(context, IScaObjectLocator.class, null);
		locatorTracker.open(true);
		super.start(context);
		JacorbEclipseActivator.getDefault().init();
	}
	
	/**
	 * @since 19.0
	 */
	public BundleContext getBundleContext() {
		return bundleContext;
	}
	
	/**
	 * @since 19.0
	 */
	public EventAdmin getEventAdmin() {
		if (eventAdminTracker == null) {
			eventAdminTracker = new ServiceTracker<EventAdmin, EventAdmin>(getBundleContext(), EventAdmin.class, null);
			eventAdminTracker.open();
		}
		return (EventAdmin) eventAdminTracker.getService();
	}

	/**
	 * @since 14.0
	 */
	public IScaObjectLocator[] getScaObjectLocators() {
		if (locatorTracker == null) {
			return new IScaObjectLocator[0];
		}
		Object[] services = locatorTracker.getServices();
		if (services != null) {
			IScaObjectLocator[] retVal = new IScaObjectLocator[services.length];
			System.arraycopy(services, 0, retVal, 0, services.length);
			return retVal;
		} else {
			return new IScaObjectLocator[0];
		}

	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		if (locatorTracker != null) {
			locatorTracker.close();
			locatorTracker = null;
		}
		if (eventAdminTracker != null) {
			eventAdminTracker.close();
			eventAdminTracker = null;
		}
		ScaModelPlugin.instance = null;
		SilentModelJob.setShouldRun(false);
		Job.getJobManager().cancel(SilentModelJob.JOB_FAMILY);
		DataProviderServicesRegistry.INSTANCE.dispose();
		//		Job.getJobManager().join(SilentModelJob.JOB_FAMILY, null);
		bundleContext = null;
		super.stop(context);
	}

	public static void logError(final String msg, final Throwable e) {
		ScaModelPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ScaModelPlugin.ID, msg, e));
	}

	/**
	 * @since 9.0
	 */
	public static IDataProviderServiceRegistry getDataProviderRegistry() {
		return DataProviderServicesRegistry.INSTANCE;
	}

	/**
	 * @since 14.0
	 */
	@Override
	public < T extends CorbaObjWrapper< ? >> T findEObject(Class<T> type, String ior) {
		for (IScaObjectLocator locator : getScaObjectLocators()) {
			T obj = locator.findEObject(type, ior);
			if (obj != null) {
				return obj;
			}
		}
		return null;
	}

}

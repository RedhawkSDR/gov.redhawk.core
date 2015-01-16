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
package gov.redhawk.monitor;

import gov.redhawk.monitor.internal.MonitorRefreshJob;
import gov.redhawk.monitor.internal.MonitorRegistryAdapter;
import gov.redhawk.monitor.model.ports.MonitorRegistry;
import gov.redhawk.monitor.model.ports.PortsFactory;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Adapter;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class MonitorPlugin implements BundleActivator {

	public static final String PLUGIN_ID = "gov.redhawk.monitor";

	private static BundleContext context;

	private static MonitorPlugin instance;

	private MonitorRegistry registry;

	private Adapter registryAdapter;

	private Job refreshJob;

	private long refreshInterval = 10000;

	static BundleContext getContext() {
		return context;
	}

	/**
	 * Returns the shared instance.
	 *
	 * @return the shared instance
	 */
	public static MonitorPlugin getDefault() {
		return MonitorPlugin.instance;
	}

	/**
	 * Gets the registry which keeps all statistics-monitoring model objects.
	 *
	 * @return The monitor registry
	 */
	public MonitorRegistry getMonitorRegistry() {
		return registry;
	}

	/**
	 * Get the refresh interval for monitor statistics
	 *
	 * @return The interval in milliseconds
	 */
	public long getRefreshInterval() {
		return this.refreshInterval;
	}

	/**
	 * @param refreshInterval The refresh interval in milliseconds
	 */
	public void setRefreshInterval(long refreshInterval) {
		long oldRefreshInterval = this.refreshInterval;
		this.refreshInterval = refreshInterval;

		// Don't make them wait if they're speeding it up
		if (this.refreshInterval < oldRefreshInterval) {
			this.refreshJob.cancel();
			this.refreshJob.schedule();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		MonitorPlugin.context = bundleContext;
		MonitorPlugin.instance = this;

		this.registry = PortsFactory.eINSTANCE.createMonitorRegistry();
		this.refreshJob = new MonitorRefreshJob(this.registry);
		this.registryAdapter = new MonitorRegistryAdapter(this.refreshJob);
		this.registry.eAdapters().add(this.registryAdapter);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		MonitorPlugin.context = null;
		MonitorPlugin.instance = null;

		if (this.registryAdapter != null) {
			this.registry.eAdapters().remove(this.registryAdapter);
			this.registryAdapter = null;
		}
		if (this.refreshJob != null) {
			this.refreshJob.cancel();
			this.refreshJob = null;
		}
		if (this.registry != null) {
			this.registry.getMonitors().clear();
		}
	}

}

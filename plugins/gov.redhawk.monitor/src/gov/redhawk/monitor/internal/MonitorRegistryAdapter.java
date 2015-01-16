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
package gov.redhawk.monitor.internal;

import gov.redhawk.monitor.MonitorPlugin;
import gov.redhawk.monitor.model.ports.MonitorRegistry;
import gov.redhawk.monitor.model.ports.PortsPackage;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

/**
 * Adapts a {@link MonitorRegistry} to ensure a refresh job is scheduled/cancelled depending on whether the registry has
 * any {@link Monitor}s.
 */
public class MonitorRegistryAdapter extends AdapterImpl {

	private Job refreshJob;

	public MonitorRegistryAdapter(Job refreshJob) {
		this.refreshJob = refreshJob;
	}

	@Override
	public void notifyChanged(final Notification msg) {
		if (msg.getFeatureID(MonitorRegistry.class) == PortsPackage.MONITOR_REGISTRY__MONITORS) {
			switch (msg.getEventType()) {
			case Notification.ADD:
			case Notification.ADD_MANY:
				long refreshInterval = MonitorPlugin.getDefault().getRefreshInterval();
				this.refreshJob.schedule(refreshInterval);
				break;
			case Notification.REMOVE:
			case Notification.REMOVE_MANY:
				MonitorRegistry registry = (MonitorRegistry) msg.getNotifier();
				if (registry.getMonitors().isEmpty()) {
					this.refreshJob.cancel();
				}
				break;
			default:
				break;
			}
		}
	}

}

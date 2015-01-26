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

import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.monitor.model.ports.PortConnectionMonitor;
import gov.redhawk.monitor.model.ports.PortMonitor;
import gov.redhawk.monitor.model.ports.PortsPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.util.EContentAdapter;

import BULKIO.PortStatistics;

/**
 * Provides notification of new/updated or removed statistics for ports. Intended to adapt a
 * {@link gov.redhawk.monitor.model.ports.MonitorRegistry}.
 *
 * @see MonitorPlugin#getMonitorRegistry()
 */
public class MonitorPortAdapter extends EContentAdapter {

	private IPortStatListener statListener;

	/**
	 * Creates the adapter. It will deliver updates to the provided listener.
	 *
	 * @param statListener A listener to receive updates
	 */
	public MonitorPortAdapter(IPortStatListener statListener) {
		this.statListener = statListener;
	}

	@Override
	public void notifyChanged(Notification notification) {
		Object notifier = notification.getNotifier();
		if (notifier instanceof PortMonitor && notification.getFeatureID(PortMonitor.class) == PortsPackage.PORT_MONITOR__DATA) {
			final PortMonitor portMonitor = (PortMonitor) notifier;
			final ScaPort< ? , ? > port = portMonitor.getPort();
			if (port instanceof ScaProvidesPort) {
				final PortStatistics stats = portMonitor.getData();
				if (stats == null) {
					statListener.noStatistics(port);
				} else {
					statListener.newStatistics(port, stats);
				}
			}
		} else if (notifier instanceof PortConnectionMonitor
			&& notification.getFeatureID(PortConnectionMonitor.class) == PortsPackage.PORT_CONNECTION_MONITOR__DATA) {
			final PortConnectionMonitor portConnection = (PortConnectionMonitor) notifier;
			final PortStatistics stats = portConnection.getData();
			if (stats == null) {
				statListener.noStatistics(portConnection.getPort().getPort(), portConnection.getConnectionId());
			} else {
				statListener.newStatistics(portConnection.getPort().getPort(), portConnection.getConnectionId(), stats);
			}
		}
		super.notifyChanged(notification);
	}

	@Override
	protected void addAdapter(Notifier notifier) {
		if (notifier instanceof PortMonitor) {
			final PortMonitor portMonitor = (PortMonitor) notifier;
			final ScaPort< ? , ? > port = portMonitor.getPort();
			if (port instanceof ScaUsesPort) {
				for (final PortConnectionMonitor portConnection : portMonitor.getConnections()) {
					final PortStatistics stats = portConnection.getData();
					if (stats != null) {
						statListener.newStatistics(port, portConnection.getConnectionId(), stats);
					}
				}
			} else if (port instanceof ScaProvidesPort) {
				PortStatistics stats = portMonitor.getData();
				if (stats != null) {
					statListener.newStatistics(port, stats);
				}
			}
		}
		super.addAdapter(notifier);
	}

	@Override
	protected void removeAdapter(Notifier notifier) {
		if (notifier instanceof PortMonitor) {
			final PortMonitor monitor = (PortMonitor) notifier;
			if (monitor.getPort() instanceof ScaProvidesPort) {
				statListener.noStatistics(monitor.getPort());
			}
		} else if (notifier instanceof PortConnectionMonitor) {
			final PortConnectionMonitor portConnection = (PortConnectionMonitor) notifier;
			if (portConnection.getPort() != null) {
				statListener.noStatistics(portConnection.getPort().getPort(), portConnection.getConnectionId());
			}
		}
		super.removeAdapter(notifier);
	}

}

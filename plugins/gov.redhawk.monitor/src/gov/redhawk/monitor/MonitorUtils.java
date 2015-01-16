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

import org.eclipse.emf.ecore.util.EcoreUtil;

import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.monitor.model.ports.Monitor;
import gov.redhawk.monitor.model.ports.PortMonitor;
import gov.redhawk.monitor.model.ports.PortSupplierMonitor;
import gov.redhawk.monitor.model.ports.PortsFactory;

public final class MonitorUtils {

	private MonitorUtils() {
	}

	/**
	 * Begins monitoring a port. No effect if already being monitored.
	 * 
	 * @param port The port to monitor
	 * @return The monitor object
	 * @since 1.0
	 */
	public static PortMonitor addMonitor(final ScaPort< ? , ? > port) {
		PortMonitor monitor = findMonitor(port);
		if (monitor == null) {
			monitor = PortsFactory.eINSTANCE.createPortMonitor();
			monitor.setPort(port);
			MonitorPlugin.getDefault().getMonitorRegistry().getMonitors().add(monitor);
		}
		monitor.fetchStats();
		return monitor;
	}

	/**
	 * Finds an existing monitor for a port, if it exists.
	 * 
	 * @param port The port whose monitor it to be searched for
	 * @return The monitor for the port, or null if none
	 * @since 1.0
	 */
	private static PortMonitor findMonitor(ScaPort< ? , ? > port) {
		for (final Monitor monitor : MonitorPlugin.getDefault().getMonitorRegistry().getMonitors()) {
			if (monitor instanceof PortMonitor) {
				final PortMonitor portMonitor = (PortMonitor) monitor;
				if (portMonitor.getPort() == port) {
					return portMonitor;
				}
			}
		}
		return null;
	}

	/**
	 * Removes monitoring for a port, if any.
	 * 
	 * @param port The port to stop monitoring.
	 * @since 1.0
	 */
	public static void removeMonitor(ScaPort< ? , ? > port) {
		Monitor monitor = findMonitor(port);
		if (monitor != null) {
			removeMonitor(monitor);
		}
	}

	/**
	 * Begins monitoring a port container (and thus all ports in the container). No effect if already being monitored.
	 * 
	 * @param portContainer The port container to monitor
	 * @return The monitor object
	 * @since 1.0
	 */
	public static PortSupplierMonitor addMonitor(final ScaPortContainer portContainer) {
		PortSupplierMonitor monitor = findMonitor(portContainer);
		if (monitor == null) {
			monitor = PortsFactory.eINSTANCE.createPortSupplierMonitor();
			monitor.setPortContainer(portContainer);
			MonitorPlugin.getDefault().getMonitorRegistry().getMonitors().add(monitor);
		}
		monitor.fetchStats();
		return monitor;
	}

	/**
	 * Finds an existing monitor for a port container, if it exists.
	 * 
	 * @param portContainer The port container whose monitor it to be searched for
	 * @return The monitor for the port container, or null if none
	 * @since 1.0
	 */
	private static PortSupplierMonitor findMonitor(ScaPortContainer portContainer) {
		for (final Monitor monitor : MonitorPlugin.getDefault().getMonitorRegistry().getMonitors()) {
			if (monitor instanceof PortSupplierMonitor) {
				final PortSupplierMonitor portSupplierMonitor = (PortSupplierMonitor) monitor;
				if (portSupplierMonitor.getPortContainer() == portContainer) {
					return portSupplierMonitor;
				}
			}
		}
		return null;
	}

	/**
	 * Removes monitoring for a port container, if any.
	 * 
	 * @param portContainer The port container to stop monitoring.
	 * @since 1.0
	 */
	public static void removeMonitor(ScaPortContainer portContainer) {
		Monitor monitor = findMonitor(portContainer);
		if (monitor != null) {
			removeMonitor(monitor);
		}
	}

	/**
	 * Removes a monitor.
	 * 
	 * @param monitor The monitor to remove
	 * @since 1.0
	 */
	public static void removeMonitor(Monitor monitor) {
		EcoreUtil.remove(monitor);
	}

}

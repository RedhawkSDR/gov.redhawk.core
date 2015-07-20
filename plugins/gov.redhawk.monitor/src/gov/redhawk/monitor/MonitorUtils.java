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
import org.eclipse.emf.transaction.RunnableWithResult;

import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.monitor.model.ports.Monitor;
import gov.redhawk.monitor.model.ports.MonitorRegistry;
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
		final MonitorRegistry registry = MonitorPlugin.getDefault().getMonitorRegistry();
		PortMonitor monitor = ScaModelCommandWithResult.execute(registry, new ScaModelCommandWithResult<PortMonitor>() {
			@Override
			public void execute() {
				PortMonitor monitor = findMonitor(port);
				if (monitor == null) {
					monitor = PortsFactory.eINSTANCE.createPortMonitor();
					monitor.setPort(port);
					registry.getMonitors().add(monitor);
				}
				setResult(monitor);
			}
		});
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
	private static PortMonitor findMonitor(final ScaPort< ? , ? > port) {
		try {
			final MonitorRegistry registry = MonitorPlugin.getDefault().getMonitorRegistry();
			PortMonitor portMonitor = ScaModelCommand.runExclusive(registry, new RunnableWithResult.Impl<PortMonitor>() {
				@Override
				public void run() {
					for (final Monitor monitor : registry.getMonitors()) {
						if (monitor instanceof PortMonitor) {
							final PortMonitor portMonitor = (PortMonitor) monitor;
							if (portMonitor.getPort() == port) {
								setResult(portMonitor);
							}
						}
					}
				};
			});
			return portMonitor;
		} catch (InterruptedException e) {
			return null;
		}
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
		final MonitorRegistry registry = MonitorPlugin.getDefault().getMonitorRegistry();
		PortSupplierMonitor monitor = ScaModelCommandWithResult.execute(registry, new ScaModelCommandWithResult<PortSupplierMonitor>() {
			@Override
			public void execute() {
				PortSupplierMonitor monitor = findMonitor(portContainer);
				if (monitor == null) {
					monitor = PortsFactory.eINSTANCE.createPortSupplierMonitor();
					monitor.setPortContainer(portContainer);
					registry.getMonitors().add(monitor);
				}
				setResult(monitor);
			}
		});
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
	private static PortSupplierMonitor findMonitor(final ScaPortContainer portContainer) {
		try {
			final MonitorRegistry registry = MonitorPlugin.getDefault().getMonitorRegistry();
			PortSupplierMonitor portSupplierMonitor = ScaModelCommand.runExclusive(registry, new RunnableWithResult.Impl<PortSupplierMonitor>() {
				@Override
				public void run() {
					for (final Monitor monitor : MonitorPlugin.getDefault().getMonitorRegistry().getMonitors()) {
						if (monitor instanceof PortSupplierMonitor) {
							final PortSupplierMonitor portSupplierMonitor = (PortSupplierMonitor) monitor;
							if (portSupplierMonitor.getPortContainer() == portContainer) {
								setResult(portSupplierMonitor);
							}
						}
					}
				}
			});
			return portSupplierMonitor;
		} catch (InterruptedException ex) {
			return null;
		}
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
	public static void removeMonitor(final Monitor monitor) {
		ScaModelCommand.execute(monitor, new ScaModelCommand() {
			@Override
			public void execute() {
				EcoreUtil.remove(monitor);
			}
		});
	}

}

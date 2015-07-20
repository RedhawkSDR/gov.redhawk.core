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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.transaction.RunnableWithResult;

import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.monitor.MonitorPlugin;
import gov.redhawk.monitor.model.ports.Monitor;
import gov.redhawk.monitor.model.ports.MonitorRegistry;

/**
 * A job that periodically asks all {@link Monitor}s to fetch their statistics.
 */
public class MonitorRefreshJob extends Job {

	private MonitorRegistry registry;

	/**
	 * @param registry The registry whose monitors will be periodically asked to fetch their stats
	 */
	public MonitorRefreshJob(MonitorRegistry registry) {
		super("Refreshing statistics");
		setUser(false);
		setSystem(true);
		this.registry = registry;
	}

	@Override
	public boolean shouldSchedule() {
		return !this.registry.getMonitors().isEmpty() && super.shouldSchedule();
	}

	@Override
	public boolean shouldRun() {
		return !this.registry.getMonitors().isEmpty() && super.shouldRun();
	}

	@Override
	protected IStatus run(final IProgressMonitor progress) {
		try {
			// Get the list of of monitors
			List<Monitor> monitors = ScaModelCommandWithResult.runExclusive(registry, new RunnableWithResult.Impl<List<Monitor>>() {
				@Override
				public void run() {
					setResult(new ArrayList<Monitor>(registry.getMonitors()));
				}
			});

			// Ask each one to fetch stats
			SubMonitor subprogress = SubMonitor.convert(progress, monitors.size());
			for (final Monitor m : monitors) {
				m.fetchStats();
				subprogress.worked(1);
				if (subprogress.isCanceled()) {
					return Status.CANCEL_STATUS;
				}
			}
		} catch (InterruptedException e) {
			// PASS
		}

		// Re-schedule ourself after a delay
		schedule(MonitorPlugin.getDefault().getRefreshInterval());
		return Status.OK_STATUS;
	}

}

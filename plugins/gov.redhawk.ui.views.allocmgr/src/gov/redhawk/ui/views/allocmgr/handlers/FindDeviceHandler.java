/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.ui.views.allocmgr.handlers;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.handlers.HandlerUtil;

import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.ui.views.allocmgr.AllocMgrPlugin;
import gov.redhawk.ui.views.allocmgr.AllocationStatus;
import mil.jpeojtrs.sca.util.collections.FeatureMapList;

public class FindDeviceHandler extends AbstractFindHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveShellChecked(event);
		IWorkbenchPage activeWorkbenchWindow = HandlerUtil.getActiveWorkbenchWindowChecked(event).getActivePage();
		IStructuredSelection ss = HandlerUtil.getCurrentStructuredSelection(event);
		AllocationStatus allocStatus = (AllocationStatus) ss.getFirstElement();
		String ior = allocStatus.getDeviceIOR();

		Job findDeviceJob = Job.create("Find device in explorer view", monitor -> {
			final int WORK_QUICK = 10;
			final int WORK_LONG = 90;
			SubMonitor progress = SubMonitor.convert(monitor, WORK_QUICK + WORK_LONG);

			// Quick search - find only if it's already in the model
			ScaDomainManagerRegistry registry = ScaPlugin.getDefault().getDomainManagerRegistry(null);
			List<ScaDomainManager> domMgrs = getConnectedDomains(registry);
			ScaDevice< ? > device = search(progress.split(WORK_QUICK), domMgrs, ior, false);
			if (device != null) {
				setExplorerSelectionAsync(shell.getDisplay(), activeWorkbenchWindow, device);
				return Status.OK_STATUS;
			}

			// Long search - perform fetches for the model and find the object
			device = search(progress.split(WORK_LONG), domMgrs, ior, true);
			if (device != null) {
				setExplorerSelectionAsync(shell.getDisplay(), activeWorkbenchWindow, device);
				return Status.OK_STATUS;
			}

			return new Status(IStatus.ERROR, AllocMgrPlugin.ID, "The device associated with the allocation cannot be found in any of the connected domains.");
		});
		findDeviceJob.setUser(true);
		findDeviceJob.schedule();

		return null;
	}

	private ScaDevice< ? > search(SubMonitor progress, List<ScaDomainManager> domMgrs, String deviceIor, boolean fetch) {
		progress.setWorkRemaining(domMgrs.size());

		for (ScaDomainManager domMgr : domMgrs) {
			SubMonitor domMgrProgress = progress.split(1);

			// If requested, perform fetches to populate the model. We fetch only the bare minimum that the explorer
			// view needs so that it doesn't have a deferred fetch of any children (which prevents the selection).
			if (fetch) {
				domMgrProgress.setWorkRemaining(10);
				for (ScaDeviceManager devMgr : domMgr.fetchDeviceManagers(domMgrProgress.split(1), RefreshDepth.SELF)) {
					devMgr.fetchDevices(domMgrProgress.split(1), RefreshDepth.SELF);
					devMgr.fetchServices(domMgrProgress.split(1), RefreshDepth.SELF);
					devMgr.fetchPorts(domMgrProgress.split(1));
				}
			}
			if (progress.isCanceled()) {
				throw new OperationCanceledException();
			}

			ScaDevice< ? > device = ScaModelCommand.runExclusive(domMgr, () -> {
				for (ScaDeviceManager devMgr : domMgr.getDeviceManagers()) {
					for (ScaDevice< ? > dev : new FeatureMapList<>(devMgr.getDevices(), ScaDevice.class)) {
						if (deviceIor.equals(dev.getIor())) {
							return dev;
						}
					}
				}
				return null;
			});
			if (device != null) {
				return device;
			}
		}

		return null;
	}
}

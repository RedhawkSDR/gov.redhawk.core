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
package gov.redhawk.ui.views.allocmgr.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.omg.CORBA.ORB;
import org.omg.CORBA.SystemException;

import CF.Device;
import CF.DeviceHelper;
import CF.DeviceManager;
import CF.DeviceManagerHelper;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.ui.views.allocmgr.AllocMgrPlugin;
import gov.redhawk.ui.views.allocmgr.AllocationStatus;
import mil.jpeojtrs.sca.util.collections.FeatureMapList;

class FetchLabelsJob extends Job {

	/**
	 * Delay after which the job is scheduled to re-run to attempt to resolve any labels it couldn't resolve.
	 */
	private static final long REPEAT_FETCH_DELAY_MS = 10000;

	private Set<AllocationStatus> statusesWithoutLabels = new HashSet<>();
	private Map<String, String> iorToLabel = new HashMap<>();
	private ScaDomainManager domMgr;
	private boolean shouldRun = true;

	public FetchLabelsJob() {
		super("Fetch labels");
		setSystem(true);
	}

	/**
	 * @param domMgr The domain manager model object whose children will be used to help resolve labels.
	 */
	public void setDomainManager(ScaDomainManager domMgr) {
		this.domMgr = domMgr;
	}

	/**
	 * Add a {@link AllocationStatus} to the list of statues whose labels need to be resolved. The job should be
	 * scheduled after adding a status.
	 * @param status The status with unresolved labels
	 */
	public void addStatusWithoutLables(AllocationStatus status) {
		synchronized (statusesWithoutLabels) {
			statusesWithoutLabels.add(status);
		}
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, IProgressMonitor.UNKNOWN);

		// Grab the statuses from the class-level variables
		Set<AllocationStatus> statuses;
		synchronized (statusesWithoutLabels) {
			statuses = new HashSet<>(statusesWithoutLabels);
			statusesWithoutLabels.clear();
		}

		// First resolve things we already know about
		statuses = resolveFromCache(statuses);
		if (statuses.isEmpty()) {
			return Status.OK_STATUS;
		}

		// What do we need to find labels for?
		Set<String> deviceIors = new HashSet<>();
		Set<String> deviceMgrIors = new HashSet<>();
		for (AllocationStatus status : statuses) {
			String ior = status.getDeviceIOR();
			if (ior != null && status.getDeviceLabel() == null) {
				deviceIors.add(ior);
			}
			ior = status.getDeviceMgrIOR();
			if (ior != null && status.getDeviceMgrLabel() == null) {
				deviceMgrIors.add(ior);
			}
		}

		// Find labels in the model first
		fetchLabelsFromModel(deviceIors, deviceMgrIors, progress.split(1));
		statuses = resolveFromCache(statuses);
		if (statuses.isEmpty()) {
			return Status.OK_STATUS;
		}

		// Retrieve remaining labels via CORBA calls (some objects may be in other domains)
		fetchLabelsDirectly(deviceIors, deviceMgrIors, progress.split(1));
		statuses = resolveFromCache(statuses);
		if (statuses.isEmpty()) {
			return Status.OK_STATUS;
		}

		// We didn't resolve everything. We can try again with anything remaining.
		synchronized (statusesWithoutLabels) {
			statusesWithoutLabels.addAll(statuses);
		}
		schedule(REPEAT_FETCH_DELAY_MS);

		return Status.OK_STATUS;
	}

	@Override
	public boolean shouldRun() {
		return shouldRun;
	}

	@Override
	protected void canceling() {
		shouldRun = false;
	}

	/**
	 * Checks the cache and sets device / device manager labels on {@link AllocationStatus} objects.
	 * @param statuses The statuses to check
	 * @return Any statuses with unresolved labels
	 */
	private Set<AllocationStatus> resolveFromCache(Set<AllocationStatus> statuses) {
		Set<AllocationStatus> unresolved = new HashSet<>();
		for (AllocationStatus status : statuses) {
			String ior = status.getDeviceIOR();
			if (ior != null && status.getDeviceLabel() == null) {
				if (iorToLabel.containsKey(ior)) {
					status.setDeviceLabel(iorToLabel.get(ior));
				} else {
					unresolved.add(status);
				}
			}
			ior = status.getDeviceMgrIOR();
			if (ior != null && status.getDeviceMgrLabel() == null) {
				if (iorToLabel.containsKey(ior)) {
					status.setDeviceMgrLabel(iorToLabel.get(ior));
				} else {
					unresolved.add(status);
				}
			}
		}
		return unresolved;
	}

	/**
	 * Perform CORBA calls to fetch any missing labels
	 * @param deviceIors Devices to fetch labels for
	 * @param deviceMgrIors Device managers to fetch labels for
	 * @param progress
	 */
	private void fetchLabelsDirectly(Set<String> deviceIors, Set<String> deviceMgrIors, SubMonitor progress) {
		OrbSession session = OrbSession.createSession(AllocMgrPlugin.ID);
		try {
			ORB orb = session.getOrb();
			for (String deviceMgrIor : deviceMgrIors) {
				try {
					org.omg.CORBA.Object obj = orb.string_to_object(deviceMgrIor);
					DeviceManager devMgr = DeviceManagerHelper.narrow(obj);
					String label = devMgr.label();
					iorToLabel.put(deviceMgrIor, label);
				} catch (SystemException e) {
					// PASS
				}

				progress.worked(1);
				if (progress.isCanceled()) {
					throw new OperationCanceledException();
				}
			}
			for (String deviceIor : deviceIors) {
				try {
					org.omg.CORBA.Object obj = orb.string_to_object(deviceIor);
					Device device = DeviceHelper.narrow(obj);
					String label = device.label();
					iorToLabel.put(deviceIor, label);
				} catch (SystemException e) {
					// PASS
				}

				progress.worked(1);
				if (progress.isCanceled()) {
					throw new OperationCanceledException();
				}
			}
		} finally {
			session.dispose();
		}
	}

	/**
	 * Use the SCA model to fetch labels anywhere we find a matching IOR.
	 * @param deviceIors Devices to fetch labels for
	 * @param deviceMgrIors Device managers to fetch labels for
	 * @param progress
	 */
	private void fetchLabelsFromModel(Set<String> deviceIors, Set<String> deviceMgrIors, SubMonitor progress) {
		if (domMgr == null) {
			return;
		}

		// Find device managers and devices with IORs we're interested in
		List<ScaDeviceManager> devMgrs = new ArrayList<>();
		List<ScaDevice< ? >> devices = new ArrayList<>();
		ScaModelCommand.runExclusive(domMgr, () -> {
			for (ScaDeviceManager devMgr : domMgr.getDeviceManagers()) {
				if (devMgr.isSetIor() && deviceMgrIors.contains(devMgr.getIor())) {
					devMgrs.add(devMgr);
				}

				for (ScaDevice< ? > device : new FeatureMapList<>(devMgr.getDevices(), ScaDevice.class)) {
					if (device.isSetIor() && deviceIors.contains(device.getIor())) {
						devices.add(device);
					}
				}
			}
			return null;
		});
		progress.worked(1);

		// Fetch labels as necessary
		for (ScaDeviceManager devMgr : devMgrs) {
			String ior = devMgr.getIor();
			String label = devMgr.fetchLabel(progress.split(1));
			if (ior != null && label != null) {
				iorToLabel.put(ior, label);
			}
		}
		for (ScaDevice< ? > device : devices) {
			String ior = device.getIor();
			String label = device.fetchLabel(progress.split(1));
			if (ior != null && label != null) {
				iorToLabel.put(ior, label);
			}
		}
	}
}

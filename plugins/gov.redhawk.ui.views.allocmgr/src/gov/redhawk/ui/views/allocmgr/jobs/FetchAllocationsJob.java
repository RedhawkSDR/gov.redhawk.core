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
package gov.redhawk.ui.views.allocmgr.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.InstanceScope;

import gov.redhawk.model.sca.DomainConnectionState;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.sca.util.IPreferenceAccessor;
import gov.redhawk.sca.util.ScopedPreferenceAccessor;
import gov.redhawk.ui.views.allocmgr.ScaAllocationManager;

public class FetchAllocationsJob extends Job {

	private ScaDomainManager domMgr;
	private ScaAllocationManager allocMgr;
	private boolean shouldRun = true;

	public FetchAllocationsJob(ScaDomainManager domMgr, ScaAllocationManager allocMgr) {
		super("Fetch allocations");
		this.domMgr = domMgr;
		this.allocMgr = allocMgr;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, IProgressMonitor.UNKNOWN);

		// Cancel if the domain manager is disposed
		if (domMgr.isDisposed()) {
			return Status.CANCEL_STATUS;
		}

		// Don't cancel, but do re-schedule for later if the domain is disconnected
		if (!DomainConnectionState.CONNECTED.equals(domMgr.getState())) {
			schedule(getDelay());
			return Status.OK_STATUS;
		}

		try {
			allocMgr.refresh(progress.split(1), RefreshDepth.CHILDREN);
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		}

		if (!progress.isCanceled()) {
			schedule(getDelay());
		}
		return Status.OK_STATUS;
	}

	@Override
	protected void canceling() {
		shouldRun = false;
	}

	@Override
	public boolean shouldRun() {
		return shouldRun;
	}

	private long getDelay() {
		IPreferenceAccessor preferences = new ScopedPreferenceAccessor(InstanceScope.INSTANCE, "gov.redhawk.sca.model.provider.refresh"); //$NON-NLS-1$
		return preferences.getLong("refreshInterval"); //$NON-NLS-1$
	}
}

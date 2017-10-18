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
package gov.redhawk.sca.model.jobs;

import java.util.Arrays;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.sca.util.SubMonitor;

/**
 * A {@link Job} that refreshes one or more {@link IRefreshable}.
 *
 * @since 21.0
 */
public class RefreshJob extends Job {

	/**
	 * Creates a {@link Job} that performs a full-depth refresh of the object(s).
	 *
	 * @param refreshables
	 * @return
	 */
	public static RefreshJob create(IRefreshable... refreshables) {
		return new RefreshJob("Refresh", Arrays.asList(refreshables), RefreshDepth.FULL);
	}

	/**
	 * Creates a {@link Job} that performs a full-depth refresh of the object(s).
	 *
	 * @param refreshables
	 * @return
	 */
	public static RefreshJob create(Iterable< ? extends IRefreshable> refreshables) {
		return new RefreshJob("Refresh", refreshables, RefreshDepth.FULL);
	}

	private Iterable< ? extends IRefreshable> refreshables;
	private RefreshDepth depth;

	/**
	 * Creates a {@link Job} that performs a refresh of the provided object(s).
	 *
	 * @param name Job name
	 * @param refreshables Objects to refresh
	 * @param depth Depth of refresh on each object
	 */
	public RefreshJob(String name, Iterable< ? extends IRefreshable> refreshables, RefreshDepth depth) {
		super(name);
		this.refreshables = refreshables;
		this.depth = depth;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor);
		for (IRefreshable refreshable : refreshables) {
			try {
				refreshable.refresh(progress.newChild(1), depth);
				if (progress.isCanceled()) {
					return Status.CANCEL_STATUS;
				}
			} catch (InterruptedException e) {
				return Status.CANCEL_STATUS;
			}
		}
		return Status.OK_STATUS;
	}

}

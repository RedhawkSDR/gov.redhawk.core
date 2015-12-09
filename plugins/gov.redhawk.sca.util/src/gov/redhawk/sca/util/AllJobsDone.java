/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package gov.redhawk.sca.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Class to provide notification when a collection of jobs are all done.
 * @since 3.3
 */
public abstract class AllJobsDone implements IJobChangeListener {

	private List<Job> jobs = new ArrayList<Job>();

	private List<IStatus> statuses = new ArrayList<IStatus>();

	public AllJobsDone() {
	}

	/**
	 * Add a job for monitoring. <b>Be careful not to start jobs before adding them.</b>
	 * @param job
	 */
	public void addJob(Job job) {
		jobs.add(job);
		job.addJobChangeListener(this);
	}

	/**
	 * Add the jobs for monitoring. <b>Be careful not to start jobs before adding them.</b>
	 * @param jobs
	 */
	public void addAllJobs(Collection<Job> jobs) {
		this.jobs.addAll(jobs);
		for (Job job : jobs) {
			job.addJobChangeListener(this);
		}
	}

	/**
	 * Called when all jobs have completed
	 * @return
	 */
	protected abstract void allDone();

	/**
	 * @return the results of the jobs
	 */
	public List<IStatus> getStatuses() {
		return statuses;
	}

	public void aboutToRun(IJobChangeEvent event) {
	}

	public void awake(IJobChangeEvent event) {
	}

	public void done(IJobChangeEvent event) {
		statuses.add(event.getResult());
		if (jobs.size() == statuses.size()) {
			allDone();
		}
	}

	public void running(IJobChangeEvent event) {
	}

	public void scheduled(IJobChangeEvent event) {
	}

	public void sleeping(IJobChangeEvent event) {
	}

}

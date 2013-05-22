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
package gov.redhawk.sca.internal.ui;

import gov.redhawk.sca.ui.ScaUiPlugin;

import org.eclipse.core.runtime.jobs.Job;

/**
 * 
 */
public abstract class ScaUiModelJob extends Job {

	public static final String JOB_FAMILY = ScaUiPlugin.PLUGIN_ID + ".jobFamily";

	private static boolean shouldRun = true;

	public static void setShouldRun(final boolean shouldRun) {
		ScaUiModelJob.shouldRun = shouldRun;
	}

	public ScaUiModelJob(final String name) {
		super(name);
		setUser(true);
	}

	@Override
	public boolean belongsTo(final Object family) {
		return ScaUiModelJob.JOB_FAMILY.equals(family);
	}

	@Override
	public boolean shouldRun() {
		return ScaUiModelJob.shouldRun && super.shouldRun();
	}

	@Override
	public boolean shouldSchedule() {
		return ScaUiModelJob.shouldRun && super.shouldSchedule();
	}

}

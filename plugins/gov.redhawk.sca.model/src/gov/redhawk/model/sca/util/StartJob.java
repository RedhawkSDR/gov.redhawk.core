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
package gov.redhawk.model.sca.util;

import gov.redhawk.model.sca.ScaModelPlugin;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import CF.ResourceOperations;
import CF.ResourcePackage.StartError;

/**
 * @since 14.0
 * 
 */
public class StartJob extends Job {

	private ResourceOperations resource;
	private String resourceName;

	public StartJob(String name, ResourceOperations resource) {
		super("Starting " + name);
		this.resource = resource;
		this.resourceName = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		monitor.beginTask(getName(), IProgressMonitor.UNKNOWN);
		try {
			resource.start();
		} catch (StartError e) {
			return new Status(IStatus.ERROR, ScaModelPlugin.ID, "Failed to start: " + resourceName, e);
		} finally {
			monitor.done();
		}
		return Status.OK_STATUS;
	}

}

/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.sca.model.jobs;

import java.util.concurrent.ExecutionException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import CF.ResourceOperations;
import CF.ResourcePackage.StopError;
import gov.redhawk.model.sca.ScaModelPlugin;
import mil.jpeojtrs.sca.util.CFErrorFormatter;
import mil.jpeojtrs.sca.util.CorbaUtils2;

/**
 * @since 22.0
 */
public class StopJob extends Job {

	private ResourceOperations resource;
	private String resourceName;

	public StopJob(String name, ResourceOperations resource) {
		super("Stopping " + name);
		this.resource = resource;
		this.resourceName = name;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		monitor.beginTask(getName(), IProgressMonitor.UNKNOWN);
		try {
			return CorbaUtils2.invoke(() -> {
				try {
					resource.stop();
					return Status.OK_STATUS;
				} catch (StopError e) {
					return new Status(IStatus.ERROR, ScaModelPlugin.ID, CFErrorFormatter.format(e, this.resourceName), e);
				}
			}, monitor);
		} catch (ExecutionException e) {
			String errorMsg = String.format("Unable to stop %s", this.resourceName);
			return new Status(IStatus.ERROR, ScaModelPlugin.ID, errorMsg, e.getCause());
		}
	}
}

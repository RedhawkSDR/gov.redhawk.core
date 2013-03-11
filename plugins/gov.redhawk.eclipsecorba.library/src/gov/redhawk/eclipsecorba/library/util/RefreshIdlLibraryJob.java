/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.eclipsecorba.library.util;

import gov.redhawk.eclipsecorba.library.IdlLibrary;
import gov.redhawk.eclipsecorba.library.LibraryPlugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * A {@link Job} used for refreshing an {@link IdlLibrary}.
 * 
 * @since 5.2
 */
public class RefreshIdlLibraryJob extends Job {

	private final IdlLibrary library;

	protected RefreshIdlLibraryJob(final String mesg, final IdlLibrary library) {
		super(mesg);
		this.library = library;
		setPriority(Job.LONG);
	}

	public RefreshIdlLibraryJob(final IdlLibrary library) {
		this("Refresh IDL Library", library);
	}

	@Override
	public boolean shouldSchedule() {
		return super.shouldSchedule() && this.library != null;
	}

	@Override
	protected IStatus run(final IProgressMonitor monitor) {
		monitor.beginTask("Refreshing IDL Library...", IProgressMonitor.UNKNOWN);
		try {
			this.library.load(monitor);
		} catch (final CoreException e) {
			return new Status(e.getStatus().getSeverity(), LibraryPlugin.PLUGIN_ID, e.getMessage(), e);
		}
		return Status.OK_STATUS;
	}

	protected IdlLibrary getLibrary() {
		return this.library;
	}

}

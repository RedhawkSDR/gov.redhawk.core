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

import java.util.concurrent.ExecutionException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;

import CF.LifeCycleOperations;
import CF.LifeCyclePackage.ReleaseError;
import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaWaveform;
import mil.jpeojtrs.sca.util.CorbaUtils2;

/**
 * Interruptible job that performs a CORBA call to {@link LifeCycleOperations#releaseObject()}.
 * 
 * @since 21.1
 */
public class ReleaseJob extends Job {

	private final LifeCycleOperations obj;
	private String name;

	public ReleaseJob(String name, LifeCycleOperations obj) {
		super("Releasing " + name);
		this.obj = obj;
		this.name = name;
	}

	public ReleaseJob(LifeCycleOperations obj) {
		super("Releasing");
		this.obj = obj;
		this.name = null;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		if (name == null) {
			if (obj instanceof ScaWaveform) {
				name = ((ScaWaveform) obj).getName();
			} else if (obj instanceof ScaAbstractComponent) {
				name = ((ScaAbstractComponent< ? >) obj).getIdentifier();
			} else {
				name = obj.toString();
			}
		}

		SubMonitor progress = SubMonitor.convert(monitor, "Releasing: " + name, IProgressMonitor.UNKNOWN);
		try {
			return CorbaUtils2.invoke(() -> {
				try {
					obj.releaseObject();
					return Status.OK_STATUS;
				} catch (final ReleaseError e) {
					return new Status(IStatus.ERROR, ScaModelPlugin.ID, "Failed to release: " + name, e);
				}
			}, progress);
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			return new Status(IStatus.ERROR, ScaModelPlugin.ID, cause.getMessage(), cause);
		} finally {
			progress.done();
		}
	}

}

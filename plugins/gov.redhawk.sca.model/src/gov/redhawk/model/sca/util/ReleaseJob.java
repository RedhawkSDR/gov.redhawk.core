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
package gov.redhawk.model.sca.util;

import java.util.concurrent.Callable;

import org.eclipse.core.runtime.CoreException;
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
import mil.jpeojtrs.sca.util.CorbaUtils;

/**
 * Interruptible job that performs a CORBA call to {@link LifeCycleOperations#releaseObject()}.
 * @since 20.0
 */
public class ReleaseJob extends Job {

	private final LifeCycleOperations obj;

	public ReleaseJob(LifeCycleOperations obj) {
		super("Releasing");
		this.obj = obj;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		final String releaseName;
		if (obj instanceof ScaWaveform) {
			releaseName = ((ScaWaveform) obj).getName();
		} else if (obj instanceof ScaAbstractComponent) {
			releaseName = ((ScaAbstractComponent<?>) obj).getIdentifier();
		} else {
			releaseName = obj.toString();
		}

		SubMonitor progress = SubMonitor.convert(monitor, "Releasing: " + releaseName, IProgressMonitor.UNKNOWN);
		try {
			CorbaUtils.invoke(new Callable<Object>() {

				@Override
				public Object call() throws Exception {
					try {
						obj.releaseObject();
					} catch (final ReleaseError e) {
						throw new CoreException(new Status(IStatus.ERROR, ScaModelPlugin.ID, "Failed to release: " + releaseName, e));
					}
					return null;
				}

			}, progress);
			return Status.OK_STATUS;
		} catch (CoreException e) {
			return new Status(e.getStatus().getSeverity(), ScaModelPlugin.ID, "Failed to release: " + releaseName, e);
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		} finally {
			progress.done();
		}
	}

}

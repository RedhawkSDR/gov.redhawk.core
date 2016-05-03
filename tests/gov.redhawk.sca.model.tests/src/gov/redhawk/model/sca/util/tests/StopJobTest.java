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
package gov.redhawk.model.sca.util.tests;

import org.junit.Test;

import CF.ErrorNumberType;
import CF.ResourceOperations;
import CF.ResourcePackage.StopError;
import gov.redhawk.model.sca.tests.stubs.AbstractResourceImpl;
import gov.redhawk.model.sca.util.StopJob;
import junit.framework.Assert;

public class StopJobTest {

	@Test
	public void errorReported() throws InterruptedException {
		ResourceOperations resource = new AbstractResourceImpl() {
			@Override
			public void stop() throws StopError {
				throw new StopError(ErrorNumberType.CF_EINTR, "Test message");
			}
		};
		StopJob job = new StopJob("StopErrorResource", resource);
		job.schedule();
		job.join();
		String message = job.getResult().getMessage();
		Assert.assertEquals("Error while stopping StopErrorResource. CF.ResourcePackage.StopError: Test message (error number CF_EINTR)", message);
	}

}

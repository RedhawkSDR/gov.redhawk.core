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

import org.junit.Assert;
import org.junit.Test;

import CF.ErrorNumberType;
import CF.ResourceOperations;
import CF.ResourcePackage.StartError;
import gov.redhawk.model.sca.tests.stubs.AbstractResourceImpl;
import gov.redhawk.model.sca.util.StartJob;

public class StartJobTest {

	@Test
	public void errorReported() throws InterruptedException {
		ResourceOperations resource = new AbstractResourceImpl() {
			@Override
			public void start() throws StartError {
				throw new StartError(ErrorNumberType.CF_EIO, "Test message");
			}
		};
		StartJob job = new StartJob("StartErrorResource", resource);
		job.schedule();
		job.join();
		String message = job.getResult().getMessage();
		Assert.assertEquals("Error while starting StartErrorResource. CF.ResourcePackage.StartError: Test message (error number CF_EIO)", message);
	}
	
}

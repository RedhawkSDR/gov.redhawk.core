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
package gov.redhawk.sca.ui.tests;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.sca.ui.describers.ScaDeviceManagerDescriber;
import gov.redhawk.sca.ui.editors.IScaContentDescriber;

public class ScaDeviceManagerDescriberTest {

	@Test
	public void describeDeviceManager() throws IOException {
		ScaDeviceManager devMgr = ScaFactory.eINSTANCE.createScaDeviceManager();
		Assert.assertEquals(IScaContentDescriber.VALID, new ScaDeviceManagerDescriber().describe(devMgr));
	}

	@Test
	public void describeNonDeviceManager() throws IOException {
		IScaContentDescriber describer = new ScaDeviceManagerDescriber();
		Assert.assertEquals(IScaContentDescriber.INVALID, describer.describe(ScaFactory.eINSTANCE.createScaDevice()));
		Assert.assertEquals(IScaContentDescriber.INVALID, describer.describe(null));
	}
}

package gov.redhawk.sca.tests;
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

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.sca.ScaPlugin;

import org.junit.Test;


public class SimpleDomainTest {

	@Test
	public void testSimpleDomain() {
		ScaDomainManager domain = ScaPlugin.getDefault().createTransientDomain("test", null);
		domain.getEnabledDataProviders();
	}
}

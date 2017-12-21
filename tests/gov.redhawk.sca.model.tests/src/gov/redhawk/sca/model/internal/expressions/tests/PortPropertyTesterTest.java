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
package gov.redhawk.sca.model.internal.expressions.tests;

import org.junit.Assert;
import org.junit.Test;

import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.sca.model.internal.expressions.PortPropertyTester;
import mil.jpeojtrs.sca.scd.Provides;
import mil.jpeojtrs.sca.scd.ScdFactory;
import mil.jpeojtrs.sca.scd.Uses;

public class PortPropertyTesterTest {

	private static final String IDL_BULKIO_LONG = "IDL:BULKIO/dataLong";
	private static final String IDL_BULKIO_WILDCARD = "IDL:BULKIO/data.*";
	private static final String IDL_BURSTIO_FLOAT = "IDL:BURSTIO/burstFloat";

	@Test
	public void uses() {
		Uses uses = ScdFactory.eINSTANCE.createUses();
		uses.setRepID(IDL_BULKIO_LONG);
		ScaUsesPort port = ScaFactory.eINSTANCE.createScaUsesPort();
		port.setProfileObj(uses);

		PortPropertyTester tester = new PortPropertyTester();
		Assert.assertTrue(tester.test(port, "interface", new Object[0], IDL_BULKIO_WILDCARD));
		Assert.assertTrue(tester.test(port, "interface", new Object[0], IDL_BULKIO_LONG));
		Assert.assertFalse(tester.test(port, "interface", new Object[0], IDL_BURSTIO_FLOAT));
	}

	@Test
	public void provides() {
		Provides provides = ScdFactory.eINSTANCE.createProvides();
		provides.setRepID(IDL_BULKIO_LONG);
		ScaProvidesPort port = ScaFactory.eINSTANCE.createScaProvidesPort();
		port.setProfileObj(provides);

		PortPropertyTester tester = new PortPropertyTester();
		Assert.assertTrue(tester.test(port, "interface", new Object[0], IDL_BULKIO_WILDCARD));
		Assert.assertTrue(tester.test(port, "interface", new Object[0], IDL_BULKIO_LONG));
		Assert.assertFalse(tester.test(port, "interface", new Object[0], IDL_BURSTIO_FLOAT));
	}
}

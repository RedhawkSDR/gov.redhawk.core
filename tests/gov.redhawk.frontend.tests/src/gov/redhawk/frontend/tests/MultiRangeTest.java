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
package gov.redhawk.frontend.tests;

import org.junit.Assert;
import org.junit.Test;

import gov.redhawk.frontend.util.MultiRange;

public class MultiRangeTest {

	@Test
	public void test() {
		MultiRange mr = new MultiRange();

		mr.addRange("bad");
		Assert.assertFalse(mr.hasRanges());
		Assert.assertTrue(mr.inRange(1.0));
		Assert.assertTrue(mr.inRange(2.0));
		Assert.assertTrue(mr.inRange(3.0));

		mr.addRange(" 1 - 2 , 3 , 4 - 5 ");
		Assert.assertTrue(mr.hasRanges());
		Assert.assertFalse(mr.inRange(0.5));
		Assert.assertTrue(mr.inRange(1.0));
		Assert.assertTrue(mr.inRange(1.5));
		Assert.assertTrue(mr.inRange(2.0));
		Assert.assertFalse(mr.inRange(2.5));
		Assert.assertTrue(mr.inRange(3.0));
		Assert.assertFalse(mr.inRange(3.5));
		Assert.assertTrue(mr.inRange(4.0));
		Assert.assertTrue(mr.inRange(4.5));
		Assert.assertTrue(mr.inRange(5.0));
		Assert.assertFalse(mr.inRange(5.5));
	}

}

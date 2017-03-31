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

package gov.redhawk.swt.browser;
import gov.redhawk.swt.browser.OsVersionParser;

import org.junit.Assert;
import org.junit.Test;
import org.osgi.framework.Version;

public class BrowserInitializerTest {

	/**
	 * Tests a few versions strings for RHEL
	 */
	@Test
	public void testRedHatVersions() {
		Assert.assertEquals(new Version(5, 11, 0), OsVersionParser.getRedhatVersion("Red Hat Enterprise Linux Server release 5.11 (Tikanga)"));
		Assert.assertEquals(new Version(6, 4, 0), OsVersionParser.getRedhatVersion("Red Hat Enterprise Linux Server release 6.4 (Santiago)"));
	}

	/**
	 * Tests a few version strings for CentOS
	 */
	@Test
	public void testCentOSVersions() {
		Assert.assertEquals(new Version(6, 0, 0), OsVersionParser.getRedhatVersion("CentOS Linux release 6.0 (Final)"));
		Assert.assertEquals(new Version(6, 4, 0), OsVersionParser.getRedhatVersion("CentOS release 6.4 (Final)"));
		Assert.assertEquals(new Version(6, 5, 0), OsVersionParser.getRedhatVersion("CentOS release 6.5 (Final)"));
		Assert.assertEquals(new Version(6, 6, 0), OsVersionParser.getRedhatVersion("CentOS release 6.6 (Final)"));
		Assert.assertEquals(new Version(7, 0, 0), OsVersionParser.getRedhatVersion("CentOS Linux release 7.0.1406 (Core)"));
		Assert.assertEquals(new Version(7, 1, 0), OsVersionParser.getRedhatVersion("CentOS Linux release 7.1.1503 (Core) "));
	}

	/**
	 * Test non-matching strings
	 */
	@Test
	public void noMatch() {
		Version zeroVersion = new Version(0, 0, 0);
		Assert.assertEquals(zeroVersion, OsVersionParser.getRedhatVersion(""));
		Assert.assertEquals(zeroVersion, OsVersionParser.getRedhatVersion("Other text 1.2.3"));
	}
}

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
package gov.redhawk.sca.validation.tests;

import org.eclipse.core.databinding.validation.IValidator;
import org.junit.Assert;
import org.junit.Test;

import gov.redhawk.sca.validation.NamingServiceValidator;

public class NamingServiceValidatorTest {

	private IValidator validator = new NamingServiceValidator();

	@Test
	public void emptyOrNull() {
		Assert.assertFalse(validator.validate("").isOK());
		Assert.assertFalse(validator.validate(null).isOK());
	}

	@Test
	public void corbaname() {
		Assert.assertFalse("No protocol", validator.validate("corbaname:").isOK());

		// rir:
		Assert.assertTrue(validator.validate("corbaname:rir:").isOK());
		Assert.assertFalse("Junk after rir", validator.validate("corbaname:rir:x").isOK());

		// : (short for iiop:)
		Assert.assertTrue(validator.validate("corbaname::somehost").isOK());
		Assert.assertTrue(validator.validate("corbaname::somehost.domain.com").isOK());
		Assert.assertTrue(validator.validate("corbaname::127.0.0.1").isOK());
		Assert.assertTrue(validator.validate("corbaname::[1234:5678:90ab:cdef:1234:5678:90ab:cdef]").isOK());
		Assert.assertTrue(validator.validate("corbaname::[::1]").isOK());
		Assert.assertFalse("Missing hostname", validator.validate("corbaname::").isOK());
		Assert.assertFalse("Bad IPv4", validator.validate("corbaname::0.0.0.1").isOK());
		Assert.assertFalse("Bad IPv4", validator.validate("corbaname::127.256.0.1").isOK());
		Assert.assertFalse("Bad IPv6", validator.validate("corbaname::[1234:::4567]").isOK());
		Assert.assertFalse("Bad IPv6", validator.validate("corbaname::[1234:zyxw]").isOK());
		Assert.assertFalse("Bad hostname", validator.validate("corbaname::a.b.c-").isOK());
		Assert.assertFalse("Bad hostname", validator.validate("corbaname::a!").isOK());

		// iiop:
		Assert.assertTrue(validator.validate("corbaname:iiop:somehost").isOK());
		Assert.assertTrue(validator.validate("corbaname:iiop:somehost.domain.com").isOK());
		Assert.assertTrue(validator.validate("corbaname:iiop:127.0.0.1").isOK());
		Assert.assertTrue(validator.validate("corbaname:iiop:[1234:5678:90ab:cdef:1234:5678:90ab:cdef]").isOK());
		Assert.assertTrue(validator.validate("corbaname:iiop:[::1]").isOK());
		Assert.assertFalse("Missing hostname", validator.validate("corbaname:iiop:").isOK());
		Assert.assertFalse("Bad IPv4", validator.validate("corbaname:iiop:0.0.0.1").isOK());
		Assert.assertFalse("Bad IPv4", validator.validate("corbaname:iiop:127.256.0.1").isOK());
		Assert.assertFalse("Bad IPv6", validator.validate("corbaname:iiop:[1234:::4567]").isOK());
		Assert.assertFalse("Bad IPv6", validator.validate("corbaname:iiop:[1234:zyxw]").isOK());
		Assert.assertFalse("Bad hostname", validator.validate("corbaname:iiop:a.b.c-").isOK());
		Assert.assertFalse("Bad hostname", validator.validate("corbaname:iiop:a!").isOK());

		// Port checks
		Assert.assertTrue(validator.validate("corbaname::somehost:1234").isOK());
		Assert.assertFalse("Missing port", validator.validate("corbaname::somehost:").isOK());
		Assert.assertFalse("Invalid port", validator.validate("corbaname::somehost:0").isOK());
		Assert.assertFalse("Invalid port", validator.validate("corbaname::somehost:65536").isOK());
		Assert.assertFalse("Invalid port", validator.validate("corbaname::somehost:a").isOK());

		// Multiple addresses
		String[] addrs = { ":somehost", ":otherhost:1234", "iiop:127.0.0.1", ":[::1]", "iiop:a.b.c:456" };
		Assert.assertTrue(validator.validate("corbaname:" + String.join(",", addrs)).isOK());

		// Key string
		Assert.assertTrue(validator.validate("corbaname::somehost/").isOK());
		Assert.assertTrue(validator.validate("corbaname::somehost/abc").isOK());

		// Stringified name
		Assert.assertTrue(validator.validate("corbaname::somehost#a").isOK());
		Assert.assertTrue(validator.validate("corbaname::somehost#a/b").isOK());
		Assert.assertTrue(validator.validate("corbaname::somehost#a/b.c/./d").isOK());

		// Key + stringified name
		Assert.assertTrue(validator.validate("corbaname::somehost/abc#a/b").isOK());
	}

	@Test
	public void corbaloc() {
		Assert.assertFalse("No protocol", validator.validate("corbaloc:").isOK());

		// rir:
		Assert.assertTrue(validator.validate("corbaloc:rir:").isOK());
		Assert.assertFalse("Junk after rir", validator.validate("corbaloc:rir:x").isOK());

		// : (short for iiop:)
		Assert.assertTrue(validator.validate("corbaloc::somehost").isOK());
		Assert.assertTrue(validator.validate("corbaloc::somehost.domain.com").isOK());
		Assert.assertTrue(validator.validate("corbaloc::127.0.0.1").isOK());
		Assert.assertTrue(validator.validate("corbaloc::[1234:5678:90ab:cdef:1234:5678:90ab:cdef]").isOK());
		Assert.assertTrue(validator.validate("corbaloc::[::1]").isOK());
		Assert.assertFalse("Missing hostname", validator.validate("corbaloc::").isOK());
		Assert.assertFalse("Bad IPv4", validator.validate("corbaloc::0.0.0.1").isOK());
		Assert.assertFalse("Bad IPv4", validator.validate("corbaloc::127.256.0.1").isOK());
		Assert.assertFalse("Bad IPv6", validator.validate("corbaloc::[1234:::4567]").isOK());
		Assert.assertFalse("Bad IPv6", validator.validate("corbaloc::[1234:zyxw]").isOK());
		Assert.assertFalse("Bad hostname", validator.validate("corbaloc::a.b.c-").isOK());
		Assert.assertFalse("Bad hostname", validator.validate("corbaloc::a!").isOK());

		// iiop:
		Assert.assertTrue(validator.validate("corbaloc:iiop:somehost").isOK());
		Assert.assertTrue(validator.validate("corbaloc:iiop:somehost.domain.com").isOK());
		Assert.assertTrue(validator.validate("corbaloc:iiop:127.0.0.1").isOK());
		Assert.assertTrue(validator.validate("corbaloc:iiop:[1234:5678:90ab:cdef:1234:5678:90ab:cdef]").isOK());
		Assert.assertTrue(validator.validate("corbaloc:iiop:[::1]").isOK());
		Assert.assertFalse("Missing hostname", validator.validate("corbaloc:iiop:").isOK());
		Assert.assertFalse("Bad IPv4", validator.validate("corbaloc:iiop:0.0.0.1").isOK());
		Assert.assertFalse("Bad IPv4", validator.validate("corbaloc:iiop:127.256.0.1").isOK());
		Assert.assertFalse("Bad IPv6", validator.validate("corbaloc:iiop:[1234:::4567]").isOK());
		Assert.assertFalse("Bad IPv6", validator.validate("corbaloc:iiop:[1234:zyxw]").isOK());
		Assert.assertFalse("Bad hostname", validator.validate("corbaloc:iiop:a.b.c-").isOK());
		Assert.assertFalse("Bad hostname", validator.validate("corbaloc:iiop:a!").isOK());

		// Port checks
		Assert.assertTrue(validator.validate("corbaloc::somehost:1234").isOK());
		Assert.assertFalse("Missing port", validator.validate("corbaloc::somehost:").isOK());
		Assert.assertFalse("Invalid port", validator.validate("corbaloc::somehost:0").isOK());
		Assert.assertFalse("Invalid port", validator.validate("corbaloc::somehost:65536").isOK());
		Assert.assertFalse("Invalid port", validator.validate("corbaloc::somehost:a").isOK());

		// Multiple addresses
		String[] addrs = { ":somehost", ":otherhost:1234", "iiop:127.0.0.1", ":[::1]", "iiop:a.b.c:456" };
		Assert.assertTrue(validator.validate("corbaloc:" + String.join(",", addrs)).isOK());

		// Key string
		Assert.assertTrue(validator.validate("corbaloc::somehost/").isOK());
		Assert.assertTrue(validator.validate("corbaloc::somehost/abc").isOK());
	}

	@Test
	public void ior() {
		Assert.assertFalse("Bad casing on IOR", validator.validate("IoR:1234567890abcdefABCDEF").isOK());
		Assert.assertFalse("IOR with no body", validator.validate("IOR:").isOK());
		Assert.assertFalse("IOR with odd number of hex digits", validator.validate("IOR:1234567890abcdefABCDEF1").isOK());
		Assert.assertFalse("IOR with non-hex digit", validator.validate("IOR:1234567890abcdefABCDEFxx").isOK());
		Assert.assertTrue(validator.validate("IOR:1234567890abcdefABCDEF").isOK());
	}

	/**
	 * Test examples from the corbaloc specification.
	 */
	@Test
	public void corbalocExamples() {
		Assert.assertTrue(validator.validate("corbaloc::555xyz.com/Prod/TradingService").isOK());
		Assert.assertTrue(validator.validate("corbaloc:iiop:1.1@555xyz.com/Prod/TradingService").isOK());
		Assert.assertTrue(validator.validate("corbaloc::555xyz.com,:556xyz.com:80/Dev/NameService").isOK());
		Assert.assertTrue(validator.validate("corbaloc:rir:/TradingService").isOK());
		Assert.assertTrue(validator.validate("corbaloc:rir:/NameService").isOK());
		Assert.assertTrue(validator.validate("corbaloc:iiop:192.168.14.25:555/NameService").isOK());
		Assert.assertTrue(validator.validate("corbaloc::[1080::8:800:200C:417A]:88/DefaultEventChannel").isOK());
	}

	/**
	 * Test examples from the corbaname specification.
	 */
	@Test
	public void corbanameExamples() {
		Assert.assertTrue(validator.validate("corbaname::555xyz.com/dev/NContext1#a/b/c").isOK());
		Assert.assertTrue(validator.validate("corbaname::555xyz.com#a/b/c").isOK());
		Assert.assertTrue(validator.validate("corbaname:rir:#a/b/c").isOK());
		Assert.assertTrue(validator.validate("corbaname:rir:").isOK());
		Assert.assertTrue(validator.validate("corbaname:rir:/NameService").isOK());
	}
}

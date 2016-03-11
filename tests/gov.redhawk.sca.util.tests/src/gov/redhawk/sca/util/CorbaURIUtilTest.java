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
package gov.redhawk.sca.util;

import org.junit.Assert;
import org.junit.Test;

public class CorbaURIUtilTest {

	@Test
	public void addDefaultPrefix() {
		// Non-prefixed
		Assert.assertEquals("corbaname::localhost", CorbaURIUtil.addDefaultPrefix("localhost"));
		Assert.assertEquals("corbaname::127.0.0.1", CorbaURIUtil.addDefaultPrefix("127.0.0.1"));

		// Already prefixed
		Assert.assertEquals("corbaname::localhost", CorbaURIUtil.addDefaultPrefix("corbaname::localhost"));
		Assert.assertEquals("corbaname:iiop:localhost", CorbaURIUtil.addDefaultPrefix("corbaname:iiop:localhost"));
		Assert.assertEquals("corbaloc::localhost", CorbaURIUtil.addDefaultPrefix("corbaloc::localhost"));
		Assert.assertEquals("corbaloc:iiop:localhost", CorbaURIUtil.addDefaultPrefix("corbaloc:iiop:localhost"));
		Assert.assertEquals("IOR:123456", CorbaURIUtil.addDefaultPrefix("IOR:123456"));
	}

	@Test
	public void addDefaultPort() {
		Assert.assertEquals("corbaname::localhost:2809", CorbaURIUtil.addDefaultPort("corbaname::localhost"));
		Assert.assertEquals("corbaname:iiop:localhost:2809", CorbaURIUtil.addDefaultPort("corbaname:iiop:localhost"));
		Assert.assertEquals("corbaname::localhost:2809/a/b/c", CorbaURIUtil.addDefaultPort("corbaname::localhost/a/b/c"));
		Assert.assertEquals("corbaname::localhost:2809#a/b/c", CorbaURIUtil.addDefaultPort("corbaname::localhost#a/b/c"));
		Assert.assertEquals("corbaname::localhost:2809/a/b/c#d/e/f", CorbaURIUtil.addDefaultPort("corbaname::localhost/a/b/c#d/e/f"));

		Assert.assertEquals("corbaname::localhost:1234", CorbaURIUtil.addDefaultPort("corbaname::localhost:1234"));
		Assert.assertEquals("corbaname:iiop:localhost:1234", CorbaURIUtil.addDefaultPort("corbaname:iiop:localhost:1234"));
		Assert.assertEquals("corbaname::localhost:1234/a/b/c", CorbaURIUtil.addDefaultPort("corbaname::localhost:1234/a/b/c"));
		Assert.assertEquals("corbaname::localhost:1234#a/b/c", CorbaURIUtil.addDefaultPort("corbaname::localhost:1234#a/b/c"));
		Assert.assertEquals("corbaname::localhost:1234/a/b/c#d/e/f", CorbaURIUtil.addDefaultPort("corbaname::localhost:1234/a/b/c#d/e/f"));

		Assert.assertEquals("corbaloc::localhost:2809", CorbaURIUtil.addDefaultPort("corbaloc::localhost"));
		Assert.assertEquals("corbaloc:iiop:localhost:2809", CorbaURIUtil.addDefaultPort("corbaloc:iiop:localhost"));
		Assert.assertEquals("corbaloc::localhost:2809/a/b/c", CorbaURIUtil.addDefaultPort("corbaloc::localhost/a/b/c"));

		Assert.assertEquals("corbaloc::localhost:1234", CorbaURIUtil.addDefaultPort("corbaloc::localhost:1234"));
		Assert.assertEquals("corbaloc:iiop:localhost:1234", CorbaURIUtil.addDefaultPort("corbaloc:iiop:localhost:1234"));
		Assert.assertEquals("corbaloc::localhost:1234/a/b/c", CorbaURIUtil.addDefaultPort("corbaloc::localhost:1234/a/b/c"));

		Assert.assertEquals("IOR:123456", CorbaURIUtil.addDefaultPort("IOR:123456"));
	}

}

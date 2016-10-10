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
package gov.redhawk.eclipsecorba.idl.tests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gov.redhawk.eclipsecorba.idl.Definition;
import gov.redhawk.eclipsecorba.idl.ForwardDcl;
import gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl;
import gov.redhawk.eclipsecorba.idl.Module;
import gov.redhawk.eclipsecorba.idl.Specification;

public class LocalInterfaceParsingTest {

	private ResourceSet resourceSet;

	private Resource resource;

	@Before
	public void setup() throws URISyntaxException, IOException {
		resourceSet = new ResourceSetImpl();
		URL url = IdlTestPlugin.getDefault().getBundle().getEntry("idl-tests/LocalInterfaces.idl");
		resource = resourceSet.getResource(URI.createURI(FileLocator.toFileURL(url).toURI().toString()), true);
		Assert.assertTrue("Failed to parse IDL", resource.getEObject("/") instanceof Specification);
	}

	@Test
	public void localForwardDeclaredInterface() throws IOException {
		final Specification forwardDeclSpec = (Specification) resource.getEObject("/");
		final Module module = getModule(forwardDeclSpec.getDefinitions(), "LocalInterfaces");
		for (Definition def : module.getDefinitions()) {
			if ("LocalForwardDeclaredInterface".equals(def.getName())) {
				Assert.assertTrue("Wrong type for forward-declared interface", def instanceof ForwardDcl);
				ForwardDcl intf = (ForwardDcl) def;
				Assert.assertTrue("Interface should be local", intf.isLocal());
				return;
			}
		}
		Assert.fail("Failed to find LocalForwardDeclaredInterface");
	}

	@Test
	public void localInterface() throws IOException {
		final Specification forwardDeclSpec = (Specification) resource.getEObject("/");
		final Module module = getModule(forwardDeclSpec.getDefinitions(), "LocalInterfaces");
		for (Definition def : module.getDefinitions()) {
			if ("LocalInterface".equals(def.getName())) {
				Assert.assertTrue("Wrong type for interface", def instanceof IdlInterfaceDcl);
				IdlInterfaceDcl intf = (IdlInterfaceDcl) def;
				Assert.assertTrue("Interface should be local", intf.isLocal());
				Assert.assertEquals("Interface should contain an operation", 1, intf.getBody().size());
				return;
			}
		}
		Assert.fail("Failed to find LocalInterface");
	}

	private Module getModule(List<Definition> defs, String moduleName) {
		for (Definition def : defs) {
			if (def instanceof Module) {
				Module module = (Module) def;
				if (moduleName.equals(module.getName())) {
					return module;
				}
			}
		}
		Assert.assertTrue("Couldn't find module " + moduleName, false);
		return null;
	}
}

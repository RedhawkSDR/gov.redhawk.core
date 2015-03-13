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
package gov.redhawk.eclipsecorba.idl.tests;

import gov.redhawk.eclipsecorba.idl.Definition;
import gov.redhawk.eclipsecorba.idl.ForwardDcl;
import gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl;
import gov.redhawk.eclipsecorba.idl.Module;
import gov.redhawk.eclipsecorba.idl.Specification;
import gov.redhawk.eclipsecorba.idl.operations.Operation;

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

public class ForwardDeclarationParsingTest {

	private ResourceSet resourceSet;

	private Resource resource;

	@Before
	public void setUpStatic() throws URISyntaxException, IOException {
		resourceSet = new ResourceSetImpl();
		URL url = IdlTestPlugin.getDefault().getBundle().getEntry("idl-tests/ForwardDeclarations.idl");
		resource = resourceSet.getResource(URI.createURI(FileLocator.toFileURL(url).toURI().toString()), true);
		Assert.assertTrue("Failed to parse IDL", resource.getEObject("/") instanceof Specification);
	}

	@Test
	public void testMultipleForwardDeclarations() throws IOException {
		final Specification forwardDeclSpec = (Specification) resource.getEObject("/");
		final Module module = getModule(forwardDeclSpec.getDefinitions(), "MultipleForwardDeclarations");
		boolean foundDecl = false;
		int forwardDeclCount = 0;
		for (Definition def : module.getDefinitions()) {
			if (def instanceof IdlInterfaceDcl) {
				IdlInterfaceDcl interfaceDecl = (IdlInterfaceDcl) def;
				if ("A".equals(interfaceDecl.getName())) {
					foundDecl = true;
					Assert.assertEquals("Expected one operation", 1, interfaceDecl.getBody().size());
					Assert.assertTrue("Expected an operation", interfaceDecl.getBody().get(0) instanceof Operation);
					Assert.assertEquals("Expected operation 'methodB'", "methodB", ((Operation) interfaceDecl.getBody().get(0)).getName());
				}
			}
			if (def instanceof ForwardDcl) {
				ForwardDcl forwardDcl = (ForwardDcl) def;
				if ("A".equals(forwardDcl.getName())) {
					forwardDeclCount += 1;
					Assert.assertNotNull("Forward declaration doesn't reference declaration", forwardDcl.getImplementation());
				}
			}
		}
		Assert.assertTrue("Couldn't find interface declaration", foundDecl);
		Assert.assertEquals("Expected two forward declarations", 2, forwardDeclCount);
	}

	@Test
	public void testForwardDeclsAfterDecl() throws IOException {
		final Specification forwardDeclSpec = (Specification) resource.getEObject("/");
		final Module module = getModule(forwardDeclSpec.getDefinitions(), "ForwardDeclarationAfterDeclaration");
		boolean foundDecl = false;
		int forwardDeclCount = 0;
		for (Definition def : module.getDefinitions()) {
			if (def instanceof IdlInterfaceDcl) {
				IdlInterfaceDcl interfaceDecl = (IdlInterfaceDcl) def;
				if ("A".equals(interfaceDecl.getName())) {
					foundDecl = true;
					Assert.assertEquals("Expected one operation", 1, interfaceDecl.getBody().size());
					Assert.assertTrue("Expected an operation", interfaceDecl.getBody().get(0) instanceof Operation);
					Assert.assertEquals("Expected operation 'methodB'", "methodB", ((Operation) interfaceDecl.getBody().get(0)).getName());
				}
			}
			if (def instanceof ForwardDcl) {
				ForwardDcl forwardDcl = (ForwardDcl) def;
				if ("A".equals(forwardDcl.getName())) {
					forwardDeclCount += 1;
					Assert.assertNotNull("Forward declaration doesn't reference declaration", forwardDcl.getImplementation());
				}
			}
		}
		Assert.assertTrue("Couldn't find interface declaration", foundDecl);
		Assert.assertEquals("Expected a forward declaration", 1, forwardDeclCount);
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

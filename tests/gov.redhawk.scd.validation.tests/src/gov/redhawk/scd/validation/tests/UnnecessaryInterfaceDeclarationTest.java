/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.scd.validation.tests;

import gov.redhawk.scd.validation.UnnecessaryInterfaceDeclaration;

import java.io.IOException;

import mil.jpeojtrs.sca.scd.Interface;
import mil.jpeojtrs.sca.spd.SoftPkg;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UnnecessaryInterfaceDeclarationTest {

	private AbstractModelConstraint constraint;
	private ResourceSet resourceSet;
	private SoftPkg softPkg;

	@Before
	public void setUp() throws Exception {
		this.constraint = new UnnecessaryInterfaceDeclaration();
		this.resourceSet = new ResourceSetImpl();

	}

	@After
	public void tearDown() throws Exception {
		this.constraint = null;
		this.resourceSet = null;
	}

	@Test
	public void testValidateIValidationContextValidScd() throws IOException {
		this.softPkg = SoftPkg.Util.getSoftPkg(this.resourceSet.getResource(ScdValidationTestUtil.getURI("testDevice/testDevice.spd.xml"), true));
		for (Interface iface : this.softPkg.getDescriptor().getComponent().getInterfaces().getInterface()) {
			IValidationContext context = new TestValidationContext(UnnecessaryInterfaceDeclaration.ID, iface);
			Assert.assertEquals(IStatus.OK, this.constraint.validate(context).getSeverity());
		}

	}

	@Test
	public void testValidateIValidationContextInvalidScd() throws IOException {
		this.softPkg = SoftPkg.Util.getSoftPkg(this.resourceSet.getResource(ScdValidationTestUtil.getURI("testInvalidDevice/testDevice.spd.xml"), true));
		Interface iface = this.softPkg.getDescriptor().getComponent().getInterfaces().getInterface().get(0);
		IValidationContext context = new TestValidationContext(UnnecessaryInterfaceDeclaration.ID, iface);
		Assert.assertEquals(IStatus.ERROR, this.constraint.validate(context).getSeverity());
	}

}

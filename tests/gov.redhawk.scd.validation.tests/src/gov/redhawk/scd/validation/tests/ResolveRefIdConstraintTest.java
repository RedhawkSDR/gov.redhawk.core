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
package gov.redhawk.scd.validation.tests;

import gov.redhawk.scd.validation.ResolveRefIdConstraint;

import java.io.IOException;

import mil.jpeojtrs.sca.scd.ComponentRepId;
import mil.jpeojtrs.sca.scd.InheritsInterface;
import mil.jpeojtrs.sca.scd.Provides;
import mil.jpeojtrs.sca.scd.SupportsInterface;
import mil.jpeojtrs.sca.scd.Uses;
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

public class ResolveRefIdConstraintTest {

	private AbstractModelConstraint constraint;
	private ResourceSet resourceSet;
	private SoftPkg softPkg;

	@Before
	public void setUp() throws Exception {
		this.constraint = new ResolveRefIdConstraint();
		this.resourceSet = new ResourceSetImpl();
	}

	@After
	public void tearDown() throws Exception {
		this.constraint = null;
		this.resourceSet = null;
		this.softPkg = null;
	}

	@Test
	public void testValidateComponentRepId() throws IOException {
		this.softPkg = SoftPkg.Util.getSoftPkg(this.resourceSet.getResource(ScdValidationTestUtil.getURI("testDevice/testDevice.spd.xml"), true));
		ComponentRepId repId = this.softPkg.getDescriptor().getComponent().getComponentRepID();

		IValidationContext context = new TestValidationContext(ResolveRefIdConstraint.ID, repId);
		Assert.assertEquals(IStatus.OK, this.constraint.validate(context).getSeverity());
	}

	@Test
	public void testValidateInvalidRepId() throws IOException {
		this.softPkg = SoftPkg.Util.getSoftPkg(this.resourceSet.getResource(ScdValidationTestUtil.getURI("testInvalidDevice/testDevice.spd.xml"), true));
		ComponentRepId repId = this.softPkg.getDescriptor().getComponent().getComponentRepID();

		IValidationContext context = new TestValidationContext(ResolveRefIdConstraint.ID, repId);
		Assert.assertEquals(IStatus.ERROR, this.constraint.validate(context).getSeverity());
	}

	@Test
	public void testValidateSupportsInterface() throws IOException {
		this.softPkg = SoftPkg.Util.getSoftPkg(this.resourceSet.getResource(ScdValidationTestUtil.getURI("testDevice/testDevice.spd.xml"), true));
		SupportsInterface supports = this.softPkg.getDescriptor().getComponent().getComponentFeatures().getSupportsInterface().get(0);
		IValidationContext context = new TestValidationContext(ResolveRefIdConstraint.ID, supports);
		Assert.assertEquals(IStatus.OK, this.constraint.validate(context).getSeverity());
	}

	@Test
	public void testValidateInvalidSupportsInterface() throws IOException {
		this.softPkg = SoftPkg.Util.getSoftPkg(this.resourceSet.getResource(ScdValidationTestUtil.getURI("testInvalidDevice/testDevice.spd.xml"), true));
		SupportsInterface supports = this.softPkg.getDescriptor().getComponent().getComponentFeatures().getSupportsInterface().get(0);
		IValidationContext context = new TestValidationContext(ResolveRefIdConstraint.ID, supports);
		Assert.assertEquals(IStatus.ERROR, this.constraint.validate(context).getSeverity());
	}

	@Test
	public void testValidateInheritsInterface() throws IOException {
		this.softPkg = SoftPkg.Util.getSoftPkg(this.resourceSet.getResource(ScdValidationTestUtil.getURI("testDevice/testDevice.spd.xml"), true));
		InheritsInterface inherits = this.softPkg.getDescriptor().getComponent().getInterfaces().getInterface().get(0).getInheritsInterfaces().get(0);
		IValidationContext context = new TestValidationContext(ResolveRefIdConstraint.ID, inherits);
		Assert.assertEquals(IStatus.OK, this.constraint.validate(context).getSeverity());
	}

	@Test
	public void testValidateInvalidInheritsInterface() throws IOException {
		this.softPkg = SoftPkg.Util.getSoftPkg(this.resourceSet.getResource(ScdValidationTestUtil.getURI("testInvalidDevice/testDevice.spd.xml"), true));
		InheritsInterface inherits = this.softPkg.getDescriptor().getComponent().getInterfaces().getInterface().get(0).getInheritsInterfaces().get(0);
		IValidationContext context = new TestValidationContext(ResolveRefIdConstraint.ID, inherits);
		Assert.assertEquals(IStatus.ERROR, this.constraint.validate(context).getSeverity());
	}

	@Test
	public void testValidateProvides() throws IOException {
		this.softPkg = SoftPkg.Util.getSoftPkg(this.resourceSet.getResource(ScdValidationTestUtil.getURI("testComponent/testJavaComponent.spd.xml"), true));
		Provides provides = this.softPkg.getDescriptor().getComponent().getComponentFeatures().getPorts().getProvides().get(0);
		IValidationContext context = new TestValidationContext(ResolveRefIdConstraint.ID, provides);
		Assert.assertEquals(IStatus.OK, this.constraint.validate(context).getSeverity());
	}

	@Test
	public void testValidateInvalidProvides() throws IOException {
		this.softPkg = SoftPkg.Util.getSoftPkg(this.resourceSet.getResource(ScdValidationTestUtil.getURI("testInvalidComponent/testJavaComponent.spd.xml"), true));
		Provides provides = this.softPkg.getDescriptor().getComponent().getComponentFeatures().getPorts().getProvides().get(0);
		IValidationContext context = new TestValidationContext(ResolveRefIdConstraint.ID, provides);
		Assert.assertEquals(IStatus.ERROR, this.constraint.validate(context).getSeverity());
	}

	@Test
	public void testValidateUses() throws IOException {
		this.softPkg = SoftPkg.Util.getSoftPkg(this.resourceSet.getResource(ScdValidationTestUtil.getURI("testComponent/testJavaComponent.spd.xml"), true));
		Uses uses = this.softPkg.getDescriptor().getComponent().getComponentFeatures().getPorts().getUses().get(0);
		IValidationContext context = new TestValidationContext(ResolveRefIdConstraint.ID, uses);
		Assert.assertEquals(IStatus.OK, this.constraint.validate(context).getSeverity());
	}

	@Test
	public void testValidateInvalidUses() throws IOException {
		this.softPkg = SoftPkg.Util.getSoftPkg(this.resourceSet.getResource(ScdValidationTestUtil.getURI("testInvalidComponent/testJavaComponent.spd.xml"), true));
		Uses uses = this.softPkg.getDescriptor().getComponent().getComponentFeatures().getPorts().getUses().get(0);
		IValidationContext context = new TestValidationContext(ResolveRefIdConstraint.ID, uses);
		Assert.assertEquals(IStatus.ERROR, this.constraint.validate(context).getSeverity());
	}

}

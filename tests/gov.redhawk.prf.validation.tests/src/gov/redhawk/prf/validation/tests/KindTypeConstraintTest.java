/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.prf.validation.tests;

import java.io.IOException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gov.redhawk.validation.prf.KindTypeConstraint;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.Properties;

public class KindTypeConstraintTest {

	private AbstractModelConstraint constraint;
	private ResourceSet resourceSet;
	private Properties prf;

	@Before
	public void setUp() throws Exception {
		this.constraint = new KindTypeConstraint();
		this.resourceSet = new ResourceSetImpl();

		this.prf = Properties.Util.getProperties(this.resourceSet.getResource(PrfValidationTestUtil.getURI("testFiles/KindTypeConstraint.prf.xml"), true));
	}

	@After
	public void tearDown() throws Exception {
		this.constraint = null;
		this.resourceSet = null;
	}

	@Test
	public void testValidateNonNull() throws IOException {
		IValidationContext context = new TestValidationContext(KindTypeConstraint.ID, null);
		Assert.assertEquals(IStatus.OK, this.constraint.validate(context).getSeverity());
	}

	@Test
	public void testValidateKindTypeEvent() throws IOException {
		IValidationContext context;

		AbstractProperty prop = this.prf.getProperty("simple1");
		context = new TestValidationContext(KindTypeConstraint.ID, prop);
		Assert.assertEquals(IStatus.OK, this.constraint.validate(context).getSeverity());

		prop = this.prf.getProperty("simple2");
		context = new TestValidationContext(KindTypeConstraint.ID, prop);
		Assert.assertEquals(IStatus.OK, this.constraint.validate(context).getSeverity());

		prop = this.prf.getProperty("simple3");
		context = new TestValidationContext(KindTypeConstraint.ID, prop);
		Assert.assertEquals(IStatus.OK, this.constraint.validate(context).getSeverity());

		prop = this.prf.getProperty("simple4");
		context = new TestValidationContext(KindTypeConstraint.ID, prop);
		Assert.assertEquals(IStatus.OK, this.constraint.validate(context).getSeverity());

		prop = this.prf.getProperty("simple5");
		context = new TestValidationContext(KindTypeConstraint.ID, prop);
		Assert.assertEquals(IStatus.ERROR, this.constraint.validate(context).getSeverity());

		prop = this.prf.getProperty("simple6");
		context = new TestValidationContext(KindTypeConstraint.ID, prop);
		Assert.assertEquals(IStatus.ERROR, this.constraint.validate(context).getSeverity());

		prop = this.prf.getProperty("struct1");
		context = new TestValidationContext(KindTypeConstraint.ID, prop);
		Assert.assertEquals(IStatus.OK, this.constraint.validate(context).getSeverity());

		prop = this.prf.getProperty("struct2");
		context = new TestValidationContext(KindTypeConstraint.ID, prop);
		Assert.assertEquals(IStatus.OK, this.constraint.validate(context).getSeverity());

		prop = this.prf.getProperty("struct3");
		context = new TestValidationContext(KindTypeConstraint.ID, prop);
		Assert.assertEquals(IStatus.OK, this.constraint.validate(context).getSeverity());

		prop = this.prf.getProperty("struct4");
		context = new TestValidationContext(KindTypeConstraint.ID, prop);
		Assert.assertEquals(IStatus.OK, this.constraint.validate(context).getSeverity());

		prop = this.prf.getProperty("struct5");
		context = new TestValidationContext(KindTypeConstraint.ID, prop);
		Assert.assertEquals(IStatus.ERROR, this.constraint.validate(context).getSeverity());

		prop = this.prf.getProperty("struct6");
		context = new TestValidationContext(KindTypeConstraint.ID, prop);
		Assert.assertEquals(IStatus.ERROR, this.constraint.validate(context).getSeverity());
	}
}

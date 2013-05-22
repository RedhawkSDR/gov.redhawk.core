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
package gov.redhawk.partitioning.validation.tests;

import gov.redhawk.partitioning.validation.ComponentFileRefConstraint;
import junit.framework.Assert;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ComponentFileRefContstraintTest {

	private ResourceSet resourceSet;
	private SoftwareAssembly sad;
	private ComponentFileRefConstraint constraint;
	private PartitioningTestsHelper sadHelper;

	@Before
	public void setUp() throws Exception {
		this.resourceSet = new ResourceSetImpl();
		this.constraint = new ComponentFileRefConstraint();
		this.sadHelper = new PartitioningTestsHelper();
	}

	@After
	public void tearDown() throws Exception {
		this.resourceSet = null;
		this.sad = null;
		this.constraint = null;
		this.sadHelper = null;
	}

	@Test
	public void test_validateValidComponentFileRefConstraint() throws Exception { // SUPPRESS CHECKSTYLE METHOD NAME
		this.sad = SoftwareAssembly.Util.getSoftwareAssembly(this.resourceSet.getResource(
		        PartitioningTestsHelper.getURI("testFiles/ComponentFileRefConstraint.sad.xml"), true));

		Assert.assertNotNull(this.sad);
		Assert.assertNotNull(this.sad.getPartitioning().getComponentPlacement().get(0).getComponentFileRef());

		final IValidationContext ctx = this.sadHelper.createSuccessfulValidationContext(this.sad.getPartitioning().getComponentPlacement().get(0)
		        .getComponentFileRef());

		Assert.assertNotNull(ctx);
		Assert.assertTrue(this.constraint.validate(ctx).isOK());
	}

	@Test
	public void test_validateInvalidComponentFileRefConstraint() throws Exception { // SUPPRESS CHECKSTYLE METHOD NAME
		this.sad = SoftwareAssembly.Util.getSoftwareAssembly(this.resourceSet.getResource(
		        PartitioningTestsHelper.getURI("testFiles/ComponentFileRefConstraintInvalid.sad.xml"), true));

		Assert.assertNotNull(this.sad);
		Assert.assertNotNull(this.sad.getPartitioning().getComponentPlacement().get(0).getComponentFileRef());

		final IValidationContext ctx = this.sadHelper.createUnsuccessfulValidationConstraint(this.sad.getPartitioning().getComponentPlacement().get(0)
		        .getComponentFileRef());

		Assert.assertNotNull(ctx);
		Assert.assertFalse(this.constraint.validate(ctx).isOK());
	}

}

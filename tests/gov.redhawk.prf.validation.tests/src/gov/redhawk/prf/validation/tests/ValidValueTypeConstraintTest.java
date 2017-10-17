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
package gov.redhawk.prf.validation.tests;

import java.io.IOException;
import java.util.Arrays;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gov.redhawk.validation.prf.ValidValueTypeConstraint;
import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.StructValue;
import mil.jpeojtrs.sca.prf.Values;

/**
 * Note that there are also tests for the class in the bundle <code>gov.redhawk.sca.sad.validation.tests</code>.
 */
public class ValidValueTypeConstraintTest {

	private static final String CONSTRAINT_ID = "gov.redhawk.prf.validation.constraint.ValueType";
	private Properties prf;
	private AbstractModelConstraint constraint;

	@Before
	public void before() throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = PrfValidationTestUtil.getURI("testFiles/ValidValueTypeConstraint.prf.xml");
		prf = Properties.Util.getProperties(resourceSet.getResource(uri, true));
		constraint = new ValidValueTypeConstraint();
	}

	/**
	 * Validate a {@link Simple} with an invalid value.
	 */
	@Test
	public void simple() throws IOException {
		Simple simple = (Simple) prf.getProperty("simple");
		IStatus status = this.constraint.validate(new TestValidationContext(CONSTRAINT_ID, simple));
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		Assert.assertEquals(Arrays.asList("simple value", "x", "long", "Valid long values are between -2^31 and 2^31 - 1.").toString(), status.getMessage());
	}

	/**
	 * Validate a {@link SimpleSequence} with an invalid value.
	 */
	@Test
	public void simpleseq() throws IOException {
		Values values = ((SimpleSequence) prf.getProperty("simpleseq")).getValues();
		IStatus status = this.constraint.validate(new TestValidationContext(CONSTRAINT_ID, values));
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		Assert.assertEquals(Arrays.asList("simplesequence value at index 0", "x", "long", "Valid long values are between -2^31 and 2^31 - 1.").toString(),
			status.getMessage());
	}

	/**
	 * Validate a {@link Simple} inside a {@link Struct} with an invalid value.
	 */
	@Test
	public void struct_simple() throws IOException {
		Simple simple = (Simple) ((Struct) prf.getProperty("struct")).getProperty("struct::simple");
		IStatus status = this.constraint.validate(new TestValidationContext(CONSTRAINT_ID, simple));
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		Assert.assertEquals(Arrays.asList("simple value", "x", "long", "Valid long values are between -2^31 and 2^31 - 1.").toString(), status.getMessage());
	}

	/**
	 * Validate a {@link SimpleSequence} inside a {@link Struct} with an invalid value.
	 */
	@Test
	public void struct_simpleseq() throws IOException {
		Values values = ((SimpleSequence) ((Struct) prf.getProperty("struct")).getProperty("struct::simpleseq")).getValues();
		IStatus status = this.constraint.validate(new TestValidationContext(CONSTRAINT_ID, values));
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		Assert.assertEquals(Arrays.asList("simplesequence value at index 0", "x", "long", "Valid long values are between -2^31 and 2^31 - 1.").toString(),
			status.getMessage());
	}

	/**
	 * Validate a {@link Simple} inside a {@link StructSequence} with an invalid value.
	 */
	@Test
	public void structseq_simple() throws IOException {
		Simple simple = (Simple) ((StructSequence) prf.getProperty("structseq")).getStruct().getProperty("structseq::simple");
		IStatus status = this.constraint.validate(new TestValidationContext(CONSTRAINT_ID, simple));
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		Assert.assertEquals(Arrays.asList("simple value", "x", "long", "Valid long values are between -2^31 and 2^31 - 1.").toString(), status.getMessage());
	}

	/**
	 * Validate a {@link SimpleSequence} inside a {@link StructSequence} with an invalid value.
	 */
	@Test
	public void structseq_simpleseq() throws IOException {
		Values values = ((SimpleSequence) ((StructSequence) prf.getProperty("structseq")).getStruct().getProperty("structseq::simpleseq")).getValues();
		IStatus status = this.constraint.validate(new TestValidationContext(CONSTRAINT_ID, values));
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		String expected = Arrays.asList("simplesequence value at index 0", "x", "long", "Valid long values are between -2^31 and 2^31 - 1.").toString();
		Assert.assertEquals(expected, status.getMessage());
	}

	/**
	 * Validate a {@link SimpleRef} inside a {@link StructValue} of a {@link StructSequence} with an invalid value.
	 */
	@Test
	public void structseqref_simple() throws IOException {
		SimpleRef simpleRef = (SimpleRef) ((StructSequence) prf.getProperty("structseqref")).getStructValue().get(0).getRef("structseqref::simple");
		IStatus status = this.constraint.validate(new TestValidationContext(CONSTRAINT_ID, simpleRef));
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		String expected = Arrays.asList("simpleref value", "x", "long", "Valid long values are between -2^31 and 2^31 - 1.").toString();
		Assert.assertEquals(expected, status.getMessage());
	}

	/**
	 * Validate a {@link SimpleSequenceRef} inside a {@link StructValue} of a {@link StructSequence} with an invalid
	 * value.
	 */
	@Test
	public void structseqref_simpleseq() throws IOException {
		Values values = ((SimpleSequenceRef) ((StructSequence) prf.getProperty("structseqref")).getStructValue().get(0).getRef(
			"structseqref::simpleseq")).getValues();
		IStatus status = this.constraint.validate(new TestValidationContext(CONSTRAINT_ID, values));
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		String expected = Arrays.asList("simplesequenceref value at index 0", "x", "long", "Valid long values are between -2^31 and 2^31 - 1.").toString();
		Assert.assertEquals(expected, status.getMessage());
	}
}

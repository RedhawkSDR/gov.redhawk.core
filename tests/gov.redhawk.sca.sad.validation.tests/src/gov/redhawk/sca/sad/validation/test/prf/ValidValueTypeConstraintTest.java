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
package gov.redhawk.sca.sad.validation.test.prf;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.eclipse.core.runtime.IStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gov.redhawk.sca.sad.validation.test.SadSdrHelper;
import gov.redhawk.sca.sad.validation.test.TestValidationContext;
import gov.redhawk.validation.prf.ValidValueTypeConstraint;
import mil.jpeojtrs.sca.partitioning.ComponentProperties;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.prf.StructRef;
import mil.jpeojtrs.sca.prf.StructSequenceRef;
import mil.jpeojtrs.sca.prf.StructValue;
import mil.jpeojtrs.sca.prf.Values;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

/**
 * Tests {@link ValidValueTypeConstraint} as it applies to the SAD.
 */
public class ValidValueTypeConstraintTest {

	private static final String CONSTRAINT_ID = "gov.redhawk.prf.validation.constraint.ValueType";

	private ComponentProperties compProps;
	private ValidValueTypeConstraint constraint;

	@Before
	public void before() throws URISyntaxException {
		SoftwareAssembly sad = SadSdrHelper.loadSAD("/waveforms/ValidValueTypeConstraintSad/ValidValueTypeConstraintSad.sad.xml");
		compProps = sad.getAllComponentInstantiations().get(0).getComponentProperties();
		constraint = new ValidValueTypeConstraint();
	}

	/**
	 * Validate a {@link SimpleRef} with an invalid value.
	 */
	@Test
	public void simpleref() {
		SimpleRef simpleRef = compProps.getSimpleRef().get(0);
		IStatus status = this.constraint.validate(new TestValidationContext(CONSTRAINT_ID, simpleRef));
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		Assert.assertEquals(Arrays.asList("simpleref value", "x", "long", "Valid long values are between -2^31 and 2^31 - 1.").toString(), status.getMessage());
	}

	/**
	 * Validate a {@link SimpleSequenceRef} with an invalid value.
	 */
	@Test
	public void simpleseqref() {
		Values values = compProps.getSimpleSequenceRef().get(0).getValues();
		IStatus status = this.constraint.validate(new TestValidationContext(CONSTRAINT_ID, values));
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		Assert.assertEquals(Arrays.asList("simplesequenceref value at index 0", "x", "long", "Valid long values are between -2^31 and 2^31 - 1.").toString(),
			status.getMessage());
	}

	/**
	 * Validate a {@link SimpleRef} inside a {@link StructRef} with an invalid value.
	 */
	@Test
	public void struct_simple() throws IOException {
		SimpleRef simpleRef = compProps.getStructRef().get(0).getSimpleRef().get(0);
		IStatus status = this.constraint.validate(new TestValidationContext(CONSTRAINT_ID, simpleRef));
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		Assert.assertEquals(Arrays.asList("simpleref value", "x", "long", "Valid long values are between -2^31 and 2^31 - 1.").toString(), status.getMessage());
	}

	/**
	 * Validate a {@link SimpleSequenceRef} inside a {@link StructRef} with an invalid value.
	 */
	@Test
	public void struct_simpleseq() throws IOException {
		Values values = compProps.getStructRef().get(0).getSimpleSequenceRef().get(0).getValues();
		IStatus status = this.constraint.validate(new TestValidationContext(CONSTRAINT_ID, values));
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		Assert.assertEquals(Arrays.asList("simplesequenceref value at index 0", "x", "long", "Valid long values are between -2^31 and 2^31 - 1.").toString(),
			status.getMessage());
	}

	/**
	 * Validate a {@link SimpleRef} inside a {@link StructValue} of a {@link StructSequenceRef} with an invalid value.
	 */
	@Test
	public void structseqref_simple() throws IOException {
		SimpleRef simpleRef = compProps.getStructSequenceRef().get(0).getStructValue().get(0).getSimpleRef().get(0);
		IStatus status = this.constraint.validate(new TestValidationContext(CONSTRAINT_ID, simpleRef));
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		String expected = Arrays.asList("simpleref value", "x", "long", "Valid long values are between -2^31 and 2^31 - 1.").toString();
		Assert.assertEquals(expected, status.getMessage());
	}

	/**
	 * Validate a {@link SimpleSequenceRef} inside a {@link StructValue} of a {@link StructSequenceRef} with an invalid
	 * value.
	 */
	@Test
	public void structseqref_simpleseq() throws IOException {
		Values values = compProps.getStructSequenceRef().get(0).getStructValue().get(0).getSimpleSequenceRef().get(0).getValues();
		IStatus status = this.constraint.validate(new TestValidationContext(CONSTRAINT_ID, values));
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		String expected = Arrays.asList("simplesequenceref value at index 0", "x", "long", "Valid long values are between -2^31 and 2^31 - 1.").toString();
		Assert.assertEquals(expected, status.getMessage());
	}
}

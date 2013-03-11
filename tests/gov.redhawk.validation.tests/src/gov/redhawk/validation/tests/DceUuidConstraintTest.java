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
package gov.redhawk.validation.tests;

import gov.redhawk.validation.DceUuidConstraint;
import gov.redhawk.validation.DceUuidConstraint.ValidationFailure;

import org.eclipse.core.runtime.IStatus;
import org.junit.Assert;
import org.junit.Test;

public class DceUuidConstraintTest {

	private String nullString;
	private String empty = "";
	private String valid = "DCE:255cfa9b-921b-4836-a325-03b2d64dc8b0";
	private String noDCE = "255cfa9b-921b-4836-a325-03b2d64dc8b0";
	private String length = "DCE:255cfa9b-921b-4836-a325-03b2d64dc8b";
	private String format = "DCE:255cfa9b-921b-4836-a325-03b2d64dc8bz";
	private String trailer = "DCE:255cfa9b-921b-4836-a325-03b2d64dc8b0;";
	private String badMinor = "DCE:255cfa9b-921b-4836-a325-03b2d64dc8b0:z";
	private String missingMinor = "DCE:255cfa9b-921b-4836-a325-03b2d64dc8b0:";
	private String validMinor = "DCE:255cfa9b-921b-4836-a325-03b2d64dc8b0:7";

	@Test
	public void testValidateStringValid() {
		IStatus status = DceUuidConstraint.validate(this.valid);
		Assert.assertEquals(IStatus.OK, status.getSeverity());
	}

	@Test
	public void testValidateStringNull() {
		IStatus status = DceUuidConstraint.validate(this.nullString);
		Assert.assertEquals(ValidationFailure.EMPTY.getMessage(), status.getMessage());
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
	}

	@Test
	public void testValidateStringEmpty() {
		IStatus status = DceUuidConstraint.validate(this.empty);
		Assert.assertEquals(ValidationFailure.EMPTY.getMessage(), status.getMessage());
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
	}

	@Test
	public void testValidateStringNoDCE() {
		IStatus status = DceUuidConstraint.validate(this.noDCE);
		Assert.assertEquals(ValidationFailure.PREFIX.getMessage(), status.getMessage());
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
	}

	@Test
	public void testValidateStringLength() {
		IStatus status = DceUuidConstraint.validate(this.length);
		Assert.assertEquals(ValidationFailure.LENGTH.getMessage(), status.getMessage());
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
	}

	@Test
	public void testValidateStringFormat() {
		IStatus status = DceUuidConstraint.validate(this.format);
		Assert.assertEquals(ValidationFailure.FORMAT.getMessage(), status.getMessage());
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
	}

	@Test
	public void testValidateStringTrailingFormat() {
		IStatus status = DceUuidConstraint.validate(this.trailer);
		Assert.assertEquals(ValidationFailure.TRAILING_FORMAT.getMessage(), status.getMessage());
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
	}

	@Test
	public void testValidateStringMissingMinor() {
		IStatus status = DceUuidConstraint.validate(this.missingMinor);
		Assert.assertEquals(ValidationFailure.MINOR_VERSION_MISSING.getMessage(), status.getMessage());
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
	}

	@Test
	public void testValidateStringBadMinor() {
		IStatus status = DceUuidConstraint.validate(this.badMinor);
		Assert.assertEquals(ValidationFailure.MINOR_VERSION_FORMAT.getMessage(), status.getMessage());
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
	}

	@Test
	public void testValidateStringValidMinor() {
		IStatus status = DceUuidConstraint.validate(this.validMinor);
		Assert.assertEquals(IStatus.OK, status.getSeverity());
	}

}

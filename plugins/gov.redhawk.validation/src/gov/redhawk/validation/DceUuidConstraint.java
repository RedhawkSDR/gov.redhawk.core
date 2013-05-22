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
package gov.redhawk.validation;

import java.util.UUID;

import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.spd.SoftPkg;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

public class DceUuidConstraint extends AbstractModelConstraint {

	private static final int MIN_DCE_UUID_LENGTH = 40;

	private static final String DCE_PREFIX = "DCE:";

	private static final int PREFIX_LENGTH = DCE_PREFIX.length();

	private static final int UUID_LENGTH = 36;

	/**
	 * Represents the various validation failure possibilities.
	 * 
	 * @since 2.0
	 */
	public enum ValidationFailure {
		/** Validation failure caused by an empty or null value. */
		EMPTY("Enter a DCE UUID"),
		
		/** Validation failure caused by an improper prefix. */
		PREFIX("DCE UUID must start with 'DCE:'"),
		
		/** Validation failure caused by an invalid length. */
		LENGTH("Enter a DCE UUID"),
		
		/** Validation failure caused by an improper format. */
		FORMAT("Enter a valid UUID"),
		
		/** Validation failure caused by invalid trailing characters. */
		TRAILING_FORMAT("Invalid trailing data, expected ':' after UUID"),
		
		/** Validation failure caused by improper minor version. */
		MINOR_VERSION_FORMAT("Invalid DCE UUID minor version number"),
		
		/** Validation failure caused by a missing minor version. */
		MINOR_VERSION_MISSING("Missing minor version number");

		/** The message to report to the user on validation failure. */
		private String message;

		/**
		 * 
		 * @param message the String to associate with the validation failure
		 */
		private ValidationFailure(String message) {
			this.message = message;
		}

		/**
		 * Gets the message to display to the user on a validation failure.
		 * 
		 * @return the message corresponding to the failure specified
		 */
		public String getMessage() {
			return this.message;
		}

		/**
		 * Creates and returns a status object based on the validation failure.
		 * 
		 * @param e the {@link Throwable} caught during validation failure
		 * @return the {@link IStatus} resulting from the validation failure
		 */
		public IStatus getStatus(Throwable e) {
			return new Status(IStatus.ERROR, "gov.redhawk.validation", this.message, e);
		}
	}

	public DceUuidConstraint() {
	}

	@Override
	public IStatus validate(final IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		String s = "";
		if (target instanceof SoftPkg) {
			s = ((SoftPkg) target).getId();
		} else if (target instanceof Simple) {
			s = ((Simple) target).getId();
		} else if (target instanceof SimpleSequence) {
			s = ((SimpleSequence) target).getId();
		}

		final IStatus status = DceUuidConstraint.validate(s);
		if (!status.isOK()) {
			return ctx.createFailureStatus(status.getMessage());
		} else {
			return ctx.createSuccessStatus();
		}
	}

	/**
	 * @since 1.1
	 */
	public static IStatus validate(final String s) {
		if ((s == null) || (s.length() == 0)) {
			return ValidationFailure.EMPTY.getStatus(null);
		}
		if (!s.startsWith(DceUuidConstraint.DCE_PREFIX)) {
			return ValidationFailure.PREFIX.getStatus(null);
		}

		if (s.length() < DceUuidConstraint.MIN_DCE_UUID_LENGTH) {
			return ValidationFailure.LENGTH.getStatus(null);
		}

		// See if the next 36 (UUID_LENGTH) characters can become a Java UUID
		try {
			UUID.fromString(s.substring(DceUuidConstraint.PREFIX_LENGTH, DceUuidConstraint.PREFIX_LENGTH + DceUuidConstraint.UUID_LENGTH));
		} catch (final IllegalArgumentException e) {
			return ValidationFailure.FORMAT.getStatus(e);
		}

		// If their are any trailing characters they must be a ':' followed by a
		// decimal version number
		if (s.length() > DceUuidConstraint.MIN_DCE_UUID_LENGTH) {
			if (s.charAt(DceUuidConstraint.MIN_DCE_UUID_LENGTH) != ':') {
				return ValidationFailure.TRAILING_FORMAT.getStatus(null);
			} else {
				if ((s.length() > DceUuidConstraint.MIN_DCE_UUID_LENGTH + 1)) {
					try {
						Integer.parseInt(s.substring(DceUuidConstraint.MIN_DCE_UUID_LENGTH + 1, s.length()));
					} catch (final NumberFormatException e) {
						return ValidationFailure.MINOR_VERSION_FORMAT.getStatus(e);
					}
				} else {
					return ValidationFailure.MINOR_VERSION_MISSING.getStatus(null);
				}
			}
		}

		return Status.OK_STATUS;
	}
}

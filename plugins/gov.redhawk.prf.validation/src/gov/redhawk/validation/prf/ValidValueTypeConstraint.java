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
package gov.redhawk.validation.prf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.prf.Values;
import mil.jpeojtrs.sca.prf.util.PrfSwitch;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

/**
 * @since 1.1
 *
 */
public class ValidValueTypeConstraint extends AbstractModelConstraint {
	/**
	 * TODO: This is to force PRF complex value of format a+jb
	 */
	private static final Pattern COMPLEX_PATTERN_1 = Pattern.compile("[-]?\\d+(\\.\\d+)?([eE][+-]?\\d+)?[-+]?j(\\d+(\\.\\d+)?([eE][+-]?\\d+)?)?");
	private static final Pattern COMPLEX_PATTERN_2 = Pattern.compile("[-+]?j(\\d+(\\.\\d+)?([eE][+-]?\\d+)?)?");

	private static final String REASON_COMPLEX_FORMAT = " Valid format A+jB. May not contain spaces.";

	private static class ValidationSwitch extends PrfSwitch<IStatus> {
		private final IValidationContext ctx;

		public ValidationSwitch(final IValidationContext ctx) {
			this.ctx = ctx;
		}

		private IStatus createFailureStatus(boolean complex, String typeString, String value, PropertyValueType type, String reason) {
			Object[] message;
			if (complex) {
				message = new Object[] { typeString, value, "complex " + type, reason };
			} else {
				message = new Object[] { typeString, value, type, reason };
			}
			return ctx.createFailureStatus(message);
		}

		@Override
		public IStatus caseSimple(final Simple object) {
			final String value = object.getValue();
			if (value == null) {
				return ctx.createSuccessStatus();
			}
			final PropertyValueType type = object.getType();
			if (type == null) {
				return ctx.createSuccessStatus();
			}

			if (object.isComplex() && !isComplexNumber(value)) {
				return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(true, simpleTypeString(object), value, type, REASON_COMPLEX_FORMAT),
					PrfPackage.Literals.SIMPLE__VALUE);
			} else if (!(type.isValueOfType(value, object.isComplex()))) {
				return new EnhancedConstraintStatus(
					(ConstraintStatus) createFailureStatus(object.isComplex(), simpleTypeString(object), value, type, type.getFormattingHelp()),
					PrfPackage.Literals.SIMPLE__VALUE);
			}
			return ctx.createSuccessStatus();
		}

		private String simpleTypeString(Simple object) {
			return "simple value";
		}

		@Override
		public IStatus caseSimpleRef(final SimpleRef object) {
			if (!(object.getProperty() instanceof Simple)) {
				return ctx.createSuccessStatus();
			}
			Simple mySimple = object.getProperty();
			final String value = object.getValue();
			if (value == null) {
				return ctx.createSuccessStatus();
			}
			final PropertyValueType type = mySimple.getType();
			if (type == null) {
				return ctx.createSuccessStatus();
			}

			if (mySimple.isComplex() && !isComplexNumber(value)) {
				return new EnhancedConstraintStatus(
					(ConstraintStatus) createFailureStatus(true, simpleRefTypeString(object), value, type, REASON_COMPLEX_FORMAT),
					PrfPackage.Literals.SIMPLE_REF__VALUE);
			} else if (!(type.isValueOfType(value, mySimple.isComplex()))) {
				return new EnhancedConstraintStatus(
					(ConstraintStatus) createFailureStatus(mySimple.isComplex(), simpleRefTypeString(object), value, type, type.getFormattingHelp()),
					PrfPackage.Literals.SIMPLE_REF__VALUE);
			}

			return ctx.createSuccessStatus();
		}

		private String simpleRefTypeString(SimpleRef object) {
			return "simpleref value";
		}

		@Override
		public IStatus caseValues(final Values object) {
			final EObject contObj = object.eContainer();
			SimpleSequence sequence = null;
			if (contObj instanceof SimpleSequence) {
				sequence = (SimpleSequence) contObj;
			} else if (contObj instanceof SimpleSequenceRef) {
				SimpleSequenceRef ref = (SimpleSequenceRef) contObj;
				if (!(ref.getProperty() instanceof SimpleSequence)) {
					return ctx.createSuccessStatus();
				}
				sequence = ref.getProperty();
			}
			if (sequence == null) {
				return ctx.createSuccessStatus();
			}

			for (int i = 0; i < object.getValue().size(); i++) {
				String value = object.getValue().get(i);
				final PropertyValueType type = sequence.getType();
				if (sequence.isComplex() && !isComplexNumber(value)) {
					return new EnhancedConstraintStatus(
						(ConstraintStatus) createFailureStatus(true, valuesTypeString(object, i), value, type, REASON_COMPLEX_FORMAT),
						PrfPackage.Literals.VALUES__VALUE);
				} else if (!type.isValueOfType(value, sequence.isComplex())) {
					return new EnhancedConstraintStatus(
						(ConstraintStatus) createFailureStatus(sequence.isComplex(), valuesTypeString(object, i), value, type, type.getFormattingHelp()),
						PrfPackage.Literals.VALUES__VALUE);
				}
			}

			return ctx.createSuccessStatus();
		}

		private String valuesTypeString(Values object, int index) {
			EObject container = object.eContainer();
			if (container instanceof SimpleSequence) {
				return "simplesequence value at index " + index;
			} else if (container instanceof SimpleSequenceRef) {
				return "simplesequenceref value at index " + index;
			} else {
				return "value at index " + index;
			}
		}

		@Override
		public IStatus defaultCase(final EObject object) {
			return this.ctx.createSuccessStatus();
		}
	};

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(final IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		final ValidationSwitch validateSwitch = new ValidationSwitch(ctx);
		return validateSwitch.doSwitch(target);
	}

	public static boolean isComplexNumber(String value) {
		Matcher matcher = COMPLEX_PATTERN_1.matcher(value);
		if (matcher.matches()) {
			return true;
		}
		try {
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
			// PASS
		}
		matcher = COMPLEX_PATTERN_2.matcher(value);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

}

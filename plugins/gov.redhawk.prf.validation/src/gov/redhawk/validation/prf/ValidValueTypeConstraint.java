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

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.StructValue;
import mil.jpeojtrs.sca.prf.Values;
import mil.jpeojtrs.sca.prf.util.PrfSwitch;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

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

	private static class ValidationSwitch extends PrfSwitch<IStatus> {
		private final IValidationContext ctx;

		public ValidationSwitch(final IValidationContext ctx) {
			this.ctx = ctx;
		}
		
		private IStatus createFailureStatus(boolean complex, EObject container, String value, PropertyValueType type) {
			Object [] message;
			String typeString;
			if (container instanceof Simple) {
				Simple s = (Simple) container;
				typeString = "value";
			} else if (container instanceof SimpleRef) {
				SimpleRef ref = (SimpleRef) container;
				StructValue sValue = (StructValue) ref.eContainer();
				StructSequence ss = (StructSequence) sValue.eContainer();
				int index = ss.getStructValue().indexOf(sValue);
				typeString  = "value of struct sequence \"" + ss.getId() + "\" at index " + index + " for property \"" + ref.getRefID() + "\" is invalid.  ";
			} else {
				typeString = "value";
			}
			if (complex) {
				message = new Object []{typeString, value, "complex " + type, " Valid format A+jB. May not contain spaces."};
			} else {
				message = new Object []{typeString, value, type, ""};
			}
			return ctx.createFailureStatus(message);
		}

		@Override
		public IStatus caseSimple(final Simple object) {
			final String value = object.getValue();
			if (value != null) {
				final PropertyValueType type = object.getType();
				if (type != null) {
					boolean isComplexFormat = isComplexNumber(value);
					if (object.isComplex() && !isComplexFormat) { // TODO: This is to force PRF complex value of format a+jb
						return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(true, object, value, type),
							PrfPackage.Literals.SIMPLE__VALUE);
					} else if (!(type.isValueOfType(value, object.isComplex()))) {
						if (object.isComplex()) {
							return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(true, object, value, type),
								PrfPackage.Literals.SIMPLE__VALUE);
						} else {
							return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(false, object, value, type), PrfPackage.Literals.SIMPLE__VALUE);
						}
					}
				}
			}
			return super.caseSimple(object);
		}

		@Override
		public IStatus caseSimpleRef(final SimpleRef object) {
			Simple mySimple = object.getProperty();
			final String value = object.getValue();
			
			if (value != null && mySimple != null) {
				final PropertyValueType type = mySimple.getType();
				if (type != null) {
					boolean isComplexFormat = isComplexNumber(value);
					if (mySimple.isComplex() && !isComplexFormat) { // TODO: This is to force PRF complex value of format a+jb
						return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(true, object, value, type),
							PrfPackage.Literals.SIMPLE__VALUE);
					} else if (!(type.isValueOfType(value, mySimple.isComplex()))) {
						if (mySimple.isComplex()) {
							return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(true, object, value, type),
								PrfPackage.Literals.SIMPLE_REF__VALUE);
						} else {
							return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(false, object, value, type),
								PrfPackage.Literals.SIMPLE_REF__VALUE);
						}

					}
				}
			}

			return super.caseSimpleRef(object);
		}

		@Override
		public IStatus caseSimpleSequence(SimpleSequence object) {
			Values values = object.getValues();
			if (values != null) {
				final PropertyValueType type = object.getType();
				if (type != null) {
					for (String value: values.getValue()) {
						boolean isComplexFormat = isComplexNumber(value);
						if (object.isComplex() && !isComplexFormat) { // TODO: This is to force PRF complex value of format a+jb
							return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(true, object, value, type),
								PrfPackage.Literals.SIMPLE__VALUE);
						} else if (!(type.isValueOfType(value, object.isComplex()))) {
							if (object.isComplex()) {
								return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(true, object, value, type),
									PrfPackage.Literals.SIMPLE__VALUE);
							} else {
								return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(false, object, value, type), PrfPackage.Literals.SIMPLE__VALUE);
							}
						}
					}
				}
			}
			return super.caseSimpleSequence(object);
		}
		
		@Override
		public IStatus caseSimpleSequenceRef(SimpleSequenceRef refObject) {
			SimpleSequence object = refObject.getProperty();
			Values values = object.getValues();
			if (values != null) {
				final PropertyValueType type = object.getType();
				if (type != null) {
					for (String value: values.getValue()) {
						boolean isComplexFormat = isComplexNumber(value);
						if (object.isComplex() && !isComplexFormat) { // TODO: This is to force PRF complex value of format a+jb
							return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(true, object, value, type),
								PrfPackage.Literals.SIMPLE__VALUE);
						} else if (!(type.isValueOfType(value, object.isComplex()))) {
							if (object.isComplex()) {
								return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(true, object, value, type),
									PrfPackage.Literals.SIMPLE__VALUE);
							} else {
								return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(false, object, value, type), PrfPackage.Literals.SIMPLE__VALUE);
							}
						}
					}
				}
			}
			return super.caseSimpleSequenceRef(refObject);
		}
		
		@Override
		public IStatus caseValues(final Values object) {
			final EObject contObj = object.eContainer();
			SimpleSequence sequence = null;
			if (contObj instanceof SimpleSequence) {
				sequence = (SimpleSequence) contObj;
			} else if (contObj instanceof SimpleSequenceRef) {
				SimpleSequenceRef ref = (SimpleSequenceRef) contObj;
				sequence = ref.getProperty();
			}
			if (sequence != null) {
				for (final String value : object.getValue()) {
					final PropertyValueType type = sequence.getType();
					boolean isComplexFormat = isComplexNumber(value);
					if (sequence.isComplex() && !isComplexFormat) { // TODO: This is to force PRF complex value of format a+jb
						return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(true, object, value, type),
							PrfPackage.Literals.SIMPLE__VALUE);
					} else if (!type.isValueOfType(value, sequence.isComplex())) {
						if (sequence.isComplex()) {
							return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(true, object, value, type),
								PrfPackage.Literals.VALUES__VALUE);
						} else {
							return new EnhancedConstraintStatus((ConstraintStatus) createFailureStatus(false, object, value, type), PrfPackage.Literals.VALUES__VALUE);
						}

					}
				}
			} 
			return super.caseValues(object);
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

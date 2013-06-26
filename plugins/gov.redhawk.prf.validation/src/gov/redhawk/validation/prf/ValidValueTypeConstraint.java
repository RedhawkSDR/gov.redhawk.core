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

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.StructSequence;
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
	public static final Pattern COMPLEX_PATTERN = Pattern.compile("\\d+(.\\d+)?\\s*[-+]\\s*j(\\d+(.\\d+)?)?");

	private static class ValidationSwitch extends PrfSwitch<IStatus> {
		private final IValidationContext ctx;

		public ValidationSwitch(final IValidationContext ctx) {
			this.ctx = ctx;
		}

		@Override
		public IStatus caseSimple(final Simple object) {
			final String value = object.getValue();
			if (value != null) {
				final PropertyValueType type = object.getType();
				if (type != null) {
					Matcher matcher = COMPLEX_PATTERN.matcher(value);
					if (object.isComplex() && !matcher.matches()) { // TODO: This is to force PRF complex value of format a+jb
						return new EnhancedConstraintStatus((ConstraintStatus) this.ctx.createFailureStatus(value, "complex " + type),
							PrfPackage.Literals.SIMPLE__VALUE);
					} else if (!(type.isValueOfType(value, object.isComplex()))) {
						if (object.isComplex()) {
							return new EnhancedConstraintStatus((ConstraintStatus) this.ctx.createFailureStatus(value, "complex " + type),
								PrfPackage.Literals.SIMPLE__VALUE);
						} else {
							return new EnhancedConstraintStatus((ConstraintStatus) this.ctx.createFailureStatus(value, type), PrfPackage.Literals.SIMPLE__VALUE);
						}
					}
				}
			}
			return super.caseSimple(object);
		}

		@Override
		public IStatus caseSimpleRef(final SimpleRef object) {
			final String value = object.getValue();
			final EObject contObj = object.eContainer().eContainer();
			if (contObj instanceof StructSequence) {
				final StructSequence sequence = (StructSequence) contObj;
				if (sequence.getStruct() != null) {
					final List<Simple> simples = sequence.getStruct().getSimple();
					Simple mySimple = null;
					for (final Simple simple : simples) {
						if (simple.getId() != null && simple.getId().equals(object.getRefID())) {
							mySimple = simple;
							break;
						}
					}
					if (value != null && mySimple != null) {
						final PropertyValueType type = mySimple.getType();
						if (type != null) {
							Matcher matcher = COMPLEX_PATTERN.matcher(value);
							if (mySimple.isComplex() && !matcher.matches()) { // TODO: This is to force PRF complex value of format a+jb
								return new EnhancedConstraintStatus((ConstraintStatus) this.ctx.createFailureStatus(value, "complex " + type),
									PrfPackage.Literals.SIMPLE__VALUE);
							} else if (!(type.isValueOfType(value, mySimple.isComplex()))) {
								if (mySimple.isComplex()) {
									return new EnhancedConstraintStatus((ConstraintStatus) this.ctx.createFailureStatus(value, "complex " + type),
										PrfPackage.Literals.SIMPLE_REF__VALUE);
								} else {
									return new EnhancedConstraintStatus((ConstraintStatus) this.ctx.createFailureStatus(value, type),
										PrfPackage.Literals.SIMPLE_REF__VALUE);
								}

							}
						}
					}
				}
			}
			return super.caseSimpleRef(object);
		}

		@Override
		public IStatus caseValues(final Values object) {
			final EObject contObj = object.eContainer();
			if (contObj instanceof SimpleSequence) {
				final SimpleSequence sequence = (SimpleSequence) contObj;
				for (final String value : object.getValue()) {
					final PropertyValueType type = sequence.getType();
					Matcher matcher = COMPLEX_PATTERN.matcher(value);
					if (sequence.isComplex() && !matcher.matches()) { // TODO: This is to force PRF complex value of format a+jb
						return new EnhancedConstraintStatus((ConstraintStatus) this.ctx.createFailureStatus(value, "complex " + type),
							PrfPackage.Literals.SIMPLE__VALUE);
					} else if (!type.isValueOfType(value, sequence.isComplex())) {
						if (sequence.isComplex()) {
							return new EnhancedConstraintStatus((ConstraintStatus) this.ctx.createFailureStatus(value, "complex " + type),
								PrfPackage.Literals.VALUES__VALUE);
						} else {
							return new EnhancedConstraintStatus((ConstraintStatus) this.ctx.createFailureStatus(value, type), PrfPackage.Literals.VALUES__VALUE);
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

}

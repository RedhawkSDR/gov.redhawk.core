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

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Range;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.Values;
import mil.jpeojtrs.sca.prf.util.PrfSwitch;
import mil.jpeojtrs.sca.util.AnyUtils;
import mil.jpeojtrs.sca.util.math.CompareNumbers;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

/**
 * @since 1.1
 *
 */
public class ValueWithinRangeTypeConstraint extends AbstractModelConstraint {

	private static class ValidationSwitch extends PrfSwitch<IStatus> {
		private final IValidationContext ctx;

		public ValidationSwitch(final IValidationContext ctx) {
			this.ctx = ctx;
		}

		private IStatus checkWithinRange(Range range, String value, PropertyValueType type, EStructuralFeature feature) {
			if (range == null) {
				// No range to change
				return null;
			}

			String strMin = range.getMin();

			String strMax = range.getMax();

			Object obj = AnyUtils.convertString(value, type.getLiteral(), false);
			if (!(obj instanceof Number)) {
				// Can't compare non numbers
				return null;
			}
			Object minObj = AnyUtils.convertString(strMin, type.getLiteral(), false);
			Object maxObj = AnyUtils.convertString(strMax, type.getLiteral(), false);
			if (minObj instanceof Number && maxObj instanceof Number) {
				if (CompareNumbers.compare((Number) obj, (Number) minObj) < 0) {
					return new EnhancedConstraintStatus((ConstraintStatus) this.ctx.createFailureStatus(value + " is less than min value of " + strMin),
						feature);
				} else if (CompareNumbers.compare((Number) obj, (Number) maxObj) > 0) {
					return new EnhancedConstraintStatus((ConstraintStatus) this.ctx.createFailureStatus(value + " is greater than max value of " + strMax),
						feature);
				}
			}
			return ctx.createSuccessStatus();
		}

		@Override
		public IStatus caseSimple(final Simple object) {
			final String value = object.getValue();
			if (value != null && !object.isComplex()) {
				final PropertyValueType type = object.getType();
				if (type != null) {
					return checkWithinRange(object.getRange(), value, type, PrfPackage.Literals.SIMPLE__VALUE);
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
					if (value != null && mySimple != null && !mySimple.isComplex()) {
						return checkWithinRange(mySimple.getRange(), value, mySimple.getType(), PrfPackage.Literals.SIMPLE_REF__VALUE);
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
				if (!sequence.isComplex()) {
					for (final String value : object.getValue()) {
						final PropertyValueType type = sequence.getType();
						return checkWithinRange(sequence.getRange(), value, type, PrfPackage.Literals.VALUES__VALUE);
					}
				}
			}
			return super.caseValues(object);
		}

		@Override
		public IStatus defaultCase(final EObject object) {
			return this.ctx.createSuccessStatus();
		};
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

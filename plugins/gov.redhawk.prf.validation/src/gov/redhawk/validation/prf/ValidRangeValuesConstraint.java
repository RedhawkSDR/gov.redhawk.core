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

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Range;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.util.AnyUtils;
import mil.jpeojtrs.sca.util.math.CompareNumbers;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

/**
 * @since 1.1
 */
public class ValidRangeValuesConstraint extends AbstractModelConstraint {
	@Override
	public IStatus validate(IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		if (target instanceof Range) {
			Range range = (Range) target;
			String min = range.getMin();
			String max = range.getMax();

			EObject parent = range.eContainer();
			if (parent instanceof Simple) {
				Simple prop = (Simple) parent;
				PropertyValueType type = prop.getType();
				boolean complex = prop.isComplex();
				return checkValues(ctx, min, max, type, complex);
			} else if (parent instanceof SimpleSequence) {
				SimpleSequence prop = (SimpleSequence) parent;
				PropertyValueType type = prop.getType();
				boolean complex = prop.isComplex();
				return checkValues(ctx, min, max, type, complex);
			}
		}
		return null;
	}

	private IStatus checkValues(IValidationContext ctx, String min, String max, PropertyValueType type, boolean complex) {
		if (!complex && min != null && max != null && min.trim().length() > 0 && max.trim().length() > 0) {
			Object minValue = AnyUtils.convertString(min, type.getLiteral());
			Object maxValue = AnyUtils.convertString(max, type.getLiteral());
			if (minValue instanceof Number && maxValue instanceof Number && CompareNumbers.compare((Number) minValue, (Number) maxValue) > 0) {
				return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(min, max), PrfPackage.Literals.RANGE__MIN);
			}
		}
		return null;
	}

}

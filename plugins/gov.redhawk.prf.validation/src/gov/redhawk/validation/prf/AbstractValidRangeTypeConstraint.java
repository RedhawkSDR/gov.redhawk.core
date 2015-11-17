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

import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Range;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

/**
 * @since 1.1
 */
public abstract class AbstractValidRangeTypeConstraint extends AbstractModelConstraint {

	private EStructuralFeature feature;

	public AbstractValidRangeTypeConstraint(EStructuralFeature feature) {
		this.feature = feature;
	}

	@Override
	public IStatus validate(IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		if (target instanceof Range) {
			Range range = (Range) target;
			String value = (String) range.eGet(feature);
			if (value == null) {
				value = "";
			}
			EObject parent = range.eContainer();
			if (parent instanceof Simple) {
				Simple prop = (Simple) parent;
				PropertyValueType type = prop.getType();
				boolean complex = prop.isComplex();
				boolean isComplexFormat = ValidValueTypeConstraint.isComplexNumber(value);
				if (complex && !isComplexFormat) {
					return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus((complex) ? "complex " + type : type), feature);
				} else if (!type.isValueOfType(value, complex)) {
					return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus((complex) ? "complex " + type : type), feature);
				}
			} else if (parent instanceof SimpleSequence) {
				SimpleSequence prop = (SimpleSequence) parent;
				PropertyValueType type = prop.getType();
				boolean complex = prop.isComplex();
				boolean isComplexFormat = ValidValueTypeConstraint.isComplexNumber(value);
				if (complex && !isComplexFormat) {
					return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus((complex) ? "complex " + type : type), feature);
				} else if (!type.isValueOfType(value, complex)) {
					return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus((complex) ? "complex " + type : type), feature);
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}

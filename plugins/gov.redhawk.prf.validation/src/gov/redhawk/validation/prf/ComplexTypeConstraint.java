/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.validation.prf;

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;
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
public class ComplexTypeConstraint extends AbstractModelConstraint {

	private static class ValidationSwitch extends PrfSwitch<IStatus> {
		private final IValidationContext ctx;

		public ValidationSwitch(final IValidationContext ctx) {
			this.ctx = ctx;
		}

		@Override
		public IStatus caseSimple(final Simple object) {
			boolean complex = object.isComplex();
			PropertyValueType type = object.getType();
			if (!check(complex, type)) {
				return new EnhancedConstraintStatus((ConstraintStatus) this.ctx.createFailureStatus(type), PrfPackage.Literals.SIMPLE__COMPLEX);
			}
			return ctx.createSuccessStatus();
		}
		
		private boolean check(boolean complex, PropertyValueType type) {
			if (complex) {
				switch (type) {
				case BOOLEAN:
				case CHAR:
				case OBJREF:
				case STRING:
				case UTCTIME:
					return false;
				default:
					break;
				}
			}
			return true;
		}

		@Override
		public IStatus caseSimpleSequence(SimpleSequence object) {
			boolean complex = object.isComplex();
			PropertyValueType type = object.getType();
			if (!check(complex, type)) {
				return new EnhancedConstraintStatus((ConstraintStatus) this.ctx.createFailureStatus(type), PrfPackage.Literals.SIMPLE_SEQUENCE__COMPLEX);
			}
			return ctx.createSuccessStatus();
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

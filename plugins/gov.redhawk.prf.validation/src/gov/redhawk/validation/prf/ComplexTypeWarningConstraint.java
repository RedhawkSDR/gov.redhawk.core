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

import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.util.PrfSwitch;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

/**
 * 
 */
public class ComplexTypeWarningConstraint extends AbstractModelConstraint {

	private static class ValidationSwitch extends PrfSwitch<IStatus> {
		private final IValidationContext ctx;

		public ValidationSwitch(final IValidationContext ctx) {
			this.ctx = ctx;
		}

		@Override
		public IStatus caseSimple(final Simple object) {
			Boolean complex = object.getComplex();
			PropertyValueType type = object.getType();
			return check(complex, type);
		}
		
		private IStatus check(Boolean complex, PropertyValueType type) {
			if (complex != null) {
				switch (type) {
				case ULONG:
				case ULONGLONG:
				case USHORT:
				case OCTET:
					return this.ctx.createFailureStatus(type);
				default:
					break;
				}
			}
			return null;
		}

		@Override
		public IStatus caseSimpleSequence(SimpleSequence object) {
			Boolean complex = object.getComplex();
			PropertyValueType type = object.getType();
			return check(complex, type);
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

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
package gov.redhawk.validation.prf;

import mil.jpeojtrs.sca.prf.Enumeration;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.util.PrfSwitch;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

public class ValidEnumValueConstraint extends AbstractModelConstraint {

	private static class ValidationSwitch extends PrfSwitch<IStatus> {
		private final IValidationContext ctx;

		public ValidationSwitch(final IValidationContext ctx) {
			this.ctx = ctx;
		}

		@Override
		public IStatus caseSimple(final Simple object) {
			if (object.getValue() != null) {
				final String value = object.getValue();

				if (object.getEnumerations() != null) {
					boolean failed = true;
					for (final Enumeration enumeration : object.getEnumerations().getEnumeration()) {
						if (enumeration.getValue() != null) {
							if (enumeration.getValue().equals(value)) {
								failed = false;
							}
						}
					}

					if (failed) {
						return new EnhancedConstraintStatus((ConstraintStatus) this.ctx.createFailureStatus(value, object.getType()),
						        PrfPackage.Literals.SIMPLE__VALUE);
					}
				}
			}
			return super.caseSimple(object);
		}

		@Override
		public IStatus defaultCase(final EObject object) {
			return this.ctx.createSuccessStatus();
		};
	};

	@Override
	public IStatus validate(final IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		final ValidationSwitch validateSwitch = new ValidationSwitch(ctx);
		return validateSwitch.doSwitch(target);
	}

}

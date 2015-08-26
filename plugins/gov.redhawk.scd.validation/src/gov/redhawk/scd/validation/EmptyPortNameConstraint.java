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
package gov.redhawk.scd.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import mil.jpeojtrs.sca.scd.AbstractPort;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

/**
 * @since 1.2
 */
public class EmptyPortNameConstraint extends AbstractModelConstraint {

	@Override
	public IStatus validate(IValidationContext ctx) {
		AbstractPort port = (AbstractPort) ctx.getTarget();
		AbstractPort sibling = port.getSibling();
		if (sibling != null) {
			ctx.skipCurrentConstraintFor(sibling);
		}
		String name = port.getName();
		if (name == null || name.isEmpty()) {
			return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(), ScdPackage.Literals.ABSTRACT_PORT__NAME);
		}
		return ctx.createSuccessStatus();
	}
}

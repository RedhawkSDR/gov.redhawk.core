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
import mil.jpeojtrs.sca.scd.Ports;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

/**
 * @since 1.2
 */
public class DuplicatePortNameConstraint extends AbstractModelConstraint {

	@Override
	public IStatus validate(IValidationContext ctx) {
		AbstractPort port = (AbstractPort) ctx.getTarget();
		AbstractPort sibling = port.getSibling();
		if (sibling != null) {
			// Only report duplicate name once for a bi-directional port
			ctx.skipCurrentConstraintFor(sibling);
		}
		final String name = port.getName();
		Ports ports = (Ports) port.eContainer();
		if (name != null && ports != null) {
			for (AbstractPort other : ports.getAllPorts()) {
				// Ignore the target port and its sibling, which must have the same name
				if (other == port || other == sibling) {
					continue;
				}
				if (name.equals(other.getName())) {
					return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(name), ScdPackage.Literals.ABSTRACT_PORT__NAME);
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}

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
package gov.redhawk.scd.validation;

import mil.jpeojtrs.sca.scd.AbstractPort;
import mil.jpeojtrs.sca.scd.ComponentRepId;
import mil.jpeojtrs.sca.scd.InheritsInterface;
import mil.jpeojtrs.sca.scd.Interface;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.scd.SupportsInterface;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

public class ResolveRefIdConstraint extends AbstractModelConstraint {

	public static final String SOURCE_ID = "gov.redhawk.scd.validation";

	/**
	 * @since 1.1
	 */
	public static final String ID = "gov.redhawk.validation.constraint.scd.validRefId";

	public static final int STATUS_CODE = 1000;

	public ResolveRefIdConstraint() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IStatus validate(final IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		if (target instanceof ComponentRepId) {
			final ComponentRepId cp = (ComponentRepId) target;
			if (cp.getRepid() != null) {
				final Interface intfx = cp.getInterface();
				if (intfx == null) {
					final EnhancedConstraintStatus status = new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(cp.getRepid()),
					        ScdPackage.Literals.COMPONENT_REP_ID__REPID);
					return status;
				}
			}
		} else if (target instanceof SupportsInterface) {
			final SupportsInterface si = (SupportsInterface) target;
			if (si.getRepId() != null) {
				final Interface intfx = si.getInterface();
				if (intfx == null) {
					final EnhancedConstraintStatus status = new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(si.getRepId()),
					        ScdPackage.Literals.SUPPORTS_INTERFACE__REP_ID);
					return status;
				}
			}
		} else if (target instanceof InheritsInterface) {
			final InheritsInterface ii = (InheritsInterface) target;
			if (ii.getRepid() != null) {
				final Interface intfx = ii.getInterface();
				if (intfx == null) {
					final EnhancedConstraintStatus status = new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(ii.getRepid()),
					        ScdPackage.Literals.INHERITS_INTERFACE__REPID);
					return status;
				}
			}
		} else if (target instanceof AbstractPort) {
			final AbstractPort p = (AbstractPort) target;
			if (p.getRepID() != null) {
				final Interface intfx = p.getInterface();
				if (intfx == null) {
					final EnhancedConstraintStatus status = new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(p.getRepID()),
					        ScdPackage.Literals.ABSTRACT_PORT__REP_ID);
					return status;
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}

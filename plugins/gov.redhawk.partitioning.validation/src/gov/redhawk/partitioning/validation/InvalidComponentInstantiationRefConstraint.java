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
package gov.redhawk.partitioning.validation;

import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiationRef;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

/**
 * @since 2.1
 */
public class InvalidComponentInstantiationRefConstraint extends AbstractModelConstraint {
	public static final String SOURCE_ID = PartitioningValidationConstants.SOURCE_ID;
	public static final int STATUS_CODE = 1002;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IStatus validate(final IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		if (target instanceof ComponentInstantiationRef) {
			final ComponentInstantiationRef< ? > ref = (ComponentInstantiationRef< ? >) target;
			final ComponentInstantiation inst = ref.getInstantiation();
			if (inst == null) {
				final EnhancedConstraintStatus status = new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(ref.getRefid()),
				        PartitioningPackage.Literals.COMPONENT_INSTANTIATION_REF__REFID);
				return status;
			}
		}
		return ctx.createSuccessStatus();
	}

}

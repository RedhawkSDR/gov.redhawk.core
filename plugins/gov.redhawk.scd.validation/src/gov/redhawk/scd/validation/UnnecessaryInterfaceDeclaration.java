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
package gov.redhawk.scd.validation;

import java.util.Collection;

import mil.jpeojtrs.sca.scd.Interface;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

/**
 * 
 */
public class UnnecessaryInterfaceDeclaration extends AbstractModelConstraint {

	public static final String SOURCE_ID = "gov.redhawk.scd.validation";
	/**
     * @since 1.1
     */
	public static final String ID = "gov.redhawk.scd.validation.unnecessaryInterfaceDeclaration";

	public static final int STATUS_CODE = 1001;

	public UnnecessaryInterfaceDeclaration() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IStatus validate(final IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		if (target instanceof Interface) {
			final Interface inter = (Interface) target;
			final Collection<Setting> references = EcoreUtil.UsageCrossReferencer.find(inter, inter.eResource());
			if (references.size() == 0) {
				final EnhancedConstraintStatus status = new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(inter.getRepid()),
				        ScdPackage.Literals.INTERFACES__INTERFACE);
				return status;

			}
		}
		return ctx.createSuccessStatus();
	}

}

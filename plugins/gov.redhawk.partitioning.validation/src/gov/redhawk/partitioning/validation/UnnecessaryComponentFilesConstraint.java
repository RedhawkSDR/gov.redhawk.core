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
package gov.redhawk.partitioning.validation;

import java.util.Collection;

import mil.jpeojtrs.sca.partitioning.ComponentFile;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

/**
 * @since 2.0
 */
public class UnnecessaryComponentFilesConstraint extends AbstractModelConstraint {

	public static final String SOURCE_ID = PartitioningValidationConstants.SOURCE_ID;
	public static final int STATUS_CODE = 1001;

	public UnnecessaryComponentFilesConstraint() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IStatus validate(final IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		if (target instanceof ComponentFile) {
			final ComponentFile file = (ComponentFile) target;
			final Collection<Setting> references = EcoreUtil.UsageCrossReferencer.find(file, file.eResource());
			if (references.size() == 0) {
				final EnhancedConstraintStatus status = new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(file.getLocalFile().getName()),
				        PartitioningPackage.Literals.COMPONENT_FILES__COMPONENT_FILE);
				return status;

			}
		}
		return ctx.createSuccessStatus();
	}

}

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
package gov.redhawk.spd.internal.validation;

import mil.jpeojtrs.sca.spd.Code;
import mil.jpeojtrs.sca.spd.CodeFileType;
import mil.jpeojtrs.sca.spd.SpdPackage;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;


public class EntryPointConstraint extends AbstractModelConstraint {

	public static final String CODE_ENTRYPOINT_ID = "gov.redhawk.spd.validation.codeentrypoint_constraint";

	@Override
	public IStatus validate(final IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		IStatus retVal = null;
		if (ctx.getCurrentConstraintId().equals(EntryPointConstraint.CODE_ENTRYPOINT_ID)) {
			final Code code = (Code) target;
			if ((code.getLocalFile() != null) && (!code.getType().equals(CodeFileType.NODE_BOOTER))) {
				retVal = createEntryPointFailureStatus(ctx, code, SpdPackage.Literals.CODE__ENTRY_POINT);
			}
		}
		if (retVal != null) {
			return retVal;
		} else {
			return ctx.createSuccessStatus();
		}
	}

	private ConstraintStatus createEntryPointFailureStatus(final IValidationContext ctx, final Code code, final EAttribute eReference) {
		final String s = code.getEntryPoint();
		if (s != null) {
			if (!"".equals(s)) {
				final IProject project = this.getProject(code);
				if (project != null && project.findMember(s) == null) {
					return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(s), eReference);
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param eObject
	 * @return
	 */
	private IProject getProject(final EObject eObject) {
		if (eObject == null) {
			return null;
		}
		final IResource resource = getResource(eObject.eResource());
		if (resource != null) {
			return resource.getProject();
		} else {
			return null;
		}
	}

	/**
	 * Gets the resource.
	 * 
	 * @param eResource the e resource
	 * 
	 * @return the resource
	 */
	private IFile getResource(final Resource eResource) {
		return WorkspaceSynchronizer.getFile(eResource);
	}

}

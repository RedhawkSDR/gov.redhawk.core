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
package gov.redhawk.sca.sad.validation;

import java.util.regex.Pattern;

import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

public class VersionConstraint extends AbstractModelConstraint {

	/**
	 * Valid examples matching the pattern:
	 * <ul>
	 * <li>1</li>
	 * <li>1.2</li>
	 * <li>1.2.3</li>
	 * <li>1.2.3.foo</li>
	 * </ul>
	 */
	private Pattern versionRegex = Pattern.compile("^\\d+(\\.\\d+(\\.\\d+(\\.\\S+)?)?)?$");

	public VersionConstraint() {
	}

	@Override
	public IStatus validate(IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		if (target instanceof SoftwareAssembly) {
			final String version = ((SoftwareAssembly) target).getVersion();
			if (version != null) {
				if (!versionRegex.matcher(version).find()) {
					return ctx.createFailureStatus(version);
				} else {
					return ctx.createSuccessStatus();
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}

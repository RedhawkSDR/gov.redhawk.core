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
package gov.redhawk.ui.validation;

import java.util.regex.Pattern;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;

/**
 * A databinding validator for interface names.
 * @since 6.0
 */
public class InterfaceNameValidator implements IValidator {
	// Rules:
	// A interface name must not end in '.' (on windows)
	// A interface name must not have "/" in it
	// A interface name must not be ''
	// A interface name must not start with a number
	// A interface name must not contain spaces

	/**
	 * @since 5.1
	 */
	public static final String VALID_IMPL_NAME_REGEX = "^[A-Za-z][A-Za-z0-9_-]*";

	/**
	 * {@inheritDoc}
	 */
	public IStatus validate(final Object value) {
		final String s = ((String) value); 
		// Project names are always stripped of whitespace (see the Java Project Wizard)
		if ((s == null) || (s.length() == 0)) {
			return ValidationStatus.error("Enter an interface name.");
		}

		if (!Pattern.matches(InterfaceNameValidator.VALID_IMPL_NAME_REGEX, s)) {
			return ValidationStatus.error("Invalid character present in interface name.");
		}

		final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		if (!root.getLocation().isValidSegment(s)) {
			return ValidationStatus.error("Enter a valid interface name.");
		}

		return ValidationStatus.ok();
	}
}
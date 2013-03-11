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

import gov.redhawk.ui.RedhawkUiActivator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;

/**
 * A databinding validator for project names.
 */
public class ProjectNameValidator implements IValidator {
	// Rules:
	// A project name must not end in '.' (on windows)
	// A project name must not have "/" in it
	// A project name must not already be in use
	// A project name must not be ''
	// A project name must not start with a number
	// A project name must not contain spaces

	/**
	 * @since 5.1
	 */
	public static final String VALID_IMPL_NAME_REGEX = "^[A-Za-z][A-Za-z0-9_-]*";

	/**
	 * {@inheritDoc}
	 */
	public IStatus validate(final Object value) {
		// Project names are always stripped of whitespace (see the Java Project Wizard)
		final String s = ((String) value);
		if ((s == null) || (s.length() == 0)) {
			return ValidationStatus.error("Enter a project name.");
		}

		if (!Pattern.matches(ProjectNameValidator.VALID_IMPL_NAME_REGEX, s)) {
			return ValidationStatus.error("Invalid character present in project name.");
		}

		final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		if (!root.getLocation().isValidSegment(s)) {
			return ValidationStatus.error("Enter a valid project name.");
		}

		// TODO on Window verify that we don't end in '.'
		final IProject project = root.getProject(s);
		if (project.exists()) {
			return ValidationStatus.error("A project with this name already exists.");
		} else {
			try {
				if (this.hasExistingContent(new URI(root.getRawLocationURI().toString() + IPath.SEPARATOR + s))) {
					return ValidationStatus.error("A filesystem resource already exists at the specified location.");
				}
			} catch (final URISyntaxException e) {
				return ValidationStatus.error("Enter a valid project name.");
			}
		}

		return ValidationStatus.ok();
	}

	private boolean hasExistingContent(final URI realLocation) {
		IFileStore file = null;
		try {
			file = EFS.getStore(realLocation);
		} catch (final CoreException e) {
			RedhawkUiActivator.logException(e);
			return false;
		}
		return file.fetchInfo().exists();
	}
}

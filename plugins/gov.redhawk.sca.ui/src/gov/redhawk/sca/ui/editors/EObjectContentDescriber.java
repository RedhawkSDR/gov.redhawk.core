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
package gov.redhawk.sca.ui.editors;

import gov.redhawk.sca.ui.ScaUI;
import gov.redhawk.sca.ui.ScaUiPlugin;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IEditorInput;

/**
 * Describes an {@link EObject} either by its ID ({@link #PARAM_ID}) or by regular expression on the last segment of
 * the {@link Resource}'s URI ({@link #PARAM_FILENAME}).
 * @since 3.0
 */
public class EObjectContentDescriber implements IScaContentDescriber, IExecutableExtension {

	/**
	 * The ID of the EMF object
	 */
	public static final String PARAM_ID = "id";

	/**
	 * A regular expression for the last segment of the EMF object's resource URI.
	 */
	public static final String PARAM_FILENAME = "fileName";

	private Map<String, String> params;

	@Override
	public int describe(final Object contents) throws IOException {
		if (this.params == null) {
			IStatus status = new Status(IStatus.WARNING, ScaUiPlugin.PLUGIN_ID, "No parameters provided");
			ScaUiPlugin.getDefault().getLog().log(status);
			return IScaContentDescriber.INVALID;
		}

		boolean valid = false;

		if (!(contents instanceof EObject)) {
			return IScaContentDescriber.INVALID;
		}
		final EObject eObj = (EObject) contents;

		final String idParam = this.params.get(EObjectContentDescriber.PARAM_ID);
		if (idParam != null) {
			if (idParam.equals(EcoreUtil.getID(eObj))) {
				valid = true;
			} else {
				return IScaContentDescriber.INVALID;
			}
		}

		final String fileNameExpr = this.params.get(EObjectContentDescriber.PARAM_FILENAME);
		if (fileNameExpr != null) {
			final Resource resource = eObj.eResource();
			if (resource == null || resource.getURI() == null || resource.getURI().lastSegment() == null) {
				return IScaContentDescriber.INVALID;
			}

			try {
				if (resource.getURI().lastSegment().matches(fileNameExpr)) {
					valid = true;
				} else {
					return IScaContentDescriber.INVALID;
				}
			} catch (final PatternSyntaxException e) {
				return IScaContentDescriber.INVALID;
			}
		}

		if (valid) {
			return IScaContentDescriber.VALID;
		} else {
			IStatus status = new Status(IStatus.WARNING, ScaUiPlugin.PLUGIN_ID, "At least one parameter must be supplied");
			ScaUiPlugin.getDefault().getLog().log(status);
			return IScaContentDescriber.INVALID;
		}
	}

	@Override
	public IEditorInput getEditorInput(final Object contents) {
		if (contents instanceof EObject) {
			final EObject eObj = (EObject) contents;
			final Resource resource = eObj.eResource();
			if (resource != null) {
				IFileStore store;
				try {
					org.eclipse.emf.common.util.URI uri = resource.getURI();
					final URI resolvedURI;
					if (uri.isPlatform() || uri.isFile()) {
						uri = uri.trimQuery();
					}
					if (uri.isPlatform()) {
						resolvedURI = URI.create(CommonPlugin.resolve(uri).toString());
					} else {
						resolvedURI = URI.create(uri.toString());
					}
					store = EFS.getStore(resolvedURI);
					return ScaUI.getEditorInput(store);
				} catch (final CoreException e) {
					// PASS
				}
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setInitializationData(final IConfigurationElement config, final String propertyName, final Object data) throws CoreException {
		if (data instanceof Map) {
			this.params = (Map<String, String>) data;
		}
	}

}

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
package gov.redhawk.sca.ui.editors;

import gov.redhawk.sca.ui.ScaUI;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IEditorInput;

/**
 * @since 3.0
 * 
 */
public class EObjectContentDescriber implements IScaContentDescriber, IExecutableExtension {

	public static final String PARAM_ID = "id";
	public static final String PARAM_FILENAME = "fileName";
	private Map<String, String> params;

	/**
	 * {@inheritDoc}
	 */
	public int describe(final Object contents) throws IOException {
		if (this.params == null) {
			return IScaContentDescriber.INDETERMINATE;
		}

		boolean valid = false;

		if (contents instanceof EObject) {
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
				if (resource != null && resource.getURI() != null && resource.getURI().lastSegment() != null) {
					try {
						if (resource.getURI().lastSegment().matches(fileNameExpr)) {
							valid = true;
						} else {
							return IScaContentDescriber.INVALID;
						}
					} catch (final Exception e) {
						return IScaContentDescriber.INVALID;
					}
				}
			}

			if (valid) {
				return IScaContentDescriber.VALID;
			} else {
				return IScaContentDescriber.INDETERMINATE;
			}
		}
		return IScaContentDescriber.INVALID;
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void setInitializationData(final IConfigurationElement config, final String propertyName, final Object data) throws CoreException {
		if (data instanceof Map) {
			this.params = (Map<String, String>) data;
		}
	}

}

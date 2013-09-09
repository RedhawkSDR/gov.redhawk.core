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

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.ProfileObjectWrapper;
import gov.redhawk.sca.ui.ScaFileStoreEditorInput;
import gov.redhawk.sca.ui.ScaUI;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.IEditorInput;

/**
 * @since 2.2
 * 
 */
public class ScaObjectWrapperContentDescriber implements IScaContentDescriber, IExecutableExtension {
	public static final String PARAM_PROFILE_FILENAME = "profileFilename";
	private Map<String, String> params;

	/**
	 * {@inheritDoc}
	 */
	public int describe(final Object contents) throws IOException {
		if (this.params == null) {
			return IScaContentDescriber.INDETERMINATE;
		}

		if (!(contents instanceof ProfileObjectWrapper< ? >)) {
			return IScaContentDescriber.INVALID;
		}

		final ProfileObjectWrapper< ? > obj = (ProfileObjectWrapper< ? >) contents;

		final String profileFileName = this.params.get(ScaObjectWrapperContentDescriber.PARAM_PROFILE_FILENAME);
		try {
			if (profileFileName != null) {
				final Object scaObj = obj.getProfileObj();
				if (scaObj instanceof EObject) {
					final EObject eObj = (EObject) scaObj;
					final Resource resource = eObj.eResource();
					if (resource != null && resource.getURI() != null && resource.getURI().lastSegment() != null
					        && resource.getURI().lastSegment().matches(profileFileName)) {
						return IScaContentDescriber.VALID;
					}
				}
			}
		} catch (final Exception e) {
			// PASS If we run into any problems return invalid
		}
		return IScaContentDescriber.INVALID;
	}

	/**
	 * {@inheritDoc}
	 */
	public IEditorInput getEditorInput(final Object contents) {
		final ProfileObjectWrapper< ? > obj = (ProfileObjectWrapper< ? >) contents;

		URI uri = null;
		final Object profileObj = ((ProfileObjectWrapper< ? >) obj).getProfileObj();
		if (profileObj instanceof EObject && ((EObject) profileObj).eResource() != null) {
			final EObject scaObj = (EObject) profileObj;
			uri = scaObj.eResource().getURI();
			final String query = uri.query();
			final Map<String, String> oldtParams = QueryParser.parseQuery(query);
			final Map<String, String> queryParams = new HashMap<String, String>();
			queryParams.putAll(oldtParams);
			if (obj instanceof CorbaObjWrapper< ? >) {
				final CorbaObjWrapper< ? > wrapper = (CorbaObjWrapper< ? >) obj;
				queryParams.put(ScaFileSystemConstants.QUERY_PARAM_WF, wrapper.getIor());
			}
			uri = uri.trimQuery().appendQuery(QueryParser.createQuery(queryParams));
		}
		if (uri == null) {
			return null;
		}
		IFileStore store;
		try {
			if (uri.isPlatform()) {
				store = EFS.getStore(java.net.URI.create(CommonPlugin.resolve(uri).toString()));
			} else {
				store = EFS.getStore(java.net.URI.create(uri.toString()));
			}
		} catch (final CoreException e) {
			return null;
		}
		if (obj instanceof CorbaObjWrapper< ? >) {
			return new ScaFileStoreEditorInput((CorbaObjWrapper< ? >) obj, store);
		}
		return ScaUI.getEditorInput(store);
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

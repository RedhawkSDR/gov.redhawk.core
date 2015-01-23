/*******************************************************************************
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.model.sca.util;

import java.util.HashMap;
import java.util.Map;

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.ProfileObjectWrapper;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;

/**
 * @since 19.1
 */
public final class ScaFileSystemUtil {

	private ScaFileSystemUtil() {
	}

	/**
	 * Gets the {@link IFileStore} for a particular model object, based on its profile URI.
	 *
	 * @param obj The file store
	 * @return The file store, or null if
	 * @throws CoreException
	 */
	public static IFileStore getFileStore(ProfileObjectWrapper< ? > obj) throws CoreException {
		URI uri = obj.getProfileURI();
		if (uri == null) {
			return null;
		}

		final String query = uri.query();
		final Map<String, String> oldtParams = QueryParser.parseQuery(query);
		final Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.putAll(oldtParams);
		if (obj instanceof CorbaObjWrapper< ? >) {
			final CorbaObjWrapper< ? > wrapper = (CorbaObjWrapper< ? >) obj;
			queryParams.put(ScaFileSystemConstants.QUERY_PARAM_WF, wrapper.getIor());
		}
		uri = uri.trimQuery().appendQuery(QueryParser.createQuery(queryParams));

		IFileStore store = null;
		if (uri.isPlatform()) {
			store = EFS.getStore(java.net.URI.create(CommonPlugin.resolve(uri).toString()));
		} else {
			store = EFS.getStore(java.net.URI.create(uri.toString()));
		}

		return store;
	}
}

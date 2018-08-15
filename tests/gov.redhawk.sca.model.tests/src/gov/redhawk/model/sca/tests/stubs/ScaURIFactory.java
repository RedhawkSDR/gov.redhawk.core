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
package gov.redhawk.model.sca.tests.stubs;

import java.util.Collections;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;

import CF.FileSystem;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

public final class ScaURIFactory {

	private ScaURIFactory() {
	}

	public static URI createURI(String path, FileSystem fs) {
		String query = QueryParser.createQuery(Collections.singletonMap(ScaFileSystemConstants.QUERY_PARAM_FS, fs.toString()));
		return URI.createHierarchicalURI(ScaFileSystemConstants.SCHEME, null, null, new Path(path).segments(), query, null);
	}
}

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
package gov.redhawk.efs.sca.internal;

import gov.redhawk.sca.efs.ScaFileSystemPlugin;
import gov.redhawk.sca.util.PluginUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.provider.FileSystem;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.NLS;

public class ScaFileSystem extends FileSystem {

	public ScaFileSystem() {

	}

	@Override
	public boolean canDelete() {
		return true;
	}

	@Override
	public boolean canWrite() {
		return true;
	}

	@Override
	public int attributes() {
		return EFS.ATTRIBUTE_READ_ONLY;
	}

	@Override
	public IFileStore getStore(final URI uri) {
		// Asked to get FileStore After the plug-in is disposed, return a null FileStore
		if (ScaFileSystemPlugin.getDefault() == null) {
			return EFS.getNullFileSystem().getStore(uri);
		}

		URI fsInitRef = null;
		try {
			if (uri.getQuery() != null) {
				final Map<String, String> query = QueryParser.parseQuery(uri.getQuery());
				final String fsQuery = query.get(ScaFileSystemConstants.QUERY_PARAM_FS);
				if (fsQuery != null) {
					fsInitRef = new URI(fsQuery);
				}
			}
		} catch (final URISyntaxException e1) {
			PluginUtil.logError(ScaFileSystemPlugin.getDefault(), NLS.bind(Messages.ScaFileSystem__INVALID_SCA_FS_URI, uri), null);
			return EFS.getNullFileSystem().getStore(uri);
		}

		if (fsInitRef == null) {
			return EFS.getNullFileSystem().getStore(uri);
		}

		final ScaFileEntry fileEntry = new ScaFileEntry(uri);
		if (ScaFileSystemConstants.FS_SCHEME_CORBA_IOR.equals(fsInitRef.getScheme())
		        || ScaFileSystemConstants.FS_SCHEME_CORBA_NAME.equals(fsInitRef.getScheme())) {
			return new ScaFileStore(fsInitRef, fileEntry);
		} else {

			try {
				final String path = uri.getPath();
				if (path != null && path.length() > 0) {
					org.eclipse.emf.common.util.URI tmpURI = org.eclipse.emf.common.util.URI.createURI(fsInitRef.toString());
					final String[] segments = path.split("/");
					tmpURI = tmpURI.appendSegments(segments);
					final IFileStore rootStore = EFS.getStore(URI.create(tmpURI.toString()));
					return new ScaWrappedFileStore(uri, rootStore);
				} else {
					final IFileStore rootStore = EFS.getStore(fsInitRef);
					return new ScaWrappedFileStore(uri, rootStore);
				}
			} catch (final CoreException e) {
				ScaFileSystemPlugin.logError("Failed to resolve: " + uri, e);
				return EFS.getNullFileSystem().getStore(uri);
			}
		}
	}
}

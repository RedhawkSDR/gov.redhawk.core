package gov.redhawk.model.sca.tests.stubs;

import java.util.Collections;

import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;

import CF.FileSystem;

public final class ScaURIFactory {

	private ScaURIFactory() {

	}

	public static URI createURI(String path, FileSystem fs) {
		String query = QueryParser.createQuery(Collections.singletonMap(
				ScaFileSystemConstants.QUERY_PARAM_FS, fs.toString()));
		return URI.createHierarchicalURI(ScaFileSystemConstants.SCHEME, null,
				null, new Path(path).segments(), query, null);

	}
}

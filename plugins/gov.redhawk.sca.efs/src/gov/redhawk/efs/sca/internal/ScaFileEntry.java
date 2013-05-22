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

import java.net.URI;

/**
 * 
 */
public class ScaFileEntry {
	public static final String PATH_SEPARATOR = "/"; //$NON-NLS-1$
	private final URI uri;
	private final String absolutePath;
	private final String name;

	public ScaFileEntry(final URI uri) {
		if (uri == null) {
			throw new IllegalArgumentException(Messages.ScaFileEntry__NULL_URI);
		}
		this.uri = uri;
		this.absolutePath = initAbsolutePath();
		this.name = initName();
	}

	/**
	 * @return
	 */
	private String initName() {
		String path = this.uri.getPath();
		if ((path == null) || path.length() == 0) {
			path = ScaFileEntry.PATH_SEPARATOR;
		}

		final String newName = path.substring(path.lastIndexOf(ScaFileEntry.PATH_SEPARATOR) + 1);
		if (newName.length() == 0) {
			return ScaFileEntry.PATH_SEPARATOR;
		} else {
			return newName;
		}
	}

	/**
	 * 
	 * @return URI to parent file entry otherwise returns null if this file entry is considered a root element.
	 */
	public URI getParentUri() {
		final org.eclipse.emf.common.util.URI tmpUri = org.eclipse.emf.common.util.URI.createURI(this.uri.toString());
		if (tmpUri.segmentCount() == 0) {
			return null;
		}
		return URI.create(tmpUri.trimSegments(1).toString());
	}

	/**
	 * @return the uri
	 */
	public URI getUri() {
		return this.uri;
	}

	public String getName() {
		return this.name;
	}

	private String initAbsolutePath() {
		String path = this.uri.getPath();
		
		while (path.endsWith(ScaFileEntry.PATH_SEPARATOR)) {
			path = path.substring(0, path.length() - 1);
		}
		while (path.startsWith(ScaFileEntry.PATH_SEPARATOR)) {
			path = path.substring(1, path.length());
		}
		if (path.length() > 0 && !path.startsWith(ScaFileEntry.PATH_SEPARATOR)) {
			path = ScaFileEntry.PATH_SEPARATOR + path;
		}
		return path;
	}

	public String getAbsolutePath() {
		return this.absolutePath;
	}
}

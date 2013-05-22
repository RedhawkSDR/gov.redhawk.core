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
package gov.redhawk.sca.model.tests;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.provider.FileStore;
import org.eclipse.core.filesystem.provider.FileSystem;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * This FILE SYSTEM IS NOT FULLY IMPLEMENTED NOR HAS ALL THE CORRECT BEHAVIOR
 * IT SHOULD ONLY BE USED WITHIN TESTING PLUGINS
 */
public class PlatformFileSystem extends FileSystem {

	private class PlatformFileStore extends FileStore {

		private final URI uri;
		private final IFileStore parent;

		public PlatformFileStore(final URI uri, final IFileStore parent) {
			this.uri = uri;
			this.parent = parent;
		}

		@Override
		public String[] childNames(final int options, final IProgressMonitor monitor) throws CoreException {
			return new String[0];
		}

		@Override
		public IFileInfo fetchInfo(final int options, final IProgressMonitor monitor) throws CoreException {
			return EFS.createFileInfo();
		}

		@Override
		public IFileStore getChild(final String name) {
			org.eclipse.emf.common.util.URI tmpUri = org.eclipse.emf.common.util.URI.createURI(this.uri.toString());
			tmpUri = tmpUri.appendSegment(name);
			return new PlatformFileStore(URI.create(tmpUri.toString()), this);
		}

		@Override
		public String getName() {
			final org.eclipse.emf.common.util.URI tmpUri = org.eclipse.emf.common.util.URI.createURI(this.uri.toString());
			return tmpUri.lastSegment();
		}

		@Override
		public IFileStore getParent() {
			return this.parent;
		}

		@Override
		public InputStream openInputStream(final int options, final IProgressMonitor monitor) throws CoreException {
			try {
				return this.uri.toURL().openStream();
			} catch (final MalformedURLException e) {
				throw new CoreException(new Status(IStatus.ERROR, "gov.redhawk.sca.model.tests", "Error with URL: " + uri, e));
			} catch (final IOException e) {
				throw new CoreException(new Status(IStatus.ERROR, "gov.redhawk.sca.model.tests", "Error opening stream", e));
			}
		}

		@Override
		public URI toURI() {
			return this.uri;
		}

	}

	public PlatformFileSystem() {

	}

	@Override
	public IFileStore getStore(final URI uri) {
		return new PlatformFileStore(uri, null);
	}

}

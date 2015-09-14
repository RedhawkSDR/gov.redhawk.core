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
package gov.redhawk.sca.efs;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.provider.FileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @since 1.1
 * 
 */
public abstract class WrappedFileStore extends FileStore {

	public static IFileStore unwrap(IFileStore store) {
		while (store instanceof WrappedFileStore) {
			store = ((WrappedFileStore) store).wrappedStore;
		}
		return store;
	}

	protected final URI uri; // SUPPRESS CHECKSTYLE Protected Final Field
	protected final IFileStore wrappedStore; // SUPPRESS CHECKSTYLE Protected Final Field

	public WrappedFileStore(final URI uri, final IFileStore wrappedStore) {
		this.uri = uri;
		this.wrappedStore = wrappedStore;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] childNames(final int options, final IProgressMonitor monitor) throws CoreException {
		return this.wrappedStore.childNames(options, monitor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFileInfo fetchInfo(final int options, final IProgressMonitor monitor) throws CoreException {
		return this.wrappedStore.fetchInfo(options, monitor);
	}

	@Override
	public void putInfo(final IFileInfo info, final int options, final IProgressMonitor monitor) throws CoreException {
		this.wrappedStore.putInfo(info, options, monitor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFileStore getChild(final String name) {
		final IFileStore childStore = this.wrappedStore.getChild(name);
		final org.eclipse.emf.common.util.URI tmpUri = org.eclipse.emf.common.util.URI.createURI(this.uri.toString());

		try {
			final String encodedName = URLEncoder.encode(name, "UTF-8");
			final WrappedFileStore child = createFileStore(URI.create(tmpUri.appendSegment(encodedName).toString()), childStore);
			return child;
		} catch (final UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	protected abstract WrappedFileStore createFileStore(URI uri, IFileStore child);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return this.wrappedStore.getName();
	}

	@Override
	public void copy(final IFileStore destination, final int options, final IProgressMonitor monitor) throws CoreException {
		this.wrappedStore.copy(destination, options, monitor);
	}

	@Override
	public void move(final IFileStore destination, final int options, final IProgressMonitor monitor) throws CoreException {
		this.wrappedStore.move(destination, options, monitor);
	}

	@Override
	public File toLocalFile(final int options, final IProgressMonitor monitor) throws CoreException {
		return this.wrappedStore.toLocalFile(options, monitor);
	}

	@Override
	public void delete(final int options, final IProgressMonitor monitor) throws CoreException {
		this.wrappedStore.delete(options, monitor);
	}

	@Override
	public IFileStore mkdir(final int options, final IProgressMonitor monitor) throws CoreException {
		this.wrappedStore.mkdir(options, monitor);
		return this;
	}

	@Override
	public IFileInfo[] childInfos(final int options, final IProgressMonitor monitor) throws CoreException {
		return this.wrappedStore.childInfos(options, monitor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFileStore getParent() {
		final org.eclipse.emf.common.util.URI tmpURI = org.eclipse.emf.common.util.URI.createURI(this.uri.toString());
		if (tmpURI.segmentCount() == 0) {
			return null;
		}
		final org.eclipse.emf.common.util.URI parentURI = tmpURI.trimSegments(1);
		final IFileStore parent = this.wrappedStore.getParent();

		if (parent == null) {
			return null;
		}
		return createFileStore(URI.create(parentURI.toString()), parent);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InputStream openInputStream(final int options, final IProgressMonitor monitor) throws CoreException {
		return this.wrappedStore.openInputStream(options, monitor);
	}

	@Override
	public OutputStream openOutputStream(final int options, final IProgressMonitor monitor) throws CoreException {
		return this.wrappedStore.openOutputStream(options, monitor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public URI toURI() {
		return this.uri;
	}

	public IFileStore getWrappedStore() {
		return this.wrappedStore;
	}

}

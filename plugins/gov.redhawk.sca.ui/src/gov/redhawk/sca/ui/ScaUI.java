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
package gov.redhawk.sca.ui;

import gov.redhawk.sca.internal.ui.ScaContentTypeRegistry;
import gov.redhawk.sca.ui.editors.IScaContentDescriber;

import java.util.ArrayList;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.FileEditorInput;

/**
 * @since 7.0
 * 
 */
public final class ScaUI {
	private ScaUI() {

	}

	/**
	 * Attempts to open an editor on the give EObject by first consulting the 
	 * SCA Content type registry and then delegating to opening the filestore associated with the EObject.
	 * @param page The page to open the editor within
	 * @param object The object to open an editor on
	 * @return an open editor or null if we either opened an external editor
	 * @throws PartInitException if no internal editor can be found or if the editor could not be initialized
	 * @throws NoEditorAvailableException if no editor could be found
	 * @see #openEditorOnEObject(IWorkbenchPage, EObject, boolean)
	 */
	public static IEditorPart openEditorOnEObject(final IWorkbenchPage page, final EObject object) throws PartInitException, NoEditorAvailableException {
		return ScaUI.openEditorOnEObject(page, object, false);
	}

	/**
	 * Attempts to open an editor on the give EObject by first consulting the 
	 * SCA Content type registry and then delegating to opening the filestore associated with the EObject.
	 * @param page The page to open the editor within
	 * @param object The object to open an editor on
	 * @param true if should attempt to open an editor on the EObject's URI if SCA Content resolution fails
	 * @return an open editor or null if we either opened an external editor
	 * @throws PartInitException if no internal editor can be found or if the editor could not be initialized
	 * @throws NoEditorAvailableException if no editor could be found
	 */
	public static IEditorPart openEditorOnEObject(final IWorkbenchPage page, final EObject object, final boolean useUri) throws PartInitException,
	        NoEditorAvailableException {
		Assert.isNotNull(object);
		final IScaContentTypeRegistry contentTypeRegistry = ScaUiPlugin.getContentTypeRegistry();
		final String contentTypeId = contentTypeRegistry.findContentType(object);
		if (contentTypeId != null) {
			final String editorId = contentTypeRegistry.findEditor(contentTypeId);
			final IScaContentDescriber describer = contentTypeRegistry.getDescriber(contentTypeId);
			if (editorId != null && describer != null) {
				final IEditorInput editorInput = describer.getEditorInput(object);
				if (editorInput != null) {
					return page.openEditor(editorInput, editorId);
				} else {
					throw new NoEditorAvailableException("No matching editor.");
				}
			}
		}

		if (useUri) {
			final Resource resource = object.eResource();
			if (resource != null) {
				final URI uri = resource.getURI();
				IFileStore fileStore;
				try {
					fileStore = EFS.getStore(java.net.URI.create(uri.toString()));
					final IEditorInput input = ScaUI.getEditorInput(fileStore);
					final IEditorDescriptor editorDesc = ScaContentTypeRegistry.INSTANCE.getDefaultEditor(fileStore);
					if (input != null && editorDesc != null) {
						return page.openEditor(input, editorDesc.getId());
					} else {
						throw new NoEditorAvailableException("No editor available for " + fileStore);
					}
				} catch (final CoreException e) {
					throw new PartInitException("Fail to accuire a file store for " + object, e);
				}
			}
		}
		throw new NoEditorAvailableException("No editor available for the input: " + object);
	}

	public static boolean editorExistsFor(final EObject object, final boolean useUri) {
		if (object == null) {
			return false;
		}
		final IScaContentTypeRegistry contentTypeRegistry = ScaUiPlugin.getContentTypeRegistry();
		final String contentTypeId = contentTypeRegistry.findContentType(object);
		if (contentTypeId != null) {
			final String editorId = contentTypeRegistry.findEditor(contentTypeId);
			if (editorId != null) {
				return true;
			}
		}

		if (useUri) {
			final Resource resource = object.eResource();
			if (resource != null) {
				final URI uri = resource.getURI();
				if (uri.scheme().equals("platform")) {
					final IFile[] files = ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(java.net.URI.create(uri.toString()));
					return files.length > 0;
				} else {
					try {
						EFS.getStore(java.net.URI.create(uri.toString()));
						return true;
					} catch (final CoreException e) {
						return false;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Determine whether or not the <code>IFileStore</code> represents a file
	 * currently in the workspace.
	 * 
	 * @param fileStore The <code>IFileStore</code> to test
	 * @return The workspace's <code>IFile</code> if it exists or
	 *         <code>null</code> if not
	 */
	public static IFile getWorkspaceFile(final IFileStore fileStore) {
		final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile[] files = root.findFilesForLocationURI(fileStore.toURI());
		files = ScaUI.filterNonExistentFiles(files);
		if (files == null || files.length == 0) {
			return null;
		}

		// for now only return the first file
		return files[0];
	}

	/**
	 * Create the Editor Input appropriate for the given <code>IFileStore</code>
	 * . The result is a normal file editor input if the file exists in the
	 * workspace and, if not, we create a wrapper capable of managing an
	 * 'external' file using its <code>IFileStore</code>.
	 * 
	 * @param fileStore The file store to provide the editor input for
	 * @return The editor input associated with the given file store
	 */
	public static IEditorInput getEditorInput(final IFileStore fileStore) {
		final IFile workspaceFile = ScaUI.getWorkspaceFile(fileStore);
		if (workspaceFile != null) {
			return new FileEditorInput(workspaceFile);
		}
		return new FileStoreEditorInput(fileStore);
	}

	/**
	 * Filter the incoming array of <code>IFile</code> elements by removing any
	 * that do not currently exist in the workspace.
	 * 
	 * @param files The array of <code>IFile</code> elements
	 * @return The filtered array
	 */
	public static IFile[] filterNonExistentFiles(final IFile[] files) {
		if (files == null) {
			return null;
		}

		final int length = files.length;
		final ArrayList<IFile> existentFiles = new ArrayList<IFile>(length);
		for (int i = 0; i < length; i++) {
			if (files[i].exists()) {
				existentFiles.add(files[i]);
			}
		}
		return existentFiles.toArray(new IFile[existentFiles.size()]);
	}
}

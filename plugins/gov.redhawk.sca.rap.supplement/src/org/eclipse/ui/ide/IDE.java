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
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/

/****************************************************************************************************
 *  !!!!!!! NOTE !!!!!!!!
 *  
 * This class contains only a subset of the methods from org.eclipse.ui.ide.IDE, which are needed for
 * the sca_explorer application running in the RAP environment
 ****************************************************************************************************/
package org.eclipse.ui.ide;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.misc.UIStats;
import org.eclipse.ui.part.FileEditorInput;

public class IDE {

	/**
	 * The persistent property key used on IFile resources to contain the
	 * preferred editor ID to use.
	 * <p>
	 * Example of retrieving the persisted editor id:
	 * 
	 * <pre><code>
	 *  IFile file = ...
	 *  IEditorDescriptor editorDesc = null;
	 *  try {
	 *  	String editorID = file.getPersistentProperty(EDITOR_KEY);
	 *  	if (editorID != null) {
	 *  		editorDesc = editorReg.findEditor(editorID);
	 *  	}
	 *  } catch (CoreException e) {
	 *  	// handle problem accessing persistent property here
	 *  }
	 * </code></pre>
	 * 
	 * </p>
	 * <p>
	 * Example of persisting the editor id:
	 * 
	 * <pre><code>
	 *  IFile file = ...
	 *  try {
	 *  	file.setPersistentProperty(EDITOR_KEY, editorDesc.getId());
	 *  } catch (CoreException e) {
	 *  	// handle problem setting persistent property here
	 *  }
	 * </code></pre>
	 * 
	 * </p>
	 */
	public static final QualifiedName EDITOR_KEY = new QualifiedName(
			"org.eclipse.ui.internal.registry.ResourceEditorRegistry", "EditorProperty"); //$NON-NLS-2$//$NON-NLS-1$

	private IDE() {

	}

	/**
	 * Create the Editor Input appropriate for the given <code>IFileStore</code>.
	 * The result is a normal file editor input if the file exists in the
	 * workspace and, if not, we create a wrapper capable of managing an
	 * 'external' file using its <code>IFileStore</code>.
	 * 
	 * @param fileStore
	 *            The file store to provide the editor input for
	 * @return The editor input associated with the given file store
	 * @since 3.3
	 */
	private static IEditorInput getEditorInput(IFileStore fileStore) {
		IFile workspaceFile = getWorkspaceFile(fileStore);
		if (workspaceFile != null) {
			return new FileEditorInput(workspaceFile);
		}
		return new FileStoreEditorInput(fileStore);
	}

	/**
	 * Filter the incoming array of <code>IFile</code> elements by removing
	 * any that do not currently exist in the workspace.
	 * 
	 * @param files
	 *            The array of <code>IFile</code> elements
	 * @return The filtered array
	 */
	private static IFile[] filterNonExistentFiles(IFile[] files) {
		if (files == null) {
			return null;
		}
		int length = files.length;
		ArrayList existentFiles = new ArrayList(length);
		for (int i = 0; i < length; i++) {
			if (files[i].exists()) {
				existentFiles.add(files[i]);
			}
		}
		return (IFile[]) existentFiles.toArray(new IFile[existentFiles.size()]);
	}

	/**
	 * Determine whether or not the <code>IFileStore</code> represents a file
	 * currently in the workspace.
	 * 
	 * @param fileStore
	 *            The <code>IFileStore</code> to test
	 * @return The workspace's <code>IFile</code> if it exists or
	 *         <code>null</code> if not
	 */
	private static IFile getWorkspaceFile(IFileStore fileStore) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile[] files = root.findFilesForLocationURI(fileStore.toURI());
		files = filterNonExistentFiles(files);
		if (files == null || files.length == 0) {
			return null;
		}

		// for now only return the first file
		return files[0];
	}

	/**
	 * Opens an editor on the given object.
	 * <p>
	 * If the page already has an editor open on the target object then that
	 * editor is brought to front; otherwise, a new editor is opened. If
	 * <code>activate == true</code> the editor will be activated.
	 * <p>
	 * 
	 * @param page
	 *            the page in which the editor will be opened
	 * @param input
	 *            the editor input
	 * @param editorId
	 *            the id of the editor extension to use
	 * @param activate
	 *            if <code>true</code> the editor will be activated
	 * @return an open editor or <code>null</code> if an external editor was
	 *         opened
	 * @exception PartInitException
	 *                if the editor could not be initialized
	 * @see org.eclipse.ui.IWorkbenchPage#openEditor(IEditorInput, String,
	 *      boolean)
	 */
	public static IEditorPart openEditor(IWorkbenchPage page,
			IEditorInput input, String editorId, boolean activate)
					throws PartInitException {
		// sanity checks
		if (page == null) {
			throw new IllegalArgumentException();
		}

		// open the editor on the file
		return page.openEditor(input, editorId, activate);
	}

	/**
	 * Opens an editor on the given file resource. This method will attempt to
	 * resolve the editor based on content-type bindings as well as traditional
	 * name/extension bindings.
	 * <p>
	 * If the page already has an editor open on the target object then that
	 * editor is brought to front; otherwise, a new editor is opened.
	 * <p>
	 * 
	 * @param page
	 *            the page in which the editor will be opened
	 * @param input
	 *            the editor input
	 * @return an open editor or <code>null</code> if an external editor was
	 *         opened
	 * @exception PartInitException
	 *                if the editor could not be initialized
	 * @see org.eclipse.ui.IWorkbenchPage#openEditor(IEditorInput, String)
	 */
	public static IEditorPart openEditor(IWorkbenchPage page, IFile input)
			throws PartInitException {
		// sanity checks
		if (page == null) {
			throw new IllegalArgumentException();
		}

		// open the editor on the file
		IEditorDescriptor editorDesc = getEditorDescriptor(input);
		return page.openEditor(new FileEditorInput(input), editorDesc.getId());
	}

	/**
	 * Opens an editor on the given object.
	 * <p>
	 * If the page already has an editor open on the target object then that
	 * editor is brought to front; otherwise, a new editor is opened.
	 * <p>
	 * 
	 * @param page
	 *            the page in which the editor will be opened
	 * @param input
	 *            the editor input
	 * @param editorId
	 *            the id of the editor extension to use
	 * @return an open editor or <code>null</code> if an external editor was
	 *         opened
	 * @exception PartInitException
	 *                if the editor could not be initialized
	 * @see org.eclipse.ui.IWorkbenchPage#openEditor(IEditorInput, String)
	 */
	public static IEditorPart openEditor(IWorkbenchPage page,
			IEditorInput input, String editorId) throws PartInitException {
		// sanity checks
		if (page == null) {
			throw new IllegalArgumentException();
		}

		// open the editor on the file
		return page.openEditor(input, editorId);
	}

	/**
	 * Returns an editor descriptor appropriate for opening the given file
	 * resource.
	 * <p>
	 * The editor descriptor is determined using a multi-step process. This
	 * method will attempt to resolve the editor based on content-type bindings
	 * as well as traditional name/extension bindings.
	 * </p>
	 * <ol>
	 * <li>The <code>IResource</code> is consulted for a persistent property named
	 * <code>IDE.EDITOR_KEY</code> containing the preferred editor id to be
	 * used.</li>
	 * <li>The workbench editor registry is consulted to determine if an editor
	 * extension has been registered for the file type. If so, an instance of
	 * the editor extension is opened on the file. See
	 * <code>IEditorRegistry.getDefaultEditor(String)</code>.</li>
	 * <li>The operating system is consulted to determine if an in-place
	 * component editor is available (e.g. OLE editor on Win32 platforms).</li>
	 * <li>The operating system is consulted to determine if an external editor
	 * is available.</li>
	 * <li>The workbench editor registry is consulted to determine if the
	 * default text editor is available.</li>
	 * </ol>
	 * </p>
	 * 
	 * @param file
	 *            the file
	 * @return an editor descriptor, appropriate for opening the file
	 * @throws PartInitException
	 *             if no editor can be found
	 */
	public static IEditorDescriptor getEditorDescriptor(IFile file)
			throws PartInitException {
		return getEditorDescriptor(file, true);
	}

	/**
	 * Returns an editor descriptor appropriate for opening the given file
	 * resource.
	 * <p>
	 * The editor descriptor is determined using a multi-step process. This
	 * method will attempt to resolve the editor based on content-type bindings
	 * as well as traditional name/extension bindings if
	 * <code>determineContentType</code>is <code>true</code>.
	 * </p>
	 * <ol>
	 * <li>The <code>IResource</code> is consulted for a persistent property named
	 * <code>IDE.EDITOR_KEY</code> containing the preferred editor id to be
	 * used.</li>
	 * <li>The workbench editor registry is consulted to determine if an editor
	 * extension has been registered for the file type. If so, an instance of
	 * the editor extension is opened on the file. See
	 * <code>IEditorRegistry.getDefaultEditor(String)</code>.</li>
	 * <li>The operating system is consulted to determine if an in-place
	 * component editor is available (e.g. OLE editor on Win32 platforms).</li>
	 * <li>The operating system is consulted to determine if an external editor
	 * is available.</li>
	 * <li>The workbench editor registry is consulted to determine if the
	 * default text editor is available.</li>
	 * </ol>
	 * </p>
	 * 
	 * @param file
	 *            the file
	 * @param determineContentType
	 *            query the content type system for the content type of the file
	 * @return an editor descriptor, appropriate for opening the file
	 * @throws PartInitException
	 *             if no editor can be found
	 * @since 3.1
	 */
	public static IEditorDescriptor getEditorDescriptor(IFile file,
			boolean determineContentType) throws PartInitException {

		if (file == null) {
			throw new IllegalArgumentException();
		}

		return getEditorDescriptor(file.getName(), PlatformUI.getWorkbench()
				.getEditorRegistry(), getDefaultEditor(file,
						determineContentType));
	}

	/**
	 * Returns the default editor for a given file. This method will attempt to
	 * resolve the editor based on content-type bindings as well as traditional
	 * name/extension bindings if <code>determineContentType</code> is
	 * <code>true</code>.
	 * <p>
	 * A default editor id may be registered for a specific file using
	 * <code>setDefaultEditor</code>. If the given file has a registered
	 * default editor id the default editor will derived from it. If not, the
	 * default editor is determined by taking the file name for the file and
	 * obtaining the default editor for that name.
	 * </p>
	 * 
	 * @param file
	 *            the file
	 * @param determineContentType
	 *            determine the content type for the given file
	 * @return the descriptor of the default editor, or <code>null</code> if
	 *         not found
	 * @since 3.1
	 */
	public static IEditorDescriptor getDefaultEditor(IFile file,
			boolean determineContentType) {
		// Try file specific editor.
		IEditorRegistry editorReg = PlatformUI.getWorkbench()
				.getEditorRegistry();
		try {
			String editorID = file.getPersistentProperty(EDITOR_KEY);
			if (editorID != null) {
				IEditorDescriptor desc = editorReg.findEditor(editorID);
				if (desc != null) {
					return desc;
				}
			}
		} catch (CoreException e) {
			// PASS
		}

		IContentType contentType = null;
		if (determineContentType) {
			contentType = getContentType(file);
		}
		// Try lookup with filename
		return editorReg.getDefaultEditor(file.getName(), contentType);
	}

	/**
	 * Return the content type for the given file.
	 * 
	 * @param file
	 *            the file to test
	 * @return the content type, or <code>null</code> if it cannot be
	 *         determined.
	 * @since 3.1
	 */
	public static IContentType getContentType(IFile file) {
		try {
			UIStats.start(UIStats.CONTENT_TYPE_LOOKUP, file.getName());
			IContentDescription contentDescription = file
					.getContentDescription();
			if (contentDescription == null) {
				return null;
			}
			return contentDescription.getContentType();
		} catch (CoreException e) {
			if (e.getStatus().getCode() == IResourceStatus.OUT_OF_SYNC_LOCAL) {
				// Determine the content type from the file name.
				return Platform.getContentTypeManager()
						.findContentTypeFor(file.getName());
			}
			return null;
		} finally {
			UIStats.end(UIStats.CONTENT_TYPE_LOOKUP, file, file.getName());
		}
	}

	/**
	 * Opens an editor on the given IFileStore object.
	 * <p>
	 * Unlike the other <code>openEditor</code> methods, this one
	 * can be used to open files that reside outside the workspace
	 * resource set.
	 * </p>
	 * <p>
	 * If the page already has an editor open on the target object then that
	 * editor is brought to front; otherwise, a new editor is opened.
	 * </p>
	 * 
	 * @param page
	 *            the page in which the editor will be opened
	 * @param fileStore 
	 *            the IFileStore representing the file to open
	 * @return an open editor or <code>null</code> if an external editor was opened
	 * @exception PartInitException
	 *                if the editor could not be initialized
	 * @see org.eclipse.ui.IWorkbenchPage#openEditor(IEditorInput, String)
	 * @since 3.3
	 */
	public static IEditorPart openEditorOnFileStore(IWorkbenchPage page, IFileStore fileStore) throws PartInitException {
		//sanity checks
		if (page == null) {
			throw new IllegalArgumentException();
		}

		IEditorInput input = getEditorInput(fileStore);
		String editorId = getEditorId(fileStore);

		// open the editor on the file
		return page.openEditor(input, editorId);
	}

	/**
	 * Opens an internal editor on the given IFileStore object.
	 * <p>
	 * Unlike the other <code>openEditor</code> methods, this one can be used to
	 * open files that reside outside the workspace resource set.
	 * </p>
	 * <p>
	 * If the page already has an editor open on the target object then that
	 * editor is brought to front; otherwise, a new editor is opened.
	 * </p>
	 * 
	 * @param page
	 *            the page in which the editor will be opened
	 * @param fileStore
	 *            the IFileStore representing the file to open
	 * @return an open editor or <code>null</code> if an external editor was
	 *         opened
	 * @exception PartInitException
	 *                if no internal editor can be found or if the editor could
	 *                not be initialized
	 * @see org.eclipse.ui.IWorkbenchPage#openEditor(IEditorInput, String)
	 * @since 3.6
	 */
	public static IEditorPart openInternalEditorOnFileStore(IWorkbenchPage page, IFileStore fileStore) throws PartInitException {
		if (page == null) {
			throw new IllegalArgumentException();
		}
		if (fileStore == null) {
			throw new IllegalArgumentException();
		}
		IEditorInput input = getEditorInput(fileStore);
		String name = fileStore.fetchInfo().getName();
		if (name == null) {
			throw new IllegalArgumentException();
		}
		IContentType[] contentTypes = null;
		InputStream is = null;
		try {
			is = fileStore.openInputStream(EFS.NONE, null);
			contentTypes = Platform.getContentTypeManager().findContentTypesFor(is, name);
		} catch (CoreException ex) {
			// PASS
		} catch (IOException ex) {
			// PASS
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// PASS Noting good can be done here
				}
			}
		}

		IEditorRegistry editorReg = PlatformUI.getWorkbench().getEditorRegistry();
		if (contentTypes != null) {
			for (int i = 0; i < contentTypes.length; i++) {
				IEditorDescriptor editorDesc = editorReg.getDefaultEditor(name, contentTypes[i]);
				if ((editorDesc != null) && (editorDesc.isInternal())) {
					return page.openEditor(input, editorDesc.getId());
				}
			}
		}

		// no content types are available, use file name associations
		IEditorDescriptor[] editors = editorReg.getEditors(name);
		if (editors != null) {
			for (int i = 0; i < editors.length; i++) {
				if ((editors[i] != null) && (editors[i].isInternal())) {
					return page.openEditor(input, editors[i].getId());
				}
			}
		}

		// fallback to the default text editor
		IEditorDescriptor textEditor = editorReg.findEditor(/*IDEWorkbenchPlugin.DEFAULT_TEXT_EDITOR_ID*/"org.eclipse.ui.DefaultTextEditor");
		if (textEditor == null) {
			throw new PartInitException(IDEWorkbenchMessages.IDE_noFileEditorFound);
		}
		return page.openEditor(input, textEditor.getId());
	}

	/**
	 * Returns an editor id appropriate for opening the given file
	 * store.
	 * <p>
	 * The editor descriptor is determined using a multi-step process. This
	 * method will attempt to resolve the editor based on content-type bindings
	 * as well as traditional name/extension bindings.
	 * </p>
	 * <ol>
	 * <li>The workbench editor registry is consulted to determine if an editor
	 * extension has been registered for the file type. If so, an instance of
	 * the editor extension is opened on the file. See
	 * <code>IEditorRegistry.getDefaultEditor(String)</code>.</li>
	 * <li>The operating system is consulted to determine if an in-place
	 * component editor is available (e.g. OLE editor on Win32 platforms).</li>
	 * <li>The operating system is consulted to determine if an external editor
	 * is available.</li>
	 * <li>The workbench editor registry is consulted to determine if the
	 * default text editor is available.</li>
	 * </ol>
	 * </p>
	 * 
	 * @param fileStore 
	 *            the file store
	 * @return the id of an editor, appropriate for opening the file
	 * @throws PartInitException
	 *             if no editor can be found
	 */
	private static String getEditorId(IFileStore fileStore) throws PartInitException {
		String name = fileStore.fetchInfo().getName();
		if (name == null) {
			throw new IllegalArgumentException();
		}

		IContentType contentType = null;
		try {
			InputStream is = null;
			try {
				is = fileStore.openInputStream(EFS.NONE, null);
				contentType = Platform.getContentTypeManager().findContentTypeFor(is, name);
			} finally {
				if (is != null) {
					is.close();
				}
			}
		} catch (CoreException ex) {
			// PASS continue without content type
		} catch (IOException ex) {
			// PASS continue without content type
		}

		IEditorRegistry editorReg = PlatformUI.getWorkbench().getEditorRegistry();

		return getEditorDescriptor(name, editorReg, editorReg.getDefaultEditor(name, contentType)).getId();
	}

	/**
	 * Get the editor descriptor for a given name using the editorDescriptor
	 * passed in as a default as a starting point.
	 * 
	 * @param name
	 *            The name of the element to open.
	 * @param editorReg
	 *            The editor registry to do the lookups from.
	 * @param defaultDescriptor
	 *            IEditorDescriptor or <code>null</code>
	 * @return IEditorDescriptor
	 * @throws PartInitException
	 *             if no valid editor can be found
	 * 
	 * @since 3.1
	 */
	private static IEditorDescriptor getEditorDescriptor(String name,
			IEditorRegistry editorReg, IEditorDescriptor defaultDescriptor)
					throws PartInitException {

		if (defaultDescriptor != null) {
			return defaultDescriptor;
		}

		IEditorDescriptor editorDesc = defaultDescriptor;

		// next check the OS for in-place editor (OLE on Win32)
		if (editorReg.isSystemInPlaceEditorAvailable(name)) {
			editorDesc = editorReg
					.findEditor(IEditorRegistry.SYSTEM_INPLACE_EDITOR_ID);
		}

		// next check with the OS for an external editor
		//Not available for RAP
		//		if (editorDesc == null
		//				&& editorReg.isSystemExternalEditorAvailable(name)) {
		//			editorDesc = editorReg
		//					.findEditor(IEditorRegistry.SYSTEM_EXTERNAL_EDITOR_ID);
		//		}

		// next lookup the default text editor
		if (editorDesc == null) {
			editorDesc = editorReg
					.findEditor(/*IDEWorkbenchPlugin.DEFAULT_TEXT_EDITOR_ID*/"org.eclipse.ui.DefaultTextEditor");
		}

		// if no valid editor found, bail out
		if (editorDesc == null) {
			throw new PartInitException(
					IDEWorkbenchMessages.IDE_noFileEditorFound);
		}

		return editorDesc;
	}
}

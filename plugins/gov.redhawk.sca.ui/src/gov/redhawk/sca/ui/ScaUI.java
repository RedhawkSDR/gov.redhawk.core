/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.sca.ui;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
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
import org.eclipse.ui.progress.WorkbenchJob;
import org.eclipse.ui.statushandlers.StatusManager;

import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.sca.internal.ui.ScaContentTypeRegistry;
import gov.redhawk.sca.ui.editors.IScaContentDescriber;
import mil.jpeojtrs.sca.util.CorbaUtils;

/**
 * @since 7.0
 * 
 */
public final class ScaUI {

	/**
	 * @since 10.0
	 */
	public static final String WIDGET_TEST_ID = "org.eclipse.swtbot.search.defaultKey";

	private ScaUI() {

	}

	/**
	 * Attempts to open an editor for the given EObject by first consulting the
	 * REDHAWK Content type registry and then delegating to opening the filestore associated with the EObject.
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
	 * Attempts to open an editor for the given EObject by first consulting the
	 * REDHAWK Content type registry and then delegating to opening the filestore associated with the EObject.
	 * @param page The page to open the editor within
	 * @param object The object to open an editor on
	 * @param useUri If true, open an editor on the EObject's URI if REDHAWK Content resolution fails
	 * @return an open editor or null if we either opened an external editor
	 * @throws PartInitException if no internal editor can be found or if the editor could not be initialized
	 * @throws NoEditorAvailableException if no editor could be found
	 */
	public static IEditorPart openEditorOnEObject(final IWorkbenchPage page, final EObject object, final boolean useUri)
		throws PartInitException, NoEditorAvailableException {
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
				if ("platform".equals(uri.scheme())) {
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
	 * <code>null</code> if not
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

	/**
	 * The job family for opening editors
	 * @since 10.1
	 */
	public static final Object FAMILY_OPEN_EDITOR = new Object();

	/**
	 * Asynchronously opens the default editor for the {@link EObject}. If the {@link EObject} is an
	 * {@link IRefreshable}, it will be refreshed first. Any jobs scheduled will belong to the family
	 * {@link ScaUI#FAMILY_OPEN_EDITOR}, which allows tracking job progress.
	 * @param page The workbench page (e.g. <code>PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()</code>
	 * @param eObject
	 * @since 11.1
	 */
	public static void openEditor(IWorkbenchPage page, EObject eObject) {
		openEditor(page, ScaUiPlugin.getContentTypeRegistry().getScaEditorDescriptor(eObject));
	}

	/**
	 * Asynchronously opens the editor for the provided descriptor. If the descriptor's object is an
	 * {@link IRefreshable}, it will be refreshed first. Any jobs scheduled will belong to the family
	 * {@link ScaUI#FAMILY_OPEN_EDITOR}, which allows tracking job progress.
	 * @param page The workbench page (e.g. <code>PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()</code>
	 * @param editorDescriptor
	 * @since 11.1
	 */
	public static void openEditor(IWorkbenchPage page, IScaEditorDescriptor editorDescriptor) {
		if (editorDescriptor == null) {
			return;
		}

		// A job to open the editor
		final WorkbenchJob openJob = new WorkbenchJob(page.getWorkbenchWindow().getShell().getDisplay(), "Opening editor") {

			@Override
			public boolean belongsTo(Object family) {
				return (family == ScaUI.FAMILY_OPEN_EDITOR) || super.belongsTo(family);
			}

			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				open(page, editorDescriptor);
				return Status.OK_STATUS;
			}
		};
		openJob.setUser(true);
		openJob.setPriority(Job.SHORT);

		if (editorDescriptor.getSelectedObject() instanceof IRefreshable) {
			final IRefreshable refreshable = (IRefreshable) editorDescriptor.getSelectedObject();

			// This job will first perform a full refresh of the item
			final Job refreshJob = new Job("Refreshing state...") {

				@Override
				public boolean belongsTo(Object family) {
					return (family == ScaUI.FAMILY_OPEN_EDITOR) || super.belongsTo(family);
				}

				@Override
				protected IStatus run(final IProgressMonitor monitor) {
					try {
						CorbaUtils.invoke(new Callable<Object>() {

							public Object call() throws Exception {
								refreshable.refresh(monitor, RefreshDepth.FULL);
								return null;
							}

						}, monitor);
						return Status.OK_STATUS;
					} catch (CoreException e) {
						return new Status(e.getStatus().getSeverity(), ScaUiPlugin.PLUGIN_ID, e.getLocalizedMessage(), e);
					} catch (InterruptedException e) {
						return new Status(IStatus.CANCEL, ScaUiPlugin.PLUGIN_ID, "Interrupted while refreshing prior to opening an editor", e);
					}
				}
			};
			refreshJob.setUser(true);
			refreshJob.setPriority(Job.SHORT);

			// Successful completion of refresh job -> triggers open job
			refreshJob.addJobChangeListener(new JobChangeAdapter() {
				@Override
				public void done(IJobChangeEvent event) {
					if (event.getResult().isOK()) {
						openJob.schedule();
					}
				}
			});

			refreshJob.schedule();
		} else {
			// Not refreshable - just schedule the open job
			openJob.schedule();
		}
	}

	private static void open(IWorkbenchPage page, IScaEditorDescriptor editorDescriptor) {
		IEditorInput editorInput = editorDescriptor.getEditorInput();
		String editorId = editorDescriptor.getEditorDescriptor().getId();
		if (editorInput == null || editorId == null) {
			return;
		}

		try {
			page.openEditor(editorInput, editorId, true, IWorkbenchPage.MATCH_ID | IWorkbenchPage.MATCH_INPUT);
		} catch (final PartInitException e) {
			StatusManager.getManager().handle(e, ScaUiPlugin.PLUGIN_ID);
		}
	}
}

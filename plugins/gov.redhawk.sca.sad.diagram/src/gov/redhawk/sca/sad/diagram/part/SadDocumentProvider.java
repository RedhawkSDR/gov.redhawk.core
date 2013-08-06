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
package gov.redhawk.sca.sad.diagram.part;

import gov.redhawk.diagram.editor.URIEditorInputProxy;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import mil.jpeojtrs.sca.sad.diagram.part.Messages;
import mil.jpeojtrs.sca.sad.diagram.part.SadDiagramEditorPlugin;
import mil.jpeojtrs.sca.sad.diagram.part.SadDiagramEditorUtil;
import mil.jpeojtrs.sca.sad.util.SadResourceImpl;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.DiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.EditorInputProxy;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.document.FileEditorInputProxy;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.EditorStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.util.DiagramIOUtil;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

/**
 * 
 */
public class SadDocumentProvider extends mil.jpeojtrs.sca.sad.diagram.part.SadDocumentProvider {

	private static enum SINGLETON {
		INSTANCE;
		private final SadDocumentProvider value = new SadDocumentProvider();
	}

	public static SadDocumentProvider getInstance() {
		return SINGLETON.INSTANCE.value;
	}

	private SadDocumentProvider() {

	};

	@Override
	protected IDocument createDocument(final Object element) throws CoreException {
		if (!(element instanceof FileEditorInput) && !(element instanceof URIEditorInput) && !(element instanceof EditorInputProxy)) {
			throw new CoreException(new Status(IStatus.ERROR, SadDiagramEditorPlugin.ID, 0, NLS.bind(Messages.SadDocumentProvider_IncorrectInputError,
				new Object[] { element, "org.eclipse.ui.part.FileEditorInput", "org.eclipse.emf.common.ui.URIEditorInput" }), //$NON-NLS-1$ //$NON-NLS-2$
				null));
		}
		final IDocument document = createEmptyDocument(element);
		setDocumentContent(document, (IEditorInput) element);
		setupDocument(element, document);
		return document;
	}

	@Override
	protected ElementInfo createElementInfo(final Object element) throws CoreException {
		if (!(element instanceof FileEditorInput) && !(element instanceof URIEditorInput) && !(element instanceof EditorInputProxy)) {
			throw new CoreException(new Status(IStatus.ERROR, SadDiagramEditorPlugin.ID, 0, NLS.bind(Messages.SadDocumentProvider_IncorrectInputError,
				new Object[] { element, "org.eclipse.ui.part.FileEditorInput", "org.eclipse.emf.common.ui.URIEditorInput" }), //$NON-NLS-1$ //$NON-NLS-2$
				null));
		}
		final IEditorInput editorInput = (IEditorInput) element;
		final IDiagramDocument document = (IDiagramDocument) createDocument(editorInput);

		final ResourceSetInfo info = new ResourceSetInfo(document, editorInput);
		info.setModificationStamp(computeModificationStamp(info));
		info.fStatus = null;
		return info;
	}

	private long computeModificationStamp(final ResourceSetInfo info) {
		int result = 0;
		for (final Iterator<Resource> it = info.getLoadedResourcesIterator(); it.hasNext();) {
			final Resource nextResource = it.next();
			final IFile file = WorkspaceSynchronizer.getFile(nextResource);
			if (file != null) {
				if (file.getLocation() != null) {
					result += file.getLocation().toFile().lastModified();
				} else {
					result += file.getModificationStamp();
				}
			}
		}
		return result;
	}

	@Override
	protected IDocument createEmptyDocument() {
		// END GENERATED CODE
		return createEmptyDocument(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * @param object
	 */
	protected IDocument createEmptyDocument(final Object input) {
		IDocument document;
		if (input instanceof EditorInputProxy) {
			final EditorInputProxy proxy = (EditorInputProxy) input;
			document = new DiagramDocument();
			((DiagramDocument) document).setEditingDomain(proxy.getEditingDomain());

		} else {
			document = super.createEmptyDocument();
		}
		return document;
	}

	@Override
	protected void doSaveDocument(final IProgressMonitor monitor, final Object element, final IDocument document, final boolean overwrite) throws CoreException {
		final ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			if (!overwrite && !info.isSynchronized()) {
				throw new CoreException(new Status(IStatus.ERROR, SadDiagramEditorPlugin.ID, IResourceStatus.OUT_OF_SYNC_LOCAL,
				        Messages.SadDocumentProvider_UnsynchronizedFileSaveError, null));
			}
			info.stopResourceListening();
			fireElementStateChanging(element);
			try {
				monitor.beginTask(Messages.SadDocumentProvider_SaveDiagramTask, info.getResourceSet().getResources().size() + 1); //"Saving diagram"
				for (final Iterator<org.eclipse.emf.ecore.resource.Resource> it = info.getLoadedResourcesIterator(); it.hasNext();) {
					final Resource nextResource = it.next();
					monitor.setTaskName(NLS.bind(Messages.SadDocumentProvider_SaveNextResourceTask, nextResource.getURI()));
					if (nextResource.isLoaded() && !info.getEditingDomain().isReadOnly(nextResource)
					        && (nextResource instanceof SadResourceImpl || nextResource instanceof GMFResource)) {
						try {
							nextResource.save(SadDiagramEditorUtil.getSaveOptions());
						} catch (final IOException e) {
							fireElementStateChangeFailed(element);
							throw new CoreException(new Status(IStatus.ERROR, SadDiagramEditorPlugin.ID, EditorStatusCodes.RESOURCE_FAILURE,
							        e.getLocalizedMessage(), null));
						}
					}
					monitor.worked(1);
				}
				monitor.done();
				info.setModificationStamp(computeModificationStamp(info));
			} catch (final RuntimeException x) {
				fireElementStateChangeFailed(element);
				throw x;
			} finally {
				info.startResourceListening();
			}
		} else {
			URI newResoruceURI;
			List< ? > affectedFiles = null;
			if (element instanceof FileEditorInput || element instanceof FileEditorInputProxy) {
				IFile newFile;
				if (element instanceof FileEditorInputProxy) {
					newFile = ((FileEditorInputProxy) element).getFile();
				} else {
					newFile = ((FileEditorInput) element).getFile();
				}
				affectedFiles = Collections.singletonList(newFile);
				newResoruceURI = URI.createPlatformResourceURI(newFile.getFullPath().toString(), true);
			} else if (element instanceof URIEditorInputProxy) {
				newResoruceURI = ((URIEditorInputProxy) element).getURI();
			} else if (element instanceof URIEditorInput) {
				newResoruceURI = ((URIEditorInput) element).getURI();
			} else {
				fireElementStateChangeFailed(element);
				throw new CoreException(new Status(IStatus.ERROR, SadDiagramEditorPlugin.ID, 0, NLS.bind(Messages.SadDocumentProvider_IncorrectInputError,
				        new Object[] { element, "org.eclipse.ui.part.FileEditorInput", "org.eclipse.emf.common.ui.URIEditorInput" }), //$NON-NLS-1$ //$NON-NLS-2$
				        null));
			}
			if (!(document instanceof IDiagramDocument)) {
				fireElementStateChangeFailed(element);
				throw new CoreException(new Status(IStatus.ERROR, SadDiagramEditorPlugin.ID, 0, "Incorrect document used: " + document
				        + " instead of org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument", null)); //$NON-NLS-1$ 
			}
			final IDiagramDocument diagramDocument = (IDiagramDocument) document;
			final Resource newResource = diagramDocument.getEditingDomain().getResourceSet().createResource(newResoruceURI);
			final Diagram diagramCopy = EcoreUtil.copy(diagramDocument.getDiagram());
			try {
				final AbstractTransactionalCommand command = new AbstractTransactionalCommand(diagramDocument.getEditingDomain(), NLS.bind(
				        Messages.SadDocumentProvider_SaveAsOperation, diagramCopy.getName()), affectedFiles) {
					@Override
					protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
						newResource.getContents().add(diagramCopy);
						return CommandResult.newOKCommandResult();
					}
				};
				command.execute(monitor, null);
				newResource.save(SadDiagramEditorUtil.getSaveOptions());
			} catch (final ExecutionException e) {
				fireElementStateChangeFailed(element);
				throw new CoreException(new Status(IStatus.ERROR, SadDiagramEditorPlugin.ID, 0, e.getLocalizedMessage(), null));
			} catch (final IOException e) {
				fireElementStateChangeFailed(element);
				throw new CoreException(new Status(IStatus.ERROR, SadDiagramEditorPlugin.ID, 0, e.getLocalizedMessage(), null));
			}
			newResource.unload();
		}
	}

	@Override
	protected void handleElementMoved(final IEditorInput input, final URI uri) {
		if (input instanceof FileEditorInput || input instanceof FileEditorInputProxy) {
			final IFile newFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(URI.decode(uri.path())).removeFirstSegments(1));
			Object elementMoved;
			if (newFile == null) {
				elementMoved = null;
			} else {
				elementMoved = new FileEditorInput(newFile);
			}
			fireElementMoved(input, elementMoved);
			return;
		}
		// TODO: append suffix to the URI! (use diagram as a parameter)
		fireElementMoved(input, new URIEditorInput(uri));
	}

	@Override
	public boolean isModifiable(final Object element) {
		if (!isStateValidated(element) && (element instanceof EditorInputProxy)) {
			final EditorInputProxy proxy = (EditorInputProxy) element;
			if (element instanceof EObject) {
				return proxy.getEditingDomain().isReadOnly(((EObject) element).eResource());
			} else if (element instanceof Resource) {
				return proxy.getEditingDomain().isReadOnly((Resource) element);
			}
			return true;
		} else {
			return super.isModifiable(element);
		}
	}

	@Override
	protected void setDocumentContent(final IDocument document, final IEditorInput element) throws CoreException {
		final IDiagramDocument diagramDocument = (IDiagramDocument) document;
		final TransactionalEditingDomain domain = diagramDocument.getEditingDomain();
		if (element instanceof FileEditorInputProxy) {
			final IStorage storage = ((FileEditorInputProxy) element).getStorage();
			final Diagram diagram = DiagramIOUtil.load(domain, storage, true, getProgressMonitor());
			document.setContent(diagram);
		} else if (element instanceof URIEditorInputProxy) {
			super.setDocumentContent(diagramDocument, ((URIEditorInputProxy) element).getProxied());
		} else {
			super.setDocumentContent(diagramDocument, element);
		}
	}
}

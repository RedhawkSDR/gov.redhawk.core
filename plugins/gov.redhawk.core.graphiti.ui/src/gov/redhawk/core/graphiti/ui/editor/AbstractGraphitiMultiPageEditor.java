/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package gov.redhawk.core.graphiti.ui.editor;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.commands.operations.DefaultOperationHistory;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.part.MultiPageSelectionProvider;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import gov.redhawk.core.graphiti.ui.GraphitiUIPlugin;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.ui.editor.SCAFormEditor;
import mil.jpeojtrs.sca.util.ScaResourceFactoryUtil;

public abstract class AbstractGraphitiMultiPageEditor extends SCAFormEditor implements ITabbedPropertySheetPageContributor, IViewerProvider {

	private static final String DIAGRAM_PAGE_ID = "2";

	/**
	 * This is used to manually override the dirty state. It can be used to avoid marking the editor as dirty on trivial
	 * or hidden actions, such as linking the diagram to the sad.xml
	 */
	private boolean isDirtyAllowed = true;

	/**
	 * The graphical diagram editor embedded into this editor.
	 */
	private AbstractGraphitiDiagramEditor diagramEditor;

	/**
	 * This selection provider coordinates the selections of the various editor
	 * parts.
	 */
	private final MultiPageSelectionProvider selectionProvider;

	private IEditorPart textEditor;

	public AbstractGraphitiMultiPageEditor() {
		super();
		this.selectionProvider = new MultiPageSelectionProvider(this);
		this.selectionProvider.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				setStatusLineManager(event.getSelection());
			}
		});
	}

	@Override
	public < T > T getAdapter(Class<T> adapter) {
		if (adapter == IPropertySheetPage.class) {
			return adapter.cast(new TabbedPropertySheetPage(this));
		}
		return super.getAdapter(adapter);
	}

	@Override
	protected String getPropertyEditorPageKey(final IFileEditorInput input) {
		final String retVal = super.getPropertyEditorPageKey(input);
		if (retVal == null) {
			return getDefaultPageKey();
		}
		return retVal;
	}

	@Override
	protected String getDefaultPageKey() {
		return DIAGRAM_PAGE_ID;
	}

	@Override
	public void gotoMarker(final IMarker marker) {
		try {
			if (marker.getType().equals(EValidator.MARKER)) {
				final String uriAttribute = marker.getAttribute(EValidator.URI_ATTRIBUTE, null);
				if (uriAttribute != null) {
					final URI uri = URI.createURI(uriAttribute);
					final EObject eObject = getEditingDomain().getResourceSet().getEObject(uri, true);
					if (eObject != null) {
						setSelectionToViewer(Collections.singleton(getWrapper(eObject)));
					}
				}
			}
		} catch (final CoreException exception) {
			StatusManager.getManager().handle(new Status(IStatus.ERROR, GraphitiUIPlugin.PLUGIN_ID, "Failed to go to marker.", exception),
				StatusManager.LOG | StatusManager.SHOW);
		}
	}

	private Object getWrapper(final EObject eObject) {
		return AdapterFactoryEditingDomain.getWrapper(eObject, getEditingDomain());
	}

	private void setStatusLineManager(final ISelection selection) {
		final IStatusLineManager statusLineManager;

		statusLineManager = getActionBars().getStatusLineManager();

		if (statusLineManager != null) {
			if (selection instanceof IStructuredSelection) {
				final Collection< ? > collection = ((IStructuredSelection) selection).toList();
				switch (collection.size()) {
				case 0:
					statusLineManager.setMessage("Selected Nothing");
					break;
				case 1:
					final String text = new AdapterFactoryItemDelegator(getAdapterFactory()).getText(collection.iterator().next());
					statusLineManager.setMessage(MessageFormat.format("Selected Object: {0}", text));
					break;
				default:
					statusLineManager.setMessage(MessageFormat.format("Selected {0} Objects", Integer.toString(collection.size())));
					break;
				}
			} else {
				statusLineManager.setMessage("");
			}
		}
	}

	@Override
	public boolean isDirty() {
		if (this.getMainResource() != null && !this.getMainResource().getURI().isPlatform()) {
			return false;
		}
		return super.isDirty();
	}

	/**
	 * Determines if this multi-page editor show dirty state
	 */
	@Override
	protected boolean computeDirtyState() {
		// an override to ignore potentially dirty state
		if (!isDirtyAllowed()) {
			setDirtyAllowed(true);
			return false;
		}

		// text editor dirty state
		if (textEditor != null && textEditor.isDirty()) {
			return true;
		}

		// state of resources in command stack diagram file,
		// sad file is taken care of above with text editor
		if (diagramEditor != null && diagramEditor.getEditingDomain() != null && diagramEditor.getEditingDomain().getCommandStack() != null) {
			BasicCommandStack commandStack = (BasicCommandStack) diagramEditor.getEditingDomain().getCommandStack();
			return commandStack.isSaveNeeded();
		}
		return false;
	}

	@Override
	public String getContributorId() {
		if (this.diagramEditor == null) {
			return null;
		}
		return this.diagramEditor.getContributorId();
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * {@link AbstractGraphitiMultiPageEditor} performs the following steps to create the diagram:
	 * <ol>
	 * <li>{@link #createDiagramEditor()}</li>
	 * <li>{@link #initModelMap()}</li>
	 * <li>{@link #createDiagramInput()}</li>
	 * </ol>
	 * An XML text editor is also created.
	 */
	@Override
	protected void addPages() {
		try {
			this.diagramEditor = createDiagramEditor();
			initModelMap();
			final IEditorInput diagramInput = createDiagramInput();
			int pageIndex = addPage(this.diagramEditor, diagramInput);
			setPageText(pageIndex, "Diagram");
			DUtil.layout(this.diagramEditor);

			this.textEditor = createTextEditor(getEditorInput());
			if (this.textEditor != null) {
				pageIndex = addPage(-1, textEditor, getEditorInput(), getMainResource());
				setPageText(pageIndex, getEditorInput().getName());
			}
		} catch (IOException | CoreException e) {
			StatusManager.getManager().handle(new Status(IStatus.ERROR, GraphitiUIPlugin.PLUGIN_ID, "Failed to create editor parts.", e),
				StatusManager.LOG | StatusManager.SHOW);
		}
	}

	/**
	 * Creates an appropriate Graphiti diagram editor
	 * @return
	 */
	protected abstract AbstractGraphitiDiagramEditor createDiagramEditor();

	/**
	 * @return the Graphiti diagram editor
	 */
	public AbstractGraphitiDiagramEditor getDiagramEditor() {
		return this.diagramEditor;
	}

	/**
	 * Initializes the runtime model map, if applicable.
	 */
	protected void initModelMap() throws CoreException {
	}

	/**
	 * Create the input for the {@link Diagram} editor (a page in this multi-page editor).
	 * @return
	 * @throws IOException
	 * @throws CoreException
	 */
	protected abstract IEditorInput createDiagramInput() throws IOException, CoreException;

	/**
	 * Returns the property value that should be set for the {@link Diagram} container's DIAGRAM_CONTEXT property.
	 * Indicates the mode the diagram is operating in. This may be called by {@link #createDiagramInput()}.
	 * @return
	 */
	protected abstract String getDiagramContext();

	/**
	 * Gets the extension point ID of the diagram type provider to be used.
	 * @return
	 */
	protected abstract String getDiagramTypeProviderID();

	public IEditorPart getTextEditor() {
		return this.textEditor;
	}

	public IActionBars getActionBars() {
		return getActionBarContributor().getActionBars();
	}

	@Override
	public void reload() {
		super.reload();
		diagramEditor.getDiagramBehavior().getUpdateBehavior().setResourceChanged(true);
		diagramEditor.getDiagramBehavior().getUpdateBehavior().handleActivate();
	}

	@Override
	protected IContentOutlinePage createContentOutline() {
		return null;
	}

	@Override
	protected TransactionalEditingDomain createEditingDomain() {
		final ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();
		final IWorkspaceCommandStack workspaceCommandStack = new RHCommandStackImpl(new DefaultOperationHistory());

		TransactionalEditingDomain domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE),
			workspaceCommandStack, resourceSet);
		WorkspaceEditingDomainFactory.INSTANCE.mapResourceSet((TransactionalEditingDomain) domain);
		domain.setID(getEditingDomainId());

		// Create an adapter factory that yields item providers.
		//
		final ComposedAdapterFactory localAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		localAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		localAdapterFactory.addAdapterFactory(getSpecificAdapterFactory());
		localAdapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		((AdapterFactoryEditingDomain) domain).setAdapterFactory(localAdapterFactory);
		return domain;
	}

	public boolean isDirtyAllowed() {
		return isDirtyAllowed;
	}

	public void setDirtyAllowed(boolean isDirtyAllowed) {
		this.isDirtyAllowed = isDirtyAllowed;
	}

	/**
	 * Should return the XML model object in the {@link Resource} returned by {@link #getMainResource()}.
	 * @return
	 */
	protected abstract EObject getMainObject();

	/**
	 * Triggers a selection update if the given object is the current selection. This can be used from the sandbox
	 * editors to refresh the properties view when the local component/device has registered.
	 * @param businessObject
	 */
	public void refreshSelectedObject(final Object businessObject) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				ISelection selection = getSelection();
				if (!selection.isEmpty()) {
					IStructuredSelection ss = (IStructuredSelection) selection;
					Object element = ss.getFirstElement();
					if (element instanceof EditPart) {
						EditPart part = (EditPart) element;
						Object bo = DUtil.getBusinessObject((PictogramElement) part.getModel());
						if (bo == businessObject) {
							// The properties view ignores the new selection if it's equal to the old selection, even
							// though in our case it may lead to a change in input; setting the selection to the whole
							// diagram and then back to the original selection triggers a refresh.
							getSite().getSelectionProvider().setSelection(new StructuredSelection(part.getRoot()));
							getSite().getSelectionProvider().setSelection(selection);
						}
					}
				}
			}
		});
	}

}

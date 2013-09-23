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
package gov.redhawk.ui.editor;

import gov.redhawk.eclipsecorba.library.IdlLibrary;
import gov.redhawk.eclipsecorba.library.util.RefreshIdlLibraryJob;
import gov.redhawk.internal.ui.ScaIdeConstants;
import gov.redhawk.internal.ui.editor.validation.ValidatingEContentAdapter;
import gov.redhawk.ui.RedhawkUiActivator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mil.jpeojtrs.sca.validator.AdvancedEObjectValidator;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EditingDomainEObjectObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.databinding.swt.ISWTObservable;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.viewers.IPostSelectionProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.MultiPageEditorSite;
import org.eclipse.ui.progress.WorkbenchJob;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.xml.sax.InputSource;

/**
 * The Class SCAFormEditor.
 */
public abstract class SCAFormEditor extends FormEditor implements IEditingDomainProvider, IMenuListener, IExecutableExtension, IViewerProvider, IGotoMarker {

	/**
	 * @since 6.0
	 */
	public static int getFieldBindingDelay() {
		return 200;
	}

	/**
	 * Heavily inspired by code in the GEF LogicEditor example
	 * 
	 * @since 6.0
	 */
	public class ResourceTracker implements IResourceChangeListener, IResourceDeltaVisitor {

		private final Set<IResource> trackedResources;

		public ResourceTracker() {
			super();
			this.trackedResources = new HashSet<IResource>();
		}

		public ResourceTracker(final IResource[] loadedResources) {
			super();
			this.trackedResources = new HashSet<IResource>(Arrays.asList(loadedResources));
		}

		public void addTrackedResource(final IResource resource) {
			this.trackedResources.add(resource);
		}

		public void removeTrackedResource(final IResource resource) {
			this.trackedResources.remove(resource);
		}

		public void clearTrackedResources() {
			this.trackedResources.clear();
		}

		@Override
		public void resourceChanged(final IResourceChangeEvent event) {
			final IResourceDelta delta = event.getDelta();
			switch (event.getType()) {
			case IResourceChangeEvent.POST_CHANGE:
			case IResourceChangeEvent.PRE_CLOSE:
			case IResourceChangeEvent.PRE_DELETE:
				if (delta != null) {
					if (Display.getCurrent() == null) {
						PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

							@Override
							public void run() {
								try {
									delta.accept(ResourceTracker.this);
								} catch (final CoreException e) {
									// PASS
								}
							}

						});
					} else {
						try {
							delta.accept(this);
						} catch (final CoreException e) {
							// PASS
						}
					}

				}
				break;
			default:
				break;
			}
		}

		@Override
		public boolean visit(final IResourceDelta delta) throws CoreException {
			if ((delta == null) || (!this.trackedResources.contains(delta.getResource()))) {
				return true;
			}

			switch (delta.getKind()) {
			case IResourceDelta.REMOVED:
				if ((IResourceDelta.MOVED_TO & delta.getFlags()) == 0) { // deleted
					final IFile resource = ResourcesPlugin.getWorkspace().getRoot().getFile(delta.getFullPath());
					SCAFormEditor.this.resourceDeleted(resource);
				} else { // moved or renamed
					final IFile to = ResourcesPlugin.getWorkspace().getRoot().getFile(delta.getMovedToPath());
					SCAFormEditor.this.resourceMoved(delta.getResource(), to);
				}
				break;
			case IResourceDelta.CHANGED:
				if (delta.getResource() instanceof IFile) {
					final IFile newFile = (IFile) delta.getResource();

					// If the resource has been changed on disk and the editor is dirty we will prompt the user before blowing away its changes.
					if (SCAFormEditor.this.isDirty() && !SCAFormEditor.this.editorSaving) {
						boolean confirmOverwrite = MessageDialog.openConfirm(SCAFormEditor.this.getSite().getShell(), "File Changed", "The file '"
							+ delta.getResource().getFullPath().toOSString()
							+ "' has been changed on the file system. Do you want to replace the editor contents with these changes?");

						SCAFormEditor.this.setFocus();

						if (confirmOverwrite) {
							SCAFormEditor.this.resourceChanged(newFile, delta);
							// Since the document gets changed within the resourceChanged function, the document will be marked dirty
							// even though the document matches what is on disk.  Currently I cannot figure out a good way to get rid
							// of this dirty flag.  It's a small bug that should not happen often though.

						}
					}

					// If the editor is not dirty and the editor is not in the middle of saving then we'll take the changes.
					// This will cause the editor to appear dirty erroneously as mentioned in the note above.  
					if (!SCAFormEditor.this.isDirty() && !SCAFormEditor.this.editorSaving) {
						SCAFormEditor.this.resourceChanged(newFile, delta);
					}
				}
				break;
			default:
				break;
			}

			return false;
		}
	}

	/**
	 * @since 6.0
	 */
	private ResourceTracker resourceTracker = new ResourceTracker();

	/**
	 * Updates the OutlinePage selection.
	 */
	public class SCAFormEditorChangeListener implements ISelectionChangedListener {

		/**
		 * Installs this selection changed listener with the given selection provider. If the selection provider is a
		 * post selection provider, post selection changed events are the preferred choice, otherwise normal selection
		 * changed events are requested.
		 * 
		 * @param selectionProvider the selection provider
		 */
		public void install(final ISelectionProvider selectionProvider) {
			if (selectionProvider == null) {
				return;
			}

			if (selectionProvider instanceof IPostSelectionProvider) {
				final IPostSelectionProvider provider = (IPostSelectionProvider) selectionProvider;
				provider.addPostSelectionChangedListener(this);
			} else {
				selectionProvider.addSelectionChangedListener(this);
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void selectionChanged(final SelectionChangedEvent event) {
			if (RedhawkUiActivator.getDefault().getPreferenceStore().getBoolean("ToggleLinkWithEditorAction.isChecked")) {
				if (getFormOutline() != null) {
					getFormOutline().setSelection(event.getSelection());
				}
			}
		}

		/**
		 * Removes this selection changed listener from the given selection provider.
		 * 
		 * @param selectionProvider the selection provider
		 */
		public void uninstall(final ISelectionProvider selectionProvider) {
			if (selectionProvider == null) {
				return;
			}

			if (selectionProvider instanceof IPostSelectionProvider) {
				final IPostSelectionProvider provider = (IPostSelectionProvider) selectionProvider;
				provider.removePostSelectionChangedListener(this);
			} else {
				selectionProvider.removeSelectionChangedListener(this);
			}
		}

	}

	private static final String F_DIALOG_EDITOR_SECTION_KEY = "pde-form-editor"; //$NON-NLS-1$

	private WorkbenchJob updateTitleJob;

	@Override
	protected void setSite(final org.eclipse.ui.IWorkbenchPartSite site) {
		this.updateTitleJob = new WorkbenchJob(site.getShell().getDisplay(), "UpdateTitle") {
			{
				setUser(false);
				setSystem(true);
			}

			@Override
			public IStatus runInUIThread(final IProgressMonitor monitor) {
				firePropertyChange(IWorkbenchPart.PROP_TITLE);
				return Status.OK_STATUS;
			}

		};
		super.setSite(site);
	}

	/**
	 * The editor selection changed listener.
	 */
	private SCAFormEditorChangeListener fEditorSelectionChangedListener;
	private Clipboard clipboard;
	private IContentOutlinePage fFormOutline;
	private SCAMultiPageContentOutline fContentOutline;
	private int fLastActivePageIndex;
	private boolean fLastDirtyState;
	private boolean handledStructuredModelChange;

	/**
	 * Listen for changes to command stack and attempt to handle the change.
	 */
	private CommandStackListener commandStackListener = new CommandStackListener() {
		@Override
		public void commandStackChanged(final EventObject event) {
			if (Display.getCurrent() != null) {
				handleStackChanged(event);
			} else if (getContainer() != null && !getContainer().isDisposed()) {
				getContainer().getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						if (getContainer() != null && !getContainer().isDisposed()) {
							handleStackChanged(event);
						}
					}
				});
			}
		}

		private void handleStackChanged(EventObject event) {
			handleStructuredModelChange(event);
			firePropertyChange(IEditorPart.PROP_DIRTY);
			final Command mostRecentCommand = ((CommandStack) event.getSource()).getMostRecentCommand();
			if (mostRecentCommand != null && mostRecentCommand.getAffectedObjects() != null && !mostRecentCommand.getAffectedObjects().isEmpty()) {
				setSelectionToViewer(mostRecentCommand.getAffectedObjects());
			}
		}
	};

	/**
	 * This keeps track of the editing domain that is used to track all changes to the model.
	 */
	private EditingDomain editingDomain;

	/** The outline cache. */
	private final Map<IEditorPart, IContentOutlinePage> outlineCache = new HashMap<IEditorPart, IContentOutlinePage>();

	private final DataBindingContext bindingContext;

	private ValidatingEContentAdapter validator;

	private static final String IDL_PREFERENCE_NODE_ID = "gov.redhawk.ide";

	private String id;

	private boolean disposed;

	private Resource mainResource;

	private final Map<Resource, IDocument> resourceToDocumentMap = new HashMap<Resource, IDocument>();

	private boolean editorSaving;

	private RefreshIdlLibraryJob reloadLibraryJob;

	/**
	 * The Class SCAMultiPageEditorSite.
	 */
	private static class SCAMultiPageEditorSite extends MultiPageEditorSite {

		/**
		 * Instantiates a new sCA multi page editor site.
		 * 
		 * @param multiPageEditor the multi page editor
		 * @param editor the editor
		 */
		public SCAMultiPageEditorSite(final MultiPageEditorPart multiPageEditor, final IEditorPart editor) {
			super(multiPageEditor, editor);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public IWorkbenchPart getPart() {
			return getMultiPageEditor();
		}

		@Override
		protected void handleSelectionChanged(SelectionChangedEvent event) {
			super.handleSelectionChanged(event);
			super.handlePostSelectionChanged(event);
		}
	}

	/**
	 * Instantiates a new sCA form editor.
	 */
	public SCAFormEditor() {
		this.bindingContext = new EMFDataBindingContext();
	}

	/**
	 * Handles activation of the editor or it's associated views.
	 */
	protected void handleActivate() {
		// Recompute the read only state.
		//
		if (this.editingDomain instanceof AdapterFactoryEditingDomain) {
			if (((AdapterFactoryEditingDomain) this.editingDomain).getResourceToReadOnlyMap() != null) {
				((AdapterFactoryEditingDomain) this.editingDomain).getResourceToReadOnlyMap().clear();

				// Refresh any actions that may become enabled or disabled.
				//
				getSite().getSelectionProvider().setSelection(getSite().getSelectionProvider().getSelection());
			}
		}
	}

	/**
	 * This sets up the editing domain for the model editor.
	 */
	protected void initializeEditingDomain() {
		// Create the editing domain with a special command stack.
		//
		TransactionalEditingDomain domain = TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain(getEditingDomainId());

		if (domain == null) {
			domain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
			domain.setID(getEditingDomainId());

			// Create an adapter factory that yields item providers.
			//
			final ComposedAdapterFactory localAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

			localAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
			localAdapterFactory.addAdapterFactory(getSpecificAdapterFactory());
			localAdapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
			((AdapterFactoryEditingDomain) domain).setAdapterFactory(localAdapterFactory);
		}

		final Map<String, Boolean> myOptions = new HashMap<String, Boolean>();
		myOptions.put(Transaction.OPTION_NO_VALIDATION, Boolean.TRUE);
		if (domain instanceof TransactionalEditingDomainImpl) {
			((TransactionalEditingDomainImpl) domain).setDefaultTransactionOptions(myOptions);
		}

		final NotificationFilter resourceModifiedFilter = NotificationFilter.createNotifierFilter(domain.getResourceSet()).and(
			NotificationFilter.createEventTypeFilter(Notification.ADD)).and(
			NotificationFilter.createFeatureFilter(ResourceSet.class, ResourceSet.RESOURCE_SET__RESOURCES));
		domain.getResourceSet().eAdapters().add(new Adapter() {

			private Notifier myTarget;

			@Override
			public Notifier getTarget() {
				return this.myTarget;
			}

			@Override
			public boolean isAdapterForType(final Object type) {
				return false;
			}

			@Override
			public void notifyChanged(final Notification notification) {
				if (resourceModifiedFilter.matches(notification)) {
					final Object value = notification.getNewValue();
					if (value instanceof Resource) {
						((Resource) value).setTrackingModification(true);
					}
				}
			}

			@Override
			public void setTarget(final Notifier newTarget) {
				this.myTarget = newTarget;
			}

		});

		// Add a listener to set the most recent command's affected objects to
		// be the selection of the viewer with focus.
		//
		domain.getCommandStack().addCommandStackListener(this.commandStackListener);

		this.editingDomain = domain;
	}

	/**
	 * Provide access to the command stack listener so that subclasses may remove if so desired.
	 * 
	 * @return the {@link CommandStackListener} associated with this editors editing domain
	 * @since 4.0
	 */
	protected CommandStackListener getCommandStackListener() {
		return this.commandStackListener;
	}

	/**
	 * @return The ID of the Editing Domain used by this editor
	 */
	public abstract String getEditingDomainId();

	/**
	 * @return Adapter Factory Specific to this editor
	 */
	protected abstract AdapterFactory getSpecificAdapterFactory();

	/**
	 * We must override nested site creation so that we properly pass the source editor contributor when asked.
	 * 
	 * @param editor the editor
	 * @return the i editor site
	 */
	@Override
	protected IEditorSite createSite(final IEditorPart editor) {
		return new SCAMultiPageEditorSite(this, editor);
	}

	/**
	 * Gets the context manager.
	 * 
	 * @return the context manager
	 */
	@Override
	public EditingDomain getEditingDomain() {
		return this.editingDomain;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FormToolkit createToolkit(final Display display) {
		// Create a toolkit that shares colors between editors.
		return new FormToolkit(RedhawkUiActivator.getDefault().getFormColors(display));
	}

	/*
	 * When subclassed, don't forget to call 'super'
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createPages() {
		this.clipboard = new Clipboard(getContainer().getDisplay());

		super.createPages();

		final int pageIndex = computeInitialPageId();
		if (pageIndex >= 0 && pageIndex < getPageCount()) {
			setActivePage(pageIndex);
		}
		updateTitle();

		final Notifier obj = getRootValidationNotifier();
		this.validator = new ValidatingEContentAdapter(this, obj);
		if (obj != null) {
			obj.eAdapters().add(this.validator);
			this.validate();
		}

		// Ensures that this editor will only display the page's tab
		// area if there are more than one page
		//
		getContainer().addControlListener(new ControlAdapter() {
			private boolean guard = false;

			@Override
			public void controlResized(final ControlEvent event) {
				if (!this.guard) {
					this.guard = true;
					hideTabs();
					this.guard = false;
				}
			}
		});
	}

	/**
	 * @return
	 */
	protected Notifier getRootValidationNotifier() {
		return this.getMainResource();
	}

	/**
	 * If there is just one page in the multi-page editor part, this hides the single tab at the bottom. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 */
	protected void hideTabs() {
		if (getPageCount() <= 1) {
			setPageText(0, "");
			if (getContainer() instanceof CTabFolder) {
				((CTabFolder) getContainer()).setTabHeight(1);
				final Point point = getContainer().getSize();
				getContainer().setSize(point.x, point.y + 6); // SUPPRESS CHECKSTYLE MagicNumber
			}
		}
	}

	/**
	 * If there is more than one page in the multi-page editor part, this shows the tabs at the bottom. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void showTabs() {
		if (getPageCount() > 1) {
			if (getContainer() instanceof CTabFolder) {
				((CTabFolder) getContainer()).setTabHeight(SWT.DEFAULT);
				final Point point = getContainer().getSize();
				getContainer().setSize(point.x, point.y - 6); // SUPPRESS CHECKSTYLE MagicNumber
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void pageChange(final int newPageIndex) {
		super.pageChange(newPageIndex);
		updateContentOutline(newPageIndex);
		this.fLastActivePageIndex = newPageIndex;
		final Viewer viewer = getViewer();
		if (viewer != null) {
			this.setSelection(viewer.getSelection());
		} else {
			this.setSelection(new StructuredSelection());
		}
		validate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFocus() {
		super.setFocus();
		final IFormPage page = getActivePageInstance();
		// Could be done on setActive in PDEFormPage;
		// but setActive only handles page switches and not focus events
		if ((page != null) && (page instanceof ScaFormPage)) {
			((ScaFormPage) page).updateFormSelection();
		}
	}

	/**
	 * Gets the clipboard.
	 * 
	 * @return the clipboard
	 * @since 5.0
	 */
	public Clipboard getClipboard() {
		return this.clipboard;
	}

	/**
	 * Gets the contributor.
	 * 
	 * @return the contributor
	 */
	public ScaMultipageActionBarContributor getActionBarContributor() {
		return (ScaMultipageActionBarContributor) getEditorSite().getActionBarContributor();
	}

	/**
	 * Compute initial page id.
	 * 
	 * @return the string
	 */
	protected int computeInitialPageId() {
		int firstPageId = 0;
		final String storedFirstPageId = loadDefaultPage();
		if (storedFirstPageId != null) {
			try {
				firstPageId = Integer.valueOf(storedFirstPageId);
			} catch (final NumberFormatException e) {
				firstPageId = 0;
			}
		}
		if (firstPageId >= getPageCount()) {
			firstPageId = 0;
		}
		return firstPageId;
	}

	/**
	 * Update title.
	 */
	public void updateTitle() {
		this.updateTitleJob.schedule();
	}

	/**
	 * Gets the title property.
	 * 
	 * @return the title property
	 */
	public String getTitleProperty() {
		return ""; //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doSave(final IProgressMonitor monitor) {
		try {
			this.editorSaving = true;
			commitPages(true);
			monitor.beginTask("Saving " + this.getTitle(), this.getPageCount() + 2);
			internalDoValidate(new SubProgressMonitor(monitor, 1));
			for (int i = 0; i < this.getPageCount(); i++) {
				final IEditorPart part = this.getEditor(i);
				if (part != null && part.isDirty()) {
					part.doSave(new SubProgressMonitor(monitor, 1));
				} else {
					monitor.worked(1);
				}
			}
			if (isDirty()) {
				emfDoSave(new SubProgressMonitor(monitor, 1));
			}
			editorDirtyStateChanged();
		} catch (final OperationCanceledException e) {
			// PASS
		} finally {
			monitor.done();
			this.editorSaving = false;
		}
	}

	/**
	 * @since 6.0
	 */
	protected void closeEditor(final boolean save) {
		getSite().getPage().closeEditor(this, save);
	}

	/**
	 * This is for implementing {@link IEditorPart} and simply saves the model file.
	 * 
	 * @param progressMonitor the progress monitor
	 * @since 2.3
	 */
	protected void emfDoSave(final IProgressMonitor progressMonitor) {
		// Save only resources that have actually changed.
		//
		final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);

		// Do the work within an operation because this is a long running
		// activity that modifies the workbench.
		//
		final WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			// This is the method that gets invoked when the operation runs.
			//
			@Override
			public void execute(final IProgressMonitor monitor) throws CoreException {
				// Save the resources to the file system.
				//
				for (final Resource resource : SCAFormEditor.this.editingDomain.getResourceSet().getResources()) {
					if (isPersisted(resource) && !SCAFormEditor.this.editingDomain.isReadOnly(resource)) {
						// Lastly, check to make sure this resource is not only local to the current workspace
						// but is also local to the project we're currently saving.  This fixes bug # 266
						if (isPersisted(resource) && isLocal(resource)) {
							try {
								resource.save(saveOptions);
							} catch (final Exception exception) {
								throw new CoreException(new Status(IStatus.ERROR, RedhawkUiActivator.getPluginId(), "Failed to save resource: " + resource,
									exception));
							}
						}

					}
				}
			}

			/**
			 * Checks to see if the given resource is within the same project as the SCAFormEditor.
			 * This fixes bug # 266
			 * @param resource The resource to be checked
			 * @return 
			 */
			private boolean isLocal(final Resource resource) {
				// If the resource isn't located in the workspace it isn't local
				if (!resource.getURI().isPlatformResource()) {
					return false;
				}

				// Get the file for the referenced resource
				IPath pathToResource = new Path(resource.getURI().toPlatformString(true));
				IFile resourceFile = ResourcesPlugin.getWorkspace().getRoot().getFile(pathToResource);

				// Check the file's project against the SCA Form Editor's scd file's project.
				if (resourceFile != null && resourceFile.getProject() != null) {
					if (SCAFormEditor.this.getEditorInput() instanceof IFileEditorInput) {
						IFile localFile = ((IFileEditorInput) SCAFormEditor.this.getEditorInput()).getFile();
						if (localFile != null) {
							if (localFile.getProject().equals(resourceFile.getProject())) {
								return true;
							}
						}
					}
				}
				return false;
			}
		};

		try {
			operation.run(progressMonitor);

			// Refresh the necessary state.
			//
			((BasicCommandStack) this.editingDomain.getCommandStack()).saveIsDone();
		} catch (final Exception exception) {
			// Something went wrong that shouldn't.
			//
			StatusManager.getManager().handle(
				new Status(IStatus.ERROR, RedhawkUiActivator.getPluginId(), "Error occured while attempting to save.", exception),
				StatusManager.SHOW | StatusManager.LOG);
		}
	}

	/**
	 * This returns whether something has been persisted to the URI of the specified resource. The implementation uses
	 * the URI converter from the editor's resource set to try to open an input stream.
	 * 
	 * @param resource the resource
	 * @return true, if checks if is persisted
	 * @since 2.1
	 */
	public boolean isPersisted(final Resource resource) {
		// If the resource isn't located in the workspace it isn't local
		if (resource != null && resource.getURI() != null && resource.getURI().isPlatformResource()) {
			// Get the file for the referenced resource
			IPath pathToResource = new Path(resource.getURI().toPlatformString(true));
			IFile resourceFile = ResourcesPlugin.getWorkspace().getRoot().getFile(pathToResource);

			// Check the file's project against the SCA Form Editor's scd file's project.
			if (resourceFile != null && resourceFile.getProject() != null) {
				if (SCAFormEditor.this.getEditorInput() instanceof IFileEditorInput) {
					IFile localFile = ((IFileEditorInput) SCAFormEditor.this.getEditorInput()).getFile();
					if (localFile != null) {
						if (localFile.getProject().equals(resourceFile.getProject())) {
							return true;
						}
					}
				}
			}
			return false;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void gotoMarker(final IMarker marker) {
		final String uriAttribute = marker.getAttribute(EValidator.URI_ATTRIBUTE, null);
		final int featureID = marker.getAttribute(AdvancedEObjectValidator.FEATURE_ID, AdvancedEObjectValidator.DEFAULT_ID);
		if (uriAttribute != null) {
			final URI uri = URI.createURI(uriAttribute);
			final EObject eObject = this.editingDomain.getResourceSet().getEObject(uri, true);
			if (eObject != null) {
				for (final Object o : this.bindingContext.getBindings()) {
					final Binding binding = (Binding) o;
					EditingDomainEObjectObservableValue emfObservable = null;
					ISWTObservable swtObservable = null;
					final IObservable model = binding.getModel();
					final IObservable target = binding.getTarget();
					if (model instanceof EditingDomainEObjectObservableValue && target instanceof ISWTObservable) {
						emfObservable = (EditingDomainEObjectObservableValue) model;
						swtObservable = (ISWTObservable) target;

					} else if (binding.getTarget() instanceof EditingDomainEObjectObservableValue && model instanceof ISWTObservable) {
						swtObservable = (ISWTObservable) model;
						emfObservable = (EditingDomainEObjectObservableValue) target;
					}
					if (emfObservable != null && swtObservable != null) {
						if (this.selectFeature(emfObservable, swtObservable, eObject, featureID)) {
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * Sets focus on the correct control.
	 * 
	 * @param emfObservable
	 * @param swtObservable
	 * @param object
	 * @param featureID
	 * @return
	 */
	private boolean selectFeature(final EditingDomainEObjectObservableValue emfObservable, final ISWTObservable swtObservable, final EObject object,
		final int featureID) {
		boolean retVal = false;
		final Object observed = emfObservable.getObserved();
		if (emfObservable.getValueType() instanceof EStructuralFeature) {
			final int myId = ((EStructuralFeature) emfObservable.getValueType()).getFeatureID();
			if (object == observed) {
				if (featureID == myId) {
					if (swtObservable.getWidget() instanceof Control) {
						final Control control = (Control) swtObservable.getWidget();
						control.setFocus();
						retVal = true;
					}
				}
			}
		}
		return retVal;
	}

	/**
	 * This is the method called to load a resource into the editing domain's resource set based on the editor's input.
	 */
	protected void createModel() {
		final URI resourceURI = EditUIUtil.getURI(getEditorInput());
		// For safety we'll decode the URI to make sure escape sequences have been correctly represented
		String decodedURIString = URI.decode(resourceURI.toString());
		URI decodedURI = URI.createURI(decodedURIString);
		Exception exception = null;
		try {
			// Load the resource through the editing domain.
			//
			this.mainResource = this.editingDomain.getResourceSet().getResource(decodedURI, true);
		} catch (final Exception e) {
			exception = e;
			this.mainResource = this.editingDomain.getResourceSet().getResource(decodedURI, false);
		}
		if (exception != null) {
			StatusManager.getManager().handle(
				new Status(IStatus.ERROR, RedhawkUiActivator.getPluginId(), "Errors occured while loading the main resource of the editor.", exception),
				StatusManager.SHOW | StatusManager.LOG);
		}
	}

	/**
	 * @return the mainResource
	 */
	public Resource getMainResource() {
		return this.mainResource;
	}

	/**
	 * This also changes the editor's input.
	 */
	@Override
	public void doSaveAs() {
		if (!isSaveAsAllowed()) {
			throw new UnsupportedOperationException("Save as not supported");
		}
		final SaveAsDialog saveAsDialog = new SaveAsDialog(getSite().getShell());
		saveAsDialog.open();
		final IPath path = saveAsDialog.getResult();
		if (path != null) {
			final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			if (file != null) {
				doSaveAs(URI.createPlatformResourceURI(file.getFullPath().toString(), true), new FileEditorInput(file));
			}
		}
	}

	/**
	 * Do save as. This method should be overridden.
	 * 
	 * @param createPlatformResourceURI the create platform resource uri
	 * @param fileEditorInput the file editor input
	 * @since 5.0
	 */
	protected void doSaveAs(final URI createPlatformResourceURI, final FileEditorInput fileEditorInput) {
		throw new UnsupportedOperationException("Save as not supported");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	/**
	 * 
	 */
	private void storeDefaultPage() {
		final IEditorInput input = getEditorInput();
		final String pageId = Integer.toString(this.fLastActivePageIndex);
		if (pageId == null) {
			return;
		}
		if (input instanceof IFileEditorInput) {
			// Triggered by opening a file in the workspace
			// e.g. From the Package Explorer View
			setPropertyEditorPageKey((IFileEditorInput) input, pageId);
		} else if (input instanceof IStorageEditorInput) {
			// Triggered by opening a file NOT in the workspace
			// e.g. From the Plug-in View
			setDialogEditorPageKey(pageId);
		}
	}

	/**
	 * Sets the dialog editor page key.
	 * 
	 * @param pageID the page id
	 */
	protected void setDialogEditorPageKey(final String pageID) {
		// Use one global setting for all files belonging to a given editor
		// type. Use the editor ID as the key.
		// Could use the storage editor input to get the underlying file
		// and use it as a unique key; but, the dialog settings file will
		// grow out of control and we do not need that level of granularity
		final IDialogSettings section = getSettingsSection();
		section.put(getEditorID(), pageID);
	}

	/**
	 * Gets the dialog editor page key.
	 * 
	 * @return the dialog editor page key
	 */
	protected String getDialogEditorPageKey() {
		// Use one global setting for all files belonging to a given editor
		// type. Use the editor ID as the key.
		// Could use the storage editor input to get the underlying file
		// and use it as a unique key; but, the dialog settings file will
		// grow out of control and we do not need that level of granularity
		final IDialogSettings section = getSettingsSection();
		return section.get(getEditorID());
	}

	/**
	 * Gets the editor id.
	 * 
	 * @return the editor id
	 */
	protected final String getEditorID() {
		return this.id;
	}

	/**
	 * Sets the property editor page key.
	 * 
	 * @param input the input
	 * @param pageId the page id
	 * @since 5.0
	 */
	protected void setPropertyEditorPageKey(final IFileEditorInput input, final String pageId) {
		// We are using the file itself to persist the editor page key property
		// The value persists even after the editor is closed
		final IFile file = input.getFile();
		try {
			// Set the editor page ID as a persistent property on the file
			file.setPersistentProperty(ScaIdeConstants.PROPERTY_EDITOR_PAGE_KEY, pageId);
		} catch (final CoreException e) {
			// PASS
		}
	}

	/**
	 * Load default page.
	 * 
	 * @return the string
	 */
	private String loadDefaultPage() {
		final IEditorInput input = getEditorInput();
		if (input instanceof IFileEditorInput) {
			// Triggered by opening a file in the workspace
			// e.g. From the Package Explorer View
			return getPropertyEditorPageKey((IFileEditorInput) input);
		} else if (input instanceof IStorageEditorInput) {
			// Triggered by opening a file NOT in the workspace
			// e.g. From the Plug-in View
			return getDialogEditorPageKey();
		}
		return null;
	}

	/**
	 * Gets the property editor page key.
	 * 
	 * @param input the input
	 * @return the property editor page key
	 * @since 5.0
	 */
	protected String getPropertyEditorPageKey(final IFileEditorInput input) {
		// We are using the file itself to persist the editor page key property
		// The value persists even after the editor is closed
		final IFile file = input.getFile();
		// Get the persistent editor page key from the file
		try {
			return file.getPersistentProperty(ScaIdeConstants.PROPERTY_EDITOR_PAGE_KEY);
		} catch (final CoreException e) {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		this.disposed = true;
		storeDefaultPage();

		if (this.fEditorSelectionChangedListener != null) {
			this.fEditorSelectionChangedListener.uninstall(getSite().getSelectionProvider());
			this.fEditorSelectionChangedListener = null;
		}
		setSelection(new StructuredSelection());

		if (this.clipboard != null) {
			this.clipboard.dispose();
			this.clipboard = null;
		}

		if (getEditingDomain() instanceof AdapterFactoryEditingDomain) {
			if (((AdapterFactoryEditingDomain) this.editingDomain).getAdapterFactory() instanceof IDisposable) {
				((IDisposable) ((AdapterFactoryEditingDomain) this.editingDomain).getAdapterFactory()).dispose();
			}
		}
		if (this.commandStackListener != null) {
			if (this.editingDomain != null) {
				if (this.editingDomain.getCommandStack() != null) {
					this.editingDomain.getCommandStack().removeCommandStackListener(this.commandStackListener);
				}
			}
			this.commandStackListener = null;
		}
		this.editingDomain = null;

		this.outlineCache.clear();

		// Waveform Explorer editor input is ScaFileStoreEditorInput, ignore this
		if (getEditorInput() instanceof IFileEditorInput) {
			final IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			file.getWorkspace().removeResourceChangeListener(this.resourceTracker);
		}
		this.resourceTracker.clearTrackedResources();
		this.resourceTracker = null;
		super.dispose();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDirty() {
		this.fLastDirtyState = computeDirtyState();
		return this.fLastDirtyState;
	}

	/**
	 * Compute dirty state.
	 * 
	 * @return true, if successful
	 */
	private boolean computeDirtyState() {
		if ((this.editingDomain != null) && ((BasicCommandStack) this.editingDomain.getCommandStack()).isSaveNeeded()) {
			return true;
		}
		return super.isDirty();
	}

	/**
	 * Gets the last dirty state.
	 * 
	 * @return the last dirty state
	 */
	public boolean getLastDirtyState() {
		return this.fLastDirtyState;
	}

	/**
	 * Gets the settings section.
	 * 
	 * @return the settings section
	 */
	private IDialogSettings getSettingsSection() {
		// Store global settings that will persist when the editor is closed
		// in the dialog settings (This is cheating)
		// Get the dialog settings
		final IDialogSettings root = RedhawkUiActivator.getDefault().getDialogSettings();
		// Get the dialog section reserved for PDE form editors
		IDialogSettings section = root.getSection(SCAFormEditor.F_DIALOG_EDITOR_SECTION_KEY);
		// If the section is not defined, define it
		if (section == null) {
			section = root.addNewSection(SCAFormEditor.F_DIALOG_EDITOR_SECTION_KEY);
		}
		return section;
	}

	/**
	 * Sets the selection.
	 * 
	 * @param selection the new selection
	 */
	public void setSelection(final ISelection selection) {
		if (getSite() != null && getSite().getSelectionProvider() != null) {
			getSite().getSelectionProvider().setSelection(selection);
		}
	}

	/**
	 * Gets the selection.
	 * 
	 * @return the selection
	 */
	public ISelection getSelection() {
		return getSite().getSelectionProvider().getSelection();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") final Class key) {
		if (key.equals(IContentOutlinePage.class)) {
			return getContentOutline();
		}
		if (key.equals(IGotoMarker.class)) {
			return this;
		}
		return super.getAdapter(key);
	}

	/**
	 * Gets the content outline.
	 * 
	 * @return the content outline
	 */
	public SCAMultiPageContentOutline getContentOutline() {
		if (this.fContentOutline == null || this.fContentOutline.isDisposed()) {
			this.fContentOutline = new SCAMultiPageContentOutline(this);
			updateContentOutline(getActivePage());
		}
		return this.fContentOutline;
	}

	/**
	 * Gets the form outline.
	 * 
	 * @return outline page or null
	 * @since 6.0
	 */
	public IContentOutlinePage getFormOutline() {
		if (this.fFormOutline == null) {
			this.fFormOutline = createContentOutline();
			if (this.fFormOutline != null) {
				this.fEditorSelectionChangedListener = new SCAFormEditorChangeListener();
				this.fEditorSelectionChangedListener.install(getSite().getSelectionProvider());
			}
		}
		return this.fFormOutline;
	}

	/**
	 * Creates the content outline.
	 * 
	 * @return the i sortable content outline page
	 */
	protected abstract IContentOutlinePage createContentOutline();

	/**
	 * Update content outline.
	 * 
	 * @param index the index
	 */
	private void updateContentOutline(final int index) {
		if (this.fContentOutline == null) {
			return;
		}
		IContentOutlinePage outline = null;

		final IEditorPart editor = this.getEditor(index);
		if (editor != null) {
			outline = this.outlineCache.get(editor);
			if (outline == null) {
				final Object adapter = editor.getAdapter(IContentOutlinePage.class);
				if (adapter instanceof IContentOutlinePage) {
					outline = (IContentOutlinePage) adapter;
					this.outlineCache.put(editor, outline);
				}
			}
		}
		if (outline == null) {
			outline = getFormOutline();
			if (outline != null && outline instanceof FormOutlinePage) {
				((FormOutlinePage) outline).refresh();
			}
		}
		this.fContentOutline.setPageActive(outline);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFormPage setActivePage(final String pageId) {
		final IFormPage page = super.setActivePage(pageId);
		if (page != null) {
			updateContentOutline(page.getIndex());
		}
		return page;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
		super.init(site, input);

		//		ResourcesPlugin.getWorkspace().addResourceChangeListener(this.resourceChangeListener, IResourceChangeEvent.POST_CHANGE);
	}

	@Override
	protected void addPages() {
		// TODO Auto-generated method stub

	}

	/**
	 * Add resource tracking to the provided input. Sub-classes may override but should call super.setInput().
	 * Sub-classes may add other resources to track via the getResourceTracker().addTrackedResource()
	 */
	@Override
	protected void setInput(final IEditorInput input) {
		IFile file = null;
		if (input instanceof IFileEditorInput) {
			file = ((IFileEditorInput) input).getFile();
		}

		IFile previousFile = null;
		if ((getEditorInput() != null) && (getEditorInput() instanceof IFileEditorInput)) {
			previousFile = ((IFileEditorInput) getEditorInput()).getFile();
		}

		// Remove the previous tracker...not technically necessary but 
		// implemented for the sake of completeness
		if (previousFile != null) {
			this.resourceTracker.clearTrackedResources();
			previousFile.getWorkspace().removeResourceChangeListener(this.resourceTracker);
		}

		initializeEditingDomain();
		super.setInput(input);
		createModel();

		if (file != null) {
			this.resourceTracker.addTrackedResource(file);
			file.getWorkspace().addResourceChangeListener(this.resourceTracker);
		}
	}

	/**
	 * Contribute to toolbar.
	 * 
	 * @param manager the manager
	 */
	public void contributeToToolbar(final IToolBarManager manager) {
	}

	/**
	 * This creates a context menu for the viewer and adds a listener as well registering the menu for extension.
	 * 
	 * @param viewer the viewer
	 */
	protected void createContextMenuFor(final StructuredViewer viewer) {
		final MenuManager contextMenu = new MenuManager("#PopUp");
		contextMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(this);
		final Menu menu = contextMenu.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(contextMenu, new UnwrappingSelectionProvider(viewer));

		final int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		final Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
		viewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(getEditingDomain(), viewer));
	}

	/**
	 * This implements {@link org.eclipse.jface.action.IMenuListener} to help fill the context menus with contributions
	 * from the Edit menu.
	 * 
	 * @param menuManager the menu manager
	 */
	@Override
	public void menuAboutToShow(final IMenuManager menuManager) {
		((IMenuListener) getEditorSite().getActionBarContributor()).menuAboutToShow(menuManager);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setInitializationData(final IConfigurationElement config, final String propertyName, final Object data) {
		super.setInitializationData(config, propertyName, data);

		this.id = config.getAttribute("id");
	}

	/**
	 * @return
	 */
	public DataBindingContext getDataBindingContext() {
		return this.bindingContext;
	}

	/**
	 * @return
	 */
	public AdapterFactory getAdapterFactory() {
		if (getEditingDomain() instanceof AdapterFactoryEditingDomain) {
			return ((AdapterFactoryEditingDomain) getEditingDomain()).getAdapterFactory();
		}
		return null;
	}

	/**
	 * @since 6.0
	 */
	protected void validate() {
		if (this.validator != null) {
			Display display = null;
			if (getSite() != null) {
				display = getSite().getShell().getDisplay();
			}
			final WorkbenchJob job = new WorkbenchJob(display, "Validating editor: " + this.getTitle()) {

				@Override
				public boolean shouldRun() {
					return super.shouldRun() && !isDisposed();
				}

				@Override
				public IStatus runInUIThread(final IProgressMonitor monitor) {
					SCAFormEditor.this.validator.validate();
					final Viewer viewer = getViewer();
					if (viewer != null) {
						viewer.refresh();
					}
					return Status.OK_STATUS;
				}

			};
			job.setSystem(true);
			job.setUser(false);

			//Validation Job should be DECORATE Priority; schedule for future to allow resource reload to complete.
			job.setPriority(Job.DECORATE);
			job.schedule();
		}
	}

	/**
	 * @param monitor
	 */
	protected void internalDoValidate(final IProgressMonitor monitor) {

		// result of the Validation
		final BasicDiagnostic diagnostic = new BasicDiagnostic();
		final List<Resource> resources = new ArrayList<Resource>();
		resources.addAll(getEditingDomain().getResourceSet().getResources());
		for (final Resource resource : resources) {
			if (isPersisted(resource)) {
				for (final EObject obj : resource.getContents()) {
					this.validator.validate(obj, diagnostic);
				}
			}
		}

		if (diagnostic.getSeverity() == Diagnostic.ERROR) {
			final Dialog dialog = new DiagnosticDialog(getSite().getShell(), "Invalid Model", null, diagnostic, Diagnostic.ERROR | Diagnostic.WARNING) {
				@Override
				protected Control createMessageArea(final Composite composite) {
					this.message = "Errors have been detected. Do you want to save anyway?";
					return super.createMessageArea(composite);
				}

				@Override
				protected void createButtonsForButtonBar(final Composite parent) {
					// create OK and Details buttons
					createButton(parent, IDialogConstants.OK_ID, IDialogConstants.YES_LABEL, true);
					createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.NO_LABEL, true);
					createDetailsButton(parent);
				}
			};

			if (dialog.open() != Window.OK) {
				if (monitor != null) {
					monitor.setCanceled(true);
				}
				throw new OperationCanceledException();
			}
		}

	}

	/**
	 * @return
	 */
	public boolean isDisposed() {
		return this.disposed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Viewer getViewer() {
		final IFormPage page = this.getActivePageInstance();
		if (page instanceof IViewerProvider) {
			return ((IViewerProvider) page).getViewer();
		}
		return null;
	}

	@Override
	public int addPage(final IFormPage page) throws PartInitException {
		return super.addPage(page);
	};

	/**
	 * @since 6.0
	 */
	public TextEditor createTextEditor(final IEditorInput input) {
		TextEditor editor;
		// StructuredTextEditors only work on workspace entries
		// because
		// org.eclipse.wst.sse.core.FileBufferModelManager:bufferCreated()
		// assumes that the editor input is in the workspace.
		if (input instanceof FileEditorInput) {
			try {
				editor = new org.eclipse.wst.sse.ui.StructuredTextEditor();
			} catch (final NoClassDefFoundError e) {
				editor = new TextEditor();
			}
		} else {
			editor = new TextEditor();
		}
		return editor;
	}

	/**
	 * Adds a complete editor part to the multi-page editor and associates an editor document with a resource; adds a
	 * document listener to allow document changes to be propagated to the resource.
	 * 
	 * @param editor the nested editor
	 * @param input the input of the nested editor
	 * @param resource the resource to associate with the editor
	 * @return the index of the page in the editor
	 * @throws PartInitException
	 * @since 4.0
	 */
	public int addPage(final IEditorPart editor, final IEditorInput input, final Resource resource) throws PartInitException {
		return addPage(-1, editor, input, resource);
	}

	/**
	 * Adds a complete editor part to the multi-page editor and associates an editor document with a resource; adds a
	 * document listener to allow document changes to be propagated to the resource.
	 * 
	 * @param editor the nested editor
	 * @param input the input of the nested editor
	 * @param resource the resource to associate with the editor
	 * @return the index of the page in the editor
	 * @throws PartInitException
	 * @since 6.0
	 */
	public int addPage(int index, final IEditorPart editor, final IEditorInput input, final Resource resource) throws PartInitException {
		if (index == -1) {
			index = super.addPage(editor, input);
		} else {
			super.addPage(index, editor, input);
		}
		if (editor instanceof TextEditor) {
			final IDocument document = ((TextEditor) editor).getDocumentProvider().getDocument(editor.getEditorInput());
			this.resourceToDocumentMap.put(resource, document);
			document.addDocumentListener(new IDocumentListener() {

				@Override
				public void documentAboutToBeChanged(final DocumentEvent documentEvent) {
					// Ignore
				}

				@Override
				public void documentChanged(final DocumentEvent documentEvent) {
					try {
						if (SCAFormEditor.this.handledStructuredModelChange) {
							SCAFormEditor.this.handledStructuredModelChange = false;
						} else {
							handleDocumentChange(resource);
						}
					} catch (final Exception exception) {
						//PASS
					}
				}
			});
		}
		return index;
	}

	/**
	 * Updates a {@link Resource} with changes to its associated document.
	 * 
	 * @param document the document that changed
	 * @since 4.0
	 */
	protected void handleDocumentChange(final Resource resource) {
		final IDocument document = this.resourceToDocumentMap.get(resource);
		final XMLResource xmlResource = (XMLResource) resource;
		try {
			if (xmlResource.isLoaded()) {
				xmlResource.unload();
			}
			xmlResource.load(new InputSource(new StringReader(document.get())), xmlResource.getDefaultLoadOptions());
		} catch (final Exception exception) {
			//PASS - Allow the validators to do their job rather than prevent invalid xml
		}

	}

	/**
	 * Updates each document associated with this editor based on its associated {@link Resource} changes.
	 * 
	 * @since 4.0
	 */
	protected void handleStructuredModelChange(final EventObject event) {
		if (isDisposed()) {
			return;
		}
		if (event.getSource() instanceof TransactionalCommandStack) {
			final TransactionalCommandStack stack = ((TransactionalCommandStack) event.getSource());
			if (stack.getMostRecentCommand() != null) {

				final Collection< ? > objects = stack.getMostRecentCommand().getAffectedObjects();
				if (objects.isEmpty()) {
					updateAllDocs();
				} else {
					for (final Object obj : objects) {
						if (obj instanceof EObject) {
							final Resource resource = ((EObject) obj).eResource();
							updateDocument(resource);
						}
					}
				}
			} else {
				updateAllDocs();
			}
		}
	}

	private void updateAllDocs() {
		for (Resource r : resourceToDocumentMap.keySet()) {
			updateDocument(r);
		}
	}

	/**
	 * Updates the document that maps to the given resource.  
	 * @param resource The EMF Resource object which has been changed.  This resource must be
	 * within the resource to document map in order to pull up the corresponding document.
	 */
	private void updateDocument(final Resource resource) {
		if (this.resourceToDocumentMap.containsKey(resource)) {

			final IDocument document = this.resourceToDocumentMap.get(resource);

			//taken from org.eclipse.xsd.presentation.XSDEditor
			final ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
				resource.save(out, null);
				final String newContent = out.toString();
				final String oldContent = document.get();
				int startIndex = 0;
				while (startIndex < newContent.length() && startIndex < oldContent.length() && newContent.charAt(startIndex) == oldContent.charAt(startIndex)) {
					++startIndex;
				}
				int newEndIndex = newContent.length() - 1;
				int oldEndIndex = oldContent.length() - 1;
				while (newEndIndex >= startIndex && oldEndIndex >= startIndex && newContent.charAt(newEndIndex) == oldContent.charAt(oldEndIndex)) {
					--newEndIndex;
					--oldEndIndex;
				}

				final String replacement = newContent.substring(startIndex, newEndIndex + 1);
				final int length = oldEndIndex - startIndex + 1;
				this.handledStructuredModelChange = true;
				//Only replace if there is actually a change
				if (!"".equals(replacement) || length != 0) {
					document.replace(startIndex, length, replacement);
				}
			} catch (final Exception exception) {
				//PASS
			}
		}
	}

	/**
	 * @since 7.0
	 */
	public List< ? > getPages() {
		if (this.pages == null) {
			return null;
		}
		return Collections.unmodifiableList((List< ? >) this.pages);
	}

	/**
	 * @since 2.0
	 */
	public abstract List<Object> getOutlineItems();

	/**
	 * @return
	 * @since 2.1
	 */
	public IdlLibrary getIdlLibrary() {
		return RedhawkUiActivator.getDefault().getIdlLibraryService().getLibrary();
	}

	/**
	 * @throws CoreException
	 * @since 6.0
	 */
	public void reloadIdlLibrary() {
		if (reloadLibraryJob == null) {
			reloadLibraryJob = new RefreshIdlLibraryJob(getIdlLibrary());
		}
		reloadLibraryJob.schedule();
	}

	/**
	 * @throws CoreException
	 * @since 3.0
	 */
	public IdlLibrary loadIdlLibrary(final IProgressMonitor monitor) throws CoreException {
		IdlLibrary library = getIdlLibrary();
		if (library != null) {
			IStatus status = library.getLoadStatus();
			if (status == null) {
				library.load(monitor);
			}
		}
		return library;
	}

	/**
	 * @return
	 * @since 2.1
	 */
	protected List<IPath> getDefaultIdlIncludePath() {
		return Collections.emptyList();
	}

	/**
	 * Sets the selection on this editor's viewer.
	 * 
	 * @param collection the objects to select in the viewer
	 * @since 5.0
	 */
	public void setSelectionToViewer(final Collection< ? > collection) {
		final Collection< ? > theSelection = collection;
		// Make sure it's okay.
		//
		if (theSelection != null && !theSelection.isEmpty()) {
			final Runnable runnable = new Runnable() {
				@Override
				public void run() {
					// Try to select the items in the current content viewer of the editor.
					//
					if (getViewer() != null) {
						getViewer().setSelection(new StructuredSelection(theSelection.toArray()), true);
					}
				}
			};
			getSite().getShell().getDisplay().asyncExec(runnable);
		}
	}

	/**
	 * A tracked resource has been deleted. Sub-classes may override.
	 * 
	 * @since 6.0
	 */
	protected void resourceDeleted(final IResource resource) {
		// close the editor if it's not dirty
		if (!isDirty()) {
			closeEditor(false);
		}
	}

	/**
	 * A tracked resource has been moved. Sub-classes may override.
	 * 
	 * @since 6.0
	 */
	protected void resourceMoved(final IResource from, final IResource to) {
		// reopen the editor on the new file
		if (to instanceof IFile) {
			setInput(new FileEditorInput((IFile) to));
		}
	}

	/**
	 * A tracked resource has been changed. Sub-classes may override.
	 * 
	 * @param resource The resource who input has changed
	 * @param delta The delta that describes that change that has occurred to the associated resource
	 * @since 6.0
	 */
	protected void resourceChanged(final IResource resource, final IResourceDelta delta) {
		// reload the model file for the resource which has been changed
		for (final Resource r : getEditingDomain().getResourceSet().getResources()) {
			final IFile file = WorkspaceSynchronizer.getFile(r);
			if (file != null && file.equals(resource)) {
				r.unload();
				try {
					r.load(getEditingDomain().getResourceSet().getLoadOptions());
					updateDocument(r);
				} catch (final IOException e) {
					// PASS
				}
			}
		}
		if (getEditingDomain() != null) {
			getEditingDomain().getCommandStack().flush();
		}
	}

	/**
	 * @since 6.0
	 */
	public ResourceTracker getResourceTracker() {
		return this.resourceTracker;
	}
}

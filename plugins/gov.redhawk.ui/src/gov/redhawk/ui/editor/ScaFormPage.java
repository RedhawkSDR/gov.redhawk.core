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

import gov.redhawk.internal.ui.ScaPluginImages;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

/**
 * The Class ScaFormPage.
 */
public abstract class ScaFormPage extends FormPage implements IMenuListener, IEditingDomainProvider {
	private boolean fNewStyleHeader = true;
	private Control fLastFocusControl;
	private Resource input;
	private final Adapter listener = new AdapterImpl() {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void notifyChanged(final Notification msg) {
			switch (msg.getFeatureID(Resource.class)) {
			case Resource.RESOURCE__IS_LOADED:
				final Resource resource = (Resource) msg.getNotifier();
				PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

					public void run() {
						if (!isDisposed()) {
							refresh(resource);
						}
					}

				});
				break;
			default:
				break;
			}
		}
	};

	private boolean disposed;

	/**
	* Instantiates a new sca form page.
	* 
	* @param editor the editor
	* @param id the id
	* @param title the title
	*/
	public ScaFormPage(final SCAFormEditor editor, final String id, final String title) {
		super(editor, id, title);
		this.fLastFocusControl = null;
	}

	/**
	 * @param resource
	 */
	protected abstract void refresh(final Resource resource);

	/**
	 * Instantiates a new sca form page.
	 * 
	 * @param editor the editor
	 * @param id the id
	 * @param title the title
	 * @param newStyleHeader the new style header
	 */
	public ScaFormPage(final SCAFormEditor editor, final String id, final String title, final boolean newStyleHeader) {
		this(editor, id, title);
		this.fNewStyleHeader = newStyleHeader;
	}

	/**
	 * @return the common editing domain provided by the parent editor
	 */
	public EditingDomain getEditingDomain() {
		return getEditor().getEditingDomain();
	}

	/**
	 * @return the common command stack provided by the parent editor
	 */
	protected BasicCommandStack getCommandStack() {
		return ((BasicCommandStack) getEditingDomain().getCommandStack());
	}

	/**
	 * Executes a command on the editing domain command stack
	 * 
	 * @param command
	 */
	protected void execute(final Command command) {
		getCommandStack().execute(command);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createFormContent(final IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		final FormToolkit toolkit = managedForm.getToolkit();
		// FormColors colors = toolkit.getColors();
		// form.getForm().setSeparatorColor(colors.getColor(FormColors.TB_BORDER));
		if (this.fNewStyleHeader) {
			// createNewStyleHeader(form, colors);
			toolkit.decorateFormHeading(form.getForm());
		}

		final IToolBarManager manager = form.getToolBarManager();
		// Create the group marker so that additional buttons can be added to this group
		// and be to the left of the help button.
		manager.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

		getEditor().contributeToToolbar(manager);

		final String href = getHelpResource();
		if (href != null) {
			final Action helpAction = new Action("help") { //$NON-NLS-1$
				@Override
				public void run() {
					BusyIndicator.showWhile(form.getDisplay(), new Runnable() {
						public void run() {
							PlatformUI.getWorkbench().getHelpSystem().displayHelp(href);
						}
					});
				}
			};
			helpAction.setToolTipText("Help");
			helpAction.setImageDescriptor(ScaPluginImages.DESC_HELP);
			manager.add(helpAction);
		}
		// check to see if our form parts are contributing actions
		final IFormPart[] parts = managedForm.getParts();
		for (int i = 0; i < parts.length; i++) {
			if (parts[i] instanceof IAdaptable) {
				final IAdaptable adapter = (IAdaptable) parts[i];
				final IAction[] actions = (IAction[]) adapter.getAdapter(IAction[].class);
				if (actions != null) {
					for (int j = 0; j < actions.length; j++) {
						form.getToolBarManager().add(actions[j]);
					}
				}
			}
		}
		form.updateToolBar();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SCAFormEditor getEditor() {
		return (SCAFormEditor) super.getEditor();
	}

	/**
	 * Gets the help resource.
	 * 
	 * @return the help resource
	 */
	protected String getHelpResource() {
		return null;
	}

	/**
	 * This creates a context menu for the viewer and adds a listener as well
	 * registering the menu for extension.
	 */
	protected void createContextMenuFor(final StructuredViewer viewer) {
		final MenuManager contextMenu = new MenuManager("#PopUp");
		contextMenu.add(new Separator("additions"));
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(this);
		final Menu menu = contextMenu.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(contextMenu, new UnwrappingSelectionProvider(viewer));

		final int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		final Transfer[] transfers = new Transfer[] {
			LocalTransfer.getInstance()
		};
		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
		viewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(getEditingDomain(), viewer));
	}

	/**
	 * Gets the focus control.
	 * 
	 * @return the focus control
	 */
	protected Control getFocusControl() {
		final IManagedForm form = getManagedForm();
		if (form == null) {
			return null;
		}
		final Control control = form.getForm();
		if (control == null || control.isDisposed()) {
			return null;
		}
		final Display display = control.getDisplay();
		final Control focusControl = display.getFocusControl();
		if (focusControl == null || focusControl.isDisposed()) {
			return null;
		}
		return focusControl;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createPartControl(final Composite parent) {
		super.createPartControl(parent);
		// Dynamically add focus listeners to all the forms children in order
		// to track the last focus control
		final IManagedForm managedForm = getManagedForm();
		if (managedForm != null) {
			addLastFocusListeners(managedForm.getForm());
		}
	}

	/**
	 * Programatically and recursively add focus listeners to the specified
	 * composite and its children that track the last control to have focus
	 * before a page change or the editor lost focus.
	 * 
	 * @param composite the composite
	 */
	public void addLastFocusListeners(final Composite composite) {
		final Control[] controls = composite.getChildren();
		for (int i = 0; i < controls.length; i++) {
			final Control control = controls[i];
			// Add a focus listener if the control is any one of the below types
			// Note that the controls listed below represent all the controls
			// currently in use by all form pages in PDE. In the future,
			// more controls will have to be added.
			// Could not add super class categories of controls because it
			// would include things like tool bars that we don't want to track
			// focus for.
			if ((control instanceof Text) || (control instanceof Button) || (control instanceof Combo) || (control instanceof CCombo)
			        || (control instanceof Tree) || (control instanceof Table) || (control instanceof Spinner) || (control instanceof Link)
			        || (control instanceof List) || (control instanceof TabFolder) || (control instanceof CTabFolder) || (control instanceof Hyperlink)
			        || (control instanceof FilteredTree)) {
				addLastFocusListener(control);
			}
			if (control instanceof Composite) {
				// Recursively add focus listeners to this composites children
				addLastFocusListeners((Composite) control);
			}
		}
	}

	/**
	 * Add a focus listener to the specified control that tracks the last
	 * control to have focus on this page. When focus is gained by this control,
	 * it registers itself as the last control to have focus. The last control
	 * to have focus is stored in order to be restored after a page change or
	 * editor loses focus.
	 * 
	 * @param control the control
	 */
	private void addLastFocusListener(final Control control) {
		control.addFocusListener(new FocusListener() {
			public void focusGained(final FocusEvent e) {
				// NO-OP
			}

			public void focusLost(final FocusEvent e) {
				ScaFormPage.this.fLastFocusControl = control;
			}
		});
	}

	/**
	 * Set the focus on the last control to have focus before a page change or
	 * the editor lost focus.
	 */
	public void updateFormSelection() {
		if ((this.fLastFocusControl != null) && !this.fLastFocusControl.isDisposed()) {
			// Set focus on the control
			this.fLastFocusControl.setFocus();
			// If the control is a Text widget, select its contents
			if (this.fLastFocusControl instanceof Text) {
				final Text text = (Text) this.fLastFocusControl;
				text.setSelection(0, text.getText().length());
			}
		} else {
			// No focus control set
			// Fallback on managed form selection mechanism by setting the
			// focus on this page itself.
			// The managed form will set focus on the first managed part.
			// Most likely this will turn out to be a section.
			// In order for this to work properly, we must override the
			// sections setFocus() method and set focus on a child control
			// (preferrably first) that can practically take focus.
			setFocus();
		}
	}

	/**
	 * Gets the last focus control.
	 * 
	 * @return the last focus control
	 */
	public Control getLastFocusControl() {
		return this.fLastFocusControl;
	}

	/**
	 * Sets the last focus control.
	 * 
	 * @param control the control
	 */
	public void setLastFocusControl(final Control control) {
		this.fLastFocusControl = control;
	}

	/**
	 * Used to align the section client / decriptions of two section headers
	 * horizontally adjacent to each other. The misalignment is caused by one
	 * section header containing toolbar icons and the other not.
	 * 
	 * @param masterSection the master section
	 * @param detailsSection the details section
	 */
	public void alignSectionHeaders(final Section masterSection, final Section detailsSection) {
		detailsSection.descriptionVerticalSpacing += masterSection.getTextClientHeightDifference();
	}

	/*
	 * (non-Javadoc)
	 * @seeorg.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.
	 * IProgressMonitor)
	 */
	@Override
	public final void doSave(final IProgressMonitor monitor) {
		// nothing to do here - this is handled by the parent editor
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public final void doSaveAs() {
		// nothing to do here - this is handled by the parent editor
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return getCommandStack().isSaveNeeded();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public final boolean isSaveAsAllowed() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void menuAboutToShow(final IMenuManager manager) {
		// pass the request to show the context menu on to the parent editor
		getEditor().getActionBarContributor().menuAboutToShow(manager);
		// final IEditorActionBarContributor contributor =
		// if (contributor instanceof EditingDomainActionBarContributor) {
		// ((EditingDomainActionBarContributor)
		// contributor).menuAboutToShow(manager);
		// }
	}

	protected Resource getInput() {
		return this.input;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		if (isDisposed()) {
			return;
		}
		this.disposed = true;
		this.removeResourceListener(this.input);
		super.dispose();
	}

	/**
	 * @since 4.0
	 */
	public boolean isDisposed() {
		return this.disposed;
	}

	/**
	 * This is used by the parent to pass information on the input to be used to
	 * the editor part
	 * 
	 * @param selection
	 */
	public void setInput(final Resource input) {
		removeResourceListener(this.input);
		this.input = input;
		addResourceListener(this.input);
		refresh(this.input);
	}

	protected void addResourceListener(final Resource resource) {
		if (resource != null) {
			resource.eAdapters().add(this.listener);
		}
	}

	protected void removeResourceListener(final Resource resource) {
		if (resource != null) {
			resource.eAdapters().remove(this.listener);
		}
	}

	/**
	 * @since 2.1
	 */
	public void setSelection(final ISelection selection) {
		if (this.isActive()) {
			this.getEditor().setSelection(selection);
		}
	}

	/**
	 * @since 2.1
	 */
	public ISelection getSelection() {
		final ISelectionProvider provider = (ISelectionProvider) this.getAdapter(ISelectionProvider.class);
		if (provider != null) {
			return provider.getSelection();
		}
		return null;
	}
}

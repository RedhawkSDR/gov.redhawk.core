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
package gov.redhawk.ui.sad.editor.presentation;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.action.CreateSiblingAction;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * This is the action bar contributor for the Sad model editor.
 */
public class SadActionBarContributor extends EditingDomainActionBarContributor implements ISelectionChangedListener {
	/**
	 * This keeps track of the active editor.
	 */
	private IEditorPart activeEditorPart;

	/**
	 * This keeps track of the current selection provider.
	 */
	private ISelectionProvider selectionProvider;

	/**
	 * This action opens the Properties view.
	 */
	private final IAction showPropertiesViewAction = new Action("Show &Properties View") {
		@Override
		public void run() {
			try {
				getPage().showView("org.eclipse.ui.views.PropertySheet");
			} catch (final PartInitException exception) {
				StatusManager.getManager().handle(new Status(IStatus.ERROR, SadExplorerPlugin.getPluginID(), "Failed to open Property View.", exception),
				        StatusManager.LOG | StatusManager.SHOW);
			}
		}
	};

	/**
	 * This action refreshes the viewer of the current editor if the editor
	 * implements {@link org.eclipse.emf.common.ui.viewer.IViewerProvider}.
	 */
	private final IAction refreshViewerAction = new Action("&Refresh") {
		@Override
		public boolean isEnabled() {
			return SadActionBarContributor.this.activeEditorPart instanceof IViewerProvider;
		}

		@Override
		public void run() {
			if (SadActionBarContributor.this.activeEditorPart instanceof IViewerProvider) {
				final Viewer viewer = ((IViewerProvider) SadActionBarContributor.this.activeEditorPart).getViewer();
				if (viewer != null) {
					viewer.refresh();
				}
			}
		}
	};

	/**
	 * This will contain one
	 * {@link org.eclipse.emf.edit.ui.action.CreateChildAction} corresponding to
	 * each descriptor generated for the current selection by the item provider.
	 */
	private Collection<IAction> createChildActions;

	/**
	 * This is the menu manager into which menu contribution items should be
	 * added for CreateChild actions.
	 */
	private IMenuManager createChildMenuManager;

	/**
	 * This will contain one
	 * {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} corresponding
	 * to each descriptor generated for the current selection by the item
	 * provider.
	 */
	private Collection<IAction> createSiblingActions;

	/**
	 * This is the menu manager into which menu contribution items should be
	 * added for CreateSibling actions.
	 */
	private IMenuManager createSiblingMenuManager;

	/**
	 * This creates an instance of the contributor.
	 */
	public SadActionBarContributor() {
		super(EditingDomainActionBarContributor.ADDITIONS_LAST_STYLE);
	}

	/**
	 * This adds Separators for editor additions to the tool bar.
	 */
	@Override
	public void contributeToToolBar(final IToolBarManager toolBarManager) {
		toolBarManager.add(new Separator("sad-settings"));
		toolBarManager.add(new Separator("sad-additions"));
	}

	/**
	 * This adds to the menu bar a menu and some separators for editor
	 * additions, as well as the sub-menus for object creation items.
	 */
	@Override
	public void contributeToMenu(final IMenuManager menuManager) {
		super.contributeToMenu(menuManager);
		final IMenuManager submenuManager = new MenuManager("&Sad Editor", "mil.jpeojtrs.sca.sadMenuID");
		menuManager.insertAfter("additions", submenuManager);
		submenuManager.add(new Separator("settings"));
		submenuManager.add(new Separator("actions"));
		submenuManager.add(new Separator("additions"));
		submenuManager.add(new Separator("additions-end"));

		// Force an update because Eclipse hides empty menus now.
		//
		submenuManager.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(final IMenuManager menuManager) {
				menuManager.updateAll(true);
			}
		});

		addGlobalActions(submenuManager);
	}

	/**
	 * When the active editor changes, this remembers the change and registers
	 * with it as a selection provider.
	 */
	@Override
	public void setActiveEditor(final IEditorPart part) {
		super.setActiveEditor(part);
		this.activeEditorPart = part;

		// Switch to the new selection provider.
		//
		if (this.selectionProvider != null) {
			this.selectionProvider.removeSelectionChangedListener(this);
		}
		if (part == null) {
			this.selectionProvider = null;
		} else {
			this.selectionProvider = part.getSite().getSelectionProvider();
			if (this.selectionProvider != null) {
				this.selectionProvider.addSelectionChangedListener(this);

				// Fake a selection changed event to update the menus.
				//
				if (this.selectionProvider.getSelection() != null) {
					selectionChanged(new SelectionChangedEvent(this.selectionProvider, this.selectionProvider.getSelection()));
				}
			}
		}
	}

	/**
	 * This implements
	 * {@link org.eclipse.jface.viewers.ISelectionChangedListener}, handling
	 * {@link org.eclipse.jface.viewers.SelectionChangedEvent}s by querying for
	 * the children and siblings that can be added to the selected object and
	 * updating the menus accordingly.
	 */
	public void selectionChanged(final SelectionChangedEvent event) {
		// Remove any menu items for old selection.
		//
		if (this.createChildMenuManager != null) {
			depopulateManager(this.createChildMenuManager, this.createChildActions);
		}
		if (this.createSiblingMenuManager != null) {
			depopulateManager(this.createSiblingMenuManager, this.createSiblingActions);
		}

		// Query the new selection for appropriate new child/sibling descriptors
		//
		Collection< ? > newChildDescriptors = null;
		Collection< ? > newSiblingDescriptors = null;

		final ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1) {
			final Object object = ((IStructuredSelection) selection).getFirstElement();
			IEditingDomainProvider provider = (IEditingDomainProvider) this.activeEditorPart.getAdapter(IEditingDomainProvider.class);

			final EditingDomain domain = provider.getEditingDomain();
			if (domain != null) {
				newChildDescriptors = domain.getNewChildDescriptors(object, null);
				newSiblingDescriptors = domain.getNewChildDescriptors(null, object);
			}
		}

		// Generate actions for selection; populate and redraw the menus.
		//
		this.createChildActions = generateCreateChildActions(newChildDescriptors, selection);
		this.createSiblingActions = generateCreateSiblingActions(newSiblingDescriptors, selection);

		if (this.createChildMenuManager != null) {
			populateManager(this.createChildMenuManager, this.createChildActions, null);
			this.createChildMenuManager.update(true);
		}
		if (this.createSiblingMenuManager != null) {
			populateManager(this.createSiblingMenuManager, this.createSiblingActions, null);
			this.createSiblingMenuManager.update(true);
		}
	}

	/**
	 * This generates a {@link org.eclipse.emf.edit.ui.action.CreateChildAction}
	 * for each object in <code>descriptors</code>, and returns the collection
	 * of these actions.
	 */
	protected Collection<IAction> generateCreateChildActions(final Collection< ? > descriptors, final ISelection selection) {
		final Collection<IAction> actions = new ArrayList<IAction>();
		if (descriptors != null) {
			for (final Object descriptor : descriptors) {
				actions.add(new CreateChildAction(this.activeEditorPart, selection, descriptor));
			}
		}
		return actions;
	}

	/**
	 * This generates a
	 * {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} for each
	 * object in <code>descriptors</code>, and returns the collection of these
	 * actions.
	 */
	protected Collection<IAction> generateCreateSiblingActions(final Collection< ? > descriptors, final ISelection selection) {
		final Collection<IAction> actions = new ArrayList<IAction>();
		if (descriptors != null) {
			for (final Object descriptor : descriptors) {
				actions.add(new CreateSiblingAction(this.activeEditorPart, selection, descriptor));
			}
		}
		return actions;
	}

	/**
	 * This populates the specified <code>manager</code> with
	 * {@link org.eclipse.jface.action.ActionContributionItem}s based on the
	 * {@link org.eclipse.jface.action.IAction}s contained in the
	 * <code>actions</code> collection, by inserting them before the specified
	 * contribution item <code>contributionID</code>. If
	 * <code>contributionID</code> is <code>null</code>, they are simply added.
	 */
	protected void populateManager(final IContributionManager manager, final Collection< ? extends IAction> actions, final String contributionID) {
		if (actions != null) {
			for (final IAction action : actions) {
				if (contributionID != null) {
					manager.insertBefore(contributionID, action);
				} else {
					manager.add(action);
				}
			}
		}
	}

	/**
	 * This removes from the specified <code>manager</code> all
	 * {@link org.eclipse.jface.action.ActionContributionItem}s based on the
	 * {@link org.eclipse.jface.action.IAction}s contained in the
	 * <code>actions</code> collection.
	 */
	protected void depopulateManager(final IContributionManager manager, final Collection< ? extends IAction> actions) {
		if (actions != null) {
			final IContributionItem[] items = manager.getItems();
			for (int i = 0; i < items.length; i++) {
				// Look into SubContributionItems
				//
				IContributionItem contributionItem = items[i];
				while (contributionItem instanceof SubContributionItem) {
					contributionItem = ((SubContributionItem) contributionItem).getInnerItem();
				}

				// Delete the ActionContributionItems with matching action.
				//
				if (contributionItem instanceof ActionContributionItem) {
					final IAction action = ((ActionContributionItem) contributionItem).getAction();
					if (actions.contains(action)) {
						manager.remove(contributionItem);
					}
				}
			}
		}
	}

	/**
	 * This populates the pop-up menu before it appears.
	 */
	@Override
	public void menuAboutToShow(final IMenuManager menuManager) {
		super.menuAboutToShow(menuManager);
	}

	/**
	 * This inserts global actions before the "additions-end" separator.
	 */
	@Override
	protected void addGlobalActions(final IMenuManager menuManager) {
		menuManager.insertAfter("additions-end", new Separator("ui-actions"));
		menuManager.insertAfter("ui-actions", this.showPropertiesViewAction);

		this.refreshViewerAction.setEnabled(this.refreshViewerAction.isEnabled());
		menuManager.insertAfter("ui-actions", this.refreshViewerAction);

		super.addGlobalActions(menuManager);
	}

	/**
	 * This ensures that a delete action will clean up all references to deleted
	 * objects.
	 */
	@Override
	protected boolean removeAllReferencesOnDelete() {
		return true;
	}

}

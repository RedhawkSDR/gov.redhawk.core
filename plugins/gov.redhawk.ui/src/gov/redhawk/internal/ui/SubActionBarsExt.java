/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.internal.ui;

import java.util.Map;

import org.eclipse.jface.action.ContributionManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.SubCoolBarManager;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IActionBars2;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.SubActionBars2;

/**
 * An extended sub cool bar manager for used by the
 * <code>TopicmapMultipageActionBarContributor</code>.
 */
public class SubActionBarsExt extends SubActionBars2 {

	private IEditorActionBarContributor myContributor;

	private final String myType;

	private IToolBarManager myToolBarManager;

	private ToolBarContributionItem myToolBarContributionItem;

	private PartListener myPartListener;

	/**
	 * Default constructor.
	 * 
	 * @param page
	 * @param parent
	 * @param subContributor
	 * @param type
	 */
	public SubActionBarsExt(final IWorkbenchPage page, final IActionBars2 parent,
			final IEditorActionBarContributor subContributor, final String type) {
		super(parent, parent.getServiceLocator());
		assert type != null;
		this.myType = type;
		assert page != null;
		this.myPartListener = new PartListener(page);
		assert subContributor != null;
		this.myContributor = subContributor;
		this.myContributor.init(this, page);
	}

	/**
	 * @return the action bar contributor
	 */
	public IEditorActionBarContributor getContributor() {
		return this.myContributor;
	}

	/**
	 * Changes the active editor part.
	 * 
	 * @param editorPart
	 */
	public void setEditorPart(final IEditorPart editorPart) {
		this.myContributor.setActiveEditor(editorPart);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.SubActionBars#getToolBarManager()
	 */
	@Override
	public IToolBarManager getToolBarManager() {
		if (this.myToolBarManager == null) {
			final ICoolBarManager parentCoolBarManager = getTopCoolBarManager();
			if (parentCoolBarManager == null) {
				return null;
			}
			final IContributionItem foundItem = parentCoolBarManager.find(this.myType);
			if (foundItem instanceof ToolBarContributionItem
					&& ((ToolBarContributionItem) foundItem).getToolBarManager() != null) {
				this.myToolBarContributionItem = (ToolBarContributionItem) foundItem;
				this.myToolBarManager = this.myToolBarContributionItem.getToolBarManager();
			} else {
				if (!(parentCoolBarManager instanceof ContributionManager)) {
					return null;
				}
				this.myToolBarManager = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
				this.myToolBarContributionItem = new ToolBarContributionItem(this.myToolBarManager, this.myType);
				if (!((ContributionManager) parentCoolBarManager).replaceItem(this.myType,
						this.myToolBarContributionItem)) {
					parentCoolBarManager.add(this.myToolBarContributionItem);
				}
			}
			this.myToolBarContributionItem.setVisible(getActive());
			this.myToolBarManager.markDirty();
		}

		return this.myToolBarManager;
	}

	/**
	 * @return the top-level cool bar manager instance
	 */
	private ICoolBarManager getTopCoolBarManager() {
		ICoolBarManager coolBarManager = getCastedParent().getCoolBarManager();
		while (coolBarManager instanceof SubCoolBarManager
				&& ((SubCoolBarManager) coolBarManager).getParent() instanceof ICoolBarManager) {
			coolBarManager = (ICoolBarManager) ((SubCoolBarManager) coolBarManager).getParent();
		}
		return coolBarManager;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.SubActionBars2#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
		this.myContributor.dispose();
		this.myContributor = null;

		// XXX for some reason I'm not supposed to dispose of this or else it
		// creates NPE in the worbbench
		// if (this.myToolBarContributionItem != null) {
		// this.myToolBarContributionItem.dispose();
		// this.myToolBarContributionItem = null;
		// }

		if (this.myToolBarManager != null) {
			this.myToolBarManager.removeAll();
			this.myToolBarManager = null;
		}

		this.myPartListener.dispose();
		this.myPartListener = null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.SubActionBars2#setActive(boolean)
	 */
	@Override
	protected void setActive(final boolean value) {
		if (getActive() == value) {
			return;
		}
		super.setActive(value);

		final ICoolBarManager parentCoolBarManager = getTopCoolBarManager();
		if (parentCoolBarManager != null) {
			parentCoolBarManager.markDirty();
		}
		if (this.myToolBarManager != null && parentCoolBarManager != null) {
			final IContributionItem[] items = this.myToolBarManager.getItems();
			for (int i = 0; i < items.length; i++) {
				final IContributionItem item = items[i];
				item.setVisible(value);
			}
			this.myToolBarManager.markDirty();
			this.myToolBarManager.update(false);
		}

		if (value) {
			final Map< ? , ? > globals = getGlobalActionHandlers();
			if (globals != null) {
				for (final Map.Entry< ? , ? > nextEntry : globals.entrySet()) {
					final Object key = nextEntry.getKey();
					final Object entryValue = nextEntry.getValue();
					if (key instanceof String && entryValue instanceof IAction) {
						getParent().setGlobalActionHandler((String) key, (IAction) entryValue);
					}
				}
			}
		} else {
			getParent().clearGlobalActionHandlers();
		}
		getParent().updateActionBars();
	}

	/**
	 * Inner class to be able to be notified when parts are activated.
	 */
	private class PartListener implements IPartListener {

		private IWorkbenchPage myPage;

		/**
		 * Default constructor.
		 * 
		 * @param page
		 */
		public PartListener(final IWorkbenchPage page) {
			this.myPage = page;
			this.myPage.addPartListener(this);
		}

		/**
		 * Default cleanup method.
		 */
		public void dispose() {
			this.myPage.removePartListener(this);
			this.myPage = null;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.eclipse.ui.IPartListener#partActivated(org.eclipse.ui.IWorkbenchPart
		 * )
		 */
		public void partActivated(final IWorkbenchPart part) {
			if (part instanceof IEditorPart) {
				final IEditorPart editorPart = (IEditorPart) part;
				if (editorPart.getEditorSite().getActionBars() != getParent() && getActive()) {
					deactivate();
					updateActionBars();
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * @seeorg.eclipse.ui.IPartListener#partBroughtToTop(org.eclipse.ui.
		 * IWorkbenchPart)
		 */
		public void partBroughtToTop(final IWorkbenchPart part) {
			// not required
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.eclipse.ui.IPartListener#partClosed(org.eclipse.ui.IWorkbenchPart
		 * )
		 */
		public void partClosed(final IWorkbenchPart part) {
			// not required
		}

		/*
		 * (non-Javadoc)
		 * @seeorg.eclipse.ui.IPartListener#partDeactivated(org.eclipse.ui.
		 * IWorkbenchPart)
		 */
		public void partDeactivated(final IWorkbenchPart part) {
			// not required
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.eclipse.ui.IPartListener#partOpened(org.eclipse.ui.IWorkbenchPart
		 * )
		 */
		public void partOpened(final IWorkbenchPart part) {
			// not required
		}

	}

}

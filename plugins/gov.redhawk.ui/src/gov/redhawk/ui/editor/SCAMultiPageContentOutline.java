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
package gov.redhawk.ui.editor;

import gov.redhawk.ui.RedhawkUiActivator;

import java.util.ArrayList;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.IPageBookViewPage;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

// TODO: Auto-generated Javadoc
/**
 * The Class SCAMultiPageContentOutline.
 */
public class SCAMultiPageContentOutline extends Page implements IContentOutlinePage, ISelectionProvider,
        ISelectionChangedListener {
	private PageBook pagebook;
	private ISelection selection;
	private ArrayList<ISelectionChangedListener> listeners;
	private IContentOutlinePage currentPage;
	private IContentOutlinePage emptyPage;
	private IActionBars actionBars;

	/**
	 * Instantiates a new sCA multi page content outline.
	 * 
	 * @param editor the editor
	 */
	public SCAMultiPageContentOutline(final SCAFormEditor editor) {
		this.listeners = new ArrayList<ISelectionChangedListener>();

	}

	/**
	 * Adds the focus listener.
	 * 
	 * @param listener the listener
	 */
	public void addFocusListener(final FocusListener listener) {
	}

	/**
	 * {@inheritDoc}
	 */
	public void addSelectionChangedListener(final ISelectionChangedListener listener) {
		this.listeners.add(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(final Composite parent) {
		this.pagebook = new PageBook(parent, SWT.NONE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		if (this.pagebook != null && !this.pagebook.isDisposed()) {
			this.pagebook.dispose();
		}
		if (this.emptyPage != null) {
			this.emptyPage.dispose();
			this.emptyPage = null;
		}
		this.pagebook = null;
		this.listeners = null;
	}

	/**
	 * Checks if is disposed.
	 * 
	 * @return true, if is disposed
	 */
	public boolean isDisposed() {
		return this.listeners == null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Control getControl() {
		return this.pagebook;
	}

	/**
	 * Gets the pagebook.
	 * 
	 * @return the pagebook
	 */
	public PageBook getPagebook() {
		return this.pagebook;
	}

	/**
	 * {@inheritDoc}
	 */
	public ISelection getSelection() {
		return this.selection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void makeContributions(final IMenuManager menuManager, final IToolBarManager toolBarManager,
	        final IStatusLineManager statusLineManager) {
	}

	/**
	 * Removes the focus listener.
	 * 
	 * @param listener the listener
	 */
	public void removeFocusListener(final FocusListener listener) {
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
		this.listeners.remove(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	public void selectionChanged(final SelectionChangedEvent event) {
		setSelection(event.getSelection());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setActionBars(final IActionBars actionBars) {
		super.setActionBars(actionBars);
		this.actionBars = actionBars;
		if (this.currentPage != null) {
			setPageActive(this.currentPage);
		}

	}

	/**
	 * Gets the action bars.
	 * 
	 * @return the action bars
	 */
	public IActionBars getActionBars() {
		return this.actionBars;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFocus() {
		if (this.currentPage != null) {
			this.currentPage.setFocus();
		}
	}

	/**
	 * Gets the empty page.
	 * 
	 * @return the empty page
	 */
	private IContentOutlinePage getEmptyPage() {
		if (this.emptyPage == null) {
			this.emptyPage = new EmptyOutlinePage();
		}
		return this.emptyPage;
	}

	/**
	 * Sets the page active.
	 * 
	 * @param page the new page active
	 */
	public void setPageActive(IContentOutlinePage page) {
		if (page == null) {
			page = getEmptyPage();
		}

		if (this.currentPage != null) {
			this.currentPage.removeSelectionChangedListener(this);
		}

		page.addSelectionChangedListener(this);
		this.currentPage = page;
		if (this.pagebook == null) {
			// still not being made
			return;
		}
		Control control = page.getControl();
		if (control == null || control.isDisposed()) {
			// first time
			if (page instanceof IPageBookViewPage) {
				initPage((IPageBookViewPage) page);
			}
			page.createControl(this.pagebook);
			page.setActionBars(getActionBars());
			control = page.getControl();
		}
		this.pagebook.showPage(control);
		this.currentPage = page;
		this.getActionBars().updateActionBars();
	}

	/**
	 * Initializes the given page with a page site.
	 * <p>
	 * Subclasses should call this method after the page is created but before
	 * creating its controls.
	 * </p>
	 * <p>
	 * Subclasses may override
	 * </p>
	 * 
	 * @param page The page to initialize
	 */
	protected void initPage(final IPageBookViewPage page) {
		try {
			page.init(getSite());
		} catch (final PartInitException e) {
			RedhawkUiActivator.log(getClass(), "initPage", e); //$NON-NLS-1$
		}
	}

	/**
	 * Set the selection.
	 * 
	 * @param selection the selection
	 */
	public void setSelection(final ISelection selection) {
		this.selection = selection;
		if (this.listeners == null) {
			return;
		}
		final SelectionChangedEvent e = new SelectionChangedEvent(this, selection);
		for (int i = 0; i < this.listeners.size(); i++) {
			(this.listeners.get(i)).selectionChanged(e);
		}
	}
}

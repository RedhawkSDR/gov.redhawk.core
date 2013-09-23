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
/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package gov.redhawk.ui.editor;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.editor.IFormPage;

/**
 * The Class FormOutlinePage.
 */
public abstract class FormOutlinePage extends ScaOutlinePage {

	/** The tree viewer. */
	private TreeViewer treeViewer;

	/** The editor selection. */
	private boolean editorSelection;

	/** The outline selection. */
	private boolean outlineSelection;

	/** The adapter factory. */
	private ComposedAdapterFactory adapterFactory;

	private ViewerComparator viewerComparator;
	private boolean sorted;
	private ILabelProvider labelProvider;

	private class FormOutlineAdapterFactoryContentProvider extends AdapterFactoryContentProvider {

		public FormOutlineAdapterFactoryContentProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
		}

		@Override
		public Object[] getElements(Object obj) {
			List<Object> myList = getOutlineItems();
			if (myList != null) {
				return myList.toArray();
			}
			return new Object[0];
		}

		@Override
		public Object[] getChildren(Object object) {
			return FormOutlinePage.this.getChildren(object) ? super.getChildren(object) : new Object[0]; // SUPPRESS CHECKSTYLE AvoidInLine
		}
	}

	/**
	 * Instantiates a new form outline page.
	 * 
	 * @param editor the editor
	 */
	public FormOutlinePage(final SCAFormEditor editor) {
		super(editor);
	}

	/**
	 * Creates the content provider.
	 * 
	 * @return the i tree content provider
	 */
	public ITreeContentProvider createContentProvider() {
		return new FormOutlineAdapterFactoryContentProvider(getAdapterFactory());
	}

	/**
	 * Gets the adapter factory.
	 * 
	 * @return the adapter factory
	 * @since 2.0
	 */
	public AdapterFactory getAdapterFactory() {
		if (this.adapterFactory == null) {
			// Create an adapter factory that yields item providers.
			//
			this.adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

			this.adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
			addItemProviders(this.adapterFactory);
			this.adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		}
		return this.adapterFactory;
	}

	/**
	 * Adds the item providers.
	 * 
	 * @param itemAdapterFactory the adapter factory
	 */
	protected abstract void addItemProviders(ComposedAdapterFactory itemAdapterFactory);

	/**
	 * Creates the outline sorter.
	 * 
	 * @return the viewer comparator
	 */
	public ViewerComparator createOutlineSorter() {
		return new ViewerComparator();
	}

	/**
	 * Creates the control.
	 * 
	 * @param parent the parent
	 */
	@Override
	public void createControl(final Composite parent) {
		final Tree widget = new Tree(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		this.treeViewer = new TreeViewer(widget);
		this.treeViewer.addSelectionChangedListener(this);
		this.treeViewer.setContentProvider(createContentProvider());
		this.treeViewer.setLabelProvider(createLabelProvider());
		this.viewerComparator = createOutlineSorter();
		if (this.sorted) {
			this.treeViewer.setComparator(this.viewerComparator);
		} else {
			this.treeViewer.setComparator(null);
		}
		this.treeViewer.setAutoExpandLevel(AbstractTreeViewer.ALL_LEVELS);
		this.treeViewer.setUseHashlookup(true);
		this.treeViewer.setInput(this.fEditor.getEditingDomain().getResourceSet());

	}

	/**
	 * Creates the label provider.
	 * 
	 * @return the i label provider
	 */
	public ILabelProvider createLabelProvider() {
		if (this.labelProvider == null) {
			this.labelProvider = new AdapterFactoryLabelProvider(this.getAdapterFactory());
		}
		return this.labelProvider;
	}

	/**
	 * Dispose.
	 */
	@Override
	public void dispose() {
		if (this.adapterFactory != null) {
			this.adapterFactory.dispose();
		}
		super.dispose();
	}

	/**
	 * Gets the control.
	 * 
	 * @return the control
	 */
	@Override
	public Control getControl() {
		if (this.treeViewer != null) {
			return this.treeViewer.getControl();
		} else {
			return null;
		}
	}

	/**
	 * Refresh.
	 */
	public void refresh() {
		final Control control = getControl();
		if (control == null || control.isDisposed()) {
			return;
		}
		control.getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				if (!FormOutlinePage.this.treeViewer.getControl().isDisposed()) {
					FormOutlinePage.this.treeViewer.refresh();
					FormOutlinePage.this.treeViewer.expandAll();

				}
			}
		});
	}

	/**
	 * Gets the parent page id.
	 * 
	 * @param item the item
	 * @return the parent page id
	 */
	protected String getParentPageId(final Object item) {
		if (item instanceof IFormPage) {
			return ((IFormPage) item).getId();
		}
		return null;
	}

	/**
	 * Gets the children.
	 * 
	 * @param parent the parent
	 * @return the children
	 * @since 2.0
	 */
	protected boolean getChildren(final Object parent) {
		return false;
	}

	/**
	 * Selection changed.
	 * 
	 * @param item the item
	 */
	public void selectionChanged(final Object item) {
		final IFormPage page = this.fEditor.getActivePageInstance();
		final String id = getParentPageId(item);
		IFormPage newPage = null;
		if (id != null && (page == null || !page.getId().equals(id))) {
			newPage = this.fEditor.setActivePage(id);
		}
		final IFormPage revealPage = (newPage != null) ? newPage : page; // SUPPRESS CHECKSTYLE AvoidInLine
		if (revealPage != null && !(item instanceof IFormPage)) {
			revealPage.selectReveal(item);
		}
	}

	/**
	 * Selection changed.
	 * 
	 * @param event the event
	 */
	@Override
	public void selectionChanged(final SelectionChangedEvent event) {
		if (this.editorSelection) {
			return;
		}
		this.outlineSelection = true;
		try {
			final ISelection selection = event.getSelection();
			if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
				final IStructuredSelection ssel = (IStructuredSelection) selection;
				final Object item = ssel.getFirstElement();
				selectionChanged(item);
			}
			fireSelectionChanged(selection);
		} finally {
			this.outlineSelection = false;
		}
	}

	/**
	 * Sets the focus.
	 */
	@Override
	public void setFocus() {
		if (this.treeViewer != null) {
			this.treeViewer.getTree().setFocus();
		}
	}

	/**
	 * Gets the selection.
	 * 
	 * @return the selection
	 */
	@Override
	public ISelection getSelection() {
		if (this.treeViewer == null) {
			return StructuredSelection.EMPTY;
		}
		return this.treeViewer.getSelection();
	}

	/**
	 * Sort.
	 * 
	 * @param sorting the sorting
	 */
	public void sort(final boolean sorting) {
		this.sorted = sorting;
		if (this.treeViewer != null) {
			if (sorting) {
				this.treeViewer.setComparator(this.viewerComparator);
			} else {
				this.treeViewer.setComparator(null);
			}
		}
	}

	/*
	 * (non-Javadoc) Method declared on ISelectionProvider.
	 */
	/**
	 * Sets the selection.
	 * 
	 * @param selection the new selection
	 */
	@Override
	public void setSelection(final ISelection selection) {
		if (this.outlineSelection) {
			return;
		}
		this.editorSelection = true;
		try {
			if (this.treeViewer == null) {
				return;
			}
			this.treeViewer.setSelection(selection);
		} finally {
			this.editorSelection = false;
		}
	}

	/**
	 * Gets the tree viewer.
	 * 
	 * @return the tree viewer
	 */
	@Override
	protected TreeViewer getTreeViewer() {
		return this.treeViewer;
	}
	
	/**
     * @since 2.0
     */
	public void setLabelProvider(ILabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}
	
	/**
     * @since 2.0
     */
	public List<Object>  getOutlineItems() {
		return this.fEditor.getOutlineItems();
	}
}

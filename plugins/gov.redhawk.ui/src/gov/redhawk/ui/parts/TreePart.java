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
package gov.redhawk.ui.parts;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

// TODO: Auto-generated Javadoc
/**
 * The Class TreePart.
 */
public class TreePart extends StructuredViewerPart {

	/**
	 * Constructor for TreePart.
	 * 
	 * @param buttonLabels the button labels
	 */
	public TreePart(final String[] buttonLabels) {
		super(buttonLabels);
	}

	/**
	 * Creates the tree viewer.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * @return the tree viewer
	 */
	protected TreeViewer createTreeViewer(final Composite parent, final int style) {
		return new TreeViewer(parent, style);
	}

	/*
	 * @see StructuredViewerPart#createStructuredViewer(Composite,
	 * FormWidgetFactory)
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected StructuredViewer createStructuredViewer(final Composite parent, int style, final FormToolkit toolkit) {
		style |= SWT.H_SCROLL | SWT.V_SCROLL;
		if (toolkit == null) {
			style |= SWT.BORDER;
		} else {
			style |= toolkit.getBorderStyle();
		}
		final TreeViewer treeViewer = createTreeViewer(parent, style);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(final SelectionChangedEvent e) {
				TreePart.this.selectionChanged((IStructuredSelection) e.getSelection());
			}
		});
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(final DoubleClickEvent e) {
				TreePart.this.handleDoubleClick((IStructuredSelection) e.getSelection());
			}
		});
		return treeViewer;
	}

	/**
	 * Gets the tree viewer.
	 * 
	 * @return the tree viewer
	 */
	public TreeViewer getTreeViewer() {
		return (TreeViewer) getViewer();
	}

	/*
	 * @see SharedPartWithButtons#buttonSelected(int)
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void buttonSelected(final Button button, final int index) {
	}

	/**
	 * Selection changed.
	 * 
	 * @param selection the selection
	 */
	protected void selectionChanged(final IStructuredSelection selection) {
	}

	/**
	 * Handle double click.
	 * 
	 * @param selection the selection
	 */
	protected void handleDoubleClick(final IStructuredSelection selection) {
	}
}

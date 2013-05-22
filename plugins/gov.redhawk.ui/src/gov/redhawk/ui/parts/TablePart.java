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
package gov.redhawk.ui.parts;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The Class TablePart.
 */
public class TablePart extends StructuredViewerPart {

	/**
	 * Constructor for TablePart.
	 * 
	 * @param buttonLabels the button labels
	 */
	public TablePart(final String[] buttonLabels) {
		super(buttonLabels);
	}

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
		final TableViewer tableViewer = new TableViewer(parent, style);
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(final SelectionChangedEvent e) {
				TablePart.this.selectionChanged((IStructuredSelection) e.getSelection());
			}
		});
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(final DoubleClickEvent e) {
				TablePart.this.handleDoubleClick((IStructuredSelection) e.getSelection());
			}
		});
		return tableViewer;
	}

	/**
	 * Gets the table viewer.
	 * 
	 * @return the table viewer
	 */
	public TableViewer getTableViewer() {
		return (TableViewer) getViewer();
	}

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

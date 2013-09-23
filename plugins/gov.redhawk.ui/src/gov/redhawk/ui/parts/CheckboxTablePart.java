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

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The Class CheckboxTablePart.
 */
public class CheckboxTablePart extends StructuredViewerPart {

	/**
	 * Instantiates a new checkbox table part.
	 * 
	 * @param buttonLabels the button labels
	 */
	public CheckboxTablePart(final String[] buttonLabels) {
		super(buttonLabels);
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
		final CheckboxTableViewer tableViewer = CheckboxTableViewer.newCheckList(parent, style);
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent e) {
				CheckboxTablePart.this.selectionChanged((IStructuredSelection) e.getSelection());
			}
		});
		tableViewer.addCheckStateListener(new ICheckStateListener() {
			@Override
			public void checkStateChanged(final CheckStateChangedEvent event) {
				elementChecked(event.getElement(), event.getChecked());
			}
		});
		return tableViewer;
	}

	/**
	 * Gets the table viewer.
	 * 
	 * @return the table viewer
	 */
	public CheckboxTableViewer getTableViewer() {
		return (CheckboxTableViewer) getViewer();
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
	 * Element checked.
	 * 
	 * @param element the element
	 * @param checked the checked
	 */
	protected void elementChecked(final Object element, final boolean checked) {
	}

	/**
	 * Selection changed.
	 * 
	 * @param selection the selection
	 */
	protected void selectionChanged(final IStructuredSelection selection) {
	}
}

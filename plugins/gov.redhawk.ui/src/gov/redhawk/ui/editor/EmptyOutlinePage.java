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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * The Class EmptyOutlinePage.
 */
public class EmptyOutlinePage implements IContentOutlinePage {
	private Composite control;

	/**
	 * Instantiates a new empty outline page.
	 */
	public EmptyOutlinePage() {
	}

	/**
	 * Creates the control.
	 * 
	 * @param parent the parent
	 */
	@Override
	public void createControl(final Composite parent) {
		this.control = new Composite(parent, SWT.NULL);
	}

	/**
	 * Dispose.
	 */
	@Override
	public void dispose() {
	}

	/**
	 * Gets the control.
	 * 
	 * @return the control
	 */
	@Override
	public Control getControl() {
		return this.control;
	}

	/**
	 * Sets the action bars.
	 * 
	 * @param actionBars the new action bars
	 */
	@Override
	public void setActionBars(final IActionBars actionBars) {
	}

	/**
	 * Sets the focus.
	 */
	@Override
	public void setFocus() {
	}

	/**
	 * Adds the selection changed listener.
	 * 
	 * @param listener the listener
	 */
	@Override
	public void addSelectionChangedListener(final ISelectionChangedListener listener) {
	}

	/**
	 * Gets the selection.
	 * 
	 * @return the selection
	 */
	@Override
	public ISelection getSelection() {
		return new ISelection() {
			@Override
			public boolean isEmpty() {
				return true;
			}
		};
	}

	/**
	 * Removes the selection changed listener.
	 * 
	 * @param listener the listener
	 */
	@Override
	public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
	}

	/**
	 * Sets the selection.
	 * 
	 * @param selection the new selection
	 */
	@Override
	public void setSelection(final ISelection selection) {
	}
}

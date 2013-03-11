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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

// TODO: Auto-generated Javadoc
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
	public void createControl(final Composite parent) {
		this.control = new Composite(parent, SWT.NULL);
	}

	/**
	 * Dispose.
	 */
	public void dispose() {
	}

	/**
	 * Gets the control.
	 * 
	 * @return the control
	 */
	public Control getControl() {
		return this.control;
	}

	/**
	 * Sets the action bars.
	 * 
	 * @param actionBars the new action bars
	 */
	public void setActionBars(final IActionBars actionBars) {
	}

	/**
	 * Sets the focus.
	 */
	public void setFocus() {
	}

	/**
	 * Adds the selection changed listener.
	 * 
	 * @param listener the listener
	 */
	public void addSelectionChangedListener(final ISelectionChangedListener listener) {
	}

	/**
	 * Gets the selection.
	 * 
	 * @return the selection
	 */
	public ISelection getSelection() {
		return new ISelection() {
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
	public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
	}

	/**
	 * Sets the selection.
	 * 
	 * @param selection the new selection
	 */
	public void setSelection(final ISelection selection) {
	}
}

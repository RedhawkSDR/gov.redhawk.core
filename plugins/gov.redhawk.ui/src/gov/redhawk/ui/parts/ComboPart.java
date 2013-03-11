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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The Class ComboPart.
 */
public class ComboPart {
	// CHECKSTYLE:OFF
	/** The combo. */
	protected Control combo;

	// CHECKSTYLE:ON

	/**
	 * Instantiates a new combo part.
	 */
	public ComboPart() {
	}

	/**
	 * Adds the selection listener.
	 * 
	 * @param listener the listener
	 */
	public void addSelectionListener(final SelectionListener listener) {
		if (this.combo instanceof Combo) {
			((Combo) this.combo).addSelectionListener(listener);
		} else {
			((CCombo) this.combo).addSelectionListener(listener);
		}
	}

	/**
	 * Index of.
	 * 
	 * @param item the item
	 * @return the int
	 */
	public int indexOf(final String item) {
		if (this.combo instanceof Combo) {
			return ((Combo) this.combo).indexOf(item);
		}

		return ((CCombo) this.combo).indexOf(item);
	}

	/**
	 * Adds the modify listener.
	 * 
	 * @param listener the listener
	 */
	public void addModifyListener(final ModifyListener listener) {
		if (this.combo instanceof Combo) {
			((Combo) this.combo).addModifyListener(listener);
		} else {
			((CCombo) this.combo).addModifyListener(listener);
		}
	}

	/**
	 * Creates the control.
	 * 
	 * @param parent the parent
	 * @param toolkit the toolkit
	 * @param style the style
	 */
	public void createControl(final Composite parent, final FormToolkit toolkit, final int style) {
		if (toolkit.getBorderStyle() == SWT.BORDER) {
			this.combo = new Combo(parent, style | SWT.BORDER);
		} else {
			this.combo = new CCombo(parent, style | SWT.FLAT);
		}
		toolkit.adapt(this.combo, true, false);
	}

	/**
	 * Gets the control.
	 * 
	 * @return the control
	 */
	public Control getControl() {
		return this.combo;
	}

	/**
	 * Gets the selection index.
	 * 
	 * @return the selection index
	 */
	public int getSelectionIndex() {
		if (this.combo instanceof Combo) {
			return ((Combo) this.combo).getSelectionIndex();
		}
		return ((CCombo) this.combo).getSelectionIndex();
	}

	/**
	 * Adds the.
	 * 
	 * @param item the item
	 * @param index the index
	 */
	public void add(final String item, final int index) {
		if (this.combo instanceof Combo) {
			((Combo) this.combo).add(item, index);
		} else {
			((CCombo) this.combo).add(item, index);
		}
	}

	/**
	 * Adds the.
	 * 
	 * @param item the item
	 */
	public void add(final String item) {
		if (this.combo instanceof Combo) {
			((Combo) this.combo).add(item);
		} else {
			((CCombo) this.combo).add(item);
		}
	}

	/**
	 * Removes the.
	 * 
	 * @param index the index
	 */
	public void remove(final int index) {
		// Ensure the index is valid
		if ((index < 0) || (index >= getItemCount())) {
			return;
		}
		// Remove the item from the specified index
		if (this.combo instanceof Combo) {
			((Combo) this.combo).remove(index);
		} else {
			((CCombo) this.combo).remove(index);
		}
	}

	/**
	 * Select.
	 * 
	 * @param index the index
	 */
	public void select(final int index) {
		if (this.combo instanceof Combo) {
			((Combo) this.combo).select(index);
		} else {
			((CCombo) this.combo).select(index);
		}
	}

	/**
	 * Gets the selection.
	 * 
	 * @return the selection
	 */
	public String getSelection() {
		if (this.combo instanceof Combo) {
			return ((Combo) this.combo).getText().trim();
		}
		return ((CCombo) this.combo).getText().trim();
	}

	/**
	 * Sets the text.
	 * 
	 * @param text the new text
	 */
	public void setText(final String text) {
		if (this.combo instanceof Combo) {
			((Combo) this.combo).setText(text);
		} else {
			((CCombo) this.combo).setText(text);
		}
	}

	/**
	 * Sets the items.
	 * 
	 * @param items the new items
	 */
	public void setItems(final String[] items) {
		if (this.combo instanceof Combo) {
			((Combo) this.combo).setItems(items);
		} else {
			((CCombo) this.combo).setItems(items);
		}
	}

	/**
	 * Sets the enabled.
	 * 
	 * @param enabled the new enabled
	 */
	public void setEnabled(final boolean enabled) {
		if (this.combo instanceof Combo) {
			((Combo) this.combo).setEnabled(enabled);
		} else {
			((CCombo) this.combo).setEnabled(enabled);
		}
	}

	/**
	 * Gets the item count.
	 * 
	 * @return the item count
	 */
	public int getItemCount() {
		if (this.combo instanceof Combo) {
			return ((Combo) this.combo).getItemCount();
		}
		return ((CCombo) this.combo).getItemCount();
	}

	/**
	 * Gets the items.
	 * 
	 * @return the items
	 */
	public String[] getItems() {
		if (this.combo instanceof Combo) {
			return ((Combo) this.combo).getItems();
		}
		return ((CCombo) this.combo).getItems();
	}
}

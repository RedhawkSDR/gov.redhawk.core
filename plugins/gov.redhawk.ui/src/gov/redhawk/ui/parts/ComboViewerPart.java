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
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package gov.redhawk.ui.parts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The Class ComboViewerPart.
 */
public class ComboViewerPart {

	/** The magic object used to deal with null content. */
	public static final Object NULL_OBJECT = new Object();

	private Control fCombo;
	private ComboViewer fComboViewer;
	private List<Object> fObjects;

	/**
	 * Instantiates a new combo viewer part.
	 */
	public ComboViewerPart() {
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
			this.fCombo = new Combo(parent, style | SWT.BORDER);
			this.fComboViewer = new ComboViewer((Combo) this.fCombo);
		} else {
			this.fCombo = new CCombo(parent, style | SWT.FLAT);
			this.fComboViewer = new ComboViewer((CCombo) this.fCombo);
		}

		this.fObjects = new ArrayList<Object>();
		this.fComboViewer.setLabelProvider(new LabelProvider());
		this.fComboViewer.setContentProvider(new ArrayContentProvider());
		this.fComboViewer.setInput(this.fObjects);
	}

	/**
	 * Gets the control.
	 * 
	 * @return the control
	 */
	public Control getControl() {
		return this.fCombo;
	}

	/**
	 * Sets the enabled.
	 * 
	 * @param enabled the new enabled
	 */
	public void setEnabled(final boolean enabled) {
		this.fCombo.setEnabled(enabled);
	}

	/**
	 * Refresh.
	 */
	public void refresh() {
		if ((this.fComboViewer == null) || ((this.fComboViewer.getControl() != null) && this.fComboViewer.getControl().isDisposed())) {
			return;
		}
		this.fComboViewer.refresh();
	}

	/**
	 * Adds the item.
	 * 
	 * @param item the item
	 */
	public void addItem(final Object item) {
		this.fObjects.add((item == null) ? ComboViewerPart.NULL_OBJECT : item); // SUPPRESS CHECKSTYLE AvoidInLine
		refresh();
	}

	/**
	 * Adds the item.
	 * 
	 * @param item the item
	 * @param index the index
	 */
	public void addItem(final Object item, final int index) {
		this.fObjects.add(index, (item == null) ? ComboViewerPart.NULL_OBJECT : item); // SUPPRESS CHECKSTYLE AvoidInLine
		refresh();
	}

	/**
	 * Gets the items.
	 * 
	 * @return the items
	 */
	public Collection< ? > getItems() {
		return this.fObjects;
	}

	/**
	 * Sets the items.
	 * 
	 * @param items the new items
	 */
	public void setItems(final Object[] items) {
		this.fObjects.clear();
		for (int i = 0; i < items.length; i++) {
			this.fObjects.add((items[i] == null) ? ComboViewerPart.NULL_OBJECT : items[i]); // SUPPRESS CHECKSTYLE AvoidInLine
		}
		refresh();
	}

	/**
	 * Sets the items.
	 * 
	 * @param items the new items
	 */
	public void setItems(final Collection< ? > items) {
		this.fObjects.clear();
		final Iterator< ? > it = items.iterator();
		while (it.hasNext()) {
			final Object o = it.next();
			this.fObjects.add((o == null) ? ComboViewerPart.NULL_OBJECT : o); // SUPPRESS CHECKSTYLE AvoidInLine
		}
		refresh();
	}

	/**
	 * Select.
	 * 
	 * @param item the item
	 */
	public void select(final Object item) {
		if (item != null) {
			this.fComboViewer.setSelection(new StructuredSelection(item));
		} else {
			this.fComboViewer.setSelection(null);
		}
	}

	/**
	 * Select.
	 * 
	 * @param index the index
	 */
	public void select(final int index) {
		if (index < this.fObjects.size()) {
			select(this.fObjects.get(index));
		}
	}

	/**
	 * Sets the label provider.
	 * 
	 * @param labelProvider the new label provider
	 */
	public void setLabelProvider(final IBaseLabelProvider labelProvider) {
		this.fComboViewer.setLabelProvider(labelProvider);
	}

	/**
	 * Sets the comparator.
	 * 
	 * @param comparator the new comparator
	 */
	public void setComparator(final ViewerComparator comparator) {
		this.fComboViewer.setComparator(comparator);
	}

	/**
	 * Adds the selection changed listener.
	 * 
	 * @param listener the listener
	 */
	public void addSelectionChangedListener(final ISelectionChangedListener listener) {
		this.fComboViewer.addSelectionChangedListener(listener);
	}

	/**
	 * Gets the selection.
	 * 
	 * @return the selection
	 */
	public Object getSelection() {
		return ((IStructuredSelection) this.fComboViewer.getSelection()).getFirstElement();
	}

	/**
	 * 
	 */
	public ComboViewer getViewer() {
		return this.fComboViewer;

	}
}

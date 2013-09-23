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
package gov.redhawk.diagram.ui.tools.internal;

import java.util.Arrays;

import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Extends the TextCellEditor to provide a convenient method that will permitting the set of the value and doing the
 * necessary process to update the state of the cell editor and also notify all listeners listening on changes in the
 * cell editor value.
 * 
 */
public class ComboCellEditorEx extends ComboBoxCellEditor {

	private Object originalValue;
	private boolean deactivationLock = false;

	/**
	 */
	public ComboCellEditorEx() {
		// empty
	}

	/**
	 * @param parent the parent control
	 * @param items
	 */
	public ComboCellEditorEx(final Composite parent, final String[] items) {
		super(parent, items);
	}

	/**
	 * Creates a new text string cell editor parented under the given control. The cell editor value is the string
	 * itself, which is initially the empty string. Initially, the cell editor has no cell validator.
	 * 
	 * @param parent the parent control
	 * @param style the style bits
	 * @param items
	 */
	public ComboCellEditorEx(final Composite parent, final String[] items, final int style) {
		super(parent, items, style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.CellEditor#doSetValue(java.lang.Object)
	 */
	@Override
	protected void doSetValue(final Object value) {
		if (this.originalValue == null) {
			this.originalValue = value;
		}
		final int index = Arrays.asList(getItems()).indexOf(value);
		super.doSetValue(index);
	}

	@Override
	protected Object doGetValue() {
		// TODO Auto-generated method stub
		//        return super.doGetValue();
		return getItems()[(Integer) super.doGetValue()];
	}

	/**
	 * @return boolean value specifying whether or not the value has been changed
	 */
	public boolean hasValueChanged() {
		if (getValue() == null) {
			return this.originalValue != null;
		}
		return !getValue().equals(this.originalValue);
	}

	/*
	 * Runs super deactivate unless it has been locked and otherwise unlocks deactivation
	 * 
	 * @see org.eclipse.jface.viewers.CellEditor#deactivate()
	 */
	@Override
	public void deactivate() {
		if (!isDeactivationLocked()) {
			super.deactivate();
		}
		setDeactivationLock(false);
	}

	/**
	 * Returns true if deactivation has been locked
	 * 
	 * @return
	 */
	public boolean isDeactivationLocked() {
		return this.deactivationLock;
	}

	@Override
	protected Control createControl(final Composite parent) {
		// TODO Auto-generated method stub
		final CCombo retVal = (CCombo) super.createControl(parent);
		retVal.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(final ModifyEvent e) {
				valueChanged(isValueValid(), isCorrect(retVal.getText()));
			}
		});
		return retVal;
	}

	/**
	 * Sets deactivation lock so that the cell editor does not perform deactivate
	 * 
	 * @param deactivationLock
	 */
	public void setDeactivationLock(final boolean deactivationLock) {
		this.deactivationLock = deactivationLock;
	}

}

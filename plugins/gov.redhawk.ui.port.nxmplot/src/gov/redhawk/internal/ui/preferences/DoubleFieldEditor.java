/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.internal.ui.preferences;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * 
 */
public class DoubleFieldEditor extends StringFieldEditor {
	/** a 64-bit double has 15-17 decimal digit precision 
	 *  + 7 (1 for decimal point, 1 for negative sign, 5 for E-xxx)
	 */
	static final int DEFAULT_TEXT_LIMIT = 24;
	
	private Double minValidValue;
	private Double maxValidValue;
	private final Composite parent;

	/**
	 * Creates an double field editor.
	 * 
	 * @param name the name of the preference this field editor works on
	 * @param labelText the label text of the field editor
	 * @param parent the parent of the field editor's control
	 */
	public DoubleFieldEditor(String name, String labelText, Composite parent) {
		this(name, labelText, parent, DoubleFieldEditor.DEFAULT_TEXT_LIMIT);
	}

	/**
	 * Creates an double field editor.
	 * 
	 * @param name the name of the preference this field editor works on
	 * @param labelText the label text of the field editor
	 * @param parent the parent of the field editor's control
	 * @param textLimit the maximum number of characters in the text.
	 */
	public DoubleFieldEditor(String name, String labelText, Composite parent, int textLimit) {
		init(name, labelText);
		this.parent = parent;
		setTextLimit(textLimit);
		setEmptyStringAllowed(false);
		setErrorMessage(labelText + " " + JFaceResources.getString("not a valid double")); //$NON-NLS-1$
		createControl(parent);
	}

	/**
	 * Sets the range of valid values for this field.
	 * 
	 * @param min the minimum allowed value (inclusive)
	 * @param max the maximum allowed value (inclusive)
	 */
	public void setValidRange(Double min, Double max) {
		minValidValue = min;
		maxValidValue = max;
		
		setErrorMessage(JFaceResources.format("{0} must be a value between {1} {2}", //$NON-NLS-1$
			new Object[] { getLabelText(), min, max }));
	}

	/* (non-Javadoc)
	 * Method declared on StringFieldEditor.
	 * Checks whether the entered String is a valid double or not.
	 */
	@Override
	protected boolean checkState() {

		Text text = getTextControl();

		if (text == null) {
			return false;
		}

		String numberString = text.getText();
		try {
			double number = Double.valueOf(numberString).doubleValue();
			if ((minValidValue == null || number >= minValidValue) && (maxValidValue == null || number <= maxValidValue)) {
				clearErrorMessage();
				return true;
			}

			showErrorMessage();
			return false;

		} catch (NumberFormatException e1) {
			showErrorMessage();
		}

		return false;
	}

	/* (non-Javadoc)
	 * Method declared on FieldEditor.
	 */
	@Override
	protected void doLoad() {
		Text text = getTextControl();
		if (text != null) {
			double value = getPreferenceStore().getDouble(getPreferenceName());
			text.setText("" + value); //$NON-NLS-1$
			oldValue = "" + value; //$NON-NLS-1$
		}

	}

	/* (non-Javadoc)
	 * Method declared on FieldEditor.
	 */
	@Override
	protected void doLoadDefault() {
		Text text = getTextControl();
		if (text != null) {
			double value = getPreferenceStore().getDefaultDouble(getPreferenceName());
			text.setText("" + value); //$NON-NLS-1$
		}
		valueChanged();
	}

	/* (non-Javadoc)
	 * Method declared on FieldEditor.
	 */
	@Override
	protected void doStore() {
		Text text = getTextControl();
		if (text != null) {
			Double i = new Double(text.getText());
			getPreferenceStore().setValue(getPreferenceName(), i.doubleValue());
		}
	}

	/**
	 * Returns this field editor's current value as an double.
	 *
	 * @return the value
	 * @exception NumberFormatException if the <code>String</code> does not contain a parsable double
	 */
	public double getDoubleValue() throws NumberFormatException {
		return new Double(getStringValue()).doubleValue();
	}

	public void setToolTipText(String toolTipText) {
		Text textControl = getTextControl();
		if (textControl != null) {
			textControl.setToolTipText(toolTipText);
		}
	}

	public void addFocusListener(FocusListener listener) {
		getTextControl().addFocusListener(listener);
	}
	
	public void removeFocusListener(FocusListener listener) {
		getTextControl().removeFocusListener(listener);
	}
	
	public void setEnabled(boolean enabled) {
		setEnabled(enabled, this.parent);
	}
}

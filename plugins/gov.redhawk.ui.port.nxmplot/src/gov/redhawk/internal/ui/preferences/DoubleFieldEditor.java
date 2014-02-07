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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * 
 */
public class DoubleFieldEditor extends StringFieldEditor {
	private Double minValidValue;

	private Double maxValidValue;

	private static final int DEFAULT_TEXT_LIMIT = 10;

	/**
	 * Creates a new double field editor 
	 */
	protected DoubleFieldEditor() {
	}

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
		setTextLimit(textLimit);
		setEmptyStringAllowed(false);
		setErrorMessage(JFaceResources.getString("Not a valid double")); //$NON-NLS-1$
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
		setErrorMessage(JFaceResources.format("Must be a value between {0} {1}", //$NON-NLS-1$
			new Object[] { new Double(min), new Double(max) }));
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
	 * @exception NumberFormatException if the <code>String</code> does not
	 *   contain a parsable double
	 */
	public double getDoubleValue() throws NumberFormatException {
		return new Double(getStringValue()).doubleValue();
	}

}

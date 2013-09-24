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
package gov.redhawk.sca.ui.preferences;

import gov.redhawk.sca.preferences.ScaPreferenceConstants;

import java.io.File;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @since 8.0
 * 
 */
public class StringListFieldEditor extends ListEditor {

	public StringListFieldEditor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StringListFieldEditor(final String name, final String labelText, final Composite parent) {
		super(name, labelText, parent);
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String createList(final String[] items) {
		return ScaPreferenceConstants.createPath(items);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNewInputObject() {
		final InputDialog input = new InputDialog(getShell(), "New Path", "Path:", "", new IInputValidator() {

			@Override
			public String isValid(final String newText) {
				if (newText.contains(File.pathSeparator)) {
					return "Must not contain '" + File.pathSeparator + "'";
				}
				return null;
			}
		});
		if (input.open() == Window.OK) {
			return input.getValue();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] parseString(final String stringList) {
		return ScaPreferenceConstants.parsePath(stringList);
	}

	@Override
	public Button getUpButton() {
		// TODO Auto-generated method stub
		return super.getUpButton();
	}

	@Override
	public Button getDownButton() {
		// TODO Auto-generated method stub
		return super.getDownButton();
	}

}

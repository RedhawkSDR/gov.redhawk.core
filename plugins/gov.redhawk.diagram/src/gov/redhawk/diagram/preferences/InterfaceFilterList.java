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
package gov.redhawk.diagram.preferences;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 */
public class InterfaceFilterList extends ListEditor {

	private static final String SEPERATOR = ",";

	/**
	 * Creates a new path field editor
	 */
	protected InterfaceFilterList() {
	}

	/**
	 * Creates a path field editor.
	 * 
	 * @param name the name of the preference this field editor works on
	 * @param labelText the label text of the field editor
	 * @param parent the parent of the field editor's control
	 */
	public InterfaceFilterList(final String name, final Composite parent) {
		init(name, "Interface Filters:");
		createControl(parent);
	}

	/*
	 * (non-Javadoc) Method declared on ListEditor. Creates a single string from
	 * the given array by separating each string with the appropriate
	 * OS-specific path separator.
	 */
	@Override
	protected String createList(final String[] items) {
		return InterfaceFilterList.createFilterString(items);
	}

	public static String createFilterString(final String... items) {
		final StringBuffer path = new StringBuffer(""); //$NON-NLS-1$

		for (int i = 0; i < items.length; i++) {
			path.append(items[i]);
			path.append(InterfaceFilterList.SEPERATOR);
		}
		return path.toString();
	}

	/*
	 * (non-Javadoc) Method declared on ListEditor. Creates a new path element
	 * by means of a directory dialog.
	 */
	@Override
	protected String getNewInputObject() {
		final InputDialog dialog = new InputDialog(getShell(), "Add Filter", "Enter a regular expression to filter out.", "", new IInputValidator() {

			public String isValid(final String newText) {
				try {
					Pattern.compile(newText);
					return null;
				} catch (final PatternSyntaxException e) {
					return e.getMessage();
				}
			}

		});
		if (dialog.open() == Window.OK) {
			return dialog.getValue();
		}
		return null;
	}

	/*
	 * (non-Javadoc) Method declared on ListEditor.
	 */
	@Override
	protected String[] parseString(final String stringList) {
		return InterfaceFilterList.parseFilterString(stringList);
	}

	public static String[] parseFilterString(final String stringList) {
		return (stringList != null) ? stringList.split(InterfaceFilterList.SEPERATOR) : new String[0]; // SUPPRESS CHECKSTYLE AvoidInline
	}

}

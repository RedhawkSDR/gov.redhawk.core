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
package gov.redhawk.ui.util;

import java.io.File;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.swt.widgets.Composite;

public class DirectoryFieldEditorWithSupportForEnvironment extends DirectoryFieldEditor {

	public DirectoryFieldEditorWithSupportForEnvironment() {
		super();
	}

	public DirectoryFieldEditorWithSupportForEnvironment(final String name, final String labelText,
	        final Composite parent) {
		super(name, labelText, parent);
	}

	@Override
	protected boolean doCheckState() {
		String fileName = getTextControl().getText();
		fileName = fileName.trim();
		if (fileName.startsWith("${") && fileName.endsWith("}")) {
			final String envName = fileName.substring(2, fileName.length() - 1);
			fileName = System.getenv(envName);
			if (fileName == null) {
				return false;
			}
		}
		if (fileName.length() == 0 && isEmptyStringAllowed()) {
			return true;
		}
		final File file = new File(fileName);
		return file.isDirectory();
	}

}

/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.prf.internal.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import gov.redhawk.prf.ui.PrfUiPlugin;

public class PrfEditorPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(PrfUiPlugin.getDefault().getPreferenceStore());
		setDescription("Settings for the PRF editor");
	}

	@Override
	protected void createFieldEditors() {
		addField(new BooleanFieldEditor(PrfEditorPreferenceConstants.ID_SCOPING, "Automatic ID Scoping", getFieldEditorParent()));
	}

}

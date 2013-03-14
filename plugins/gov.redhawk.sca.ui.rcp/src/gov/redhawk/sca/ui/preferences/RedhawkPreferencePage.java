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
package gov.redhawk.sca.ui.preferences;

import gov.redhawk.sca.ui.ScaUiPlugin;

import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @since 10.0
 */
public class RedhawkPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	public static final String PREFERENCES_PAGE_ID = "gov.redhawk.ui.preferences";

	public void init(final IWorkbench workbench) {
		setPreferenceStore(ScaUiPlugin.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		final RadioGroupFieldEditor field = new RadioGroupFieldEditor(RedhawkUIPreferenceConstants.CREATE_WAVEFORM_LAUNCH_CONFIGURATION,
		        "Create new launch shortcut after Waveform Launch",
		        3,
		        new String[][] {
		                new String[] {
		                        "Always", MessageDialogWithToggle.ALWAYS
		                }, new String[] {
		                        "Never", MessageDialogWithToggle.NEVER
		                }, new String[] {
		                        "Prompt", MessageDialogWithToggle.PROMPT
		                }
		        },
		        getFieldEditorParent(),
		        true);
		addField(field);
	}

}

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

import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.preferences.SddsPreferences;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class SddsBlockPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private IWorkbench workbench;

	public SddsBlockPreferencePage() {
		super("SDDS", FieldEditorPreferencePage.GRID);
		setDescription("Modify how the data is being received via BulkIO SDDS Data.");
	}

	@Override
	public void init(IWorkbench workbench) {
		this.workbench = workbench;
		setPreferenceStore(PlotActivator.getDefault().getPreferenceStore());
		setDescription("Change various default settings for how the data is being received via BulkIO SDDS.");
	}

	@Override
	protected void createFieldEditors() {
		String[][] byteOrderEntryNamesAndValues = new String[][] {
			{"native (current machine's endianiness)", "NATIVE"},
			{"little endian", "LITTLE_ENDIAN"},
			{"big endian", "BIG_ENDIAN",    },
		};
		ComboFieldEditor byteOrderField = new ComboFieldEditor(SddsPreferences.BYTE_ORDER.getName(), "Data Byte Order:", byteOrderEntryNamesAndValues, getFieldEditorParent());
		addField(byteOrderField);
	}

}

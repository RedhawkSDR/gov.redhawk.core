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

import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.ui.port.nxmplot.preferences.BulkIOPreferences;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

/**
 * 
 */
public class BulkIOBlockPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private IWorkbench workbench;

	/**
	 * 
	 */
	public BulkIOBlockPreferencePage() {
		super("BULKIO", FieldEditorPreferencePage.GRID);
		setDescription("Modify how the data is being received via the CORBA Bulk Data.");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		this.workbench = workbench;
		setPreferenceStore(ScaUiPlugin.getDefault().getScaPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		if (workbench != null) {
			addField(new IntegerFieldEditor(BulkIOPreferences.TLL.getName(), "&Time line length:", getFieldEditorParent()));
		} else {
			addField(new ReadOnlyStringFieldEditor(BulkIOPreferences.CONNECTION_ID.getName(), "&Connection ID:", getFieldEditorParent()));
			addField(new OverridableIntegerFieldEditor(BulkIOPreferences.SAMPLE_RATE.getName(), BulkIOPreferences.SAMPLE_RATE_OVERRIDE.getName(),
				"&Sample Rate:", getFieldEditorParent()));
		}

		final Composite booleanControls = new Composite(getFieldEditorParent(), SWT.None);
		booleanControls.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		addField(new BooleanFieldEditor(BulkIOPreferences.BLOCKING.getName(), "&Blocking", booleanControls));
		addField(new BooleanFieldEditor(BulkIOPreferences.REMOVE_ON_EOS.getName(), "&Remove on 'End of Stream'", booleanControls));

		//		createAdvancedFields();

	}

	private void createAdvancedFields() {
		final Composite parent = getFieldEditorParent();
		Section advancedComposite = new Section(parent, ExpandableComposite.TWISTIE);
		advancedComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		advancedComposite.setText("Advanced");
		advancedComposite.setFont(parent.getFont());
		advancedComposite.setExpanded(false);
		Composite section = new Composite(advancedComposite, SWT.None);
		advancedComposite.setClient(section);

		addField(new OverridableIntegerFieldEditor(BulkIOPreferences.PIPE_SIZE.getName(), BulkIOPreferences.PIPE_SIZE_OVERRIDE.getName(), "&Pipe Size:",
			section));
		addField(new IntegerFieldEditor(BulkIOPreferences.PIPE_SIZE_MULTIPLIER.getName(), "&Pipe Size Multiplier:", section));
		Composite sectionBooleanComposite = new Composite(section, SWT.None);
		sectionBooleanComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		addField(new BooleanFieldEditor(BulkIOPreferences.CAN_GROW_PIPE.getName(), "&Can grow pipe", sectionBooleanComposite));
	}

}

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

import gov.redhawk.ui.port.nxmblocks.BulkIONxmBlockSettings.BlockingOption;
import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.preferences.BulkIOPreferences;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

public class BulkIOBlockPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private IWorkbench workbench;

	public BulkIOBlockPreferencePage() {
		super("BULKIO", FieldEditorPreferencePage.GRID);
		setDescription("Modify how the data is being received via the CORBA Bulk Data.");
	}

	@Override
	public void init(IWorkbench workbench) {
		this.workbench = workbench;
		setPreferenceStore(PlotActivator.getDefault().getPreferenceStore());
		setDescription("Change various default settings for how the data is being received via CORBA Bulk IO.");
	}

	@Override
	protected void createFieldEditors() {
		if (workbench != null) {
			IntegerFieldEditor tllField = new IntegerFieldEditor(BulkIOPreferences.TLL.getName(), "&Time line length:", getFieldEditorParent());
			tllField.setErrorMessage("Time line length must be an integer >= 1");
			tllField.setValidRange(1, Integer.MAX_VALUE);
			addField(tllField);
		} else {
			addField(new ReadOnlyStringFieldEditor(BulkIOPreferences.CONNECTION_ID.getName(), "&Connection ID:", getFieldEditorParent()));
			OverridableIntegerFieldEditor sampleRateField = new OverridableIntegerFieldEditor(BulkIOPreferences.SAMPLE_RATE.getName(),
				BulkIOPreferences.SAMPLE_RATE_OVERRIDE.getName(), "&Sample Rate:", getFieldEditorParent());
			sampleRateField.setToolTipText("Custom sample rate to override value in StreamSRI. Use 0 or leave blank (AUTO) to use value from StreamSRI.");
			sampleRateField.setValidRange(0, Integer.MAX_VALUE);
			sampleRateField.setErrorMessage("Sample rate must be an integer >= 0");
			addField(sampleRateField);
		}

		final Composite booleanControls = new Composite(getFieldEditorParent(), SWT.None);
		booleanControls.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		
		// blocking option
		final BlockingOption[] blockingOptionValues = BlockingOption.values();
		final int numBlockingOptions = blockingOptionValues.length;
		final String[][] blockingLabelsAndValues = new String[numBlockingOptions][];
		int ii = 0;
		for (BlockingOption b : blockingOptionValues) {
			blockingLabelsAndValues[ii++] = new String[] { b.getLabel(), b.name() };
		}
		RadioGroupFieldEditor blockingRadioGroupFieldEditor = new RadioGroupFieldEditor(
			BulkIOPreferences.BLOCKING_OPTION.getName(), "Blocking Option", numBlockingOptions, blockingLabelsAndValues, getFieldEditorParent(), true);
		addField(blockingRadioGroupFieldEditor);
		
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

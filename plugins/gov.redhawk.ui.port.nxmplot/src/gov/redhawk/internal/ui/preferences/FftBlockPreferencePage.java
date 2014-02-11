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

import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings;
import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.preferences.FftPreferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

/**
 * 
 */
public class FftBlockPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private IWorkbench workbench;

	/**
	 * 
	 */
	public FftBlockPreferencePage() {
		super("FFT", FieldEditorPreferencePage.GRID);
		setDescription("Change various settings on the FFT primitive.");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		this.workbench = workbench;
		setPreferenceStore(PlotActivator.getDefault().getPreferenceStore());
		setDescription("Change various default settings for the FFT primitive.");
	}

	@Override
	protected void createFieldEditors() {
		IntegerFieldEditor numAvgs = new IntegerFieldEditor(FftPreferences.NUM_AVERAGES.getName(), "&Num Averages:", getFieldEditorParent());
		numAvgs.setValidRange(0, Integer.MAX_VALUE);
		numAvgs.setErrorMessage("Must be greater than or equal to '0'");
		addField(numAvgs);

		List<String[]> values = new ArrayList<String[]>();
		for (FftNxmBlockSettings.OutputType type : FftNxmBlockSettings.OutputType.values()) {
			values.add(new String[] { type.getLabel(), type.toString() });
		}

		String[][] nameValue = values.toArray(new String[0][]);
		ComboFieldEditor outputType = new ComboFieldEditor(FftPreferences.OUTPUT_TYPE.getName(), "&Output Type:", nameValue, getFieldEditorParent());
		addField(outputType);
		addField(new StringFieldEditor(FftPreferences.OVERLAP.getName(), "O&verlap:", getFieldEditorParent()));
		addField(new IntegerFieldEditor(FftPreferences.SLIDING_NUM_AVERAGES.getName(), "&Sliding Num Averages:", getFieldEditorParent()));
		addField(new IntegerFieldEditor(FftPreferences.TRANSFORM_SIZE.getName(), "&Transform Size:", getFieldEditorParent()));

		List<String[]> windowTypeValues = new ArrayList<String[]>();
		for (FftNxmBlockSettings.WindowType type : FftNxmBlockSettings.WindowType.values()) {
			windowTypeValues.add(new String[] { type.getLabel(), type.toString() });
		}
		String[][] windowValue = windowTypeValues.toArray(new String[0][]);
		ComboFieldEditor windowTypeField = new ComboFieldEditor(FftPreferences.WINDOW_TYPE.getName(), "&Window Type:", windowValue, getFieldEditorParent());
		addField(windowTypeField);

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
		section.setLayout(new GridLayout(2, false));

		addField(new OverridableIntegerFieldEditor(FftPreferences.PIPE_SIZE.getName(), FftPreferences.PIPE_SIZE_OVERRIDE.getName(), "Pipe Size:", section));

		advancedComposite.setClient(section);
	}
}

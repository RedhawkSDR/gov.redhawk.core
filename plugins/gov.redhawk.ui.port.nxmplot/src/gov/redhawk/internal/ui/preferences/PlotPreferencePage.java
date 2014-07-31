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
package gov.redhawk.internal.ui.preferences;

import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.PlotSettings;
import gov.redhawk.ui.port.nxmplot.PlotSettings.PlotMode;
import gov.redhawk.ui.port.nxmplot.preferences.PlotPreferences;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

public class PlotPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private boolean isBlock;
	private IWorkbench workbench;
	private IPreferenceStore blockPreferenceStore;
	private List<FieldEditor> blockPreferences = new ArrayList<FieldEditor>();

	public PlotPreferencePage() {
		this(null, false);
	}

	public PlotPreferencePage(String label) {
		this(label, false);
	}

	public PlotPreferencePage(String label, boolean isBlock) {
		super("Plot", FieldEditorPreferencePage.GRID);
		this.isBlock = isBlock;
		if (label != null) {
			setTitle(label);
		}
		setDescription("Change various settings on how the data is displayed within the plot.");
	}

	@Override
	protected void initialize() {
		super.initialize();
		if (blockPreferenceStore != null) {
			Iterator<FieldEditor> e = blockPreferences.iterator();
			while (e.hasNext()) {
				FieldEditor pe = e.next();
				pe.setPage(this);
				pe.setPropertyChangeListener(this);
				pe.setPreferenceStore(blockPreferenceStore);
				pe.load();
			}
		}
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		if (blockPreferenceStore != null) {
			Iterator<FieldEditor> e = blockPreferences.iterator();
			while (e.hasNext()) {
				FieldEditor pe = e.next();
				pe.loadDefault();
			}
		}
		// Force a recalculation of my error state.
		checkState();
	}

	@Override
	public boolean performOk() {
		if (!super.performOk()) {
			return false;
		}
		if (blockPreferenceStore != null) {
			Iterator<FieldEditor> e = blockPreferences.iterator();
			while (e.hasNext()) {
				FieldEditor pe = e.next();
				pe.store();
				// TODO How do we we do this for block preferences, I don't have access to the method
				//				pe.setPresentsDefaultValue(false);
			}
		}
		return true;
	}

	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	@Override
	public void createFieldEditors() {
		if (workbench != null) {
			addField(createPlotModesField());
			addField(createFrameSizeField());
			addField(createRefreshRateField(getFieldEditorParent()));
			addField(createLinePlotConsumeLengthField(getFieldEditorParent()));
			addField(createConfigureMenuField(getFieldEditorParent()));
			addField(createQuickControlsField());
		} else {
			if (isBlock) {
				addField(createFrameSizeField());
				//				creatBlockAdancedFields();

			} else {
				/*
				 * Page settings store is Plot Settings
				 * Block setting store is Nxm Block Settings
				 */
				addField(createPlotModesField());
				if (blockPreferenceStore != null) {
					blockPreferences.add(createFrameSizeField());
				}

				OverridableDoubleFieldEditor minField = new OverridableDoubleFieldEditor(PlotPreferences.MIN.getName(),
					PlotPreferences.MIN_OVERRIDE.getName(), "Mi&n:", getFieldEditorParent());
				addField(minField);

				OverridableDoubleFieldEditor maxField = new OverridableDoubleFieldEditor(PlotPreferences.MAX.getName(),
					PlotPreferences.MAX_OVERRIDE.getName(), "Ma&x:", getFieldEditorParent());
				addField(maxField);

				final Composite booleanControls = new Composite(getFieldEditorParent(), SWT.None);
				addField(createConfigureMenuField(booleanControls));
				booleanControls.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

				if (blockPreferenceStore != null) {
					blockPreferences.add(createQuickControlsField());
				}

				createAdvancedFields();
			}
		}
	}

	private BooleanFieldEditor createConfigureMenuField(Composite parent) {
		return new BooleanFieldEditor(PlotPreferences.ENABLE_CONFIGURE_MENU_USING_MOUSE.getName(),
			"&Enable plot configure menu using mouse",
			parent);
	}

	private OverridableIntegerFieldEditor createFrameSizeField() {
		OverridableIntegerFieldEditor frameSizeField = new OverridableIntegerFieldEditor(PlotPreferences.FRAMESIZE.getName(),
			PlotPreferences.FRAMESIZE_OVERRIDE.getName(), "&Framesize:", getFieldEditorParent());
		frameSizeField.setErrorMessage("Framesize must be a positive integer >= 2");
		frameSizeField.setValidRange(2, Integer.MAX_VALUE);
		return frameSizeField;
	}

	private OverridableIntegerFieldEditor createLinePlotConsumeLengthField(Composite parent) {
		OverridableIntegerFieldEditor linePlotConsLenField = new OverridableIntegerFieldEditor(PlotPreferences.LINE_PLOT_CONSUMELENGTH.getName(),
			PlotPreferences.LINE_PLOT_CONSUMELENGTH_OVERRIDE.getName(), "&Line Plot Frame Thinning:", parent);
		linePlotConsLenField.setErrorMessage("Consume Length must a an integer >= -1");
		linePlotConsLenField.setValidRange(-1, Integer.MAX_VALUE);
		linePlotConsLenField.setToolTipText("Thin line plot by displaying 1 out of every n frames. "
			+ "Use -1 for no thinning. Leave blank to use default of " + PlotPreferences.LINE_PLOT_CONSUMELENGTH.getDefaultValue() + ".");
		return linePlotConsLenField;
	}
	
	private BooleanFieldEditor createQuickControlsField() {
		return new BooleanFieldEditor(PlotPreferences.ENABLE_QUICK_CONTROLS.getName(),
			"Enable &quick access control widgets",
			getFieldEditorParent());
	}

	private OverridableIntegerFieldEditor createRefreshRateField(Composite parent) {
		OverridableIntegerFieldEditor refreshRateField = new OverridableIntegerFieldEditor(PlotPreferences.REFRESH_RATE.getName(),
			PlotPreferences.REFRESH_RATE_OVERRIDE.getName(), "&Refresh Rate:", parent);
		refreshRateField.setErrorMessage("Refresh Rate must a an integer >= 0");
		refreshRateField.setValidRange(0, Integer.MAX_VALUE);
		refreshRateField.setToolTipText("Desired refresh rate (screen frames per second (FPS)) to do smart thinning of data. "
			+ "Use 0 to disable smart thinning. Leave blank to use default of " + PlotPreferences.REFRESH_RATE.getDefaultValue() + ".");
		return refreshRateField;
	}
	private void creatBlockAdancedFields() {
		final Composite parent = getFieldEditorParent();
		Section advancedComposite = new Section(parent, ExpandableComposite.TWISTIE);
		advancedComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		advancedComposite.setText("Advanced");
		advancedComposite.setFont(parent.getFont());
		advancedComposite.setExpanded(false);
		Composite section = new Composite(advancedComposite, SWT.None);
		advancedComposite.setClient(section);

		addField(new OverridableIntegerFieldEditor(PlotPreferences.PIPESIZE.getName(), PlotPreferences.PIPESIZE_OVERRIDE.getName(), "&Pipe Size:", section));
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

		if (blockPreferenceStore != null) {
			addField(new ReadOnlyStringFieldEditor(PlotPreferences.PIPESIZE.getName(), "&Pipe Size:", section));
//			blockPreferences.add(new OverridableIntegerFieldEditor(PlotPreferences.PIPESIZE.getName(), PlotPreferences.PIPESIZE_OVERRIDE.getName(),
//				"&Pipe Size:", section));
			
			blockPreferences.add(createRefreshRateField(section));
			blockPreferences.add(createLinePlotConsumeLengthField(section));
		}
		
		addField(new ReadOnlyStringFieldEditor(PlotPreferences.LAUNCH_ARGS.getName(), "Launch &Args:", section));
		addField(new ReadOnlyStringFieldEditor(PlotPreferences.LAUNCH_SWITCHES.getName(), "Launch &Switches:", section));
	}

	public void setBlockPreferenceStore(IPreferenceStore store) {
		this.isBlock = false;
		this.blockPreferenceStore = store;
	}

	public IPreferenceStore getBlockPreferenceStore() {
		return blockPreferenceStore;
	}

	private ComboFieldEditor createPlotModesField() {
		List<String[]> modes = new ArrayList<String[]>();
		for (PlotSettings.PlotMode mode : PlotMode.getStandardModes()) {
			modes.add(new String[] { mode.getLabel(), mode.toString() });
		}
		String[][] modeValues = modes.toArray(new String[0][]);
		ComboFieldEditor modeField = new ComboFieldEditor(PlotPreferences.MODE.getName(), "&Mode:", modeValues, getFieldEditorParent());
		return modeField;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		this.workbench = workbench;
		setPreferenceStore(PlotActivator.getDefault().getPreferenceStore());
		setDescription("Change various default settings on how the data is displayed within the plots.");
	}

}

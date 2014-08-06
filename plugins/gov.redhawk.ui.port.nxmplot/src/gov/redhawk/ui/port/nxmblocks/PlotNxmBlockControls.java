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
package gov.redhawk.ui.port.nxmblocks;

import gov.redhawk.sca.util.ArrayUtil;
import gov.redhawk.ui.port.nxmplot.preferences.PlotPreferences;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Adjust/override INxmBlock settings to plot user entry dialog.
 * @NonNullByDefault
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class PlotNxmBlockControls {

	private static final String VALUE_USE_DEFAULT = "default";
	private static final Object[] FRAME_SIZE_COMBO_VALUES = new Object[] { VALUE_USE_DEFAULT, 512, 1024, 2048, 4096, 8192 };
	
	private static final String FRAME_SIZE_FIELD_NAME = "Frame Size";
	
	private final PlotNxmBlockSettings settings;
	private final DataBindingContext dataBindingCtx;

	// widgets
	private ComboViewer frameSizeField;
	private Text linePlotConsumeLengthField;
	private Text refreshRateField;

	public PlotNxmBlockControls(PlotNxmBlockSettings settings, DataBindingContext dataBindingCtx) {
		this.settings = settings;
		this.dataBindingCtx = dataBindingCtx;
	}

	public void createControls(final Composite container) {
		container.setLayout(new GridLayout(2, false));
		Label label;

		// === frame size ===
		label = new Label(container, SWT.NONE);
		label.setText(FRAME_SIZE_FIELD_NAME + ":");
		this.frameSizeField = new ComboViewer(container, SWT.BORDER); // writable
		this.frameSizeField.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true,  false).create());
		this.frameSizeField.getCombo().setToolTipText("Custom frame size to override value in StreamSRI. Default uses value from StreamSRI.");
		this.frameSizeField.setContentProvider(ArrayContentProvider.getInstance()); // ArrayContentProvider does not store any state, therefore can re-use instances
		this.frameSizeField.setLabelProvider(new LabelProvider());
		Object[] inputValues = ArrayUtil.copyAndPrependIfNotNull(this.settings.getFrameSize(), FRAME_SIZE_COMBO_VALUES);
		this.frameSizeField.setInput(inputValues);
		this.frameSizeField.setSelection(new StructuredSelection(inputValues[0])); // select first value (which is current value or default)
		this.frameSizeField.addSelectionChangedListener(new SelectAllTextComboTextListener(this.frameSizeField.getCombo()));

		// === refresh rate (FPS) smart thinning ===
		label = new Label(container, SWT.NONE);
		label.setText("&Refresh Rate (fps):");
		this.refreshRateField = new Text(container, SWT.BORDER);
		this.refreshRateField.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		this.refreshRateField.setToolTipText("Attempt desired refresh rate (screen frames per second (FPS)) to do smart thinning of data. "
				+ "Use 0 to disable smart thinning. Leave blank to use default of " + PlotPreferences.REFRESH_RATE.getDefaultValue() + ".");

		// === line plot frame thinning ===
		label = new Label(container, SWT.NONE);
		label.setText("&Line Plot Frame Thinning:");
		this.linePlotConsumeLengthField = new Text(container, SWT.BORDER);
		this.linePlotConsumeLengthField.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		this.linePlotConsumeLengthField.setToolTipText("Thin line plot by displaying 1 out of every n frames. "
			+ "Use -1 for no thinning. Leave blank to use default of " + PlotPreferences.LINE_PLOT_CONSUMELENGTH.getDefaultValue() + ".");

		initDataBindings();
	}

	private void initDataBindings() {
		IObservableValue frameSizeWidgetValue = WidgetProperties.selection().observe(this.frameSizeField.getCombo());
		IObservableValue frameSizeModelValue = PojoProperties.value(PlotNxmBlockSettings.PROP_FRAME_SIZE).observe(this.settings);
		UpdateValueStrategy frameSizeTargetToModel = new UpdateValueStrategy();
		frameSizeTargetToModel.setAfterGetValidator(new StringToIntegerValidator(FRAME_SIZE_FIELD_NAME, VALUE_USE_DEFAULT));
		frameSizeTargetToModel.setConverter(new ObjectToNullConverter(StringToNumberConverter.toInteger(false), true, true, VALUE_USE_DEFAULT));
		frameSizeTargetToModel.setAfterConvertValidator(new NumberRangeValidator<Integer>(FRAME_SIZE_FIELD_NAME, Integer.class, 2));
		UpdateValueStrategy frameSizeModelToTarget = new UpdateValueStrategy();
		frameSizeModelToTarget.setConverter(new ObjectToNullConverter()); // converts null to null, otherwise uses toString()
		Binding bindingValue = dataBindingCtx.bindValue(frameSizeWidgetValue, frameSizeModelValue, frameSizeTargetToModel, frameSizeModelToTarget);
		ControlDecorationSupport.create(bindingValue, SWT.TOP | SWT.LEFT);
		
		bindingValue = dataBindingCtx.bindValue(
			WidgetProperties.text(SWT.Modify).observe(refreshRateField),
			PojoProperties.value("refreshRate").observe(this.settings));
		ControlDecorationSupport.create(bindingValue, SWT.TOP | SWT.LEFT);

		bindingValue = dataBindingCtx.bindValue(
			WidgetProperties.text(SWT.Modify).observe(linePlotConsumeLengthField),
			PojoProperties.value("linePlotConsumeLength").observe(this.settings));
		ControlDecorationSupport.create(bindingValue, SWT.TOP | SWT.LEFT);
	}
}

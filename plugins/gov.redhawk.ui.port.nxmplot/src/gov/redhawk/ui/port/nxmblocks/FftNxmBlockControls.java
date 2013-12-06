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
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings.OutputType;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings.WindowType;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
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
 * Adjust/override FFT settings user entry dialog.
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class FftNxmBlockControls {

	private static final String VALUE_USE_DEFAULT = "default"; // TODO: DELETE - NOT USED?
	private static final Object[] FFT_SIZE_COMBO_VALUES = new Object[] { 1024, 2048, 4096, 8192, 16384 };
	private static final String FFT_SIZE_FIELD_NAME = "Transform Size";
	private static final String OVERLAP_FIELD_NAME = "Percent Overlap";
	private static final String NUM_AVERAGES_FIELD_NAME = "Num Averages";
	
	private final FftNxmBlockSettings settings;
	private DataBindingContext dataBindingCtx;
	
	private ComboViewer transformSizeField;
	private Text        overlapField;
	private Text        numAveragesField;
	private ComboViewer fftType;
	private ComboViewer fftWindow;

	public FftNxmBlockControls(@NonNull final FftNxmBlockSettings settings, @NonNull final DataBindingContext dataBindingsCtx) {
		this.settings = settings;
		this.dataBindingCtx = dataBindingsCtx;
	}

	public void createControls(@NonNull final Composite container) {
		container.setLayout(new GridLayout(2, false));
		Label label;

		// === FFT transform size ===
		label = new Label(container, SWT.NONE);
		label.setText(FFT_SIZE_FIELD_NAME + ":");
		this.transformSizeField = new ComboViewer(container, SWT.BORDER); // writable
		this.transformSizeField.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true,  false).create());
		this.transformSizeField.getCombo().setToolTipText("Performance is best if factorable by 2, 3, 4 and 5.");
		this.transformSizeField.setContentProvider(ArrayContentProvider.getInstance()); // ArrayContentProvider does not store any state, therefore can re-use instances
		this.transformSizeField.setLabelProvider(new LabelProvider());
		Object[] inputValues = ArrayUtil.copyAndPrependIfNotNull(this.settings.getTransformSize(), FFT_SIZE_COMBO_VALUES);
		this.transformSizeField.setInput(inputValues);
		this.transformSizeField.setSelection(new StructuredSelection(inputValues[0])); // select first value (which is current value or default)

		// === percent overlap ===
		label = new Label(container, SWT.NONE);
		label.setText(OVERLAP_FIELD_NAME + ":");
		this.overlapField = new Text(container, SWT.BORDER);
		this.overlapField.setLayoutData(GridDataFactory.fillDefaults().grab(true,  false).create());
		this.overlapField.setText(Integer.toString(this.settings.getOverlap()));

		// === num averages (/nexp) ===
		label = new Label(container, SWT.NONE);
		label.setText(NUM_AVERAGES_FIELD_NAME + ":");
		this.numAveragesField = new Text(container, SWT.BORDER);
		this.numAveragesField.setLayoutData(GridDataFactory.fillDefaults().grab(true,  false).create());
		this.numAveragesField.setText(Integer.toString(this.settings.getNumAverages()));
		this.numAveragesField.setToolTipText("Avoid using large value as it will cause highlighted energy to remain longer.");

		// === can not change FFT output type at this time ===
		label = new Label(container, SWT.NONE);
		label.setText("Output Type:");
		this.fftType = new ComboViewer(container, SWT.READ_ONLY);
		fftType.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true,  false).create());
		fftType.setContentProvider(ArrayContentProvider.getInstance()); // ArrayContentProvider does not store any state, therefore can re-use instances
		fftType.setLabelProvider(new LabelProvider());
		fftType.setInput(FftNxmBlockSettings.OutputType.values());
		OutputType currentOutputType = this.settings.getOutputType();
		if (currentOutputType == null) {
			currentOutputType = OutputType.PSD; // default to PSD output
		} else {
			fftType.getCombo().setEnabled(false); // disable changing fft output type
		}
		fftType.setSelection(new StructuredSelection(currentOutputType));

		// === FFT Window ===
		label = new Label(container, SWT.NONE);
		label.setText("Window:");
		fftWindow = new ComboViewer(container, SWT.READ_ONLY);
		fftWindow.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true,  false).create());
		fftWindow.setContentProvider(ArrayContentProvider.getInstance()); // ArrayContentProvider does not store any state, therefore can re-use instances
		fftWindow.setLabelProvider(new LabelProvider());
		fftWindow.setInput(FftNxmBlockSettings.WindowType.values());
		WindowType windowType = this.settings.getWindow();
		if (windowType == null) {
			windowType = WindowType.HANNING; // default to Hanning Window Type
		}
		fftWindow.setSelection(new StructuredSelection(windowType));
		
		initDataBindings();
	}

	private void initDataBindings() {
		Binding bindingValue;

		IObservableValue tsWidgetValue = WidgetProperties.selection().observe(this.transformSizeField.getCombo());
		IObservableValue tsModelValue = PojoProperties.value(FftNxmBlockSettings.PROP_TRANSFORM_SIZE).observe(this.settings);
		bindingValue = dataBindingCtx.bindValue(tsWidgetValue, tsModelValue, createTargetToModelForFftSize(), null);
		ControlDecorationSupport.create(bindingValue, SWT.TOP | SWT.LEFT);

		IObservableValue overlapWidgetValue = WidgetProperties.text(SWT.Modify).observe(this.overlapField);
		IObservableValue overlapModelValue  = PojoProperties.value(FftNxmBlockSettings.PROP_OVERLAP).observe(this.settings);
		bindingValue = dataBindingCtx.bindValue(overlapWidgetValue, overlapModelValue, createTargetToModelForOverlap(), null);
		ControlDecorationSupport.create(bindingValue, SWT.TOP | SWT.LEFT);

		IObservableValue numAvgWidgetValue = WidgetProperties.text(SWT.Modify).observe(this.numAveragesField);
		IObservableValue numAvgModelValue = PojoProperties.value(FftNxmBlockSettings.PROP_NUM_AVERAGES).observe(this.settings);
		bindingValue = dataBindingCtx.bindValue(numAvgWidgetValue, numAvgModelValue, createTargetToModelForNumAverages(), null);
		ControlDecorationSupport.create(bindingValue, SWT.TOP | SWT.LEFT);

		// todo bind output type
		
		IObservableValue windowWidgetValue = ViewerProperties.singleSelection().observe(this.fftWindow);
		IObservableValue windowModelValue = PojoProperties.value(FftNxmBlockSettings.PROP_WINDOW_TYPE).observe(this.settings);
		bindingValue = dataBindingCtx.bindValue(windowWidgetValue, windowModelValue);
		ControlDecorationSupport.create(bindingValue, SWT.TOP | SWT.LEFT);
	}

	private UpdateValueStrategy createTargetToModelForFftSize() {
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		updateValueStrategy.setAfterConvertValidator(new NumberRangeValidator<Integer>(FFT_SIZE_FIELD_NAME, Integer.class, 0, false));
		return updateValueStrategy;
	}
	
	private UpdateValueStrategy createTargetToModelForOverlap() {
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		updateValueStrategy.setAfterConvertValidator(new NumberRangeValidator<Integer>(OVERLAP_FIELD_NAME, Integer.class, 0, 100));
		return updateValueStrategy;
	}

	private UpdateValueStrategy createTargetToModelForNumAverages() {
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		updateValueStrategy.setAfterConvertValidator(new NumberRangeValidator<Integer>(NUM_AVERAGES_FIELD_NAME, Integer.class, 0, false));
		return updateValueStrategy;
	}

}

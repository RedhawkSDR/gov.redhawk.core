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
package gov.redhawk.ui.port.nxmblocks;

import gov.redhawk.sca.util.ArrayUtil;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.NumberToStringConverter;
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Adjust/override BULKIO NXM block settings user interface/entry control widgets.
 * @NonNullByDefault
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class BulkIONxmBlockControls {

	private static final String VALUE_USE_DEFAULT = "default";
	
	private final BulkIONxmBlockSettings settings;
	private final DataBindingContext dataBindingCtx;
	
	// widgets
	private ComboViewer sampleRateField;
	private Button blockingField;

	public BulkIONxmBlockControls(BulkIONxmBlockSettings settings, DataBindingContext dataBindingCtx) {
		this.settings = settings;
		this.dataBindingCtx = dataBindingCtx;
	}

	public void createControls(final Composite container) {
		container.setLayout(new GridLayout(2, false));
		Label label;
		
		// === sample rate ===
		label = new Label(container, SWT.NONE);
		label.setText("Sample rate:");
		this.sampleRateField = new ComboViewer(container, SWT.BORDER); // writable
		this.sampleRateField.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true,  false).create());
		this.sampleRateField.getCombo().setToolTipText("Custom sample rate to override value in StreamSRI. Default uses value from StreamSRI.");
		this.sampleRateField.setContentProvider(ArrayContentProvider.getInstance()); // ArrayContentProvider does not store any state, therefore can re-use instances
		this.sampleRateField.setLabelProvider(new LabelProvider());
		Object[] inputValues = ArrayUtil.copyAndPrependIfNotNull(this.settings.getSampleRate(), VALUE_USE_DEFAULT);
		this.sampleRateField.setInput(inputValues);
		this.sampleRateField.setSelection(new StructuredSelection(inputValues[0])); // select first value (which is current value or default)

		// === blocking option ===
		label = new Label(container, SWT.NONE);
		label.setText("Blocking:");
		this.blockingField = new Button(container, SWT.CHECK);
		this.blockingField.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		this.blockingField.setToolTipText("On/checked to block pushPacket when Plot is not able to keep up; Off to drop packets in this scenario.");

		initDataBindings();
	}

	private void initDataBindings() {
		Binding bindingValue; 
		
		IObservableValue srWidgetValue = WidgetProperties.selection().observe(this.sampleRateField.getCombo());
		IObservableValue srModelValue = PojoProperties.value(BulkIONxmBlockSettings.PROP_SAMPLE_RATE).observe(settings);
		UpdateValueStrategy srModelToTarget = new UpdateValueStrategy();
		srModelToTarget.setConverter(new ObjectsToNullConverterWrapper(NumberToStringConverter.fromDouble(false))); // wrap so that null converts to null
		bindingValue = dataBindingCtx.bindValue(srWidgetValue, srModelValue, createTargetToModelForSampleRate(), srModelToTarget);
		ControlDecorationSupport.create(bindingValue, SWT.TOP | SWT.LEFT);
		
		IObservableValue boWidgetValue = WidgetProperties.selection().observe(this.blockingField); 
		IObservableValue boModelValue = PojoProperties.value(BulkIONxmBlockSettings.PROP_BLOCKING_OPTION).observe(settings);
		bindingValue = dataBindingCtx.bindValue(boWidgetValue, boModelValue);
		ControlDecorationSupport.create(bindingValue, SWT.TOP | SWT.LEFT);
	}

	private UpdateValueStrategy createTargetToModelForSampleRate() {
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		updateValueStrategy.setConverter(new ObjectsToNullConverterWrapper(StringToNumberConverter.toDouble(false), true, true, VALUE_USE_DEFAULT));
		updateValueStrategy.setAfterConvertValidator(new NumberRangeValidator<Double>("Sample rate", Double.class, 0.0, false));
		return updateValueStrategy;
	}

}

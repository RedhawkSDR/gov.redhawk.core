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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Adjust/override INxmBlock settings to plot user entry dialog.
 * @NonNullByDefault
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class PlotNxmBlockControls {

	private static final String VALUE_USE_DEFAULT = "default";
	private static final Object[] FRAME_SIZE_COMBO_VALUES = new Object[] { VALUE_USE_DEFAULT, 512, 1024, 2048, 4096, 8192 };
	
	private final PlotNxmBlockSettings settings;
	private final DataBindingContext dataBindingCtx;

	private ComboViewer frameSizeField;

	public PlotNxmBlockControls(PlotNxmBlockSettings settings, DataBindingContext dataBindingCtx) {
		this.settings = settings;
		this.dataBindingCtx = dataBindingCtx;
	}

	public void createControls(final Composite container) {
		container.setLayout(new GridLayout(2, false));
		Label label;

		// === frame size ===
		label = new Label(container, SWT.NONE);
		label.setText("Frame Size:");
		this.frameSizeField = new ComboViewer(container, SWT.BORDER); // writable
		this.frameSizeField.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true,  false).create());
		this.frameSizeField.getCombo().setToolTipText("Custom frame size to override value in StreamSRI. Default uses value from StreamSRI.");
		this.frameSizeField.setContentProvider(ArrayContentProvider.getInstance()); // ArrayContentProvider does not store any state, therefore can re-use instances
		this.frameSizeField.setLabelProvider(new LabelProvider());
		Object[] inputValues = ArrayUtil.copyAndPrependIfNotNull(this.settings.getFrameSize(), FRAME_SIZE_COMBO_VALUES);
		this.frameSizeField.setInput(inputValues);
		this.frameSizeField.setSelection(new StructuredSelection(inputValues[0])); // select first value (which is current value or default)
		this.frameSizeField.addSelectionChangedListener(new SelectAllTextComboTextListener(this.frameSizeField.getCombo()));
		
		initDataBindings();
	}

	private void initDataBindings() {
		IObservableValue frameSizeWidgetValue = WidgetProperties.selection().observe(this.frameSizeField.getCombo());
		IObservableValue frameSizeModelValue = PojoProperties.value(PlotNxmBlockSettings.PROP_FRAME_SIZE).observe(this.settings);
		UpdateValueStrategy frameSizeModelToTarget = new UpdateValueStrategy();
		frameSizeModelToTarget.setConverter(new ObjectsToNullConverterWrapper(NumberToStringConverter.fromInteger(false))); // wrap so that null converts to null
		Binding bindingValue = dataBindingCtx.bindValue(frameSizeWidgetValue, frameSizeModelValue, createTargetToModelForFrameSize(), frameSizeModelToTarget);
		ControlDecorationSupport.create(bindingValue, SWT.TOP | SWT.LEFT);
	}

	private UpdateValueStrategy createTargetToModelForFrameSize() {
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		updateValueStrategy.setConverter(new ObjectsToNullConverterWrapper(StringToNumberConverter.toInteger(false), true, true, VALUE_USE_DEFAULT));
		updateValueStrategy.setAfterConvertValidator(new NumberRangeValidator<Integer>("Frame size", Integer.class, 0, false));

		return updateValueStrategy;
	}
}

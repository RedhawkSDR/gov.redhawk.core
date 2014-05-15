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
import org.eclipse.swt.widgets.Text;

/**
 * Adjust/override BULKIO NXM block settings user interface/entry control widgets.
 * @NonNullByDefault
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class BulkIONxmBlockControls {

	private static final String VALUE_USE_DEFAULT = "default";
	private static final String SAMPLE_RATE_FIELD_NAME = "Sample Rate";
	private static final String PIPE_SIZE_FIELD_NAME = "Pipe Size";

	private final BulkIONxmBlockSettings settings;
	private final DataBindingContext dataBindingCtx;

	// widgets
	private Text connectionIDField;
	private ComboViewer sampleRateField;
	private Text pipeSizeField;
	private Button blockingField;
	private Button removeOnEOSButton;

	public BulkIONxmBlockControls(BulkIONxmBlockSettings settings, DataBindingContext dataBindingCtx) {
		this.settings = settings;
		this.dataBindingCtx = dataBindingCtx;
	}

	public void createControls(final Composite container) {
		container.setLayout(new GridLayout(2, false));
		Label label;

		// === connection ID ==
		label = new Label(container, SWT.None);
		label.setText("Connection ID:");
		connectionIDField = new Text(container, SWT.BORDER);
		connectionIDField.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		connectionIDField.setToolTipText("Custom Port connection ID to use vs a generated one.");

		if (this.settings.getConnectionID() != null && !this.settings.getConnectionID().isEmpty()) {
			connectionIDField.setEditable(false); // cannot change custom connection ID after it has been set at this time
			connectionIDField.setEnabled(false);
		}

		// === sample rate ===
		label = new Label(container, SWT.NONE);
		label.setText(BulkIONxmBlockControls.SAMPLE_RATE_FIELD_NAME + ":");
		this.sampleRateField = new ComboViewer(container, SWT.BORDER); // writable
		this.sampleRateField.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		this.sampleRateField.getCombo().setToolTipText("Custom sample rate to override value in StreamSRI. Use default or 0 to use value from StreamSRI.");
		this.sampleRateField.setContentProvider(ArrayContentProvider.getInstance()); // ArrayContentProvider does not store any state, therefore can re-use instances
		this.sampleRateField.setLabelProvider(new LabelProvider());
		Object[] inputValues = ArrayUtil.copyAndPrependIfNotNull(this.settings.getSampleRate(), BulkIONxmBlockControls.VALUE_USE_DEFAULT);
		this.sampleRateField.setInput(inputValues);
		this.sampleRateField.setSelection(new StructuredSelection(inputValues[0])); // select first value (which is current value or default)

		/**
				// === pipe size ==
				label = new Label(container, SWT.None);
				label.setText("Pipe Size:");
				pipeSizeField = new Text(container, SWT.BORDER);
				pipeSizeField.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
				pipeSizeField.setToolTipText("Custom pipe (buffer) size vs default.");
		 */

		// === blocking option ===
		this.blockingField = new Button(container, SWT.CHECK);
		this.blockingField.setText("Blocking");
		this.blockingField.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());
		this.blockingField.setToolTipText("On/checked to block pushPacket when Plot is not able to keep up; Off to drop packets in this scenario.");

		// === remove source from plot on end-of-stream (EOS) ===
		this.removeOnEOSButton = new Button(container, SWT.CHECK);
		this.removeOnEOSButton.setText("Remove Stream from Plot on End Of Stream");
		this.removeOnEOSButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		this.removeOnEOSButton.setToolTipText("On/checked to remove streams from plot when an end-of-stream is received in pushPacket.");

		initDataBindings();
	}

	private void initDataBindings() {
		Binding bindingValue;

		IObservableValue connIdWidgetValue = WidgetProperties.text(SWT.Modify).observe(connectionIDField);
		IObservableValue connIdModelValue = PojoProperties.value(BulkIONxmBlockSettings.PROP_CONNECTION_ID).observe(settings);
		bindingValue = dataBindingCtx.bindValue(connIdWidgetValue, connIdModelValue);
		ControlDecorationSupport.create(bindingValue, SWT.TOP | SWT.LEFT);

		IObservableValue srWidgetValue = WidgetProperties.selection().observe(sampleRateField.getCombo());
		IObservableValue srModelValue = PojoProperties.value(BulkIONxmBlockSettings.PROP_SAMPLE_RATE).observe(settings);
		UpdateValueStrategy srTargetToModel = new UpdateValueStrategy();
		srTargetToModel.setAfterGetValidator(new StringToIntegerValidator(BulkIONxmBlockControls.SAMPLE_RATE_FIELD_NAME,
			BulkIONxmBlockControls.VALUE_USE_DEFAULT));
		srTargetToModel.setConverter(new ObjectToNullConverter(StringToNumberConverter.toInteger(false), true, true, BulkIONxmBlockControls.VALUE_USE_DEFAULT));
		srTargetToModel.setAfterConvertValidator(new NumberRangeValidator<Integer>(BulkIONxmBlockControls.SAMPLE_RATE_FIELD_NAME, Integer.class, 0, true));
		UpdateValueStrategy srModelToTarget = new UpdateValueStrategy();
		srModelToTarget.setConverter(new ObjectToNullConverter()); // converts null to null, otherwise uses toString()
		bindingValue = dataBindingCtx.bindValue(srWidgetValue, srModelValue, srTargetToModel, srModelToTarget);
		ControlDecorationSupport.create(bindingValue, SWT.TOP | SWT.LEFT);

		/**
		IObservableValue psWidgetValue = WidgetProperties.text(SWT.Modify).observe(pipeSizeField);
		IObservableValue psModelValue = PojoProperties.value(BulkIONxmBlockSettings.PROP_PIPE_SIZE).observe(settings);
		UpdateValueStrategy psTargetToModel = new UpdateValueStrategy();
		psTargetToModel.setAfterConvertValidator(new NumberRangeValidator<Integer>(BulkIONxmBlockControls.PIPE_SIZE_FIELD_NAME, Integer.class, 1, true));
		bindingValue = dataBindingCtx.bindValue(psWidgetValue, psModelValue, psTargetToModel, null);
		ControlDecorationSupport.create(bindingValue, SWT.TOP | SWT.LEFT);
		 */

		IObservableValue boWidgetValue = WidgetProperties.selection().observe(blockingField);
		IObservableValue boModelValue = PojoProperties.value(BulkIONxmBlockSettings.PROP_BLOCKING_OPTION).observe(settings);
		dataBindingCtx.bindValue(boWidgetValue, boModelValue);

		IObservableValue removeOnEOSWidgetValue = WidgetProperties.selection().observe(removeOnEOSButton);
		IObservableValue removeOnEOSModelValue = PojoProperties.value(BulkIONxmBlockSettings.PROP_REMOVE_ON_EOS).observe(settings);
		dataBindingCtx.bindValue(removeOnEOSWidgetValue, removeOnEOSModelValue);
	}

}

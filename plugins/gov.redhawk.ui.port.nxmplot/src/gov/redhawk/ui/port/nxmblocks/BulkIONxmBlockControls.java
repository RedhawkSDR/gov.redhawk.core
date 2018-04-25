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

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.WordUtils;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import gov.redhawk.sca.util.ArrayUtil;
import gov.redhawk.ui.port.nxmblocks.BulkIONxmBlockSettings.BlockingOption;
import gov.redhawk.ui.port.nxmplot.PlotActivator;

/**
 * Adjust/override BULKIO NXM block settings user interface/entry control widgets.
 * @NonNullByDefault
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class BulkIONxmBlockControls {

	private static final String VALUE_USE_DEFAULT = "default";
	private static final String SAMPLE_RATE_FIELD_NAME = "Sample Rate";

	private final BulkIONxmBlockSettings settings;
	private final DataBindingContext dataBindingCtx;
	private final Map<String, Boolean> connectionIdToUsage;

	// widgets
	private Text connectionIDTextField;
	private ComboViewer connectionIDComboField;
	private ComboViewer sampleRateField;
	private Button removeOnEOSButton;

	/**
	 * @param settings
	 * @param dataBindingCtx
	 * @param connectionIdToUsage - If the map is not empty, it contains valid connectino IDs mapped to whether the ID
	 * is in use. The user will be presented the IDs in a combo box, and validation will be attached.
	 */
	public BulkIONxmBlockControls(BulkIONxmBlockSettings settings, DataBindingContext dataBindingCtx, Map<String, Boolean> connectionIdToUsage) {
		this.settings = settings;
		this.dataBindingCtx = dataBindingCtx;
		this.connectionIdToUsage = connectionIdToUsage;
	}

	@SuppressWarnings({ "unchecked" })
	public void createControls(final Composite container) {
		container.setLayout(new GridLayout(2, false));
		Label label;

		// === connection ID ==
		label = new Label(container, SWT.None);
		label.setText("Connection ID:");
		if (connectionIdToUsage.isEmpty()) {
			connectionIDTextField = new Text(container, SWT.BORDER);
			connectionIDTextField.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
			connectionIDTextField.setToolTipText("Custom Port connection ID to use vs a generated one.");
			if (this.settings.getConnectionID() != null && !this.settings.getConnectionID().isEmpty()) {
				// Can't change the ID after it has been set
				connectionIDTextField.setEditable(false);
				connectionIDTextField.setEnabled(false);
			}
		} else {
			connectionIDComboField = new ComboViewer(container, SWT.BORDER);
			connectionIDComboField.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
			connectionIDComboField.getCombo().setToolTipText("Available mulit-out port connection IDs");
			connectionIDComboField.setContentProvider(ArrayContentProvider.getInstance());
			connectionIDComboField.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					if (element instanceof Entry) {
						return ((Entry< ? , ? >) element).getKey().toString();
					}
					return super.getText(element);
				}
			});
			connectionIDComboField.setInput(connectionIdToUsage.entrySet());
		}

		// === sample rate ===
		label = new Label(container, SWT.NONE);
		label.setText(BulkIONxmBlockControls.SAMPLE_RATE_FIELD_NAME + ":");
		this.sampleRateField = new ComboViewer(container, SWT.BORDER); // writable
		this.sampleRateField.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		this.sampleRateField.getCombo().setToolTipText("Custom sample rate to override value in StreamSRI. Use default or 0 to use value from StreamSRI.");
		this.sampleRateField.setContentProvider(ArrayContentProvider.getInstance());
		this.sampleRateField.setLabelProvider(new LabelProvider());
		Object[] inputValues = ArrayUtil.copyAndPrependIfNotNull(this.settings.getSampleRate(), BulkIONxmBlockControls.VALUE_USE_DEFAULT);
		this.sampleRateField.setInput(inputValues);
		this.sampleRateField.setSelection(new StructuredSelection(inputValues[0])); // select first value (which is
																					// current value or default)

		// === blocking option ===
		label = new Label(container, SWT.NONE);
		label.setText("Blocking option:");
		Group blockingOptionGroup = new Group(container, SWT.SHADOW_NONE | SWT.NO_TRIM | SWT.NO_BACKGROUND);
		blockingOptionGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		RowLayout rowLayout = new RowLayout(SWT.HORIZONTAL);
		blockingOptionGroup.setLayout(rowLayout);
		// add blocking options into radio group
		BlockingOption[] blockingOptions = BlockingOption.values();
		SelectObservableValue<BlockingOption> radioBtnGroupWidgetValue = new SelectObservableValue<>(BlockingOption.class);
		for (BlockingOption b : blockingOptions) {
			Button button = new Button(blockingOptionGroup, SWT.RADIO);
			button.setText(b.getLabel());
			IObservableValue<Boolean> btnWidgetValue = WidgetProperties.selection().observe(button);
			radioBtnGroupWidgetValue.addOption(b, btnWidgetValue);
		}
		// do data binding here since we need the above radioBtnGroupWidgetValue
		IObservableValue<BlockingOption> blockingModelValue = PojoProperties.value(BulkIONxmBlockSettings.PROP_BLOCKING_OPTION).observe(settings);
		dataBindingCtx.bindValue(radioBtnGroupWidgetValue, blockingModelValue);

		// === remove source from plot on end-of-stream (EOS) ===
		this.removeOnEOSButton = new Button(container, SWT.CHECK);
		this.removeOnEOSButton.setText("Remove Stream from Plot on End Of Stream");
		this.removeOnEOSButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		this.removeOnEOSButton.setToolTipText("On/checked to remove streams from plot when an end-of-stream is received in pushPacket.");

		initDataBindings();
		setConnectionIDDefault();
	}

	@SuppressWarnings({ "unchecked" })
	private void initDataBindings() {
		Binding bindingValue;
		IObservableValue<String> connectionIdModel = PojoProperties.value(BulkIONxmBlockSettings.PROP_CONNECTION_ID).observe(settings);
		if (connectionIDTextField != null) {
			IObservableValue<String> connectionIdTarget = WidgetProperties.text(SWT.Modify).observe(connectionIDTextField);
			bindingValue = dataBindingCtx.bindValue(connectionIdTarget, connectionIdModel);
		} else {
			IObservableValue< ? > connectionIdTarget = WidgetProperties.selection().observe(connectionIDComboField.getCombo());
			UpdateValueStrategy targetToModel = new UpdateValueStrategy();
			targetToModel.setBeforeSetValidator(value -> {
				// Determine if the connection ID is in our list, and if it's already in use or not
				Boolean isValid = connectionIdToUsage.get(value);
				if (isValid != null) {
					if (isValid) {
						return Status.OK_STATUS;
					} else {
						return new Status(Status.ERROR, PlotActivator.PLUGIN_ID, "Selected connection ID is already in use");
					}
				}

				// Warn about using a connection ID that isn't in the connection table
				String text = WordUtils.wrap(gov.redhawk.model.sca.provider.Messages.MultiOutPortManualConnectionIDWarning, 60);
				return new Status(Status.WARNING, PlotActivator.PLUGIN_ID, text);
			});
			bindingValue = dataBindingCtx.bindValue(connectionIdTarget, connectionIdModel, targetToModel, null);
		}
		ControlDecorationSupport.create(bindingValue, SWT.TOP | SWT.LEFT);

		IObservableValue<String> srWidgetValue = WidgetProperties.selection().observe(sampleRateField.getCombo());
		IObservableValue<Integer> srModelValue = PojoProperties.value(BulkIONxmBlockSettings.PROP_SAMPLE_RATE).observe(settings);
		UpdateValueStrategy srTargetToModel = new UpdateValueStrategy();
		srTargetToModel.setAfterGetValidator(
			new StringToIntegerValidator(BulkIONxmBlockControls.SAMPLE_RATE_FIELD_NAME, BulkIONxmBlockControls.VALUE_USE_DEFAULT));
		srTargetToModel.setConverter(new ObjectToNullConverter(StringToNumberConverter.toInteger(false), true, true, BulkIONxmBlockControls.VALUE_USE_DEFAULT));
		srTargetToModel.setAfterConvertValidator(new NumberRangeValidator<Integer>(BulkIONxmBlockControls.SAMPLE_RATE_FIELD_NAME, Integer.class, 0, true));
		UpdateValueStrategy srModelToTarget = new UpdateValueStrategy();
		srModelToTarget.setConverter(new ObjectToNullConverter()); // converts null to null, otherwise uses toString()
		bindingValue = dataBindingCtx.bindValue(srWidgetValue, srModelValue, srTargetToModel, srModelToTarget);
		ControlDecorationSupport.create(bindingValue, SWT.TOP | SWT.LEFT);

		IObservableValue<Boolean> removeOnEOSWidgetValue = WidgetProperties.selection().observe(removeOnEOSButton);
		IObservableValue<Boolean> removeOnEOSModelValue = PojoProperties.value(BulkIONxmBlockSettings.PROP_REMOVE_ON_EOS).observe(settings);
		dataBindingCtx.bindValue(removeOnEOSWidgetValue, removeOnEOSModelValue);
	}

	private void setConnectionIDDefault() {
		if (connectionIDComboField == null) {
			return;
		}
		for (Entry<String, Boolean> entry : connectionIdToUsage.entrySet()) {
			if (entry.getValue()) {
				connectionIDComboField.setSelection(new StructuredSelection(entry));
				return;
			}
		}
		// If we get here, then all the connections are in use, so don't have an entry
		connectionIDComboField.getCombo().setText("");
	}

}

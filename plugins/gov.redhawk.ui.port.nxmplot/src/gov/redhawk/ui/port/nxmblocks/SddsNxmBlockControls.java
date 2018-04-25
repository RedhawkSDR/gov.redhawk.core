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

import java.nio.ByteOrder;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.WordUtils;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.Status;
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

import gov.redhawk.ui.port.nxmplot.PlotActivator;

/**
 * Adjust/override SDDS NXM block settings user entry dialog.
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class SddsNxmBlockControls {

	private final SddsNxmBlockSettings settings;
	private final DataBindingContext dataBindingCtx;
	private final Map<String, Boolean> connectionIdToUsage;

	// widgets
	private Text connectionIDTextField;
	private ComboViewer connectionIDComboField;
	private ComboViewer dataByteOrderField;

	/**
	 * @param settings
	 * @param dataBindingCtx
	 * @param connectionIdToUsage - If the map is not empty, it contains valid connectino IDs mapped to whether the ID
	 * is in use. The user will be presented the IDs in a combo box, and validation will be attached.
	 */
	public SddsNxmBlockControls(SddsNxmBlockSettings settings, DataBindingContext dataBindingCtx, Map<String, Boolean> connectionIdToUsage) {
		this.settings = settings;
		this.dataBindingCtx = dataBindingCtx;
		this.connectionIdToUsage = connectionIdToUsage;
	}

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

		// === data byte order ===
		label = new Label(container, SWT.NONE);
		label.setText("Data Byte Order:");
		dataByteOrderField = new ComboViewer(container, SWT.READ_ONLY);
		dataByteOrderField.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		dataByteOrderField.getCombo().setToolTipText("Custom data byte order to override value in SDDS packets.");
		dataByteOrderField.setContentProvider(ArrayContentProvider.getInstance());
		dataByteOrderField.setLabelProvider(new LabelProvider());
		dataByteOrderField.setInput(new ByteOrder[] { ByteOrder.BIG_ENDIAN, ByteOrder.LITTLE_ENDIAN });

		addDataBindings();
		setConnectionIDDefault();
	}

	@SuppressWarnings("unchecked")
	protected void addDataBindings() {
		Binding bindingValue;
		IObservableValue<String> connectionIdModel = PojoProperties.value(SddsNxmBlockSettings.PROP_CONNECTION_ID).observe(settings);
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

		IObservableValue<ByteOrder> byteOrderTarget = ViewerProperties.singleSelection().observe(this.dataByteOrderField);
		IObservableValue<ByteOrder> byteOrderModel = PojoProperties.value(SddsNxmBlockSettings.PROP_DATA_BYTE_ORDER).observe(this.settings);
		dataBindingCtx.bindValue(byteOrderTarget, byteOrderModel);
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

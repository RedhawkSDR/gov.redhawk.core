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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Adjust/override SDDS NXM block settings user entry dialog.
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class SddsNxmBlockControls {

	private final SddsNxmBlockSettings settings;
	private final DataBindingContext dataBindingCtx;
	
	// widgets
	private ComboViewer dataByteOrderField;

	public SddsNxmBlockControls(SddsNxmBlockSettings settings, DataBindingContext dataBindingCtx) {
		this.settings = settings;
		this.dataBindingCtx = dataBindingCtx;
	}

	public void createControls(final Composite container) {
		container.setLayout(new GridLayout(2, false));
		Label label;

		// === data byte order ===
		label = new Label(container, SWT.NONE);
		label.setText("Data Byte Order:");
		this.dataByteOrderField = new ComboViewer(container, SWT.READ_ONLY);
		this.dataByteOrderField.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true,  false).create());
		this.dataByteOrderField.getCombo().setToolTipText("Custom data byte order to override value in SDDS packets.");
		this.dataByteOrderField.setContentProvider(ArrayContentProvider.getInstance()); // ArrayContentProvider does not store any state, therefore can re-use instances
		this.dataByteOrderField.setLabelProvider(new LabelProvider());
		this.dataByteOrderField.setInput(new ByteOrder[] { ByteOrder.BIG_ENDIAN, ByteOrder.LITTLE_ENDIAN});
		
		addDataBindings();
	}

	protected void addDataBindings() {
		IObservableValue boTargetObservableValue = ViewerProperties.singleSelection().observe(this.dataByteOrderField);
		IObservableValue boModelObservableValue = PojoProperties.value(SddsNxmBlockSettings.PROP_DATA_BYTE_ORDER).observe(this.settings);
		dataBindingCtx.bindValue(boTargetObservableValue, boModelObservableValue);
	}

}

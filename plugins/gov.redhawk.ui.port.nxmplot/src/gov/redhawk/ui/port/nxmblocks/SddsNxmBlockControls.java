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
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Adjust/override BULKIO SDDS NXM block settings user entry dialog.
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class SddsNxmBlockControls extends Composite {

	private DataBindingContext dataBindingCtx;
	private final SddsNxmBlockSettings settings;
	private ComboViewer dataByteOrderField;

	/**
	 * Instantiates a new entry dialog.
	 *
	 * @param parentShell the parent shell
	 */
	public SddsNxmBlockControls(Composite parent, int style, SddsNxmBlockSettings settings, DataBindingContext dataBindingCtx) {
		super(parent, style);
		this.settings = settings;
		this.dataBindingCtx = dataBindingCtx;
		createControls(this);
		addBindings();
	}

	protected void createControls(final Composite container) {
		final GridLayout gridLayout = new GridLayout(2, false);
		container.setLayout(gridLayout);

		// === data byte order ===
		final Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		label.setText("Data Byte Order:");
		this.dataByteOrderField = new ComboViewer(container, SWT.READ_ONLY);
		this.dataByteOrderField.getCombo().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		this.dataByteOrderField.setContentProvider(new ArrayContentProvider());
		this.dataByteOrderField.setLabelProvider(new LabelProvider());
		this.dataByteOrderField.setInput(new ByteOrder[] { ByteOrder.BIG_ENDIAN, ByteOrder.LITTLE_ENDIAN});
	}

	protected void addBindings() {
		IObservableValue boTargetObservableValue = WidgetProperties.text(SWT.Modify).observeDelayed(200, this.dataByteOrderField.getCombo());
		IObservableValue boModelObservableValue = BeansObservables.observeValue(settings, "dataByteOrder");
		dataBindingCtx.bindValue(boTargetObservableValue, boModelObservableValue, null, null);
	}

}

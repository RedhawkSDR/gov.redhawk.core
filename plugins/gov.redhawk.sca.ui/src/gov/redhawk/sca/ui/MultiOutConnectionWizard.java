/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.sca.ui;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * @since 10.2
 * 
 * Creates a dialog that allows for selection of predefined connection IDs
 */
public class MultiOutConnectionWizard extends Dialog {
	private Map<String, Boolean> connectionIds;
	private WritableValue<String> selectedId = new WritableValue<>();

	public MultiOutConnectionWizard(Shell parentShell, Map<String, Boolean> connectionIds) {
		super(parentShell);
		this.connectionIds = connectionIds;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Multi-out port connection wizard");

	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(2, false));

		// First check to see if there are "any" available connections
		boolean availableConnections = false;
		for (Boolean isAvailable : connectionIds.values()) {
			if (isAvailable) {
				availableConnections = true;
				break;
			}
		}

		if (availableConnections) {
			createMessageArea(container);
			createRadioComposite(container);
		} else {
			createNoConnectionsMessageArea(container);
		}
		return container;
	}

	private void createNoConnectionsMessageArea(Composite parent) {
		final Image image = parent.getDisplay().getSystemImage(SWT.ICON_ERROR);
		final Label imageLabel = new Label(parent, SWT.NONE);
		imageLabel.setImage(image);
		imageLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

		final Label msg = new Label(parent, SWT.NONE);
		msg.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		msg.setText("Multi-out port selection: All available connections IDs are already in use");
	}

	private void createMessageArea(Composite parent) {
		final Image image = parent.getDisplay().getSystemImage(SWT.ICON_WARNING);
		final Label imageLabel = new Label(parent, SWT.NONE);
		imageLabel.setImage(image);
		imageLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

		final Label msg = new Label(parent, SWT.NONE);
		msg.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		msg.setText("Attempting to perform a connection operation on a multi-out port with multiple data streams.\n"
			+ "Select one of the provided allocation IDs to use as the connection ID.");
	}

	@SuppressWarnings("unchecked")
	private void createRadioComposite(Composite container) {
		Composite radioComposite = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginLeft = 50;
		radioComposite.setLayout(layout);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1);
		data.widthHint = 400;
		radioComposite.setLayoutData(data);
		boolean connectionsInUse = false;

		SelectObservableValue<String> selectedRadioBtnObservable = new SelectObservableValue<String>();
		for (Entry<String, Boolean> entry : connectionIds.entrySet()) {
			Button radioButton = new Button(radioComposite, SWT.RADIO);
			radioButton.setText(entry.getKey());
			selectedRadioBtnObservable.addOption(entry.getKey(), WidgetProperties.selection().observe(radioButton));
			if (!entry.getValue()) {
				radioButton.setText(entry.getKey() + " (IN USE)");
				radioButton.setEnabled(false);
				connectionsInUse = true;
			}
		}

		if (connectionsInUse) {
			radioComposite.setToolTipText("Some connection IDs are already in use and are not available for this operation");
		}

		DataBindingContext dbc = new DataBindingContext();
		dbc.bindValue(selectedRadioBtnObservable, PojoProperties.value(this.getClass(), "selectedId").observe(this));
	}

	public String getSelectedId() {
		return this.selectedId.getValue();
	}

	public void setSelectedId(String newId) {
		this.selectedId.setValue(newId);
	}

}

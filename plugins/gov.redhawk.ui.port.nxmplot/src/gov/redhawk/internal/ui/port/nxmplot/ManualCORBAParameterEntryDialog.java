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
package gov.redhawk.internal.ui.port.nxmplot;

import gov.redhawk.ui.port.nxmplot.CorbaConnectionSettings;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * The Class ManualCORBAParameterEntryDialog.
 */
public class ManualCORBAParameterEntryDialog extends Dialog {

	/** The plot description. */
	private final CorbaConnectionSettings connectionSettings;
	private Text hostField;
	private Text portField;
	private Text resourceField;
	private Text portNameField;
	private Text formatField;

	/**
	 * Instantiates a new manual stream parameter entry dialog.
	 * 
	 * @param parentShell the parent shell
	 */
	public ManualCORBAParameterEntryDialog(final Shell parentShell, final CorbaConnectionSettings settings) {
		super(parentShell);
		this.connectionSettings = settings;
	}

	@Override
	protected void configureShell(final Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Connection Settings");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createDialogArea(final Composite parent) {
		final Composite container = (Composite) super.createDialogArea(parent);
		final GridLayout gridLayout = new GridLayout(4, false);
		container.setLayout(gridLayout);

		final Label hostLabel = new Label(container, SWT.NONE);
		hostLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		hostLabel.setText("Host:");

		this.hostField = new Text(container, SWT.BORDER);
		this.hostField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		this.hostField.setText(this.connectionSettings.getHost());
		this.hostField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				ManualCORBAParameterEntryDialog.this.connectionSettings.setHost(ManualCORBAParameterEntryDialog.this.hostField.getText());
			}
		});

		final Label portLabel = new Label(container, SWT.NONE);
		portLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		portLabel.setText("Port:");

		this.portField = new Text(container, SWT.BORDER);
		this.portField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		this.portField.setText(Integer.toString(this.connectionSettings.getPort()));
		this.portField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				try {
					final int port = Integer.parseInt(ManualCORBAParameterEntryDialog.this.portField.getText());
					ManualCORBAParameterEntryDialog.this.connectionSettings.setPort(port);
				} catch (final NumberFormatException n) {
					ManualCORBAParameterEntryDialog.this.connectionSettings.setPort(0);
				}
			}
		});

		final Label resourceLabel = new Label(container, SWT.NONE);
		resourceLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		resourceLabel.setText("Resource:");

		this.resourceField = new Text(container, SWT.BORDER);
		this.resourceField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 3, 1)); // SUPPRESS CHECKSTYLE MagicNumber
		this.resourceField.setText(this.connectionSettings.getResource());
		this.resourceField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				ManualCORBAParameterEntryDialog.this.connectionSettings.setResource(ManualCORBAParameterEntryDialog.this.resourceField.getText());
			}
		});

		final Label portNameLabel = new Label(container, SWT.NONE);
		portNameLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		portNameLabel.setText("Port Name:");

		this.portNameField = new Text(container, SWT.BORDER);
		this.portNameField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 3, 1)); // SUPPRESS CHECKSTYLE MagicNumber
		this.portNameField.setText(this.connectionSettings.getPortName());
		this.portNameField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				ManualCORBAParameterEntryDialog.this.connectionSettings.setPortName(ManualCORBAParameterEntryDialog.this.portNameField.getText());
			}
		});

		final Label formatLabel = new Label(container, SWT.NONE);
		formatLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		formatLabel.setText("Incoming IDL:");

		this.formatField = new Text(container, SWT.BORDER);
		this.formatField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		String idl = this.connectionSettings.getIDL();
		if (idl == null) {
			idl = "";
		}
		this.formatField.setText(idl);
		this.formatField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				ManualCORBAParameterEntryDialog.this.connectionSettings.setIDL(ManualCORBAParameterEntryDialog.this.formatField.getText());
			}
		});

		final Button button = new Button(container, SWT.CHECK);
		button.setText("2D Plot");
		button.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 2, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				ManualCORBAParameterEntryDialog.this.connectionSettings.set2D(button.getSelection());
			}
		});

		return container;
	}

	/**
	 * Gets the plot description created by this dialog.
	 * 
	 * @return the plot description
	 */
	public CorbaConnectionSettings getConnectionSettings() {
		this.connectionSettings.updatePortString();
		return this.connectionSettings;
	}

}

/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package gov.redhawk.ui.wizards;

import gov.redhawk.internal.ui.HelpContextIds;
import gov.redhawk.ui.RedhawkUiActivator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SelectionStatusDialog;

/**
 * The Class RenameDialog.
 * 
 * @since 2.0
 */
public class RenameDialog extends SelectionStatusDialog {
	private List<String> oldNames;
	private String oldName;
	private String newName;
	private Text renameText;
	private IStatus status;
	private final boolean isCaseSensitive;
	private IInputValidator fValidator;

	/**
	 * Create a new rename dialog instance for the given window.
	 * 
	 * @param shell The parent of the dialog
	 * @param oldName Current name of the item being renamed
	 */
	public RenameDialog(final Shell shell, final String oldName) {
		super(shell);
		this.isCaseSensitive = false;
		initialize();
		setOldName(oldName);
	}

	/**
	 * Create a new rename dialog instance for the given window.
	 * 
	 * @param shell The parent of the dialog
	 * @param isCaseSensitive Flags whether dialog will perform case sensitive
	 *            checks against old names
	 * @param names Set of names which the user should not be allowed to rename
	 *            to
	 * @param oldName Current name of the item being renamed
	 */
	public RenameDialog(final Shell shell, final boolean isCaseSensitive, final String[] names, final String oldName) {
		super(shell);
		this.isCaseSensitive = isCaseSensitive;
		initialize();
		if (names != null) {
			for (int i = 0; i < names.length; i++) {
				addOldName(names[i]);
			}
		}
		setOldName(oldName);
	}

	/**
	 * Initialize.
	 */
	public void initialize() {
		this.oldNames = new ArrayList<String>();
		setStatusLineAboveButtons(true);
	}

	/**
	 * Adds the old name.
	 * 
	 * @param oldName the old name
	 */
	public void addOldName(final String oldName) { // SUPPRESS CHECKSTYLE HiddenField
		if (!this.oldNames.contains(oldName)) {
			this.oldNames.add(oldName);
		}

	}

	/**
	 * Sets the old name.
	 * 
	 * @param oldName the new old name
	 */
	public void setOldName(final String oldName) {
		this.oldName = oldName;
		addOldName(oldName);
		if (this.renameText != null) {
			this.renameText.setText(oldName);
		}
		this.newName = oldName;
	}

	/*
	 * @see org.eclipse.jface.window.Window#configureShell(Shell)
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configureShell(final Shell shell) {
		super.configureShell(shell);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(shell, HelpContextIds.RENAME_DIALOG);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createDialogArea(final Composite parent) {
		final Composite container = new Composite(parent, SWT.NULL);
		final GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 9; // SUPPRESS CHECKSTYLE MagicNumber
		layout.marginHeight = layout.marginWidth;
		container.setLayout(layout);

		GridData gd = new GridData(GridData.FILL_BOTH);
		container.setLayoutData(gd);

		final Label label = new Label(container, SWT.NULL);
		label.setText("&Enter new name:");

		this.renameText = new Text(container, SWT.SINGLE | SWT.BORDER);
		this.renameText.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent e) {
				textChanged(RenameDialog.this.renameText.getText());
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		this.renameText.setLayoutData(gd);
		Dialog.applyDialogFont(container);
		return container;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int open() {
		this.renameText.setText(this.oldName);
		this.renameText.selectAll();
		final Button okButton = getButton(IDialogConstants.OK_ID);

		this.status = new Status(IStatus.OK, RedhawkUiActivator.getPluginId(), IStatus.OK, "", //$NON-NLS-1$
		        null);
		updateStatus(this.status);
		okButton.setEnabled(false);
		return super.open();
	}

	/**
	 * Text changed.
	 * 
	 * @param text the text
	 */
	private void textChanged(final String text) {
		final Button okButton = getButton(IDialogConstants.OK_ID);
		if (this.fValidator != null) {
			final String message = this.fValidator.isValid(text);
			if (message != null) {
				this.status = new Status(IStatus.ERROR, RedhawkUiActivator.getPluginId(), IStatus.ERROR, message, null);
				updateStatus(this.status);
				okButton.setEnabled(false);
				return;
			}
		}
		for (int i = 0; i < this.oldNames.size(); i++) {
			if ((this.isCaseSensitive && text.equals(this.oldNames.get(i)))
			        || (!this.isCaseSensitive && text.equalsIgnoreCase(this.oldNames.get(i).toString()))) {
				this.status = new Status(IStatus.ERROR, RedhawkUiActivator.getPluginId(), IStatus.ERROR, "Name already exists.", null);
				updateStatus(this.status);
				okButton.setEnabled(false);
				break;
			}
			okButton.setEnabled(true);
			this.status = new Status(IStatus.OK, RedhawkUiActivator.getPluginId(), IStatus.OK, "", //$NON-NLS-1$
			        null);
			updateStatus(this.status);
		}
	}

	/**
	 * Gets the new name.
	 * 
	 * @return the new name
	 */
	public String getNewName() {
		return this.newName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void okPressed() {
		this.newName = this.renameText.getText().trim();
		super.okPressed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.dialogs.SelectionStatusDialog#computeResult()
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void computeResult() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTitle(final String title) {
		getShell().setText(title);
	}

	/**
	 * Sets the input validator.
	 * 
	 * @param validator the new input validator
	 */
	public void setInputValidator(final IInputValidator validator) {
		this.fValidator = validator;
	}
}

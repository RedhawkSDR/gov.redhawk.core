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
package gov.redhawk.logging.ui.dialogs;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import gov.redhawk.logging.ui.LogLevels;

public class SetLogLevelDialog extends TitleAreaDialog {

	private int currentLogLevel;
	private int desiredLogLevel;
	private ComboViewer newLogLevelCombo;

	public SetLogLevelDialog(Shell parentShell, int currentLogLevel) {
		super(parentShell);
		this.currentLogLevel = currentLogLevel;
	}

	@Override
	public void create() {
		super.create();
		setTitle("Set Debug Level");
		setMessage("Select a new logging level for the resource from the drop down or select cancel to use the existing.");
		setDialogHelpAvailable(false);
		setHelpAvailable(false);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Set Debug Level");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogArea = (Composite) super.createDialogArea(parent);

		Composite container = new Composite(dialogArea, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		Label currentLogLevelLabel = new Label(container, SWT.READ_ONLY);
		currentLogLevelLabel.setText("Current Log Level: ");
		Label currentLogLevelValue = new Label(container, SWT.READ_ONLY);
		currentLogLevelValue.setText(LogLevels.intToLogLevel(currentLogLevel).getLabel());

		Label newLogLevelLabel = new Label(container, SWT.READ_ONLY);
		newLogLevelLabel.setText("New Log Level: ");
		newLogLevelCombo = new ComboViewer(container, SWT.READ_ONLY | SWT.BORDER);
		newLogLevelCombo.setContentProvider(new ArrayContentProvider());
		newLogLevelCombo.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(final Object element) {
				final LogLevels logLevel = (LogLevels) element;
				return logLevel.getLabel();
			}
		});

		newLogLevelCombo.setInput(LogLevels.values());
		newLogLevelCombo.setSelection(new StructuredSelection(LogLevels.intToLogLevel(currentLogLevel)));

		return dialogArea;
	}

	@Override
	protected void okPressed() {
		LogLevels logLevel = (LogLevels) ((IStructuredSelection) newLogLevelCombo.getSelection()).getFirstElement();
		desiredLogLevel = logLevel.getLevel();
		super.okPressed();
	}

	public int getDesiredLogLevel() {
		return desiredLogLevel;
	}

}

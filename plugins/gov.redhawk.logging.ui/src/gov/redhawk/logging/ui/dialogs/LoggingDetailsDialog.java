/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package gov.redhawk.logging.ui.dialogs;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import gov.redhawk.logging.ui.LogLevels;

public class LoggingDetailsDialog extends TitleAreaDialog {

	private String logger = ""; //$NON-NLS-1$
	private LogLevels logLevel = LogLevels.INFO;

	public LoggingDetailsDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
		setTitle(Messages.LoggingDetailsDialog_1);
		setMessage(Messages.LoggingDetailsDialog_2);
		setDialogHelpAvailable(false);
		setHelpAvailable(false);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogArea = (Composite) super.createDialogArea(parent);

		Composite container = new Composite(dialogArea, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		GridDataFactory fillHorzGrid = GridDataFactory.fillDefaults().grab(true, false);

		Label levelLabel = new Label(container, SWT.NONE);
		levelLabel.setText(Messages.LoggingDetailsDialog_3);
		Combo levelCombo = new Combo(container, SWT.NONE);
		fillHorzGrid.applyTo(levelCombo);

		Label loggerLabel = new Label(container, SWT.NONE);
		loggerLabel.setText(Messages.LoggingDetailsDialog_4);
		Text loggerText = new Text(container, SWT.NONE);
		loggerText.setMessage(Messages.LoggingDetailsDialog_5);
		fillHorzGrid.applyTo(loggerText);

		ComboViewer levelViewer = new ComboViewer(levelCombo);
		levelViewer.setContentProvider(new ArrayContentProvider());
		levelViewer.setInput(LogLevels.values());
		levelViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((LogLevels) element).getLabel();
			}
		});

		DataBindingContext dbc = new DataBindingContext();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		IObservableValue logLevelModelObservable = PojoProperties.value("logLevel").observe(this);
		dbc.bindValue(ViewerProperties.singleSelection().observe(levelViewer), logLevelModelObservable); //$NON-NLS-1$
		@SuppressWarnings({ "rawtypes", "unchecked" })
		IObservableValue loggerModelObservable = PojoProperties.value("logger").observe(this);
		dbc.bindValue(WidgetProperties.text(SWT.Modify).observe(loggerText), loggerModelObservable); //$NON-NLS-1$

		return parent;
	}

	public String getLogger() {
		return logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}

	public LogLevels getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevels logLevel) {
		this.logLevel = logLevel;
	}
}

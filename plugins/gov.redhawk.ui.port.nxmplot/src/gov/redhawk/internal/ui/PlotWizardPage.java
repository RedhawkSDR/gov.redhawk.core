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
package gov.redhawk.internal.ui;

import gov.redhawk.ui.port.nxmplot.PlotType;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * 
 */
public class PlotWizardPage extends WizardPage {
	private PlotWizardSettings settings = new PlotWizardSettings();
	private DataBindingContext dataBindingContext = new DataBindingContext();

	protected PlotWizardPage(String pageName, String title, ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		setDescription("Provide the initial settings for the new plot.");
	}

	@Override
	public void createControl(Composite root) {
		Composite parent = new Composite(root, SWT.None);
		parent.setLayout(new GridLayout(2, false));
		
		Label label;
		Text text;
		
		label = new Label(parent, SWT.None);
		label.setText("Plot Type:");
		ComboViewer viewer = new ComboViewer(parent, SWT.None);
		viewer.setLabelProvider(new LabelProvider());
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setInput(PlotType.values());
		viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		dataBindingContext.bindValue(ViewerProperties.singleSelection().observe(viewer), PojoProperties.value(PlotWizardSettings.PROP_TYPE).observe(settings));
		
		
		label = new Label(parent, SWT.None);
		Button button = new Button(parent, SWT.CHECK);
		button.setText("Take FFT");
		button.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		dataBindingContext.bindValue(WidgetProperties.selection().observe(button), PojoProperties.value(PlotWizardSettings.PROP_FFT).observe(settings));
		
		label = new Label(parent, SWT.None);
		label.setText("Connection ID:");
		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		dataBindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(text), PojoProperties.value(PlotWizardSettings.PROP_CONNECTION_ID).observe(settings));
		
		WizardPageSupport.create(this, dataBindingContext);
		
		setControl(parent);
	}

	public PlotWizardSettings getPlotSettings() {
		return settings;
	}

}

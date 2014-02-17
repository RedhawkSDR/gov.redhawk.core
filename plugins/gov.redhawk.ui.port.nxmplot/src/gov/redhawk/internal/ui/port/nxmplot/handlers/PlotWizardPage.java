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
package gov.redhawk.internal.ui.port.nxmplot.handlers;

import gov.redhawk.ui.port.nxmblocks.BulkIONxmBlockControls;
import gov.redhawk.ui.port.nxmblocks.BulkIONxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockControls;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.PlotNxmBlockControls;
import gov.redhawk.ui.port.nxmblocks.PlotNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.SddsNxmBlockControls;
import gov.redhawk.ui.port.nxmblocks.SddsNxmBlockSettings;
import gov.redhawk.ui.port.nxmplot.PlotType;

import java.util.Arrays;
import java.util.List;

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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

/**
 * @noreference This class is not intended to be referenced by clients.
 * @since 4.4
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
		Group group;

		// === plot type ===
		label = new Label(parent, SWT.None);
		label.setText("Plot Type:");
		ComboViewer viewer = new ComboViewer(parent, SWT.READ_ONLY);
		viewer.setLabelProvider(new LabelProvider());
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setInput(PlotType.getStandardPlotTypes());
		viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		dataBindingContext.bindValue(ViewerProperties.singleSelection().observe(viewer),
			PojoProperties.value(PlotWizardSettings.PROP_PLOT_TYPE).observe(settings));

		// == PLOT Block settings (e.g. frame size) ==
		PlotNxmBlockSettings plotSettings = settings.getPlotBlockSettings();
		if (plotSettings != null) {
			group = new Group(parent, SWT.None);
			group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
			group.setText("PLOT");
			new PlotNxmBlockControls(plotSettings, dataBindingContext).createControls(group);
		}

		// === BULKIO settings ===
		BulkIONxmBlockSettings bulkioSettings = settings.getBulkIOBlockSettings();
		if (bulkioSettings != null) {
			group = new Group(parent, SWT.None);
			group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
			group.setText("BULKIO");
			new BulkIONxmBlockControls(bulkioSettings, dataBindingContext).createControls(group);
		}

		// == BULKIO SDDS settings ===
		SddsNxmBlockSettings sddsSettings = settings.getSddsBlockSettings();
		if (sddsSettings != null) {
			group = new Group(parent, SWT.None);
			group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
			group.setText("BULKIO SDDS");
			new SddsNxmBlockControls(sddsSettings, dataBindingContext).createControls(group);
		}

		// == FFT settings ==

		final Group fftGroup = new Group(parent, SWT.None);

		final Button button = new Button(fftGroup, SWT.CHECK);
		button.setText("Take FFT");
		button.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		dataBindingContext.bindValue(WidgetProperties.selection().observe(button), PojoProperties.value(PlotWizardSettings.PROP_DO_FFT).observe(settings));
		FftNxmBlockSettings fftSettings = settings.getFftBlockSettings();
		if (fftSettings == null) {
			fftSettings = new FftNxmBlockSettings();
			settings.setFftBlockSettings(fftSettings);
		}

		fftGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		fftGroup.setText("FFT");

		final List<Control> skip = Arrays.asList(fftGroup, button);

		new FftNxmBlockControls(fftSettings, dataBindingContext).createControls(fftGroup);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setFftEnabled(fftGroup, button.getSelection(), skip);
			}
		});
		setFftEnabled(fftGroup, button.getSelection(), skip);

		WizardPageSupport.create(this, dataBindingContext);

		setControl(parent);
	}

	private void setFftEnabled(Composite control, boolean selection, List<Control> skip) {
		for (Control c : control.getChildren()) {
			if (c instanceof Composite) {
				setFftEnabled((Composite) c, selection, skip);
			}
			if (!skip.contains(c)) {
				c.setEnabled(selection);
			}
		}
	}

	public PlotWizardSettings getPlotSettings() {
		return settings;
	}

}

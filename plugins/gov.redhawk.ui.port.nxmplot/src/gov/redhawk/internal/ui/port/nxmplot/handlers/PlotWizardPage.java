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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import gov.redhawk.ui.port.nxmblocks.BulkIONxmBlockControls;
import gov.redhawk.ui.port.nxmblocks.BulkIONxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockControls;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.PlotNxmBlockControls;
import gov.redhawk.ui.port.nxmblocks.PlotNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.SddsNxmBlockControls;
import gov.redhawk.ui.port.nxmblocks.SddsNxmBlockSettings;
import gov.redhawk.ui.port.nxmplot.PlotSettings;
import gov.redhawk.ui.port.nxmplot.PlotSettings.PlotMode;
import gov.redhawk.ui.port.nxmplot.PlotType;

/**
 * @noreference This class is not intended to be referenced by clients.
 * @since 4.4
 */
public class PlotWizardPage extends WizardPage {
	private BulkIONxmBlockSettings bulkIOBlockSettings = null;
	private SddsNxmBlockSettings sddsBlockSettings = null;
	private FftNxmBlockSettings fftBlockSettings = new FftNxmBlockSettings();
	private PlotNxmBlockSettings plotBlockSettings = new PlotNxmBlockSettings();
	private boolean fft;
	private PlotSettings plotSettings = new PlotSettings();
	private Map<String, Boolean> connectionIds = new HashMap<>();

	private DataBindingContext dataBindingContext = new DataBindingContext();

	protected PlotWizardPage(String pageName, String title, ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		setDescription("Provide the initial settings for the new plot.");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createControl(Composite root) {
		Composite parent = new Composite(root, SWT.None);
		parent.setLayout(new GridLayout(2, false));

		Label label;
		Group group;

		// == PLOT Block settings (e.g. plot type, plot mode, frame size, etc.) ==
		group = new Group(parent, SWT.None);
		group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		group.setText("PLOT");
		label = new Label(group, SWT.None);
		label.setText("&Type:");
		ComboViewer viewer = new ComboViewer(group, SWT.READ_ONLY);
		viewer.setLabelProvider(new LabelProvider());
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setInput(PlotType.getStandardPlotTypes());
		viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		if (plotSettings.getPlotType() == null) {
			plotSettings.setPlotType(PlotType.LINE);
		}
		dataBindingContext.bindValue(ViewerProperties.singleSelection().observe(viewer), PojoProperties.value("plotType").observe(plotSettings));

		label = new Label(group, SWT.None);
		label.setText("&Mode:");
		viewer = new ComboViewer(group, SWT.READ_ONLY);
		viewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((PlotMode) element).getLabel();
			}
		});
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setInput(PlotMode.getStandardModes());
		viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		dataBindingContext.bindValue(ViewerProperties.singleSelection().observe(viewer), PojoProperties.value("plotMode").observe(plotSettings));

		new PlotNxmBlockControls(plotBlockSettings, dataBindingContext).createControls(group);

		// === BULKIO settings ===
		if (bulkIOBlockSettings != null) {
			group = new Group(parent, SWT.None);
			group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
			group.setText("BULKIO");
			new BulkIONxmBlockControls(bulkIOBlockSettings, dataBindingContext, connectionIds).createControls(group);
		}

		// == BULKIO SDDS settings ===
		if (sddsBlockSettings != null) {
			group = new Group(parent, SWT.None);
			group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
			group.setText("BULKIO SDDS");
			new SddsNxmBlockControls(sddsBlockSettings, dataBindingContext).createControls(group);
		}

		// == FFT settings ==

		final Group fftGroup = new Group(parent, SWT.None);

		final Button button = new Button(fftGroup, SWT.CHECK);
		button.setText("Take &FFT");
		button.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		dataBindingContext.bindValue(WidgetProperties.selection().observe(button), PojoProperties.value("fft").observe(this));
		fftGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		fftGroup.setText("FFT");

		final List<Control> skip = Arrays.asList(fftGroup, button);

		new FftNxmBlockControls(fftBlockSettings, dataBindingContext).createControls(fftGroup);
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

	public void setFft(boolean fft) {
		this.fft = fft;
	}

	public boolean isFft() {
		return fft;
	}

	public BulkIONxmBlockSettings getBulkIOBlockSettings() {
		return bulkIOBlockSettings;
	}

	public void setBulkIOBlockSettings(BulkIONxmBlockSettings bulkIOBlockSettings) {
		this.bulkIOBlockSettings = bulkIOBlockSettings;
	}

	public SddsNxmBlockSettings getSddsBlockSettings() {
		return sddsBlockSettings;
	}

	public void setSddsBlockSettings(SddsNxmBlockSettings sddsBlockSettings) {
		this.sddsBlockSettings = sddsBlockSettings;
	}

	public FftNxmBlockSettings getFftBlockSettings() {
		return fftBlockSettings;
	}

	public void setFftBlockSettings(FftNxmBlockSettings fftBlockSettings) {
		this.fftBlockSettings = fftBlockSettings;
	}

	public PlotNxmBlockSettings getPlotBlockSettings() {
		return plotBlockSettings;
	}

	public void setPlotBlockSettings(PlotNxmBlockSettings plotBlockSettings) {
		this.plotBlockSettings = plotBlockSettings;
	}

	public PlotSettings getPlotSettings() {
		return plotSettings;
	}

	public void setPlotSettings(PlotSettings plotSettings) {
		this.plotSettings = plotSettings;
	}
	
	public void setConnectionIds(Map<String, Boolean> connectionIds) {
		this.connectionIds = connectionIds;
	}

}

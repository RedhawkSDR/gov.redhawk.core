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

import gov.redhawk.ui.port.nxmblocks.BulkIONxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.PlotNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.SddsNxmBlockSettings;
import gov.redhawk.ui.port.nxmplot.PlotSettings;

import java.nio.ByteOrder;

import org.eclipse.jface.wizard.Wizard;

/**
 * @noreference This class is not intended to be referenced by clients.
 * @since 4.4
 */
public class PlotWizard extends Wizard {
	private PlotWizardPage page = new PlotWizardPage("settings", "Plot Port Settings", null);

	public PlotWizard(boolean containsBulkIOPort, boolean containsSDDSPort) {
		setWindowTitle("Plot Port");
		if (containsBulkIOPort) {
			page.setBulkIOBlockSettings(new BulkIONxmBlockSettings());
		}
		if (containsSDDSPort) {
			SddsNxmBlockSettings sddsSettings = new SddsNxmBlockSettings();
			sddsSettings.setDataByteOrder(ByteOrder.nativeOrder()); // <-- workaround for REDHAWK SinkNic Component
			page.setSddsBlockSettings(sddsSettings);
		}
	}

	@Override
	public void addPages() {
		addPage(page);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		return true;
	}

	public PlotSettings getPlotSettings() {
		return page.getPlotSettings();
	}

	public boolean isFft() {
		return page.isFft();
	}

	public FftNxmBlockSettings getFftBlockSettings() {
		return page.getFftBlockSettings();
	}

	public BulkIONxmBlockSettings getBulkIOBlockSettings() {
		return page.getBulkIOBlockSettings();
	}

	public SddsNxmBlockSettings getSddsBlockSettings() {
		return page.getSddsBlockSettings();
	}

	public PlotNxmBlockSettings getPlotBlockSettings() {
		return page.getPlotBlockSettings();
	}
}

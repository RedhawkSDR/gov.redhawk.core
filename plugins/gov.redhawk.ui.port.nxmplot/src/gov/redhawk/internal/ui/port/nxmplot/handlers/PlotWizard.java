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

import java.nio.ByteOrder;

import gov.redhawk.ui.port.nxmblocks.BulkIONxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.PlotNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.SddsNxmBlockSettings;

import org.eclipse.jface.wizard.Wizard;

/**
 * @noreference This class is not intended to be referenced by clients.
 * @since 10.1
 */
public class PlotWizard extends Wizard {
	private PlotWizardPage page = new PlotWizardPage("settings", "Plot Port Settings", null);
	
	public PlotWizard(boolean containsBulkIOPort, boolean containsSDDSPort) {
		setWindowTitle("Plot Port");
		if (containsBulkIOPort) {
			getPlotSettings().setBulkIOBlockSettings(new BulkIONxmBlockSettings());
		}
		if (containsSDDSPort) {
			SddsNxmBlockSettings sddsSettings = new SddsNxmBlockSettings();
			sddsSettings.setDataByteOrder(ByteOrder.nativeOrder()); // <-- workaround for REDHAWK SinkNic Component
			getPlotSettings().setSddsBlockSettings(sddsSettings);
		}
		getPlotSettings().setPlotBlockSettings(new PlotNxmBlockSettings());
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

	public PlotWizardSettings getPlotSettings() {
		return page.getPlotSettings();
	}
}

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

import org.eclipse.jface.wizard.Wizard;

/**
 * 
 */
public class PlotWizard extends Wizard {
	private PlotWizardPage page = new PlotWizardPage("settings", "Plot Port Settings", null);
	
	public PlotWizard() {
		setWindowTitle("Plot Port");
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

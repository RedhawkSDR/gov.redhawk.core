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
package gov.redhawk.internal.ui.port.nxmplot.handlers;

import gov.redhawk.internal.ui.port.nxmplot.FftParameterEntryDialog;
import gov.redhawk.ui.port.IPortHandler;
import gov.redhawk.ui.port.nxmplot.FftSettings;
import gov.redhawk.ui.port.nxmplot.PlotActivator;

import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;

public class FftPlotPortHandler implements IPortHandler {

	public FftPlotPortHandler() {
	}

	public void connect(final List< ? > portList) {
		FftSettings fft = null;
		final FftParameterEntryDialog fftDialog = new FftParameterEntryDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), new FftSettings());
		final int result = fftDialog.open();
		if (result == Window.OK) {
			fft = fftDialog.getFFTSettings();
			PlotActivator.getDefault().addPlot(portList, fft);
		}
	}
	
	public void connect(final List< ? > portList, final String filter) {
		if (filter != null && filter.startsWith(IPortHandler.FILTER_FFT)) {
			connect(portList);
		}
	}
}

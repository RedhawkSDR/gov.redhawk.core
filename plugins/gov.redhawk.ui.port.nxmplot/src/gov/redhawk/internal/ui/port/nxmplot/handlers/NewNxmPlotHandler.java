/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.internal.ui.port.nxmplot.handlers;

import gov.redhawk.internal.ui.port.nxmplot.FftParameterEntryDialog;
import gov.redhawk.internal.ui.port.nxmplot.ManualCORBAParameterEntryDialog;
import gov.redhawk.ui.port.nxmplot.CorbaConnectionSettings;
import gov.redhawk.ui.port.nxmplot.FftSettings;
import gov.redhawk.ui.port.nxmplot.PlotActivator;

import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;

/**
 * The Class NewNxmPlotHandler.
 */
public class NewNxmPlotHandler extends AbstractHandler implements IHandler {

	private static final String FFT_PARAM = "gov.redhawk.ui.port.nxmplot.newdataplot.fftparam";

	/**
	 * Execute the event.
	 * 
	 * @param event the event
	 * @return always null
	 * @throws ExecutionException if the execution fails
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ManualCORBAParameterEntryDialog dialog = new ManualCORBAParameterEntryDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
		        new CorbaConnectionSettings());

		final String var = event.getParameter(NewNxmPlotHandler.FFT_PARAM);

		int result = dialog.open();
		if (result == Window.OK) {
			FftSettings fft = null;
			if (Boolean.parseBoolean(var)) {
				final FftParameterEntryDialog fftDialog = new FftParameterEntryDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
				        new FftSettings());
				result = fftDialog.open();
				if (result == Window.OK) {
					fft = fftDialog.getFFTSettings();
				} else {
					return null;
				}
			}
			final ArrayList<CorbaConnectionSettings> settingsList = new ArrayList<CorbaConnectionSettings>();
			settingsList.add(dialog.getConnectionSettings());
			PlotActivator.getDefault().addPlot(settingsList, fft);
		}

		return null;
	}

}

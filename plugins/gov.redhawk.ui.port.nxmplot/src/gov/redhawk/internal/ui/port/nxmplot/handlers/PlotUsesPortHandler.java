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
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.ui.port.nxmplot.FftSettings;
import gov.redhawk.ui.port.nxmplot.PlotActivator;

import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

public class PlotUsesPortHandler extends AbstractHandler {
	private static final String FFT_PARAM = "gov.redhawk.ui.port.nxmplot.command.plot.fftparam";

	public PlotUsesPortHandler() {
		super();
	}

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ISelection sel = HandlerUtil.getActiveMenuSelection(event);
		final String var = event.getParameter(PlotUsesPortHandler.FFT_PARAM);
		if (sel instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) sel;
			final Object element = ss.getFirstElement();
			if (element instanceof ScaUsesPort) {
				final ScaUsesPort port = (ScaUsesPort) element;
				FftSettings fft = null;
				if (Boolean.parseBoolean(var)) {
					final FftParameterEntryDialog fftDialog = new FftParameterEntryDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
					        new FftSettings());
					final int result = fftDialog.open();
					if (result == Window.OK) {
						fft = fftDialog.getFFTSettings();
					} else {
						return null;
					}
				}

				final ArrayList<ScaUsesPort> portList = new ArrayList<ScaUsesPort>();
				portList.add(port);
				PlotActivator.getDefault().addPlot(portList, fft);
			}
		}
		return null;
	}
}

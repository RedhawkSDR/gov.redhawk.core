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
package gov.redhawk.internal.ui.port.nxmplot;

import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.IPlotSession;

/**
 * @noreference This class is not intended to be referenced by clients
 */
public class PlotSession implements IPlotSession {

	private AbstractNxmPlotWidget plotWidget;
	private String command;
	private String file;

	public PlotSession(AbstractNxmPlotWidget plotWidget, String command, String file) {
		this.plotWidget = plotWidget;
		this.command = command;
		this.file = file;
	}

	@Override
	public void dispose() {
		if (!plotWidget.isDisposed()) {
			if (file != null) {
				plotWidget.sendPlotMessage("CLOSEFILE", 0, file);
			}
			if (command != null) {
				plotWidget.runHeadlessCommand("SENDTO " + command + " EXIT");
			}
		}
	}

	@Override
	public String getSourceId() {
		return this.file;
	}

	/** @since 4.2 */
	public String getCommandId() {
		return this.command;
	}
}

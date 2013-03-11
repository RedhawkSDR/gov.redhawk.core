package gov.redhawk.internal.ui.port.nxmplot;

import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.IPlotSession;

public class PlotSession implements IPlotSession {

	private AbstractNxmPlotWidget plotWidget;
	private String command;
	private String file;

	public PlotSession(AbstractNxmPlotWidget plotWidget, String command, String file) {
		this.plotWidget = plotWidget;
		this.command = command;
		this.file = file;
	}

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

	public String getSourceId() {
		return this.file;
	}

}

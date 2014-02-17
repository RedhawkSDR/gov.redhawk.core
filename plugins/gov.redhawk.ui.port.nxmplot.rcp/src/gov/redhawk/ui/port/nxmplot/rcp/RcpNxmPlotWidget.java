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
package gov.redhawk.ui.port.nxmplot.rcp;

import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.PlotSettings;
import gov.redhawk.ui.port.nxmplot.PlotType;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import nxm.rcp.ui.core.NeXtMidasComposite;
import nxm.redhawk.lib.RedhawkNxmUtil;
import nxm.sys.inc.Commandable;
import nxm.sys.lib.Command;
import nxm.sys.lib.Midas;
import nxm.sys.lib.NeXtMidas;
import nxm.sys.lib.Results;
import nxm.sys.lib.Shell;
import nxm.sys.prim.plot;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * @noextend This class is not intended to be subclassed by clients.
 * @since 1.1
 */
public class RcpNxmPlotWidget extends AbstractNxmPlotWidget {

	private static AtomicInteger uniqueCounter = new AtomicInteger();

	private final String msgHandlerId;
	private NeXtMidasComposite nxmComp;
	private final Shell rootNxmShell;
	private final Midas rootMidasContext;
	private plot plotCommand;

	private Set<String> sources = new HashSet<String>();
	private boolean initialized;

	public RcpNxmPlotWidget(final Composite parent, int style) {
		super(parent, style);
		parent.addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				dispose();
			}
		});
		setLayout(new FillLayout());
		nxmComp = new NeXtMidasComposite(this, SWT.None);

		RedhawkNxmUtil.initializeRedhawkOptionTrees();
		NeXtMidas globalNxm = NeXtMidas.getGlobalInstance();
		rootNxmShell = globalNxm.getShell();
		rootMidasContext = globalNxm.getMidasContext();
		msgHandlerId = rootMidasContext.getRegistry().putInstance("MAIN_MSG_HANLDER", getPlotMessageHandler());
	}

	@Override
	public void internalInitPlot(String plotSwitches, String plotArgs) {
		if (this.initialized) {
			throw new IllegalStateException("Plot already initialized.");
		}
		this.initialized = true;

		if (plotArgs == null) {
			plotArgs = "";
		}
		if (plotSwitches == null) {
			plotSwitches = "";
		}
		plotCommand = (plot) nxmComp.runGlobalCommand("PLOT/BG/VERBOSE=FALSE/EXIT=MESSAGE|WINDOW" + plotSwitches + " " + plotArgs, true);
		plotCommand.setMessageHandler(getPlotMessageHandler());
	}

	@Override
	public boolean isInitialized() {
		return initialized;
	}

	@Override
	public void runHeadlessCommand(String command) {
		runHeadlessCommandWithResult(command);
	}

	@Override
	public Command runHeadlessCommandWithResult(String command) {
		if (!isInitialized()) {
			throw new IllegalStateException("Plot not initialized");
		}
		return nxmComp.runGlobalCommand(command + " /MSGID=" + msgHandlerId, false);
	}

	@Override
	public void runClientCommand(String command) {
		runHeadlessCommand(command);
	}

	@Override
	public void dispose() {
		String[] sourcesCopy = Arrays.copyOf(sources.toArray(new String[0]), sources.size());
		for (String source : sourcesCopy) {
			removeSource(source);
		}
		this.sources.clear();
		if (plotCommand != null) {
			plotCommand.setState(Commandable.FINISH); // tell PLOT to FINISH/EXIT
		}
		rootMidasContext.getRegistry().remove(msgHandlerId);
		super.dispose();

	}

	@Override
	public void internalAddSource(String sourcePipeId, String pipeQualifiers) {
		if (!isInitialized()) {
			throw new IllegalStateException("Plot not initialized");
		}
		nxmComp.runGlobalCommand("SENDTO " + plotCommand.id + " OPENFILE " + sourcePipeId + ((pipeQualifiers == null) ? "" : pipeQualifiers), false);
		this.sources.add(sourcePipeId);
	}

	/**
	 * Get a copy of current Plot Settings
	 * @since 4.2
	 */
	@Override
	public PlotSettings getPlotSettings() {
		plot curPlot = getPlot();
		PlotSettings retVal = new PlotSettings(getPreferenceStore());
		if (curPlot != null) {
			String curPlotModeStr = curPlot.MP.getMode();
			if (!"".equals(curPlotModeStr)) {
				retVal.setPlotMode(PlotSettings.PlotMode.of(curPlotModeStr));
			}
			String curPlotTypeStr = curPlot.getPlotType();
			if (!"".equals(curPlotTypeStr)) {
				retVal.setPlotType(PlotType.valueOf(curPlotTypeStr));
			}
		}
		return retVal;
	}

	@Override
	public Set<String> getSources() {
		return Collections.unmodifiableSet(this.sources);
	}

	@Override
	public void removeSource(String sourcePipeId) {
		if (!isInitialized()) {
			throw new IllegalStateException("Plot not initialized");
		}
		if (!nxmComp.isDisposed()) {
			nxmComp.runGlobalCommand("SENDTO " + plotCommand.id + " CLOSEFILE " + sourcePipeId, false);
		}
		this.sources.remove(sourcePipeId);
	}

	@Override
	public void sendPlotMessage(String msgName, int info, Object data) {
		if (!isInitialized()) {
			throw new IllegalStateException("Plot not initialized");
		}
		// PLOT is running on same instance as server/processing side
		sendMessageToCommand(plotCommand.id, msgName, info, data, null);
	}

	private static String createUniqueResName() {
		return "_TEMPRES_" + RcpNxmPlotWidget.uniqueCounter.incrementAndGet();
	}

	//This method available only in the RCP plot widget
	@Override
	public plot getPlot() {
		return this.plotCommand;
	}

	@Override
	public String addDataFeature(Number xStart, Number xEnd, String color) {
		String featureId = AbstractNxmPlotWidget.createUniqueName(false);
		final double dx = xEnd.doubleValue() - xStart.doubleValue();
		final String cmd = "FEATURE LABEL=" + featureId + " PLOT=" + plotCommand.id + " TABLE={NAME=\"" + featureId + "\",TYPE=\"DATA\",X="
				+ (xStart.doubleValue() + (dx / 2)) + ",DX=" + dx + ",COLOR=\"" + color + "\"}";
		this.runClientCommand(cmd);
		return featureId;
	}

	@Override
	public String addFeatureByTypeMask(Number xStart, Number xEnd, Number yStart, Number yEnd, String typeMask, String color) {
		String featureId = AbstractNxmPlotWidget.createUniqueName(false);
		final String cmd;
		if (xStart != null && xEnd != null) {
			final double dx = xEnd.doubleValue() - xStart.doubleValue();
			cmd = "FEATURE LABEL=" + featureId + " PLOT=" + plotCommand.id + " TABLE={NAME=\"" + featureId + "\",TYPE=\"" + typeMask + "\"" + ",X="
					+ (xStart.doubleValue() + (dx / 2)) + ",DX=" + dx + ",COLOR=\"" + color + "\"}";
		} else if (yStart != null && yEnd != null) {
			final double dy = yEnd.doubleValue() - yStart.doubleValue();
			cmd = "FEATURE LABEL=" + featureId + " PLOT=" + plotCommand.id + " TABLE={NAME=\"" + featureId + "\",TYPE=\"" + typeMask + "\"" + ",Y="
					+ (yStart.doubleValue() + (dy / 2)) + ",DY=" + dy + ",COLOR=\"" + color + "\"}";
		} else {
			cmd = null;
		}

		if (cmd != null) {
			this.runClientCommand(cmd);
		}
		return featureId;
	}

	@Override
	public String addDragboxFeature(Number xmin, Number ymin, Number xmax, Number ymax, String color) {
		String featureId = AbstractNxmPlotWidget.createUniqueName(false);
		final double x = (xmax.doubleValue() + xmin.doubleValue()) / 2d;
		final double y = (ymax.doubleValue() + ymin.doubleValue()) / 2d;
		final double dx = xmax.doubleValue() - xmin.doubleValue();
		final double dy = ymax.doubleValue() - ymin.doubleValue();
		final String command = "FEATURE LABEL=" + featureId + " PLOT=" + plotCommand.id + " TABLE={NAME=\"" + featureId + "\",TYPE=\"BOX\",X=" + x + ",DX="
				+ dx + ",Y=" + y + ",DY=" + dy + ",COLOR=\"" + color + "\"}";
		this.runClientCommand(command);
		return featureId;
	}

	@Override
	public void removeFeature(String featureId) {
		final String command = "INVOKE junk reg." + plotCommand.id + ".removeFeature(\"" + featureId + "\")";
		this.runClientCommand(command);
	}

	@Override
	public void configurePlot(Map<String, String> configuration) {
		if (!isInitialized()) {
			throw new IllegalStateException("Plot not initialized");
		}
		for (Map.Entry<String, String> entry : configuration.entrySet()) {
			nxmComp.runGlobalCommand("SET " + "REG." + plotCommand.id + "." + entry.getKey() + " " + entry.getValue(), false);
		}
	}

	@Override
	public void clearSources() {
		for (String source : sources.toArray(new String[sources.size()])) {
			removeSource(source);
		}
	}

	@Override
	public void sendMessageToCommand(String cmdID, String msgName, int info, Object data, Object quals) {
		final String tempRes4Data = RcpNxmPlotWidget.createUniqueResName();
		final String tempRes4Quals = RcpNxmPlotWidget.createUniqueResName();
		final Results resultsTable = rootNxmShell.M.results;
		resultsTable.put(tempRes4Data, data); //to pass object reference for DATA=  below
		resultsTable.put(tempRes4Quals, quals); //to pass object reference for QUALS= below
		nxmComp.runGlobalCommand("MESSAGE SEND ID=" + cmdID + " NAME=" + msgName + " INFO=" + info + " DATA=" + tempRes4Data + " QUALS=" + tempRes4Quals, false);
		resultsTable.remove(tempRes4Data); // cleanup
		resultsTable.remove(tempRes4Quals); // cleanup
	}

	@Override
	public Command runGlobalCommand(String command) {
		return nxmComp.runGlobalCommand(command, false);
	}
}

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
package gov.redhawk.ui.port.nxmplot;

import gov.redhawk.internal.ui.port.nxmplot.PlotSession;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings;
import gov.redhawk.ui.port.nxmplot.PlotSettings.PlotMode;
import gov.redhawk.ui.port.nxmplot.preferences.PlotPreferences;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import nxm.sys.lib.Data;

import org.eclipse.jdt.annotation.NonNull;

/**
 * @since 3.0
 */
public final class NxmPlotUtil {

	/** key to get NeXtMidas Command ID entry in Map returned from launchInputMacro(..) methods. */
	private static final String KEY_COMMAND = "command";
	/** key to get NeXtMidas Pipe/File name entry in Map returned from launchInputMacro(..) methods. */
	private static final String KEY_FILE = "file";
	private static final String EMPTY_STRING = "";

	private static final int MAX_RMIF_PACKET_SIZE = 32768; // in bytes

	private NxmPlotUtil() {
	}

	/**
	 * Get default pipe qualifiers for specified plot type.
	 * @since 4.2
	 */
	public static String getDefaultPlotQualifiers(PlotType type) {
		if (type == null) {
			return null;
		}
		switch (type) {
		case RASTER:
			return null;
		default:
			return "{CL=8}"; // <-- quick data thinning for line plots by skipping (n-1) elements for every 1 element read (for TL=1)
		}
	}

	/**
	 * @since 4.2
	 */
	public static String getDefaultPlotArgs(PlotType type) {
		if (type == null) {
			return null;
		}
		switch (type) {
		case RASTER:
			return "TYPE=RASTER View=iYX SCALE=AutoMin|AutoMax|NoAverage AUTOL=16";
		case CONTOUR: // fall-through
		case DOT: // fall-through
		case MESH: // fall-through
		case LINE: // fall-through
		case POINT:
			return "TYPE=" + type + " AXIS=+GRID OPTIONS=BStore SCALE=AutoMin|AutoMax|NoAverage AUTOL=16";
		default:
			return null;
		}
	}

	/**
	 * @since 4.2
	 */
	public static String getDefaultPlotSwitches(PlotType type) {
		if (type == null) {
			return null;
		}
		switch (type) {
		case RASTER:
			return "/LPS=200/RT/NICE/FOCUS=FALSE";
		case CONTOUR: // fall-through
		case DOT: // fall-through
		case MESH: // fall-through
		case LINE: // fall-through
		case POINT:
			return "/RT/NICE/FOCUS=FALSE";
		default:
			return null;
		}

	}

	/**
	 * @param plotSettings merge desired plot settings with default plot arguments
	 * @return never null, an empty string if plotSettings is null,
	 *         otherwise the default plot args for the specified plotSettings.
	 * @since 4.4
	 */
	@NonNull
	public static String getDefaultPlotArgs(PlotSettings plotSettings) {
		if (plotSettings == null) {
			return NxmPlotUtil.EMPTY_STRING;
		}
		PlotType plotType = plotSettings.getPlotType();
		String plotArgs = NxmPlotUtil.getDefaultPlotArgs(plotType);
		if (plotArgs == null) {
			plotArgs = NxmPlotUtil.EMPTY_STRING; // so we can easily append to plot args below
		}
		PlotMode plotMode = plotSettings.getPlotMode();
		if (plotMode != null) {
			plotArgs += " CM=" + plotSettings.getPlotMode().toModeString();
		}
		// user specified launch arguments could override any of the previous settings
		if (plotSettings.getLaunchArgs() != null) {
			plotArgs += " " + plotSettings.getLaunchArgs();
		}
		return plotArgs;
	}

	/**
	 * @param plotSettings merge desired plot settings with default plot switches
	 * @return never null, an empty string if plotSettings is null or unknown plot type,
	 *         otherwise the default plot switches for the specified plotSettings.
	 * @since 4.4
	 */
	@NonNull
	public static String getDefaultPlotSwitches(PlotSettings plotSettings) {
		if (plotSettings == null) {
			return NxmPlotUtil.EMPTY_STRING;
		}
		PlotType plotType = plotSettings.getPlotType();
		String plotSwitches = NxmPlotUtil.getDefaultPlotSwitches(plotType);
		if (plotSwitches == null) {
			plotSwitches = NxmPlotUtil.EMPTY_STRING; // so we can easily append to plot args below
		}
		Boolean enablePlotMenu = plotSettings.getEnablePlotMenu();
		if (enablePlotMenu == null) { // not set, get user's configured workbench preference for this
			enablePlotMenu = PlotPreferences.ENABLE_CONFIGURE_MENU_USING_MOUSE.getValue(PlotActivator.getDefault().getPreferenceStore());
			plotSettings.setEnablePlotMenu(enablePlotMenu);
		}

		if (Boolean.FALSE.equals(enablePlotMenu)) { // PLOT has configure menu on by default
			plotSwitches += "/EVENTFILTER=+NoMiddleMouse"; // so only need to disable when false
		}

		// user specified launch switches could override any of the previous settings
		if (plotSettings.getLaunchSwitches() != null) {
			plotSwitches += plotSettings.getLaunchSwitches();
		}
		return plotSwitches;
	}

	public static String formatPlotArgs(Map<String, String> args) {
		StringBuilder sb = new StringBuilder();
		String delim = "";
		for (Entry<String, String> entry : args.entrySet()) {
			String valueString = null;
			if (entry.getValue() == null || "".equals(entry.getValue())) {
				valueString = "";
			} else {
				valueString = "=" + entry.getValue();
			}
			sb.append(delim + entry.getKey() + valueString);
			delim = " ";
		}
		return sb.toString();
	}

	public static String formatPlotSwitches(Map<String, String> switches) {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String> entry : switches.entrySet()) {
			sb.append("/" + entry.getKey() + "=" + entry.getValue());
		}
		return sb.toString();
	}

	public static String formatPlotQualifiers(Map<String, String> qualifiers) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		String delim = "";
		for (Entry<String, String> entry : qualifiers.entrySet()) {
			sb.append(delim + entry.getKey() + "=" + entry.getValue());
			delim = ",";
		}
		sb.append("}");
		return sb.toString();
	}

	private static Map<String, String> launchInputMacro(final SddsSource sdds, final Integer magExponent, final FftNxmBlockSettings fft,
		final AbstractNxmPlotWidget plotWidget, String pipeSize) {
		final String outName = AbstractNxmPlotWidget.createUniqueName(true);
		final String transformIn = AbstractNxmPlotWidget.createUniqueName(true);
		final String fftIn = AbstractNxmPlotWidget.createUniqueName(true);

		plotWidget.runHeadlessCommand("PIPE ON");

		final StringBuilder command = new StringBuilder();
		final String sourcenicId = "SOURCENIC_" + AbstractNxmPlotWidget.createUniqueName(false);
		if (pipeSize == null) {
			// Default size
			pipeSize = "128k";
		}
		command.append("SOURCENIC/PS=" + pipeSize + "/ID=" + sourcenicId);
		command.append("/fc=" + sdds.format);
		if (sdds.mcastAddress != null) {
			command.append("/mgrp=" + sdds.mcastAddress);
		}
		if (sdds.vlan != 0) {
			command.append("/vlan=" + sdds.vlan);
		}
		if (sdds.port != 0) {
			command.append("/port=" + sdds.port);
		}
		if (magExponent != null) {
			command.append(" out=" + transformIn);
			plotWidget.runHeadlessCommand(command.toString());

			command.delete(0, command.length());
			command.append("FCALCULATOR ");
			if (fft == null) {
				command.append(outName + " ");
				command.append(transformIn + " ");
				command.append(magExponent + " pow");
				plotWidget.runHeadlessCommand(command.toString());
			} else {
				command.append(fftIn + " ");
				command.append(transformIn + " ");
				command.append(magExponent + " pow");
				plotWidget.runHeadlessCommand(command.toString());

				command.delete(0, command.length());
				command.append("fft/id=" + "\"FFT_" + AbstractNxmPlotWidget.createUniqueName(false) + "\"" + "/psd/ac");
				command.append(" in1=\"" + fftIn + "\"");
				command.append(" out1=\"" + outName + "\"");
				command.append(" nfft=" + fft.getTransformSize() + " win=\"" + fft.getWindow() + "\" over=" + fft.getOverlap() + " navg="
						+ fft.getNumAverages());
				plotWidget.runHeadlessCommand(command.toString());
			}
		} else {
			if (fft == null) {
				command.append(" out=" + outName);
				plotWidget.runHeadlessCommand(command.toString());
			} else {
				command.append(" out=" + fftIn);
				plotWidget.runHeadlessCommand(command.toString());

				command.delete(0, command.length());
				command.append("fft/id=" + "\"FFT_" + AbstractNxmPlotWidget.createUniqueName(false) + "\"" + "/psd/ac");
				command.append(" in1=\"" + fftIn + "\"");
				command.append(" out1=\"" + outName + "\"");
				command.append(" nfft=" + fft.getTransformSize() + " win=\"" + fft.getWindow() + "\" over=" + fft.getOverlap() + " navg="
						+ fft.getNumAverages());
				plotWidget.runHeadlessCommand(command.toString());
			}
		}
		plotWidget.runHeadlessCommand("PIPE RUN");
		Map<String, String> map = new HashMap<String, String>();
		map.put(NxmPlotUtil.KEY_COMMAND, sourcenicId);
		map.put(NxmPlotUtil.KEY_FILE, outName);
		return map;
	}

	private static Map<String, String> launchInputMacro(final File file, String format, final boolean thinData, Integer thinIncr, Integer yDelta,
		final AbstractNxmPlotWidget plotWidget) {
		final String outName = AbstractNxmPlotWidget.createUniqueName(true);
		final String thinIn = AbstractNxmPlotWidget.createUniqueName(true);

		format = format.toUpperCase();
		int bytesPerSample = Data.getBPA(format); // since 4.2

		if (yDelta == null || yDelta.intValue() == 0) {
			yDelta = Integer.valueOf(5);
		}
		if (thinIncr == null || thinIncr.intValue() == 0) {
			// make the resulting file 32k bytes in size, to conform to RMIF packet size limit
			thinIncr = new Double(Math.floor(file.length() / (double) NxmPlotUtil.MAX_RMIF_PACKET_SIZE / bytesPerSample)).intValue();
		}

		plotWidget.runHeadlessCommand("PIPE ON");

		if (thinData) {
			plotWidget.runHeadlessCommand("NOOP/WRAP/RT " + file.getAbsolutePath() + "{ydelta=" + yDelta.intValue() + ",yu=time} " + thinIn);
			plotWidget.runHeadlessCommand("THIN IN=" + thinIn + " OUT=" + outName + " FINC=" + thinIncr.intValue());
		} else {
			plotWidget.runHeadlessCommand("NOOP/WRAP/RT " + file.getAbsolutePath() + "{ydelta=" + yDelta.intValue() + ",yu=time} " + outName);
		}

		plotWidget.runHeadlessCommand("PIPE RUN");
		Map<String, String> map = new HashMap<String, String>();
		map.put(NxmPlotUtil.KEY_FILE, outName);
		return map;
	}

	public static IPlotSession addSource(final SddsSource sdds, final AbstractNxmPlotWidget plotWidget, final String qualifers) {
		return NxmPlotUtil.addSource(sdds, null, null, plotWidget, qualifers);
	}

	/**
	 * @since 4.4
	 */
	public static IPlotSession addSource(final SddsSource sdds, final FftNxmBlockSettings fft, final AbstractNxmPlotWidget plotWidget, final String qualifers) {
		return NxmPlotUtil.addSource(sdds, null, fft, plotWidget, qualifers);
	}

	/**
	 * @since 4.4
	 */
	public static IPlotSession addSource(final SddsSource sdds, final Integer magExponent, final FftNxmBlockSettings fft,
		final AbstractNxmPlotWidget plotWidget, final String qualifiers) {
		final Map<String, String> outputIds = NxmPlotUtil.launchInputMacro(sdds, magExponent, fft, plotWidget, null);
		PlotSession session = new PlotSession(plotWidget, outputIds.get(NxmPlotUtil.KEY_COMMAND), outputIds.get(NxmPlotUtil.KEY_FILE));
		plotWidget.addSource(session.getSourceId(), ((qualifiers == null) ? "" : qualifiers), session);
		return session;
	}

	public static void setPlotToReal(boolean real, AbstractNxmPlotWidget nxmPlotWidget) {
		if (real) {
			nxmPlotWidget.configurePlot(Collections.singletonMap("MODE", "MAG"));
		} else {
			nxmPlotWidget.configurePlot(Collections.singletonMap("MODE", "REAL"));
		}
	}

	/**
	 * @since 4.2
	 */
	public static IPlotSession addSource(final File file, String format, final boolean thinData, Integer thinIncr, Integer yDelta,
		final AbstractNxmPlotWidget plotWidget, final String qualifiers) {

		final Map<String, String> outputIds = NxmPlotUtil.launchInputMacro(file, format, thinData, thinIncr, yDelta, plotWidget);
		PlotSession session = new PlotSession(plotWidget, outputIds.get(NxmPlotUtil.KEY_COMMAND), outputIds.get(NxmPlotUtil.KEY_FILE));
		plotWidget.addSource(session.getSourceId(), ((qualifiers == null) ? "" : qualifiers), session);
		return session;
	}
}

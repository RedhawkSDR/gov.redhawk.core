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
import gov.redhawk.model.sca.ScaUsesPort;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.swt.SWT;

import nxm.redhawk.prim.corbareceiver;
import nxm.sys.lib.Data;
import nxm.sys.lib.Table;

/**
 * @since 3.0
 */
public final class NxmPlotUtil {

	private static final String KEY_FILE = "file";

	private static final String KEY_COMMAND = "command";
	private static final int MAX_RMIF_PACKET_SIZE = 32768; // in bytes

	/**
	 * @since 4.2
	 */
	public static String getDefaultPlotArgs(PlotType type) {
		if (type == null) {
			return null;
		}
		switch(type) {
		case RASTER:
			return "TYPE=RASTER View=iYX SCALE=AutoMin|AutoMax|NoAverage AUTOL=16";
		case CONTOUR: // fall-through
		case DOT:     // fall-through
		case MESH:    // fall-through
		case LINE:    // fall-through
		case POINT:
			return "TYPE=" + type + " AXIS=+GRID OPTIONS=BStore SCALE=AutoMin|AutoMax AUTOL=16";
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
		switch(type) {
		case RASTER:
			return "/LPS=200/RT/NICE";
		case CONTOUR: // fall-through
		case DOT:     // fall-through
		case MESH:    // fall-through
		case LINE:    // fall-through
		case POINT:
			return "/RT/NICE";
		default:
			return null;
		}

	}

	public static String formatPlotArgs(Map<String, String> args) {
		StringBuilder sb = new StringBuilder();
		String delim = "";
		for (Entry<String, String> entry : args.entrySet()) {
			String valueString = null;
			if (entry.getValue() == null || entry.getValue().equals("")) {
				valueString = "";
			} else {
				valueString =  "=" + entry.getValue();
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

	private NxmPlotUtil() {

	}

	private static Map<String, String> launchInputMacro(final CorbaConnectionSettings settings, final FftSettings fft, final AbstractNxmPlotWidget plotWidget, String pipeSize) {
		final String outName = AbstractNxmPlotWidget.createUniqueName(true);

		final Table corbaArgs = new Table();
		if (pipeSize == null) {
			// Default size
			pipeSize = "128k";
		}
		corbaArgs.put("PIPESIZE", pipeSize);
		corbaArgs.put(corbareceiver.A_IDL, settings.getIDL());
		corbaArgs.put(corbareceiver.A_FRAMESIZE, settings.getFrameSize());
		corbaArgs.put(corbareceiver.A_OVERRIDE_SRI_SUBSIZE, settings.isOverrideSRISubSize());
		if (settings.getHost() != null && !"".equals(settings.getHost())) {
			corbaArgs.put(corbareceiver.A_HOST, settings.getHost());
			corbaArgs.put(corbareceiver.A_PORT, settings.getPort());
		} else {
			corbaArgs.put(corbareceiver.A_HOST, "");
			corbaArgs.put(corbareceiver.A_PORT, 9000); // <-- TODO: this does not seem like the right default port for CORBARECEIVER // SUPPRESS CHECKSTYLE MagicNumber
		}
		if (settings.getResource() != null && !"".equals(settings.getResource())) {
			corbaArgs.put(corbareceiver.A_RESOURCE, settings.getResource());
		} else {
			corbaArgs.put(corbareceiver.A_RESOURCE, "");
		}
		if (settings.getPortName() != null) {
			corbaArgs.put(corbareceiver.A_PORT_NAME, settings.getPortName());
		} else {
			corbaArgs.put(corbareceiver.A_PORT_NAME, "");
		}

		final Table fftArgs = new Table();
		if (fft != null) {
			fftArgs.put("FFTSIZE", fft.getTransformSize());
			fftArgs.put("WINDOW", fft.getWindow());
			fftArgs.put("OVER", (Double.parseDouble(fft.getOverlap()) / 100.0)); // SUPPRESS CHECKSTYLE MagicNumber
			fftArgs.put("NUMAVG", fft.getNumAverages());
		}

		final String thinArgsStr;
		if (SWT.getPlatform().startsWith("rap")) { // thin out data for remote PLOT on RAP platform
			Table thinArgs = new Table();
			thinArgs.put("PIPESIZE", pipeSize);
			thinArgs.put("REFRESHRATE", 10); // SUPPRESS CHECKSTYLE MagicNumber
			thinArgsStr = " THINARGS=" + thinArgs;
		} else {
			thinArgsStr = ""; // for RCP we do not need to thin out data since PLOT is on same machine
		}

		final String corbaArgsStr = "CORBRAARGS=" + corbaArgs;
		final String fftArgsStr;
		if (fftArgs.isEmpty()) {
			fftArgsStr = "";
		} else {
			fftArgsStr = " FFTARGS=" + fftArgs;
		}

		final String outputStr = " OUTPUT=" + outName;
		final String b2mId = "B2M_" + AbstractNxmPlotWidget.createUniqueName(false);
		final String command = "bulkio2midas/BG/ID=" + b2mId + " " + corbaArgsStr + fftArgsStr + thinArgsStr + outputStr;
		plotWidget.runHeadlessCommand(command);

		Map<String, String> map = new HashMap<String, String>();
		map.put(KEY_COMMAND, b2mId);
		map.put(KEY_FILE, outName);
		return map;
	}

	private static Map<String, String> launchInputMacro(final SddsSource sdds, final Integer magExponent, final FftSettings fft,
			final AbstractNxmPlotWidget plotWidget, String pipeSize) {
		final String outName = AbstractNxmPlotWidget.createUniqueName(true);
		final String transformIn = AbstractNxmPlotWidget.createUniqueName(true);
		final String fftIn = AbstractNxmPlotWidget.createUniqueName(true);

		plotWidget.runHeadlessCommand("PIPE ON");

		final StringBuilder command = new StringBuilder();
		final String sourceId = "SOURCENIC_" + AbstractNxmPlotWidget.createUniqueName(false);
		if (pipeSize == null) {
			// Default size
			pipeSize = "128k";
		}
		command.append("SOURCENIC/PS=" + pipeSize + "/ID=" + sourceId);
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
		map.put(KEY_COMMAND, sourceId);
		map.put(KEY_FILE, outName);
		return map;
	}

	/**
	 * This creates the pipes and connects them for the appropriate plot output.
	 *
	 * @param connList the list of connections to set up
	 * @param fft2 the settings for the FFT to run, null if no FFT
	 * @param commandSources list to store the commands created so they can be
	 *        started later
	 * @return a list of sources created to be plotted
	 */
	private static List<Map<String, String>> launchInputMacros(final List<CorbaConnectionSettings> connList, final FftSettings fft,
			final AbstractNxmPlotWidget plotWidget, String pipeSize) {
		final List<Map<String, String>> outputList = new ArrayList<Map<String, String>>();

		for (final CorbaConnectionSettings settings : connList) {
			outputList.add(launchInputMacro(settings, fft, plotWidget, pipeSize));
		}

		return outputList;
	}

	private static Map<String, String> launchInputMacro(final File file, String format, final boolean thinData,
			Integer thinIncr, Integer yDelta, final AbstractNxmPlotWidget plotWidget) {
		final String outName = AbstractNxmPlotWidget.createUniqueName(true);
		final String thinIn = AbstractNxmPlotWidget.createUniqueName(true);

		int bytesPerSample = 1;
		format = format.toUpperCase();
		bytesPerSample = Data.getBPA(format); // since 4.2
// prior to 4.2 (TODO: remove below?)
//		if (format.charAt(1) == 'F') {
//			bytesPerSample = 4;
//		} else if (format.charAt(1) == 'D') {
//			bytesPerSample = 8;
//		} if (format.charAt(1) == 'I') {
//			bytesPerSample = 4;
//		}
// end prior to 4.2

		if (yDelta == null || yDelta.intValue() == 0) {
			yDelta = Integer.valueOf(5);
		}
		if (thinIncr == null || thinIncr.intValue() == 0) {
			// make the resulting file 32k bytes in size, to conform to RMIF packet size limit
			thinIncr = new Double(Math.floor(file.length() / (double) MAX_RMIF_PACKET_SIZE / bytesPerSample)).intValue();
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
		map.put(KEY_FILE, outName);
		return map;
	}

	public static List<CorbaConnectionSettings> createConnList(final List< ? extends ScaUsesPort> portList) {
		final List<CorbaConnectionSettings> connList = new ArrayList<CorbaConnectionSettings>();

		for (final ScaUsesPort port : portList) {
			connList.add(createConnectionSettings(port));
		}

		return connList;
	}

	public static CorbaConnectionSettings createConnectionSettings(ScaUsesPort port) {
		final CorbaConnectionSettings connectionSettings = new CorbaConnectionSettings(port.getIor(), port.getRepid());
		return connectionSettings;
	}

	/**
	 * @deprecated Use {@Link #addSource(ScaUsesPort, AbstractNxmPlotWidget, String)} instead. Using this method will
	 * result in a resource leak, as there is no way for the client to kill the NXM processes used to provide the plot data.
	 * @param port the port that provides the data to be plotted
	 * @param nxmPlotWidget the plot widget
	 * @return the pipe ID for the data being plotted
	 */
	@Deprecated
	public static String addSource(final ScaUsesPort port, final AbstractNxmPlotWidget plotWidget) {
		return addSource(port, null, plotWidget);
	}

	/**
	 * @deprecated Use {@Link #addSource(ScaUsesPort, FftSettings, AbstractNxmPlotWidget, String)} instead. Using this method will
	 * result in a resource leak, as there is no way for the client to kill the NXM processes used to provide the plot data.
	 * @param port the port that provides the data to be plotted
	 * @param fft the parameters of the FFT to be applied to the data before plotting
	 * @param nxmPlotWidget the plot widget
	 * @return the pipe ID for the data being plotted
	 */
	@Deprecated
	public static String addSource(final ScaUsesPort port, final FftSettings fft, final AbstractNxmPlotWidget plotWidget) {
		return addSource(port, fft, plotWidget, null).getSourceId();
	}


	public static IPlotSession addSource(final ScaUsesPort port, final AbstractNxmPlotWidget plotWidget, final String qualifers) {
		return addSource(port, null, plotWidget, qualifers);
	}

	public static IPlotSession addSource(final ScaUsesPort port, final FftSettings fft, final AbstractNxmPlotWidget plotWidget, final String qualifiers) {
		final Map<String, String> outputIds = launchInputMacro(createConnectionSettings(port), fft, plotWidget, null);
		PlotSession session = new PlotSession(plotWidget, outputIds.get(KEY_COMMAND), outputIds.get(KEY_FILE));
		plotWidget.addSource(session.getSourceId(), ((qualifiers == null) ? "" : qualifiers));
		return session;
	}

	/**
	 * @deprecated Use {@link #addSource(List, FftSettings, AbstractNxmPlotWidget, String))} instead. Using this method will
	 * result in a resource leak, as there is no way for the client to kill the NXM processes used to provide the plot data.
	 * @param port the port that provides the data to be plotted. There is also no way for the client to close the plot file.
	 */
	@Deprecated
	public static void plot(final List<CorbaConnectionSettings> connList, final FftSettings fft, final AbstractNxmPlotWidget plotWidget) {
		final List<Map<String, String>> outputIds = launchInputMacros(connList, fft, plotWidget, null);
		setPlotToReal(fft != null, plotWidget);

		for (Map<String, String> map : outputIds) {
			plotWidget.addSource(map.get(KEY_FILE), null);
		}
	}

	public static List<IPlotSession> addSource(final List<CorbaConnectionSettings> connList, final FftSettings fft, final AbstractNxmPlotWidget plotWidget,
			final String qualifiers) {
		List<Map<String, String>> outputIds = launchInputMacros(connList, fft, plotWidget, null);
		setPlotToReal(fft != null, plotWidget);
		List<IPlotSession> sessions = new ArrayList<IPlotSession>();
		for (Map<String, String> map : outputIds) {
			plotWidget.addSource(map.get(KEY_FILE), ((qualifiers == null) ? "" : qualifiers));
			sessions.add(new PlotSession(plotWidget, map.get(KEY_COMMAND), map.get(KEY_FILE)));
		}
		return sessions;
	}

	public static IPlotSession addSource(final SddsSource sdds, final AbstractNxmPlotWidget plotWidget, final String qualifers) {
		return addSource(sdds, null, plotWidget, qualifers);
	}

	public static IPlotSession addSource(final SddsSource sdds, final FftSettings fft, final AbstractNxmPlotWidget plotWidget, final String qualifers) {
		return addSource(sdds, null, fft, plotWidget, qualifers);
	}

	public static IPlotSession addSource(final SddsSource sdds, final Integer magExponent, final FftSettings fft, final AbstractNxmPlotWidget plotWidget,
			final String qualifiers) {
		final Map<String, String> outputIds = launchInputMacro(sdds, magExponent, fft, plotWidget, null);
		PlotSession session = new PlotSession(plotWidget, outputIds.get(KEY_COMMAND), outputIds.get(KEY_FILE));
		plotWidget.addSource(session.getSourceId(), ((qualifiers == null) ? "" : qualifiers));
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
	 * @since 4.1
	 */
	public static IPlotSession addSource(final File file, String format, final boolean thinData, Integer thinIncr, Integer yDelta,
			final AbstractNxmPlotWidget plotWidget, final String qualifiers) {

		final Map<String, String> outputIds = launchInputMacro(file, format, thinData, thinIncr, yDelta, plotWidget);
		PlotSession session = new PlotSession(plotWidget, outputIds.get(KEY_COMMAND), outputIds.get(KEY_FILE));
		plotWidget.addSource(session.getSourceId(), ((qualifiers == null) ? "" : qualifiers));
		return session;
	}

}

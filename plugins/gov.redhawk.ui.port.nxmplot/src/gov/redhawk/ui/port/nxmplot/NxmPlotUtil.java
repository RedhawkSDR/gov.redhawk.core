package gov.redhawk.ui.port.nxmplot;

import gov.redhawk.internal.ui.port.nxmplot.PlotSession;
import gov.redhawk.model.sca.ScaUsesPort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nxm.sys.lib.Table;

import org.eclipse.swt.SWT;

/**
 * @since 3.0
 */
public final class NxmPlotUtil {

	private static final String KEY_FILE = "file";

	private static final String KEY_COMMAND = "command";

	public static String formatPlotArgs(Map<String, String> args) {
		StringBuilder sb = new StringBuilder();
		String delim = "";
		for (Entry<String, String> entry : args.entrySet()) {
			sb.append(delim + entry.getKey() + "=" + entry.getValue());
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
		if (settings.getHost() != null && !"".equals(settings.getHost())) {
			corbaArgs.put("HOST", settings.getHost());
			corbaArgs.put("PORT", settings.getPort());
		} else {
			corbaArgs.put("HOST", "");
			corbaArgs.put("PORT", 9000);
		}
		if (settings.getResource() != null && !"".equals(settings.getResource())) {
			corbaArgs.put("RESOURCE", settings.getResource());
		} else {
			corbaArgs.put("RESOURCE", "");
		}
		if (settings.getPortName() != null) {
			corbaArgs.put("PORT_NAME", settings.getPortName());
		} else {
			corbaArgs.put("PORT_NAME", "");
		}
		corbaArgs.put("IDL", settings.getIDL());
		corbaArgs.put("FORCE2000", settings.is2D());

		final Table fftArgs = new Table();
		if (fft != null) {
			fftArgs.put("FFTSIZE", fft.getTransformSize());
			fftArgs.put("WINDOW", fft.getWindow());
			fftArgs.put("OVER", (Double.parseDouble(fft.getOverlap()) / 100.0));
			fftArgs.put("NUMAVG", fft.getNumAverages());
		}

		final Table thinArgs = new Table();
		thinArgs.put("PIPESIZE", pipeSize);
		// Plot at different speeds depending on platform
		if (SWT.getPlatform().startsWith("rap")) {
			thinArgs.put("REFRESHRATE", 10);
		} else {
			thinArgs.put("REFRESHRATE", 60);
		}

		final String corbaArgsStr = "CORBRAARGS=" + corbaArgs;
		final String fftArgsStr;
		if (fftArgs.isEmpty()) {
			fftArgsStr = "";
		} else {
			fftArgsStr = " FFTARGS=" + fftArgs;
		}
		final String thinArgsStr = " THINARGS=" + thinArgs;

		final String outputStr = " OUTPUT=" + outName;
		final String b2mId = "B2M_" + AbstractNxmPlotWidget.createUniqueName(false);
		final String command = "bulkio2midas/bg/id=" + b2mId + " " + corbaArgsStr + fftArgsStr + thinArgsStr + outputStr;
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
				command.append("fft/id=fft/psd/ac");
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
				command.append("fft/id=fft/psd/ac");
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

	public static List<CorbaConnectionSettings> createConnList(final List< ? extends ScaUsesPort> portList) {
		final List<CorbaConnectionSettings> connList = new ArrayList<CorbaConnectionSettings>();

		for (final ScaUsesPort port : portList) {
			connList.add(createConnectionSettings(port));
		}

		return connList;
	}

	public static CorbaConnectionSettings createConnectionSettings(ScaUsesPort port) {
		final CorbaConnectionSettings connectionSettings = new CorbaConnectionSettings(port.getIor(), port.getRepid(), true);
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
		final Map<String, String> outputIds = launchInputMacro(sdds, magExponent, fft, plotWidget, "1m");
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

}

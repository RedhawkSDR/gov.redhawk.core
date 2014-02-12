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
import gov.redhawk.ui.port.nxmplot.PlotSettings.PlotMode;
import gov.redhawk.ui.port.nxmplot.preferences.PlotPreferences;
import gov.redhawk.ui.port.nxmplot.preferences.Preference;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import nxm.sys.inc.Commandable;
import nxm.sys.inc.MessageHandler;
import nxm.sys.lib.Command;
import nxm.sys.lib.Message;
import nxm.sys.lib.Position;
import nxm.sys.lib.Table;
import nxm.sys.libg.DragBox;
import nxm.sys.prim.plot;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.statushandlers.StatusManager;

import BULKIO.StreamSRI;

/**
 * @noextend This class is not intended to be subclassed by SDK clients.
 */
public abstract class AbstractNxmPlotWidget extends Composite {
	/** index to create unique results name. */
	private static final AtomicInteger NAME_INDEX = new AtomicInteger();

	/** index to create unique pipe name. */
	private static final AtomicInteger PIPE_NAME_INDEX = new AtomicInteger();

	private StreamSRI activeSRI;
	private final IPreferenceStore store = Preference.initStoreFromWorkbench(PlotPreferences.getAllPreferences());
	private final IPropertyChangeListener listener = new IPropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent event) {
			if (isInitialized()) {
				handlePropertyChange(event);
			}
		}
	};
	private final Map<String, IPlotSession> inputSessions = Collections.synchronizedMap(new HashMap<String, IPlotSession>());

	/**
	 * This class handles all plot clicks and mouse moves and forwards the relevant ones to
	 * all registered IPlotWidgetListener listeners
	 */
	private class PlotMessageHandler implements MessageHandler {
		private final ListenerList plotListeners = new ListenerList(ListenerList.IDENTITY);

		@Override
		public int processMessage(Message msg) {
			int retVal = Commandable.NORMAL;

			if ("STREAMSRI".equals(msg.name) && msg.info == 1) {
				activeSRI = (StreamSRI) msg.data;
			} else if ("MARK".equals(msg.name) && msg.info == 1) { //left click
				final Position p = (Position) msg.data;
				final Object[] listeners = this.plotListeners.getListeners();
				for (final Object obj : listeners) {
					final IPlotWidgetListener pl = (IPlotWidgetListener) obj;
					pl.click(p.x, p.y, p.t);
				}
			} else if ("MARK".equals(msg.name) && msg.info == 0) { //mouse move
				final Position p = (Position) msg.data;
				final Object[] listeners = this.plotListeners.getListeners();
				for (final Object obj : listeners) {
					final IPlotWidgetListener pl = (IPlotWidgetListener) obj;
					pl.motion(p.x, p.y, p.t);
				}
			} else if ("ZOOM".equals(msg.name)) { //left-click drag
				Double[] values = getValues(msg.data);
				if (values != null) {
					final Object[] listeners = this.plotListeners.getListeners();
					for (final Object obj : listeners) {
						final IPlotWidgetListener pl = (IPlotWidgetListener) obj;
						pl.zoomX(values[0], values[1], values[2], values[3], msg.data); // SUPPRESS CHECKSTYLE MagicNumber
					}
				}
			} else if ("ZOOMIN".equals(msg.name)) { //mousewheel
				Double[] values = getValues(msg.data);
				if (values != null) {
					final Object[] listeners = this.plotListeners.getListeners();
					for (final Object obj : listeners) {
						final IPlotWidgetListener pl = (IPlotWidgetListener) obj;
						pl.zoomIn(values[0], values[1], values[2], values[3], msg.data); // SUPPRESS CHECKSTYLE MagicNumber
					}
				}
			} else if ("DRAGBOX".equals(msg.name)) { //right-click drag
				final DragBox d = (DragBox) msg.data;
				final Object[] listeners = this.plotListeners.getListeners();
				for (final Object obj : listeners) {
					final IPlotWidgetListener pl = (IPlotWidgetListener) obj;
					pl.dragBox(d.getXMin(), d.getYMin(), d.getXMax(), d.getYMax());
				}
			} else if ("UNZOOM".equals(msg.name)) { //right-click
				Double[] values = getValues(msg.data);
				if (values != null) {
					final Object[] listeners = this.plotListeners.getListeners();
					for (final Object obj : listeners) {
						final IPlotWidgetListener pl = (IPlotWidgetListener) obj;
						pl.unzoom(values[0], values[1], values[2], values[3], msg.data); // SUPPRESS CHECKSTYLE MagicNumber
					}
				}
			} else if ("ZOOMOUT".equals(msg.name)) { //mousewheel
				Double[] values = getValues(msg.data);
				if (values != null) {
					final Object[] listeners = this.plotListeners.getListeners();
					for (final Object obj : listeners) {
						final IPlotWidgetListener pl = (IPlotWidgetListener) obj;
						pl.zoomOut(values[0], values[1], values[2], values[3], msg.data); // SUPPRESS CHECKSTYLE MagicNumber
					}
				}
			} else if ("PANXY".equals(msg.name)) { //middle drag
				final double[] d = (double[]) msg.data;
				final Object[] listeners = this.plotListeners.getListeners();
				for (final Object obj : listeners) {
					final IPlotWidgetListener pl = (IPlotWidgetListener) obj;
					pl.pan(d[0], d[2], d[1], d[3]); // SUPPRESS CHECKSTYLE MagicNumber
				}
			} else {
				if ("ERROR".equals(msg.name)) {
					StatusManager.getManager().handle(
						new Status(IStatus.ERROR, PlotActivator.PLUGIN_ID, "PlotMessageHandler got error message: " + msg.toString("+FROM")), StatusManager.LOG);
				}
				retVal = Commandable.NOOP;
			}

			return retVal;
		}

		private Double[] getValues(Object obj) {
			if (obj instanceof DragBox) {
				DragBox box = (DragBox) obj;
				return new Double[] { box.getXMin(), box.getYMin(), box.getXMax(), box.getYMax() };
			} else if (obj instanceof Table) {
				Table table = (Table) obj;
				Double[] values = new Double[4]; // SUPPRESS CHECKSTYLE MagicNumber
				values[0] = table.getD("X1"); // SUPPRESS CHECKSTYLE MagicNumber
				values[1] = table.getD("Y1"); // SUPPRESS CHECKSTYLE MagicNumber
				values[2] = table.getD("X2"); // SUPPRESS CHECKSTYLE MagicNumber
				values[3] = table.getD("Y2"); // SUPPRESS CHECKSTYLE MagicNumber
				return values;
			}
			return null;
		}

		public void addPlotListener(final IPlotWidgetListener listener) {
			this.plotListeners.add(listener);
		}

		public void removePlotListener(final IPlotWidgetListener listener) {
			this.plotListeners.remove(listener);
		}

	}

	private final PlotMessageHandler plotMessageHandler = new PlotMessageHandler();

	private final ListenerList messageHandlers = new ListenerList(ListenerList.IDENTITY);

	public AbstractNxmPlotWidget(final Composite parent, int style) {
		super(parent, style);
		addMessageHandler(this.plotMessageHandler);
		store.addPropertyChangeListener(listener);
	}

	public void addPlotListener(final IPlotWidgetListener listener) {
		this.plotMessageHandler.addPlotListener(listener);
	}

	public void removePlotListener(final IPlotWidgetListener listener) {
		this.plotMessageHandler.removePlotListener(listener);
	}

	public void addMessageHandler(final MessageHandler handler) {
		this.messageHandlers.add(handler);
	}

	public void removeMessageHandler(final MessageHandler handler) {
		this.messageHandlers.remove(handler);
	}

	protected int fireProcessMessage(final Message msg) {
		final int[] retVal = { Commandable.NORMAL };
		final Object[] listeners = this.messageHandlers.getListeners();
		for (final Object obj : listeners) {
			SafeRunnable.run(new ISafeRunnable() {

				@Override
				public void run() throws Exception {
					retVal[0] = ((MessageHandler) obj).processMessage(msg);
				}

				@Override
				public void handleException(final Throwable exception) {

				}
			});
		}
		return retVal[0];
	}

	public abstract String addDataFeature(Number xStart, Number xEnd, String color);

	/**
	 * @since 4.4
	 */
	public abstract String addFeatureByTypeMask(Number xStart, Number xEnd, Number yStart, Number yEnd, String typeMask, String color);

	/**
	 * @since 4.0
	 */
	public String addDragboxFeature(Number xmin, Number ymin, Number xmax, Number ymax, String color) {
		return null;
	}

	/**
	 * @since 4.0
	 */
	public void removeFeature(String featureid) {

	}

	/**
	 * This method initializes the plot by calling the plot command and any other NextMidas commands necessary for plotting.
	 * This method should be called once before any other commands are invoked.
	 * Any additional calls will be ignored.
	 * @param plotSwitches Switches to send to the plot command (MUST start with '/' if not empty or null).
	 * @param plotArgs Arguments to send to the plot command
	 */
	public final void initPlot(String plotSwitches, String plotArgs) {
		setPlotSwitches(plotSwitches);
		setPlotArgs(plotArgs);

		PlotType type = null;
		if (plotArgs != null) {
			String upperCase = plotArgs.toUpperCase();
			for (PlotType t : PlotType.values()) {
				if (upperCase.contains("TYPE=" + t)) {
					type = t;
					break;
				}
			}
		}

		if (type != null) {
			setPlotType(type);
		} else {
			setPlotType(PlotType.RASTER);
		}

		internalInitPlot(plotSwitches, plotArgs);
	}

	/**
	 * @since 4.4
	 */
	public void setPlotArgs(String plotArgs) {
		PlotPreferences.LAUNCH_ARGS.setValue(store, plotArgs);
		PlotPreferences.LAUNCH_ARGS_OVERRIDE.setValue(store, true);
	}

	/**
	 * @since 4.4
	 */
	public String getPlotArgs() {
		return PlotPreferences.LAUNCH_ARGS.getValue(store);
	}

	/**
	 * @since 4.4
	 */
	public boolean isSetPlotArgs() {
		return PlotPreferences.LAUNCH_ARGS_OVERRIDE.getValue(store);
	}

	/**
	 * @since 4.4
	 */
	public void unsetPlotArgs() {
		PlotPreferences.LAUNCH_ARGS.setToDefault(store);
		PlotPreferences.LAUNCH_ARGS_OVERRIDE.setValue(store, false);
	}

	/**
	 * @since 4.4
	 */
	public void setPlotSwitches(String plotSwitches) {
		PlotPreferences.LAUNCH_SWITCHES.setValue(store, plotSwitches);
		PlotPreferences.LAUNCH_SWITCHES_OVERRIDE.setValue(store, true);
	}

	/**
	 * @since 4.4
	 */
	public String getPlotSwitches() {
		return PlotPreferences.LAUNCH_SWITCHES.getValue(store);
	}

	/**
	 * @since 4.4
	 */
	public boolean isSetPlotSwitches() {
		return PlotPreferences.LAUNCH_SWITCHES_OVERRIDE.getValue(store);
	}

	/**
	 * @since 4.4
	 */
	public void unsetPlotSwitches() {
		PlotPreferences.LAUNCH_SWITCHES.setToDefault(store);
		PlotPreferences.LAUNCH_SWITCHES_OVERRIDE.setValue(store, false);
	}

	/**
	 * @since 4.2
	 */
	protected abstract void internalInitPlot(String plotSwtiches, String plotArgs);

	/**
	 * @since 4.4
	 */
	public abstract boolean isInitialized();

	/**
	 * Runs a command headlessly within the NmSession. TODO: renamed runServerCommand(..)
	 * <p>
	 * <b> DO NOT RUN PLOT OR SEND PLOT MESSAGES HERE</b>
	 * <p>
	 * This should be used to run source commands and additional filtering or processing commands before plotting.
	 * @param command headless nm command to run
	 */
	public abstract void runHeadlessCommand(String command);

	/**
	 * Runs a command headlessly within the NmSession (the server side for RAP).
	 * <p>
	 * <b> DO NOT RUN PLOT OR SEND PLOT MESSAGES HERE</b>
	 * <p>
	 * This should be used to run source commands and additional filtering or processing commands before plotting.
	 * @param command headless nm command to run
	 * @return Command that was executed
	 * @since 4.4
	 */
	public abstract Command runHeadlessCommandWithResult(String command);

	/**
	 * Runs a command on the client's NeXtMidas session.
	 * <p>
	 * <b> DO NOT RUN PLOT OR SEND PLOT MESSAGES HERE</b>
	 * <p>
	 * This should be used to run source commands and additional filtering or processing commands before plotting.
	 * @param command nm command to run on client machine
	 */
	public abstract void runClientCommand(String command);

	/**
	 * Runs a command within the global NeXtMidas session
	 * <p>
	 * <b> DO NOT RUN PLOT OR SEND PLOT MESSAGES HERE</b>
	 * <p>
	 * This should be used to run source commands and additional filtering or processing commands before plotting.
	 * @param command nm command to run in global NeXtMidas session
	 * @return Command that was executed
	 * @since 4.4
	 */
	public abstract Command runGlobalCommand(String command);

	/**
	 * @param sourcePipeId The nxm source pipe to add
	 * @deprecated Use {@link #addSource(String, String, IPlotSession)} instead
	 */
	@Deprecated
	public final void addSource(String sourcePipeId) {
		addSource(sourcePipeId, null);
	}

	/**
	 * @param sourcePipeId The nxm source pipe to add
	 * @param pipeQualifiers The pipe qualifiers to use for this source
	 * @deprecated Use {@link #addSource(String, String, IPlotSession)} instead
	 */
	@Deprecated
	public final void addSource(String sourcePipeId, String pipeQualifiers) {
		addSource(sourcePipeId, pipeQualifiers, null);
	}

	/**
	 * @since 4.2
	 */
	public final void addSource(String sourcePipeId, String qualifiers, IPlotSession session) {
		internalAddSource(sourcePipeId, qualifiers);
		inputSessions.put(sourcePipeId, session);
	}

	/**
	 * Add a source to the plot (e.g. open file on PLOT).
	 * @param sourcePipeId the pipe ID to add to this plot
	 * @param pipeQualifiers pipe qualifiers. Can be null.
	 * @since 4.2
	 */
	protected abstract void internalAddSource(String sourcePipeId, String pipeQualifiers);

	/**
	 * @return An unmodifiable list of the current plot sources
	 */
	public abstract Set<String> getSources();

	/**
	 * Removes all plot sources
	 */
	public void clearSources() {
		synchronized (inputSessions) {
			for (IPlotSession session : inputSessions.values()) {
				session.dispose();
			}
			inputSessions.clear();
		}

	}

	/**
	 * Remove a pipe source from the plot
	 * @param sourcePipeId remove a piped source from being plotted
	 */
	public void removeSource(String sourcePipeId) {
		IPlotSession session = inputSessions.remove(sourcePipeId);
		if (session != null) {
			session.dispose();
		} else {
			// PASS
			// TODO
			//			sendPlotMessage("CLOSEFILE", 0, sourcePipeId);
		}

	}

	/**
	 * Send the plot a message.
	 * @param msgName
	 */
	public abstract void sendPlotMessage(String msgName, int info, Object data);

	/**
	 * Configure plot settings.
	 * @param configuration
	 */
	public abstract void configurePlot(Map<String, String> configuration);

	/**
	 * Creates a unique name to be used for pipes for variables within the shared NeXtMidas session
	 * @return A new unique name for a pipe
	 * @since 4.0
	 */
	public static String createUniqueName() {
		return AbstractNxmPlotWidget.createUniqueName(true);
	}

	/**
	 * Creates a unique name to be used for pipes or commands for variables within the shared NeXtMidas session
	 * @return A new unique name
	 * @since 4.0
	 */
	public static String createUniqueName(boolean pipe) {
		if (pipe) {
			return "_UNIQUE_PIPE" + AbstractNxmPlotWidget.PIPE_NAME_INDEX.incrementAndGet();
		} else {
			return "UNIQUE_NAME" + AbstractNxmPlotWidget.NAME_INDEX.incrementAndGet();
		}
	}

	public StreamSRI getActiveSRI() {
		return activeSRI;
	}

	/**
	 * @since 4.2
	 */
	protected void setActiveSRI(StreamSRI newSRI) {
		activeSRI = newSRI;
	}

	/**
	 * @since 4.4, was added in 4.2 as protected
	 */
	public PlotMessageHandler getPlotMessageHandler() {
		return plotMessageHandler;
	}

	/** <b>INTERNAL USE ONLY</b>
	 * @noreference This method is not intended to be referenced by clients.
	 * Send the a  message to specified command running on server/processing side.
	 * @param cmdID   ID of command to send message to
	 * @param msgName name of message
	 * @param info    info of message (normally 0)
	 * @param data    data of message
	 * @param quals   quals of message
	 * @since 4.2
	 */
	public abstract void sendMessageToCommand(String cmdID, String msgName, int info, Object data, Object quals);

	/**
	 * FOR INTERNAL USE ONLY.
	 * @return null, unless implemented in subclass (e.g. RCPNxmPlotWidget) that have direct reference to the PLOT command.
	 * @since 4.4
	 */
	@Nullable
	protected plot getPlot() {
		return null;
	}

	/**
	 * Get a copy of current Plot Settings
	 * @since 4.2
	 */
	public PlotSettings getPlotSettings() {
		// TODO should we pull it from the plot?
		//		plot curPlot = getPlot();
		//		if (curPlot != null) {
		//			String curPlotModeStr = curPlot.MP.getMode();
		//			if (!"".equals(curPlotModeStr)) {
		//				plotSettings.setPlotMode(PlotMode.of(curPlotModeStr));
		//			}
		//			String curPlotTypeStr = curPlot.getPlotType();
		//			if (!"".equals(curPlotTypeStr)) {
		//				plotSettings.setPlotType(PlotType.valueOf(curPlotTypeStr));
		//			}
		//		}
		return new PlotSettings(store);
	}

	/**
	 * @since 4.2
	 */
	@Override
	public void dispose() {
		super.dispose();
		clearSources();
		store.removePropertyChangeListener(listener);
	}

	/**
	 * @since 4.4
	 */
	public double getMinValue() {
		return PlotPreferences.MIN.getValue(store);
	}

	/**
	 * @since 4.4
	 */
	public void setMinValue(double minValue) {
		PlotPreferences.MIN.setValue(store, minValue);
		PlotPreferences.MIN_OVERRIDE.setValue(store, true);
	}

	/**
	 * @since 4.4
	 */
	public void unsetMinValue() {
		PlotPreferences.MIN.setToDefault(store);
		PlotPreferences.MIN_OVERRIDE.setValue(store, false);
	}

	/**
	 * @since 4.4
	 */
	public boolean isSetMinValue() {
		return PlotPreferences.MIN_OVERRIDE.getValue(store);
	}

	/**
	 * @since 4.4
	 */
	public double getMaxValue() {
		return PlotPreferences.MAX.getValue(store);
	}

	/**
	 * @since 4.4
	 */
	public void setMaxValue(double maxValue) {
		PlotPreferences.MAX.setValue(store, maxValue);
		PlotPreferences.MAX_OVERRIDE.setValue(store, true);
	}

	/**
	 * @since 4.4
	 */
	public void unsetMaxValue() {
		PlotPreferences.MAX.setToDefault(store);
		PlotPreferences.MAX_OVERRIDE.setValue(store, false);
	}

	/**
	 * @since 4.4
	 */
	public boolean isSetMaxValue() {
		return PlotPreferences.MAX_OVERRIDE.getValue(store);
	}

	/**
	 * @since 4.4
	 */
	public int getFrameSize() {
		return PlotPreferences.FRAMESIZE.getValue(store);
	}

	/**
	 * @since 4.4
	 */
	public void setFrameSize(int fs) {
		PlotPreferences.FRAMESIZE.setValue(store, fs);
		PlotPreferences.FRAMESIZE_OVERRIDE.setValue(store, true);
	}

	/**
	 * @since 4.4
	 */
	public void unsetFrameSize() {
		PlotPreferences.FRAMESIZE.setToDefault(store);
		PlotPreferences.FRAMESIZE_OVERRIDE.setValue(store, false);
	}

	/**
	 * @since 4.4
	 */
	public boolean isSetFrameSize() {
		return PlotPreferences.FRAMESIZE_OVERRIDE.getValue(store);
	}

	/**
	 * @since 4.4
	 */
	public PlotType getPlotType() {
		return PlotType.valueOf(PlotPreferences.TYPE.getValue(store));
	}

	/**
	 * @since 4.4
	 */
	public void setPlotType(PlotType plotType) {
		PlotPreferences.TYPE.setValue(store, plotType.toString());
	}

	/**
	 * @since 4.4
	 */
	public PlotMode getPlotMode() {
		return PlotMode.valueOf(PlotPreferences.MODE.getValue(store));
	}

	/**
	 * @since 4.4
	 */
	public void setPlotMode(PlotMode plotMode) {
		PlotPreferences.MODE.setValue(store, plotMode.toString());
	}

	/**
	 * @since 4.4
	 */
	public boolean isEnablePlotMenu() {
		return PlotPreferences.ENABLE_CONFIGURE_MENU_USING_MOUSE.getValue(store);
	}

	/**
	 * @since 4.4
	 */
	public void setEnablePlotMenu(boolean enablePlotMenu) {
		PlotPreferences.ENABLE_CONFIGURE_MENU_USING_MOUSE.setValue(store, enablePlotMenu);
	}

	/**
	 * @noreference This method is not intended to be referenced by clients.
	 * @param custom plot settings to apply
	 * @since 4.2
	 */
	public void applySettings(PlotSettings settings) {
		if (settings != null) {

			Double minVal = settings.getMinValue();
			if (minVal != null) {
				setMinValue(minVal);
			}

			Double maxVal = settings.getMaxValue();
			if (maxVal != null) {
				setMaxValue(maxVal);
			}

			PlotType plotType = settings.getPlotType();
			if (plotType != null) {
				setPlotType(plotType);
			}

			final PlotMode plotMode = settings.getPlotMode();
			if (plotMode != getPlotMode()) {
				setPlotMode(plotMode);
			}

			final Boolean enablePlotMenu = settings.getEnablePlotMenu();
			if (enablePlotMenu != null) {
				setEnablePlotMenu(enablePlotMenu);
			}

			updateCorbaReceiverSettings(settings);

		}
	}

	/**
	 * apply frame size and sample rate settings change to CORBARECEIVERs
	 * @param settings
	 * @deprecated  begin adjust CORBARECEIVER setting
	 */
	@Deprecated
	private void updateCorbaReceiverSettings(PlotSettings settings) {
		final Boolean blockingOption = settings.getBlockingOption();
		Integer subsize = settings.getFrameSize();
		Double sampleRate = settings.getSampleRate();

		Table msgData = new Table();
		boolean overrideSampleRate = (sampleRate != null);
		boolean overrideSubSize = (subsize != null);
		if (!overrideSubSize) {
			subsize = -1; // -1 tells CORBARECEIVER to use it's orig frame size value
		}
		msgData.put("OVERRIDESRISUBSIZE", overrideSubSize);
		msgData.put("FRAMESIZE", subsize);
		msgData.put("OVERRIDESRISAMPLERATE", overrideSampleRate);
		if (overrideSampleRate) {
			msgData.put("SAMPLERATE", sampleRate);
		}
		if (blockingOption != null) {
			msgData.put("BLOCKING", blockingOption);
		}
		for (IPlotSession session : inputSessions.values()) {
			if (session instanceof PlotSession) {
				String cmdID = ((PlotSession) session).getCommandId();
				sendMessageToCommand(cmdID, "CHANGE_CORBARECEIVER_SETTINGS", 0, msgData, null);
			}
		}
	}

	/**
	 * @noreference This method is not intended to be referenced by clients.
	 * @param custom FFT settings to apply
	 * @since 4.4
	 * @deprecated since 4.4 use FftNxmBlock.applySettings(...) instead
	 */
	@Deprecated
	public void applyFftSettings(FftSettings fftSettings) {
		if (fftSettings != null) {
			Table msgData = new Table();
			Table fftSettingsTbl = new Table();
			fftSettingsTbl.put("OVERLAP", (Double.parseDouble(fftSettings.getOverlap()) / 100.0));
			fftSettingsTbl.put("NEXP", fftSettings.getNumAverages());
			fftSettingsTbl.put("WINDOW", fftSettings.getWindow());
			// fftSettings.getOutputType(); // cannot change: output type (NORMAL, PSD, MAG, MAG & LOG, PSD & LOG) on FFT at this time
			fftSettingsTbl.put("NFFT", fftSettings.getTransformSize()); // do this last as this can cause a restart
			msgData.put("FFT", fftSettingsTbl);

			for (IPlotSession session : inputSessions.values()) {
				if (session instanceof PlotSession) {
					String cmdID = ((PlotSession) session).getCommandId();
					sendMessageToCommand(cmdID, "CHANGE_SETTINGS", 0, msgData, null);
				}
			}
		}
	}

	/**
	 * @since 4.4
	 */
	public IPreferenceStore getPreferenceStore() {
		return this.store;
	}

	/**
	 * @since 4.4
	 */
	protected void handlePropertyChange(PropertyChangeEvent event) {
		if (PlotPreferences.ENABLE_CONFIGURE_MENU_USING_MOUSE.isEvent(event)) {
			updateMenu();
		}

		if (PlotPreferences.LAUNCH_ARGS.isEvent(event) || PlotPreferences.LAUNCH_ARGS_OVERRIDE.isEvent(event)) {
			updateLaunchArgs();
		}

		if (PlotPreferences.LAUNCH_SWITCHES.isEvent(event) || PlotPreferences.LAUNCH_SWITCHES_OVERRIDE.isEvent(event)) {
			updateLaunchSwitches();
		}

		if (PlotPreferences.MIN.isEvent(event) || PlotPreferences.MIN_OVERRIDE.isEvent(event) || PlotPreferences.MAX.isEvent(event)
				|| PlotPreferences.MAX_OVERRIDE.isEvent(event)) {
			updateScale();
		}

		if (PlotPreferences.MODE.isEvent(event)) {
			updateMode();
		}

		if (PlotPreferences.TYPE.isEvent(event)) {
			updateType();
		}
	}

	private void updateLaunchSwitches() {
		// TODO Auto-generated method stub

	}

	private void updateLaunchArgs() {
		// TODO Auto-generated method stub

	}

	private void updateType() {
		sendPlotMessage("SET.PlotType", 0, getPlotType().toString());
	}

	private void updateMode() {
		if (getPlotMode().toModeString() != null && !getPlotMode().toModeString().isEmpty()) {
			sendPlotMessage("SET.MODE", 0, getPlotMode().toModeString());
		} else {
			// PASS
			// TODO Set back to auto mode
		}
	}

	private void updateMenu() {
		String newValue = (isEnablePlotMenu()) ? "-NoMiddleMouse" : "+NoMiddleMouse";
		sendPlotMessage("SET.MW.EventFilter", 0, newValue);
	}

	private void updateScale() {
		final boolean isRaster = PlotType.RASTER.equals(getPlotType());
		String plotScaleSetting;
		String plotMinProperty = null;
		String plotMaxProperty = null;
		if (isSetMinValue()) {
			if (isRaster) {
				plotMinProperty = "SET.Z1";
			} else {
				plotMinProperty = "SET.Y1";
			}
			plotScaleSetting = "-AutoMin";
		} else {
			plotScaleSetting = "+AutoMin";
		}
		if (isSetMaxValue()) {
			if (isRaster) {
				plotMaxProperty = "SET.Z2";
			} else {
				plotMaxProperty = "SET.Y2";
			}
			plotScaleSetting += "|-AutoMax";
		} else {
			plotScaleSetting += "|+AutoMax";
		}
		sendPlotMessage("SET.SCALE", 0, plotScaleSetting); // should set scale setting before setting min/max
		if (plotMinProperty != null) {
			sendPlotMessage(plotMinProperty, 0, getMinValue());
		}
		if (plotMaxProperty != null) {
			sendPlotMessage(plotMaxProperty, 0, getMaxValue());
		}

	}
}

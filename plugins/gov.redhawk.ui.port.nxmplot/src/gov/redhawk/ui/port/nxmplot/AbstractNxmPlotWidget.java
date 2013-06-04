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

import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import nxm.sys.inc.Commandable;
import nxm.sys.inc.MessageHandler;
import nxm.sys.lib.Message;
import nxm.sys.lib.Position;
import nxm.sys.lib.Table;
import nxm.sys.libg.DragBox;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.statushandlers.StatusManager;

import BULKIO.StreamSRI;

/**
 * @since 3.0
 */
public abstract class AbstractNxmPlotWidget extends Composite {

	private static final long serialVersionUID = -5616513930191161990L;
	private StreamSRI activeSRI;

	/**
	 * This class handles all plot clicks and mouse moves and forwards the relevant ones to
	 * all registered IPlotWidgetListener listeners
	 */
	private class PlotMessageHandler implements MessageHandler {
		private final ListenerList plotListeners = new ListenerList(ListenerList.IDENTITY);

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
				final double[] d =  (double[]) msg.data;
				final Object[] listeners = this.plotListeners.getListeners();
				for (final Object obj : listeners) {
					final IPlotWidgetListener pl = (IPlotWidgetListener) obj;
					pl.pan(d[0], d[2], d[1], d[3]); // SUPPRESS CHECKSTYLE MagicNumber
				}
			} else {
				if ("ERROR".equals(msg.name)) {
					StatusManager.getManager().handle(
							new Status(IStatus.ERROR, PlotActivator.PLUGIN_ID, "PlotMessageHandler got error message: " + msg.toString("+FROM")),
							StatusManager.LOG);
				}
				retVal = Commandable.NOOP;
			}

			return retVal;
		}

		private Double[] getValues(Object obj) {
			if (obj instanceof DragBox) {
				DragBox box = (DragBox) obj;
				return new Double[]{box.getXMin(), box.getYMin(), box.getXMax(), box.getYMax()};
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

	/** @deprecated since 4.2 use {@link #getPlotMessageHandler()} */
	@Deprecated // TODO: this should become a private field in next major version (5.0)
	protected final PlotMessageHandler plotMessageHandler = new PlotMessageHandler();

	private final ListenerList messageHandlers = new ListenerList(ListenerList.IDENTITY);

	public AbstractNxmPlotWidget(final Composite parent, int style) {
		super(parent, style);
		addMessageHandler(this.plotMessageHandler);
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
		final int[] retVal = {Commandable.NORMAL};
		final Object[] listeners = this.messageHandlers.getListeners();
		for (final Object obj : listeners) {
			SafeRunnable.run(new ISafeRunnable() {

				public void run() throws Exception {
					retVal[0] = ((MessageHandler) obj).processMessage(msg);
				}

				public void handleException(final Throwable exception) {

				}
			});
		}
		return retVal[0];
	}

	public abstract String addDataFeature(Number xStart, Number xEnd, String color);

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
	 * @param plotSwtiches Switches to send to the plot command
	 * @param plotArgs Arguments to send to the plot command
	 */
	public abstract void initPlot(String plotSwtiches, String plotArgs);

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
	 * Runs a command on the client's NeXtMidas session.
	 * <p>
	 * <b> DO NOT RUN PLOT OR SEND PLOT MESSAGES HERE</b>
	 * <p>
	 * This should be used to run source commands and additional filtering or processing commands before plotting.
	 * @param command nm command to run on client machine
	 */
	public abstract void runClientCommand(String command);

	/**
	 * Use {@Link #addSource(String, String)} to specify plot qualifiers. This method
	 * will not work properly if a qualifier is concatenated to the sourceId
	 * Add a source to the plot
	 * @param sourcePipeId the pipe ID to add to this plot
	 */
	public abstract void addSource(String sourcePipeId);

	/**
	 * Add a source to the plot
	 * @param sourcePipeId the pipe ID to add to this plot
	 * @param qualifiers plot qualifiers. Can be null.
	 */
	public abstract void addSource(String sourcePipeId, String qualifiers);

	/**
	 * @return An unmodifiable list of the current plot sources
	 */
	public abstract Set<String> getSources();

	/**
	 * Removes all plot sources
	 */
	public abstract void clearSources();

	/**
	 * Remove a pipe source from the plot
	 * @param sourcePipeId remove a piped source from being plotted
	 */
	public abstract void removeSource(String sourcePipeId);

	/**
	 * Send the plot a message
	 * @param msgName
	 */
	public abstract void sendPlotMessage(String msgName, int info, Object data);

	/**
	 * Configure plot settings
	 * @param configuration
	 */
	public abstract void configurePlot(Map<String, String> configuration);

	private static final AtomicInteger NAME_INDEX = new AtomicInteger();

	/**
	 * Creates a unique name to be used for pipes for variables within the shared NeXtMidas session
	 * @return A new unique name
	 * @since 4.0
	 */
	public static String createUniqueName() {
		return createUniqueName(true);
	}

	/**
	 * Creates a unique name to be used for pipes or commands for variables within the shared NeXtMidas session
	 * @return A new unique name
	 * @since 4.0
	 */
	public static String createUniqueName(boolean pipe) {
		if (pipe) {
			return "_UNIQUE_PIPE" + NAME_INDEX.incrementAndGet();
		} else {
			return "UNIQUE_NAME" + NAME_INDEX.incrementAndGet();
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

	/** @since 4.2 */
	protected PlotMessageHandler getPlotMessageHandler() {
		return plotMessageHandler;
	}

}

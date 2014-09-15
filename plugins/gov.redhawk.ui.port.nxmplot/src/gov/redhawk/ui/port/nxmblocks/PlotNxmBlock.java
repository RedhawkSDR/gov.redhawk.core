/*******************************************************************************
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.ui.port.nxmblocks;

import gov.redhawk.internal.ui.preferences.PlotPreferencePage;
import gov.redhawk.sca.util.Debug;
import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.PlotType;
import gov.redhawk.ui.port.nxmplot.preferences.PlotPreferences;
import gov.redhawk.ui.port.nxmplot.preferences.Preference;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import nxm.sys.lib.NeXtMidas;
import nxm.sys.lib.Table;
import nxm.sys.prim.plot;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.PlatformUI;

import BULKIO.StreamSRI;

/**
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class PlotNxmBlock extends AbstractNxmBlock<plot> {

	private static final Debug TRACE_LOG = new Debug(PlotActivator.PLUGIN_ID, PlotNxmBlock.class.getSimpleName());
	/** zero-length arrays are immutable. For more info see Effective Java, Item 27: Return zero-length arrays, not null. */
	private static final StreamSRI[] EMPTY_STREAMSRI_ARRAY = new StreamSRI[0];

	/** using a "synchronized" LinkedHashMap to keep order of launched streams. */
	private Map<String, StreamSRI> streamIdToSriMap = Collections.synchronizedMap(new LinkedHashMap<String, StreamSRI>());
	private ConcurrentHashMap<String, String> streamIdToSourceNameMap = new ConcurrentHashMap<String, String>();
	private ConcurrentHashMap<String, Boolean> streamIdToIsHidden = new ConcurrentHashMap<String, Boolean>();
	private IMenuManager menu;

	public PlotNxmBlock(@NonNull AbstractNxmPlotWidget plotWidget) {
		this(plotWidget, PlotNxmBlock.createInitStore(), null);
	}

	public PlotNxmBlock(@NonNull AbstractNxmPlotWidget plotWidget, PlotNxmBlockSettings settings) {
		this(plotWidget, PlotNxmBlock.createInitStore(), settings);
	}

	public PlotNxmBlock(@NonNull AbstractNxmPlotWidget plotWidget, @NonNull IPreferenceStore store) {
		this(plotWidget, store, null);
	}

	public PlotNxmBlock(@NonNull AbstractNxmPlotWidget plotWidget, @NonNull IPreferenceStore store, PlotNxmBlockSettings settings) {
		super(plot.class, plotWidget, store);
		if (settings != null) {
			applySettings(settings);
		}
	}

	public static IPreferenceStore createInitStore() {
		return Preference.initStoreFromWorkbench(PlotPreferences.getAllPreferences());
	}

	@Override
	public int getMaxInputs() {
		return 1; // only one is support here, create multiple instances for multiple ports
	}

	@Override
	public int getMaxOutputs() {
		return 0; // this is the end point to plot so it has no outputs
	}

	@Override
	public List<String> getStreamIDs() {
		return Collections.unmodifiableList(new ArrayList<String>(streamIdToSriMap.keySet()));
	}

	@Override
	public StreamSRI[] getLaunchedStreams() {
		StreamSRI[] retval = streamIdToSriMap.values().toArray(EMPTY_STREAMSRI_ARRAY);
		return retval;
	}

	@Override
	public void launch(String streamID, StreamSRI sri) {
		PlotNxmBlock.TRACE_LOG.enteringMethod(streamID, sri);
		checkLaunchParams(streamID, sri);

		BlockIndexPair inputBlockInfo = this.getInputBlockInfo(0);
		if (inputBlockInfo == null) {
			throw new IllegalStateException("A input index 0 must be set before launch() can be called.");
		}

		final AbstractNxmPlotWidget currentPlotWidget = getContext();
		final String sourceName = inputBlockInfo.getBlock().getOutputName(inputBlockInfo.getIndex(), streamID);

		final String pipeQuals = getPipeQualifiers(sri, currentPlotWidget, sourceName);
		currentPlotWidget.addSource(sourceName, pipeQuals, null);

		streamIdToSourceNameMap.put(streamID, sourceName); // save mapping for shutdown, apply settings, etc.
		streamIdToSriMap.put(streamID, sri);

		final IMenuManager menuManager = this.menu;
		if (menuManager != null) {
			IAction action = new Action(streamID, IAction.AS_CHECK_BOX) {
				@Override
				public void run() {
					String layerEnableOption = isChecked() ? "+GLOBAL" : "-GLOBAL"; // show/hide layer (i.e source stream)
					String setViaMsgName = "SET.LAYERS." + sourceName + ".enable";
					currentPlotWidget.sendPlotMessage(setViaMsgName, 0, layerEnableOption);
				}
			};
			action.setChecked(true);
			action.setId(streamID);
			menuManager.add(action);
		}
		// FYI: this is end point, so it DOES NOT have any follow on blocks
		PlotNxmBlock.TRACE_LOG.exitingMethod(streamID);
	}

	@Override
	public void shutdown(final String streamID) {
		PlotNxmBlock.TRACE_LOG.enteringMethod(streamID);
		final AbstractNxmPlotWidget currentPlotWidget = getContext();
		if (currentPlotWidget == null) {
			throw new IllegalStateException("A context (AbstractNxmPlotWidget) must be set before shutdown() can be called.");
		}

		String sourceName = streamIdToSourceNameMap.remove(streamID);
		if (sourceName != null) {
			currentPlotWidget.removeSource(sourceName);
		}
		streamIdToSriMap.remove(streamID);

		final IMenuManager menuManager = this.menu;
		if (menuManager != null) {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					menuManager.remove(streamID);
				}
			});

			//			new UIJob("remove stream from menu") {
			//				@Override
			//				public IStatus runInUIThread(IProgressMonitor monitor) {
			//					menuManager.remove(streamID);
			//					return Status.OK_STATUS;
			//				}
			//			}
			//			.schedule(0);
		}
	}

	@Override
	public void stop() {
		PlotNxmBlock.TRACE_LOG.enteringMethod("curSize=" + streamIdToSourceNameMap.size());
		final AbstractNxmPlotWidget currentPlotWidget = getContext();
		if (currentPlotWidget == null) {
			throw new IllegalStateException("A context (AbstractNxmPlotWidget) must be set before stop() can be called.");
		}

		// remove all our streamIDs from plot widget
		Iterator<String> valuesIter = streamIdToSourceNameMap.values().iterator();
		while (valuesIter.hasNext()) {
			String sourceName = valuesIter.next();
			currentPlotWidget.removeSource(sourceName);
			valuesIter.remove();
		}
		streamIdToSriMap.clear();
	}

	public PlotNxmBlockSettings getSettings() {
		return new PlotNxmBlockSettings(getPreferences());
	}

	public void applySettings(PlotNxmBlockSettings newSettings) {
		if (newSettings.getFrameSize() != null) {
			setFrameSize(newSettings.getFrameSize());
		}

		if (newSettings.getPipeSize() != null) {
			setPipeSize(newSettings.getPipeSize());
		}

		if (newSettings.getLinePlotConsumeLength() != null) {
			setLinePlotConsumeLength(newSettings.getLinePlotConsumeLength());
		}
		
		if (newSettings.getRefreshRate() != null) {
			setRefreshRate(newSettings.getRefreshRate());
		}
	}

	@Override
	protected String formCmdLine(AbstractNxmPlotWidget plotWidget, String streamID) {
		return null; // null for no Command to execute
	}

	private String getPipeQualifiers(StreamSRI sri, AbstractNxmPlotWidget plotWidget, String sourceName) {
		StringBuilder pipeQualifiers = new StringBuilder();
		int frameSize = getFrameSize();
		if (isSetFrameSize()) { // 1. override frame size with value in settings
			if (frameSize <= 1) { // PANIC!! Bad value, use a value so at least something comes up
				frameSize = 1024;
			}
			pipeQualifiers.append("{FRAMESIZE=").append(frameSize).append('}');
		} else { // Frame size not overridden
			if (sri != null) { // 2. check sri.subsize
				frameSize = sri.subsize;
			}
			String tmpResName = AbstractNxmPlotWidget.createUniqueName(false);
			plotWidget.runGlobalCommand("TABLE " + tmpResName + " CREATE");
			plotWidget.runGlobalCommand("STATUS " + sourceName + " typeCodeClass=" + tmpResName + ".TYPECODECLASS  frameSize=" + tmpResName
				+ ".FRAMESIZE");
			if (PlotNxmBlock.TRACE_LOG.enabled) {
				plotWidget.runGlobalCommand("RESULTS/ALL " + tmpResName);
				plotWidget.runGlobalCommand("STATUS/VERBOSE " + sourceName);
			}
			plotWidget.runGlobalCommand("RESULTS/GLOBAL " + tmpResName + " " + tmpResName); // put in global results table
			Table statusResults = NeXtMidas.getGlobalInstance().getMidasContext().getResults().getTable(tmpResName);
			plotWidget.runGlobalCommand("REMOVE " + tmpResName); // cleanup tmp results
			plotWidget.runGlobalCommand("REMOVE/GLOBAL " + tmpResName); // cleanup tmp results
			int typeCodeClass = 1;
			if (statusResults != null) {
				typeCodeClass = statusResults.getL("TYPECODECLASS", typeCodeClass);
				frameSize = statusResults.getL("FRAMESIZE", frameSize);
			}
			if (typeCodeClass == 1 && frameSize <= 1) { // 3. no frame size and type 1000 data
				frameSize = PlotPreferences.FRAMESIZE.getDefaultValue(getPreferences()); // Revert to using workbench default
				if (frameSize <= 1) { // PANIC!! Bad global default, use a value so at least something comes up
					frameSize = 1024;
				}
				pipeQualifiers.append("{FRAMESIZE=" + frameSize + "}"); //    frame to workbench default
			}
		}

		if (isSetPipeSize()) {
			int pipeSize = getPipeSize();
			pipeQualifiers.append("{PIPESIZE=").append(pipeSize).append('}');
		}
		
		// only apply consume length (quick data thinning for line plot)
		if (PlotType.LINE.equals(plotWidget.getPlotType())) {
			int consumeLength;
			if (isSetLinePlotConsumeLength()) {
				consumeLength = getLinePlotConsumeLength();
			} else {
				consumeLength = PlotPreferences.LINE_PLOT_CONSUMELENGTH.getDefaultValue(getPreferences());
			}
			if (consumeLength > 0) {
				pipeQualifiers.append("{CL=").append(consumeLength).append('}');
			}
		}

		// screen frames per second (FPS) / refresh rate smart thinning of input data stream
		int refreshRate;
		if (isSetRefreshRate()) {
			refreshRate = getRefreshRate();
		} else {
			refreshRate = PlotPreferences.REFRESH_RATE.getDefaultValue(getPreferences());
		}
		if (refreshRate >= 0) {
			pipeQualifiers.append("{LAYER={REFRESHRATE=").append(refreshRate).append("}}");
		}
		
		PlotNxmBlock.TRACE_LOG.exitingMethod(pipeQualifiers);
		return pipeQualifiers.toString();
	}

	public int getLinePlotConsumeLength() {
		return PlotPreferences.LINE_PLOT_CONSUMELENGTH.getValue(getPreferences());
	}

	public boolean isSetLinePlotConsumeLength() {
		return PlotPreferences.LINE_PLOT_CONSUMELENGTH_OVERRIDE.getValue(getPreferences());
	}

	public void unsetLinePlotConsumeLength() {
		PlotPreferences.LINE_PLOT_CONSUMELENGTH.setToDefault(getPreferences());
		PlotPreferences.LINE_PLOT_CONSUMELENGTH_OVERRIDE.setValue(getPreferences(), false);
	}

	/** set to -1 to NOT do any frame thinning of line plots */
	public void setLinePlotConsumeLength(int val) {
		PlotPreferences.LINE_PLOT_CONSUMELENGTH.setValue(getPreferences(), val);
		PlotPreferences.LINE_PLOT_CONSUMELENGTH_OVERRIDE.setValue(getPreferences(), true);
	}

	public int getPipeSize() {
		return PlotPreferences.PIPESIZE.getValue(getPreferences());
	}

	public boolean isSetPipeSize() {
		return PlotPreferences.PIPESIZE_OVERRIDE.getValue(getPreferences());
	}

	public void unsetPipeSize() {
		PlotPreferences.PIPESIZE.setToDefault(getPreferences());
		PlotPreferences.PIPESIZE_OVERRIDE.setValue(getPreferences(), false);
	}

	public void setPipeSize(int pipeSize) {
		PlotPreferences.PIPESIZE.setValue(getPreferences(), pipeSize);
		PlotPreferences.PIPESIZE_OVERRIDE.setValue(getPreferences(), true);
	}

	public int getFrameSize() {
		return PlotPreferences.FRAMESIZE.getValue(getPreferences());
	}

	public boolean isSetFrameSize() {
		return PlotPreferences.FRAMESIZE_OVERRIDE.getValue(getPreferences());
	}

	public void unsetFrameSize() {
		PlotPreferences.FRAMESIZE.setToDefault(getPreferences());
		PlotPreferences.FRAMESIZE_OVERRIDE.setValue(getPreferences(), false);
	}

	public void setFrameSize(int fs) {
		PlotPreferences.FRAMESIZE.setValue(getPreferences(), fs);
		PlotPreferences.FRAMESIZE_OVERRIDE.setValue(getPreferences(), true);
	}

	public int getRefreshRate() {
		return PlotPreferences.REFRESH_RATE.getValue(getPreferences());
	}

	public boolean isSetRefreshRate() {
		return PlotPreferences.REFRESH_RATE_OVERRIDE.getValue(getPreferences());
	}

	public void unsetRefreshRate() {
		PlotPreferences.REFRESH_RATE.setToDefault(getPreferences());
		PlotPreferences.REFRESH_RATE_OVERRIDE.setValue(getPreferences(), false);
	}

	/** set to 0 to NOT do any smart thinning of plots based on desired refresh rate. */
	public void setRefreshRate(int val) {
		PlotPreferences.REFRESH_RATE.setValue(getPreferences(), val);
		PlotPreferences.REFRESH_RATE_OVERRIDE.setValue(getPreferences(), true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if ((PlotPreferences.FRAMESIZE.isEvent(event) || PlotPreferences.FRAMESIZE_OVERRIDE.isEvent(event)) && isSetFrameSize()) {
			updatePipeQualifiers();
		}

		if (PlotPreferences.FRAMESIZE_OVERRIDE.isEvent(event) && getFrameSize() != PlotPreferences.FRAMESIZE.getDefaultValue(getPreferences())) {
			updatePipeQualifiers();
		}

		if (PlotPreferences.PIPESIZE.isEvent(event) && isSetPipeSize()) {
			updatePipeQualifiers();
		}

		if (PlotPreferences.PIPESIZE_OVERRIDE.isEvent(event) && getPipeSize() != PlotPreferences.PIPESIZE.getDefaultValue(getPreferences())) {
			updatePipeQualifiers();
		}

		if (PlotPreferences.LINE_PLOT_CONSUMELENGTH.isEvent(event) && isSetLinePlotConsumeLength()
				&& PlotType.LINE.equals(getContext().getPlotType())) {
			updatePipeQualifiers();
		}
		
		if (PlotPreferences.LINE_PLOT_CONSUMELENGTH_OVERRIDE.isEvent(event)
				&& getLinePlotConsumeLength() != PlotPreferences.LINE_PLOT_CONSUMELENGTH.getDefaultValue(getPreferences())
				&& PlotType.LINE.equals(getContext().getPlotType())) {
			updatePipeQualifiers();
		}

		if (PlotPreferences.REFRESH_RATE.isEvent(event) && isSetRefreshRate()) {
			setPropertyOnAllLayers("REFRESHRATE", 0, "" + getRefreshRate()); 
			// above works better than calling calling updatePipeQualifiers() (don't have to remove/add source)
		}

		if (PlotPreferences.REFRESH_RATE_OVERRIDE.isEvent(event)
				&& getRefreshRate() != PlotPreferences.REFRESH_RATE.getDefaultValue(getPreferences())) {
			setPropertyOnAllLayers("REFRESHRATE", 0, "" + getRefreshRate());
			// above works better than calling calling updatePipeQualifiers() (don't have to remove/add source)
		}
	}

	private void updatePipeQualifiers() {
		Iterator<String> keyIter = streamIdToSourceNameMap.keySet().iterator();
		while (keyIter.hasNext()) {
			String streamId = keyIter.next();
			String sourceName = streamIdToSourceNameMap.get(streamId);
			StreamSRI sri = streamIdToSriMap.get(streamId);

			AbstractNxmPlotWidget plotWidget = getContext();
			String pipeQuals = getPipeQualifiers(sri, plotWidget, sourceName);
			plotWidget.removeSource(sourceName);
			plotWidget.addSource(sourceName, pipeQuals, null);
		}
	}

	@Override
	public IPreferencePage createPreferencePage() {
		PlotPreferencePage retVal = new PlotPreferencePage("Data", true);
		retVal.setPreferenceStore(getPreferences());
		return retVal;
	}

	/** set property of all layers (streams) on this block by sending a message so that this can work in both RCP and RAP. */
	void setPropertyOnAllLayers(String properyName, int info, String data) {
		AbstractNxmPlotWidget plotWidget = getContext();
		Iterator<String> keyIter = streamIdToSourceNameMap.keySet().iterator();
		while (keyIter.hasNext()) {
			String streamId = keyIter.next();
			String sourceName = streamIdToSourceNameMap.get(streamId);
			plotWidget.sendPlotMessage("SET.LAYERS." + sourceName + "." + properyName, info, data);
		}
	}
	
	/** set property of a single layer (stream) on this block by sending a message so that this can work in both RCP and RAP. */
	void setPropertyOnLayer(String streamId, String properyName, int info, String data) {
		AbstractNxmPlotWidget plotWidget = getContext();
		String sourceName = streamIdToSourceNameMap.get(streamId);
		plotWidget.setPropertyOnLayer(sourceName, properyName, 0, data);
	}
	
	/** hide all streams on PLOT */
	public void hide() {
		Iterator<String> keyIter = streamIdToSourceNameMap.keySet().iterator();
		while (keyIter.hasNext()) {
			String streamId = keyIter.next();
			streamIdToIsHidden.put(streamId, Boolean.TRUE);
		}
		setPropertyOnAllLayers("ENABLE", 0, "-GLOBAL");
	}

	/** show all streams on PLOT */
	public void show() {
		streamIdToIsHidden.clear();
		setPropertyOnAllLayers("ENABLE", 0, "+GLOBAL");
	}
	
	/** hide specified Stream ID on PLOT.
	 *  @since 5.0 
	 */
	public void hideStream(@NonNull String streamId) {
		streamIdToIsHidden.put(streamId, Boolean.TRUE);
		setPropertyOnLayer(streamId, "ENABLE", 0, "-GLOBAL");
	}
	
	/** show (un-hide) specified Stream ID on PLOT.
	 *  @since 5.0 
	 */
	public void showStream(@NonNull String streamId) {
		streamIdToIsHidden.remove(streamId);
		setPropertyOnLayer(streamId, "ENABLE", 0, "+GLOBAL");
	}
	
	/** is the specified stream shown on the PLOT?
	 * @since 5.0
	 */
	public boolean isStreamShown(@NonNull String streamId) {
		return !streamIdToIsHidden.containsKey(streamId);
	}
	
	/** set the line color of the specified stream ID on the line plot. 
	 * @since 5.0
	 */
	public void setStreamLineColor(@NonNull String streamId, Color color) {
		AbstractNxmPlotWidget plotWidget = getContext();
		String sourceName = streamIdToSourceNameMap.get(streamId);
		plotWidget.setLineColor(sourceName, color);
	}
	
	/** get the current line color of the specified stream ID on the line plot.
	 * @since 5.0
	 */
	public Color getStreamLineColor(@NonNull String streamId) {
		AbstractNxmPlotWidget plotWidget = getContext();
		String sourceName = streamIdToSourceNameMap.get(streamId);
		return plotWidget.getLineColor(sourceName);
	}
}

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

import gov.redhawk.sca.util.Debug;
import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.PlotActivator;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import nxm.sys.prim.plot;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.swt.widgets.Composite;

import BULKIO.StreamSRI;

/**
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class PlotNxmBlock extends AbstractNxmBlock<plot, PlotNxmBlockSettings> {

	private static final Debug TRACE_LOG = new Debug(PlotActivator.PLUGIN_ID, PlotNxmBlock.class.getSimpleName());
	
	private PlotNxmBlockSettings settings;
	private ConcurrentHashMap<String, String> streamIdToSourceNameMap = new ConcurrentHashMap<String, String>();

	public PlotNxmBlock(@NonNull AbstractNxmPlotWidget plotWidget, PlotNxmBlockSettings settings) {
		super(plot.class, "PLOT", plotWidget);
		if (settings == null) {
			settings = new PlotNxmBlockSettings();
		} else {
			// PASS: TODO: clone settings?
		}
		this.settings = settings;
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
	public void launch(String streamID, StreamSRI sri) {
		TRACE_LOG.enteringMethod(streamID, sri);
		checkLaunchParams(streamID, sri);

		BlockIndexPair inputBlockInfo = this.getInputBlockInfo(0);
		if (inputBlockInfo == null) {
			throw new IllegalStateException("A input index 0 must be set before launch() can be called.");
		}
		
		final AbstractNxmPlotWidget currentPlotWidget = getContext();
		String sourceName = inputBlockInfo.getBlock().getOutputName(inputBlockInfo.getIndex(), streamID);

		StringBuilder pipeQualifiers = new StringBuilder();
		int frameSize = settings.getFrameSize();
		if (frameSize > 0) {   // 1. override frame size with value in settings
			pipeQualifiers.append("{FRAMESIZE=").append(frameSize).append('}');
		} else if (!(getInputBlockInfo(0).getBlock() instanceof FftNxmBlock)) {
			if (sri != null) { // ?. check sri.subsize
				frameSize = sri.subsize;
			}
			String tmpResName = AbstractNxmPlotWidget.createUniqueName(false);
			currentPlotWidget.runHeadlessCommandWithResult("TABLE " + tmpResName + " CREATE");
			currentPlotWidget.runHeadlessCommandWithResult("STATUS/VERBOSE " + sourceName + " type=" + tmpResName + ".type  frameSize=" + tmpResName + ".fs");
			currentPlotWidget.runHeadlessCommandWithResult("RES/ALL " + tmpResName + "");
			currentPlotWidget.runHeadlessCommandWithResult("STATUS/VERBOSE " + sourceName);
			if (frameSize <= 0) { // 3. no frame size
				pipeQualifiers.append("{FRAMESIZE=1024}"); // frame type 1000 pipe to 1024
			}
		}
		
		final int pipeSize = settings.getPipeSize();
		if (pipeSize > 0) {
			pipeQualifiers.append("{PIPESIZE=").append(pipeSize).append('}');
		}
		
		//		PlotSession plotSession = new PlotSession(currentPlotWidget, )
		currentPlotWidget.addSource(sourceName, pipeQualifiers.toString());

		streamIdToSourceNameMap.put(streamID, sourceName); // save mapping for shutdown

		// FYI: this is end point, so it DOES NOT have any follow on blocks
		TRACE_LOG.exitingMethod(streamID);
	}

	@Override
	public void shutdown(String streamID) {
		TRACE_LOG.enteringMethod(streamID);
		final AbstractNxmPlotWidget currentPlotWidget = getContext();
		if (currentPlotWidget == null) {
			throw new IllegalStateException("A context (AbstractNxmPlotWidget) must be set before shutdown() can be called.");
		}

		String sourceName = streamIdToSourceNameMap.remove(streamID);
		if (sourceName != null) {
			currentPlotWidget.removeSource(sourceName);
		}
	}

	@Override
	public void stop() {
		TRACE_LOG.enteringMethod("curSize=" + streamIdToSourceNameMap.size());
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
	}

	@Override
	public boolean hasControls() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Composite createControls(Composite parent, PlotNxmBlockSettings currentSettings, DataBindingContext dataBindingContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlotNxmBlockSettings getSettings() {
		return settings;
	}

	@Override
	public void applySettings(PlotNxmBlockSettings settings) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String formCmdLine(AbstractNxmPlotWidget plotWidget, String streamID) {
		return null; // null for no Command to execute
	}

}

/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.ui.port.nxmblocks;

import gov.redhawk.bulkio.util.AbstractUberBulkIOPort;
import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.bulkio.util.BulkIOUtilActivator;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.sca.util.Debug;
import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.PlotActivator;

import java.text.MessageFormat;

import nxm.redhawk.prim.corbareceiver2;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.swt.widgets.Composite;

import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;

/**
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class BulkIONxmBlock extends AbstractNxmBlock<corbareceiver2> {

	private static final Debug TRACE_LOG = new Debug(PlotActivator.PLUGIN_ID, BulkIONxmBlock.class.getSimpleName());
	
	private BulkIONxmBlockSettings settings;

	private ScaUsesPort scaPort;
	private final BulkIOPort bulkIOPort = new BulkIOPort(); 
	private final String ior;
	private final BulkIOType bulkIOType; 

	class BulkIOPort extends AbstractUberBulkIOPort {

		@Override
		protected void handleStreamSRIChanged(String streamID, StreamSRI oldSri, StreamSRI newSri) {
			TRACE_LOG.enteringMethod(streamID, oldSri, newSri);
			launch(streamID, newSri); // so launch for this stream
			
//			if (oldSri == null) { // no previous SRI for this stream
//				PropertyChangeEvent event = new PropertyChangeEvent(this, "newStreamId", oldSri, newSri);
//				firePropertyChangeEvent(event);
//			}
//else { // BulkIOPort.handleStreamSRIChanged() have previous SRI for this stream, not launching: 

		}

		@Override
		public void pushPacket(short[] data, PrecisionUTCTime time, boolean eos, String streamID) {
			super.pushPacket(data.length, time, eos, streamID);
			// ignore, not receiving data in this class
		}

		@Override
		public void pushPacket(char[] data, PrecisionUTCTime time, boolean eos, String streamID) {
			super.pushPacket(data.length, time, eos, streamID);
			// ignore, not receiving data in this class
		}

		@Override
		public void pushPacket(double[] data, PrecisionUTCTime time, boolean eos, String streamID) {
			super.pushPacket(data.length, time, eos, streamID);
			// ignore, not receiving data in this class
		}

		@Override
		public void pushPacket(float[] data, PrecisionUTCTime time, boolean eos, String streamID) {
			super.pushPacket(data.length, time, eos, streamID);
			// ignore, not receiving data in this class
		}

		@Override
		public void pushPacket(long[] data, PrecisionUTCTime time, boolean eos, String streamID) {
			super.pushPacket(data.length, time, eos, streamID);
			// ignore, not receiving data in this class
		}

		@Override
		public void pushPacket(int[] data, PrecisionUTCTime time, boolean eos, String streamID) {
			super.pushPacket(data.length, time, eos, streamID);
			// ignore, not receiving data in this class
		}

		@Override
		public void pushPacket(byte[] data, PrecisionUTCTime time, boolean eos, String streamID) {
			super.pushPacket(data.length, time, eos, streamID);
			// ignore, not receiving data in this class
		}

	} // end inner class BulkIOPort

	/** 
	 * @param settings
	 */
	public BulkIONxmBlock(@NonNull AbstractNxmPlotWidget plotWidget, @NonNull ScaUsesPort scaUsesPort, @NonNull BulkIONxmBlockSettings settings) {
		super(corbareceiver2.class, BulkIONxmBlockSettings.class, "BULKIO", plotWidget);
		this.settings = settings;
		this.scaPort = scaUsesPort;
		this.ior = scaUsesPort.getIor();
		String idl = scaPort.getRepid();
		this.bulkIOType = BulkIOType.getType(idl);
	}

	@Override
	public boolean hasControls() {
		return true;
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.IInputSource#createControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControls(Composite parent, Object settings, DataBindingContext dataBindingContext) {
		BulkIONxmBlockSettings blockSettings = null;
		if (settings instanceof BulkIONxmBlockSettings) {
			blockSettings = (BulkIONxmBlockSettings) settings;
		}
		new BulkIONxmBlockControls(blockSettings, dataBindingContext).createControls(parent);
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.INxmCmdSource#getSettings()
	 */
	@Override
	public BulkIONxmBlockSettings getSettings() {
		BulkIONxmBlockSettings clone = settings.clone();
		//		corbareceiver corbareceiver = getNxmCommand(); // we now possibly have multiple commands running per stream ID
		//		if (corbareceiver != null) {
		//			clone.setSampleRate(corbareceiver.getSampleRate());
		//			clone.setBlocking(corbareceiver.isBlocking());
		//		}
		return clone;
	}

	@Override
	protected void applySettingsTo(corbareceiver2 cmd, Object settings, String streamId) {
		if (settings instanceof BulkIONxmBlockSettings) {
			BulkIONxmBlockSettings newSettings = (BulkIONxmBlockSettings) settings;
			boolean blocking = newSettings.isBlocking();
			Double sampleRate = newSettings.getSampleRate();
			cmd.setBlocking(blocking);
			if (sampleRate == null) {
				sampleRate = 0.0; // zero to use default from input stream
			}
			cmd.setSampleRate(sampleRate);
		}
	}

	@Override
	public void start() throws CoreException {
		BulkIOUtilActivator.getBulkIOPortConnectionManager().connect(ior, bulkIOType, bulkIOPort);
	}

	@Override
	public void stop() {
		TRACE_LOG.enteringMethod();
		if (scaPort != null) {
			BulkIOUtilActivator.getBulkIOPortConnectionManager().disconnect(ior, bulkIOType, bulkIOPort);
			scaPort = null;
		}
	}

	@Override
	public int getMaxInputs() {
		return 0; // BULKIO Port is starting point (so it has no inputs)
	}

	// =========================================================================
	@Override 
	protected String formCmdLine(@NonNull AbstractNxmPlotWidget plotWidget, String streamID) {

		String outputName = AbstractNxmPlotWidget.createUniqueName(true);
		putOutputNameMapping(0, streamID, outputName); // save output name mapping 

		final StringBuilder switches = new StringBuilder("/POLL=1.0");
		final int pipeSize = settings.getPipeSize(); // in bytes
		if (pipeSize > 0) {
			switches.append("/PS=").append(pipeSize);
		}
		final int timeLineLen = settings.getTimelineLength();
		if (timeLineLen > 0) {
			switches.append("/TLL=").append(timeLineLen);
		}
		final String idl = scaPort.getRepid();
		String pattern = "CORBARECEIVER2{0}/BG FILE={1} IOR={2} IDL=\"{3}\" STREAMID=\"{4}\"";
		String cmdLine = MessageFormat.format(pattern, switches, outputName, ior, idl, streamID);

		return cmdLine;
	}

}

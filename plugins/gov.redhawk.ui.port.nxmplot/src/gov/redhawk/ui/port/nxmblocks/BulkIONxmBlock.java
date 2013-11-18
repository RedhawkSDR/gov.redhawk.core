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
import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;

import java.text.MessageFormat;

import nxm.redhawk.prim.corbareceiver;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;

/**
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class BulkIONxmBlock extends AbstractNxmBlock<corbareceiver, BulkIONxmBlockSettings> {

	private BulkIONxmBlockSettings settings;

	private ScaUsesPort scaPort;
	private final BulkIOPort bulkIOPort = new BulkIOPort(); 
	private final String ior;
	private final BulkIOType bulkIOType; 

	class BulkIOPort extends AbstractUberBulkIOPort {

		@Override
		protected void handleStreamSRIChanged(String streamID, StreamSRI oldSri, StreamSRI newSri) {
			launch(streamID, newSri); // so launch for this stream
//			if (oldSri == null) { // no previous SRI for this stream
//			}
//else { System.out.println("DEBUG: BulkIOPort.handleStreamSRIChanged() have previous SRI for this stream, not launching: " + streamID + " oldSRI: " + oldSri); } // SUPPRESS CHECKSTYLE DEBUG - TODO: REMOVE ME 

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

	/** <b>INTERNAL USE ONLY</b>
	 * @noreference This constructor is not intended to be referenced by clients.
	 * @param settings
	 */
	public BulkIONxmBlock(@NonNull AbstractNxmPlotWidget plotWidget, @NonNull ScaUsesPort scaUsesPort, @NonNull BulkIONxmBlockSettings settings) {
		super(corbareceiver.class, "BULKIO");
		this.settings = settings;
		this.scaPort = scaUsesPort;
		this.ior = scaUsesPort.getIor();
		String idl = scaPort.getRepid();
		this.bulkIOType = BulkIOType.getType(idl);
		setContext(plotWidget);
	}

	@Override
	public boolean hasControls() {
		return true;
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.IInputSource#createControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public Composite createControls(Composite parent, BulkIONxmBlockSettings settings, DataBindingContext dataBindingContext) {
		return new BulkIONxmBlockControls(parent, SWT.NONE, settings, dataBindingContext);
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.INxmCmdSource#getSettings()
	 */
	@Override
	public BulkIONxmBlockSettings getSettings() {
		BulkIONxmBlockSettings clone = settings.clone();
		//		corbareceiver corbareceiver = getNxmCommand(); // we now possilby have multiple Fft commands running per stream ID
		//		if (corbareceiver != null) {
		//			clone.setFrameSize(corbareceiver.getFrameSize());
		//			clone.setSampleRate(corbareceiver.getSampleRate());
		//			clone.setBlocking(corbareceiver.isBlocking());
		//		}
		return clone;
	}

	/* (non-Javadoc)
	 * @see gov.redhawk.ui.port.nxmplot.INxmCmdSource#applySettings(java.lang.Object)
	 */
	@Override
	public void applySettings(BulkIONxmBlockSettings settings) {
		//		corbareceiver corbaReceiver = getNxmCommand();
		//		if (corbaReceiver != null) {
		//			corbaReceiver.setBlocking(settings.isBlocking());
		//
		//			corbaReceiver.setFrameSize(settings.getFrameSize());
		//			corbaReceiver.setSampleRate(settings.getSampleRate());
		//		}
	}

	@Override
	public void start() throws CoreException {
		BulkIOUtilActivator.getBulkIOPortConnectionManager().connect(ior, bulkIOType, bulkIOPort);
	}

	@Override
	public void stop() {
		System.out.println("DEBUG: bulkioNxmBlock.stop() enter"); // SUPPRESS CHECKSTYLE DEBUG - TODO: REMOVE ME
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

		int frameSize = settings.getFrameSize();
		if (frameSize <= 0) {
			frameSize = 1024; 
		}
		int pipeSize = settings.getPipeSize();
		if (pipeSize <= 0) {
			pipeSize = 131072; // in bytes 
		}
		final int timeLineLen = settings.getTimelineLength();
		final String idl = scaPort.getRepid();
		String pattern = "CORBARECEIVER/BG/TLL={0,number,#}/PS={1,number,#}/POLL=1.0 FILE={2} FRAMESIZE={3,number,#} PORT_NAME={4} IDL=\"{5}\"";
		String cmdLine = MessageFormat.format(pattern, timeLineLen, pipeSize, outputName, frameSize, ior, idl);

		return cmdLine;
	}

}

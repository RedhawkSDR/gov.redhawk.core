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
import gov.redhawk.internal.ui.BooleanUtil;
import gov.redhawk.internal.ui.preferences.BulkIOBlockPreferencePage;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.sca.util.Debug;
import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.preferences.BulkIOPreferences;
import gov.redhawk.ui.port.nxmplot.preferences.Preference;

import java.text.MessageFormat;

import nxm.redhawk.prim.corbareceiver2;
import nxm.sys.lib.StringUtil;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;

import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;

/**
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class BulkIONxmBlock extends AbstractNxmBlock<corbareceiver2> {

	private static final Debug TRACE_LOG = new Debug(PlotActivator.PLUGIN_ID, BulkIONxmBlock.class.getSimpleName());

	private ScaUsesPort scaPort;
	private final BulkIOPort bulkIOPort = new BulkIOPort();
	private final String ior;
	private final BulkIOType bulkIOType;

	private String originalConnectionID;

	class BulkIOPort extends AbstractUberBulkIOPort {

		@Override
		protected void handleStreamSRIChanged(final String streamID, final StreamSRI oldSri, final StreamSRI newSri) {
			if (BulkIONxmBlock.TRACE_LOG.enabled) {
				BulkIONxmBlock.TRACE_LOG.enteringMethod(streamID, oldSri, newSri);
				if (Double.isInfinite(newSri.xdelta)) {
					BulkIONxmBlock.TRACE_LOG.message("WARN: streamID={0} BAD xdelta={1}", newSri.streamID, newSri.xdelta);
				}
			}
			if (oldSri == null) { // only launch for a new stream
				// run in background so we don't further block our caller and cause potential deadlock
				Job job = new Job("launching stream [" + streamID + "] to plot") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						launch(streamID, newSri);
						return Status.OK_STATUS;
					}
				};
				job.schedule(0);
			}
		}

		private void handlePushPacket(int length, PrecisionUTCTime time, boolean endOfStream, String streamID) {
//			BulkIONxmBlock.TRACE_LOG.message("BulkIO block got pushPacket: {0} len={1} eos={2}", streamID, length, endOfStream);
//			if (time != null && Double.isInfinite(time.twsec)) {
//				BulkIONxmBlock.TRACE_LOG.message("WARN: streamID={0} BAD twsec={1}", streamID, time.twsec);
//			}
			super.pushPacket(length, time, endOfStream, streamID);
			if (endOfStream && isRemoveOnEndOfStream()) {
				shutdown(streamID);
			}
		}

		@Override
		public void pushPacket(short[] data, PrecisionUTCTime time, boolean eos, String streamID) {
			handlePushPacket(data.length, time, eos, streamID);
			// ignore, not receiving data in this class
		}

		@Override
		public void pushPacket(char[] data, PrecisionUTCTime time, boolean eos, String streamID) {
			handlePushPacket(data.length, time, eos, streamID);
			// ignore, not receiving data in this class
		}

		@Override
		public void pushPacket(double[] data, PrecisionUTCTime time, boolean eos, String streamID) {
			handlePushPacket(data.length, time, eos, streamID);
			// ignore, not receiving data in this class
		}

		@Override
		public void pushPacket(float[] data, PrecisionUTCTime time, boolean eos, String streamID) {
			handlePushPacket(data.length, time, eos, streamID);
			// ignore, not receiving data in this class
		}

		@Override
		public void pushPacket(long[] data, PrecisionUTCTime time, boolean eos, String streamID) {
			handlePushPacket(data.length, time, eos, streamID);
			// ignore, not receiving data in this class
		}

		@Override
		public void pushPacket(int[] data, PrecisionUTCTime time, boolean eos, String streamID) {
			handlePushPacket(data.length, time, eos, streamID);
			// ignore, not receiving data in this class
		}

		@Override
		public void pushPacket(byte[] data, PrecisionUTCTime time, boolean eos, String streamID) {
			handlePushPacket(data.length, time, eos, streamID);
			// ignore, not receiving data in this class
		}

	} // end inner class BulkIOPort

	public BulkIONxmBlock(AbstractNxmPlotWidget plotWidget, @NonNull ScaUsesPort scaUsesPort, BulkIONxmBlockSettings settings) {
		super(corbareceiver2.class, plotWidget, BulkIONxmBlock.initPreferences());
		this.scaPort = scaUsesPort;
		this.ior = scaUsesPort.getIor();
		String idl = scaPort.getRepid();
		this.bulkIOType = BulkIOType.getType(idl);
		if (settings != null) {
			applySettings(settings);
		}
	}

	/**
	 * @param settings
	 */
	public BulkIONxmBlock(AbstractNxmPlotWidget plotWidget, @NonNull ScaUsesPort scaUsesPort) {
		this(plotWidget, scaUsesPort, null);
	}

	private static IPreferenceStore initPreferences() {
		return Preference.initStoreFromWorkbench(BulkIOPreferences.getAllPreferences());
	}

	public boolean isRemoveOnEndOfStream() {
		return BulkIOPreferences.REMOVE_ON_EOS.getValue(getPreferences());
	}

	public BulkIONxmBlockSettings getSettings() {
		BulkIONxmBlockSettings clone = new BulkIONxmBlockSettings(getPreferences());
		return clone;
	}

	public void applySettings(BulkIONxmBlockSettings newSettings) {
		boolean blocking = newSettings.isBlocking();
		Integer sampleRate = newSettings.getSampleRate();
		Integer pipeSize = newSettings.getPipeSize();

		setBlocking(blocking);
		if (sampleRate != null) {
			setSampleRate(sampleRate);
		} else {
			unsetSampleRate();
		}
		if (pipeSize != null) {
			setPipeSize(pipeSize);
		}
		setRemoveOnEndOfStream(newSettings.isRemoveOnEndOfStream());
		if (newSettings.getConnectionID() != null && !newSettings.getConnectionID().isEmpty()) {
			setConnectionID(newSettings.getConnectionID());
		}
		setTimelineLength(newSettings.getTimelineLength());
	}

	public void setSampleRate(int sampleRate) {
		BulkIOPreferences.SAMPLE_RATE.setValue(getPreferences(), sampleRate);
		BulkIOPreferences.SAMPLE_RATE_OVERRIDE.setValue(getPreferences(), true);
	}

	public void unsetSampleRate() {
		BulkIOPreferences.SAMPLE_RATE.setToDefault(getPreferences());
		BulkIOPreferences.SAMPLE_RATE_OVERRIDE.setToDefault(getPreferences());
	}

	public boolean isSetSampleRate() {
		return BulkIOPreferences.SAMPLE_RATE_OVERRIDE.getValue(getPreferences());
	}

	public int getSampleRate() {
		return BulkIOPreferences.SAMPLE_RATE.getValue(getPreferences());
	}

	public void setBlocking(boolean blocking) {
		BulkIOPreferences.BLOCKING.setValue(getPreferences(), blocking);
	}
	
	boolean isBlocking() {
		return BulkIOPreferences.BLOCKING.getValue(getPreferences());
	}

	@Override
	public void start() throws CoreException {
		this.originalConnectionID = getConnectionID();
		if (this.originalConnectionID != null && originalConnectionID.isEmpty()) {
			this.originalConnectionID = null;
		}
		String connectionID = BulkIOUtilActivator.getBulkIOPortConnectionManager().connect(ior, bulkIOType, bulkIOPort, this.originalConnectionID);
		if (connectionID == null) {
			connectionID = ""; // set non-null value so that Connection ID field in adjust settings is read-only
		}
		setConnectionID(connectionID);
	}

	@Override
	public void stop() {
		BulkIONxmBlock.TRACE_LOG.enteringMethod();
		if (scaPort != null) {
			BulkIOUtilActivator.getBulkIOPortConnectionManager().disconnect(ior, bulkIOType, bulkIOPort, this.originalConnectionID);
			scaPort = null;
			this.originalConnectionID = null;
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

		final StringBuilder switches = new StringBuilder("/POLL=0.1"); // reading & writing of data is done in thread calling pushPacket(..)
		final int pipeSize = getPipeSize(); // in bytes
		if (pipeSize > 0) {
			switches.append("/PS=").append(pipeSize);
		}
		final int timeLineLen = getTimelineLength();
		if (timeLineLen > 0) {
			switches.append("/TLL=").append(timeLineLen);
		}
		String customConnectionId = getConnectionID();
		if (customConnectionId != null && customConnectionId.trim().length() > 0) {
			customConnectionId = StringUtil.escapeString(customConnectionId, true);
			switches.append("/CONNECTIONID=\"").append(customConnectionId).append('\"');
		}
		if (isSetSampleRate()) {
			int sampleRate = getSampleRate();
			if (sampleRate > 0) { // ignore negative or zero sample rates
				switches.append(corbareceiver2.SW_SAMPLE_RATE).append('=').append(sampleRate);
			}
		}
		if (isBlocking()) {
			switches.append(corbareceiver2.SW_BLOCKING);
		}

		final String idl = scaPort.getRepid();
		String pattern = "CORBARECEIVER2{0}/BG FILE={1} IOR={2} IDL=\"{3}\" STREAMID=\"{4}\"";
		String cmdLine = MessageFormat.format(pattern, switches, outputName, ior, idl, streamID);

		return cmdLine;
	}

	public String getConnectionID() {
		return BulkIOPreferences.CONNECTION_ID.getValue(getPreferences());
	}

	public void setConnectionID(String connectionID) {
		BulkIOPreferences.CONNECTION_ID.setValue(getPreferences(), connectionID);
	}

	public int getTimelineLength() {
		return BulkIOPreferences.TLL.getValue(getPreferences());
	}

	public void setTimelineLength(int tll) {
		BulkIOPreferences.TLL.setValue(getPreferences(), tll);
	}

	public int getPipeSize() {
		return BulkIOPreferences.PIPE_SIZE.getValue(getPreferences());
	}

	public void setRemoveOnEndOfStream(boolean removeOnEndOfStream) {
		BulkIOPreferences.REMOVE_ON_EOS.setValue(getPreferences(), removeOnEndOfStream);
	}

	public void setPipeSize(int pipeSize) {
		BulkIOPreferences.PIPE_SIZE.setValue(getPreferences(), pipeSize);
		BulkIOPreferences.PIPE_SIZE_OVERRIDE.setValue(getPreferences(), true);
	}

	public boolean isSetPipeSize() {
		return BulkIOPreferences.PIPE_SIZE_OVERRIDE.getValue(getPreferences());
	}

	public void unsetPipeSize() {
		BulkIOPreferences.PIPE_SIZE.setToDefault(getPreferences());
		BulkIOPreferences.PIPE_SIZE_OVERRIDE.setToDefault(getPreferences());
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		Boolean blocking = null;
		if (BulkIOPreferences.BLOCKING.isEvent(event)) {
			blocking = BooleanUtil.toBoolean(event.getNewValue());
		}

		Integer sampleRate = null;
		if (BulkIOPreferences.SAMPLE_RATE.isEvent(event) || BulkIOPreferences.SAMPLE_RATE_OVERRIDE.isEvent(event)) {
			if (isSetSampleRate()) {
				sampleRate = getSampleRate();
			} else {
				sampleRate = 0; // zero to use default from input stream
			}
		}

		Integer pipeSize = null;
		if (BulkIOPreferences.PIPE_SIZE.isEvent(event) || BulkIOPreferences.PIPE_SIZE_OVERRIDE.isEvent(event)) {
			if (isSetPipeSize()) {
				pipeSize = getPipeSize();
			} else {
				pipeSize = BulkIOPreferences.PIPE_SIZE.getDefaultValue();
			}
			if (pipeSize <= 0) { // PANIC!!
				pipeSize = 131072;
			}
		}

		Boolean canGrowPipe = null;
		if (BulkIOPreferences.CAN_GROW_PIPE.isEvent(event)) {
			canGrowPipe = BooleanUtil.toBoolean(event.getNewValue());
		}

		Integer pipeSizeMultiplier = null;
		if (BulkIOPreferences.PIPE_SIZE_MULTIPLIER.isEvent(event)) {
			pipeSizeMultiplier = (Integer) event.getNewValue();
		}

		for (corbareceiver2 cmd : getNxmCommands()) {
			if (blocking != null) {
				cmd.setBlocking(blocking);
			}
			if (pipeSize != null) {
				cmd.setPipeSize(pipeSize);
			}

			if (canGrowPipe != null) {
				cmd.setCanGrowPipe(canGrowPipe);
			}

			if (pipeSizeMultiplier != null) {
				cmd.setPipeSizeMultiplier(pipeSizeMultiplier);
			}

			if (sampleRate != null) {
				cmd.setSampleRate(sampleRate);
			}
		}
	}

	@Override
	public IPreferencePage createPreferencePage() {
		BulkIOBlockPreferencePage retVal = new BulkIOBlockPreferencePage();
		retVal.setPreferenceStore(getPreferences());
		return retVal;
	}

}

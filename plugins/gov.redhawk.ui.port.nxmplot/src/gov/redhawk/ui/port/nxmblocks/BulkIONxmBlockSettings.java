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
package gov.redhawk.ui.port.nxmblocks;

import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.preferences.BulkIOPreferences;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * BULK IO settings
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class BulkIONxmBlockSettings implements Cloneable {
	public static final String PROP_BLOCKING_OPTION = "blockingOption";
	public static final String PROP_CONNECTION_ID = "connectionID";
	public static final String PROP_PIPE_SIZE = "pipeSize";
	public static final String PROP_SAMPLE_RATE = "sampleRate";
	public static final String PROP_REMOVE_ON_EOS = "removeOnEndOfStream";

	// NOTE1: these enum names MUST matches those in corbareceiver2$BlockingOption
	// NOTE2: we only contain a subset here to simply UI as corbareceiver2.BlockingOption.TRUE is same as BLOCKING, etc.
	public static enum BlockingOption {
		NONBLOCKING("non-blocking"),
		BLOCKING("blocking"),
		FROMSRI("use SRI.blocking");

		private final String label;

		BlockingOption(String label) {
			this.label = label;
		}

		public String getLabel() {
			return this.label;
		}
	}

	/** custom connection ID to use. */
	private String connectionID;
	/** null (or zero) to use default from StreamSRI. */
	private Integer sampleRate;
	private BlockingOption blocking;
	/** null to use default pipe size from NeXtMidas (128K) */
	private Integer pipeSize;
	/** time line length for output data pipe. */
	private int timelineLength;
	/** remove stream from PLOT (by calling shutdown(streamID) on end-of-stream (EOS) is received in pushPacket */
	private boolean removeOnEndOfStream = true;

	public BulkIONxmBlockSettings() {
		this(null);
	}

	public BulkIONxmBlockSettings(IPreferenceStore store) {
		if (store == null) {
			store = PlotActivator.getDefault().getPreferenceStore();
		}
		connectionID = BulkIOPreferences.CONNECTION_ID.getValue(store);

		if (BulkIOPreferences.SAMPLE_RATE_OVERRIDE.getValue(store)) {
			sampleRate = BulkIOPreferences.SAMPLE_RATE.getValue(store);
		}

		blocking = BlockingOption.valueOf(BulkIOPreferences.BLOCKING_OPTION.getValue(store));

		if (BulkIOPreferences.PIPE_SIZE_OVERRIDE.getValue(store)) {
			pipeSize = BulkIOPreferences.PIPE_SIZE.getValue(store);
		}
	}

	@NonNull
	@Override
	public BulkIONxmBlockSettings clone() {
		try {
			return (BulkIONxmBlockSettings) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError("This should never happen: " + e);
		}
	}

	public String getConnectionID() {
		return connectionID;
	}

	public void setConnectionID(String connectionID) {
		this.connectionID = connectionID;
	}

	/**
	 * @return the current sample rate (null to use default)
	 */
	public Integer getSampleRate() {
		return sampleRate;
	}

	/**
	 * @param sampleRate the sampleRate to set (null to use default)
	 */
	public void setSampleRate(Integer sampleRate) {
		this.sampleRate = sampleRate;
	}

	/** @deprecated since 5.0, use {@link #setBlockingOption(BlockingOption)} */
	@Deprecated
	public void setBlocking(boolean blocking) {
		if (blocking) {
			this.blocking = BlockingOption.BLOCKING;
		} else {
			this.blocking = BlockingOption.NONBLOCKING;
		}
	}

	/** @since 5.0 */
	public BlockingOption getBlockingOption() {
		return this.blocking;
	}

	/** @since 5.0 */
	public void setBlockingOption(BlockingOption blocking) {
		this.blocking = blocking;
	}

	/**
	 * @return the pipeSize
	 */
	public Integer getPipeSize() {
		return pipeSize;
	}

	/**
	 * @param pipeSize the pipeSize to set
	 */
	public void setPipeSize(Integer pipeSize) {
		this.pipeSize = pipeSize;
	}

	/**
	 * @return the timelineLength
	 */
	public int getTimelineLength() {
		return timelineLength;
	}

	/**
	 * @param timelineLength the timelineLength to set
	 */
	public void setTimelineLength(int timelineLength) {
		this.timelineLength = timelineLength;
	}

	public boolean isRemoveOnEndOfStream() {
		return removeOnEndOfStream;
	}

	public void setRemoveOnEndOfStream(boolean removeOnEndOfStream) {
		this.removeOnEndOfStream = removeOnEndOfStream;
	}

}

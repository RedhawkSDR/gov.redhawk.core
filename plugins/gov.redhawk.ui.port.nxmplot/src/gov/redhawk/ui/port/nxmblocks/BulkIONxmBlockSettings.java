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
	public static final String PROP_BLOCKING_OPTION = "blocking";
	public static final String PROP_CONNECTION_ID = "connectionID";
	public static final String PROP_PIPE_SIZE = "pipeSize";
	public static final String PROP_SAMPLE_RATE = "sampleRate";
	public static final String PROP_REMOVE_ON_EOS = "removeOnEndOfStream";

	/** custom connection ID to use. */
	private String connectionID;
	/** null to use default from StreamSRI. */
	private Double sampleRate;
	/** true to block pushPacket when downstream Command (e.g. Plot) cannot keep up, false to drop packets in this scenario. */
	private boolean blocking;
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

		blocking = BulkIOPreferences.BLOCKING.getValue(store);

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
	public Double getSampleRate() {
		return sampleRate;
	}

	/**
	 * @param sampleRate the sampleRate to set (null to use default)
	 */
	public void setSampleRate(Double sampleRate) {
		this.sampleRate = sampleRate;
	}

	public boolean isBlocking() {
		return this.blocking;
	}

	public void setBlocking(boolean blocking) {
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

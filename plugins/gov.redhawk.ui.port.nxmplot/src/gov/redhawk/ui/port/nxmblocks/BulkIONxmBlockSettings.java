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

import org.eclipse.jdt.annotation.NonNull;

/**
 * BULK IO settings
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class BulkIONxmBlockSettings implements Cloneable {
	public static final String PROP_CONNECTION_ID   = "connectionID";
	public static final String PROP_SAMPLE_RATE     = "sampleRate";
	public static final String PROP_BLOCKING_OPTION = "blocking";
	
	private static final int DEFAULT_TIMELINE_LENGTTH = 200;

	/** custom connection ID to use. */
	private String connectionID;
	/** null to use default from StreamSRI. */
	private Double sampleRate;
	/** true to block pushPacket when downstream Command (e.g. Plot) cannot keep up, false to drop packets in this scenario. */
	private boolean blocking;
	/** zero to use NeXtMidas default pipe size. */
	private int pipeSize;
	/** time line length for output data pipe. */
	private int timelineLength = DEFAULT_TIMELINE_LENGTTH;

	public BulkIONxmBlockSettings() {
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
	public int getPipeSize() {
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

}

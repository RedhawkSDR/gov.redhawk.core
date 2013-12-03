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
	public static final String PROP_SAMPLE_RATE     = "sampleRate";
	public static final String PROP_BLOCKING_OPTION = "blocking";
	
	private static final int DEFAULT_TIMELINE_LENGTTH = 200;

	private Double sampleRate; // null or zero to use default from StreamSRI
	
	private boolean blocking;
	private int pipeSize;
	private int timelineLength = DEFAULT_TIMELINE_LENGTTH;

	public BulkIONxmBlockSettings() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@NonNull
	@Override
	public BulkIONxmBlockSettings clone() {
		try {
			return (BulkIONxmBlockSettings) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError("This should never happenn: " + e);
		}
	}

	/**
	 * @return the sampleRate
	 */
	public Double getSampleRate() {
		return sampleRate;
	}

	/**
	 * @param sampleRate the sampleRate to set
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

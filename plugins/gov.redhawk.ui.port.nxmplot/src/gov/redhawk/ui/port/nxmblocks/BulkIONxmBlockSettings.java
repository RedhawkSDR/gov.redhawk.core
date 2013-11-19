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
	private static final int DEFAULT_TIMELINE_LENGTTH = 200;

	private String host = "localhost";
	private int port = 2809;          // SUPPRESS CHECKSTYLE MagicNumber
	private int frameSize = -1;       // -1 to use default
	private double sampleRate = -1.0; // -1 to use default
	private int pipeSize;
	private int timelineLength = DEFAULT_TIMELINE_LENGTTH;
	private boolean blocking;

	private String cfResourceIor;
	private String cfPortName;
	private String dataTypeIdl;

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

	public String getHost() {
		return this.host;
	}

	public void setHost(final String host) {
		this.host = host;
	}

	public int getPort() {
		return this.port;
	}

	public void setPort(final int port) {
		this.port = port;
	}

	public void setFrameSize(int frameSize) {
		this.frameSize = frameSize;
	}

	public int getFrameSize() {
		return frameSize;
	}

	/**
	 * @return the sampleRate
	 */
	public double getSampleRate() {
		return sampleRate;
	}

	/**
	 * @param sampleRate the sampleRate to set
	 */
	public void setSampleRate(double sampleRate) {
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
	public void setPipeSize(int pipeSize) {
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

	/**
	 * @return the cfResourceIor
	 */
	public String getCfResourceIor() {
		return cfResourceIor;
	}

	/**
	 * @param cfResourceIor the cfResourceIor to set
	 */
	public void setCfResourceIor(String cfResourceIor) {
		this.cfResourceIor = cfResourceIor;
	}

	/**
	 * @return the cfPortName
	 */
	public String getCfPortName() {
		return cfPortName;
	}

	/**
	 * @param cfPortName the cfPortName to set
	 */
	public void setCfPortName(String cfPortName) {
		this.cfPortName = cfPortName;
	}

	/**
	 * @return the dataTypeIdl
	 */
	public String getDataTypeIdl() {
		return dataTypeIdl;
	}

	/**
	 * @param dataTypeIdl the dataTypeIdl to set
	 */
	public void setDataTypeIdl(String dataTypeIdl) {
		this.dataTypeIdl = dataTypeIdl;
	}

}

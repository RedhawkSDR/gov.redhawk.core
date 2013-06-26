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
package gov.redhawk.bulkio.util;

import BULKIO.PortStatistics;
import BULKIO.PortUsageType;
import BULKIO.ProvidesPortStatisticsProviderOperations;
import BULKIO.StreamSRI;
import BULKIO.updateSRIOperations;
import CF.DataType;

/**
 * 
 */
public abstract class AbstractBulkIOPort implements ProvidesPortStatisticsProviderOperations, updateSRIOperations {

	private StreamSRI sri;
	private PortStatistics stats = new PortStatistics();
	private int bpa;
	private long lastWrite = -1;
	private long lastUpdate = -1;
	private int numElements;
	private int numCalls;
	{
		stats.callsPerSecond = -1;
		stats.elementsPerSecond = -1;
		stats.timeSinceLastCall = -1;
		stats.bitsPerSecond = -1;
		stats.keywords = new DataType[0];
		stats.portName = "port_" + System.getProperty("user.name", "user") + "_" + System.currentTimeMillis();
		stats.streamIDs = new String[0];
	}

	/**
	 * Call this method to update the port statistics
	 */
	protected synchronized void updateStatitics() {
		long oldLastUpdate = this.lastUpdate;
		this.lastUpdate = System.currentTimeMillis();
		if (oldLastUpdate == -1) {
			return;
		}
		long delta = Math.max(1, System.currentTimeMillis() - oldLastUpdate);

		stats.callsPerSecond = ((float) this.numCalls) / delta * 1000f;
		this.numCalls = 0;

		stats.elementsPerSecond = ((float) this.numElements) / delta * 1000f;
		this.numElements = 0;

		if (this.lastWrite > 0) {
			stats.timeSinceLastCall = System.currentTimeMillis() - this.lastWrite;
		} else {
			stats.timeSinceLastCall = -1;
		}

		stats.bitsPerSecond = this.bpa * stats.elementsPerSecond * 8;
	}

	protected void setBytesPerAtom(int bpa) {
		this.bpa = bpa;
	}

	/**
	 * Call this method every time a push packet is received
	 * @param size
	 */
	protected synchronized void pushPacket(int size) {
		if (lastUpdate == -1) {
			lastUpdate = System.currentTimeMillis();
		}
		this.lastWrite = System.currentTimeMillis();
		this.numElements += size;
		this.numCalls++;
	}

	public PortUsageType state() {
		return PortUsageType.ACTIVE;
	}

	public PortStatistics getPortStatistics() {
		return stats;
	}

	public PortStatistics statistics() {
		updateStatitics();
		return stats;
	}

	public StreamSRI[] activeSRIs() {
		if (sri == null) {
			return new StreamSRI[0];
		}
		return new StreamSRI[] { sri };
	}

	public void pushSRI(StreamSRI sri) {
		this.sri = sri;
	}

	public StreamSRI getSri() {
		return sri;
	}
}

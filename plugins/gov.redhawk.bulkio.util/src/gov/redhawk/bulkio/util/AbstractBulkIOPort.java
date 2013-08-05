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

import java.util.HashMap;
import java.util.Map;

import BULKIO.PortStatistics;
import BULKIO.PortUsageType;
import BULKIO.PrecisionUTCTime;
import BULKIO.ProvidesPortStatisticsProviderOperations;
import BULKIO.StreamSRI;
import BULKIO.updateSRIOperations;
import CF.DataType;

/**
 * 
 */
public abstract class AbstractBulkIOPort implements ProvidesPortStatisticsProviderOperations, updateSRIOperations {

	private Map<String, StreamSRI> streamSRIMap = new HashMap<String, StreamSRI>();
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
	private void updateStatitics() {
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
	 * @param length Length of the push packet array.
	 */
	protected synchronized boolean pushPacket(int length, final PrecisionUTCTime time, final boolean endOfStream, final String streamID) {
		if (endOfStream) {
			// Process last packet sent
			this.streamSRIMap.remove(streamID);
		} else if (getSri(streamID) == null) {
			return false;
		}
		if (lastUpdate == -1) {
			lastUpdate = System.currentTimeMillis();
		}
		this.lastWrite = System.currentTimeMillis();
		this.numElements += length;
		this.numCalls++;
		updateStatitics();
		return true;
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
		return this.streamSRIMap.values().toArray(new StreamSRI[this.streamSRIMap.size()]);
	}

	public void pushSRI(StreamSRI sri) {
		this.streamSRIMap.put(sri.streamID, sri);
	}

	public StreamSRI getSri(String streamID) {
		return this.streamSRIMap.get(streamID);
	}
}

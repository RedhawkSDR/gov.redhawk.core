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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

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

	private final Map<String, StreamSRI> streamSRIMap = Collections.synchronizedMap(new HashMap<String, StreamSRI>());
	private StreamSRI currentSri;
	private final PortStatistics stats = new PortStatistics();
	private AtomicLong lastWrite = new AtomicLong(-1);
	private AtomicLong lastUpdate = new AtomicLong(-1);
	private AtomicInteger numElements = new AtomicInteger();
	private AtomicInteger numCalls = new AtomicInteger();
	private BulkIOType type;
	{
		stats.callsPerSecond = -1;
		stats.elementsPerSecond = -1;
		stats.timeSinceLastCall = -1;
		stats.bitsPerSecond = -1;
		stats.keywords = new DataType[0];
		stats.portName = "port_" + System.getProperty("user.name", "user") + "_" + System.currentTimeMillis();
		stats.streamIDs = new String[0];
	}
	
	protected AbstractBulkIOPort() {
	}
	
	/**
	 * @since 2.0
	 */
	protected AbstractBulkIOPort(BulkIOType type) {
		this.type = type;
	}
	
	/**
	 * @since 2.0
	 */
	@NonNull
	public BulkIOType getBulkIOType() {
		return type;
	}
	
	/**
	 * @since 2.0
	 */
	public void setBulkIOType(@NonNull BulkIOType type) {
		this.type = type;
	}


	/**
	 * Call this method to update the port statistics, should only need to be called from the statistics method itself
	 */
	private synchronized void updateStatitics() {
		long currentTime = System.currentTimeMillis();
		long oldLastUpdate = this.lastUpdate.getAndSet(currentTime);
		if (oldLastUpdate == -1) {
			return;
		}
		long delta = Math.max(1, currentTime - oldLastUpdate);
		
		stats.callsPerSecond = this.numCalls.floatValue() / delta * 1000f; // SUPPRESS CHECKSTYLE MAGIC NUMBER
		this.numCalls.set(0);

		stats.elementsPerSecond = this.numElements.floatValue() / delta * 1000f; // SUPPRESS CHECKSTYLE MAGIC NUMBER
		this.numElements.set(0);

		if (this.lastWrite.get() > 0) {
			stats.timeSinceLastCall = Math.max(0, currentTime - this.lastWrite.get());
		} else {
			stats.timeSinceLastCall = -1;
		}

		int bpa = (type != null) ? type.getBytePerAtom() : 1;    // SUPPRESS CHECKSTYLE AvoidInline
		stats.bitsPerSecond = bpa * stats.elementsPerSecond * 8; // SUPPRESS CHECKSTYLE MAGIC NUMBER
	}

	/**
	 * Call this method every time a push packet is received
	 * @param length Length of the push packet array.
	 * @return true if should process packet
	 */
	protected boolean pushPacket(int length, @Nullable final PrecisionUTCTime time, final boolean endOfStream, @Nullable final String streamID) {
		if (endOfStream) {
			// Process last packet sent
			this.streamSRIMap.remove(streamID);
		} else if (getSri(streamID) == null) {
			return false;
		}
		this.lastUpdate.compareAndSet(-1, System.currentTimeMillis());
		this.lastWrite.set(System.currentTimeMillis());
		this.numElements.addAndGet(length);
		this.numCalls.incrementAndGet();
		return true;
	}

	@Override
	@NonNull
	public PortUsageType state() {
		return PortUsageType.ACTIVE;
	}
	
	@NonNull
	public PortStatistics getPortStatistics() {
		return stats;
	}

	@Override
	@NonNull
	public PortStatistics statistics() {
		updateStatitics();
		return stats;
	}

	@Override
	@NonNull
	public StreamSRI[] activeSRIs() {
		return this.streamSRIMap.values().toArray(new StreamSRI[this.streamSRIMap.size()]);
	}

	/**
	 * sub-class should override the {@link #handleStreamSRIChanged(String, StreamSRI, StreamSRI)} method.
	 * @since 2.0
	 */
	@Override
	public final void pushSRI(@Nullable StreamSRI sri) {
		if (sri != null) {
			String streamId = sri.streamID;
			if (streamId != null) {
				StreamSRI oldSri = this.streamSRIMap.put(streamId, sri);
				if (!StreamSRIUtil.equals(oldSri, sri)) {
					handleStreamSRIChanged(streamId, oldSri, sri);
				}
			}
		}
		StreamSRI oldSri = this.currentSri;
		this.currentSri = sri;
		if (!StreamSRIUtil.equals(oldSri, sri)) {
			handleStreamSRIChanged(oldSri, sri);
		}
	}
	
	/**
	 * @since 2.0
	 */
	@Nullable
	public StreamSRI getStreamSRI() {
		return this.currentSri;
	}
	
	/** callback to notify that SRI has changed from previous SRI 
	 * @since 2.0*/
	protected void handleStreamSRIChanged(@Nullable StreamSRI oldSri, @Nullable StreamSRI newSri) {
		
	}

	/** callback to notify that SRI has changed for specified streamID (this is method that sub-classes should override). 
	 * @since 2.0*/
	protected void handleStreamSRIChanged(@NonNull String streamID, @Nullable StreamSRI oldSri, @NonNull StreamSRI newSri) {
		
	}

	/** 
	 * @param streamID 
	 * @return SRI for specified stream ID (null if it does not exist or has reached end-of-stream (EOS)).
	 */
	@Nullable
	public StreamSRI getSri(@Nullable String streamID) {
		return this.streamSRIMap.get(streamID);
	}
}

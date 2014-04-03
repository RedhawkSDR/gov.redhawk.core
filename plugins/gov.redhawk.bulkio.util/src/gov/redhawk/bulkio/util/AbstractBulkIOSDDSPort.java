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

import gov.redhawk.sca.util.Debug;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.Servant;

import BULKIO.PortStatistics;
import BULKIO.PortUsageType;
import BULKIO.PrecisionUTCTime;
import BULKIO.SDDSStreamDefinition;
import BULKIO.StreamSRI;
import BULKIO.dataSDDSOperations;
import BULKIO.dataSDDSPOATie;
import BULKIO.dataSDDSPackage.AttachError;
import BULKIO.dataSDDSPackage.DetachError;
import BULKIO.dataSDDSPackage.InputUsageState;
import BULKIO.dataSDDSPackage.StreamInputError;
import CF.DataType;

/**
 * @since 2.0
 */
public abstract class AbstractBulkIOSDDSPort implements dataSDDSOperations {
	private static final Debug TRACE_LOG = new Debug(BulkIOUtilActivator.getDefault(), AbstractBulkIOSDDSPort.class.getSimpleName());
	
	private final PortStatistics stats = new PortStatistics();

	/** key=attachUuid value=SddsStreamSession */
	private Map<String, SddsStreamSession> sddsSessionMap = new ConcurrentHashMap<String, SddsStreamSession>();
	/** key=streamID value=StreamSRI */
	private final Map<String, StreamSRI> streamSRIMap = new ConcurrentHashMap<String, StreamSRI>();

	// static initializer for PortStatisticsstats field
	{
		stats.callsPerSecond = -1;
		stats.elementsPerSecond = -1;
		stats.timeSinceLastCall = -1;
		stats.bitsPerSecond = -1;
		stats.keywords = new DataType[0];
		stats.portName = "sddsPort_" + System.getProperty("user.name", "user").replace(' ', '_') + "_" + System.currentTimeMillis();
		stats.streamIDs = new String[0];
	}

	protected AbstractBulkIOSDDSPort() {
	}

	/* (non-Javadoc)
	 * @see BULKIO.ProvidesPortStatisticsProviderOperations#state()
	 */
	
	@Override
	public PortUsageType state() {
		if (sddsSessionMap.isEmpty()) {
			return PortUsageType.IDLE;
		}
		return PortUsageType.ACTIVE;
	}

	/* (non-Javadoc)
	 * @see BULKIO.ProvidesPortStatisticsProviderOperations#statistics()
	 */
	@Override
	public PortStatistics statistics() {
		// updateStatitics(); // TODO: what do we do here?
		return stats;
	}

	/* (non-Javadoc)
	 * @see BULKIO.dataSDDSOperations#usageState()
	 */
	@Override
	public InputUsageState usageState() {
		if (sddsSessionMap.isEmpty()) {
			return InputUsageState.IDLE;
		} else {
			return InputUsageState.ACTIVE;
		}
	}

	/* (non-Javadoc)
	 * @see BULKIO.dataSDDSOperations#attachedStreams()
	 */
	@Override
	public SDDSStreamDefinition[] attachedStreams() {
		SddsStreamSession[] values = sddsSessionMap.values().toArray(new SddsStreamSession[0]);
		SDDSStreamDefinition[] retVals = new SDDSStreamDefinition[values.length];
		int ii = 0;
		for (SddsStreamSession val : values) {
			retVals[ii] = val.getSddsStreamDef();
			ii++;
		}
		return retVals;
	}

	/* (non-Javadoc)
	 * @see BULKIO.dataSDDSOperations#attachmentIds()
	 */
	@Override
	public String[] attachmentIds() {
		SddsStreamSession[] values = sddsSessionMap.values().toArray(new SddsStreamSession[0]);
		String[] retVals = new String[values.length];
		int ii = 0;
		for (SddsStreamSession val : values) {
			retVals[ii] = val.getAttachId();
			ii++;
		}
		return retVals;
	}

	/* (non-Javadoc)
	 * @see BULKIO.dataSDDSOperations#attach(BULKIO.SDDSStreamDefinition, java.lang.String)
	 */
	@Override
	public String attach(SDDSStreamDefinition sddsStreamDef, String userid) throws AttachError, StreamInputError {
		TRACE_LOG.enteringMethod(sddsStreamDef, userid);
		if (sddsStreamDef == null) {
			throw new AttachError("Invalid/null SDDSStreamDefinition");
		} else if (userid == null) {
			throw new AttachError("Invalid/null userid");
		}
		String attachUuid = UUID.randomUUID().toString();
		SddsStreamSession sss = new SddsStreamSession(sddsStreamDef, userid, attachUuid);

		handleAttach(sss);
		sddsSessionMap.put(attachUuid, sss);
		return attachUuid;
	}

	/* (non-Javadoc)
	 * @see BULKIO.dataSDDSOperations#detach(java.lang.String)
	 */
	@Override
	public void detach(String attachId) throws DetachError, StreamInputError {
		TRACE_LOG.enteringMethod(attachId);
		SddsStreamSession sss = sddsSessionMap.get(attachId);
		if (sss == null) {
			return;
		}
		handleDetach(sss);

		sddsSessionMap.remove(attachId);
		streamSRIMap.remove(sss.getSddsStreamDef().id);
	}

	/* (non-Javadoc)
	 * @see BULKIO.dataSDDSOperations#getStreamDefinition(java.lang.String)
	 */
	@Override
	public SDDSStreamDefinition getStreamDefinition(String attachId) throws StreamInputError {
		SddsStreamSession sss = sddsSessionMap.get(attachId);
		if (sss == null) {
			throw new StreamInputError("No SDDSStreamDefinition found for attachId: " + attachId);
		}
		return sss.getSddsStreamDef();
	}

	/* (non-Javadoc)
	 * @see BULKIO.dataSDDSOperations#getUser(java.lang.String)
	 */
	@Override
	public String getUser(String attachId) throws StreamInputError {
		SddsStreamSession sss = sddsSessionMap.get(attachId);
		if (sss == null) {
			throw new StreamInputError("No user found for attachId: " + attachId);
		}
		return sss.getUserId();
	}

	/* (non-Javadoc)
	 * @see BULKIO.dataSDDSOperations#attachedSRIs()
	 */
	@Override
	public StreamSRI[] attachedSRIs() {
		return this.streamSRIMap.values().toArray(new StreamSRI[this.streamSRIMap.size()]);
	}

	/* (non-Javadoc)
	 * @see BULKIO.dataSDDSOperations#pushSRI(BULKIO.StreamSRI, BULKIO.PrecisionUTCTime)
	 * Push SRI to downstream components with time tag
	 */
	@Override
	public void pushSRI(StreamSRI sri, PrecisionUTCTime time) {
		if (TRACE_LOG.enabled) {
			TRACE_LOG.enteringMethod(StreamSRIUtil.toString(sri), time);
		}
		// FYI: StreamSRI <=> SDDSStreamDefinition.id
		if (sri != null) {
			String streamId = sri.streamID;
			if (streamId != null) {
				StreamSRI oldSri = this.streamSRIMap.put(streamId, sri);
				if (!StreamSRIUtil.equals(oldSri, sri)) {
					handleStreamSRIChanged(streamId, oldSri, sri);
				}
			}
		} else {
			TRACE_LOG.message("Ignoring null StreamSRI");
		}
	}

	@NonNull
	public Servant toServant(POA poa) {
		return new dataSDDSPOATie(this, poa);
	}
	
	/**
	 * @param streamID stream ID
	 * @return StreamSRI for specified stream ID (null if it does not exist or has been detached)
	 */
	@Nullable
	public StreamSRI getSri(@NonNull String streamID) {
		return this.streamSRIMap.get(streamID);
	}
	
	protected StreamSRI putSri(@NonNull String streamID, StreamSRI sri) {
		return this.streamSRIMap.put(streamID, sri);
	}

	// =========================================================================
	// Methods that sub-classes MUST implement
	// =========================================================================

	/** callback to notify when there is a new SDDSStreamDefinition attach request. */
	protected abstract void handleAttach(@NonNull SddsStreamSession sss) throws AttachError, StreamInputError;

	/** callback to notify when there is a SDDSStreamDefinition detach request. */
	protected abstract void handleDetach(@NonNull SddsStreamSession sss) throws DetachError, StreamInputError;

	/** callback to notify that SRI has changed for specified streamID. */
	protected abstract void handleStreamSRIChanged(@NonNull String streamID, @Nullable StreamSRI oldSri, @NonNull StreamSRI newSri);

	// =========================================================================
	// Inner classes
	// =========================================================================

	@NonNullByDefault
	public static class SddsStreamSession {
		private final SDDSStreamDefinition sddsStreamDef;
		private final String userId;
		private final String attachId;

		public SddsStreamSession(SDDSStreamDefinition sddsStreamDef, String userId, String attachId) {
			super();
			this.sddsStreamDef = sddsStreamDef;
			this.userId = userId;
			this.attachId = attachId;
		}

		/**
		 * @return the sddsStreamDef
		 */
		public SDDSStreamDefinition getSddsStreamDef() {
			return sddsStreamDef;
		}

		/**
		 * @return the userId
		 */
		public String getUserId() {
			return userId;
		}

		/**
		 * @return the attachId
		 */
		public String getAttachId() {
			return attachId;
		}

		public String toString() {
			return "( SDDSStreamDefinition ["
					+ " id=" + sddsStreamDef.id
					+ " multicastAddress=" + sddsStreamDef.multicastAddress
					+ " port=" + sddsStreamDef.port
					+ " vlan=" + sddsStreamDef.vlan
					+ " dataFormat=" + sddsStreamDef.dataFormat.value()
					+ " sampleRate=" + sddsStreamDef.sampleRate
					+ " timeTagValid=" + sddsStreamDef.timeTagValid
					+ " privateInfo=" + sddsStreamDef.privateInfo
					+ " ]; userID = " + userId
					+ " attachID = " + attachId
					+ " ; " + super.toString()
					+ " ) " + hashCode();
		}
	} // end class SddsStreamSession 
}

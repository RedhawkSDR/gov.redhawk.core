
package bulkio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.omg.CORBA.TCKind;
import org.ossie.properties.AnyUtils;
import org.ossie.component.QueryableUsesPort;
import org.apache.log4j.Logger;
import CF.DataType;
import CF.PropertiesHelper;
import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;
import BULKIO.UsesPortStatistics;
import ExtendedCF.UsesConnection;
import BULKIO.PortUsageType;
import BULKIO.dataSDDSPackage.AttachError;
import BULKIO.dataSDDSPackage.DetachError;
import BULKIO.dataSDDSPackage.InputUsageState;
import BULKIO.dataSDDSPackage.StreamInputError;
import BULKIO.dataSDDSOperations;

import bulkio.linkStatistics;
import bulkio.Int8Size;



/**
 * @generated
 */
public class OutSDDSPort extends BULKIO.UsesPortStatisticsProviderPOA {

    /**
     * @generated
     */
    protected String name;

    /**
     * @generated
     */
    protected Object updatingPortsLock;

    /**
     * @generated
     */
    protected boolean active;

    /**
     * @generated
     */
    protected boolean refreshSRI;

    /**
     * @generated
     */
    protected BULKIO.SDDSStreamDefinition lastStreamData;

    /**
     * @generated
     */
    protected String userId;


    /**
     * Map of connection Ids to port objects
     * @generated
     */
    protected Map<String, dataSDDSOperations> outConnections = null;

    /**
     * Map of connection ID to statistics
     * @generated
     */
    protected Map<String, linkStatistics > stats;


    /**
     * Map of stream IDs to streamSRI/Time pairs
     * @generated
     */
    protected Map<String, streamTimePair> currentSRIs;


    /**
     * Map of attachIds to SDDSStreamDefinition/UserID pairs
     * @generated
     */
    protected Map<String, streamdefUseridPair> attachedGroup;

    /**
     * Map of attachIds to Ports
     * @generated
     */
    protected Map<String, org.omg.CORBA.Object> attachedPorts;


    /**
     *
     */
    protected Logger   logger = null;


    /**
     *
     */
    protected ConnectionEventListener callback = null;


    public OutSDDSPort(String portName ){
	this( portName, null, null );
    }

    public OutSDDSPort(String portName,
		       Logger logger ) {
	this( portName, logger, null );
    }

    /**
     * @generated
     */
    public OutSDDSPort(String portName,
		       Logger logger,
		       ConnectionEventListener  eventCB ) {
        this.name = portName;
        this.updatingPortsLock = new Object();
        this.active = false;
        this.outConnections = new HashMap<String, BULKIO.dataSDDSOperations>();
        this.stats = new HashMap<String, linkStatistics>();
        this.currentSRIs = new HashMap<String, streamTimePair>();
        this.attachedGroup = new HashMap<String, streamdefUseridPair>();
        this.attachedPorts = new HashMap<String, org.omg.CORBA.Object>();
        this.lastStreamData = null;
	this.logger = logger;
	this.callback = eventCB;
	if ( this.logger != null ) {
	    this.logger.debug( "bulkio::OutPort CTOR port: " + portName ); 
	}
    }

    public void setLogger( Logger newlogger ){
        synchronized (this.updatingPortsLock) {
	    logger = newlogger;
	}
    }

    public void setConnectionEventListener( ConnectionEventListener newListener ){
        synchronized (this.updatingPortsLock) {
	    callback = newListener;
	}
    }

    /**
     * @generated
     */
    public PortUsageType state() {
        PortUsageType state = PortUsageType.IDLE;

        if (this.outConnections.size() > 0) {
            state = PortUsageType.ACTIVE;
        }

        return state;
    }

    /**
     * @generated
     */
    public void enableStats(final boolean enable)
    {
        for (String connId : outConnections.keySet()) {
            stats.get(connId).setEnabled(enable);
        }
    };

    /**
     * @generated
     */
    public UsesPortStatistics[] statistics() {
        UsesPortStatistics[] portStats = new UsesPortStatistics[this.outConnections.size()];
        int i = 0;
        
        synchronized (this.updatingPortsLock) {
            for (String connId : this.outConnections.keySet()) {
                portStats[i] = new UsesPortStatistics(connId, this.stats.get(connId).retrieve());
            }
        }
        
        return portStats;
    }

    /**
     * @generated
     */
    public StreamSRI[] activeSRIs() 
    {
        List<StreamSRI> sris = new ArrayList<StreamSRI>();
        for (streamTimePair val : this.currentSRIs.values()) {
            sris.add(val.stream);
        }
        return sris.toArray(new StreamSRI[0]);
    }

    /**
     * @generated
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * @generated
     */
    public void setActive(final boolean active) {
        this.active = active;
    }

    /**
     * @generated
     */
    public String getName() {
        return this.name;
    }

    /**
     * @generated
     */
    public HashMap<String, BULKIO.dataSDDSOperations> getPorts() {
        return new HashMap<String, dataSDDSOperations>();
    }
 
    /**
     * pushSRI
     *     description: send out SRI describing the data payload
     *
     *  H: structure of type BULKIO::StreamSRI with the SRI for this stream
     *    hversion
     *    xstart: start time of the stream
     *    xdelta: delta between two samples
     *    xunits: unit types from Platinum specification
     *    subsize: 0 if the data is one-dimensional
     *    ystart
     *    ydelta
     *    yunits: unit types from Platinum specification
     *    mode: 0-scalar, 1-complex
     *    streamID: stream identifier
     *    sequence<CF::DataType> keywords: unconstrained sequence of key-value pairs for additional description
     *
     *  T: structure of type BULKIO::PrecisionUTCTime with the Time for this stream
     *    tcmode: timecode mode
     *    tcstatus: timecode status
     *    toff: Fractional sample offset
     *    twsec
     *    tfsec
     * @generated
     */
    public void pushSRI(StreamSRI header, PrecisionUTCTime time) 
    {

	if ( logger != null ) {
	    logger.trace("bulkio.OutPort pushSRI  ENTER (port=" + name +")" );
	}

        // Header cannot be null
        if (header == null) {
	    if ( logger != null ) {
		logger.trace("bulkio.OutPort pushSRI  EXIT (port=" + name +")" );
	    }
	    return;
	}

        // Header cannot have null keywords
        if (header.keywords == null) header.keywords = new DataType[0];

        synchronized(this.updatingPortsLock) {    // don't want to process while command information is coming in
            if (this.active) {

                for (Entry<String, dataSDDSOperations> p : this.outConnections.entrySet()) {
                    try {
                        p.getValue().pushSRI(header, time);
                    } catch(Exception e) {
			if ( logger != null ) {
			    logger.error("PUSH-SRI FAILED, PORT/Connection " + name + "/" + p.getKey() );
			}
                    }
                }
            }

            this.currentSRIs.put(header.streamID, new streamTimePair(header, time));
            this.refreshSRI = false;

        }    // don't want to process while command information is coming in


	if ( logger != null ) {
	    logger.trace("bulkio.OutPort pushSRI  EXIT (port=" + name +")" );
	}
        return;
    }

    /**
     * @generated
     */
    public void connectPort(final org.omg.CORBA.Object connection, final String connectionId) throws CF.PortPackage.InvalidPort, CF.PortPackage.OccupiedPort
    {

	if ( logger != null ) {
	    logger.trace("bulkio.OutPort connectPort ENTER (port=" + name +")" );
	}

        synchronized (this.updatingPortsLock) {
            final dataSDDSOperations port;
            try {
                port = BULKIO.jni.dataSDDSHelper.narrow(connection);
            } catch (final Exception ex) {
		if ( logger != null ) {
		    logger.error("bulkio::OutPort CONNECT PORT: " + name + " PORT NARROW FAILED");
		}
                throw new CF.PortPackage.InvalidPort((short)1, "Invalid port for connection '" + connectionId + "'");
            }
            this.outConnections.put(connectionId, port);
            this.active = true;
            this.stats.put(connectionId, new linkStatistics( this.name, new Int8Size() ) );
            if (this.lastStreamData != null) {
                try {
                    final String attachId = port.attach(this.lastStreamData, this.userId);
                    this.attachedGroup.put(attachId, new streamdefUseridPair(this.lastStreamData, this.userId));
                    this.attachedPorts.put(attachId, connection);
                } catch (AttachError a) {
                    // PASS
                } catch (StreamInputError e) {
                    // PASS
                }
            }
	    if ( logger != null ) {
		logger.debug("bulkio::OutPort CONNECT PORT: " + name + " CONNECTION '" + connectionId + "'");
	    }
        }

    }

    /**
     * @generated
     */
    public void disconnectPort(String connectionId) {

	if ( logger != null ) {
	    logger.trace("bulkio.OutPort disconnectPort ENTER (port=" + name +")" );
	}
        synchronized (this.updatingPortsLock) {
            dataSDDSOperations port = this.outConnections.remove(connectionId);
            this.stats.remove(connectionId);
            this.active = (this.outConnections.size() != 0);

	    if ( logger != null ) {
		logger.trace("bulkio.OutPort DISCONNECT PORT:" + name + " CONNECTION '" + connectionId + "'");
	    }

            for (Entry<String, org.omg.CORBA.Object> entry : this.attachedPorts.entrySet()) {
                if (entry.getValue().equals((org.omg.CORBA.Object) port)) {
                    final String attachId = entry.getKey();
                    this.attachedPorts.remove(attachId);
                    this.attachedGroup.remove(attachId);
                    try {
                        port.detach(attachId);
                    } catch (DetachError e) {
                        // PASS
                    } catch (StreamInputError e) {
                        // PASS
                    }
                    break;
                }
            }
        }

	if ( callback != null ) {
	    callback.connect(connectionId);
	}

	if ( logger != null ) {
	    logger.trace("bulkio.OutPort disconnectPort EXIT (port=" + name +")" );
	}

    }

    /**
     * @generated
     */
    public UsesConnection[] connections() {
        final UsesConnection[] connList = new UsesConnection[this.outConnections.size()];
        int i = 0;
        synchronized (this.updatingPortsLock) {
            for (Entry<String, dataSDDSOperations> ent : this.outConnections.entrySet()) {
                connList[i++] = new UsesConnection(ent.getKey(), (org.omg.CORBA.Object) ent.getValue());
            }
        }
        return connList;
    }

    /**
     * @generated
     */
    public BULKIO.SDDSStreamDefinition getStreamDefinition(final String attachId)
    {
        final streamdefUseridPair defPair = this.attachedGroup.get(attachId);
        return (defPair == null) ? new BULKIO.SDDSStreamDefinition() : defPair.streamDef;
    }

    /**
     * @generated
     */
    public String getUser(final String attachId)
    {
        final streamdefUseridPair defPair = this.attachedGroup.get(attachId);
        return (defPair == null) ? "" : defPair.userId;
    }

    /**
     * @generated
     */
    public InputUsageState usageState()
    {
        if (this.attachedGroup.size() == 0) {
            return InputUsageState.IDLE;
        } else if (this.attachedGroup.size() == 1) {
            return InputUsageState.BUSY;
        } else {
            return InputUsageState.ACTIVE;
        }
    }

    /**
     * @generated
     */
    public BULKIO.SDDSStreamDefinition[] attachedStreams()
    {
        return new BULKIO.SDDSStreamDefinition[] { 
	    (this.lastStreamData != null) ? this.lastStreamData : new BULKIO.SDDSStreamDefinition()
	};
    }

    /**
     * @generated
     */
    public String[] attachmentIds()
    {
        return this.attachedGroup.keySet().toArray(new String[0]);
    }

    /**
     * @generated
     */
    public String attach(final BULKIO.SDDSStreamDefinition streamDef, final String userId) throws AttachError, StreamInputError
    {
	if ( logger != null ) {
	    logger.trace("bulkio.OutPort attach ENTER (port=" + name +")" );
	}

        String attachId = null;
        synchronized (this.updatingPortsLock) {
            this.userId = userId;
            this.lastStreamData = streamDef;
            for (Entry<String, org.omg.CORBA.Object> entry : this.attachedPorts.entrySet()) {
                try {
                    ((dataSDDSOperations) entry.getValue()).detach(entry.getKey());
                } catch (DetachError d) {
                    // PASS
                }
                this.attachedGroup.remove(entry.getKey());
            }
            
            for (dataSDDSOperations port : this.outConnections.values()) {
                attachId = port.attach(streamDef, userId);
                this.attachedGroup.put(attachId, new streamdefUseridPair(streamDef, userId));
                this.attachedPorts.put(attachId, (org.omg.CORBA.Object) port);
            }
        }


	if ( logger != null ) {
	    logger.trace("SDDS PORT: ATTACH COMLPETED ID:" + attachId + " NAME(userid):" + userId );
	    logger.trace("bulkio.OutPort attach EXIT (port=" + name +")" );
	}
        return attachId;
    }

    /**
     * @generated
     */
    public void detach(String attachId, String connectionId) throws DetachError, StreamInputError
    {
	if ( logger != null ) {
	    logger.trace("bulkio.OutPort detach ENTER (port=" + name +")" );
	}

        synchronized (this.updatingPortsLock) {
            dataSDDSOperations port = this.outConnections.get(connectionId);
            if (this.attachedPorts.containsKey(attachId)) {
                port.detach(attachId);
            }
        }
	if ( logger != null ) {
	    logger.trace("bulkio.OutPort detach ENTER (port=" + name +")" );
	}

    }

    /**
     * @generated
     */
    public class streamTimePair {
        /** @generated */
        StreamSRI stream;
        /** @generated */
        PrecisionUTCTime time;
        
        /** 
         * @generated
         */
        public streamTimePair(final BULKIO.StreamSRI stream, final BULKIO.PrecisionUTCTime time) {
            this.stream = stream;
            this.time = time;
        }
    }

    /**
     * @generated
     */
    public class streamdefUseridPair {
        /** @generated */
        BULKIO.SDDSStreamDefinition streamDef;
        /** @generated */
        String userId;
        
        /** 
         * @generated
         */
        public streamdefUseridPair(final BULKIO.SDDSStreamDefinition streamDef, final String userId) {
            this.streamDef = streamDef;
            this.userId = userId;
        }
    }

}

package bulkio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.omg.CORBA.TCKind;
import org.ossie.properties.AnyUtils;
import org.apache.log4j.Logger;
import CF.DataType;
import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;
import BULKIO.UsesPortStatistics;
import ExtendedCF.UsesConnection;
import BULKIO.PortUsageType;
import BULKIO.dataLongOperations;

import bulkio.linkStatistics;
import bulkio.Int32Size;
import bulkio.ConnectionEventListener;

/**
 * 
 */
public class OutInt32Port extends BULKIO.UsesPortStatisticsProviderPOA {

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
     * Map of connection Ids to port objects
     * @generated
     */
    protected Map<String, dataLongOperations> outConnections = null;

    /**
     * Map of connection ID to statistics
     * @generated
     */
    protected Map<String, linkStatistics > stats;

    /**
     * Map of stream IDs to streamSRI's
     * @generated
     */
    protected Map<String, StreamSRI > currentSRIs;

    /**
     *
     */
    protected Logger   logger = null;


    /**
     * Event listener when connect/disconnet events happen
     */
    protected ConnectionEventListener   callback = null;


    public OutInt32Port(String portName ){
	this( portName, null, null );
    }

    public OutInt32Port(String portName,
		       Logger logger ) {
	this( portName, logger, null );
    }

    /**
     * @generated
     */
    public OutInt32Port(String portName,
		       Logger logger,
		       ConnectionEventListener  eventCB ) {
        name = portName;
        updatingPortsLock = new Object();
        active = false;
	outConnections = new HashMap<String, dataLongOperations>();
        stats = new HashMap<String, linkStatistics >();
        currentSRIs = new HashMap<String, StreamSRI>();
	callback = eventCB;
	logger = logger;

	if ( this.logger != null ) {
	    this.logger.debug( "bulkio::OutPort CTOR port: " + portName ); 
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
        return this.currentSRIs.values().toArray(new StreamSRI[0]);
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
    public HashMap<String, dataLongOperations> getPorts() {
        return new HashMap<String, dataLongOperations>();
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
     * @generated
     */
    public void pushSRI(StreamSRI header)
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

                for (Entry<String, dataLongOperations> p : this.outConnections.entrySet()) {
                    try {
                        p.getValue().pushSRI(header);
                    } catch(Exception e) {
			if ( logger != null ) {
			    logger.error("Call to pushSRI failed on port " + name + " connection " + p.getKey() );
			}
                    }
                }
            }

            this.currentSRIs.put(header.streamID, header);
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
    public void pushPacket(int[] data, PrecisionUTCTime time, boolean endOfStream, String streamID)
    {
	if ( logger != null ) {
	    logger.trace("bulkio.OutPort pushPacket  ENTER (port=" + name +")" );
	}

        if (this.refreshSRI) {
            if (!this.currentSRIs.containsKey(streamID)) {
                StreamSRI sri = new StreamSRI();
                sri.mode = 0;
                sri.xdelta = 1.0;
                sri.ydelta = 0.0;
                sri.subsize = 0;
                sri.xunits = 1; // TIME_S
                sri.streamID = streamID;
                this.currentSRIs.put(streamID, sri);
            }
            this.pushSRI(this.currentSRIs.get(streamID));
        }

        synchronized(this.updatingPortsLock) {    // don't want to process while command information is coming in
            int[] odata = data;
            if (this.active) {
                for (Entry<String, dataLongOperations> p : this.outConnections.entrySet()) {
                    try {
                        p.getValue().pushPacket( odata, time, endOfStream, streamID);
                        this.stats.get(p.getKey()).update( odata.length, (float)0.0, endOfStream, streamID, false);
                    } catch(Exception e) {
			if ( logger != null ) {
			    logger.error("Call to pushPacket failed on port " + name + " connection " + p.getKey() );
			}
                    }
                }
            }
	    if ( endOfStream ) {
		if ( this.currentSRIs.containsKey(streamID) ) {
		    this.currentSRIs.remove(streamID);
		}
	    }

        }    // don't want to process while command information is coming in

	if ( logger != null ) {
	    logger.trace("bulkio.OutPort pushPacket  EXIT (port=" + name +")" );
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
            final dataLongOperations port;
            try {
                port = BULKIO.jni.dataLongHelper.narrow(connection);
            } catch (final Exception ex) {
		if ( logger != null ) {
		    logger.error("bulkio::OutPort CONNECT PORT: " + name + " PORT NARROW FAILED");
		}
                throw new CF.PortPackage.InvalidPort((short)1, "Invalid port for connection '" + connectionId + "'");
            }
            this.outConnections.put(connectionId, port);
            this.active = true;
            this.stats.put(connectionId, new linkStatistics( this.name, new Int32Size() ) );
            this.refreshSRI = true;
	    if ( logger != null ) {
		logger.debug("bulkio::OutPort CONNECT PORT: " + name + " CONNECTION '" + connectionId + "'");
	    }
        }

	if ( logger != null ) {
	    logger.trace("bulkio.OutPort connectPort EXIT (port=" + name +")" );
	}

	if ( callback != null ) {
	    callback.connect(connectionId);
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
            dataLongOperations port = this.outConnections.remove(connectionId);
            this.stats.remove(connectionId);
            this.active = (this.outConnections.size() != 0);
	    if ( logger != null ) {
		logger.trace("bulkio.OutPort DISCONNECT PORT:" + name + " CONNECTION '" + connectionId + "'");
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
            for (Entry<String, dataLongOperations> ent : this.outConnections.entrySet()) {
                connList[i++] = new UsesConnection(ent.getKey(), (org.omg.CORBA.Object) ent.getValue());
            }
        }
        return connList;
    }

}


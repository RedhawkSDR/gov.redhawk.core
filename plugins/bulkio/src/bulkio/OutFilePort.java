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
import BULKIO.dataFileOperations;

import bulkio.linkStatistics;
import bulkio.Int8Size;
import bulkio.ConnectionEventListener;
import bulkio.connection_descriptor_struct;
import org.ossie.properties.*;

/**
 * 
 */
public class OutFilePort extends BULKIO.UsesPortStatisticsProviderPOA {

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
    protected Map<String, dataFileOperations> outConnections = null;

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

    protected List<connection_descriptor_struct> filterTable = null;


    public OutFilePort(String portName ){
	this( portName, null, null );
    }

    public OutFilePort(String portName,
		       Logger logger ) {
	this( portName, logger, null );
    }

    /**
     * @generated
     */
    public OutFilePort(String portName,
		       Logger logger,
		       ConnectionEventListener  eventCB ) {
        name = portName;
        updatingPortsLock = new Object();
        active = false;
	outConnections = new HashMap<String, dataFileOperations>();
        stats = new HashMap<String, linkStatistics >();
        currentSRIs = new HashMap<String, StreamSRI>();
	callback = eventCB;
	this.logger = logger;
        filterTable = null;
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
    public HashMap<String, dataFileOperations> getPorts() {
        return new HashMap<String, dataFileOperations>();
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
		// state if this port is not listed in the filter table... then pushSRI down stream
		boolean portListed = false;

		// for each connection
                for (Entry<String, dataFileOperations> p : this.outConnections.entrySet()) {
		    
		    // if connection is in the filter table
                    for (connection_descriptor_struct ftPtr : bulkio.utils.emptyIfNull(this.filterTable) ) {

			// if there is an entry for this port in the filter table....so save that state
			if (ftPtr.port_name.equals(this.name)) {
			    portListed = true;		    
			}

			if ( logger != null ) {
			    logger.trace( "pushSRI - FilterMatch port:" + this.name + " connection:" + p.getKey() + 
					    " streamID:" + header.streamID ); 
			}

			if ( (ftPtr.port_name.getValue().equals(this.name)) &&
			     (ftPtr.connection_id.getValue().equals(p.getKey())) &&
			     (ftPtr.stream_id.getValue().equals(header.streamID))) {
                            try {
				if ( logger != null ) {
				    logger.trace( "pushSRI - FilterMatch port:" + this.name + " connection:" + p.getKey() + 
						  " streamID:" + header.streamID ); 
				}
				p.getValue().pushSRI(header);
                            } catch(Exception e) {
                                if ( logger != null ) {
				    logger.error("Call to pushSRI failed on port " + name + " connection " + p.getKey() );
                                }
                            }
                        }
                    }
		}

		// no entry exists for this port in the filter table so all connections get SRI data
		if (!portListed ) {
		    for (Entry<String, dataFileOperations> p : this.outConnections.entrySet()) {
                        try {
			    if ( logger != null ) {
				logger.trace( "pushSRI - NO Filter port:" + this.name + " connection:" + p.getKey() + 
					      " streamID:" + header.streamID ); 
			    }
			    p.getValue().pushSRI(header);
			} catch(Exception e) {
			    if ( logger != null ) {
				logger.error("Call to pushSRI failed on port " + name + " connection " + p.getKey() );
			    }
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

    public void updateConnectionFilter(List<connection_descriptor_struct> _filterTable) {
        this.filterTable = _filterTable;
    };

    /**
     * @generated
     */
    public void pushPacket(String data, PrecisionUTCTime time, boolean endOfStream, String streamID)
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
            String odata = data;
            if (this.active) {
		boolean portListed = false;
                for (Entry<String, dataFileOperations> p : this.outConnections.entrySet()) {

                    for (connection_descriptor_struct ftPtr : bulkio.utils.emptyIfNull(this.filterTable) ) {

			if (ftPtr.port_name.getValue().equals(this.name)) {
			    portListed = true;		    
			}
                        if ( (ftPtr.port_name.getValue().equals(this.name)) && 
			     (ftPtr.connection_id.getValue().equals(p.getKey())) && 
			     (ftPtr.stream_id.getValue().equals(streamID)) ) {
                            try {
                                p.getValue().pushPacket( odata, time, endOfStream, streamID);
                                this.stats.get(p.getKey()).update( odata.length(), (float)0.0, endOfStream, streamID, false);
                            } catch(Exception e) {
                                if ( logger != null ) {
                                    logger.error("Call to pushPacket failed on port " + name + " connection " + p.getKey() );
                                }
                            }
                        }
                    }
                }

		if (!portListed ){
		    for (Entry<String, dataFileOperations> p : this.outConnections.entrySet()) {
                        try {
                            p.getValue().pushPacket( odata, time, endOfStream, streamID);
                            this.stats.get(p.getKey()).update( odata.length(), (float)0.0, endOfStream, streamID, false);
                        } catch(Exception e) {
                            if ( logger != null ) {
                                logger.error("Call to pushPacket failed on port " + name + " connection " + p.getKey() );
                            }
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
            final dataFileOperations port;
            try {
                port = BULKIO.jni.dataFileHelper.narrow(connection);
            } catch (final Exception ex) {
		if ( logger != null ) {
		    logger.error("bulkio::OutPort CONNECT PORT: " + name + " PORT NARROW FAILED");
		}
                throw new CF.PortPackage.InvalidPort((short)1, "Invalid port for connection '" + connectionId + "'");
            }
            this.outConnections.put(connectionId, port);
            this.active = true;
            this.stats.put(connectionId, new linkStatistics( this.name, new Int8Size() ) );
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
            boolean portListed = false;
            for (connection_descriptor_struct ftPtr : bulkio.utils.emptyIfNull(this.filterTable) ) {
                if (ftPtr.port_name.getValue().equals(this.name)) {
                    portListed = true;
                    break;
                }
            }
            dataFileOperations port = this.outConnections.remove(connectionId);
            if (port != null)
            {
                String odata = "";
                BULKIO.PrecisionUTCTime tstamp = bulkio.time.utils.now();
                for (StreamSRI cSriSid : this.activeSRIs()) {
                    String streamID = cSriSid.streamID;
                    for (String aSIDs : this.stats.get(connectionId).getActiveStreamIDs()) {
                        if (streamID.equals(aSIDs)) {
                            if (portListed) {
                                for (connection_descriptor_struct ftPtr : bulkio.utils.emptyIfNull(this.filterTable)) {
                                    if ( (ftPtr.port_name.getValue().equals(this.name)) &&
					 (ftPtr.connection_id.getValue().equals(connectionId)) &&
					 (ftPtr.stream_id.getValue().equals(streamID))) {
                                        try {
                                            port.pushPacket(odata,tstamp,true,streamID);
                                        } catch(Exception e) {
                                            if ( logger != null ) {
                                                logger.error("Call to pushPacket failed on port " + name + " connection " + connectionId );
                                            }
                                        }
                                    }
                                }
                            } else {
                                try {
                                    port.pushPacket(odata,tstamp,true,streamID);
                                } catch(Exception e) {
                                    if ( logger != null ) {
                                        logger.error("Call to pushPacket failed on port " + name + " connection " + connectionId );
                                    }
                                }
                            }
                        }
                    }
                }
            }
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
            for (Entry<String, dataFileOperations> ent : this.outConnections.entrySet()) {
                connList[i++] = new UsesConnection(ent.getKey(), (org.omg.CORBA.Object) ent.getValue());
            }
        }
        return connList;
    }

}


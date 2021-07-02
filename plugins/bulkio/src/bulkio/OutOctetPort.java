/*
 * This file is protected by Copyright. Please refer to the COPYRIGHT file
 * distributed with this source distribution.
 *
 * This file is part of REDHAWK bulkioInterfaces.
 *
 * REDHAWK bulkioInterfaces is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * REDHAWK bulkioInterfaces is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
/*
 * WARNING: This file is generated from OutPort.java.template.
 *          Do not modify directly.
 */
package bulkio;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import BULKIO.PrecisionUTCTime;
import BULKIO.dataOctetOperations;
import BULKIO.StreamSRI;
import bulkio.OutOctetStream;

/**
 * BulkIO output port implementation for dataOctet.
 */
public class OutOctetPort extends ChunkingOutPort<dataOctetOperations,byte[]> {

    protected Map<String, OutOctetStream> streams;
    public Object streamsMutex;

    public OutOctetPort(String portName) {
        this(portName, null, null);
    }

    public OutOctetPort(String portName, Logger logger) {
        this(portName, logger, null);
    }

    public OutOctetPort(String portName, Logger logger, ConnectionEventListener eventCB) {
        super(portName, logger, eventCB, new OctetDataHelper());
        if (this.logger != null) {
            this.logger.debug("bulkio.OutPort CTOR port: " + portName);
        }
        this.streams = new HashMap<String, OutOctetStream>();
        this.streamsMutex = new Object();
    }

    protected dataOctetOperations narrow(final org.omg.CORBA.Object obj) {
        return BULKIO.jni.dataOctetHelper.narrow(obj);
    }

    protected void sendPacket(dataOctetOperations port, byte[] data, PrecisionUTCTime time,
                              boolean endOfStream, String streamID) {
        port.pushPacket(data, time, endOfStream, streamID);
    }

    public String getRepid() {
        return BULKIO.dataOctetHelper.id();
    }

    public OutOctetStream getStream(String streamID)
    {
        synchronized (this.updatingPortsLock) {
            if (streams.containsKey(streamID)) {
                return streams.get(streamID);
            }
        }
        return null;
    }
  
    public OutOctetStream[] getStreams()
    {
        OutOctetStream[] retval = null;
        Iterator<OutOctetStream> streams_iter = streams.values().iterator();
        synchronized (this.streamsMutex) {
            retval = new OutOctetStream[streams.size()];
            int streams_idx = 0;
            while (streams_iter.hasNext()) {
                retval[streams_idx] = streams_iter.next();
                streams_idx++;
            }
        }
        return retval;
    }
  
    public OutOctetStream createStream(String streamID)
    {
        OutOctetStream stream = null;
        synchronized (this.updatingPortsLock) {
            if (streams.containsKey(streamID)) {
                return streams.get(streamID);
            }
            stream = new OutOctetStream(bulkio.sri.utils.create(streamID), this);
            streams.put(streamID, stream);
        }
        return stream;
    }
  
    public OutOctetStream createStream(BULKIO.StreamSRI sri)
    {
        OutOctetStream stream = null;
        synchronized (this.updatingPortsLock) {
            String streamID = sri.streamID;
            if (streams.containsKey(streamID)) {
                return streams.get(streamID);
            }
            stream = new OutOctetStream(sri, this);
            streams.put(streamID, stream);
        }
        return stream;
    }
}


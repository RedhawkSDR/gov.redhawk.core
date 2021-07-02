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
import BULKIO.dataLongLongOperations;
import BULKIO.StreamSRI;
import bulkio.OutLongLongStream;

/**
 * BulkIO output port implementation for dataLongLong.
 */
public class OutLongLongPort extends ChunkingOutPort<dataLongLongOperations,long[]> {

    protected Map<String, OutLongLongStream> streams;
    public Object streamsMutex;

    public OutLongLongPort(String portName) {
        this(portName, null, null);
    }

    public OutLongLongPort(String portName, Logger logger) {
        this(portName, logger, null);
    }

    public OutLongLongPort(String portName, Logger logger, ConnectionEventListener eventCB) {
        super(portName, logger, eventCB, new LongLongDataHelper());
        if (this.logger != null) {
            this.logger.debug("bulkio.OutPort CTOR port: " + portName);
        }
        this.streams = new HashMap<String, OutLongLongStream>();
        this.streamsMutex = new Object();
    }

    protected dataLongLongOperations narrow(final org.omg.CORBA.Object obj) {
        return BULKIO.jni.dataLongLongHelper.narrow(obj);
    }

    protected void sendPacket(dataLongLongOperations port, long[] data, PrecisionUTCTime time,
                              boolean endOfStream, String streamID) {
        port.pushPacket(data, time, endOfStream, streamID);
    }

    public String getRepid() {
        return BULKIO.dataLongLongHelper.id();
    }

    public OutLongLongStream getStream(String streamID)
    {
        synchronized (this.updatingPortsLock) {
            if (streams.containsKey(streamID)) {
                return streams.get(streamID);
            }
        }
        return null;
    }
  
    public OutLongLongStream[] getStreams()
    {
        OutLongLongStream[] retval = null;
        Iterator<OutLongLongStream> streams_iter = streams.values().iterator();
        synchronized (this.streamsMutex) {
            retval = new OutLongLongStream[streams.size()];
            int streams_idx = 0;
            while (streams_iter.hasNext()) {
                retval[streams_idx] = streams_iter.next();
                streams_idx++;
            }
        }
        return retval;
    }
  
    public OutLongLongStream createStream(String streamID)
    {
        OutLongLongStream stream = null;
        synchronized (this.updatingPortsLock) {
            if (streams.containsKey(streamID)) {
                return streams.get(streamID);
            }
            stream = new OutLongLongStream(bulkio.sri.utils.create(streamID), this);
            streams.put(streamID, stream);
        }
        return stream;
    }
  
    public OutLongLongStream createStream(BULKIO.StreamSRI sri)
    {
        OutLongLongStream stream = null;
        synchronized (this.updatingPortsLock) {
            String streamID = sri.streamID;
            if (streams.containsKey(streamID)) {
                return streams.get(streamID);
            }
            stream = new OutLongLongStream(sri, this);
            streams.put(streamID, stream);
        }
        return stream;
    }
}


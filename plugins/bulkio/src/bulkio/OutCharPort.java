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
import BULKIO.dataCharOperations;
import BULKIO.StreamSRI;
import bulkio.OutCharStream;

/**
 * BulkIO output port implementation for dataChar.
 */
public class OutCharPort extends ChunkingOutPort<dataCharOperations,char[]> {

    protected Map<String, OutCharStream> streams;
    public Object streamsMutex;

    public OutCharPort(String portName) {
        this(portName, null, null);
    }

    public OutCharPort(String portName, Logger logger) {
        this(portName, logger, null);
    }

    public OutCharPort(String portName, Logger logger, ConnectionEventListener eventCB) {
        super(portName, logger, eventCB, new CharDataHelper());
        if (this.logger != null) {
            this.logger.debug("bulkio.OutPort CTOR port: " + portName);
        }
        this.streams = new HashMap<String, OutCharStream>();
        this.streamsMutex = new Object();
    }

    protected dataCharOperations narrow(final org.omg.CORBA.Object obj) {
        return BULKIO.jni.dataCharHelper.narrow(obj);
    }

    protected void sendPacket(dataCharOperations port, char[] data, PrecisionUTCTime time,
                              boolean endOfStream, String streamID) {
        port.pushPacket(data, time, endOfStream, streamID);
    }

    public String getRepid() {
        return BULKIO.dataCharHelper.id();
    }

    public OutCharStream getStream(String streamID)
    {
        synchronized (this.updatingPortsLock) {
            if (streams.containsKey(streamID)) {
                return streams.get(streamID);
            }
        }
        return null;
    }
  
    public OutCharStream[] getStreams()
    {
        OutCharStream[] retval = null;
        Iterator<OutCharStream> streams_iter = streams.values().iterator();
        synchronized (this.streamsMutex) {
            retval = new OutCharStream[streams.size()];
            int streams_idx = 0;
            while (streams_iter.hasNext()) {
                retval[streams_idx] = streams_iter.next();
                streams_idx++;
            }
        }
        return retval;
    }
  
    public OutCharStream createStream(String streamID)
    {
        OutCharStream stream = null;
        synchronized (this.updatingPortsLock) {
            if (streams.containsKey(streamID)) {
                return streams.get(streamID);
            }
            stream = new OutCharStream(bulkio.sri.utils.create(streamID), this);
            streams.put(streamID, stream);
        }
        return stream;
    }
  
    public OutCharStream createStream(BULKIO.StreamSRI sri)
    {
        OutCharStream stream = null;
        synchronized (this.updatingPortsLock) {
            String streamID = sri.streamID;
            if (streams.containsKey(streamID)) {
                return streams.get(streamID);
            }
            stream = new OutCharStream(sri, this);
            streams.put(streamID, stream);
        }
        return stream;
    }
}


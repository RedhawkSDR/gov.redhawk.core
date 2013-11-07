
package bulkio;

import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;
import org.omg.CORBA.CharHolder;
import org.omg.CORBA.ShortHolder;



/**
 * A class to hold packet data.
 * @generated
 */
public class DataTransfer< DT extends Object > {

    /** @generated */
    public final DT dataBuffer;
    /** @generated */
    public final PrecisionUTCTime T;
    /** @generated */
    public final boolean EOS;
    /** @generated */
    public final String streamID;
    /** @generated */
    public final StreamSRI SRI;
    /** @generated */
    public final boolean inputQueueFlushed;
    /** @generated */
    public final boolean sriChanged;
        
    /**
     * @generated
     */
    public DataTransfer(DT data, PrecisionUTCTime time, boolean endOfStream, String streamID, StreamSRI H, boolean sriChanged, boolean inputQueueFlushed) {
	this.dataBuffer = data;
	this.T = time;
	this.EOS = endOfStream;
	this.streamID = streamID;
	this.SRI = H;
	this.inputQueueFlushed = inputQueueFlushed;
	this.sriChanged = sriChanged;
    };
        
    /**
     * @generated
     */
    public DT getData() {
	return this.dataBuffer;
    }
        
    /**
     * @generated
     */
    public PrecisionUTCTime getTime() {
	return this.T;
    }
        
    /**
     * @generated
     */
    public boolean getEndOfStream() {
	return this.EOS;
    }
        
    /**
     * @generated
     */
    public String getStreamID() {
	return this.streamID;
    }
        
    /**
     * @generated
     */
    public StreamSRI getSRI() {
	return this.SRI;
    }
        
    /**
     * This returns true if the input queue for the port was cleared because
     * the queue reached its size limit. The number of packets discarded
     * before this packet is equal to maxQueueDepth.
     * @generated
     */
    public boolean inputQueueFlushed() {
	return this.inputQueueFlushed;
    }
        
    /**
     * @generated
     */
    public boolean sriChanged() {
	return this.sriChanged;
    }
};






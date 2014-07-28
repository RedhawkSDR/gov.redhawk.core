package bulkio;

import CF.DataType;
import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;

  /**
     * @generated
     */
class sriState {
    /** @generated */
    protected StreamSRI sri;
    /** @generated */
    protected boolean changed;
        
    /**
     * @generated
     */
    public sriState(StreamSRI sri, boolean changed) {
	this.sri = sri;
	this.changed = changed;
    }
        
    /**
     * @generated
     */
    public StreamSRI getSRI() {
	return this.sri;
    }
        
    /**
     * @generated
     */
    public boolean isChanged() {
	return this.changed;
    }
        
    /**
     * @generated
     */
    public void setSRI(StreamSRI sri) {
	this.sri = sri;
    }
        
    /**
     * @generated
     */
    public void setChanged(boolean changed) {
	this.changed = changed;
    }
}


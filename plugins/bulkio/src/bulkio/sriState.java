package bulkio;
/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

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


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

import BULKIO.PortStatistics;
import BULKIO.PortUsageType;
import BULKIO.ProvidesPortStatisticsProviderOperations;
import BULKIO.StreamSRI;
import BULKIO.updateSRIOperations;

/**
 * 
 */
public abstract class AbstractBulkIOPort implements ProvidesPortStatisticsProviderOperations, updateSRIOperations {

    private StreamSRI sri;
    private PortStatistics stats = new PortStatistics();

    public PortUsageType state() {
        return PortUsageType.ACTIVE;
    }
    
    public PortStatistics getPortStatistics() {
        return stats;
    }

    public PortStatistics statistics() {
        return stats;
    }

    public StreamSRI[] activeSRIs() {
        if (sri == null) {
            return new StreamSRI[0];
        }
        return new StreamSRI[] { sri };
    }

    public void pushSRI(StreamSRI sri) {
        this.sri = sri;
    }

    public StreamSRI getSri() {
        return sri;
    }
}

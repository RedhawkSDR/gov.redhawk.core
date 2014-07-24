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

import java.util.HashSet;
import java.util.Set;
import BULKIO.StreamSRI;
import BULKIO.PrecisionUTCTime;

public class SriMapStruct {
        public SriMapStruct(StreamSRI sri, PrecisionUTCTime time) {
            this.sri = sri;
            this.connections = new HashSet<String>();
            this.time = time; 
        }

        public SriMapStruct(StreamSRI sri, Set<String> connections) {
            this.sri = sri;
            this.connections = connections;
            this.time = null;
        }

        public SriMapStruct(StreamSRI sri, Set<String> connections, PrecisionUTCTime time) {
            this.sri = sri;
            this.connections = connections;
            this.time = time;
        }

        public SriMapStruct(StreamSRI sri) {
            this.sri = sri;
            this.connections = new HashSet<String>(); 
            this.time = null;
        }
    
        public SriMapStruct() {
            this.sri = bulkio.sri.utils.create();
            this.connections = new HashSet<String>();
            this.time = null;
        }

        public StreamSRI sri;
        public Set<String> connections;
        public PrecisionUTCTime time;
};

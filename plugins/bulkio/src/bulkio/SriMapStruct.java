package bulkio;

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

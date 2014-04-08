package bulkio;

import java.util.HashSet;
import java.util.Set;
import BULKIO.StreamSRI;

public class SriMapStruct {
        public SriMapStruct(StreamSRI sri, Set<String> connections) {
            this.sri = sri;
            this.connections = connections;
        }

        public SriMapStruct(StreamSRI sri) {
            this.sri = sri;
            this.connections = new HashSet<String>(); 
        }
    
        public SriMapStruct() {
            this.sri = bulkio.sri.utils.create();
            this.connections = new HashSet<String>();
        }

        public StreamSRI sri;
        public Set<String> connections;
};

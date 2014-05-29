package bulkio.vita49;

import BULKIO.dataVITA49Operations;
import BULKIO.dataVITA49Package.DetachError;
import BULKIO.dataVITA49Package.StreamInputError;

//
// Stream Attachment represents a single port attachment
//
public class VITA49StreamAttachment {
        public VITA49StreamAttachment() {
            this(null, null, null);
        }

        public VITA49StreamAttachment(String connectionId, dataVITA49Operations inputPort) {
            this(connectionId, null, inputPort);
        }

        public VITA49StreamAttachment(String connectionId, String attachId, dataVITA49Operations inputPort) {
            this.connectionId = connectionId;
            this.attachId = attachId;
            this.inputPort = inputPort;
        }

        public void detach() throws DetachError,StreamInputError {
            if (this.attachId != null && !this.attachId.isEmpty()){
                this.inputPort.detach(attachId);
                this.attachId = null;
            }
        }

        public String getConnectionId(){
            return this.connectionId;
        }

        public String getAttachId(){
            return this.attachId;
        }

        public dataVITA49Operations getInputPort(){
            return this.inputPort;
        }

        public void setConnectionId(String id){
            this.connectionId = id;
        }

        public void setAttachId(String id){
            this.attachId = id;
        }

        public void setInputPort(dataVITA49Operations port){
            this.inputPort = port;
        }

        protected String connectionId;
        protected String attachId;
        protected dataVITA49Operations inputPort;
};

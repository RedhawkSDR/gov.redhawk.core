package bulkio.sdds;
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

import BULKIO.dataSDDSOperations;
import BULKIO.dataSDDSPackage.DetachError;
import BULKIO.dataSDDSPackage.StreamInputError;

//
// Stream Attachment represents a single port attachment
//
public class SDDSStreamAttachment {
        public SDDSStreamAttachment() {
            this(null, null, null);
        }

        public SDDSStreamAttachment(String connectionId, dataSDDSOperations inputPort) {
            this(connectionId, null, inputPort);
        }

        public SDDSStreamAttachment(String connectionId, String attachId, dataSDDSOperations inputPort) {
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

        public dataSDDSOperations getInputPort(){
            return this.inputPort;
        }

        public void setConnectionId(String id){
            this.connectionId = id;
        }

        public void setAttachId(String id){
            this.attachId = id;
        }

        public void setInputPort(dataSDDSOperations port){
            this.inputPort = port;
        }

        protected String connectionId;
        protected String attachId;
        protected dataSDDSOperations inputPort;
};

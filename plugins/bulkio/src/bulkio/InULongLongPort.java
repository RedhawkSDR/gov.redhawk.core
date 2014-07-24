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
import org.apache.log4j.Logger;

/**
 * 
 */
public class InULongLongPort extends InUInt64Port {
    
    /**
     * 
     */
    public InULongLongPort( String portName ) {
	super( portName );
    }

    public InULongLongPort( String portName, 
		       bulkio.sri.Comparator compareSRI ) {
	super( portName, null, compareSRI, null );
    }

    public InULongLongPort( String portName, 
		       bulkio.sri.Comparator compareSRI, 
		       bulkio.SriListener sriCallback ) {
	super( portName, null, compareSRI, sriCallback);
    }

    public InULongLongPort( String portName, Logger logger ) {
	super( portName, logger );
    }

    public InULongLongPort( String portName, 
		       Logger logger,
		       bulkio.sri.Comparator compareSRI, 
		       bulkio.SriListener sriCallback ) {
	super( portName, logger, compareSRI, sriCallback);
    }

}


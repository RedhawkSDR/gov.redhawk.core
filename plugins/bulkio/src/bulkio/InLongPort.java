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
/**
 * 
 */
public class InLongPort extends InInt32Port {

    /**

     * 
     */
    public InLongPort( String portName ) {
	super( portName);
    }

    public InLongPort( String portName, 
		       bulkio.sri.Comparator compareSRI ) {
	super(portName, compareSRI );
    }

    public InLongPort( String portName, 
			bulkio.sri.Comparator compareSRI, 
		       bulkio.SriListener sriCallback ) {
	super(portName, compareSRI, sriCallback );
    }


}


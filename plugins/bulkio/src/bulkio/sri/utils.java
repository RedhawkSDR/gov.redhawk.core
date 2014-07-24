package bulkio.sri;
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

import java.lang.System;
import BULKIO.StreamSRI;
import CF.DataType;

public class  utils {

    public static StreamSRI  create() {
	return create("defaultSRI", 1.0, (short)1, false );
    }

    public static StreamSRI  create( String sid, 
				     double srate, 
				     short xunits,
				     boolean blocking ) {

	StreamSRI tsri = new StreamSRI();
	tsri.streamID = sid;
	tsri.hversion =1;
	tsri.xstart = 0.0;
	if ( srate <= 0.0 ) {
	    tsri.xdelta = 1.0;
	}
	else {
	    tsri.xdelta = 1.0/srate;
	}
	tsri.xunits = xunits;
	tsri.subsize = 0;
	tsri.ystart = 0.0;
	tsri.ydelta = 0.0;
	tsri.yunits = 0;
	tsri.mode = 0;
	tsri.blocking=blocking;
	tsri.keywords = new DataType[0];

	return tsri;
    }
}

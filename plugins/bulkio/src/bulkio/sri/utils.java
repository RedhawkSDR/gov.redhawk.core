package bulkio.sri;

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

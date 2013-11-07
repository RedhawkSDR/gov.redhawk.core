
package bulkio.time;

import java.lang.System;
import BULKIO.PrecisionUTCTime;

public class  utils {

    public static PrecisionUTCTime  create( double wholesecs, double fractionalsecs ) {
	return create( wholesecs, fractionalsecs, BULKIO.TCM_CPU.value );
    }

    public static PrecisionUTCTime  create( double wholesecs, double fractionalsecs, short tsrc ) {

	double wsec = wholesecs;
	double fsec = fractionalsecs;
	if ( wsec < 0.0 || fsec < 0.0 ) {
	    long tmp_time = System.currentTimeMillis();
	    wsec = tmp_time /1000;
	    fsec = tmp_time % 1000;
	}
	PrecisionUTCTime tstamp = new PrecisionUTCTime();
	tstamp.tcmode = tsrc;
	tstamp.tcstatus = (short)1;
	tstamp.toff = 0.0;
	tstamp.twsec = wsec;
	tstamp.tfsec = fsec;
	return tstamp;
    }


    public static PrecisionUTCTime now() {
	return create(-1.0,-1.0,BULKIO.TCM_CPU.value);
    }

}

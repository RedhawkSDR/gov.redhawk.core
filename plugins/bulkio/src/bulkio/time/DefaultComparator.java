
package bulkio.time;

import java.lang.System;
import BULKIO.PrecisionUTCTime;

/**
 * @generated
 */
public class DefaultComparator implements bulkio.time.Comparator {


    public boolean compare(final PrecisionUTCTime T1, final PrecisionUTCTime T2) {
    	if (T1.tcmode != T2.tcmode)
    		return false;
    	if (T1.tcstatus != T2.tcstatus)
    		return false;
    	if (T1.tfsec != T2.tfsec)
    		return false;
    	if (T1.toff != T2.toff)
    		return false;
    	if (T1.twsec != T2.twsec)
    		return false;
    	return true;
    }

}

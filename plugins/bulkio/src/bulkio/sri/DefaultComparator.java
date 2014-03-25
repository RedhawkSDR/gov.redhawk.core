
package bulkio.sri;

import org.ossie.properties.AnyUtils;
import BULKIO.StreamSRI;

public class DefaultComparator implements bulkio.sri.Comparator {

    public boolean compare(StreamSRI SRI_1, StreamSRI SRI_2){
	
	if ( SRI_1 == null || SRI_2 == null ) 
	    return false;
        if (SRI_1.hversion != SRI_2.hversion)
            return false;
        if (SRI_1.xstart != SRI_2.xstart)
            return false;
        if (SRI_1.xdelta != SRI_2.xdelta)
            return false;
        if (SRI_1.xunits != SRI_2.xunits)
            return false;
        if (SRI_1.subsize != SRI_2.subsize)
            return false;
        if (SRI_1.ystart != SRI_2.ystart)
            return false;
        if (SRI_1.ydelta != SRI_2.ydelta)
            return false;
        if (SRI_1.yunits != SRI_2.yunits)
            return false;
        if (SRI_1.mode != SRI_2.mode)
            return false;
        if (SRI_1.blocking != SRI_2.blocking)
            return false;
        if (!SRI_1.streamID.equals(SRI_2.streamID))
            return false;
        if (SRI_1.keywords == null || SRI_2.keywords == null )
	    return false;
        if (SRI_1.keywords.length != SRI_2.keywords.length)
            return false;
        String action = "eq";
        for (int i=0; i < SRI_1.keywords.length; i++) {
            if (!SRI_1.keywords[i].id.equals(SRI_2.keywords[i].id)) {
                return false;
            }
            if (!SRI_1.keywords[i].value.type().equivalent(SRI_2.keywords[i].value.type())) {
                return false;
            }
            if (!AnyUtils.compareAnys(SRI_1.keywords[i].value, SRI_2.keywords[i].value, action)) {
                return false;
            }
        }
        return true;
    } 

}



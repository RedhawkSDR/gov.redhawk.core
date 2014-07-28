package bulkio;
import org.apache.log4j.Logger;
/**
 * 
 */
public class InShortPort extends InInt16Port {

    /**
     * 
     */
    public InShortPort( String portName ) {
	super( portName );
    }

    public InShortPort( String portName, 
		       bulkio.sri.Comparator compareSRI ) {
	super( portName, null, compareSRI, null );
    }

    public InShortPort( String portName, 
		       bulkio.sri.Comparator compareSRI, 
		       bulkio.SriListener sriCallback ) {
	super( portName, null, compareSRI, sriCallback);
    }

    public InShortPort( String portName, Logger logger ) {
	super( portName, logger );
    }

    public InShortPort( String portName, 
		       Logger logger,
		       bulkio.sri.Comparator compareSRI, 
		       bulkio.SriListener sriCallback ) {
	super( portName, logger, compareSRI, sriCallback);
    }




}


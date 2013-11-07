package bulkio;
import org.apache.log4j.Logger;

/**
 * 
 */
public class InUShortPort extends InUInt16Port {
    
    /**
     * 
     */
    public InUShortPort( String portName ) {
	super( portName );
    }

    public InUShortPort( String portName, 
		       bulkio.sri.Comparator compareSRI ) {
	super( portName, null, compareSRI, null );
    }

    public InUShortPort( String portName, 
		       bulkio.sri.Comparator compareSRI, 
		       bulkio.SriListener streamCB ) {
	super( portName, null, compareSRI, streamCB);
    }

    public InUShortPort( String portName, Logger logger ) {
	super( portName, logger );
    }

    public InUShortPort( String portName, 
		       Logger logger,
		       bulkio.sri.Comparator compareSRI, 
		       bulkio.SriListener streamCB ) {
	super( portName, logger, compareSRI, streamCB);
    }

}


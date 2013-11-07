package bulkio;
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
		       bulkio.SriListener streamCB ) {
	super( portName, null, compareSRI, streamCB);
    }

    public InULongLongPort( String portName, Logger logger ) {
	super( portName, logger );
    }

    public InULongLongPort( String portName, 
		       Logger logger,
		       bulkio.sri.Comparator compareSRI, 
		       bulkio.SriListener streamCB ) {
	super( portName, logger, compareSRI, streamCB);
    }

}


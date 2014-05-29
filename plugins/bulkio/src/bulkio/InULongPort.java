package bulkio;
import org.apache.log4j.Logger;

/**
 * 
 */
public class InULongPort extends InUInt32Port {

    /**
     * 
     */
    public InULongPort( String portName ) {
	super( portName );
    }

    public InULongPort( String portName, 
		       bulkio.sri.Comparator compareSRI ) {
	super( portName, null, compareSRI, null );
    }

    public InULongPort( String portName, 
		       bulkio.sri.Comparator compareSRI, 
		       bulkio.SriListener sriCallback ) {
	super( portName, null, compareSRI, sriCallback);
    }

    public InULongPort( String portName, Logger logger ) {
	super( portName, logger );
    }

    public InULongPort( String portName, 
		       Logger logger,
		       bulkio.sri.Comparator compareSRI, 
		       bulkio.SriListener sriCallback ) {
	super( portName, logger, compareSRI, sriCallback);
    }

}


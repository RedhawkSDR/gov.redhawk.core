package bulkio;

import org.apache.log4j.Logger;

/**
 * 
 */
public class InLongLongPort extends InInt64Port {

    /**
     * 
     */
    public InLongLongPort( String portName ) {
	super( portName );
    }

    public InLongLongPort( String portName, 
		       bulkio.sri.Comparator compareSRI ) {
	super( portName, null, compareSRI, null );
    }

    public InLongLongPort( String portName, 
			   bulkio.sri.Comparator compareSRI, 
			   bulkio.SriListener sriCallback ) {
 
	super( portName, null, compareSRI, sriCallback);
    }

    public InLongLongPort( String portName, Logger logger ) {
	super( portName, logger );
    }

    public InLongLongPort( String portName, 
		       Logger logger,
			bulkio.sri.Comparator compareSRI, 
			bulkio.SriListener sriCallback ) {
	super( portName, logger, compareSRI, sriCallback);
    }
    

}


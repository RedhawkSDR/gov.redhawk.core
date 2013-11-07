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
			   bulkio.SriListener streamCB ) {
 
	super( portName, null, compareSRI, streamCB);
    }

    public InLongLongPort( String portName, Logger logger ) {
	super( portName, logger );
    }

    public InLongLongPort( String portName, 
		       Logger logger,
			bulkio.sri.Comparator compareSRI, 
			bulkio.SriListener streamCB ) {
	super( portName, logger, compareSRI, streamCB);
    }
    

}


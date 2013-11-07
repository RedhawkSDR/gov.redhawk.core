package bulkio;
import org.apache.log4j.Logger;
/**
 * 
 */
public class InOctetPort extends InUInt8Port {

    public InOctetPort( String portName ) {
	super( portName );
    }

    public InOctetPort( String portName, 
			bulkio.sri.Comparator compareSRI ) {
	super( portName, null, compareSRI, null );
    }

    public InOctetPort( String portName, 
			bulkio.sri.Comparator compareSRI, 
			bulkio.SriListener streamCB ) {
	super( portName, null, compareSRI, streamCB);
    }

    public InOctetPort( String portName, Logger logger ) {
	super( portName, logger );
    }

    public InOctetPort( String portName, 
			Logger logger,
			bulkio.sri.Comparator compareSRI, 
			bulkio.SriListener streamCB ) {
	super( portName, logger, compareSRI, streamCB);
    }

}


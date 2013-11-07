package bulkio;
import org.apache.log4j.Logger;

/**
 * 
 */
public class OutCharPort extends OutInt8Port  {

    /**
     * @generated
     */
    public OutCharPort(String portName)
    {
	super(portName );
    }

    public OutCharPort(String portName, Logger logger)
    {
	super(portName, logger );
    }


    public OutCharPort(String portName, Logger logger, ConnectionEventListener eventCB )
    {
	super(portName, logger, eventCB );
    }

}


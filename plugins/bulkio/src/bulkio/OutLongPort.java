package bulkio;
import org.apache.log4j.Logger;

/**
 * 
 */
public class OutLongPort extends OutInt32Port  {

    /**
     * @generated
     */
    public OutLongPort(String portName)
    {
	super(portName );
    }

    public OutLongPort(String portName, Logger logger)
    {
	super(portName, logger );
    }


    public OutLongPort(String portName, Logger logger, ConnectionEventListener eventCB )
    {
	super(portName, logger, eventCB );
    }

}


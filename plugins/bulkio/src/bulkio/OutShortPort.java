package bulkio;
import org.apache.log4j.Logger;

/**
 * 
 */
public class OutShortPort extends OutInt16Port  {

    /**
     * @generated
     */
    public OutShortPort(String portName)
    {
        super(portName );
    }

    public OutShortPort(String portName, Logger logger)
    {
        super(portName, logger );
    }


    public OutShortPort(String portName, Logger logger, ConnectionEventListener eventCB )
    {
        super(portName, logger, eventCB );
    }

}


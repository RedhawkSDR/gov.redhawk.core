package bulkio;
import org.apache.log4j.Logger;

/**
 * 
 */
public class OutUShortPort extends OutUInt16Port  {

    /**
     * @generated
     */
    public OutUShortPort(String portName)
    {
        super(portName );
    }

    public OutUShortPort(String portName, Logger logger)
    {
        super(portName, logger );
    }


    public OutUShortPort(String portName, Logger logger, ConnectionEventListener eventCB )
    {
        super(portName, logger, eventCB );
    }

}


package bulkio;
import org.apache.log4j.Logger;

/**
 * 
 */
public class OutOctetPort extends OutUInt8Port  {

    /**
     * @generated
     */
    public OutOctetPort(String portName)
    {
        super(portName );
    }

    public OutOctetPort(String portName, Logger logger)
    {
        super(portName, logger );
    }


    public OutOctetPort(String portName, Logger logger, ConnectionEventListener eventCB )
    {
        super(portName, logger, eventCB );
    }

}


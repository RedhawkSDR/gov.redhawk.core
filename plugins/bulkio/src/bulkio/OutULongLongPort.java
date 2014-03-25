package bulkio;
import org.apache.log4j.Logger;

/**
 * 
 */
public class OutULongLongPort extends OutUInt64Port  {

    /**
     * @generated
     */
    public OutULongLongPort(String portName)
    {
        super(portName );
    }

    public OutULongLongPort(String portName, Logger logger)
    {
        super(portName, logger );
    }


    public OutULongLongPort(String portName, Logger logger, ConnectionEventListener eventCB )
    {
        super(portName, logger, eventCB );
    }

}


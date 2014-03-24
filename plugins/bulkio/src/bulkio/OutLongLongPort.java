package bulkio;
import org.apache.log4j.Logger;

/**
 * 
 */
public class OutLongLongPort extends OutInt64Port  {

    /**
     * @generated
     */
    public OutLongLongPort(String portName)
    {
        super(portName );
    }

    public OutLongLongPort(String portName, Logger logger)
    {
        super(portName, logger );
    }


    public OutLongLongPort(String portName, Logger logger, ConnectionEventListener eventCB )
    {
        super(portName, logger, eventCB );
    }

}


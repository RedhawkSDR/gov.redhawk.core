package bulkio;
import org.apache.log4j.Logger;

/**
 * 
 */
public class OutULongPort extends OutUInt32Port  {

    public OutULongPort(String portName)
    {
        super(portName );
    }

    public OutULongPort(String portName, Logger logger)
    {
        super(portName, logger );
    }


    public OutULongPort(String portName, Logger logger, ConnectionEventListener eventCB )
    {
        super(portName, logger, eventCB );
    }

}


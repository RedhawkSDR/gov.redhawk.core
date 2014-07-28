package bulkio;
/**
 * 
 */
public class InLongPort extends InInt32Port {

    /**

     * 
     */
    public InLongPort( String portName ) {
	super( portName);
    }

    public InLongPort( String portName, 
		       bulkio.sri.Comparator compareSRI ) {
	super(portName, compareSRI );
    }

    public InLongPort( String portName, 
			bulkio.sri.Comparator compareSRI, 
		       bulkio.SriListener sriCallback ) {
	super(portName, compareSRI, sriCallback );
    }


}


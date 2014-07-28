
package bulkio;


public interface ConnectionEventListener {

    public void    connect( String streamId );

    public void    disconnect( String streamId );

}


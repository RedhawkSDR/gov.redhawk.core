
package bulkio;

import BULKIO.StreamSRI;

public interface SriListener {

    public void     newSRI( StreamSRI sri );

    public boolean  changedSRI( StreamSRI sri );

}



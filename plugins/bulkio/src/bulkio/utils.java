
package bulkio;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class utils {
    
    //
    // convenience routine to check for null collections
    //
    public static <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
	return iterable == null ? Collections.<T>emptyList() : iterable;
    }
}


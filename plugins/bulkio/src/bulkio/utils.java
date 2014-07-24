
/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
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


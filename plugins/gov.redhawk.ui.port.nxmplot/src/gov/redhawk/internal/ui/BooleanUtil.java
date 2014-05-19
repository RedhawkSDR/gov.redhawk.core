/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.internal.ui;

public class BooleanUtil {
	// Prevent instantiation since all methods here are static
	private BooleanUtil() {
	}

	/**
	 * Converts an Object to a Boolean. Support Objects types are:<pre>
	 *   1. Boolean   - returns obj
	 *   2. String    - returns results of Boolean.valueOf((String) obj)
	 *   3. null      - return null
	 *   4. otherwise - returns Boolean.FALSE
	 * </pre>
	 * @param obj Object to convert to a Boolean object.
	 * @return null if obj is null, otherwise the Object converted to a Boolean.
	 */
	public static Boolean toBoolean(Object obj) {
		if (obj instanceof Boolean) {  // most common case
			return (Boolean) obj;
		} else if (obj instanceof String) {
			return Boolean.valueOf((String) obj);
		} else if (obj == null) {
			return null;
		} else {
			return Boolean.FALSE;
		}
	}
}

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
package gov.redhawk.sca.util;

import java.util.Arrays;

import org.eclipse.jdt.annotation.Nullable;

/**
 * @since 3.4
 */
public class ArrayUtil {
	
	private ArrayUtil() {
		// Prevent instantiation since all methods here are static
	}

	/** copy and insert (prepend) element at beginning if it is not null, otherwise just return values as is. */
	public static Object[] copyAndPrependIfNotNull(@Nullable Object element, Object... values) {
		if (element != null) {
			Object[] newArray = new Object[values.length + 1];
			System.arraycopy(values, 0, newArray, 1, values.length);
			newArray[0] = element; // insert element at beginning (prepend) 
			return newArray;
		}
		return values;
	}
	
	/** copy and insert (append) element at end if it is not null, otherwise just return values as is. */
	public static Object[] copyAndAppendIfNotNull(@Nullable Object element, Object... values) {
		if (element != null) {
			values = Arrays.copyOf(values, values.length + 1);
			values[values.length - 1] = element; // insert element at end (append)
		}
		return values;
	}
}

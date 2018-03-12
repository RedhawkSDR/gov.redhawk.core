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
package gov.redhawk.bulkio.util;

import java.util.Arrays;

import BULKIO.BitSequence;

/**
 * @since 4.0
 */
public class BufferCopy {

	private BufferCopy() {
	}

	/**
	 * Copies a buffer of data of a BULKIO type
	 * @param data The data must be one of short[], float[], int[], double[], long[], byte[], char[],
	 * {@link BitSequence}
	 * @param newLength The new length must be less than or equal to the existing length
	 * @return The new buffer
	 */
	public static Object copyOf(Object data, int newLength) {
		if (data instanceof short[]) {
			return Arrays.copyOf((short[]) data, newLength);
		} else if (data instanceof float[]) {
			return Arrays.copyOf((float[]) data, newLength);
		} else if (data instanceof int[]) {
			return Arrays.copyOf((int[]) data, newLength);
		} else if (data instanceof double[]) {
			return Arrays.copyOf((double[]) data, newLength);
		} else if (data instanceof long[]) {
			return Arrays.copyOf((long[]) data, newLength);
		} else if (data instanceof byte[]) {
			return Arrays.copyOf((byte[]) data, newLength);
		} else if (data instanceof BitSequence) {
			BitSequence oldSequence = (BitSequence) data;
			return new BitSequence(Arrays.copyOf(oldSequence.data, (int) Math.ceil(newLength / 8.0)), newLength);
		} else if (data instanceof char[]) {
			return Arrays.copyOf((char[]) data, newLength);
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Typed version of {@link #copyOf(Object, int)}.
	 * @param data
	 * @param dataClass The type of the data parameter
	 * @param newLength
	 * @return
	 */
	public static < T > T copyOf(T data, Class<T> dataClass, int newLength) {
		if (data instanceof short[]) {
			return dataClass.cast(Arrays.copyOf((short[]) data, newLength));
		} else if (data instanceof float[]) {
			return dataClass.cast(Arrays.copyOf((float[]) data, newLength));
		} else if (data instanceof int[]) {
			return dataClass.cast(Arrays.copyOf((int[]) data, newLength));
		} else if (data instanceof double[]) {
			return dataClass.cast(Arrays.copyOf((double[]) data, newLength));
		} else if (data instanceof long[]) {
			return dataClass.cast(Arrays.copyOf((long[]) data, newLength));
		} else if (data instanceof byte[]) {
			return dataClass.cast(Arrays.copyOf((byte[]) data, newLength));
		} else if (data instanceof BitSequence) {
			BitSequence oldSequence = (BitSequence) data;
			return dataClass.cast(new BitSequence(Arrays.copyOf(oldSequence.data, (int) Math.ceil(newLength / 8.0)), newLength));
		} else if (data instanceof char[]) {
			return dataClass.cast(Arrays.copyOf((char[]) data, newLength));
		} else {
			throw new IllegalArgumentException();
		}
	}
}

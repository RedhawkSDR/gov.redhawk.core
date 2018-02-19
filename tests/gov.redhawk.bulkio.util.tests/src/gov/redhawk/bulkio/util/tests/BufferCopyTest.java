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
package gov.redhawk.bulkio.util.tests;

import org.junit.Assert;
import org.junit.Test;

import BULKIO.BitSequence;
import gov.redhawk.bulkio.util.BufferCopy;

public class BufferCopyTest {

	private static final short[] SHORTS_IN = new short[] { (short) 1, (short) 2, (short) 3 };
	private static final short[] SHORTS_OUT = new short[] { (short) 1, (short) 2 };
	private static final float[] FLOATS_IN = new float[] { 1.0f, 2.0f, 3.0f };
	private static final float[] FLOATS_OUT = new float[] { 1.0f, 2.0f };
	private static final int[] INTS_IN = new int[] { 1, 2, 3 };
	private static final int[] INTS_OUT = new int[] { 1, 2 };
	private static final double[] DOUBLES_IN = new double[] { 1.0, 2.0, 3.0 };
	private static final double[] DOUBLES_OUT = new double[] { 1.0, 2.0 };
	private static final long[] LONGS_IN = new long[] { 1, 2, 3 };
	private static final long[] LONGS_OUT = new long[] { 1, 2 };
	private static final byte[] BYTES_IN = new byte[] { 0x1, 0x2, 0x3 };
	private static final byte[] BYTES_OUT = new byte[] { 0x1, 0x2 };
	private static final BitSequence BITS_IN_1 = new BitSequence(new byte[] { (byte) 0b10100000 }, 3);
	private static final BitSequence BITS_IN_2 = new BitSequence(new byte[] { (byte) 0b10101010, (byte) 0b10000000 }, 9);

	@Test
	public void copyOf_Object_int() {
		Object array = BufferCopy.copyOf(SHORTS_IN, 2);
		Assert.assertArrayEquals(SHORTS_OUT, (short[]) array);
		array = BufferCopy.copyOf(FLOATS_IN, 2);
		Assert.assertArrayEquals(FLOATS_OUT, (float[]) array, 0);
		array = BufferCopy.copyOf(INTS_IN, 2);
		Assert.assertArrayEquals(INTS_OUT, (int[]) array);
		array = BufferCopy.copyOf(DOUBLES_IN, 2);
		Assert.assertArrayEquals(DOUBLES_OUT, (double[]) array, 0);
		array = BufferCopy.copyOf(LONGS_IN, 2);
		Assert.assertArrayEquals(LONGS_OUT, (long[]) array);
		array = BufferCopy.copyOf(BYTES_IN, 2);
		Assert.assertArrayEquals(BYTES_OUT, (byte[]) array);

		array = BufferCopy.copyOf(BITS_IN_1, 2);
		Assert.assertEquals(2, ((BitSequence) array).bits);
		Assert.assertEquals(1, ((BitSequence) array).data.length);
		Assert.assertEquals(BITS_IN_1.data[0] & 0b11000000, ((BitSequence) array).data[0] & 0b11000000);
		array = BufferCopy.copyOf(BITS_IN_2, 8);
		Assert.assertEquals(8, ((BitSequence) array).bits);
		Assert.assertEquals(1, ((BitSequence) array).data.length);
		Assert.assertEquals(BITS_IN_2.data[0], ((BitSequence) array).data[0]);
		array = BufferCopy.copyOf(BITS_IN_2, 6);
		Assert.assertEquals(6, ((BitSequence) array).bits);
		Assert.assertEquals(1, ((BitSequence) array).data.length);
		Assert.assertEquals(BITS_IN_2.data[0] & 0b11111100, ((BitSequence) array).data[0] & 0b11111100);
	}

	@Test(expected = IllegalArgumentException.class)
	public void copyOf_Object_int_throw() {
		BufferCopy.copyOf(1.0, 1);
	}

	@Test
	public void copyOf_Object_clazz_int() {
		Object array = BufferCopy.copyOf(SHORTS_IN, short[].class, 2);
		Assert.assertArrayEquals(SHORTS_OUT, (short[]) array);
		array = BufferCopy.copyOf(FLOATS_IN, float[].class, 2);
		Assert.assertArrayEquals(FLOATS_OUT, (float[]) array, 0);
		array = BufferCopy.copyOf(INTS_IN, int[].class, 2);
		Assert.assertArrayEquals(INTS_OUT, (int[]) array);
		array = BufferCopy.copyOf(DOUBLES_IN, double[].class, 2);
		Assert.assertArrayEquals(DOUBLES_OUT, (double[]) array, 0);
		array = BufferCopy.copyOf(LONGS_IN, long[].class, 2);
		Assert.assertArrayEquals(LONGS_OUT, (long[]) array);
		array = BufferCopy.copyOf(BYTES_IN, byte[].class, 2);
		Assert.assertArrayEquals(BYTES_OUT, (byte[]) array);

		array = BufferCopy.copyOf(BITS_IN_1, BitSequence.class, 2);
		Assert.assertEquals(2, ((BitSequence) array).bits);
		Assert.assertEquals(1, ((BitSequence) array).data.length);
		Assert.assertEquals(BITS_IN_1.data[0] & 0b11000000, ((BitSequence) array).data[0] & 0b11000000);
		array = BufferCopy.copyOf(BITS_IN_2, BitSequence.class, 8);
		Assert.assertEquals(8, ((BitSequence) array).bits);
		Assert.assertEquals(1, ((BitSequence) array).data.length);
		Assert.assertEquals(BITS_IN_2.data[0], ((BitSequence) array).data[0]);
		array = BufferCopy.copyOf(BITS_IN_2, BitSequence.class, 6);
		Assert.assertEquals(6, ((BitSequence) array).bits);
		Assert.assertEquals(1, ((BitSequence) array).data.length);
		Assert.assertEquals(BITS_IN_2.data[0] & 0b11111100, ((BitSequence) array).data[0] & 0b11111100);
	}

	@Test(expected = IllegalArgumentException.class)
	public void copyOf_Object_clazz_int_throw() {
		BufferCopy.copyOf(1.0, double.class, 1);
	}
}

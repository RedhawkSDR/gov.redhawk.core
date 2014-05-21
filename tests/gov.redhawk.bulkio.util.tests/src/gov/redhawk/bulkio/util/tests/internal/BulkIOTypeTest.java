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
package gov.redhawk.bulkio.util.tests.internal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import gov.redhawk.bulkio.util.AbstractBulkIOPort;
import gov.redhawk.bulkio.util.BulkIOType;

import org.junit.Test;
import org.omg.PortableServer.Servant;

import BULKIO.PrecisionUTCTime;
import BULKIO.dataCharOperations;
import BULKIO.dataCharPOATie;
import BULKIO.dataDoubleOperations;
import BULKIO.dataDoublePOATie;
import BULKIO.dataFloatOperations;
import BULKIO.dataFloatPOATie;
import BULKIO.dataLongLongOperations;
import BULKIO.dataLongLongPOATie;
import BULKIO.dataLongOperations;
import BULKIO.dataLongPOATie;
import BULKIO.dataOctetOperations;
import BULKIO.dataOctetPOATie;
import BULKIO.dataShortOperations;
import BULKIO.dataShortPOATie;
import BULKIO.dataUlongLongOperations;
import BULKIO.dataUlongLongPOATie;
import BULKIO.dataUlongOperations;
import BULKIO.dataUlongPOATie;
import BULKIO.dataUshortOperations;
import BULKIO.dataUshortPOATie;

public class BulkIOTypeTest {

	@Test
	public void testCreateServant() {
		BulkIOType[] bulkioTypes = new BulkIOType[] {
			BulkIOType.LONG,
			BulkIOType.ULONG,
			BulkIOType.LONG_LONG,
			BulkIOType.ULONG_LONG,
			BulkIOType.SHORT,
			BulkIOType.USHORT,
			BulkIOType.FLOAT,
			BulkIOType.DOUBLE,
			BulkIOType.OCTET,
			BulkIOType.CHAR,
		};
		Object[] handlers = new Object[] {
			new TestDataLongPort(),
			new TestDataUlongPort(),
			new TestDataLongLongPort(),
			new TestDataUlongLongPort(),
			new TestDataShortPort(),
			new TestDataUshortPort(),
			new TestDataFloatPort(),
			new TestDataDoublePort(),
			new TestDataOctetPort(),
			new TestDataCharPort(),
		};
		Class<?>[] expServantClasses = new Class<?>[] {
			dataLongPOATie.class,
			dataUlongPOATie.class,
			dataLongLongPOATie.class,
			dataUlongLongPOATie.class,
			dataShortPOATie.class,
			dataUshortPOATie.class,
			dataFloatPOATie.class,
			dataDoublePOATie.class,
			dataOctetPOATie.class,
			dataCharPOATie.class,
		};
		assertThat(expServantClasses.length, is(equalTo(bulkioTypes.length)));
		assertThat(expServantClasses.length, is(equalTo(handlers.length)));

		for (int ii = 0; ii < bulkioTypes.length; ii++) {
			BulkIOType bulkioType = bulkioTypes[ii];
			Object handler = handlers[ii];
			Class<?> expClass = expServantClasses[ii];
			Servant servant = bulkioType.createServant(handler);
			assertThat(bulkioType + ".createServant(" + handler.getClass().getSimpleName() + ")",
				servant, is(instanceOf(expClass)));
		}
	}

	private static class TestDataLongPort extends AbstractBulkIOPort implements dataLongOperations {
		@Override
		public void pushPacket(int[] data, PrecisionUTCTime T, boolean EOS, String streamID) {
		}
	}

	private static class TestDataUlongPort extends AbstractBulkIOPort implements dataUlongOperations {
		@Override
		public void pushPacket(int[] data, PrecisionUTCTime T, boolean EOS, String streamID) {
		}
	}

	private static class TestDataLongLongPort extends AbstractBulkIOPort implements dataLongLongOperations {
		@Override
		public void pushPacket(long[] data, PrecisionUTCTime T, boolean EOS, String streamID) {
		}
	}

	private static class TestDataUlongLongPort extends AbstractBulkIOPort implements dataUlongLongOperations {
		@Override
		public void pushPacket(long[] data, PrecisionUTCTime T, boolean EOS, String streamID) {
		}
	}

	private static class TestDataShortPort extends AbstractBulkIOPort implements dataShortOperations {
		@Override
		public void pushPacket(short[] data, PrecisionUTCTime T, boolean EOS, String streamID) {
		}
	}

	private static class TestDataUshortPort extends AbstractBulkIOPort implements dataUshortOperations {
		@Override
		public void pushPacket(short[] data, PrecisionUTCTime T, boolean EOS, String streamID) {
		}
	}

	private static class TestDataFloatPort extends AbstractBulkIOPort implements dataFloatOperations {
		@Override
		public void pushPacket(float[] data, PrecisionUTCTime T, boolean EOS, String streamID) {
		}
	}

	private static class TestDataDoublePort extends AbstractBulkIOPort implements dataDoubleOperations {
		@Override
		public void pushPacket(double[] data, PrecisionUTCTime T, boolean EOS, String streamID) {
		}
	}

	private static class TestDataOctetPort extends AbstractBulkIOPort implements dataOctetOperations {
		@Override
		public void pushPacket(byte[] data, PrecisionUTCTime T, boolean EOS, String streamID) {
		}
	}

	private static class TestDataCharPort extends AbstractBulkIOPort implements dataCharOperations {
		@Override
		public void pushPacket(char[] data, PrecisionUTCTime T, boolean EOS, String streamID) {
		}
	}
}

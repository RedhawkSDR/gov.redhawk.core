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
package nxm.redhawk.prim.data;

import org.eclipse.jdt.annotation.NonNull;

import gov.redhawk.bulkio.util.AbstractBulkIOPort;
import gov.redhawk.bulkio.util.BulkIOType;
import mil.jpeojtrs.sca.util.UnsignedUtils;
import nxm.redhawk.prim.corbareceiver;
import nxm.sys.inc.DataTypes;
import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;
import BULKIO.dataCharOperations;
import BULKIO.dataDoubleOperations;
import BULKIO.dataFloatOperations;
import BULKIO.dataLongLongOperations;
import BULKIO.dataLongOperations;
import BULKIO.dataOctetOperations;
import BULKIO.dataShortOperations;
import BULKIO.dataUlongLongOperations;
import BULKIO.dataUlongOperations;
import BULKIO.dataUshortOperations;

/**
 * 
 */
public class BulkIOReceiver extends AbstractBulkIOPort implements dataCharOperations, dataDoubleOperations, dataFloatOperations, dataLongLongOperations,
		dataLongOperations, dataOctetOperations, dataShortOperations, dataUlongLongOperations, dataUlongOperations, dataUshortOperations {

	private final corbareceiver receiver;
	private final char midasType;
	private final boolean signed;

	public BulkIOReceiver(@NonNull final corbareceiver receiver, @NonNull BulkIOType type) {
		super(type);
		this.receiver = receiver;
		this.signed = !type.isUnsigned();
		switch (type) {
		case CHAR:
			this.midasType = 'I';
			break;
		case DOUBLE:
			this.midasType = 'D';
			break;
		case FLOAT:
			this.midasType = 'F';
			break;
		case LONG:
			this.midasType = 'L';
			break;
		case LONG_LONG:
			this.midasType = 'X';
			break;
		case OCTET:
			this.midasType = 'I';
			break;
		case SHORT:
			this.midasType = 'I';
			break;
		case ULONG:
			this.midasType = 'X';
			break;
		case ULONG_LONG:
			this.midasType = 'X';
			break;
		case USHORT:
			this.midasType = 'L';
			break;
		default:
			throw new IllegalStateException("Unhandled BulkIO Type.");
		}
	}

	public char getMidasType() {
		return this.midasType;
	}

	@Override
	protected void handleStreamSRIChanged(StreamSRI oldSri, StreamSRI newSri) {
		super.handleStreamSRIChanged(oldSri, newSri);
		receiver.setStreamSri(newSri);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void pushPacket(final char[] dataArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamId) {
		if (!super.pushPacket(dataArray.length, time, endOfStream, streamId)) {
			return;
		}
		receiver.write(dataArray, dataArray.length, DataTypes.INT, endOfStream, time);
	}

	@Override
	public void pushPacket(final double[] dataArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamId) {
		if (!super.pushPacket(dataArray.length, time, endOfStream, streamId)) {
			return;
		}
		receiver.write(dataArray, dataArray.length, DataTypes.DOUBLE, endOfStream, time);
	}

	@Override
	public void pushPacket(final float[] dataArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamId) {
		if (!super.pushPacket(dataArray.length, time, endOfStream, streamId)) {
			return;
		}
		receiver.write(dataArray, dataArray.length, DataTypes.FLOAT, endOfStream, time);
	}

	@Override
	public void pushPacket(final long[] dataArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamId) {
		if (!super.pushPacket(dataArray.length, time, endOfStream, streamId)) {
			return;
		}
		if (signed) {
			receiver.write(dataArray, dataArray.length, DataTypes.XLONG, endOfStream, time);
		} else {
			final long[] newDataArray = new long[dataArray.length];
			for (int i = 0; i < dataArray.length; i++) {
				if (dataArray[i] >= 0) {
					newDataArray[i] = dataArray[i];
				} else {
					// NextMidas does not accept integer precision values outside of Long.MAX_VALUE,  therefore clip.
					newDataArray[i] = Long.MAX_VALUE;
				}
			}
			receiver.write(newDataArray, newDataArray.length, DataTypes.XLONG, endOfStream, time);
		}
	}

	@Override
	public void pushPacket(final int[] dataArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamId) {
		if (!super.pushPacket(dataArray.length, time, endOfStream, streamId)) {
			return;
		}
		if (signed) {
			receiver.write(dataArray, dataArray.length, DataTypes.LONG, endOfStream, time);
		} else {
			final long[] newDataArray = UnsignedUtils.toSigned(dataArray);
			receiver.write(newDataArray, newDataArray.length, DataTypes.XLONG, endOfStream, time);
		}
	}

	@Override
	public void pushPacket(final byte[] dataArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamId) {
		if (!super.pushPacket(dataArray.length, time, endOfStream, streamId)) {
			return;
		}
		if (signed) {
			receiver.write(dataArray, dataArray.length, DataTypes.BYTE, endOfStream, time);
		} else {
			final short[] newDataArray = UnsignedUtils.toSigned(dataArray);
			receiver.write(newDataArray, newDataArray.length, DataTypes.INT, endOfStream, time);
		}
	}

	@Override
	public void pushPacket(final short[] dataArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamId) {
		if (!super.pushPacket(dataArray.length, time, endOfStream, streamId)) {
			return;
		}
		if (signed) {
			receiver.write(dataArray, dataArray.length, DataTypes.INT, endOfStream, time);
		} else {
			final int[] newDataArray = UnsignedUtils.toSigned(dataArray);
			receiver.write(newDataArray, newDataArray.length, DataTypes.LONG, endOfStream, time);
		}
	}

}

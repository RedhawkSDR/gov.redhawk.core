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

import BULKIO.BitSequence;
import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;
import gov.redhawk.bulkio.util.AbstractUberBulkIOPort;
import gov.redhawk.bulkio.util.BulkIOType;
import mil.jpeojtrs.sca.util.UnsignedUtils;
import nxm.redhawk.prim.IMidasDataWriter;
import nxm.sys.inc.DataTypes;

/**
 * uber BULKIO Port (CORBA) data receiver
 * @noreference This class is not intended to be referenced by clients.
 */
public class BulkIOReceiver extends AbstractUberBulkIOPort {

	private final IMidasDataWriter receiver;
	private final char midasType;
	private final boolean signed;
	private String filterStreamId = null;

	public BulkIOReceiver(final IMidasDataWriter receiver, BulkIOType type) {
		this(receiver, type, false, null);
	}

	/**
	 * @since 10.1
	 */
	public BulkIOReceiver(final IMidasDataWriter receiver, BulkIOType type, boolean treatOctetAsUnsigned, String streamId) {
		super(type);
		this.receiver = receiver;
		switch (type) {
		case OCTET:
			if (treatOctetAsUnsigned) {
				this.signed = false;
				this.midasType = 'I'; // need to upcast to signed 16-bit integer to represent unsigned 8-bit
			} else {
				this.signed = !type.isUnsigned();
				this.midasType = type.getMidasType();
			}
			break;
		case BIT:
			this.signed = false;
			this.midasType = BulkIOType.OCTET.getMidasType();
			break;
		default:
			this.signed = !type.isUnsigned();
			this.midasType = type.getMidasType();
		}
		this.filterStreamId = streamId;
	}

	public char getMidasType() {
		return this.midasType;
	}

	@Override
	protected void handleStreamSRIChanged(String streamID, StreamSRI oldSri, StreamSRI newSri) {
		receiver.setStreamSri(streamID, oldSri, newSri);
	}

	@Override
	public void pushPacket(BitSequence data, PrecisionUTCTime time, boolean endOfStream, String streamId) {
		if (!super.pushPacket(data.bits, time, endOfStream, streamId)) {
			return;
		} else if (this.filterStreamId != null && !this.filterStreamId.equals(streamId)) {
			return; // ignore streams that we are not interested in
		}

		// Expand each bit into its own byte
		int expandedIndex = 0;
		byte[] expandedData = new byte[data.bits];
		int dataIndex = 0;
		while (data.bits - expandedIndex >= 8) {
			byte dataValue = data.data[dataIndex];
			expandedData[expandedIndex + 7] = (byte) (dataValue & 0x1);
			dataValue >>= 1;
			expandedData[expandedIndex + 6] = (byte) (dataValue & 0x1);
			dataValue >>= 1;
			expandedData[expandedIndex + 5] = (byte) (dataValue & 0x1);
			dataValue >>= 1;
			expandedData[expandedIndex + 4] = (byte) (dataValue & 0x1);
			dataValue >>= 1;
			expandedData[expandedIndex + 3] = (byte) (dataValue & 0x1);
			dataValue >>= 1;
			expandedData[expandedIndex + 2] = (byte) (dataValue & 0x1);
			dataValue >>= 1;
			expandedData[expandedIndex + 1] = (byte) (dataValue & 0x1);
			dataValue >>= 1;
			expandedData[expandedIndex] = (byte) (dataValue & 0x1);
			expandedIndex += 8;
			dataIndex++;
		}
		for (; expandedIndex < data.bits; expandedIndex++) {
			expandedData[expandedIndex] = (byte) (data.data[dataIndex] >> (7 - expandedIndex % 8) & 0x1);
		}

		receiver.write(expandedData, data.bits, DataTypes.BYTE, endOfStream, time, streamId);
	}

	@Override
	public void pushPacket(final char[] dataArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamId) {
		if (!super.pushPacket(dataArray.length, time, endOfStream, streamId)) {
			return;
		} else if (this.filterStreamId != null && !this.filterStreamId.equals(streamId)) {
			return; // ignore streams that we are not interested in
		}
		receiver.write(dataArray, dataArray.length, DataTypes.INT, endOfStream, time, streamId);
	}

	@Override
	public void pushPacket(final double[] dataArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamId) {
		if (!super.pushPacket(dataArray.length, time, endOfStream, streamId)) {
			return;
		} else if (this.filterStreamId != null && !this.filterStreamId.equals(streamId)) {
			return; // ignore streams that we are not interested in
		}
		receiver.write(dataArray, dataArray.length, DataTypes.DOUBLE, endOfStream, time, streamId);
	}

	@Override
	public void pushPacket(final float[] dataArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamId) {
		if (!super.pushPacket(dataArray.length, time, endOfStream, streamId)) {
			return;
		} else if (this.filterStreamId != null && !this.filterStreamId.equals(streamId)) {
			return; // ignore streams that we are not interested in
		}
		receiver.write(dataArray, dataArray.length, DataTypes.FLOAT, endOfStream, time, streamId);
	}

	@Override
	public void pushPacket(final long[] dataArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamId) {
		if (!super.pushPacket(dataArray.length, time, endOfStream, streamId)) {
			return;
		} else if (this.filterStreamId != null && !this.filterStreamId.equals(streamId)) {
			return; // ignore streams that we are not interested in
		}
		if (signed) {
			receiver.write(dataArray, dataArray.length, DataTypes.XLONG, endOfStream, time, streamId);
		} else {
			for (int i = 0; i < dataArray.length; i++) {
				if (dataArray[i] < 0) {
					// NextMidas does not accept integer precision values outside of Long.MAX_VALUE, therefore clip.
					dataArray[i] = Long.MAX_VALUE;
				} // else no change necessary
			}
			receiver.write(dataArray, dataArray.length, DataTypes.XLONG, endOfStream, time, streamId);
		}
	}

	@Override
	public void pushPacket(final int[] dataArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamId) {
		if (!super.pushPacket(dataArray.length, time, endOfStream, streamId)) {
			return;
		} else if (this.filterStreamId != null && !this.filterStreamId.equals(streamId)) {
			return; // ignore streams that we are not interested in
		}
		if (signed) {
			receiver.write(dataArray, dataArray.length, DataTypes.LONG, endOfStream, time, streamId);
		} else {
			final long[] newDataArray = UnsignedUtils.toSigned(dataArray);
			receiver.write(newDataArray, newDataArray.length, DataTypes.XLONG, endOfStream, time, streamId);
		}
	}

	@Override
	public void pushPacket(final short[] dataArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamId) {
		if (!super.pushPacket(dataArray.length, time, endOfStream, streamId)) {
			return;
		} else if (this.filterStreamId != null && !this.filterStreamId.equals(streamId)) {
			return; // ignore streams that we are not interested in
		}
		if (signed) {
			receiver.write(dataArray, dataArray.length, DataTypes.INT, endOfStream, time, streamId);
		} else {
			final int[] newDataArray = UnsignedUtils.toSigned(dataArray);
			receiver.write(newDataArray, newDataArray.length, DataTypes.LONG, endOfStream, time, streamId);
		}
	}

	@Override
	public void pushPacket(final byte[] dataArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamId) {
		if (!super.pushPacket(dataArray.length, time, endOfStream, streamId)) {
			return;
		} else if (this.filterStreamId != null && !this.filterStreamId.equals(streamId)) {
			return; // ignore streams that we are not interested in
		}
		if (signed) {
			receiver.write(dataArray, dataArray.length, DataTypes.BYTE, endOfStream, time, streamId);
		} else {
			final short[] newDataArray = UnsignedUtils.toSigned(dataArray);
			receiver.write(newDataArray, newDataArray.length, DataTypes.INT, endOfStream, time, streamId);
		}
	}
}

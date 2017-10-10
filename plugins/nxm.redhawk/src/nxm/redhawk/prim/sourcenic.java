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
package nxm.redhawk.prim;

import gov.redhawk.sca.util.Debug;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.text.MessageFormat;
import java.util.EnumSet;
import java.util.Enumeration;

import nxm.redhawk.lib.RedhawkOptActivator;
import nxm.redhawk.lib.SDDSHeader;
import nxm.sys.lib.BaseFile;
import nxm.sys.lib.Data;
import nxm.sys.lib.DataFile;
import nxm.sys.lib.Primitive;

/**
 * @since 8.0
 */
public class sourcenic extends Primitive { //SUPPRESS CHECKSTYLE ClassName
	private static final Debug TRACE_LOGGER = new Debug(RedhawkOptActivator.ID, sourcenic.class.getSimpleName());

	private static final int SDDS_HEADER_SIZE = 56;
	private static final int SDDS_PAYLOAD_SIZE = 1024;
	private static final int SDDS_PACKET_SIZE = SDDS_HEADER_SIZE + SDDS_PAYLOAD_SIZE;
	private static final int SDDS_PACKET_ALT_DEFAULT = 0;

	private static final int DEFAULT_MCAST_PORT = 29495;
	private static final int DEFAULT_MCAST_READ_TIMEOUT_MS = 100;

	/** SDDS packets defaults to using Big Endian for the data's byte order */
	private static final ByteOrder DEFAULT_SDDS_DATA_BYTE_ORDER = ByteOrder.BIG_ENDIAN;

	/** the output file to write to */
	private DataFile outputFile = null;
	private Data outputData;
	private SDDSHeader sddsHeader;

	private NetworkInterface ni;
	private MulticastSocket msock;
	private InetSocketAddress maddr;
	private int port;

	/** statistics for socket read timeouts. */
	private long emptyCount;

	private DatagramPacket packet;

	/** warning bit fields to display first warning msg of a particular type per instance session. */
	private static enum WarnBit {
		WARN1,
		WARN2,
	};
	private final EnumSet<WarnBit> warnedSet = EnumSet.noneOf(WarnBit.class);
	private boolean warn;

	/** is SDDS packet payload data in big-endian/IEEE (network) byte order per spec? or in little-endian/EEEI byte-order. */
	private ByteOrder packetDataByteOrder = DEFAULT_SDDS_DATA_BYTE_ORDER;

	/**
	 * Gets the number of socket read timeouts.
	 * @return
	 */
	public long getEmptyCount() {
		return this.emptyCount;
	}

	@Override
	public int open() {
		if (TRACE_LOGGER.enabled) {
			TRACE_LOGGER.enteringMethod();
		}
		warn = MA.getState("/WARN", true);
		verbose = MA.getState("/VERBOSE", false);

		int ret = super.open();
		if (ret != NORMAL) {
			return ret;
		}

		// Parse arguments and switches
		String mgrp = MA.getS("/MGRP", null);
		this.port = MA.getL("/PORT", DEFAULT_MCAST_PORT);
		int vlan = MA.getL("/VLAN", 0);
		String interfaceBaseName = MA.getS("/INTERFACE", null);

		String fc = MA.getS("/FC", "SI");
		int sddsPacketAlt = MA.getL("/ALT", SDDS_PACKET_ALT_DEFAULT);
		sddsHeader = new SDDSHeader(sddsPacketAlt);

		outputFile = MA.getDataFile("OUT", "1000", fc, DataFile.OUTPUT, 0, null);
		outputFile.open(BaseFile.OUTPUT);

		// allocate data buffer with enough room for SDDS packet header + SDDS data + 8 (bytes extra to detect non-standard SDDS packets)
		final int numDataElements = SDDS_PAYLOAD_SIZE / outputFile.bpa;
		outputData = outputFile.getDataBuffer(numDataElements + (SDDS_HEADER_SIZE + 8) / outputFile.bpa); // SUPPRESS CHECKSTYLE MagicNumber
		outputData.boff = SDDS_HEADER_SIZE;  // move byte offset pass SDDS header in outputData's byte buffer
		outputData.setSize(numDataElements); // reset size to number of SDDS data samples/elements
		packet = new DatagramPacket(outputData.getBuf(), outputData.buf.length); // use dataBuffer

		// switch to allow workaround for REDHAWK SinkNic Component sending data in little-endian byte order
		setByteOrder(MA.getS("/BYTEORDER", DEFAULT_SDDS_DATA_BYTE_ORDER.toString()));

		// SDDS 4-bit data is packed in NXM IEEE sub-byte order, i,e. byte0:(sample0, sample1), byte1:(sample2, sample3),...
		if (outputFile.getFormatType() == Data.NIBBLE) {
			outputFile.setDataRep("IEEE");
			outputData.rep = Data.IEEE;
		}

		// Make sure the user provided a reasonable network interface
		if (interfaceBaseName != null) {
			try {
				this.ni = getNetworkInterface(interfaceBaseName, vlan);
				if (this.ni == null) {
					M.error("Could not locate interface " + interfaceBaseName + " for vlan " + vlan);
				}
				if (!this.ni.isUp()) {
					M.error("Specified interface is not up, cowardly refusing to continue");
				}
				if (!this.ni.supportsMulticast()) {
					M.error("Specified interface does not support multicast, cowardly refusing to continue");
				}
			} catch (SocketException e) {
				M.error(e);
			}
		} else {
			// If a vlan was provided attempt to find a valid interface
			if (vlan > 0) {
				try {
					this.ni = findUsableNetworkInterface(vlan);
				} catch (SocketException e) {
					M.error(e);
				}
				if (this.ni == null) {
					M.error("Couldn't find usable network interface for vlan, try using the INTERFACE switch");
				}
			}
			if (verbose) {
				M.info("Using default multicast interface");
			}
			if (TRACE_LOGGER.enabled) {
				TRACE_LOGGER.message("Using default multicast interface");
			}
		}

		// If requested, join the group immediately
		if (mgrp != null) {
			if (verbose) {
				M.info("Joining " + mgrp);
			}
			if (TRACE_LOGGER.enabled) {
				TRACE_LOGGER.message("Joining " + mgrp);
			}
			this.setMgrp(mgrp);
		}

	    return ret;
	}

	@Override
	public int process() {
		if (this.msock == null) {
			return NOOP;
		}

		try {
			try {
				// this receives packet directly into byte[] at outputData.getBuf() - reducing another array copy
				msock.receive(packet);
			}  catch (SocketTimeoutException e) {
				this.emptyCount += 1;
				return NOOP;
			}

			//System.out.println("Packet HA " + packet.getAddress().getHostAddress());
			final int len = packet.getLength();
			if (len != SDDS_PACKET_SIZE) {
				warn(WarnBit.WARN1, "Discarding packet of incorrect length {0}", len);
				return NORMAL;
			}

			sddsHeader.parsePacket(packet.getData(), packet.getOffset(), len);

			final byte packetDataType;
			final int dataBitSize = sddsHeader.getDataFieldBps();
			switch (dataBitSize) {
			case SDDSHeader.BITS_PER_SAMPLE_4:
				packetDataType = Data.NIBBLE;
				break;

			case SDDSHeader.BITS_PER_SAMPLE_8:
				packetDataType = Data.BYTE;
				break;

			case SDDSHeader.BITS_PER_SAMPLE_16:
				packetDataType = Data.INT;
				if (sddsHeader.ss && sddsHeader.isComplex()) { // If necessary, spectral swap/invert complex data
					intSwap(outputData.buf);
				}
				break;

			case SDDSHeader.BITS_PER_SAMPLE_32:
				// to receive REDHAWK SinkNic Component SDDS packets
				packetDataType = Data.FLOAT;
				break;

			default:
				// return ABORT; // prior to 10.1
				warn(WarnBit.WARN2, "Discarding packet of unsupported bit size: {0}", dataBitSize);
				return NOOP;
			}

			if (packetDataType != outputData.getFormatType()) {
				outputData.setFormatType(packetDataType);
			}
			this.outputFile.write(outputData);

			return NORMAL;
		} catch (IOException e) {
			M.warning(e);
			return ABORT;
		}
	}

	@Override
	public int close() {
		if (TRACE_LOGGER.enabled) {
			TRACE_LOGGER.enteringMethod();
		}
		outputFile.close();
		if (this.maddr != null) {
			try {
				msock.leaveGroup(this.maddr, this.ni);
			} catch (IOException e) {
				M.warning(e);
			}
		}
		return super.close();
	}

	// Controls
	private void setMgrp(String mgrp) {
		if ((this.msock != null) && (this.maddr != null)) {
			try {
				this.msock.leaveGroup(this.maddr, this.ni);
			} catch (IOException e) {
				M.error(e);
			}
			this.msock = null;
			this.maddr = null;
		}

		if ((mgrp == null) || mgrp.trim().isEmpty()) {
			return;
		}

		this.maddr = new InetSocketAddress(mgrp, this.port);
		if (!this.maddr.getAddress().isMulticastAddress()) {
			M.error("Provided MGRP is not a multicast address");
		}

		try {
			this.msock = new MulticastSocket(maddr);
			this.msock.setSoTimeout(DEFAULT_MCAST_READ_TIMEOUT_MS);
			this.msock.setReuseAddress(true);
		} catch (IOException e) {
			M.error(e);
		}

		try {
			this.msock.joinGroup(this.maddr, this.ni);
		} catch (UnknownHostException e) {
			M.error(e);
		} catch (IOException e) {
			M.error(e);
		}
	}

	// Utility Functions
	private void byteSwap(byte[] buf) {
		for (int i = 0; i < buf.length; i += 2) {
			byte tmp = buf[i];
			buf[i  ] = buf[i + 1];
			buf[i + 1] = tmp;
		}
	}

	/**
	 * Swaps/invert 16-bit integers (2 bytes) in a 32-bit (4 bytes pair) buffer. <br>
	 * buf[0] = buf[2]<br>
	 * buf[1] = buf[3]<br>
	 * buf[2] = orig buf[0]<br>
	 * buf[3] = orig buf[1]<br>
	 * ...
	 * @param buf The buffer to swap byte order
	 */
	private void intSwap(byte[] buf) {
		for (int i = 0; i < buf.length; i += 4) {
			byte tmp1 = buf[i];
			byte tmp2 = buf[i + 1];
			buf[i    ] = buf[i + 2];
			buf[i + 1] = buf[i + 3];
			buf[i + 2] = tmp1;
			buf[i + 3] = tmp2;
		}
	}

	private NetworkInterface getNetworkInterface(String baseName, int vlan) throws SocketException {
		if (vlan > 0) {
		    return NetworkInterface.getByName(baseName + "." + vlan);
		}
		return NetworkInterface.getByName(baseName);
	}

	/**
	 * Assuming Linux naming convention "device.vlan"
	 *
	 * @param vlan
	 * @return
	 * @throws SocketException
	 */
	private NetworkInterface findUsableNetworkInterface(int vlan) throws SocketException {
		Enumeration<NetworkInterface> iterator = NetworkInterface.getNetworkInterfaces();
		if (iterator == null) {
			return null;
		}

		while (iterator.hasMoreElements()) {
			NetworkInterface localni = iterator.nextElement();
			if (localni.isUp() && localni.supportsMulticast()) {
				String[] niParts = localni.getName().split("\\.");
				try {
					if ((niParts.length == 2) && (Integer.parseInt(niParts[1]) == vlan)) {
						return localni;
					}
				} catch (NumberFormatException e) {
					// PASS
				}
			}
		}

		return null;
	}

	private void warn(final WarnBit warnBit, final String msgFormatPattern, final Object... args) {
		if (warn) {
			if (!warnedSet.contains(warnBit)) { // if we have not already warned on this bit field
				warnedSet.add(warnBit);
				String msg = MessageFormat.format(msgFormatPattern, args);
				M.warning(msg);
			} else if (TRACE_LOGGER.enabled) {
				TRACE_LOGGER.message("WARN: " + msgFormatPattern, args);
			}
		}
	}

	/**
     * @since 10.2
     */
	public ByteOrder getDataByteOrder() {
		return packetDataByteOrder;
	}

	/**
     * @since 10.2
     */
	public void setDataByteOrder(ByteOrder byteOrder) {
		if (byteOrder == null) {
			packetDataByteOrder = DEFAULT_SDDS_DATA_BYTE_ORDER;
		} else {
			packetDataByteOrder = byteOrder;
		}
		if (outputData != null) {
			if (packetDataByteOrder == ByteOrder.LITTLE_ENDIAN) {
				outputData.rep = Data.EEEI;
			} else { // BIG_ENDIAN
				outputData.rep = Data.IEEE;
			}
		}
	}

	/**
	 * Change output file's pipe size (immediately)
	 * @param newValue new pipe size for output data file/pipe (in bytes)
	 * @since 11.1
	 */
	public synchronized void setPipeSize(int newValue) {
		if (newValue <= 0) {
			throw new IllegalArgumentException("pipe size (bytes) must be greater than 0");
		}
		DataFile df = this.outputFile;
		if (df != null && df.getPipeSize() != newValue) {
			TRACE_LOGGER.message("{0}: changing pipe size from {1} to {2} bytes for {3}", getID(), df.getPipeSize(), newValue, df);
			df.setPipeSize(newValue);
		}
	}

	/** get packet data byte order
	 * @since 10.2
	 */
	public String getByteOrder() {
		return packetDataByteOrder.toString();
	}

	/** set packet data byte order
	 * @param byteOrderStr possible values: BIG_ENDIAN, LITTLE_ENDIAN, or NATIVE (to use local machine's byte order)
	 * @since 10.2
	 */
	public void setByteOrder(String byteOrderStr) {
		final ByteOrder newByteOrder;
		if (ByteOrder.BIG_ENDIAN.toString().equals(byteOrderStr)) {
			newByteOrder = ByteOrder.BIG_ENDIAN;
		} else if (ByteOrder.LITTLE_ENDIAN.toString().equals(byteOrderStr)) {
			newByteOrder = ByteOrder.LITTLE_ENDIAN;
		} else if ("NATIVE".equals(byteOrderStr)) {
			newByteOrder = ByteOrder.nativeOrder();
		} else {
			throw new IllegalArgumentException("Invalid data byte order specified: " + byteOrderStr);
		}
		setDataByteOrder(newByteOrder);
	}

}

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

	private static final int BITS_PER_SAMPLE_4 = 4;
	private static final int BITS_PER_SAMPLE_8 = 8;
	private static final int BITS_PER_SAMPLE_16 = 16;
	/** this is used by REDHAWK SinkNic Component to send out float data (32-bit) since SDDS header only allows 5 bit to represent bits per sample (bps). */
	private static final int BITS_PER_SAMPLE_32 = 32;

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
	private static enum WarnBit { WARN1, WARN2, WARN3 };
	private final EnumSet<WarnBit> warnedSet = EnumSet.noneOf(WarnBit.class);
	private boolean warn;

	/** is SDDS packet payload data in big-endian/IEEE (network) byte order per spec? or in little-endian/EEEI byte-order. */
	private ByteOrder packetDataByteOrder = ByteOrder.BIG_ENDIAN;

	/**
	 * Gets the number of socket read timeouts.
	 * @return
	 */
	public long getEmptyCount() {
		return this.emptyCount;
	}

	@Override
	public int open() {
		if (TRACE_LOGGER.enabled) { TRACE_LOGGER.enteringMethod(); }
		warn = MA.getState("/WARN", true);
		verbose = MA.getState("/VERBOSE", false);
		int ret = super.open();

		if (ret == NORMAL) {
			// Parse arguments and switches
			String interfaceBaseName = MA.getS("/INTERFACE", null);
			String mgrp = MA.getS("/MGRP", null);
			int vlan = MA.getL("/VLAN", 0);
			this.port = MA.getL("/PORT", DEFAULT_MCAST_PORT);
			String fc = MA.getS("/FC", "SI");
			int sddsPacketAlt = MA.getL("/ALT", SDDS_PACKET_ALT_DEFAULT);
			sddsHeader = new SDDSHeader(sddsPacketAlt);

			outputFile = MA.getDataFile("OUT", "1000", fc, DataFile.OUTPUT, 0, null);
			outputFile.open(BaseFile.OUTPUT);

			// allocate data buffer with enough room for SDDS packet header + SDDS data + 8 (bytes extra to detect non-standard SDDS packets)
			final int numDataElements = SDDS_PAYLOAD_SIZE / outputFile.bpa;
			outputData = outputFile.getDataBuffer(numDataElements + (SDDS_HEADER_SIZE + 8) / outputFile.bpa);
			outputData.boff = SDDS_HEADER_SIZE;  // move byte offset pass SDDS header in outputData's byte buffer
			outputData.setSize(numDataElements); // reset size to number of SDDS data samples/elements
			packet = new DatagramPacket(outputData.getBuf(), outputData.buf.length); // use dataBuffer

			// switch to allow workaround for REDHAWK SinkNic Component sending data in little-endian byte order
			setDataByteOrder(MA.getS("/BYTEORDER", ByteOrder.BIG_ENDIAN.toString()));

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
				if (verbose) { M.info("Using default multicast interface"); }
				if (TRACE_LOGGER.enabled) { TRACE_LOGGER.message("Using default multicast interface"); }
			}

			// If requested, join the group immediately
			if (mgrp != null) {
				if (verbose) { M.info("Joining " + mgrp); }
				if (TRACE_LOGGER.enabled) { TRACE_LOGGER.message("Joining " + mgrp); }
				this.setMgrp(mgrp);
			}
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
			switch(dataBitSize) {
			case BITS_PER_SAMPLE_4:
				packetDataType = Data.NIBBLE;
				break;

			case BITS_PER_SAMPLE_8:
				packetDataType = Data.BYTE;
				break;

			case BITS_PER_SAMPLE_16:
				packetDataType = Data.INT;
				if (sddsHeader.ss && sddsHeader.isComplex()) { // If necessary, spectral swap/invert complex data
					intSwap(outputData.buf);
				}
				break;

			case BITS_PER_SAMPLE_32:
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
		if (TRACE_LOGGER.enabled) { TRACE_LOGGER.enteringMethod(); }
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
		// CHECKSTYLE:OFF
		for (int i = 0; i < buf.length; i += 4) {
			byte tmp1 = buf[i];
			byte tmp2 = buf[i + 1];
			buf[i] = buf[i + 2];
			buf[i + 1] = buf[i + 3];
			buf[i + 2] = tmp1;
			buf[i + 3] = tmp2;
		}
		// CHECKSTYLE:ON
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

	/**
	 * A crude, partially implemented lightweight SDDS header helper.
	 *
	 * In the absence of a current and readily-available SDDS packet standard, a
	 * number of nearly-(but not quite entirely-)compatible "de facto" standards
	 * for SDDS packets developed. The compile-time SDDS_PACKET_ALT setting allows
	 * users to build copies of this header file that are compatible with these
	 * variations.
	 *
	 * SDDS_MODE_COMPAT     - Attempts to accept "du jure" SDDS packets while
	 *                        also retaining compatibility for SDDS packets
	 *                        that use the TEN_IN_SIXTEEN_AD mode to indicate
	 *                        16-bit complex data.  This mode will not work
	 *                        for "du jure" packets that use the TEN_IN_SIXTEEN_AD mode.
	 *
	 * SDDS_MODE_STRICT     - Follows the current "du jure" SDDS packet standard.
	 *
	 * SDDS_MODE_LEGACY_CX1 - Follows a "de facto" standard from the mid- to
	 *                        late-2000s. In this variant, the TEN_IN_SIXTEEN_AD
	 *                        data mode is used to indicate 16-bit complex data.
	 *                        At the time this standard came into use, the cx bit
	 *                        was not present (i.e. no way to indicate complex
	 *                        data) and the bit currently used for cx was always
	 *                        zero.
     *
	 * SDDS_MODE_LEGACY_CX2 - Follows a "de facto" standard from 2010 that was
	 *                        based on a mis-interpretation of the "de facto"
	 *                        SDDS_PACKET_ALT=1 standard. Strictly speaking, this
	 *                        is a superset of SDDS_PACKET_ALT=1 where FOUR_IN_EIGHT
	 *                        is unsupported (this is a reasonable assumption
	 *                        since nearly all digitizers produced in the last
	 *                        decade use FOUR_IN_FOUR rather than FOUR_IN_EIGHT).
	 *
	 *                        In this variant, the "former" AD-passthrough-bit is
	 *                        used as a complex data indicator akin to the cx bit.
	 *                        The cx bit is ignored in this variant and is always
	 *                        set to zero. This provides an opportunity for this
	 *                        variant to read the "du jure" packets (with the cx
	 *                        bit in use) in addition to the variant packets
	 *                        provided that the "du jure" packets do not use
	 *                        FOUR_IN_EIGHT or TEN_IN_SIXTEEN_AD modes.
	 */
	class SDDSHeader {
		static final int DMODE_TEN_IN_SIXTEEN_AD = 0x06;
		/** REDHAWK SinkNic Component uses dmode=4 and bitsPerSample(bps)=31 (since only 5 bits are used for that field in SDDS header) */
		static final int DMODE_FOUR_FOR_32BITS_PER_SAMPLE = 0x04;

		static final int SDDS_MODE_COMPAT = 0;
		static final int SDDS_MODE_STRICT = 1;
		static final int SDDS_MODE_LEGACY_CX1 = 2;
		static final int SDDS_MODE_LEGACY_CX2 = 3;

		static final int SDDS_MODE_DEFAULT = SDDS_MODE_COMPAT;

		// CHECKSTYLE:OFF

		final int mode;

		// byte 1
		/** is Standard Format (SF) packet */
		public boolean sf;
		/** is Start of Sequence (SoS) */
		public boolean sos;
		/** Parity Packet (PP) */
		public boolean pp;
		/** Original Format (OF) */
		public boolean of;
		/** Spectral Sense (SS) */
		public boolean ss;
		/** Data Mode / data field (DF) */
		public byte dmode;

		// byte 2
		/** is CompleX data */
		public boolean cx;
		public boolean snp;
		public boolean bw;
		/** Bits Per Sample */
		public byte bps;

		// Frame sequences (byte 3 & byte 4)
		public short seq;

		public short msptr;
		public short msdel;

		// Time tags
		public long ttag;
		public int ttage;

		// CHECKSTYLE:ON

        public SDDSHeader() {
			this(SDDS_MODE_DEFAULT);
		}

		public SDDSHeader(int mode) {
			assert (mode == 0);  // TODO implement other modes
			this.mode = mode;
		}

		public void parsePacket(final byte[] data, final int offset, final int len) {
			if (len != SDDS_PACKET_SIZE) {
				throw new IllegalArgumentException("Packet data is not the correct size");
			}

			final byte byte0 = data[offset + 0];
			final byte byte1 = data[offset + 1];
			final byte byte2 = data[offset + 2];
			final byte byte3 = data[offset + 3];
			// CHECKSTYLE:OFF
			sf  = (byte0 & 0x80) != 0;
			if (!sf) {
				warn(WarnBit.WARN3, "Received non-standard packet");
			}
			sos = (byte0 & 0x40) != 0;
			pp  = (byte0 & 0x20) != 0;
			of  = (byte0 & 0x10) != 0;
			ss  = (byte0 & 0x08) != 0;
			dmode = (byte) (byte0 & 0x07); // 3 bits

			cx  = (byte1 & 0x80) != 0;
			snp = (byte1 & 0x40) != 0;
			bw  = (byte1 & 0x20) != 0;
			bps = (byte) (byte1 & 0x1F); // 5 bits

			seq = byte2;
			seq = (short) ((seq << 8) | byte3);
			// CHECKSTYLE:ON
		}

		public boolean isComplex() {
			switch(mode) {
			case SDDS_MODE_COMPAT:
				return ((dmode == DMODE_TEN_IN_SIXTEEN_AD) || (cx));
			case SDDS_MODE_STRICT:
				return cx;
			case SDDS_MODE_LEGACY_CX1:
			case SDDS_MODE_LEGACY_CX2:
				return ((dmode == DMODE_TEN_IN_SIXTEEN_AD) || (cx));
			default:
				throw new IllegalStateException("Invalid SDDS packet mode");
			}
		}

		/**
		 * The data field contains elements of size getDataFieldBps().
		 * @return
		 */
		public int getDataFieldBps() {
			if (dmode == DMODE_FOUR_FOR_32BITS_PER_SAMPLE && bps == 31) { // REDHAWK SinkNic Component uses this for 32-bit samples
				return BITS_PER_SAMPLE_32;
			}
			int x = (dmode & 0x3); // SUPPRESS CHECKSTYLE MAGIC NUMBER
			switch(x) {
			case 0:
				return BITS_PER_SAMPLE_4;
			case 1:
				return BITS_PER_SAMPLE_8;
			case 2:
				return BITS_PER_SAMPLE_16;
			default:
				throw new IllegalStateException("Invalid SDDS packet dmode");
			}
		}
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
     * @since 10.1
     */
	public ByteOrder getDataByteOrder() {
		return packetDataByteOrder;
	}

	/**
     * @since 10.1
     */
	public void setDataByteOrder(ByteOrder byteOrder) {
		packetDataByteOrder = byteOrder;
	}

	/**
     * @since 10.1
     */
	public void setDataByteOrder(String byteOrderStr) {
		if (ByteOrder.BIG_ENDIAN.toString().equals(byteOrderStr)) {
			packetDataByteOrder = ByteOrder.BIG_ENDIAN;
		} else if (ByteOrder.LITTLE_ENDIAN.toString().equals(byteOrderStr)) {
			packetDataByteOrder = ByteOrder.LITTLE_ENDIAN;
		} else {
			throw new IllegalArgumentException("Invalid data byte order specified: " + byteOrderStr);
		}
		if (outputData != null) {
			outputData.rep = (packetDataByteOrder == ByteOrder.BIG_ENDIAN) ? Data.IEEE : Data.EEEI;
		}
	}

}

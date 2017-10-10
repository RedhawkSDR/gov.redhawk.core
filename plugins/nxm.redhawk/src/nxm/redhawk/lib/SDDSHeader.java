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
package nxm.redhawk.lib;

import gov.redhawk.sca.util.Debug;
import nxm.redhawk.prim.sourcenic;

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
public class SDDSHeader {

	private static final int SDDS_HEADER_SIZE = 56;
	private static final int SDDS_PAYLOAD_SIZE = 1024;
	private static final int SDDS_PACKET_SIZE = SDDS_HEADER_SIZE + SDDS_PAYLOAD_SIZE;

	private static final int DMODE_TEN_IN_SIXTEEN_AD = 0x06;
	/**
	 * REDHAWK SinkNic Component uses dmode=4 and bitsPerSample(bps)=31 (since only 5 bits are used for that field in
	 * SDDS header)
	 */
	private static final int DMODE_FOUR_FOR_32BITS_PER_SAMPLE = 0x04;

	private static final int SDDS_MODE_COMPAT = 0;
	private static final int SDDS_MODE_STRICT = 1;
	private static final int SDDS_MODE_LEGACY_CX1 = 2;
	private static final int SDDS_MODE_LEGACY_CX2 = 3;

	private static final int SDDS_MODE_DEFAULT = SDDS_MODE_COMPAT;

	public static final int BITS_PER_SAMPLE_4 = 4;
	public static final int BITS_PER_SAMPLE_8 = 8;
	public static final int BITS_PER_SAMPLE_16 = 16;
	/**
	 * this is used by REDHAWK SinkNic Component to send out float data (32-bit) since SDDS header only allows 5 bit to
	 * represent bits per sample (bps).
	 */
	public static final int BITS_PER_SAMPLE_32 = 32;

	private static final Debug TRACE_LOGGER = new Debug(RedhawkOptActivator.ID, sourcenic.class.getSimpleName());

	// CHECKSTYLE:OFF

	private final int mode;

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
		assert (mode == 0); // TODO implement other modes
		this.mode = mode;
	}

	public void parsePacket(final byte[] data, final int offset, final int len) {
		if (len != SDDS_PACKET_SIZE) {
			throw new IllegalArgumentException("Packet data is not the correct size");
		}

		// CHECKSTYLE:OFF
		final byte byte0 = data[offset + 0];
		final byte byte1 = data[offset + 1];
		final byte byte2 = data[offset + 2];
		final byte byte3 = data[offset + 3];
		sf = (byte0 & 0x80) != 0;
		if (!sf && TRACE_LOGGER.enabled) {
			TRACE_LOGGER.message("WARN: Received non-standard packet");
		}
		sos = (byte0 & 0x40) != 0;
		pp = (byte0 & 0x20) != 0;
		of = (byte0 & 0x10) != 0;
		ss = (byte0 & 0x08) != 0;
		dmode = (byte) (byte0 & 0x07); // 3 bits

		cx = (byte1 & 0x80) != 0;
		snp = (byte1 & 0x40) != 0;
		bw = (byte1 & 0x20) != 0;
		bps = (byte) (byte1 & 0x1F); // 5 bits

		seq = byte2;
		seq = (short) ((seq << 8) | byte3);
		// CHECKSTYLE:ON
	}

	public boolean isComplex() {
		switch (mode) {
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
		if (dmode == DMODE_FOUR_FOR_32BITS_PER_SAMPLE && bps == 31) { // REDHAWK SinkNic Component uses this for 32-bit
																		// samples - SUPPRESS CHECKSTYLE MagicNumber
			return BITS_PER_SAMPLE_32;
		}
		int x = (dmode & 0x3); // SUPPRESS CHECKSTYLE MAGIC NUMBER
		switch (x) {
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

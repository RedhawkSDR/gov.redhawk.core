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
package gov.redhawk.ui.port.nxmblocks;

import java.nio.ByteOrder;

import org.eclipse.jdt.annotation.NonNull;

/**
 * SDDS (UDP/Multicast) source settings.
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class SddsNxmBlockSettings implements Cloneable {
	// SOURCENIC switches
	private String mcastAddress;  // /MGRP=      [DEF=null]
	private int    port;          // /PORT=      [DEF=29495]
	private int    vlan;          // /VLAN=      [DEF=0]
	private String outputFormat;  // /FC=        [DEF=SI]

	private String interfaceName; // /INTERFACE= [DEF=null]
	// private int    mode;       // /ALT=       [DEF=0] only SDDS_MODE_COMPAT=0 is supported at this time

	private int    pipeSize;      // /PS=
	private ByteOrder dataByteOrder = ByteOrder.BIG_ENDIAN; // multi-byte data defaults to big-endian/network byte order per SDDS spec

	public SddsNxmBlockSettings() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@NonNull
	@Override
	public SddsNxmBlockSettings clone() {
		try {
			return (SddsNxmBlockSettings) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError("This should never happen: " + e);
		}
	}

	/**
	 * @return the mcastAddress
	 */
	public String getMcastAddress() {
		return mcastAddress;
	}

	/**
	 * @param mcastAddress the mcastAddress to set
	 */
	public void setMcastAddress(String mcastAddress) {
		this.mcastAddress = mcastAddress;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the vlan
	 */
	public int getVlan() {
		return vlan;
	}

	/**
	 * @param vlan the vlan to set
	 */
	public void setVlan(int vlan) {
		this.vlan = vlan;
	}

	/**
	 * @return the output format (MIDAS digraph, e.g. SI)
	 */
	public String getOutputFormat() {
		return outputFormat;
	}

	/**
	 * @param format the format to set (MIDAS digraph, e.g. SI)
	 */
	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	/**
	 * @return the interfaceName
	 */
	public String getInterfaceName() {
		return interfaceName;
	}

	/**
	 * @param interfaceName the interfaceName to set
	 */
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	/**
	 * @return the pipeSize
	 */
	public int getPipeSize() {
		return pipeSize;
	}

	/**
	 * @param pipeSize the pipeSize to set
	 */
	public void setPipeSize(int pipeSize) {
		this.pipeSize = pipeSize;
	}

	/** data byte-order for multi-byte data formats. */
	@NonNull
	public ByteOrder getDataByteOrder() {
		return dataByteOrder;
	}

	public void setDataByteOrder(@NonNull ByteOrder byteOrder) {
		this.dataByteOrder = byteOrder;
	}

}

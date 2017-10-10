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

import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.preferences.SddsPreferences;

import java.nio.ByteOrder;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * SDDS (Multicast) source settings.
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class SddsNxmBlockSettings extends CommonBulkIONxmBlockSettings implements Cloneable {

	public static final String PROP_DATA_BYTE_ORDER = "dataByteOrder";

	// SOURCENIC switches

	/**
	 * MGRP switch. MUST be multicast addresss (224.0.x.x - 239.255.x.x)
	 */
	private String mcastAddress;

	/**
	 * PORT switch
	 */
	private int port;

	/**
	 * VLAN switch
	 */
	private int vlan;

	/**
	 * FC switch
	 */
	private String outputFormat;

	/**
	 * INTERFACE switch
	 */
	private String interfaceName;

	/**
	 * BYTEORDER switch. Byte order is big-endian by default in the SDDS spec.
	 */
	private ByteOrder dataByteOrder = ByteOrder.BIG_ENDIAN;

	public SddsNxmBlockSettings() {
		this(PlotActivator.getDefault().getPreferenceStore());
	}

	public SddsNxmBlockSettings(IPreferenceStore preferences) {
		super(preferences);

		this.mcastAddress = SddsPreferences.MCAST_ADDRESS.getValue(preferences);
		this.port = SddsPreferences.PORT.getValue(preferences);
		this.vlan = SddsPreferences.VLAN.getValue(preferences);
		this.interfaceName = SddsPreferences.INTERFACE_NAME.getValue(preferences);
		this.outputFormat = SddsPreferences.OUTPUT_FORMAT.getValue(preferences);
		String byteOrderStr = SddsPreferences.BYTE_ORDER.getValue(preferences);
		if (ByteOrder.BIG_ENDIAN.toString().equals(byteOrderStr)) {
			dataByteOrder = ByteOrder.BIG_ENDIAN;
		} else if (ByteOrder.LITTLE_ENDIAN.toString().equals(byteOrderStr)) {
			dataByteOrder = ByteOrder.LITTLE_ENDIAN;
		} else if ("NATIVE".equals(byteOrderStr)) {
			dataByteOrder = ByteOrder.nativeOrder();
		} else {
			dataByteOrder = null;
		}
	}

	@NonNull
	@Override
	public SddsNxmBlockSettings clone() {
		try {
			return (SddsNxmBlockSettings) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError("This should never happen: " + e);
		}
	}

	public String getMcastAddress() {
		return mcastAddress;
	}

	public void setMcastAddress(String mcastAddress) {
		this.mcastAddress = mcastAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getVlan() {
		return vlan;
	}

	public void setVlan(int vlan) {
		this.vlan = vlan;
	}

	/**
	 * @return the NIC name
	 */
	public String getInterfaceName() {
		return interfaceName;
	}

	/**
	 * @param interfaceName the NIC name
	 */
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	/**
	 * @return the output format (MIDAS digraph, e.g. "SI")
	 */
	public String getOutputFormat() {
		return outputFormat;
	}

	/**
	 * @param format the format to set (MIDAS digraph, e.g. "SI")
	 */
	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	/**
	 * @return Data byte order for multi-byte data formats
	 */
	@NonNull
	public ByteOrder getDataByteOrder() {
		return dataByteOrder;
	}

	/**
	 * @param byteOrder Data byte order for multi-byte data formats
	 */
	public void setDataByteOrder(@NonNull ByteOrder byteOrder) {
		this.dataByteOrder = byteOrder;
	}
}

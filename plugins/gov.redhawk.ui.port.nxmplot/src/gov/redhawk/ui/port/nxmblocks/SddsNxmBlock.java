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

import gov.redhawk.internal.ui.preferences.SddsBlockPreferencePage;
import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.preferences.Preference;
import gov.redhawk.ui.port.nxmplot.preferences.SddsPreferences;

import java.nio.ByteOrder;
import java.text.MessageFormat;

import nxm.redhawk.prim.sourcenic;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;

/**
 * SDDS (Multicast) source NXM block.
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class SddsNxmBlock extends AbstractNxmBlock<sourcenic> {

	public SddsNxmBlock(@NonNull AbstractNxmPlotWidget plotWidget) {
		this(plotWidget, null);
	}

	public SddsNxmBlock(@NonNull AbstractNxmPlotWidget plotWidget, @Nullable SddsNxmBlockSettings settings) {
		this(plotWidget, null, null);
	}

	public SddsNxmBlock(@NonNull AbstractNxmPlotWidget plotWidget, @Nullable SddsNxmBlockSettings settings, IPreferenceStore store) {
		super(sourcenic.class, plotWidget, SddsNxmBlock.initPreferenceStore(store));
		if (settings != null) {
			applySettings(settings);
		}
	}

	public static IPreferenceStore initPreferenceStore(IPreferenceStore store) {
		return Preference.initStoreFromWorkbench(SddsPreferences.getAllPreferences(), store);
	}

	@Override
	public int getMaxInputs() {
		return 0; // SDDS source is starting point (so it has no inputs)
	}

	@Override
	@NonNull
	protected String formCmdLine(@NonNull AbstractNxmPlotWidget plotWidget, String streamID) {
		final String outputName = AbstractNxmPlotWidget.createUniqueName(true);
		putOutputNameMapping(0, streamID, outputName); // save output name mapping

		final StringBuilder switches = new StringBuilder(256);
		String mcastAddress = getMcastAddress();
		if (mcastAddress != null && !mcastAddress.isEmpty()) {
			switches.append("/MGRP=").append(mcastAddress);
		}
		switches.append("/PORT=").append(getPort());
		switches.append("/VLAN=").append(getVlan());
		ByteOrder byteOrder = getDataByteOrder();
		if (byteOrder != null) {
			switches.append("/BYTEORDER=").append(byteOrder);
		}
		String outputFormat = getOutputFormat();
		if (outputFormat != null && !outputFormat.isEmpty()) {
			switches.append("/FC=").append(outputFormat);
		}
		final int pipeSize = getPipeSize(); // in bytes
		if (pipeSize > 0) {
			switches.append("/PS=").append(pipeSize);
		}

		String pattern = "SOURCENIC{0}/BG OUT={1}";
		String cmdLine = MessageFormat.format(pattern, switches, outputName);

		return cmdLine;
	}

	public int getVlan() {
		return SddsPreferences.VLAN.getValue(getPreferences());
	}

	public void setVlan(int vlan) {
		SddsPreferences.VLAN.setValue(getPreferences(), vlan);
	}

	public int getPort() {
		return SddsPreferences.PORT.getValue(getPreferences());
	}

	public void setPort(int port) {
		SddsPreferences.PORT.setValue(getPreferences(), port);
	}
	
	public String getMcastAddress() {
		return SddsPreferences.MCAST_ADDRESS.getValue(getPreferences());
	}
	
	public void setMcastAddress(String mcastAddress) {
		if (mcastAddress != null) {
			SddsPreferences.MCAST_ADDRESS.setValue(getPreferences(), mcastAddress);
		} else {
			SddsPreferences.MCAST_ADDRESS.setToDefault(getPreferences());
		}
	}

	public String getInterfaceName() {
		return SddsPreferences.INTERFACE_NAME.getValue(getPreferences());
	}

	public void setInterfaceName(String interfaceName) {
		if (interfaceName != null) {
			SddsPreferences.INTERFACE_NAME.setValue(getPreferences(), interfaceName);
		} else {
			SddsPreferences.INTERFACE_NAME.setToDefault(getPreferences());
		}
	}
	
	public String getOutputFormat() {
		return SddsPreferences.OUTPUT_FORMAT.getValue(getPreferences());
	}

	public void setOutputFormat(String outputFormat) {
		if (outputFormat != null) {
			SddsPreferences.OUTPUT_FORMAT.setValue(getPreferences(), outputFormat);
		} else {
			SddsPreferences.OUTPUT_FORMAT.setToDefault(getPreferences());
		}
	}

	/**
	 * @return current data byte order to use
	 */
	@Nullable
	public ByteOrder getDataByteOrder() {
		String newValue = SddsPreferences.BYTE_ORDER.getValue(getPreferences());
		if (ByteOrder.BIG_ENDIAN.toString().equals(newValue)) {
			return ByteOrder.BIG_ENDIAN;
		} else if (ByteOrder.LITTLE_ENDIAN.toString().equals(newValue)) {
			return ByteOrder.LITTLE_ENDIAN;
		} else if ("NATIVE".equals(newValue)) {
			return ByteOrder.nativeOrder();
		} else {
			return null;
		}
	}

	public void setDataByteOrder(ByteOrder byteOrder) {
		if (byteOrder != null) {
			SddsPreferences.BYTE_ORDER.setValue(getPreferences(), byteOrder.toString());
		} else {
			SddsPreferences.BYTE_ORDER.setToDefault(getPreferences());
		}
	}
	
	public int getPipeSize() {
		return SddsPreferences.PIPE_SIZE.getValue(getPreferences());
	}

	public void setPipeSize(int pipeSize) {
		SddsPreferences.PIPE_SIZE.setValue(getPreferences(), pipeSize);
	}
	
	@NonNull
	public SddsNxmBlockSettings getSettings() {
		return new SddsNxmBlockSettings(getPreferences());
	}

	public void applySettings(@NonNull SddsNxmBlockSettings newSettings) {
		ByteOrder byteOrder = newSettings.getDataByteOrder();
		setDataByteOrder(byteOrder);
		String interfaceName = newSettings.getInterfaceName();
		setInterfaceName(interfaceName);
		String mcastAddress = newSettings.getMcastAddress();
		setMcastAddress(mcastAddress);
		int port = newSettings.getPort();
		setPort(port);
		int vlan = newSettings.getVlan();
		setVlan(vlan);
		String outputFormat = newSettings.getOutputFormat();
		setOutputFormat(outputFormat);
		int pipeSize = newSettings.getPipeSize();
		setPipeSize(pipeSize);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		ByteOrder newByteOrder = null;

		if (SddsPreferences.BYTE_ORDER.isEvent(event)) {
			newByteOrder = getDataByteOrder();
		}

		for (sourcenic c : getNxmCommands()) {
			c.setDataByteOrder(newByteOrder);
		}
	}

	@Override
	public IPreferencePage createPreferencePage() {
		SddsBlockPreferencePage retVal = new SddsBlockPreferencePage();
		retVal.setPreferenceStore(getPreferences());
		return retVal;
	}
}

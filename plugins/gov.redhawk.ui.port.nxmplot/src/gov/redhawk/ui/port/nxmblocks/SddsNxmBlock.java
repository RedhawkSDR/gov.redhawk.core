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

import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.preferences.Preference;
import gov.redhawk.ui.port.nxmplot.preferences.SddsPreferences;

import java.nio.ByteOrder;
import java.text.MessageFormat;

import nxm.redhawk.prim.sourcenic;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;

/**
 * SDDS (UDP/Multicast) source NXM block.
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

		final StringBuilder switches = new StringBuilder("");
		final int pipeSize = getPipeSize(); // in bytes
		if (pipeSize > 0) {
			switches.append("/PS=").append(pipeSize);
		}
		ByteOrder byteOrder = getDataByteOrder();
		String outputFormat = getOutputFormat();
		if (outputFormat == null) {
			outputFormat = "";
		}

		String pattern = "SOURCENIC{0}/BG/BYTEORDER={1}/FC={2}/MGRP={3}/PORT={4,number,#}/VLAN={5,number,#} OUT={6}";
		String cmdLine = MessageFormat.format(pattern, switches, byteOrder, outputFormat, getMcastAddress(), getPort(), getVlan(), outputName);

		return cmdLine;
	}

	public int getVlan() {
		return SddsPreferences.VLAN.getValue(getPreferences());
	}

	public int getPort() {
		return SddsPreferences.PORT.getValue(getPreferences());
	}

	public String getMcastAddress() {
		if (SddsPreferences.MCAST_ADDRESS.isDefault(getPreferences())) {
			return null;
		}
		return SddsPreferences.MCAST_ADDRESS.getValue(getPreferences());
	}

	public String getOutputFormat() {
		return SddsPreferences.OUTPUT_FORMAT.getValue(getPreferences());
	}

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

	public int getPipeSize() {
		return SddsPreferences.PIPE_SIZE.getValue(getPreferences());
	}

	@NonNull
	public SddsNxmBlockSettings getSettings() {
		return new SddsNxmBlockSettings(getPreferences());
	}

	public void applySettings(SddsNxmBlockSettings newSettings) {
		ByteOrder byteOrder = newSettings.getDataByteOrder();
		setDataByteOrder(byteOrder);
	}

	public void setDataByteOrder(ByteOrder byteOrder) {
		if (byteOrder != null) {
			SddsPreferences.BYTE_ORDER.setValue(getPreferences(), byteOrder.toString());
		} else {
			SddsPreferences.BYTE_ORDER.setToDefault(getPreferences());
		}
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

}

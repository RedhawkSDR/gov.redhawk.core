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
package gov.redhawk.ui.port.nxmplot;

import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.ui.port.nxmblocks.BulkIONxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.PlotNxmBlock;
import gov.redhawk.ui.port.nxmblocks.PlotNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.SddsNxmBlockSettings;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @since 4.4
 */
public class PlotSource {
	private final ScaUsesPort input;
	private final String qualifiers;

	private final BulkIONxmBlockSettings bulkioBlockSettings;
	private final SddsNxmBlockSettings sddsBlockSettings;
	private final FftNxmBlockSettings fftBlockSettings;
	private final PlotNxmBlockSettings plotBlockSettings;
	private final IPreferenceStore store = PlotNxmBlock.createInitStore();

	public PlotSource(ScaUsesPort input, FftNxmBlockSettings fftOptions, String qualifiers) {
		this(input, null, null, fftOptions, null, qualifiers, null);
	}

	public PlotSource(ScaUsesPort input, BulkIONxmBlockSettings bulkioSettings, FftNxmBlockSettings fftSettings, PlotNxmBlockSettings plotSettings,
		String qualifiers) {
		this(input, bulkioSettings, null, fftSettings, plotSettings, qualifiers);
	}

	public PlotSource(ScaUsesPort input, SddsNxmBlockSettings sddsSettings, FftNxmBlockSettings fftSettings, PlotNxmBlockSettings plotSettings,
		String qualifiers) {
		this(input, null, sddsSettings, fftSettings, plotSettings, qualifiers);
	}

	/** private as BulkIONxmBlockSettings and SddsNxmBlockSettings should be mutually exclusive */
	private PlotSource(ScaUsesPort input, BulkIONxmBlockSettings bulkioSettings, SddsNxmBlockSettings sddsSettings, FftNxmBlockSettings fftSettings,
		PlotNxmBlockSettings plotSettings, String qualifiers) {
		this(input, bulkioSettings, sddsSettings, fftSettings, plotSettings, qualifiers, null);
	}

	private PlotSource(ScaUsesPort input, BulkIONxmBlockSettings bulkioSettings, SddsNxmBlockSettings sddsSettings, FftNxmBlockSettings fftSettings,
		PlotNxmBlockSettings plotSettings, String qualifiers, FftSettings oldFftOptions) {
		this.input = input;
		this.bulkioBlockSettings = bulkioSettings;
		this.sddsBlockSettings = sddsSettings;
		this.fftBlockSettings = fftSettings;
		this.plotBlockSettings = plotSettings;
		this.qualifiers = qualifiers;
	}

	public IPreferenceStore getStore() {
		return store;
	}

	public ScaUsesPort getInput() {
		return input;
	}

	public String getQualifiers() {
		return qualifiers;
	}

	public BulkIONxmBlockSettings getBulkIOBlockSettings() {
		return bulkioBlockSettings;
	}

	public SddsNxmBlockSettings getSddsBlockSettings() {
		return sddsBlockSettings;
	}

	public FftNxmBlockSettings getFftBlockSettings() {
		return fftBlockSettings;
	}

	public PlotNxmBlockSettings getPlotBlockSettings() {
		return plotBlockSettings;
	}
}

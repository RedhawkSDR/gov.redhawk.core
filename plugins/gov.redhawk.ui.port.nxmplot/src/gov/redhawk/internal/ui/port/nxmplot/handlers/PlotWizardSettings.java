/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.internal.ui.port.nxmplot.handlers;

import gov.redhawk.ui.port.nxmblocks.BulkIONxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.PlotNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.SddsNxmBlockSettings;
import gov.redhawk.ui.port.nxmplot.PlotType;

/**
 * @noreference This class is not intended to be referenced by clients.
 * @since 10.1
 */
public class PlotWizardSettings {
	public static final String PROP_PLOT_TYPE = "type";
	public static final String PROP_DO_FFT = "fft";
	
	private PlotType type = PlotType.LINE;
	private boolean fft = false;

	private BulkIONxmBlockSettings bulkIOBlockSettings; // <-- this contains connectionID settings
	private SddsNxmBlockSettings sddsBlockSettings;
	private FftNxmBlockSettings fftBlockSettings;
	private PlotNxmBlockSettings plotBlockSettings;
	
	public PlotType getType() {
		return type;
	}
	public void setType(PlotType type) {
		this.type = type;
	}
	public boolean isFft() {
		return fft;
	}
	public void setFft(boolean fft) {
		this.fft = fft;
	}
	public BulkIONxmBlockSettings getBulkIOBlockSettings() {
		return bulkIOBlockSettings;
	}
	public void setBulkIOBlockSettings(BulkIONxmBlockSettings bulkIOSettings) {
		this.bulkIOBlockSettings = bulkIOSettings;
	}
	public SddsNxmBlockSettings getSddsBlockSettings() {
		return sddsBlockSettings;
	}
	public void setSddsBlockSettings(SddsNxmBlockSettings sddsSettings) {
		this.sddsBlockSettings = sddsSettings;
	}
	public FftNxmBlockSettings getFftBlockSettings() {
		return fftBlockSettings;
	}
	public void setFftBlockSettings(FftNxmBlockSettings fftSettings) {
		this.fftBlockSettings = fftSettings;
	}
	public PlotNxmBlockSettings getPlotBlockSettings() {
		return plotBlockSettings;
	}
	public void setPlotBlockSettings(PlotNxmBlockSettings plotBlockSettings) {
		this.plotBlockSettings = plotBlockSettings;
	}
	
}

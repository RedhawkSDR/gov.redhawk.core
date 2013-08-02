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
package gov.redhawk.ui.port.nxmplot;

import gov.redhawk.sca.util.PluginUtil;

/**
 * @since 5.0
 */
public class PlotSettings {

	private Integer frameSize = null; // null to use default (e.g. from SRI)
	private Double sampleRate = null; // null to use default (e.g. from SRI)
	private Double minValue = null; // null to use default (i.e. AutoMin)
	private Double maxValue = null; // null to use default (i.e. AutoMax)
	private PlotType plotType = null; // null to not change plot type

	public PlotSettings() {
	}

	public PlotSettings(PlotSettings settings) {
		this.frameSize = settings.frameSize;
		this.sampleRate = settings.sampleRate;
		this.minValue = settings.minValue;
		this.maxValue = settings.maxValue;
		this.plotType = settings.plotType;
	}

	public PlotSettings(final PlotType plotType) {
		super();
		this.plotType = plotType;
	}

	public PlotSettings(final Integer frameSize, final Double minValue, final Double maxValue, final Double sampleRate, final PlotType plotType) {
		super();
		this.frameSize = frameSize;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.sampleRate = sampleRate;
		this.plotType = plotType;
	}

	public Integer getFrameSize() {
		return this.frameSize;
	}

	public void setFrameSize(final Integer frameSize) {
		this.frameSize = frameSize;
	}

	public Double getMinValue() {
		return this.minValue;
	}

	public void setMinValue(final Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public Double getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(Double sampleRate) {
		this.sampleRate = sampleRate;
	}

	public PlotType getPlotType() {
		return plotType;
	}

	public void setPlotType(PlotType plotType) {
		this.plotType = plotType;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PlotSettings) {
			PlotSettings other = (PlotSettings) obj;
			return PluginUtil.equals(frameSize, other.frameSize) && PluginUtil.equals(maxValue, other.maxValue) && PluginUtil.equals(minValue, other.minValue)
				&& PluginUtil.equals(plotType, other.plotType) && PluginUtil.equals(sampleRate, other.sampleRate);
		}
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}

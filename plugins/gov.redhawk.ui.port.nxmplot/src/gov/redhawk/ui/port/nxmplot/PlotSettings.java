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

import java.util.List;

/**
 * @since 5.0
 */
public class PlotSettings {

	private List<IPlotSession> sessions = null; // reference to the sessions for this settings
	private Integer  frameSize  = null; // null to use default (e.g. from SRI)
	private Double   sampleRate = null; // null to use default (e.g. from SRI)
	private Double   minValue   = null; // null to use default (i.e. AutoMin)
	private Double   maxValue   = null; // null to use default (i.e. AutoMax)
	private PlotType plotType   = null; // null to not change plot type

	public PlotSettings() {
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

	/**
	 * @return the plot sessions list
	 */
	public List<IPlotSession> getSessions() {
		return sessions;
	}

	/**
	 * @param plot sessions list to set
	 */
	public void setSessions(List<IPlotSession> plotSessions) {
		this.sessions = plotSessions;
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

}

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


/** <b>INTERNAL USE ONLY</b>
 * @since 4.2
 */
public class PlotSettings {

	private Integer frameSize = null; // null to use default (e.g. from SRI)
	private Double sampleRate = null; // null to use default (e.g. from SRI)
	private Boolean blockingOption = false; // null to use default

	private Double minValue = null; // null to use default (i.e. AutoMin)
	private Double maxValue = null; // null to use default (i.e. AutoMax)
	private PlotType plotType = null; // null to not change plot type

	public PlotSettings() {
	}

	public PlotSettings(PlotSettings settings) {
		this.frameSize = settings.frameSize;
		this.sampleRate = settings.sampleRate;
		this.blockingOption = settings.blockingOption;
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

	/**
	 * @return the blockingOption
	 */
	public Boolean getBlockingOption() {
		return blockingOption;
	}

	/**
	 * @param blockingOption the blockingOption to set
	 */
	public void setBlockingOption(Boolean blockingOption) {
		this.blockingOption = blockingOption;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((frameSize == null) ? 0 : frameSize.hashCode());
		result = prime * result + ((maxValue == null) ? 0 : maxValue.hashCode());
		result = prime * result + ((minValue == null) ? 0 : minValue.hashCode());
		result = prime * result + ((plotType == null) ? 0 : plotType.hashCode());
		result = prime * result + ((sampleRate == null) ? 0 : sampleRate.hashCode());
		result = prime * result + ((blockingOption == null) ? 0 : blockingOption.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PlotSettings)) {
			return false;
		}
		PlotSettings other = (PlotSettings) obj;
		if (frameSize == null) {
			if (other.frameSize != null) {
				return false;
			}
		} else if (!frameSize.equals(other.frameSize)) {
			return false;
		}
		if (maxValue == null) {
			if (other.maxValue != null) {
				return false;
			}
		} else if (!maxValue.equals(other.maxValue)) {
			return false;
		}
		if (minValue == null) {
			if (other.minValue != null) {
				return false;
			}
		} else if (!minValue.equals(other.minValue)) {
			return false;
		}
		if (plotType != other.plotType) {
			return false;
		}
		if (sampleRate == null) {
			if (other.sampleRate != null) {
				return false;
			}
		} else if (!sampleRate.equals(other.sampleRate)) {
			return false;
		}
		if (blockingOption == null) {
			if (other.blockingOption != null) {
				return false;
			}
		} else if (!blockingOption.equals(other.blockingOption)) {
			return false;
		}

		return true;
	}
	
}

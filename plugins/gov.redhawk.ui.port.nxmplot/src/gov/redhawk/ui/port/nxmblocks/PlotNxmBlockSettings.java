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
import gov.redhawk.ui.port.nxmplot.preferences.PlotPreferences;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class PlotNxmBlockSettings implements Cloneable {
	public static final String PROP_FRAME_SIZE = "frameSize";

	/** null to use default. */
	private Integer frameSize;
	/** null to use default. */
	private Integer pipeSize;
	/** null to use default. */
	private Integer linePlotConsumeLength;
	/** null to use default. */
	private Integer refreshRate;

	public PlotNxmBlockSettings() {
		this(null);
	}

	public PlotNxmBlockSettings(IPreferenceStore preferences) {
		if (preferences == null) {
			preferences = PlotActivator.getDefault().getPreferenceStore();
		}
		if (PlotPreferences.FRAMESIZE_OVERRIDE.getValue(preferences)) {
			frameSize = PlotPreferences.FRAMESIZE.getValue(preferences);
		}
		if (PlotPreferences.PIPESIZE_OVERRIDE.getValue(preferences)) {
			pipeSize = PlotPreferences.PIPESIZE.getValue(preferences);
		}
		if (PlotPreferences.LINE_PLOT_CONSUMELENGTH_OVERRIDE.getValue(preferences)) {
			linePlotConsumeLength = PlotPreferences.LINE_PLOT_CONSUMELENGTH.getValue(preferences);
		}
		if (PlotPreferences.REFRESH_RATE_OVERRIDE.getValue(preferences)) {
			refreshRate = PlotPreferences.REFRESH_RATE.getValue(preferences);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@NonNull
	@Override
	public PlotNxmBlockSettings clone() {
		try {
			return (PlotNxmBlockSettings) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError("This should never happen: " + e);
		}
	}

	public void setFrameSize(Integer frameSize) {
		this.frameSize = frameSize;
	}

	public Integer getFrameSize() {
		return frameSize;
	}

	public Integer getPipeSize() {
		return pipeSize;
	}

	public void setPipeSize(Integer pipeSize) {
		this.pipeSize = pipeSize;
	}

	public Integer getLinePlotConsumeLength() {
		return linePlotConsumeLength;
	}

	public void setLinePlotConsumeLength(Integer consumeLength) {
		this.linePlotConsumeLength = consumeLength;
	}

	/**
	 * @since 4.4
	 */
	public Integer getRefreshRate() {
		return refreshRate;
	}

	/**
	 * @since 4.4
	 */
	public void setRefreshRate(Integer refreshRate) {
		this.refreshRate = refreshRate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((frameSize == null) ? 0 : frameSize.hashCode());
		result = prime * result + ((pipeSize == null) ? 0 : pipeSize.hashCode());
		result = prime * result + ((linePlotConsumeLength == null) ? 0 : linePlotConsumeLength.hashCode());
		result = prime * result + ((refreshRate == null) ? 0 : refreshRate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PlotNxmBlockSettings other = (PlotNxmBlockSettings) obj;
		if (frameSize == null) {
			if (other.frameSize != null) {
				return false;
			}
		} else if (!frameSize.equals(other.frameSize)) {
			return false;
		}
		if (pipeSize == null) {
			if (other.pipeSize != null) {
				return false;
			}
		} else if (!pipeSize.equals(other.pipeSize)) {
			return false;
		}
		if (linePlotConsumeLength == null) {
			if (other.linePlotConsumeLength != null) {
				return false;
			}
		} else if (!linePlotConsumeLength.equals(other.linePlotConsumeLength)) {
			return false;
		}
		if (refreshRate == null) {
			if (other.refreshRate != null) {
				return false;
			}
		} else if (!refreshRate.equals(other.refreshRate)) {
			return false;
		}
		return true;
	}

}

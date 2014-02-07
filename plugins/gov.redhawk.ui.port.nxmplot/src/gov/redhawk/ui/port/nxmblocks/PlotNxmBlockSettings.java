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

	public PlotNxmBlockSettings() {
	}

	public PlotNxmBlockSettings(IPreferenceStore preferences) {
		frameSize = PlotPreferences.FRAMESIZE.getValue(preferences);
		pipeSize = PlotPreferences.PIPESIZE.getValue(preferences);
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

	/**
	 * @return the pipeSize
	 */
	public Integer getPipeSize() {
		return pipeSize;
	}

	/**
	 * @param pipeSize the pipeSize to set
	 */
	public void setPipeSize(Integer pipeSize) {
		this.pipeSize = pipeSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((frameSize == null) ? 0 : frameSize.hashCode());
		result = prime * result + ((pipeSize == null) ? 0 : pipeSize.hashCode());
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
		return true;
	}

}

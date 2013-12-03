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

import org.eclipse.jdt.annotation.NonNull;

/**
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class PlotNxmBlockSettings implements Cloneable {
	public static final String PROP_FRAME_SIZE = "frameSize";
	public static final int DEFAULT_FRAME_SIZE = 1024; // SUPPRESS CHECKSTYLE MAGIC NUMBER

	private Integer frameSize; // null or <= 0 to use default
	private Integer pipeSize;  // null or <= 0 to use default

	public PlotNxmBlockSettings() {
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
			throw new AssertionError("This should never happenn: " + e);
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

}

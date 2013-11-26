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
 * FFT settings.
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class FftNxmBlockSettings implements Cloneable {
	// FFT args/switches
	public static enum WindowType {
		BARTLETT { 
			public String toString() { return "Bartlett"; } 
			public String toWindowString() { return "BART"; }
		},
		HANNING {
			public String toString() { return "Hanning"; }
			public String toWindowString() { return "HANN"; }
		},
		HAMMING {
			public String toString() { return "Hamming"; }
			public String toWindowString() { return "HAMM"; }
		},
		BH92 {
			public String toString() { return "Blackman-Harris"; }
			public String toWindowString() { return "BH92"; }
		},
		BLACKMAN {
			public String toString() { return "Blackman"; }
			public String toWindowString() { return "BLAC"; }
		};

		public abstract String toWindowString();
	}

	public static enum OutputType {
		NORMAL {
			public String toString() { return "Normal"; }
			public String toSwitchString() { return ""; }
		},
		MAG_SQ {
			public String toString() { return "Magnitude Squared"; }
			public String toSwitchString() { return "/MAG"; }
		},
		PSD {
			public String toString() { return "Power Spectral Density"; }
			public String toSwitchString() { return "/PSD"; }
		},
		MAG_20LOG {
			public String toString() { return "20 Log Magnitude"; }
			public String toSwitchString() { return "/MAG/LOG"; }
		},
		PSD_20LOG {
			public String toString() { return "20 Log PSD"; }
			public String toSwitchString() { return "/PSD/LOG"; }
		};

		public abstract String toSwitchString();
	}

	private int transformSize  = 8192; // SUPPRESS CHECKSTYLE MAGIC NUMBER
	private int overlap        = 50;   // SUPPRESS CHECKSTYLE MAGIC NUMBER
	private int numAverages    = 1;
	private int numExpAverages = 1;
	private WindowType window = WindowType.HANNING;
	private OutputType outputType = OutputType.PSD;

	private int    pipeSize;      // /PS=

	public FftNxmBlockSettings() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@NonNull
	@Override
	public FftNxmBlockSettings clone() {
		try {
			return (FftNxmBlockSettings) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError("This should never happen: " + e);
		}
	}

	/**
	 * @return the transformSize
	 */
	public int getTransformSize() {
		return transformSize;
	}

	/**
	 * @param transformSize the transformSize to set
	 */
	public void setTransformSize(int transformSize) {
		this.transformSize = transformSize;
	}

	/** 0-100 (percent) of the transform size to overlap blocks of data from the input. */
	public int getOverlap() {
		return this.overlap;
	}

	public void setOverlap(final int overlap) {
		this.overlap = overlap;
	}

	public int getNumAverages() {
		return this.numAverages;
	}

	public void setNumAverages(final int numAverages) {
		this.numAverages = numAverages;
	}

	public int getNumExpAverages() {
		return numExpAverages;
	}

	public void setNumExpAverages(final int numExpAverages) {
		this.numExpAverages = numExpAverages;
	}

	public void setWindow(@NonNull final WindowType window) {
		this.window = window;
	}

	public WindowType getWindow() {
		return this.window;
	}

	public String getWindowString() {
		return this.window.toWindowString();
	}

	public void setOutputType(final OutputType outputType) {
		this.outputType = outputType;
	}

	public OutputType getOutputType() {
		return this.outputType;
	}

	/**
	 * @return the pipeSize
	 */
	public int getPipeSize() {
		return pipeSize;
	}

	/**
	 * @param pipeSize the pipeSize to set
	 */
	public void setPipeSize(int pipeSize) {
		this.pipeSize = pipeSize;
	}

}

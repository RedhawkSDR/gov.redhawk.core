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

/**
 * @since 2.1
 */
public class FftSettings {
	public enum WindowType {
		BARTLETT { 
			@Override public String toString() { return "Bartlett"; } 
			@Override public String toWindowString() { return "BART"; }
		},
		HANNING { 
			@Override public String toString() { return "Hanning"; } 
			@Override public String toWindowString() { return "HANN"; }
		},
		HAMMING {
			@Override public String toString() { return "Hamming"; } 
			@Override public String toWindowString() { return "HAMM"; }
		},
		/** @since 4.0 */
		BH92 { 
			@Override public String toString() { return "Blackman-Harris"; }
			@Override public String toWindowString() { return "BH92"; }
		},
		BLACKMAN { 
			@Override public String toString() { return "Blackman"; }
			@Override public String toWindowString() { return "BLAC"; }
		};
		
		public abstract String toWindowString();
	}

	public enum OutputType {
		NORMAL {
			@Override public String toString() { return "Normal"; }
			@Override public String toFlagString() { return ""; }
		},
		MAG_SQ { 
			@Override public String toString() { return "Magnitude Squared"; }
			@Override public String toFlagString() { return "/MAG"; }
		},
		PSD {
			@Override public String toString() { return "Power Spectral Density"; }
			@Override public String toFlagString() { return "/PSD"; }
		},
		MAG_20LOG {
			@Override public String toString() { return "20 Log Magnitude"; }
			@Override public String toFlagString() { return "/MAG/LOG"; }
		},
		PSD_20LOG {
			@Override public String toString() { return "20 Log PSD"; }
			@Override public String toFlagString() { return "/PSD/LOG"; }
		};
		
		public abstract String toFlagString();
	}

	private String transformSize = "8192"; // SUPPRESS CHECKSTYLE MagicNumber
	private String overlap = "50";
	private String numAverages = "2";
	private WindowType window = WindowType.HANNING;
	private OutputType outputType = OutputType.PSD;

	public FftSettings() {
	}

	public FftSettings(final String transformSize, final String overlap, final String numAverages) {
		super();
		this.transformSize = transformSize;
		this.overlap = overlap;
		this.numAverages = numAverages;
	}

	public String getTransformSize() {
		return this.transformSize;
	}

	public void setTransformSize(final String transformSize) {
		this.transformSize = transformSize;
	}

	/** 0-100 (percent) of the transform size to overlap blocks of data from the input. */
	public String getOverlap() {
		return this.overlap;
	}

	public void setOverlap(final String overlap) {
		this.overlap = overlap;
	}

	public String getNumAverages() {
		return this.numAverages;
	}

	public void setNumAverages(final String numAverages) {
		this.numAverages = numAverages;
	}

	public void setWindow(final WindowType window) {
		this.window = window;
	}

	public String getWindow() {
		return this.window.toWindowString();
	}
	
	/**
	 * @since 4.3
	 */
	public WindowType getWindowType() {
		return this.window;
	}

	public void setOutputType(final OutputType outputType) {
		this.outputType = outputType;
	}

	public OutputType getOutputType() {
		return this.outputType;
	}
}

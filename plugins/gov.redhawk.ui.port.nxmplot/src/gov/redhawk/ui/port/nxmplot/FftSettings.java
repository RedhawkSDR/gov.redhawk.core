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
 * @deprecated Use {@link gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings}
 */
@Deprecated
public class FftSettings {
	/**
	 * @deprecated Use {@link gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings.WindowType}
	 */
	@Deprecated
	public static enum WindowType {
		BARTLETT("Bartlett", "BART"),
		HANNING("Hanning", "HANN"),
		HAMMING("Hamming", "HAMM"),
		/** @since 4.0 */
		BH92("Blackman-Harris", "BH92"),
		BLACKMAN("Blackman", "BLAC");

		private String label;
		private String windowString;

		private WindowType(String label, String windowString) {
			this.label = label;
			this.windowString = windowString;
		}

		public String toWindowString() {
			return this.windowString;
		}

		/**
		 * @since 4.4
		 */
		public String getLabel() {
			return label;
		}
	}

	/**
	 * @deprecated Use {@link gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings.OutputType}
	 */
	@Deprecated
	public static enum OutputType {
		NORMAL("Normal", ""),
		MAG_SQ("Magnitude Squared", "/MAG"),
		PSD("Power Spectral Density", "/PSD"),
		MAG_20LOG("20 Log Magnitude", "/MAG/LOG"),
		PSD_20LOG("20 Log PSD", "/PSD/LOG");

		private String flagString;
		private String label;

		private OutputType(String label, String flagString) {
			this.label = label;
			this.flagString = flagString;
		}

		public String toFlagString() {
			return flagString;
		}

		/**
		 * @since 4.4
		 */
		public String getLabel() {
			return label;
		}
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
	 * @since 4.4
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

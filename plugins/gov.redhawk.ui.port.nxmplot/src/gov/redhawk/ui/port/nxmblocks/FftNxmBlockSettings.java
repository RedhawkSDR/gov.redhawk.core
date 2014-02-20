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
import gov.redhawk.ui.port.nxmplot.preferences.FftPreferences;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * FFT settings.
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class FftNxmBlockSettings implements Cloneable {
	public static final String PROP_NUM_AVERAGES = "numAverages";
	public static final String PROP_SLIDING_NUM_AVERAGES = "numExpAverages";
	public static final String PROP_OVERLAP = "overlap";
	public static final String PROP_OUTPUT_TYPE = "outputType";
	public static final String PROP_TRANSFORM_SIZE = "transformSize";
	public static final String PROP_WINDOW_TYPE = "window";

	// FFT args/switches
	public static enum WindowType {
		BARTLETT("Bartlett", "BART"),
		HANNING("Hanning", "HANN"),
		HAMMING("Hamming", "HAMM"),
		BH92("Blackman-Harris", "BH92"),
		BLACKMAN("Blackman", "BLAC");

		private String windowString;
		private String label;

		private WindowType(String label, String windowString) {
			this.label = label;
			this.windowString = windowString;
		}

		public String toWindowString() {
			return this.windowString;
		}

		public String getLabel() {
			return label;
		}
	}

	public static enum OutputType {
		NORMAL("Normal", ""),
		MAG_SQ("Magnitude Squared", "/MAG"),
		PSD("Power Spectral Density", "/PSD"),
		MAG_20LOG("20 Log Magnitude", "/MAG/LOG"),
		PSD_20LOG("20 Log PSD", "/PSD/LOG");

		private String label;
		private String switches;

		private OutputType(String label, String switches) {
			this.label = label;
			this.switches = switches;
		}

		public String toSwitchString() {
			return this.switches;
		}

		public String getLabel() {
			return this.label;
		}

		public static List<OutputType> getStandardTypes() {
			return Arrays.asList(OutputType.NORMAL, OutputType.MAG_SQ, OutputType.PSD);
		}
	}

	private int transformSize = 8192; // SUPPRESS CHECKSTYLE MAGIC NUMBER
	private int overlap = 50; // SUPPRESS CHECKSTYLE MAGIC NUMBER
	private int numAverages = 1;
	private int numExpAverages = 1;
	private WindowType window = WindowType.HANNING;
	private OutputType outputType;

	private int pipeSize; // /PS=

	public FftNxmBlockSettings() {
		this(null);
	}

	public FftNxmBlockSettings(IPreferenceStore preferences) {
		if (preferences == null) {
			preferences = PlotActivator.getDefault().getPreferenceStore();
		}
		this.numAverages = FftPreferences.NUM_AVERAGES.getValue(preferences);
		this.numExpAverages = FftPreferences.SLIDING_NUM_AVERAGES.getValue(preferences);
		this.outputType = OutputType.valueOf(FftPreferences.OUTPUT_TYPE.getValue(preferences));
		this.overlap = FftPreferences.OVERLAP.getValue(preferences);
		this.pipeSize = FftPreferences.PIPE_SIZE.getValue(preferences);
		this.transformSize = FftPreferences.TRANSFORM_SIZE.getValue(preferences);
		this.window = WindowType.valueOf(FftPreferences.WINDOW_TYPE.getValue(preferences));
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

	public void setOutputType(@NonNull final OutputType outputType) {
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

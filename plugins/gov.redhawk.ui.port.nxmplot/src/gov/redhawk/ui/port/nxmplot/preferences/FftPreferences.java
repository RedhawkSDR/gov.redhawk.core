/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.ui.port.nxmplot.preferences;

import gov.redhawk.ui.port.nxmplot.FftSettings;

import java.util.List;

/**
 * @since 4.4
 */
public class FftPreferences {

	public static final Preference<Integer> NUM_AVERAGES = new Preference<Integer>("fftBlock.numAverages", 1);
	public static final Preference<Integer> SLIDING_NUM_AVERAGES = new Preference<Integer>("fftBlock.numExpAverages", 1);
	public static final Preference<Integer> OVERLAP = new Preference<Integer>("fftBlock.overlap", 50);

	public static final Preference<String> OUTPUT_TYPE = new Preference<String>("fftBlock.outputType", FftSettings.OutputType.PSD_20LOG.toString());
	public static final Preference<Integer> TRANSFORM_SIZE = new Preference<Integer>("fftBlock.transformSize", 8192);
	public static final Preference<String> WINDOW_TYPE = new Preference<String>("fftBlock.windowType", FftSettings.WindowType.HAMMING.toString());

	public static final Preference<Boolean> PIPE_SIZE_OVERRIDE = new Preference<Boolean>("fftBlock.pipeSize.override", false);
	public static final Preference<Integer> PIPE_SIZE = new Preference<Integer>("fftBlock.pipeSize", 131072);

	public static List<Preference< ? >> getAllPreferences() {
		return Preference.gettAllPreferencesFor(FftPreferences.class);
	}

	private FftPreferences() {

	}

}

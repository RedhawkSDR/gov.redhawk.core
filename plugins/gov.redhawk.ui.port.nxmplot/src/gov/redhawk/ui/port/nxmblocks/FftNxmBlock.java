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
package gov.redhawk.ui.port.nxmblocks;

import gov.redhawk.internal.ui.preferences.FftBlockPreferencePage;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings.OutputType;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings.WindowType;
import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.preferences.FftPreferences;
import gov.redhawk.ui.port.nxmplot.preferences.Preference;

import java.text.MessageFormat;

import nxm.sys.prim.fft;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;

import BULKIO.StreamSRI;

/**
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class FftNxmBlock extends AbstractNxmBlock<fft> {

	public FftNxmBlock(@NonNull AbstractNxmPlotWidget plotWidget, @NonNull FftNxmBlockSettings settings) {
		super(fft.class, plotWidget, FftNxmBlock.initStore());
		if (settings != null) {
			applySettings(settings);
		}
	}

	private static IPreferenceStore initStore() {
		return Preference.initStoreFromWorkbench(FftPreferences.getAllPreferences());
	}

	@Override
	public int getMaxInputs() {
		return 2;
	}

	@Override
	public int getMaxOutputs() {
		return 3; // SUPPRESS CHECKSTYLE MAGIC NUMBER
	}

	@Override
	@NonNull
	protected String formCmdLine(@NonNull AbstractNxmPlotWidget plotWidget, String streamID) {
		final int numInputs = getNumberInputs();
		if (numInputs < 1) {
			throw new IllegalArgumentException("Input(s) has not been specified");
		}

		BlockIndexPair inBlockIndexPair1 = getInputBlockInfo(0);
		if (inBlockIndexPair1 == null) {
			throw new IllegalArgumentException("Input 1 has not been specified");
		}

		String inputName1 = inBlockIndexPair1.getBlock().getOutputName(inBlockIndexPair1.getIndex(), streamID);
		String outputName1 = AbstractNxmPlotWidget.createUniqueName(true);
		putOutputNameMapping(0, streamID, outputName1); // save output 1 name mapping

		String inputName2;
		String outputName2;
		String outputNameCross;
		BlockIndexPair inBlockInfo2 = getInputBlockInfo(1);
		if (inBlockInfo2 != null) {
			inputName2 = inBlockInfo2.getBlock().getOutputName(inBlockInfo2.getIndex(), streamID);
			outputName2 = AbstractNxmPlotWidget.createUniqueName(true);
			putOutputNameMapping(1, streamID, outputName2); // save output 2 name mapping

			if (hasOutputBlockAt(2)) {
				outputNameCross = AbstractNxmPlotWidget.createUniqueName(true);
				putOutputNameMapping(2, streamID, outputNameCross); // save cross output 3 name mapping
			} else {
				outputNameCross = ""; // no cross output 3 specified
			}
		} else {
			inputName2 = ""; // no input 2
			outputName2 = ""; // hence no output 2
			outputNameCross = ""; //   and no cross output 3
		}

		String fftSwitches;
		final OutputType fftOutputType = getOutputType();
		if (fftOutputType != null) {
			fftSwitches = fftOutputType.toSwitchString();
		} else {
			fftSwitches = "";
		}
		double overlap = getOverlap() / 100.0; // fraction of <transform size> to overlap blocks // SUPPRESS CHECKSTYLE MAGIC NUMBER
		String pattern = "FFT{0}/NEXP={1,number,#} IN1={2} OUT1={3} NFFT={4,number,#} WIN={5} OVER={6,number,#.###} NAVG={7,number,#} IN2={8} OUT2={9} CROSS={10}";
		String cmdLine = MessageFormat.format(pattern, fftSwitches, getNumExpAverages(), inputName1, outputName1, getTransformSize(),
			getWindowType().toWindowString(), overlap, getNumAverages(), inputName2, outputName2, outputNameCross);

		return cmdLine;
	}

	public int getNumAverages() {
		return FftPreferences.NUM_AVERAGES.getValue(getPreferences());
	}

	public WindowType getWindowType() {
		try {
			return WindowType.valueOf(FftPreferences.WINDOW_TYPE.getValue(getPreferences()));
		} catch (IllegalArgumentException e) {
			return WindowType.HAMMING;
		}
	}

	public int getTransformSize() {
		return FftPreferences.TRANSFORM_SIZE.getValue(getPreferences());
	}

	public int getNumExpAverages() {
		return FftPreferences.SLIDING_NUM_AVERAGES.getValue(getPreferences());
	}

	public int getOverlap() {
		return FftPreferences.OVERLAP.getValue(getPreferences());
	}

	public OutputType getOutputType() {
		String retVal = FftPreferences.OUTPUT_TYPE.getValue(getPreferences());
		try {
			return OutputType.valueOf(retVal);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@NonNull
	public FftNxmBlockSettings getSettings() {
		return new FftNxmBlockSettings(getPreferences());
	}

	public void applySettings(FftNxmBlockSettings newSettings) {

		// update block's settings (model - so that getSettings() returns latest value)
		setNumAverages(newSettings.getNumAverages());
		setNumExpAverages(newSettings.getNumExpAverages());
		setOverlap(newSettings.getOverlap());

		setWindow(newSettings.getWindow());
		setTransformSize(newSettings.getTransformSize());

		setOutputType(newSettings.getOutputType());
		setPipeSize(newSettings.getPipeSize());
	}

	protected void setOutputType(OutputType outputType) {
		FftPreferences.OUTPUT_TYPE.setValue(getPreferences(), outputType.toString());
	}

	public void setTransformSize(int transformSize) {
		FftPreferences.TRANSFORM_SIZE.setValue(getPreferences(), transformSize);
	}

	public void setWindow(WindowType window) {
		FftPreferences.WINDOW_TYPE.setValue(getPreferences(), window.toString());
	}

	public void setOverlap(int overlap) {
		FftPreferences.OVERLAP.setValue(getPreferences(), overlap);
	}

	public void setNumExpAverages(int numExpAverages) {
		FftPreferences.SLIDING_NUM_AVERAGES.setValue(getPreferences(), numExpAverages);
	}

	public void setNumAverages(int numAverages) {
		FftPreferences.NUM_AVERAGES.setValue(getPreferences(), numAverages);
	}

	public void setPipeSize(int pipeSize) {
		FftPreferences.PIPE_SIZE.setValue(getPreferences(), pipeSize);
		FftPreferences.PIPE_SIZE_OVERRIDE.setValue(getPreferences(), true);
	}

	public int getPipeSize() {
		return FftPreferences.PIPE_SIZE.getValue(getPreferences());
	}

	public boolean isSetPipeSize() {
		return FftPreferences.PIPE_SIZE_OVERRIDE.getValue(getPreferences());
	}

	public void unsetPipeSize() {
		FftPreferences.PIPE_SIZE.setToDefault(getPreferences());
		FftPreferences.PIPE_SIZE_OVERRIDE.setValue(getPreferences(), false);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		Integer numAvg = null;
		if (FftPreferences.NUM_AVERAGES.isEvent(event)) {
			numAvg = getNumAverages();
		}

		Integer nexp = null;
		if (FftPreferences.SLIDING_NUM_AVERAGES.isEvent(event)) {
			nexp = getNumExpAverages();
		}

		Integer overlap = null;
		if (FftPreferences.OVERLAP.isEvent(event)) {
			overlap = getOverlap();
		}

		String window = null;
		if (FftPreferences.WINDOW_TYPE.isEvent(event)) {
			WindowType windowType = getWindowType();
			window = windowType.toWindowString();
		}

		OutputType outputType = null;
		if (FftPreferences.OUTPUT_TYPE.isEvent(event)) {
			outputType = getOutputType();
		}

		Integer nfft = null;
		if (FftPreferences.TRANSFORM_SIZE.isEvent(event)) {
			nfft = getTransformSize();
		}

		Integer pipeSize = null;
		if (FftPreferences.PIPE_SIZE.isEvent(event) || FftPreferences.PIPE_SIZE_OVERRIDE.isEvent(event)) {
			if (isSetPipeSize()) {
				pipeSize = getPipeSize();
			} else {
				pipeSize = FftPreferences.PIPE_SIZE.getDefaultValue();
			}
			if (pipeSize <= 0) { // PANIC!!
				pipeSize = 131072;
			}
		}

		// update actual FFT Command's settings for each stream
		for (fft cmd : getNxmCommands()) {
			if (numAvg != null) {
				cmd.setNAvg(numAvg);
			}

			if (nexp != null) {
				cmd.setNExp(nexp);
			}

			if (overlap != null) {
				cmd.setOverlap(overlap / 100.0); // SUPPRESS CHECKSTYLE MagicNumber
			}

			if (window != null) {
				cmd.setWindow(window);
			}

// TODO: change PIPE_SIZE for input1? output1? output2?, etc.
//			if (pipeSize != null) {
//			}

			if (outputType != null) {
// PASS TODO: cannot change: output type (NORMAL, PSD, MAG, MAG & LOG, PSD & LOG) on FFT at this time
// 1. this could cause a restart (when switching from NORMAL to any of the other output types
// 2. could always force the FFT command to restart with new launch switches
			}

			if (nfft != null) {
				cmd.setNFft(nfft); // do this last as it can cause FFT to restart
			}
		}
	}

	@Override
	public IPreferencePage createPreferencePage() {
		FftBlockPreferencePage retVal = new FftBlockPreferencePage();
		retVal.setPreferenceStore(getPreferences());
		return retVal;
	}
	
	public int getOutFramesize(int sriMode) {
		int size = getTransformSize();
		if (sriMode == 0) {  // for scalar data
			size = size / 2; // framesize is half fft size
		}
		return size;
	}
}

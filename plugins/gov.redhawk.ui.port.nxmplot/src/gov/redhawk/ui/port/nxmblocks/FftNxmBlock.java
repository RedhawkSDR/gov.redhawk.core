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

import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings.OutputType;
import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;

import java.text.MessageFormat;

import nxm.sys.prim.fft;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class FftNxmBlock extends AbstractNxmBlock<fft, FftNxmBlockSettings> {
	private FftNxmBlockSettings settings;

	public FftNxmBlock(@NonNull AbstractNxmPlotWidget plotWidget, @NonNull FftNxmBlockSettings settings) {
		super(fft.class, "FFT", plotWidget);
		this.settings = settings;
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
	public boolean hasControls() {
		return true;
	}

	@Override
	public Composite createControls(@NonNull Composite parent, @Nullable Object settings, DataBindingContext dataBindingContext) {
		FftNxmBlockSettings blockSettings = null;
		if (settings instanceof FftNxmBlockSettings) {
			blockSettings = (FftNxmBlockSettings) settings;
		}
		return new FftNxmBlockControls(parent, SWT.NONE, blockSettings, dataBindingContext);
	}

	@Override
	@NonNull protected String formCmdLine(@NonNull AbstractNxmPlotWidget plotWidget, String streamID) {
		if (settings == null) {
			throw new IllegalArgumentException("FFT settings have not been specified");
		}

		final int numInputs = getNumberInputs();
		if (numInputs < 1) {
			throw new IllegalArgumentException("Input(s) has not been specified");
		}

		BlockIndexPair inBlockIndexPair1 = getInputBlockInfo(0);
		if (inBlockIndexPair1 == null) {
			throw new IllegalArgumentException("Input 1 has not been specified");
		}

		String inputName1  = inBlockIndexPair1.getBlock().getOutputName(inBlockIndexPair1.getIndex(), streamID);
		String outputName1 = AbstractNxmPlotWidget.createUniqueName(true);
		putOutputNameMapping(0, streamID, outputName1);     // save output 1 name mapping 

		String inputName2;
		String outputName2;
		String outputNameCross;
		BlockIndexPair inBlockInfo2 = getInputBlockInfo(1);
		if (inBlockInfo2 != null) {
			inputName2  = inBlockInfo2.getBlock().getOutputName(inBlockInfo2.getIndex(), streamID);
			outputName2 = AbstractNxmPlotWidget.createUniqueName(true);
			putOutputNameMapping(1, streamID, outputName2); // save output 2 name mapping

			if (hasOutputBlockAt(2)) {
				outputNameCross = AbstractNxmPlotWidget.createUniqueName(true);
				putOutputNameMapping(2, streamID, outputNameCross); // save cross output 3 name mapping
			} else {
				outputNameCross = ""; // no cross output 3 specified
			}
		} else {
			inputName2 = "";      // no input 2
			outputName2 = "";     // hence no output 2
			outputNameCross = ""; //   and no cross output 3
		}

		String fftSwitches;
		final OutputType fftOutputType = settings.getOutputType();
		if (fftOutputType != null) {
			fftSwitches = fftOutputType.toSwitchString();
		} else {
			fftSwitches = "";
		}
		double overlap = settings.getOverlap() / 100.0; // fraction of <transform size> to overlap blocks // SUPPRESS CHECKSTYLE MAGIC NUMBER
		String pattern = "FFT{0}/NEXP={1,number,#} IN1={2} OUT1={3} NFFT={4,number,#} WIN={5} OVER={6,number,#} NAVG={7,number,#} IN2={8} OUT2={9} CROSS={10}";
		String cmdLine = MessageFormat.format(pattern, fftSwitches, settings.getNumExpAverages(),
			inputName1, outputName1, settings.getTransformSize(), settings.getWindowString(), overlap,
			settings.getNumAverages(), inputName2, outputName2, outputNameCross);

		return cmdLine;
	}

	@Override
	@NonNull
	public FftNxmBlockSettings getSettings() {
		FftNxmBlockSettings clone;
		if (settings != null) {
			clone = settings.clone();
		} else {
			clone = new FftNxmBlockSettings();
		}
		return clone;
	}

	@Override
	public void applySettings(FftNxmBlockSettings settings) {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

}

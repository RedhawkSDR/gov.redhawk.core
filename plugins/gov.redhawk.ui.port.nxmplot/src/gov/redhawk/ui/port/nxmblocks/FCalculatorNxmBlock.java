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

import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;

import java.text.MessageFormat;

import nxm.sys.prim.fcalculator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class FCalculatorNxmBlock extends AbstractNxmBlock<fcalculator> {

	private String calcExpression;

	/**
	 * @param fcalcExpression FCALCULATOR expression to append to all inputs
	 * @param inputSelectors (can be null to use default output on all input, otherwise size must match inputSources length)
	 * @param inputSources input sources (most not have any null inputs)
	 */
	public FCalculatorNxmBlock(@NonNull AbstractNxmPlotWidget plotWidget, @NonNull String fcalcExpression) {
		super(fcalculator.class, plotWidget, FCalculatorNxmBlock.initStore(null));
		this.calcExpression = fcalcExpression;
	}

	private static IPreferenceStore initStore(IPreferenceStore object) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the calcExpression
	 */
	public String getCalcExpression() {
		return calcExpression;
	}

	/**
	 * @param calcExpression the calcExpression to set
	 */
	public void setCalcExpression(@NonNull String calcExpression) {
		this.calcExpression = calcExpression;
	}

	@Override
	public int getMaxInputs() {
		return 1; // TODO: support more than one input?
	}

	@Override
	protected String formCmdLine(AbstractNxmPlotWidget plotWidget, String streamID) {
		if (calcExpression == null) {
			throw new IllegalStateException("FCALCULATOR expression has not been specified");
		}
		//		List<INxmBlock> inputs = getInputs();
		//		if (inputs.size() < 1) {
		//			throw new IllegalStateException("Input have not been specified");
		//		}
		StringBuilder inputSB = new StringBuilder();
		//		int ii = 0;
		//		for (INxmBlock inSrc : inputs) {
		//			String inputSelector = (inputSelectors == null) ? null : inputSelectors[ii];
		//			inputSB.append(inSrc.getOutput(inputSelector)).append(' ');
		//			ii++;
		//		}
		String outputName = AbstractNxmPlotWidget.createUniqueName(true);

		String pattern = "FCALCULATOR/BG {0} {1} {2}";
		String cmdLine = MessageFormat.format(pattern, outputName, inputSB, calcExpression);

		return cmdLine;
	}

}

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
import java.util.HashMap;
import java.util.Map;

import nxm.redhawk.prim.dispthin;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.swt.widgets.Composite;

/**
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class DispThinNxmBlock extends AbstractNxmBlock<dispthin, Map<String, Object>> {

	private Map<String, Object> settings;
	private int pipeSize;
	private int transferLength = 1;
	private int refreshRate;

	public DispThinNxmBlock(@NonNull AbstractNxmPlotWidget plotWidget) {
		super(dispthin.class, "Display Thin");
		setContext(plotWidget);
	}

	@Override
	public int getMaxInputs() {
		return 1;
	}

	@Override
	public boolean hasControls() {
		return false; // none for now
	}

	@Override
	public Composite createControls(Composite parent, Map<String, Object> settings, DataBindingContext context) {
		return null; // none for now
	}

	@Override
	@NonNull
	public Map<String, Object> getSettings() {
		Map<String, Object> clone = settings;
		if (clone == null) {
			clone = new HashMap<String, Object>();
		}
		return clone;
	}

	@Override
	public void applySettings(Map<String, Object> settings) {
		throw new UnsupportedOperationException("settings adjustment not supported for " + getClass());
		// TODO: can only set refreshRate at this time on dispthin
	}

	@Override
	protected String formCmdLine(AbstractNxmPlotWidget plotWidget, String streamID) {

		BlockIndexPair inBlockIndexPair = getInputBlockInfo(0);
		if (inBlockIndexPair == null) {
			throw new IllegalStateException("Input has not been specified");
		}
		String inputName  = inBlockIndexPair.getBlock().getOutputName(inBlockIndexPair.getIndex(), streamID);
		String outputName = AbstractNxmPlotWidget.createUniqueName(true);
		putOutputNameMapping(0, streamID, outputName);     // save output name mapping 

		// DISPTHIN*       P,4     ,INFILE=,OUTFILE=,REFRESHRATE=,FRAMELENGTH=,
		String pattern = "DISPTHIN/BG/PS={0,number,#}/TL={1,number,#} {0} {1} {2,number,#}";
		String cmdLine = MessageFormat.format(pattern, pipeSize, transferLength, inputName, outputName, refreshRate);

		return cmdLine;
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

	/**
	 * @return the transferLength
	 */
	public int getTransferLength() {
		return transferLength;
	}

	/**
	 * @param transferLength the transferLength to set
	 */
	public void setTransferLength(int transferLength) {
		this.transferLength = transferLength;
	}

	/**
	 * @return the refreshRate
	 */
	public int getRefreshRate() {
		return refreshRate;
	}

	/**
	 * @param refreshRate the refreshRate to set
	 */
	public void setRefreshRate(int refreshRate) {
		this.refreshRate = refreshRate;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

}

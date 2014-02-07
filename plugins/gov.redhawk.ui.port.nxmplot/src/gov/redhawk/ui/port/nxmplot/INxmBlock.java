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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;

import BULKIO.StreamSRI;

/** A NeXtMidas block.
 *
 * Nxm Block is data source (i.e. BULKIO Port (CORBA), BULKIO SDDS Port (Udp/multicast), pipe, file, etc.) for a
 * NeXtMidas commands to read and process (e.g. FFT, FCALCUALTOR, DISPTHIN) and display (e.g. PLOT, LIST2) / etc.
 * <br>
 * S is the settings object.
 *
 * <pre>e.g.
 *  === starting block has 0 inputs since it is the leaf/edge (main input block: e.g. bulkio, sdds, file) ==
 *
 *  startingBlock = new BulkIONxmBlock(..);
 *  ...
 *
 *  srcInputNxmBlock = startingBlock;
 *  aBlock.addInput(myInIndex, srcInputNxmBlock, srcBlockOutIndex)
 *    new streamID     --> aBlock.launch(streamID)
 *    end of streamID  --> aBlock.shutdown(streamID)
 *
 *  aBlock.removeInput(myInIndex)
 *    --> srcInputNxmBlock.shutdown(*)
 *
 *  aBlock.launch(streamID, streamSRI)
 *     for each block --> block.launch(streamID, streamSRI)
 *
 *  aBlock.shutdown(streamID?)
 *    for each block --> block.shutdown(streamID)
 *
 *  aBlock.dispose()
 *    for each block --> block.dispose()
 *
 *  ==== startup / add hook ===
 *  fftBlock.addInput(0, bulkioBlock, bulkioBlockOutIndex)
 *  plotBlock.addInput(0, fftBlock, fftBlockOutIndex)
 *    --or to use defaults for inIndex and outIndex--
 *  fftBlock.addInput(bulkioBlock)
 *  plotBlock.addInput(fftBlock)
 *
 *  === remove hook / from plot ==
 *    plotBlock.removeInput(inIndex)
 *    fftBlock.removeInput(inIndex)
 *
 * NOTE:
 *   we choose addInput(inputIndex, sourceBlock, sourceBlockOutIndex) API
 *        over addOutput(outIndex, destinationBlock, destBlockInIndex)
 *   because in NeXtMidas:
 *     1. can only have ONE source/writer to a file/pipe (i.e. a Command's input)  <-- primary reason
 *     2. a Command can have multiple inputs (e.g. FFT, PLOT)
 *     3. there can be ZERO to multiple destination/readers on a Command's output file/pipe
 * </pre>
 *
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
@NonNullByDefault
public interface INxmBlock {

	/** get current PlotWidget context. */
	@Nullable
	AbstractNxmPlotWidget getContext();

	/** hook up input to source block's output.
	 *  This only does the hook up, it will NOT launch things.
	 *  @param inIndex  this block's input index.
	 *  @param srcBlock the source block to get input from
	 *  @param srcBlockOutIndex the source block's output index
	 *  @throws IllegalArgumentException, e.g. for invalid inIndex and or srcBlockOutIndex
	 *  @throws UnsupportedOperationException, e.g. if block does not have any input b/c it is the starting point
	 */
	void addInput(int inIndex, INxmBlock srcBlock, int srcBlockOutIndex);

	/** This implementation should call {@link #addInput(int, INxmBlock, int)} with normal case
	 *  defaults (e.g. inIndex would be 0 and srcBlockOutIndex would 0).
	 *  @param srcBlock the source block to get input from
	 *  @throws IllegalArgumentException, e.g. for invalid inIndex
	 *  @throws UnsupportedOperationException, e.g. if block does not have any input b/c it is the starting point
	 */
	void addInput(INxmBlock srcBlock);

	/** remove hook for specified input index.
	 *   This only removes the hook up, it will NOT shutdown things.
	 *  @param inIndex  this block's input index to remove the hook
	 *  @throws IllegalArgumentException, e.g. for invalid inIndex
	 *  @throws UnsupportedOperationException, e.g. if this block does not have any input b/c it is the starting point
	 */
	void removeInput(int inIndex);

	/**
	 * get input/source block and it's output index for specified input index.
	 * @param inIndex   this block's input index (MUST be less than {@link #getMaxInputs()})
	 * @return input/source block (null for none)
	 * @throws UnsupportedOperationException, e.g. if this block does not have any input b/c it is the starting point
	 */
	@Nullable
	INxmBlock getInputBlock(int inIndex);

	/** this SHOULD only be called by {@link #addInput(int, INxmBlock, int)} to provide forward lookup
	 *  on the srcBlock to invoke this (destination) block's {@link #launch(String, StreamSRI)} when it's launch is called.
	 *  @param outIndex this block's output index
	 *  @param destBlock destination block
	 *  @param destBlockInIndex destination block's input index
	 *  @throws IllegalArgumentException, e.g. for invalid outIndex and or destBlockInIndex
	 *  @throws UnsupportedOperationException, e.g. if block does not have any output(s)
	 */
	void internalAddOutputMapping(int outIndex, INxmBlock destBlock, int destBlockInIndex);

	void internalRemoveOutputMapping(int outIndex, INxmBlock destBlock, int destBlockInIndex);

	/** @return maximum number of inputs allowed by this (usually 1+, 0 for none i.e. for start point) */
	int getMaxInputs();

	/** @return maximum number of outputs allowed by this (usually 1+, 0 for none i.e. for end point) */
	int getMaxOutputs();

	/** @return current number of inputs to this (usually 1 -or- 0 for starting input point). */
	int getNumberInputs();

	/**
	 * @returns current number of outputs available for this (usually 1 -or- 0 for end point).
	 * e.g. if FFT has one input and hence one output, then it should return 1;
	 * if FFT has two inputs, and hence it can have two or three outputs (out1, out2, outCross),
	 * then it should return 2 (no cross output enabled) or 3 (if cross output is enabled).
	 */
	int getNumberOutputs();

	/** returns the output file/pipe name that should be processed/displayed.
	 *  @param outIndex the output index
	 *  @param streamID the streamID
	 *  @return output name (null if none?)
	 *  @throws IllegalArgumentException, e.g. for invalid outIndex
	 */
	@Nullable
	String getOutputName(int outIndex, String streamID);

	/** launches the appropriate NeXtMidas command to acquire the input (e.g. CORBARECEIVER, SOURCENIC)
	 *  or process it's input(s) (e.g. using FFT, FCALCUALTOR).
	 *  Called every time when a new or changed StreamSRI comes in.
	 *  NOTE: This MUST launch hooked/connected follow on blocks.
	 * @param sriStreamID
	 * @param sri (StreamSRI for this streamID - this can be null)
	 */
	void launch(String streamID, @Nullable StreamSRI sri);

	/** Disposes of input source for specified stream ID. All resources MUST be freed. Any launched
	 *  commands/processing should be closed/exited and cleaned for this stream.
	 *  NOTE: This MUST shutdown hooked/connected follow on blocks.
	 */
	void shutdown(String streamID);

	/** get array of StreamSRIs that has been launched by this block.
	 * @return empty array if none, otherwise array of launched streams
	 */
	StreamSRI[] getLaunchedStreams();

	/** (optional) start any necessary things for this block.
	 *  e.g. register/connect to BULK IO Port for pushSRI, or BULK IO SDDS Port for attach/detach.
	 */
	void start() throws CoreException; // or init()

	/** (optional) stop any necessary things for this block.
	 *  e.g. de-register/disconnect to BULK IO Port or BULK IO SDDS Port.
	 *  
	 */
	void stop(); // or maybe use dispose() instead

	/** tear *ALL* my resources and call my INxmBlock's shutdown() for each stream that I have published.??? */
	void dispose();

	/**
	 * The preferences of this block
	 * @return
	 */
	IPreferenceStore getPreferences();

	/**
	 * Create the preference control page for this block
	 * @return Null if there is no preference control page
	 */
	IPreferencePage createPreferencePage();

}

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

import gov.redhawk.sca.util.Debug;
import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.INxmBlock;
import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.preferences.Preference;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import nxm.sys.inc.Commandable;
import nxm.sys.lib.Command;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import BULKIO.StreamSRI;

/**
 * This class provides a skeletal implementation of the {@link INxmBlock} interface,
 * to minimize the effort required to implement this interface.
 * <br>
 * C is the NeXtMidas Command that should have been launch.
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public abstract class AbstractNxmBlock< C extends Command > implements INxmBlock, IPropertyChangeListener {

	/** state flag: for block is disposed. */
	protected static final int DISPOSED = 1 << 0;
	/** state flag: for block is stopped. */
	protected static final int STOPPED  = 1 << 1;
	
	private static final Debug TRACE_LOG = new Debug(PlotActivator.PLUGIN_ID, AbstractNxmBlock.class.getSimpleName());
	private static final StreamSRI[] EMPTY_STREAMSRI_ARRAY = new StreamSRI[0];

	private final AbstractNxmPlotWidget plotWidget;
	private final Class< ? extends C> desiredLaunchClass;
	private int defaultInputIndex = 0;
	/** state mask of this block */
	private int state = 0;

	// FYI: ConcurrentHashMap does not allow null key or value
	/** key=streamID value=(cmdline,Command). */
	private final ConcurrentHashMap<String, SimpleImmutableEntry<String, C>> streamIDToCmdMap = new ConcurrentHashMap<String, SimpleImmutableEntry<String, C>>();
	private final List<StreamSRI> launchedStreamsList = Collections.synchronizedList(new ArrayList<StreamSRI>());

	private final ConcurrentHashMap<String, String> outIndexStreamIDToOutNameMap = new ConcurrentHashMap<String, String>();

	/** key=input index;  value=source block & it's output index. */
	private final ConcurrentHashMap<Integer, BlockIndexPair> inputMap = new ConcurrentHashMap<Integer, BlockIndexPair>();
	/** key=output index; value=list of (destination block & it's input index). */
	private final ConcurrentHashMap<Integer, List<BlockIndexPair>> outputMap = new ConcurrentHashMap<Integer, List<BlockIndexPair>>();
	private final IPreferenceStore preferenceStore;

	protected AbstractNxmBlock(@NonNull Class<C> desiredLaunchCommandClass, @NonNull AbstractNxmPlotWidget plotWidget, IPreferenceStore store) {
		this.plotWidget = plotWidget;
		this.desiredLaunchClass = desiredLaunchCommandClass;
		if (store == null) {
			store = Preference.createRuntimeStore();
		}
		this.preferenceStore = store;
		this.preferenceStore.addPropertyChangeListener(this);
	}

	@NonNull
	@Override
	public AbstractNxmPlotWidget getContext() {
		return plotWidget;
	}

	@Override
	public IPreferenceStore getPreferences() {
		return this.preferenceStore;
	}

	@Override
	public IPreferencePage createPreferencePage() {
		return null;
	}

	@Override
	public void addInput(int myInIndex, INxmBlock srcBlock, int srcBlockOutIndex) {
		final int maxInIndex = getMaxInputs();
		if (maxInIndex == 0) {
			throw new UnsupportedOperationException(getClass().getName() + " does not have any inputs.");
		} else if (myInIndex >= maxInIndex) {
			throw new IllegalArgumentException(getClass().getName() + " has max input of " + maxInIndex + ", given input index of " + myInIndex);
		}
		BlockIndexPair srcBlockPair = new BlockIndexPair(srcBlock, srcBlockOutIndex);
		inputMap.put(myInIndex, srcBlockPair);
		srcBlock.internalAddOutputMapping(srcBlockOutIndex, this, myInIndex);
		// TODO: throw IllegalArgumentException for invalid inIndex, and srcBlockOutIndex)?
	}

	@Override
	public void addInput(INxmBlock srcBlock) {
		addInput(getDefaultInputIndex(), srcBlock, 0);
	}

	@Override
	public void removeInput(int myInIndex) {
		final int maxInIndex = getMaxInputs();
		if (maxInIndex == 0) {
			throw new UnsupportedOperationException(getClass().getName() + " does not have any inputs.");
		} else if (myInIndex >= maxInIndex) {
			throw new IllegalArgumentException(getClass().getName() + " has max input of " + maxInIndex + ", given input index of " + myInIndex);
		}
		BlockIndexPair srcBlockPair = inputMap.remove(myInIndex);
		if (srcBlockPair != null) {
			INxmBlock block = srcBlockPair.getBlock();
			block.internalRemoveOutputMapping(srcBlockPair.getIndex(), this, myInIndex);
		}
	}

	@Override
	public INxmBlock getInputBlock(int myInIndex) {
		final int maxInIndex = getMaxInputs();
		if (maxInIndex == 0) {
			throw new UnsupportedOperationException(getClass().getName() + " does not have any inputs.");
		} else if (myInIndex >= maxInIndex) {
			throw new IllegalArgumentException(getClass().getName() + " has max input of " + maxInIndex + ", given input index of " + myInIndex);
		}

		BlockIndexPair srcBlockPair = inputMap.get(myInIndex);
		if (srcBlockPair != null) {
			INxmBlock block = srcBlockPair.getBlock();
			return block;
		}
		return null;
	}

	protected Set<Entry<Integer, BlockIndexPair>> getInputMappings() {
		return inputMap.entrySet();
	}

	protected Collection<BlockIndexPair> getInputBlocks() {
		return inputMap.values();
	}

	@Override
	public void internalAddOutputMapping(int outIndex, INxmBlock destBlock, int destBlockInIndex) {
		final int maxOutIndex = getMaxOutputs();
		if (maxOutIndex == 0) {
			throw new UnsupportedOperationException(getClass().getName() + " does not have any outputs.");
		} else if (outIndex >= maxOutIndex) {
			throw new IllegalArgumentException(getClass().getName() + " has max ouput of " + maxOutIndex + ", given output index of " + outIndex);
		}

		BlockIndexPair destBlockPair = new BlockIndexPair(destBlock, destBlockInIndex);
		List<BlockIndexPair> destBlockList = outputMap.get(outIndex);
		if (destBlockList == null) {
			synchronized (outputMap) { // lock outpuMap, since do not have output block list at this output index
				destBlockList = outputMap.get(outIndex); // double check, if someone else added a new list
				if (destBlockList == null) {
					destBlockList = Collections.synchronizedList(new ArrayList<BlockIndexPair>());
					outputMap.put(outIndex, destBlockList);
				}
			}
		}
		destBlockList.add(destBlockPair);
	}

	@Override
	public void internalRemoveOutputMapping(int outIndex, INxmBlock destBlock, int destBlockInIndex) {
		final int maxOutIndex = getMaxOutputs();
		if (maxOutIndex == 0) {
			throw new UnsupportedOperationException(getClass().getName() + " does not have any outputs.");
		} else if (outIndex >= maxOutIndex) {
			throw new IllegalArgumentException(getClass().getName() + " has max ouput of " + maxOutIndex + ", given output index of " + outIndex);
		}

		List<BlockIndexPair> destBlockList = outputMap.get(outIndex);
		if (destBlockList != null) {
			BlockIndexPair destBlockPair = new BlockIndexPair(destBlock, destBlockInIndex);
			destBlockList.remove(destBlockPair);
		}
	}

	/** Uses subclass's {@link #formCmdLine()} to get NeXtMidas command line to launch/execute
	 * (in background thread) and start PIPE data flow.
	 * NOTE: subclasses should generally NOT override this implementation.
	 */
	@SuppressWarnings("unchecked")
	// this is checked via Class.isAssignableFrom(..) before casting
	@Override
	public void launch(String streamID, StreamSRI sri) {
		checkLaunchParams(streamID, sri);
		final AbstractNxmPlotWidget currentPlotContext = getContext();

		// subclasses specifies the NeXtMidas command line to execute
		String cmdLine = formCmdLine(currentPlotContext, streamID);

		if (AbstractNxmBlock.TRACE_LOG.enabled) {
			AbstractNxmBlock.TRACE_LOG.message("launch({0}) cmdline: {1} [{2}]", streamID, cmdLine, toString());
		}
		if (cmdLine != null && !cmdLine.trim().isEmpty()) {
			final Command cmd = currentPlotContext.runGlobalCommand(cmdLine + " /BG");
			if (cmd == null) {
				throw new IllegalStateException("Expected to launch NeXtMidas command, but got null");
			} else {
				if (desiredLaunchClass.isAssignableFrom(cmd.getClass())) {
					C nxmCommand = (C) cmd;
					SimpleImmutableEntry<String, C> cmd4Stream = new SimpleImmutableEntry<String, C>(cmdLine, nxmCommand);
					streamIDToCmdMap.put(streamID, cmd4Stream);
					launchedStreamsList.add(sri);
					if (AbstractNxmBlock.TRACE_LOG.enabled) {
						AbstractNxmBlock.TRACE_LOG.message("launched({0}) Command: {1} [{2}]", streamID, nxmCommand, toString());
					}

				} else {
					throw new IllegalStateException("Expected to launch " + desiredLaunchClass + " command, but found: " + cmd.getClass());
				}
			}
			cmd.setMessageHandler(currentPlotContext.getPlotMessageHandler());

			currentPlotContext.runGlobalCommand("PIPE RUN"); // start NeXtMidas pipe data flow (if it has not already started)

			if (AbstractNxmBlock.TRACE_LOG.enabled) {
				currentPlotContext.runGlobalCommand("STATUS/VERBOSE " + getOutputName(0, streamID));
			}
		}

		// launch hooked output blocks to consume this streamID
		for (List<BlockIndexPair> outBlocks : outputMap.values()) {
			for (BlockIndexPair outBlockIndexPair : outBlocks) {
				outBlockIndexPair.getBlock().launch(streamID, sri);
			}
		}
	}

	@Override
	public void shutdown(String streamID) {
		SimpleImmutableEntry<String, C> cmd4Stream = streamIDToCmdMap.remove(streamID);
		if (cmd4Stream != null) {
			C nxmCommand = cmd4Stream.getValue();
			if (nxmCommand != null) {
				nxmCommand.setState("Finish"); // tell command to wrap-up and exit
			}
		}

		// shutdown hooked/follow-on output blocks for this streamID
		for (List<BlockIndexPair> outBlocks : outputMap.values()) {
			for (BlockIndexPair outBlockIndexPair : outBlocks) {
				outBlockIndexPair.getBlock().shutdown(streamID);
			}
		}
	}

	@Override
	public StreamSRI[] getLaunchedStreams() {
		StreamSRI[] retval = launchedStreamsList.toArray(AbstractNxmBlock.EMPTY_STREAMSRI_ARRAY);
		return retval;
	}

	@Override
	public int getMaxOutputs() {
		return 1;
	}

	@Override
	public int getNumberInputs() {
		return inputMap.size();
	}

	@Override
	public int getNumberOutputs() {
		return outputMap.size();
	}

	@Override
	public String getOutputName(int outIndex, String streamID) {
		String outputName = outIndexStreamIDToOutNameMap.get(outIndex + streamID);
		return outputName;
	}

	/** Adds outIndex/streamID to outputName mapping. returns previous value (if any).
	 *  Subclass MUST call this (most likely in it's {@link #formCmdLine(AbstractNxmPlotWidget, String)})
	 *  to setup output name mapping for it's outputs.
	 */
	protected String putOutputNameMapping(int outIndex, String streamID, String outputName) {
		String prevOutputName = outIndexStreamIDToOutNameMap.put(outIndex + streamID, outputName);
		return prevOutputName;
	}

	/** Removes outIndex/streamID to outputName mapping. returns current value (if any).
	 *  Subclass SHOULD call this in it's {@link #shutdown(String)} to clean up.
	 */
	protected String removeOutputNameMapping(int outIndex, String streamID) {
		String outputName = outIndexStreamIDToOutNameMap.remove(outIndex + streamID);
		return outputName;
	}

	@Override
	public void start() throws CoreException {
		// nothing to startup by default
	}

	@Override
	public void stop() {
		if (isStopped()) {
			return; // It is valid to attempt to stop a block more than once, so just return
		}
		state |= STOPPED;
	}
	
	@Override
	public boolean isStopped() {
		return (state & STOPPED) != 0;
	}

	@Override
	public boolean isDisposed() {
		return (state & DISPOSED) != 0;
	}

	@Override
	public void dispose() {
		AbstractNxmBlock.TRACE_LOG.enteringMethod(isDisposed());
		if (isDisposed()) {
			return; // It is valid to attempt to dispose a block more than once, so just return
		}
		state |= DISPOSED;
		stop();
		// shutdown all launched Commands
		// for (SimpleImmutableEntry<String, C> entry : streamIDToCmdMap.values()) {
		Iterator<Entry<String, SimpleImmutableEntry<String, C>>> iter = streamIDToCmdMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, SimpleImmutableEntry<String, C>> entry = iter.next();
			C nxmCommand = entry.getValue().getValue();
			nxmCommand.setState(Commandable.FINISH); // tell Command it should FINISH/EXIT
			iter.remove();
		}

		this.preferenceStore.removePropertyChangeListener(this);
	}

	public List<C> getNxmCommands() {
		List<C> retVal = new ArrayList<C>();
		for (SimpleImmutableEntry<String, C> entry : streamIDToCmdMap.values()) {
			retVal.add(entry.getValue());
		}
		return retVal;
	}

	/**
	 * @return the NeXtMidas Command for the specified streamID (null if none)
	 */
	@Nullable
	public C getNxmCommand(String streamID) {
		C nxmCommand;
		SimpleImmutableEntry<String, C> entry = streamIDToCmdMap.get(streamID);
		if (entry != null) {
			nxmCommand = entry.getValue();
		} else {
			nxmCommand = null;
		}
		return nxmCommand;
	}
	
	@NonNull
	public List<String> getStreamIDs() {
		return Collections.unmodifiableList(new ArrayList<String>(streamIDToCmdMap.keySet()));
	}

	protected int getDefaultInputIndex() {
		return defaultInputIndex;
	}

	/** only sub-classes can set it's default input index. */
	protected void setDefaultInputIndex(int defaultInputIndex) {
		this.defaultInputIndex = defaultInputIndex;
	}

	protected BlockIndexPair getInputBlockInfo(int inIndex) {
		BlockIndexPair srcBlockPair = inputMap.get(inIndex);
		return srcBlockPair;
	}

	protected boolean hasOutputBlockAt(int outIndex) {
		List<BlockIndexPair> destBlockList = outputMap.get(outIndex);
		if (destBlockList != null) {
			return !destBlockList.isEmpty();
		}
		return false;
	}

	protected void checkLaunchParams(@NonNull String streamID, @Nullable StreamSRI sri) {
		if (getContext() == null) {
			throw new IllegalStateException("A context (AbstractNxmPlotWidget) must be set before launch() can be called.");
		}

		if (sri != null && !streamID.equals(sri.streamID)) { // sri specified, check that sri.streamID matches streamID
			throw new IllegalArgumentException("streamID [" + streamID + "] MUST match sri.streamID [" + sri.streamID + "] when sri is specified.");
		}
	}

	protected Map<String, SimpleImmutableEntry<String, C>> getStreamIdToCommandMap() {
		return Collections.unmodifiableMap(streamIDToCmdMap);
	}

	// =========================================================================
	// METHODS that subclasses should implement
	// =========================================================================

	/**
	 * @param plotWidget
	 * @param streamID
	 * @return NeXtMidas command line to run/execute (null to NOT run any command)
	 */
	@Nullable
	protected abstract String formCmdLine(@NonNull AbstractNxmPlotWidget plotWidget, @NonNull String streamID);

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub

	}

	// =========================================================================
	// Begin Inner classes
	// =========================================================================
	protected static class BlockIndexPair {
		private final INxmBlock block;
		private final int index;

		public BlockIndexPair(INxmBlock block, int index) {
			super();
			this.block = block;
			this.index = index;
		}

		public INxmBlock getBlock() {
			return block;
		}

		public int getIndex() {
			return index;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((block == null) ? 0 : block.hashCode()); // SUPPRESS CHECKSTYLE AvoidInline
			result = prime * result + index;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			BlockIndexPair other = (BlockIndexPair) obj;
			if (block == null) {
				if (other.block != null) {
					return false;
				}
			} else if (!block.equals(other.block)) {
				return false;
			}
			if (index != other.index) {
				return false;
			}
			return true;
		}

	} // end class BlockIndexPair 
}

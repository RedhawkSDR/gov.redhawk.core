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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import nxm.sys.lib.Command;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.action.IMenuManager;

import BULKIO.StreamSRI;

/**
 * This class provides a skeletal implementation of the {@link INxmBlock} interface,
 * to minimize the effort required to implement this interface.
 * <br>
 * C is the NeXtMidas Command that should have been launch.
 * S is the settings object.
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public abstract class AbstractNxmBlock<C extends Command, S extends Object> implements INxmBlock {

	private static final Debug TRACE_LOG = new Debug(PlotActivator.PLUGIN_ID, AbstractNxmBlock.class.getSimpleName());
		
	private AbstractNxmPlotWidget plotWidget;
	private int defaultInputIndex = 0;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final Class<? extends C> desiredLaunchClass;

	// FYI: ConcurrentHashMap does not allow null key or value
	/** key=streamID value=(cmdline,Command). */
	private final ConcurrentHashMap<String, SimpleImmutableEntry<String, C>> streamIDToCmdMap = new ConcurrentHashMap<String, SimpleImmutableEntry<String, C>>();
	private final List<String> launchedStreamIDList = Collections.synchronizedList(new ArrayList<String>());

	private final ConcurrentHashMap<String, String> outIndexStreamIDToOutNameMap = new ConcurrentHashMap<String, String>();

	static class BlockIndexPair {
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
			result = prime * result + ((block == null) ? 0 : block.hashCode());
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

	}
	/** key=input index;  value=source block & it's output index. */
	private final ConcurrentHashMap<Integer, BlockIndexPair> inputMap  = new ConcurrentHashMap<Integer, BlockIndexPair>();
	/** key=output index; value=list of (destination block & it's input index). */
	private final ConcurrentHashMap<Integer, List<BlockIndexPair>> outputMap = new ConcurrentHashMap<Integer, List<BlockIndexPair>>();
	private String label;
 
	protected AbstractNxmBlock(@NonNull Class<C> desiredLaunchCommandClass, @NonNull String label, @NonNull AbstractNxmPlotWidget plotWidget) {
		desiredLaunchClass = desiredLaunchCommandClass;
		this.label = label;
		this.plotWidget = plotWidget;
	}

	/** any sub-class that overrides this method MUST call super.setContext(..). */
	@Override
	public void setContext(AbstractNxmPlotWidget widget) {
		this.plotWidget = widget;
	}

	@NonNull
	@Override
	public AbstractNxmPlotWidget getContext() {
		return plotWidget;
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
	public INxmBlock  getInputBlock(int myInIndex) {
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
	@SuppressWarnings("unchecked") // this is checked via Class.isAssignableFrom(..) before casting
	@Override
	public void launch(String streamID, StreamSRI sri) {
		checkLaunchParams(streamID, sri);
		final AbstractNxmPlotWidget currentPlotContext = getContext();

		// subclasses specifies the NeXtMidas command line to execute
		String cmdLine = formCmdLine(currentPlotContext, streamID);

		if (TRACE_LOG.enabled) {
			TRACE_LOG.message("launch({0}) cmdline: {1} [{2}]", streamID, cmdLine, toString());
		}
		if (cmdLine != null && !cmdLine.trim().isEmpty()) {
			final Command cmd = currentPlotContext.runHeadlessCommandWithResult(cmdLine + " /BG");
			if (cmd == null) {
				throw new IllegalStateException("Expected to launch NeXtMidas command, but got null");
			} else {
				if (desiredLaunchClass.isAssignableFrom(cmd.getClass())) {
					C nxmCommand = (C) cmd;
					SimpleImmutableEntry<String, C> cmd4Stream = new SimpleImmutableEntry<String, C>(cmdLine, nxmCommand);
					streamIDToCmdMap.put(streamID, cmd4Stream);
					if (TRACE_LOG.enabled) {
						TRACE_LOG.message("launched({0}) Command: {1} [{2}]", streamID, nxmCommand, toString());
					}

				} else {
					throw new IllegalStateException("Expected to launch " + desiredLaunchClass + " command, but found: " + cmd.getClass());
				}
			}
			cmd.setMessageHandler(currentPlotContext.getPlotMessageHandler());

			currentPlotContext.runHeadlessCommand("PIPE RUN"); // start NeXtMidas pipe data flow (if it has not already started)
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
		// nothing to stop by default
	}


	@Override
	public void dispose() {
		TRACE_LOG.enteringMethod();
		stop();
	}

	@Override
	@NonNull
	public String getLabel() {
		final String tmp = label;
		if (tmp != null) {
			return tmp;
		} else {
			return "unknown";
		}
	}

	@Nullable
	public String setLabel(String newLabel) {
		String prevLabel = label;
		label = newLabel;
		return prevLabel;
	}

	@Override
	public void contributeMenuItems(IMenuManager menu) {
		// sub-classes should override this to contribute menu items
	}
	
	@Override
	public void addProperChangeListener(PropertyChangeListener nxmCmdSourceListner) {
		pcs.addPropertyChangeListener(nxmCmdSourceListner);
	}

	@Override
	public void removeProperChangeListener(PropertyChangeListener nxmCmdSourceListner) {
		pcs.removePropertyChangeListener(nxmCmdSourceListner);
	}

	@Override
	public void applySettings(Object settings, String streamId) {
		if (settings == null) {
			return;
		}
		if (streamId != null) {
			C cmd = getNxmCommand(streamId);
			if (cmd != null) {
				applySettingsTo(cmd, settings, streamId);
			}
		} else { // apply to all stream IDs
			Set<Entry<String, SimpleImmutableEntry<String, C>>> entryValues = streamIDToCmdMap.entrySet();
			for (Entry<String, SimpleImmutableEntry<String, C>> entry : entryValues) {
				C cmd = entry.getValue().getValue();
				if (cmd != null) {
					applySettingsTo(cmd, settings, entry.getKey());
				}
			}
		}
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

	/**
	 * sub-classes should override this instead of {@link #applySettings(Object, String)} 
	 * since this classes's implementation does the command lookup and iteration logic.
	 * The only exception is if the subclass does not support applying settings operations. 
	 */
	protected void applySettingsTo(@NonNull C cmd, @NonNull Object settings, @NonNull String streamId) {
	}
	
	@Nullable
	protected C getNxmCommandForFirstStream() {
		C nxmCommand;
		String firstStreamID;
		try {
			firstStreamID = launchedStreamIDList.get(0);
		} catch (IndexOutOfBoundsException e) {
			firstStreamID = null;
		}
		if (firstStreamID != null) {
			SimpleImmutableEntry<String, C> entry = streamIDToCmdMap.get(firstStreamID);
			if (entry != null) {
				nxmCommand = entry.getValue();
			} else {
				nxmCommand = null;
			}
		} else {
			nxmCommand = null;
		}
		return nxmCommand;
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

	protected void firePropertyChangeEvent(PropertyChangeEvent event) {
		pcs.firePropertyChange(event);
	}
	
}

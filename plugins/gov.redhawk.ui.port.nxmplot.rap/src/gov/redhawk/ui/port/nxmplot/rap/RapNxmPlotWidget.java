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
package gov.redhawk.ui.port.nxmplot.rap;

import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nxm.rap.ui.NxmRapComposite;
import nxm.redhawk.lib.RedhawkNxmUtil;
import nxm.sys.lib.Command;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

/**
 *
 */
public class RapNxmPlotWidget extends AbstractNxmPlotWidget {

	private static final String MSG_HANDLER_ID = "MAIN_MSG_HANLDER";
	private static final String PLOT_ID = "PLOT";
	private NxmRapComposite nxmComp;
	private boolean initialized;
	private Set<String> sources = new HashSet<String>();
	private boolean initializing;

	public RapNxmPlotWidget(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		this.nxmComp = new NxmRapComposite(this, SWT.NONE);
		RedhawkNxmUtil.initializeRedhawkOptionTrees();
		nxmComp.initNxm();
		nxmComp.getNxmShell().M.registry.put(MSG_HANDLER_ID, getPlotMessageHandler());
	}

	public synchronized void internalInitPlot(String plotSwitches, String plotArgs) {
		if (initialized) {
			return;
		}
		initialized = true;
		if (plotArgs == null) {
			plotArgs = "";
		}
		if (plotSwitches == null) {
			plotSwitches = "";
		}
		nxmComp.runClientCommand("PLOT" + plotSwitches + "/BG/EXIT=MESSAGE|WINDOW/ID=" + PLOT_ID + " " + plotArgs);
	}

	@Override
	public void dispose() {
		/* avoid race condition when plot is disposed and re-initialized
		 * rapidly (e.g. via menu listeners causing plot to be hidden and shown).
		 */
		while (!this.nxmComp.isClientInitialized()) {
			if (!getParent().getDisplay().readAndDispatch()) {
				getParent().getDisplay().sleep();
			}
		}
		String[] sourcesCopy = Arrays.copyOf(sources.toArray(new String[0]), sources.size());
		for (String source : sourcesCopy) {
			removeSource(source);
		}
		runClientCommand("PIPE STOP"); // tell client macro to end
		super.dispose();
		nxmComp = null;
	}

	private void assertNotDisposed() {
		Assert.isTrue(!isDisposed(), "Widget is disposed");
	}

	@Override
	public void runHeadlessCommand(String command) {
		runHeadlessCommandWithResult(command);
	}

	@Override
	public Command runHeadlessCommandWithResult(String command) {
		assertNotDisposed();
		return nxmComp.runServerCommand(command + " /MSGID=" + MSG_HANDLER_ID);
	}

	@Override
	public void runClientCommand(String command) {
		assertNotDisposed();
		nxmComp.runClientCommand(command);
	}

	@Override
	public void internalAddSource(String sourcePipeId, String plotQualifiers) {
		assertNotDisposed();
		Object pipeInSubShell = nxmComp.getNxmShell().M.pipes.get(sourcePipeId);
		Assert.isTrue(pipeInSubShell instanceof nxm.sys.lib.Pipe,
				sourcePipeId + " value is not a valid data PIPE! value=" + pipeInSubShell);
		// Copy pipe reference from sub-shell into global shell
		NxmRapComposite.getRootNxmShell().M.pipes.put(sourcePipeId, pipeInSubShell);

		// Publish pipe on RMIF
		nxmComp.getRmifPrim().getRmif().addProperty(sourcePipeId);

		// From Client openFile
		nxmComp.runClientCommand("SENDTO RMIF_SESSION ADDC {" + sourcePipeId + "=" + sourcePipeId + "} INFO=-1");
		nxmComp.runClientCommand("SENDTO " + PLOT_ID + " OPENFILE " + sourcePipeId + ((plotQualifiers == null) ? "" : plotQualifiers));

		this.sources.add(sourcePipeId);
	}

	@Override
	public void removeSource(String sourcePipeId) {
		assertNotDisposed();
		if (sourcePipeId != null) {
			// From Client closeFIle
			nxmComp.runClientCommand("SENDTO " + PLOT_ID + " CLOSEFILE " + sourcePipeId);

			// From Client via RMIF disconnect pipe
			nxmComp.runClientCommand("SENDTO RMIF DELC " + sourcePipeId);

			// UnPublish pipe on RMIF
			nxmComp.getRmifPrim().getRmif().closeChannel(sourcePipeId);

			// From Server remove pipe reference from global registry
			nxmComp.runServerCommand("REMOVE/global RAM." + sourcePipeId);
			
			this.sources.remove(sourcePipeId);
		}

	}

	@Override
	public void sendPlotMessage(String msgName, int info, Object data) {
		assertNotDisposed();
		nxmComp.runClientCommand("SENDTO " + PLOT_ID + " " + msgName + " " + data + " INFO=" + info);
	}

	@Override
	public void configurePlot(Map<String, String> configuration) {
		assertNotDisposed();
		for (Map.Entry<String, String> entry : configuration.entrySet()) {
			nxmComp.runClientCommand("SET " + "REG." + PLOT_ID + "." + entry.getKey() + " " + entry.getValue());
		}
	}

	@Override
	public String addDataFeature(Number xStart, Number xEnd, String color) {
		String featureId = AbstractNxmPlotWidget.createUniqueName(false);
		double dx = xEnd.doubleValue() - xStart.doubleValue();
		final String cmd = "FEATURE LABEL=" + featureId + " PLOT=" + PLOT_ID + " TABLE={NAME=\""
				+ featureId + "\",TYPE=\"DATA\",X=" + (xStart.doubleValue() + (dx / 2)) + ",DX=" + dx + ",COLOR=\"" + color + "\"}";
		this.runClientCommand(cmd);
		return featureId;
	}

	@Override
	public String addDragboxFeature(Number xmin, Number ymin, Number xmax, Number ymax, String color) {
		String featureId = AbstractNxmPlotWidget.createUniqueName(false);
		final double x = (xmax.doubleValue() + xmin.doubleValue()) / 2d;
		final double y = (ymax.doubleValue() + ymin.doubleValue()) / 2d;
		final double dx = xmax.doubleValue() - xmin.doubleValue();
		final double dy = ymax.doubleValue() - ymin.doubleValue();
		final String command = "FEATURE LABEL=" + featureId + " PLOT=" + PLOT_ID + " TABLE={NAME=\"" + featureId
				+ "\",TYPE=\"BOX\",X=" + x + ",DX=" + dx + ",Y=" + y + ",DY=" + dy + ",COLOR=\"" + color + "\"}";
		this.runClientCommand(command);
		return featureId;
	}

	@Override
	public void removeFeature(String featureId) {
		final String command = "INVOKE retval REG." + PLOT_ID + ".removeFeature(\"" + featureId + "\")";
		this.runClientCommand(command);
	}

	@Override
	public Set<String> getSources() {
		return Collections.unmodifiableSet(this.sources);
	}

	@Override
	public void clearSources() {
		for (String source : sources.toArray(new String[sources.size()])) {
			removeSource(source);
		}
	}

	/** @since 5.0 */
	@Override
	public void sendMessageToCommand(String cmdID, String msgName, int info, Object data, Object quals) {
		assertNotDisposed();
		nxmComp.runServerCommand("MESSAGE SEND ID=" + cmdID + " NAME=" + msgName + " INFO=" + info
				+ " DATA=" + data + " QUALS=" + quals);
	}

}

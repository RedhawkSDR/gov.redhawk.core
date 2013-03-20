/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.ui.port.nxmplot.rcp;

import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import nxm.rcp.ui.core.NeXtMidasComposite;
import nxm.redhawk.lib.RedhawkNxmUtil;
import nxm.sys.prim.plot;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * 
 */
public class RcpNxmPlotWidget extends AbstractNxmPlotWidget {

	private NeXtMidasComposite nxmComp;
	private plot plotCommand;
	private DisposeListener disposeListener = new DisposeListener() {

		public void widgetDisposed(DisposeEvent e) {
			dispose();
		}
	};
	
	private static final String MSG_HANDLER_ID = "MAIN_MSG_HANLDER";
	
	private Set<String> sources = new HashSet<String>();
	private boolean initialized;

	public RcpNxmPlotWidget(final Composite parent, int style) {
		super(parent, style);
		parent.addDisposeListener(disposeListener);
		setLayout(new FillLayout());
		RedhawkNxmUtil.initializeRedhawkOptionTrees();
		nxmComp = new NeXtMidasComposite(this, SWT.None);
	}

	public synchronized void initPlot(String plotSwitches, String plotArgs) {
		Assert.isTrue(Display.getCurrent() == null, "Do not call initPlot from the UI thread.");
		if (this.initialized) {
			return;
		}
		this.initialized = true;
		RedhawkNxmUtil.initializeRedhawkOptionTrees();
		nxmComp.initNxm();
		nxmComp.getLocalShell().M.registry.put(MSG_HANDLER_ID, this.plotMessageHandler);
		if (plotArgs==null) {
			plotArgs = "";
		}
		if (plotSwitches == null) {
			plotSwitches = "";
		}
		plotCommand = (plot) nxmComp.runCommand("plot/bg" + plotSwitches + " " + plotArgs);
		plotCommand.setMessageHandler(plotMessageHandler);
	}

	@Override
	public void runHeadlessCommand(String command) {
		nxmComp.runCommand(command + " /MSGID_TMP=" + MSG_HANDLER_ID, false);
	}

	@Override
	public void runClientCommand(String command) {
		runHeadlessCommand(command); 
    }
	
	@Override
	public void dispose() {
		String[] sourcesCopy = Arrays.copyOf(sources.toArray(new String[0]), sources.size());
		for (String source : sourcesCopy) {
			removeSource(source);
		}
		this.sources.clear();
	    super.dispose();

	}

	/**
	 * Use {@Link #addSource(String, String)} to sepecify plot qualifiers. Calling this method with qualifiers
	 * concatenated with the sourceId may result in undefined behavior.
	 */
	@Override
	public void addSource(String sourcePipeId) {
		addSource(sourcePipeId, null);
    }
	
	@Override
	public void addSource(String sourcePipeId, String pipeQualifiers) {
		nxmComp.runCommand("sendto " + plotCommand.id + " OPENFILE " + sourcePipeId + (pipeQualifiers == null ? "" : pipeQualifiers));
		this.sources.add(sourcePipeId);
    }
	
	public Set<String> getSources() {
		return Collections.unmodifiableSet(this.sources);
	}

	@Override
	public void removeSource(String sourcePipeId) {
		nxmComp.runCommand("sendto " + plotCommand.id + " CLOSEFILE " + sourcePipeId);
		this.sources.remove(sourcePipeId);
    }


	@Override
	public void sendPlotMessage(String msgName, int info, Object data) {
		//	    this.plotCommand.processMessage(msgName, info, data);
		// XXX We need to rethink this for RAP support
		String tempResName = createUniqueResName();
		nxmComp.getLocalShell().M.results.put(tempResName, data); //to pass object reference for data= in the SENDTO command
		nxmComp.runCommand("sendto " + plotCommand.id + " "+ msgName + " " + tempResName + " INFO=" + info);
		nxmComp.getLocalShell().M.results.remove(tempResName); // cleanup
	}
	
	private static AtomicInteger uniqueCounter = new AtomicInteger();
	
	private static String createUniqueResName() {
		return "_TEMPRES_" + uniqueCounter.incrementAndGet();
	}
	
	//This method available only in the RCP plot widget
	public plot getPlot() {
		return this.plotCommand;
	}
	
	@Override
	public String addDataFeature(Number xStart, Number xEnd, String color) {
		String featureId = AbstractNxmPlotWidget.createUniqueName(false);
		final double dx = xEnd.doubleValue() - xStart.doubleValue();
		final String cmd = "FEATURE LABEL=" + featureId + " PLOT=" + plotCommand.id + " TABLE={NAME=\"" +
		featureId + "\",TYPE=\"DATA\",X=" + (xStart.doubleValue() + (dx/2)) + ",DX=" + dx + ",COLOR=\"" + color + "\"}";
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
		final String command = "FEATURE LABEL=" + featureId + " PLOT=" + plotCommand.id + " TABLE={NAME=\"" + featureId
				+ "\",TYPE=\"BOX\",X=" + x + ",DX=" + dx + ",Y=" + y + ",DY=" + dy + ",COLOR=\"" + color + "\"}";
		this.runClientCommand(command);
		return featureId;
	}
	
	@Override
	public void removeFeature(String featureId) {
		final String command = "invoke junk reg." + plotCommand.id + ".removeFeature(\"" + featureId + "\")";
		this.runClientCommand(command);
	}

	@Override
    public void configurePlot(Map<String, String> configuration) {
	    for (Map.Entry<String, String> entry: configuration.entrySet()) {
	    	nxmComp.runCommand("set " + "REG." + plotCommand.id + "." + entry.getKey() + " " + entry.getValue());
	    }
    }

	@Override
    public void clearSources() {
	    for (String source : sources.toArray(new String[sources.size()])) {
	    	removeSource(source);
	    }
    }

}

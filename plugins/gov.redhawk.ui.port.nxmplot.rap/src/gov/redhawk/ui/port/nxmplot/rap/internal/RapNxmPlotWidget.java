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
package gov.redhawk.ui.port.nxmplot.rap.internal;

import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nxm.rap.ui.NxmRapComposite;
import nxm.redhawk.lib.RedhawkNxmUtil;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 *
 */
public class RapNxmPlotWidget extends AbstractNxmPlotWidget {

	/**
	 *
	 */
    private static final long serialVersionUID = -3610272766732782615L;
    private static final String PLOT_ID = "PLOT";
	private NxmRapComposite nxmComp;
	private boolean initialized;
	private Set<String> sources = new HashSet<String>();

	public RapNxmPlotWidget(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		this.nxmComp = new NxmRapComposite(this, SWT.NONE);
    }

	public synchronized void initPlot(String plotSwitches, String plotArgs) {
		Assert.isTrue(Display.getCurrent() == null, "Do not call initPlot from the UI thread.");
		if (initialized) {
			return;
		}
		initialized = true;
		RedhawkNxmUtil.initializeRedhawkOptionTrees();
		nxmComp.initNxm();
		if (plotArgs==null) {
			plotArgs = "";
		}
		if (plotSwitches == null) {
			plotSwitches = "";
		}
		nxmComp.runClientCommand("plot" + plotSwitches + "/bg/ID=" + PLOT_ID + " " + plotArgs);
	}

	@Override
	public void dispose() {
		Set<String> sourcesCopy = Collections.unmodifiableSet(sources);
		for (String source : sourcesCopy) {
			removeSource(source);
		}
	    super.dispose();
	    nxmComp = null;
	}

	private void assertNotDisposed() {
		Assert.isTrue(!isDisposed(), "Widget is disposed");
	}

	@Override
	public void runHeadlessCommand(String command) {
		assertNotDisposed();
		nxmComp.runServerCommand(command);
	}

	@Override
	public void runClientCommand(String command) {
		assertNotDisposed();
		nxmComp.runClientCommand(command);
	}
	/**
	 * Use {@link #addSource(String, String)} to specify plot qualifiers. This method will not work properly
	 * if plot qualifiers are concatenated to the source ID.
	 */
	@Override
	public void addSource(String sourcePipeId) {
		assertNotDisposed();
		// Copy pipe reference from sub-shell into global shell
		NxmRapComposite.getRootNxmShell().M.pipes.put(sourcePipeId, nxmComp.getNxmShell().M.pipes.get(sourcePipeId));

		// Publish pipe on rmif
		nxmComp.getRmifPrim().getRmif().addProperty(sourcePipeId);

		// From Client openFile
		nxmComp.runClientCommand("sendto RMIF_SESSION ADDC {" + sourcePipeId + "=" + sourcePipeId + "} INFO=-1");
		nxmComp.runClientCommand("sendto " + " " + PLOT_ID + " OPENFILE " + sourcePipeId);

		this.sources.add(sourcePipeId);
	}

	@Override
	public void addSource(String sourcePipeId, String plotQualifiers) {
		assertNotDisposed();
		// Copy pipe reference from sub-shell into global shell
		NxmRapComposite.getRootNxmShell().M.pipes.put(sourcePipeId, nxmComp.getNxmShell().M.pipes.get(sourcePipeId));

		// Publish pipe on rmif
		nxmComp.getRmifPrim().getRmif().addProperty(sourcePipeId);

		// From Client openFile
		nxmComp.runClientCommand("sendto RMIF_SESSION ADDC {" + sourcePipeId + "=" + sourcePipeId + "} INFO=-1");
		nxmComp.runClientCommand("sendto " + " " + PLOT_ID + " OPENFILE " + sourcePipeId + (plotQualifiers == null ? "" : plotQualifiers));

		this.sources.add(sourcePipeId);
	}

	@Override
	public void removeSource(String sourcePipeId) {
		assertNotDisposed();
		// From Client closeFIle
		nxmComp.runClientCommand("sendto " + PLOT_ID + " CLOSEFIE " + sourcePipeId);

		// From Client via RMIF disconnect pipe
		nxmComp.runClientCommand("sendto RMIF DELC " + sourcePipeId);

		// UnPublish pipe on rmif
		nxmComp.getRmifPrim().getRmif().closeChannel(sourcePipeId);

		// From Server remove reference from global registry
		nxmComp.runServerCommand("remove/global RAM." + sourcePipeId);

		this.sources.remove(sourcePipeId);
	}

	@Override
	public void sendPlotMessage(String msgName, int info, Object data) {
		assertNotDisposed();
		nxmComp.runClientCommand("sendto " + PLOT_ID + " " + msgName + " " + data + " INFO=" + info);
	}

	@Override
	public void configurePlot(Map<String, String> configuration) {
		assertNotDisposed();
		for (Map.Entry<String, String> entry : configuration.entrySet()) {
			nxmComp.runClientCommand("set " + "REG." + PLOT_ID + "." + entry.getKey() + " " + entry.getValue());
		}
	}

	@Override
	public String addDataFeature(Number xStart, Number xEnd, String color) {
		String featureId = AbstractNxmPlotWidget.createUniqueName(false);
                double dx = xEnd.doubleValue() - xStart.doubleValue();
		final String cmd = "FEATURE LABEL=" + featureId + " PLOT=" + PLOT_ID + " TABLE={NAME=\"" +
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
		final String command = "FEATURE LABEL=" + featureId + " PLOT=" + PLOT_ID + " TABLE={NAME=\"" + featureId
				+ "\",TYPE=\"BOX\",X=" + x + ",DX=" + dx + ",Y=" + y + ",DY=" + dy + ",COLOR=\"" + color + "\"}";
		this.runClientCommand(command);
		return featureId;
	}
	
	@Override
	public void removeFeature(String featureId) {
		final String command = "invoke junk reg." + PLOT_ID + ".removeFeature(\"" + featureId + "\")";
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

}

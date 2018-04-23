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
package gov.redhawk.core.graphiti.internal.ui.diagram.providers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.ColorDecorator;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.ui.statushandlers.StatusManager;
import org.omg.CORBA.BAD_OPERATION;

import BULKIO.PortStatistics;
import CF.DataType;
import gov.redhawk.core.graphiti.ui.GraphitiUIPlugin;
import gov.redhawk.core.graphiti.ui.diagram.providers.IDecoratorProvider;
import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.preferences.DiagramPreferenceConstants;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.monitor.IPortStatListener;
import gov.redhawk.monitor.MonitorPlugin;
import gov.redhawk.monitor.MonitorPortAdapter;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadConnectInterface;
import mil.jpeojtrs.sca.sad.SadUsesPort;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class PortMonitorDecoratorProvider implements IDecoratorProvider, IPortStatListener {

	private static final IColorConstant COLOR_OK = IColorConstant.GREEN;
	private static final IColorConstant COLOR_WARNING_1 = IColorConstant.YELLOW;
	private static final IColorConstant COLOR_WARNING_2 = new ColorConstant(255, 170, 0);
	private static final IColorConstant COLOR_WARNING_3 = new ColorConstant(255, 85, 0);
	private static final IColorConstant COLOR_ERROR = IColorConstant.RED;

	private static final IDecorator[] NO_DECORATORS = new IDecorator[0];

	private IDiagramTypeProvider diagramTypeProvider;
	private MonitorPortAdapter adapter = new MonitorPortAdapter(this);

	private boolean enabled = true;

	@SuppressWarnings("serial")
	private static class ColorMap extends HashMap<String, IColorConstant> {
	}

	private Map<String, ColorMap> providesPortColors = new HashMap<String, ColorMap>();

	@SuppressWarnings("serial")
	private static class ConnectionColorMap extends HashMap<String, ColorMap> {
	}

	private Map<String, ConnectionColorMap> connectionColors = new HashMap<String, ConnectionColorMap>();

	public PortMonitorDecoratorProvider(IDiagramTypeProvider diagramTypeProvider) {
		this.diagramTypeProvider = diagramTypeProvider;
		MonitorPlugin.getDefault().getMonitorRegistry().eAdapters().add(adapter);
	}

	public void dispose() {
		MonitorPlugin.getDefault().getMonitorRegistry().eAdapters().remove(adapter);
	}

	public void setEnabled(boolean state) {
		enabled = state;
	}

	protected boolean isDiagramComponent(ScaComponent component) {
		return (component.getWaveform() == DUtil.getBusinessObject(diagramTypeProvider.getDiagram(), ScaWaveform.class));
	}

	protected ProvidesPortStub getProvidesPortStub(String componentId, String portName) {
		Diagram diagram = diagramTypeProvider.getDiagram();
		SoftwareAssembly sad = DUtil.getDiagramSAD(diagram);
		SadComponentInstantiation inst = sad.getComponentInstantiation(componentId);
		for (ProvidesPortStub provides : inst.getProvides()) {
			if (portName.equals(provides.getName())) {
				return provides;
			}
		}
		return null;
	}

	protected boolean isUsesPortMatch(SadUsesPort usesPort, String componentId, String portName) {
		return usesPort.getComponentInstantiationRef().getRefid().equals(componentId) && usesPort.getUsesIdentifier().equals(portName);
	}

	protected SadConnectInterface getSadConnection(String componentId, String portName, String connectionId) {
		Diagram diagram = diagramTypeProvider.getDiagram();
		SoftwareAssembly sad = DUtil.getDiagramSAD(diagram);
		for (SadConnectInterface connectInterface : sad.getConnections().getConnectInterface()) {
			if (connectInterface.getId().equals(connectionId) && isUsesPortMatch(connectInterface.getUsesPort(), componentId, portName)) {
				return connectInterface;
			}
		}
		return null;
	}

	private void putProvidesColor(String instantiationId, String portName, IColorConstant color) {
		synchronized (providesPortColors) {
			ColorMap portColors = providesPortColors.get(instantiationId);
			if (portColors == null) {
				portColors = new ColorMap();
				providesPortColors.put(instantiationId, portColors);
			}
			portColors.put(portName, color);
		}
	}

	private boolean removeProvidesColor(String instantiationId, String portName) {
		synchronized (providesPortColors) {
			ColorMap portColors = providesPortColors.get(instantiationId);
			if (portColors == null) {
				return false;
			} else {
				return portColors.remove(portName) != null;
			}
		}
	}

	private IColorConstant getProvidesColor(String instantiationId, String portName) {
		synchronized (providesPortColors) {
			ColorMap portColors = providesPortColors.get(instantiationId);
			if (portColors == null) {
				return null;
			} else {
				return portColors.get(portName);
			}
		}
	}

	private void putConnectionColor(String instantiationId, String portName, String connectionId, IColorConstant color) {
		synchronized (connectionColors) {
			ConnectionColorMap portMap = connectionColors.get(instantiationId);
			if (portMap == null) {
				portMap = new ConnectionColorMap();
				connectionColors.put(instantiationId, portMap);
			}
			ColorMap connectionMap = portMap.get(portName);
			if (connectionMap == null) {
				connectionMap = new ColorMap();
				portMap.put(portName, connectionMap);
			}
			connectionMap.put(connectionId, color);
		}
	}

	private boolean removeConnectionColor(String instantiationId, String portName, String connectionId) {
		synchronized (connectionColors) {
			ConnectionColorMap portMap = connectionColors.get(instantiationId);
			if (portMap != null) {
				ColorMap colorMap = portMap.get(portName);
				if (colorMap != null) {
					return colorMap.remove(connectionId) != null;
				}
			}
			return false;
		}
	}

	private IColorConstant getConnectionColor(String instantiationId, String portName, String connectionId) {
		synchronized (connectionColors) {
			ConnectionColorMap portMap = connectionColors.get(instantiationId);
			if (portMap != null) {
				ColorMap colorMap = portMap.get(portName);
				if (colorMap != null) {
					return colorMap.get(connectionId);
				}
			}
			return null;
		}
	}

	@Override
	public void newStatistics(ScaPort< ? , ? > port, PortStatistics portStatistics) {
		ScaPortContainer container = port.getPortContainer();
		if (container instanceof ScaComponent) {
			ScaComponent component = (ScaComponent) container;
			if (isDiagramComponent(component)) {
				IColorConstant color = getProvidesMonitorColor(portStatistics);
				String instantiationId = component.getInstantiationIdentifier();
				String portName = port.getName();
				putProvidesColor(instantiationId, portName, color);
				ProvidesPortStub provides = getProvidesPortStub(instantiationId, portName);
				refreshDecorators(provides);
			}
		}
	}

	private void refreshDecorators(final EObject object) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				Diagram diagram = diagramTypeProvider.getDiagram();
				for (PictogramElement pe : Graphiti.getLinkService().getPictogramElements(diagram, object)) {
					diagramTypeProvider.getDiagramBehavior().refreshRenderingDecorators(pe);
				}
			}
		});
	}

	@Override
	public void newStatistics(ScaPort< ? , ? > port, String connectionId, PortStatistics portStatistics) {
		ScaPortContainer container = port.getPortContainer();
		if (container instanceof ScaComponent) {
			ScaComponent component = (ScaComponent) container;
			if (isDiagramComponent(component)) {
				IColorConstant color = getConnectionMonitorColor(portStatistics);
				String instantiationId = component.getInstantiationIdentifier();
				String portName = port.getName();
				putConnectionColor(instantiationId, portName, connectionId, color);
				SadConnectInterface connectInterface = getSadConnection(instantiationId, portName, connectionId);
				refreshDecorators(connectInterface);
			}
		}
	}

	@Override
	public void noStatistics(ScaPort< ? , ? > port) {
		ScaPortContainer container = port.getPortContainer();
		if (container instanceof ScaComponent) {
			ScaComponent component = (ScaComponent) container;
			if (isDiagramComponent(component)) {
				String instantiationId = component.getInstantiationIdentifier();
				String portName = port.getName();
				if (removeProvidesColor(instantiationId, portName)) {
					ProvidesPortStub provides = getProvidesPortStub(instantiationId, portName);
					refreshDecorators(provides);
				}
			}
		}
	}

	@Override
	public void noStatistics(ScaPort< ? , ? > port, String connectionId) {
		ScaPortContainer container = port.getPortContainer();
		if (container instanceof ScaComponent) {
			ScaComponent component = (ScaComponent) container;
			if (isDiagramComponent(component)) {
				String instantiationId = component.getInstantiationIdentifier();
				String portName = port.getName();
				removeConnectionColor(instantiationId, portName, connectionId);
				SadConnectInterface connectInterface = getSadConnection(instantiationId, portName, connectionId);
				refreshDecorators(connectInterface);
			}
		}
	}

	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		if (enabled) {
			if ((pe instanceof AnchorContainer) && !((AnchorContainer) pe).getAnchors().isEmpty()) {
				ProvidesPortStub portStub = getProvidesPort(pe);
				if (portStub != null) {
					RHContainerShape componentShape = ScaEcoreUtils.getEContainerOfType(pe, RHContainerShape.class);
					if (portStub.getProvides() != null && componentShape != null) {
						ComponentInstantiation componentInstantiation = DUtil.getBusinessObject(componentShape, ComponentInstantiation.class);
						String portName = portStub.getProvides().getName();
						IDecorator decorator = getProvidesPortDecorator(componentInstantiation, portName);
						if (decorator != null) {
							return new IDecorator[] { decorator };
						}
					}
				}
			} else if (pe instanceof Connection) {
				ConnectInterface< ? , ? , ? > connectInterface = (ConnectInterface< ? , ? , ? >) Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(
					pe);
				if (connectInterface != null) {
					Connection connection = (Connection) pe;
					RHContainerShape componentShape = ScaEcoreUtils.getEContainerOfType(connection.getStart(), RHContainerShape.class);
					UsesPortStub portStub = connectInterface.getSource();
					if (portStub != null && portStub.getUses() != null && componentShape != null) {
						ComponentInstantiation componentInstantiation = DUtil.getBusinessObject(componentShape, ComponentInstantiation.class);
						String portName = portStub.getUses().getName();
						IDecorator decorator = getConnectionDecorator(componentInstantiation, portName, connectInterface.getId());
						if (decorator != null) {
							return new IDecorator[] { decorator };
						}
					}
				}
			}
		}
		return PortMonitorDecoratorProvider.NO_DECORATORS;
	}

	private ProvidesPortStub getProvidesPort(PictogramElement pe) {
		ProvidesPortStub portStub = null;
		for (EObject object : Graphiti.getLinkService().getAllBusinessObjectsForLinkedPictogramElement(pe)) {
			if (object instanceof ProvidesPortStub) {
				if (portStub == null) {
					portStub = (ProvidesPortStub) object;
				} else {
					// More than one provides port is linked with the PictogramElement, so it must be a super port
					return null;
				}
			}
		}
		return portStub;
	}

	protected IDecorator getProvidesPortDecorator(ComponentInstantiation component, String portName) {
		IColorConstant color = getProvidesColor(component.getId(), portName);
		if (color != null) {
			return new ColorDecorator(null, color);
		}
		return null;
	}

	protected IDecorator getConnectionDecorator(ComponentInstantiation component, String portName, String connectionId) {
		IColorConstant color = getConnectionColor(component.getId(), portName, connectionId);
		if (color != null) {
			return new ColorDecorator(color, color);
		}
		return null;
	}

	protected IColorConstant getProvidesMonitorColor(BULKIO.PortStatistics statistics) {
		IPreferenceStore store = new ScopedPreferenceStore(InstanceScope.INSTANCE, GraphitiUIPlugin.PLUGIN_ID);
		double queueDepthWarningLevel = store.getDouble(DiagramPreferenceConstants.PREF_PORT_STATISTICS_QUEUE_LEVEL) / 100;
		double queueDepthIncrement = (1.0 - queueDepthWarningLevel) / 4;
		double lastFlushResetTime = store.getDouble(DiagramPreferenceConstants.PREF_PORT_STATISTICS_QUEUE_FLUSH_DISPLAY);
		double lastFlush = getLastFlushTime(statistics.keywords);

		if (lastFlush < lastFlushResetTime || (lastFlush != Double.MAX_VALUE && lastFlushResetTime < 0)) {
			// If last flush reset time is set to -1, never reset the color if a flush has occurred
			return PortMonitorDecoratorProvider.COLOR_ERROR;
		} else if (statistics.averageQueueDepth < queueDepthWarningLevel) {
			return PortMonitorDecoratorProvider.COLOR_OK;
		} else if (statistics.averageQueueDepth < (queueDepthWarningLevel + queueDepthIncrement)) {
			return PortMonitorDecoratorProvider.COLOR_WARNING_1;
		} else if (statistics.averageQueueDepth < (queueDepthWarningLevel + 2 * queueDepthIncrement)) {
			return PortMonitorDecoratorProvider.COLOR_WARNING_2;
		} else if (statistics.averageQueueDepth < (queueDepthWarningLevel + 3 * queueDepthIncrement)) {
			return PortMonitorDecoratorProvider.COLOR_WARNING_3;
		} else {
			return PortMonitorDecoratorProvider.COLOR_ERROR;
		}
	}

	protected IColorConstant getConnectionMonitorColor(BULKIO.PortStatistics statistics) {
		IPreferenceStore store = new ScopedPreferenceStore(InstanceScope.INSTANCE, GraphitiUIPlugin.PLUGIN_ID);
		double lastCallWarningLevel = store.getDouble(DiagramPreferenceConstants.PREF_PORT_STATISTICS_NO_DATA_PUSHED_SECONDS);
		if (statistics.timeSinceLastCall < lastCallWarningLevel) {
			return PortMonitorDecoratorProvider.COLOR_OK;
		} else {
			return PortMonitorDecoratorProvider.COLOR_WARNING_1;
		}
	}

	private double getLastFlushTime(DataType[] keywords) {
		if (keywords != null) {
			for (DataType keyword : keywords) {
				if ("timeSinceLastFlush".equals(keyword.id)) {
					try {
						return keyword.value.extract_double();
					} catch (BAD_OPERATION e) {
						StatusManager.getManager().handle(new Status(Status.WARNING, GraphitiUIPlugin.PLUGIN_ID,
							"Expected double value for timeSinceLastFlush keyword (TCKind was " + keyword.value.type().kind() + ")", e));
					}
				}
			}
		}
		return Double.MAX_VALUE;
	}
}

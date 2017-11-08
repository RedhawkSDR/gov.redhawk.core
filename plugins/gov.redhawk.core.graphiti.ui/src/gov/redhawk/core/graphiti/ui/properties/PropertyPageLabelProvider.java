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
package gov.redhawk.core.graphiti.ui.properties;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;

import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaConnection;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.dcd.provider.DcdItemProviderAdapterFactory;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.partitioning.provider.PartitioningItemProviderAdapterFactory;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.sad.provider.SadItemProviderAdapterFactory;

public class PropertyPageLabelProvider extends AdapterFactoryLabelProvider {

	public PropertyPageLabelProvider() {
		super(new ComposedAdapterFactory(new AdapterFactory[] { new ScaItemProviderAdapterFactory(), new SadItemProviderAdapterFactory(),
			new DcdItemProviderAdapterFactory(), new PartitioningItemProviderAdapterFactory() }));
	}

	@Override
	public Image getImage(Object element) {
		element = unwrap(element);
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		element = unwrap(element);
		return super.getText(element);
	}

	private Object unwrap(Object element) {
		if (element instanceof IStructuredSelection) {
			element = ((IStructuredSelection) element).getFirstElement();
		}
		if (element instanceof AbstractEditPart) {
			element = ((AbstractEditPart) element).getModel();
		}
		if (element instanceof Diagram) {
			return getDiagramBusinessObject(element);
		} else if (element instanceof Connection) {
			Connection graphitiConnection = (Connection) element;
			ConnectInterface< ? , ? , ? > connectInterface = DUtil.getBusinessObject(graphitiConnection, ConnectInterface.class);
			EObject container = graphitiConnection.eContainer();

			if ((container instanceof Diagram)) {
				ScaWaveform waveform = DUtil.getBusinessObject((Diagram) container, ScaWaveform.class);
				if (waveform != null) {
					return getScaConnection(waveform, connectInterface);
				}

				ScaDeviceManager deviceManager = DUtil.getBusinessObject((Diagram) container, ScaDeviceManager.class);
				if (deviceManager != null) {
					return getScaConnection(deviceManager, connectInterface);
				}

				return connectInterface;
			}
		} else if (element instanceof PictogramElement) {
			element = ((PictogramElement) element).getLink().getBusinessObjects().get(0);
		}

		return element;
	}

	/**
	 * @return Either the {@link ScaWaveform} or the {@link ScaDeviceManager} as appropriate
	 */
	private Object getDiagramBusinessObject(Object element) {
		ScaWaveform waveform = DUtil.getBusinessObject((Diagram) element, ScaWaveform.class);
		if (waveform != null) {
			return waveform;
		}
		ScaDeviceManager deviceManager = DUtil.getBusinessObject((Diagram) element, ScaDeviceManager.class);
		if (deviceManager != null) {
			return deviceManager;
		}
		SoftwareAssembly sad = DUtil.getBusinessObject((Diagram) element, SoftwareAssembly.class);
		if (sad != null) {
			return sad;
		}
		DeviceConfiguration dcd = DUtil.getBusinessObject((Diagram) element, DeviceConfiguration.class);
		if (dcd != null) {
			return dcd;
		}

		return null;
	}

	/**
	 * Traverse the waveform and return either a matching {@link ScaConnection} or null if one is not found
	 */
	private Object getScaConnection(ScaWaveform waveform, ConnectInterface< ? , ? , ? > connectInterface) {
		return ScaModelCommand.runExclusive(waveform, () -> {
			for (ScaComponent comp : waveform.getComponents()) {
				for (ScaPort< ? , ? > port : comp.getPorts()) {
					if (!(port instanceof ScaUsesPort)) {
						continue;
					}
					for (ScaConnection connection : ((ScaUsesPort) port).getConnections()) {
						if (connection.getId().equals(connectInterface.getId())) {
							return connection;
						}
					}
				}
			}
			return null;
		});
	}

	/**
	 * Traverse the device manager and return either a matching {@link ScaConnection} or null if one is not found
	 */
	private Object getScaConnection(ScaDeviceManager deviceManager, ConnectInterface< ? , ? , ? > connectInterface) {
		return ScaModelCommand.runExclusive(deviceManager, () -> {
			for (ScaDevice< ? > device : deviceManager.getRootDevices()) {
				for (ScaPort< ? , ? > port : device.getPorts()) {
					if (!(port instanceof ScaUsesPort)) {
						continue;
					}
					for (ScaConnection connection : ((ScaUsesPort) port).getConnections()) {
						if (connection.getId().equals(connectInterface.getId())) {
							return connection;
						}
					}
				}
			}
			for (ScaService service : deviceManager.getServices()) {
				for (ScaPort< ? , ? > port : service.getPorts()) {
					if (!(port instanceof ScaUsesPort)) {
						continue;
					}
					for (ScaConnection connection : ((ScaUsesPort) port).getConnections()) {
						if (connection.getId().equals(connectInterface.getId())) {
							return connection;
						}
					}
				}
			}
			return null;
		});
	}
}

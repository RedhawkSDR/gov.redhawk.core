/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.core.graphiti.ui.adapters;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.platform.GraphitiConnectionEditPart;

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
import mil.jpeojtrs.sca.dcd.DcdConnectInterface;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.sad.SadConnectInterface;

/**
 * @since 2.1
 */
public class ConnectionAdapterFactory implements IAdapterFactory {

	private static final Class< ? >[] ADAPTER_TYPES = new Class< ? >[] { ScaConnection.class, ConnectInterface.class };

	@Override
	public < T > T getAdapter(Object adaptableObject, Class<T> adapterType) {
		GraphitiConnectionEditPart connectionEditPart = (GraphitiConnectionEditPart) adaptableObject;

		if (!(connectionEditPart.getModel() instanceof Connection)) {
			return null;
		}
		Connection peConnection = (Connection) connectionEditPart.getPictogramElement();

		ConnectInterface< ? , ? , ? > connectInterface = DUtil.getBusinessObject(peConnection, ConnectInterface.class);
		if (adapterType.isInstance(connectInterface)) {
			return adapterType.cast(connectInterface);
		}

		EObject container = peConnection.eContainer();
		if (!(container instanceof Diagram)) {
			return null;
		}

		ScaWaveform waveform = DUtil.getBusinessObject((Diagram) container, ScaWaveform.class);
		if (waveform != null) {
			return getScaConnection(waveform, peConnection, adapterType);
		}

		ScaDeviceManager deviceManager = DUtil.getBusinessObject((Diagram) container, ScaDeviceManager.class);
		if (deviceManager != null) {
			return getScaConnection(deviceManager, peConnection, adapterType);
		}

		return null;
	}

	/**
	 * Traverse the waveform and return either a matching {@link ScaConnection} or null if one is not found
	 */
	private < T > T getScaConnection(ScaWaveform waveform, Connection peConnection, Class<T> adapterType) {
		return ScaModelCommand.runExclusive(waveform, () -> {
			for (ScaComponent comp : waveform.getComponents()) {
				for (ScaPort< ? , ? > port : comp.getPorts()) {
					if (!(port instanceof ScaUsesPort)) {
						continue;
					}
					for (ScaConnection scaConnection : ((ScaUsesPort) port).getConnections()) {
						if (scaConnection.getId().equals(DUtil.getBusinessObject(peConnection, SadConnectInterface.class).getId())) {
							if (adapterType.isInstance(scaConnection)) {
								return adapterType.cast(scaConnection);
							}
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
	private < T > T getScaConnection(ScaDeviceManager deviceManager, Connection peConnection, Class<T> adapterType) {
		ScaConnection scaConnection = ScaModelCommand.runExclusive(deviceManager, () -> {
			for (ScaDevice< ? > device : deviceManager.getRootDevices()) {
				for (ScaPort< ? , ? > port : device.getPorts()) {
					if (!(port instanceof ScaUsesPort)) {
						continue;
					}
					for (ScaConnection connection : ((ScaUsesPort) port).getConnections()) {
						if (connection.getId().equals(DUtil.getBusinessObject(peConnection, DcdConnectInterface.class).getId())) {
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
						if (connection.getId().equals(DUtil.getBusinessObject(peConnection, DcdConnectInterface.class).getId())) {
							return connection;
						}
					}
				}
			}
			return null;
		});

		if (adapterType.isInstance(scaConnection)) {
			return adapterType.cast(scaConnection);
		}
		return null;
	}

	@Override
	public Class< ? >[] getAdapterList() {
		return ADAPTER_TYPES;
	}

}

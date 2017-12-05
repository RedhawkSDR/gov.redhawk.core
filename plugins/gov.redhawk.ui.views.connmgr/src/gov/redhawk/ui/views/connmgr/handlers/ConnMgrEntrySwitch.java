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
package gov.redhawk.ui.views.connmgr.handlers;

import org.eclipse.emf.ecore.EObject;

import CF.Device;
import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaEventChannel;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.util.ScaSwitch;

/**
 * A switch for finding an SCA model object that corresponds to an endpoint in a connection manager status entry. The
 * switch checks the object and its ports for a match. The return value is the endpoint if found, otherwise null.
 */
public class ConnMgrEntrySwitch extends ScaSwitch<CorbaObjWrapper< ? >> {

	private String entityName;
	private String portName;
	private String endpointIor;

	/**
	 * @param entityName The entity name (usually the object whose port is being used for the connection)
	 * @param portName The port's name
	 * @param endpointIor The endpoint's IOR
	 */
	public ConnMgrEntrySwitch(String entityName, String portName, String endpointIor) {
		this.entityName = entityName;
		this.portName = portName;
		this.endpointIor = endpointIor;
	}

	@Override
	public CorbaObjWrapper< ? > caseScaDomainManager(ScaDomainManager object) {
		// Connection to the domain manager
		if (endpointIor.equals(object.getIor())) {
			return object;
		}

		return null;
	}

	@Override
	public CorbaObjWrapper< ? > caseScaDeviceManager(ScaDeviceManager object) {
		// CF doesn't support device manager endpoints (see control/sdr/dommgr/ConnectionManager.cpp)
		return null;
	}

	@Override
	public < D extends Device > CorbaObjWrapper< ? > caseScaDevice(ScaDevice<D> object) {
		// Connection to device
		if (portName.isEmpty() && endpointIor.equals(object.getIor())) {
			return object;
		}

		// Connection device's port
		if (entityName.equals(object.getIdentifier())) {
			ScaPort< ? , ? > port = object.getScaPort(portName);
			if (port != null && endpointIor.equals(port.getIor())) {
				return port;
			}
		}

		return null;
	}

	@Override
	public CorbaObjWrapper< ? > caseScaWaveform(ScaWaveform object) {
		// Connection to waveform
		if (portName.isEmpty() && endpointIor.equals(object.getIor())) {
			return object;
		}

		// Connection to waveform's port
		if (entityName.equals(object.getIdentifier())) {
			ScaPort< ? , ? > port = object.getScaPort(portName);
			if (port != null && endpointIor.equals(port.getIor())) {
				return port;
			}
		}

		return null;
	}

	@Override
	public CorbaObjWrapper< ? > caseScaComponent(ScaComponent object) {
		// Connection to component
		if (portName.isEmpty() && endpointIor.equals(object.getIor())) {
			return object;
		}

		// Connection components's port
		if (entityName.equals(object.getIdentifier())) {
			ScaPort< ? , ? > port = object.getScaPort(portName);
			if (port != null && endpointIor.equals(port.getIor())) {
				return port;
			}
		}

		return null;
	}

	@Override
	public CorbaObjWrapper< ? > caseScaService(ScaService object) {
		// Connection to service
		if (endpointIor.equals(object.getIor())) {
			return object;
		}

		return null;
	}

	@Override
	public CorbaObjWrapper< ? > caseScaEventChannel(ScaEventChannel object) {
		// Connection to event channel
		if (portName.isEmpty() && endpointIor.equals(object.getIor())) {
			return object;
		}

		return null;
	}

	public CorbaObjWrapper< ? > defaultCase(EObject eObject) {
		return null;
	}
}

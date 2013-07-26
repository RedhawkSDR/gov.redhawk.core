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
package gov.redhawk.model.sca.commands;

import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaUsesPort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.scd.AbstractPort;
import mil.jpeojtrs.sca.scd.Provides;
import mil.jpeojtrs.sca.scd.Uses;

import org.eclipse.core.runtime.IStatus;
import org.omg.CORBA.Object;

/**
 * @since 14.0
 */
public class MergePortsCommand extends SetStatusCommand<ScaPortContainer> {

	public static class PortData {
		private final AbstractPort portProfile;
		private final org.omg.CORBA.Object portObj;
		private String portName;
		
		public PortData(AbstractPort portProfile, Object portObj) {
			this(portProfile.getName(), portProfile, portObj);
		}

		/**
		 * @since 18.0
		 */
		public PortData(String portName, AbstractPort portProfile, Object portObj) {
			super();
			this.portProfile = portProfile;
			this.portObj = portObj;
			this.portName = portName;
		}

		public AbstractPort getPortProfile() {
			return portProfile;
		}

		public org.omg.CORBA.Object getPortObj() {
			return portObj;
		}
	}
	
	private static String buildId(AbstractPort port, String portName) {
		return port.getClass().getName() + ":" + portName;
	}

	private List<PortData> newPorts;

	public MergePortsCommand(ScaPortContainer portContainer, List<PortData> newPorts, IStatus status) {
		super(portContainer, ScaPackage.Literals.SCA_PORT_CONTAINER__PORTS, status);
		this.newPorts = newPorts;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		if (status.isOK()) {
			Map<String, ScaPort< ? , ? >> currentMap = new HashMap<String, ScaPort< ? , ? >>();
			for (ScaPort< ? , ? > port : provider.getPorts()) {
				currentMap.put(buildId(port.getProfileObj(), port.getName()), port);
			}

			Map<String, ScaPort< ? , ? >> removeMap = new HashMap<String, ScaPort< ? , ? >>();
			removeMap.putAll(currentMap);

			Map<String, PortData> newPortMap = new HashMap<String, MergePortsCommand.PortData>();
			for (PortData data : newPorts) {
				newPortMap.put(buildId(data.portProfile, data.portName), data);
			}

			// Remove stale ports
			removeMap.keySet().removeAll(newPortMap.keySet());
			if (!removeMap.isEmpty() && !provider.getPorts().isEmpty()) {
				provider.getPorts().removeAll(removeMap.values());
			}

			// Remove duplicate ports
			newPortMap.keySet().removeAll(currentMap.keySet());

			for (PortData data : newPortMap.values()) {
				ScaPort< ? , ? > newPort = null;
				if (data.portProfile instanceof Provides) {
					ScaProvidesPort tmpPort = ScaFactory.eINSTANCE.createScaProvidesPort();
					tmpPort.setProfileObj((Provides) data.portProfile);
					newPort = tmpPort;
				} else if (data.portProfile instanceof Uses) {
					ScaUsesPort tmpPort = ScaFactory.eINSTANCE.createScaUsesPort();
					tmpPort.setProfileObj((Uses) data.portProfile);
					newPort = tmpPort;
				}
				if (newPort != null) {
					newPort.setCorbaObj(data.portObj);
					newPort.setName(data.portName);
					provider.getPorts().add(newPort);
				}
			}
			
			if (!provider.isSetPorts()) {
				provider.getPorts().clear();
			}
		} else {
			provider.unsetPorts();
		}
		super.execute();
	}

}

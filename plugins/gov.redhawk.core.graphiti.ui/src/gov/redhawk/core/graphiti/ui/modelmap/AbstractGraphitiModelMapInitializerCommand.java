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
package gov.redhawk.core.graphiti.ui.modelmap;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import gov.redhawk.model.sca.ScaConnection;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.sca.util.PluginUtil;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;

public abstract class AbstractGraphitiModelMapInitializerCommand extends AbstractCommand {

	private AbstractGraphitiModelMap modelMap;

	public AbstractGraphitiModelMapInitializerCommand(AbstractGraphitiModelMap modelMap) {
		this.modelMap = modelMap;
	}

	/**
	 * Create XML and initialize the model map for the given runtime connection. If the target of the connection is
	 * not within the diagram, the connection should be ignored.
	 * @param connection
	 */
	protected abstract void initConnection(ScaConnection connection);

	/**
	 * Map runtime connections to XML connections, creating new XML as necessary for any dynamically created
	 * connections.
	 * @param livePortContainers The set of port containers to examine for runtime connections
	 * @param xmlConnections The list of XML connections
	 */
	protected void createConnectionMappings(List< ? extends ScaPortContainer> livePortContainers,
		List< ? extends ConnectInterface< ? , ? , ? >> xmlConnections) {
		List<ScaConnection> liveConnections = new ArrayList<>();
		for (final ScaPortContainer liveContainer : livePortContainers) {
			for (final ScaPort< ? , ? > port : liveContainer.getPorts()) {
				if (port instanceof ScaUsesPort) {
					liveConnections.addAll(((ScaUsesPort) port).getConnections());
				}
			}
		}
		for (ConnectInterface< ? , ? , ? > xmlConnection : xmlConnections) {
			ScaConnection connection = null;
			for (ScaConnection liveConnection : liveConnections) {
				if (PluginUtil.equals(liveConnection.getId(), xmlConnection.getId())) {
					liveConnections.remove(liveConnection);
					connection = liveConnection;
					break;
				}
			}

			// Put the connection <--> XML profile mapping in our map; connection may be null if a connection is
			// mentioned in the XML but missing in the live waveform
			modelMap.put(connection, xmlConnection);
		}

		// Create XML for any live connections we can't find XML for
		for (final ScaConnection liveConnection : liveConnections) {
			initConnection(liveConnection);
		}
	}

}

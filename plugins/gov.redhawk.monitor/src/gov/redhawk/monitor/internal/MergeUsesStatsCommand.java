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
package gov.redhawk.monitor.internal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import BULKIO.UsesPortStatistics;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.monitor.model.ports.PortConnectionMonitor;
import gov.redhawk.monitor.model.ports.PortMonitor;
import gov.redhawk.monitor.model.ports.PortsFactory;

public class MergeUsesStatsCommand extends ScaModelCommand {
	
	private PortMonitor usesPort;
	private UsesPortStatistics[] newStats;

	public MergeUsesStatsCommand(PortMonitor usesPort, UsesPortStatistics[] newStats) {
		this.usesPort = usesPort;
		this.newStats = newStats;
	}

	@Override
	public void execute() {
		// Create a list of all connection we currently have. Initially we'll plan to remove all existing connections.
		final Map<String, PortConnectionMonitor> currentMap = new HashMap<String, PortConnectionMonitor>();
		for (final PortConnectionMonitor pcm : usesPort.getConnections()) {
			currentMap.put(pcm.getConnectionId(), pcm);
		}
		final Map<String, PortConnectionMonitor> toRemove = new HashMap<String, PortConnectionMonitor>();
		toRemove.putAll(currentMap);

		// Update each connection's statistics, creating connections if necessary. Compile a set of all the connection IDs. 
		final Set<String> newStatsMap = new HashSet<String>();
		for (final UsesPortStatistics stat : newStats) {
			PortConnectionMonitor pcm = currentMap.get(stat.connectionId);
			if (pcm == null) {
				pcm = PortsFactory.eINSTANCE.createPortConnectionMonitor();
				pcm.setConnectionId(stat.connectionId);
				usesPort.getConnections().add(pcm);
			}
			pcm.setData(stat.statistics);
			newStatsMap.add(stat.connectionId);
		}

		// Eliminate all connection IDs we saw from the removal list, then drop all those connections
		toRemove.keySet().removeAll(newStatsMap);
		usesPort.getConnections().removeAll(toRemove.values());

	}

}

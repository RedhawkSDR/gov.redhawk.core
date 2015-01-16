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
package gov.redhawk.monitor;

import gov.redhawk.model.sca.ScaPort;
import BULKIO.PortStatistics;

public interface IPortStatListener {

	/**
	 * Provides notification of new/updated statistics for a port (only provides ports)
	 * 
	 * @param port The port statistics were received for
	 * @param portStatistics The latest statistics for the port.
	 * @see PortStatistics#streamIDs
	 */
	void newStatistics(ScaPort< ? , ? > port, PortStatistics portStatistics);

	/**
	 * Provides notification of new/updated statistics for a port's connection (only uses ports)
	 *
	 * @param port The port for the connection
	 * @param connectionId The connection statistics were received for
	 * @param portStatistics The latest statics for the connection. These are reported once per-connection.
	 * @see PortStatistics#streamIDs
	 */
	void newStatistics(ScaPort< ? , ? > port, String connectionId, PortStatistics portStatistics);

	/**
	 * Provides notification that monitoring has stopped on a port (only provides ports). No more statistics will be
	 * provided.
	 *
	 * @param port The port for which no more statistics will be provided
	 */
	void noStatistics(ScaPort< ? , ? > port);

	/**
	 * Provides notification that monitoring has stopped on a port's connection (only uses ports). No more statistics
	 * will be provided.
	 *
	 * @param port The port for the connection
	 * @param connectionId The connection that no longer has statistics
	 */
	void noStatistics(ScaPort< ? , ? > port, String connectionId);

}

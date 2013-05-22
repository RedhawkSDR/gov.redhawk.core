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
package nxm.redhawk.prim.data;

import nxm.redhawk.prim.corbareceiver;

import org.omg.PortableServer.Servant;

import BULKIO.PortStatistics;
import BULKIO.PortUsageType;
import BULKIO.ProvidesPortStatisticsProviderOperations;
import BULKIO.StreamSRI;
import BULKIO.updateSRIOperations;

/**
 * 
 */
public abstract class BaseBulkIOReceiver implements ProvidesPortStatisticsProviderOperations, updateSRIOperations {

	private final corbareceiver receiver;

	public BaseBulkIOReceiver(final corbareceiver receiver) {
		this.receiver = receiver;
	}

	public abstract char getType();

	public abstract Servant toServant();

	public corbareceiver getReceiver() {
		return this.receiver;
	}

	public StreamSRI[] activeSRIs() {
		return this.receiver.activeSRIs();
	}

	public void pushSRI(final StreamSRI sri) {
		this.receiver.pushSRI(sri);
	}

	public PortUsageType state() {
		return this.receiver.state();
	}

	public PortStatistics statistics() {
		return this.receiver.statistics();
	}

}

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
package gov.redhawk.model.sca.tests.stubs;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.AggregateDevice;
import CF.DataType;
import CF.DeviceOperations;
import CF.DevicePackage.AdminType;
import CF.DevicePackage.InsufficientCapacity;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;
import CF.DevicePackage.OperationalType;
import CF.DevicePackage.UsageType;

public class AbstractDeviceImpl extends AbstractResourceImpl implements
		DeviceOperations {

	private OperationalType opState = OperationalType.ENABLED;
	private AdminType adminState = AdminType.LOCKED;
	private UsageType usageState = UsageType.IDLE;

	public AbstractDeviceImpl() {
		super();
	}

	public AbstractDeviceImpl(String compId, String compName, ORB orb, POA poa)
			throws ServantNotActive, WrongPolicy {
		super(compId, compName, orb, poa);
	}

	public UsageType usageState() {
		return this.usageState;
	}

	public AdminType adminState() {
		return this.adminState;
	}

	public void adminState(AdminType newAdminState) {
		this.adminState = newAdminState;
	}

	public OperationalType operationalState() {
		return this.opState;
	}

	public String softwareProfile() {
		return this.spd.eResource().getURI().path();
	}

	public String label() {
		return this.compName;
	}

	public AggregateDevice compositeDevice() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean allocateCapacity(DataType[] capacities)
			throws InvalidCapacity, InvalidState, InsufficientCapacity {
		// TODO Auto-generated method stub
		return false;
	}

	public void deallocateCapacity(DataType[] capacities)
			throws InvalidCapacity, InvalidState {
		// TODO Auto-generated method stub

	}

}

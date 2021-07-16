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

import org.eclipse.core.runtime.CoreException;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.AggregateDevice;
import CF.DataType;
import CF.DeviceOperations;
import CF.DevicePackage.AdminType;
import CF.DevicePackage.Allocation;
import CF.DevicePackage.InsufficientCapacity;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;
import CF.DevicePackage.OperationalType;
import CF.DevicePackage.UsageType;
import gov.redhawk.sca.util.OrbSession;

public class AbstractDeviceImpl extends AbstractResourceImpl implements
		DeviceOperations {

	private OperationalType opState = OperationalType.ENABLED;
	private AdminType adminState = AdminType.LOCKED;
	private UsageType usageState = UsageType.IDLE;

	public AbstractDeviceImpl() {
		super();
	}

	public AbstractDeviceImpl(String compId, String compName, String profile, OrbSession session)
			throws ServantNotActive, WrongPolicy, CoreException {
		super(compId, compName, profile, session);
	}

	@Override
	public UsageType usageState() {
		return this.usageState;
	}

	@Override
	public AdminType adminState() {
		return this.adminState;
	}

	@Override
	public void adminState(AdminType newAdminState) {
		this.adminState = newAdminState;
	}

	@Override
	public OperationalType operationalState() {
		return this.opState;
	}

	@Override
	public String softwareProfile() {
		return this.spd.eResource().getURI().path();
	}

	@Override
	public String label() {
		return this.compName;
	}

	@Override
	public AggregateDevice compositeDevice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean allocateCapacity(DataType[] capacities)
			throws InvalidCapacity, InvalidState, InsufficientCapacity {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deallocateCapacity(DataType[] capacities)
			throws InvalidCapacity, InvalidState {
		// TODO Auto-generated method stub

	}
	
	       
	@Override
	public Allocation[] allocate(DataType[] capacities) throws InvalidCapacity, InvalidState, InsufficientCapacity {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void deallocate(String allocationId) throws InvalidCapacity, InvalidState {
		// TODO Auto-generated method stub
	}


}

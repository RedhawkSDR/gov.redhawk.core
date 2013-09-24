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

import CF.DataType;
import CF.ExecutableDeviceOperations;
import CF.InvalidFileName;
import CF.DevicePackage.InvalidState;
import CF.ExecutableDevicePackage.ExecuteFail;
import CF.ExecutableDevicePackage.InvalidFunction;
import CF.ExecutableDevicePackage.InvalidOptions;
import CF.ExecutableDevicePackage.InvalidParameters;
import CF.ExecutableDevicePackage.InvalidProcess;

public class AbstractExecutableDeviceImpl extends AbstractLoadableDeviceImpl
		implements ExecutableDeviceOperations {

	public AbstractExecutableDeviceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AbstractExecutableDeviceImpl(String compId, String compName,
			ORB orb, POA poa) throws ServantNotActive, WrongPolicy {
		super(compId, compName, orb, poa);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void terminate(int processId) throws InvalidProcess, InvalidState {
		// TODO Auto-generated method stub

	}

	@Override
	public int execute(String name, DataType[] options, DataType[] parameters)
			throws InvalidState, InvalidFunction, InvalidParameters,
			InvalidOptions, InvalidFileName, ExecuteFail {
		// TODO Auto-generated method stub
		return 0;
	}

}

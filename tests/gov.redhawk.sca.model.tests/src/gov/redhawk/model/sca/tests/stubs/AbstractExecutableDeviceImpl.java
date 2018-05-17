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

import CF.DataType;
import CF.ExecutableDeviceOperations;
import CF.InvalidFileName;
import CF.DevicePackage.InvalidState;
import CF.ExecutableDevicePackage.ExecuteFail;
import CF.ExecutableDevicePackage.InvalidFunction;
import CF.ExecutableDevicePackage.InvalidOptions;
import CF.ExecutableDevicePackage.InvalidParameters;
import CF.ExecutableDevicePackage.InvalidProcess;
import gov.redhawk.sca.util.OrbSession;

public class AbstractExecutableDeviceImpl extends AbstractLoadableDeviceImpl implements ExecutableDeviceOperations {

	public AbstractExecutableDeviceImpl() {
		super();
	}

	public AbstractExecutableDeviceImpl(String compId, String compName, String profile, OrbSession session) throws ServantNotActive, WrongPolicy, CoreException {
		super(compId, compName, profile, session);
	}

	@Override
	public void terminate(int processId) throws InvalidProcess, InvalidState {
		// TODO Auto-generated method stub
	}

	@Override
	public int execute(String name, DataType[] options, DataType[] parameters)
		throws InvalidState, InvalidFunction, InvalidParameters, InvalidOptions, InvalidFileName, ExecuteFail {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeLinked(String name, DataType[] options, DataType[] parameters, String[] deps)
		throws InvalidState, InvalidFunction, InvalidParameters, InvalidOptions, InvalidFileName, ExecuteFail {
		// TODO Auto-generated method stub
		return 0;
	}

}

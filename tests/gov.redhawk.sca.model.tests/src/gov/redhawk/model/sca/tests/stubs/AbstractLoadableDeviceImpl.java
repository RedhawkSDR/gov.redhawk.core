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

import CF.FileSystem;
import CF.InvalidFileName;
import CF.LoadableDeviceOperations;
import CF.DevicePackage.InvalidState;
import CF.LoadableDevicePackage.InvalidLoadKind;
import CF.LoadableDevicePackage.LoadFail;
import CF.LoadableDevicePackage.LoadType;
import gov.redhawk.sca.util.OrbSession;

public class AbstractLoadableDeviceImpl extends AbstractDeviceImpl implements LoadableDeviceOperations {

	public AbstractLoadableDeviceImpl() {
		super();
	}

	public AbstractLoadableDeviceImpl(String compId, String compName, String profile, OrbSession session) throws ServantNotActive, WrongPolicy, CoreException {
		super(compId, compName, profile, session);
	}

	@Override
	public void load(FileSystem fs, String fileName, LoadType loadKind) throws InvalidState, InvalidLoadKind, InvalidFileName, LoadFail {
		// TODO Auto-generated method stub
	}

	@Override
	public void unload(String fileName) throws InvalidState, InvalidFileName {
		// TODO Auto-generated method stub
	}

}

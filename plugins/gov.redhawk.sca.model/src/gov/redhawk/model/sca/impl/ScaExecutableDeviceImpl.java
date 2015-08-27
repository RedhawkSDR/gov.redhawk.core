/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

// BEGIN GENERATED CODE
package gov.redhawk.model.sca.impl;

import gov.redhawk.model.sca.ScaExecutableDevice;
import gov.redhawk.model.sca.ScaPackage;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import CF.DataType;
import CF.ExecutableDevice;
import CF.ExecutableDeviceHelper;
import CF.InvalidFileName;
import CF.DevicePackage.InvalidState;
import CF.ExecutableDevicePackage.ExecuteFail;
import CF.ExecutableDevicePackage.InvalidFunction;
import CF.ExecutableDevicePackage.InvalidOptions;
import CF.ExecutableDevicePackage.InvalidParameters;
import CF.ExecutableDevicePackage.InvalidProcess;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Executable Device</b></em>'.
 * 
 * @since 12.0
 *        <!-- end-user-doc -->
 *
 * @generated
 */
public class ScaExecutableDeviceImpl extends ScaLoadableDeviceImpl<ExecutableDevice>implements ScaExecutableDevice {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	protected ScaExecutableDeviceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_EXECUTABLE_DEVICE;
	}

	/**
	 * @since 14.0
	 */
	@Override
	protected ExecutableDevice narrow(final org.omg.CORBA.Object obj) {
		// END GENERATED CODE
		return ExecutableDeviceHelper.narrow(obj);
		// BEGIN GENERATED CODE
	}

	@Override
	protected Class<ExecutableDevice> getCorbaType() {
		return ExecutableDevice.class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ExecutableDevice getExecutableDev() {
		// END GENERATED CODE
		return fetchNarrowedObject(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	@Override
	public int execute(final String name, final DataType[] options, final DataType[] parameters)
		throws InvalidState, InvalidFunction, InvalidParameters, InvalidOptions, InvalidFileName, ExecuteFail {
		// END GENERATED CODE
		ExecutableDevice device = fetchNarrowedObject(null);
		if (device == null) {
			throw new IllegalStateException();
		}
		return device.execute(name, options, parameters);
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * This is an EMF-generated wrapper for {@link #executeLinked(String, DataType[], DataType[], String[])}.
	 * 
	 * @since 20.0
	 *        <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int executeLinked(String name, DataType[] options, DataType[] parameters, EList<String> deps)
		throws InvalidState, InvalidFunction, InvalidParameters, InvalidOptions, InvalidFileName, ExecuteFail {
		return this.executeLinked(name, options, parameters, deps.toArray(new String[deps.size()]));
	}

	/**
	 * @since 20.0
	 */
	@Override
	public int executeLinked(final String name, final DataType[] options, final DataType[] parameters, final String[] deps)
		throws InvalidState, InvalidFunction, InvalidParameters, InvalidOptions, InvalidFileName, ExecuteFail {
		// END GENERATED CODE
		ExecutableDevice device = fetchNarrowedObject(null);
		if (device == null) {
			throw new IllegalStateException();
		}
		return device.executeLinked(name, options, parameters, deps);
		// BEGIN GENERATED CODE
	}

	@Override
	public void terminate(final int processId) throws InvalidProcess, InvalidState {
		// END GENERATED CODE
		ExecutableDevice device = fetchNarrowedObject(null);
		if (device == null) {
			throw new IllegalStateException();
		}
		device.terminate(processId);
		// BEGIN GENERATED CODE
	}

} // ScaExecutableDeviceImpl

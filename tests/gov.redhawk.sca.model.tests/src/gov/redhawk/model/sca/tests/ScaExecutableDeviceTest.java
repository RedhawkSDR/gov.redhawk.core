/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

 // BEGIN GENERATED CODE
package gov.redhawk.model.sca.tests;

import gov.redhawk.model.sca.ScaExecutableDevice;
import junit.textui.TestRunner;
import CF.DataType;
import CF.InvalidFileName;
import CF.DevicePackage.InvalidState;
import CF.ExecutableDevicePackage.ExecuteFail;
import CF.ExecutableDevicePackage.InvalidFunction;
import CF.ExecutableDevicePackage.InvalidOptions;
import CF.ExecutableDevicePackage.InvalidParameters;
import CF.ExecutableDevicePackage.InvalidProcess;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Executable Device</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link CF.ExecutableDeviceOperations#terminate(int) <em>Terminate</em>}</li>
 *   <li>{@link CF.ExecutableDeviceOperations#execute(java.lang.String, CF.DataType[], CF.DataType[]) <em>Execute</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaExecutableDeviceTest extends ScaLoadableDeviceTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaExecutableDeviceTest.class);
	}

	/**
	 * Constructs a new Executable Device test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaExecutableDeviceTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Executable Device test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaExecutableDevice getFixture() {
		return (ScaExecutableDevice)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated NOT
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated NOT
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.ExecutableDeviceOperations#terminate(int) <em>Terminate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidState 
	 * @throws InvalidProcess 
	 * @see mil.jpeojtrs.sca.cf.ExecutableDeviceOperations#terminate(int)
	 * @generated NOT
	 */
	public void testTerminate__int() throws InvalidProcess, InvalidState {
		// END GENERATED CODE
		getFixture().terminate(0);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.ExecutableDeviceOperations#execute(java.lang.String, mil.jpeojtrs.sca.cf.DataType[], mil.jpeojtrs.sca.cf.DataType[]) <em>Execute</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws ExecuteFail 
	 * @throws InvalidFileName 
	 * @throws InvalidOptions 
	 * @throws InvalidParameters 
	 * @throws InvalidFunction 
	 * @throws InvalidState 
	 * @see mil.jpeojtrs.sca.cf.ExecutableDeviceOperations#execute(java.lang.String, mil.jpeojtrs.sca.cf.DataType[], mil.jpeojtrs.sca.cf.DataType[])
	 * @generated NOT
	 */
	public void testExecute__String_DataType_DataType() throws InvalidState, InvalidFunction, InvalidParameters, InvalidOptions, InvalidFileName, ExecuteFail {
		// END GENERATED CODE
		getFixture().execute("", new DataType[0], new DataType[0]);
		// BEGIN GENERATED CODE
	}

} //ScaExecutableDeviceTest

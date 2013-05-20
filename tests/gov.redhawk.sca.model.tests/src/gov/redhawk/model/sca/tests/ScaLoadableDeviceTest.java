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
package gov.redhawk.model.sca.tests;

import gov.redhawk.model.sca.ScaLoadableDevice;
import junit.textui.TestRunner;
import CF.InvalidFileName;
import CF.DevicePackage.InvalidState;
import CF.LoadableDevicePackage.InvalidLoadKind;
import CF.LoadableDevicePackage.LoadFail;
import CF.LoadableDevicePackage.LoadType;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Loadable Device</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link CF.LoadableDeviceOperations#load(CF.FileSystem, java.lang.String, CF.LoadableDevicePackage.LoadType) <em>Load</em>}</li>
 *   <li>{@link CF.LoadableDeviceOperations#unload(java.lang.String) <em>Unload</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaLoadableDeviceTest extends ScaDeviceTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaLoadableDeviceTest.class);
	}

	/**
	 * Constructs a new Loadable Device test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaLoadableDeviceTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Loadable Device test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaLoadableDevice<?> getFixture() {
		return (ScaLoadableDevice<?>)fixture;
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
	 * Tests the '{@link mil.jpeojtrs.sca.cf.LoadableDeviceOperations#load(mil.jpeojtrs.sca.cf.FileSystem, java.lang.String, mil.jpeojtrs.sca.cf.LoadableDevicePackage.LoadType) <em>Load</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws LoadFail 
	 * @throws InvalidFileName 
	 * @throws InvalidLoadKind 
	 * @throws InvalidState 
	 * @see mil.jpeojtrs.sca.cf.LoadableDeviceOperations#load(mil.jpeojtrs.sca.cf.FileSystem, java.lang.String, mil.jpeojtrs.sca.cf.LoadableDevicePackage.LoadType)
	 * @generated NOT
	 */
	public void testLoad__FileSystem_String_LoadType() throws InvalidState, InvalidLoadKind, InvalidFileName, LoadFail {
		// END GENERATED CODE
		getFixture().load(null, "", LoadType.EXECUTABLE);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.LoadableDeviceOperations#unload(java.lang.String) <em>Unload</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidFileName 
	 * @throws InvalidState 
	 * @see mil.jpeojtrs.sca.cf.LoadableDeviceOperations#unload(java.lang.String)
	 * @generated NOT
	 */
	public void testUnload__String() throws InvalidState, InvalidFileName {
		// END GENERATED CODE
		getFixture().unload("");
		// BEGIN GENERATED CODE
	}

} //ScaLoadableDeviceTest

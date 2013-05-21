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

import gov.redhawk.sca.util.Debug;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>sca</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScaTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new ScaTests("sca Tests");
		suite.addTestSuite(ScaComponentTest.class);
		suite.addTestSuite(ScaDeviceTest.class);
		suite.addTestSuite(ScaDeviceManagerTest.class);
		suite.addTestSuite(ScaServiceTest.class);
		suite.addTestSuite(ScaDeviceManagerFileSystemTest.class);
		suite.addTestSuite(ScaDocumentRootTest.class);
		suite.addTestSuite(ScaDomainManagerTest.class);
		suite.addTestSuite(ScaDomainManagerFileSystemTest.class);
		suite.addTestSuite(ScaDomainManagerRegistryTest.class);
		suite.addTestSuite(ScaExecutableDeviceTest.class);
		suite.addTestSuite(ScaFileStoreTest.class);
		suite.addTestSuite(ScaLoadableDeviceTest.class);
		suite.addTestSuite(ScaProvidesPortTest.class);
		suite.addTestSuite(ScaSimplePropertyTest.class);
		suite.addTestSuite(ScaSimpleSequencePropertyTest.class);
		suite.addTestSuite(ScaStructPropertyTest.class);
		suite.addTestSuite(ScaUsesPortTest.class);
		suite.addTestSuite(ScaConnectionTest.class);
		suite.addTestSuite(ScaWaveformTest.class);
		suite.addTestSuite(ScaWaveformFactoryTest.class);
		suite.addTestSuite(ScaStructSequencePropertyTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaTests(String name) {
		super(name);
	}

	static final Debug DEBUG = new Debug("gov.redhawk.sca.model.tests");

} //ScaTests

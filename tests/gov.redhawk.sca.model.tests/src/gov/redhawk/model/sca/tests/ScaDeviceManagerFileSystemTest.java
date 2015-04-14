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

import gov.redhawk.model.sca.ScaDeviceManagerFileSystem;
import org.junit.Assert;
import junit.textui.TestRunner;
import CF.FileSystemHelper;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Device Manager File System</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScaDeviceManagerFileSystemTest extends ScaFileSystemTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaDeviceManagerFileSystemTest.class);
	}

	/**
	 * Constructs a new Device Manager File System test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaDeviceManagerFileSystemTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Device Manager File System test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaDeviceManagerFileSystem getFixture() {
		return (ScaDeviceManagerFileSystem) fixture;
	}

	private TestEnvirornment env;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated NOT
	 */
	@Override
	protected void setUp() throws Exception {
		this.env = TestEnvirornment.getInstance();

		setFixture(this.env.getDomMgr().getDeviceManagers().get(0).getFileSystem());
		Assert.assertNotNull(getFixture());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated NOT
	 */
	@Override
	protected void tearDown() throws Exception {
		this.env = null;

		setFixture(null);
	}

	@Override
	protected String getRepId() {
		return FileSystemHelper.id();
	}

} // ScaDeviceManagerFileSystemTest

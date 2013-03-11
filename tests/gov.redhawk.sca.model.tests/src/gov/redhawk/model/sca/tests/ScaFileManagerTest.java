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

import gov.redhawk.model.sca.ScaFileManager;

import org.junit.Assert;

import CF.InvalidFileName;
import CF.FileManagerPackage.InvalidFileSystem;
import CF.FileManagerPackage.MountPointAlreadyExists;
import CF.FileManagerPackage.NonExistentMount;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>File Manager</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link CF.FileManagerOperations#mount(java.lang.String, CF.FileSystem) <em>Mount</em>}</li>
 *   <li>{@link CF.FileManagerOperations#unmount(java.lang.String) <em>Unmount</em>}</li>
 *   <li>{@link CF.FileManagerOperations#getMounts() <em>Get Mounts</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public abstract class ScaFileManagerTest extends ScaFileSystemTest {

	/**
	 * Constructs a new File Manager test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaFileManagerTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this File Manager test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaFileManager getFixture() {
		return (ScaFileManager)fixture;
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.FileManagerOperations#mount(java.lang.String, mil.jpeojtrs.sca.cf.FileSystem) <em>Mount</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mil.jpeojtrs.sca.cf.FileManagerOperations#mount(java.lang.String, mil.jpeojtrs.sca.cf.FileSystem)
	 * @generated NOT
	 */
	public void testMount__String_FileSystem() {
		// END GENERATED CODE
		try {
			getFixture().mount("", null);
			Assert.fail();
		} catch (InvalidFileName e) {
			// PASS
		} catch (InvalidFileSystem e) {
			// PASS
		} catch (MountPointAlreadyExists e) {
			// PASS
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.FileSystemOperations#unmount(java.lang.String) <em>Unmount</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws NonExistentMount 
	 * @see mil.jpeojtrs.sca.cf.FileSystemOperations#unmount(java.lang.String)
	 * @generated NOT
	 */
	public void testUnmount__String() {
		// END GENERATED CODE
		try {
			getFixture().unmount("");
			Assert.fail();
		} catch (NonExistentMount e) {
			// PASS
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.FileSystemOperations#getMounts() <em>Get Mounts</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mil.jpeojtrs.sca.cf.FileSystemOperations#getMounts()
	 * @generated NOT
	 */
	public void testGetMounts() {
		// END GENERATED CODE
		getFixture().getMounts();
		// BEGIN GENERATED CODE
	}

} //ScaFileManagerTest

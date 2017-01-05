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

import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.model.sca.ScaFileSystem;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.net.URI;

import org.eclipse.emf.common.util.EList;
import org.junit.Assert;

import CF.DataType;
import CF.FileException;
import CF.InvalidFileName;
import CF.PropertiesHolder;
import CF.FileSystemPackage.UnknownFileSystemProperties;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>File System</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaFileSystem#getFileSystemURI() <em>File System URI</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaFileSystem#createURI(java.lang.String) <em>Create URI</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaFileStore#fetchChildren(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
 * Children</em>}</li>
 * <li>{@link CF.FileSystemOperations#remove(java.lang.String) <em>Remove</em>}</li>
 * <li>{@link CF.FileSystemOperations#copy(java.lang.String, java.lang.String) <em>Copy</em>}</li>
 * <li>{@link CF.FileSystemOperations#exists(java.lang.String) <em>Exists</em>}</li>
 * <li>{@link CF.FileSystemOperations#list(java.lang.String) <em>List</em>}</li>
 * <li>{@link CF.FileSystemOperations#create(java.lang.String) <em>Create</em>}</li>
 * <li>{@link CF.FileSystemOperations#open(java.lang.String, boolean) <em>Open</em>}</li>
 * <li>{@link CF.FileSystemOperations#mkdir(java.lang.String) <em>Mkdir</em>}</li>
 * <li>{@link CF.FileSystemOperations#rmdir(java.lang.String) <em>Rmdir</em>}</li>
 * <li>{@link CF.FileSystemOperations#query(CF.PropertiesHolder) <em>Query</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public abstract class ScaFileSystemTest extends CorbaObjWrapperTest {

	/**
	 * Constructs a new File System test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaFileSystemTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this File System test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaFileSystem< ? > getFixture() {
		return (ScaFileSystem< ? >) fixture;
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaFileSystem#getChildren() <em>Children</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaFileSystem#getChildren()
	 * @generated NOT
	 */
	public void testGetChildren() {
		// END GENERATED CODE
		getFixture().getChildren();
		// BEGIN GENERATED CODE
	}

	@Override
	public void testRefreshWithNullAndDispose() throws InterruptedException {

	}

	@Override
	public void testFetchAttributes__IProgressMonitor() throws InterruptedException {
		super.testFetchAttributes__IProgressMonitor();
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaFileSystem#getFileSystemURI() <em>File System URI</em>}' feature
	 * getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaFileSystem#getFileSystemURI()
	 * @generated NOT
	 */
	public void testGetFileSystemURI() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getFileSystemURI());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaFileSystem#setFileSystemURI(java.net.URI) <em>File System URI</em>}'
	 * feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaFileSystem#setFileSystemURI(java.net.URI)
	 * @generated NOT
	 */
	public void testSetFileSystemURI() {
		final URI uri = getFixture().getFileSystemURI();
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setFileSystemURI(null);
			}
		});

		Assert.assertNull(getFixture().getFileSystemURI());

		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setFileSystemURI(uri);
			}
		});
		Assert.assertEquals(uri, getFixture().getFileSystemURI());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaFileSystem#createURI(java.lang.String) <em>Create URI</em>}'
	 * operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaFileSystem#createURI(java.lang.String)
	 * @generated NOT
	 */
	public void testCreateURI__String() {
		// END GENERATED CODE
		Assert.assertNull(getFixture().createURI(null));
		Assert.assertNotNull(getFixture().createURI("/hello"));
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaFileStore#fetchChildren(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Children</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaFileStore#fetchChildren(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchChildren__IProgressMonitor() {
		// END GENERATED CODE
		EList<ScaFileStore> childrenEList = getFixture().fetchChildren(null);
		try {
			childrenEList.clear();
			Assert.fail("fetched Children list should be unmodifiable");
		} catch (UnsupportedOperationException e) {
			Assert.assertTrue("fetched Children list is unmodifiable", true);
		}
		// BEGIN GENERATED CODE
	}

	@Override
	public void testSetCorbaObj__Object() {

	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.FileSystemOperations#remove(java.lang.String) <em>Remove</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidFileName
	 * @throws FileException
	 * @see mil.jpeojtrs.sca.cf.FileSystemOperations#remove(java.lang.String)
	 * @generated NOT
	 */
	public void testRemove__String() {
		// END GENERATED CODE
		try {
			getFixture().remove("");
			Assert.fail();
		} catch (FileException e) {
			// PASS
		} catch (InvalidFileName e) {
			// PASS
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.FileSystemOperations#copy(java.lang.String, java.lang.String)
	 * <em>Copy</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws FileException
	 * @throws InvalidFileName
	 * @see mil.jpeojtrs.sca.cf.FileSystemOperations#copy(java.lang.String, java.lang.String)
	 * @generated NOT
	 */
	public void testCopy__String_String() {
		// END GENERATED CODE
		try {
			getFixture().copy("", "");
			Assert.fail();
		} catch (InvalidFileName e) {
			// PASS
		} catch (FileException e) {
			// PASS
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.FileSystemOperations#exists(java.lang.String) <em>Exists</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidFileName
	 * @see mil.jpeojtrs.sca.cf.FileSystemOperations#exists(java.lang.String)
	 * @generated NOT
	 */
	public void testExists__String() throws InvalidFileName {
		// END GENERATED CODE
		try {
			getFixture().exists("");
			Assert.fail("Expected InvalidFileName");
		} catch (InvalidFileName e) {
			// PASS
		}
		try {
			getFixture().exists("blah");
			Assert.fail("Expected InvalidFileName");
		} catch (InvalidFileName e) {
			// PASS
		}
		Assert.assertFalse(getFixture().exists("/blah/blah/blah/blah/file"));
		// BEGIN GENERATED CODE
	}

	@Override
	public void testExists() {
		Assert.assertTrue(getFixture().exists());
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.FileSystemOperations#list(java.lang.String) <em>List</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidFileName
	 * @throws FileException
	 * @see mil.jpeojtrs.sca.cf.FileSystemOperations#list(java.lang.String)
	 * @generated NOT
	 */
	public void testList__String() throws FileException, InvalidFileName {
		// END GENERATED CODE
		Assert.assertEquals(1, getFixture().list("").length);
		Assert.assertTrue("Expected multiple file listings", getFixture().list("/*").length > 1);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.FileSystemOperations#create(java.lang.String) <em>Create</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws FileException
	 * @throws InvalidFileName
	 * @see mil.jpeojtrs.sca.cf.FileSystemOperations#create(java.lang.String)
	 * @generated NOT
	 */
	public void testCreate__String() {
		// END GENERATED CODE
		try {
			getFixture().create("");
			Assert.fail();
		} catch (InvalidFileName e) {
			// PASS
		} catch (FileException e) {
			// PASS
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.FileSystemOperations#open(java.lang.String, boolean) <em>Open</em>}'
	 * operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws FileException
	 * @throws InvalidFileName
	 * @see mil.jpeojtrs.sca.cf.FileSystemOperations#open(java.lang.String, boolean)
	 * @generated NOT
	 */
	public void testOpen__String_boolean() {
		// END GENERATED CODE
		try {
			getFixture().open("", false);
			Assert.fail();
		} catch (InvalidFileName e) {
			// PASS
		} catch (FileException e) {
			// PASS
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.FileSystemOperations#mkdir(java.lang.String) <em>Mkdir</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws FileException
	 * @throws InvalidFileName
	 * @see mil.jpeojtrs.sca.cf.FileSystemOperations#mkdir(java.lang.String)
	 * @generated NOT
	 */
	public void testMkdir__String() {
		// END GENERATED CODE
		try {
			getFixture().mkdir("");
			Assert.fail();
		} catch (InvalidFileName e) {
			// PASS
		} catch (FileException e) {
			// PASS
		}
		// BEGIN GENERATED CODE
	}

	@Override
	public void testDispose() {
		// PASS
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.FileSystemOperations#rmdir(java.lang.String) <em>Rmdir</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws FileException
	 * @throws InvalidFileName
	 * @see mil.jpeojtrs.sca.cf.FileSystemOperations#rmdir(java.lang.String)
	 * @generated NOT
	 */
	public void testRmdir__String() {
		// END GENERATED CODE
		try {
			getFixture().rmdir("");
			Assert.fail();
		} catch (InvalidFileName e) {
			// PASS
		} catch (FileException e) {
			// PASS
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.FileSystemOperations#query(mil.jpeojtrs.sca.cf.PropertiesHolder)
	 * <em>Query</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws UnknownFileSystemProperties
	 * @see mil.jpeojtrs.sca.cf.FileSystemOperations#query(mil.jpeojtrs.sca.cf.PropertiesHolder)
	 * @generated NOT
	 */
	public void testQuery__PropertiesHolder() throws UnknownFileSystemProperties {
		// END GENERATED CODE
		PropertiesHolder holder = new PropertiesHolder();
		holder.value = new DataType[0];
		getFixture().query(holder);
		// BEGIN GENERATED CODE
	}

} // ScaFileSystemTest

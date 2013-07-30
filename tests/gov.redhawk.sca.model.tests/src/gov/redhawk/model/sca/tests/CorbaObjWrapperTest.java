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

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import junit.framework.Assert;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Corba Obj Wrapper</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.CorbaObjWrapper#getIor() <em>Ior</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.CorbaObjWrapper#exists() <em>Exists</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.CorbaObjWrapper#fetchAttributes(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Attributes</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.CorbaObjWrapper#fetchNarrowedObject(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Narrowed Object</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.CorbaObjWrapper#_is_a(java.lang.String) <em>is a</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public abstract class CorbaObjWrapperTest extends DataProviderObjectTest {

	/**
	 * Constructs a new Corba Obj Wrapper test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorbaObjWrapperTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Corba Obj Wrapper test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected CorbaObjWrapper<?> getFixture() {
		return (CorbaObjWrapper<?>)fixture;
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.CorbaObjWrapper#getIor() <em>Ior</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.CorbaObjWrapper#getIor()
	 * @generated NOT
	 */
	public void testGetIor() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getObj());
		Assert.assertEquals(getFixture().getObj().toString(), getFixture().getIor());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.CorbaObjWrapper#setIor(java.lang.String) <em>Ior</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.CorbaObjWrapper#setIor(java.lang.String)
	 * @generated NOT
	 */
	public void testSetIor() {
		getFixture().fetchNarrowedObject(null);
		final String ior = getFixture().getIor();
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			
			@Override
			public void execute() {
				getFixture().setIor(null);
			}
		});
		
		Assert.assertNull(getFixture().getIor());
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			
			@Override
			public void execute() {
				getFixture().setIor(ior);
			}
		});
		
		Assert.assertEquals(ior, getFixture().getIor());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.CorbaObjWrapper#unsetIor() <em>unsetIor()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.CorbaObjWrapper#unsetIor()
	 * @generated NOT
	 */
	public void testUnsetIor() {
		getFixture().fetchNarrowedObject(null);
		Assert.assertTrue(getFixture().isSetIor());
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			
			@Override
			public void execute() {
				getFixture().unsetIor();
			}
		});
		
		Assert.assertFalse(getFixture().isSetIor());
		Assert.assertNull(getFixture().getIor());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.CorbaObjWrapper#isSetIor() <em>isSetIor()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.CorbaObjWrapper#isSetIor()
	 * @generated NOT
	 */
	public void testIsSetIor() {
		// END GENERATED CODE
		getFixture().fetchNarrowedObject(null);
		Assert.assertTrue(getFixture().isSetIor());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.CorbaObjWrapper#setCorbaObj(org.omg.CORBA.Object) <em>Set Corba Obj</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.CorbaObjWrapper#setCorbaObj(org.omg.CORBA.Object)
	 * @generated NOT
	 */
	public void testSetCorbaObj__Object() {
		final org.omg.CORBA.Object obj = getFixture().getObj();
		Assert.assertNotNull(obj);
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			public void execute() {
				getFixture().setCorbaObj(null);
				Assert.assertNull(getFixture().getCorbaObj());
				Assert.assertNull(getFixture().getObj());
				getFixture().setCorbaObj(obj);
			}
		});
		getFixture().fetchNarrowedObject(null);
		Assert.assertNotNull(getFixture().getCorbaObj());
		Assert.assertNotNull(getFixture().getObj());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.CorbaObjWrapper#exists() <em>Exists</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.CorbaObjWrapper#exists()
	 * @generated NOT
	 */
	public void testExists() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().exists());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.CorbaObjWrapper#fetchAttributes(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Attributes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @see gov.redhawk.model.sca.CorbaObjWrapper#fetchAttributes(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchAttributes__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		getFixture().fetchAttributes(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.CorbaObjWrapper#fetchNarrowedObject(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Narrowed Object</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.CorbaObjWrapper#fetchNarrowedObject(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchNarrowedObject__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchNarrowedObject(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.CorbaObjWrapper#_is_a(java.lang.String) <em>is a</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.CorbaObjWrapper#_is_a(java.lang.String)
	 * @generated NOT
	 */
	public void test_is_a__String() {
		// END GENERATED CODE
		Assert.assertFalse(getFixture()._is_a(null));
		Assert.assertFalse(getFixture()._is_a(""));
		if (getRepId() != null) {
			Assert.assertTrue(getFixture()._is_a(getRepId()));
		}
		// BEGIN GENERATED CODE
	}

	protected abstract String getRepId();
	
	public void testRefreshWithNullAndDispose() throws InterruptedException {
		final org.omg.CORBA.Object obj = getFixture().getCorbaObj();
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			
			public void execute() {
			    getFixture().setCorbaObj(null);	
			}
		});
	    super.testRefresh__IProgressMonitor_RefreshDepth();
	    ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			
			public void execute() {
				getFixture().setCorbaObj(obj);
			}
		});
	    
	}

} //CorbaObjWrapperTest

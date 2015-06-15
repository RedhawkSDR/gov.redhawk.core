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

import gov.redhawk.model.sca.ProfileObjectWrapper;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import org.junit.Assert;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Profile Object Wrapper</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileURI() <em>Profile URI</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileObj() <em>Profile Obj</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileObject(org.eclipse.core.runtime.IProgressMonitor)
 * <em>Fetch Profile Object</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileURI(org.eclipse.core.runtime.IProgressMonitor)
 * <em>Fetch Profile URI</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public abstract class ProfileObjectWrapperTest extends IStatusProviderTest {
	/**
	 * Constructs a new Profile Object Wrapper test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProfileObjectWrapperTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Profile Object Wrapper test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ProfileObjectWrapper< ? > getFixture() {
		return (ProfileObjectWrapper< ? >) fixture;
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileURI() <em>Profile URI</em>}' feature
	 * getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#getProfileURI()
	 * @generated
	 */
	public void testGetProfileURI() {
		// TODO: implement this feature getter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#setProfileURI(org.eclipse.emf.common.util.URI)
	 * <em>Profile URI</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#setProfileURI(org.eclipse.emf.common.util.URI)
	 * @generated
	 */
	public void testSetProfileURI() {
		// TODO: implement this feature setter test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#unsetProfileURI() <em>unsetProfileURI()</em>}'
	 * method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#unsetProfileURI()
	 * @generated
	 */
	public void testUnsetProfileURI() {
		// TODO: implement this test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#isSetProfileURI() <em>isSetProfileURI()</em>}'
	 * method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#isSetProfileURI()
	 * @generated
	 */
	public void testIsSetProfileURI() {
		// TODO: implement this test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileObj() <em>Profile Obj</em>}' feature
	 * getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#getProfileObj()
	 * @generated NOT
	 */
	public void testGetProfileObj() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertNotNull(getFixture().getProfileObj());
			}
		});
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#setProfileObj(java.lang.Object) <em>Profile
	 * Obj</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#setProfileObj(java.lang.Object)
	 * @generated NOT
	 */
	public void testSetProfileObj() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setProfileObj(null);
			}

		});
		// END GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#unsetProfileObj() <em>unsetProfileObj()</em>}'
	 * method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#unsetProfileObj()
	 * @generated
	 */
	public void testUnsetProfileObj() {
		// TODO: implement this test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#isSetProfileObj() <em>isSetProfileObj()</em>}'
	 * method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#isSetProfileObj()
	 * @generated
	 */
	public void testIsSetProfileObj() {
		// TODO: implement this test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileObject(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Profile Object</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileObject(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchProfileObject__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		getFixture().fetchProfileObject(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileURI(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Profile URI</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileURI(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchProfileURI__IProgressMonitor() {
		getFixture().fetchProfileURI(null);
	}

} // ProfileObjectWrapperTest

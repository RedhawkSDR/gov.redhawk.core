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

import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.RefreshDepth;
import junit.framework.TestCase;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>IRefreshable</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public abstract class IRefreshableTest extends TestCase {

	/**
	 * The fixture for this IRefreshable test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IRefreshable fixture = null;

	/**
	 * Constructs a new IRefreshable test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IRefreshableTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this IRefreshable test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(IRefreshable fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this IRefreshable test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IRefreshable getFixture() {
		return fixture;
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.IRefreshable#refresh(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth) <em>Refresh</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @see gov.redhawk.model.sca.IRefreshable#refresh(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
	 * @generated NOT
	 */
	public void testRefresh__IProgressMonitor_RefreshDepth() throws InterruptedException {
		// END GENERATED CODE
		for (final RefreshDepth depth : RefreshDepth.values()) {
			getFixture().refresh(null, depth);
		}
		// BEGIN GENERATED CODE
	}

} //IRefreshableTest

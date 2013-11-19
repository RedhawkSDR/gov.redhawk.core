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

import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaFileStore;
import org.junit.Assert;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>File Store</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaFileStore#fetchChildren(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Children</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaFileStoreTest extends IStatusProviderTest {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaFileStoreTest.class);
	}

	/**
	 * Constructs a new File Store test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaFileStoreTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this File Store test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaFileStore getFixture() {
		return (ScaFileStore)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated NOT
	 */
	@Override
	protected void setUp() throws Exception {
		this.env = TestEnvirornment.getInstance();
		setFixture(this.env.getDomMgr().getFileManager());
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

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaFileStore#fetchChildren(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Children</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaFileStore#fetchChildren(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchChildren__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchChildren(null);
		// BEGIN GENERATED CODE
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

	/**
	 * A placeholder until real tests are implemented.
	 * @generated NOT
	 */
	public void testPlaceholder() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture());
		// BEGIN GENERATED CODE
	}

} //ScaFileStoreTest

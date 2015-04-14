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

import gov.redhawk.model.sca.DataProviderObject;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.commands.ScaModelCommand;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Data Provider Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.DataProviderObject#attachDataProviders() <em>Attach Data Providers</em>}</li>
 * <li>{@link gov.redhawk.model.sca.DataProviderObject#detachDataProviders() <em>Detach Data Providers</em>}</li>
 * <li>{@link gov.redhawk.model.sca.IDisposable#dispose() <em>Dispose</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public abstract class DataProviderObjectTest extends IStatusProviderTest {
	/**
	 * Constructs a new Data Provider Object test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataProviderObjectTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Data Provider Object test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected DataProviderObject getFixture() {
		return (DataProviderObject) fixture;
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.DataProviderObject#attachDataProviders() <em>Attach Data Providers</em>}'
	 * operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.DataProviderObject#attachDataProviders()
	 * @generated NOT
	 */
	public void testAttachDataProviders() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().attachDataProviders();
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.DataProviderObject#detachDataProviders() <em>Detach Data Providers</em>}'
	 * operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.DataProviderObject#detachDataProviders()
	 * @generated NOT
	 */
	public void testDetachDataProviders() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().detachDataProviders();
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.DataProviderObject#refresh(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
	 * <em>Refresh</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @see gov.redhawk.model.sca.DataProviderObject#refresh(org.eclipse.core.runtime.IProgressMonitor,
	 * gov.redhawk.model.sca.RefreshDepth)
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
	 * Tests the '{@link gov.redhawk.model.sca.IDisposable#dispose() <em>Dispose</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.IDisposable#dispose()
	 * @generated NOT
	 */
	public void testDispose() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				IDisposableTest.testDipose(getFixture());
			}
		});
		// BEGIN GENERATED CODE
	}

} // DataProviderObjectTest

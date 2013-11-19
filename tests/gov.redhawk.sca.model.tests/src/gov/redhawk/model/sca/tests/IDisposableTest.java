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

import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import org.junit.Assert;
import junit.framework.TestCase;

import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>IDisposable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.IDisposable#dispose() <em>Dispose</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public abstract class IDisposableTest extends TestCase {
	/**
	 * The fixture for this IDisposable test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IDisposable fixture = null;

	/**
	 * Constructs a new IDisposable test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IDisposableTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this IDisposable test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(IDisposable fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this IDisposable test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IDisposable getFixture() {
		return fixture;
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
	
	public static void testDipose(IDisposable disposable) {
		Assert.assertNotNull(TransactionUtil.getEditingDomain(disposable));
		Assert.assertFalse(disposable.isDisposed());
		disposable.dispose();
		Assert.assertTrue(disposable.isDisposed());
	}

} //IDisposableTest

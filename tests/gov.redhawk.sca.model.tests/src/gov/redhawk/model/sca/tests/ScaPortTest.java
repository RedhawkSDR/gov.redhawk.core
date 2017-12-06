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

import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaTransport;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.util.List;

import org.junit.Assert;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaPort#getRepid() <em>Repid</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaPort#fetchSupportedTransports(org.eclipse.core.runtime.IProgressMonitor)
 * <em>Fetch Supported Transports</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public abstract class ScaPortTest extends CorbaObjWrapperTest {

	/**
	 * Constructs a new Port test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaPortTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Port test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaPort< ? , ? > getFixture() {
		return (ScaPort< ? , ? >) fixture;
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaPort#getRepid() <em>Repid</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaPort#getRepid()
	 * @generated NOT
	 */
	public void testGetRepid() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertNotNull(getFixture().getRepid());
				getFixture().setProfileObj(null);
				Assert.assertNull(getFixture().getRepid());
			}

		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the
	 * '{@link gov.redhawk.model.sca.ScaPort#fetchSupportedTransports(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Supported Transports</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaPort#fetchSupportedTransports(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchSupportedTransports__IProgressMonitor() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), () -> {
			getFixture().unsetSupportedTransports();
		});
		Assert.assertFalse(getFixture().isSetSupportedTransports());
		getFixture().fetchSupportedTransports(null);
		List<ScaTransport> transports = getFixture().getSupportedTransports();
		Assert.assertNotNull(transports);
		Assert.assertEquals(1, transports.size());
		// BEGIN GENERATED CODE
	}

} // ScaPortTest

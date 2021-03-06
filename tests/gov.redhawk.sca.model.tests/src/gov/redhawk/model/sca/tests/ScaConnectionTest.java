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

import org.junit.Assert;

import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaConnection;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;
import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScaConnectionTest extends TestCase {

	/**
	 * The fixture for this Connection test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaConnection fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaConnectionTest.class);
	}

	/**
	 * Constructs a new Connection test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaConnectionTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Connection test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ScaConnection fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Connection test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaConnection getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated NOT
	 */
	@Override
	protected void setUp() throws Exception {
		TestEnvirornment env = TestEnvirornment.getInstance();
		ScaWaveform waveform = env.getDomMgr().getWaveforms().get(0);
		// Get SigGen_1
		ScaComponent component = waveform.getScaComponent("DCE:7264cd85-6985-4903-92e1-c30982197dbd");
		ScaUsesPort usesPort = (ScaUsesPort) component.getScaPort("out");
		setFixture(usesPort.getConnections().get(0));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	// END GENERATED CODE

	public void testGetId() {
		Assert.assertEquals("DCE:223f0573-772d-44ad-ae36-18ddffaa0ffe", getFixture().getId());
	}

	public void testGetTargetPort() {
		Assert.assertNotNull(getFixture().getTargetPort());
	}

	// BEGIN GENERATED CODE

} // ScaConnectionTest

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
import gov.redhawk.model.sca.ScaNegotiatedConnection;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Negotiated Connection</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScaNegotiatedConnectionTest extends ScaConnectionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaNegotiatedConnectionTest.class);
	}

	/**
	 * Constructs a new Negotiated Connection test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaNegotiatedConnectionTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Negotiated Connection test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaNegotiatedConnection getFixture() {
		return (ScaNegotiatedConnection) fixture;
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

	public void testGetTransportType() {
		Assert.assertEquals("transport_type", ((ScaNegotiatedConnection) getFixture()).getTransportType());
	}

	public void testIsAlive() {
		Assert.assertEquals(true, ((ScaNegotiatedConnection) getFixture()).isAlive());
	}

	// BEGIN GENERATED CODE

} // ScaNegotiatedConnectionTest

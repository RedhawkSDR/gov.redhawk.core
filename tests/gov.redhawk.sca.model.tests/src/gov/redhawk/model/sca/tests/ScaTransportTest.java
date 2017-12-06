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
import org.junit.Test;

import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaTransport;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Transport</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScaTransportTest extends TestCase {

	/**
	 * The fixture for this Transport test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaTransport fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaTransportTest.class);
	}

	/**
	 * Constructs a new Transport test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaTransportTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Transport test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ScaTransport fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Transport test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaTransport getFixture() {
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
		// END GENERATED CODE
		TestEnvirornment env = TestEnvirornment.getInstance();
		ScaWaveform waveform = env.getDomMgr().getWaveforms().get(0);
		ScaComponent component = waveform.getScaComponent(ScaTestConstaints.SIG_GEN_1);
		ScaUsesPort usesPort = (ScaUsesPort) component.getScaPort("out");
		ScaTransport transport = usesPort.getSupportedTransports().get(0);
		setFixture(transport);
		// BEGIN GENERATED CODE
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

	@Test
	public void testTransportDetails() {
		Assert.assertEquals("shmipc", getFixture().getTransportType());
		Assert.assertEquals(1, getFixture().getTransportProperties().size());
		ScaSimpleProperty simpleProp = (ScaSimpleProperty) getFixture().getTransportProperties().get(0);
		Assert.assertEquals("simpleProp", simpleProp.getId());
		Assert.assertEquals(123, simpleProp.getValue());
	}

} // ScaTransportTest

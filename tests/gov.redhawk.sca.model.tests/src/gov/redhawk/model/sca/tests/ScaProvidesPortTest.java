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

import ExtendedCF.NegotiableProvidesPortHelper;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Provides Port</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScaProvidesPortTest extends ScaPortTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaProvidesPortTest.class);
	}

	/**
	 * Constructs a new Provides Port test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaProvidesPortTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Provides Port test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaProvidesPort getFixture() {
		return (ScaProvidesPort) fixture;
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
		waveform.refresh(null, RefreshDepth.FULL);
		env.validateStartState();
		ScaComponent kitchenSinkComp = waveform.findComponent(ScaTestConstaints.HARD_LIMIT_1);
		ScaPort< ? , ? > port = kitchenSinkComp.getScaPort("dataDouble_in");
		setFixture(port);
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated NOT
	 */
	@Override
	protected void tearDown() throws Exception {
		// END GENERATED CODE
		this.env = null;
		setFixture(null);
		// BEGIN GENERATED CODE
	}

	// END GENERATED CODE

	@Override
	protected String getRepId() {
		return NegotiableProvidesPortHelper.id();
	}

	// BEGIN GENERATED CODE

} // ScaProvidesPortTest

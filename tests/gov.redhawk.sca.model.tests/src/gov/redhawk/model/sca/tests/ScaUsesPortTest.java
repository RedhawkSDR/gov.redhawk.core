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

import org.eclipse.emf.common.util.EList;
import org.junit.Assert;

import CF.PortPackage.InvalidPort;
import CF.PortPackage.OccupiedPort;
import ExtendedCF.NegotiableUsesPortHelper;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaConnection;
import gov.redhawk.model.sca.ScaNegotiatedConnection;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Uses Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaUsesPort#fetchConnections(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
 * Connections</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaUsesPort#disconnectPort(gov.redhawk.model.sca.ScaConnection) <em>Disconnect
 * Port</em>}</li>
 * <li>{@link CF.PortOperations#connectPort(org.omg.CORBA.Object, java.lang.String) <em>Connect Port</em>}</li>
 * <li>{@link CF.PortOperations#disconnectPort(java.lang.String) <em>Disconnect Port</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaUsesPortTest extends ScaPortTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaUsesPortTest.class);
	}

	/**
	 * Constructs a new Uses Port test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaUsesPortTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Uses Port test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaUsesPort getFixture() {
		return (ScaUsesPort) fixture;
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
		ScaComponent kitchenSinkComp = waveform.findComponent(ScaTestConstaints.SIG_GEN_1);
		ScaPort< ? , ? > port = kitchenSinkComp.getScaPort("out");
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

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaUsesPort#fetchConnections(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Connections</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @see gov.redhawk.model.sca.ScaUsesPort#fetchConnections(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchConnections__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), () -> {
			getFixture().unsetConnections();
		});
		Assert.assertFalse(getFixture().isSetConnections());
		EList<ScaConnection> connectionsEList = getFixture().fetchConnections(null);
		Assert.assertTrue(getFixture().isSetConnections());
		Assert.assertEquals(1, getFixture().getConnections().size());
		Assert.assertTrue(getFixture().getConnections().get(0) instanceof ScaNegotiatedConnection);
		try {
			connectionsEList.clear();
			Assert.fail("fetched Connections list should be unmodifiable");
		} catch (UnsupportedOperationException e) {
			Assert.assertTrue("fetched Connections list is unmodifiable", true);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaUsesPort#disconnectPort(gov.redhawk.model.sca.ScaConnection)
	 * <em>Disconnect Port</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidPort
	 * @see gov.redhawk.model.sca.ScaUsesPort#disconnectPort(gov.redhawk.model.sca.ScaConnection)
	 * @generated NOT
	 */
	public void testDisconnectPort__ScaConnection() throws InvalidPort {
		// END GENERATED CODE
		getFixture().disconnectPort((ScaConnection) null);

		ScaConnection connection = getFixture().getConnections().get(0);
		getFixture().disconnectPort(connection);
		Assert.assertFalse(getFixture().getConnections().contains(connection));
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.PortOperations#connectPort(org.omg.CORBA.Object, java.lang.String)
	 * <em>Connect Port</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws OccupiedPort
	 * @throws InvalidPort
	 * @see mil.jpeojtrs.sca.cf.PortOperations#connectPort(org.omg.CORBA.Object, java.lang.String)
	 * @generated NOT
	 */
	public void testConnectPort__Object_String() {
		// END GENERATED CODE

		// try {
		// getFixture().connectPort(null, "");
		// } catch (InvalidPort e) {
		// // PASS
		// } catch (OccupiedPort e) {
		// // PASS
		// }

		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.PortOperations#disconnectPort(java.lang.String) <em>Disconnect Port</em>}'
	 * operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidPort
	 * @see mil.jpeojtrs.sca.cf.PortOperations#disconnectPort(java.lang.String)
	 * @generated NOT
	 */
	public void testDisconnectPort__String() throws InvalidPort {
		// END GENERATED CODE
		getFixture().disconnectPort((String) null);

		boolean caught = false;
		try {
			getFixture().disconnectPort("bogus_connection");
		} catch (InvalidPort e) {
			caught = true;
		}
		Assert.assertTrue(caught);

		Assert.assertEquals(1, getFixture().getConnections().size());
		getFixture().disconnectPort("DCE:223f0573-772d-44ad-ae36-18ddffaa0ffe");
		Assert.assertEquals(0, getFixture().getConnections().size());
		// BEGIN GENERATED CODE
	}

	// END GENERATED CODE

	@Override
	protected String getRepId() {
		return NegotiableUsesPortHelper.id();
	}

	// BEGIN GENERATED CODE

} // ScaUsesPortTest

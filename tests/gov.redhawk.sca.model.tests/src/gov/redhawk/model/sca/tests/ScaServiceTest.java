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
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import org.eclipse.emf.common.util.EList;
import org.junit.Assert;
import junit.textui.TestRunner;
import CF.DeviceHelper;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaService#isInstance(mil.jpeojtrs.sca.scd.Interface) <em>Is Instance</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaPortContainer#getScaPort(java.lang.String) <em>Get Sca Port</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaPortContainer#fetchPorts(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
 * Ports</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaServiceTest extends ScaPropertyContainerTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaServiceTest.class);
	}

	/**
	 * Constructs a new Service test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaServiceTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Service test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaService getFixture() {
		return (ScaService) fixture;
	}

	private TestEnvirornment env;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated NOT
	 */
	@Override
	protected void setUp() throws Exception {
		this.env = TestEnvirornment.getInstance();
		final ScaDeviceManager deviceManager = this.env.getDomMgr().getDeviceManagers().get(0);
		setFixture(deviceManager.getScaService(ScaTestConstaints.SERVICE_NAME));
		Assert.assertNotNull(getFixture());
		getFixture().refresh(null, RefreshDepth.FULL);
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaService#isInstance(mil.jpeojtrs.sca.scd.Interface) <em>Is
	 * Instance</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaService#isInstance(mil.jpeojtrs.sca.scd.Interface)
	 * @generated
	 */
	public void testIsInstance__Interface() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaPortContainer#getScaPort(java.lang.String) <em>Get Sca Port</em>}'
	 * operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaPortContainer#getScaPort(java.lang.String)
	 * @generated NOT
	 */
	public void testGetScaPort__String() {
		getFixture().getScaPort(null);
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaPortContainer#fetchPorts(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Ports</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaPortContainer#fetchPorts(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchPorts__IProgressMonitor() {
		EList<ScaPort< ? , ? >> portsEList = getFixture().fetchPorts(null);
		Assert.assertTrue(getFixture().isSetPorts());
		Assert.assertEquals(2, portsEList.size());

		try {
			portsEList.clear();
			Assert.fail("fetched Ports list should be unmodifiable");
		} catch (UnsupportedOperationException e) {
			// PASS
		}

		// Test w/o a CORBA object
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			@Override
			public void execute() {
				getFixture().unsetCorbaObj();
			}
		});
		portsEList = getFixture().fetchPorts(null);
		Assert.assertEquals(0, portsEList.size());
	}

	@Override
	protected String getRepId() {
		return DeviceHelper.id();
	}

} // ScaServiceTest

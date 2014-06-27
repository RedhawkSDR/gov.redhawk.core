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
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import org.junit.Assert;

import junit.textui.TestRunner;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.util.TransactionUtil;

import CF.ResourceHelper;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaComponent#getInstantiationIdentifier() <em>Instantiation Identifier</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaComponent#fetchDevices(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Devices</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaComponentTest extends ScaAbstractComponentTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaComponentTest.class);
	}

	/**
	 * Constructs a new Component test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaComponentTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Component test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaComponent getFixture() {
		return (ScaComponent)fixture;
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
		final ScaWaveform waveform = this.env.getDomMgr().getWaveformFactories().get(0).createWaveform(null, "name", null, null);
		waveform.refresh(null, RefreshDepth.FULL);
		this.env.validateStartState();
		ScaModelCommand.execute(waveform, new ScaModelCommand() {

			@Override
			public void execute() {
				if (waveform.getComponents().isEmpty() && ScaTests.DEBUG.enabled) {
					ScaTests.DEBUG.message("Invalid state: {0}", waveform);
				}
				Assert.assertFalse(waveform.getComponents().isEmpty());
				setFixture(waveform.getComponents().get(0));
			}

		});
		Assert.assertNotNull(getFixture());
		Assert.assertNotNull(TransactionUtil.getEditingDomain(getFixture()));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated NOT
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaComponent#getInstantiationIdentifier() <em>Instantiation Identifier</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaComponent#getInstantiationIdentifier()
	 * @generated NOT
	 */
	public void testGetInstantiationIdentifier() {
		// END GENERATED CODE
		final String identifier = getFixture().getObj().identifier();
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertEquals(identifier, getFixture().getIdentifier());
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaComponent#isSetInstantiationIdentifier() <em>isSetInstantiationIdentifier()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaComponent#isSetInstantiationIdentifier()
	 * @generated NOT
	 */
	public void testIsSetInstantiationIdentifier() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertTrue(getFixture().isSetComponentInstantiation());
				getFixture().unsetComponentInstantiation();
				Assert.assertFalse(getFixture().isSetComponentInstantiation());
			}
		});

		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaComponent#fetchDevices(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Devices</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaComponent#fetchDevices(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchDevices__IProgressMonitor() {
		// END GENERATED CODE
		EList<ScaDevice< ? >> devicesEList = getFixture().getDevices();
		Assert.assertEquals(1, devicesEList.size());
		try {
			devicesEList.clear();
			Assert.fail("fetched Devices list should be unmodifiable");
		} catch (UnsupportedOperationException e) {
			Assert.assertTrue("fetched Devices list is unmodifiable", true);
		}
		// BEGIN GENERATED CODE
	}
	
	@Override
	protected String getRepId() {
		return ResourceHelper.id();
	}

} //ScaComponentTest

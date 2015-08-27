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
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import org.junit.Assert;
import junit.textui.TestRunner;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;

import org.eclipse.emf.transaction.util.TransactionUtil;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Simple Sequence Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaSimpleSequenceProperty#setValue(java.lang.Object[]) <em>Set Value</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaSimpleSequenceProperty#getValue() <em>Get Value</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaSimpleSequenceProperty#createPropertyRef() <em>Create Property Ref</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaSimpleSequencePropertyTest extends ScaAbstractPropertyTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaSimpleSequencePropertyTest.class);
	}

	/**
	 * Constructs a new Simple Sequence Property test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaSimpleSequencePropertyTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Simple Sequence Property test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaSimpleSequenceProperty getFixture() {
		return (ScaSimpleSequenceProperty) fixture;
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
		Assert.assertNotNull(waveform);
		waveform.refresh(null, RefreshDepth.FULL);
		this.env.validateStartState();
		ScaModelCommand.execute(waveform, new ScaModelCommand() {

			@Override
			public void execute() {
				final ScaComponent kitchenSink = waveform.findComponent(ScaTestConstaints.DCE_KITCHEN_SINK_COMPONENT);
				if (kitchenSink == null && ScaTestsUtil.DEBUG.enabled) {
					ScaTestsUtil.DEBUG.message("Invalid Object State: {0}", waveform);
				}
				if (kitchenSink == null) {
					return;
				}
				final ScaAbstractProperty< ? > prop = kitchenSink.getProperty(ScaTestConstaints.DCE_SIMPLE_SEQUENCE_STRING_PROP);
				if (prop == null && ScaTestsUtil.DEBUG.enabled) {
					ScaTestsUtil.DEBUG.message("Invalid Object State: {0}", kitchenSink);
				}
				setFixture(prop);
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
		this.env = null;
		setFixture(null);
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaSimpleSequenceProperty#getValue() <em>Value</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaSimpleSequenceProperty#getValue()
	 * @generated NOT
	 */
	public void testGetValue() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertNotNull(getFixture().getValue());
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaSimpleSequenceProperty#createPropertyRef() <em>Create Property
	 * Ref</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaSimpleSequenceProperty#createPropertyRef()
	 * @generated NOT
	 */
	public void testCreatePropertyRef() {
		ScaSimpleSequenceProperty prop = getFixture();
		SimpleSequenceRef ref = prop.createPropertyRef();
		Assert.assertEquals(prop.getId(), ref.getRefID());
		Assert.assertEquals(prop.getValues().size(), ref.getValues().getValue().size());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaSimpleSequenceProperty#setValue(java.lang.Object[]) <em>Value</em>}'
	 * feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaSimpleSequenceProperty#setValue(java.lang.Object[])
	 * @generated NOT
	 */
	public void testSetValue() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getValue());
		final String[] value = new String[0];

		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setValue(value);
				Assert.assertNotNull(getFixture().getValue());
				Assert.assertEquals(value.length, getFixture().getValues().size());
				getFixture().setValue(null);
				Assert.assertTrue(getFixture().getValues().size() == 0);
			}
		});
		// END GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaSimpleSequenceProperty#setRemoteValue(java.lang.Object[]) <em>Set
	 * Remote Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws PartialConfiguration
	 * @throws InvalidConfiguration
	 * @throws InterruptedException
	 * @see gov.redhawk.model.sca.ScaSimpleSequenceProperty#setRemoteValue(java.lang.Object[])
	 * @generated NOT
	 */
	public void testSetRemoteValue__Object() throws InvalidConfiguration, PartialConfiguration, InterruptedException {
		// END GENERATED CODE
		getFixture().setRemoteValue(getFixture().toAny());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaSimpleSequenceProperty#setValue(java.lang.Object[]) <em>Set
	 * Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaSimpleSequenceProperty#setValue(java.lang.Object[])
	 * @generated NOT
	 */
	public void testSetValue__Object() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setValue(getFixture().getValue());
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaSimpleSequenceProperty#setRemoteValue(java.lang.Object[]) <em>Set
	 * Remote Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @throws PartialConfiguration
	 * @throws InvalidConfiguration
	 * @see gov.redhawk.model.sca.ScaSimpleSequenceProperty#setRemoteValue(java.lang.Object[])
	 * @generated NOT
	 */
	public void testSetRemoteValue__Object_1() throws InvalidConfiguration, PartialConfiguration, InterruptedException {
		// END GENERATED CODE
		getFixture().setRemoteValue(getFixture().toAny());
		// BEGIN GENERATED CODE
	}

} // ScaSimpleSequencePropertyTest

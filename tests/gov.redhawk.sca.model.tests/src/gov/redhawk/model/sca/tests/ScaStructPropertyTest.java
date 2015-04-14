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
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import org.junit.Assert;
import junit.textui.TestRunner;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.transaction.util.TransactionUtil;
import CF.DataType;
import CF.PropertiesHolder;
import CF.UnknownProperties;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Struct Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaStructProperty#getSimple(java.lang.String) <em>Get Simple</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaStructProperty#getSequence(java.lang.String) <em>Get Sequence</em>}</li>
 * <li>{@link CF.PropertySetOperations#configure(CF.DataType[]) <em>Configure</em>}</li>
 * <li>{@link CF.PropertySetOperations#query(CF.PropertiesHolder) <em>Query</em>}</li>
 * <li>
 * {@link CF.PropertySetOperations#registerPropertyListener(org.omg.CORBA.Object, org.eclipse.emf.common.util.EList, float)
 * <em>Register Property Listener</em>}</li>
 * <li>{@link CF.PropertySetOperations#unregisterPropertyListener(java.lang.String) <em>Unregister Property
 * Listener</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaStructPropertyTest extends ScaAbstractPropertyTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaStructPropertyTest.class);
	}

	/**
	 * Constructs a new Struct Property test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaStructPropertyTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Struct Property test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaStructProperty getFixture() {
		return (ScaStructProperty) fixture;
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
					ScaTestsUtil.DEBUG.message("Invalid state: {0}", waveform);
				}
				if (kitchenSink == null) {
					return;
				}
				final ScaAbstractProperty< ? > prop = kitchenSink.getProperty(ScaTestConstaints.DCE_STRUCT_PROP);
				if (prop == null && ScaTestsUtil.DEBUG.enabled) {
					ScaTestsUtil.DEBUG.message("Invalid state: {0}", kitchenSink);
				}
				setFixture(prop);
			}

		});
		Assert.assertNotNull(getFixture());
		Assert.assertNotNull(TransactionUtil.getEditingDomain(getFixture()));
	}

	public void testListener() {
		final boolean[] simpleNotification = new boolean[] { false };
		final EContentAdapter adapter = new EContentAdapter() {
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				if (notification.getNotifier() instanceof ScaSimpleProperty) {
					switch (notification.getFeatureID(ScaSimpleProperty.class)) {
					case ScaPackage.SCA_SIMPLE_PROPERTY__VALUE:
						simpleNotification[0] = true;
						break;
					default:
						break;
					}
				}
			}
		};
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				System.out.println("Contents: " + getFixture().eContents().size());
				getFixture().eAdapters().add(adapter);
				getFixture().getSimples().get(0).setValue("newValue");
			}
		});

		Assert.assertTrue(simpleNotification[0]);
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaStructProperty#getSimples() <em>Simples</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaStructProperty#getSimples()
	 * @generated NOT
	 */
	public void testGetSimples() {
		// END GENERATED CODE
		Assert.assertEquals(getFixture().getSimples().size(), getFixture().getDefinition().getSimple().size());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaStructProperty#getSimple(java.lang.String) <em>Get Simple</em>}'
	 * operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaStructProperty#getSimple(java.lang.String)
	 * @generated NOT
	 */
	public void testGetSimple__String() {
		// END GENERATED CODE
		Assert.assertNull(getFixture().getSimple(null));
		Assert.assertNotNull(getFixture().getSimple(getFixture().getSimples().get(0).getId()));
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaStructProperty#getSequence(java.lang.String) <em>Get Sequence</em>}'
	 * operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaStructProperty#getSequence(java.lang.String)
	 * @generated
	 */
	public void testGetSequence__String() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaStructProperty#setRemoteValue(mil.jpeojtrs.sca.cf.DataType[]) <em>Set
	 * Remote Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @throws InvalidConfiguration
	 * @throws PartialConfiguration
	 * @see gov.redhawk.model.sca.ScaStructProperty#setRemoteValue(mil.jpeojtrs.sca.cf.DataType[])
	 * @generated NOT
	 */
	public void testSetRemoteValue__DataType() throws PartialConfiguration, InvalidConfiguration, InterruptedException {
		// END GENERATED CODE
		getFixture().setRemoteValue(getFixture().toAny());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.IPropertyConfigurer#configure(mil.jpeojtrs.sca.cf.DataType)
	 * <em>Configure</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidConfiguration
	 * @throws PartialConfiguration
	 * @see gov.redhawk.model.sca.IPropertyConfigurer#configure(mil.jpeojtrs.sca.cf.DataType)
	 * @generated NOT
	 */
	public void testConfigure__DataType() throws PartialConfiguration, InvalidConfiguration {
		// END GENERATED CODE
		getFixture().configure(new DataType[0]);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.PropertySetOperations#query(mil.jpeojtrs.sca.cf.PropertiesHolder)
	 * <em>Query</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws UnknownProperties
	 * @see mil.jpeojtrs.sca.cf.PropertySetOperations#query(mil.jpeojtrs.sca.cf.PropertiesHolder)
	 * @generated NOT
	 */
	public void testQuery__PropertiesHolder() throws UnknownProperties {
		// END GENERATED CODE
		PropertiesHolder holder = new PropertiesHolder();
		holder.value = new DataType[0];
		getFixture().query(holder);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link CF.PropertySetOperations#registerPropertyListener(org.omg.CORBA.Object, org.eclipse.emf.common.util.EList, float)
	 * <em>Register Property Listener</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see CF.PropertySetOperations#registerPropertyListener(org.omg.CORBA.Object, org.eclipse.emf.common.util.EList,
	 * float)
	 * @generated
	 */
	public void testRegisterPropertyListener__Object_EList_float() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link CF.PropertySetOperations#unregisterPropertyListener(java.lang.String) <em>Unregister Property
	 * Listener</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see CF.PropertySetOperations#unregisterPropertyListener(java.lang.String)
	 * @generated
	 */
	public void testUnregisterPropertyListener__String() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	public void testStructOrder() {
		int index = 0;
		for (final ScaSimpleProperty simple : getFixture().getSimples()) {
			Assert.assertEquals(simple.getId(), getFixture().getDefinition().getSimple().get(index).getId());
			index++;
		}
	}

} // ScaStructPropertyTest

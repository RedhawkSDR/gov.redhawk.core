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
import gov.redhawk.model.sca.ScaStructSequenceProperty;
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
 * A test case for the model object '<em><b>Struct Sequence Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaStructSequenceProperty#createScaStructProperty() <em>Create Sca Struct Property</em>}</li>
 *   <li>{@link CF.PropertySetOperations#configure(CF.DataType[]) <em>Configure</em>}</li>
 *   <li>{@link CF.PropertySetOperations#query(CF.PropertiesHolder) <em>Query</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaStructSequencePropertyTest extends ScaAbstractPropertyTest {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaStructSequencePropertyTest.class);
	}

	/**
	 * Constructs a new Struct Sequence Property test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaStructSequencePropertyTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Struct Sequence Property test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaStructSequenceProperty getFixture() {
		return (ScaStructSequenceProperty)fixture;
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
				Assert.assertNotNull(kitchenSink);
				final ScaAbstractProperty< ? > prop = kitchenSink.getProperty(ScaTestConstaints.DCE_STUCT_SEQ_PROP);
				setFixture(prop);
				Assert.assertNotNull(getFixture());
			}

		});
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaStructSequenceProperty#createScaStructProperty() <em>Create Sca Struct Property</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaStructSequenceProperty#createScaStructProperty()
	 * @generated NOT
	 */
	public void testCreateScaStructProperty() {
		// END GENERATED CODE
		ScaStructProperty newStruct = getFixture().createScaStructProperty();
		Assert.assertNotNull(newStruct);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaStructSequenceProperty#getStructs() <em>Structs</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaStructSequenceProperty#getStructs()
	 * @generated NOT
	 */
	public void testGetStructs() {
		// END GENERATED CODE
		Assert.assertEquals(getFixture().getDefinition().getStructValue().size(), getFixture().getStructs().size());
		// BEGIN GENERATED CODE
	}
	
	public void testListener() {
		final boolean [] simpleNotification = new boolean[] {
				false
		};
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
				ScaStructProperty struct = getFixture().createScaStructProperty();
				getFixture().getStructs().add(struct);
				struct.getSimples().get(0).setValue("newValue");		
			}
		});
		
		Assert.assertTrue(simpleNotification[0]);
	}
	
	@Override
	public void testIsDefaultValue() {
		super.testIsDefaultValue();
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			
			@Override
			public void execute() {
				getFixture().getStructs().add(getFixture().createScaStructProperty());	
			}
		});
		Assert.assertFalse(getFixture().isDefaultValue());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.IPropertyConfigurer#configure(mil.jpeojtrs.sca.cf.DataType) <em>Configure</em>}' operation.
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
	 * Tests the '{@link mil.jpeojtrs.sca.cf.PropertySetOperations#query(mil.jpeojtrs.sca.cf.PropertiesHolder) <em>Query</em>}' operation.
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaStructSequenceProperty#setRemoteValue(mil.jpeojtrs.sca.cf.DataType[][]) <em>Set Remote Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @throws InvalidConfiguration 
	 * @throws PartialConfiguration 
	 * @see gov.redhawk.model.sca.ScaStructSequenceProperty#setRemoteValue(mil.jpeojtrs.sca.cf.DataType[][])
	 * @generated NOT
	 */
	public void testSetRemoteValue__DataType() throws PartialConfiguration, InvalidConfiguration, InterruptedException {
		// END GENERATED CODE
		getFixture().setRemoteValue(getFixture().toAny());
		// BEGIN GENERATED CODE
	}

} //ScaStructSequencePropertyTest

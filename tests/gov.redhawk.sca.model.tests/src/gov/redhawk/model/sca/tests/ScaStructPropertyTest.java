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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.junit.Assert;

import CF.DataType;
import CF.PropertiesHolder;
import CF.UnknownProperties;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import junit.textui.TestRunner;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructRef;
import mil.jpeojtrs.sca.prf.StructValue;
import mil.jpeojtrs.sca.prf.Values;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Struct Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaStructProperty#getSimples() <em>Simples</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaStructProperty#getSimple(java.lang.String) <em>Get Simple</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaStructProperty#getField(java.lang.String) <em>Get Field</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaStructProperty#createPropertyRef() <em>Create Property Ref</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaStructProperty#setValueFromRef(mil.jpeojtrs.sca.prf.StructRef) <em>Set Value From
 * Ref</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaStructProperty#createStructValue() <em>Create Struct Value</em>}</li>
 * <li>{@link CF.PropertySetOperations#configure(CF.DataType[]) <em>Configure</em>}</li>
 * <li>{@link CF.PropertySetOperations#query(CF.PropertiesHolder) <em>Query</em>}</li>
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
		// END GENERATED CODE
		this.env = TestEnvirornment.getInstance();
		final ScaWaveform waveform = this.env.getDomMgr().getWaveformFactories().get(0).createWaveform(null, "name", null, null, RefreshDepth.SELF);
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
		// BEGIN GENERATED CODE
	}

	// END GENERATED CODE

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
				getFixture().eAdapters().add(adapter);
				getFixture().getSimples().get(0).setValue("newValue");
			}
		});

		Assert.assertTrue(simpleNotification[0]);
	}

	// BEGIN GENERATED CODE

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
	public void testFromAny__Any() {
		// TODO: Write a better test. See same method in ScaSimplePropertyTest / ScaSimpleSequencePropertyTest
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().fromAny(getFixture().toAny());
			}
		});
	}

	// BEGIN GENERATED CODE

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
	 * Tests the '{@link gov.redhawk.model.sca.ScaStructProperty#getField(java.lang.String) <em>Get Field</em>}'
	 * operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaStructProperty#getField(java.lang.String)
	 * @generated NOT
	 */
	public void testGetField__String() {
		// END GENERATED CODE
		Assert.assertNull(getFixture().getField(null));
		Assert.assertNotNull(getFixture().getField(getFixture().getFields().get(0).getId()));
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaStructProperty#createPropertyRef() <em>Create Property Ref</em>}'
	 * operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaStructProperty#createPropertyRef()
	 * @generated NOT
	 */
	public void testCreatePropertyRef() {
		// END GENERATED CODE
		final ScaStructProperty prop = getFixture();

		StructRef ref = prop.createPropertyRef();
		Assert.assertEquals(prop.getId(), ref.getRefID());
		Assert.assertEquals(0, ref.getRefs().size());

		ScaModelCommand.execute(prop, new ScaModelCommand() {
			@Override
			public void execute() {
				((ScaSimpleSequenceProperty) prop.getFields().get(1)).setValue(new String[] { "A", "B" });
			}
		});
		ref = prop.createPropertyRef();
		Assert.assertEquals(1, ref.getRefs().size());
		// BEGIN GENERATED CODE
	}

	// END GENERATED CODE

	private ScaStructProperty pre_testSetValueFromRef() {
		final String ID = "abc";
		final String ID_MEMBER1 = "def";
		final String ID_MEMBER2 = "ghi";

		ScaStructProperty prop = ScaFactory.eINSTANCE.createScaStructProperty();
		Struct struct = PrfFactory.eINSTANCE.createStruct();
		struct.setId(ID);
		Simple member1 = PrfFactory.eINSTANCE.createSimple();
		member1.setId(ID_MEMBER1);
		member1.setType(PropertyValueType.LONG);
		member1.setValue("123");
		struct.getSimple().add(member1);
		SimpleSequence member2 = PrfFactory.eINSTANCE.createSimpleSequence();
		member2.setId(ID_MEMBER2);
		member2.setType(PropertyValueType.LONG);
		Values values = PrfFactory.eINSTANCE.createValues("456", "789");
		member2.setValues(values);
		struct.getSimpleSequence().add(member2);
		prop.setDefinition(struct);
		Assert.assertNotNull(prop.getField(ID_MEMBER1));
		Assert.assertEquals(123, prop.getSimple(ID_MEMBER1).getValue());
		Assert.assertNotNull(prop.getField(ID_MEMBER2));
		Assert.assertArrayEquals(new Object[] { 456, 789 }, ((ScaSimpleSequenceProperty) prop.getField(ID_MEMBER2)).getValue());
		Assert.assertTrue(prop.getStatus().isOK());

		return prop;
	}

	public void testSetValueFromRef__AbstractPropertyRef() {
		final String ID = "abc";
		final String ID_MEMBER1 = "def";
		final String ID_MEMBER2 = "ghi";

		ScaStructProperty prop = pre_testSetValueFromRef();

		SimpleRef simpleRef = PrfFactory.eINSTANCE.createSimpleRef(ID, "13579");
		prop.setValueFromRef(simpleRef);
		Assert.assertEquals(123, prop.getSimple(ID_MEMBER1).getValue());
		Assert.assertArrayEquals(new Object[] { 456, 789 }, ((ScaSimpleSequenceProperty) prop.getField(ID_MEMBER2)).getValue());
		Assert.assertFalse(prop.getStatus().isOK());
	}

	// BEGIN GENERATED CODE

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaStructProperty#setValueFromRef(mil.jpeojtrs.sca.prf.StructRef) <em>Set
	 * Value From Ref</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaStructProperty#setValueFromRef(mil.jpeojtrs.sca.prf.StructRef)
	 * @generated NOT
	 */
	public void testSetValueFromRef__StructRef() {
		// END GENERATED CODE
		final String ID = "abc";
		final String ID_MEMBER1 = "def";
		final String ID_MEMBER2 = "ghi";

		ScaStructProperty prop = pre_testSetValueFromRef();

		StructRef structRef = PrfFactory.eINSTANCE.createStructRef(ID);
		SimpleRef simpleRef = PrfFactory.eINSTANCE.createSimpleRef(ID_MEMBER1, "456");
		SimpleSequenceRef simpleSeqRef = PrfFactory.eINSTANCE.createSimpleSequenceRef(ID_MEMBER2, "321");
		structRef.getSimpleRef().add(simpleRef);
		structRef.getSimpleSequenceRef().add(simpleSeqRef);
		prop.setValueFromRef(structRef);
		Assert.assertEquals(456, prop.getSimple(ID_MEMBER1).getValue());
		Assert.assertArrayEquals(new Object[] { 321 }, ((ScaSimpleSequenceProperty) prop.getField(ID_MEMBER2)).getValue());
		Assert.assertTrue(prop.getStatus().isOK());

		simpleRef.setValue("bad");
		prop.setValueFromRef(structRef);
		Assert.assertEquals(456, prop.getSimple(ID_MEMBER1).getValue());
		Assert.assertArrayEquals(new Object[] { 321 }, ((ScaSimpleSequenceProperty) prop.getField(ID_MEMBER2)).getValue());
		Assert.assertFalse(prop.getStatus().isOK());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaStructProperty#createStructValue() <em>Create Struct Value</em>}'
	 * operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaStructProperty#createStructValue()
	 * @generated NOT
	 */
	public void testCreateStructValue() {
		// END GENERATED CODE
		ScaStructProperty prop = getFixture();
		StructValue value = prop.createStructValue();
		Assert.assertEquals(prop.getFields().size(), value.getRefs().size());
		// BEGIN GENERATED CODE
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

	// END GENERATED CODE

	/**
	 * Tests that struct field order is preserved from the definition to the runtime property.
	 */
	public void testStructOrder() {
		int index = 0;
		for (final ScaAbstractProperty< ? > field : getFixture().getFields()) {
			AbstractProperty fieldDefinition = (AbstractProperty) getFixture().getDefinition().getFields().getValue(index);
			Assert.assertEquals(field.getId(), fieldDefinition.getId());
			index++;
		}
	}

	protected void setNewValue() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			@Override
			public void execute() {
				getFixture().getSimple(ScaTestConstaints.DCE_STRUCT_PROP_SIMPLE_FIELD).setValue("test value");
			}
		});
	}

	protected void clearAndResetDefintion() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			@Override
			public void execute() {
				Struct struct = getFixture().getDefinition();
				getFixture().setDefinition(null);
				getFixture().setDefinition(struct);
			}
		});
	}

	// BEGIN GENERATED CODE

} // ScaStructPropertyTest

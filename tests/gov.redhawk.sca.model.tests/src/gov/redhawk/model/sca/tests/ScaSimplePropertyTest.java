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

import org.eclipse.emf.transaction.util.TransactionUtil;
import org.jacorb.JacorbUtil;
import org.junit.Assert;
import org.omg.CORBA.Any;
import org.omg.CORBA.TCKind;

import CF.DataType;
import CF.complexDouble;
import CF.complexDoubleHelper;
import CF.complexFloat;
import CF.complexFloatHelper;
import CF.complexLong;
import CF.complexLongHelper;
import CF.complexLongLong;
import CF.complexLongLongHelper;
import CF.complexOctet;
import CF.complexOctetHelper;
import CF.complexShort;
import CF.complexShortHelper;
import CF.complexULong;
import CF.complexULongHelper;
import CF.complexULongLong;
import CF.complexULongLongHelper;
import CF.complexUShort;
import CF.complexUShortHelper;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import junit.textui.TestRunner;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.StructRef;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Simple Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaSimpleProperty#createPropertyRef() <em>Create Property Ref</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaSimpleProperty#setValueFromRef(mil.jpeojtrs.sca.prf.SimpleRef) <em>Set Value From
 * Ref</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaSimplePropertyTest extends ScaAbstractPropertyTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaSimplePropertyTest.class);
	}

	/**
	 * Constructs a new Simple Property test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaSimplePropertyTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Simple Property test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaSimpleProperty getFixture() {
		return (ScaSimpleProperty) fixture;
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
		final ScaWaveform waveform = this.env.getDomMgr().getWaveformFactories().get(0).createWaveform(null, "name", null, null, RefreshDepth.SELF);
		Assert.assertNotNull(waveform);
		waveform.refresh(null, RefreshDepth.FULL);
		this.env.validateStartState();
		Assert.assertNotNull(waveform);
		ScaModelCommand.execute(this.env.getDomMgr(), new ScaModelCommand() {

			@Override
			public void execute() {
				final ScaComponent kitchenSink = waveform.findComponent(ScaTestConstaints.DCE_KITCHEN_SINK_COMPONENT);
				if (kitchenSink == null && ScaTestsUtil.DEBUG.enabled) {
					ScaTestsUtil.DEBUG.message("Invalid state: {0}", waveform);
				}
				if (kitchenSink == null) {
					return;
				}
				final ScaAbstractProperty< ? > prop = kitchenSink.getProperty(ScaTestConstaints.DCE_SIMPLE_STRING_PROP);
				if (prop == null && ScaTestsUtil.DEBUG.enabled) {
					ScaTestsUtil.DEBUG.message("Invalid state: {0}", kitchenSink);
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

	// END GENERATED CODE

	@Override
	public void testToAny() {
		ScaComponent component = ScaModelCommand.runExclusive(env.getDomMgr(), () -> {
			return env.getDomMgr().getWaveforms().get(0).getScaComponent(ScaTestConstaints.DCE_KITCHEN_SINK_COMPONENT);
		});

		// Test each primitive type
		Any any = component.getProperty("DCE:7e951611-dbc5-45e9-acbb-82366eb38fff").toAny();
		validateAny(any, PropertyValueType.BOOLEAN, false);
		any = component.getProperty("DCE:a96f7e42-a223-40c9-9651-fba2fd1c7736").toAny();
		validateAny(any, PropertyValueType.CHAR, false);
		any = component.getProperty("DCE:4dbea6ec-0ddb-4c70-b54b-78e85e995e08").toAny();
		validateAny(any, PropertyValueType.DOUBLE, false);
		any = component.getProperty("DCE:0e26ba48-1ee9-41e5-9a4b-80841ba5244e").toAny();
		validateAny(any, PropertyValueType.FLOAT, false);
		any = component.getProperty("DCE:7fb3cecd-b229-4b6b-92ba-5968a962c7fc").toAny();
		validateAny(any, PropertyValueType.LONG, false);
		any = component.getProperty("DCE:c365be23-f963-4dc2-bf44-cea7f20c1a8b").toAny();
		validateAny(any, PropertyValueType.LONGLONG, false);
		any = component.getProperty("DCE:b5c3d31d-dca9-45a6-972e-b7a852ef2198").toAny();
		validateAny(any, PropertyValueType.OCTET, false);
		any = component.getProperty("DCE:e4d63120-f72a-430f-86f8-18ea9b6e9f37").toAny();
		validateAny(any, PropertyValueType.SHORT, false);
		any = component.getProperty("DCE:2413dca0-ddd7-4be6-9d39-cfbae210f2fd").toAny();
		validateAny(any, PropertyValueType.STRING, false);
		any = component.getProperty("DCE:37d64150-a679-45ce-ac06-32825a3455f5").toAny();
		validateAny(any, PropertyValueType.ULONG, false);
		any = component.getProperty("simple_ulonglong").toAny();
		validateAny(any, PropertyValueType.ULONGLONG, false);
		any = component.getProperty("DCE:cb88b15a-92a3-47ab-acbe-a021237b1bbd").toAny();
		validateAny(any, PropertyValueType.USHORT, false);

		// Test each complex primitive type
		any = component.getProperty("simple_complexdouble").toAny();
		validateAny(any, PropertyValueType.DOUBLE, true);
		any = component.getProperty("simple_complexfloat").toAny();
		validateAny(any, PropertyValueType.FLOAT, true);
		any = component.getProperty("simple_complexlong").toAny();
		validateAny(any, PropertyValueType.LONG, true);
		any = component.getProperty("simple_complexlonglong").toAny();
		validateAny(any, PropertyValueType.LONGLONG, true);
		any = component.getProperty("simple_complexoctet").toAny();
		validateAny(any, PropertyValueType.OCTET, true);
		any = component.getProperty("simple_complexshort").toAny();
		validateAny(any, PropertyValueType.SHORT, true);
		any = component.getProperty("simple_complexulong").toAny();
		validateAny(any, PropertyValueType.ULONG, true);
		any = component.getProperty("simple_complexulonglong").toAny();
		validateAny(any, PropertyValueType.ULONGLONG, true);
		any = component.getProperty("simple_complexushort").toAny();
		validateAny(any, PropertyValueType.USHORT, true);

		// A property with no value (null) should not return a DataType.
		// This is only used when the SCA model is being used to determine what properties have non-null values for a
		// call to initializeProperties(), configure(), etc.
		ScaSimpleProperty prop = (ScaSimpleProperty) component.getProperty("DCE:7e951611-dbc5-45e9-acbb-82366eb38fff");
		ScaModelCommand.execute(prop, () -> {
			prop.setIgnoreRemoteSet(true);
			prop.setValue(null);
		});
		Assert.assertNull(prop.toAny());
	}

	private void validateAny(Any any, PropertyValueType type, boolean complex) {
		switch (type) {
		case BOOLEAN:
			Assert.assertEquals(TCKind.tk_boolean, any.type().kind());
			Assert.assertEquals(true, any.extract_boolean());
			return;
		case CHAR:
			Assert.assertEquals(TCKind.tk_char, any.type().kind());
			Assert.assertEquals('a', any.extract_char());
			return;
		case DOUBLE:
			if (complex) {
				Assert.assertEquals(complexDoubleHelper.type(), any.type());
				complexDouble cdval = complexDoubleHelper.extract(any);
				Assert.assertEquals(123e45, cdval.real, 1e32);
				Assert.assertEquals(567e89, cdval.imag, 1e76);
				return;
			}
			Assert.assertEquals(TCKind.tk_double, any.type().kind());
			Assert.assertEquals(12345.123151e123, any.extract_double(), 1e112);
			return;
		case FLOAT:
			if (complex) {
				Assert.assertEquals(complexFloatHelper.type(), any.type());
				complexFloat cFval = complexFloatHelper.extract(any);
				Assert.assertEquals(123.4F, cFval.real, 1e-14);
				Assert.assertEquals(-567.8F, cFval.imag, 1e-13);
				return;
			}
			Assert.assertEquals(TCKind.tk_float, any.type().kind());
			Assert.assertEquals(100.123456e12F, any.extract_float(), 1e-2F);
			return;
		case LONG:
			if (complex) {
				Assert.assertEquals(complexLongHelper.type(), any.type());
				complexLong clval = complexLongHelper.extract(any);
				Assert.assertEquals(123456, clval.real);
				Assert.assertEquals(78901, clval.imag);
				return;
			}
			Assert.assertEquals(TCKind.tk_long, any.type().kind());
			Assert.assertEquals(999999999, any.extract_long());
			return;
		case LONGLONG:
			if (complex) {
				Assert.assertEquals(complexLongLongHelper.type(), any.type());
				complexLongLong cllval = complexLongLongHelper.extract(any);
				Assert.assertEquals(1234567890123L, cllval.real);
				Assert.assertEquals(-4567890123456L, cllval.imag);
				return;
			}
			Assert.assertEquals(TCKind.tk_longlong, any.type().kind());
			Assert.assertEquals(999999999999999999L, any.extract_longlong());
			return;
		case OCTET:
			if (complex) {
				Assert.assertEquals(complexOctetHelper.type(), any.type());
				complexOctet coval = complexOctetHelper.extract(any);
				Assert.assertEquals((byte) 78, coval.real);
				Assert.assertEquals((byte) 25, coval.imag);
				return;
			}
			Assert.assertEquals(TCKind.tk_octet, any.type().kind());
			Assert.assertEquals((byte) 123, any.extract_octet());
			return;
		case SHORT:
			if (complex) {
				Assert.assertEquals(complexShortHelper.type(), any.type());
				complexShort csval = complexShortHelper.extract(any);
				Assert.assertEquals((short) 30000, csval.real);
				Assert.assertEquals((short) -1537, csval.imag);
				return;
			}
			Assert.assertEquals(TCKind.tk_short, any.type().kind());
			Assert.assertEquals((short) -10000, any.extract_short());
			return;
		case STRING:
			Assert.assertEquals(TCKind.tk_string, any.type().kind());
			Assert.assertEquals("defaultStringValue", any.extract_string());
			return;
		case ULONG:
			if (complex) {
				Assert.assertEquals(complexULongHelper.type(), any.type());
				complexULong culval = complexULongHelper.extract(any);
				Assert.assertEquals(-1, culval.real); // Actual value is unsigned; expressed as signed here
				Assert.assertEquals(324086, culval.imag);
				return;
			}
			Assert.assertEquals(TCKind.tk_ulong, any.type().kind());
			Assert.assertEquals(1000000000, any.extract_ulong());
			return;
		case ULONGLONG:
			if (complex) {
				Assert.assertEquals(complexULongLongHelper.type(), any.type());
				complexULongLong cullval = complexULongLongHelper.extract(any);
				Assert.assertEquals(-1L, cullval.real); // Actual value is unsigned; expressed as signed here
				Assert.assertEquals(3214623047623046L, cullval.imag);
				return;
			}
			Assert.assertEquals(TCKind.tk_ulonglong, any.type().kind());
			Assert.assertEquals(12345678901234567L, any.extract_ulonglong());
			return;
		case USHORT:
			if (complex) {
				Assert.assertEquals(complexUShortHelper.type(), any.type());
				complexUShort cusval = complexUShortHelper.extract(any);
				Assert.assertEquals((short) -1, cusval.real); // Actual value is unsigned; expressed as signed here
				Assert.assertEquals((short) 12473, cusval.imag);
				return;
			}
			Assert.assertEquals(TCKind.tk_ushort, any.type().kind());
			Assert.assertEquals((short) 10000, any.extract_ushort());
			return;
		default:
			Assert.fail();
			return;
		}
	}

	@Override
	public void testFromAny__Any() {
		// Any with valid string
		Any validValue = JacorbUtil.init().create_any();
		validValue.insert_string("abc");
		ScaModelCommand.execute(getFixture(), () -> {
			getFixture().fromAny(validValue);
		});
		Assert.assertEquals("abc", getFixture().getValue());
		Assert.assertTrue(getFixture().getStatus().isOK());

		// Any with tk_null
		Any nullValue = JacorbUtil.init().create_any();
		ScaModelCommand.execute(getFixture(), () -> {
			getFixture().fromAny(nullValue);
		});
		Assert.assertEquals(null, getFixture().getValue());
		Assert.assertTrue(getFixture().getStatus().isOK());

		// Reset back to good values
		ScaModelCommand.execute(getFixture(), () -> {
			getFixture().fromAny(validValue);
		});

		// Any with non-string
		Any nonArrayValue = JacorbUtil.init().create_any();
		nonArrayValue.insert_long(123);
		ScaModelCommand.execute(getFixture(), () -> {
			getFixture().fromAny(nonArrayValue);
		});
		Assert.assertFalse(getFixture().getStatus().isOK());
	}

	@Override
	public void testGetProperty() {
		ScaComponent component = ScaModelCommand.runExclusive(env.getDomMgr(), () -> {
			return env.getDomMgr().getWaveforms().get(0).getScaComponent(ScaTestConstaints.DCE_KITCHEN_SINK_COMPONENT);
		});

		// Test each primitive type
		DataType dt = component.getProperty("DCE:7e951611-dbc5-45e9-acbb-82366eb38fff").getProperty();
		Assert.assertEquals("DCE:7e951611-dbc5-45e9-acbb-82366eb38fff", dt.id);
		validateAny(dt.value, PropertyValueType.BOOLEAN, false);
		dt = component.getProperty("DCE:a96f7e42-a223-40c9-9651-fba2fd1c7736").getProperty();
		Assert.assertEquals("DCE:a96f7e42-a223-40c9-9651-fba2fd1c7736", dt.id);
		validateAny(dt.value, PropertyValueType.CHAR, false);
		dt = component.getProperty("DCE:4dbea6ec-0ddb-4c70-b54b-78e85e995e08").getProperty();
		Assert.assertEquals("DCE:4dbea6ec-0ddb-4c70-b54b-78e85e995e08", dt.id);
		validateAny(dt.value, PropertyValueType.DOUBLE, false);
		dt = component.getProperty("DCE:0e26ba48-1ee9-41e5-9a4b-80841ba5244e").getProperty();
		Assert.assertEquals("DCE:0e26ba48-1ee9-41e5-9a4b-80841ba5244e", dt.id);
		validateAny(dt.value, PropertyValueType.FLOAT, false);
		dt = component.getProperty("DCE:7fb3cecd-b229-4b6b-92ba-5968a962c7fc").getProperty();
		Assert.assertEquals("DCE:7fb3cecd-b229-4b6b-92ba-5968a962c7fc", dt.id);
		validateAny(dt.value, PropertyValueType.LONG, false);
		dt = component.getProperty("DCE:c365be23-f963-4dc2-bf44-cea7f20c1a8b").getProperty();
		Assert.assertEquals("DCE:c365be23-f963-4dc2-bf44-cea7f20c1a8b", dt.id);
		validateAny(dt.value, PropertyValueType.LONGLONG, false);
		dt = component.getProperty("DCE:b5c3d31d-dca9-45a6-972e-b7a852ef2198").getProperty();
		Assert.assertEquals("DCE:b5c3d31d-dca9-45a6-972e-b7a852ef2198", dt.id);
		validateAny(dt.value, PropertyValueType.OCTET, false);
		dt = component.getProperty("DCE:e4d63120-f72a-430f-86f8-18ea9b6e9f37").getProperty();
		Assert.assertEquals("DCE:e4d63120-f72a-430f-86f8-18ea9b6e9f37", dt.id);
		validateAny(dt.value, PropertyValueType.SHORT, false);
		dt = component.getProperty("DCE:2413dca0-ddd7-4be6-9d39-cfbae210f2fd").getProperty();
		Assert.assertEquals("DCE:2413dca0-ddd7-4be6-9d39-cfbae210f2fd", dt.id);
		validateAny(dt.value, PropertyValueType.STRING, false);
		dt = component.getProperty("DCE:37d64150-a679-45ce-ac06-32825a3455f5").getProperty();
		Assert.assertEquals("DCE:37d64150-a679-45ce-ac06-32825a3455f5", dt.id);
		validateAny(dt.value, PropertyValueType.ULONG, false);
		dt = component.getProperty("simple_ulonglong").getProperty();
		Assert.assertEquals("simple_ulonglong", dt.id);
		validateAny(dt.value, PropertyValueType.ULONGLONG, false);
		dt = component.getProperty("DCE:cb88b15a-92a3-47ab-acbe-a021237b1bbd").getProperty();
		Assert.assertEquals("DCE:cb88b15a-92a3-47ab-acbe-a021237b1bbd", dt.id);
		validateAny(dt.value, PropertyValueType.USHORT, false);

		// Test each complex primitive type
		dt = component.getProperty("simple_complexdouble").getProperty();
		Assert.assertEquals("simple_complexdouble", dt.id);
		validateAny(dt.value, PropertyValueType.DOUBLE, true);
		dt = component.getProperty("simple_complexfloat").getProperty();
		Assert.assertEquals("simple_complexfloat", dt.id);
		validateAny(dt.value, PropertyValueType.FLOAT, true);
		dt = component.getProperty("simple_complexlong").getProperty();
		Assert.assertEquals("simple_complexlong", dt.id);
		validateAny(dt.value, PropertyValueType.LONG, true);
		dt = component.getProperty("simple_complexlonglong").getProperty();
		Assert.assertEquals("simple_complexlonglong", dt.id);
		validateAny(dt.value, PropertyValueType.LONGLONG, true);
		dt = component.getProperty("simple_complexoctet").getProperty();
		Assert.assertEquals("simple_complexoctet", dt.id);
		validateAny(dt.value, PropertyValueType.OCTET, true);
		dt = component.getProperty("simple_complexshort").getProperty();
		Assert.assertEquals("simple_complexshort", dt.id);
		validateAny(dt.value, PropertyValueType.SHORT, true);
		dt = component.getProperty("simple_complexulong").getProperty();
		Assert.assertEquals("simple_complexulong", dt.id);
		validateAny(dt.value, PropertyValueType.ULONG, true);
		dt = component.getProperty("simple_complexulonglong").getProperty();
		Assert.assertEquals("simple_complexulonglong", dt.id);
		validateAny(dt.value, PropertyValueType.ULONGLONG, true);
		dt = component.getProperty("simple_complexushort").getProperty();
		Assert.assertEquals("simple_complexushort", dt.id);
		validateAny(dt.value, PropertyValueType.USHORT, true);

		// A property with no value (null) should not return a DataType.
		// This is only used when the SCA model is being used to determine what properties have non-null values for a
		// call to initializeProperties(), configure(), etc.
		ScaSimpleProperty prop = (ScaSimpleProperty) component.getProperty("DCE:7e951611-dbc5-45e9-acbb-82366eb38fff");
		ScaModelCommand.execute(prop, () -> {
			prop.setIgnoreRemoteSet(true);
			prop.setValue(null);
		});
		Assert.assertNull(prop.getProperty());
	}

	// BEGIN GENERATED CODE

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaSimpleProperty#createPropertyRef() <em>Create Property Ref</em>}'
	 * operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaSimpleProperty#createPropertyRef()
	 * @generated NOT
	 */
	public void testCreatePropertyRef() {
		ScaSimpleProperty prop = getFixture();
		SimpleRef ref = prop.createPropertyRef();
		Assert.assertEquals(prop.getId(), ref.getRefID());
		Assert.assertNotNull(ref.getValue());
	}

	// END GENERATED CODE

	private ScaSimpleProperty pre_testSetValueFromRef() {
		final String ID = "abc";

		ScaSimpleProperty prop = ScaFactory.eINSTANCE.createScaSimpleProperty();
		Simple simple = PrfFactory.eINSTANCE.createSimple();
		simple.setId(ID);
		simple.setType(PropertyValueType.LONG);
		simple.setValue("123");
		prop.setDefinition(simple);
		Assert.assertEquals(123, prop.getValue());
		Assert.assertTrue(prop.getStatus().isOK());

		return prop;
	}

	public void testSetValueFromRef__AbstractPropertyRef() {
		final String ID = "abc";

		ScaSimpleProperty prop = pre_testSetValueFromRef();

		StructRef structRef = PrfFactory.eINSTANCE.createStructRef();
		structRef.setRefID(ID);
		SimpleRef member = PrfFactory.eINSTANCE.createSimpleRef("somememberid", "123");
		structRef.getSimpleRef().add(member);
		prop.setValueFromRef(structRef);
		Assert.assertEquals(123, prop.getValue());
		Assert.assertFalse(prop.getStatus().isOK());
	}

	// BEGIN GENERATED CODE

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaSimpleProperty#setValueFromRef(mil.jpeojtrs.sca.prf.SimpleRef) <em>Set
	 * Value From Ref</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaSimpleProperty#setValueFromRef(mil.jpeojtrs.sca.prf.SimpleRef)
	 * @generated NOT
	 */
	public void testSetValueFromRef__SimpleRef() {
		// END GENERATED CODE
		final String ID = "abc";

		ScaSimpleProperty prop = pre_testSetValueFromRef();

		SimpleRef simpleRef = PrfFactory.eINSTANCE.createSimpleRef(ID, "456");
		prop.setValueFromRef(simpleRef);
		Assert.assertEquals(456, prop.getValue());
		Assert.assertTrue(prop.getStatus().isOK());

		simpleRef = PrfFactory.eINSTANCE.createSimpleRef(ID, "bad");
		prop.setValueFromRef(simpleRef);
		Assert.assertEquals(456, prop.getValue());
		Assert.assertFalse(prop.getStatus().isOK());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaSimpleProperty#getValue() <em>Value</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaSimpleProperty#getValue()
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaSimpleProperty#setValue(java.lang.Object) <em>Value</em>}' feature
	 * setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaSimpleProperty#setValue(java.lang.Object)
	 * @generated NOT
	 */
	public void testSetValue() {
		Assert.assertNotNull(getFixture().getValue());
		final String value = "test";

		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setValue(value);
				Assert.assertEquals(value, getFixture().getValue());
				getFixture().setValue(null);
				Assert.assertNull(getFixture().getValue());
			}
		});
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaSimpleProperty#setRemoteValue(java.lang.Object) <em>Set Remote
	 * Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidConfiguration
	 * @throws PartialConfiguration
	 * @see gov.redhawk.model.sca.ScaSimpleProperty#setRemoteValue(java.lang.Object)
	 * @generated NOT
	 */
	public void testSetRemoteValue__Object() throws PartialConfiguration, InvalidConfiguration {
		// END GENERATED CODE
		getFixture().setRemoteValue(JacorbUtil.init().create_any());
		// BEGIN GENERATED CODE
	}

	protected void setNewValue() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			@Override
			public void execute() {
				getFixture().setValue("temp value");
			}
		});
	}

	protected void clearAndResetDefintion() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			@Override
			public void execute() {
				Simple simple = getFixture().getDefinition();
				getFixture().setDefinition(null);
				getFixture().setDefinition(simple);
			}
		});
	}

} // ScaSimplePropertyTest

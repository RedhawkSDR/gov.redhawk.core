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
import org.omg.CORBA.BooleanSeqHelper;
import org.omg.CORBA.CharSeqHelper;
import org.omg.CORBA.DoubleSeqHelper;
import org.omg.CORBA.FloatSeqHelper;
import org.omg.CORBA.LongLongSeqHelper;
import org.omg.CORBA.LongSeqHelper;
import org.omg.CORBA.OctetSeqHelper;
import org.omg.CORBA.ShortSeqHelper;
import org.omg.CORBA.StringSeqHelper;
import org.omg.CORBA.ULongLongSeqHelper;
import org.omg.CORBA.ULongSeqHelper;
import org.omg.CORBA.UShortSeqHelper;

import CF.DataType;
import CF.complexDouble;
import CF.complexDoubleSeqHelper;
import CF.complexFloat;
import CF.complexFloatSeqHelper;
import CF.complexLong;
import CF.complexLongLong;
import CF.complexLongLongSeqHelper;
import CF.complexLongSeqHelper;
import CF.complexOctet;
import CF.complexOctetSeqHelper;
import CF.complexShort;
import CF.complexShortSeqHelper;
import CF.complexULong;
import CF.complexULongLong;
import CF.complexULongLongSeqHelper;
import CF.complexULongSeqHelper;
import CF.complexUShort;
import CF.complexUShortSeqHelper;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import junit.textui.TestRunner;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.prf.StructRef;
import mil.jpeojtrs.sca.prf.Values;

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
 * <li>{@link gov.redhawk.model.sca.ScaSimpleSequenceProperty#setValueFromRef(mil.jpeojtrs.sca.prf.SimpleSequenceRef)
 * <em>Set Value From Ref</em>}</li>
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
	public void testToAny() {
		ScaComponent component = ScaModelCommand.runExclusive(env.getDomMgr(), () -> {
			return env.getDomMgr().getWaveforms().get(0).getScaComponent(ScaTestConstaints.DCE_KITCHEN_SINK_COMPONENT);
		});

		// Test each primitive sequence type
		Any any = component.getProperty("DCE:3566d9bb-e691-4ab8-9323-fff5d8a8cfb5").toAny();
		validateAny(any, PropertyValueType.BOOLEAN, false);
		any = component.getProperty("DCE:599598f1-e3ee-4c5d-aa0e-e03beaf3d1b7").toAny();
		validateAny(any, PropertyValueType.CHAR, false);
		any = component.getProperty("DCE:64f35c44-175b-4145-ad59-39c777520be7").toAny();
		validateAny(any, PropertyValueType.DOUBLE, false);
		any = component.getProperty("DCE:9653815f-c422-43c0-8e1e-e567dfcea833").toAny();
		validateAny(any, PropertyValueType.FLOAT, false);
		any = component.getProperty("DCE:c47fd56f-71d8-4ec2-9918-9ef88cf57552").toAny();
		validateAny(any, PropertyValueType.LONG, false);
		any = component.getProperty("DCE:e7dc3478-8d74-459a-a25c-93726b0728b6").toAny();
		validateAny(any, PropertyValueType.LONGLONG, false);
		any = component.getProperty("DCE:10cd1587-31b2-406c-84e1-34bb405ab982").toAny();
		validateAny(any, PropertyValueType.OCTET, false);
		any = component.getProperty("DCE:14bce25f-f779-4a94-bb1d-47a387af9fd9").toAny();
		validateAny(any, PropertyValueType.SHORT, false);
		any = component.getProperty("DCE:e4a63886-8bb7-403d-bdd7-3bc79717f5b5").toAny();
		validateAny(any, PropertyValueType.STRING, false);
		any = component.getProperty("DCE:737f2e6c-7534-4af2-ad72-1a13fd6565de").toAny();
		validateAny(any, PropertyValueType.ULONG, false);
		any = component.getProperty("simpleseq_ulonglong").toAny();
		validateAny(any, PropertyValueType.ULONGLONG, false);
		any = component.getProperty("DCE:a7dd9e6b-03c9-4fe5-82a1-ce884b409efe").toAny();
		validateAny(any, PropertyValueType.USHORT, false);

		// Test each complex primitive sequence type
		any = component.getProperty("simpleseq_complexdouble").toAny();
		validateAny(any, PropertyValueType.DOUBLE, true);
		any = component.getProperty("simpleseq_complexfloat").toAny();
		validateAny(any, PropertyValueType.FLOAT, true);
		any = component.getProperty("simpleseq_complexlong").toAny();
		validateAny(any, PropertyValueType.LONG, true);
		any = component.getProperty("simpleseq_complexlonglong").toAny();
		validateAny(any, PropertyValueType.LONGLONG, true);
		any = component.getProperty("simpleseq_complexoctet").toAny();
		validateAny(any, PropertyValueType.OCTET, true);
		any = component.getProperty("simpleseq_complexshort").toAny();
		validateAny(any, PropertyValueType.SHORT, true);
		any = component.getProperty("simpleseq_complexulong").toAny();
		validateAny(any, PropertyValueType.ULONG, true);
		any = component.getProperty("simpleseq_complexulonglong").toAny();
		validateAny(any, PropertyValueType.ULONGLONG, true);
		any = component.getProperty("simpleseq_complexushort").toAny();
		validateAny(any, PropertyValueType.USHORT, true);

		// Simple sequences must have a value - but it can be a zero-length sequence
		ScaSimpleSequenceProperty prop = (ScaSimpleSequenceProperty) component.getProperty("DCE:3566d9bb-e691-4ab8-9323-fff5d8a8cfb5");
		ScaModelCommand.execute(prop, () -> {
			prop.setIgnoreRemoteSet(true);
			prop.setValue(new Object[0]);
		});
		any = prop.toAny();
		Assert.assertEquals(BooleanSeqHelper.type(), any.type());
		Assert.assertEquals(0, BooleanSeqHelper.extract(any).length);
	}

	private void validateAny(Any any, PropertyValueType type, boolean complex) {
		switch (type) {
		case BOOLEAN:
			Assert.assertEquals(BooleanSeqHelper.type(), any.type());
			Assert.assertArrayEquals(new boolean[] { true, false, true }, BooleanSeqHelper.extract(any));
			return;
		case CHAR:
			Assert.assertEquals(CharSeqHelper.type(), any.type());
			Assert.assertArrayEquals(new char[] { 'a', 'b', 'c' }, CharSeqHelper.extract(any));
			return;
		case DOUBLE:
			if (complex) {
				Assert.assertEquals(complexDoubleSeqHelper.type(), any.type());
				complexDouble[] cdval = complexDoubleSeqHelper.extract(any);
				Assert.assertEquals(2, cdval.length);
				Assert.assertEquals(123e45, cdval[0].real, 1e32);
				Assert.assertEquals(567e89, cdval[0].imag, 1e76);
				Assert.assertEquals(1.0, cdval[1].real, 0);
				Assert.assertEquals(-2.0, cdval[1].imag, 0);
				return;
			}
			Assert.assertEquals(DoubleSeqHelper.type(), any.type());
			Assert.assertArrayEquals(new double[] { 100e99, 1.2345e-199, 12345 }, DoubleSeqHelper.extract(any), 0.0);
			return;
		case FLOAT:
			if (complex) {
				Assert.assertEquals(complexFloatSeqHelper.type(), any.type());
				complexFloat[] cfval = complexFloatSeqHelper.extract(any);
				Assert.assertEquals(2, cfval.length);
				Assert.assertEquals(123.4F, cfval[0].real, 1e-14);
				Assert.assertEquals(-567.8F, cfval[0].imag, 1e-13);
				Assert.assertEquals(1.0, cfval[1].real, 0);
				Assert.assertEquals(-2.0, cfval[1].imag, 0);
				return;
			}
			Assert.assertEquals(FloatSeqHelper.type(), any.type());
			Assert.assertArrayEquals(new float[] { 1.23e4F, 0.12e-2F, 1.246F, 1234F }, FloatSeqHelper.extract(any), 0F);
			return;
		case LONG:
			if (complex) {
				Assert.assertEquals(complexLongSeqHelper.type(), any.type());
				complexLong[] clval = complexLongSeqHelper.extract(any);
				Assert.assertEquals(2, clval.length);
				Assert.assertEquals(1000000000, clval[0].real);
				Assert.assertEquals(-1000000000, clval[0].imag);
				Assert.assertEquals(1, clval[1].real);
				Assert.assertEquals(2, clval[1].imag);
				return;
			}
			Assert.assertEquals(LongSeqHelper.type(), any.type());
			Assert.assertArrayEquals(new int[] { 1000000000, -1000000000 }, LongSeqHelper.extract(any));
			return;
		case LONGLONG:
			if (complex) {
				Assert.assertEquals(complexLongLongSeqHelper.type(), any.type());
				complexLongLong[] cllval = complexLongLongSeqHelper.extract(any);
				Assert.assertEquals(1, cllval.length);
				Assert.assertEquals(1234567890123L, cllval[0].real);
				Assert.assertEquals(-4567890123456L, cllval[0].imag);
				return;
			}
			Assert.assertEquals(LongLongSeqHelper.type(), any.type());
			Assert.assertArrayEquals(new long[] { 1000000000000000000L, -1000000000000000000L }, LongLongSeqHelper.extract(any));
			return;
		case OCTET:
			if (complex) {
				Assert.assertEquals(complexOctetSeqHelper.type(), any.type());
				complexOctet[] coval = complexOctetSeqHelper.extract(any);
				Assert.assertEquals(2, coval.length);
				Assert.assertEquals((byte) 78, coval[0].real);
				Assert.assertEquals((byte) 25, coval[0].imag);
				Assert.assertEquals((byte) 2, coval[1].real);
				Assert.assertEquals((byte) 255, coval[1].imag);
				return;
			}
			Assert.assertEquals(OctetSeqHelper.type(), any.type());
			Assert.assertArrayEquals(new byte[] { 12, 22, 127 }, OctetSeqHelper.extract(any));
			return;
		case SHORT:
			if (complex) {
				Assert.assertEquals(complexShortSeqHelper.type(), any.type());
				complexShort[] csval = complexShortSeqHelper.extract(any);
				Assert.assertEquals(2, csval.length);
				Assert.assertEquals((short) 30000, csval[0].real);
				Assert.assertEquals((short) -1537, csval[0].imag);
				Assert.assertEquals((short) -1, csval[1].real);
				Assert.assertEquals((short) 234, csval[1].imag);
				return;
			}
			Assert.assertEquals(ShortSeqHelper.type(), any.type());
			Assert.assertArrayEquals(new short[] { 10000, -10000, 10003 }, ShortSeqHelper.extract(any));
			return;
		case STRING:
			Assert.assertEquals(StringSeqHelper.type(), any.type());
			Assert.assertArrayEquals(new String[] { "str1", "str2", "str3" }, StringSeqHelper.extract(any));
			return;
		case ULONG:
			if (complex) {
				Assert.assertEquals(complexULongSeqHelper.type(), any.type());
				complexULong[] culval = complexULongSeqHelper.extract(any);
				Assert.assertEquals(2, culval.length);
				Assert.assertEquals(-1, culval[0].real);
				Assert.assertEquals(324086, culval[0].imag);
				Assert.assertEquals(1, culval[1].real);
				Assert.assertEquals(2, culval[1].imag);
				return;
			}
			Assert.assertEquals(ULongSeqHelper.type(), any.type());
			Assert.assertArrayEquals(new int[] { 1000000000, 1000000001, 1000000002 }, ULongSeqHelper.extract(any));
			return;
		case ULONGLONG:
			if (complex) {
				Assert.assertEquals(complexULongLongSeqHelper.type(), any.type());
				complexULongLong[] cullval = complexULongLongSeqHelper.extract(any);
				Assert.assertEquals(1, cullval.length);
				Assert.assertEquals(-1L, cullval[0].real);
				Assert.assertEquals(3214623047623046L, cullval[0].imag);
				return;
			}
			Assert.assertEquals(ULongLongSeqHelper.type(), any.type());
			Assert.assertArrayEquals(new long[] { -1, 3214623047623046L }, ULongLongSeqHelper.extract(any));
			return;
		case USHORT:
			if (complex) {
				Assert.assertEquals(complexUShortSeqHelper.type(), any.type());
				complexUShort[] cusval = complexUShortSeqHelper.extract(any);
				Assert.assertEquals(2, cusval.length);
				Assert.assertEquals((short) 65535, cusval[0].real);
				Assert.assertEquals((short) 12473, cusval[0].imag);
				Assert.assertEquals((short) 1, cusval[1].real);
				Assert.assertEquals((short) 2, cusval[1].imag);
				return;
			}
			Assert.assertEquals(UShortSeqHelper.type(), any.type());
			Assert.assertArrayEquals(new short[] { 10000, 10001, 10002 }, UShortSeqHelper.extract(any));
			return;
		default:
			Assert.fail();
			return;
		}
	}

	@Override
	public void testFromAny__Any() {
		// Any with valid string sequence
		Any validValues = JacorbUtil.init().create_any();
		StringSeqHelper.insert(validValues, new String[] { "abc", "def" });
		ScaModelCommand.execute(getFixture(), () -> {
			getFixture().fromAny(validValues);
		});
		Assert.assertEquals(2, getFixture().getValues().size());
		Assert.assertEquals("abc", getFixture().getValues().get(0));
		Assert.assertEquals("def", getFixture().getValues().get(1));
		Assert.assertTrue(getFixture().getStatus().isOK());

		// Any with tk_null
		Any nullValue = JacorbUtil.init().create_any();
		ScaModelCommand.execute(getFixture(), () -> {
			getFixture().fromAny(nullValue);
		});
		Assert.assertEquals(0, getFixture().getValues().size());
		Assert.assertTrue(getFixture().getStatus().isOK());

		// Reset back to good values
		ScaModelCommand.execute(getFixture(), () -> {
			getFixture().fromAny(validValues);
		});

		// Any with zero-length sequence
		Any zeroLen = JacorbUtil.init().create_any();
		StringSeqHelper.insert(zeroLen, new String[] {});
		ScaModelCommand.execute(getFixture(), () -> {
			getFixture().fromAny(zeroLen);
		});
		Assert.assertEquals(getFixture().getValues().size(), 0);
		Assert.assertTrue(getFixture().getStatus().isOK());

		// Reset to having some good values
		ScaModelCommand.execute(getFixture(), () -> {
			getFixture().fromAny(validValues);
		});

		// Any with non-sequence
		Any nonArrayValue = JacorbUtil.init().create_any();
		nonArrayValue.insert_string("abc");
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

		// Test each primitive sequence type
		DataType dt = component.getProperty("DCE:3566d9bb-e691-4ab8-9323-fff5d8a8cfb5").getProperty();
		Assert.assertEquals("DCE:3566d9bb-e691-4ab8-9323-fff5d8a8cfb5", dt.id);
		validateAny(dt.value, PropertyValueType.BOOLEAN, false);
		dt = component.getProperty("DCE:599598f1-e3ee-4c5d-aa0e-e03beaf3d1b7").getProperty();
		Assert.assertEquals("DCE:599598f1-e3ee-4c5d-aa0e-e03beaf3d1b7", dt.id);
		validateAny(dt.value, PropertyValueType.CHAR, false);
		dt = component.getProperty("DCE:64f35c44-175b-4145-ad59-39c777520be7").getProperty();
		Assert.assertEquals("DCE:64f35c44-175b-4145-ad59-39c777520be7", dt.id);
		validateAny(dt.value, PropertyValueType.DOUBLE, false);
		dt = component.getProperty("DCE:9653815f-c422-43c0-8e1e-e567dfcea833").getProperty();
		Assert.assertEquals("DCE:9653815f-c422-43c0-8e1e-e567dfcea833", dt.id);
		validateAny(dt.value, PropertyValueType.FLOAT, false);
		dt = component.getProperty("DCE:c47fd56f-71d8-4ec2-9918-9ef88cf57552").getProperty();
		Assert.assertEquals("DCE:c47fd56f-71d8-4ec2-9918-9ef88cf57552", dt.id);
		validateAny(dt.value, PropertyValueType.LONG, false);
		dt = component.getProperty("DCE:e7dc3478-8d74-459a-a25c-93726b0728b6").getProperty();
		Assert.assertEquals("DCE:e7dc3478-8d74-459a-a25c-93726b0728b6", dt.id);
		validateAny(dt.value, PropertyValueType.LONGLONG, false);
		dt = component.getProperty("DCE:10cd1587-31b2-406c-84e1-34bb405ab982").getProperty();
		Assert.assertEquals("DCE:10cd1587-31b2-406c-84e1-34bb405ab982", dt.id);
		validateAny(dt.value, PropertyValueType.OCTET, false);
		dt = component.getProperty("DCE:14bce25f-f779-4a94-bb1d-47a387af9fd9").getProperty();
		Assert.assertEquals("DCE:14bce25f-f779-4a94-bb1d-47a387af9fd9", dt.id);
		validateAny(dt.value, PropertyValueType.SHORT, false);
		dt = component.getProperty("DCE:e4a63886-8bb7-403d-bdd7-3bc79717f5b5").getProperty();
		Assert.assertEquals("DCE:e4a63886-8bb7-403d-bdd7-3bc79717f5b5", dt.id);
		validateAny(dt.value, PropertyValueType.STRING, false);
		dt = component.getProperty("DCE:737f2e6c-7534-4af2-ad72-1a13fd6565de").getProperty();
		Assert.assertEquals("DCE:737f2e6c-7534-4af2-ad72-1a13fd6565de", dt.id);
		validateAny(dt.value, PropertyValueType.ULONG, false);
		dt = component.getProperty("simpleseq_ulonglong").getProperty();
		Assert.assertEquals("simpleseq_ulonglong", dt.id);
		validateAny(dt.value, PropertyValueType.ULONGLONG, false);
		dt = component.getProperty("DCE:a7dd9e6b-03c9-4fe5-82a1-ce884b409efe").getProperty();
		Assert.assertEquals("DCE:a7dd9e6b-03c9-4fe5-82a1-ce884b409efe", dt.id);
		validateAny(dt.value, PropertyValueType.USHORT, false);

		// Test each complex primitive sequence type
		dt = component.getProperty("simpleseq_complexdouble").getProperty();
		Assert.assertEquals("simpleseq_complexdouble", dt.id);
		validateAny(dt.value, PropertyValueType.DOUBLE, true);
		dt = component.getProperty("simpleseq_complexfloat").getProperty();
		Assert.assertEquals("simpleseq_complexfloat", dt.id);
		validateAny(dt.value, PropertyValueType.FLOAT, true);
		dt = component.getProperty("simpleseq_complexlong").getProperty();
		Assert.assertEquals("simpleseq_complexlong", dt.id);
		validateAny(dt.value, PropertyValueType.LONG, true);
		dt = component.getProperty("simpleseq_complexlonglong").getProperty();
		Assert.assertEquals("simpleseq_complexlonglong", dt.id);
		validateAny(dt.value, PropertyValueType.LONGLONG, true);
		dt = component.getProperty("simpleseq_complexoctet").getProperty();
		Assert.assertEquals("simpleseq_complexoctet", dt.id);
		validateAny(dt.value, PropertyValueType.OCTET, true);
		dt = component.getProperty("simpleseq_complexshort").getProperty();
		Assert.assertEquals("simpleseq_complexshort", dt.id);
		validateAny(dt.value, PropertyValueType.SHORT, true);
		dt = component.getProperty("simpleseq_complexulong").getProperty();
		Assert.assertEquals("simpleseq_complexulong", dt.id);
		validateAny(dt.value, PropertyValueType.ULONG, true);
		dt = component.getProperty("simpleseq_complexulonglong").getProperty();
		Assert.assertEquals("simpleseq_complexulonglong", dt.id);
		validateAny(dt.value, PropertyValueType.ULONGLONG, true);
		dt = component.getProperty("simpleseq_complexushort").getProperty();
		Assert.assertEquals("simpleseq_complexushort", dt.id);
		validateAny(dt.value, PropertyValueType.USHORT, true);

		// Simple sequences must have a value - but it can be a zero-length sequence
		ScaSimpleSequenceProperty prop = (ScaSimpleSequenceProperty) component.getProperty("DCE:3566d9bb-e691-4ab8-9323-fff5d8a8cfb5");
		ScaModelCommand.execute(prop, () -> {
			prop.setIgnoreRemoteSet(true);
			prop.setValue(null);
		});
		dt = prop.getProperty();
		Assert.assertEquals("DCE:3566d9bb-e691-4ab8-9323-fff5d8a8cfb5", dt.id);
		Assert.assertEquals(BooleanSeqHelper.type(), dt.value.type());
		Assert.assertEquals(0, BooleanSeqHelper.extract(dt.value).length);
	}

	// BEGIN GENERATED CODE

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
		// END GENERATED CODE
		ScaSimpleSequenceProperty prop = getFixture();
		SimpleSequenceRef ref = prop.createPropertyRef();
		Assert.assertEquals(prop.getId(), ref.getRefID());
		Assert.assertEquals(prop.getValues().size(), ref.getValues().getValue().size());
		// BEGIN GENERATED CODE
	}

	// END GENERATED CODE

	private ScaSimpleSequenceProperty pre_testSetValueFromRef() {
		final String ID = "abc";

		ScaSimpleSequenceProperty prop = ScaFactory.eINSTANCE.createScaSimpleSequenceProperty();
		SimpleSequence simpleSeq = PrfFactory.eINSTANCE.createSimpleSequence();
		simpleSeq.setId(ID);
		simpleSeq.setType(PropertyValueType.LONG);
		Values values = PrfFactory.eINSTANCE.createValues("123", "456");
		simpleSeq.setValues(values);
		prop.setDefinition(simpleSeq);
		Assert.assertArrayEquals(new Object[] { 123, 456 }, prop.getValue());
		Assert.assertTrue(prop.getStatus().isOK());

		return prop;
	}

	public void testSetValueFromRef__AbstractPropertyRef() {
		final String ID = "abc";

		ScaSimpleSequenceProperty prop = pre_testSetValueFromRef();

		StructRef structRef = PrfFactory.eINSTANCE.createStructRef();
		structRef.setRefID(ID);
		SimpleRef member = PrfFactory.eINSTANCE.createSimpleRef("somememberid", "123");
		structRef.getSimpleRef().add(member);
		prop.setValueFromRef(structRef);
		Assert.assertArrayEquals(new Object[] { 123, 456 }, prop.getValue());
		Assert.assertFalse(prop.getStatus().isOK());
	}

	// BEGIN GENERATED CODE

	/**
	 * Tests the
	 * '{@link gov.redhawk.model.sca.ScaSimpleSequenceProperty#setValueFromRef(mil.jpeojtrs.sca.prf.SimpleSequenceRef)
	 * <em>Set Value From Ref</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaSimpleSequenceProperty#setValueFromRef(mil.jpeojtrs.sca.prf.SimpleSequenceRef)
	 * @generated NOT
	 */
	public void testSetValueFromRef__SimpleSequenceRef() {
		// END GENERATED CODE
		final String ID = "abc";

		ScaSimpleSequenceProperty prop = pre_testSetValueFromRef();

		SimpleSequenceRef simpleSeqRef = PrfFactory.eINSTANCE.createSimpleSequenceRef(ID, "789");
		prop.setValueFromRef(simpleSeqRef);
		Assert.assertArrayEquals(new Object[] { 789 }, prop.getValue());
		Assert.assertTrue(prop.getStatus().isOK());

		simpleSeqRef = PrfFactory.eINSTANCE.createSimpleSequenceRef(ID, "bad");
		prop.setValueFromRef(simpleSeqRef);
		Assert.assertArrayEquals(new Object[] { 789 }, prop.getValue());
		Assert.assertFalse(prop.getStatus().isOK());
		// BEGIN GENERATED CODE
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

	// END GENERATED CODE

	protected void setNewValue() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			@Override
			public void execute() {
				getFixture().setValue(new String[] { "temp value", "one more temp value" });
			}
		});
	}

	protected void clearAndResetDefintion() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			@Override
			public void execute() {
				SimpleSequence simpleSeq = getFixture().getDefinition();
				getFixture().setDefinition(null);
				getFixture().setDefinition(simpleSeq);
			}
		});
	}

} // ScaSimpleSequencePropertyTest

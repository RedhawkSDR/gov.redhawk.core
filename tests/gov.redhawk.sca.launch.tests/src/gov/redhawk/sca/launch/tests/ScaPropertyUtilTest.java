package gov.redhawk.sca.launch.tests;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.Arrays;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.Assert;
import org.junit.Test;
import org.osgi.framework.FrameworkUtil;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.sca.launch.internal.ScaPropertyUtil;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.util.ScaResourceFactoryUtil;
import mil.jpeojtrs.sca.util.math.ComplexByte;
import mil.jpeojtrs.sca.util.math.ComplexDouble;
import mil.jpeojtrs.sca.util.math.ComplexFloat;
import mil.jpeojtrs.sca.util.math.ComplexLong;
import mil.jpeojtrs.sca.util.math.ComplexLongLong;
import mil.jpeojtrs.sca.util.math.ComplexShort;
import mil.jpeojtrs.sca.util.math.ComplexULong;
import mil.jpeojtrs.sca.util.math.ComplexULongLong;
import mil.jpeojtrs.sca.util.math.ComplexUShort;
import mil.jpeojtrs.sca.util.time.UTCTime;

/**
 * Tests the {@link #ScaPropertyUtil} class's serializing/de-serializing of
 * property values.
 */
public class ScaPropertyUtilTest {

	@Test
	public void simple_props() throws IOException {
		// Create two components with our test properties
		String spdPath = "/testFiles/simple.spd.xml";
		ScaComponent component1 = loadComponent(spdPath);
		ScaComponent component2 = loadComponent(spdPath);

		// Verify properties are the same
		assertProps(component1, component2, true);

		// Change component 1's properties
		((ScaSimpleProperty) component1.getProperty("boolean")).setValue(Boolean.TRUE);
		((ScaSimpleProperty) component1.getProperty("char")).setValue(new Character('!'));
		((ScaSimpleProperty) component1.getProperty("double")).setValue(new Double(1.2));
		((ScaSimpleProperty) component1.getProperty("float")).setValue(new Float(3.4f));
		((ScaSimpleProperty) component1.getProperty("long")).setValue(new Integer(5));
		((ScaSimpleProperty) component1.getProperty("longlong")).setValue(new Long(6));
		((ScaSimpleProperty) component1.getProperty("octet")).setValue(new Short((short) 7));
		((ScaSimpleProperty) component1.getProperty("short")).setValue(new Short((short) 8));
		((ScaSimpleProperty) component1.getProperty("string")).setValue("abc");
		((ScaSimpleProperty) component1.getProperty("ulong")).setValue(new Long(8));
		((ScaSimpleProperty) component1.getProperty("ulonglong")).setValue(new BigInteger("9"));
		((ScaSimpleProperty) component1.getProperty("ushort")).setValue(new Integer(10));
		((ScaSimpleProperty) component1.getProperty("utctime")).setValue(new UTCTime((short) 1, 2.0, 0.3));

		// Verify properties are different
		assertProps(component1, component2, false);

		// Serialize component 1, deserialize into component 2
		String serializedProps = ScaPropertyUtil.save(component1);
		ScaPropertyUtil.load(component2, serializedProps);

		// Verify properties are the same
		assertProps(component1, component2, true);
	}

	@Test
	public void simple_sequence_props() throws IOException {
		// Create two components with our test properties
		String spdPath = "/testFiles/simple_sequence.spd.xml";
		ScaComponent component1 = loadComponent(spdPath);
		ScaComponent component2 = loadComponent(spdPath);

		// Verify properties are the same
		assertProps(component1, component2, true);

		// Change component 1's properties
		((ScaSimpleSequenceProperty) component1.getProperty("boolean")).setValue(new Object[] { Boolean.TRUE, Boolean.FALSE });
		((ScaSimpleSequenceProperty) component1.getProperty("char")).setValue(new Object[] { new Character('!'), new Character('*') });
		((ScaSimpleSequenceProperty) component1.getProperty("double")).setValue(new Object[] { new Double(1.2), new Double(3.4) });
		((ScaSimpleSequenceProperty) component1.getProperty("float")).setValue(new Object[] { new Float(5.6f), new Float(7.8f) });
		((ScaSimpleSequenceProperty) component1.getProperty("long")).setValue(new Object[] { new Integer(9), new Integer(10) });
		((ScaSimpleSequenceProperty) component1.getProperty("longlong")).setValue(new Object[] { new Long(11), new Long(12) });
		((ScaSimpleSequenceProperty) component1.getProperty("octet")).setValue(new Object[] { new Short((short) 13), new Short((short) 14) });
		((ScaSimpleSequenceProperty) component1.getProperty("short")).setValue(new Object[] { new Short((short) 15), new Short((short) 16) });
		((ScaSimpleSequenceProperty) component1.getProperty("string")).setValue(new Object[] { "abc", "def" });
		((ScaSimpleSequenceProperty) component1.getProperty("ulong")).setValue(new Object[] { new Long(17), new Long(18) });
		((ScaSimpleSequenceProperty) component1.getProperty("ulonglong")).setValue(new Object[] { new BigInteger("19"), new BigInteger("20") });
		((ScaSimpleSequenceProperty) component1.getProperty("ushort")).setValue(new Object[] { new Integer(21), new Integer(22) });
		((ScaSimpleSequenceProperty) component1.getProperty("utctime")).setValue(
			new Object[] { new UTCTime((short) 1, 2.0, 0.3), new UTCTime((short) 1, 4.0, 0.5) });

		// Verify properties are different
		assertProps(component1, component2, false);

		// Serialize component 1, deserialize into component 2
		String serializedProps = ScaPropertyUtil.save(component1);
		ScaPropertyUtil.load(component2, serializedProps);

		// Verify properties are the same
		assertProps(component1, component2, true);
	}

	@Test
	public void complex_simple_props() throws IOException {
		// Create two components with our test properties
		String spdPath = "/testFiles/complex_simple.spd.xml";
		ScaComponent component1 = loadComponent(spdPath);
		ScaComponent component2 = loadComponent(spdPath);

		// Verify properties are the same
		assertProps(component1, component2, true);

		// Change component 1's properties
		((ScaSimpleProperty) component1.getProperty("double")).setValue(new ComplexDouble(1.0, 2.0));
		((ScaSimpleProperty) component1.getProperty("float")).setValue(new ComplexFloat(3.0f, 4.0f));
		((ScaSimpleProperty) component1.getProperty("long")).setValue(new ComplexLong(5, 6));
		((ScaSimpleProperty) component1.getProperty("longlong")).setValue(new ComplexLongLong(7, 8));
		((ScaSimpleProperty) component1.getProperty("octet")).setValue(new ComplexByte((byte) 9, (byte) 10));
		((ScaSimpleProperty) component1.getProperty("short")).setValue(new ComplexShort((short) 11, (short) 12));
		((ScaSimpleProperty) component1.getProperty("ulong")).setValue(new ComplexULong(13, 14));
		((ScaSimpleProperty) component1.getProperty("ulonglong")).setValue(new ComplexULongLong(new BigInteger("15"), new BigInteger("16")));
		((ScaSimpleProperty) component1.getProperty("ushort")).setValue(new ComplexUShort(17, 18));

		// Verify properties are different
		assertProps(component1, component2, false);

		// Serialize component 1, deserialize into component 2
		String serializedProps = ScaPropertyUtil.save(component1);
		ScaPropertyUtil.load(component2, serializedProps);

		// Verify properties are the same
		assertProps(component1, component2, true);
	}

	@Test
	public void complex_sequence_props() throws IOException {
		// Create two components with our test properties
		String spdPath = "/testFiles/complex_sequence.spd.xml";
		ScaComponent component1 = loadComponent(spdPath);
		ScaComponent component2 = loadComponent(spdPath);

		// Verify properties are the same
		assertProps(component1, component2, true);

		// Change component 1's properties
		((ScaSimpleSequenceProperty) component1.getProperty("doubleseq")).setValue(new Object[] { new ComplexDouble(1.0, 2.0) });
		((ScaSimpleSequenceProperty) component1.getProperty("floatseq")).setValue(new Object[] { new ComplexFloat(3.0f, 4.0f) });
		((ScaSimpleSequenceProperty) component1.getProperty("longseq")).setValue(new Object[] { new ComplexLong(5, 6) });
		((ScaSimpleSequenceProperty) component1.getProperty("longlongseq")).setValue(new Object[] { new ComplexLongLong(7, 8) });
		((ScaSimpleSequenceProperty) component1.getProperty("octetseq")).setValue(new Object[] { new ComplexByte((byte) 9, (byte) 10) });
		((ScaSimpleSequenceProperty) component1.getProperty("shortseq")).setValue(new Object[] { new ComplexShort((short) 11, (short) 12) });
		((ScaSimpleSequenceProperty) component1.getProperty("ulongseq")).setValue(new Object[] { new ComplexULong(13, 14) });
		((ScaSimpleSequenceProperty) component1.getProperty("ulonglongseq")).setValue(
			new Object[] { new ComplexULongLong(new BigInteger("15"), new BigInteger("16")) });
		((ScaSimpleSequenceProperty) component1.getProperty("ushortseq")).setValue(new Object[] { new ComplexUShort(17, 18) });

		// Verify properties are differen
		assertProps(component1, component2, false);

		// Serialize component 1, deserialize into component 2
		String serializedProps = ScaPropertyUtil.save(component1);
		ScaPropertyUtil.load(component2, serializedProps);

		// Verify properties are the same
		assertProps(component1, component2, true);
	}

	private URI getURI(String bundleRelatativePath) throws IOException {
		final URL url = FileLocator.toFileURL(FileLocator.find(FrameworkUtil.getBundle(getClass()), new Path(bundleRelatativePath), null));
		return URI.createURI(url.toString());
	}

	/**
	 * Creates a component in its own ResourceSet
	 * 
	 * @parm spdPath the bundle-relative SPD path
	 * @return
	 * @throws IOException
	 */
	private ScaComponent loadComponent(String spdPath) throws IOException {
		// Load the profile
		ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();
		Resource resource = resourceSet.getResource(getURI(spdPath), true);
		SoftPkg spd = SoftPkg.Util.getSoftPkg(resource);
		spd.getPropertyFile().getProperties();

		// Create an SCA model object with the component profile we loaded, load
		// properties
		ScaComponent component = ScaFactory.eINSTANCE.createScaComponent();
		component.setProfileObj(spd);
		for (final ScaAbstractProperty< ? > prop : component.fetchProperties(null)) {
			prop.setIgnoreRemoteSet(true);
		}

		return component;
	}

	private void assertProps(ScaComponent component1, ScaComponent component2, boolean equal) {
		for (final ScaAbstractProperty< ? > prop : component1.getProperties()) {
			switch (prop.eClass().getClassifierID()) {
			case ScaPackage.SCA_SIMPLE_PROPERTY:
				ScaSimpleProperty prop1 = (ScaSimpleProperty) prop;
				ScaSimpleProperty prop2 = (ScaSimpleProperty) component2.getProperty(prop.getId());
				if (equal) {
					String msg = String.format("Values don't match for property %s", prop.getId());
					Assert.assertEquals(msg, prop1.getValue(), prop2.getValue());
				} else {
					String msg = String.format("Values should NOT match (but do) for property %s", prop.getId());
					Assert.assertFalse(msg, prop1.getValue().equals(prop2.getValue()));
				}
				break;
			case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY:
				ScaSimpleSequenceProperty propSeq1 = (ScaSimpleSequenceProperty) prop;
				ScaSimpleSequenceProperty propSeq2 = (ScaSimpleSequenceProperty) component2.getProperty(prop.getId());
				if (equal) {
					String msg = String.format("Values don't match for property %s", prop.getId());
					Assert.assertArrayEquals(msg, propSeq1.getValue(), propSeq2.getValue());
				} else {
					String msg = String.format("Values don't match for property %s", prop.getId());
					Assert.assertFalse(msg, Arrays.deepEquals(propSeq1.getValue(), propSeq2.getValue()));
				}
				break;
			}
		}
	}

}

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

/**
 * Tests the {@link #ScaPropertyUtil} class's serializing/de-serializing of
 * property values.
 */
public class ScaPropertyUtilTest {

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

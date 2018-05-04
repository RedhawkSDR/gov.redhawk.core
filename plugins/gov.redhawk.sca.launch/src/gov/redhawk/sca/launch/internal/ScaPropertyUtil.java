/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.sca.launch.internal;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility methods to load/save properties for a component to/from a string. Allows passing this information through
 * Eclipse launch configurations. The format is NOT portable - load and save must occur through this class.
 */
public class ScaPropertyUtil {

	private ScaPropertyUtil() {
	}

	/**
	 * Loads a set of properties from a string of XML.
	 * @param component A property container (component)
	 * @param serializedValue The serialized string
	 */
	public static void load(final ScaPropertyContainer< ? , ? > component, final String serializedValue) {
		if (component == null || serializedValue == null) {
			return;
		}
		try (XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(serializedValue.getBytes()))) {
			final Map< ? , ? > propMap = (Map< ? , ? >) decoder.readObject();
			ScaPropertyUtil.restoreProperties(component, propMap);
		}
	}

	/**
	 * Saves a set of properties to a string of XML. Only properties with non-default values are saved.
	 * @param component A property container (component)
	 * @return The serialized string
	 */
	public static String save(final ScaPropertyContainer<?, ?> component) {
		if (component != null) {
			final Map< ? , ? > propMap = ScaPropertyUtil.storeProperties(component);

			if (propMap.isEmpty()) {
				return null;
			}

			ByteArrayOutputStream buffer = null;
			XMLEncoder encoder = null;
			buffer = new ByteArrayOutputStream();
			encoder = new XMLEncoder(buffer);
			encoder.setPersistenceDelegate(BigInteger.class, new BigIntegerPersistenceDelegate());
			encoder.writeObject(propMap);

			encoder.close();
			try {
				buffer.close();
			} catch (IOException e) {
				// PASS
			}

			return buffer.toString();
		} else {
			return null;
		}
	}

	/**
	 * Allows persisting {@link BigInteger}s with the java.beans system.
	 */
	private static class BigIntegerPersistenceDelegate extends PersistenceDelegate {

		public BigIntegerPersistenceDelegate() {
		}

		@Override
		protected Expression instantiate(Object oldInstance, Encoder out) {
			BigInteger bigInt = (BigInteger) oldInstance;
			return new Expression(bigInt, BigInteger.class, "new", new Object[] { bigInt.toString() });
		}
	}

	private static Map< ? , ? > storeProperties(final ScaPropertyContainer< ?, ? > component) {
		final Map<Object, Object> propMap = new HashMap<Object, Object>();
		for (final ScaAbstractProperty< ? > prop : component.getProperties()) {
			if (prop.isDefaultValue()) {
				continue;
			}
			Object value = ScaPropertyUtil.storeProperty(prop);
			propMap.put(prop.getId(), value);
		}
		return propMap;
	}

	private static Object storeProperty(final ScaAbstractProperty< ? > prop) {
		switch (prop.eClass().getClassifierID()) {
		case ScaPackage.SCA_SIMPLE_PROPERTY:
			return ((ScaSimpleProperty) prop).getValue();
		case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY:
			return ((ScaSimpleSequenceProperty) prop).getValue();
		case ScaPackage.SCA_STRUCT_PROPERTY:
			return ScaPropertyUtil.storeStruct((ScaStructProperty) prop);
		case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY:
			return ScaPropertyUtil.storeStructSequence((ScaStructSequenceProperty) prop);
		default:
			throw new IllegalArgumentException("Unknown property type");
		}
	}

	private static Map< ? , ? > storeStruct(final ScaStructProperty struct) {
		final HashMap<Object, Object> retVal = new HashMap<Object, Object>();
		for (final ScaAbstractProperty< ? > prop : struct.getFields()) {
			if (prop.isDefaultValue()) {
				continue;
			}
			retVal.put(prop.getId(), ScaPropertyUtil.storeProperty(prop));
		}
		return retVal;
	}

	private static List< ? > storeStructSequence(final ScaStructSequenceProperty prop) {
		final ArrayList<Object> retVal = new ArrayList<Object>();
		for (final ScaStructProperty struct : prop.getStructs()) {
			retVal.add(ScaPropertyUtil.storeStruct(struct));
		}
		return retVal;
	}

	private static void restoreProperties(final ScaPropertyContainer< ? , ? > component, final Map< ? , ? > propMap) {
		for (final ScaAbstractProperty< ? > prop : component.getProperties()) {
			if (!propMap.containsKey(prop.getId())) {
				continue;
			}
			final Object value = propMap.get(prop.getId());
			ScaPropertyUtil.restoreProperty(prop, value);
		}
	}

	private static void restoreProperty(final ScaAbstractProperty< ? > prop, Object value) {
		switch (prop.eClass().getClassifierID()) {
		case ScaPackage.SCA_SIMPLE_PROPERTY:
			((ScaSimpleProperty) prop).setValue(value);
			break;
		case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY:
			((ScaSimpleSequenceProperty) prop).setValue((Object[]) value);
			break;
		case ScaPackage.SCA_STRUCT_PROPERTY:
			ScaPropertyUtil.restoreStruct((ScaStructProperty) prop, (Map< ? , ? >) value);
			break;
		case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY:
			ScaPropertyUtil.restoreStructSequence((ScaStructSequenceProperty) prop, (List< ? >) value);
			break;
		default:
			throw new IllegalArgumentException("Unknown property type");
		}
	}

	private static void restoreStructSequence(final ScaStructSequenceProperty prop, final List< ? > values) {
		if (values != null) {
			prop.getStructs().clear();
			for (final Object obj : values) {
				final ScaStructProperty struct = prop.createScaStructProperty();
				ScaPropertyUtil.restoreStruct(struct, (Map< ? , ? >) obj);
				prop.getStructs().add(struct);
			}
		}
	}

	private static void restoreStruct(final ScaStructProperty struct, final Map< ? , ? > propMap) {
		if (propMap != null) {
			for (final ScaAbstractProperty< ? > prop : struct.getFields()) {
				if (propMap.containsKey(prop.getId())) {
					final Object value = propMap.get(prop.getId());
					ScaPropertyUtil.restoreProperty(prop, value);
				}
			}
		}
	}
}

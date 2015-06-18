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
package gov.redhawk.sca.launch;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility methods to load/save properties for a component to/from a string. Allows passing this information through
 * Eclipse launch configurations. The format is NOT portable - load and save must occur through this class.
 * @since 8.0
 */
class ScaPropertyUtil {

	private ScaPropertyUtil() {
	}

	/**
	 * Loads a set of properties from a string of XML.
	 * @param component A property container (component)
	 * @param serializedValue The serialized string
	 */
	public static void load(final ScaPropertyContainer< ? , ? > component, final String serializedValue) {
		if (component != null && serializedValue != null) {
			final XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(serializedValue.getBytes()));
			final Map< ? , ? > propMap = (Map< ? , ? >) decoder.readObject();
			ScaPropertyUtil.restoreProperties(component, propMap);
			decoder.close();
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

	private static Map< ? , ? > storeProperties(final ScaPropertyContainer< ?, ? > component) {
		final Map<Object, Object> propMap = new HashMap<Object, Object>();
		for (final ScaAbstractProperty< ? > prop : component.getProperties()) {
			if (prop.isDefaultValue()) {
				continue;
			}
			Object value = null;
			if (prop instanceof ScaSimpleProperty) {
				value = ((ScaSimpleProperty) prop).getValue();
			} else if (prop instanceof ScaSimpleSequenceProperty) {
				value = ((ScaSimpleSequenceProperty) prop).getValue();
			} else if (prop instanceof ScaStructProperty) {
				value = ScaPropertyUtil.storeStruct((ScaStructProperty) prop);
			} else if (prop instanceof ScaStructSequenceProperty) {
				value = ScaPropertyUtil.storeStructSequence((ScaStructSequenceProperty) prop);
			}
			propMap.put(prop.getId(), value);
		}
		return propMap;
	}

	private static Map< ? , ? > storeStruct(final ScaStructProperty struct) {
		final HashMap<Object, Object> retVal = new HashMap<Object, Object>();
		for (final ScaSimpleProperty prop : struct.getSimples()) {
			if (prop.isDefaultValue()) {
				continue;
			}
			retVal.put(prop.getId(), prop.getValue());
		}
		for (final ScaSimpleSequenceProperty prop : struct.getSequences()) {
			if (prop.isDefaultValue()) {
				continue;
			}
			retVal.put(prop.getId(), prop.getValue());
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
			if (prop instanceof ScaSimpleProperty) {
				((ScaSimpleProperty) prop).setValue(value);
			} else if (prop instanceof ScaSimpleSequenceProperty) {
				((ScaSimpleSequenceProperty) prop).setValue((Object[]) value);
			} else if (prop instanceof ScaStructProperty) {
				ScaPropertyUtil.restoreStruct((ScaStructProperty) prop, (Map< ? , ? >) value);
			} else if (prop instanceof ScaStructSequenceProperty) {
				ScaPropertyUtil.restoreStructSequence((ScaStructSequenceProperty) prop, (List< ? >) value);
			}
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
			for (final ScaSimpleProperty prop : struct.getSimples()) {
				if (propMap.containsKey(prop.getId())) {
					final Object value = propMap.get(prop.getId());
					prop.setValue(value);
				}
			}
			for (final ScaSimpleSequenceProperty prop : struct.getSequences()) {
				if (propMap.containsKey(prop.getId())) {
					final Object[] value = (Object[]) propMap.get(prop.getId());
					prop.setValue(value);
				}
			}
		}
	}
}

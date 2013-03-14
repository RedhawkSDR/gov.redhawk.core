/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.sca.launch;

import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaAbstractProperty;
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
 * @since 8.0
 * 
 */
class ScaPropertyUtil {
	private static final String SIMPLE_SEQ_VALUES = "values";
	private static final String SIMPLE_VALUE = "value";

	private ScaPropertyUtil() {

	}

	public static void load(final ScaAbstractComponent< ? > component, final String serializedValue) {
		if (component != null && serializedValue != null) {
			final XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(serializedValue.getBytes()));
			final Map< ? , ? > propMap = (Map< ? , ? >) decoder.readObject();
			ScaPropertyUtil.restoreProperties(component, propMap);
			decoder.close();
		}
	}

	public static String save(final ScaAbstractComponent< ? > component) {
		if (component != null) {
			final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			try {
				XMLEncoder encoder = null;
				try {
					encoder = new XMLEncoder(buffer);
					final Map< ? , ? > propMap = ScaPropertyUtil.storeProperties(component);
					if (propMap.isEmpty()) {
						return null;
					}
					encoder.writeObject(propMap);
				} finally {
					if (encoder != null) {
						encoder.close();
					}
				}
				return new String(buffer.toByteArray());
			} finally {
		            try {
				if (buffer != null) {
	        	            buffer.close();
				}
	                    } catch (IOException e) {
        	            	// PASS
                	    }
			}
		} else {
			return null;
		}
	}

	private static Map< ? , ? > storeProperties(final ScaAbstractComponent< ? > component) {
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
		return retVal;
	}

	private static List< ? > storeStructSequence(final ScaStructSequenceProperty prop) {
		final ArrayList<Object> retVal = new ArrayList<Object>();
		for (final ScaStructProperty struct : prop.getStructs()) {
			retVal.add(ScaPropertyUtil.storeStruct(struct));
		}
		return retVal;
	}

	private static void restoreProperties(final ScaAbstractComponent< ? > component, final Map< ? , ? > propMap) {
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
		}
	}
}

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
package gov.redhawk.model.sca.commands;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.sca.util.PluginUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.util.AnyUtils;
import CF.DataType;
import CF.PropertiesHolder;

/**
 * @since 14.0
 */
public class SetPropertiesValuesCommand extends SetStatusCommand<ScaPropertyContainer< ? , ? >> {

	private PropertiesHolder propHolder;
	private List<AbstractProperty> defs;

	/**
	 * @since 19.0
	 */
	public SetPropertiesValuesCommand(ScaPropertyContainer< ? , ? > container, PropertiesHolder propHolder, List<AbstractProperty> defs) {
		super(container, ScaPackage.Literals.SCA_PROPERTY_CONTAINER__PROPERTIES, null);
		this.propHolder = propHolder;
		this.defs = defs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		if (propHolder.value != null && propHolder.value.length > 0) {
			final Map<String, ScaAbstractProperty< ? >> props = new HashMap<String, ScaAbstractProperty< ? >>();
			for (final ScaAbstractProperty< ? > prop : provider.getProperties()) {
				props.put(prop.getId(), prop);
			}

			for (final DataType dt : propHolder.value) {
				final ScaAbstractProperty< ? > prop = props.get(dt.id);
				if (prop != null) {
					if (prop.getDefinition() == null) {
						AbstractProperty def = getDefinition(prop.getId());
						((ScaAbstractProperty) prop).setDefinition(def);
					}
					prop.fromAny(dt.value);
				} else {
					ScaAbstractProperty< ? > newProp = createProperty(dt);

					if (newProp != null) {
						provider.getProperties().add(newProp);
					}

				}
			}
		}
		super.execute();
	}

	private AbstractProperty getDefinition(String id) {
		if (id == null) {
			return null;
		}
		for (AbstractProperty prop : defs) {
			if (PluginUtil.equals(prop.getId(), id)) {
				return prop;
			}
		}
		return null;
	}

	private static ScaAbstractProperty< ? > createProperty(DataType dt) {
		ScaAbstractProperty< ? > newProp = null;
		if (isSimple(dt)) {
			newProp = createSimple(dt);
		} else if (isStruct(dt)) {
			newProp = createStruct(dt);
		} else if (isSequence(dt)) {
			newProp = createSequence(dt);
		} else if (isStructSequence(dt)) {
			newProp = createStructSequence(dt);
		}
		return newProp;
	}

	private static ScaStructSequenceProperty createStructSequence(DataType dt) {
		ScaStructSequenceProperty retVal = ScaFactory.eINSTANCE.createScaStructSequenceProperty();
		retVal.setId(dt.id);
		retVal.fromAny(dt.value);
		return retVal;
	}

	private static boolean isStructSequence(DataType dt) {
		return AnyUtils.isStructSequence(dt);
	}

	private static ScaSimpleSequenceProperty createSequence(DataType dt) {
		ScaSimpleSequenceProperty retVal = ScaFactory.eINSTANCE.createScaSimpleSequenceProperty();
		retVal.setId(dt.id);
		retVal.fromAny(dt.value);
		return retVal;
	}

	private static boolean isSequence(DataType dt) {
		return AnyUtils.isSequence(dt);
	}

	private static ScaStructProperty createStruct(DataType dt) {
		ScaStructProperty retVal = ScaFactory.eINSTANCE.createScaStructProperty();
		retVal.setId(dt.id);
		retVal.fromAny(dt.value);
		return retVal;
	}

	private static boolean isStruct(DataType dt) {
		return AnyUtils.isStruct(dt);
	}

	private static boolean isSimple(DataType dt) {
		return AnyUtils.isSimple(dt);
	}

	private static ScaSimpleProperty createSimple(DataType dt) {
		ScaSimpleProperty retVal = ScaFactory.eINSTANCE.createScaSimpleProperty();
		retVal.setId(dt.id);
		retVal.fromAny(dt.value);
		return retVal;
	}

}

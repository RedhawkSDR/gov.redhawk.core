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

	@Override
	public void execute() {
		if (propHolder.value != null && propHolder.value.length > 0) {
			final Map<String, ScaAbstractProperty< ? >> props = new HashMap<String, ScaAbstractProperty< ? >>();
			for (final ScaAbstractProperty< ? > prop : provider.getProperties()) {
				props.put(prop.getId(), prop);
			}

			for (final DataType dt : propHolder.value) {
				ScaAbstractProperty< ? > prop = props.get(dt.id);
				if (prop != null) {
					if (prop.getDefinition() == null) {
						AbstractProperty def = getDefinition(prop.getId());
						if (def != null) {
							prop = setDefinition(prop, def);
						}
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

	/**
	 * Set the definition of the SCA model property to what is in the PRF. If there is a type mismatch, the property
	 * may be re-created.
	 * @param prop The model property
	 * @param def The PRF definition
	 * @return The model property (may be a different object than was passed in)
	 */
	@SuppressWarnings("unchecked")
	private ScaAbstractProperty< ? > setDefinition(ScaAbstractProperty< ? > prop, AbstractProperty def) {
		try {
			// Normally this will complete without exception
			((ScaAbstractProperty<AbstractProperty>) prop).setDefinition(def);
			return prop;
		} catch (ClassCastException e) {
			// This only occurs if a property definition initially isn't available and the SCA model property is
			// created based solely on the return value from query(). If the definition is later added to the SCA
			// model property, but the types don't agree (e.g. Simple vs. ScaStruct), the ClassCastException occurs.
			// This also implies that the resource is returning values from query() that don't agree with its PRF.
			provider.getProperties().remove(prop);
			ScaAbstractProperty< ? extends AbstractProperty> replacementProp = ScaFactory.eINSTANCE.createScaProperty(def);
			provider.getProperties().add(replacementProp);
			return replacementProp;
		}
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
		ScaAbstractProperty< ? > newProp;
		if (AnyUtils.isSimple(dt)) {
			newProp = ScaFactory.eINSTANCE.createScaSimpleProperty();
		} else if (AnyUtils.isStruct(dt)) {
			newProp = ScaFactory.eINSTANCE.createScaStructProperty();
		} else if (AnyUtils.isSequence(dt)) {
			newProp = ScaFactory.eINSTANCE.createScaSimpleSequenceProperty();
		} else if (AnyUtils.isStructSequence(dt)) {
			newProp = ScaFactory.eINSTANCE.createScaStructSequenceProperty();
		} else {
			return null;
		}
		newProp.setId(dt.id);
		newProp.fromAny(dt.value);
		return newProp;
	}
}

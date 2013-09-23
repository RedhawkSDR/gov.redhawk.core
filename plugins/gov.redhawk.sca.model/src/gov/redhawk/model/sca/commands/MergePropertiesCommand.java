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
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.impl.ScaSimplePropertyImpl;
import gov.redhawk.model.sca.impl.ScaSimpleSequencePropertyImpl;
import gov.redhawk.model.sca.impl.ScaStructPropertyImpl;
import gov.redhawk.model.sca.impl.ScaStructSequencePropertyImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;

/**
 * @since 14.0
 * 
 */
public class MergePropertiesCommand extends ScaModelCommand {

	private List<AbstractProperty> propertyDefs;
	private ScaPropertyContainer< ? , ? > container;

	/**
	 * @since 18.0
	 */
	public MergePropertiesCommand(ScaPropertyContainer< ? , ? > container, List<AbstractProperty> propertyDefs) {
		this.container = container;
		this.propertyDefs = propertyDefs;
	}

	@Override
	public void execute() {
		// New Properties
		final Map<String, ScaAbstractProperty< ? >> newProperties = new HashMap<String, ScaAbstractProperty< ? >>();
		if (propertyDefs != null) {
			for (AbstractProperty value : this.propertyDefs) {
				if (value instanceof AbstractProperty) {
					AbstractProperty prop = (AbstractProperty) value;
					ScaAbstractProperty< ? > scaProp = null;
					if (prop instanceof Simple) {
						final Simple simpleProp = (Simple) prop;
						scaProp = createSimpleScaProperty(simpleProp);
					} else if (prop instanceof SimpleSequence) {
						SimpleSequence simpleSequence = (SimpleSequence) prop;
						scaProp = createSimpleSequenceScaProperty(simpleSequence);
					} else if (prop instanceof Struct) {
						Struct struct = (Struct) prop;
						scaProp = createStructScaProperty(struct);
					} else if (prop instanceof StructSequence) {
						StructSequence structSequence = (StructSequence) prop;
						scaProp = createStructSequenceScaProperty(structSequence);
					}
					newProperties.put(prop.getId(), scaProp);
				}
			}
		}

		// Setup Current Property Map
		final Map<String, ScaAbstractProperty< ? >> currentProperties = new HashMap<String, ScaAbstractProperty< ? >>();
		for (ScaAbstractProperty< ? > property : container.getProperties()) {
			currentProperties.put(property.getId(), property);
		}

		// Setup remove properties map
		final Map<String, ScaAbstractProperty< ? >> removeProperties = new HashMap<String, ScaAbstractProperty< ? >>();
		removeProperties.putAll(currentProperties);
		removeProperties.keySet().removeAll(newProperties.keySet());

		// Remove Properties
		if (!removeProperties.isEmpty()) {
			container.getProperties().removeAll(removeProperties.values());
		}

		// Remove Duplicates
		newProperties.keySet().removeAll(currentProperties.keySet());

		// Add Properties
		if (!newProperties.isEmpty()) {
			container.getProperties().addAll(newProperties.values());
		}

		if (!container.isSetProperties()) {
			container.getProperties().clear();
		}
	}

	protected ScaStructProperty createStructScaProperty(final Struct value) {
		// END GENERATED CODE
		final ScaStructPropertyImpl prop = (ScaStructPropertyImpl) ScaFactory.eINSTANCE.createScaStructProperty();
		prop.setDefinition(value);
		return prop;
		// BEGIN GENERATED CODE
	}

	protected ScaSimpleSequenceProperty createSimpleSequenceScaProperty(final SimpleSequence value) {
		// END GENERATED CODE
		final ScaSimpleSequencePropertyImpl prop = (ScaSimpleSequencePropertyImpl) ScaFactory.eINSTANCE.createScaSimpleSequenceProperty();
		prop.setDefinition(value);
		return prop;
		// BEGIN GENERATED CODE
	}

	protected ScaSimpleProperty createSimpleScaProperty(final Simple simpleProp) {
		// END GENERATED CODE
		final ScaSimplePropertyImpl prop = (ScaSimplePropertyImpl) ScaFactory.eINSTANCE.createScaSimpleProperty();
		prop.setDefinition(simpleProp);
		return prop;
		// BEGIN GENERATED CODE
	}

	protected ScaStructSequenceProperty createStructSequenceScaProperty(final StructSequence structSequence) {
		// END GENERATED CODE
		final ScaStructSequencePropertyImpl prop = (ScaStructSequencePropertyImpl) ScaFactory.eINSTANCE.createScaStructSequenceProperty();
		prop.setDefinition(structSequence);
		return prop;
		// BEGIN GENERATED CODE
	}

}

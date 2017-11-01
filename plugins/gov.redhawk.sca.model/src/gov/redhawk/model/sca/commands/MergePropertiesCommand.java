/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.model.sca.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPropertyContainer;
import mil.jpeojtrs.sca.prf.AbstractProperty;

/**
 * @since 14.0
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
			for (AbstractProperty prop : this.propertyDefs) {
				ScaAbstractProperty< ? > scaProp = ScaFactory.eINSTANCE.createScaProperty(prop);
				newProperties.put(prop.getId(), scaProp);
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
}

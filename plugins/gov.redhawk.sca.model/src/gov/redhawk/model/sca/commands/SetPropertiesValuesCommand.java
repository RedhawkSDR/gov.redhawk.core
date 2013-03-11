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
package gov.redhawk.model.sca.commands;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPropertyContainer;

import java.util.HashMap;
import java.util.Map;

import CF.DataType;
import CF.PropertiesHolder;

/**
 * @since 14.0
 * 
 */
public class SetPropertiesValuesCommand extends SetStatusCommand<ScaPropertyContainer<?, ?>> {
	
	private PropertiesHolder propHolder;

	public SetPropertiesValuesCommand(ScaPropertyContainer<?, ?> container,  PropertiesHolder propHolder) {
		super(container, ScaPackage.Literals.SCA_PROPERTY_CONTAINER__PROPERTIES, null);
		this.propHolder = propHolder;
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		if (propHolder.value != null && propHolder.value.length > 0) {
			final Map<String, ScaAbstractProperty< ? >> props = new HashMap<String, ScaAbstractProperty< ? >>();
			for (final ScaAbstractProperty< ? > prop : provider.getProperties()) {
				props.put(prop.getId(), prop);
			}

			for (final DataType dt : propHolder.value) {
				final ScaAbstractProperty< ? > prop = props.get(dt.id);
				if (prop != null) {
					prop.fromAny(dt.value);
				}
			}
		}
		super.execute();
	}

}

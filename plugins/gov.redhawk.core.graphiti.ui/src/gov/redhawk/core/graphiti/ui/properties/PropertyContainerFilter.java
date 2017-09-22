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
package gov.redhawk.core.graphiti.ui.properties;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.IFilter;

import gov.redhawk.model.sca.ScaPropertyContainer;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;

/**
 * Selects only {@link ComponentInstantiation}s (representing a component/device/service) in design-time diagrams.
 * @see {@link PropertiesSection}
 */
public class PropertyContainerFilter implements IFilter {

	@Override
	public boolean select(Object toTest) {
		return Platform.getAdapterManager().getAdapter(toTest, ScaPropertyContainer.class) != null;
	}

}

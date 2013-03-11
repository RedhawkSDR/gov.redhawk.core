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
package gov.redhawk.sca.dcd.diagram.providers;

import gov.redhawk.diagram.providers.PartitioningLayoutProvider;
import gov.redhawk.sca.dcd.diagram.part.RedhawkDcdVisualIDRegistry;

/**
 * 
 */
public class DcdLayoutProvider extends PartitioningLayoutProvider {

	public DcdLayoutProvider() {
		super(RedhawkDcdVisualIDRegistry.INSTANCE);
	}

}

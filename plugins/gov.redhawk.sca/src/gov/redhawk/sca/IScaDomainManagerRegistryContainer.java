/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.sca;

import gov.redhawk.model.sca.ScaDomainManagerRegistry;

/**
 * @since 6.1
 */
public interface IScaDomainManagerRegistryContainer {

	/**
	 * Gets the Domain Manager registry.
	 * 
	 * @param context
	 *            The current Display or null. Required only in RAP, to ensure separate scope for
	 *            each user for persisting domain connection configuration information.
	 * @return the Domain Manager registry
	 */
	ScaDomainManagerRegistry getRegistry(Object context);
}

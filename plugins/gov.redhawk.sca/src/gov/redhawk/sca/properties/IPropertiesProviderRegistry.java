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
package gov.redhawk.sca.properties;

import java.util.List;

/**
 * Provides access to available {@link IPropertiesProviderDescriptor}.
 * 
 * @since 5.0
 * 
 */
public interface IPropertiesProviderRegistry {

	/**
	 * Gets the list of {@link IPropertiesProviderDescriptor}.
	 * 
	 * @return the list of {@link IPropertiesProviderDescriptor}
	 */
	List<IPropertiesProviderDescriptor> getPropertiesProvidersDescriptors();
	
	/**
	 * Gets the list of {@link IPropertiesProvider}.
	 * 
	 * @return the list of {@link IPropertiesProvider}
	 */
	List<IPropertiesProvider> getPropertiesProviders();

	/**
	 * Gets the name of the {@link IPropertiesProvider} as stored in the descriptor.
	 * 
	 * @param the IPropertiesProvider to obtain a name for
	 * @return the name of the {@link IPropertiesProvider}
	 */
	String getName(IPropertiesProvider provider);
}

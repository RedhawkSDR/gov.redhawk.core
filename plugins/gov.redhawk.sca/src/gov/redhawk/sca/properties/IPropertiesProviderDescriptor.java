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
package gov.redhawk.sca.properties;


/**
 * 
 * @since 5.0
 */
public interface IPropertiesProviderDescriptor {
	
	/**
	 * Gets the name for the provider.
	 * 
	 * @return the String name
	 */
	String getName();

	/**
	 * Gets the provider String id.
	 * 
	 * @return the String id
	 */
	String getId();

	/**
	 * Gets the {@link IPropertiesProvider}.
	 * 
	 * @return the {@link IPropertiesProvider}
	 */
	IPropertiesProvider getProvider();
}

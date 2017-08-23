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
 * Provides convenient access to {@link mil.jpeojtrs.sca.prf.Properties} via Categories.
 * @since 5.0
 */
public interface IPropertiesProvider {

	/**
	 * @return The name of the property provider
	 * @since 9.0
	 */
	String getName();

	/**
	 * @return A description of the property provider
	 * @since 9.0
	 */
	String getDescription();

	/**
	 * @return The plug-in ID containing the icon for the provider
	 * @since 9.0
	 */
	public String getIconPluginId();

	/**
	 * @return The path to the icon for the provider (within a plug-in)
	 * @since 9.0
	 */
	public String getIconPath();

	/**
	 * Returns a List of {@link gov.redhawk.sca.properties.Category} contributed by this provider.
	 * 
	 * @return the list of {@link gov.redhawk.sca.properties.Category}
	 */
	public List<Category> getCategories();

}

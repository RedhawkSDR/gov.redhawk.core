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

import java.util.List;

import mil.jpeojtrs.sca.prf.Properties;

import org.eclipse.emf.ecore.EObject;

/**
 * A Category contains a list of Properties and a list of other Categories and is used to provide Properties.
 * 
 * @since 5.0
 */
public interface Category {

	/**
	 * Gets the name of the {@link Category}.
	 * 
	 * @return the name of the category
	 */
	String getName();

	/**
	 * Gets the list of categories belonging to this {@link Category}.
	 * 
	 * @return a list of categories contained by this category
	 */
	List<Category> getCategories();

	/**
	 * Gets the list of properties belonging to this {@link Category}.
	 * 
	 * @return a list of properties contained by this category
	 */
	List<Properties> getProperties();

	/**
	 * Determines if the object is in this category's property list.
	 * 
	 * @param obj the object to check the property list for
	 * @return <code> true </code> if the object is contained in this category's properties; <code> false </code> otherwise
	 */
	boolean containsProperty(EObject obj);

}

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
package gov.redhawk.sca.ui;

import gov.redhawk.sca.ui.editors.IScaContentDescriber;

/**
 * @since 6.0
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IScaContentTypeRegistry {

	/**
	 * Find all the content types associated with the given input Object
	 * @param input The object to look for
	 * @return Array of strings of content type ids, or an empty array if none are found
	 */
	public String[] findContentTypes(final Object input);

	/**
	 * Gets the content type describer for the given content type.
	 * @param contentType
	 * @return The content type describer, may be null
	 */
	public IScaContentDescriber getDescriber(final String contentType);

	/**
	 * Find all the editors associated with a 
	 * @param contentType
	 * @return Array of editor ids, or empty array if there are none
	 */
	public String[] findEditors(final String contentType);

	/**
	 * Find the content type of the given object
	 * @param obj The object to find the default content type for, typically this is an Sca Model object
	 * @return The content type id, or null if none exists
	 */
	public String findContentType(final Object obj);

	/**
	 * Given a content type ID find the default editor ID 
	 * @param contentTypeId The content type ID associated with the editor
	 * @return The default editor for the given content type, null if none exists.
	 */
	public String findEditor(final String contentTypeId);

	/**
	 * Find the default editor of the given object
	 * @param obj The object to find the default editor for, typically this is an Sca Model object
	 * @return The content editor desc, or null if none exists
	 * @since 8.0
	 */
	public IScaEditorDescriptor getScaEditorDescriptor(Object obj);

	/**
	 * Find the default editor of the given object
	 * @param obj The object to find the default editor for, typically this is an Sca Model object
	 * @return The content editor desc, or null if none exists
	 * @since 8.0
	 */
	public IScaEditorDescriptor[] getAllScaEditorDescriptors(Object obj);

}

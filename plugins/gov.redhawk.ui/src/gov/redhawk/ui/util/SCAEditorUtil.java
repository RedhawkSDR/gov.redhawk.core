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
package gov.redhawk.ui.util;

import gov.redhawk.ui.editor.ScaFormPage;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * Provide utility methods for the SCAEditor.
 * 
 * @since 2.3
 */
public class SCAEditorUtil {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private SCAEditorUtil() {
	}

	/**
	 * Determines whether the specified resource is editable. For now, only
	 * resources with a platform URI are potentially editable.
	 * 
	 * @param page the {@link ScaFormPage} the resource belongs to
	 * @param resource the {@link Resource} to evaluate
	 * @return <code>true</code> if the resource is editable,
	 *         <code> false </code> otherwise
	 */
	public static boolean isEditableResource(ScaFormPage page, Resource resource) {
		boolean retVal = false;
		if (resource != null && !ScaFileSystemConstants.SCHEME.equals(resource.getURI().scheme())) {
			retVal = !page.getEditingDomain().isReadOnly(resource);
		}
		return retVal;
	}

}

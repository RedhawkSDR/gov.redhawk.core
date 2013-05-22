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
package gov.redhawk.diagram;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;

/**
 * @since 3.0
 */
public interface IDiagramUtilHelper {

	String getDiagramFileExtension();

	Map< ? , ? > getSaveOptions();

	String getModelId();

	PreferencesHint getDiagramPreferencesHint();

	EObject getRootDiagramObject(Resource resource);

	String getSemanticFileExtension();

	IFile getResource(Resource resource);

}

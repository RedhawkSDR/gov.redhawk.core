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
package gov.redhawk.sca.sad.diagram;

import gov.redhawk.diagram.IDiagramUtilHelper;
import gov.redhawk.model.sca.util.ModelUtil;

import java.util.Map;

import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.sad.diagram.part.SadDiagramEditorPlugin;
import mil.jpeojtrs.sca.sad.diagram.part.SadDiagramEditorUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;

public enum SadDiagramUtilHelper implements IDiagramUtilHelper {
	INSTANCE;

	public static final String SAD_FILE_EXTENSION = SadPackage.FILE_EXTENSION;
	public static final String SAD_DIAGRAM_FILE_EXTENSION = ".sad_diagramV2"; //$NON-NLS-1$

	@Override
	public String getDiagramFileExtension() {
		return SadDiagramUtilHelper.SAD_DIAGRAM_FILE_EXTENSION;
	}

	@Override
	public Map< ? , ? > getSaveOptions() {
		return SadDiagramEditorUtil.getSaveOptions();
	}

	@Override
	public String getModelId() {
		return mil.jpeojtrs.sca.sad.diagram.edit.parts.SoftwareAssemblyEditPart.MODEL_ID;
	}

	@Override
	public PreferencesHint getDiagramPreferencesHint() {
		return SadDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}

	@Override
	public EObject getRootDiagramObject(final Resource resource) {
		return SoftwareAssembly.Util.getSoftwareAssembly(resource);
	}

	@Override
	public String getSemanticFileExtension() {
		return SadDiagramUtilHelper.SAD_FILE_EXTENSION;
	}

	@Override
	public IFile getResource(final Resource resource) {
		return ModelUtil.getResource(resource);
	}

}

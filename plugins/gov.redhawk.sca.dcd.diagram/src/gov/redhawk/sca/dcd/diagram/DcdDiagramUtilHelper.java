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
package gov.redhawk.sca.dcd.diagram;

import gov.redhawk.diagram.IDiagramUtilHelper;
import gov.redhawk.model.sca.util.ModelUtil;

import java.util.Map;

import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DeviceConfigurationEditPart;
import mil.jpeojtrs.sca.dcd.diagram.part.DcdDiagramEditorPlugin;
import mil.jpeojtrs.sca.dcd.diagram.part.DcdDiagramEditorUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;

/**
 * 
 */
public enum DcdDiagramUtilHelper implements IDiagramUtilHelper {
	INSTANCE;

	public static final String FILE_EXTENSION = ".dcd.xml"; //$NON-NLS-1$
	public static final String DIAGRAM_FILE_EXTENSION = ".dcd_diagramV2"; //$NON-NLS-1$

	@Override
	public String getDiagramFileExtension() {
		return DIAGRAM_FILE_EXTENSION;
	}

	@Override
	public Map< ? , ? > getSaveOptions() {
		return DcdDiagramEditorUtil.getSaveOptions();
	}

	@Override
	public String getModelId() {
		return DeviceConfigurationEditPart.MODEL_ID;
	}

	@Override
	public PreferencesHint getDiagramPreferencesHint() {
		return DcdDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}

	@Override
	public EObject getRootDiagramObject(final Resource resource) {
		return DeviceConfiguration.Util.getDeviceConfiguration(resource);
	}

	@Override
	public String getSemanticFileExtension() {
		return FILE_EXTENSION;
	}

	@Override
	public IFile getResource(final Resource resource) {
		return ModelUtil.getResource(resource);
	}

}

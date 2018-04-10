/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.core.graphiti.dcd.ui.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;

import gov.redhawk.core.graphiti.ui.editor.IDiagramUtilHelper;
import mil.jpeojtrs.sca.dcd.DcdPackage;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;

public enum DcdDiagramUtilHelper implements IDiagramUtilHelper {
	INSTANCE;

	@Override
	public String getDiagramFileExtension() {
		return DcdPackage.DIAGRAM_FILE_EXTENSION;
	}

	@Override
	public Map< ? , ? > getSaveOptions() {
		HashMap<String, Object> saveOptions = new HashMap<String, Object>();
		saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		return saveOptions;
	}

	@Override
	public EObject getRootDiagramObject(Resource resource) {
		return DeviceConfiguration.Util.getDeviceConfiguration(resource);
	}

	@Override
	public String getSemanticFileExtension() {
		return DcdPackage.FILE_EXTENSION;
	}

	@Override
	public IFile getFile(Resource resource) {
		return WorkspaceSynchronizer.getFile(resource);
	}

}

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
package gov.redhawk.core.resourcefactory;

import gov.redhawk.core.filemanager.IFileManager;
import CF.InvalidFileName;
import CF.FileManagerPackage.InvalidFileSystem;
import CF.FileManagerPackage.MountPointAlreadyExists;

public interface IResourceFactoryRegistry {
	void addResourceFactory(ResourceDesc desc) throws MountPointAlreadyExists, InvalidFileName, InvalidFileSystem;

	void removeResourceFactory(ResourceDesc refID);

	IFileManager getFileManager();

	ResourceDesc getDescByID(String refID);

	ResourceDesc getDescByProfile(String profile);

	ResourceDesc[] getResources();

	void dispose();
}

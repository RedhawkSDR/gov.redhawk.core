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
package gov.redhawk.model.sca.commands;

import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import CF.FileSystem;

/**
 * @since 14.0
 * 
 */
public class SetDeviceManagerFileSystemCommand extends SetStatusCommand<ScaDeviceManager> {

	private FileSystem fileSys;

	public SetDeviceManagerFileSystemCommand(ScaDeviceManager provider, FileSystem fileSys) {
		super(provider, ScaPackage.Literals.SCA_DEVICE_MANAGER__FILE_SYSTEM, null);
		this.fileSys = fileSys;
	}

	@Override
	public void execute() {
		if (this.provider.getFileSystem() == null && fileSys != null) {
			this.provider.setFileSystem(ScaFactory.eINSTANCE.createScaDeviceManagerFileSystem());
		}
		if (fileSys == null) {
			this.provider.setFileSystem(null);
		} else {
			this.provider.getFileSystem().setCorbaObj(fileSys);
		}
		super.execute();
	}

}

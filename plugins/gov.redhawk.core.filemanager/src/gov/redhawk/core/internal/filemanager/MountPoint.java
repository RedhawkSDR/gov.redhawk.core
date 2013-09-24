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
package gov.redhawk.core.internal.filemanager;

import gov.redhawk.core.filemanager.filesystem.AbstractFileSystem.ScaFileInformationDataType;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.omg.CORBA.Any;
import org.omg.CORBA.TCKind;

import CF.DataType;
import CF.FileSystem;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.FileType;

public class MountPoint implements Node {
	private final FileSystem fileSystem;

	public MountPoint(final FileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}

	public FileSystem getFileSystem() {
		return this.fileSystem;
	}

	public String getProfilePath(final String refId) {
		return null;
	}

	@Override
	public DataType[] createDataTypeArray() {
		final Any readOnly = AnyUtils.toAny(true, TCKind.tk_boolean, false);

		return new DataType[] {
			new DataType(ScaFileInformationDataType.READ_ONLY.name(), readOnly)
		};

	}

	@Override
	public FileInformationType createFileInformationType() {
		final FileInformationType info = new FileInformationType();
		info.fileProperties = createDataTypeArray();
		info.kind = FileType.FILE_SYSTEM;
		return info;
	}

}
